package com.talya.shutterfly.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.talya.shutterfly.R;
import com.talya.shutterfly.presenter.IPresenter;
import com.talya.shutterfly.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements IView {
    private IPresenter mPresenter;
    private EditText searchEditText;
    private GridView photoGrid;
    private View clearText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this);
        findViews();
        setViews();
    }

    private void setViews() {
        searchEditText.setOnEditorActionListener(mPresenter);
        photoGrid.setAdapter(mPresenter.getGridAdapter());
        clearText.setOnClickListener(mPresenter);
    }

    private void findViews() {
        this.searchEditText = findViewById(R.id.search_edit_text);
        this.photoGrid = findViewById(R.id.grid);
        this.clearText = findViewById(R.id.clear);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onCallError() {
        Toast.makeText(getContext(), getString(R.string.err_msg), Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearSearchText() {
        searchEditText.getText().clear();
    }

    @Override
    public IBinder getWindowToken() {
        return getWindow().getDecorView().getRootView().getWindowToken();
    }

}
