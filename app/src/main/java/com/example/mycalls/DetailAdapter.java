package com.example.mycalls;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {
    private ArrayList<CallsModel> data = new ArrayList();

    public void setData(List<CallsModel> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailAdapter.DetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View listItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item, viewGroup, false);
        return new DetailAdapter.DetailHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.DetailHolder holder, int i) {
        holder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class DetailHolder extends RecyclerView.ViewHolder {
        TextView caldiText;
        TextView cevaplandiText;
        TextView cekildiText;
        TextView donulduText;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(final CallsModel data) {

            caldiText = itemView.findViewById(R.id.caldıText);
            cevaplandiText = itemView.findViewById(R.id.cevaplandıText);
            cekildiText = itemView.findViewById(R.id.cekildiText);
            donulduText = itemView.findViewById(R.id.donulduText);
            caldiText.setText(data.caldi);
            cevaplandiText.setText(data.cevaplandi);
            cekildiText.setText(data.cekildi);
            donulduText.setText(data.donuldu);

        }
    }
}