package com.grad.one4everything.applock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.grad.one4everything.R;
import com.grad.one4everything.activitypack.SecurityActivity;
import com.grad.one4everything.patternview.PatternView;
import com.grad.one4everything.utilities.SharedPrefs;

/**
 * Created by Anki on 10/7/2015.
 */
public class AppLockChooserActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fab;
    LinearLayout numLock, patternLock;
    PatternView patternView;
    TextView whichLock;
    int count = -1;
    boolean isConfrmPass;
    boolean isSetting = false;
    int[] textIds = {R.id.digit1, R.id.digit2, R.id.digit3, R.id.digit4};
    private String[] pwd = new String[textIds.length];
    TextView[] textDigits = new TextView[4];
    Button[] btnNumbs = new Button[10];
    private String patternString;
    boolean doubleBackToExitPressedOnce = false;
    int[] butnIds = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};
    String password = "";
    SharedPrefs sharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_choose_activity);
        initView();
        listenClick();
        patternCode();
        numberCode();
    }

    private void numberCode() {

    }

    private void patternCode() {
        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {
            @Override
            public void onPatternDetected() {
                fab.setVisibility(View.GONE);
                if (patternString == null) {
                    patternString = patternView.getPatternString();
                    System.out.println("pattern" + patternString);
                    whichLock.setText("Draw Again to confirm");
                    patternView.clearPattern();
                    return;
                }
                if (patternString.equals(patternView.getPatternString())) {
                    System.out.println("pattern" + patternView.getPatternString());
                    sharedPrefs.setPatternValue(patternView.getPatternString());
                    patternView.clearPattern();
                    Toast.makeText(getApplicationContext(), "PATTERN SET SUNCCESSFULLY", Toast.LENGTH_SHORT).show();
                    Intent mainAct = new Intent(getApplicationContext(), SecurityActivity.class);
                    startActivity(mainAct);
                    sharedPrefs.setIspattern(true);
                    finish();
                    return;
                } else {
                    patternString = null;
                    Toast.makeText(getApplicationContext(), "wrong previous password", Toast.LENGTH_SHORT).show();
                    whichLock.setText("Please set password for the first time");
                    System.out.println("Pattern String" + patternString);
                }
                Toast.makeText(getApplicationContext(), "PATTERN NOT CORRECT", Toast.LENGTH_SHORT).show();
                patternView.clearPattern();
            }
        });

    }

    private void listenClick() {
        fab.setOnClickListener(this);
    }


    public void patternDetector() {
        patternView.setOnPatternDetectedListener(new PatternView.OnPatternDetectedListener() {
            @Override
            public void onPatternDetected() {
                if (patternString == null) {
                    patternString = patternView.getPatternString();
                    System.out.println("pattern" + patternString);
                    whichLock.setText("Draw Again to confirm");
                    patternView.clearPattern();
                    return;
                }
                if (patternString.equals(patternView.getPatternString())) {
                    System.out.println("pattern" + patternView.getPatternString());
                    sharedPrefs.setPatternValue(patternView.getPatternString());
                    patternView.clearPattern();
                    Toast.makeText(getApplicationContext(), "PATTERN SET SUNCCESSFULLY", Toast.LENGTH_SHORT).show();
                    Intent mainAct = new Intent(getApplicationContext(), SecurityActivity.class);
                    startActivity(mainAct);
                    sharedPrefs.setIspattern(true);
                    finish();
                    return;
                } else {
                    patternString = null;
                    Toast.makeText(getApplicationContext(), "wrong previous password", Toast.LENGTH_SHORT).show();
                    whichLock.setText("Please set password for the first time");
                    System.out.println("Pattern String" + patternString);
                }
                Toast.makeText(getApplicationContext(), "PATTERN NOT CORRECT", Toast.LENGTH_SHORT).show();
                patternView.clearPattern();

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                lockVisibilty();
                break;
        }
    }

    private void initView() {
        sharedPrefs = new SharedPrefs(getApplicationContext());
        fab = (FloatingActionButton) findViewById(R.id.fab);
        numLock = (LinearLayout) findViewById(R.id.numLock);
        whichLock = (TextView) findViewById(R.id.whichLock);
        patternLock = (LinearLayout) findViewById(R.id.patternLock);
        patternView = (PatternView) findViewById(R.id.patternView);
        if (sharedPrefs.isLock()) {
            patternLock.setVisibility(View.VISIBLE);
        } else {
            numLock.setVisibility(View.VISIBLE);
        }
    }

    private void lockVisibilty() {
        if (sharedPrefs.isLock() == true) {
            numLock.setVisibility(View.VISIBLE);
            patternLock.setVisibility(View.GONE);
            fab.setImageResource(R.mipmap.ic_pattern);
            whichLock.setText("Enter Unlock Pincode");
            patternString = null;
            sharedPrefs.setLock(false);
        } else {
            patternLock.setVisibility(View.VISIBLE);
            numLock.setVisibility(View.GONE);
            whichLock.setText("Draw Unlock Pattern");
            fab.setImageResource(R.mipmap.ic_num);
            sharedPrefs.setLock(true);
        }
    }

    private void numPasscode(View v) {
        for (int i = 0; i < butnIds.length; i++) {
            if (v == btnNumbs[i]) {
                if (i < 10 && count < 3) {
                    count++;
                    textDigits[count].setBackgroundResource(R.mipmap.aftr_passcode1);
                    pwd[count] = (i + "");
                    if (count == 3) {
                        String confrmPassword = "";
                        for (int j = 0; j < textDigits.length; j++) {
                            if (isConfrmPass) {
                                confrmPassword += pwd[j];
                            } else {
                                password += pwd[j];
                            }
                            textDigits[j].setBackgroundResource(R.mipmap.pre_passcode1);
                            count--;
                        }
                        if (isConfrmPass) {
                            if (confrmPassword.equals(password)) {
                                sharedPrefs.setIsnumber(true);
                                Log.v("Password match : ", "true");
                                startActivity(new Intent(getApplicationContext(), SecurityActivity.class));
                                finish();
                            } else {
                                Log.v("Password match : ", "false");
                                Toast.makeText(AppLockChooserActivity.this,
                                        "Password not matched,Please enter again",
                                        Toast.LENGTH_LONG).show();
                                password = "";
                                confrmPassword = "";
                            }
                            whichLock.setText("Please set password for first time");
                            isConfrmPass = false;
                        } else {
                            whichLock.setText("Confirm Password");
                            isConfrmPass = true;
                        }
                        Log.v("Password : ", password);
                        Log.v("Confirm Password : ", confrmPassword);
                    }
                } else if (i == 10) {
                    if (count > -1) {
                        textDigits[count]
                                .setBackgroundResource(R.mipmap.pre_passcode1);
                        pwd[count] = "";
                        count--;
                    }
                } else if (i == 11) {
                    while (count > -1) {
                        textDigits[count]
                                .setBackgroundResource(R.mipmap.pre_passcode1);
                        pwd[count] = "";
                        count--;
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
