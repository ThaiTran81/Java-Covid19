package View.User;

import Controller.CovidDAO;
import Model.NecessityHistoryModel;
import View.User.content.ContentPanel;
import View.User.content.account.AcountPanel;
import View.User.content.account.AcountPanel;
import View.User.content.information.*;
import View.User.content.necessity.NecessityPanel;
import View.User.content.necessity.NecessityPurchasePanel;
import View.User.content.necessity.NecessityPurchaseTablePanel;
import View.User.content.necessity.NecessityTablePanel;
import View.User.content.payment.PaymentPanel;
import View.User.sidebar.SideBarButton;
import View.User.sidebar.SideBarPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
import java.util.List;
import java.util.Vector;

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
    private JPanel necessity_content;
    private NecessityTablePanel necessity_table_panel;
    private JButton purchase_button = new JButton("Mua hàng");
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
            ex.printStackTrace();
        }
        String[] nec_type = new String[nec_type_lst.size() + 1];
        nec_type[0] = "Tất cả";
        for (int i = 1; i < nec_type.length; i++) {
            nec_type[i] = nec_type_lst.get(i - 1);
        }

        return nec_type;
    }

    public void showNecessityPanel() {
        necessity_panel.removeAll();

        JPanel necessity_title = new JPanel();
        necessity_title.setBackground(Color.white);
        necessity_title.setPreferredSize(new Dimension(720,100));
        JLabel necessity_text_title = new JLabel("Xem nhu yếu phẩm");
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
        search_button.addActionListener(this);

        JLabel filter_label = new JLabel("Loại: ");
        String[] necessity_type = getNecessityType();
        filter_combo_box = new JComboBox(necessity_type);
        filter_combo_box.addActionListener(this);

        JLabel sort_label = new JLabel("Sắp xếp theo: ");
        String[] necessity_columns = {"Mặc định" ,"Tên", "Giá tăng dần", "Lượng mua còn lại"};
        sort_combo_box = new JComboBox(necessity_columns);
        sort_combo_box.addActionListener(this);

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

    private NecessityPurchaseTablePanel necessity_purchase_table_panel;
    private JTextField quantity_field = new JTextField("Số lượng");
    private JButton add_button = new JButton("Thêm");
    private JButton buy_button = new JButton("Mua");

    public void showPurchasePanel() {
        necessity_purchase_panel.removeAll();

        JLabel title = new JLabel("Mua nhu yếu phẩm");
        title.setFont(content_title_font);

        JPanel none = new JPanel();
        none.setPreferredSize(new Dimension(700,20));
        none.setBackground(Color.white);
        none.setOpaque(true);

        JLabel add_title = new JLabel("      Thêm sản phẩm");
        add_title.setHorizontalAlignment(SwingConstants.LEFT);
        add_title.setPreferredSize(new Dimension(350,50));
        add_title.setFont(text_font);

        JLabel keyword_title = new JLabel("Tìm từ khóa");
        keyword_title.setHorizontalAlignment(SwingConstants.LEFT);
        keyword_title.setPreferredSize(new Dimension(150,20));

        search_bar.setPreferredSize(new Dimension(135,30));
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

        JLabel filter_label = new JLabel("Chọn loại sản phẩm: ");
        filter_label.setHorizontalAlignment(SwingConstants.LEFT);
        filter_label.setPreferredSize(new Dimension(150,20));
        String[] necessity_type = getNecessityType();
        filter_combo_box = new JComboBox(necessity_type);

        quantity_field.setPreferredSize(new Dimension(180,30));
        quantity_field.setForeground(new Color(153, 153, 153));
        quantity_field.addActionListener(this);
        quantity_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (quantity_field.getText().equals("Tìm kiếm")) {
                    quantity_field.setText("");
                    quantity_field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (quantity_field.getText().trim().equals("") || quantity_field.getText().equals("Tìm kiếm")) {
                    quantity_field.setText("Tìm kiếm");
                    quantity_field.setForeground(new Color(153, 153, 153));
                }
            }
        });

        necessity_purchase_table_panel = new NecessityPurchaseTablePanel(getId());

        JPanel add_necessity_panel = new JPanel();
        add_necessity_panel.setBorder(new LineBorder(Color.black,1));
        add_necessity_panel.add(add_title);
        add_necessity_panel.add(keyword_title);
        add_necessity_panel.add(search_bar);
        add_necessity_panel.add(filter_label);
        add_necessity_panel.add(filter_combo_box);
        add_necessity_panel.add(quantity_field);
        add_necessity_panel.add(add_button);
        add_necessity_panel.add(necessity_purchase_table_panel);

        JLabel cart_title = new JLabel("      Giỏ hàng");
        cart_title.setHorizontalAlignment(SwingConstants.LEFT);
        cart_title.setPreferredSize(new Dimension(350,50));
        cart_title.setFont(text_font);

        String[][] data = new String[3][3];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = "";
            data[i][1] = "";
            data[i][2] = "";
        }
        String[] columns = {"Tên sản phẩm", "Số lượng", "Đơn Giá"};
        JTable table = new JTable(data, columns);
        JScrollPane sp = new JScrollPane(table);
        sp.setPreferredSize(new Dimension(320,200));

        JPanel cart_table_panel = new JPanel();
        cart_table_panel.setBackground(Color.white);
        cart_table_panel.setOpaque(false);
        cart_table_panel.setPreferredSize(new Dimension(690,210));
        cart_table_panel.add(sp);

        JLabel total_price = new JLabel("Tổng số tiền: 0000.00");
        total_price.setHorizontalAlignment(SwingConstants.LEFT);
        total_price.setPreferredSize(new Dimension(250,30));
        total_price.setFont(text_font);

        buy_button.addActionListener(this);

        JPanel cart_panel = new JPanel();
        cart_panel.setBorder(new LineBorder(Color.black,1));
        cart_panel.add(cart_title);
        cart_panel.add(cart_table_panel);
        cart_panel.add(total_price);
        cart_panel.add(buy_button);

        JPanel ct = new JPanel();
        ct.setLayout(new GridLayout(1,2,0,10));
        ct.setBorder(new LineBorder(Color.black,1));
        ct.setBackground(Color.yellow);
        ct.setOpaque(true);
        ct.setPreferredSize(new Dimension(700,400));
        ct.add(add_necessity_panel);
        ct.add(cart_panel);

        necessity_purchase_panel.add(title);
        necessity_purchase_panel.add(none);
        necessity_purchase_panel.add(ct);
    }

    private JTextField cost_field = new JTextField("Số tiền thanh toán");
    private JButton payment_accept_button = new JButton("Thanh toán");

    public void showPaymentPanel() {
        payment_panel.removeAll();

        JPanel payment_title = new JPanel();
        payment_title.setBackground(Color.white);
        payment_title.setPreferredSize(new Dimension(720,100));
        JLabel payment_text_title = new JLabel("Thanh toán");
        payment_text_title.setFont(content_title_font);
        payment_title.add(payment_text_title);

        JLabel debt = new JLabel();
        debt.setText("Số tiền cần thanh toán:");
        debt.setFont(new Font("Text",Font.BOLD,20));

        JLabel cost = new JLabel();
        cost.setText("Nhập số tiền thanh toán (tối thiểu 20%):");
        cost.setFont(new Font("Text",Font.BOLD,20));

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
            ex.printStackTrace();
        }

        if (money != "") {
            debt.setText("Số tiền cần thanh toán: " + money);
            double m = Double.parseDouble(money)/5;
            cost.setText("Nhập số tiền thanh toán (tối thiểu " + (int)m + "):");
        }

        cost_field.setPreferredSize(new Dimension(180,30));
        cost_field.setForeground(new Color(153, 153, 153));
        cost_field.addActionListener(this);
        cost_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (cost_field.getText().equals("Số tiền thanh toán")) {
                    cost_field.setText("");
                    cost_field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (cost_field.getText().trim().equals("") || cost_field.getText().equals("Số tiền thanh toán")) {
                    cost_field.setText("Số tiền thanh toán");
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

    private JTextField old_password_field = new JTextField("");
    private JTextField new_password_field = new JTextField("");
    private JTextField retype_new_password_field = new JTextField("");
    private JButton change_password_button = new JButton("Change");

    public void showAccountPanel() {
        account_panel.removeAll();

        JPanel account_title = new JPanel();
        account_title.setBackground(Color.white);
        account_title.setPreferredSize(new Dimension(720,100));
        JLabel acount_text_title = new JLabel("Đổi mật khẩu");
        acount_text_title.setFont(content_title_font);
        account_title.add(acount_text_title);

        JLabel old_password = new JLabel();
        old_password.setText("Mật khẩu cũ:");
        old_password.setFont(new Font("Text",Font.BOLD,20));

        JLabel new_password = new JLabel();
        new_password.setText("Mật khẩu mới:");
        new_password.setFont(new Font("Text",Font.BOLD,20));

        JLabel retype_new_password = new JLabel();
        retype_new_password.setText("Nhập lại mật khẩu mới:");
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
            ex.printStackTrace();
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
            showPaymentPanel();
            switchPanel(payment_panel);
        }
        if (e.getSource() == account_button) {
            showAccountPanel();
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
        if (e.getSource() == filter_combo_box) {
            String nec_type = filter_combo_box.getSelectedItem().toString().trim();
            necessity_content.removeAll();
            necessity_content.add(new NecessityTablePanel(getId(), nec_type));
            necessity_content.repaint();
            necessity_content.revalidate();
        }
        if (e.getSource() == sort_combo_box) {
            String nec_type = filter_combo_box.getSelectedItem().toString().trim();
            String sort_type = sort_combo_box.getSelectedItem().toString();
            necessity_content.removeAll();
            necessity_content.add(new NecessityTablePanel(getId(), nec_type, sort_type));
            necessity_content.repaint();
            necessity_content.revalidate();
        }
        if (e.getSource() == purchase_button) {
            showPurchasePanel();
            switchPanel(necessity_purchase_panel);
        }
        if (e.getSource() == cost_field) {
            cost_field.setText("");
        }
        if (e.getSource() == payment_accept_button) {
            String cost = cost_field.getText();
            String id_bank = "";
            String balance = "";
            String debt = "";
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
                ex.printStackTrace();
            }
            sql = "SELECT *\n" +
                    "FROM DEBT\n" +
                    "WHERE USER_ID = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                ResultSet rs = pre.executeQuery();

                if (rs.next()) {
                    debt = rs.getString(2);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String nb = Integer.toString(Integer.parseInt(balance) - Integer.parseInt(cost));
            sql = "UPDATE PAYMENT SET BALANCE = ? WHERE ID_BANK = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, nb);
                pre.setString(2, id_bank);
                pre.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            sql = "INSERT INTO PAYMENT_HISTORY VALUES(?, GETDATE(), ?)";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, getId());
                pre.setString(2, cost);
                pre.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String nd = Integer.toString(Integer.parseInt(debt) - Integer.parseInt(cost));
            sql = "UPDATE DEBT SET DEBT = ? WHERE USER_ID = ?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, nd);
                pre.setString(2, getId());
                pre.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
