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
import wj.entity.valueBean.CarLogin;
import wj.mapper.UserMapper;
import wj.service.interfaces.ICarInformation;
import wj.until.Resp;

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
//登记车辆
    @RequestMapping(value = "/CarInformationLogin",method = RequestMethod.POST)
     String loginCarInformation(@Valid CarLogin carLogin, Model model, Errors errors){
        int userId;
        if (errors.hasErrors()){
            System.out.println(errors.getAllErrors().toString());
        }
        //判断是否临时登记用户
        List<Map> map = mapper.findUserByPhoneId(carLogin.getPhoneId());
        if (map==null||map.size()==0){
           userId=999;
        }else {
            userId = (int)map.get(0).get("user_id");
        }
        boolean login = carInformation.savCarInformation(carLogin,userId);
        if (login){
            //登记成功，查询并展示登记信息页面展示
            List<CarInformation> carList = new ArrayList<>();
            CarInformation[] cars = carInformation.getAllCarInformationByPhonrId(carLogin.getPhoneId());
            for (CarInformation car: cars
                 ) {
                carList.add(car);
            }
            Map<String,Object> map1 = new HashMap<>();
            map1.put("cars",carList);
            model.addAttribute("result",Resp.OK(0,map1));
        }else{
            model.addAttribute("result", Resp.error("未登录"));
        }
        return "showCarInformation";
    }

    @RequestMapping("/getCheckInCar")
    public String getCheckInCar(){
        return "checkInCar";
    }

}
