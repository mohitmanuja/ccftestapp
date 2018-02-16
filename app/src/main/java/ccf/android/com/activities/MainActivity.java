package ccf.android.com.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ccf.android.com.util.BasicCallBack;
import ccf.android.com.R;
import ccf.android.com.threadUnit.ThreadUnit;
import ccf.android.com.network.BaseCustomAsyncClass;

public class MainActivity extends AppCompatActivity {

    public static String serverUrl = "https://d2gn4xht817m0g.cloudfront.net/conversation_message_attachment/i/332414-1df90f2ae6ef88c6e4a4d9b22e43e476-original?1518691958";
    TextView letStart;
    String responseString = null;
    public static ArrayList<ThreadUnit> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        letStart = (TextView) findViewById(R.id.start);

        letStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (responseString!=null){
                    Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                    intent.putExtra("response", responseString);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Please wait", Toast.LENGTH_SHORT).show();
                }

            }
        });


        BasicCallBack basicCallBack = new BasicCallBack() {
            @Override
            public void callBack(int status, Object data) {
                responseString = data.toString();
                JSONObject response = (JSONObject) data;
                response.optString("null");
                JSONArray posts = new JSONArray();
                posts = response.optJSONObject("data").optJSONArray("posts");

                arrayList = new ArrayList<>();
                for (int i = 0; i < posts.length(); i++) {
                    ThreadUnit threadUnit = new ThreadUnit();
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
                            threadUnit.setDate(date);
                        if (avatar != null)
                            threadUnit.setPostAvatar(avatar);
                        if (body != null)
                            threadUnit.setPostBody(body);
                        if(username!=null)
                            threadUnit.setUsername(username);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    arrayList.add(threadUnit);

                }
                letStart.setText("Fetched (Click Here)");

            }
        };

        BaseCustomAsyncClass baseCustomAsyncClass = new BaseCustomAsyncClass(basicCallBack, serverUrl);
        baseCustomAsyncClass.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null, null, null);


    }
}
