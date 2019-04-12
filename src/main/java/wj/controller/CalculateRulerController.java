package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wj.entity.dataBaseMapping.CalculateRuler;
import wj.entity.valueBean.CalculateRulerBean;
import wj.mapper.CalculateRulerMapper;
import wj.service.impl.CalculateRulerImpl;
import wj.service.impl.UserServiceImpl;
import wj.until.CarTimeConst;
import wj.until.Resp;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CalculateRulerController {

    @Autowired
    private CalculateRulerImpl calculateService;

    @Autowired
    private CalculateRulerMapper mapper;

    @Autowired
    private UserServiceImpl userService;

    //查询计算规则表的历史
    @RequestMapping(value = "/getCalculateHis",method = RequestMethod.GET)
    public Resp getCalculateHis(@RequestParam("page")int page, Model model, HttpSession session ){
        //验证权限
        if (CarTimeConst.NO_GENERAL.equals(userService.judgeManager(model,session))) {

            if (page == 0) {
                page = 1;
            }
            int count = (page - 1) * 10;
            CalculateRuler[] rulers = calculateService.getAllCalculateInfo(count);
            int allCounts = mapper.getAllCountFromCalculate();
            if (allCounts == 0) {
                return Resp.error(400, "calculate表为空");
            }
            int allPages = (int) Math.ceil(allCounts / 10);
            Map<String, Object> map = new HashMap<>();
            map.put("rulers", allPages);
            map.put("allPages", allPages);
            return Resp.OK(0, map);

        }
        return Resp.error();
    }

    //添加计费规则
    @RequestMapping("addCalculateRuler")
    public String addCalculateRuler(@Valid CalculateRulerBean bean,Model model,HttpSession session){
        //判断用户权限
        if (CarTimeConst.NO_GENERAL.equals(userService.judgeManager(model,session))){
            return calculateService.addCalculate(model,bean);
        }
        return "error";
    }
}
