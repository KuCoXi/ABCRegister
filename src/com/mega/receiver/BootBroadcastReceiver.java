package com.mega.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.mega.abcregister.SplashActivity;

public class BootBroadcastReceiver extends BroadcastReceiver {  
    //重写onReceive方法  
    @Override  
    public void onReceive(Context context, Intent intent) {  
        Log.v("TAG", "开机自动服务自动启动.....");  
        Intent intent1 = new Intent(context,SplashActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1 );        
    }  
  
}  
