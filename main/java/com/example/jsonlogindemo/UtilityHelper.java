package com.example.jsonlogindemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dell on 16-04-2018.
 */

class UtilityHelper {


    private static final String FILE_NAME = "SHAREPREF";
    public static final String USER_NAME = "username";

    public void writeUser(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences
                (FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, username);
        editor.commit();
    }
}
