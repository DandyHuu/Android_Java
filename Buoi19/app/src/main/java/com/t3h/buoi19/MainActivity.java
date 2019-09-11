package com.t3h.buoi19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.t3h.buoi19.adapter.ChatAdapter;
import com.t3h.buoi19.model.Chat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnSuccessListener<Void>, OnFailureListener, ValueEventListener {
    private RecyclerView rvChat;
    private EditText edtMess;
    private ImageView imSend;

    private ChatAdapter adapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("Chat");
    private final SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy HH:mm");
    private ArrayList<String> arrToken = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        reference.addValueEventListener(this);
        getToken();
    }

    public void getToken(){
        database.getReference("Token").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrToken.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String token = snapshot.getValue(String.class);
                    arrToken.add(token);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        rvChat = findViewById(R.id.rvChat);
        edtMess = findViewById(R.id.edt_message);
        imSend = findViewById(R.id.im_sent);

        imSend.setOnClickListener(this);
        adapter = new ChatAdapter(this);
        rvChat.setAdapter(adapter);

    }

    private void sendFCM(String user, String message){

        try {
            final JSONObject object = new JSONObject();
            JSONArray arr = new JSONArray(arrToken);
            object.put("registration_ids", arr);
            JSONObject notification = new JSONObject();
            notification.put("body", message);
            notification.put("title", user);
            object.put("notification", notification);

            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    "https://fcm.googleapis.com/fcm/send",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String err = new String(error.networkResponse.data);
                            int a = 3;
                        }
                    })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "key=AIzaSyAsjZ8nC459jb-4GNma2StMDTJXOe_KFe8");
                    return headers;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    String s = object.toString();
                    return s.getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
            };

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        String message = edtMess.getText().toString();
        if (message.isEmpty() == true) {
            return;
        }

        Chat chat = new Chat();
        chat.setDate(format.format(new Date()));
        chat.setMessage(message);
        chat.setName("Heo đồng đội");

        reference.child(chat.getId()+"").setValue(chat)
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
        sendFCM("HeoTeam",message);
    }

    @Override
    public void onSuccess(Void aVoid) {
        edtMess.setText("");
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(this, "Send message false!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        final ArrayList<Chat> arr = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
            Chat chat = snapshot.getValue(Chat.class);
            arr.add(chat);

        }
        adapter.setData(arr);
        rvChat.post(new Runnable() {
            @Override
            public void run() {
                rvChat.scrollToPosition(arr.size()-1);
            }
        });

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
