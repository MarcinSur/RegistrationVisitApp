package com.example.marcin.registrationvisitapp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.marcin.registrationvisitapp.ui.login.LoginViewModel;
import com.example.marcin.registrationvisitapp.utilities.GravitySnapHelper;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.marcin.registrationvisitapp.data.Visit;
import com.example.marcin.registrationvisitapp.ui.viewmodels.VisitViewModel;
import com.example.marcin.registrationvisitapp.ui.VisitDialog;
import com.google.firebase.database.DataSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class VisitFragment extends Fragment implements VisitDialogListener {

    private VisitViewModel mViewModel;
    VisitDialog dialog = new VisitDialog();
    private RecyclerView mRecyclerView;
    private VisitsAdapter mVisitsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar progressBar;
    private ImageButton addImage;
    private ImageView avatar;
    private BottomNavigationView bottomNavigationView;
    BottomAppBar bottomAppBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.visit_fragment_v2, container, false);

        dialog.setupVisitDialogListeners(this);
        progressBar = view.findViewById(R.id.progressBar);
        bottomNavigationView = view.findViewById(R.id.bottomAppBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.action_favorites:
                    break;

                case R.id.action_music:
                    NavHostFragment.findNavController(this).navigate(R.id.likes);
                    break;

            }
            return true;
        });

//        bottomAppBar = view.findViewById(R.id.bottomAppBar);
//        bottomAppBar.replaceMenu(R.menu.bottom_menu_items);

        FloatingActionButton button = view.findViewById(R.id.floatingActionButton);
        button.setOnClickListener(view1 -> dialog.show(getFragmentManager(), "VisitDialogFragment"));

        avatar = view.findViewById(R.id.imageView2);

        addImage = view.findViewById(R.id.buttonAddImage);
        addImage.setOnClickListener(view1 -> {
            OpenGallery();
        });

        ImageButton loginButton = view.findViewById(R.id.imageButton);
        loginButton.setOnClickListener(view12 -> ViewModelProviders.of(this).get(LoginViewModel.class).logOut());
        mRecyclerView = view.findViewById(R.id.visit_recyclerview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mVisitsAdapter = new VisitsAdapter(getActivity());
        mRecyclerView.setAdapter(mVisitsAdapter);
        mViewModel = ViewModelProviders.of(this).get(VisitViewModel.class);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== 1 && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            Bitmap bmp;
            try {
                Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), fullPhotoUri);
                bmp = Bitmap.createScaledBitmap(bitmap,50,50,false);
                avatar.setImageBitmap(bmp);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.isUserLogged().observe(this, isLogged -> {
            if(!isLogged){
                NavHostFragment.findNavController(this).navigate(R.id.action_visitFragment_to_loginFragment6);
                Log.w("onCreateView",isLogged.toString());
            }
        });
        mViewModel.visits.observe(this, visits -> {
            mVisitsAdapter.addAll(visits);
            progressBar.setVisibility(View.GONE);
        });
    }


    @Override
    public void onDialogSaveClick(Visit visit) {
        mViewModel.saveToFirebase(visit);
        dialog.getDialog().cancel();
        Toast.makeText(getContext(),"zapisano " + visit.getName(),Toast.LENGTH_SHORT).show();
        Snackbar.make(getView(), "Zapisano", Snackbar.LENGTH_SHORT).show();
    }

    private void OpenGallery(){
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        startActivityForResult(cameraIntent, 1);
    }
}
