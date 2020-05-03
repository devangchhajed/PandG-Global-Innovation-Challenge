package com.pgkartavya.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pgkartavya.R;
import com.pgkartavya.Service.SessionManager;

public class Profile extends AppCompatActivity {

    TextView name, phone, email, uid;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        sessionManager = new SessionManager(this);

        name = findViewById(R.id.profile_name_text);
        phone = findViewById(R.id.profile_phone_text);
        email = findViewById(R.id.profile_email_text);
        uid = findViewById(R.id.profile_uid_text);

        name.setText(sessionManager.getName());
        email.setText(sessionManager.getEmail());
        phone.setText(sessionManager.getPhone());
        uid.setText(sessionManager.getUID());


    }
}
