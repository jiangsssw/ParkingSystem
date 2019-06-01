package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wj.entity.dataBaseMapping.Muse;
import wj.entity.valueBean.MuseBean;
import wj.mapper.MuseMapper;
import wj.service.interfaces.IMuse;
import wj.until.ReflectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MuseImpl implements IMuse {
    private static Logger log = Logger.getLogger(MuseImpl.class);

    @Autowired
    private MuseMapper mapper;

    @Override
    public List<Muse> getAllByBean(MuseBean bean) {
        Muse se  = new Muse();
        se.setMuse_id(bean.getMuseId());
        se.setMuse_role(bean.getRole());
        se.setMuse_url(bean.getMuseUrl());
        se.setMuse_name(bean.getMuseName());
        List<Map<String,Object>> listMap = mapper.getAllMuseBy(se);
        List<Muse> list = new ArrayList<>();
        if (listMap!=null||listMap.size()>0) {
            for (Map<String, Object> map : listMap) {
                try {
                    Muse muse = new Muse();
                    ReflectUtil.mapToObject(map, muse);
                    list.add(muse);
                } catch (Exception e) {
                    log.error("转换pojo出错---》" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        return list;

    }
}
