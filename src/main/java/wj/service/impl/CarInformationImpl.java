package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.valueBean.CarLogin;
import wj.mapper.CarInformationMapper;
import wj.service.interfaces.ICarInformation;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

import java.util.List;
import java.util.Map;

@Service
public class CarInformationImpl implements ICarInformation {
    private static Logger log = Logger.getLogger(CarInformationImpl.class);
    @Autowired
    private CarInformationMapper  mapper;

    /***
     *userId=999,为临时车用户ID
     */

    @Override
    public boolean savCarInformation(CarLogin carLogin, int userId) {
        CarInformation car = new CarInformation();
        car.setCar_status(carLogin.getCarStatus());
        car.setUser_car_id(carLogin.getUserCarId());
        car.setUser_id(userId);
        car.setCreate_time(TimeUtil.getCurrentTimeNow());
        car.setUser_name(carLogin.getUserName());
        car.setCar_type(carLogin.getCarType());
        car.setPhone_id(carLogin.getPhoneId());
        log.error("登记的车辆信息为"+car.toString());
        int i =mapper.addCarInformation(car);
        if (i==1){
            return true;
        }
        return false;
    }
/***
 *
 * 通过手机号获取用户所有的车牌号
 */

    @Override
    public CarInformation[] getAllCarInformationByPhonrId(String phoneId) {
        List<Map> result = mapper.findCarInformationByPhoneId(phoneId);
        if (result==null||result.size()==0){
            return null;
        }
        int length = result.size();
        CarInformation[] carInformations = new CarInformation[length];
        for (int i = 0;i<length;i++){
            CarInformation car = new CarInformation();
            ReflectUtil.mapToObject(result.get(i),car);
            carInformations[i] = car;
        }
        return carInformations;
    }
/**
 * 修改车位信息
 */

    @Override
    public boolean updateCarInformation(CarLogin carLogin, int userId) {
        CarInformation car = new CarInformation();
        car.setCar_status(carLogin.getCarStatus());
        car.setUser_car_id(carLogin.getUserCarId());
        car.setUser_id(userId);
        car.setCreate_time(TimeUtil.getCurrentTimeNow());
        car.setUser_name(carLogin.getUserName());
        car.setCar_type(carLogin.getCarType());
        car.setPhone_id(carLogin.getPhoneId());
        log.error("修改的车辆新信息为："+car.toString());
        int i = mapper.updateCarInformation(car);
        if (i==1){
            return true;
        }
        return false;
    }
}
