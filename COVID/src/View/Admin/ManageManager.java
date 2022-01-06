package View.Admin;

import Controller.CovidDAO;
import Model.HistoryModel;
import Model.profileModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class ManageManager extends JPanel implements ActionListener {
    private JLabel title = new JLabel("MANAGER VIEW");

    private JLabel mlabel = new JLabel("Managers");
    private JComboBox mtx = new JComboBox();

    private JButton sub = new JButton("View");
    private JTextArea result = new JTextArea();
    private JButton delete = new JButton("Delete");
    private JTable info;

    private JScrollPane scrollPane;
    private String usn;
    private profileModel p = new profileModel();
    ManageManager() {
        setBounds(240, 0, 760, 700);
        setOpaque(true);
        setVisible(true);
        setLayout(null);
        setBackground(Color.WHITE);

        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setBounds(150, 30, 400, 30);
        add(title);

        JLabel inform1 = new JLabel("----------- Choose Manager");
        inform1.setFont(new Font("Arial", Font.PLAIN, 15));
        inform1.setBounds(20, 70, 190, 20);
        add(inform1);

        mlabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mlabel.setBounds(20, 100, 190, 20);
        add(mlabel);

        mtx.setFont(new Font("Arial", Font.PLAIN, 15));
        mtx.setBounds(120, 100, 120, 20);
        mtx.addActionListener(this);
        add(mtx);

        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setBounds(20, 350, 100, 20);
        sub.addActionListener(this);
        sub.setBorder(new RoundBtn(15));
        sub.setToolTipText("view the history of this manager");
        add(sub);

        delete.setFont(new Font("Arial", Font.PLAIN, 15));
        delete.setBounds(120, 350, 100, 20);
        delete.setBorder(new RoundBtn(15));
        delete.addActionListener(this);
        delete.setToolTipText("delete this manager");
        add(delete);

        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setBounds(20, 150, 200, 250);
        result.setEditable(false);
        add(result);

        String sql = "  SELECT C.*" +
                "  FROM PROFILE P JOIN ACCOUNT C ON C.USERNAME = P.ID" +
                "  WHERE C.TYPE = 1 AND (BLOCK IS NULL OR BLOCK <> 1)";
        try (Connection conn = new CovidDAO().getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            mtx.removeAll();
            while (rs.next()) {
                String name = rs.getString(1);
                mtx.addItem(name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    void view(Vector<HistoryModel> list){
        Vector<String> columnNames = new Vector<String>();
//        columnNames.addElement("USER ID");
        columnNames.addElement("WHEN");
        columnNames.addElement("ACTION");

        Vector<Vector> Rowdata = new Vector<Vector>();
        for (HistoryModel h : list){
            Vector<String> temp = new Vector<String>();
//            temp.add(h.getUser_id());
            temp.add(h.getTime().toString());
            temp.add(h.getMsg());
            Rowdata.add(temp);
        }

        info = new JTable(Rowdata, columnNames);
        info.setFont(new Font("Arial", Font.PLAIN, 15));
        scrollPane = new JScrollPane(info);
        scrollPane.setBounds(250, 100, 470, 500);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mtx) {
            usn = (String) mtx.getSelectedItem();
            String sql = "  SELECT C.*, P.*" +
                    "  FROM PROFILE P JOIN ACCOUNT C ON C.USERNAME = P.ID" +
                    "  WHERE USERNAME=?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, usn);

                ResultSet rs = pre.executeQuery();
                if (rs.next()) {
                    p.setUsername(rs.getString(1));
                    p.setPassword(rs.getString(2));
                    p.setType(rs.getInt(3));
                    p.setBlock(rs.getInt(4));
                    p.setFullname(rs.getString(6));
                    p.setPhone(rs.getString(7));
                    p.setDob(rs.getDate(8));
                    p.setGender(rs.getString(9));
                    p.setProvince(rs.getString(10));
                    p.setDistrict(rs.getString(11));
                    p.setVillage(rs.getString(12));
                    result.setText(p.toString());
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        if (e.getSource() == sub){
            Vector<HistoryModel> list = new Vector<>();

            String sql = "  SELECT H.*" +
                    "  FROM PROFILE P JOIN HISTORY H ON H.USER_ID = P.ID" +
                    "  WHERE P.ID=?";
            try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                pre.setString(1, usn);

                ResultSet rs = pre.executeQuery();
                while (rs.next()) {
                    HistoryModel temp = new HistoryModel();
                    temp.setUser_id(rs.getString(1));
                    temp.setTime(rs.getTimestamp(2));
                    temp.setMsg(rs.getNString(3));
                    list.add(temp);
                }
            } catch (SQLException ex){
                ex.printStackTrace();
            }

            view(list);
        }
        if (e.getSource() == delete) {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure to delete", "Just to make sure", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String sql = "UPDATE ACCOUNT SET BLOCK=1 WHERE USERNAME=?";
                try (Connection conn = new CovidDAO().getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {
                    pre.setString(1, (String) mtx.getSelectedItem());

                    pre.execute();
                    JOptionPane.showMessageDialog(null, "Success");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            String sql = "  SELECT C.*" +
                    "  FROM PROFILE P JOIN ACCOUNT C ON C.USERNAME = P.ID" +
                    "  WHERE C.TYPE = 1 AND (BLOCK IS NULL OR BLOCK <> 1)";
            try (Connection conn = new CovidDAO().getConnection(); Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                mtx.removeAllItems();
                while (rs.next()) {
                    String name = rs.getString(1);
                    mtx.addItem(name);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class RoundBtn implements Border
{
    private int r;
    RoundBtn(int r) {
        this.r = r;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }
}