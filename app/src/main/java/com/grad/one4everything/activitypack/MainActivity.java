package com.grad.one4everything.activitypack;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.grad.one4everything.R;
import com.grad.one4everything.applock.AppLockActivity;
import com.grad.one4everything.imagehider.HiderMainActivity;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout revealItems;
    boolean hidden = true;
    CoordinatorLayout tabContent;
    LinearLayout cardLayout;
    LinearLayout appLock, imageHide, appBackup, mobileClean, message, contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        revealItems = (LinearLayout) findViewById(R.id.revealItems);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tabToolbar);
        setSupportActionBar(toolbar);
        tabContent = (CoordinatorLayout) findViewById(R.id.tabContent);
        appBackup = (LinearLayout) findViewById(R.id.appbackup);
        appLock = (LinearLayout) findViewById(R.id.applock);
        message = (LinearLayout) findViewById(R.id.message);
        cardLayout = (LinearLayout) findViewById(R.id.cardLayout);
        contact = (LinearLayout) findViewById(R.id.contact);
        imageHide = (LinearLayout) findViewById(R.id.imagehide);
        mobileClean = (LinearLayout) findViewById(R.id.mobileclean);
        appLock.setOnClickListener(this);
        message.setOnClickListener(this);
        contact.setOnClickListener(this);
        cardLayout.setOnClickListener(this);
        imageHide.setOnClickListener(this);
        mobileClean.setOnClickListener(this);
        appBackup.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_switch:
                revealAnimation();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void revealAnimation() {
        int cx = (revealItems.getLeft() + revealItems.getRight());
        int cy = revealItems.getTop();
        int radius = Math.max(revealItems.getWidth(), revealItems.getHeight());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(revealItems, cx, cy, 0, radius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(500);
            SupportAnimator animator_reverse = animator.reverse();
            if (hidden) {
                revealItems.setVisibility(View.VISIBLE);
                animator.start();
                hidden = false;
            } else {
                animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        revealItems.setVisibility(View.INVISIBLE);
                        hidden = true;
                    }

                    @Override
                    public void onAnimationCancel() {
                    }

                    @Override
                    public void onAnimationRepeat() {
                    }
                });
                animator_reverse.start();

            }
        } else {
            if (hidden) {
                Animator anim = android.view.ViewAnimationUtils.createCircularReveal(revealItems, cx, cy, 0, radius);
                revealItems.setVisibility(View.VISIBLE);
                anim.start();
                hidden = false;
            } else {
                Animator anim = android.view.ViewAnimationUtils.createCircularReveal(revealItems, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        revealItems.setVisibility(View.INVISIBLE);
                        hidden = true;
                    }
                });
                anim.start();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.applock:
                Intent appIntent = new Intent(getApplicationContext(), AppLockActivity.class);
                startActivity(appIntent);
                break;
            case R.id.appbackup:
                Intent appBackUp = new Intent(getApplicationContext(),AppBackUp.class);
                startActivity(appBackUp);
                break;
            case R.id.message:
                Intent messageBackup = new Intent(getApplicationContext(),MessageActivity.class);
                startActivity(messageBackup);
                break;
            case R.id.contact:
                Intent contactBackup = new Intent(getApplicationContext(),ContactBackup.class);
                startActivity(contactBackup);
                break;
            case R.id.mobileclean:
                Intent mobileClean = new Intent(getApplicationContext(),MobileClean.class);
                startActivity(mobileClean);
                break;
            case R.id.imagehide:
                Intent hider = new Intent(getApplicationContext(), HiderMainActivity.class);
                startActivity(hider);
                break;
            case R.id.cardLayout:
                revealItems.setVisibility(View.GONE);
                break;

        }
    }
}
