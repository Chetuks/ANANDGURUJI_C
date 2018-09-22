package com.ananada.addme.neutrinos.anandaguruji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    SharedPrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        prefManager = new SharedPrefManager(getApplicationContext());
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    if (prefManager.getISLogged_IN())
                    {
                        Intent intent = new Intent(SplashScreen.this, DashBoardActivity.class);
                        intent.putExtra("callAPI", 1);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(SplashScreen.this, RegistrationActivity.class);
                        intent.putExtra("callAPI", 1);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        timerThread.start();
    }



}
