package com.talya.shutterfly.ui;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.talya.shutterfly.R;
import com.talya.shutterfly.data.entity.Photo;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

class PhotoViewHolder {
    private View container;
    private ImageView image;
    private ImageView hart;
    private ImageView bigHart;
    private ProgressBar busyIndicator;
    private Animation zoomToFull;

    PhotoViewHolder(View container) {
        findViews(container);
        zoomToFull = AnimationUtils.loadAnimation(container.getContext(), R.anim.zoom_to_full);
    }

    private void findViews(View container) {
        this.container = container;
        this.busyIndicator = container.findViewById(R.id.busy_indicator);
        this.image = container.findViewById(R.id.image);
        this.hart = container.findViewById(R.id.hart);
        this.bigHart = container.findViewById(R.id.big_hart);
    }

    View getView() {
        return this.container;
    }

    void updateUI(Photo photo) {
        setImage(photo.url);
        setHart(photo.liked);
    }

    private void setHart(boolean liked) {
        this.hart.setImageResource(liked ? R.drawable.filled_hart : R.drawable.empty_hart);
    }

    private void setImage(String url) {
        this.busyIndicator.setVisibility(VISIBLE);
        Picasso.get()
                .load(url)
                .centerCrop()
                .resize(300, 300) //minimize, required
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        busyIndicator.setVisibility(GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });
    }


    void animateHart(boolean liked) {
        this.bigHart.setImageResource(liked ? R.drawable.filled_hart : R.drawable.empty_hart);
        this.bigHart.setVisibility(VISIBLE);
        this.bigHart.startAnimation(zoomToFull);
        this.bigHart.setVisibility(INVISIBLE);
    }
}
