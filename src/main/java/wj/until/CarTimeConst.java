package wj.until;

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

}
