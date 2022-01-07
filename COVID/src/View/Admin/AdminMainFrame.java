package View.Admin;

import Controller.LoginController;
import View.LoginView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminMainFrame extends JFrame implements ActionListener {
    private Container container = getLayeredPane();
    private JPanel Sidebar = new JPanel();

    private JButton CreateAccountBtn = new JButton("Create Manager");
    private JButton ManageBtn = new JButton("Manager");
    private JButton QuarantineBtn = new JButton("Quarantine");
    private JButton Logout = new JButton("Logout");

    private RegisterManager rm = new RegisterManager();
    private RegisterQuarantine rq = new RegisterQuarantine();
    private ManageManager ra = new ManageManager();
    public AdminMainFrame(){
        setTitle("ADMIN PAGE");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        Sidebar.setOpaque(true);
        Sidebar.setBounds(0,0,240,700);
        Sidebar.setBackground(new Color(32, 53, 80));

        JToolBar tool = new JToolBar(JToolBar.VERTICAL);
        tool.setLayout(new GridLayout(4,1,0,0));
        tool.setOpaque(false);
        tool.setBackground(Color.WHITE);
        tool.setFloatable(false);

        CreateAccountBtn.setFont(new Font("Arial", Font.BOLD, 20));
        CreateAccountBtn.setBounds(200, 150, 190, 20);
        CreateAccountBtn.setOpaque(false);
        CreateAccountBtn.setPreferredSize(new Dimension(240, 50));
        CreateAccountBtn.setIcon(new ImageIcon("src/com/cv19/icon/gear.png"));
        CreateAccountBtn.setBorder(new EmptyBorder(0, 15, 0, 0));
        CreateAccountBtn.setFocusable(false);
        CreateAccountBtn.setForeground(Color.WHITE);
        CreateAccountBtn.setHorizontalAlignment(SwingConstants.LEFT);
        CreateAccountBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                CreateAccountBtn.setBackground(new Color(74, 75, 228));
                CreateAccountBtn.setOpaque(true);
            }
            public void mouseExited(MouseEvent evt)
            {
                CreateAccountBtn.setBackground(new Color(32, 53, 80));
                CreateAccountBtn.setOpaque(true);
            }
        });
        CreateAccountBtn.addActionListener(this);
        tool.add(CreateAccountBtn);

        ManageBtn.setFont(new Font("Arial", Font.BOLD, 20));
        ManageBtn.setBounds(200, 150, 190, 20);
        ManageBtn.setOpaque(false);
        ManageBtn.setPreferredSize(new Dimension(240, 50));
        ManageBtn.setIcon(new ImageIcon("src/com/cv19/icon/user.png"));
        ManageBtn.setBorder(new EmptyBorder(0, 15, 0, 0));
        ManageBtn.setFocusable(false);
        ManageBtn.setForeground(Color.WHITE);
        ManageBtn.setHorizontalAlignment(SwingConstants.LEFT);
        ManageBtn.addActionListener(this);
        ManageBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                ManageBtn.setBackground(new Color(74, 75, 228));
                ManageBtn.setOpaque(true);
            }
            public void mouseExited(MouseEvent evt)
            {
                ManageBtn.setBackground(new Color(32, 53, 80));
                ManageBtn.setOpaque(false);
            }
        });

        tool.add(ManageBtn);

        QuarantineBtn.setFont(new Font("Arial", Font.BOLD, 20));
        QuarantineBtn.setBounds(200, 150, 190, 20);
        QuarantineBtn.setOpaque(false);
        QuarantineBtn.setPreferredSize(new Dimension(240, 50));
        QuarantineBtn.setIcon(new ImageIcon("src/com/cv19/icon/home.png"));
        QuarantineBtn.setBorder(new EmptyBorder(0, 15, 0, 0));
        QuarantineBtn.setFocusable(false);
        QuarantineBtn.setForeground(Color.WHITE);
        QuarantineBtn.addActionListener(this);
        QuarantineBtn.setHorizontalAlignment(SwingConstants.LEFT);
        QuarantineBtn.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                QuarantineBtn.setBackground(new Color(74, 75, 228));
                QuarantineBtn.setOpaque(true);
            }
            public void mouseExited(MouseEvent evt)
            {
                QuarantineBtn.setBackground(new Color(32, 53, 80));
                QuarantineBtn.setOpaque(true);
            }
        });

        tool.add(QuarantineBtn);

        Logout.setFont(new Font("Arial", Font.BOLD, 20));
        Logout.setBounds(200, 150, 190, 20);
        Logout.setOpaque(false);
        Logout.setPreferredSize(new Dimension(240, 50));
        Logout.setIcon(new ImageIcon("src/com/cv19/icon/classroom.png"));
        Logout.setBorder(new EmptyBorder(0, 15, 0, 0));
        Logout.setFocusable(false);
        Logout.setForeground(Color.WHITE);
        Logout.addActionListener(this);
        Logout.setHorizontalAlignment(SwingConstants.LEFT);
        Logout.addMouseListener(new MouseAdapter()
        {
            public void mouseEntered(MouseEvent evt)
            {
                Logout.setBackground(new Color(74, 75, 228));
                Logout.setOpaque(true);
            }
            public void mouseExited(MouseEvent evt)
            {
                Logout.setBackground(new Color(32, 53, 80));
                Logout.setOpaque(true);
            }
        });

        tool.add(Logout);
        Sidebar.add(tool);
        container.add(Sidebar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CreateAccountBtn){
            container.remove(rq);
            container.remove(ra);
            rm.revalidate();
            container.add(rm);
        }
        if (e.getSource() == ManageBtn){
            container.remove(rq);
            container.remove(rm);
            ra.revalidate();
            container.add(ra);
        }

        if (e.getSource() == QuarantineBtn){
            container.remove(rm);
            container.remove(ra);
            container.add(rq);
            rq.revalidate();
        }
        if (e.getSource() == Logout){
            this.dispose();
            new LoginController();
        }
    }
}
