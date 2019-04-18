package wj.service.interfaces;

import org.springframework.ui.Model;
import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.dataBaseMapping.ParkingInformation;
import wj.entity.valueBean.ParkingInfoBean;
import wj.until.Resp;

import java.util.List;

public interface IParkingInformation {
    ParkingInformation[] getAllCarInfoInRoom(int number);

    ParkingInformation[] getAllCarInfoByStatus(String status);

    ParkingInformation[] getAllCarInfoByUserId(int userId);

    Resp addParkingInfo(ParkingInformation information);

    String setCarToParking(Model model,int userId, String carId, String parkingStatus, String payType, String useTime, String carType,String name);

    public void setParkingInfoToHis(ParkingInformation parkingInfos,String userName) throws Exception;

    String dealWithOutCar(Model model,ParkingInformation parking);

    List<ParkingInfoBean> getAllCarByRoomIdAndParkingId(int roomId, String parkingId);
}
