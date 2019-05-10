package fre.shown.tryboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Radon Freedom
 * created at 2019.02.19 11:34
 */


//TODO 不使用这个类，使用持久层级别的时间创建工具
public class DateUtils {

    /**
     * 使用 yyyy-MM-dd HH:mm:ss 格式化当前时间并返回
     * @return 格式化后的当前时间（精确到秒）
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
}
