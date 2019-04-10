package wj.until;

import org.springframework.util.StringUtils;

import java.util.Map;

public class TypeUtil {
   public static boolean isBigThanZero(Map.Entry<String,Object> entry){
        String longName = entry.getValue().getClass().toString();
        String name = longName.substring(longName.lastIndexOf(".")+1);
        if ("int,long,Integer,Long,Double,double,byte,Byte".contains(name)) {
            if (Integer.valueOf(TypeUtil.subDocToString(String.valueOf(entry.getValue()))) > 0) {
                return true;
            }else {
                return false;
            }
        }
        return true;
    }
    public static String subDocToString(String s){
       if (StringUtils.isEmpty(s)){
           return "";
       }
       int num = s.indexOf(".");
       if (num>0){
           return s.substring(0,num);
       }
       return s;
    }
}
