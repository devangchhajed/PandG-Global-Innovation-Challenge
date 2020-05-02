package com.pgkartavya.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.pgkartavya.MainActivity;
import com.pgkartavya.R;
import com.pgkartavya.SessionManager;

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

                Intent intent = new Intent(Login.this, MainActivity.class);

                String uid = "1";
                String name = "Ddev";
                String phone = "9876543210";

                sessionManager.setLogin(true, uid, name, phone);
                Log.e("login", sessionManager.getName());

                startActivity(intent);
                finish();
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
