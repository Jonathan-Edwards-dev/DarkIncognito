package com.jd.darkincognito.tor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class TorSessionManager {

    private final Activity activity;

    public TorSessionManager(Activity activity) {
        this.activity = activity;
    }

    public boolean ensureTorAvailable() {
        if (!TorChecker.isOrbotInstalled(activity)) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://orbot.app/en/")));
            activity.finish();
            return false;
        }

        Toast.makeText(activity, "Privacy is not a license to break the law.", Toast.LENGTH_LONG).show();
        return true;
    }
}
