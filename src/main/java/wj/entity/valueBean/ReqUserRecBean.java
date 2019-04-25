package wj.entity.valueBean;

public class ReqUserRecBean {
//    UserId	int	用户ID		是
//    userCarId	String 	车牌号		是
//    phoneId	String	电话号码		是
//    startTime	string	起始时间	yyyymmdd	否
//    endTime	String 	结束时间	yyyymmdd	否
//    carType	String	汽车类型	01~06	否
    private int UserId;
    private String userCarId;
    private String phoneId;
    private String startTime;
    private String endTime;
    private String carType;
    private int startCount;// -1 不分页,>=0分页
    @Override
    public String toString() {
        return "ReqUserRecBean{" +
                "UserId=" + UserId +
                ", userCarId='" + userCarId + '\'' +
                ", phoneId='" + phoneId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", carType='" + carType + '\'' +
                ", startCount='" + startCount + '\'' +
                '}';
    }

    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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
}
