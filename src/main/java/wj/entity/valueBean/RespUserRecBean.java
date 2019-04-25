package wj.entity.valueBean;

import java.sql.Timestamp;

public class RespUserRecBean {
    private String carParkingId;
    private int carRoomNumber;
    private int userId;
    private String parkingStatus;
    private String payType;
    private String useStartTime;
    private String useTime;
    private int countMoney;
    private String useEndTime;
    private String userCarId;
    private int parkingTime;  //实际停车时间
    private String phoneId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RespUserRecBean{" +
                "carParkingId='" + carParkingId + '\'' +
                ", carRoomNumber=" + carRoomNumber +
                ", userId=" + userId +
                ", parkingStatus='" + parkingStatus + '\'' +
                ", payType='" + payType + '\'' +
                ", useStartTime='" + useStartTime + '\'' +
                ", useTime='" + useTime + '\'' +
                ", countMoney=" + countMoney +
                ", useEndTime='" + useEndTime + '\'' +
                ", userCarId='" + userCarId + '\'' +
                ", parkingTime=" + parkingTime +
                ", phoneId='" + phoneId + '\'' +
                '}';
    }

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

    public String getParkingStatus() {
        return parkingStatus;
    }

    public void setParkingStatus(String parkingStatus) {
        this.parkingStatus = parkingStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(String useStartTime) {
        this.useStartTime = useStartTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public int getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(int countMoney) {
        this.countMoney = countMoney;
    }

    public String getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(String useEndTime) {
        this.useEndTime = useEndTime;
    }

    public String getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(String userCarId) {
        this.userCarId = userCarId;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }
}
