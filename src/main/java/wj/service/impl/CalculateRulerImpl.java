package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import wj.entity.dataBaseMapping.CalculateRuler;
import wj.mapper.CalculateRulerMapper;
import wj.service.interfaces.ICalculateRuler;
import wj.until.ReflectUtil;

import java.util.List;
import java.util.Map;
@Service
public class CalculateRulerImpl implements ICalculateRuler {

    private static Logger log = Logger.getLogger(CalculateRulerImpl.class);

    private CalculateRulerMapper mapper;

    @Override
    public CalculateRuler[] getAllCalculateInfo(int count) {
        List<Map<String,Object>> list = mapper.getAllCalculateInfo(count);
        if (list==null||list.size()==0){
            return new CalculateRuler[0];
        }
        CalculateRuler[] rulers = new CalculateRuler[list.size()];
        for (int i = 0; i<list.size();i++){
            Map<String,Object> map = list.get(i);
            CalculateRuler ruler = new CalculateRuler();
            ReflectUtil.mapToObject(map,ruler);
            rulers[i]=ruler;
        }

        return rulers;
    }
}
