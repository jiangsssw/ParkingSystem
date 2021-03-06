package testForParkingRecHis;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wj.config.Appconfig;
import wj.config.TestConfig;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ReqParkingRecHBean;
import wj.service.impl.ParkingRecHisImpl;
import wj.until.BeanUtils;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, Appconfig.class})
@ActiveProfiles("RecHis")
public class Test1 {
    @Autowired
    private ParkingRecHisImpl recHis;
    @Test
    public void testForRec()throws Exception{

        ReqParkingRecHBean bean = new ReqParkingRecHBean();
        bean.setUserId(10001);
        bean.setStartTime("201903200000");
        bean.setEndTime("201903250000");
        bean.setStartCount(0);
        ParkingRecHis[] parkingRecHis = recHis.getParkingRecHis(bean);
        Map<String,Object> map = new HashMap<>();
        map= BeanUtils.obj2Map(parkingRecHis[0]);
        System.out.println(map);
    }

    @Test
    public void testForCoounts(){
        int count = recHis.getParkingRecHCounts();
        System.out.println("counts:"+count);
    }
}
