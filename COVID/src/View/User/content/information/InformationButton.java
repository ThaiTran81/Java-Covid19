package View.User.content.information;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InformationButton extends JButton{
    Font text_font = new Font("Text",Font.BOLD,12);

    public InformationButton() {}
    public InformationButton(String name) {
        setText(name);
        setBorder(new LineBorder(Color.black, 1));
        setFocusable(false);
        setPreferredSize(new Dimension(200,70));
        setFont(text_font);
    }
}
