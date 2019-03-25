package wj.entity.valueBean;

public class ParkingRecHBean {
//    UserId	int
//    userCarId	String
//    phoneId	String
//    startTime	string
//    endTime	String
//    carType	String
    private int userId;
    private String userCarId;
    private String phoneId;
    private String startTime;
    private String endTime;
    private String carType;
    private int startCount;

    @Override
    public String toString() {
        return "ParkingRecHBean{" +
                "userId=" + userId +
                ", userCarId=" + userCarId +
                ", phoneId=" + phoneId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", carType=" + carType +",startCount"+ startCount +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserCarId() {
        return userCarId;
    }

    public void setUserCarId(String userCarId) {
        this.userCarId = userCarId;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }
}
