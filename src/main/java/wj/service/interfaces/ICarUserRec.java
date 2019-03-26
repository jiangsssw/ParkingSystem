package wj.service.interfaces;

import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.valueBean.UserRecBean;

public interface ICarUserRec {
    public CarUserRec[] getAllUserRecInfo(UserRecBean bean);
}
