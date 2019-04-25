package wj.entity.valueBean;

import java.sql.Timestamp;

public class RespParkingRecHisBean {
    private String carParkingId;
    private int carRoomNumber;
    private int userId;
    private String payType;
    private String parkingStartTime;
    private String parkingEndTime;
    private int parkingTime;
    private String userName;
    private String userCarId;
    private String parkingType;
    private String carType;

    public String getCarParkingId() {
        return carParkingId;
    }

    public void setCarParkingId(String carParkingId) {
        this.carParkingId = carParkingId;
    }

    public int getCarRoomNumber() {
        return carRoomNumber;
    }

    public void setCarRoomNumber(int carRoomNumber) {
        this.carRoomNumber = carRoomNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getParkingStartTime() {
        return parkingStartTime;
    }

    public void setParkingStartTime(String parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public String getParkingEndTime() {
        return parkingEndTime;
    }

    public void setParkingEndTime(String parkingEndTime) {
        this.parkingEndTime = parkingEndTime;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(String userCarId) {
        this.userCarId = userCarId;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
