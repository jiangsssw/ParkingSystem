package wj.entity.dataBaseMapping;

import wj.until.TimeUtil;

import java.sql.Timestamp;

public class CarInformation {

    private int user_id;
    private String user_name;
    private String user_car_id;
    private String phone_id;
    private String car_status;
    private Timestamp create_time;
    private String car_type;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_car_id() {
        return user_car_id;
    }

    public void setUser_car_id(String user_car_id) {
        this.user_car_id = user_car_id;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String getCar_status() {
        return car_status;
    }

    public void setCar_status(String car_status) {
        this.car_status = car_status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    @Override
    public String toString() {
        return "CarInformation{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_car_id='" + user_car_id + '\'' +
                ", phone_id='" + phone_id + '\'' +
                ", car_status='" + car_status + '\'' +
                ", create_time=" + TimeUtil.timeFormat(create_time)+
                ", car_type='" + car_type + '\'' +
                '}';
    }
}
