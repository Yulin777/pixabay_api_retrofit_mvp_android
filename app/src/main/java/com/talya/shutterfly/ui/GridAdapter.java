package com.talya.shutterfly.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.talya.shutterfly.R;
import com.talya.shutterfly.data.entity.Photo;
import com.talya.shutterfly.data.entity.PhotoList;

public class GridAdapter extends BaseAdapter{
    private final Context context;
    private PhotoList photoList;

    public GridAdapter(Context c) {
        this.context = c;
    }

    public void setPhotoList(PhotoList photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photoList == null ? 0 : photoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View container =  LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        PhotoViewHolder holder = new PhotoViewHolder(container);
        holder.updateUI(photoList.get(position));
        holder.getView().setOnClickListener(v -> onPhotoClicked(holder, photoList.get(position)));
        return holder.getView();
    }

    private void onPhotoClicked(PhotoViewHolder holder, Photo photo) {
        photo.liked = !photo.liked;
        holder.updateUI(photo);
        holder.animateHart(photo.liked);
    }
}
