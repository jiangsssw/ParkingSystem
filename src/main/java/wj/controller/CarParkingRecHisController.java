package wj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ReqParkingRecHBean;
import wj.entity.valueBean.RespParkingRecHisBean;
import wj.service.impl.ParkingRecHisImpl;
import wj.service.impl.UserServiceImpl;
import wj.until.CarTimeConst;
import wj.until.TimeUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarParkingRecHisController {

    @Autowired
    private ParkingRecHisImpl recHisService;

    @Autowired
    private UserServiceImpl userService;

    //权限验证
    //查询历史停车信息
@RequestMapping(value = "/getAllParkingRecHisInfo",method = RequestMethod.POST)
    public String getAllParkingRecHisInfo(@Valid ReqParkingRecHBean bean, Model model, HttpSession session){
            //必要参数校验
            if (bean.getUserId()>0){
                int a = bean.getStartCount();
                int b = (a-1)*10;
                if (b<0){
                    b=0;
                }
                bean.setStartCount(b);
                ParkingRecHis[] recHiss = recHisService.getParkingRecHis(bean);
                List<RespParkingRecHisBean> list = new ArrayList<>();
                if (recHiss!=null&recHiss.length>0) {
                    for (ParkingRecHis his : recHiss){
                        RespParkingRecHisBean respBean = new RespParkingRecHisBean();
                        respBean.setCarParkingId(his.getCar_parking_id());
                        respBean.setCarRoomNumber(his.getCar_room_number());
                        respBean.setCarType(CarTimeConst.transCar_Type(his.getCar_type()));
                        respBean.setParkingEndTime(TimeUtil.timeTrans(his.getParking_end_time()));
                        respBean.setParkingStartTime(TimeUtil.timeTrans(his.getParking_start_time()));
                        respBean.setParkingTime(his.getParking_time());
                        respBean.setParkingType(CarTimeConst.transUse_Time(his.getParking_type()));
                        respBean.setPayType(CarTimeConst.transPay_Type(his.getPay_type()));
                        respBean.setUserCarId(his.getUser_car_id());
                        respBean.setUserId(his.getUser_id());
                        respBean.setUserName(his.getUser_name());
                        list.add(respBean);
                    }
                    Map<String, Object> map = new HashMap<>();
                    int counts = recHisService.getParkingRecHCounts();
                    int num = (int) Math.ceil(counts / 10);
                    model.addAttribute("recHis", list);
                    model.addAttribute("counts", num);
                    return "showAllParkingHis";
                }
            }
            model.addAttribute("result","参数有误");
            return "error";

    }

    //返回展示界面
    @RequestMapping(value = "/getAllParkingRecHisInfo",method = RequestMethod.GET)
    public String showAllParkingHis(){
        return "showAllParkingHis";
    }
}
