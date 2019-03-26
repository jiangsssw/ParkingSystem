package wj.entity.dataBaseMapping;

import wj.until.TimeUtil;

import java.sql.Timestamp;

public class CarUserRec {
    /**
     * */
    private String car_parking_id;
    private int car_room_number;
    private int user_id;
    private String parking_status;
    private String pay_type;
    private Timestamp use_start_time;
    private String use_time;
    private int count_money;
    private Timestamp use_end_time;
    private String user_car_id;
    private int parking_time;  //实际停车时间
    private String phone_id;


    @Override
    public String toString() {
        return "CarUserRec{" +
                "car_parking_id='" + car_parking_id + '\'' +
                ", car_room_number=" + car_room_number +
                ", user_id=" + user_id +
                ", parking_status='" + parking_status + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", use_start_time=" + use_start_time +
                ", use_time='" + use_time + '\'' +
                ", count_money=" + count_money +
                ", use_end_time=" + use_end_time +
                ", user_car_id='" + user_car_id + '\'' +
                ", parking_time=" + parking_time +
                ", phone_id='" + phone_id + '\'' +
                '}';
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
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

    public Timestamp getUse_end_time() {
        return use_end_time;
    }

    public void setUse_end_time(Timestamp use_end_time) {
        this.use_end_time = use_end_time;
    }

    public String getUser_car_id() {
        return user_car_id;
    }

    public void setUser_car_id(String user_car_id) {
        this.user_car_id = user_car_id;
    }

    public int getParking_time() {
        return parking_time;
    }

    public void setParking_time(int parking_time) {
        this.parking_time = parking_time;
    }
}
