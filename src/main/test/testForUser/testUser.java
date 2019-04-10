package testForUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONAwareSerializer;
import com.alibaba.fastjson.serializer.JSONSerializerMap;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wj.config.Appconfig;

import wj.config.TestConfig;
import wj.entity.dataBaseMapping.Muse;
import wj.entity.dataBaseMapping.User;
import wj.mapper.dySql.DynamicSql;
import wj.service.impl.CarInformationImpl;
import wj.service.impl.ParkingInformationImpl;
import wj.service.impl.UserServiceImpl;
import wj.service.interfaces.IUserService;
import wj.until.BeanUtils;
import wj.until.CheckUtil;
import wj.until.TimeUtil;
import wj.until.TypeUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Appconfig.class, TestConfig.class})
@ActiveProfiles("dev")
public class testUser {
    private static Logger log = Logger.getLogger(testUser.class);
    @Autowired //注入bean
   private IUserService service;

    @Test
    public void showSomeTest(){
       boolean relut = service.findPeopleByIdOrUserId(0,"13296570239","");
       System.out.println("结果："+relut);
    }

    @Test
    public void addSomeTest(){
        User se = new User();
        se.setUser_id(10002);
        se.setPassword("12456");
        se.setPhone_id("13296572589");
        se.setUser_name("小明3");
        se.setUser_type("01");
        se.setUser_address("中国武汉东西湖区");
//        int a = service.upDateUser(se);

        System.out.println("数据被更改"+"行");
    }
    @Test
    public void deleteSomeTest(){

        int a = service.deleteUser(10007);
        System.out.println("数据被更改"+a+"行");
    }
    @Test
    public void testOther(){
//        boolean a = CheckUtil.isNumeric("012356X");
//        int b = Integer.valueOf("01236");
//        System.out.println("jieguo"+a);
//        Timestamp timestamp = TimeUtil.getCurrentTimeNow();
//        String time = TimeUtil.timeFormat(timestamp);
//        System.out.println("shijian"+time);
        List<Map> map = service.findUserByPhoneId("13296572589");
        log.error("map"+map.toString());
        System.out.println("map"+map.size());
        String aaa=(String) map.get(1).get("UTF");
if (aaa==null||aaa.length()==0){
    aaa="";
}
        System.out.println("aaa"+aaa.length());
    }
    @Test
    public void testanOther()throws ParseException{
//        Timestamp stamp1 = TimeUtil.getCurrentTimeNow();
//        Date date = new Date();
//        long time = date.getTime()-10000000;
//        Timestamp stamp2 = new Timestamp(time);
//        int hour = TimeUtil.getHourFromTwoTime(stamp1,stamp2);
//        System.out.println("shijian"+hour);

        //测试收费方法

        User se = new User();
//        se.setUser_id(10002);
        se.setPassword("12456");
//        se.setPhone_id("13296572589");
//        se.setUser_name("小明3");
//        se.setUser_type("01");
//        se.setUser_address("中国武汉东西湖区");
        String json = JSON.toJSONString(se);
        JSONObject j = JSON.parseObject(json);
        Map<String,Object> map1 = BeanUtils.obj2Map(se);
        Map<String,Object> map  = (Map)j;
//        map.put("mian","20101");
//        map.put("mian2","20101");
//        map.put("USER",se);
        System.out.println("map"+map.toString());
    }

    @Test
    public void testSome(){
        Muse muse = new Muse();

        DynamicSql sql = new DynamicSql();
        String s = sql.dynamicMuseSql(muse);
        System.out.println(s);

    }
    @Test
    public void testSome1(){
       String a = TypeUtil.subDocToString("81032131.232321");
       System.out.println("a:"+a);
    }
}
