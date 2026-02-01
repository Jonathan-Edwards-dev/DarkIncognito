package com.jd.darkincognito.tor;

import android.webkit.WebView;
import java.lang.reflect.Method;

public class TorProxyConfig {

    public static void setProxy(WebView webView) {
        try {
            // Most devices use 127.0.0.1:9050 for Orbot
            String host = "127.0.0.1";
            int port = 9050;

            Class webViewClass = Class.forName("android.webkit.WebView");
            Method setProxy = webViewClass.getDeclaredMethod(
                    "setProxyOverride",
                    String.class,
                    java.util.List.class,
                    String.class
            );

            setProxy.invoke(null,
                    host + ":" + port,
                    null,
                    null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
