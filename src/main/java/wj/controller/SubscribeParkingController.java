package wj.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.entity.dataBaseMapping.User;
import wj.mapper.CarInformationMapper;
import wj.mapper.ParkingInformationMapper;
import wj.service.interfaces.ICarInformation;
import wj.service.interfaces.IParkingInformation;
import wj.until.CarTimeConst;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Map;

@Controller
public class SubscribeParkingController {

    private static Logger log = Logger.getLogger(SubscribeParkingController.class);

    @Autowired
    private IParkingInformation parkingInformation;


    @Autowired
    private ParkingInformationMapper mapper;

    @Autowired
    private CarInformationMapper carMapper;

    //展示车库信息
    @RequestMapping("/showParkingOfRoom")
    public String showParkingOfRoom(@PathVariable("roomId") int roomId,Model model){
        if (roomId<0){
            //展示错误页面
            model.addAttribute("error","错误的车库编号"+roomId);
            return "error";
        }
        ParkingInformation[] parkingInformations = parkingInformation.getAllCarInfoInRoom(roomId);
        //展示页面
        model.addAttribute("result",parkingInformations);
        return "parkingInformations";
    }


    //手动预约车辆
    @RequestMapping(value = "/subscribeParking",method = RequestMethod.POST)
    public String subscribeParking(@PathVariable("pakingId")String parkingId,@PathVariable("useTime")String useTime, Model model, HttpSession httpSession){
        if (parkingId==null||parkingId.length()==0){
            //展示错误页面
            model.addAttribute("error","错误的车库编号"+parkingId);
            return "error";
        }
        Map map = mapper.findParkingInformationByCarParkingId(parkingId);
        String status =(String) map.get("parking_status");
        if ("01".equals(status)){
            //车位空闲，进行预约

            //获取用户信息
            User u = (User) httpSession.getAttribute("User");
            Timestamp times =null;
            try {
                times = TimeUtil.timeParse(useTime);
            }catch (Exception e){
                log.error("转换时间出错");
                e.printStackTrace();
            }

            ParkingInformation parkingInfos = new ParkingInformation();
            parkingInfos.setUser_id(u.getUser_id());
            parkingInfos.setUse_start_time(times);
            parkingInfos.setCar_parking_id(parkingId);
            parkingInfos.setParking_status("03");//车位预约
            mapper.updateParkingInformation(parkingInfos);
            model.addAttribute("rulet","预约成功");
            return "SubscribeSuccess";
        }

        return "Subscribefaile";
    }


    //快速预约车辆
    @RequestMapping(value = "/quickSubscribeParking",method = RequestMethod.POST)
    public String quickSubscribeParking(@PathVariable("useTime")String useTime, Model model, HttpSession httpSession){
        ParkingInformation[] parkingInformations = parkingInformation.getAllCarInfoByStatus("01");
        if (parkingInformations==null||parkingInformations.length==0){
            //无停车位，
            model.addAttribute("relut","no parking");
            return "error";
        }
        String parkingId = parkingInformations[0].getCar_parking_id();
        User u = (User) httpSession.getAttribute("User");
        Timestamp times =null;
        try {
            times = TimeUtil.timeParse(useTime);
        }catch (Exception e){
            log.error("转换时间出错");
            e.printStackTrace();
        }

        ParkingInformation parkingInfos = new ParkingInformation();
        parkingInfos.setUser_id(u.getUser_id());
        parkingInfos.setUse_start_time(times);
        parkingInfos.setCar_parking_id(parkingId);
        parkingInfos.setParking_status("03");//车位预约
        int i = mapper.updateParkingInformation(parkingInfos);
        if (i>0){
            model.addAttribute("result","预约成功");
            return "SubscribeSuccess";
        }

        model.addAttribute("result","预约失败");
        return "error";
    }



    //1查找车辆信息表判断是否为登记的用户，若不是则登记车辆信息然后5（String carId）
    //2登记在册的用户获取器userId判断其是否是注册用户，如果是则手机号查询用户是否预约，
    // 3若无预约则查找车位登记停车
    //4若预约则通过校验登记停车
    // 5若非注册用户但登记过，则查找车位登记停车
    @RequestMapping(value = "/carIn",method = RequestMethod.POST)
    public String dealWithCarIn(@PathVariable("carId") String carId ,Model model,Errors errors) throws Exception{
           Map map = carMapper.findCarInformationByCarId(carId);
           if(map==null||map.size()==0){
               //未登记车辆进行登记
               return "loginCar";
           }
        CarInformation parkings = new CarInformation();
        ReflectUtil.mapToObject(map,parkings);
        if (parkings==null||parkings.getPhone_id()==null){
            //转换出错
            log.error("map转pojo出错");
            throw new Exception();
        }
        int userId = parkings.getUser_id();
        String carType = parkings.getCar_type();
        if (userId==999){
            //非注册用户
            return parkingInformation.setCarToParking(model,userId,carId,"03","01","01",carType);
        }
        //userId=other,查询parkingInfo..表中是否有其预约
        Map carMap = mapper.findParkingInformationByCarId(carId);
        if (carMap==null||carMap.size()==0){
            //非预约的用户
            return parkingInformation.setCarToParking(model,userId,carId,"03","01","01",carType);
        }
        //判断是长期租车还是预约租车
        ParkingInformation p = new ParkingInformation();
        ReflectUtil.mapToObject(carMap,p);
        if (p==null||p.getUser_car_id()==null){
            //转换出错
            log.error("map转pojo出错");
            throw new Exception("map转pojo出错");
        }
        String pType = p.getPay_type();
        String useTime = p.getUse_time();
        if ("01".equals(useTime)&&"01".equals(pType)){
            //预约车辆
            p.setUse_start_time(TimeUtil.getCurrentTimeNow());//时间改
            p.setParking_status("02");//状态改为占用
            log.error("停车信息："+p.toString());
           int n = mapper.updateParkingInformation(p);

           if (n>0){
               //修改失败
               model.addAttribute("result","车辆通过失败");
               log.error("停车失败信息："+p.toString());
               return "checkFailed";

           }

            model.addAttribute("result","车辆通过");
            //调用其他的接口，如硬件放行
            return "checkSuccess";
        }
        //长期租用车辆
        if ("02".equals(pType)&&!"01".equals(useTime)){
           //判断是否再租期内
           Timestamp timeNow = TimeUtil.getCurrentTimeNow();
           Timestamp timeUse = p.getUse_start_time();
           String use = p.getUse_time();
            int hourForuse = CarTimeConst.TransForTime(use);
            int houForUsed = TimeUtil.getHourFromTwoTime(timeNow,timeUse);
            if (hourForuse>houForUsed){
                //在租期内放行

                //登记停车记录表

            }
        }

        return "";
    }


}
