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

//                SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
//                profileModel model = new profileModel();
//                model.setUsername("88776655");
//                model.setPassword("123456");
//                model.setType(2);
//                model.setFullname("Thai");
//                model.setGender("Nam");
//                model.setDob(Date.valueOf("2000-01-01"));
//                model.setDistrict(1);
//                model.setVillage(1);
//                model.setProvince(1);
//                model.setId_qua(1);
//                model.setStatus("F0");
//                try {
//                    new CovidDAO().addAccount(model);
//                } catch (SQLServerException ex) {
//                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SQLException ex) {
//                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//                }
                } catch (SQLServerException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
