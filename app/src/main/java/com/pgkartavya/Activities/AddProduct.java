package com.pgkartavya.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pgkartavya.Adapters.CartItemsAdapter;
import com.pgkartavya.Model.CartItem;
import com.pgkartavya.Model.ItemMaster;
import com.pgkartavya.R;
import com.pgkartavya.Service.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity {


    String TAG;
    FirebaseFirestore db;
    List<ItemMaster> itemMasterList;
    List<CartItem> cartItemList;
    ArrayAdapter adapter;
    ListView cartItemListView;
    SessionManager sessionManager;
    String userUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProduct.this, Scanner.class);
                startActivityForResult(intent,2);
            }
        });

        cartItemListView = findViewById(R.id.cartitemslistview);
        sessionManager = new SessionManager(this);
        userUID = sessionManager.getUID();

        db = FirebaseFirestore.getInstance();
        itemMasterList = new ArrayList<ItemMaster>();
        cartItemList = new ArrayList<CartItem>();

        CartItemsAdapter adapter=new CartItemsAdapter(this, cartItemList);
        cartItemListView.setAdapter(adapter);

        refreshList();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==2){
            refreshList();
        }

    }

    public void refreshList(){
        db.collection("itemsmaster")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        itemMasterList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ItemMaster itemMaster = new ItemMaster(document.getId(),document.get("productname").toString(), document.getString("productqr").toString());
                            itemMasterList.add(itemMaster);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });

        db.collection("cart")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        itemMasterList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(document.getString("userid").toString().equals(userUID)){
                                String product="";
                                for(ItemMaster item: itemMasterList){
                                    if(document.get("productid").toString().equals(item.getUid())){
                                        CartItem cartItem = new CartItem(document.getId(),document.get("productid").toString(), document.getString("userid").toString(), document.getString("status").toString(), document.getString("timestamp").toString(), document.get("productname").toString());
                                        cartItemList.add(cartItem);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });


    }

}
