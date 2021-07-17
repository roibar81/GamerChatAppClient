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
import com.example.gamerchatapp.dm.Body;
import com.example.gamerchatapp.dm.Header;
import com.example.gamerchatapp.fragments.ChatFragment;
import com.example.gamerchatapp.fragments.MainFragment;
import com.example.gamerchatapp.fragments.MenuFragment;
import com.example.gamerchatapp.fragments.ProfileFragment;
import com.example.gamerchatapp.fragments.RegisterFragment;
import com.google.gson.Gson;
import com.example.gamerchatapp.R;
import com.example.gamerchatapp.dm.*;
import com.example.gamerchatapp.fragments.LoginFragment;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private String reqStr;
    private String resStr;
    private DoingBackground doingBackground;
    private EditText nameText;
    private EditText passText;
    private EditText emailText;
    private CustomAdapter adapter;
    private Response response;
    private Bundle bundle;
    private String messageText;

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
        private Response response;
        @Override
        protected Response doInBackground(Request... requests) {
            try {
                Socket socket = new Socket("10.100.102.8", 12345);
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
            //Log.d("response", response.toString());
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
        body.setUser(user);
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
        body.setUser(user);
        Request request = new Request(header, body);
        new DoingBackground().execute(request);
    }

    public void sendMessage(Response response) {
        Request request = new Request(new Header("write_message"), new Body());
        request.getBody().setMessage(response.getBody().getMessage());
        request.getBody().setChatRoom(response.getBody().getChatRoom());
        new DoingBackground().execute(request);
    }

    public void enterChatRoom(Response response) {
        Request request = new Request(new Header(""), new Body());
        request.getHeader().setAction("enter_chat_room");
        request.getBody().setUser(response.getBody().getUser());
        request.getBody().setChatRoom(response.getBody().getChatRoom());
        new DoingBackground().execute(request);
    }

    public void loadSetFragment(Response response) {
        fragmentTransaction = fragmentManager.beginTransaction();
        FrameLayout frameLayouts = null;
        MainFragment mainFragment = null;
        FrameLayout frameLayouts2 = null;
        RegisterFragment registerFragment = null;
        MenuFragment menuFragment = null;
        ProfileFragment profileFragment = null;
        LoginFragment loginFragment = null;
        ChatFragment chatFragment = null;


        switch(response.getHeader().getAction()) {
            case "sign_in success":
                frameLayouts = (FrameLayout) findViewById(R.id.main_fragment);;
                mainFragment = new MainFragment();;
                frameLayouts2 = (FrameLayout) findViewById(R.id.fragment_login);
                bundle = new Bundle();
                bundle.putParcelable("res", response);
                mainFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.main_fragment, mainFragment);
                break;
            case "sign_up success":
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_login);
                loginFragment = new LoginFragment();
                frameLayouts2 = (FrameLayout) findViewById(R.id.fragment_register);
                fragmentTransaction.add(R.id.fragment_login, loginFragment);
                break;
            case "register_page":
                registerFragment = new RegisterFragment();
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_register);
                frameLayouts2 = (FrameLayout) findViewById(R.id.fragment_login);
                fragmentTransaction.add(R.id.fragment_register, registerFragment);
                break;
            case "profile_page":
                profileFragment = new ProfileFragment();
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_profile);
                frameLayouts2 = (FrameLayout) findViewById(R.id.menu_fragment);
                bundle = new Bundle();
                bundle.putParcelable("res", response);
                profileFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_profile,  profileFragment);
                break;
            case "menu_page":
                menuFragment = new MenuFragment();
                frameLayouts  = (FrameLayout) findViewById(R.id.menu_fragment);
                frameLayouts2 = (FrameLayout) findViewById(R.id.main_fragment);
                bundle = new Bundle();
                bundle.putParcelable("res", response);
                menuFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.menu_fragment, menuFragment);
                break;
            case "logOut":
                loginFragment = new LoginFragment();
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_login);
                frameLayouts2 = (FrameLayout) findViewById(R.id.menu_fragment);
                fragmentTransaction.add(R.id.fragment_login, loginFragment);
                break;
            case "home":
                mainFragment = new MainFragment();
                frameLayouts = (FrameLayout) findViewById(R.id.main_fragment);
                frameLayouts2 = (FrameLayout) findViewById(R.id.menu_fragment);
                bundle = new Bundle();
                bundle.putParcelable("res", response);
                mainFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.main_fragment, mainFragment);
                break;
            case "chat_room_page":
                chatFragment = new ChatFragment();
                frameLayouts = (FrameLayout) findViewById(R.id.fragment_chat);
                frameLayouts2 = (FrameLayout) findViewById(R.id.main_fragment);
                bundle = new Bundle();
                bundle.putParcelable("res", response);
                chatFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.fragment_chat, chatFragment);
                break;
            case "write_message_success":
                EditText message_textView = findViewById(R.id.message_editText_cr);
                message_textView.setText("");
                chatFragment = new ChatFragment();
                bundle = new Bundle();
                bundle.putParcelable("res", response);
                chatFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_chat, chatFragment);
                break;
            default:
                System.out.println("Failed");
                break;
        }
        if(!response.getHeader().getAction().equals("write_message_success")) {
            frameLayouts.setVisibility(View.VISIBLE);
            frameLayouts2.setVisibility(View.GONE);
        }
        fragmentTransaction.addToBackStack(null).commit();
    }

    public String writeRequest (Request request) {
        String reqStr = null;
        Gson gson = new Gson();
        reqStr = gson.toJson(request);
        return reqStr;
    }

    public Response readResponse(String responseString) {
        Response response = null;
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try(Reader reader = new StringReader(responseString)){
            response = gson.fromJson(reader, Response.class);
        }catch (IOException e) {
            e.getMessage();
        }
        return response;
    }

}