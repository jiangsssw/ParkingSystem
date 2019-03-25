package wj.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ParkingRecHBean;
import wj.mapper.dySql.DynamicSql;

import java.util.List;
import java.util.Map;

@Repository
public interface ParkingRecHisMapper {
    //添加历史停车信息
    @Insert("insert into parking_rec_his values(#{parking.car_parking_id},#{parking.car_room_number},#{parking.user_id},#{parking.pay_type},#{parking.parking_start_time},#{parking.parking_end_time},#{parking.parking_time},#{parking.user_name},#{parking.user_car_id},#{parking.parking_type},#{parking.car_type})")
    @Options(keyProperty="parking.car_parking_id",useGeneratedKeys=true)
    public int addParkingRecHis(@Param("parking") ParkingRecHis parking);

    @Select("select count(*) from parking_rec_his")
    int getCountFromParkingRecH();

    //查询历史停车信息  (分页查询)
    @SelectProvider(type = DynamicSql.class,method = "dynamicParkingRecHSql")
    List<Map<String,Object>> getParkingRecH(ParkingRecHBean bean);
}
