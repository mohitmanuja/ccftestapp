package ccf.android.com.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccf.android.com.R;
import ccf.android.com.model.PostUnit;
import ccf.android.com.util.ActivitySwitchHelper;
import ccf.android.com.util.Util;

/**
 * Created by admin on 17/02/18.
 */

public class FinalActivity extends Activity {
    View view;
    LinearLayout layout;
    ImageView imageProfile;
    TextView username;
    TextView dateText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_fragment);

        username = findViewById(R.id.post_title);
        imageProfile = findViewById(R.id.user_profile);

        dateText = findViewById(R.id.date);
        layout = (LinearLayout) findViewById(R.id.layout);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username.setText(bundle.getString("name"));
            dateText.setText(bundle.getString("date"));
            String profile = bundle.getString("profile");
            Glide.with(Util.context).load(profile).into(imageProfile);
            String body = bundle.getString("body");
            if (body != null)
                fillLayout(body);
            else
                Toast.makeText(Util.context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }


    }

    void fillLayout(String input) {
        Pattern hashTagPattern = Pattern.compile("\\[ATTACH=full\\][0-9]*\\[/ATTACH\\]");
        Matcher hashTagMatcher = hashTagPattern.matcher(input);
        int lastEnd = 0;

        if (hashTagMatcher.find()) {
            do {
                int start = hashTagMatcher.start();
                int end = hashTagMatcher.end();

                String textData = input.substring(lastEnd, start);
                lastEnd = end;

                String imageId = hashTagMatcher.group(0).substring(13, hashTagMatcher.group(0)
                        .indexOf("[/"));
                addTextView(textData);
                addImageView(imageId);
                try {
                    String imageTag = input.substring(start, end);

                } catch (Exception e) {

                }
            } while (hashTagMatcher.find());
        } else {
            addTextView(input);
        }

        while (hashTagMatcher.find()) {

        }
    }

    private void addTextView(String input) {
        TextView textView = new TextView(Util.context);
        textView.setTextSize(15);
        textView.setTextIsSelectable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 5, 5, 5);
        textView.setLayoutParams(layoutParams);
        textView.setText(Html.fromHtml(input));
        textView.setTextColor(Util.context.getResources().getColor(R.color.black));
        layout.addView(textView);
    }

    private void addImageView(String imageId) {
        ImageView imageView = new ImageView(Util.context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        layoutParams.setMargins(15, 15, 15, 15);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        final String url = Util.IMAGE_URL_PART1 + imageId + Util.IMAGE_URL_PART2;
        Util.fillImage(imageView, url);
        layout.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitySwitchHelper.openImageFragment(url);
            }
        });
    }

}
