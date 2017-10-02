package com.world.mvp.repos;

import java.util.List;

import com.world.mvp.models.Repo;
import retrofit.RetrofitError;
import retrofit.client.Response;


public interface IRepoListView {
    void onReposLoadedSuccess(List<Repo> list, Response response);
    void onReposLoadedFailure(RetrofitError error);
}
