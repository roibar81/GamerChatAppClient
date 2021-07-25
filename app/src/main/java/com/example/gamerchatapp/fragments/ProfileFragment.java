package com.example.gamerchatapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;
import com.example.gamerchatapp.dm.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private Response response;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Bundle bundle = new Bundle();
        bundle = getArguments();
        if(bundle != null) {
            response = bundle.getParcelable("res");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_profile);
        imageView.setImageResource(R.drawable.usericon);
        TextView name_textView = (TextView) view.findViewById(R.id.name_textView_profile);
        TextView email_textView = (TextView) view.findViewById(R.id.email_textView_profile);
        //TextView nf_textView = (TextView) view.findViewById(R.id.num_of_freinds_profile);
        TextView ng_textView = (TextView) view.findViewById(R.id.num_of_games_profile);

        name_textView.setText(response.getBody().getUser().getName());
        email_textView.setText(response.getBody().getUser().getEmail());
        //nf_textView.setText(String.valueOf(response.getBody().getUser().getFriends().size()));
        ng_textView.setText(String.valueOf(response.getBody().getUser().getFavGames().size()));
        return view;
    }

    public boolean onBackPressed3() {
        FrameLayout frameLayouts = (FrameLayout) this.getActivity().findViewById(R.id.menu_fragment);
        FrameLayout frameLayouts2 = (FrameLayout) this.getActivity().findViewById(R.id.fragment_profile);
        frameLayouts.setVisibility(View.VISIBLE);
        frameLayouts2.setVisibility(View.GONE);
        return false;
    }
}