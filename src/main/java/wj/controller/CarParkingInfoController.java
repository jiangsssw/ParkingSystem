package wj.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.entity.dataBaseMapping.User;
import wj.entity.valueBean.ParkingInfoBean;
import wj.mapper.ParkingInformationMapper;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.IParkingInformation;
import wj.until.CarTimeConst;
import wj.until.ReflectUtil;
import wj.until.Resp;
import wj.until.SystemUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class CarParkingInfoController {
    private static Logger log = Logger.getLogger(CarParkingInfoController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private IParkingInformation parkingInfoService;

    @Autowired
    private ParkingInformationMapper mapper;

    //权限验证
    @SystemUser
    //添加车位信息
    @RequestMapping(value = "/addParkingInformation",method = RequestMethod.POST)
    @ResponseBody
    public Resp addParkingInformation(@Valid String roomId, @Valid String parkingId,@Valid String status, Model model, HttpSession session){
        //管理员权限验证
            ParkingInformation information = new ParkingInformation();
            information.setCar_room_number(Integer.valueOf(roomId));
            information.setCar_parking_id(parkingId);
            information.setParking_status(status);

//                model.addAttribute("result", Resp.OK("添加成功"));
                return parkingInfoService.addParkingInfo(information);

//            model.addAttribute("result",Resp.error("添加失败，请检查参数"));



    }

    //对车位信息状态的修改
    //权限验证
    @SystemUser
    @RequestMapping(value = "/modifyParkingInfo",method = RequestMethod.POST)
    @ResponseBody
    public Resp modifyParkingInfo(@Valid String parkingId, @Valid String status,@Valid int subscriptionStatus, HttpSession session,Model model){
        //参数验证
        if (StringUtils.isEmpty(parkingId)||StringUtils.isEmpty(status)){
//            model.addAttribute("result","参数校验失败");
            return Resp.error("参数校验失败");
        }

        //管理员验证
            //查出这个车位信息
            Map map = mapper.findParkingInformationByCarParkingId(parkingId);
            if (map==null||map.size()==0){
//                model.addAttribute("result","没找到这个车位");
                log.error("修改parkingId-->"+parkingId+"失败，原因：没有找到对应的数据 ");
                return Resp.error("修改失败。");
            }
            ParkingInformation information = new ParkingInformation();
            ReflectUtil.mapToObject(map,information);
            information.setParking_status(status);
            information.setIs_subscription(subscriptionStatus);
            //修改车位转态信息
            int i = mapper.updateParkingInformation(information);
            if (i>0){
                log.error("修改后的车位信息："+information.toString());
                return Resp.OK("修改成功。。");
            }else {
                log.error("修改失败");
//                model.addAttribute("result","修改信息失败");
                return Resp.error("修改失败，，");
            }


//        model.addAttribute("result","权限不够");
//        return "";
    }

    //权限验证
    @SystemUser
    //删除车位信息
    @RequestMapping(value = "/deleteParkingInfo",method = RequestMethod.POST)
    @ResponseBody
    public Resp deleteParkingInfo(@Valid String parkingId,Model model,HttpSession httpSession){
        //参数验证
        if (StringUtils.isEmpty(parkingId)){
            model.addAttribute("result","校验参数失败");
            return Resp.error("参数校验失败");
        }
        //权限验证
            //查出车位信息
            Map map = mapper.findParkingInformationByCarParkingId(parkingId);
           int i =  mapper.deleteParkingInformation(parkingId);
           if (i>0){
               log.error("操作人员："+
               "删除车位信息"+map.toString());
//               model.addAttribute("result","删除成功");
               return Resp.OK("删除成功");
           }else {
//               model.addAttribute("result","删除失败");
               return Resp.error("删除失败");
           }
//        model.addAttribute("result","权限失败");
//        return "error";
    }

    //加权限验证
    @SystemUser
    //获取某个车库下的所有车位信息
    @RequestMapping(value = "/getParkingsOfCarRoom")
    public String getParkingsOfCarRoom(@Valid int roomId,@Valid String parkingId, Model model){
        //验证参数，
        List<ParkingInfoBean> list = parkingInfoService.getAllCarByRoomIdAndParkingId(roomId, parkingId);
        model.addAttribute("list",list);
        return "/carInfo/ParkingsOfCarRoom";
    }
}
