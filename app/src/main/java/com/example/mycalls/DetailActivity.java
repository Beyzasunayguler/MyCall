package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ProgressBar loadingBar;
    private RecyclerView mRecyclerView;
    private DetailAdapter detailAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        linearLayout = (LinearLayout) findViewById(R.id.fragment_detail_grid_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        detailAdapter = new DetailAdapter();//MainActivity.this,null
        mRecyclerView.setAdapter(detailAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MInterface mInterface = ApiClient.getClient().create(MInterface.class);
                Call<CallResult> call = mInterface.getCalls();
                call.enqueue(new Callback<CallResult>() {
                    @Override
                    public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                        detailAdapter.setData(response.body().getCalls());
                        mRecyclerView.setAdapter(detailAdapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                        loadingBar.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<CallResult> call, Throwable t) {
                        loadingBar.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getApplicationContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
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
                detailAdapter.setData(response.body().getCalls());
                mRecyclerView.setAdapter(detailAdapter);
                loadingBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CallResult> call, Throwable t) {
                loadingBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}
