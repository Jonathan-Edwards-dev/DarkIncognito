package com.jd.darkincognito.tor;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public class TorChecker {

    private static final String[] ORBOT_PACKAGES = {
            "org.torproject.android",
            "org.torproject.android.debug"
    };

    // Check if Orbot app is installed
    public static boolean isOrbotInstalled(Context context) {
        PackageManager pm = context.getPackageManager();

        for (String pkg : ORBOT_PACKAGES) {
            try {
                PackageInfo info = pm.getPackageInfo(pkg, PackageManager.GET_ACTIVITIES);
                return true;
            } catch (PackageManager.NameNotFoundException ignored) {}
        }
        return false;
    }

    // Check if VPN (Orbot VPN mode) is active
    public static boolean isTorVpnActive(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) return false;

        Network active = cm.getActiveNetwork();
        if (active == null) return false;

        NetworkCapabilities caps = cm.getNetworkCapabilities(active);
        if (caps == null) return false;

        return caps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
    }
}
