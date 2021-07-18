package com.example.gamerchatapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gamerchatapp.R;
import com.example.gamerchatapp.dm.Messages;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyMessageViewHolder>{

    private final ArrayList<Messages> messageList;

    public MessageAdapter(ArrayList<Messages> messageList) {
        this.messageList = messageList;
    }

    public static class MyMessageViewHolder extends RecyclerView.ViewHolder {

        CardView messageCardView;
        TextView name_textView;
        TextView message_textView;

        public MyMessageViewHolder(@NonNull View messageView) {
            super(messageView);
            this.messageCardView = messageView.findViewById(R.id.messageCardView);
            this.name_textView = messageView.findViewById(R.id.name_textView_message);
            this.message_textView = messageView.findViewById(R.id.message_textView_mesage);
        }
    }

    @NonNull
    @Override
    public MessageAdapter.MyMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout, parent, false);
        MyMessageViewHolder myMessageViewHolder = new MessageAdapter.MyMessageViewHolder(view);
        return myMessageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyMessageViewHolder holder, int position) {
        CardView messageCardView = holder.messageCardView;
        TextView name_textView = holder.name_textView;
        TextView message_textView = holder.message_textView;

        name_textView.setText(messageList.get(position).getUser_name() + ": ");
        message_textView.setText(messageList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


}
