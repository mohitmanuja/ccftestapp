package ccf.android.com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohit on 15/02/18.
 */

public class Util {

    public static String IMAGE_URL_PART1 = "https://canadiancorvetteforums.com/api.php?action=attachment-view&attachment_id=";
    public static String IMAGE_URL_PART2 = "&visitor_id=3945&token=05a19491e556075c9ce832476dd4c3db3d561a5e";
    public static String getDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        Date resultdate = new Date(date);
        String s = sdf.format(resultdate);
        return s;
    }


}
