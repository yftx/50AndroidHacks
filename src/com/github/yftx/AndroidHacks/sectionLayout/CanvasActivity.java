package com.github.yftx.AndroidHacks.sectionLayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * User: Liuzl
 * Date: 13-11-26
 */
public class CanvasActivity extends Activity {
    private static final boolean DBG = Boolean.FALSE;
    private static final String TAG = CanvasActivity.class.getSimpleName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        DrawView drawView = new DrawView(this);
        drawView.width = point.x;
        drawView.height = point.y;
        if (DBG) Log.d(TAG, "width " + getWindowManager().getDefaultDisplay().getWidth() + " point " + point);
        setContentView(drawView);
    }
}

class DrawView extends View {
    public int width;
    public int height;
    private Rectangle rectangle;

    public DrawView(Context context) {
        super(context);
        rectangle = new Rectangle(context, this);
        rectangle.setARGB(255, 255, 0, 0);
        rectangle.setSpeedX(3);
        rectangle.setSpeedX(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        invalidate();
        rectangle.move();
        rectangle.onDraw(canvas);
    }
}

class Rectangle extends View {
    public static final int MAX_SIZE = 40;
    private static final int ALPHA = 255;
    private int mCoordX = 0;
    private int mCoordY = 0;
    private int mRealSize = 40;
    private int mSpeedX = 3;
    private int mSpeedY = 3;

    private boolean goRight = true;
    private boolean goDown = true;
    private DrawView mDrawView;

    private Paint mInnerPaint;
    private RectF mDrawRect;

    public Rectangle(Context context, DrawView drawView) {
        super(context);
        mDrawView = drawView;

        mInnerPaint = new Paint();

        mDrawRect = new RectF();

    /* Red is default */
        mInnerPaint.setARGB(ALPHA, 255, 0, 0);
        mInnerPaint.setAntiAlias(true);
    }

    public void setARGB(int a, int r, int g, int b) {
        mInnerPaint.setARGB(a, r, g, b);
    }

    public void setX(int newValue) {
        mCoordX = newValue;
    }

    public float getX() {
        return mCoordX;
    }

    public void setY(int newValue) {
        mCoordY = newValue;
    }

    public float getY() {
        return mCoordY;
    }

    public void move() {
        moveTo(mSpeedX, mSpeedY);
    }

    private void moveTo(int goX, int goY) {

        // check the borders, and set the direction if a border has reached
        if (mCoordX > (mDrawView.width - MAX_SIZE)) {
            goRight = false;
        }

        if (mCoordX < 0) {
            goRight = true;
        }

        if (mCoordY > (mDrawView.height - MAX_SIZE)) {
            goDown = false;
        }
        if (mCoordY < 0) {
            goDown = true;
        }

        // move the x and y
        if (goRight) {
            mCoordX += goX;
        } else {
            mCoordX -= goX;
        }
        if (goDown) {
            mCoordY += goY;
        } else {
            mCoordY -= goY;
        }

    }

    public int getSpeedX() {
        return mSpeedX;
    }

    public void setSpeedX(int speedX) {
        mSpeedX = speedX;
    }

    public int getmSpeedY() {
        return mSpeedY;
    }

    public void setSpeedY(int speedY) {
        mSpeedY = speedY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mDrawRect.set(mCoordX, mCoordY, mCoordX + mRealSize, mCoordY
                + mRealSize);
        canvas.drawRoundRect(mDrawRect, 0, 0, mInnerPaint);

    }

    public void setSize(int newSize) {
        mRealSize = newSize;
    }

    public int getSize() {
        return mRealSize;
    }
}