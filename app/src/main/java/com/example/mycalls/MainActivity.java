package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
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
    private ArrayList<CallsModel> data = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        callsAdapter= new CallsAdapter(MainActivity.this,null);
        MInterface mInterface=ApiClient.getClient().create(MInterface.class);
        Call<List<CallsModel>> call=mInterface.getCalls();
        call.enqueue(new Callback<List<CallsModel>>() {
            @Override
            public void onResponse(Call<List<CallsModel>> call, Response<List<CallsModel>> response) {
                callsAdapter.setData(response.body());
                mRecyclerView.setAdapter(callsAdapter);
                loadingBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<CallsModel>> call, Throwable t) {
                loadingBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Something went wrong, check the logs", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }
}
