package wj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wj.entity.dataBaseMapping.User;
import wj.entity.valueBean.Register;
import wj.mapper.UserMapper;
import wj.service.interfaces.IUserService;
import wj.until.Resp;
import wj.until.TimeUtil;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {
    @Autowired
    private IUserService service;
    @Autowired
    private UserMapper mapper;
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String getRegister(){
        return "register";
    }

    //注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Resp getRegister(@Valid Register register, RedirectAttributes model, Errors errors){
        if (errors.hasErrors()){
            return Resp.error("未知原因");
        }
        Map<String,String> result = new HashMap<>();

        String phoneId = register.getPhoneId();
        String emailAddress = register.getEmailAddress();
        String address = register.getAddress();
        String password = register.getPassword();
        String username = register.getUsername();
        if (password.length()<6){
            result.put("reason","密码不符合要求，请重新输入");
            return Resp.error("密码不符合要求，请重新输入");
        }
        List<Map> isExist = mapper.findUserByPhoneId(phoneId);
        if (isExist==null||isExist.size()==0){
            //用户不存在可以注册
            User u = new User();
            u.setPhone_id(phoneId);
            u.setUser_name(username);
            u.setPassword(password);
            u.setEmail_address(emailAddress);
            u.setUser_address(address);
            u.setRegister_time(TimeUtil.getCurrentTimeNow());
            u.setUser_type("01");
            service.addSome(u);
            List<Map> collection = mapper.findUserByPhoneId(phoneId);
            String name = u.getUser_name();
            int userIdNew = (int) collection.get(0).get("user_id");
            return Resp.OK("注册成功!")
                    .put("userId",userIdNew)
                    .put("userName",name);
        }else {
            return Resp.error("该手机号已经注册过");
        }

    }
}

