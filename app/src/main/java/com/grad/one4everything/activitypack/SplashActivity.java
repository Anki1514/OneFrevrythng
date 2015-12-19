package com.grad.one4everything.activitypack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.grad.one4everything.R;
import com.grad.one4everything.applock.AppLockChooserActivity;

/**
 * Created by Anki on 12/3/2015.
 */
public class SplashActivity extends AppCompatActivity {
    protected boolean active = true;
    protected int splashTime = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (active && (waited < splashTime)) {
                        sleep(100);
                        if (active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {
                } finally {
                    startActivity(new Intent(SplashActivity.this,
                            AppLockChooserActivity.class));
                    finish();
                }
            }
        };
        splashTread.start();
    }
}