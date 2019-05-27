package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.CarRoomInformation;
import wj.entity.valueBean.CarLogin;
import wj.mapper.CarInformationMapper;
import wj.mapper.CarRoomInformationMapper;
import wj.mapper.ParkingInformationMapper;
import wj.service.interfaces.ICarInformation;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CarInformationImpl implements ICarInformation {
    private static Logger log = Logger.getLogger(CarInformationImpl.class);
    @Autowired
    private CarInformationMapper  mapper;

    @Autowired
    private CarRoomInformationMapper roomInformationMapper;
    @Autowired
    private ParkingInformationMapper parkingInformationMapper;
    /***
     *userId=999,为临时车用户ID
     */

    @Override
    public boolean savCarInformation(CarLogin carLogin, int userId) {
        CarInformation car = new CarInformation();
        car.setCar_status(userId==999?"02":"01");
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

    public int addCarRoom(int roomId,int parkingNum,String remark){
        CarRoomInformation room = new CarRoomInformation();
        room.setCar_room_number(roomId);
        room.setCar_parking_num(parkingNum);
        room.setEXT1(remark);
        return roomInformationMapper.addCarRoomInformation(room);
    }

    public List<CarRoomInformation> getAllCarRoom(){
        List<Map<String,Object>> roomsMap=roomInformationMapper.getAllRoom();
        if (roomsMap!=null&&roomsMap.size()>0){
            List<CarRoomInformation>  list = new ArrayList<>();
            for (Map<String,Object> map : roomsMap){
                CarRoomInformation information = new CarRoomInformation();
                try {
                    ReflectUtil.mapToObject(map,information);
                }catch (Exception e){
                    log.error("map convert pojo error--->"+e.getMessage());
                    continue;
                }
                list.add(information);
            }
            return list;
        }
        return null;
    }

    public int deleteRoomId(int roomId){
        //删除车位信息表的 所有该仓库的车位
        int j = parkingInformationMapper.deleteParkingInformationByRoomId(roomId);
        if (j==0){
            log.error("deleteRoomId删除车位失败 roomId-->"+roomId);
            return 0;
        }
        //删除车库表的车库信息
        int i = roomInformationMapper.deleteRoomInformation(roomId);
        if (i==0){
            log.error("deleteRoomId删除车库表失败 roomId-->"+roomId);
            return 0;
        }
        return i;
    }

    @Override
    public CarInformation[] getAllCarInformationByUserId(int userId) {
        List<Map> result = mapper.findCarInformationUserId(userId);
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
}
