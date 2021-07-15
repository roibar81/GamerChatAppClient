package com.example.gamerchatapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gamerchatapp.R;
import com.example.gamerchatapp.dm.Game;
import com.example.gamerchatapp.dm.Response;
import com.example.gamerchatapp.dm.User;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private final ArrayList<Game> gameList;
    private final ArrayList<User> friendsList;

    public CustomAdapter(ArrayList<Game> gameList, ArrayList<User> friendsList) {
        this.gameList = gameList;
        this.friendsList = friendsList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView itemCardView;
        TextView name_textView_item;
        ImageView imageView_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemCardView = itemView.findViewById(R.id.itemCardView);
            this.name_textView_item = itemView.findViewById(R.id.name_textView_item);
            this.imageView_item = itemView.findViewById(R.id.imageView_item);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        TextView itemName = holder.name_textView_item;
        ImageView imageView_item = holder.imageView_item;
        CardView gameCardView = holder.itemCardView;

        if(gameList != null) {
            itemName.setText(gameList.get(position).getName());

            int id = holder.itemView.getResources()
                    .getIdentifier(
                            gameList.get(position).getName(),
                            "drawable", null);
            imageView_item.setImageResource(gameList.get(position).getImage());
            Log.d("draw", String.valueOf(R.drawable.warcraft));
            Log.d("draw2", String.valueOf(R.drawable.gtav));
            Log.d("draw3", String.valueOf(R.drawable.fifa22));
            Log.d("draw4", String.valueOf(R.drawable.nba_2k));
            Log.d("draw5", String.valueOf(R.drawable.heroes3));
        }
        if(friendsList != null) {
            itemName.setText(friendsList.get(position).getName());
            imageView_item.setImageResource(R.drawable.usericon);
        }
    }

    @Override
    public int getItemCount() {
        return gameList != null ? gameList.size() : friendsList.size();
    }

}
