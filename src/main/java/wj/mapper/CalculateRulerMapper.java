package wj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.CalculateRuler;
import wj.entity.dataBaseMapping.CarInformation;

import java.util.List;
import java.util.Map;

@Repository
public interface CalculateRulerMapper {

    //获取最新的计费规则
    @Select("select * from calculate_ruler order by rule_id DESC limit 1")
    Map<String,Object> getCalculateRuler();

    //添加计费规则
    @Insert("insert into calculate_ruler values(#{car.rule_id},#{car.hour_mount},#{car.day_mount},#{car.week_mount},#{car.month_mount},#{car.year_mount},#{car.creat_time},#{car.modefiy_peple},#{car.modefiy_id})")
    @Options(keyProperty="car.rule_id",useGeneratedKeys=true)
    public int addCarInformation(@Param("car") CalculateRuler car);

    //查询历史的计费规则
    @Select("select * from calculate_ruler limit #{count},10")
    public List<Map<String,Object>> getAllCalculateInfo(int count);

    //获取数据库总记录条数
    @Select("select count(*) from calculate_ruler")
    public int getAllCountFromCalculate();
}
