package com.bidding.dell.BIDJUNCTION.Utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 8/10/2016.
 */
public class SRMB extends Application {
    private static Context mContext;
    public static String currentServerDate;

    @Override
    public void onCreate() {
        super.onCreate();
//        mContext = getApplicationContext();
    }
    public static Context getContext() { return mContext; }
    public static void setContext(Context context) { mContext = context; }

}