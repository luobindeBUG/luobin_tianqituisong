package com.luobin.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JiNianRiUtils {

    public static int getLianAi(){
        return calculationLianAi("恋爱日期 需修改 格式为例 2000-01-26");
    }
    public static int getBirthday_Jo(){
        try {
            return calculationBirthday("生日 需修改 格式为例 2000-01-26");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 计算生日
    public static int calculationBirthday(String clidate) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cToday = Calendar.getInstance();
        Calendar cBirth = Calendar.getInstance();
        cBirth.setTime(myFormatter.parse(clidate));
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR));
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {

            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {

            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }

        if (days == 0) {
            return 0;
        } else {
            return days;
        }
    }

    // 计算天数
    public static int calculationLianAi(String date) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            long time = System.currentTimeMillis() - simpleDateFormat.parse(date).getTime();
            day = (int) (time / 86400000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

}
