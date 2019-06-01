package wj.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.mapper.CarInformationMapper;
import wj.mapper.ParkingInformationMapper;
import wj.service.interfaces.IParkingInformation;
import wj.until.ReflectUtil;
import wj.until.SystemUser;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class OutCarController {
    private static Logger log = Logger.getLogger(OutCarController.class);

    @Autowired
    private CarInformationMapper carMapper;

    @Autowired
    private ParkingInformationMapper mapper;
    @Autowired
    private IParkingInformation parkingInformation;

    //车辆出库
    /*
    * 查找car 的登记信息，未找到返回 非法车辆
    * 查找car 用carId去停车信息表找 未找到返回非法车辆，
    * 【找到 进入收费方法算去收费返回金额
    * */
    @RequestMapping(value = "/outOfCarFromParking",method = RequestMethod.POST)
public String outOfCarFromParking(@Valid String carId, Model model) throws Exception{
        log.error("正在处理车辆:"+carId);
        Map map = carMapper.findCarInformationByCarId(carId);
        String name = (String)map.get("user_name");
        if(map==null||map.size()==0){
            //未登记车辆进行登记
            model.addAttribute("result","非法车辆");
            return "error";
        }
        CarInformation carInformation = new CarInformation();
        ReflectUtil.mapToObject(map,carInformation);
        Map carMap = mapper.findParkingInformationByCarId(carId);
        if (carMap==null||carMap.size()==0){
            //
            model.addAttribute("result","非法车辆");
            return "error";
        }
        ParkingInformation parking = new ParkingInformation();
        ReflectUtil.mapToObject(carMap,parking);
        if (parking.getUser_car_id()!=null){
            return parkingInformation.dealWithOutCar(model,parking,carInformation);
        }
       model.addAttribute("result","处理失败");
    return "error";
}

    //管理员管理外出车辆演示界面
    //权限验证
    @SystemUser
    @RequestMapping(value = "/outOfCarFromParking",method = RequestMethod.GET)
    public String getutOfCarFromParkingPage(){

    return "systemUser/outOfCarFromParking";
    }

}
