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
import android.widget.Toast;

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

public class Cart extends AppCompatActivity {


    String TAG = Cart.class.getSimpleName();
    FirebaseFirestore db;
    List<ItemMaster> itemMasterList;
    ArrayList<CartItem> cartItemList;
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
        TAG = "CART ACTIVITY";

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, Scanner.class);
                startActivityForResult(intent,2);
            }
        });

        db = FirebaseFirestore.getInstance();


        cartItemListView = findViewById(R.id.cartitemslistview);
        sessionManager = new SessionManager(this);
        userUID = sessionManager.getUID();

        itemMasterList = new ArrayList<ItemMaster>();
        cartItemList = new ArrayList<CartItem>();

        refreshList();

        CartItemsAdapter adapter=new CartItemsAdapter(this, cartItemList);
        cartItemListView.setAdapter(adapter);






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==2){
            refreshList();
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
                        itemMasterList.clear();
                        itemMasterList = new ArrayList<ItemMaster>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ItemMaster itemMaster = new ItemMaster(document.getId(),document.get("productname").toString(), document.getString("productqr").toString());
                            itemMasterList.add(itemMaster);
                            Log.e(TAG, document.getId()+"-"+document.get("productname").toString()+" - "+document.getString("productqr").toString());
                        }

                        Log.e(TAG,"Total itemMaster : "+itemMasterList.size());

                        db.collection("cart")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            cartItemList.clear();
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.e(TAG, "Check User : "+document.getString("userid").toString().equals(userUID.toString()));
                                                if(document.getString("userid").toString().equals(userUID.toString())){
                                                    String product="";
                                                    for(ItemMaster item: itemMasterList){
                                                        Log.e(TAG, "Check Prod : "+document.get("productid").toString().equals(item.getUid().toString())+  document.get("productid").toString()+"-"+item.getUid().toString());
                                                        if(document.get("productid").toString().equals(item.getProductBarCode().toString())){
                                                            Log.e(TAG, "Product added to cart "+document.getId());
                                                            CartItem cartItem = new CartItem(document.getId(),document.get("productid").toString(), document.getString("userid").toString(), document.getString("status").toString(), document.getString("timestamp").toString(), item.getProductName());
                                                            cartItemList.add(cartItem);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            CartItemsAdapter adapter=new CartItemsAdapter(Cart.this, cartItemList);
                                            cartItemListView.setAdapter(adapter);

                                        } else {
                                            Log.d(TAG, "Error getting documents: ", task.getException());
                                        }
                                        Toast.makeText(getApplicationContext(), ""+ cartItemList.size(), Toast.LENGTH_SHORT).show();
                                    }
                                });



                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });

    }

}
