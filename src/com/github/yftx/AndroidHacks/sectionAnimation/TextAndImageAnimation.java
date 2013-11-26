package com.github.yftx.AndroidHacks.sectionAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.github.yftx.AndroidHacks.R;

import java.util.logging.Handler;

/**
 * User: Liuzl
 * Date: 13-11-26
 */
public class TextAndImageAnimation extends Activity {
    private TextSwitcher mTextSwitcher;
    private int mPostTimes;
    private Runnable mTextChangeRunnable;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_image_animation);
        initTextSwitcher();
    }

    private void initTextSwitcher() {
        mTextSwitcher = (TextSwitcher) findViewById(R.id.tv_switcher);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(TextAndImageAnimation.this);
                tv.setGravity(Gravity.CENTER);
                return tv;
            }
        });
        mTextSwitcher.setInAnimation(in);
        mTextSwitcher.setOutAnimation(out);
        mTextChangeRunnable = new ChangeTextRunnable();
    }



    public void onClickStartChange(View v){
        mTextSwitcher.post(mTextChangeRunnable);
        Toast.makeText(this,"start change text view",Toast.LENGTH_SHORT).show();
    }

     class ChangeTextRunnable implements Runnable {
        @Override
        public void run() {
            mTextSwitcher.setText("postimes " + mPostTimes);
            mPostTimes++;
            mTextSwitcher.postDelayed(mTextChangeRunnable,500);

        }
    }

}