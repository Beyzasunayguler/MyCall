package com.example.mycalls;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoHolder> {
    private ArrayList<CallsModel> data = new ArrayList();

    public void setData(List<CallsModel> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InfoAdapter.InfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View listItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_info, viewGroup, false);
        return new InfoAdapter.InfoHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoHolder holder, int i) {
        holder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class InfoHolder extends RecyclerView.ViewHolder {
        TextView cevaplandiText;
        TextView mIdText;
        TextView kullaniciText;
        TextView telnoText;
        TextView zamanText;
        TextView mesajText;

        public InfoHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(CallsModel data) {
            mIdText=itemView.findViewById(R.id.idInfoText);
            kullaniciText = itemView.findViewById(R.id.kullanıcıInfoText);
            mesajText=itemView.findViewById(R.id.mesajText);
            telnoText = itemView.findViewById(R.id.telnoInfoText);
            zamanText = itemView.findViewById(R.id.zamanInfoText);
            cevaplandiText = itemView.findViewById(R.id.cevaplandıInfoText);
            mIdText.setText(data.id);
            kullaniciText.setText(data.kullanici);
            telnoText.setText(data.telNo);
            zamanText.setText(data.zaman);
            cevaplandiText.setText(data.cevaplandi);
            mesajText.setText(data.mesaj);

        }
    }
}
