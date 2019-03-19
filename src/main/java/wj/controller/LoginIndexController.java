package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wj.entity.dataBaseMapping.User;
import wj.entity.valueBean.Login;
import wj.service.interfaces.IUserService;
import wj.until.CheckUtil;
import wj.until.ReflectUtil;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class LoginIndexController {
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

}
