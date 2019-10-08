package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
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

public class InfoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ProgressBar loadingBar;
    private InfoAdapter infoAdapter;
    private LinearLayout linearLayout;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        linearLayout = (LinearLayout) findViewById(R.id.fragment_info_grid_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        infoAdapter = new InfoAdapter();
        mRecyclerView.setAdapter(infoAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MInterface mInterface = ApiClient.getClient().create(MInterface.class);
                Call<CallResult> call = mInterface.getInfo();
                call.enqueue(new Callback<CallResult>() {
                    @Override
                    public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                        infoAdapter.setData(response.body().getInfos());
                        mRecyclerView.setAdapter(infoAdapter);
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
        Call<CallResult> call = mInterface.getInfo();
        call.enqueue(new Callback<CallResult>() {
            @Override
            public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                infoAdapter.setData(response.body().getInfos());
                mRecyclerView.setAdapter(infoAdapter);
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
