package com.pgkartavya.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgkartavya.Model.CartItem;
import com.pgkartavya.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CartItemsAdapter extends ArrayAdapter<CartItem> {
    private final Activity context;
    private final ArrayList<CartItem> item;
    public CartItemsAdapter(@NonNull Activity context, ArrayList<CartItem> item) {
        super(context,-1);
        this.context=context;
        this.item = item;
        Log.e("Adapter", "Adap List Size : "+item.size());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.e("Adapter","Prod Added to List");
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cart_listview, parent, false);


        TextView ProductName = (TextView) rowView.findViewById(R.id.cart_listview_product_name);
        TextView ProductTimestamp = (TextView) rowView.findViewById(R.id.cart_listview_product_timestamp);

        CartItem citem = item.get(position);

        ProductName.setText("citem.getProductname()");
        ProductTimestamp.setText("citem.getTimestamp()");

        return rowView;
    }
}
