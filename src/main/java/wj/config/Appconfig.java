package wj.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import wj.entity.dataBaseMapping.User;
import wj.mapper.UserMapper;
import wj.service.impl.UserServiceImpl;

@Configuration
public class Appconfig {
    //配置数据源
    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("com.mysql.jdbc.Driver");
        bs.setUrl("jdbc:mysql://localhost:3306/parking_system");
        bs.setUsername("root");
        bs.setPassword("123456");
        return bs;
    }
//配置事物管理器
    @Bean
    public DataSourceTransactionManager transactionManager(BasicDataSource dataSource){
        DataSourceTransactionManager dm = new DataSourceTransactionManager();
        dm.setDataSource(dataSource);
        return dm;
    }

//    //配置支持命名的jdbcTemplate
//    @Bean
//    public NamedParameterJdbcTemplate parameterJdbcTemplate(BasicDataSource dataSource){
//        return new NamedParameterJdbcTemplate(dataSource);
//    }

    //配置myBatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(BasicDataSource dataSource){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("wj.entity");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setUseColumnLabel(true);
        bean.setConfiguration(configuration);
        return bean;
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("wj.mapper");
        return configurer;
    }


}
