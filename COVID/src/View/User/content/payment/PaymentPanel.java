package View.User.content.payment;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentPanel extends JPanel {

    PaymentPanel() {}
    public PaymentPanel(String id_value) {
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760, 600));
        this.setOpaque(false);
    }
}
