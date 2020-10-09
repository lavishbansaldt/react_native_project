package com.example.reactnativepoc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceholderAPI {



    @GET("comments")
    Call<List<Comment>> getComments();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") Integer postId);




}