package com.example.mycalls;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private ProgressBar loadingBar;
    private RecyclerView mRecyclerView;
    private CallsAdapter callsAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayout gridLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mainView.findViewById(R.id.swipe_refresh_layout);
        gridLayout=(GridLayout) mainView.findViewById(R.id.fragment_main_grid_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadingBar = (ProgressBar) mainView.findViewById(R.id.loadingBar);
        callsAdapter = new CallsAdapter();//MainActivity.this,null
        mRecyclerView.setAdapter(callsAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MInterface mInterface = ApiClient.getClient().create(MInterface.class);
                Call<CallResult> call = mInterface.getCalls();
                call.enqueue(new Callback<CallResult>() {
                    @Override
                    public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                        callsAdapter.setData(response.body().getCalls());
                        mRecyclerView.setAdapter(callsAdapter);
                        loadingBar.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setRefreshing(false);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        gridLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<CallResult> call, Throwable t) {
                        loadingBar.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
            }
        });
        MInterface mInterface = ApiClient.getClient().create(MInterface.class);
        Call<CallResult> call = mInterface.getCalls();
        call.enqueue(new Callback<CallResult>() {
            @Override
            public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                callsAdapter.setData(response.body().getCalls());
                mRecyclerView.setAdapter(callsAdapter);
                loadingBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CallResult> call, Throwable t) {
                loadingBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
        return mainView;
    }
}

