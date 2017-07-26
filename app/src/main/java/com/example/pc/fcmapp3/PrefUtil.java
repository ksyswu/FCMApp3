package com.example.pc.fcmapp3;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pc on 2017-07-17.
 */

public class PrefUtil {
    public static final String PREF_FILE_NAME = "prefSWU";
    public static void setpref(Context context, String key, String value){
        SharedPreferences pref =
                context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();//파일저장
    }

    public static String getPref(Context context, String key){
        SharedPreferences pref =  context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return pref.getString(key, ""); //key에 해당값이 저장되어있지 않을 경우에 반환..?출력...?
    }
}
