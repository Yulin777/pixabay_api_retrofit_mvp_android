package com.talya.shutterfly.presenter;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

public interface IPresenter extends TextView.OnEditorActionListener, View.OnClickListener {

    @Override
    boolean onEditorAction(TextView v, int actionId, KeyEvent event);

    ListAdapter getGridAdapter();

    @Override
    void onClick(View v);
}
