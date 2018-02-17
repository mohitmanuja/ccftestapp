package ccf.android.com.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;

import ccf.android.com.R;

/**
 * Created by mohit on 15/02/18.
 */

public class Util {

    public static void setContext(Context context) {
        Util.context = context;
    }

    public static Context context;

    public static String IMAGE_URL_PART1 = "https://canadiancorvetteforums.com/api.php?action=attachment-view&attachment_id=";
    public static String IMAGE_URL_PART2 = "&visitor_id=3945&token=05a19491e556075c9ce832476dd4c3db3d561a5e";
    public static String getDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date resultdate = new Date(date);
        String s = sdf.format(resultdate);
        return s;
    }

    public static void fillImage(ImageView imageView, String url){
        Glide.with(context).load(url).placeholder(R.drawable.loading).thumbnail(0.1f).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }




}
