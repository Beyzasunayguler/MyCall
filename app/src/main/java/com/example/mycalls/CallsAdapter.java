package com.example.mycalls;


import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.CallsHolder> {

    private ArrayList<CallsModel> data = new ArrayList();

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


        public CallsHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(final CallsModel data) {
            mIdText=itemView.findViewById(R.id.id);
            kullaniciText = itemView.findViewById(R.id.textView);
            telnoText = itemView.findViewById(R.id.telnoText);
            zamanText = itemView.findViewById(R.id.zamanText);
            if ("0".equals(data.kullanici)) {
                mIdText.setTextColor(Color.parseColor("#738b28"));
               kullaniciText.setTextColor(Color.parseColor("#738b28"));
                telnoText.setTextColor(Color.parseColor("#738b28"));
                zamanText.setTextColor(Color.parseColor("#738b28"));

            } else {
                mIdText.setTextColor(Color.BLACK);
                kullaniciText.setTextColor(Color.BLACK);
                telnoText.setTextColor(Color.BLACK);
                zamanText.setTextColor(Color.BLACK);
            }
            mIdText.setText(data.id);
            kullaniciText.setText(data.kullanici);
            telnoText.setText(data.telNo);
            zamanText.setText(data.zaman);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), InfoActivity.class);
                    itemView.getContext().startActivity(intent);
                    return true;
                }
            });


        }
    }

}
