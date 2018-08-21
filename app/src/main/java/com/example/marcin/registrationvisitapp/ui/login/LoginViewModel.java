package com.example.marcin.registrationvisitapp.ui.login;

import android.util.Log;

import com.example.marcin.registrationvisitapp.data.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference users = FirebaseDatabase.getInstance().getReference("/users");
    FirebaseUser user = mAuth.getCurrentUser();
    private Boolean isLogged;
    MutableLiveData<Boolean> b = new MutableLiveData<>();


    public MutableLiveData<Boolean> isUserLogged() {
        checkCurrentUserisLogged();
        b.setValue(isLogged);
        return b;
    }

    private void checkCurrentUserisLogged(){
        user = mAuth.getCurrentUser();
        isLogged = user != null;
    }

    public void createUserFirebase(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        User user = new User(email, password);
                        users.push().setValue(user);
                        isLogged = true;
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("onComplete", "createUserWithEmail:failure", task.getException());
                        isLogged = false;
                    }
                });
    }

    public void loginWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    isLogged = true;
                    isUserLogged();
                }
                if (task.isCanceled()) {
                    isLogged = false;
                    isUserLogged();
                }
            }
        });

    }

    public void logOut() {
        mAuth.signOut();
        isLogged = false;
        isUserLogged();
    }
}
