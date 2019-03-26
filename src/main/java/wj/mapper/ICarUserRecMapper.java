package wj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.valueBean.UserRecBean;
import wj.mapper.dySql.DynamicSql;

import java.util.List;
import java.util.Map;

@Repository
public interface ICarUserRecMapper {
    //查询用户消费历史
    @SelectProvider(type = DynamicSql.class,method = "dynamicUserRecSql")
    List<Map<String,Object>> getAllUserRec(UserRecBean bean);

    //查询记录总条数
    @Select("select count(*) from car_use_rec")
    int getCountsFromUserRec();

    //插入用户消费历史
    @Insert("insert into car_use_rec values(#{rec.car_parking_id},#{rec.car_room_number}" +
            ",#{rec.user_id}" +
            ",#{rec.parking_status}" +
            ",#{rec.pay_type}" +
            ",#{rec.use_start_time}" +
            ",#{rec.use_time}" +
            ",#{rec.count_money}" +
            ",#{rec.use_end_time}" +
            ",#{rec.user_car_id}" +
            ",#{rec.parking_time}" +
            ",#{rec.phone_id}" +
            ")")
    int addUserRecInfo(@Param("rec") CarUserRec rec);
}
