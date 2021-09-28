package com.demo.httppostpatchinjava;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<UserResponse> createNewUserLiveData;
    private MutableLiveData<UserResponse> loadUserData;

    public MainActivityViewModel() {
        createNewUserLiveData = new MutableLiveData<>();
        loadUserData = new MutableLiveData<>();
    }

    public MutableLiveData<UserResponse> getCreateUserObserver(){
        return createNewUserLiveData;
    }

    public MutableLiveData<UserResponse> getLoadUserObserver(){
        return loadUserData;
    }

    public void createNewUser(User user) {
        RetroServiceInterface retroServiceInterface = RetroInstance.getRetroInstance().create(RetroServiceInterface.class);
        Call<UserResponse> call =   retroServiceInterface.createUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    createNewUserLiveData.postValue(response.body());
                } else {
                    createNewUserLiveData.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                createNewUserLiveData.postValue(null);
            }
        });
    }

    public void loadUserData(String user_id) {
        RetroServiceInterface retroServiceInterface = RetroInstance.getRetroInstance().create(RetroServiceInterface.class);
        Call<UserResponse> call =   retroServiceInterface.getUserData(user_id);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    loadUserData.postValue(response.body());
                } else {
                    loadUserData.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                loadUserData.postValue(null);
            }
        });
    }

    public void updateUserData(User user, String user_id) {
        RetroServiceInterface retroServiceInterface = RetroInstance.getRetroInstance().create(RetroServiceInterface.class);
        Call<UserResponse> call =   retroServiceInterface.updateUserData(user_id, user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    createNewUserLiveData.postValue(response.body());
                } else {
                    createNewUserLiveData.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                createNewUserLiveData.postValue(null);
            }
        });
    }
}
