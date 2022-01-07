package com.View.User.content.necessity;

import javax.swing.*;
import java.awt.*;

public class NecessityPurchasePanel extends JPanel {
    Font content_title_font = new Font("Title",Font.BOLD,40);

    public NecessityPurchasePanel() {}
    public NecessityPurchasePanel(String id_value) {
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760,600));
        this.setOpaque(false);
    }
}
