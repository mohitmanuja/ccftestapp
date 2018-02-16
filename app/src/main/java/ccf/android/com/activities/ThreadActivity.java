package ccf.android.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import ccf.android.com.R;
import ccf.android.com.threadUnit.ThreadViewAdapter;
import ccf.android.com.util.Util;

public class ThreadActivity extends AppCompatActivity {


    String response;
    JSONObject responseJson;
    RecyclerView threadRecyclerView;
    ImageView threadAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        threadAvatar = findViewById(R.id.user_profile);

        response = getIntent().getStringExtra("response");
        try {
            responseJson = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String threadTitle = responseJson.optJSONObject("data").optJSONObject("thread").optString("title");
        long date = responseJson.optJSONObject("data").optJSONObject("thread").optLong("last_post_date");
        String threadAvatarUrl = responseJson.optJSONObject("data").optJSONObject("thread").optString("avatar");
        Glide.with(this).load(threadAvatarUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(threadAvatar);

        TextView titleText = findViewById(R.id.post_title);
        TextView dateText = findViewById(R.id.date);

        titleText.setText(threadTitle);
        dateText.setText(Util.getDate(date));

        threadRecyclerView = findViewById(R.id.recycler_view);
        ThreadViewAdapter threadViewAdapter = new ThreadViewAdapter(this, MainActivity.arrayList);
        threadRecyclerView.setAdapter(threadViewAdapter);
        threadRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

}
