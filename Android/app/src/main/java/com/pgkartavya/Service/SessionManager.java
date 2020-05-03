package com.pgkartavya.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Kartavya";

    private static final String KEY_ID = "uid";
    private static final String KEY_NAME = "uname";
    private static final String KEY_PHONE = "uphone";
    private static final String KEY_EMAIL = "uemail";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setLogin(boolean isLoggedIn, String id, String name, String phone, String email) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUID(){
        return (String) pref.getString(KEY_ID, "-1");
    }
    public String getName(){
        return (String) pref.getString(KEY_NAME, "Default");
    }
    public String getPhone(){
        return (String) pref.getString(KEY_PHONE, "0");
    }
    public String getEmail(){
        return (String) pref.getString(KEY_EMAIL, "0");
    }
}
