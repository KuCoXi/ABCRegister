package com.mega.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.mega.abcregister.SplashActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {  
    //��дonReceive����  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        Log.v("TAG", "�����Զ������Զ�����.....");  
        Intent intent1 = new Intent(context,SplashActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1 );        
    }  
  
}  
