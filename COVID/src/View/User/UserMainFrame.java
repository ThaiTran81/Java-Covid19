package View.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class UserMainFrame extends JFrame implements ActionListener {
    private JLayeredPane main_layer;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel homePanel;
    private JPanel informationPanel;
    private JPanel necessityPanel;
    private JPanel paymentPanel;

//    GradientPanel content_background = new GradientPanel(new Color(255,140,0), new Color(255,169,0), 0);
//    GradientPanel sidebar_background = new GradientPanel(new Color(0,255,0), new Color(173,255,47), 3);
    Font sidebar_font = new Font("Task", Font.BOLD, 20);
    Font content_title_font = new Font("Title",Font.BOLD,40);
    Font text_font = new Font("Text",Font.BOLD,20);

    public UserMainFrame() {
        this.setResizable(false);
        this.setTitle("Covid-19 Information Manage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);

        sidebarPanel = new JPanel();
        sidebarPanel.setOpaque(true);
        sidebarPanel.setBounds(0,0,239,1000);
        sidebarPanel.setBackground(new Color(255,169,0));
        addSideBar();

        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setBounds(239,0,1000-240,600);
        contentPanel.setBackground(Color.black);
        addContent();

        main_layer = new JLayeredPane();
        main_layer.setBounds(0,0,2000,1000);
        main_layer.add(contentPanel);
        main_layer.add(sidebarPanel);

        this.add(main_layer);
        this.setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.repaint();
        contentPanel.revalidate();
    }

    public void addSideBar() {
        JToolBar sidebar = new JToolBar(JToolBar.VERTICAL);
        sidebar.setLayout(new GridLayout(4,1,0,0));
        sidebar.setOpaque(false);
        sidebar.setBackground(Color.green);
        sidebar.setFloatable(false);
//        sidebar.setMargin(new Insets(0, 5, 0, 5));

        JButton home_button = new JButton("    Home ");
        home_button.setIcon(new ImageIcon("src\\com\\cv19\\icon\\home_icon.png"));
        home_button.setHorizontalAlignment(SwingConstants.LEFT);
        home_button.setOpaque(false);
        home_button.setPreferredSize(new Dimension(240,50));
        home_button.setBorder(new EmptyBorder(0,15,0,0));
        home_button.setForeground(Color.black);
        home_button.setFocusable(false);
        home_button.setFont(sidebar_font);
        home_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(homePanel);
            }
        });

        JButton information_button = new JButton("    Information");
        information_button.setIcon(new ImageIcon("src\\com\\cv19\\icon\\information_icon.png"));
        information_button.setHorizontalAlignment(SwingConstants.LEFT);
        information_button.setOpaque(false);
        information_button.setPreferredSize(new Dimension(240,50));
        information_button.setBorder(new EmptyBorder(0,15,0,0));
        information_button.setFocusable(false);
        information_button.setFont(sidebar_font);
        information_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(informationPanel);
            }
        });

        JButton necessity_button = new JButton("    Necessity");
        necessity_button.setIcon(new ImageIcon("src\\com\\cv19\\icon\\necessity_icon.png"));
        necessity_button.setHorizontalAlignment(SwingConstants.LEFT);
        necessity_button.setOpaque(false);
        necessity_button.setPreferredSize(new Dimension(240,50));
        necessity_button.setBorder(new EmptyBorder(0,15,0,0));
        necessity_button.setFocusable(false);
        necessity_button.setFont(sidebar_font);
        necessity_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(necessityPanel);
            }
        });

        JButton payment_button = new JButton("    Payment");
        payment_button.setIcon(new ImageIcon("src\\com\\cv19\\icon\\payment_icon.png"));
        payment_button.setHorizontalAlignment(SwingConstants.LEFT);
        payment_button.setOpaque(false);
        payment_button.setPreferredSize(new Dimension(240,50));
        payment_button.setBorder(new EmptyBorder(0,15,0,0));
        payment_button.setFocusable(false);
        payment_button.setFont(sidebar_font);
        payment_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel(paymentPanel);
            }
        });

        sidebar.add(home_button);
        sidebar.add(information_button);
        sidebar.add(necessity_button);
        sidebar.add(payment_button);
        sidebarPanel.add(sidebar);
    }

    private JPanel personalInformationPanel;
    private JPanel managedHistoryPanel;
    private JPanel necessityHistoryPanel;
    private JPanel checkDebtPanel;
    private JPanel paymentHistoryPanel;

    public void addContent() {
        homePanel = new JPanel();
        informationPanel = new JPanel();
        necessityPanel = new JPanel();
        paymentPanel = new JPanel();
        contentPanel.add(homePanel);

        informationPanel.setLayout(new GridLayout(4,1,0,0));
        informationPanel.setBackground(Color.blue);
        informationPanel.setPreferredSize(new Dimension(760,600));
        informationPanel.setOpaque(true);

        JPanel information_title = new JPanel();
        JLabel information_text_title = new JLabel("Information");
        information_text_title.setFont(content_title_font);
        information_title.add(information_text_title);

        JButton personal_information_button = new JButton("Xem thông tin cá nhân");
        personal_information_button.setBorder(new LineBorder(Color.black, 1));
        personal_information_button.setFocusable(false);
        personal_information_button.setPreferredSize(new Dimension(240,50));
        personal_information_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPersonalInformationPanel();
            }
        });

        JButton managed_history_button = new JButton("Lịch sử được quản lý");
        managed_history_button.setBorder(new LineBorder(Color.black, 1));
        managed_history_button.setFocusable(false);
        managed_history_button.setPreferredSize(new Dimension(240,50));
        managed_history_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToManagedHistoryPanel();
            }
        });

        JButton necessity_history_button = new JButton("Lịch sử tiêu thụ gói Nhu yếu phẩm");
        necessity_history_button.setBorder(new LineBorder(Color.black, 1));
        necessity_history_button.setFocusable(false);
        necessity_history_button.setPreferredSize(new Dimension(240,50));
        necessity_history_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToNecessityHistoryPanel();
            }
        });

        JButton check_debt_button = new JButton("Xem dư nợ");
        check_debt_button.setBorder(new LineBorder(Color.black, 1));
        check_debt_button.setFocusable(false);
        check_debt_button.setPreferredSize(new Dimension(240,50));
        check_debt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToCheckDebtPanel();
            }
        });

        JButton payment_history_button = new JButton("Lịch sử thanh toán");
        payment_history_button.setBorder(new LineBorder(Color.black, 1));
        payment_history_button.setFocusable(false);
        payment_history_button.setPreferredSize(new Dimension(240,50));
        payment_history_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPaymentHistoryPanel();
            }
        });

        JPanel information_content_panel1 = new JPanel();
        information_content_panel1.add(personal_information_button);
        information_content_panel1.add(managed_history_button);

        JPanel information_content_panel2 = new JPanel();
        information_content_panel2.add(necessity_history_button);
        information_content_panel2.add(check_debt_button);

        JPanel information_content_panel3 = new JPanel();
        information_content_panel3.add(payment_history_button);

        homePanel.add(new JLabel("Home"));
        informationPanel.add(information_title);
        informationPanel.add(information_content_panel1);
        informationPanel.add(information_content_panel2);
        informationPanel.add(information_content_panel3);
        necessityPanel.add(new JLabel("Necessity"));
        paymentPanel.add(new JLabel("Payment"));
    }

    public void switchToPersonalInformationPanel() {
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

        personalInformationPanel = new JPanel();
        personalInformationPanel.setBackground(Color.blue);
        personalInformationPanel.setPreferredSize(new Dimension(760,600));
        personalInformationPanel.setOpaque(false);
        personalInformationPanel.add(title);
        personalInformationPanel.add(none);
        personalInformationPanel.add(ct);

        switchPanel(personalInformationPanel);
    }

    public void switchToManagedHistoryPanel() {
        JLabel title = new JLabel("Lịch sử được quản lý");
        title.setFont(content_title_font);

        managedHistoryPanel = new JPanel();
        managedHistoryPanel.setBackground(Color.red);
        managedHistoryPanel.setPreferredSize(new Dimension(760,600));
        managedHistoryPanel.setOpaque(false);
        managedHistoryPanel.add(title);

        switchPanel(managedHistoryPanel);
    }

    public void switchToNecessityHistoryPanel() {
        JLabel title = new JLabel("Lịch sử tiêu thụ gói Nhu yếu phẩm");
        title.setFont(content_title_font);

        necessityHistoryPanel = new JPanel();
        necessityHistoryPanel.setBackground(Color.green);
        necessityHistoryPanel.setPreferredSize(new Dimension(760,600));
        necessityHistoryPanel.setOpaque(false);
        necessityHistoryPanel.add(title);

        switchPanel(necessityHistoryPanel);
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

        switchPanel(checkDebtPanel);
    }

    public void switchToPaymentHistoryPanel() {
        JLabel title = new JLabel("Lịch sử thanh toán");
        title.setFont(content_title_font);

        paymentHistoryPanel = new JPanel();
        paymentHistoryPanel.setBackground(Color.cyan);
        paymentHistoryPanel.setPreferredSize(new Dimension(760,600));
        paymentHistoryPanel.setOpaque(false);
        paymentHistoryPanel.add(title);

        switchPanel(paymentHistoryPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
