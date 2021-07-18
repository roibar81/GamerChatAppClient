package com.example.gamerchatapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;
import com.example.gamerchatapp.dm.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private Response response;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Button b_profile = (Button) view.findViewById(R.id.profile_button_menu);
        b_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getHeader().setAction("profile_page");
                ((MainActivity) getActivity()).loadSetFragment(response);
            }
        });
        Button b_logOut = (Button) view.findViewById(R.id.logOut_button_menu);
        b_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getHeader().setAction("logOut");
                ((MainActivity) getActivity()).loadSetFragment(response);
            }
        });
        Button b_home = (Button) view.findViewById(R.id.home_button_menu);
        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getHeader().setAction("home");
                ((MainActivity) getActivity()).loadSetFragment(response);
            }
        });
        return view;
    }

    public boolean onBackPressed4() {
        FrameLayout frameLayouts = (FrameLayout) this.getActivity().findViewById(R.id.main_fragment);
        FrameLayout frameLayouts2 = (FrameLayout) this.getActivity().findViewById(R.id.menu_fragment);
        frameLayouts.setVisibility(View.VISIBLE);
        frameLayouts2.setVisibility(View.GONE);
        return false;
    }
}