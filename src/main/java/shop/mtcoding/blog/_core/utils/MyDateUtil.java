package shop.mtcoding.blog._core.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.util.Date;

public class MyDateUtil {
    public static String timestampFormat(Timestamp boardDate){ // 재사용 가능함
        Date currentDate = new Date(boardDate.getTime());
        return DateFormatUtils.format(currentDate, "yyyy-MM-dd HH:mm");
    }
}
