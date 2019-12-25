package com.mp.stockscanner.di.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.mp.stockscanner.di.app.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppNetworkManager implements NetworkManager {

    private Context context;

    @Inject
    public AppNetworkManager(@ApplicationContext Context context) {
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    @Override
    public String connectionType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return "WIFI";
            }

            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return "MOBILE";
            }
        }
        return "OFFLINE";
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();
                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();
                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);
                    return ((nc != null && nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                            || (nc != null && nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)));
                }
            }
        }

        return false;


    }
}
