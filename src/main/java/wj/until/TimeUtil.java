package wj.until;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//时间工具
public class TimeUtil {
/*
* 转换时间 timestamp===> yyyyMMddHHMM
* **/
    public static String timeFormat(Timestamp timestamp){
        if (timestamp==null){
            return null;
        }
        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateformat.format(timestamp);
    }
    /*
    * 转换时间 timestamp===> yyyyMMddHHMM
    * **/
    public static String timeFormat(Date timestamp){
        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmm");
        return dateformat.format(timestamp);
    }
    /*
* 获取当前时间
* **/
    public static Timestamp getCurrentTimeNow(){
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        return nousedate;
    }
    /**
     * 转换时间 yyyyMMddHHMM====>Timestamp
     * **/

    public static Timestamp timeParse(String yyyyMMddHHMM)throws ParseException{
        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = (Date)dateformat.parseObject(yyyyMMddHHMM);
        long timt = date.getTime();
        Timestamp timestamp = new Timestamp(timt);
        return timestamp;
    }

    public static int getHourFromTwoTime(Timestamp time1,Timestamp time2){
        long s1 = time1.getTime();
        long s2 = time2.getTime();
        if (s1>s2){
            return (int)Math.floor((s1-s2)/(60*60*1000));
        }
       return  (int)Math.floor((s2-s1)/(60*60*1000));
    }
}
