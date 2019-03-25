package wj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wj.entity.dataBaseMapping.ParkingRecHis;
import wj.entity.valueBean.ParkingRecHBean;
import wj.mapper.ParkingRecHisMapper;
import wj.service.interfaces.IParkingRecHis;
import wj.until.ReflectUtil;

import java.util.List;
import java.util.Map;

@Service
public class ParkingRecHisImpl implements IParkingRecHis {
    @Autowired
    ParkingRecHisMapper mapper;
    @Override
    public ParkingRecHis[] getParkingRecHis(ParkingRecHBean bean) {


        List<Map<String,Object>> list  = mapper.getParkingRecH(bean);
        int size = list.size();
        if (list!=null&&size>0){
            ParkingRecHis[] recHis = new ParkingRecHis[size];
            for (int i = 0;i<size;i++){
    Map<String,Object> p = list.get(i);
    ParkingRecHis his = new ParkingRecHis();
                ReflectUtil.mapToObject(p,his);
                        recHis[i]=his;
                        }
                        return recHis;
                        }



                        return new ParkingRecHis[0];
                        }
                        }
