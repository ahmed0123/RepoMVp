package com.world.mvp;

import java.util.List;

import com.world.mvp.models.Repo;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;



public interface GithubService {
    @GET("/users/{user}/repos")
    void listRepos(@Path("user") String user, Callback<List<Repo>> callback);
}