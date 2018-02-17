package ccf.android.com.util;

import android.app.Activity;

import ccf.android.com.R;
import ccf.android.com.fragments.ImageFragment;

/**
 * Created by admin on 17/02/18.
 */

public class ActivitySwitchHelper {


    public static void openImageFragment(String url){
        Activity activity = (Activity) Util.context;
        android.app.FragmentManager fm1 = activity.getFragmentManager();
        android.app.FragmentTransaction transaction = fm1.beginTransaction();

        ImageFragment currentFragment = ImageFragment.newInstance(url);
        transaction.add(android.R.id.content, currentFragment);
        transaction.setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_left,
                R.animator.enter_from_right, R.animator.exit_to_right);
        transaction.addToBackStack(ImageFragment.class.getName());
        transaction.commitAllowingStateLoss();

    }
}
