package com.talya.shutterfly.presenter;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.talya.shutterfly.R;
import com.talya.shutterfly.data.entity.PhotoList;
import com.talya.shutterfly.data.rest.GetPhotosListService;
import com.talya.shutterfly.data.rest.RetrofitInstance;
import com.talya.shutterfly.ui.GridAdapter;
import com.talya.shutterfly.ui.IView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements IPresenter {
    private static final int MAX_PHOTOS_LIMIT = 200;
    private final IView mView;
    private final GridAdapter gridAdapter;

    public Presenter(IView view) {
        this.mView = view;
        this.gridAdapter = new GridAdapter(mView.getContext());
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String searchText = v.getText().toString();
            searchText = searchText.replace(" ", "_");
            searchForImages(searchText);
            return true;
        }
        return false;
    }

    @Override
    public ListAdapter getGridAdapter() {
        return gridAdapter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear:
                mView.clearSearchText();
                break;
        }
    }

    private void searchForImages(String searchText) {

        GetPhotosListService service = RetrofitInstance.getInstance().create(GetPhotosListService.class);
        Call<PhotoList> call = service.getPhotos(mView.getContext().getString(R.string.api_key), searchText, "photo", MAX_PHOTOS_LIMIT);

        call.enqueue(new Callback<PhotoList>() {
            @Override
            public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {
                PhotoList photoList = response.body();
                if (photoList != null) {
                    gridAdapter.setPhotoList(photoList);
                    hideKeyboard();
                }
            }

            @Override
            public void onFailure(Call<PhotoList> call, Throwable t) {
                mView.onCallError();
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) mView.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
    }
}
