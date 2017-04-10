package com.anb.pos.support;

import android.content.Context;
import android.content.SharedPreferences;

import com.anb.pos.ANBApplication;
import com.anb.pos.R;


/**
 * Created by IdeaLavoro on 27/04/16.
 * <p>
 * Purpose of this class is to save data in preferance and retrive values from preferance througout the lifewcycle of application
 * This class is hold methods for storing and retriving values from preference.
 */
public class Preference {

    private static Preference mPreference;
    public SharedPreferences mSharedPreferences;
    public final static String USER_ID = "user_id";
    public final static String USER_GRP_CD = "user_grp_cd";
    public final static String CURRENT_BRANCH = "selected_branch";

    private Preference() {
        mSharedPreferences = ANBApplication.mInstance.getSharedPreferences(ANBApplication.mInstance.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    /**
     * @return the {@link SharedPreferences} object that will be used to save values in the application preference
     */
    public static Preference getInstance() {
        if (mPreference == null) {
            mPreference = new Preference();
        }
        return mPreference;
    }

    /**
     * Stores the {@link String} value in the preference
     *
     * @param key   {@link String} parameter for the key for the values in preference
     * @param value {@link String} parameter for the value to be stored in preference
     */
    public void savePreferenceData(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Stores the {@link Boolean} value in the preference
     *
     * @param key   {@link String} parameter for the key for the values in preference
     * @param value {@link Boolean} parameter for the value to be stored in preference
     */
    public void savePreferenceData(String key, Boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Stores the {@link Integer} value in the preference
     *
     * @param key   {@link String} parameter for the key for the values in preference
     * @param value {@link Integer} parameter for the value to be stored in preference
     */
    public void savePreferenceData(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}
