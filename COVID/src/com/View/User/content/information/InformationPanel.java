package com.View.User.content.information;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    public InformationPanel() {
        this.setLayout(new GridLayout(5,1,0,0));
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760,600));
        this.setOpaque(false);
    }
}
