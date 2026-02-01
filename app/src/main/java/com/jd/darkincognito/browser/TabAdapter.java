package com.jd.darkincognito.browser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jd.darkincognito.R;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.Holder> {

    private final TabManager manager;

    public TabAdapter(TabManager manager) {
        this.manager = manager;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tab, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder h, int pos) {
        var tab = manager.getTabs().get(pos);
        h.title.setText(tab.title);

        h.itemView.setOnClickListener(v -> {
            manager.switchTo(pos);
            manager.hideSheet();
        });

        h.close.setOnClickListener(v -> {
            manager.close(pos);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return manager.getTabs().size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title;
        ImageButton close;

        Holder(View v) {
            super(v);
            title = v.findViewById(R.id.tabTitle);
            close = v.findViewById(R.id.btnClose);
        }
    }
}
