package com.pgkartavya.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pgkartavya.R;


public class Register extends AppCompatActivity {
    private EditText name, contact, password, email;
    private TextView login;
    private Button btn_register;
    private String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TAG = "Register Activity";

        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        name = findViewById(R.id.activity_register_et_fullname);
        contact = findViewById(R.id.activity_register_et_phone);
        password = findViewById(R.id.activity_register_et_password);
        email = findViewById(R.id.activity_register_et_email);

        btn_register = findViewById(R.id.acivity_register_btn_register);
        login = findViewById(R.id.activity_register_login_text);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                params.put("name", name.getText().toString().trim());
                params.put("phone", contact.getText().toString().trim());

                db.collection("users")
                        .add(params)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Intent intent = new Intent(Register.this, Login.class);
                                Toast.makeText(getApplicationContext(),
                                        "User Registered", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(getApplicationContext(),
                                        "Please Try Again", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

    }
}
