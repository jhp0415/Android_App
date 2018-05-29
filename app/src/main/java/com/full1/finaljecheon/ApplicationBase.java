package com.full1.finaljecheon;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Full1 on 2017-07-25.
 */

public class ApplicationBase extends Application {
    @Override public void onCreate() {
        super.onCreate();
        // 폰트 정의
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NanumBarunGothic.ttf"))
                        .addBold(Typekit.createFromAsset(this, "NanumBarunGothicBold.ttf"));
    }


}
