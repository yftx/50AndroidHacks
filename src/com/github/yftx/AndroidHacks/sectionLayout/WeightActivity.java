package com.github.yftx.AndroidHacks.sectionLayout;

import android.app.Activity;
import android.os.Bundle;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-25
 *
 * LinearLayout
 * 为horizontal时需要设置子view的layout_width 为0dp layout_height为正常值
 * vertical 需要设置layout_height为0dp layout_width为正常值
 * weight 的计算公式为
 * A possible  example  would  be  a  200dp wide LinearLayout with  its
 * android:weightSum set to 1. The width of the Button would be calculated as follows:
 * Button's width + Button's weight * 200 / sum(weight)
 *
 */
public class WeightActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_activity);
    }
}