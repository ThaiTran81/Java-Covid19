package main;

import Controller.AdminRegisterController;
import Controller.LoginController;
import Model.profileModel;
import View.LoginView;
import View.User.MainFrame;
import Controller.CovidDAO;
import View.Admin.AdminMainFrame;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    if (new CovidDAO().checkAdminExist()) {
                        new LoginController();

                    } else {
                        new AdminRegisterController();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
