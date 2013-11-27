package com.github.yftx.AndroidHacks.sectionAnimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.github.yftx.AndroidHacks.R;

import java.util.Random;

/**
 * User: Liuzl
 * Date: 13-11-27
 */
public class KenBurnsEffect extends Activity implements Animator.AnimatorListener {
    private static final int ANIM_COUNT = 4;
    private static final boolean DBG = Boolean.FALSE;
    private static final String TAG = KenBurnsEffect.class.getSimpleName();
    private ViewGroup mContainer;
    private ImageView mShowView;
    private int mCurShowPhotoIndex;
    private int[] Photos = {
            R.drawable.pic_0,
            R.drawable.pic_2,
            R.drawable.pic_3,
            R.drawable.pic_4,
            R.drawable.pic_5};
    private Random mRandom = new Random();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContainer = new FrameLayout(this);
        mContainer.setLayoutParams(
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        mShowView = createView();
        mContainer.addView(mShowView);
        setContentView(mContainer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playImageView();
    }

    private ImageView createView() {
        ImageView img = new ImageView(this);
        img.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (DBG) Log.d(TAG, "cur photo index " + mCurShowPhotoIndex);
        img.setImageResource(Photos[mCurShowPhotoIndex]);
        mCurShowPhotoIndex = (mCurShowPhotoIndex + 1 < Photos.length) ? mCurShowPhotoIndex + 1 : 0;
        return img;
    }

    private void playImageView() {
        AnimatorSet animationSet = new AnimatorSet();
        final int index = mRandom.nextInt(ANIM_COUNT);
        if (DBG) Log.d(TAG, "cur animation is " + index);
        switch (index) {
            case 0:
                animationSet.play(ObjectAnimator.ofFloat(mShowView, "scaleX", 0.5f, 1f))
                        .with(ObjectAnimator.ofFloat(mShowView, "scaleY", 0.5f, 1f));
                break;
            case 1:
                animationSet.play(ObjectAnimator.ofFloat(mShowView, "scaleX", 1.5f, 0.5f))
                        .with(ObjectAnimator.ofFloat(mShowView, "scaleY", 1.5f, 0.5f));
                break;
            case 2:
                mShowView.setScaleX(1.5f);
                mShowView.setScaleY(1.5f);
                animationSet.play(ObjectAnimator.ofFloat(mShowView, "translationY", 300f, 0f))
                        .after(ObjectAnimator.ofFloat(mShowView, "translationX", 0f, 500f));
                break;
            default:
                animationSet.play(ObjectAnimator.ofFloat(mShowView, "alpha", 0f, 1.0f));
                break;
        }
        animationSet.setDuration(4000);
        animationSet.addListener(this);
        animationSet.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mContainer.removeView(mShowView);
        mShowView = createView();
        mContainer.addView(mShowView);
        playImageView();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}