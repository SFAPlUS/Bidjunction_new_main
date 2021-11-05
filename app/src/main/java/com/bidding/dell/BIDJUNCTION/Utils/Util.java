package com.bidding.dell.BIDJUNCTION.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.bidding.dell.BIDJUNCTION.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;


public class Util {

    public static boolean isFCMRequest=false;
    public static boolean cameraFlag = false;
    public static boolean observerFlag = false;
    public static boolean contactFlag = false;
    public static boolean isPrivacy=false;
    public  static boolean isJourneyStarted=false;
    public static boolean isAutocancel = false;
    public static boolean checkNetStatus(final Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo()== null){
            View view=activity.findViewById(android.R.id.content);
//            final Snackbar snackbar = Snackbar.make(view,"No or poor internet connectivity", Snackbar.LENGTH_LONG);
//            View vw = snackbar.getView();
//            TextView tview = (TextView)vw.findViewById(android.support.design.R.id.snackbar_text);
//            tview.setTextColor(Color.parseColor("#fcc71a"));
            final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            dialog.setTitle("INFO");
            dialog.setMessage("Network disconnected. Please check your Internet connection.");
            dialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    checkNetStatus(activity);
                }
            });
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.show();
                }
            });
            return false;
        }
        return  true;
    }
    public static boolean checkNetStatusResult(final Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo()== null){
            return false;
        }
        return  true;
    }

    public static Bitmap getScaledBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // RECREATE THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }




    public static String getAddressFromLocation(Context context, Location position) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String locality = "";

        try {
            List<Address> listAddresses = geocoder.getFromLocation(position.getLatitude(), position.getLongitude(), 1);
            if(null!=listAddresses && listAddresses.size()>0){
                //locality = listAddresses.get(0).getAddressLine(1);
                locality = listAddresses.get(0).getAddressLine(0) + ", " + listAddresses.get(0).getAddressLine(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locality;
    }
    public static String getDateTime(String mongoDate){
        String outputTime="";
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

            Date date = format.parse(mongoDate);
            int offSet= Calendar.getInstance().getTimeZone().getOffset(date.getTime());
            long dt=date.getTime();

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMM d',' h:mm a");

            outputTime=simpleDateFormat.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputTime;
    }

    public static String getDateTimeEpochAt(Long epochTime){
        String outputTime="";
        try {
            Date date = new Date(epochTime);
            int offSet= Calendar.getInstance().getTimeZone().getOffset(date.getTime());
            long dt=date.getTime();

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM, yyyy - EE '@' h:mm a");

            outputTime=simpleDateFormat.format(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputTime;
    }
    public static String getAmPmEpoch(Long epochTime){
        String outputTime="";
        try {
            Date date = new Date(epochTime);
            int offSet= Calendar.getInstance().getTimeZone().getOffset(date.getTime());
            long dt=date.getTime();

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("h:mma");

            outputTime=simpleDateFormat.format(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputTime;
    }

    public static String hhmm(int min){
        if(min>0){
            String time="";
            if(min>59){
                time=(min/60)+"hr "+(min%60)+" min";
            }else{
                time=min+" min";
            }
            return time;
        }
        return "N/A";
    }

    public static String getCurrentDate(){
        long ms= System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        return simpleDateFormat.format(calendar.getTime());
    }
    public static String getDummyImageText(String text){

        String word = null;
        try {

            String split[] = text.trim().split(" ");
            if (split != null) {
                int len = split.length;
                if (len > 1) {
                    word = split[0].substring(0, 1).toUpperCase() + split[len - 1].substring(0, 1).toUpperCase();
                } else if (len == 1) {
                    word = split[0].substring(0, 1).toUpperCase();
                }
            } else {
                word = "!";
            }
        }catch (Exception e){
            e.printStackTrace();
            word="!";
        }
        return word;
    }
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static int convertPixelsToDp(float px){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }
    public static int convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public static void hideSoftKeyBoard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            if (inputMethodManager.isAcceptingText())
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void hideSoftKeyBoardFragment(Activity activity, View view) {
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showKeyBoardFragment(Activity activity) {
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void subscribeToTopics(String token){

    }

    public static LatLngBounds toBounds(LatLng center, double radiusInMeters) {
        double distanceFromCenterToCorner = radiusInMeters * Math.sqrt(2.0);
        LatLng southwestCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
    }

    public static String getLastnCharacters(String inputString,
                                            int subStringLength){
        int length = inputString.length();
        if(length <= subStringLength){
            return inputString;
        }
        int startIndex = length-subStringLength;
        return inputString.substring(startIndex);
    }

    public static void  changeStatusBarColor(Activity activity){
        try{
            Window window = activity.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        }catch (Exception e){e.printStackTrace();}
    }



//    public static void showCustomLayoutPopup(Activity activity, int layoutId, String Message, Integer iconID, String Color, boolean isSucess) {
//        if(activity!=null) {
//
//            final Dialog popupdialog = new Dialog(activity);
//            Window window = popupdialog.getWindow();
//            //window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            window.requestFeature(Window.FEATURE_NO_TITLE);
//            popupdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            popupdialog.setContentView(layoutId);
//            TextView Messagepopoup = (TextView) popupdialog.findViewById(R.id.Messagepopoup);
//            TextView greetings = (TextView) popupdialog.findViewById(R.id.greetings);
//            ImageView popupImg = (ImageView) popupdialog.findViewById(R.id.icon);
//            LottieAnimationView indicatorS = (LottieAnimationView) popupdialog.findViewById(R.id.sucess1);
//            LottieAnimationView indicatorF = (LottieAnimationView) popupdialog.findViewById(R.id.failure1);
//            if(isSucess){
//                greetings.setText("Congratulations!");
//                indicatorF.setVisibility(View.GONE);
//                indicatorS.setVisibility(View.VISIBLE);
//            }else {
//                greetings.setText("OOPS Sorry!");
//                indicatorF.setVisibility(View.VISIBLE);
//                indicatorS.setVisibility(View.GONE);
//            }
//            LinearLayout toppopupcolor = (LinearLayout) popupdialog.findViewById(R.id.toppopupcolor);
//            Button dismissBtn = (Button) popupdialog.findViewById(R.id.dismissButton);
//            Messagepopoup.setText(Message);
//            popupImg.setImageResource(iconID);
//            toppopupcolor.setBackgroundColor(android.graphics.Color.parseColor(Color));
//
//            //popupImg.setImageResource(iconId);
//            //txt.setText(msg);
//            popupdialog.setCanceledOnTouchOutside(true);
//            popupdialog.setCancelable(true);
//
//            popupdialog.show();
//            dismissBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    popupdialog.dismiss();
//                    popupdialog.cancel();
//                }
//            });
//        }
//    }


    public static String getprocessFPResponse(String url) throws Exception {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String getUrl = url;
        try {
            urlConnection = (HttpURLConnection) new URL(getUrl).openConnection();

            try {
                inputStream = urlConnection.getInputStream();
            }
            catch (Exception ex)
            {
                ex.getMessage();
            }
            return processFPResponseObject(urlConnection, inputStream);
        } finally {
            if(inputStream != null) inputStream.close();
            if(urlConnection != null) urlConnection.disconnect();
        }
    }

    private static String processFPResponseObject(HttpURLConnection urlConnection, InputStream inputStream) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != 200) { throw new Exception(sb.toString()); }
            return sb.toString();
//            return new JSONObject(sb.toString());
        } finally {
            try {inputStream.close();} catch (Exception e){}
        }
    }

    public static void hideKeyBoard(Context context, View view){
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void hideKeyBoardForActivity(Context context){
//        try {
//            if (view != null) {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public static String getJSONFromUrlVchechk(String url,String param1,String param2) throws Exception {

    HttpURLConnection urlConnection = null;
    InputStream inputStream = null;String getUrl = appendParam(appendParam(url, param1), param2);
    try {
        urlConnection = (HttpURLConnection) new URL(getUrl).openConnection();
        try {
            inputStream = urlConnection.getInputStream();
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
        return process(urlConnection, inputStream);
    } finally {
        if(inputStream != null) inputStream.close();
        if(urlConnection != null) urlConnection.disconnect();
    }
    //return null;
}
    private static String process(HttpURLConnection urlConnection, InputStream inputStream) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != 200) { throw new Exception(sb.toString()); }
            // return String.valueOf(statusCode);
            return sb.toString();
        } finally {
            try {inputStream.close();}
            catch (Exception e)
            {
                e.getMessage();
            }
        }
    }
    private static String appendParam(String url, String param) {
        try {
            //    param = param.replaceAll("/","%252F");
            String u = URLEncoder.encode(param, "UTF-8");
            return url + (param.equals("") ? "" : "/" + u);
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public static String convertdateforapi(String cdate)
    {
        String finalString="";
        try

        {
            String start_dt = cdate;
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = (Date)formatter.parse(start_dt);
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            finalString = newFormat.format(date);

        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        return finalString;

    }
    public static String convertapidatetoformat(String cdate)
    {
        String converteddate="";
        try

        {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = (Date) formatter.parse(cdate);
        SimpleDateFormat newFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        converteddate = newFormat1.format(date);
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        return converteddate;
    }
    public static String convertapidatetodisplay(String cdate)
    {
        String converteddate="";
        try

        {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = (Date) formatter.parse(cdate);
        SimpleDateFormat newFormat1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        converteddate = newFormat1.format(date);
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }

        return converteddate;
    }

    public static boolean exapiryornot(String inputDate)
    {
        boolean expairy = false;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = (Date) formatter.parse(inputDate);
            SimpleDateFormat newFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            String fdate = newFormat1.format(date);


            Date currentTime1 = Calendar.getInstance().getTime();
            //2019-08-26T06:28:50.673
            SimpleDateFormat newFormat2 = new SimpleDateFormat("dd-MM-yyyy");
            String curdaten = newFormat2.format(currentTime1);

            Date d1 = newFormat2.parse(fdate);
            Date d2 = newFormat2.parse(curdaten);

            if (d1.compareTo(d2) > 0) {

                expairy = true;
                // When Date d1 > Date d2

            } else if (d1.compareTo(d2) < 0) {

                // When Date d1 < Date d2
                expairy = false;
            } else if (d1.compareTo(d2) == 0) {

                expairy = false;
            }
        }
        catch (Exception ex)
        {
            ex.getMessage();
        }
        return expairy;
    }

}
