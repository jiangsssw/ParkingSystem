package wj.service.interfaces;

import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ReqParkingRecHBean;

public interface IParkingRecHis {
    public ParkingRecHis[] getParkingRecHis(ReqParkingRecHBean bean);

    public int getParkingRecHCounts();
}
