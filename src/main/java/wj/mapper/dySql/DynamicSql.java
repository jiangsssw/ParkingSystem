package wj.mapper.dySql;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import wj.entity.valueBean.ParkingRecHBean;
import wj.until.TimeUtil;


public class DynamicSql {
    private static Logger log = Logger.getLogger(DynamicSql.class);
    public String dynamicParkingRecHSql(ParkingRecHBean bean) throws Exception{
        StringBuilder sb = new StringBuilder("select * from Select * from parking_rec_his where ");
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
            sb.append(" and parking_start_time between");
            sb.append(TimeUtil.timeParse(startTime));
            sb.append( " and ");
            sb.append(TimeUtil.timeParse(endTime));
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
}
