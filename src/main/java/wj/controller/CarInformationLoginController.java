package wj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.User;
import wj.entity.valueBean.CarLogin;
import wj.mapper.CarInformationMapper;
import wj.mapper.UserMapper;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.ICarInformation;
import wj.until.Resp;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarInformationLoginController {

    @Autowired
    private ICarInformation carInformation;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CarInformationMapper carInformationMapper;
//登记车辆
    @RequestMapping(value = "/CarInformationLogin",method = RequestMethod.POST)
    public String loginCarInformation(@Valid CarLogin carLogin, Model model,HttpSession session, Errors errors)throws Exception{
        int userId;
        if (errors.hasErrors()){
            System.out.println(errors.getAllErrors().toString());
        }
        User u = userService.getNowUser(session);
        userId=u.getUser_id();
        carLogin.setPhoneId(u.getPhone_id());
        boolean login = carInformation.savCarInformation(carLogin,userId);
        if (login){
            //登记成功，查询并展示登记信息页面展示
            CarInformation[] cars = carInformation.getAllCarInformationByUserId(userId);
            model.addAttribute("code",0);
            model.addAttribute("carList",cars);
        }else{
            model.addAttribute("code", 1);
            model.addAttribute("result", "登记失败");
        }
        return "checkInCar";
    }

    @RequestMapping("/getCheckInCar")
    public String getCheckInCar(Model model, HttpSession session)throws Exception{
        User u =userService.getNowUser(session);
        CarInformation[] cars = carInformation.getAllCarInformationByUserId(u.getUser_id());
        model.addAttribute("code",0);
        model.addAttribute("carList",cars);
        return "checkInCar";
    }

    //删除车辆
    @RequestMapping("/deleteCheckInCar")
    public String deleteCarInfo(@Valid String carId ,Model model, HttpSession session)throws Exception{
       int i = carInformationMapper.deleteCarInformation(carId);
       if (i>0){
           model.addAttribute("deleteCode",0);
           model.addAttribute("result","删除成功");
           return "redirect:/getCheckInCar";
       }else {
           model.addAttribute("deleteCode",0);
           model.addAttribute("result","删除失败");
           return "error";
       }

    }
}
