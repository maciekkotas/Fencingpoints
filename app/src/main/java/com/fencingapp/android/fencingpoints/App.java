package com.fencingapp.android.fencingpoints;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by macie on 20.07.2017.
 */

public  class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .

    setDefaultFontPath("simplifica.ttf")

    .

    setFontAttrId(R.attr.fontPath)

    .

    build()

    );
}


}
