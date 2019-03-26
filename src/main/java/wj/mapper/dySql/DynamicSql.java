package wj.mapper.dySql;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import wj.entity.valueBean.ParkingRecHBean;
import wj.entity.valueBean.UserRecBean;
import wj.until.TimeUtil;


public class DynamicSql {
    private static Logger log = Logger.getLogger(DynamicSql.class);
    public String dynamicParkingRecHSql(ParkingRecHBean bean) throws Exception{
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
}
