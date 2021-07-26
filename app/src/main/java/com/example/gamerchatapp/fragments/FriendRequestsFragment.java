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
import android.widget.FrameLayout;

import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;
import com.example.gamerchatapp.adapter.CustomAdapter;
import com.example.gamerchatapp.dm.Header;
import com.example.gamerchatapp.dm.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendRequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendRequestsFragment extends Fragment {

    private static CustomAdapter adapter;
    private static RecyclerView friendsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Response response;

    public FriendRequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendRequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendRequestsFragment newInstance(String param1, String param2) {
        FriendRequestsFragment fragment = new FriendRequestsFragment();
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
        View view = inflater.inflate(R.layout.fragment_friend_requests, container, false);
        EditText search_editText = (EditText) view.findViewById(R.id.fr_serach_editText);
        Button search_button = (Button) view.findViewById(R.id.search_button_fr);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getHeader().setAction("search-user-by-name");
                response.getBody().setPattern(search_editText.getText().toString());
                ((MainActivity) getActivity()).searchUserList(response);
            }
        });
        friendsRecyclerView = view.findViewById(R.id.fr_recyclerView);
        friendsRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        friendsRecyclerView.setLayoutManager(layoutManager);
        friendsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if(response.getBody().getUsersRs().isEmpty()) {
            adapter = new CustomAdapter(null, response.getBody().getUserList(), null, response,
                    getContext());
        }else {
            adapter = new CustomAdapter(null, response.getBody().getUsersRs(), null, response,
                    getContext());
        }
        friendsRecyclerView.setAdapter(adapter);
        return view;
    }

    public boolean onBackPressed5() {
        FrameLayout frameLayouts = (FrameLayout) this.getActivity().findViewById(R.id.menu_fragment);
        FrameLayout frameLayouts2 = (FrameLayout) this.getActivity().findViewById(R.id.fragment_fr);
        frameLayouts.setVisibility(View.VISIBLE);
        frameLayouts2.setVisibility(View.GONE);
        return false;
    }
}