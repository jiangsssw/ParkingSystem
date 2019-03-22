package wj.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CalculateRulerMapper {

    //获取最新的计费规则
    @Select("select * from calculate_ruler order by rule_id DESC limit 1")
    Map<String,Object> getCalculateRuler();
}
