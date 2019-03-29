package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.IParkingInformation;
import wj.until.CarTimeConst;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CarParkingInfoController {

    @Autowired
    private UserServiceImpl userService;

    private IParkingInformation parkingInfoService;
    //添加车位信息
    @RequestMapping(value = "/addParkingInformation",method = RequestMethod.POST)
    public String addParkingInformation(@Valid String roomId, @Valid String parkingId, Model model, HttpSession session){
        //管理员权限验证
        if (CarTimeConst.NO_GENERAL.equals(userService.judgeManager(model,session))){
            ParkingInformation information = new ParkingInformation();
            information.setCar_room_number(Integer.valueOf(roomId));
            information.setCar_parking_id(parkingId);
            information.setParking_status("01");
            if (parkingInfoService.addParkingInfo(information)){
                model.addAttribute("result","添加成功");
                return "success";
            }
            model.addAttribute("result","添加失败，请检查参数");
            return "error";
        }

        model.addAttribute("result","权限不够");
        return "";
    }

    //对车位信息状态的修改
    @RequestMapping(value = "/modifyParkingInfo",method = RequestMethod.POST)
    public String modifyParkingInfo(@Valid String parkingId, @Valid String status, HttpSession session,Model model){
        //管理员验证
        if (CarTimeConst.NO_GENERAL.equals(userService.judgeManager(model,session))){
            //查出这个车位信息

            //修改车位转态信息


        }

        model.addAttribute("result","权限不够");
        return "";
    }
}
