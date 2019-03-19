package wj.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.User;


import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    //通过用户id查找记录
    @Select("select * from user_relationship where (user_id=#{id} or phone_id=#{phoneId}) and password = #{password}")
    public Map findUserById(@Param("id") int id,@Param("phoneId") String phoneId,@Param("password")String password);

    //通过用户手机号查找记录
    @Select("select * from user_relationship where phone_id=#{phoneId}")
    public List<Map> findUserByPhoneId(String phoneId);

    //增加用户
    @Insert("insert into user_relationship values(#{user.user_id},#{user.password},#{user.phone_id},#{user.user_type},#{user.user_address},#{user.remark},#{user.user_name},#{user.email_address},#{user.register_time})")
    @Options(keyProperty="user.user_id",useGeneratedKeys=true)
    public int addUser(@Param("user") User user);

    //修改用户
    @Update("update user_relationship set password=#{user.password}, phone_id=#{user.phone_id},user_type=#{user.user_type},user_address=#{user.user_address},remark=#{user.remark},user_name=#{user.user_name},email_address=#{user.email_address},register_time=#{user.register_time} where user_id=#{user.user_id}")
    public int updateUser(@Param("user") User user);

    //删除用户
    @Delete("delete from user_relationship where user_id=#{id}")
    public int deleteUser(int id);
}
