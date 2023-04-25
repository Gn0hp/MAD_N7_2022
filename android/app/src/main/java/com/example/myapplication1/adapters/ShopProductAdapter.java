package com.example.myapplication1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication1.R;
import com.example.myapplication1.models.Product;

import java.util.ArrayList;

public class ShopProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> arr;

    public ShopProductAdapter(Context context, ArrayList<Product> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) arr.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item,null);
        TextView name = view.findViewById(R.id.itemName);
        TextView des = view.findViewById(R.id.itemDes);
        TextView price = view.findViewById(R.id.itemPrice);

        Product product = arr.get(position);
        name.setText(product.getName());
        des.setText(product.getDescription());
        price.setText(Integer.toString(product.getPrice()));
        return view;
    }
}
