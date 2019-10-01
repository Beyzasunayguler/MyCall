package com.example.mycalls;

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
        TextView kullaniciText;
        TextView telnoText;
        TextView zamanText;
        TextView caldiText;
        TextView cevaplandiText;
        TextView cekildiText;
        TextView donulduText;

        public CallsHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(final CallsModel data) {
            kullaniciText = itemView.findViewById(R.id.textView);
            //telnoText = itemView.findViewById(R.id.telnoText);
           // zamanText = itemView.findViewById(R.id.zamanText);
            //caldiText = itemView.findViewById(R.id.caldıText);
            //cevaplandiText = itemView.findViewById(R.id.cevaplandıText);
            //cekildiText = itemView.findViewById(R.id.cekildiText);
            //donulduText = itemView.findViewById(R.id.donulduText);
            kullaniciText.setText(data.kullanici);
            //telnoText.setText(data.telNo);
            //zamanText.setText(data.zaman);
            //caldiText.setText(data.caldi);
            //cevaplandiText.setText(data.cevaplandi);
            //cekildiText.setText(data.cekildi);
            //donulduText.setText(data.donuldu);
        }
    }

}
