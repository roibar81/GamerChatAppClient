package com.example.gamerchatapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.gamerchatapp.adapter.CustomAdapter;
import com.example.gamerchatapp.fragments.MainFragment;
import com.example.gamerchatapp.fragments.RegisterFragment;
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

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String reqStr;
    private String resStr;
    private Response response;
    private DoingBackground doingBackground;
    private EditText nameText;
    private EditText passText;
    private EditText emailText;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.fragment_login, loginFragment);
        fragmentTransaction.commit();
        doingBackground = new DoingBackground();
    }

    private class DoingBackground extends AsyncTask<Request, Void, Response> {

        @Override
        protected Response doInBackground(Request... requests) {
            try {
                Socket socket = new Socket("10.100.102.7", 12345);
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
            loadSetFragment(response);
        }

    }

    public void signIn(View view) {
        nameText = (EditText) findViewById(R.id.userNameEditText);
        passText = (EditText) findViewById(R.id.passwordText);
        String userName = nameText.getText().toString();
        String password = passText.getText().toString();
        User user = new User(userName, password);
        Header header = new Header("sign_in");
        Body body = new Body();
        body.getUserList().add(user);
        Request request = new Request(header, body);
        new DoingBackground().execute(request);
    }

    public void signUp(View view) {
        nameText = (EditText) findViewById(R.id.username_editTextReg);
        passText = (EditText) findViewById(R.id.password_TextReg);
        emailText = (EditText) findViewById(R.id.emailText);
        String userName = nameText.getText().toString();
        String password = passText.getText().toString();
        String email = emailText.getText().toString();
        User user = new User(userName, password, email);
        Header header = new Header("sign_up");
        Body body = new Body();
        body.getUserList().add(user);
        Request request = new Request(header, body);
        new DoingBackground().execute(request);
    }

    public void loadSetFragment(Response response) {
        fragmentTransaction = fragmentManager.beginTransaction();
        FrameLayout frameLayouts = null;
        MainFragment mainFragment = null;
        FrameLayout frameLayouts2 = null;
        RegisterFragment registerFragment = null;

        switch(response.getHeader().getAction()) {
            case "sign_in success":
                frameLayouts = (FrameLayout) findViewById(R.id.main_fragment);;
                frameLayouts.setVisibility(View.VISIBLE);
                mainFragment = new MainFragment();;
                frameLayouts2 = (FrameLayout) findViewById(R.id.fragment_login);
                frameLayouts2.setVisibility(View.GONE);
                adapter = new CustomAdapter(response.getBody().getGameList());
                fragmentTransaction.add(R.id.main_fragment, mainFragment);
                break;
            case "sign_up success":
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_login);
                frameLayouts.setVisibility(View.VISIBLE);
                LoginFragment loginFragment = new LoginFragment();
                frameLayouts2 = (FrameLayout) findViewById(R.id.fragment_register);
                frameLayouts2.setVisibility(View.GONE);
                fragmentTransaction.add(R.id.fragment_login, loginFragment);
                break;
            case "register_page":
                registerFragment = new RegisterFragment();
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_register);
                frameLayouts.setVisibility(View.VISIBLE);
                frameLayouts2 = (FrameLayout) findViewById(R.id.fragment_login);
                frameLayouts2.setVisibility(View.GONE);
                fragmentTransaction.add(R.id.fragment_register, registerFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.addToBackStack(null).commit();
    }

    public String writeRequest(Request request) {
        String reqStr = null;
        Gson gson = new Gson();
        reqStr = gson.toJson(request);
        return reqStr;
    }

    public Response readResponse(String responseString) {
        Gson gson = new Gson();
        Response response = gson.fromJson(responseString, Response.class);
        return response;
    }

}