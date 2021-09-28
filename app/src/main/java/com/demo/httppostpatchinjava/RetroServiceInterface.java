package com.demo.httppostpatchinjava;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetroServiceInterface {

    @POST("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c"})
    Call<UserResponse> createUser(@Body User params);

    @GET("users/{user_id}")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c"})
    Call<UserResponse> getUserData(@Path ("user_id")String user_id);

    @PATCH("users/{user_id}")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 73668350bdf06c66f3388408c1a712b378c3e25da599753b21b664a6261e246c"})
    Call<UserResponse> updateUserData(@Path ("user_id")String user_id, @Body User params);

}
