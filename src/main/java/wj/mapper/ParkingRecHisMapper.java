package wj.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.ParkingRecHis;
@Repository
public interface ParkingRecHisMapper {
    //添加停车位信息
    @Insert("insert into parking_rec_his values(#{parking.car_parking_id},#{parking.car_room_number},#{parking.user_id},#{parking.pay_type},#{parking.parking_start_time},#{parking.parking_end_time},#{parking.parking_time},#{parking.user_name},#{parking.user_car_id},#{parking.parking_type},#{parking.car_type})")
    @Options(keyProperty="parking.car_parking_id",useGeneratedKeys=true)
    public int addParkingRecHis(@Param("parking") ParkingRecHis parking);
}
