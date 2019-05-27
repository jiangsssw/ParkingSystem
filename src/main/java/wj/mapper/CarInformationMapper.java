package wj.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.User;

import java.util.List;
import java.util.Map;

@Repository
public interface CarInformationMapper {

    //通过车牌号查找记录
    @Select("select * from car_information where user_car_id=#{user_car_id}")
    public Map findCarInformationByCarId(String user_car_id);


    //增加车辆信息
    @Insert("insert into car_information values(#{car.user_id},#{car.user_name},#{car.user_car_id},#{car.phone_id},#{car.car_status},#{car.create_time},#{car.car_type})")
    @Options(keyProperty="user.user_id",useGeneratedKeys=true)
    public int addCarInformation(@Param("car") CarInformation car);

    //通过用户手机号查找记录
    @Select("select * from car_information where phone_id=#{phoneId}")
    public List<Map> findCarInformationByPhoneId(String phoneId);

    //通过用户ID查找记录
    @Select("select * from car_information where user_id=#{userId}")
    public List<Map> findCarInformationUserId(int userId);
    //删除车辆信息
    @Delete("delete from car_information where user_car_id=#{carId}")
    public int deleteCarInformation(String carId);

    //修改用户
    @Update("update car_information set user_id=#{car.user_id},phone_id=#{car.phone_id},user_name=#{car.user_name},create_time=#{car.create_time},car_status=#{car.car_status},car_type=#{car.car_type} where where user_car_id=#{car.user_car_id}")
    public int updateCarInformation(@Param("car") CarInformation car);
}
