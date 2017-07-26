package com.example.pc.fcmapp3;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.Map;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //push메시지가 날라오면 처리하는 콜백 메서드
        //푸쉬하면 노티피케이션을 많이 사용함
         Map<String , String>data =  remoteMessage.getData();

        if (data != null && data.size() >0){
            try{
                Gson gson = new Gson();
                //Map -->JSON
                String dataJson = gson.toJson(data);

                //JSOn--->PushMsgBean.Data로 변환
                PushMsgBean.Data pushMsgData = gson.fromJson(dataJson, PushMsgBean.Data.class);

                //Noti호출
                showNoti(pushMsgData);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //메시지가 왔을때의

//        //push메시지가 날라오면 처리하는 콜백 메서드
//        //푸쉬하면 노티피케이션을 많이 사용함
//        String msg = remoteMessage.getData().get("messgae");
//        Intent intent = new Intent(this, SecondActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
    }//end OnMessageReceived

    private void showNoti(PushMsgBean.Data data) throws  Exception {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri dafSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setTicker(data.getTitle())
                .setContentTitle(  new String(data.getTitle().getBytes() , "UTF-8")   )
                .setContentText( new String(data.getMessage().getBytes() , "UTF-8")  )
                .setAutoCancel(true)
                .setSound(dafSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify((int)System.currentTimeMillis(), builder.build()); // 겹치지 않도록 하기위해서 입력, 즉 덮어씌워지지 않도록록
    }

    }
