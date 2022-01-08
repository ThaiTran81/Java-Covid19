package com.Model;

import com.Controller.CovidDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuarantineModel {
    private int id;
    private String name;
    private int id_province;
    private String province;
    private int district;
    private int capicity;
    private int deleted;

    public int getId_province() {
        return id_province;
    }

    public void setId_province(int id_province) {
        this.id_province = id_province;
    }

    public QuarantineModel(int id, String name, String province, int district, int capicity, int deleted) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.district = district;
        this.capicity = capicity;
        this.deleted = deleted;
    }

    public String toString(String namepro, String namedist, int id_qua) {
        String temp = null;
        if (deleted == 0){
            temp = "Open";
        }
        else if (deleted == 1){
            temp = "Closed";
        }

        int count = 0;
        String sql = "  SELECT Count(*)\n" +
                "  FROM QUARATINE Q JOIN PROFILE P ON Q.ID_QUARATINE=P.ID_QUARATINE" +
                " WHERE Q.ID_QUARATINE=?";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, id_qua);
            ResultSet rs = pre.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuarantineModel.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
        }
        return " Name: " + name + "\n" +
                " Province: " + namepro + "\n"+
                " District: " + namedist +"\n"+
                " Capacity: " + capicity +"\n" +
                " Activate: " + temp + "\n" +
                " Current Patients: " + count + "\n";
    }

    public QuarantineModel(){
        id=0;
        name=null;
        province=null;
        district=0;
        capicity=0;
        deleted=0;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getCapicity() {
        return capicity;
    }

    public void setCapicity(int capicity) {
        this.capicity = capicity;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    @Override
    public String toString() {
        return name+", "+province;
    }
    
}
