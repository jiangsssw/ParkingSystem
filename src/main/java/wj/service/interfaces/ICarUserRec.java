package wj.service.interfaces;

import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.valueBean.ReqUserRecBean;

public interface ICarUserRec {
    public CarUserRec[] getAllUserRecInfo(ReqUserRecBean bean);
}
