package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wj.entity.dataBaseMapping.Muse;
import wj.entity.dataBaseMapping.User;
import wj.entity.valueBean.Login;
import wj.entity.valueBean.MuseBean;
import wj.service.impl.MuseImpl;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.IUserService;
import wj.until.CheckUtil;
import wj.until.ReflectUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class LoginIndexController {
    @Autowired
    private MuseImpl museService;

    @Autowired
private IUserService service;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
//登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody boolean login(@Valid Login login, RedirectAttributes model, HttpSession httpSession){
        int id=0;
        String phoneId = login.getUserInput();
        if (StringUtils.isEmpty(phoneId)){
            return false;
        }
        if(CheckUtil.isNumeric(phoneId)&&phoneId.length()<11){
            id = Integer.valueOf(phoneId);
        }
        boolean result = service.findPeopleByIdOrUserId(id,phoneId,login.getPassword());
        if (result){
            Map map = service.find(id,phoneId,login.getPassword());
            User u = new User();
            ReflectUtil.mapToObject(map,u);
            httpSession.setAttribute("User",u);
            return true;
        }
    return false;
    }
    //ceshi
    @RequestMapping(value = "/loginFail",method = RequestMethod.GET)
    public String returnFail(){
        return "loginFail";
    }

    @RequestMapping(value = "/loginSuccess",method = RequestMethod.GET)
    public String returnsuccess(){
        return "loginSuccess";
    }
    @RequestMapping(value = "/homeIndex",method = RequestMethod.GET)
    public String returnhomeIndex(Model model,HttpSession session)throws Exception{
        //获取当前用户
        MuseBean bean = new MuseBean();
        User u = userService.getNowUser(session);
        if ("01".equals(u.getUser_type())){
            bean.setRole(2);
            model.addAttribute("type","普通用户");
        }else if ("02".equals(u.getUser_type())){
            bean.setRole(1);
            model.addAttribute("type","管理员用户");
        }
        model.addAttribute("userId",u.getUser_id());
        model.addAttribute("name",u.getUser_name());
        List<Muse> mesuList = museService.getAllByBean(bean);
        model.addAttribute("museList",mesuList);
        return "homeIndex";
    }

    //注销
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public String dealLoginOut(HttpSession session,Model model)throws Exception{
        MuseBean bean = new MuseBean();
        User u = userService.getNowUser(session);
        if (u!=null){
            session.removeAttribute("User");
        }
        return "login";
    }
}
