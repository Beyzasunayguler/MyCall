package com.example.mycalls;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.CallsHolder> {

    private ArrayList<CallsModel> data = new ArrayList();
    private ProgressBar loadingBar;
    private RecyclerView mRecyclerView;
    private DetailAdapter detailAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    LinearLayout linearLayout;

    public void setData(List<CallsModel> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CallsAdapter.CallsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new CallsHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CallsAdapter.CallsHolder callsHolder, final int i) {
        callsHolder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CallsHolder extends RecyclerView.ViewHolder {
        TextView mIdText;
        TextView kullaniciText;
        TextView telnoText;
        TextView zamanText;
        TextView firmaAdiText;
        TextView subeText;
        TextView kapamaZamanText;


        public CallsHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(final CallsModel data) {
            mIdText = itemView.findViewById(R.id.id);
            kullaniciText = itemView.findViewById(R.id.textView);
            telnoText = itemView.findViewById(R.id.telnoText);
            zamanText = itemView.findViewById(R.id.zamanText);
            firmaAdiText = itemView.findViewById(R.id.firmaAdiText);
            subeText = itemView.findViewById(R.id.subeText);
            kapamaZamanText = itemView.findViewById(R.id.kapamaZamanText);

            if ("1".equals(data.cevaplandi)) {
                mIdText.setTextColor(Color.parseColor("#738b28"));
                kullaniciText.setTextColor(Color.parseColor("#738b28"));
                telnoText.setTextColor(Color.parseColor("#738b28"));
                zamanText.setTextColor(Color.parseColor("#738b28"));
                firmaAdiText.setTextColor(Color.parseColor("#738b28"));
                subeText.setTextColor(Color.parseColor("#738b28"));
                kapamaZamanText.setTextColor(Color.parseColor("#738b28"));
            }

            if ("1".equals(data.donuldu)) {
                mIdText.setTextColor(Color.parseColor("#FF5722"));
                kullaniciText.setTextColor(Color.parseColor("#FF5722"));
                telnoText.setTextColor(Color.parseColor("#FF5722"));
                zamanText.setTextColor(Color.parseColor("#FF5722"));
                firmaAdiText.setTextColor(Color.parseColor("#FF5722"));
                subeText.setTextColor(Color.parseColor("#FF5722"));
                kapamaZamanText.setTextColor(Color.parseColor("#FF5722"));


            } else if ("0".equals(data.cevaplandi) && "0".equals(data.donuldu)) {
                mIdText.setTextColor(Color.parseColor("#FF0000"));
                kullaniciText.setTextColor(Color.parseColor("#FF0000"));
                telnoText.setTextColor(Color.parseColor("#FF0000"));
                zamanText.setTextColor(Color.parseColor("#FF0000"));
                firmaAdiText.setTextColor(Color.parseColor("#FF0000"));
                subeText.setTextColor(Color.parseColor("#FF0000"));
                kapamaZamanText.setTextColor(Color.parseColor("#FF0000"));
            }
            mIdText.setText(data.id);
            kullaniciText.setText(data.kullanici);
            telnoText.setText(data.telNo);
            zamanText.setText(data.aramaZaman);
            firmaAdiText.setText(data.firmaAdi);
            subeText.setText(data.subeAdi);
            kapamaZamanText.setText(data.kapatZaman);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    String calculatedDate = day + "." + (month + 1) + "." + (year);
                    intent.putExtra("infoKey", calculatedDate);
                    Toast.makeText(itemView.getContext(), calculatedDate, Toast.LENGTH_SHORT).show();
                    itemView.getContext().startActivity(intent);
                    return true;
                }
            });


        }
    }

}
