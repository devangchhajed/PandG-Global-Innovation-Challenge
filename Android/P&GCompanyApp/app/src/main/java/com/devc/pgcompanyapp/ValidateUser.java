package com.devc.pgcompanyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devc.pgcompanyapp.Model.ItemMaster;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateUser extends AppCompatActivity {

    List<ItemMaster> itemMasterList;
    FirebaseFirestore db;
    String TAG = "ValidateUser";
    Button scan, save, useridbutton;
    EditText usermobile, productNameBox;
    String selectedProductBArCode;
    String userId;
    TextView useridTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_user);

        db = FirebaseFirestore.getInstance();
        scan = findViewById(R.id.validateuser_scan);
        save = findViewById(R.id.validateuser_save);
        usermobile = findViewById(R.id.validateuser_usermobile);
        productNameBox = findViewById(R.id.validateuser_name);
        useridbutton = findViewById(R.id.validateuser_getuserid);
        useridTV = findViewById(R.id.validateuser_userid);

        refreshList();
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValidateUser.this, Scanner.class);
                startActivityForResult(intent, 0);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addProduct();

            }
        });
        useridbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserId(usermobile.getText().toString());

            }
        });


    }

    public void getUserId(final String mobile){
        db.collection("users")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            if(document.get("phone").toString().equals(mobile)){
                                userId = document.getId().toString();
                                useridTV.setText(userId);
                                break;
                            }

                        }

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });

    }

    public void addProduct(){
        Log.e("UpdateProduct", "New Call Started");

        showAlertBox(selectedProductBArCode, useridTV.getText().toString());

    }


    public void showAlertBox(String barcodeText, String userid){

        AlertDialog.Builder builder = new AlertDialog.Builder(ValidateUser.this);
        builder.setTitle("Confirm Saving");
        builder.setMessage("Want to save "+barcodeText);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
                String format = s.format(new Date());

                Map<String, String> params = new HashMap<String, String>();
                params.put("productid", barcodeText);
                params.put("userid", userid);
                params.put("status", "true");
                params.put("timestamp", format);

                db.collection("cart")
                        .add(params)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
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

                Toast.makeText(getApplicationContext(), "Product Added to the List", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Lets try again.", Toast.LENGTH_SHORT).show();

            }
        });

        builder.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            String barcode = data.getStringExtra("barcode");
            for(ItemMaster item: itemMasterList){
                if(item.getProductBarCode().equals(barcode)){
                    productNameBox.setText(item.getProductName());
                    selectedProductBArCode = barcode;
                }
            }
        }
    }


    public void refreshList(){
        Log.e(TAG, "Referishing List");



        db.collection("itemsmaster")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            itemMasterList = new ArrayList<ItemMaster>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ItemMaster itemMaster = new ItemMaster(document.getId(),document.get("productname").toString(), document.getString("productqr").toString());
                                itemMasterList.add(itemMaster);
                                Log.e(TAG, document.getId()+"-"+document.get("productname").toString()+" - "+document.getString("productqr").toString());
                            }

                            Log.e(TAG,"Total itemMaster : "+itemMasterList.size());

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
