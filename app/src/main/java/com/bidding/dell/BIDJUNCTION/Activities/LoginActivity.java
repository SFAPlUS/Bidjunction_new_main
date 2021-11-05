package com.bidding.dell.BIDJUNCTION.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bidding.dell.BIDJUNCTION.Models.LoginBO;
import com.bidding.dell.BIDJUNCTION.Network.RequestQueueService;
import com.bidding.dell.BIDJUNCTION.R;
import com.bidding.dell.BIDJUNCTION.Resource.Params;
import com.bidding.dell.BIDJUNCTION.Utils.DatabaseHelper;
import com.bidding.dell.BIDJUNCTION.Utils.SRMB;
import com.bidding.dell.BIDJUNCTION.Utils.Util;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText loginemail,loginpassword;
    ImageView imghidepassword,imgshowpassword;
    TextView loginforgotpassword, linksignup, txtvw_title;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    RecyclerView studentlist;
    RecyclerView.Adapter mAdapter;
    RecyclerView.Adapter mAdapter1;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<LoginBO> mDataset = new ArrayList<>();
    //ApiManager manager = new ApiManager();

    String userid,userfname;
    @Override public void onResume() { super.onResume(); SRMB.setContext(this); }
    @Override public void onPause() { super.onPause(); SRMB.setContext(this); }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        TextView btnloginsubmit =(TextView) findViewById(R.id.btnloginsubmit);
        loginemail =(EditText) findViewById(R.id.loginemail);
        loginpassword =(EditText) findViewById(R.id.loginpassword);
        imghidepassword =(ImageView) findViewById(R.id.imghidepassword);
        imgshowpassword =(ImageView) findViewById(R.id.imgshowpassword);

        linksignup = (TextView) findViewById(R.id.linksignup);
        loginforgotpassword = (TextView) findViewById(R.id.loginforgotpassword);
        //txtvw_title = (TextView) findViewById(R.id.txtvw_title);

        imgshowpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                imgshowpassword.setVisibility(View.GONE);

                imghidepassword.setVisibility(View.VISIBLE);

            }
        });

        imghidepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                imgshowpassword.setVisibility(View.VISIBLE);

                imghidepassword.setVisibility(View.GONE);
            }
        });

        Util.changeStatusBarColor(LoginActivity.this);



        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/OpenSans-Bold.ttf");
        loginemail.setTypeface(face);
        loginpassword.setTypeface(face);
        btnloginsubmit.setTypeface(face);
        loginforgotpassword.setTypeface(face);
        linksignup.setTypeface(face);
        //txtvw_title.setTypeface(face);
        //It's not working


        btnloginsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginemail.getText().toString().equals("") || loginpassword.getText().toString().equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please Provide Email/Phone and Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    //String url = "login/validate-login/"+loginemail.getText().toString().trim()+"/"+loginpassword.getText().toString().trim();
                    methodLogin();
                    // userLogin(url);
                }

                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);*/


            }
        });

//        SpannableString content = new SpannableString("Sign Up");
//        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        linksignup.setText(content);
//        linksignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
//
//            }
//        });
//
//        SpannableString content1 = new SpannableString("Forgot Password");
//        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
//        loginforgotpassword.setText(content1);
//        loginforgotpassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this,ForgotPasswordactivity.class));
//
//            }
//        });

    }
//    private void methodLogin()
//    {
//        if (Util.isNetworkAvailable(LoginActivity.this)) {
//            JSONObject loginParam= new JSONObject();
//            try {
//
//                loginParam.put("email", loginemail.getText().toString().trim());
//                loginParam.put("pass_wd", loginpassword.getText().toString().trim());
//                loginParam.put("push_token", DatabaseHelper.getAccountDetails(getApplicationContext(), Params.FCMKEY));
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//
//            POSTAPIRequest apiRequest = new POSTAPIRequest();
//            try {
//                apiRequest.request(LoginActivity.this, otpReqListener, loginParam, "/verifyLoginUserPasswd");
//            } catch (JSONException e) {
//                // otpReqListener.onFetchFailure(getResources().getString(R.string.other_error));
//                e.printStackTrace();
//            }
//        }
//
//    }

    public void methodLogin() {

        RequestQueueService.showProgressDialog(LoginActivity.this);
        try {
            String url = getResources().getString(R.string.server_url).concat("login/validate-login/")+loginemail.getText().toString().trim()+"/"+loginpassword.getText().toString().trim();
            Log.d("openLoadRequestAll", "url>>: "+url);
            // RequestQueueService requestQueue = Volley.newRequestQueue(getActivity());

            Log.d("openLoadRequestAll", "url>>: "+url);
            // Initialize a new JsonArrayRequest instance
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try{

                                if(response!=null&&response.length()>0)
                                {
                                    if(response.length()<2)
                                    {

                                        JSONObject data = response.getJSONObject(0);
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_USERKEY, data.optString("usercode"));
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_USERID, data.optString("usercode"));
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_Password, data.optString("Password"));
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_USERName, data.optString("username"));
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.LoginDetails, data.toString());
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.CompanyId, data.optString("companyid"));
                                        // DatabaseHelper.setAccountDetails(LoginActivity.this, Params.CompanyName, data.optString("CompanyName"));
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_RoleMapping, data.optString("RoleInterfaceMap"));

                                        JSONObject jsonObject = (JSONObject) data.get("Company");
                                        String companyName = jsonObject.optString("CompanyName");
                                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.CompanyName, companyName);

                                        // JSONObject jsonObject = (JSONObject) data.optJSONArray("RoleInterfaceMap").opt(0);
                                        //  String Role = jsonObject.optJSONObject("Role").optString("RoleName");
                                        //  DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_Role, Role);

                                        gotoMainScreen();

                                        /* if(Role.equalsIgnoreCase("Bay Supervisor") || Role.equalsIgnoreCase("Administrator")
                                                 || Role.equalsIgnoreCase("Factory")
                                                 || Role.equalsIgnoreCase("Security")
                                                 || Role.equalsIgnoreCase("Order Controller"))
                                         {
                                             gotoMainScreen();
                                         }
                                         else
                                         {
                                             Toast.makeText(LoginActivity.this,"You Are Un-authorize to use this app",Toast.LENGTH_LONG).show();
                                             MainActivity.logOutApp();
                                         }*/
                                    }
                                    else
                                    {
                                        mDataset = new ArrayList<LoginBO>();
                                        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
                                        mDataset.addAll(Arrays.asList(gson.fromJson(response.toString(), LoginBO[].class)));
                                        // showStudentPopup(LoginActivity.this,mDataset);

                                    }
                                }
                                else
                                {


                                }

                                RequestQueueService.cancelProgressDialog();
                            }catch (Exception e){
                                RequestQueueService.cancelProgressDialog();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            // Do something when error occurred
                            RequestQueueService.cancelProgressDialog();
                            Toast.makeText(LoginActivity.this,"You Are Unauthorize to use this app",Toast.LENGTH_LONG).show();
                        }
                    }
            );
            RequestQueueService.getInstance(LoginActivity.this).addToRequestQueue(jsonArrayRequest);
            // Add JsonArrayRequest to the RequestQueue

        } catch (Exception e) {
            // earningListener.onFetchFailure(getResources().getString(R.string.enter_date));
            e.printStackTrace();
        }

    }


    public void gotoMainScreen()
    {

        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    private  boolean checkAndRequestPermissions()
    {

        int CorsePermissioncontacts               = ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int FinePermissioncontacts               = ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded  = new ArrayList<>();

        if (CorsePermissioncontacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (FinePermissioncontacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(LoginActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*public void showStudentPopup (final Context context, final List<LoginBO> loginBOList){
        if (LoginActivity.this != null) {
            final Dialog popupdialog = new Dialog(LoginActivity.this);
            popupdialog.setContentView(R.layout.companylistpopup);
//
            final RecyclerView studentlist = (RecyclerView) popupdialog.findViewById(R.id.allbidsrecyclerview);

            layoutManager = new LinearLayoutManager(context);
            studentlist.setLayoutManager(layoutManager);
            mAdapter = new MyCompanyListAdapter(loginBOList, context);
            studentlist.setVisibility(View.VISIBLE);
            studentlist.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

            studentlist.addOnItemTouchListener(new RecyclerItemClickListener(context, studentlist, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    try {

                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_USERKEY, mDataset.get(position).getUserName());
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_USERID, mDataset.get(position).getUserId());
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_Password, mDataset.get(position).getPassword());
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_USERName, mDataset.get(position).getUserName());

                        Gson gson = new Gson();
                        String json = gson.toJson(mDataset);
                        JSONArray jsonArray = new JSONArray(json);

                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.LoginDetails, jsonArray.toString());
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.CompanyId, mDataset.get(position).getCompanyId());
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.CompanyName, mDataset.get(position).getCompanyName());
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_RoleMapping, mDataset.get(position).getRoleInterfaceMap().toString());

                        String Role = mDataset.get(position).getRoleInterfaceMap().get(0).getRole().getRoleName();
                        DatabaseHelper.setAccountDetails(LoginActivity.this, Params.USER_Role, Role);

                        if(Role.equalsIgnoreCase("Bay Supervisor") || Role.equalsIgnoreCase("Administrator")
                                || Role.equalsIgnoreCase("Factory")
                                || Role.equalsIgnoreCase("Security")
                                || Role.equalsIgnoreCase("Order Controller"))
                        {
                            gotoMainScreen();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"You Are Un-authorize to use this app",Toast.LENGTH_LONG).show();
                            MainActivity.logOutApp();
                        }

                        popupdialog.dismiss();
                        popupdialog.cancel();

                    }
                    catch (Exception ex)
                    {
                        ex.getMessage();
                    }

                }
                @Override
                public void onItemLongClick(View view, final int position) {
                    try {



                    }
                    catch (Exception Ex)
                    {
                        Ex.getMessage();
                    }


                }
            }));

            popupdialog.setCanceledOnTouchOutside(true);
            popupdialog.setCancelable(true);
            popupdialog.show();

        }
    }*/
}