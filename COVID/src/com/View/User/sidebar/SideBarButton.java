package com.View.User.sidebar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SideBarButton extends JButton {

    SideBarButton() {}
    public SideBarButton(String name, String icon_file_path) {
        setText(name);
        setIcon(new ImageIcon(icon_file_path));
        setHorizontalAlignment(SwingConstants.LEFT);
        setOpaque(false);
        setPreferredSize(new Dimension(240,50));
        setBorder(new EmptyBorder(0,15,0,0));
        setFocusable(false);
        setFont(new Font("Task", Font.BOLD, 20));
    }
}
