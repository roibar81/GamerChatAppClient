package com.example.gamerchatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;
import com.example.gamerchatapp.dm.Response;
import com.example.gamerchatapp.dm.User;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyFriendsViewHolder> {

    private final ArrayList<User> friendsList;

    public FriendsAdapter(Response response) {
        this.friendsList = response.getBody().getUser().getFriends();
    }

    public static class MyFriendsViewHolder extends RecyclerView.ViewHolder {
        CardView itemCardView;
        TextView name_textView_item;
        ImageView imageView_item;

        public MyFriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemCardView = itemView.findViewById(R.id.itemCardView);
            this.name_textView_item = itemView.findViewById(R.id.name_textView_item);
            this.imageView_item = itemView.findViewById(R.id.imageView_item);
        }
    }

    @NonNull
    @Override
    public FriendsAdapter.MyFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        FriendsAdapter.MyFriendsViewHolder myViewHolder = new FriendsAdapter.MyFriendsViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.MyFriendsViewHolder holder, int position) {
        TextView itemName = holder.name_textView_item;
        ImageView imageView_item = holder.imageView_item;
        CardView itemCardView = holder.itemCardView;

        itemName.setText(friendsList.get(position).getName());
        imageView_item.setImageResource(R.drawable.usericon);
        itemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}
