package com.example.gamerchatapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;
import com.example.gamerchatapp.adapter.MessageAdapter;
import com.example.gamerchatapp.dm.Messages;
import com.example.gamerchatapp.dm.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {

    private MessageAdapter messageAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private Response response;
    private Messages messageObject;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Bundle bundle = getArguments();
        if(bundle != null){
            response = bundle.getParcelable("res");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        TextView chat_name_textView = (TextView) view.findViewById(R.id.chat_id_textView);
        chat_name_textView.setText(response.getBody().getChatRoom().getName());
        EditText message_textView = (EditText) view.findViewById(R.id.message_editText_cr);
        Button b_send = (Button) view.findViewById(R.id.send_button);
        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = message_textView.getText().toString();
                messageObject = new Messages(response.getBody().getChatRoom().getChatRoom_id(), response.getBody().getUser().getName(), message);
                //response.getBody().getMessage().setChat_room_id(response.getBody().getChatRoom().getChatRoom_id());
                //response.getBody().getMessage().setMessage(message);
                //response.getBody().getMessage().setUser_name(response.getBody().getUser().getName());
                response.getBody().setMessage(messageObject);
                message_textView.setText("");
                ((MainActivity) getActivity()).sendMessage(response);
            }
        });

        recyclerView = view.findViewById(R.id.messages_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        messageAdapter = new MessageAdapter(response.getBody().getMessageList());
        recyclerView.setAdapter(messageAdapter);
        return view;
    }
}