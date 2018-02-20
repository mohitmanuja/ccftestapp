package ccf.android.com.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ccf.android.com.R;
import ccf.android.com.fragments.ImageFragment;
import ccf.android.com.fragments.FinalActivity;
import ccf.android.com.model.PostUnit;

/**
 * Created by admin on 17/02/18.
 */

public class ActivitySwitchHelper {


    public static void openImageFragment(String url) {
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

    public static void openPostActivity(PostUnit postUnit, ImageView imageView, TextView username, TextView dateText) {

        Intent i = new Intent(Util.context, FinalActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("name", postUnit.getUsername());
        bundle.putString("date", postUnit.getDate());
        bundle.putString("profile", postUnit.getPostAvatar());
        bundle.putString("body", postUnit.getPostBody());
        i.putExtras(bundle);
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation((Activity) Util.context,  imageView, ViewCompat.getTransitionName(imageView));

        Pair<View, String> p1 = Pair.create((View)imageView, "profile");
        Pair<View, String> p2 = Pair.create((View)username, "title");
        Pair<View, String> p3 = Pair.create((View)dateText, "date");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) Util.context, p1, p2, p3);


        Util.context.startActivity(i,options.toBundle());

    }

}

