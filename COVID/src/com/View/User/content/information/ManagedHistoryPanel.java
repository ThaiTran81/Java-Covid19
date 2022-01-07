package com.View.User.content.information;

import com.Controller.CovidDAO;
import com.Model.f_historyModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ManagedHistoryPanel extends JPanel {
    Vector<f_historyModel> lst = new Vector<>();

    Font content_title_font = new Font("Title",Font.BOLD,40);

    public ManagedHistoryPanel() {}
    public ManagedHistoryPanel(String id_value) {
        JLabel title = new JLabel("Lịch sử được quản lý");
        title.setFont(content_title_font);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,50));
        none.setBackground(Color.white);
        none.setOpaque(true);
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760,600));
        this.setOpaque(false);
        this.add(title);
        this.add(none);

        setData(id_value);
        String[][] data = getData();

        if (data == null) {
            this.add(new JLabel("Không có lịch sử."));
        }

        String[] columns = {"Trạng thái","Ngày thay đổi"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10,10,400,200);

        this.add(sp);
    }

    public void setData(String id_value) {
        String sql = "SELECT *\n" +
                "FROM F_HISTORY\n" +
                "WHERE USER_ID = ?\n" +
                "ORDER BY F_DATE";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, id_value);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                String f_kind = rs.getString(2);
                java.sql.Timestamp date = rs.getTimestamp(3);
                lst.add(new f_historyModel(id, f_kind, date));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String[][] getData() {
        if (this.lst == null) {
            return null;
        }
        String[][] data = new String[lst.size()][2];
        int i = 0;

        for (f_historyModel f: lst) {
            data[i][0] = f.getF_kind();
            data[i][1] = f.getDate().toString();
            i++;
        }

        return data;
    }
}
