package wj.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.ParkingInformation;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface ParkingInformationMapper {
    //添加停车位信息
    @Insert("insert into parking_information values(#{parking.car_parking_id},#{parking.car_room_number},#{parking.user_id},#{parking.parking_status},#{parking.pay_type},#{parking.use_start_time},#{parking.use_time},#{parking.count_money},#{parking.user_car_id},#{parking.car_type},#{parking.is_subscription})")
    @Options(keyProperty="parking.car_parking_id",useGeneratedKeys=true)
    public int addParkingInformation(@Param("parking") ParkingInformation parking);

    //修改车位信息
    @Update("update parking_information set user_id=#{parking.user_id}," +
            "parking_status=#{parking.parking_status}," +
            "car_room_number=#{parking.car_room_number}," +
            "pay_type=#{parking.pay_type}," +
            "use_start_time=#{parking.use_start_time}," +
            "use_time=#{parking.use_time}, " +
            "count_money=#{parking.count_money}, " +
            "user_car_id=#{parking.user_car_id}, " +
            "car_type=#{parking.car_type}," +
            "is_subscription=#{parking.is_subscription}"+
            " where car_parking_id=#{parking.car_parking_id}")
    public int updateParkingInformation(@Param("parking") ParkingInformation parking);

    //删除车位信息
    @Delete("delete from parking_information where car_parking_id=#{id}")
    public int deleteParkingInformation(String id);

    //删除车位信息
    @Delete("delete from parking_information where car_room_number=#{id}")
    public int deleteParkingInformationByRoomId(int id);

    //查找所有空闲的停车位
    @Select("select * from parking_information where parking_status=#{status}")
    public List<Map> findParkingInformationByStatus(String status);

    //查找某个停车位的状态
    @Select("select * from parking_information where car_parking_id=#{parkingId}")
    public Map findParkingInformationByCarParkingId(String parkingId);

    //通过车牌号查询车库的情况
    @Select("select * from parking_information where user_car_id=#{carId}")
    public Map findParkingInformationByCarId(String carId);

    //查找用户的停车位
    @Select("select * from parking_information where user_id=#{user_id}")
    public List<Map> findParkingInformationByUserId(int user_id);

    //查找某个车库下的停车位信息状态
    @Select("select * from parking_information where car_room_number=#{car_room_number}")
    public List<Map> findParkingInformationByCarRoom(int car_room_number);

    //该车库下车位的数量
    @Select("select count(car_room_number) from parking_information where car_room_number=#{room}")
    public int getCountOfRoom(int room);

    //查询该用户预约的车位
    @Select("select * from parking_information where parking_status='04' and user_id=#{userId} and is_subscription=#{remarkId}")
    public List<Map<String,Object>> findParkingInformationByUserIdAndbiaoshi(@Param("userId")int userId,@Param("remarkId") int remarkId);
}
