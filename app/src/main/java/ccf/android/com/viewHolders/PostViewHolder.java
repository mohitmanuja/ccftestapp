package ccf.android.com.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ccf.android.com.R;
import ccf.android.com.model.PostUnit;
import ccf.android.com.util.ActivitySwitchHelper;

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

    public void onBind(final PostUnit postUnit) {
        layout.removeAllViews();
        String bodyTextString = postUnit.getPostBody();
        username.setText(postUnit.getUsername());
        dateText.setText(postUnit.getDate());
        Glide.with(itemView.getContext()).load(postUnit.getPostAvatar()).into(imageProfile);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivitySwitchHelper.openPostActivity(postUnit,imageProfile,username,dateText);

            }
        });


    }

    String getBody(String input) {
        String ans = input.replaceAll("\\[ATTACH=full\\][0-9]*\\[/ATTACH\\]", "");
        return ans;
    }


}
