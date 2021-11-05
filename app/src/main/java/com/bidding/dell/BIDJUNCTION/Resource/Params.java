package com.bidding.dell.BIDJUNCTION.Resource;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by dell on 16/6/17.
 */

public class Params {
    public static final int CAMERA_REQUEST_EDIT_PROFILE =   101;
    public static final int CAMERA_REQUEST_POD_UPLOAD=   103;
    //public static final int GALLERY_PICTURE_POD_UPLOAD=   104;
    public static final int GALLERY_PICTURE_EDIT_PROFILE=   102;
    public static final int PICK_CONTACT                =   206;
    public static final String BID_DETAILS_PAYMENT_DATA = "BID_DETAILS_PAYMENT_DATA";
    public static       int IS_NAME_CHECKED             =   0;
    public static final int IS_EMAIL_UNIQUE             =   0;
    public static final int IS_PHONE_UNIQUE             =   0;
    public static final int IS_SOCKET_CONNECTED         =   0;
    public static int IS_ON_TRIP                        =   0;
    public static int IS_LOADER_ON                      =   0;
    public static String IS_PAYMENT_ONLINE              =   "IS_PAYMENT_ONLINE";
    public static Long startTime                        =   System.currentTimeMillis();
    public static int IS_FORGETPASSWORD                 =   0;

    public static float ICON_INACTIVE                   =   0.5f;
    public static float ICON_ACTIVE                     =   1.0f;
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE1stpage =8000 ;
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE =9999 ;



    public static final String USER_USERKEY                  =   "USERKEY";
    public static final String USER_USERID                =   "USERID";
    public static final String USER_USERName                =   "USER_USERName";
    public static final String USER_Password                =   "PASSWORD";
    public static final String USER_RoleMapping                =   "USER_RoleMapping";
    public static final String USER_Role                =   "USER_Role";
    public static final String LoginDetails                =   "LoginDetails";
    public static final String CompanyId                =   "CompanyId";
    public static final String CompanyName                =   "CompanyName";
    public static final String SearchStatus                =   "SearchStatus";




    public static final String TAG                      =   "++LOGDATA++";



    public static final String SMS_OTP_RECEIVER      =  "SMS_OTP_RECEIVER";



    public static boolean popupresult = false;


    public static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(new LatLng(23.63936, 68.14712), new LatLng(28.20453, 97.34466));
}
