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
import wj.mapper.ParkingInformationMapper;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.IParkingInformation;
import wj.until.CarTimeConst;
import wj.until.ReflectUtil;
import wj.until.Resp;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    //添加车位信息
    @RequestMapping(value = "/addParkingInformation",method = RequestMethod.POST)
    @ResponseBody
    public Resp addParkingInformation(@Valid String roomId, @Valid String parkingId,@Valid String status, Model model, HttpSession session){
        //管理员权限验证
            ParkingInformation information = new ParkingInformation();
            information.setCar_room_number(Integer.valueOf(roomId));
            information.setCar_parking_id(parkingId);
            information.setParking_status(status);
            if (parkingInfoService.addParkingInfo(information)){
//                model.addAttribute("result", Resp.OK("添加成功"));
                return Resp.OK("添加成功！！");
            }
//            model.addAttribute("result",Resp.error("添加失败，请检查参数"));
            return Resp.error("添加失败");


    }

    //对车位信息状态的修改
    @RequestMapping(value = "/modifyParkingInfo",method = RequestMethod.POST)
    public String modifyParkingInfo(@Valid String parkingId, @Valid String status, HttpSession session,Model model){
        //参数验证
        if (StringUtils.isEmpty(parkingId)||StringUtils.isEmpty(status)){
            model.addAttribute("result","参数校验失败");
            return "showCarInformation";
        }

        //管理员验证
        if (CarTimeConst.NO_GENERAL.equals(userService.judgeManager(model,session))){
            //查出这个车位信息
            Map map = mapper.findParkingInformationByCarParkingId(parkingId);
            if (map==null||map.size()==0){
                model.addAttribute("result","没找到这个车位");
                return "error";
            }
            ParkingInformation information = new ParkingInformation();
            ReflectUtil.mapToObject(map,information);
            information.setParking_status(status);
            //修改车位转态信息
            int i = mapper.updateParkingInformation(information);
            if (i>0){
                log.error("修改后的车位信息："+information.toString());
                return "successModifyParkingInfo";
            }else {
                log.error("修改失败");
                model.addAttribute("result","修改信息失败");
                return "error";
            }
        }

        model.addAttribute("result","权限不够");
        return "";
    }

    //删除车位信息
    @RequestMapping(value = "/deleteParkingInfo",method = RequestMethod.POST)
    public String deleteParkingInfo(@Valid String parkingId,Model model,HttpSession httpSession){
        //参数验证
        if (StringUtils.isEmpty(parkingId)){
            model.addAttribute("result","校验参数失败");
            return "error";
        }
        //权限验证
        if (CarTimeConst.NO_GENERAL.equals(userService.judgeManager(model,httpSession))){
            //查出车位信息
            Map map = mapper.findParkingInformationByCarParkingId(parkingId);
           int i =  mapper.deleteParkingInformation(parkingId);
           if (i>0){
               log.error("操作人员："+((User)httpSession.getAttribute("User")).getUser_id()+
               "删除车位信息"+map.toString());
               model.addAttribute("result","删除成功");
               return "success";
           }else {
               model.addAttribute("result","删除失败");
               return "error";
           }
        }
        model.addAttribute("result","权限失败");
        return "error";
    }
}
