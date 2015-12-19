package com.grad.one4everything.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anki on 10/10/2015.
 */
public class SharedPrefs {
    private final String DBNAME = "One4everything";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;
    private Context context;
    private static final String SECURITYQUESTN = "securityquestion";
    private static final String SECURITYANSWR = "securityanswr";
    private final String LOCK = "lock";
    private final String PATTERNVALUE = "patternvalue";
    private static final String NUMBER_LOCK = "numberlock";
    private final String ISPATTERN = "ispattern";
    private static final String ISNUMBER = "isnumber";
    String numLock;
    String securityanswr;
    String securityquestion;
    boolean lock;
    String patternValue;
    boolean isnumber;
    boolean ispattern;

    public SharedPrefs(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(DBNAME,
                Context.MODE_PRIVATE);
    }


    public boolean ispattern() {
        return sharedPreferences.getBoolean(ISPATTERN, false);
    }

    public void setIspattern(boolean ispattern) {
        spEditor = sharedPreferences.edit();
        spEditor.putBoolean(ISPATTERN, ispattern);
        spEditor.commit();
    }

    public boolean isnumber() {
        return isnumber;
    }

    public void setIsnumber(boolean isnumber) {
        this.isnumber = isnumber;
    }

    public String getNumLock() {
        return sharedPreferences.getString(NUMBER_LOCK, "");
    }

    public void setNumLock(String numLock) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(NUMBER_LOCK, numLock);
        spEditor.commit();
    }

    public String getSecurityanswr() {
        return sharedPreferences.getString(SECURITYANSWR, "");
    }

    public void setSecurityanswr(String securityanswr) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(SECURITYANSWR, securityanswr);
        spEditor.commit();
    }


    public String getSecurityquestion() {
        return sharedPreferences.getString(SECURITYQUESTN, "");
    }

    public void setSecurityquestion(String securityquestion) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(SECURITYQUESTN, securityquestion);
        spEditor.commit();
    }


    public String getPatternValue() {
        return sharedPreferences.getString(PATTERNVALUE, "");
    }

    public void setPatternValue(String patternValue) {
        spEditor = sharedPreferences.edit();
        spEditor.putString(PATTERNVALUE, patternValue);
        spEditor.commit();
    }

    public boolean isLock() {
        return sharedPreferences.getBoolean(LOCK, true);
    }

    public void setLock(boolean lock) {
        spEditor = sharedPreferences.edit();
        spEditor.putBoolean(LOCK, lock);
        spEditor.commit();
    }

}
