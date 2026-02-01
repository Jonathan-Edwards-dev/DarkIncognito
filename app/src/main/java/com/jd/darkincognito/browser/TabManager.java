package com.jd.darkincognito.browser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jd.darkincognito.R;

import java.util.ArrayList;

public class TabManager {

    // ---------------- Tab Model ----------------
    public static class Tab {
        public SecureWebView webView;
        public String title = "New Tab";
        public String url = "";
    }


    // ------------- Change Listener -------------
    public interface OnTabChangeListener {
        void onTabChanged(Tab tab);
    }

    private OnTabChangeListener listener;

    public void setOnTabChangeListener(OnTabChangeListener l) {
        this.listener = l;
    }

    // ---------------- Core ----------------
    private final Context context;
    private final FrameLayout container;
    private final ArrayList<Tab> tabs = new ArrayList<>();
    private int currentIndex = -1;

    private BottomSheetDialog sheet;

    public TabManager(Context context, FrameLayout container) {
        this.context = context;
        this.container = container;
    }

    // ---------------- Tabs ----------------
    public void newTab(String url) {
        Tab tab = new Tab();
        tab.url = url;

        SecureWebView webView = new SecureWebView(context);
        tab.webView = webView;

        webView.setWebViewClient(new android.webkit.WebViewClient() {
            @Override
            public void onPageFinished(android.webkit.WebView view, String loadedUrl) {
                tab.url = loadedUrl;
                tab.title = view.getTitle() != null ? view.getTitle() : "New Tab";

                if (listener != null && current() == tab) {
                    listener.onTabChanged(tab);
                }
            }
        });

        webView.loadUrl(url);
        tabs.add(tab);
        switchTo(tabs.size() - 1);
    }



    public Tab current() {
        if (currentIndex < 0 || currentIndex >= tabs.size()) return null;
        return tabs.get(currentIndex);
    }

    public ArrayList<Tab> getTabs() {
        return tabs;
    }

    public void switchTo(int index) {
        if (index < 0 || index >= tabs.size()) return;

        Tab tab = tabs.get(index);

        container.removeAllViews();
        container.addView(tab.webView);
        currentIndex = index;

        if (listener != null) listener.onTabChanged(tab);
    }


    public void close(int index) {
        if (index < 0 || index >= tabs.size()) return;

        tabs.get(index).webView.destroy();
        tabs.remove(index);

        // Always keep one tab
        if (tabs.isEmpty()) {
            newTab("file:///android_asset/home.html");
            return;
        }

        if (index >= tabs.size()) index = tabs.size() - 1;
        switchTo(index);
    }

    public void clear() {
        for (Tab t : tabs) t.webView.destroy();
        tabs.clear();
        container.removeAllViews();
        currentIndex = -1;
    }

    // ---------------- Tab Sheet ----------------
    public void showSheet() {
        sheet = new BottomSheetDialog(context);
        View v = LayoutInflater.from(context).inflate(R.layout.sheet_tabs, null);
        RecyclerView list = v.findViewById(R.id.tabList);

        list.setLayoutManager(new LinearLayoutManager(context));
        list.setAdapter(new TabAdapter(this));

        sheet.setContentView(v);
        sheet.show();
    }

    public void hideSheet() {
        if (sheet != null) sheet.dismiss();
    }
}
