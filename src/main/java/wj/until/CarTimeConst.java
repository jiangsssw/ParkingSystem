package wj.until;

import org.springframework.util.StringUtils;

public class CarTimeConst {
     public static final String ONE_DAY = "02";
    public static final String ONE_WEEK = "03";
    public  static final String ONE_MONTH = "04";
    public static final String HALF_YEAR = "05";
    public static final String ONE_YEAR = "06";
    public static final String TemporaryParking = "01";

    //计费的
    public static final String one = "hour_mount";
    public static final String two = "day_mount";
    public static final String three = "week_mount";
    public static final String four = "month_mount";
    public static final String five = "year_mount";
    public static final String six = "year_mount";

    public static final String MANAGE ="02";
    public static final String NO_MANAGE = "01";
    public static final double freeTax = 0.05;

    public static final String GENERAL = "NO_MANAGE";
    public static final String NO_GENERAL = "MANAGE";
    public static final String UNUSUALLY = "NO_LOGIN";


    public static final int LARGE_MONEY =20000;
    public static String getfield(String status){
        if (ONE_DAY.equals(status)){
            return two;
        }
        if (ONE_WEEK.equals(status)){
            return three;
        }
        if (ONE_MONTH.equals(status)){
            return four;
        }
        if (ONE_YEAR.equals(status)){
            return five;
        }
        if (HALF_YEAR.equals(status)){
            return six;
        }
        if (TemporaryParking.equals(status)){
            return one;
        }

        return "";
    }

    public static int TransForTime(String usetime){
        if (ONE_DAY.equals(usetime)){
            return 24;
        }
        if (ONE_WEEK.equals(usetime)){
            return 7*24;
        }
        if (ONE_MONTH.equals(usetime)){
            return 30*24;
        }
        if (HALF_YEAR.equals(usetime)){
            return 30*24*6;
        }
        if (ONE_YEAR.equals(usetime)){
            return 365*24;
        }
        return 0;
    }

    public static String transParking_Status(String status){
        if (StringUtils.isEmpty(status)){
            return status;
        }
        if ("01".equals(status)){
            return "车位空闲";
        }
        if ("02".equals(status)){
            return "车位占用";
        }
        if ("03".equals(status)){
            return "车位毁坏";
        }
        if ("04".equals(status)){
            return "其他";
        }
        return status;
    }

    public static String transPay_Type(String input){
        if (StringUtils.isEmpty(input)){
            return input;
        }
        if ("01".equals(input)){
            return "租用";
        }
        if ("02".equals(input)){
            return "购买";
        }
        return input;
    }
    public static String transUse_Time(String input){
        if (StringUtils.isEmpty(input)){
            return input;
        }
        if ("01".equals(input)){
            return "临时租用";
        }
        if ("02".equals(input)){
            return "一个月";
        }
        if ("03".equals(input)){
            return "半年";
        }
        if ("04".equals(input)){
            return "一年";
        }

        return input;

    }

    public static String transCar_Type(String input){
        if (StringUtils.isEmpty(input)){
            return input;
        }
        if ("01".equals(input)){
            return "普通轿车";
        }
        if ("02".equals(input)){
            return "SUV";
        }
        if ("03".equals(input)){
            return "超级跑车";
        }
        if ("04".equals(input)){
            return "面包车";
        }
        if ("05".equals(input)){
            return "小货车";
        }
        if ("06".equals(input)){
            return "中型货车及以上";
        }

        return input;
    }

    public static String transIs_Subscription(int input){
        if (input==0){
            return "";
        }
        if (1==input){
            return "预约用户";
        }
        if (2==input){
            return "非预约";
        }
        return "";
    }
}
