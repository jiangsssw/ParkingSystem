package wj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.CalculateRuler;
import wj.entity.dataBaseMapping.CarInformation;

import java.util.Map;

@Repository
public interface CalculateRulerMapper {

    //获取最新的计费规则
    @Select("select * from calculate_ruler order by rule_id DESC limit 1")
    Map<String,Object> getCalculateRuler();

    //添加计费规则
    @Insert("insert into calculate_ruler values(#{car.rule_id},#{car.hour_mount},#{car.day_mount},#{car.week_mount},#{car.month_mount},#{car.year_mount},#{car.creat_time},#{car.modefiy_peple},#{car.modefiy_id})")
    public int addCarInformation(@Param("car") CalculateRuler car);

}
