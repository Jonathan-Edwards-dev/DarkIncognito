package com.jd.darkincognito.security;

import android.webkit.WebView;

public class MemoryWiper {

    public static void wipe(WebView webView) {
        try {
            webView.clearHistory();
            webView.clearCache(true);
            webView.clearFormData();
            webView.loadUrl("about:blank");
        } catch (Exception ignored) {}
    }
}
