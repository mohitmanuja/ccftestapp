package ccf.android.com.threadUnit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ccf.android.com.R;

/**
 * Created by admin on 15/02/18.
 */

public class ThreadViewHolder extends RecyclerView.ViewHolder {
    ImageView imageProfile;
    TextView username;
    TextView dateText;
    TextView bodyText;
    ImageView bodyImage;

    public ThreadViewHolder(View itemView) {
        super(itemView);

        imageProfile = itemView.findViewById(R.id.user_profile);
        username = itemView.findViewById(R.id.post_title);
        dateText = itemView.findViewById(R.id.date);
        bodyText = itemView.findViewById(R.id.thread_body);
        bodyImage = itemView.findViewById(R.id.body_image);

    }

}
