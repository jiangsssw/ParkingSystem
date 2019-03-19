package wj.entity.dataBaseMapping;

import wj.until.TimeUtil;

import java.sql.Timestamp;

public class CalculateRuler {
    private int rule_id;
    private int hour_mount;
    private int day_mount;
    private int week_mount;
    private int month_mount;
    private int year_mount;
    private Timestamp creat_time;
    private  String modefiy_peple;   //修改人
    private int modefiy_id;         //修改人id

    @Override
    public String toString() {
        return "CalculateRuler{" +
                "rule_id=" + rule_id +
                ", hour_mount=" + hour_mount +
                ", day_mount=" + day_mount +
                ", week_mount=" + week_mount +
                ", month_mount=" + month_mount +
                ", year_mount=" + year_mount +
                ", creat_time=" + TimeUtil.timeFormat(creat_time) +
                ", modefiy_peple='" + modefiy_peple + '\'' +
                ", modefiy_id=" + modefiy_id +
                '}';
    }

    public int getRule_id() {
        return rule_id;
    }

    public void setRule_id(int rule_id) {
        this.rule_id = rule_id;
    }

    public int getHour_mount() {
        return hour_mount;
    }

    public void setHour_mount(int hour_mount) {
        this.hour_mount = hour_mount;
    }

    public int getDay_mount() {
        return day_mount;
    }

    public void setDay_mount(int day_mount) {
        this.day_mount = day_mount;
    }

    public int getWeek_mount() {
        return week_mount;
    }

    public void setWeek_mount(int week_mount) {
        this.week_mount = week_mount;
    }

    public int getMonth_mount() {
        return month_mount;
    }

    public void setMonth_mount(int month_mount) {
        this.month_mount = month_mount;
    }

    public int getYear_mount() {
        return year_mount;
    }

    public void setYear_mount(int year_mount) {
        this.year_mount = year_mount;
    }

    public Timestamp getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Timestamp creat_time) {
        this.creat_time = creat_time;
    }

    public String getModefiy_peple() {
        return modefiy_peple;
    }

    public void setModefiy_peple(String modefiy_peple) {
        this.modefiy_peple = modefiy_peple;
    }

    public int getModefiy_id() {
        return modefiy_id;
    }

    public void setModefiy_id(int modefiy_id) {
        this.modefiy_id = modefiy_id;
    }
}
