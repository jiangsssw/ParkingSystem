package wj.entity.dataBaseMapping;

public class Muse {
//    muse_idint(6)餐单编号
//    muse_urlvarchar(20)餐单栏地址
//    muse_namevarchar(20)名字
//    muse_roleint(5)权限等级
//    remarkvarchar(50)备注

    private int muse_id;
    private String muse_url;
    private String muse_name;
    private int muse_role;
    private String remark;
    private byte ii ;

    public byte getIi() {
        return ii;
    }

    public void setIi(byte ii) {
        this.ii = ii;
    }

    public int getMuse_id() {
        return muse_id;
    }

    public void setMuse_id(int muse_id) {
        this.muse_id = muse_id;
    }

    public String getMuse_url() {
        return muse_url;
    }

    public void setMuse_url(String muse_url) {
        this.muse_url = muse_url;
    }

    public String getMuse_name() {
        return muse_name;
    }

    public void setMuse_name(String muse_name) {
        this.muse_name = muse_name;
    }

    public int getMuse_role() {
        return muse_role;
    }

    public void setMuse_role(int muse_role) {
        this.muse_role = muse_role;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Muse{" +
                "muse_id=" + muse_id +
                ", muse_url='" + muse_url + '\'' +
                ", muse_name='" + muse_name + '\'' +
                ", muse_role=" + muse_role +
                ", remark='" + remark + '\'' +
                '}';
    }
}
