package wj.service.interfaces;



import wj.entity.dataBaseMapping.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    Map find(int id,String phoneId,String password);

    int addSome(User user);

    int upDateUser(User user);

    int deleteUser(int id);

    boolean findPeopleByIdOrUserId(int id,String phoneId,String password);

    List<Map> findUserByPhoneId(String phoneId);


}
