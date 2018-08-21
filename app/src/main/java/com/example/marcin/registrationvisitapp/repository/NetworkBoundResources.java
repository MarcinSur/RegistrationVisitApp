package com.example.marcin.registrationvisitapp.repository;

import com.example.marcin.registrationvisitapp.data.Resources;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class NetworkBoundResources<ResultType, RequestType> {

    private final MediatorLiveData<Resources<ResultType>> result = new MediatorLiveData<>();

    public NetworkBoundResources() {
        result.setValue(Resources.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data->{
            result.removeSource(dbSource);
            if(shouldFetch(data)){
                fetchFromNetwork(dbSource);
            }else{
                result.addSource(dbSource, newData ->
                    setValue(Resources.success(newData)));
            }
        });
    }

    private void setValue(Resources<ResultType> newValue) {
//        if (!Objects.equals(result.getValue(), newValue)) {
//            result.setValue(newValue);
//        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {

    }

    protected abstract boolean shouldFetch(@Nullable ResultType data);

    protected abstract LiveData<ResultType> loadFromDb();

    public LiveData<Resources<ResultType>> asLiveData() {
        return result;
    }

    class ApiResponse<T>{
        
    }
}
