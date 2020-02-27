package com.talya.shutterfly.ui;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;

public interface IView {
    Context getContext();

    void onCallError();

    void clearSearchText();

    IBinder getWindowToken();
}
