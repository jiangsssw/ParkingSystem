package wj.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.User;
@Repository
public interface CarRoomInformationMapper {
    //添加车位信息
    @Insert("insert into car_room_information values(#{carRoom.car_room_number},#{carRoom.car_parking_num},#{carRoom.EXT1},#{carRoom.EXT2},#{carRoom.EXT3})")
    @Options(keyProperty="carRoom.car_room_number",useGeneratedKeys=true)
    public int addCarRoomInformation(@Param("carRoom") wj.entity.dataBaseMapping.CarRoomInformation carRoom);

    //删除用户
    @Delete("delete from car_room_information where car_room_number=#{id}")
    public int deleteRoomInformation(int id);

}
