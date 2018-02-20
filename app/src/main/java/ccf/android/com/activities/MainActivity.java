package ccf.android.com.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ccf.android.com.model.PostUnit;
import ccf.android.com.util.BasicCallBack;
import ccf.android.com.R;
import ccf.android.com.network.BaseCustomAsyncClass;
import ccf.android.com.util.Urls;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String responseString = null;
    public static ArrayList<PostUnit> arrayList;
    BasicCallBack onResponseCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView letStart = (TextView) findViewById(R.id.start);
        final Button retry = (Button) findViewById(R.id.retry);

        letStart.setOnClickListener(this);
        retry.setOnClickListener(this);

        onResponseCallBack = new BasicCallBack() {
            @Override
            public void callBack(int status, Object data) {
                if (status == 0) {
                    responseString = data.toString();
                    JSONObject response = (JSONObject) data;
                    response.optString("null");
                    JSONArray posts = new JSONArray();
                    posts = response.optJSONObject("data").optJSONArray("posts");

                    arrayList = new ArrayList<>();
                    for (int i = 0; i < posts.length(); i++) {
                        PostUnit postUnit = new PostUnit();
                        long date = 0;
                        String avatar = null;
                        String body = null;
                        String username = null;
                        String image = null;
                        try {
                            date = response.getJSONObject("data").optJSONArray("posts").optJSONObject(i).optLong("post_date");
                            avatar = response.getJSONObject("data").optJSONArray("posts").optJSONObject(i).optString("avatar");
                            username = response.getJSONObject("data").optJSONArray("posts").optJSONObject(i).optString("username");
                            body = response.getJSONObject("data").optJSONArray("posts").optJSONObject(i).optString("message");
                            JSONArray attachments = response.getJSONObject("data").optJSONArray("posts").optJSONObject(i).optJSONArray("attachments");
                            if (date != 0)
                                postUnit.setDate(date);
                            if (avatar != null)
                                postUnit.setPostAvatar(avatar);
                            if (body != null)
                                postUnit.setPostBody(body);
                            if (username != null)
                                postUnit.setUsername(username);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        arrayList.add(postUnit);

                    }
                    letStart.setText("Fetched (Click Here)");

                } else {
                    retry.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

        };

        BaseCustomAsyncClass baseCustomAsyncClass = new BaseCustomAsyncClass(onResponseCallBack, Urls.serverUrl);
        baseCustomAsyncClass.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null, null, null);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start) {
            if (responseString != null) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                intent.putExtra("response", responseString);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.retry) {
            BaseCustomAsyncClass baseCustomAsyncClass = new BaseCustomAsyncClass(onResponseCallBack, Urls.serverUrl);
            baseCustomAsyncClass.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null, null, null);

        }
    }
}
