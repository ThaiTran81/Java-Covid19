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

        setData("", "");
        getUsedData(id_value);
        getData("", "", "");

        if (data == null) {
            this.add(new JLabel("Error."));
        }

        String[] columns = {"Loại nhu yếu phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng mua còn lại", "Giới hạn mua theo"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(680,300));

        this.add(sp);
    }

    public NecessityTablePanel(String id_value, String search, String nec_type, String sort_type, String time_type) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(690,320));

        setData(search, sort_type);
        getUsedData(id_value);
        data = new String[lst.size()][4];
        getData(nec_type, sort_type, time_type);

        if (data == null) {
            this.add(new JLabel("Error."));
        }

        String[] columns = {"Loại nhu yếu phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng mua còn lại", "Giới hạn mua theo"};
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
                    String name = rs.getString(3).replaceAll("(\\r|\\n)", "");
                    int quantity = rs.getInt(5);
                    used_lst.add(new NecessityHistoryModel(id, date, name, time_limit, quantity));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setData(String search_text, String sort_type) {
        String order_type = "ID_NECESSITIES";
        if (sort_type.length() == 3) order_type = "NAME";
        else if (sort_type.length() == 12) order_type = "PRICE";

        String sql = "SELECT *\n" +
                "FROM NECESSITIES\n" +
                "WHERE NAME LIKE ?\n" +
                "ORDER BY " + order_type;
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, "%" + search_text + "%");
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                String name = rs.getString(2);
                int limit = rs.getInt(3);
                String price = rs.getString(4);
                int time_limit = rs.getInt(5);
                String type = rs.getString(6);
                lst.add(new NecessityModel(name, limit, Integer.parseInt(price), time_limit, type));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getData(String nec_type, String sort_type, String time_type) {
        String[][] temp_data = new String[lst.size()][5];
        int num_of_data = 0;
        if (this.lst != null) {
            int i = 0;

            for (NecessityModel f: lst) {
                String f_type = f.getType().trim();
                if (nec_type != "" && nec_type != "Tất cả" && !Objects.equals(f_type, nec_type)) {
                    continue;
                }
                if (time_type != "" && time_type != "Tất cả" && !Objects.equals(convertTimeLimit(f.getTime_limit()), time_type)) {
                    continue;
                }
                temp_data[i][0] = f_type;
                temp_data[i][1] = f.getName().trim();
                temp_data[i][2] = String.valueOf(f.getPrice());
                temp_data[i][3] = String.valueOf(f.getLimit());
                temp_data[i][4] = convertTimeLimit(f.getTime_limit());
                if (used_lst.size() != 0) {
                    for (int j = 0; j < used_lst.size(); j++) {
                        if (Objects.equals(used_lst.elementAt(j).getName(), temp_data[i][1])) {
                            temp_data[i][3] = String.valueOf(f.getLimit() - used_lst.elementAt(j).getQuantity());
                            used_lst.remove(j);
                        }
                    }
                }
                i++;
            }
            num_of_data = i;
        }

        data = new String[num_of_data][5];

        for (int i = 0; i < data.length; i++) {
            data[i] = temp_data[i].clone();
        }

        if (sort_type.length() == 17) {
            int n = data.length, i, j;
            boolean swapped;
            String[] temp_str;
            for (i = 0; i < n - 1; i++) {
                swapped = false;
                for (j = 0; j < n - i - 1; j++) {
                    if (Integer.parseInt(data[j][3]) > Integer.parseInt(data[j + 1][3])) {
                        temp_str = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp_str;
                        swapped = true;
                    }
                }
                if (!swapped) break;
            }
        }
    }

    public boolean checkDateConsume(java.sql.Date d, int time_limit) {
        LocalDate today = LocalDate.now();
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
}
