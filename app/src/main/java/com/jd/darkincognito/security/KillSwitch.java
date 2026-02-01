package com.jd.darkincognito.security;

import android.app.Activity;

import com.jd.darkincognito.browser.SecureWebView;

public class KillSwitch {

    private final Activity activity;
    private final SecureWebView webView;

    public KillSwitch(Activity activity, SecureWebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    public void trigger() {
        if (webView != null) {
            webView.wipe();
            webView.loadUrl("about:blank");
        }
        activity.finish();
    }
}
