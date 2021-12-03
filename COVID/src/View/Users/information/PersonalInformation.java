package View.Users.information;

import javax.swing.*;
import java.awt.*;

public class PersonalInformation extends JPanel {
    Font content_title_font = new Font("Title",Font.BOLD,40);
    Font text_font = new Font("Text",Font.BOLD,20);

    public PersonalInformation() {
        JLabel title = new JLabel("Thông tin cá nhân");
        title.setFont(content_title_font);

        JLabel name = new JLabel();
        name.setText("Họ tên:");
        name.setFont(text_font);
        JLabel id = new JLabel();
        id.setText("Số CMND/CCCD:");
        id.setFont(text_font);
        JLabel dob = new JLabel();
        dob.setText("Năm sinh:");
        dob.setFont(text_font);
        JLabel address = new JLabel();
        address.setText("Địa chỉ nơi ở:");
        address.setFont(text_font);
        JLabel status = new JLabel();
        status.setText("Trạng thái hiện tại:");
        status.setFont(text_font);
        JLabel quaratine = new JLabel();
        quaratine.setText("Nơi đang điều trị:");
        quaratine.setFont(text_font);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(500,50));
        none.setBackground(Color.blue);
        none.setOpaque(false);
        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(6,1,0,0));
        ct.setBackground(Color.yellow);
        ct.setOpaque(false);
        ct.setPreferredSize(new Dimension(600,250));
        ct.add(name);
        ct.add(id);
        ct.add(dob);
        ct.add(address);
        ct.add(status);
        ct.add(quaratine);

        setLayout(new FlowLayout());
        setBackground(Color.blue);
        setPreferredSize(new Dimension(760,600));
        setOpaque(true);
        add(title);
        add(none);
        add(ct);
    }
}
