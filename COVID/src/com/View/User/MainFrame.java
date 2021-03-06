package com.View.User;

import com.Controller.CovidDAO;
import com.Controller.LoginController;
import com.View.User.content.ContentPanel;
import com.View.User.content.account.AcountPanel;
import com.View.User.content.information.*;
import com.View.User.content.necessity.NecessityPanel;
import com.View.User.content.necessity.NecessityPurchasePanel;
import com.View.User.content.necessity.NecessityPurchaseTablePanel;
import com.View.User.content.necessity.NecessityTablePanel;
import com.View.User.content.payment.PaymentPanel;
import com.View.User.sidebar.SideBarButton;
import com.View.User.sidebar.SideBarPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        showInformationPanel();
        showNecessityPanel();
        showPurchasePanel();
        showAccountPanel();
        showPaymentPanel();
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

        information_button = new SideBarButton("    Th??ng tin", "src\\com\\cv19\\icon\\information_icon.png");
        information_button.addActionListener(this);
        necessity_button = new SideBarButton("    Nhu y???u ph???m", "src\\com\\cv19\\icon\\necessity_icon.png");
        necessity_button.addActionListener(this);
        payment_button = new SideBarButton("    Thanh to??n", "src\\com\\cv19\\icon\\payment_icon.png");
        payment_button.addActionListener(this);
        account_button = new SideBarButton("    ?????i m???t kh???u", "src\\com\\cv19\\icon\\password_icon.png");
        account_button.addActionListener(this);
        logout_button = new SideBarButton("    ????ng xu???t", "src\\com\\cv19\\icon\\logout_icon.png");
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
        account_panel = new AcountPanel(getId());
        information_panel = new InformationPanel();
        necessity_panel = new NecessityPanel();
        payment_panel = new PaymentPanel(getId());

        content = new ContentPanel();

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
        JPanel none1 = new JPanel();
        none1.setPreferredSize(new Dimension(80,50));
        JPanel none2 = new JPanel();
        none2.setPreferredSize(new Dimension(80,50));
        JPanel information_title = new JPanel();
        JLabel information_text_title = new JLabel("Th??ng tin");
        information_text_title.setFont(content_title_font);
        information_title.add(information_text_title);

        personal_information_button = new InformationButton("Xem th??ng tin c?? nh??n");
        personal_information_button.addActionListener(this);
        managed_history_button = new InformationButton("L???ch s??? ???????c qu???n l??");
        managed_history_button.addActionListener(this);
        necessity_history_button = new InformationButton("L???ch s??? ti??u th??? g??i Nhu y???u ph???m");
        necessity_history_button.addActionListener(this);
        check_debt_button = new InformationButton("Xem d?? n???");
        check_debt_button.addActionListener(this);
        payment_history_button = new InformationButton("L???ch s??? thanh to??n");
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

    private JTextField search_bar = new JTextField("T??m ki???m");
    private JButton search_button = new JButton("T??m");
    private JComboBox type_filter_combo_box;
    private JComboBox time_filter_combo_box;
    private JComboBox sort_combo_box;
    private JPanel necessity_content;
    private NecessityTablePanel necessity_table_panel;
    private JButton purchase_button = new JButton("Mua h??ng");
    private NecessityPurchasePanel necessity_purchase_panel = new NecessityPurchasePanel(getId());

    String[] getNecessityType() {
        ArrayList<String> nec_type_lst = new ArrayList<String>();

        String sql = "SELECT DISTINCT NEC_KIND\n" +
                "FROM NECESSITIES";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                nec_type_lst.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
        }
        String[] nec_type = new String[nec_type_lst.size() + 1];
        nec_type[0] = "T???t c???";
        for (int i = 1; i < nec_type.length; i++) {
            nec_type[i] = nec_type_lst.get(i - 1);
        }

        return nec_type;
    }

    public void showNecessityPanel() {
        JPanel necessity_title = new JPanel();
        necessity_title.setBackground(Color.white);
        necessity_title.setPreferredSize(new Dimension(720,70));
        JLabel necessity_text_title = new JLabel("Xem nhu y???u ph???m");
        necessity_text_title.setFont(content_title_font);
        necessity_title.add(necessity_text_title);

        search_bar.setPreferredSize(new Dimension(180,30));
        search_bar.setForeground(new Color(153, 153, 153));
        search_bar.addActionListener(this);
        search_bar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (search_bar.getText().equals("T??m ki???m")) {
                    search_bar.setText("");
                    search_bar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (search_bar.getText().trim().equals("") || search_bar.getText().equals("T??m ki???m")) {
                    search_bar.setText("T??m ki???m");
                    search_bar.setForeground(new Color(153, 153, 153));
                }
            }
        });

        search_button.setFocusable(false);
        search_button.setPreferredSize(new Dimension(70,30));
        search_button.setFont(text_font);
        search_button.addActionListener(this);

        JPanel search_panel = new JPanel();
        search_panel.setOpaque(false);
        search_panel.add(search_bar);
        search_panel.add(search_button);

        JLabel sort_label = new JLabel("S???p x???p theo: ");
        String[] necessity_columns = {"M???c ?????nh" ,"T??n", "Gi?? t??ng d???n", "L?????ng mua c??n l???i"};
        sort_combo_box = new JComboBox(necessity_columns);
        sort_combo_box.addActionListener(this);

        JPanel sort_panel = new JPanel();
        sort_panel.setOpaque(false);
        sort_panel.add(sort_label);
        sort_panel.add(sort_combo_box);

        JLabel type_filter_label = new JLabel("Lo???i s???n ph???m: ");
        String[] necessity_type = getNecessityType();
        type_filter_combo_box = new JComboBox(necessity_type);
        type_filter_combo_box.addActionListener(this);

        JPanel type_filter_panel = new JPanel();
        type_filter_panel.setOpaque(false);
        type_filter_panel.add(type_filter_label);
        type_filter_panel.add(type_filter_combo_box);

        JLabel time_filter_label = new JLabel("Lo???i gi???i h???n: ");
        String[] necessity_limit_type = {"T???t c???", "Ng??y", "Tu???n", "Th??ng"};
        time_filter_combo_box = new JComboBox(necessity_limit_type);
        time_filter_combo_box.addActionListener(this);

        JPanel time_filter_panel = new JPanel();
        time_filter_panel.setOpaque(false);
        time_filter_panel.add(time_filter_label);
        time_filter_panel.add(time_filter_combo_box);

        JPanel task_bar_panel = new JPanel();
        task_bar_panel.setLayout(new GridLayout(2,2,0,0));
        task_bar_panel.setBackground(Color.white);
        task_bar_panel.setPreferredSize(new Dimension(700,100));
        task_bar_panel.add(search_panel);
        task_bar_panel.add(sort_panel);
        task_bar_panel.add(type_filter_panel);
        task_bar_panel.add(time_filter_panel);

        necessity_table_panel = new NecessityTablePanel(getId());
        necessity_content = new JPanel();
        necessity_content.setPreferredSize(new Dimension(700,320));
        necessity_content.setBackground(Color.white);
        necessity_content.add(necessity_table_panel);

        purchase_button.setFocusable(false);
        purchase_button.addActionListener(this);

        necessity_panel.add(necessity_title);
        necessity_panel.add(task_bar_panel);
        necessity_panel.add(necessity_content);
        necessity_panel.add(purchase_button);
    }

    private JTextField purchase_search_bar = new JTextField("T??m ki???m");
    private JButton purchase_search_button = new JButton("T??m");
    private JComboBox purchase_type_filter_combo_box;
    private JTextField add_product_field = new JTextField("Nh???p m?? s???n ph???m mu???n mua");
    private JButton add_product_button = new JButton("Xem");
    private NecessityPurchaseTablePanel necessity_purchase_table_panel;
    private JPanel purchase_content;
    private JTextArea product_chosen_field = new JTextArea();
    private JTextField quantity_field = new JTextField("S??? l?????ng");
    private JButton add_button = new JButton("Th??m");
    private DefaultTableModel cart_model;
    private JTextField total_cost_field = new JTextField("0");
    private JButton buy_button = new JButton("Mua");

    public void showPurchasePanel() {
        JLabel title = new JLabel("Mua nhu y???u ph???m");
        title.setFont(content_title_font);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,20));
        none.setBackground(Color.white);
        none.setOpaque(true);

        JLabel add_title = new JLabel("Th??m s???n ph???m");
        add_title.setPreferredSize(new Dimension(340,30));
        add_title.setHorizontalAlignment(SwingConstants.LEFT);
        add_title.setFont(text_font);

        purchase_search_bar.setPreferredSize(new Dimension(250,30));
        purchase_search_bar.setForeground(new Color(153, 153, 153));
        purchase_search_bar.addActionListener(this);
        purchase_search_bar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (purchase_search_bar.getText().equals("T??m ki???m")) {
                    purchase_search_bar.setText("");
                    purchase_search_bar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (purchase_search_bar.getText().trim().equals("") || purchase_search_bar.getText().equals("T??m ki???m")) {
                    purchase_search_bar.setText("T??m ki???m");
                    purchase_search_bar.setForeground(new Color(153, 153, 153));
                }
            }
        });

        purchase_search_button.setFocusable(false);
        purchase_search_button.setPreferredSize(new Dimension(70,30));
        purchase_search_button.setFont(text_font);
        purchase_search_button.addActionListener(this);

        String[] necessity_type = getNecessityType();
        purchase_type_filter_combo_box = new JComboBox(necessity_type);
        purchase_type_filter_combo_box.setBorder(javax.swing.BorderFactory.createTitledBorder("Lo???i s???n ph???m"));
        purchase_type_filter_combo_box.setPreferredSize(new Dimension(180,50));
        purchase_type_filter_combo_box.addActionListener(this);

        add_product_field.setPreferredSize(new Dimension(220,30));
        add_product_field.setForeground(new Color(153, 153, 153));
        add_product_field.addActionListener(this);
        add_product_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (add_product_field.getText().equals("Nh???p m?? s???n ph???m mu???n mua")) {
                    add_product_field.setText("");
                    add_product_field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (add_product_field.getText().trim().equals("") || add_product_field.getText().equals("Nh???p m?? s???n ph???m mu???n mua")) {
                    add_product_field.setText("Nh???p m?? s???n ph???m mu???n mua");
                    add_product_field.setForeground(new Color(153, 153, 153));
                }
            }
        });

        add_product_button.setFocusable(false);
        add_product_button.setPreferredSize(new Dimension(100,30));
        add_product_button.setFont(text_font);
        add_product_button.addActionListener(this);

        product_chosen_field.setColumns(30);
        product_chosen_field.setRows(7);
        product_chosen_field.setEditable(false);

        JPanel product_chosen_field_panel = new JPanel();
        product_chosen_field_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("S???n ph???m ???? ch???n"));
        product_chosen_field_panel.add(product_chosen_field);

        quantity_field.setPreferredSize(new Dimension(180,30));
        quantity_field.setForeground(new Color(153, 153, 153));
        quantity_field.addActionListener(this);
        quantity_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (quantity_field.getText().equals("S??? l?????ng")) {
                    quantity_field.setText("");
                    quantity_field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (quantity_field.getText().trim().equals("") || quantity_field.getText().equals("S??? l?????ng")) {
                    quantity_field.setText("S??? l?????ng");
                    quantity_field.setForeground(new Color(153, 153, 153));
                }
            }
        });

        JLabel none1 = new JLabel();
        none1.setPreferredSize(new Dimension(340,15));
        JLabel none2 = new JLabel();
        none2.setPreferredSize(new Dimension(340,20));
        JPanel add_necessity_lpanel = new JPanel();
        add_necessity_lpanel.add(add_title);
        add_necessity_lpanel.add(purchase_search_bar);
        add_necessity_lpanel.add(purchase_search_button);
        add_necessity_lpanel.add(none1);
        add_necessity_lpanel.add(purchase_type_filter_combo_box);
        add_necessity_lpanel.add(none2);
        add_necessity_lpanel.add(add_product_field);
        add_necessity_lpanel.add(add_product_button);

        necessity_purchase_table_panel = new NecessityPurchaseTablePanel(getId(), Boolean.FALSE);
        purchase_content = new JPanel();
        purchase_content.add(necessity_purchase_table_panel);

        JPanel add_necessity_rpanel = new JPanel();
        add_necessity_rpanel.add(purchase_content);

        JPanel add_necessity_panel = new JPanel();
        add_necessity_panel.setLayout(new GridLayout(1,2,0,0));
        add_necessity_panel.setBorder(new LineBorder(Color.black,1));
        add_necessity_panel.add(add_necessity_lpanel);
        add_necessity_panel.add(add_necessity_rpanel);

        add_button.addActionListener(this);
        add_button.setFocusable(false);

        JLabel none3 = new JLabel();
        none3.setPreferredSize(new Dimension(340,17));
        JPanel cart_lpanel = new JPanel();
        cart_lpanel.add(product_chosen_field_panel);
        cart_lpanel.add(none3);
        cart_lpanel.add(quantity_field);
        cart_lpanel.add(add_button);

        String[] columns = {"M?? s???n ph???m", "T??n s???n ph???m", "????n Gi??", "S??? l?????ng", "T???ng c???ng"};
        cart_model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(cart_model);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(320,150));

        JPanel cart_table_panel = new JPanel();
        cart_table_panel.setBackground(Color.white);
        cart_table_panel.setOpaque(false);
        cart_table_panel.setPreferredSize(new Dimension(690,210));
        cart_table_panel.add(sp);

        JLabel total_cost_title = new JLabel("T???ng s??? ti???n: ");
        total_cost_title.setHorizontalAlignment(SwingConstants.LEFT);
        total_cost_title.setPreferredSize(new Dimension(120,30));
        total_cost_title.setFont(text_font);

        total_cost_field.setPreferredSize(new Dimension(120,30));
        total_cost_field.setBackground(Color.white);
        total_cost_field.setForeground(Color.black);
        total_cost_field.setEditable(false);
        total_cost_field.addActionListener(this);

        buy_button.addActionListener(this);
        buy_button.setFocusable(false);

        JLabel none4 = new JLabel();
        none4.setPreferredSize(new Dimension(340,10));
        JPanel cart_rpanel = new JPanel();
        cart_rpanel.add(sp);
        cart_rpanel.add(none4);
        cart_rpanel.add(total_cost_title);
        cart_rpanel.add(total_cost_field);
        cart_rpanel.add(buy_button);

        JPanel cart_panel = new JPanel();
        cart_panel.setLayout(new GridLayout(1,2,0,0));
        cart_panel.setBorder(new LineBorder(Color.black,1));
        cart_panel.add(cart_lpanel);
        cart_panel.add(cart_rpanel);

        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(2,1,0,0));
        ct.setBorder(new LineBorder(Color.black,1));
        ct.setBackground(Color.yellow);
        ct.setOpaque(false);
        ct.setPreferredSize(new Dimension(700,450));
        ct.add(add_necessity_panel);
        ct.add(cart_panel);

        necessity_purchase_panel.add(title);
        necessity_purchase_panel.add(none);
        necessity_purchase_panel.add(ct);
    }

    private JTextField cost_field = new JTextField("S??? ti???n thanh to??n");
    private JButton payment_accept_button = new JButton("Thanh to??n");
    public JLabel debt = new JLabel();
    public JLabel cost = new JLabel();

    public void showPaymentPanel() {
        JPanel payment_title = new JPanel();
        payment_title.setBackground(Color.white);
        payment_title.setPreferredSize(new Dimension(720,100));
        JLabel payment_text_title = new JLabel("Thanh to??n");
        payment_text_title.setFont(content_title_font);
        payment_title.add(payment_text_title);


        debt.setText("S??? ti???n c???n thanh to??n:");
        debt.setFont(new Font("Text",Font.BOLD,20));

        cost.setText("Nh???p s??? ti???n thanh to??n (t???i thi???u 20%):");
        cost.setFont(new Font("Text",Font.BOLD,20));

        cost_field.setPreferredSize(new Dimension(180,30));
        cost_field.setForeground(new Color(153, 153, 153));
        cost_field.addActionListener(this);
        cost_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (cost_field.getText().equals("S??? ti???n thanh to??n")) {
                    cost_field.setText("");
                    cost_field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (cost_field.getText().trim().equals("") || cost_field.getText().equals("S??? ti???n thanh to??n")) {
                    cost_field.setText("S??? ti???n thanh to??n");
                    cost_field.setForeground(new Color(153, 153, 153));
                }
            }
        });

        payment_accept_button.setFocusable(false);
        payment_accept_button.setPreferredSize(new Dimension(120,30));
        payment_accept_button.setFont(text_font);
        payment_accept_button.addActionListener(this);

        JPanel task_bar_panel = new JPanel();
        task_bar_panel.setBackground(Color.white);
        task_bar_panel.add(cost_field);
        task_bar_panel.add(payment_accept_button);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,50));
        none.setBackground(Color.white);
        none.setOpaque(true);
        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(6,1,0,0));
        ct.setBackground(Color.yellow);
        ct.setOpaque(false);
        ct.setPreferredSize(new Dimension(600,250));
        ct.add(debt);
        ct.add(cost);
        ct.add(task_bar_panel);

        payment_panel.add(payment_title);
        payment_panel.add(none);
        payment_panel.add(ct);
    }

    private JPasswordField old_password_field = new JPasswordField();
    private JPasswordField new_password_field = new JPasswordField();
    private JPasswordField retype_new_password_field = new JPasswordField();
    private JButton change_password_button = new JButton("Change");

    public void showAccountPanel() {
        JPanel account_title = new JPanel();
        account_title.setBackground(Color.white);
        account_title.setPreferredSize(new Dimension(720,100));
        JLabel acount_text_title = new JLabel("?????i m???t kh???u");
        acount_text_title.setFont(content_title_font);
        account_title.add(acount_text_title);

        JLabel old_password = new JLabel();
        old_password.setText("M???t kh???u c??:");
        old_password.setFont(new Font("Text",Font.BOLD,20));

        JLabel new_password = new JLabel();
        new_password.setText("M???t kh???u m???i:");
        new_password.setFont(new Font("Text",Font.BOLD,20));

        JLabel retype_new_password = new JLabel();
        retype_new_password.setText("Nh???p l???i m???t kh???u m???i:");
        retype_new_password.setFont(new Font("Text",Font.BOLD,20));

        String confirm_password = "";

        String sql = "SELECT *\n" +
                "FROM ACCOUNT\n" +
                "WHERE USERNAME = ?";
        try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setString(1, getId());
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                confirm_password = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
        }

        old_password_field.setPreferredSize(new Dimension(180,30));
        old_password_field.setForeground(new Color(153, 153, 153));
        old_password_field.addActionListener(this);

        new_password_field.setPreferredSize(new Dimension(180,30));
        new_password_field.setForeground(new Color(153, 153, 153));
        new_password_field.addActionListener(this);

        retype_new_password_field.setPreferredSize(new Dimension(180,30));
        retype_new_password_field.setForeground(new Color(153, 153, 153));
        retype_new_password_field.addActionListener(this);

        change_password_button.setFocusable(false);
        change_password_button.setPreferredSize(new Dimension(120,30));
        change_password_button.setFont(text_font);
        change_password_button.addActionListener(this);

        JPanel form_panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form_panel_1.setBackground(Color.white);
        form_panel_1.add(old_password);
        form_panel_1.add(old_password_field);

        JPanel form_panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form_panel_2.setBackground(Color.white);
        form_panel_2.add(new_password);
        form_panel_2.add(new_password_field);

        JPanel form_panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form_panel_3.setBackground(Color.white);
        form_panel_3.add(retype_new_password);
        form_panel_3.add(retype_new_password_field);

        JPanel form_panel_4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        form_panel_4.setBackground(Color.white);
        form_panel_4.add(change_password_button);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,50));
        none.setBackground(Color.white);
        none.setOpaque(true);
        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(6,1,0,0));
        ct.setBackground(Color.WHITE);
        ct.setOpaque(true);
        ct.setPreferredSize(new Dimension(600,500));
        ct.add(form_panel_1);
        ct.add(form_panel_2);
        ct.add(form_panel_3);
        ct.add(form_panel_4);

        account_panel.add(account_title);
        account_panel.add(none);
        account_panel.add(ct);
    }

    public void setNecessityTable() {
        String search = search_bar.getText();
        System.out.println(search);
        String nec_type = type_filter_combo_box.getSelectedItem().toString().trim();
        String sort_type = sort_combo_box.getSelectedItem().toString();
        String time_type = time_filter_combo_box.getSelectedItem().toString();
        if (Objects.equals(search, "T??m ki???m")) {
            search = "";
        }
        necessity_content.removeAll();
        necessity_content.add(new NecessityTablePanel(getId(), search, nec_type, sort_type, time_type));
        necessity_content.repaint();
        necessity_content.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == information_button) {
            switchPanel(information_panel);
        }
        if (e.getSource() == necessity_button) {
            search_bar.setText("");
            sort_combo_box.setSelectedIndex(0);
            type_filter_combo_box.setSelectedIndex(0);
            time_filter_combo_box.setSelectedIndex(0);
            setNecessityTable();
            switchPanel(necessity_panel);
        }
        if (e.getSource() == payment_button) {
            String money = "";

            String sql = "SELECT *\n" +
                    "FROM DEBT\n" +
                    "WHERE [USER_ID] = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    money = rs.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            if (money != "") {
                debt.setText("S??? ti???n c???n thanh to??n: " + money);
                double m = Double.parseDouble(money)/5;
                cost.setText("Nh???p s??? ti???n thanh to??n (t???i thi???u " + (int)m + "):");
            }
            cost_field.setText("S??? ti???n thanh to??n");
            switchPanel(payment_panel);
        }
        if (e.getSource() == account_button) {
            switchPanel(account_panel);
        }
        if (e.getSource() == logout_button) {
            this.dispose();
            new LoginController();
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
        if (e.getSource() == search_button || e.getSource() == type_filter_combo_box || e.getSource() == sort_combo_box || e.getSource() == time_filter_combo_box) {
            setNecessityTable();
        }
        if (e.getSource() == purchase_button) {
            search_bar.setText("");
            type_filter_combo_box.setSelectedIndex(0);
            switchPanel(necessity_purchase_panel);
        }
        if (e.getSource() == purchase_search_bar) {
            purchase_search_bar.setText("");
        }
        if (e.getSource() == purchase_search_button || e.getSource() == purchase_type_filter_combo_box) {
            String search = purchase_search_bar.getText();
            String nec_type = purchase_type_filter_combo_box.getSelectedItem().toString().trim();
            if (Objects.equals(search, "T??m ki???m")) {
                search = "";
            }
            purchase_content.removeAll();
            purchase_content.add(new NecessityPurchaseTablePanel(getId(), search, nec_type));
            purchase_content.repaint();
            purchase_content.revalidate();
        }
        if (e.getSource() == add_product_button) {
            String pro_id = add_product_field.getText();
            NecessityPurchaseTablePanel pro_datas = new NecessityPurchaseTablePanel(getId(), Boolean.TRUE);
            String[][] datas = pro_datas.getData();
            String[] specific_data = new String[6];
            for (int i = 0; i < datas.length; i++) {
                if (Objects.equals(datas[i][0], pro_id)) {
                    specific_data = datas[i].clone();
                    break;
                }
            }
            String result = "M?? s???n ph???m: " + specific_data[0] +
                    "\nLo???i s???n ph???m: " + specific_data[1] +
                    "\nT??n s???n ph???m: " + specific_data[2] +
                    "\n????n gi??: " + specific_data[3] +
                    "\nGi???i h???n mua c??n l???i: " + specific_data[4] +
                    "\nLo???i gi???i h???n theo:  " + specific_data[5];
            product_chosen_field.setText(result);
        }
        if (e.getSource() == add_button) {
            int qtt = Integer.parseInt(quantity_field.getText());
            String pro_i4 = product_chosen_field.getText().trim();
            String pls_chose_pro_first = "Vui l??ng ch???n lo???i s???n ph???m tr?????c khi nh???p s??? l?????ng.";
            String success = "Th??m v??o gi??? h??ng th??nh c??ng.";
            if (Objects.equals(pro_i4, "") || Objects.equals(pro_i4, pls_chose_pro_first) || Objects.equals(pro_i4, success)) {
                product_chosen_field.setText(pls_chose_pro_first);
            }
            else {
                String pls_enter_reasonable_price = "S??? l?????ng nh???p v?????t qu?? l?????ng mua c??n l???i, vui l??ng nh???p l???i";
                String[] pro_i4_part = pro_i4.split("\n");
                String[] pro_i4_id = pro_i4_part[0].split(" ");
                String[] pro_i4_name = pro_i4_part[2].split(" ");
                String[] pro_i4_price = pro_i4_part[3].split(" ");
                String[] pro_i4_limit_qtt =  pro_i4_part[4].split(" ");
                if (qtt > Integer.parseInt(pro_i4_limit_qtt[5])) {
                    if (Objects.equals(pro_i4_part[pro_i4_part.length - 1], pls_enter_reasonable_price)) {
                        product_chosen_field.setText(pro_i4);
                    }
                    else {
                        product_chosen_field.setText(pro_i4 + "\n\n" + pls_enter_reasonable_price);
                    }
                }
                else {
                    String pro_name = "";
                    for (int i = 3; i < pro_i4_name.length; i++) {
                        pro_name += pro_i4_name[i] + " ";
                    }
                    cart_model.addRow(new Object[]{pro_i4_id[3], pro_name.trim(), pro_i4_price[2], qtt, Integer.parseInt(pro_i4_price[2])*qtt});
                    int total_cost = 0;
                    for (int i = 0; i < cart_model.getRowCount(); i++) {
                        total_cost += Integer.parseInt(String.valueOf(cart_model.getValueAt(i, 4)));
                    }
                    total_cost_field.setText(String.valueOf(total_cost));
                    product_chosen_field.setText(success);
                }
            }
        }
        if (e.getSource() == buy_button) {
            int count = 0;
            String sql = "SELECT *\n" +
                    "FROM BILL";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                ResultSet rs = pre.executeQuery();

                while (rs.next()) {
                    count++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            int id_bill = count + 1;
            sql = "INSERT BILL VALUES (?, ?, GETDATE(), ?)";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setInt(1, id_bill);
                pre.setString(2, getId());
                pre.setInt(3, Integer.parseInt(total_cost_field.getText()));

                pre.execute();
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            sql = "INSERT INTO CONSUME (ID_BILL, ID_NECESSITIES, QUANTITY) VALUES (?, ?, ?)";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                for (int i = 0; i < cart_model.getRowCount(); i++) {
                    pre.setInt(1, id_bill);
                    pre.setInt(2, Integer.parseInt(String.valueOf(cart_model.getValueAt(i, 0))));
                    pre.setInt(3, Integer.parseInt(String.valueOf(cart_model.getValueAt(i, 3))));

                    pre.execute();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            int debt = 0;
            sql = "SELECT *\n" +
                    "FROM DEBT\n" +
                    "WHERE USER_ID = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    debt = rs.getInt(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            debt += Integer.parseInt(total_cost_field.getText());
            sql = "UPDATE DEBT SET DEBT = ? WHERE USER_ID = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setInt(1, debt);
                pre.setString(2, getId());
                pre.execute();
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            JOptionPane.showMessageDialog(this, "Mua th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            product_chosen_field.setText("");
            add_product_field.setText("");
            total_cost_field.setText("");
            quantity_field.setText("");
            cart_model.setRowCount(0);
            switchPanel(necessity_purchase_panel);
        }
        if (e.getSource() == cost_field) {
            cost_field.setText("");
        }
        if (e.getSource() == payment_accept_button) {
            String cost_value = cost_field.getText();
            String id_bank = "";
            String balance = "";
            String debt_value = "";
            String sql = "SELECT PM.*\n" +
                    "FROM PROFILE P JOIN PAYMENT PM ON P.ID_BANK = PM.ID_BANK\n" +
                    "WHERE ID = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    id_bank = rs.getString(1);
                    balance = rs.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            sql = "SELECT *\n" +
                    "FROM DEBT\n" +
                    "WHERE USER_ID = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    debt_value = rs.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            int temp = Integer.parseInt(debt_value) / 5;
            if (Integer.parseInt(cost_value) < temp) {
                JOptionPane.showMessageDialog(this, "Vui l??ng thanh to??n t???i thi???u " + temp, "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (Integer.parseInt(cost_value) > Integer.parseInt(debt_value)) {
                    cost_value = debt_value;
                }
                String nb = Integer.toString(Integer.parseInt(balance) - Integer.parseInt(cost_value));
                sql = "UPDATE PAYMENT SET BALANCE = ? WHERE ID_BANK = ?";
                try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                    pre.setString(1, nb);
                    pre.setString(2, id_bank);
                    pre.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
                }
                sql = "INSERT INTO PAYMENT_HISTORY VALUES(?, GETDATE(), ?)";
                try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                    pre.setString(1, getId());
                    pre.setString(2, cost_value);
                    pre.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
                }
                String nd = Integer.toString(Integer.parseInt(debt_value) - Integer.parseInt(cost_value));
                sql = "UPDATE DEBT SET DEBT = ? WHERE USER_ID = ?";
                try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                    pre.setString(1, nd);
                    pre.setString(2, getId());
                    pre.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
                }

                JOptionPane.showMessageDialog(this, "Thanh to??n th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
            }
            String money = "";

            sql = "SELECT *\n" +
                    "FROM DEBT\n" +
                    "WHERE [USER_ID] = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    money = rs.getString(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }
            if (money != "") {
                debt.setText("S??? ti???n c???n thanh to??n: " + money);
                double m = Double.parseDouble(money)/5;
                cost.setText("Nh???p s??? ti???n thanh to??n (t???i thi???u " + (int)m + "):");
            }
            cost_field.setText("");
        }
        if (e.getSource() == change_password_button) {
            String a=null;
            String sql = "SELECT *\n" +
                    "FROM ACCOUNT\n" +
                    "WHERE USERNAME = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    a= new String(rs.getBytes(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
            }

            if (String.valueOf(old_password_field.getPassword()).equals(a)) {
                if (String.valueOf(new_password_field.getPassword()).equals(String.valueOf(retype_new_password_field.getPassword()))) {
                    sql = "UPDATE ACCOUNT SET PASSWORD = ? WHERE USERNAME = ?";
                    try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                        pre.setBytes(1, String.valueOf(new_password_field.getPassword()).getBytes());
                        pre.setString(2, getId());
                        pre.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, "Can not connect to database", ex);
                    }
                    JOptionPane.showMessageDialog(null, "?????i m???t kh???u th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"M???t kh???u m???i kh??ng kh???p nhau","Th??ng b??o", JOptionPane.WARNING_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Sai m???t kh???u c??","Th??ng b??o", JOptionPane.ERROR_MESSAGE);
            }
            old_password_field.setText("");
            new_password_field.setText("");
            retype_new_password_field.setText("");
        }
    }
}
