/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Controller;

import com.Model.HistoryModel;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

/**
 * @author ThaiTran
 */
public class HistoryDAO {
    static HistoryModel model;
    static Connection conn;
    static HistoryDAO db;

    HistoryDAO() {
        try {
            conn = new CovidDAO().getConnection();
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
    }

    public static void AddHistory(String msg) throws SQLException {
        if (conn == null) db = new HistoryDAO();
        String sql = "INSERT INTO HISTORY (USER_ID, TIME, MSG)\n" +
                " VALUES (?, GETDATE(), ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, model.getUser_id());
        stmt.setString(2, msg);
        stmt.executeUpdate();
    }

    public static void setID(String id) {
        model = new HistoryModel();
        model.setUser_id(id);
    }
}
