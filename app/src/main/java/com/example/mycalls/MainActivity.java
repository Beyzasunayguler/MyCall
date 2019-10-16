package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;


public class MainActivity extends AppCompatActivity {
    private ProgressBar loadingBar;
    private RecyclerView mRecyclerView;
    private CallsAdapter callsAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayout gridLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        gridLayout = (GridLayout) findViewById(R.id.fragment_main_grid_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        callsAdapter = new CallsAdapter();//MainActivity.this,null
        mRecyclerView.setAdapter(callsAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        String date = day + "." + (month + 1) + "." + (year);
        //Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DATE);
                String date = day + "." + (month + 1) + "." + (year);
                //Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();
                MInterface mInterface = ApiClient.getClient().create(MInterface.class);
                Call<CallResult> call = mInterface.getCallsWithDate(date);
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
                        Toast.makeText(getApplicationContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
            }
        });

        MInterface mInterface = ApiClient.getClient().create(MInterface.class);
        Call<CallResult> call = mInterface.getCallsWithDate(date);
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
                Toast.makeText(getApplicationContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String calculatedDate = dayOfMonth + "." + (month + 1) + "." + (year);
                MInterface mInterface = ApiClient.getClient().create(MInterface.class);
                Call<CallResult> call = mInterface.getCallsWithDate(calculatedDate);
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
                        Toast.makeText(getApplicationContext(), "Something went wrong \n" + t.getLocalizedMessage() + " url: " + call.request().url(), Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
                //Toast.makeText(MainActivity.this, calculatedDate, Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        datePickerDialog.show();
        return super.onOptionsItemSelected(item);

    }
}
