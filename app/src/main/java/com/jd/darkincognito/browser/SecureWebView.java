package com.jd.darkincognito.browser;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.jd.darkincognito.security.MemoryWiper;


public class SecureWebView extends WebView {

    public SecureWebView(Context context) {
        super(context);
        initSecureSettings();
    }

    public SecureWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSecureSettings();
    }

    private void initSecureSettings() {
        WebSettings settings = getSettings();

        settings.setJavaScriptEnabled(true);  // keep ON for Tor browsing
        settings.setDomStorageEnabled(false); // disable local storage

        // ❗ No saving anything
        settings.setSavePassword(false);
        settings.setSaveFormData(false);

        // no caching means RAM-only browsing
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        clearCache(true);

        // block file/system access
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);

        // no cookies (v1 strict)
        android.webkit.CookieManager.getInstance().setAcceptCookie(false);
        android.webkit.CookieManager.getInstance().removeAllCookies(null);
        android.webkit.CookieManager.getInstance().flush();

        // reduce fingerprinting
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

    }

    public void wipe() {
        MemoryWiper.wipe(this);
    }

}
