package com.ananada.addme.neutrinos.anandaguruji;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.ananada.addme.neutrinos.anandaguruji.constants.Constants;

import org.json.JSONObject;


public class Utils {
    private static final String TAG = "Utils";
    public static boolean checkNetworkConnection(Context context) {
        boolean isConnected = false;
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = ((activeNetwork != null) && (activeNetwork.isConnectedOrConnecting()));
        } catch (Exception e) {
            Logger.logE("Error", "check network", e);
        }
        return isConnected;
    }
    public static int checkIntegerData(JSONObject value, String key) {
        try {
            if (value != null && value.has(key) && !"".equals(value.getString(key))) {
                return value.getInt(key);
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, TAG, e);
        }
        return 0;
    }

    public static String nullCheck(JSONObject value, String key) {
        try {
            if (value != null && value.has(key)) {
                return value.getString(key);
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, TAG, e);
        }
        return null;
    }

    public static String checkData(JSONObject value, String key) {
        try {
            if (value != null && value.has(key)) {
                return value.getString(key);
            }
        } catch (Exception e) {
            Log.d(Constants.PROJECT, TAG, e);
        }
        return "";
    }
    public static String getMonthWithYear(String month, String year) {
        Logger.logD("Month-->",month+"");
        String[] getMonthYear= { "Jan,","Feb,","Mar,","Apr,","May,","Jun,","Jul,","Aug,","Sep,","Oct,","Nov,","Dec," };
        return getMonthYear[Integer.parseInt(month)-1]+year;
    }
    public static void displayToast(Context context, String s) {

        if (context != null && s!=null) {
            Toast t1 = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            t1.setGravity(Gravity.CENTER, 0, 0);
            t1.show();
        }
    }

    public static String getDeviceId(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getLocation(GPSTracker gpsTracker, Activity context) {
        String stringLatitude = "0.0";
        String stringLongitude = "0.0";
        if (Double.doubleToRawLongBits(gpsTracker.latitude) == 0 || Double.doubleToRawLongBits(gpsTracker.longitude) == 0) {
            MyCurrentLocationTracker tracker = new MyCurrentLocationTracker(context, null, null);
            Location loc = tracker.getLocation(null, null);
            if (loc != null) {
                stringLatitude = String.valueOf(loc.getLatitude());
                stringLongitude = String.valueOf(loc.getLongitude());
            }
        } else {
            stringLatitude = String.valueOf(gpsTracker.latitude);
            stringLongitude = String.valueOf(gpsTracker.longitude);
        }
        gpsTracker.stopUsingGPS();
        return stringLatitude+","+stringLongitude;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static String getDeviceName() {
        return Build.MODEL;
    }


    public static String getDeviceDimention(Activity activity) {
        String sendDeviceType = "";
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
        if (diagonalInches >= 6.5) {
            Logger.logD("the device width-->Tab", "Tab");
            sendDeviceType = "Tab";
        } else if (diagonalInches >= 10.0) {
            Logger.logD("the device width-->TV", "TV");
            sendDeviceType = "TV";
        } else {
            Logger.logD("the device width-->mobile", "mobile");
            sendDeviceType = "Mobile";
        }
        return sendDeviceType;
    }

}
