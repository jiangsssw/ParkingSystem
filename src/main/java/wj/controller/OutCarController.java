package wj.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.mapper.CarInformationMapper;
import wj.mapper.ParkingInformationMapper;
import wj.service.interfaces.IParkingInformation;
import wj.until.ReflectUtil;

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
public String outOfCarFromParking(@PathVariable("carId") String carId, Model model){
        log.error("正在处理车辆:"+carId);
        Map map = carMapper.findCarInformationByCarId(carId);
        if(map==null||map.size()==0){
            //未登记车辆进行登记
            model.addAttribute("result","非法车辆");
            return "error";
        }
        Map carMap = mapper.findParkingInformationByCarId(carId);
        if (carMap==null||carMap.size()==0){
            //
            model.addAttribute("result","非法车辆");
            return "error";
        }
        ParkingInformation parking = new ParkingInformation();
        ReflectUtil.mapToObject(carMap,parking);
        if (parking.getUser_car_id()!=null){
            return parkingInformation.dealWithOutCar(model,parking);
        }
       model.addAttribute("result","处理失败");
    return "error";
}

}
