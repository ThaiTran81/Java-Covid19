package Controller;
import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRegisterController {
    public static void register(String usn, String pwd) {
        try (Connection conn = ConnectToDBController.getSqlConnection()) {
            String sql = "INSERT INTO PROFILE(ID, FULLNAME, PHONENUMBER) VALUES(?, 'ADMIN', '0000000000')";
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, usn);

            int row = prepStmt.executeUpdate();

            sql = "INSERT INTO ACCOUNT(USERNAME, PASSWORD, TYPE) VALUES(?, ?, ?)";
            prepStmt = conn.prepareStatement(sql);

            prepStmt.setString(1, usn);
            prepStmt.setString(2, pwd);
            prepStmt.setInt(3, 0);

            row = prepStmt.executeUpdate();

            prepStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
