package com.talya.shutterfly.data.entity;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("previewURL")
    public String url;

    public boolean liked = false;
}
