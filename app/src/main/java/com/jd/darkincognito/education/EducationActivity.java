package com.jd.darkincognito.education;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.jd.darkincognito.R;
import com.jd.darkincognito.ui.BrowserActivity;

public class EducationActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_web);

        webView = findViewById(R.id.educationWeb);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(false);

        // Prevent leaving the onboarding
        webView.setWebViewClient(new android.webkit.WebViewClient());

        webView.addJavascriptInterface(new Object() {
            @android.webkit.JavascriptInterface
            public void enterBrowser() {
                runOnUiThread(() -> {
                    startActivity(new Intent(EducationActivity.this, BrowserActivity.class));
                    finish();
                });
            }
        }, "Android");

        webView.loadUrl("file:///android_asset/education.html");
    }
}
