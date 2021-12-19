package Controller;

import Model.profileModel;
import View.Admin.AdminMainFrame;
import View.LoginView;
import com.cv19.view.event.EventLoginCallBack;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {

    private profileModel user;
    private LoginView loginView;
    private EventLoginCallBack callback;

    public LoginController() {
        user = new profileModel();
        loginView = new LoginView(new EventLoginCallBack() {
            @Override
            public void authorize(String username, String password) {
                Login(username, password);
            }
        });
        loginView.setVisible(true);
    }

    public void Login(String username, String password) {

        try {
            ResultSet rs = new CovidDAO().authAccount(username, password);
            if (rs.next()) {
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setType(rs.getInt("TYPE"));
                System.out.println(user.getType());
                if (user.getType() == 0) {
                    loginView.dispose();
                    new AdminMainFrame();
                }
            }

        } catch (SQLServerException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
