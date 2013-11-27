package com.github.yftx.AndroidHacks.sectionTipsAndTricks;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.io.File;

/**
 * User: Liuzl
 * Date: 13-11-27
 */
public class LEDTextView extends TextView {

    private static final String FONTS_FOLDER = "fonts";
    private static final String FONT_DIGITAL_7 = FONTS_FOLDER
            + File.separator + "digital-7.ttf";

    public LEDTextView(Context context) {
        super(context);
    }

    public LEDTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LEDTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        AssetManager assets = context.getAssets();
        final Typeface font = Typeface.createFromAsset(assets, FONT_DIGITAL_7);
        setTypeface(font);
    }
}
