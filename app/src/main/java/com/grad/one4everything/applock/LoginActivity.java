package com.grad.one4everything.applock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.grad.one4everything.R;
import com.grad.one4everything.utilities.SharedPrefs;

/**
 * Created by Anki on 12/5/2015.
 */

public class LoginActivity extends AppCompatActivity {
    SharedPrefs sharedPrefs;
    LinearLayout patternLock, numLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acivity);
        patternLock = (LinearLayout) findViewById(R.id.patternLock);
        numLock = (LinearLayout) findViewById(R.id.numLock);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        if (sharedPrefs.isnumber()) {
           numLock.setVisibility(View.VISIBLE);
        }
        else{
            patternLock.setVisibility(View.VISIBLE);
        }

    }
}
