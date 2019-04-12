package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
//登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody boolean login(@Valid Login login, RedirectAttributes model, HttpSession httpSession, Errors errors){
        if (errors.hasErrors()){
            System.out.println(errors.getAllErrors().toString());

        }
        int id=0;
        String phoneId = login.getUserInput();
        if(CheckUtil.isNumeric(phoneId)){
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
    public String returnhomeIndex(Model model){
        MuseBean bean = new MuseBean();
        List<Muse> mesuList = museService.getAllByBean(bean);
        model.addAttribute("museList",mesuList);
        return "homeIndex";
    }
    @RequestMapping(value = "/returnManageC",method = RequestMethod.GET)
    public String returnManageC(){
        return "manageCalculater";
    }
}
