package ccf.android.com.viewHolders;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ccf.android.com.R;
import ccf.android.com.adapters.PostUnit;
import ccf.android.com.util.ActivitySwitchHelper;
import ccf.android.com.util.Util;

/**
 * Created by admin on 15/02/18.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {
    ImageView imageProfile;
    TextView username;
    TextView dateText;
    LinearLayout layout;
    //TextView bodyText;
    // ImageView bodyImage;

    public PostViewHolder(View itemView) {
        super(itemView);

        imageProfile = itemView.findViewById(R.id.user_profile);
        username = itemView.findViewById(R.id.post_title);
        dateText = itemView.findViewById(R.id.date);
        layout = itemView.findViewById(R.id.layout);

    }

    public void onBind(PostUnit postUnit) {
        layout.removeAllViews();
        String bodyTextString = postUnit.getPostBody();
        username.setText(postUnit.getUsername());
        dateText.setText(postUnit.getDate());
        Glide.with(itemView.getContext()).load(postUnit.getPostAvatar()).into(imageProfile);
        fillLayout(bodyTextString);
    }

    String getBody(String input) {
        String ans = input.replaceAll("\\[ATTACH=full\\][0-9]*\\[/ATTACH\\]", "");
        return ans;
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
        TextView textView = new TextView(itemView.getContext());
        textView.setTextSize(15);
        textView.setTextIsSelectable(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 5, 5, 5);
        textView.setLayoutParams(layoutParams);
        textView.setText(Html.fromHtml(input));
        textView.setTextColor(itemView.getContext().getResources().getColor(R.color.black));
        layout.addView(textView);
    }

    private void addImageView(String imageId) {
        ImageView imageView = new ImageView(itemView.getContext());
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
