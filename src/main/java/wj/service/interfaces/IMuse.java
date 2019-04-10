package wj.service.interfaces;

import wj.entity.dataBaseMapping.Muse;
import wj.entity.valueBean.MuseBean;

import java.util.List;

public interface IMuse {
    public List<Muse> getAllByBean(MuseBean bean);
}
