package wj.service.interfaces;

import wj.entity.dataBaseMapping.CarInformation;
import wj.entity.valueBean.CarLogin;

public interface ICarInformation {
     boolean savCarInformation(CarLogin carLogin,int userId);

     CarInformation[] getAllCarInformationByPhonrId(String phoneId);

     boolean updateCarInformation(CarLogin carLogin,int userId);
}
