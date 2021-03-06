package com.Controller;

import com.Model.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CovidDAO {

    public static Statement st;
    public static Connection conn = null;
    public static ConnectToDBModel sql;

    String sv = "localhost";
    String usr = "sa";
    String pwd = "123456";
    String db = "QLC19";
    int prt = 1433;

    public CovidDAO() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(usr);
        ds.setPassword(pwd);
        ds.setDatabaseName(db);
        ds.setServerName(sv);
        ds.setPortNumber(prt);
//        ds.setEncrypt(true);
//        ds.setIntegratedSecurity(true);
//        ds.setTrustServerCertificate(true);

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
        stmt.setBytes(2, password.getBytes(StandardCharsets.UTF_8));
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

    public void executeUpdate(String sql, Object[] params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);

        for (int i = 1; i <= params.length; i++) {
            stmt.setObject(i, params[i - 1]);
        }
        stmt.executeUpdate();
    }

    public boolean checkAdminExist() throws SQLException {
        String sql = "SELECT * FROM ACCOUNT WHERE TYPE=0";
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return true;
        }

        return false;
    }

    public int addAccount(profileModel model) throws SQLException {
        String sql = "EXEC AddNewAccount @ID = ?\n"
                + "                  ,@PASSWORD = ?\n"
                + "                  ,@ROLE = ?\n"
                + "                  ,@FULLNAME = ?\n"
                + "                  ,@PHONENUMBER = ?\n"
                + "                  ,@DOB = ?\n"
                + "                  ,@GENDER = ?\n"
                + "                  ,@PROVINE = ?\n"
                + "                  ,@DISTRICT = ?\n"
                + "                  ,@VILLAGE = ?\n"
                + "                  ,@ID_QUARATINE = ?\n"
                + "                  ,@RELATED = ?\n"
                + "                  ,@F_KIND = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        //(@ID VARCHAR(25), @PASSWORD VARCHAR(50), @ROLE INT, @FULLNAME NVARCHAR(100)=NULL,
        //@PHONENUMBER VARCHAR(20)=NULL, @DOB DATE=NULL, @GENDER NVARCHAR(5)=NULL,
        //@PROVINE NVARCHAR(50)=NULL, @DISTRICT NVARCHAR(50)=NULL, @VILLAGE NVARCHAR(50)=NULL,
        //@ID_QUARATINE VARCHAR(10)=NULL, @ID_BANK varchar(10)=NULL)
        stmt.setString(1, model.getUsername());
        stmt.setBytes(2, model.getPassword().getBytes());
        stmt.setInt(3, model.getType());
        stmt.setString(4, model.getFullname());
        stmt.setString(5, model.getPhone());
        stmt.setDate(6, model.getDob());
        stmt.setString(7, model.getGender());
        stmt.setString(8, model.getProvince());
        stmt.setString(9, model.getDistrict());
        stmt.setString(10, model.getVillage());
        stmt.setInt(11, model.getId_qua());
        stmt.setString(12, model.getRelated_id());
        stmt.setString(13, model.getStatus());
        return stmt.executeUpdate();
    }

    public void addAdmin(profileModel admin) throws SQLException {
        String sql = "EXEC AddNewAccount @ID = ? \n"
                + "                  ,@PASSWORD = ? \n"
                + "                  ,@ROLE = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, admin.getUsername());
        stmt.setBytes(2, admin.getPassword().getBytes());
        stmt.setInt(3, admin.getType());
        stmt.executeUpdate();
    }

    public Connection getConnection() {
        return conn;
    }

    public ArrayList<QuarantineModel> getAllQuarantine() throws SQLException {
        ArrayList<QuarantineModel> lst = new ArrayList<QuarantineModel>();

        String sql = "SELECT Q.ID_QUARATINE, Q.NAME AS NAME, P.NAME AS PROVINCE,  Q.CAPABLE FROM QUARATINE Q JOIN PROVINCE P ON Q.ID_PROVINCE = P.ID_PROVINCE WHERE IS_DELETED=0";
        Statement stmt = null;

        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            QuarantineModel tmp = new QuarantineModel();
            tmp.setId(res.getInt(1));
            tmp.setName(res.getString(2));
            tmp.setProvince(res.getString(3));
            tmp.setCapicity(res.getInt(4));
            lst.add(tmp);
        }
        if (lst.isEmpty()) {
            return null;
        }
        return lst;
    }

    public int getCountUserQuaratine(int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PROFILE WHERE ID_QUARATINE = "+id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            int i = rs.getInt(1);
            return i;
        }

        return 0;
    }

    public List<profileModel> getStatusUser(String id, String name) throws SQLException {
        String sql = "SELECT ID, FULLNAME, F_STATUS\n"
                + "FROM PROFILE\n"
                + "WHERE ID LIKE ? and FULLNAME LIKE ? AND LEN(F_STATUS)>0\n";
        if(id == null) id = "";
        if(name == null) name="";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%"+id+"%");
        stmt.setString(2, "%"+name+"%");
        ResultSet rs = stmt.executeQuery();
        List<profileModel> lst = new ArrayList<>();
        while (rs.next()) {
            profileModel tmp = new profileModel();
            tmp.setUsername(rs.getString(1));
            tmp.setFullname(rs.getString(2));
            tmp.setStatus(rs.getString(3));
            lst.add(tmp);
        }
        if(lst.size() >0) return lst;
        return null;
    }

    public List<profileModel> getAllUser() throws SQLException {
        String sql = "SELECT p.ID, p.FULLNAME, p.PHONENUMBER, p.DOB, p.GENDER, p.PROVINE, p.DISTRICT, p.VILLAGE, p.ID_QUARATINE, p.ID_BANK, p.F_STATUS, p.RELATED_USER\n"
                + "FROM PROFILE p";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<profileModel> lst = new ArrayList<>();
        while (rs.next()) {
            profileModel tmp = new profileModel();
            tmp.setUsername(rs.getString(1));
            tmp.setFullname(rs.getString(2));
            tmp.setPhone(rs.getString(3));
            tmp.setDob(rs.getDate(4));
            tmp.setGender(rs.getString(5));
            tmp.setProvince(rs.getString(6));
            tmp.setDistrict(rs.getString(7));
            tmp.setVillage(rs.getString(8));
            tmp.setId_qua(rs.getInt(9));
            tmp.setId_bank(rs.getInt(10));
            tmp.setStatus(rs.getString(11));
            tmp.setRelated_id(rs.getString(12));
            lst.add(tmp);
        }
        if (lst.size() == 0) {
            return null;
        }
        return lst;
    }

    public profileModel getProfileUser(String id) throws SQLException {
        String sql = "SELECT TOP 1 p.ID, p.FULLNAME, p.PHONENUMBER, p.DOB, p.GENDER, p.PROVINE, p.DISTRICT, p.VILLAGE, p.ID_QUARATINE, p.ID_BANK, p.F_STATUS, p.RELATED_USER\n"
                + "FROM PROFILE p\n"
                + "WHERE p.ID =?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            profileModel tmp = new profileModel();
            tmp.setUsername(rs.getString(1));
            tmp.setFullname(rs.getString(2));
            tmp.setPhone(rs.getString(3));
            tmp.setDob(rs.getDate(4));
            tmp.setGender(rs.getString(5));
            tmp.setProvince(rs.getString(6));
            tmp.setDistrict(rs.getString(7));
            tmp.setVillage(rs.getString(8));
            tmp.setId_qua(rs.getInt(9));
            tmp.setId_bank(rs.getInt(10));
            tmp.setStatus(rs.getString(11));
            tmp.setRelated_id(rs.getString(12));
            return tmp;
        }
        return null;
    }

    public TreeMap<String, FModel> getNumFDate(String date) throws SQLException {
        String where = "";
        if (date != null) {
            where = "WHERE TRY_CONVERT(DATE,fh1.F_DATE) = '" + date + "'\n";
        }
        String sql = "SELECT TRY_CONVERT(DATE,fh.F_DATE)'time', F_KIND 'status', COUNT(*) 'quantity'\n"
                + "FROM dbo.F_HISTORY fh INNER JOIN (SELECT\n"
                + "    fh1.USER_ID\n"
                + "   ,MAX(fh1.F_DATE) AS newestTime\n"
                + "  FROM F_HISTORY fh1\n"
                +where+" "
                + "  GROUP BY fh1.USER_ID) f\n"
                + "  ON fh.USER_ID = f.USER_ID AND fh.F_DATE = f.newestTime\n"
                + "GROUP BY TRY_CONVERT(DATE,fh.F_DATE),  F_KIND\n"
                + "ORDER BY TRY_CONVERT(DATE,fh.F_DATE)";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        TreeMap<String, FModel> lst = new TreeMap<String, FModel>(Collections.reverseOrder());

        while (rs.next()) {
            String time = rs.getString("time");
            String status = rs.getString("status");
            int num = rs.getInt("quantity");
            FModel f;
            if (lst.containsKey(time)) {
                f = lst.get(time);
            } else {
                f = new FModel();
                lst.put(time, f);
            }
            // set f
            if (status.equalsIgnoreCase("F0")) {
                f.setF0(num);
            }
            if (status.equalsIgnoreCase("F1")) {
                f.setF1(num);
            }
            if (status.equalsIgnoreCase("F2")) {
                f.setF2(num);
            }
            if (status.equalsIgnoreCase("F3")) {
                f.setF3(num);
            }
            if (status.equalsIgnoreCase("OK")) {
                f.setGood(num);
            }

        }
        if (lst.size() == 0) {
            return null;
        }
        return lst;
    }

    public int getNumByF(String f) throws SQLException {
        String sql = "SELECT COUNT(*)\n"
                + "FROM F_HISTORY fh\n"
                + "INNER JOIN (SELECT\n"
                + "    fh1.USER_ID\n"
                + "   ,MAX(fh1.F_DATE) AS newestTime\n"
                + "  FROM F_HISTORY fh1\n"
                + "  GROUP BY fh1.USER_ID) f\n"
                + "  ON fh.USER_ID = f.USER_ID AND fh.F_DATE = f.newestTime\n"
                + "WHERE fh.F_KIND = '" + f + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int getNumNewF0() throws SQLException {
        String sql = "SELECT COUNT(*)\n"
                + "FROM F_HISTORY fh\n"
                + "INNER JOIN (SELECT\n"
                + "    fh1.USER_ID\n"
                + "   ,MAX(fh1.F_DATE) AS newestTime\n"
                + "  FROM F_HISTORY fh1\n"
                + "  GROUP BY fh1.USER_ID) f\n"
                + "  ON fh.USER_ID = f.USER_ID AND fh.F_DATE = f.newestTime\n"
                + "WHERE TRY_CONVERT(DATE, fh.F_DATE) = TRY_CONVERT(DATE, GETDATE())\n"
                + "AND fh.F_KIND = 'F0'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    String getSrcUser(String id) {
        String sql = "";
        return null;
    }

    public ArrayList<CurrentStateModel> getCurState(String id, String fullname, String year, String status, int qua_id) throws SQLException {
        String sql = "SELECT p.ID, p.FULLNAME, p.DOB, p.F_STATUS, q.NAME AS quaratine\n"
                + "FROM PROFILE p JOIN QUARATINE q ON p.ID_QUARATINE = q.ID_QUARATINE \n";
        int count = 0;

        if (!id.isBlank()) {
            if (count == 0) {
                sql = sql + "WHERE p.ID='" + id + "'";
                count++;
            } else if (count > 0) {
                sql = sql + " AND p.ID='" + id + "'";
            }
        }

        if (!fullname.isBlank()) {
            if (count == 0) {
                sql = sql + "WHERE p.FULLNAME like N'%" + fullname + "%'";
                count++;
            } else if (count > 0) {
                sql = sql + " AND p.FULLNAME like N'%" + fullname + "%'";
            }
        }

        if (year != null) {
            if (count == 0) {
                sql = sql + "WHERE YEAR(p.DOB) =" + year;
                count++;
            } else if (count > 0) {
                sql = sql + " AND YEAR(p.DOB) =" + year;
            }
        }

        if (!status.isBlank()) {
            if (count == 0) {
                sql = sql + "WHERE p.F_STATUS='" + status + "'";
                count++;
            } else if (count > 0) {
                sql = sql + " AND p.F_STATUS='" + status + "'";
            }
        }

        if (qua_id != -1) {
            if (count == 0) {
                sql = sql + "WHERE  p.ID_QUARATINE=" + qua_id;
                count++;
            } else if (count > 0) {
                sql = sql + " AND  p.ID_QUARATINE=" + qua_id;
            }
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<CurrentStateModel> arr = new ArrayList<>();
        while (rs.next()) {
            CurrentStateModel model = new CurrentStateModel();

            model.setId(rs.getString(1));
            model.setFullname(rs.getString(2));
            model.setDob(rs.getString(3));
            model.setState(rs.getString(4));
            model.setQuaratine(rs.getString(5));

            arr.add(model);
        }
        if (arr.size() == 0) {
            return null;
        }
        return arr;
    }

    public ArrayList<NecessityModel> getAllNecessity() throws SQLException {
        String sql = "SELECT n.ID_NECESSITIES, n.NAME, n.LIMIT, n.PRICE, n.TIME_LIMIT, n.CONSUMPTION\n"
                + "FROM NECESSITIES n WHERE n.deleted=0";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<NecessityModel> lst = new ArrayList<>();
        while (rs.next()) {
            NecessityModel model = new NecessityModel();
            model.setId(rs.getInt(1));
            model.setName(rs.getString(2));
            model.setLimit(rs.getInt(3));
            model.setPrice(rs.getInt(4));
            model.setTime_limit(rs.getInt(5));
            model.setConsume(rs.getInt(6));
            lst.add(model);
        }
        if (lst.size() == 0) {
            return null;
        }
        return lst;
    }

    public void addNecessity(NecessityModel item) throws SQLException {
        String sql = "INSERT INTO NECESSITIES (NAME, LIMIT, PRICE, TIME_LIMIT,NEC_KIND,consumption)\n"
                + "  VALUES (? , ?, ?, ?,?, DEFAULT);";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, item.getName());
        stmt.setInt(2, item.getLimit());
        stmt.setInt(3, item.getPrice());
        stmt.setInt(4, item.getTime_limit());
        stmt.setString(5, item.getType());

        System.out.println(stmt.executeUpdate());
    }

    public ArrayList<NecessityModel> getNecessityByName(String name) throws SQLException {
        String sql = "SELECT n.ID_NECESSITIES, n.NAME, n.LIMIT, n.PRICE, n.TIME_LIMIT, n.consumption\n"
                + "FROM NECESSITIES n\n"
                + "WHERE n.NAME LIKE N'%" + name + "%' AND n.deleted!=1";

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<NecessityModel> lst = new ArrayList<>();
        while (rs.next()) {
            NecessityModel model = new NecessityModel();
            model.setId(rs.getInt(1));
            model.setName(rs.getString(2));
            model.setLimit(rs.getInt(3));
            model.setPrice(rs.getInt(4));
            model.setTime_limit(rs.getInt(5));
            model.setConsume(rs.getInt(6));
            lst.add(model);
        }
        if (lst.size() == 0) {
            return null;
        }
        return lst;
    }

    public NecessityModel getNecessityByID(int id) throws SQLException {
        String sql = "SELECT n.ID_NECESSITIES, n.NAME, n.LIMIT, n.PRICE, n.TIME_LIMIT, n.NEC_KIND, n.consumption\n"
                + "FROM NECESSITIES n\n"
                + "WHERE n.ID_NECESSITIES = " + id;

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            NecessityModel model = new NecessityModel();
            model.setId(rs.getInt(1));
            model.setName(rs.getString(2));
            model.setLimit(rs.getInt(3));
            model.setPrice(rs.getInt(4));
            model.setTime_limit(rs.getInt(5));
            model.setType(rs.getString(6));
            model.setConsume(rs.getInt(7));
            return model;
        }
        return null;
    }

    public OutBalanceModel getOutBalanceByID(String id) throws SQLException {
        String sql = "SELECT p.ID, p.FULLNAME, Addr=(p.VILLAGE+ ', ' +p.DISTRICT+', '+p.PROVINE), de.debt\n"
                + "FROM PROFILE p JOIN DEBT de ON p.ID = de.USER_ID\n"
                + "WHERE de.debt > 0 AND p.ID='" + id + "'";
        Statement stmt = conn.createStatement();
        OutBalanceModel model = null;
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            model = new OutBalanceModel();
            model.setId(rs.getString(1));
            model.setName(rs.getString(2));
            model.setAddress(rs.getString(3));
            model.setOutBalance(rs.getInt(4));
        }

        return model;
    }

    public List<OutBalanceModel> getAllOutBalance() throws SQLException {
        String sql = "SELECT p.ID, p.FULLNAME, Addr=(p.VILLAGE+ ', ' +p.DISTRICT+', '+p.PROVINE), de.debt\n"
                + "FROM PROFILE p JOIN DEBT de ON p.ID = de.USER_ID\n";
//                + "WHERE de.debt > 0";
        Statement stmt = conn.createStatement();
        List<OutBalanceModel> lst = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            OutBalanceModel model = new OutBalanceModel();
            model.setId(rs.getString(1));
            model.setName(rs.getString(2));
            model.setAddress(rs.getString(3));
            model.setOutBalance(rs.getInt(4));
            lst.add(model);
        }
        if (lst.size() == 0) {
            return null;
        }
        return lst;
    }

    public List<profileModel> getRelatedUser(profileModel user) throws SQLException {
        String sql = "SELECT p.ID, p.FULLNAME, p.F_STATUS\n"
                + "FROM PROFILE p\n"
                + "WHERE p.ID= ? OR  p.RELATED_USER = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, user.getRelated_id());
        stmt.setString(2, user.getUsername());

        ResultSet rs = stmt.executeQuery();
        List<profileModel> lst = new ArrayList<>();
        while (rs.next()) {
            profileModel model = new profileModel();
            model.setUsername(rs.getString("ID"));
            model.setFullname(rs.getString("FULLNAME"));
            model.setStatus(rs.getString("F_STATUS"));
            lst.add(model);
        }
        if (lst.size() == 0) {
            return null;
        }
        return lst;
    }

    public List<f_historyModel> getChangeList(String id) throws SQLException {
        String sql = "SELECT fh.F_DATE, fh.F_KIND, CONCAT(q.NAME,'(',q.ID_QUARATINE,')') AS quaratine\n"
                + "FROM F_HISTORY fh JOIN QUARATINE q ON fh.ID_QUARATINE = q.ID_QUARATINE\n"
                + "WHERE fh.USER_ID = ? \n"
                + "ORDER BY fh.F_DATE desc";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        List<f_historyModel> lst = new ArrayList<>();

        while (rs.next()) {
            f_historyModel model = new f_historyModel();

            model.setDate(rs.getTimestamp(1));
            model.setF_kind(rs.getString(2));
            model.setQuarantine(rs.getString(3));
            lst.add(model);
        }
        if (lst.size() > 0) {
            return lst;
        }
        return null;
    }

    public void updateProfile(profileModel user) throws SQLException {
        String sql = "UPDATE PROFILE \n"
                + " SET FULLNAME = ? \n"
                + "   ,PHONENUMBER = ? \n"
                + "   ,DOB = ? \n"
                + "   ,GENDER = ? \n"
                + "   ,PROVINE = ? \n"
                + "   ,DISTRICT = ? \n"
                + "   ,VILLAGE = ? \n"
                + "   ,ID_QUARATINE = ? \n"
                + "   ,F_STATUS = ? \n"
                + "   ,RELATED_USER = ? \n"
                + "WHERE ID = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, user.getFullname());
        stmt.setString(2, user.getPhone());
        stmt.setDate(3, user.getDob());
        stmt.setString(4, user.getGender());
        stmt.setString(5, user.getProvince());
        stmt.setString(6, user.getDistrict());
        stmt.setString(7, user.getVillage());
        stmt.setInt(8, user.getId_qua());
        stmt.setString(9, user.getStatus());
        stmt.setString(10, user.getRelated_id());
        stmt.setString(11, user.getUsername());

        int i = stmt.executeUpdate();

    }

    public void updateState(Object params[]) throws SQLException {
        String sql = "UPDATE PROFILE \n"
                + "set F_STATUS = '" + params[1] + "' "
                + "WHERE ID ='" + params[0]+"'";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void updateStateAndQua(Object params[]) throws SQLException {
        String sql = "UPDATE PROFILE \n"
                + "  set F_STATUS = ?, \n"
                + " ID_QUARATINE = ? \n"
                + "WHERE ID = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);

        for (int i = 1; i <= params.length; i++) {
            stmt.setObject(i, params[i - 1]);
        }

        stmt.executeUpdate();
    }

    public int updateNewPassword(String id, String password) throws SQLException {
        String sql = "UPDATE ACCOUNT \n"
                + "set PASSWORD = ? "
                + "WHERE USERNAME = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBytes(1,password.getBytes());
        stmt.setString(2,id);
        return stmt.executeUpdate();
    }
    public String getPass(String id) throws SQLException {
        String sql="SELECT a.PASSWORD\n" +
                "FROM ACCOUNT a\n" +
                "WHERE a.USERNAME = '"+id+"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        String pass = null;
        if(rs.next()){
            pass = new String(rs.getBytes(1));
        }
        return pass;

    }


    public List<String> getCategory() throws SQLException{
        String sql = "SELECT DISTINCT n.NEC_KIND\n" +
                "FROM NECESSITIES n";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        List<String> lst = new ArrayList<>();
        while(rs.next()){
            String catName = rs.getString(1);
            lst.add(catName);
        }
        if(lst.size()==0) return null;
        return lst;
    }
}
