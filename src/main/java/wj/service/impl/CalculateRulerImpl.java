package wj.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import wj.entity.dataBaseMapping.CalculateRuler;
import wj.entity.valueBean.CalculateRulerBean;
import wj.mapper.CalculateRulerMapper;
import wj.service.interfaces.ICalculateRuler;
import wj.until.ReflectUtil;
import wj.until.TimeUtil;

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

    public String addCalculate(Model model,CalculateRulerBean bean){
        int hour = bean.getHourMount();
        int day = bean.getDayMount();
        int week = bean.getWeekMount();
        int month = bean.getMonthMount();
        int year = bean.getYearMount();
        int modiflyId = bean.getModiflyId();
        String peopleId = bean.getModiflyPeople();
        CalculateRuler ruler = new CalculateRuler();
        ruler.setCreat_time(TimeUtil.getCurrentTimeNow());
        ruler.setModefiy_id(modiflyId);
        ruler.setDay_mount(day);
        ruler.setHour_mount(hour);
        ruler.setWeek_mount(week);
        ruler.setMonth_mount(month);
        ruler.setYear_mount(year);
        ruler.setModefiy_peple(peopleId);
        int i = mapper.addCarInformation(ruler);
        if (i>0){
            log.error("添加计算规则表信息："+ruler.toString());
            return "addCalculateSuccess";
        }
        model.addAttribute("result","添加计费规则失败");
        log.error("添加计算规则表信息失败："+ruler.toString());
        return "error";
    }
}
