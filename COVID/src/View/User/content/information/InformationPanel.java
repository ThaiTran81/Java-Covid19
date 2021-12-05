package View.User.content.information;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends JPanel {

    public InformationPanel() {
        this.setLayout(new GridLayout(5,1,0,0));
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760,600));
        this.setOpaque(false);
    }
}
