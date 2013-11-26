package com.github.yftx.AndroidHacks.sectionLayout.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.github.yftx.AndroidHacks.R;

/**
 * User: Liuzl
 * Date: 13-11-26
 */
public class Cascade extends ViewGroup {
    private static final String TAG = Cascade.class.getSimpleName();
    private static final boolean DBG = Boolean.FALSE;
    private int CardDefaultSize = 200;

    public Cascade(Context context) {
        super(context);
    }

    public Cascade(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Cascade(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (DBG) Log.d(TAG, "Cascadde onFinishInflate");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (DBG) Log.d(TAG, "widthMode " + widthMode + " widthSize " + widthSize
                + " heightMode " + heightMode + " heightSize " + heightSize);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                measureChild(child,
                        MeasureSpec.makeMeasureSpec(CardDefaultSize, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(CardDefaultSize, MeasureSpec.EXACTLY));
            }
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (DBG) Log.d(TAG, "changed " + changed + " l " + l + " t " + t + " r " + r + " b " + b);
        int tempLeft = l;
        int tempTop = t;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();
                if (DBG) Log.d(TAG, "child width " + child.getMeasuredWidth()
                        + " height " + child.getMeasuredHeight()
                        + " marginSpace " + lp.marginSpace);
                tempLeft += lp.marginSpace;
                tempTop += lp.marginSpace;
                child.layout(tempLeft, tempTop, tempLeft + width, tempTop + height);
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        float marginSpace;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.Cascade);
            marginSpace = a.getDimension(R.styleable.Cascade_margin_space, 0);
            if(DBG) Log.d(TAG,"view name " + a.getString(R.styleable.Cascade_custom_name));
            a.recycle();
            if (DBG) Log.d(TAG, "marginSpace " + marginSpace);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
