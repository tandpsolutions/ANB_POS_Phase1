package com.anb.pos.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.anb.pos.R;

/**
 * Created by Bhaumik on 27/03/17.
 */

public class CustomEditTextView extends android.support.v7.widget.AppCompatEditText {
    public CustomEditTextView(Context context) {
        super(context);
    }

    public CustomEditTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customTextView);
        String font = a.getString(R.styleable.customTextView_font_name);
        if (font != null) {
            setCustomFont(font, context);
        } else {
            setCustomFont(context.getString(R.string.font_roboto_regular), context);
        }
        a.recycle();
    }

    /**
     * Sets a font on a textView
     *
     * @param font
     * @param context
     */
    private void setCustomFont(String font, Context context) {
        if (font == null) {
            return;
        }
        Typeface tf = FontCache.get(font, context);
        if (tf != null) {
            setTypeface(tf);
        }
    }

}
