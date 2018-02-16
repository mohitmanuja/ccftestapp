package ccf.android.com.threadUnit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 15/02/18.
 */

public class ThreadUnit {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostAvatar() {
        return postAvatar;
    }

    public void setPostAvatar(String postAvatar) {
        this.postAvatar = postAvatar;
    }

    String username;
    String postBody;
    String postAvatar;

    public String getBodyImage() {
        return bodyImage;
    }

    public void setBodyImage(String bodyImage) {
        this.bodyImage = bodyImage;
    }

    String bodyImage;

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date resultdate = new Date(date);
        String s = sdf.format(resultdate);
        return s;
    }

    public void setDate(long date) {
        this.date = date;
    }

    long date;
}
