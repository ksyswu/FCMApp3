package com.example.pc.fcmapp3;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String PUSH_TOKEN = "pushToken";

    @Override
    public void onTokenRefresh() {
        // 기기의 갱신된 토큰 값을 처리하는 곳
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null){
            PrefUtil.setpref(this, PUSH_TOKEN, token);
            Log.d("TEST", "Refreshed token: " + token);
        }
    }

}
