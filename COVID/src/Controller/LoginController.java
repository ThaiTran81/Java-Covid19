package Controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public int Login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM ACCOUNT WHERE USERNAME=? AND PASSWORD=?";
        try(Connection conn = ConnectToDBController.getSqlConnection(); PreparedStatement ppstmt = conn.prepareStatement(sql);){
            ppstmt.setString(1, username);
            ppstmt.setString(2, password);

            ResultSet rs = ppstmt.executeQuery();
            if (rs.next()) {
                int type = rs.getInt("type");
                return type;
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static boolean checkManagerExist() throws SQLException {
        String sql = "SELECT * FROM ACCOUNT WHERE TYPE=0";
        try(Connection conn = ConnectToDBController.getSqlConnection();
            PreparedStatement ppstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){

            ResultSet rs = ppstmt.executeQuery();
            int rows = 0;
            if (rs.last()) {
                rows = rs.getRow();
                // Move to beginning
                rs.beforeFirst();
            }
            if (rows == 0)
                return false;
            else if (rows > 0)
                return true;
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
