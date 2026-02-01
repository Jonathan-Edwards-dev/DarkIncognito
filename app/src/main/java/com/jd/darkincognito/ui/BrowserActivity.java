package com.jd.darkincognito.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jd.darkincognito.R;
import com.jd.darkincognito.browser.TabManager;
import com.jd.darkincognito.security.KillSwitch;
import com.jd.darkincognito.security.ScreenshotGuard;
import com.jd.darkincognito.tor.TorChecker;
import com.jd.darkincognito.tor.TorSessionManager;

public class BrowserActivity extends AppCompatActivity {

    private FrameLayout webContainer;
    private TabManager tabManager;
    private ImageView lockIcon;

    private KillSwitch killSwitch;
    private TorSessionManager torManager;

    private EditText urlBar;
    private ProgressBar progressBar;
    private TextView torStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        lockIcon = findViewById(R.id.lockIcon);


        // UI
        urlBar = findViewById(R.id.urlBar);
        progressBar = findViewById(R.id.progressBar);
        torStatus = findViewById(R.id.torStatus);
        webContainer = findViewById(R.id.webContainer);

        ImageButton btnNewTab = findViewById(R.id.btnNewTab);
        ImageButton btnTabs = findViewById(R.id.btnTabs);

        // Security
        ScreenshotGuard.enable(this);

        // Tor
        torManager = new TorSessionManager(this);
        if (!torManager.ensureTorAvailable()) return;

        // Tab engine (ONLY ONE INSTANCE)
        tabManager = new TabManager(this, webContainer);
        tabManager.setOnTabChangeListener(tab -> {
            if (tab.url != null && tab.url.startsWith("http")) {
                urlBar.setText(tab.url);
            } else {
                urlBar.setText("");
            }
            updateSecurityIcon();
        });


        // First tab
        tabManager.newTab("file:///android_asset/home.html");

        // Kill switch
        killSwitch = new KillSwitch(this, tabManager.current().webView);

        // Buttons
        btnNewTab.setOnClickListener(v ->
                tabManager.newTab("file:///android_asset/home.html")
        );

        btnTabs.setOnClickListener(v -> tabManager.showSheet());

        // Pull-to-refresh
        SwipeRefreshLayout swipe = findViewById(R.id.swipeRefresh);

        swipe.setOnRefreshListener(() -> {
            if (tabManager.current() != null)
                tabManager.current().webView.reload();
            swipe.setRefreshing(false);
        });

        // Only enable refresh when WebView is at top
        swipe.setOnChildScrollUpCallback((parent, child) -> {
            if (tabManager.current() == null) return false;
            return tabManager.current().webView.getScrollY() > 0;
        });


        // URL bar
        urlBar.setOnEditorActionListener((v, actionId, event) -> {
            String input = urlBar.getText().toString().trim();
            if (input.isEmpty()) return true;

            // Raw onion → show guide
            if (input.endsWith(".onion")) {
                tabManager.current().webView.loadUrl("file:///android_asset/onion_help.html");
                return true;
            }

            String url;

            // Looks like a domain or URL
            if (input.contains(".") || input.startsWith("http") || input.startsWith("www")) {
                if (!input.startsWith("http://") && !input.startsWith("https://")) {
                    url = "https://" + input;
                } else {
                    url = input;
                }
            } else {
                // Treat as search query
                url = "https://duckduckgo.com/?q=" + android.net.Uri.encode(input);
            }

            tabManager.current().webView.loadUrl(url);
            return true;
        });


        // Progress bar
        tabManager.current().webView.setWebChromeClient(new android.webkit.WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    progressBar.setVisibility(android.view.View.VISIBLE);
                    progressBar.setProgress(newProgress);
                } else {
                    progressBar.setVisibility(android.view.View.GONE);
                }
            }
        });
        updateSecurityIcon();


        updateTorStatus();
    }

    private void updateTorStatus() {
        if (TorChecker.isTorVpnActive(this)) {
            torStatus.setText("TOR CONNECTED");
            torStatus.setBackgroundColor(0xFF2E7D32);
        } else {
            torStatus.setText("TOR NOT CONNECTED");
            torStatus.setBackgroundColor(0xFFC62828);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTorStatus();
        updateSecurityIcon();
    }

    @Override
    protected void onPause() {
        super.onPause();
        killSwitch.trigger();
    }

    @Override
    protected void onStop() {
        super.onStop();
        killSwitch.trigger();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killSwitch.trigger();
        if (tabManager != null) tabManager.clear();
    }

    @Override
    public void onBackPressed() {
        if (tabManager.current() != null && tabManager.current().webView.canGoBack()) {
            tabManager.current().webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void updateSecurityIcon() {
        boolean tor = TorChecker.isTorVpnActive(this);

        if (!tor) {
            lockIcon.setColorFilter(0xFFC62828); // red
            return;
        }

        if (tabManager.current() == null) {
            lockIcon.setColorFilter(0xFF2E7D32);
            return;
        }

        String url = tabManager.current().url;
        if (url == null) {
            lockIcon.setColorFilter(0xFF2E7D32);
            return;
        }

        if (url.contains(".onion")) {
            lockIcon.setColorFilter(0xFF6A1B9A); // purple
        }
        else if (url.startsWith("https://")) {
            lockIcon.setColorFilter(0xFF2E7D32); // green
        }
        else {
            lockIcon.setColorFilter(0xFFF57C00); // orange
        }
    }


}
