package com.github.yftx.AndroidHacks.sectionTipsAndTricks;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-28
 */
public class ToastPostion extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast_postion);
    }

    public void onClickStartShowToast(View view) {
        Toast toast = Toast.makeText(this, "Toast", Toast.LENGTH_SHORT);
        int gravity;
        switch (view.getId()){
            case R.id.left_top:
                gravity = Gravity.TOP | Gravity.LEFT;
                break;
            case R.id.left_bottom:
                gravity = Gravity.BOTTOM | Gravity.LEFT;
                break;
            case R.id.right_top:
                gravity = Gravity.TOP | Gravity.RIGHT;
                break;
            case R.id.right_bottom:
                gravity = Gravity.BOTTOM | Gravity.RIGHT;
                break;
            default:
                gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
                break;

        }
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

}