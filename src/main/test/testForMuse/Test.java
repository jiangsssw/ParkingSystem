package testForMuse;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wj.config.Appconfig;
import wj.config.TestConfig;
import wj.entity.dataBaseMapping.Muse;
import wj.entity.valueBean.MuseBean;
import wj.service.impl.MuseImpl;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Appconfig.class, TestConfig.class})
@ActiveProfiles("muse")
public class Test {

    @Autowired
    private MuseImpl museService;

    @org.junit.Test
    public void testMuse(){
        MuseBean bean = new MuseBean();
        List<Muse> list = museService.getAllByBean(bean);
        System.out.println("length:"+list.get(0).getMuse_url());
    }
}
