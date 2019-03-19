package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.mapper.ParkingInformationMapper;
import wj.service.interfaces.IParkingInformation;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

import java.util.List;
import java.util.Map;
@Service
public class ParkingInformationImpl implements IParkingInformation {
    private static Logger log = Logger.getLogger(ParkingInformationImpl.class);
    @Autowired
    private ParkingInformationMapper mapper;
/***
 * 获取转态的车位信息
 * **/
    @Override
    public ParkingInformation[] getAllCarInfoByStatus(String status) {
        if (status!=null&&status.length()>0){

            List<Map> list = mapper.findParkingInformationByStatus(status);
            if (list!=null&&list.size()>0){
                ParkingInformation[] parkings = new ParkingInformation[list.size()];
                for (int i = 0; i<list.size();i++){
                    Map map = list.get(i);
                    ParkingInformation parking= new ParkingInformation();
                    ReflectUtil.mapToObject(map,parking);
                    parkings[i]=parking;
                }
                return parkings;
            }
        }
        return new ParkingInformation[0];
    }

    /**
 * 获取某个车库下所有的车位信息
 * **/
    @Override
    public ParkingInformation[] getAllCarInfoInRoom(int number) {
        if (number>0){

            List<Map> list = mapper.findParkingInformationByCarRoom(number);
            if (list!=null&&list.size()>0){
                ParkingInformation[] parkings = new ParkingInformation[list.size()];
                for (int i = 0; i<list.size();i++){
                   Map map = list.get(i);
                    ParkingInformation parking= new ParkingInformation();
                    ReflectUtil.mapToObject(map,parking);
                    parkings[i]=parking;
                }
                return parkings;
            }
        }

        return new ParkingInformation[0];
    }
//查找用户的停车位信息
    @Override
    public ParkingInformation[] getAllCarInfoByUserId(int userId) {
        if (userId>0){

            List<Map> list = mapper.findParkingInformationByUserId(userId);
            if (list!=null&&list.size()>0){
                ParkingInformation[] parkings = new ParkingInformation[list.size()];
                for (int i = 0; i<list.size();i++){
                    Map map = list.get(i);
                    ParkingInformation parking= new ParkingInformation();
                    ReflectUtil.mapToObject(map,parking);
                    parkings[i]=parking;
                }
                return parkings;
            }
        }
        return new ParkingInformation[0];
    }

    @Override
    public boolean addParkingInfo(ParkingInformation information) {
        if (information.getCar_parking_id()!=null){
            int i = mapper.addParkingInformation(information);
            if (i>0){
                log.error("添加车位信息："+information.toString()+"返回"+i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String  setCarToParking(Model model,int userId, String carId, String parkingStatus, String payType, String useTime, String carType) {
        //则查找车位登记停车
        ParkingInformation[]  allCar  = getAllCarInfoByStatus("01");
        if(allCar==null||allCar.length==0){
            //无停车位，
            model.addAttribute("result","no parking can park");
            return "error";
        }
        //取第一个车辆
        String car_parking_id = allCar[0].getCar_parking_id();
        ParkingInformation parkingInfos = new ParkingInformation();
        parkingInfos.setUser_id(userId);
        parkingInfos.setUse_start_time(TimeUtil.getCurrentTimeNow());
        parkingInfos.setCar_parking_id(car_parking_id);
        parkingInfos.setParking_status(parkingStatus);//车位占用
        parkingInfos.setPay_type(payType);
        parkingInfos.setUse_time(useTime);
        parkingInfos.setUser_car_id(carId);
        parkingInfos.setCar_type(carType);
        log.error("停车信息："+parkingInfos.toString());
        int i = mapper.updateParkingInformation(parkingInfos);
        if (i>0){
            //登记停车记录表

            model.addAttribute("result","车辆通过");
            //调用其他的接口，如硬件放行
            return "checkSuccess";
        }
        model.addAttribute("result","车辆通过失败");
        log.error("停车失败信息："+parkingInfos.toString());
        return "checkFailed";
    }


    //登记车辆信息到历史表
    public void setParkingInfoToHis(ParkingInformation parkingInfos){
        ParkingRecHis his = new ParkingRecHis();
        his.setCar_parking_id(parkingInfos.getCar_parking_id());
        his.setCar_room_number(parkingInfos.getCar_room_number());
        his.setParking_start_time(parkingInfos.getUse_start_time());
        his.setParking_type(parkingInfos.getCar_type());

    }


}
