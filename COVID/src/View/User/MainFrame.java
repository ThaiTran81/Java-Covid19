package View.User;

import View.User.content.ContentPanel;
import View.User.content.account.AcountPanel;
import View.User.content.account.AcountPanel;
import View.User.content.information.*;
import View.User.content.necessity.NecessityPanel;
import View.User.content.necessity.NecessityTablePanel;
import View.User.content.payment.PaymentPanel;
import View.User.sidebar.SideBarButton;
import View.User.sidebar.SideBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainFrame extends JFrame implements ActionListener {
    private JLayeredPane main_layer;
    private JLayeredPane sidebar_layer;
    private JLayeredPane content_layer;
    private String id;

    String getId() {
        return this.id;
    }

    Font content_title_font = new Font("Title",Font.BOLD,40);
    Font text_font = new Font("Text",Font.BOLD,15);

    public MainFrame(){}
    public MainFrame(String id) {
        this.id = id;
        setResizable(false);
        setTitle("Covid-19 Information Manage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        sidebar_layer = new JLayeredPane();
        sidebar_layer.setBounds(0,0,239,1000);
        addSidebar();

        content_layer = new JLayeredPane();
        content_layer.setBounds(239,0,762,1000);
        addContent();

        main_layer = new JLayeredPane();
        main_layer.setBounds(0,0,2000,1000);
        main_layer.add(sidebar_layer);
        main_layer.add(content_layer);

        add(main_layer);
        setVisible(true);
    }

    private SideBarPanel side_bar;
    private SideBarButton information_button;
    private SideBarButton necessity_button;
    private SideBarButton payment_button;
    private SideBarButton account_button;
    private SideBarButton logout_button;

    public void addSidebar() {
        side_bar = new SideBarPanel();

        JToolBar sidebar = new JToolBar(JToolBar.VERTICAL);
        sidebar.setLayout(new GridLayout(5,1,0,0));
        sidebar.setFloatable(false);
        sidebar.setOpaque(false);

        information_button = new SideBarButton("    Thông tin", "src\\com\\cv19\\icon\\information_icon.png");
        information_button.addActionListener(this);
        necessity_button = new SideBarButton("    Nhu yếu phẩm", "src\\com\\cv19\\icon\\necessity_icon.png");
        necessity_button.addActionListener(this);
        payment_button = new SideBarButton("    Thanh toán", "src\\com\\cv19\\icon\\payment_icon.png");
        payment_button.addActionListener(this);
        account_button = new SideBarButton("    Đổi mật khẩu", "src\\com\\cv19\\icon\\password_icon.png");
        account_button.addActionListener(this);
        logout_button = new SideBarButton("    Đăng xuất", "src\\com\\cv19\\icon\\logout_icon.png");
        logout_button.addActionListener(this);

        sidebar.add(information_button);
        sidebar.add(necessity_button);
        sidebar.add(payment_button);
        sidebar.add(account_button);
        sidebar.add(logout_button);

        side_bar.add(sidebar);
        sidebar_layer.add(side_bar);
    }

    private ContentPanel content;
    private AcountPanel account_panel;
    private InformationPanel information_panel;
    private NecessityPanel necessity_panel;
    private PaymentPanel payment_panel;

    public void addContent() {
        account_panel = new AcountPanel();
        information_panel = new InformationPanel();
        necessity_panel = new NecessityPanel();
        payment_panel = new PaymentPanel();

        content = new ContentPanel();
//        content.add(account_panel);

        content_layer.add(content);
    }

    public void switchPanel(JPanel panel) {
        content.removeAll();
        content.add(panel);
        content.repaint();
        content.revalidate();
    }

    private InformationButton personal_information_button;
    private InformationButton managed_history_button;
    private InformationButton necessity_history_button;
    private InformationButton check_debt_button;
    private InformationButton payment_history_button;

    public void showInformationPanel() {
        information_panel.removeAll();

        JPanel none1 = new JPanel();
        none1.setPreferredSize(new Dimension(80,50));
        JPanel none2 = new JPanel();
        none2.setPreferredSize(new Dimension(80,50));
        JPanel information_title = new JPanel();
        JLabel information_text_title = new JLabel("Thông tin");
        information_text_title.setFont(content_title_font);
        information_title.add(information_text_title);

        personal_information_button = new InformationButton("Xem thông tin cá nhân");
        personal_information_button.addActionListener(this);
        managed_history_button = new InformationButton("Lịch sử được quản lý");
        managed_history_button.addActionListener(this);
        necessity_history_button = new InformationButton("Lịch sử tiêu thụ gói Nhu yếu phẩm");
        necessity_history_button.addActionListener(this);
        check_debt_button = new InformationButton("Xem dư nợ");
        check_debt_button.addActionListener(this);
        payment_history_button = new InformationButton("Lịch sử thanh toán");
        payment_history_button.addActionListener(this);

        JPanel information_content_panel1 = new JPanel();
        information_content_panel1.add(personal_information_button);
        information_content_panel1.add(none1);
        information_content_panel1.add(managed_history_button);

        JPanel information_content_panel2 = new JPanel();
        information_content_panel2.add(necessity_history_button);
        information_content_panel2.add(none2);
        information_content_panel2.add(check_debt_button);

        JPanel information_content_panel3 = new JPanel();
        information_content_panel3.add(payment_history_button);

        information_panel.add(information_title);
        information_panel.add(information_content_panel1);
        information_panel.add(information_content_panel2);
        information_panel.add(information_content_panel3);
        information_panel.add(new JPanel());
    }

    private JTextField search_bar = new JTextField("Tìm kiếm");
    private JButton search_button = new JButton("Tìm");
    private JComboBox filter_combo_box;
    private JComboBox sort_combo_box;
    private NecessityTablePanel necessity_table_panel;

    public void showNecessityPanel() {
        necessity_panel.removeAll();

        JPanel necessity_title = new JPanel();
        necessity_title.setBackground(Color.white);
        necessity_title.setPreferredSize(new Dimension(720,100));
        JLabel necessity_text_title = new JLabel("Mua nhu yếu phẩm");
        necessity_text_title.setFont(content_title_font);
        necessity_title.add(necessity_text_title);

        search_bar.setPreferredSize(new Dimension(180,30));
        search_bar.setForeground(new Color(153, 153, 153));
        search_bar.addActionListener(this);
        search_bar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (search_bar.getText().equals("Tìm kiếm")) {
                    search_bar.setText("");
                    search_bar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (search_bar.getText().trim().equals("") || search_bar.getText().equals("Tìm kiếm")) {
                    search_bar.setText("Tìm kiếm");
                    search_bar.setForeground(new Color(153, 153, 153));
                }
            }
        });

        search_button.setFocusable(false);
        search_button.setPreferredSize(new Dimension(70,30));
        search_button.setFont(text_font);

        JLabel filter_label = new JLabel("Loại: ");
        String[] necessity_type = {"Tất cả", "Thực phẩm ăn liền", "Rau củ", "Đồ uống", "Sữa", "Tắm gội", "Đồ gia dụng"};
        filter_combo_box = new JComboBox(necessity_type);

        JLabel sort_label = new JLabel("Sắp xếp theo: ");
        String[] necessity_columns = {"Mặc định" ,"Tên", "Giá tăng dần", "Giá giảm dần",  "Lượng mua còn lại"};
        sort_combo_box = new JComboBox(necessity_columns);

        JPanel task_bar_panel = new JPanel();
        task_bar_panel.setBackground(Color.white);
        task_bar_panel.setPreferredSize(new Dimension(740,50));
        task_bar_panel.add(search_bar);
        task_bar_panel.add(search_button);
        task_bar_panel.add(filter_label);
        task_bar_panel.add(filter_combo_box);
        task_bar_panel.add(sort_label);
        task_bar_panel.add(sort_combo_box);

        necessity_table_panel = new NecessityTablePanel(getId());
        JPanel necessity_content = new JPanel();
        necessity_content.setPreferredSize(new Dimension(700,320));
        necessity_content.setBackground(Color.white);
        necessity_content.add(necessity_table_panel);

        necessity_panel.add(necessity_title);
        necessity_panel.add(task_bar_panel);
        necessity_panel.add(necessity_content);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == information_button) {
            showInformationPanel();
            switchPanel(information_panel);
        }
        if (e.getSource() == necessity_button) {
            showNecessityPanel();
            switchPanel(necessity_panel);
        }
        if (e.getSource() == payment_button) {
            switchPanel(payment_panel);
        }
        if (e.getSource() == account_button) {
            switchPanel(account_panel);
        }
        if (e.getSource() == logout_button) {

        }
        if (e.getSource() == personal_information_button) {
            switchPanel(new PersonalInformationPanel(getId()));
        }
        if (e.getSource() == managed_history_button) {
            switchPanel(new ManagedHistoryPanel(getId()));
        }
        if (e.getSource() == necessity_history_button) {
            switchPanel(new NecessityHistoryPanel(getId()));
        }
        if (e.getSource() == check_debt_button) {
            switchPanel(new CheckDebtPanel(getId()));
        }
        if (e.getSource() == payment_history_button) {
            switchPanel(new PaymentHistoryPanel(getId()));
        }
        if (e.getSource() == search_bar) {
            search_bar.setText("");
        }
    }
}
