package View.User.content.necessity;

import Controller.CovidDAO;
import Model.NecessityHistoryModel;
import Model.NecessityModel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class NecessityTablePanel extends JPanel {
    Vector<NecessityModel> lst = new Vector<>();
    Vector<NecessityHistoryModel> used_lst = new Vector<>();
    String[][] data;

    public NecessityTablePanel() {}
    public NecessityTablePanel(String id_value) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(690,320));

        setData();
        getUsedData(id_value);
        data = new String[lst.size()][4];
        getData("");

        if (data == null) {
            this.add(new JLabel("Error."));
        }

        String[] columns = {"Loại nhu yếu phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng mua còn lại"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(680,300));

        this.add(sp);
    }

    public NecessityTablePanel(String id_value, String nec_type) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(690,320));

        setData();
        getUsedData(id_value);
        data = new String[lst.size()][4];
        getData(nec_type);

        if (data == null) {
            this.add(new JLabel("Error."));
        }

        String[] columns = {"Loại nhu yếu phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng mua còn lại"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(680,300));

        this.add(sp);
    }

    public NecessityTablePanel(String id_value, String nec_type, String sort_type) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(690,320));

        setData();
        getUsedData(id_value);
        data = new String[lst.size()][4];
        getData(nec_type);
        sortData(sort_type);

        if (data == null) {
            this.add(new JLabel("Error."));
        }

        String[] columns = {"Loại nhu yếu phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng mua còn lại"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(680,300));

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
                    String name = rs.getString(3);
                    int quantity = rs.getInt(5);
                    used_lst.add(new NecessityHistoryModel(id, date, name, time_limit, quantity));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setData() {
        String sql = "SELECT *\n" +
                "FROM NECESSITIES";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String name = rs.getString(2);
                int limit = rs.getInt(3);
                String price = rs.getString(4);
                int time_limit = rs.getInt(5);
                String type = rs.getString(6);
                lst.add(new NecessityModel(name, limit, price, time_limit, type));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getData(String nec_type) {
        if (this.lst != null) {
            int i = 0;

            for (NecessityModel f: lst) {
                String f_type = f.getType().trim();
                if (nec_type != "" && nec_type != "Tất cả" && !Objects.equals(f_type, nec_type)) {
                    continue;
                }
                data[i][0] = f_type;
                data[i][1] = f.getName().trim();
                data[i][2] = f.getPrice();
                data[i][3] = String.valueOf(f.getLimit());
                if (used_lst.size() != 0) {
                    for (int j = 0; j < used_lst.size(); j++) {
                        if (Objects.equals(used_lst.elementAt(j).getName(), data[i][1])) {
                            data[i][3] = String.valueOf(f.getLimit() - used_lst.elementAt(j).getQuantity());
                            used_lst.remove(j);
                        }
                    }
                }
                i++;
            }
        }
    }

    public boolean checkDateConsume(java.sql.Date d, int time_limit) {
        LocalDate today = LocalDate.now().minusDays(12);
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

    public void sortData(String sort_type) {
        if (Objects.equals(sort_type, "Mặc định")) {
            return;
        }
        int column_index = 0;
        if (Objects.equals(sort_type, "Tên")) {
            column_index = 1;
        }
        else if (Objects.equals(sort_type, "Giá tăng dần") || Objects.equals(sort_type, "Giá giảm dần")) {
            column_index = 2;
        }
        else if (Objects.equals(sort_type, "Lượng mua còn lại")) {
            column_index = 3;
        }

        String[] cl = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            cl[i] = data[i][column_index];
        }
        Arrays.sort(cl);

        String[] temp;
        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length - 1; j++) {
                if (cl[i] == data[j][column_index]) {
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                    break;
                }
            }
        }

//        int n = data.length, i, j;
//        boolean swapped;
//        String[] temp;
//        for (i = 0; i < n - 1; i++) {
//            swapped = false;
//            for (j = 0; j < n - i - 1; j++) {
//                if (compareData(data[j][column_index], data[j + 1][column_index])) {
//                    temp = data[j];
//                    data[j] = data[j + 1];
//                    data[j + 1] = temp;
//                    swapped = true;
//                }
//            }
//            if (!swapped) break;
//        }
    }

    public boolean compareData(String data1, String data2) {
        int i = 0, j = 0;
        while (true) {
            if (i + 1 == data1.length()) {
                return false;
            }
            else if (j + 1 == data2.length()) {
                return true;
            }
//            System.out.println(data1.charAt(i));
//            if (data1.charAt(i) == 0) {
//                i++;
//                continue;
//            }
//            if (data2.charAt(j) == 0) {
//                j++;
//                continue;
//            }
            if (data1.charAt(i) > data2.charAt(j)) {
                return true;
            }
            i++;
            j++;
        }
    }
}
