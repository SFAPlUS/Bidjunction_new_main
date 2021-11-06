package com.bidding.dell.BIDJUNCTION.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bidding.dell.BIDJUNCTION.Adapters.ViewPagerAdapter;
import com.bidding.dell.BIDJUNCTION.Models.LoginBO;
import com.bidding.dell.BIDJUNCTION.Network.RequestQueueService;
import com.bidding.dell.BIDJUNCTION.R;
import com.bidding.dell.BIDJUNCTION.Resource.Params;
import com.bidding.dell.BIDJUNCTION.Utils.DatabaseHelper;
import com.bidding.dell.BIDJUNCTION.Utils.RecyclerItemClickListener;
import com.bidding.dell.BIDJUNCTION.Utils.SRMB;
import com.bidding.dell.BIDJUNCTION.Utils.Util;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private GoogleApiClient googleApiClient;
    private Location mylocation;
    public static int backCount;

    Timer LocationUpdateTimers = new Timer();
   // List<LeadOuletBO> leadOuletBOS;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    Timer LocationUpdateTimer = new Timer();
    int i, j;
    int counti = 0, countj = 0;
    ViewPager viewPager;
    public static HomeActivity homeActivity;
    private NotificationManager myNotificationManager;
    private int notificationIdOne = 111;
    private int notificationIdTwo = 112;
    private int numMessagesOne = 0;
    private int numMessagesTwo = 0;
    TextView Ll, username, role;
    ImageView companyimg;
    RecyclerView.Adapter mAdapter;
    RecyclerView.Adapter mAdapter1;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<LoginBO> mDataset = new ArrayList<>();

    TabLayout tabLayout;
    //ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    //Button btnFloat;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setTitle("Assessment");
        setSupportActionBar(toolbar);
        SRMB.setContext(this);
        homeActivity = this;


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        ImageView menuRight = (ImageView) findViewById(R.id.menuRight);
        ImageView sync = (ImageView) findViewById(R.id.sync);
        ImageView home = (ImageView) findViewById(R.id.home);

        //btnFloat = findViewById(R.id.btn_float);

        //Ll = (TextView) findViewById(R.id.Ll);
        // checkPermissions();
        // checkAndRequestPermissions();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);




        //  moveToLandingHomeFragment();
        if (Util.isNetworkAvailable(homeActivity)) {

//                VersionChecker versionChecker = new VersionChecker(MainActivity.this);
//                versionChecker.execute();
            //CurrentversionChechk();
        } else {
            Toast.makeText(homeActivity, "Please Connect to Internet", Toast.LENGTH_LONG).show();
        }
        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });

        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view2);
        navigationView2.setNavigationItemSelectedListener(this);
        Menu nav_Menu = navigationView2.getMenu();
        if (DatabaseHelper.getAccountDetails(homeActivity, Params.USER_Role).equalsIgnoreCase("Bay Supervisor") ||
                DatabaseHelper.getAccountDetails(homeActivity, Params.USER_Role).equalsIgnoreCase("Administrator")
                || DatabaseHelper.getAccountDetails(homeActivity, Params.USER_Role).equalsIgnoreCase("Factory")) {


            if (DatabaseHelper.getAccountDetails(homeActivity, Params.USER_Role).equalsIgnoreCase("Administrator")) {
                nav_Menu.findItem(R.id.nav_bays).setVisible(true);
                nav_Menu.findItem(R.id.nav_bayitem).setVisible(true);
                nav_Menu.findItem(R.id.nav_otscontrol).setVisible(true);
                nav_Menu.findItem(R.id.nav_chnagecompany).setVisible(true);
            } else {
                nav_Menu.findItem(R.id.nav_bays).setVisible(true);
                nav_Menu.findItem(R.id.nav_bayitem).setVisible(true);
                nav_Menu.findItem(R.id.nav_otscontrol).setVisible(false);
                nav_Menu.findItem(R.id.nav_chnagecompany).setVisible(true);
            }
        } else {
            nav_Menu.findItem(R.id.nav_bays).setVisible(false);
            nav_Menu.findItem(R.id.nav_bayitem).setVisible(false);
            nav_Menu.findItem(R.id.nav_otscontrol).setVisible(false);
            nav_Menu.findItem(R.id.nav_chnagecompany).setVisible(true);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, SplashActivity.class));
                finish();
            }
        });
        View hView = navigationView2.getHeaderView(0);

        username = hView.findViewById(R.id.username);
        role = hView.findViewById(R.id.role);
        companyimg = hView.findViewById(R.id.companyimg);

        role.setText(DatabaseHelper.getAccountDetails(homeActivity, Params.USER_Role));
        username.setText(DatabaseHelper.getAccountDetails(homeActivity, Params.USER_USERName) + "-" + DatabaseHelper.getAccountDetails(homeActivity, Params.USER_USERKEY));

        if (DatabaseHelper.getAccountDetails(homeActivity, Params.CompanyName).equalsIgnoreCase("SRMB")) {
            companyimg.setBackgroundResource(R.drawable.srmb);
        } else if (DatabaseHelper.getAccountDetails(homeActivity, Params.CompanyName).equalsIgnoreCase("TOPTECH")) {
            companyimg.setBackgroundResource(R.drawable.toptech);
        } else {
            companyimg.setBackgroundResource(R.drawable.ic_genrelcompany);
        }

    }

    /*private static void moveToLandingHomeFragment() {

        if (DatabaseHelper.getAccountDetails(homeActivity, Params.USER_Role).equalsIgnoreCase("Order Controller")) {
            OrderControl landingHome = new OrderControl();
            //pushFragmentsStatic(landingHome, true, null);
        } else {
            OrdersTrackingFragment landingHome = new OrdersTrackingFragment();
            // pushFragmentsStatic(landingHome, true, null);
        }


    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String text = "";

        if (id == R.id.nav_Logout) {
            logOutApp();
        }
        if (id == R.id.nav_bays) {
         //   GridBaymangementListFragment landingHome = new GridBaymangementListFragment();
            // pushFragmentsStatic(landingHome, true, null);
        }
        if (id == R.id.nav_bayitem) {
           // BayItemMapFragment bayItemMapFragment = new BayItemMapFragment();
            // pushFragmentsStatic(bayItemMapFragment, true, null);
        } else if (id == R.id.nav_otscontrol) {
            //OrderControl landingHome = new OrderControl();
            // pushFragmentsStatic(landingHome, true, null);
        } else if (id == R.id.nav_chnagecompany) {
            try {
                String logindetails = DatabaseHelper.getAccountDetails(homeActivity, Params.LoginDetails);

                JSONArray jsonArray = new JSONArray(logindetails);
                if (jsonArray.length() >= 2) {
                    mDataset = new ArrayList<LoginBO>();
                    /*Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
                    mDataset.addAll(Arrays.asList(gson.fromJson(jsonArray.toString(), LoginBO[].class)));*/
                    //showStudentPopup(homeActivity,mDataset);


                } else {
                    Toast.makeText(homeActivity, "U have only one company Permission", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
//        else if (id == R.id.nav_Routine) {
//            Intent intent = new Intent(MainActivity.this, AddRoutine.class);
//            intent.putExtra("sid","Insert");
//            startActivity(intent);
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_home);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    public void onFragmentInteraction(Uri uri) {

    }

    public static void logOutApp() {
        DatabaseHelper.tearDown();
        Intent intent = new Intent(homeActivity, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        homeActivity.startActivity(intent);
        homeActivity.finish();
    }

    public static void showPopup(String Message, Integer iconID, String Color, boolean isSucess) {
        if (homeActivity != null) {
            final Dialog popupdialog = new Dialog(homeActivity);
            Window window = popupdialog.getWindow();
            popupdialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            //window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.requestFeature(Window.FEATURE_NO_TITLE);
            popupdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            popupdialog.setContentView(R.layout.bidsucessfullpopup);
            TextView Messagepopoup = popupdialog.findViewById(R.id.Messagepopoup);
            TextView greetings = popupdialog.findViewById(R.id.greetings);
            ImageView popupImg = popupdialog.findViewById(R.id.icon);
            ImageView indicatorS = popupdialog.findViewById(R.id.sucess1);
            if (isSucess) {
                greetings.setText("Congratulations!");
                indicatorS.setVisibility(View.VISIBLE);
                indicatorS.setBackgroundResource(R.drawable.right);
            } else {
                greetings.setText("OOPS Sorry!");
                indicatorS.setVisibility(View.VISIBLE);
                indicatorS.setBackgroundResource(R.drawable.cross);
            }
            //LinearLayout toppopupcolor = popupdialog.findViewById(R.id.toppopupcolor);
            Button dismissBtn = popupdialog.findViewById(R.id.dismissButton);
            Messagepopoup.setText(Message);
            // toppopupcolor.setBackgroundColor(android.graphics.Color.parseColor(Color));

            //popupImg.setImageResource(iconId);
            //txt.setText(msg);
            popupdialog.setCanceledOnTouchOutside(true);
            popupdialog.setCancelable(true);

            popupdialog.show();
            dismissBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupdialog.dismiss();
                    popupdialog.cancel();

                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.checkNetStatus(homeActivity)) {
            //CurrentversionChechk();
        }

//        try {
//            LocationUpdateTimer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            updateTextView();
//
//
//                        }
//                    });
//                }
//            },1000,2*60*1000);//2*60*1000
//        }
//        catch (Exception ex)
//        {
//            ex.getMessage();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermissions();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {

        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public static void pushFragmentsStatic(Fragment fragment, boolean shouldAdd, String tag) {
        backCount = 0;
        FragmentManager manager = homeActivity.getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        //ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        ft.replace(R.id.fragment_container, fragment, tag);

        if (shouldAdd) {
            ft.addToBackStack("ScreenStack");
        } else {
            manager.popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        ft.commit();
        //ft.commitAllowingStateLoss();
    }

    private boolean checkAndRequestPermissions() {
        int permissionBluetooth = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.BLUETOOTH);
        int permissionWriteStorage = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionReadStorage = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        int camPermission = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.CAMERA);
        int smsPermission = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.RECEIVE_SMS);
        int smsPermissionread = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.READ_SMS);
        int IMEIPermissionread = ContextCompat.checkSelfPermission(homeActivity, android.Manifest.permission.READ_PHONE_STATE);
        List<String> listPermissionsNeeded = new ArrayList<>();
//        if (contactPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
//        }
        if (permissionWriteStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (smsPermissionread != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_SMS);
        }
        if (permissionReadStorage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (smsPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.RECEIVE_SMS);
        }
        if (camPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (permissionBluetooth != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.BLUETOOTH);
        }
        if (IMEIPermissionread != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(homeActivity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*public void CurrentversionChechk() {


        RequestQueueService.showProgressDialog(homeActivity);
        try {
            String url = getResources().getString(R.string.sfaserver_url) + "/OtherVisit.aspx?msgtxt=22|" + "OTS";
            //http://devezyschoolwebapi.azurewebsites.net/notification/get-all-notice/2e09a23e-cd9e-4069-bdb2-e911fa168b34
            //String url=getResources().getString(R.string.server_url)+"/ezy-internal/get-apk-information/"+uid;
            Log.d("openLoadRequestAll", "url>>: " + url);
            // RequestQueueService requestQueue = Volley.newRequestQueue(getActivity());

            Log.d("openLoadRequestAll", "url>>: " + url);
            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {

                                String PlaystorevCode = response.getJSONObject(0).optString("CurrentVersion");
                                String versionCode = String.valueOf(BuildConfig.VERSION_CODE);
                                String versionName = BuildConfig.VERSION_NAME;
                                if (Float.parseFloat(PlaystorevCode) > Float.parseFloat(versionCode)) {
                                    if (response.getJSONObject(0).optString("IsMandetory").equalsIgnoreCase("true")) {
                                        showUpdateMessage(homeActivity, 1);

                                    } else {
                                        showUpdateMessage(homeActivity, 0);
                                    }

                                }

                                RequestQueueService.cancelProgressDialog();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something when error occurred
                            RequestQueueService.cancelProgressDialog();

                        }
                    }
            );
            RequestQueueService.getInstance(homeActivity).addToRequestQueue(jsonArrayRequest);
            // Add JsonArrayRequest to the RequestQueue

        } catch (Exception e) {
            // earningListener.onFetchFailure(getResources().getString(R.string.enter_date));
            e.printStackTrace();
        }

    }*/

    public void showUpdateMessage(Context context, int isMandatory) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog);
        dialog.setCancelable(false);
        dialog.setMessage("You are missing out! Please update your app to avail new features.");
        dialog.setPositiveButton("Update Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

                //logOutApp();
            }
        });
        if (isMandatory == 0) {
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        dialog.show();
    }

    public void showStudentPopup(final Context context, final List<LoginBO> loginBOList) {
        if (homeActivity != null) {
            final Dialog popupdialog = new Dialog(homeActivity);
            popupdialog.setContentView(R.layout.companylistpopup);
//
            final RecyclerView studentlist = (RecyclerView) popupdialog.findViewById(R.id.allbidsrecyclerview);

            layoutManager = new LinearLayoutManager(context);
            studentlist.setLayoutManager(layoutManager);
            // mAdapter = new MyCompanyListAdapter(loginBOList, context);
            studentlist.setVisibility(View.VISIBLE);
            studentlist.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            studentlist.addOnItemTouchListener(new RecyclerItemClickListener(context, studentlist, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    try {

                        DatabaseHelper.setAccountDetails(homeActivity, Params.USER_USERKEY, loginBOList.get(position).getUsercode());
                        DatabaseHelper.setAccountDetails(homeActivity, Params.USER_USERID, loginBOList.get(position).getUserid());
                        DatabaseHelper.setAccountDetails(homeActivity, Params.USER_Password, loginBOList.get(position).getPassword());
                        DatabaseHelper.setAccountDetails(homeActivity, Params.USER_USERName, loginBOList.get(position).getUsername());

                       /* Gson gson = new Gson();
                        String json = gson.toJson(loginBOList);
                        JSONArray jsonArray = new JSONArray(json);*/

                        //  DatabaseHelper.setAccountDetails(homeActivity, Params.LoginDetails, jsonArray.toString());
                        DatabaseHelper.setAccountDetails(homeActivity, Params.CompanyId, loginBOList.get(position).getCompanyid());
                        /*DatabaseHelper.setAccountDetails(homeActivity, Params.CompanyName, loginBOList.get(position).getCompanyName());
                        DatabaseHelper.setAccountDetails(homeActivity, Params.USER_RoleMapping, loginBOList.get(position).getRoleInterfaceMap().toString());

                        String Role = loginBOList.get(position).getRoleInterfaceMap().get(0).getRole().getRoleName();
                        DatabaseHelper.setAccountDetails(homeActivity, Params.USER_Role, Role);
*/
                        gotoMainScreen();

                        Toast.makeText(homeActivity, "Account Switched Successfully", Toast.LENGTH_LONG).show();


                        popupdialog.dismiss();
                        popupdialog.cancel();

                    } catch (Exception ex) {
                        ex.getMessage();
                    }

                }

                @Override
                public void onItemLongClick(View view, final int position) {
                    try {


                    } catch (Exception Ex) {
                        Ex.getMessage();
                    }


                }
            }));

            popupdialog.setCanceledOnTouchOutside(true);
            popupdialog.setCancelable(true);
            popupdialog.show();

        }
    }

    public void gotoMainScreen() {

        startActivity(new Intent(homeActivity, HomeActivity.class));
        finish();
    }
}