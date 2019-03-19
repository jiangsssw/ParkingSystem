package wj.entity.dataBaseMapping;

import wj.until.TimeUtil;

import java.sql.Timestamp;

public class ParkingRecHis {

    /**

     * **/
    private String car_parking_id;
    private int car_room_number;
    private int user_id;
    private String pay_type;
    private Timestamp parking_start_time;
    private Timestamp parking_end_time;
    private int parking_time;
    private String user_name;
    private String user_car_id;
    private String parking_type;
    private String car_type;

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
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

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public Timestamp getParking_start_time() {
        return parking_start_time;
    }

    public void setParking_start_time(Timestamp parking_start_time) {
        this.parking_start_time = parking_start_time;
    }

    public Timestamp getParking_end_time() {
        return parking_end_time;
    }

    public void setParking_end_time(Timestamp parking_end_time) {
        this.parking_end_time = parking_end_time;
    }

    public int getParking_time() {
        return parking_time;
    }

    public void setParking_time(int parking_time) {
        this.parking_time = parking_time;
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

    public String getParking_type() {
        return parking_type;
    }

    public void setParking_type(String parking_type) {
        this.parking_type = parking_type;
    }

    @Override
    public String toString() {
        return "ParkingRecHis{" +
                "car_parking_id='" + car_parking_id + '\'' +
                ", car_room_number=" + car_room_number +
                ", user_id=" + user_id +
                ", pay_type='" + pay_type + '\'' +
                ", parking_start_time=" + TimeUtil.timeFormat(parking_start_time) +
                ", parking_end_time=" + TimeUtil.timeFormat(parking_end_time) +
                ", parking_time=" + parking_time +
                ", user_name='" + user_name + '\'' +
                ", user_car_id='" + user_car_id + '\'' +
                ", parking_type='" + parking_type + '\'' +
                '}';
    }
}
