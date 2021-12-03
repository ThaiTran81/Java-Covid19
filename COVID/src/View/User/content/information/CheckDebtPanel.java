package View.User.content.information;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDebtPanel extends JPanel {
    Font content_title_font = new Font("Title",Font.BOLD,40);
    Font text_font = new Font("Text",Font.BOLD,20);

    public CheckDebtPanel() {}
    public CheckDebtPanel(String id_value) {
        JLabel title = new JLabel("Thông tin cá nhân");
        title.setFont(content_title_font);

        JLabel debt = new JLabel();
        debt.setText("Số tiền cần thanh toán:");
        debt.setFont(text_font);


        String sql = "SELECT *\n" +
                "FROM DEBT\n" +
                "WHERE [USER_ID] = ?";
        try (Connection conn = Controller.ConnectToDBController.getSqlConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, id_value);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                debt.setText("Số tiền cần thanh toán: " + rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,50));
        none.setBackground(Color.white);
        none.setOpaque(true);
        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(6,1,0,0));
        ct.setBackground(Color.yellow);
        ct.setOpaque(false);
        ct.setPreferredSize(new Dimension(600,250));
        ct.add(debt);

        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760, 600));
        this.setOpaque(false);
        this.add(title);
        this.add(none);
        this.add(ct);
    }
}
