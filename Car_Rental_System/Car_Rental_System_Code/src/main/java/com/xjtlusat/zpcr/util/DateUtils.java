package com.xjtlusat.zpcr.util;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
    public static int calculateDateDiff(Date date1, Date date2) {
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int daysDiff = (int) (diffInDays + (date1.before(date2) ? 0 : 1));
        return date2.after(date1) ? daysDiff : -daysDiff;
    }

    public static int abs(int a) {
        return a > 0 ? a : -a;
    }

}
