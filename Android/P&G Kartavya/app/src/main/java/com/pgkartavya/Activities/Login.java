package com.pgkartavya.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pgkartavya.R;
import com.pgkartavya.Service.SessionManager;

public class Login extends AppCompatActivity {

    Button register,login;
    EditText email,password;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.activity_login_btn_signin);
        register = findViewById(R.id.activity_login_btn_register);
        email = findViewById(R.id.activity_login_email);
        password = findViewById(R.id.activity_login_password);

        login = findViewById(R.id.activity_login_btn_signin);
        register = findViewById(R.id.activity_login_btn_register);

        Log.d("reg splash", "Reg");

        sessionManager = new SessionManager(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email.getText().toString().trim());
                params.put("password", password.getText().toString().trim());

                final String TAG = "LOGIN ACTIVITY";
                final FirebaseFirestore db = FirebaseFirestore.getInstance();


                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData()+" - "+document.get("email"));
                                        Log.e(TAG, document.get("email").toString()+"-"+document.get("password").toString());
                                        if(document.get("email").toString().equals(email.getText().toString().trim())){
                                            if(document.get("password").toString().equals(password.getText().toString().trim())){

                                                Intent intent = new Intent(Login.this, MainActivity.class);
                                                Log.e(TAG, "Login Success "+document.get("email").toString()+"-"+document.get("password").toString());

                                                String uid = document.getId();
                                                String name = document.get("name").toString();
                                                String phone = document.get("phone").toString();
                                                String email = document.get("email").toString();
                                                sessionManager.setLogin(true, uid, name, phone, email);
                                                Log.e("login", sessionManager.getName());

                                                startActivity(intent);
                                                finish();

                                            }
                                        }
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
