package com.devc.pgcompanyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {
    FirebaseFirestore db;
    Button scan, save;
    EditText barcodeBox, productNameBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        db = FirebaseFirestore.getInstance();
        scan = findViewById(R.id.addproduct_scan);
        save = findViewById(R.id.addproduct_save);
        barcodeBox = findViewById(R.id.addprodduct_barcode);
        productNameBox = findViewById(R.id.addprodduct_name);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this, Scanner.class);
                startActivityForResult(intent, 0);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addProduct(barcodeBox.getText().toString(), productNameBox.getText().toString());

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            String barcode = data.getStringExtra("barcode");
            barcodeBox.setText(barcode);
        }
    }

    public void addProduct(String barcode, String productname){
        Log.e("AddProduct", "New Call Started"+barcode+productname);
            Map<String, String> params = new HashMap<String, String>();
            params.put("productname", productname);
            params.put("productqr", barcode);

            db.collection("itemsmaster")
                .add(params)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Product Added to the List", Toast.LENGTH_SHORT).show();
                        Log.e("AddProduct", "Product Added to firebase");
                        barcodeBox.setText("");
                        productNameBox.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),
                                "Please Try Again", Toast.LENGTH_LONG).show();
                    }
                });

    }
}
