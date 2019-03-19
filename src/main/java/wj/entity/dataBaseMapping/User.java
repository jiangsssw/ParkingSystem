package wj.entity.dataBaseMapping;

import org.springframework.context.annotation.Bean;
import wj.until.TimeUtil;

import java.sql.Date;
import java.sql.Timestamp;


public class User {
    private int user_id;
    private String password;
    private String phone_id;
    private String user_type;
    private String user_address;
    private String remark;
    private String user_name;
    private String email_address;
    private Timestamp register_time;

    public Timestamp getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Timestamp register_time) {
        this.register_time = register_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", password='" + password + '\'' +
                ", phone_id='" + phone_id + '\'' +
                ", user_type='" + user_type + '\'' +
                ", user_address='" + user_address + '\'' +
                ", remark='" + remark + '\'' +
                ", user_name='" + user_name + '\'' +
                ", email_address='" + email_address + '\'' +
                ", register_time=" + TimeUtil.timeFormat(register_time)+
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
}
