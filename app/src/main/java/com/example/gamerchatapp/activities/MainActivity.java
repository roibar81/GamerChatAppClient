package com.example.gamerchatapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.gamerchatapp.fragments.MainFragment;
import com.google.gson.Gson;
import com.example.gamerchatapp.R;
import com.example.gamerchatapp.dm.Body;
import com.example.gamerchatapp.dm.Header;
import com.example.gamerchatapp.dm.Request;
import com.example.gamerchatapp.dm.Response;
import com.example.gamerchatapp.dm.User;
import com.example.gamerchatapp.fragments.LoginFragment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    private static FragmentTransaction fragmentTransaction;
    private static String reqStr;
    private static String resStr;
    private static Response response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.fragment_login, loginFragment);
        fragmentTransaction.commit();
        new DoingBackground();
    }

    private class DoingBackground extends AsyncTask<Request, Void, Response> {

        @Override
        protected Response doInBackground(Request... requests) {
            try {
                Socket socket = new Socket("10.0.0.4 ", 12345);
                ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

                reqStr = writeRequest(requests[0]);
                Log.d("to server", reqStr);
                writer.writeObject(reqStr);


                resStr = reader.readObject().toString();
                response = readResponse(resStr);

            }catch(ConnectException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
            return response;
        }


        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            loadSetFragment(findViewById(android.R.id.content).getRootView());
        }

    }

    public static void signIn(View view) {
        String userName = view.findViewById(R.id.emailEditText).toString();
        String password = view.findViewById(R.id.passwordText).toString();
        User user = new User(userName, password);
        Header header = new Header("sign_in");
        Body body = new Body();
        body.getUserList().add(user);
        Request request = new Request(header, body);
        //new DoingBackground().execute(request);
    }

    public void loadSetFragment(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        FrameLayout frameLayouts = (FrameLayout) view.findViewById(R.id.main_fragment);;
        frameLayouts.setVisibility(View.VISIBLE);
        MainFragment mainFragment = new MainFragment();
        if(response.getBody().getValid()) {
            FrameLayout frameLayouts2 = (FrameLayout) view.findViewById(R.id.fragment_login);
            frameLayouts2.setVisibility(View.GONE);
            fragmentTransaction.add(R.id.main_fragment, mainFragment);
        }
        else {
            fragmentTransaction.hide(mainFragment);
            frameLayouts.setVisibility(View.GONE);
        }
        fragmentTransaction.addToBackStack(null).commit();
    }

    public static String writeRequest(Request request) {
        String reqStr = null;
        Gson gson = new Gson();
        reqStr = gson.toJson(request);
        return reqStr;
    }

    public static Response readResponse(String responseString) {
        Gson gson = new Gson();
        Response response = gson.fromJson(responseString, Response.class);
        return response;
    }

}