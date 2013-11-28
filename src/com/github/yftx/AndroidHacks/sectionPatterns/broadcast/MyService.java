package com.github.yftx.AndroidHacks.sectionPatterns.broadcast;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import java.util.Date;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class MyService extends IntentService {
    public static final String ACTION = "com.github.yftx.Broadcast.SERVICE_MSG";
    public static final String MSG_KEY = "MSG_KEY";


    public MyService() {
        super("myService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SystemClock.sleep(5000L);
        Intent broadcast = new Intent(ACTION);
        broadcast.putExtra(MSG_KEY, new Date().toGMTString());
        sendBroadcast(broadcast);
    }
}
