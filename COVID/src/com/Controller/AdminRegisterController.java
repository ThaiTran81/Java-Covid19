package com.Controller;

import com.Model.*;
import com.View.AdminRegisterView;
import com.cv19.view.event.EventAdRegisterCallBack;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AdminRegisterController {

    profileModel adminMod;
    AdminRegisterView view;
    EventAdRegisterCallBack callback;

    public AdminRegisterController() {
        
        callback = new EventAdRegisterCallBack() {
            @Override
            public void register(profileModel model) {
                try {
                    adminMod = model;
                    new CovidDAO().addAdmin(adminMod);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra trong quá trình đăng ký admin!\n"+ex, "Thông báo", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                view.dispose();
                new LoginController();
                
            }

        };
        view = new AdminRegisterView(callback);
        view.setVisible(true);

        
    }
}
