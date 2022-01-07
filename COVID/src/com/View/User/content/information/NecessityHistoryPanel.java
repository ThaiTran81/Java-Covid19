package com.View.User.content.information;

import com.Controller.CovidDAO;
import com.Model.NecessityHistoryModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class NecessityHistoryPanel extends JPanel {
    Vector<NecessityHistoryModel> lst = new Vector<>();

    Font content_title_font = new Font("Title",Font.BOLD,40);

    public NecessityHistoryPanel() {}
    public NecessityHistoryPanel(String id_value) {
        JLabel title = new JLabel("Lịch sử tiêu thụ gói Nhu yếu phẩm");
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

        String[] columns = {"Ngày thanh toán","Tên sản phẩm","Đơn Giá","Số lượng","Tổng cộng"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10,10,700,200);

        this.add(sp);
    }

    public void setData(String id_value) {
        String sql = "SELECT B.ID_USER, B.DATE, N.NAME, N.PRICE, C.QUANTITY, N.PRICE*C.QUANTITY\n" +
                "FROM BILL B JOIN CONSUME C ON B.ID_BILL = C.ID_BILL JOIN NECESSITIES N ON C.ID_NECESSITIES = N.ID_NECESSITIES\n" +
                "WHERE B.ID_USER = ?";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, id_value);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String id = rs.getString(1);
                java.sql.Date date = rs.getDate(2);
                String name = rs.getString(3);
                String price = rs.getString(4);
                int quantity = rs.getInt(5);
                String total = rs.getString(6);
                lst.add(new NecessityHistoryModel(id, date, name, price, quantity, total));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String[][] getData() {
        if (this.lst == null) {
            return null;
        }
        String[][] data = new String[lst.size()][5];
        int i = 0;

        for (NecessityHistoryModel f: lst) {
            data[i][0] = f.getDate().toString();
            data[i][1] = f.getName();
            data[i][2] = f.getPrice();
            data[i][3] = String.valueOf(f.getQuantity());
            data[i][4] = f.getTotal();
            i++;
        }

        return data;
    }
}
