package com.softwaregiants.careertinder.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.res.ResourcesCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilityMethods {

    private static String TAG = "UtilityMethods";

    public static Point getDisplaySize(Context mContext){
        try {
            WindowManager wm = ((WindowManager) (mContext.getSystemService(Context.WINDOW_SERVICE)));
            Point size = new Point();
            if (wm != null) {
                wm.getDefaultDisplay().getRealSize(size);
            }
            return size;
        }catch (Exception e){
            Log.e(TAG, "getDisplaySize: ", e);
            return new Point(0, 0);
        }
    }
    public static int dpToPx(int dp, Context mContext) {
        return (int) (dp * ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
//        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean isConnected(Context mContext)
    {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (manager != null) {
            info = manager.getActiveNetworkInfo();
        }
        boolean isConnected = (info != null && info.isConnected());
        if ( !isConnected ) {
            Toast.makeText(mContext,Constants.MSG_CONNECTION_ERROR,Toast.LENGTH_SHORT).show();
        }
        return isConnected;
    }

    public static String sha224Hash(String toHash )
    {
        String hash = null;
        try
        {
            MessageDigest digest = MessageDigest.getInstance( "SHA-224" );
            byte[] bytes = toHash.getBytes(StandardCharsets.UTF_8);
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();

            // This is ~55x faster than looping and String.formating()
            hash = bytesToHex( bytes );
        }
        catch( NoSuchAlgorithmException e )
        {
            Log.e(TAG, "sha224Hash: ", e);
        }
        return hash;
    }

    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex( byte[] bytes )
    {
        char[] hexChars = new char[ bytes.length * 2 ];
        for( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[ j ] & 0xFF;
            hexChars[ j * 2 ] = hexArray[ v >>> 4 ];
            hexChars[ j * 2 + 1 ] = hexArray[ v & 0x0F ];
        }
        return new String( hexChars );
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getEpochTime() {
        return "_" + System.currentTimeMillis();
    }

    public static Drawable getDrawable(Context mContext, int drawableId) {
        return ResourcesCompat.getDrawable(mContext.getResources(), drawableId, null);
    }

    public static Boolean isNumeric( String str ) {
        String regex ="^[0-9]+$";
        return str.matches(regex);
    }

    public static boolean validatePhone(String phone) {
        String regex ="^\\+?[0-9]{8,20}$";
        return phone.matches(regex);
    }
}
