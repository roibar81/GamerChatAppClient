package com.example.gamerchatapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;
import com.example.gamerchatapp.adapter.CustomAdapter;
import com.example.gamerchatapp.dm.Game;
import com.example.gamerchatapp.dm.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {


    private static CustomAdapter adapter;
    private static CustomAdapter adapter2;
    private static CustomAdapter adapter3;
    private static RecyclerView gamesRecyclerView;
    private static RecyclerView friendsRecyclerView;
    private static RecyclerView chatsRecyclerView;
    private Response response;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        if(bundle != null) {
            response = bundle.getParcelable("res");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView welcomeTextView = view.findViewById(R.id.welcomTextView);
        welcomeTextView.setText("welcome " +response.getBody().getUser().getName());
        Button b_menu = (Button) view.findViewById(R.id.menu_button);
        b_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getHeader().setAction("menu_page");
                ((MainActivity) getActivity()).loadSetFragment(response);
            }
        });


        gamesRecyclerView = (RecyclerView) view.findViewById(R.id.sug_games_view);
        gamesRecyclerView.setHasFixedSize(true);
        gamesRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        gamesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        friendsRecyclerView = (RecyclerView) view.findViewById(R.id.sug_friends_rview);
        friendsRecyclerView.setHasFixedSize(true);
        friendsRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        friendsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        chatsRecyclerView = (RecyclerView) view.findViewById(R.id.chats_rview);
        chatsRecyclerView.setHasFixedSize(true);
        chatsRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        chatsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new CustomAdapter(response.getBody().getGameList(), null, null, response, getContext());
        gamesRecyclerView.setAdapter(adapter);
        adapter2 = new CustomAdapter(null, response.getBody().getUserList(), null, response, getContext());
        friendsRecyclerView.setAdapter(adapter2);
        adapter3 = new CustomAdapter(null, null, response.getBody().getChatList(), response, getContext());
        chatsRecyclerView.setAdapter(adapter3);
        return view;
    }

}