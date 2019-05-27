package wj.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.dataBaseMapping.User;
import wj.entity.valueBean.ReqUserRecBean;
import wj.entity.valueBean.RespUserRecBean;
import wj.mapper.ICarUserRecMapper;
import wj.service.impl.CarUserRecImpl;
import wj.service.impl.UserServiceImpl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CarUserRecController {
    private static Logger log = Logger.getLogger(CarUserRecController.class);

    @Autowired
    private CarUserRecImpl userRecService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ICarUserRecMapper mapper;

    //管理员查询用户历史消费记录
    //权限验证
    @RequestMapping(value = "/getAllUserRecInfo",method = RequestMethod.POST)
    public String getAllUserRecInfo(ReqUserRecBean bean, Model model, HttpSession session){

        //验证参数
        if (StringUtils.isEmpty(bean.getPhoneId())){
            model.addAttribute("result","参数有误");
            return "error";
        }
        String name="";
        try {
            List<Map> map = userService.findUserByPhoneId(bean.getPhoneId());
             name= (String) map.get(0).get("user_name");
        }catch (Exception e){
            log.error("查询手机号出错："+e.getMessage());
            model.addAttribute("result","查询手机号出错："+e.getMessage());
            return "error";
        }

        int a = bean.getStartCount();
        int b = (a-1)*10;
        if (b<0){
            b=0;
        }
        bean.setStartCount(b);
        CarUserRec[] recs = userRecService.getAllUserRecInfo(bean);
        List<RespUserRecBean> list = new ArrayList<>();
        if (recs!=null&&recs.length>0){
            for (CarUserRec rec : recs){
                RespUserRecBean respUserRecBean = userRecService.setUserRecToRespBean(rec);
                respUserRecBean.setUserName(name);
                list.add(respUserRecBean);
            }
        }else {
            return "error";
        }
        int count = mapper.getCountsFromUserRec();
        int allPage = (int) Math.ceil(count/10);
        model.addAttribute("recs",list);
        model.addAttribute("counts",allPage);
        return "systemUser/getAllUserRecInfo";
    }

    //查询用户历史消费记录
    @RequestMapping(value = "/getUserRecInfo",method = RequestMethod.GET)
    public String getPageUserRecInfo(){
        return "user/showUserRecInfo";
    }
    //查询用户历史消费记录
    @RequestMapping(value = "/getUserRecInfo",method = RequestMethod.POST)
    public String getUserRecInfo(@Valid String startTime,@Valid String endTime, Model model, HttpSession session){
        //时间格式转换
        String time1 = startTime.replace("-","").replace(" ","").replace(":","");
        String time2 = endTime.replace("-","").replace(" ","").replace(":","");
        User u = null;
        try {
            u = (User) session.getAttribute("User");
        } catch (Exception e) {
            log.error("用户登录异常：" + e.getMessage());
            return "error";
        }
        if (u==null){
            model.addAttribute("result","用户登录异常!!");
            return "error";
        }
        int userId = u.getUser_id();
        String phoneId = u.getPhone_id();
        ReqUserRecBean bean = new ReqUserRecBean();
        bean.setUserId(userId);
        bean.setPhoneId(phoneId);
        int a = bean.getStartCount();
        int b = (a-1)*10;
        if (b<0){
            b=0;
        }
        bean.setStartCount(b);
        bean.setStartTime(time1);
        bean.setEndTime(time2);
        CarUserRec[] recs = userRecService.getAllUserRecInfo(bean);
        List<RespUserRecBean> list = new ArrayList<>();
        if (recs!=null&&recs.length>0){
            for (CarUserRec rec : recs){
                RespUserRecBean respUserRecBean = userRecService.setUserRecToRespBean(rec);
                list.add(respUserRecBean);
            }
        }else {
            model.addAttribute("recs",list);
            return "user/showUserRecInfo";
        }

        int count = mapper.getCountsFromUserRec();
        int allPage = (int) Math.ceil(count/10);
        model.addAttribute("recs",list);
        model.addAttribute("counts",allPage);
        return "user/showUserRecInfo";
    }

}
