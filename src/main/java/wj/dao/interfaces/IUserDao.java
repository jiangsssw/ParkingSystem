package wj.dao.interfaces;


import wj.entity.dataBaseMapping.User;

public interface IUserDao {
    User findUserById(int id);
}
