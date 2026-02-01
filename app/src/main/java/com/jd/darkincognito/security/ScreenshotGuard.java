package com.jd.darkincognito.security;

import android.app.Activity;
import android.view.WindowManager;

public class ScreenshotGuard {

    public static void enable(Activity activity) {
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
        );
    }
}
