package View.User.content.information;

import Controller.CovidDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PersonalInformationPanel extends JPanel {
    Font content_title_font = new Font("Title",Font.BOLD,40);
    Font text_font = new Font("Text",Font.BOLD,20);

    public PersonalInformationPanel() {}
    public PersonalInformationPanel(String id_value) {
        JLabel title = new JLabel("Thông tin cá nhân");
        title.setFont(content_title_font);

        JLabel name = new JLabel();
        name.setText("Họ tên:");
        name.setFont(text_font);
        JLabel gender = new JLabel();
        gender.setText("Giới tính:");
        gender.setFont(text_font);
        JLabel id = new JLabel();
        id.setText("Số CMND/CCCD:");
        id.setFont(text_font);
        JLabel dob = new JLabel();
        dob.setText("Năm sinh:");
        dob.setFont(text_font);
        JLabel address = new JLabel();
        address.setText("Địa chỉ nơi ở:");
        address.setFont(text_font);
        JLabel status = new JLabel();
        status.setText("Trạng thái hiện tại:");
        status.setFont(text_font);
        JLabel quaratine = new JLabel();
        quaratine.setText("Nơi đang điều trị:");
        quaratine.setFont(text_font);

        String sql = "SELECT *\n" +
                "FROM PROFILE P JOIN F_HISTORY F ON P.ID = F.[USER_ID] JOIN QUARATINE Q ON Q.ID_QUARATINE = P.ID_QUARATINE\n" +
                "WHERE P.ID = ? and F.F_DATE >= ALL (\n" +
                "SELECT F1.F_DATE\n" +
                "FROM PROFILE P1 JOIN F_HISTORY F1 ON P1.ID = F1.[USER_ID]\n" +
                "WHERE P.ID = ?)";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setString(1, id_value);
            pre.setString(2, id_value);
            ResultSet rs = pre.executeQuery();

            if(rs.next()){
                name.setText("Họ tên: " + rs.getString(2));
                gender.setText("Giới tính: " + rs.getString(5));
                id.setText("Số CMND/CCCD: " + rs.getString(1));
                dob.setText("Năm sinh: " + rs.getString(4));
                address.setText("Địa chỉ nơi ở: " + rs.getString(8) + ", " + rs.getString(7) + ", " + rs.getString(6));
                status.setText("Trạng thái hiện tại: " + rs.getString(12));
                quaratine.setText("Nơi đang điều trị: " + rs.getString(19));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(500,50));
        none.setBackground(Color.blue);
        none.setOpaque(false);
        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(7,1,0,0));
        ct.setBackground(Color.yellow);
        ct.setOpaque(false);
        ct.setPreferredSize(new Dimension(600,250));
        ct.add(name);
        ct.add(gender);
        ct.add(id);
        ct.add(dob);
        ct.add(address);
        ct.add(status);
        ct.add(quaratine);

        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760,600));
        this.setOpaque(false);
        this.add(title);
        this.add(none);
        this.add(ct);
    }
}
