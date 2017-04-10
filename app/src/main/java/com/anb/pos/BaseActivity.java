package com.anb.pos;


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

    private long mLastClickTime = 0;
    public final long MAX_CLICK_INTERVAL = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View view) {
        Utils.hideSoftKeyBoard(BaseActivity.this, view);
        /**
         * Logic to Prevent the Launch of the Fragment Twice if User makes
         * the Tap(Click) very Fast.
         */
        mLastClickTime = SystemClock.elapsedRealtime();
        if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_INTERVAL) {

            return;
        }

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
                .add(R.id.container_main, newFragment, newFragment.getClass().getSimpleName())
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
                .replace(R.id.container_main, newFragment, newFragment.getClass().getSimpleName())
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
                .replace(R.id.container_main, newFragment, newFragment.getClass().getSimpleName())
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
