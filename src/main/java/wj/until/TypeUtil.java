package wj.until;

import java.util.Map;

public class TypeUtil {
   public static boolean isBigThanZero(Map.Entry<String,Object> entry){
        String longName = entry.getValue().getClass().toString();
        String name = longName.substring(longName.lastIndexOf(".")+1);
        if ("int,long,Integer,Long,Double,double,byte,Byte".contains(name)) {
            if ((int) entry.getValue() > 0) {
                return true;
            }else {
                return false;
            }
        }
        return true;
    }
}
