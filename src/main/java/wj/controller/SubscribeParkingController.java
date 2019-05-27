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
import wj.entity.valueBean.CarLogin;
import wj.mapper.CarInformationMapper;
import wj.mapper.CarRoomInformationMapper;
import wj.mapper.ParkingInformationMapper;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.ICarInformation;
import wj.service.interfaces.IParkingInformation;
import wj.until.CarTimeConst;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/***
 * 预约车辆
 * */
@Controller
public class SubscribeParkingController {

    private static Logger log = Logger.getLogger(SubscribeParkingController.class);

    @Autowired
    private IParkingInformation parkingInformation;


    @Autowired
    private ParkingInformationMapper mapper;

    @Autowired
    private CarInformationMapper carMapper;

    @Autowired
    private ICarInformation carInformation;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private IParkingInformation parkingInfoService;

    @Autowired
    private CarRoomInformationMapper roomInformationMapper;

    //展示车库信息
    @RequestMapping(value = "/showParkingOfRoom",method = RequestMethod.GET)
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
    @RequestMapping(value = "/subscribeParking",method = RequestMethod.GET)
    public String subscribeParking(@Valid String parkingId, Model model, HttpSession httpSession){
        if (parkingId==null||parkingId.length()==0){
            //展示错误页面
            model.addAttribute("error","错误的车库编号"+parkingId);
            return "error";
        }
        Map map = mapper.findParkingInformationByCarParkingId(parkingId);
        String status =(String) map.get("parking_status");
        if ("01".equals(status)){
            User u = (User) httpSession.getAttribute("User");
            ParkingInformation parkingInfos = new ParkingInformation();
            try {
                ReflectUtil.mapToObject(map,parkingInfos);
            }catch (Exception e){
                log.error("转换时间出错");
                e.printStackTrace();
            }
            parkingInfos.setUser_id(u.getUser_id());
            parkingInfos.setCar_parking_id(parkingId);
            parkingInfos.setParking_status("04");//车位预约
            parkingInfos.setIs_subscription(1);
            mapper.updateParkingInformation(parkingInfos);
            model.addAttribute("rulet","预约成功");
            return "/success/SubscribeSuccess";
        }
        return "/error/Subscribefaile";
    }


    //快速预约车辆
    @RequestMapping(value = "/quickSubscribeParking",method = RequestMethod.POST)
    public String quickSubscribeParking( Model model, HttpSession httpSession){
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
//            times = TimeUtil.timeParse(useTime);
        }catch (Exception e){
            log.error("转换时间出错");
            e.printStackTrace();
        }

        ParkingInformation parkingInfos = parkingInformations[0];
        parkingInfos.setUser_id(u.getUser_id());
//        parkingInfos.setUse_start_time(times);
        parkingInfos.setCar_parking_id(parkingId);
        parkingInfos.setParking_status("04");//车位预约
        parkingInfos.setIs_subscription(1);
        int i = mapper.updateParkingInformation(parkingInfos);
        if (i>0){
            model.addAttribute("result","预约成功");
            return "/success/SubscribeSuccess";
        }

        model.addAttribute("result","预约失败");
        return "/error/Subscribefaile";
    }



    //1查找车辆信息表判断是否为登记的用户，若不是则登记车辆信息然后5（String carId）
    //2登记在册的用户获取器userId判断其是否是注册用户，如果是则手机号查询用户是否预约，
    // 3若无预约则查找车位登记停车
    //4若预约则通过校验登记停车
    // 5若非注册用户但登记过，则查找车位登记停车
    @RequestMapping(value = "/carIn",method = RequestMethod.POST)
    public String dealWithCarIn(@Valid String carId ,Model model) throws Exception{
           Map map = carMapper.findCarInformationByCarId(carId);
           if(map==null||map.size()==0){
               //未登记车辆进行登记
               CarLogin carLogin = new CarLogin();
               carLogin.setCarStatus("02");
               carLogin.setCarType("06");
               carLogin.setPhoneId("999999");
               carLogin.setUserCarId(carId);
               carLogin.setUserName("临时停车");
               carInformation.savCarInformation(carLogin,999);
               map = carMapper.findCarInformationByCarId(carId);
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
        String name = parkings.getUser_name();
        if (userId==999){
            //非注册用户
            return parkingInformation.setCarToParking(model,userId,carId,"02","01","01",carType,name);
        }
        //userId=other,查询parkingInfo..表中是否有其预约
        List<Map<String,Object>> carMap = mapper.findParkingInformationByUserIdAndbiaoshi(userId,1);
        if (carMap==null||carMap.size()==0){
            //非预约的用户
            return parkingInformation.setCarToParking(model,userId,carId,"02","01","01",carType, name);
        }
        //判断是长期租车还是预约租车
        ParkingInformation p = new ParkingInformation();
        ReflectUtil.mapToObject(carMap.get(0),p);
        if (p.getUser_id()==0){
            //转换出错
            log.error("map转pojo出错");
            throw new Exception("map转pojo出错");
        }
//        String pType = p.getPay_type();
//        String useTime = p.getUse_time();
//        if ("01".equals(useTime)&&"01".equals(pType)){
            //预约车辆
            p.setUse_start_time(TimeUtil.getCurrentTimeNow());//时间改
            p.setParking_status("02");//状态改为占用
            p.setPay_type("01");
            p.setUse_time("01");
            p.setUser_car_id(carId);
            p.setCar_type(parkings.getCar_type());
            log.error("停车信息："+p.toString());
           int n = mapper.updateParkingInformation(p);

           if (n>0){
               //登记车辆历史表

               model.addAttribute("result","车辆通过");
               //调用其他的接口，如硬件放行
               return "/success/checkSuccess";


           }
        //修改失败
        model.addAttribute("result","车辆通过失败");
        log.error("停车失败信息："+p.toString());
        return "/error/checkFailed";
//        }
        //长期租用车辆
//        if ("02".equals(pType)&&!"01".equals(useTime)){
//           //判断是否再租期内
//           Timestamp timeNow = TimeUtil.getCurrentTimeNow();
//           Timestamp timeUse = p.getUse_start_time();
//           String use = p.getUse_time();
//            int hourForuse = CarTimeConst.TransForTime(use);
//            int houForUsed = TimeUtil.getHourFromTwoTime(timeNow,timeUse);
//            if (hourForuse>houForUsed){
//                //在租期内放行
//
//                //登记停车记录表
//               p.setUse_start_time(timeNow);
//            parkingInformation.setParkingInfoToHis(p,name);
//                model.addAttribute("result",p.getUse_time());
//                return "success/checkSuccess";
//            }
//            //不在租期内提醒用户续期或继续停车
//
//            //将过期的停车位修改为正常
//            p.setParking_status("01");
//            p.setPay_type("");
//            p.setUse_time("");
//            p.setCar_type("");
//            p.setUser_car_id("");
//            p.setUser_id(0);
//            p.setIs_subscription(0);
//            int i = mapper.updateParkingInformation(p);
//            if (i>0){
//                //修改成功
//                log.error("修改租期到时间的车位信息："+p.toString());
//                model.addAttribute("result","车已经到期");
//            }else {
//                throw new Exception("修改租期到时间的车位信息");
//            }
//            return "error";
//        }

//        return "error";
    }

    //查询用户的预约车辆 TODO 待想好
    @RequestMapping(value = "/getSubscribeCars")
    public String getSubscribeCars(HttpSession httpSession,Model model){
        User u = (User) httpSession.getAttribute("User");
        if (u==null){
            return "login";
        }
        String phoneId = u.getPhone_id();
        int userId = u.getUser_id();
        ParkingInformation[] cars =parkingInformation.getAllCarInfoByUserId(userId);
        List<ParkingInformation> carinfo = new ArrayList<>();
        if (cars!=null||cars.length>0) {
            for (int i = 0; i < cars.length; i++) {
                String status = cars[i].getParking_status();
                if ("04".equals(status)) {
                    carinfo.add(cars[i]);
                }
            }
            model.addAttribute("carList",carinfo);
            if (carinfo.size()==0){
                model.addAttribute("code",1);
            }
            model.addAttribute("code",0);
        }else {
            model.addAttribute("code",1);
        }
        return "user/PcancelSubscribeCar";
    }

    //取消预约
    @RequestMapping(value = "/cancelSubscribeCar",method = RequestMethod.GET)
    public String cancelSubscribe(@Valid String carId,HttpSession httpSession,Model model) throws Exception{
        User u = (User) httpSession.getAttribute("User");
        if (u==null){
            return "login";
        }
//        int userId = u.getUser_id();
        Map map = mapper.findParkingInformationByCarParkingId(carId);
        if (map==null||map.size()==0){
            //车辆非预约状态
            model.addAttribute("result","找不到车辆");
            return "error";
        }
        ParkingInformation information = new ParkingInformation();
        ReflectUtil.mapToObject(map,information);
        if ("04".equals(information.getParking_status())) {
            //修改状态
            information.setParking_status("01");
            information.setUser_id(0);
            information.setUser_car_id("");
            information.setCar_type("");
            information.setIs_subscription(0);
            int i =mapper.updateParkingInformation(information);
            if (i>0){
                log.error("成功取消用户"+information.getUser_id()+"预约状态,");
                return "redirect:/getSubscribeCars";
            }else {
                log.error("取消用户"+information.getUser_id()+"预约状态失败,");
                throw new Exception("取消用户"+information.getUser_id()+"预约状态失败,");
            }
        }
        model.addAttribute("result","车辆非预约状态");
        return "error";
    }


    //展示预约界面
    @RequestMapping(value = "/showSubscribeParking",method = RequestMethod.GET)
    public String getShowSubscribeParkingPage(Model model,@Valid int number, HttpSession session)throws Exception{
        //自己已登记的车辆
        User u = userService.getNowUser(session);
//        String phoneId =u.getPhone_id();
//        CarInformation[] carInformations = carInformation.getAllCarInformationByPhonrId(phoneId);
//        model.addAttribute("carsLength",carInformations.length);
//        model.addAttribute("cars",carInformations);
        //查询所有车库号
        List<Map<String,Object>> list = roomInformationMapper.getAllRoom();
        List<Integer> roomList = new ArrayList<>();
        for (Map<String,Object> map : list){
            roomList.add((int)map.get("car_room_number"));
        }
        model.addAttribute("roomList",roomList);
        //默认的一号车库信息展示
        if (number==0){
            number=1;
        }
        ParkingInformation[] parkingInformations = parkingInformation.getAllCarInfoInRoom(number);
        model.addAttribute("parkings",parkingInformations);
        return "user/showSubscribeParking";
    }

}
