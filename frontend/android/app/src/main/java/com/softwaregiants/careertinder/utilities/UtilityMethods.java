package com.softwaregiants.careertinder.utilities;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UtilityMethods {
    public static Point getDisplaySize(Context mContext){
        try {
            if(Build.VERSION.SDK_INT > 16) {
                WindowManager wm = ((WindowManager) (mContext.getSystemService(Context.WINDOW_SERVICE)));
                Point size = new Point();
                wm.getDefaultDisplay().getRealSize(size);
                return size;
            }else{
                return new Point(0, 0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Point(0, 0);
        }
    }
    public static int dpToPx(int dp, Context mContext) {
        return (int) (dp * ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
//        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
