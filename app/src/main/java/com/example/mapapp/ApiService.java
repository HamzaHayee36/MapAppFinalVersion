package com.example.mapapp;

import com.google.gson.annotations.SerializedName;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("student/login/")
    Call<UserResponse> login(@Field("email") String email, @Field("password") String password);
}

class UserResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("username")
    private String username;

    // If there are more fields in the response, you can add them with the @SerializedName annotation.

    // Getter and setter for token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Add more getters and setters if there are more fields.
}
