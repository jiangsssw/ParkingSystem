package wj.entity.dataBaseMapping;

public class CarRoomInformation {
    private int car_room_number; //车库编号
    private int car_parking_num;    //车位编号
    private String EXT1;    //扩展字段
    private String EXT2;
    private String EXT3;

    @Override
    public String toString() {
        return "CarRoomInformation{" +
                "car_room_number=" + car_room_number +
                ", car_parking_num=" + car_parking_num +
                ", EXT1='" + EXT1 + '\'' +
                ", EXT2='" + EXT2 + '\'' +
                ", EXT3='" + EXT3 + '\'' +
                '}';
    }


    public int getCar_room_number() {
        return car_room_number;
    }

    public void setCar_room_number(int car_room_number) {
        this.car_room_number = car_room_number;
    }

    public int getCar_parking_num() {
        return car_parking_num;
    }

    public void setCar_parking_num(int car_parking_num) {
        this.car_parking_num = car_parking_num;
    }

    public String getEXT1() {
        return EXT1;
    }

    public void setEXT1(String EXT1) {
        this.EXT1 = EXT1;
    }

    public String getEXT2() {
        return EXT2;
    }

    public void setEXT2(String EXT2) {
        this.EXT2 = EXT2;
    }

    public String getEXT3() {
        return EXT3;
    }

    public void setEXT3(String EXT3) {
        this.EXT3 = EXT3;
    }
}
