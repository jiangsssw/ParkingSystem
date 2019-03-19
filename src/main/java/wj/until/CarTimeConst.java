package wj.until;

public class CarTimeConst {
    public static final String ONE_DAY = "02";
    public static final String ONE_WEEK = "03";
    public static final String ONE_MONTH = "04";
    public static final String HALF_YEAR = "05";
    public static final String ONE_YEAR = "06";
    public static final String TemporaryParking = "01";

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
