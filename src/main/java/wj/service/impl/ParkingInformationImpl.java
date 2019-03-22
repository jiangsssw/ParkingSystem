package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.mapper.CalculateRulerMapper;
import wj.mapper.ParkingInformationMapper;
import wj.mapper.ParkingRecHisMapper;
import wj.service.interfaces.IParkingInformation;
import wj.until.CarTimeConst;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ParkingInformationImpl implements IParkingInformation {
    private static Logger log = Logger.getLogger(ParkingInformationImpl.class);
    @Autowired
    private ParkingInformationMapper mapper;

    @Autowired
    private ParkingRecHisMapper parkingRecHisMapper;

    @Autowired
    private CalculateRulerMapper calculateRulerMapper;
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
    public String  setCarToParking(Model model,int userId, String carId, String parkingStatus, String payType, String useTime, String carType,String name)  {
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
            // 登记停车记录表
        try {
            setParkingInfoToHis(parkingInfos,name);
        }catch (Exception e){
            log.error("出错的用户姓名："+name+"车牌号："+carId+"登记信息" +
                    parkingInfos.toString());
            e.printStackTrace();
        }
            model.addAttribute("result","车辆通过");
            //调用其他的接口，如硬件放行
            return "checkSuccess";
        }
        model.addAttribute("result","车辆通过失败");
        log.error("停车失败信息："+parkingInfos.toString());
        return "checkFailed";
    }


    //登记车辆信息到历史表
    @Override
    public void setParkingInfoToHis(ParkingInformation parkingInfos,String userName) throws Exception{
        ParkingRecHis his = new ParkingRecHis();
        his.setCar_parking_id(parkingInfos.getCar_parking_id());
        his.setCar_room_number(parkingInfos.getCar_room_number());
        his.setParking_start_time(parkingInfos.getUse_start_time());
        his.setParking_type(parkingInfos.getUse_time());
        his.setCar_type(parkingInfos.getCar_type());
        his.setUser_name(userName);
        his.setUser_id(parkingInfos.getUser_id());
        his.setPay_type(parkingInfos.getPay_type());
        int i = parkingRecHisMapper.addParkingRecHis(his);
        if (i>0){
            log.error("登记入停车历史表"+his.toString());

        }else {
            log.error("登记入停车历史表出错车辆" + his.toString());
            throw new Exception("登记停车历史表出错");

        }
    }

    @Override
    public String dealWithOutCar(Model model, ParkingInformation parking) {
        //判断其是否是预约
        int a = parking.getIs_subscription();
        Timestamp timestamp = parking.getUse_start_time();
        Timestamp timeNow = new Timestamp(new Date().getTime());
        int parkingTime =TimeUtil.getHourFromTwoTime(timeNow,timestamp);
        int realParkingTime=0;
        String status = parking.getParking_status();
        if (!"01".equals(status)&&status!=null&&status!=""){
            //长期租赁用户
            //判断其是否在租期内
            int useTime = CarTimeConst.TransForTime(parking.getUse_time());
            if (parkingTime>useTime){
                //租期到期。。。算出超时部分的钱
                 realParkingTime= parkingTime - useTime;
            }
            //没到期
            realParkingTime=0;
        }
        if ("01".equals(status)){
            //普通临时用户
            realParkingTime = parkingTime;
        }
        double realMoney=0;
        double money=0;
        try {
            money = getMoney(realParkingTime);
        }catch (Exception e){
            e.printStackTrace();
        }
        //取出相应字段
        if (a>0){
            //预约用户
            realMoney = money*(1-CarTimeConst.freeTax)-5;
            if (realMoney<0){
                realMoney = 0;
            }

        }
        if (!"01".equals(status)&&status!=null&&status!=""){
            realMoney = money*(1-CarTimeConst.freeTax);
        }

        if (status==null||status==""){
            //非法用户
        model.addAttribute("result","用户非法");
        log.error("用户非法："+parking.toString());
        return "error";
        }
        //返回应收费显示页面
        model.addAttribute("result",realMoney);
        log.error("车辆"+parking.getUser_car_id()+"应收费用"+realMoney);
        return "payMoney";

    }

    /**
     *@param parkingTime 停车时长 int
     *  用户身份 1 租期用户  2 预约用户 3.临时用户 （int）
     * @param
     * */
    public double getMoney(int parkingTime)throws Exception{
        if (parkingTime>18000){
            return CarTimeConst.LARGE_MONEY;
        }
        Map<String,Object> map = calculateRulerMapper.getCalculateRuler();
        if (map==null||map.size()==0){
            throw new Exception("calculate表没有计费方试，");
        }
        double  money =0;
            int hourCount = (int)map.get(CarTimeConst.getfield(CarTimeConst.one));
            int dayCount = (int)map.get(CarTimeConst.getfield(CarTimeConst.two));
            int weekCount = (int)map.get(CarTimeConst.getfield(CarTimeConst.three));
            int monthCount = (int)map.get(CarTimeConst.getfield(CarTimeConst.four));
            int halfYearCount = (int)map.get(CarTimeConst.getfield(CarTimeConst.five));
            int yearCount = (int)map.get(CarTimeConst.getfield(CarTimeConst.six));
            //天
            int day = (int) Math.floor(parkingTime/24);
            int remain=0;
            if (day>0){
                //周
                int week = (int) Math.floor(day/7);
                if (week>0){
                    //月
                    int month = (int) Math.floor(day/30);
                    if (month>0){
                        //半年
                        int halfYear =(int) Math.floor(month/6);
                        if (halfYear>0){
                            //年
                            int year = (int)Math.floor(halfYear/2);
                            if (year>0){
                                //年计费
                               remain = (int)Math.floor((parkingTime-year*24*365)/(24*30));
                               money = year*yearCount + remain*monthCount;
                               return money;
                            }
                            //半年计费
                            remain = (int)Math.floor((parkingTime-halfYear*6*30*24)/(24*30));
                            money = halfYear*halfYearCount + remain*monthCount;
                            return money;
                        }
                        //月计费
                        remain = (int) Math.floor((parkingTime-month*30*24)/(24*7));
                        money = month*monthCount + remain*weekCount;
                        return money;
                    }
                    //周计费
                    remain = (int) Math.floor((parkingTime - week*7*24)/24);
                    money = week*weekCount+remain*dayCount;
                    return money;
                }
                //天计费
                 remain = parkingTime-day*24;
                money = day*dayCount+remain*hourCount;
                return money;
            }
            //小时
            money = parkingTime*hourCount;
            return money;
    }
}
