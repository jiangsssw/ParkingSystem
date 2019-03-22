package testForParkingInformation;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wj.config.Appconfig;
import wj.config.TestConfig;
import wj.service.impl.ParkingInformationImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Appconfig.class, TestConfig.class})
@ActiveProfiles("test")
public class testParkingInfo {
    @Autowired //注入bean
    private ParkingInformationImpl parkingInformation;


    @Test
    public void testForParkingInfo()throws Exception{
      double money =  parkingInformation.getMoney(1800);
System.out.println("money:"+money);
    }
}
