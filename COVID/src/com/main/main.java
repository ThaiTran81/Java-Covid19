package com.main;

import com.Controller.AdminRegisterController;
import com.Controller.LoginController;
import com.Controller.CovidDAO;

import java.awt.*;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class main {
    private static final Logger logger = LogManager.getLogger(main.class.getName());
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                logger.info("start");
                try {
                    if (new CovidDAO().checkAdminExist()) {
                        new LoginController();

                    } else {
                        new AdminRegisterController();
                    }
                } catch (SQLException ex) {
                    logger.error(ex);
                }
            }
        });
    }
}
