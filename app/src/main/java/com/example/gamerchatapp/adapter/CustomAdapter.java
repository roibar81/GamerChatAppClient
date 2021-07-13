package com.example.gamerchatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamerchatapp.R;
import com.example.gamerchatapp.dm.Game;

import java.io.File;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private ArrayList<Game> gameList;

    public CustomAdapter(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView gameCardView;
        TextView textViewGameName;
        ImageView gameImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.gameCardView = itemView.findViewById(R.id.game_cardView);
            this.textViewGameName = itemView.findViewById(R.id.game_textViewGame);
            this.gameImageView = itemView.findViewById(R.id.gameImageView);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewGameName;
        ImageView gameImageView = holder.gameImageView;
        CardView gameCardView = holder.gameCardView;

        textViewName.setText(gameList.get(position).getName());

        Glide.with(holder.gameImageView.getContext())
                .load(new File(String.valueOf(gameList.get(position).getImageBlob())))
                .into(gameImageView);

//        gameImageView.setImageResource(Integer.parseInt(gameList.get(position).getImageBlob()));
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notifyItemRemoved(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

}
