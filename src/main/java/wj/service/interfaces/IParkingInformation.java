package wj.service.interfaces;

import org.springframework.ui.Model;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.ParkingInformation;

public interface IParkingInformation {
    ParkingInformation[] getAllCarInfoInRoom(int number);

    ParkingInformation[] getAllCarInfoByStatus(String status);

    ParkingInformation[] getAllCarInfoByUserId(int userId);

    boolean addParkingInfo(ParkingInformation information);

    String setCarToParking(Model model,int userId, String carId, String parkingStatus, String payType, String useTime, String carType,String name);

    public void setParkingInfoToHis(ParkingInformation parkingInfos,String userName) throws Exception;

    String dealWithOutCar(Model model,ParkingInformation parking);
}
