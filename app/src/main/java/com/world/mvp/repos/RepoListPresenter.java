package com.world.mvp.repos;

import java.util.List;

import com.world.mvp.models.Repo;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class RepoListPresenter implements IRepoListPresenter, OnRepoInteractorFinishedListener {

    private IRepoListView view;
    private RepoListInteractor interactor;

    public RepoListPresenter(IRepoListView view) {
        this.view = view;
        this.interactor = new RepoListInteractor(this); // pass in the InteractorListener
    }

    @Override
    public void loadCommits(String username) {
        interactor.loadRecentCommits(username);
    }

    @Override
    public void onNetworkSuccess(List<Repo> list, Response response) {
        view.onReposLoadedSuccess(list, response);
    }

    @Override
    public void onNetworkFailure(RetrofitError error) {
        view.onReposLoadedFailure(error);
    }
}
