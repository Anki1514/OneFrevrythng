package com.grad.one4everything.pojo;

import android.graphics.drawable.Drawable;

/**
 * Created by Anki on 10/14/2015.
 */
public class PojoClass {
    private String name = "";
    private String packageName = "";
    private Drawable icon;
    public boolean isSelect = false;
    private boolean isLock;
    private String ver_Name = "";
    private int verCode = 0;
    private String publicSrc = "";


    public int getVerCode() {
        return verCode;
    }

    public void setVerCode(int verCode) {
        this.verCode = verCode;
    }

    public String getVer_Name() {
        return ver_Name;
    }

    public void setVer_Name(String ver_Name) {
        this.ver_Name = ver_Name;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setIsLock(boolean isLock) {
        this.isLock = isLock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }


    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }


    public String getPublicSrc() {
        return publicSrc;
    }

    public void setPublicSrc(String publicSrc) {
        this.publicSrc = publicSrc;
    }


}
