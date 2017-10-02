package com.world.mvp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.world.mvp.R;
import com.world.mvp.models.Repo;
import com.world.mvp.repos.IRepoListView;
import com.world.mvp.repos.RepoAdapter;
import com.world.mvp.repos.RepoListPresenter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RepoListFragment extends Fragment implements IRepoListView {

    public RepoListFragment() {}

    @Bind(R.id.fragment_repo_list_view)
    ListView listView;

    private RepoListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RepoListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadCommits("ahmed0123");
    }

    @Override
    public void onReposLoadedSuccess(List<Repo> list, Response response) {
        listView.setAdapter(new RepoAdapter(getActivity(), list));
    }

    @Override
    public void onReposLoadedFailure(RetrofitError error) {
        Toast.makeText(getActivity(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }
}
