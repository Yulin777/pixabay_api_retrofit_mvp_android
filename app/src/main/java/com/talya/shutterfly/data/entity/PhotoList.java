package com.talya.shutterfly.data.entity;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotoList{

    @SerializedName("hits")
    private ArrayList<Photo> photoList;

    public int size() {
        return photoList.size();
    }

    public Photo get(int position) {
        return photoList.get(position);
    }

}
