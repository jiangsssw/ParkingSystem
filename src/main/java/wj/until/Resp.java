package wj.until;

import java.util.HashMap;
import java.util.Map;

public class Resp extends HashMap<String,Object> {
    private static final int SUCCESS_STATE =0;
    private static final int FAILED_STATE = 400;

    public static Resp error(){
        return error("未知错误，请联系管理员");
    }

    public static Resp error(String msg){
        return error(FAILED_STATE,msg);
    }

    public static Resp error(int code,String msg){
        Resp resp = new Resp();
        resp.put("code",code);
        resp.put("msg",msg);
        return resp;
    }

    public static Resp OK(int code, Map<String,Object> map){
        Resp resp = new Resp();
        resp.put("code",code);
        resp.putAll(map);
        return resp;
    }
    public static Resp OK(String msg){
        Resp resp = new Resp();
        resp.put("msg",msg);
        resp.put("code",SUCCESS_STATE);
        return resp;
    }
    public static Resp Ok(){
        return OK("操作成功");
    }
    public static Resp authenticationError() {
        return error(402, "登录认证失败");
    }
    public Resp put(String key,Object o){
        super.put(key,o);
        return this;
    }
}
