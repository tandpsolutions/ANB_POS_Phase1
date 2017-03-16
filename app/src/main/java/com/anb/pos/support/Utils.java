package com.anb.pos.support;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by indianic on 16/03/17.
 */

public class Utils {
    private static final Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    /**
     * Hide the soft keyboard from screen for edit text only
     *
     * @param mContext
     * @param view
     */
    public static void hideSoftKeyBoard(Context mContext, View view) {

        try {

            final InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    /**
     * Hides keyboard from screen if it is showing
     *
     * @param mActivity requires for checking keyboard is open or not
     */
    public static void hideSoftKeyBoard(Activity mActivity) {
        if (mActivity != null && !mActivity.isFinishing()) {
            final InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager.isActive()) {
                if (mActivity.getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }
    }
}
