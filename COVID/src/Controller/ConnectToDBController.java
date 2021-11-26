package Controller;

import Model.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectToDBController {
        public static Statement st;
        public static Connection conn = null;
        public static ConnectToDBModel sql;

        public static Connection getSqlConnection() throws SQLServerException {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser(sql.usr);
            ds.setPassword(sql.pwd);
            ds.setDatabaseName(sql.db);
            ds.setServerName(sql.sv);
            ds.setPortNumber(sql.prt);
            conn = ds.getConnection();
            return conn;
        }
        public static Connection getSqlConnection(String server, String nameDb, String user, String pass, int port) throws SQLServerException{
            ConnectToDBModel info = new ConnectToDBModel();
            info.sv =server;
            info.db = nameDb;
            info.usr = user;
            info.pwd = pass;
            info.prt = port;

            sql = info;
            conn = getSqlConnection();
            return conn;
        }

        public static ResultSet excuteQuery(String query) throws SQLServerException {
            ResultSet res = null;
            Statement stmt = null;
            conn = getSqlConnection();
            try {
                stmt = conn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectToDBController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                res = stmt.executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectToDBController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return res;
        }
}
