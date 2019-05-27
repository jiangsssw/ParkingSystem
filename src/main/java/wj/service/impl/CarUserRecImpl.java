package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.entity.valueBean.ReqUserRecBean;
import wj.entity.valueBean.RespUserRecBean;
import wj.mapper.ICarUserRecMapper;
import wj.service.interfaces.ICarUserRec;
import wj.until.CarTimeConst;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class CarUserRecImpl implements ICarUserRec {
    private static Logger log = Logger.getLogger(CarUserRecImpl.class);

    @Autowired
    private ICarUserRecMapper mapper;

    @Override
    public CarUserRec[] getAllUserRecInfo(ReqUserRecBean bean) {
        log.error("查询getAllUserRecInfo 入参----》"+bean.toString());
        List<Map<String,Object>> list = mapper.getAllUserRec(bean);
        if (list==null||list.size()==0){
            log.error("查询查询getAllUserRecInfo结果为空");
            return new CarUserRec[0];
        }
        CarUserRec[] recs = new CarUserRec[list.size()];
        for (int i = 0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            CarUserRec rec = new CarUserRec();
            ReflectUtil.mapToObject(map,rec);
            recs[i]=rec;
        }
        log.error("查询查询getAllUserRecInfo结果为--->"+ StringUtils.arrayToCommaDelimitedString(recs));
        return recs;
    }

    public RespUserRecBean setUserRecToRespBean(CarUserRec rec){
        RespUserRecBean bean = new RespUserRecBean();
        bean.setCarParkingId(rec.getCar_parking_id());
        bean.setCarRoomNumber(rec.getCar_room_number());
        bean.setCountMoney(rec.getCount_money());
        bean.setParkingStatus(CarTimeConst.transParking_Status(rec.getParking_status()));
        bean.setPayType(CarTimeConst.transPay_Type(rec.getPay_type()));
        bean.setPhoneId(rec.getPhone_id());
        bean.setUseEndTime(TimeUtil.timeTrans(rec.getUse_end_time()));
        bean.setUseStartTime(TimeUtil.timeTrans(rec.getUse_start_time()));
        bean.setUserCarId(rec.getUser_car_id());
        bean.setUserId(rec.getUser_id());
        bean.setUseTime(CarTimeConst.transUse_Time(rec.getPay_type()));
        bean.setParkingTime(rec.getParking_time());
        return bean;
    }

    public int addUserRecInfo(ParkingInformation information,String phoneId,int money,int time){
        CarUserRec rec = new CarUserRec();
//        private String car_parking_id;
//        private int car_room_number;
//        private int user_id;
//        private String parking_status;
//        private String pay_type;
//        private Timestamp use_start_time;
//        private String use_time;
//        private int count_money;
//        private Timestamp use_end_time;
//        private String user_car_id;
//        private int parking_time;  //实际停车时间
//        private String phone_id;
        rec.setCar_parking_id(information.getCar_parking_id());
        rec.setCar_room_number(information.getCar_room_number());
        rec.setUser_id(information.getUser_id());
        rec.setParking_status(information.getParking_status());
        rec.setPay_type(information.getPay_type());
        rec.setUse_start_time(information.getUse_start_time());
        rec.setUse_time(information.getUse_time());
        rec.setCount_money(money);
        rec.setUse_end_time(TimeUtil.getCurrentTimeNow());
        rec.setUser_car_id(information.getUser_car_id());
        rec.setParking_time(time);
        rec.setPhone_id(phoneId);
        return 0;
    }

}
