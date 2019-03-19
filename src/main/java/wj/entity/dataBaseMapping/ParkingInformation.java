package wj.entity.dataBaseMapping;

import wj.until.TimeUtil;

import java.sql.Date;
import java.sql.Timestamp;

public class ParkingInformation {
    private String car_parking_id;
    private int car_room_number;
    private int user_id;
    private String parking_status;
    private String pay_type;
    private Timestamp use_start_time;
    private String use_time;
    private int count_money;
    private String user_car_id;
    private String car_type;

    @Override
    public String toString() {
        return "ParkingInformation{" +
                "car_parking_id='" + car_parking_id + '\'' +
                ", car_room_number=" + car_room_number +
                ", user_id=" + user_id +
                ", parking_status='" + parking_status + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", use_start_time=" + TimeUtil.timeFormat(use_start_time) +
                ", use_time='" + use_time + '\'' +
                ", count_money=" + count_money +
                ", user_car_id='" + user_car_id + '\'' +
                ", car_type='" + car_type + '\'' +
                '}';
    }

    public String getCar_parking_id() {
        return car_parking_id;
    }

    public void setCar_parking_id(String car_parking_id) {
        this.car_parking_id = car_parking_id;
    }

    public int getCar_room_number() {
        return car_room_number;
    }

    public void setCar_room_number(int car_room_number) {
        this.car_room_number = car_room_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getParking_status() {
        return parking_status;
    }

    public void setParking_status(String parking_status) {
        this.parking_status = parking_status;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public Timestamp getUse_start_time() {
        return use_start_time;
    }

    public void setUse_start_time(Timestamp use_start_time) {
        this.use_start_time = use_start_time;
    }

    public String getUse_time() {
        return use_time;
    }

    public void setUse_time(String use_time) {
        this.use_time = use_time;
    }

    public int getCount_money() {
        return count_money;
    }

    public void setCount_money(int count_money) {
        this.count_money = count_money;
    }

    public String getUser_car_id() {
        return user_car_id;
    }

    public void setUser_car_id(String user_car_id) {
        this.user_car_id = user_car_id;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }
}
