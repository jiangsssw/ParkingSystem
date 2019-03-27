package wj.service.interfaces;



import org.springframework.ui.Model;
import wj.entity.dataBaseMapping.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface IUserService {
    Map find(int id,String phoneId,String password);

    int addSome(User user);

    int upDateUser(User user);

    int deleteUser(int id);

    boolean findPeopleByIdOrUserId(int id,String phoneId,String password);

    List<Map> findUserByPhoneId(String phoneId);

    String judgeManager(Model model, HttpSession httpSession);

}
