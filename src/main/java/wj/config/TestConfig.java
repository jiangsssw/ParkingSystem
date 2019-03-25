package wj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import wj.entity.dataBaseMapping.User;
import wj.mapper.UserMapper;
import wj.service.impl.ParkingInformationImpl;
import wj.service.impl.ParkingRecHisImpl;
import wj.service.impl.UserServiceImpl;

@Configuration
public class TestConfig {
    @Bean // 声明当前方法的返回值是一个bean
    @Profile("dev")
    public UserServiceImpl devTestBean() {
        return new UserServiceImpl();
    }
    @Bean
    public User getUserResultMap(){
        return new User();
    }

    @Bean
    @Profile("test")
    public ParkingInformationImpl getParkingInfoBean(){
        return new ParkingInformationImpl();
    }

    @Bean
    @Profile("RecHis")
    public ParkingRecHisImpl getParkingRecHisImplBean(){
        return new ParkingRecHisImpl();
    }
}
