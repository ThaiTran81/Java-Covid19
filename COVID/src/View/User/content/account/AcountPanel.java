package View.User.content.account;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcountPanel extends JPanel {

    AcountPanel() {}
    public AcountPanel(String id_value) {
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760, 600));
        this.setOpaque(false);
    }
}
