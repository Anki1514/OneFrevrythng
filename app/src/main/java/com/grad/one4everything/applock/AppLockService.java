package com.grad.one4everything.applock;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.grad.one4everything.utilities.EverythingDataBase;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Anki on 10/18/2015.
 */
public class AppLockService extends Service {
    EverythingDataBase everythingDataBase;
    Handler handler;


    public String getRunningActivity() {
        String topPackageName = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService("usagestats");
            long time = System.currentTimeMillis();
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(
                    UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10 * 60,
                    time);
            if (stats != null) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    topPackageName = mySortedMap.get(mySortedMap.lastKey())
                            .getPackageName();
                } else {
                    topPackageName = "no any app";
                }
            } else {
                topPackageName = "stats null";
            }
        } else {
            try {
                ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager
                        .getRunningTasks(1);
                ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
                topPackageName = ar.topActivity.getPackageName();
            } catch (Exception e) {
                e.printStackTrace();
                topPackageName = e.getMessage();
            }
        }
        return topPackageName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        everythingDataBase = new EverythingDataBase(getApplicationContext());
        System.out.print("INternalactivity" + getRunningActivity().length() + "hbhbh" + getRunningActivity());
        grantedRequest();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void grantedRequest() {
        AppOpsManager appOps = (AppOpsManager) getApplicationContext()
                .getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), getApplicationContext().getPackageName());
        boolean granted = mode == AppOpsManager.MODE_ALLOWED;
        System.out.println("granted" + granted);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler();
        handler.postDelayed(runTask,100);
        return START_STICKY;
    }

    Runnable runTask = new Runnable() {
        @Override
        public void run() {
        handler.postDelayed(runTask,100);
        }
        };
}
