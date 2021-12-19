package Controller;

import Model.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CovidDAO {

    public static Statement st;
    public static Connection conn = null;
    public static ConnectToDBModel sql;

    String sv = "localhost";
    String usr = "sa";
    String pwd = "1234567890";
    String db = "QLC19";
    int prt = 1433;

    public CovidDAO() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(usr);
        ds.setPassword(pwd);
        ds.setDatabaseName(db);
        ds.setServerName(sv);
        ds.setPortNumber(prt);
        ds.setEncrypt(true);
        ds.setIntegratedSecurity(true);
        ds.setTrustServerCertificate(true);

        conn = ds.getConnection();
    }

    public CovidDAO(String server, String nameDb, String user, String pass, int port) throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ConnectToDBModel info = new ConnectToDBModel();
        info.sv = server;
        info.db = nameDb;
        info.usr = user;
        info.pwd = pass;
        info.prt = port;
        sql = info;
        conn = ds.getConnection();
    }

    public ResultSet authAccount(String username, String password) throws SQLException {
        String sql = "SELECT * FROM ACCOUNT WHERE USERNAME=? AND PASSWORD=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public ResultSet excuteQuery(String query) throws SQLServerException {
        ResultSet res = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CovidDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(CovidDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public boolean checkAdminExist() throws SQLException{
        String sql = "SELECT * FROM ACCOUNT WHERE TYPE=0";
        Statement stmt = conn.createStatement();
        ResultSet rs=null;
        rs = stmt.executeQuery(sql);
        if(rs.next()){
            return true;
        }
        
        return false;
    }
    
    public void addAccount(profileModel model) throws SQLException {
        String sql = "EXEC AddNewAccount @ID = ? \n" +
"                  ,@PASSWORD = ? \n" +
"                  ,@ROLE = ? \n" +
"                  ,@FULLNAME = ? \n" +
"                  ,@PHONENUMBER = ? \n" +
"                  ,@DOB = ? \n" +
"                  ,@GENDER = ? \n" +
"                  ,@PROVINE = ?\n" +
"                  ,@DISTRICT = ? \n" +
"                  ,@VILLAGE = ? \n" +
"                  ,@ID_QUARATINE = ? \n";
        PreparedStatement stmt = conn.prepareStatement(sql);
        //(@ID VARCHAR(25), @PASSWORD VARCHAR(50), @ROLE INT, @FULLNAME NVARCHAR(100)=NULL, 
        //@PHONENUMBER VARCHAR(20)=NULL, @DOB DATE=NULL, @GENDER NVARCHAR(5)=NULL, 
        //@PROVINE NVARCHAR(50)=NULL, @DISTRICT NVARCHAR(50)=NULL, @VILLAGE NVARCHAR(50)=NULL, 
        //@ID_QUARATINE VARCHAR(10)=NULL, @ID_BANK varchar(10)=NULL)
        stmt.setString(1,model.getUsername());
        stmt.setString(2, model.getPassword());
        stmt.setInt(3, model.getType());
        stmt.setString(4, model.getFullname());
        stmt.setString(5, model.getPhone());
        stmt.setDate(6, model.getDob());
        stmt.setString(7, model.getGender());
        stmt.setString(8, model.getProvince());
        stmt.setString(9, model.getDistrict());
        stmt.setString(10, model.getVillage());
        stmt.setInt(11, model.getId_qua());
        stmt.executeUpdate();
    }
    
    void addAdmin(profileModel admin) throws SQLException{
        String sql = "EXEC AddNewAccount @ID = ? \n" +
"                  ,@PASSWORD = ? \n" +
"                  ,@ROLE = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,admin.getUsername());
        stmt.setString(2, admin.getPassword());
        stmt.setInt(3, admin.getType());
        stmt.executeUpdate();
    }
    public Connection getConnection() {
        return conn;
    }
    
}
