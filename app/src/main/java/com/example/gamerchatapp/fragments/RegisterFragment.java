package com.example.gamerchatapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gamerchatapp.R;
import com.example.gamerchatapp.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button b_reg = (Button) view.findViewById(R.id.register_buttonReg);
        b_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).signUp(v);

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("result", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("result", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("result", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("result", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("result", "onDestroy");
    }
}