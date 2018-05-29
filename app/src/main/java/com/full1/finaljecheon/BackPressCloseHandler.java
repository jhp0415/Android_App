package com.full1.finaljecheon;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Full1 on 2017-07-30.
 */
public class BackPressCloseHandler {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + FINISH_INTERVAL_TIME) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + FINISH_INTERVAL_TIME) {
            activity.moveTaskToBack(true);
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity,
                "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT);
        toast.show();
    }
}
