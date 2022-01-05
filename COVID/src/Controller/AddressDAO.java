/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AddressModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThaiTran
 */
public class AddressDAO {

     public ArrayList<AddressModel> getAllProvince() throws SQLServerException, SQLException {

        ArrayList<AddressModel> lst = new ArrayList<AddressModel>();

        String sql = "SELECT * FROM PROVINCE";
        Statement stmt = null;

        Connection conn = new CovidDAO().getConnection();
        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            AddressModel tmp = new AddressModel();
            tmp.setId(res.getInt(1));
            tmp.setName(res.getString(2));
            lst.add(tmp);
        }
        if (lst.isEmpty()) {
            return null;
        }
        return lst;
    }
     
    public ArrayList<AddressModel> getDistrict(int province) throws SQLServerException, SQLException {

        ArrayList<AddressModel> lst = new ArrayList<AddressModel>();

        String sql = "SELECT ID_DISTRICT, NAME FROM DISTRICT WHERE ID_PROVINCE= "+province;
        Statement stmt = null;
        Connection conn = new CovidDAO().getConnection();
        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            AddressModel tmp = new AddressModel();
            tmp.setId(res.getInt(1));
            tmp.setName(res.getString(2));
            lst.add(tmp);
        }
        if (lst.isEmpty()) {
            return null;
        }
        return lst;
    }
    
    public ArrayList<AddressModel> getVillage(int district, int province) throws SQLServerException, SQLException {

        ArrayList<AddressModel> lst = new ArrayList<AddressModel>();

        String sql = "SELECT ID_VILLAGE, NAME FROM VILLAGE WHERE ID_DISTRICT= "+district+ " AND ID_PROVINCE="+province;
        Statement stmt = null;
        Connection conn = new CovidDAO().getConnection();
        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            AddressModel tmp = new AddressModel();
            tmp.setId(res.getInt(1));
            tmp.setName(res.getString(2));
            lst.add(tmp);
        }
        if (lst.isEmpty()) {
            return null;
        }
        return lst;
    }

}
