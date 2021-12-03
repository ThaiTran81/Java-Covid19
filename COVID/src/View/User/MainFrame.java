package View.User;

import View.User.content.ContentPanel;
import View.User.content.home.HomePanel;
import View.User.content.information.*;
import View.User.content.necessity.NecessityPanel;
import View.User.content.payment.PaymentPanel;
import View.User.sidebar.SideBarButton;
import View.User.sidebar.SideBarPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JLayeredPane main_layer;
    private JLayeredPane sidebar_layer;
    private JLayeredPane content_layer;
    private String id;

    String getId() {
        return this.id;
    }

    Font content_title_font = new Font("Title",Font.BOLD,40);

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
    private SideBarButton home_button;
    private SideBarButton information_button;
    private SideBarButton necessity_button;
    private SideBarButton payment_button;

    public void addSidebar() {
        side_bar = new SideBarPanel();

        JToolBar sidebar = new JToolBar(JToolBar.VERTICAL);
        sidebar.setLayout(new GridLayout(4,1,0,0));
        sidebar.setFloatable(false);
        sidebar.setOpaque(false);

        home_button = new SideBarButton("    Home", "src\\com\\cv19\\icon\\home_icon.png");
        home_button.addActionListener(this);
        information_button = new SideBarButton("    Information", "src\\com\\cv19\\icon\\information_icon.png");
        information_button.addActionListener(this);
        necessity_button = new SideBarButton("    Necessity", "src\\com\\cv19\\icon\\necessity_icon.png");
        necessity_button.addActionListener(this);
        payment_button = new SideBarButton("    Payment", "src\\com\\cv19\\icon\\payment_icon.png");
        payment_button.addActionListener(this);

        sidebar.add(home_button);
        sidebar.add(information_button);
        sidebar.add(necessity_button);
        sidebar.add(payment_button);

        side_bar.add(sidebar);
        sidebar_layer.add(side_bar);
    }

    private ContentPanel content;
    private HomePanel home_panel;
    private InformationPanel information_panel;
    private NecessityPanel necessity_panel;
    private PaymentPanel payment_panel;

    public void addContent() {
        home_panel = new HomePanel();
        information_panel = new InformationPanel();
        necessity_panel = new NecessityPanel();
        payment_panel = new PaymentPanel();

        content = new ContentPanel();
        content.add(home_panel);

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
        JLabel information_text_title = new JLabel("Information");
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



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_button) {
            switchPanel(home_panel);
        }
        if (e.getSource() == information_button) {
            showInformationPanel();
            switchPanel(information_panel);
        }
        if (e.getSource() == necessity_button) {
            switchPanel(necessity_panel);
        }
        if (e.getSource() == payment_button) {
            switchPanel(payment_panel);
        }
        if (e.getSource() == personal_information_button) {
            switchPanel(new PersonalInformationPanel(getId()));
        }
        if (e.getSource() == managed_history_button) {
            switchPanel(new ManagedHistoryPanel(getId()));
        }
        if (e.getSource() == necessity_history_button) {
            switchPanel(payment_panel);
        }
        if (e.getSource() == check_debt_button) {
            switchPanel(new CheckDebtPanel(getId()));
        }
        if (e.getSource() == payment_history_button) {
            switchPanel(payment_panel);
        }
    }
}
