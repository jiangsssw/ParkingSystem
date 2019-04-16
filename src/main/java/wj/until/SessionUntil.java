package wj.until;


import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wj.entity.dataBaseMapping.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUntil {
    private static Logger log = Logger.getLogger(SessionUntil.class);

    public static User getSessionUser(){
       HttpServletRequest request=null;
       User u = null;
        try {
           request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
           u = (User)request.getSession().getAttribute("User");
        }catch (Exception e){
            log.error("获取session实例失败"+e.getMessage());
        }
        return u;
    }
}
