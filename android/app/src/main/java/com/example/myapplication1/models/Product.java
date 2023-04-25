package com.example.myapplication1.models;

import java.util.ArrayList;

public class Product {
    private  int id;
    private String name;
    private String description;
    private int count;
    private int price;
    private ArrayList<Attributes> attributes;

    public Product(int id, String name, String description, int count, int price, ArrayList<Attributes> attributes){
        this.id=id;
        this.name=name;
        this.description=description;
        this.count=count;
        this.price=price;
        this.attributes=attributes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Attributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attributes> attributes) {
        this.attributes = attributes;
    }
}
