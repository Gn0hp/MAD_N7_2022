package com.example.myapplication1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication1.R;
import com.example.myapplication1.models.Attributes;
import com.example.myapplication1.models.Product;

import java.util.ArrayList;

public class AttributeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Attributes> arr;

    public AttributeAdapter(Context context, ArrayList<Attributes> arr) {
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
        return arr.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.attr_item,null);
        TextView name = view.findViewById(R.id.attrArrName);
        TextView val = view.findViewById(R.id.attrArrValue);

        Attributes attributes = arr.get(position);
        name.setText(attributes.getName());
        val.setText(attributes.getValue());
        return view;
    }
}
