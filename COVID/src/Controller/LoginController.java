package Controller;

import Model.profileModel;
import View.Admin.AdminMainFrame;
import View.LoginView;
import View.User.MainFrame;
import com.cv19.view.event.EventLoginCallBack;
import com.cv19.view.manager.ManagerController;
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
            System.out.println("hahah");
            if (rs.next()) {
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setType(rs.getInt("TYPE"));
                System.out.println(user.getType());
                if (user.getType() == 0) {
                    loginView.dispose();
                    new AdminMainFrame();
                }
                if (user.getType() == 1) {
                    loginView.dispose();
                    new ManagerController();
                }
                if (user.getType() == 2) {
                    loginView.dispose();
                    new MainFrame(user.getUsername());
                }
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
