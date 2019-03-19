package wj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wj.entity.dataBaseMapping.User;
import wj.mapper.UserMapper;
import wj.service.interfaces.IUserService;

import java.util.List;
import java.util.Map;

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
}
