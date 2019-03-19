package wj.entity.valueBean;

public class CarLogin {

    private String phoneId;
    private String userName;
    private String userCarId;
    private String carType;
    private String carStatus;

    @Override
    public String toString() {
        return "CarLogin{" +
                "phoneId='" + phoneId + '\'' +
                ", userName='" + userName + '\'' +
                ", userCarId='" + userCarId + '\'' +
                ", carType='" + carType + '\'' +
                ", carStatus='" + carStatus + '\'' +
                '}';
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
}
