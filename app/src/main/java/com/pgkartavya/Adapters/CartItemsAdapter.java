package com.pgkartavya.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pgkartavya.Model.CartItem;
import com.pgkartavya.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ViewHolder> {
    private final ArrayList<CartItem> item;



    public CartItemsAdapter(ArrayList<CartItem> item) {
        this.item = item;
        Log.e("Adapter", "Adap List Size : "+item.size());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView product, timestamp;
        public Button status;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            product = (TextView) itemView.findViewById(R.id.cart_listview_product_name);
            timestamp = (TextView) itemView.findViewById(R.id.cart_listview_product_timestamp);
            status = (Button) itemView.findViewById(R.id.cart_listview_status_button);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cart_listview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        CartItem citem = item.get(position);


        // Set item views based on your views and data model
        TextView textView = holder.product;
        textView.setText(citem.getProductname());

        TextView textView2 = holder.timestamp;
        textView2.setText(citem.getTimestamp());

        Button button = holder.status;
        button.setText(citem.getStatus());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }


}
