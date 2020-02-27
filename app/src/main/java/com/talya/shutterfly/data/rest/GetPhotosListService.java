package com.talya.shutterfly.data.rest;

import com.talya.shutterfly.data.entity.PhotoList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPhotosListService {
    @GET("api/")
    Call<PhotoList> getPhotos(@Query("key") String key,
                              @Query("q") String searchText,
                              @Query("image_type") String type,
                              @Query("per_page") int limit);
}
