package com.pgkartavya.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pgkartavya.Model.CartItem;
import com.pgkartavya.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CartItemsAdapter extends ArrayAdapter<CartItem> {
    private final Activity context;
    private final List<CartItem> item;
    public CartItemsAdapter(@NonNull Activity context, List<CartItem> item) {
        super(context, R.layout.cart_listview);
        this.context=context;
        this.item = item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.cart_listview, null,true);

        TextView ProductName = (TextView) rowView.findViewById(R.id.cart_listview_product_name);
        TextView ProductTimestamp = (TextView) rowView.findViewById(R.id.cart_listview_product_timestamp);

        CartItem citem = item.get(position);

        ProductName.setText(citem.getProductname());
        ProductTimestamp.setText(citem.getTimestamp());

        return super.getView(position, convertView, parent);
    }
}
