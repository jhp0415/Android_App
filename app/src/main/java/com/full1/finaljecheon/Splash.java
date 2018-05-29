package com.full1.finaljecheon;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Full1 on 2017-07-27.
 */

public class Splash extends Activity {
    @Override
    protected void attachBaseContext(Context newBase) {
        //폰트설정정
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symbol_layout);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, 1000);// 3 초


    }
}
