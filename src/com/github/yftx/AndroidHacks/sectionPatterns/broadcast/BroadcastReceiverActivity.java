package com.github.yftx.AndroidHacks.sectionPatterns.broadcast;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class BroadcastReceiverActivity extends Activity {
    TextView result ;
    BroadcastReceiver mReceiver;
    IntentFilter intentFilter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_layout);
        result = (TextView) findViewById(R.id.result);
        mReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter(MyService.ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    public void onClickStartService(View view){
        startService(new Intent(this,MyService.class));

        Toast toast = Toast.makeText(this,"strat Service ",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }


    class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            refreshView(intent.getExtras().getString(MyService.MSG_KEY));
        }
    }

    private void refreshView(String string) {
        result.setText(string);
        findViewById(R.id.pb).setVisibility(View.GONE);
    }


}