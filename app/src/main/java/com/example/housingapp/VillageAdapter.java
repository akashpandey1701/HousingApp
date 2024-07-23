package com.example.housingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VillageAdapter extends RecyclerView.Adapter<VillageAdapter.VillageViewHolder> {

    private List<Village> villageList;
    private Context context;

    public VillageAdapter(List<Village> villageList, Context context) {
        this.villageList = villageList;
        this.context = context;
    }

    @NonNull
    @Override
    public VillageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new VillageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VillageViewHolder holder, int position) {
        Village village = villageList.get(position);
        holder.headNameTextView.setText("मुखिया का नाम: " + village.getHeadName());
        holder.villageNameTextView.setText("ग्राम: " + village.getVillageName());
        holder.districtTextView.setText("जिला: " + village.getDistrict());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity2.class);
                // Pass any data you need to MainActivity2 using intent.putExtra()
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return villageList.size();
    }

    public static class VillageViewHolder extends RecyclerView.ViewHolder {
        TextView headNameTextView;
        TextView villageNameTextView;
        TextView districtTextView;
        Button syncButton;
        CardView cardView;

        public VillageViewHolder(@NonNull View itemView) {
            super(itemView);
            headNameTextView = itemView.findViewById(R.id.headNameTextView);
            villageNameTextView = itemView.findViewById(R.id.villageNameTextView);
            districtTextView = itemView.findViewById(R.id.districtTextView);
            syncButton = itemView.findViewById(R.id.syncButton);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
