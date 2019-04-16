package wj.intercept;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.apache.log4j.Logger;
import wj.entity.dataBaseMapping.User;
import wj.until.SessionUntil;
import wj.until.SystemUser;

import java.lang.reflect.Method;

public class SystemUserCheckInterCept implements MethodInterceptor{
    private static Logger log = Logger.getLogger(SystemUserCheckInterCept.class);
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        if (method.isAnnotationPresent(SystemUser.class)){
            User user = SessionUntil.getSessionUser();
            if (user==null){
                return null;
            }
            if ("02".equals(user.getUser_type())){
                return methodInvocation.proceed();
            }else {
                log.error("用户："+user.getUser_name()+"无权限");
                return null;
            }

        }else {
            return methodInvocation.proceed();
        }

    }
}
