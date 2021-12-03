package View.User.content.information;

import javax.swing.*;
import java.awt.*;

public class ManagedHistoryPanel extends JPanel {
    Font content_title_font = new Font("Title",Font.BOLD,40);

    ManagedHistoryPanel() {
        JLabel title = new JLabel("LSDQL");
        title.setFont(content_title_font);

        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(760,600));
        this.setOpaque(false);
        this.add(title);
    }
}
