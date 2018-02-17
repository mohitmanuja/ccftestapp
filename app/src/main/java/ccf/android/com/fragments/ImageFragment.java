package ccf.android.com.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ccf.android.com.R;
import ccf.android.com.util.Util;

/**
 * Created by admin on 17/02/18.
 */

public class ImageFragment extends Fragment {
    View view;
    ImageView imageView;
    String imageUrl;

    public static ImageFragment newInstance(String url) {
        ImageFragment f = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.image_fragment, container, false);
        imageView = view.findViewById(R.id.unit_imageview);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageUrl = getArguments().getString("url");
        Util.fillImage(imageView,imageUrl);

    }
}
