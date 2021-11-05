package com.bidding.dell.BIDJUNCTION.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bidding.dell.BIDJUNCTION.R;
import com.bidding.dell.BIDJUNCTION.Resource.Params;
import com.bidding.dell.BIDJUNCTION.Utils.DatabaseHelper;
import com.bidding.dell.BIDJUNCTION.Utils.SRMB;

public class SplashActivity extends AppCompatActivity {
    String uid="";
    @Override
    public void onResume() { super.onResume(); SRMB.setContext(this); }
    @Override
    public void onPause() { super.onPause(); SRMB.setContext(this); }
    private static long SLEEP_TIME = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SRMB.setContext(this);
        new IntentLauncher().start();
    }
    private class IntentLauncher extends Thread {
        public void run() {
            try { sleep(SLEEP_TIME * 1000); } catch (Exception e) {;}

            try {
                uid = DatabaseHelper.getAccountDetails(SplashActivity.this, Params.USER_USERKEY);

                //jsonObject1.getString("user_id").toString();
                if(uid.equals("")|| uid == null || uid.equalsIgnoreCase("NOTFOUND"))
                {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }

            }
            catch (Exception ex)
            {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
                ex.getMessage();
            }

        }
    }
}