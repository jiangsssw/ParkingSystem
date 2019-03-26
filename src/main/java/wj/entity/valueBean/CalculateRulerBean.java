package wj.entity.valueBean;

public class CalculateRulerBean {
//    hourMount	Int	每小时收费		是
//    dayMount	Int	每天收费		是
//    weekMount	Int	每周收费		是
//    monthMount	Int	每月收费		是
//    yearMount	int	每年收费		是
//    modiflyPeople	string	修改人		是
//    modiflyId	int	修改用户的ID		是

    private int hourMount;
    private int dayMount;
    private int weekMount;
    private int monthMount;
    private int yearMount;
    private String modiflyPeople;
    private int modiflyId;

    @Override
    public String toString() {
        return "CalculateRulerBean{" +
                "hourMount=" + hourMount +
                ", dayMount=" + dayMount +
                ", weekMount=" + weekMount +
                ", monthMount=" + monthMount +
                ", yearMount=" + yearMount +
                ", modiflyPeople='" + modiflyPeople + '\'' +
                ", modiflyId=" + modiflyId +
                '}';
    }

    public int getHourMount() {
        return hourMount;
    }

    public void setHourMount(int hourMount) {
        this.hourMount = hourMount;
    }

    public int getDayMount() {
        return dayMount;
    }

    public void setDayMount(int dayMount) {
        this.dayMount = dayMount;
    }

    public int getWeekMount() {
        return weekMount;
    }

    public void setWeekMount(int weekMount) {
        this.weekMount = weekMount;
    }

    public int getMonthMount() {
        return monthMount;
    }

    public void setMonthMount(int monthMount) {
        this.monthMount = monthMount;
    }

    public int getYearMount() {
        return yearMount;
    }

    public void setYearMount(int yearMount) {
        this.yearMount = yearMount;
    }

    public String getModiflyPeople() {
        return modiflyPeople;
    }

    public void setModiflyPeople(String modiflyPeople) {
        this.modiflyPeople = modiflyPeople;
    }

    public int getModiflyId() {
        return modiflyId;
    }

    public void setModiflyId(int modiflyId) {
        this.modiflyId = modiflyId;
    }
}
