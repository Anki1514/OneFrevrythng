package com.grad.one4everything.applock;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.grad.one4everything.R;
import com.grad.one4everything.pojo.PojoClass;
import com.grad.one4everything.utilities.EverythingDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anki on 10/12/2015.
 */
public class AppLockActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<PojoClass> installList;
    EverythingDataBase everythingDataBase;
    AppLockAdapter appLockAdapter;
    private String nameApp = "";
    private String namePack = "";
    private Drawable appIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applock_activity);
        everythingDataBase = new EverythingDataBase(getApplicationContext());
        installList = new ArrayList<PojoClass>();
        getListInstall();
        appLockAdapter = new AppLockAdapter(getApplicationContext(), installList);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(appLockAdapter);
        listView.setOnItemClickListener(this);
    }


    private void getListInstall() {
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (PackageInfo pkinfo : packs) {
            nameApp = pkinfo.applicationInfo.loadLabel(getPackageManager()).toString();
            namePack = pkinfo.packageName;
            appIcon = pkinfo.applicationInfo.loadIcon(getPackageManager());
            PojoClass pojo = new PojoClass();
            pojo.setName(nameApp);
            pojo.setPackageName(namePack);
            pojo.setIcon(appIcon);
            installList.add(pojo);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectionChanged(position);
    }

    private void selectionChanged(int position) {
        if (installList.get(position).isSelect()) {
            everythingDataBase.deleteApp(installList.get(position).getPackageName());
            installList.get(position).setIsSelect(false);
        } else {
            installList.get(position).setIsSelect(true);
            String str = installList.get(position).getPackageName();
            if (!everythingDataBase.getAllApps().contains(str)) {
                everythingDataBase.insertApp(str);
            }
        }
        appLockAdapter.notifyDataSetChanged();
    }
}
