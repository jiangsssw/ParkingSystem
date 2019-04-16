package wj.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wj.service.impl.CarInformationImpl;
import wj.service.impl.UserServiceImpl;
import wj.until.CarTimeConst;
import wj.until.Resp;
import wj.until.SystemUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CarParkingRoomController {
    private static Logger log = Logger.getLogger(CarParkingRoomController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CarInformationImpl carService;
    //添加车库
    @RequestMapping(value = "/addCarRoom",method = RequestMethod.POST)
    @ResponseBody
    Resp addParkingCarRoom(@Valid int roomId, @Valid int parkingNum, @Valid String remark, HttpSession session, Model model){

           int i =  carService.addCarRoom(roomId,parkingNum,remark);
           if (i>0){
               log.error("添加成功信息：");
               return Resp.OK("添加成功");
           }else {
               log.error("添加失败，请关注。");
               return  Resp.error("未知原因");
           }
    }

    @RequestMapping(value = "/getCarParking",method = RequestMethod.GET)
    public String getCarParking(){
        return "carParking";
    }
}
