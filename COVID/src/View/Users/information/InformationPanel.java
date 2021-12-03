package View.Users.information;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationPanel extends JPanel implements ActionListener {
    private PersonalInformation personalInformationPanel = new PersonalInformation();
    private JPanel contentPanel;
    private JPanel managedHistoryPanel;
    private JPanel necessityHistoryPanel;
    private JPanel checkDebtPanel;
    private JPanel paymentHistoryPanel;

    public JButton personal_information_button;
    JButton managed_history_button;
    JButton necessity_history_button;
    JButton check_debt_button;
    JButton payment_history_button;

    Font text_font = new Font("Text",Font.BOLD,20);
    Font content_title_font = new Font("Title",Font.BOLD,40);

    public void switchPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.repaint();
        contentPanel.revalidate();
    }

    public InformationPanel() {
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setBounds(239,0,1000-240,600);
        contentPanel.setBackground(Color.black);
        addContent();

    }

    public void addContent() {
        setLayout(new GridLayout(4,1,0,0));
        setBackground(Color.blue);
        setPreferredSize(new Dimension(760,600));
        setOpaque(false);

        JPanel information_title = new JPanel();
        JLabel information_text_title = new JLabel("Information");
        information_text_title.setFont(content_title_font);
        information_title.add(information_text_title);

        personal_information_button = new JButton("Xem thông tin cá nhân");
        personal_information_button.setBorder(new LineBorder(Color.black, 1));
        personal_information_button.setFocusable(false);
        personal_information_button.setPreferredSize(new Dimension(240,50));

        managed_history_button = new JButton("Lịch sử được quản lý");
        managed_history_button.setBorder(new LineBorder(Color.black, 1));
        managed_history_button.setFocusable(false);
        managed_history_button.setPreferredSize(new Dimension(240,50));

        necessity_history_button = new JButton("Lịch sử tiêu thụ gói Nhu yếu phẩm");
        necessity_history_button.setBorder(new LineBorder(Color.black, 1));
        necessity_history_button.setFocusable(false);
        necessity_history_button.setPreferredSize(new Dimension(240,50));

        check_debt_button = new JButton("Xem dư nợ");
        check_debt_button.setBorder(new LineBorder(Color.black, 1));
        check_debt_button.setFocusable(false);
        check_debt_button.setPreferredSize(new Dimension(240,50));

        payment_history_button = new JButton("Lịch sử thanh toán");
        payment_history_button.setBorder(new LineBorder(Color.black, 1));
        payment_history_button.setFocusable(false);
        payment_history_button.setPreferredSize(new Dimension(240,50));

        JPanel information_content_panel1 = new JPanel();
        information_content_panel1.add(personal_information_button);
        information_content_panel1.add(managed_history_button);

        JPanel information_content_panel2 = new JPanel();
        information_content_panel2.add(necessity_history_button);
        information_content_panel2.add(check_debt_button);

        JPanel information_content_panel3 = new JPanel();
        information_content_panel3.add(payment_history_button);

        add(information_title);
        add(information_content_panel1);
        add(information_content_panel2);
        add(information_content_panel3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    public JPanel switchToPersonalInformationPanel() {
//        JLabel title = new JLabel("Thông tin cá nhân");
//        title.setFont(content_title_font);
//
//        JLabel name = new JLabel();
//        name.setText("Họ tên:");
//        name.setFont(text_font);
//        JLabel id = new JLabel();
//        id.setText("Số CMND/CCCD:");
//        id.setFont(text_font);
//        JLabel dob = new JLabel();
//        dob.setText("Năm sinh:");
//        dob.setFont(text_font);
//        JLabel address = new JLabel();
//        address.setText("Địa chỉ nơi ở:");
//        address.setFont(text_font);
//        JLabel status = new JLabel();
//        status.setText("Trạng thái hiện tại:");
//        status.setFont(text_font);
//        JLabel quaratine = new JLabel();
//        quaratine.setText("Nơi đang điều trị:");
//        quaratine.setFont(text_font);
//
//        JPanel none = new JPanel();
//        none.setPreferredSize(new Dimension(500,50));
//        none.setBackground(Color.blue);
//        none.setOpaque(false);
//        JPanel ct = new JPanel();
//        ct.setLayout(new GridLayout(6,1,0,0));
//        ct.setBackground(Color.yellow);
//        ct.setOpaque(false);
//        ct.setPreferredSize(new Dimension(600,250));
//        ct.add(name);
//        ct.add(id);
//        ct.add(dob);
//        ct.add(address);
//        ct.add(status);
//        ct.add(quaratine);
//
//        personalInformationPanel = new JPanel();
//        personalInformationPanel.setBackground(Color.blue);
//        personalInformationPanel.setPreferredSize(new Dimension(760,600));
//        personalInformationPanel.setOpaque(false);
//        personalInformationPanel.add(title);
//        personalInformationPanel.add(none);
//        personalInformationPanel.add(ct);
//
//        return personalInformationPanel;
//    }

    public void switchToManagedHistoryPanel() {
        JLabel title = new JLabel("Lịch sử được quản lý");
        title.setFont(content_title_font);

        managedHistoryPanel = new JPanel();
        managedHistoryPanel.setBackground(Color.red);
        managedHistoryPanel.setPreferredSize(new Dimension(760,600));
        managedHistoryPanel.setOpaque(false);
        managedHistoryPanel.add(title);


    }

    public void switchToNecessityHistoryPanel() {
        JLabel title = new JLabel("Lịch sử tiêu thụ gói Nhu yếu phẩm");
        title.setFont(content_title_font);

        necessityHistoryPanel = new JPanel();
        necessityHistoryPanel.setBackground(Color.green);
        necessityHistoryPanel.setPreferredSize(new Dimension(760,600));
        necessityHistoryPanel.setOpaque(false);
        necessityHistoryPanel.add(title);


    }

    public void switchToCheckDebtPanel() {
        JLabel title = new JLabel("Dư nợ");
        title.setFont(content_title_font);

        JLabel debt = new JLabel();
        debt.setText("Số tiền cần thanh toán:");
        debt.setFont(text_font);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,50));
        none.setBackground(Color.blue);
        none.setOpaque(false);
        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(6,1,0,0));
        ct.setBackground(Color.yellow);
        ct.setOpaque(false);
        ct.setPreferredSize(new Dimension(600,250));
        ct.add(debt);

        checkDebtPanel = new JPanel();
        checkDebtPanel.setBackground(Color.yellow);
        checkDebtPanel.setPreferredSize(new Dimension(760,600));
        checkDebtPanel.setOpaque(false);
        checkDebtPanel.add(title);
        checkDebtPanel.add(none);
        checkDebtPanel.add(ct);


    }

    public void switchToPaymentHistoryPanel() {
        JLabel title = new JLabel("Lịch sử thanh toán");
        title.setFont(content_title_font);

        paymentHistoryPanel = new JPanel();
        paymentHistoryPanel.setBackground(Color.cyan);
        paymentHistoryPanel.setPreferredSize(new Dimension(760,600));
        paymentHistoryPanel.setOpaque(false);
        paymentHistoryPanel.add(title);


    }
}
