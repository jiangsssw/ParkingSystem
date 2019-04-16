package wj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import wj.entity.dataBaseMapping.User;
import wj.mapper.UserMapper;
import wj.service.interfaces.IUserService;
import wj.until.CarTimeConst;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    public UserMapper getMapper() {
        return mapper;
    }

    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Map find(int id,String phoneId,String password) {
       return  mapper.findUserById(id,phoneId,password);
    }

    @Override
    public int addSome(User user) {
        return  mapper.addUser(user);
    }

    @Override
    public int upDateUser(User user) {
        return  mapper.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return  mapper.deleteUser(id);
    }

    @Override
    public boolean findPeopleByIdOrUserId(int id, String phoneId,String password) {
        if (id<1000&&phoneId.length()<11){
            return false;
        }
        Map map = find(id,phoneId,password);
        if (map==null||map.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public List<Map> findUserByPhoneId(String phoneId) {

        return mapper.findUserByPhoneId(phoneId);
    }

    @Override
    public String judgeManager(Model model, HttpSession httpSession) {
        //判断用户权限
        User user = (User) httpSession.getAttribute("User");
        if (user.getUser_id()==0|| StringUtils.isEmpty(user.getPhone_id())){
            //未登陆
//            model.addAttribute("result","用户未登陆");
            return "NO_LOGIN";
        }
        String userType = user.getUser_type();
        if (CarTimeConst.NO_MANAGE.equals(userType)){
//            model.addAttribute("result","普通用户");
            return "NO_MANAGE";
        }
        if (CarTimeConst.MANAGE.equals(userType)){
//            model.addAttribute("result","管理员");
            return "MANAGE";
        }
//        model.addAttribute("result","用户未登陆,未知原因。。");
        return "NO_LOGIN";
    }

}
