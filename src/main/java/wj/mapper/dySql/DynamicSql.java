package wj.mapper.dySql;


import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import wj.entity.dataBaseMapping.Muse;
import wj.entity.valueBean.ReqParkingRecHBean;
import wj.entity.valueBean.UserRecBean;
import wj.until.BeanUtils;
import wj.until.TimeUtil;
import wj.until.TypeUtil;

import java.util.Iterator;
import java.util.Map;


public class DynamicSql {
    private static Logger log = Logger.getLogger(DynamicSql.class);
    public String dynamicParkingRecHSql(ReqParkingRecHBean bean) throws Exception{
        StringBuilder sb = new StringBuilder("Select * from parking_rec_his where ");
        String startTime = bean.getStartTime();
        String endTime = bean.getEndTime();
        String carType = bean.getCarType();
        int  userId = bean.getUserId();
        String userCarId = bean.getUserCarId();
        if (userId==0){
            throw new Exception("userId不能为空");
        }
            sb.append("user_id =");
            sb.append(userId);

        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            sb.append(" and parking_start_time between ");
            sb.append("str_to_date( '");
            sb.append(TimeUtil.timeParse(startTime));
            sb.append("','%Y-%m-%d %H:%i:%S')");
            sb.append( " and ");
            sb.append("str_to_date( '");
            sb.append(TimeUtil.timeParse(endTime));
            sb.append("','%Y-%m-%d %H:%i:%S')");
        }
        if (!StringUtils.isEmpty(userCarId)){
            sb.append(" and user_car_id = ");
            sb.append(userCarId);
        }
        if (!StringUtils.isEmpty(carType)){
            sb.append(" and car_type = ");
            sb.append(carType);
        }
        sb.append(" limit ");
        sb.append(bean.getStartCount());
        sb.append(",10");
        log.error("parkingRecH sql:"+sb.toString());
        return sb.toString();
    }

    public String dynamicUserRecSql(UserRecBean bean) throws Exception{
        int userId = bean.getUserId();
        String userCarId = bean.getUserCarId();
        String phoneId = bean.getPhoneId();
        String startTime = bean.getStartTime();
        String endTime =bean.getEndTime();
        String carType = bean.getCarType();

        StringBuilder sb = new StringBuilder("select * from car_use_rec where");
        if (StringUtils.isEmpty(phoneId)){
            throw new Exception("号码为空，禁止查询");
        }
        sb.append(" phone_id = ");
        sb.append(phoneId);
        if (userId>0){
           sb.append(" and user_id = ");
           sb.append(userId);
        }
        if (!StringUtils.isEmpty(userCarId)){
            sb.append(" and user_car_id = ");
            sb.append(userCarId);
        }
        if (!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            sb.append(" and use_start_time between str_to_date('");
            sb.append(TimeUtil.timeParse(startTime));
            sb.append("','%Y-%m-%d %H:%m:%S') and str_to_date('");
            sb.append(TimeUtil.timeParse(endTime));
            sb.append("','%Y-%m-%d %H:%m:%S')");
        }
        if (!StringUtils.isEmpty(carType)){
            sb.append(" and car_type = ");
            sb.append(carType);
        }
        if (bean.getStartCount()>=0){
            sb.append(" limit ");
            sb.append(bean.getStartCount());
            sb.append(",10");
        }

        return sb.toString();
    }
    public String dynamicMuseSql(Muse muse){
        StringBuilder sb = new StringBuilder("select * from muse");
        Map<String,Object> map = BeanUtils.obj2Map(muse);
        if (map!=null||map.size()>0) {
            int i = 0;
//            for (Map.Entry entry : map.entrySet()) {
//                sb.append(entry.getKey());
//                sb.append("=");
//                sb.append(entry.getValue());
//
//            }
            for (Iterator<Map.Entry<String,Object>> it = map.entrySet().iterator();it.hasNext();){
                Map.Entry<String,Object> entry = it.next();

                String longName = entry.getValue().getClass().toString();
                String name = longName.substring(longName.lastIndexOf(".")+1);
                if ("int,long,Integer,Long,Double,double,byte,Byte".contains(name)){
                    if (Integer.valueOf(TypeUtil.subDocToString(String.valueOf(entry.getValue())))>0){
                        if (i==0){
                           sb.append(" where ");
                            i=1;
                        }
                        sb.append(entry.getKey());
                        sb.append("=");
                        sb.append(entry.getValue());
                    }else {
                        continue;
                    }
                }else {
                    if (i==0){
                        sb.append(" where ");
                        i=1;
                    }
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                }
                if (it.hasNext()){

                    if (TypeUtil.isBigThanZero(it.next())){
                        sb.append(" and ");
                    }
                }
            }
        }
        return sb.toString();
    }
}
