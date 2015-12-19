package com.grad.one4everything.applock;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grad.one4everything.utilities.EverythingDataBase;
import com.grad.one4everything.pojo.PojoClass;
import com.grad.one4everything.R;

import java.util.ArrayList;

/**
 * Created by Anki on 10/14/2015.
 */
public class AppLockAdapter extends BaseAdapter {
    Context context;
    ArrayList<PojoClass> installList;
    EverythingDataBase everythingDataBase;

    public AppLockAdapter(Context context, ArrayList<PojoClass> installList) {
        this.context = context;
        this.installList = installList;
        everythingDataBase = new EverythingDataBase(context);
    }

    @Override
    public int getCount() {
        return installList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }
        ImageView im_app = (ImageView) convertView.findViewById(R.id.appImage);
        TextView appName = (TextView) convertView.findViewById(R.id.appName);
        ImageView imgLockStatus = (ImageView) convertView.findViewById(R.id.imgLock);
        PojoClass pojoItem = installList.get(position);
        appName.setText(pojoItem.getName());
        if (everythingDataBase.getAllApps().contains(pojoItem.getPackageName())) {
            imgLockStatus.setImageResource(R.mipmap.lock);
            installList.get(position).setIsSelect(true);
        } else {
            imgLockStatus.setImageResource(R.mipmap.unlock);
            installList.get(position).setIsSelect(false);
        }
        im_app.setImageDrawable(pojoItem.getIcon());
        return convertView;
    }
}
