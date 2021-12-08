package View.User.content;

import View.User.content.account.AcountPanel;
import View.User.content.information.InformationPanel;
import View.User.content.necessity.NecessityPanel;
import View.User.content.payment.PaymentPanel;

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
