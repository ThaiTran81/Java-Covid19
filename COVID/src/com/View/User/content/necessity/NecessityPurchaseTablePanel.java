package com.View.User.content.necessity;

import com.Controller.CovidDAO;
import com.Model.NecessityHistoryModel;
import com.Model.NecessityModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Vector;

public class NecessityPurchaseTablePanel extends JPanel {
    Vector<NecessityModel> lst = new Vector<>();
    Vector<NecessityHistoryModel> used_lst = new Vector<>();
    String[][] data;

    public NecessityPurchaseTablePanel() {}
    public NecessityPurchaseTablePanel(String id_value, Boolean a) {
        this.setBackground(Color.white);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(690,320));

        setData("");
        getUsedData(id_value);
        if (a == Boolean.TRUE) {
            getAllData();
        }
        else {
            getData("");

            if (data == null) {
                this.add(new JLabel("Error."));
            }

            String[] columns = {"Mã sản phẩm", "Tên sản phẩm", "Loại nhu yếu phẩm"};
            JTable table = new JTable(data, columns);
            JScrollPane sp = new JScrollPane(table);
            sp.setPreferredSize(new Dimension(320, 200));

            this.add(sp);
        }
    }

    public NecessityPurchaseTablePanel(String id_value, String search, String nec_type) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(690,320));

        setData(search);
        getUsedData(id_value);
        data = new String[lst.size()][4];
        getData(nec_type);

        if (data == null) {
            this.add(new JLabel("Error."));
        }

        String[] columns = {"Mã sản phẩm", "Tên sản phẩm", "Loại nhu yếu phẩm"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(320,200));

        this.add(sp);
    }

    public void getUsedData(String id_value) {
        String sql = "SELECT B.ID_USER, B.DATE, N.NAME, N.TIME_LIMIT, C.QUANTITY\n" +
                "FROM BILL B JOIN CONSUME C ON B.ID_BILL = C.ID_BILL JOIN NECESSITIES N ON C.ID_NECESSITIES = N.ID_NECESSITIES\n" +
                "WHERE B.ID_USER = ?";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, id_value);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                java.sql.Date date = rs.getDate(2);
                int time_limit = rs.getInt(4);
                if (checkDateConsume(date, time_limit)) {
                    String id = rs.getString(1);
                    String name = rs.getString(3).replaceAll("(\\r|\\n)", "");
                    int quantity = rs.getInt(5);
                    used_lst.add(new NecessityHistoryModel(id, date, name, time_limit, quantity));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setData(String search_text) {
        String sql = "SELECT *\n" +
                "FROM NECESSITIES\n" +
                "WHERE NAME LIKE ?";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, "%" + search_text + "%");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                if (rs.getInt(8) == 0) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int limit = rs.getInt(3);
                    int price = rs.getInt(4);
                    int time_limit = rs.getInt(5);
                    String type = rs.getString(6);
                    lst.add(new NecessityModel(id, name, limit, price, time_limit, type));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getData(String nec_type) {
        String[][] temp_data = new String[lst.size()][3];
        int num_of_data = 0;
        if (this.lst != null) {
            int i = 0;

            for (NecessityModel f: lst) {
                String f_type = f.getType().trim();
                if (nec_type != "" && nec_type != "Tất cả" && !Objects.equals(f_type, nec_type)) {
                    continue;
                }
                temp_data[i][2] = f_type;
                temp_data[i][1] = f.getName().trim();
                temp_data[i][0] = String.valueOf(f.getId());
                i++;
            }
            num_of_data = i;
        }

        data = new String[num_of_data][3];

        for (int i = 0; i < data.length; i++) {
            data[i] = temp_data[i].clone();
        }
    }

    public void getAllData() {
        String[][] temp_data = new String[lst.size()][6];
        int num_of_data = 0;
        if (this.lst != null) {
            int i = 0;

            for (NecessityModel f: lst) {
                temp_data[i][0] = String.valueOf(f.getId());
                temp_data[i][1] = f.getType().trim();
                temp_data[i][2] = f.getName().trim();
                temp_data[i][3] = String.valueOf(f.getPrice());
                temp_data[i][4] = String.valueOf(f.getLimit());
                temp_data[i][5] = convertTimeLimit(f.getTime_limit());
                if (used_lst.size() != 0) {
                    for (int j = 0; j < used_lst.size(); j++) {
                        if (Objects.equals(used_lst.elementAt(j).getName(), temp_data[i][2])) {
                            temp_data[i][4] = String.valueOf(f.getLimit() - used_lst.elementAt(j).getQuantity());
                            used_lst.remove(j);
                        }
                    }
                }
                i++;
            }
            num_of_data = i;
        }

        data = new String[num_of_data][6];

        for (int i = 0; i < data.length; i++) {
            data[i] = temp_data[i].clone();
        }
    }

    public boolean checkDateConsume(java.sql.Date d, int time_limit) {
        LocalDate today = LocalDate.now().now();
        LocalDate consume_day = new java.sql.Date(d.getTime()).toLocalDate();

        if (time_limit == 1) {
            return Objects.equals(consume_day, today);
        }
        else if (time_limit == 7) {
            while (!Objects.equals(today.getDayOfWeek().toString(), "MONDAY")) {
                today = today.minusDays(1);
            }
            return !consume_day.isBefore(today);
        }
        return Objects.equals(consume_day.getMonth(), today.getMonth());
    }

    public String convertTimeLimit(int v) {
        String str = "";
        if (v == 1) str = "Ngày";
        else if (v == 7) str = "Tuần";
        else if (v == 30) str = "Tháng";

        return str;
    }

    public String[][] getData() {
        return data;
    }
}
