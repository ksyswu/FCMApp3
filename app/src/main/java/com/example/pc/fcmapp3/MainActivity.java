package com.example.pc.fcmapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.example.pc.fcmapp3.FirebaseInstanceIDService.PUSH_TOKEN;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String savedToken = PrefUtil.getPref(this, FirebaseInstanceIDService.PUSH_TOKEN);
        if (savedToken == null || savedToken.length() ==0 ) {
            savedToken = FirebaseInstanceId.getInstance().getToken();
            if (savedToken != null){
                PrefUtil.setpref(this, FirebaseInstanceIDService.PUSH_TOKEN, savedToken);
                Log.e("TEST", savedToken);

            }
        }

//        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.e("TEST", token != null ? token: "nothing");
    }
}
