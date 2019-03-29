package wj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ParkingRecHBean;
import wj.service.impl.ParkingRecHisImpl;
import wj.service.impl.UserServiceImpl;
import wj.until.CarTimeConst;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarParkingRecHisController {

    @Autowired
    private ParkingRecHisImpl recHisService;

    @Autowired
    private UserServiceImpl userService;

    //查询历史停车信息
@RequestMapping(value = "/getAllParkingRecHisInfo",method = RequestMethod.POST)
    public String getAllParkingRecHisInfo(@Valid ParkingRecHBean bean, Model model, HttpSession session){
        if (!CarTimeConst.UNUSUALLY.equals(userService.judgeManager(model,session))){
            //必要参数校验
            if (!StringUtils.isEmpty(bean.getPhoneId())){
                int a = bean.getStartCount();
                int b = (a-1)*10;
                if (b<0){
                    b=0;
                }
                bean.setStartCount(b);
                ParkingRecHis[] recHiss = recHisService.getParkingRecHis(bean);
                Map<String,Object> map = new HashMap<>();
                int counts = recHisService.getParkingRecHCounts();
                int num = (int)Math.ceil(counts/10);
                map.put("recHis",recHiss);
                map.put("counts",num);
                model.addAttribute("resultMap",map);
                return "showAllParkingHis";
            }
            model.addAttribute("result","参数有误");
            return "error";
        }
        return "error";
    }
}
