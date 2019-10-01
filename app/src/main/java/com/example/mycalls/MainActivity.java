package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private ProgressBar loadingBar;
    private RecyclerView mRecyclerView;
    private CallsAdapter callsAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<CallsModel> data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        callsAdapter = new CallsAdapter();//MainActivity.this,null
        mRecyclerView.setAdapter(callsAdapter);
        MInterface mInterface = ApiClient.getClient().create(MInterface.class);
        Call<CallResult> call = mInterface.getCalls();
        call.enqueue(new Callback<CallResult>() {
            @Override
            public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                callsAdapter.setData(response.body().getCalls());
                mRecyclerView.setAdapter(callsAdapter);
                loadingBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CallResult> call, Throwable t) {
                loadingBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                }, 5000);
            }

        });

    }

}
