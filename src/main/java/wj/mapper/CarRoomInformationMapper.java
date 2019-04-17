package wj.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.User;

import java.util.List;
import java.util.Map;

@Repository
public interface CarRoomInformationMapper {
    //添加车位信息
    @Insert("insert into car_room_information values(#{carRoom.car_room_number},#{carRoom.car_parking_num},#{carRoom.EXT1},#{carRoom.EXT2},#{carRoom.EXT3})")
    @Options(keyProperty="carRoom.car_room_number",useGeneratedKeys=true)
    public int addCarRoomInformation(@Param("carRoom") wj.entity.dataBaseMapping.CarRoomInformation carRoom);

    //删除用户
    @Delete("delete from car_room_information where car_room_number=#{id}")
    public int deleteRoomInformation(int id);

    @Select("select * from car_room_information")
    public List<Map<String,Object>> getAllRoom();

    //查询所有的roomId
    @Select("select car_room_number  from car_room_information")
    public List<Integer> getAllRoomId();
}
