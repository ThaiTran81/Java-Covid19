package com.View.User.content;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    public ContentPanel() {
        this.setBounds(0,0,1000-240,600);
        this.setOpaque(true);
        this.setBackground(Color.white);
    }

    public void switchPanel(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.repaint();
        this.revalidate();
    }
}
