package wj.service.interfaces;

import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ParkingRecHBean;

public interface IParkingRecHis {
    public ParkingRecHis[] getParkingRecHis(ParkingRecHBean bean);
}
