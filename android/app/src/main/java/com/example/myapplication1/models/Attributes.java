package com.example.myapplication1.models;

import java.io.Serializable;

public class Attributes implements Serializable {
    private int id;
    private String name;
    private String value;

    public Attributes(int id, String name, String value){
        this.id=id;
        this.name=name;
        this.value=value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
