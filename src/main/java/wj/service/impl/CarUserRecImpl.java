package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wj.entity.dataBaseMapping.CarUserRec;
import wj.entity.valueBean.UserRecBean;
import wj.mapper.ICarUserRecMapper;
import wj.service.interfaces.ICarUserRec;
import wj.until.ReflectUtil;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Service
public class CarUserRecImpl implements ICarUserRec {
    private static Logger log = Logger.getLogger(CarUserRecImpl.class);

    @Autowired
    private ICarUserRecMapper mapper;

    @Override
    public CarUserRec[] getAllUserRecInfo(UserRecBean bean) {
        log.error("查询getAllUserRecInfo 入参----》"+bean.toString());
        List<Map<String,Object>> list = mapper.getAllUserRec(bean);
        if (list==null||list.size()==0){
            log.error("查询查询getAllUserRecInfo结果为空");
            return new CarUserRec[0];
        }
        CarUserRec[] recs = new CarUserRec[list.size()];
        for (int i = 0;i<list.size();i++){
            Map<String,Object> map = list.get(i);
            CarUserRec rec = new CarUserRec();
            ReflectUtil.mapToObject(map,rec);
            recs[i]=rec;
        }
        log.error("查询查询getAllUserRecInfo结果为--->"+ StringUtils.arrayToCommaDelimitedString(recs));
        return recs;
    }
}
