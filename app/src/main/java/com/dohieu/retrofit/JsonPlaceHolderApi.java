package com.dohieu.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("/api/users")
    Call<User> getUser();

    @GET("api/v1/employees")
    Call<List<Employee>> getmployee();

}
