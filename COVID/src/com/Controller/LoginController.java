package com.Controller;

import com.Model.profileModel;
import com.View.Admin.AdminMainFrame;
import com.View.ChangeFirstPassView;
import com.View.LoginView;
import com.View.User.MainFrame;
import com.cv19.view.event.EventLoginCallBack;
import com.cv19.view.manager.ManagerController;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController {

    private profileModel user;
    private LoginView loginView;
    private EventLoginCallBack callback;
    private final Logger logger = LogManager.getLogger(LoginController.class.getName());
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
            logger.info("Authorize account " + username);
            ResultSet rs = new CovidDAO().authAccount(username, password);

            if (rs.next()) {
                user.setUsername(rs.getString("USERNAME"));
                user.setPassword(new String(rs.getBytes("PASSWORD")));
                user.setType(rs.getInt("TYPE"));
                int blocked = rs.getInt(4);
                if (blocked==1){
                    JOptionPane.showMessageDialog(null,"username này đã bị chặn","Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                System.out.println(user.getType());
                if (user.getType() == 0) {
                    loginView.dispose();
                    new AdminMainFrame();
                }
                if (user.getType() == 1) {
                    loginView.dispose();
                    new ManagerController(user.getUsername()).setVisible(true);
                }
                if (user.getType() == 2) {
                    loginView.dispose();
                    if(checkFirstLogIn(user.getUsername())){
                        new ChangeFirstPassView(user.getUsername()).setVisible(true);
                    }
                   else new MainFrame(user.getUsername());
                }
            }
            else JOptionPane.showMessageDialog(null,"username hoặc mật khẩu không đúng","Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLServerException ex) {
            logger.error(ex);
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    boolean checkFirstLogIn(String id){
        try {
            profileModel model = new CovidDAO().getProfileUser(id);
            String pass = model.getDob().toString().replace("-","");
            if(user.getPassword().equals(pass)) return true;
        } catch (SQLException e) {
            return true;
        }
        return false;
    }

}
