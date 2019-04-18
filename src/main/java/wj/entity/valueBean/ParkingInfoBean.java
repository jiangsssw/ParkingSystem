package wj.entity.valueBean;

public class ParkingInfoBean {

    private int roomId;
    private String parkingId;
    private String userId;
    private String parkingStatus;
    private String payType;
    private String useStartTime;
    private String useType;
    private String userCarId;
    private String carType;
    private String isSubscription;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(String userCarId) {
        this.userCarId = userCarId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getIsSubscription() {
        return isSubscription;
    }

    public void setIsSubscription(String isSubscription) {
        this.isSubscription = isSubscription;
    }
}
