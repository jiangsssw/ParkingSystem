package wj.until;

public class CarTimeConst {
    private static final String ONE_DAY = "02";
    private static final String ONE_WEEK = "03";
    private static final String ONE_MONTH = "04";
    private static final String HALF_YEAR = "05";
    private static final String ONE_YEAR = "06";
    private static final String TemporaryParking = "01";

    //计费的
    private static final String one = "hour_mount";
    private static final String two = "day_mount";
    private static final String three = "week_mount";
    private static final String four = "month_mount";
    private static final String five = "year_mount";
    private static final String six = "year_mount";

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
