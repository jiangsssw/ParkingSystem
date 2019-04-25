package wj.controller;

import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wj.entity.dataBaseMapping.CarRoomInformation;
import wj.mapper.CarRoomInformationMapper;
import wj.service.impl.CarInformationImpl;
import wj.service.impl.UserServiceImpl;
import wj.until.Resp;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CarParkingRoomController {
    private static Logger log = Logger.getLogger(CarParkingRoomController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CarInformationImpl carService;

    @Autowired
    private CarRoomInformationMapper roomInformationMapper;

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

    //权限验证
    @RequestMapping(value = "/getAllCarRoom",method = RequestMethod.GET)
    public String getAllCarRoom(Model model){
        //拿到所有的停车位的信息
        List<CarRoomInformation> carList =  carService.getAllCarRoom();
        log.error("/getAllCarRoom 出参为---->"+ JSONArray.toJSONString(carList));
        model.addAttribute("carList",carList);
        return "carInfo/carRoomManage";
    }

    //-->删除数据
    //权限验证

    @ResponseBody
    @RequestMapping(value = "/deleteRoomId",method = RequestMethod.POST)
    public Resp deleteRoomId(@Valid int roomId){
        int i = carService.deleteRoomId(roomId);
        if (i>0){
            return Resp.Ok();
        }
        return Resp.error("操作失败");
    }

    //权限验证
    @RequestMapping(value = "/getCarParking",method = RequestMethod.GET)
    public String getCarParking(Model model){
        //查出所有的roomID
        List<Integer> list = roomInformationMapper.getAllRoomId();
        model.addAttribute("roomList",list);
        return "systemUser/carParking";
    }
}
