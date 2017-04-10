package com.anb.pos;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.anb.pos.support.Utils;


/***************************************************************************************
 * @ClassName:BaseActivity
 * @CreatedDate:2-MAY-16
 * @CreatedBy:(lbamarnani)
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This class is  common class for   all activity.
 ***************************************************************************************/
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected abstract void initializeComponents();

    private long mLastClickTime = 0;
    public final long MAX_CLICK_INTERVAL = 1000;


    private double latitude;
    private double longitude;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private View parentView;


    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }


    public View getParentView() {
        return parentView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeComponents();
    }

    public static void startAndFinish(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onClick(View view) {
        Utils.hideSoftKeyBoard(BaseActivity.this, view);
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_INTERVAL) {

            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        final int id = view.getId();

    }

    @Override
    public void finish() {
        super.finish();
//        if (this instanceof SettingsActivity)
//            overridePendingTransition(R.anim.hold, R.anim.slide_out_down);
//        else
//            overridePendingTransition(R.anim.hold, R.anim.slide_out_right);
    }

    @Override
    public void startActivity(Intent intent) {
        try {
            super.startActivity(intent);
        } catch (ActivityNotFoundException ex) {

        }
//        if (intent.getComponent() == null)
//            return;
//        else if (intent.getComponent().getClassName().equals(SettingsActivity.class.getName()))
//            overridePendingTransition(R.anim.slide_in_down, R.anim.hold);
//        else if (intent.getComponent().getClassName().equals(UserProfileActivity.class.getName())) {
//            if (intent.hasExtra(UserProfileActivity.USER_ID))
//                overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
//            else
//                overridePendingTransition(R.anim.slide_in_left, R.anim.hold);
//        } else
//            overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
//        if (intent.getComponent() == null)
//            return;
//        else if (intent.getComponent().getClassName().equals(SettingsActivity.class.getName()))
//            overridePendingTransition(R.anim.slide_in_down, R.anim.hold);
//        else if (intent.getComponent().getClassName().equals(UserProfileActivity.class.getName())) {
//            if (intent.hasExtra(UserProfileActivity.USER_ID))
//                overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
//            else
//                overridePendingTransition(R.anim.slide_in_left, R.anim.hold);
//        } else
//            overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    /***
     * Add new fragment in given container.
     * <p>
     * This method will add new fragment in container and hide the current fragment.
     * And also will add current fragment in backstack.
     * </p>
     *
     * @param newFragment  This parameter will take new fragment name which need to be add.
     * @param hideFragment This parameter will take fragmnet name which you want to hide.
     */
    public void addFragment(final Fragment newFragment, final Fragment hideFragment) {
        getLocalFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, newFragment, newFragment.getClass().getSimpleName())
                .hide(hideFragment)
                .addToBackStack(hideFragment.getClass().getSimpleName())
                .commit();
    }


    /**
     * removes current fragment from container and replace with the new Fragment recieves in parameter
     *
     * @param newFragment a fragment object that replaces current fragment
     */
    public void replaceFragment(final Fragment newFragment) {
        getLocalFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, newFragment, newFragment.getClass().getSimpleName())
                .commit();
    }

    /**
     * removes all fragment from container and add with the new Fragment recieves in parameter
     *
     * @param newFragment a fragment object that replaces current fragment
     */
    public void replaceFragmentPopBackstack(final Fragment newFragment) {
        getLocalFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
        getLocalFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, newFragment, newFragment.getClass().getSimpleName())
                .commit();
    }

    /**
     * Gets the fragment manager object of activity required for fragment transaction
     * <p>This method can be customised on the need of application,in which it returns {@link FragmentManager} or {@link android.support.v4.app.FragmentManager}</p>
     *
     * @return object of {@link FragmentManager} or {@link android.support.v4.app.FragmentManager}
     */
    public FragmentManager getLocalFragmentManager() {
        return this.getSupportFragmentManager();
    }

}
