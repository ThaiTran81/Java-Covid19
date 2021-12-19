package Model;

import java.sql.Date;

public class profileModel {
    private String username;
    private String password;
    private int type;
    private int block;
    private String fullname;
    private String phone;
    private java.sql.Date dob;
    private String gender;
    private String province;
    private String district;
    private String village;
    private int id_qua;
    private String status;

    public profileModel() {
        username=null;
        password=null;
        type=-1;
        block=0;
        fullname=null;
        phone=null;
        dob=null;
        gender=null;
        province=null;
        district=null;
        village=null;
        id_qua=-1;
        status = null;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public int getId_qua() {
        return id_qua;
    }

    public void setId_qua(int id_qua) {
        this.id_qua = id_qua;
    }

    public String toString(String namequa) {
        return
                "fullname='" + fullname + '\'' +
                        ", phone='" + phone + '\'' +
                        ", dob=" + dob +
                        ", gender='" + gender + '\'' +
                        ", province='" + province + '\'' +
                        ", district='" + district + '\'' +
                        ", village='" + village + '\'' +
                        ", id_qua=" + id_qua +'\'' +
                        ", status="+status;

    }

    public String toString() {
        return
                "fullname: " + fullname + '\n' +
                        "phone: " + phone + '\n' +
                        "dob: " + dob + '\n' +
                        "gender: " + gender + '\n' +
                        "province: " + province + '\n' +
                        "district: " + district + '\n' +
                        "village: " + village + '\n';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
