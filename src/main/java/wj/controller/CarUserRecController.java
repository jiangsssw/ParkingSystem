package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.valueBean.UserRecBean;
import wj.mapper.ICarUserRecMapper;
import wj.service.impl.CarUserRecImpl;
import wj.service.impl.UserServiceImpl;
import wj.until.CarTimeConst;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarUserRecController {
    @Autowired
    private CarUserRecImpl userRecService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ICarUserRecMapper mapper;

    @RequestMapping(value = "/getAllUserRecInfo",method = RequestMethod.POST)
    public String getAllUserRecInfo(UserRecBean bean, Model model, HttpSession session){
        //验证权限
        if (CarTimeConst.UNUSUALLY.equals(userService.judgeManager(model,session))){
            //未登录
            return "error";
        }
        //验证参数
        if (StringUtils.isEmpty(bean.getPhoneId())){
            model.addAttribute("result","参数有误");
            return "error";
        }
        int a = bean.getStartCount();
        int b = (a-1)*10;
        if (b<0){
            b=0;
        }
        bean.setStartCount(b);
        CarUserRec[] recs = userRecService.getAllUserRecInfo(bean);

        int count = mapper.getCountsFromUserRec();
        int allPage = (int) Math.ceil(count/10);
        Map<String,Object> map = new HashMap<>();
        map.put("recs",recs);
        map.put("counts",allPage);
        model.addAttribute("resultMap",map);

        return "getAllUserRecInfo";
    }
}
