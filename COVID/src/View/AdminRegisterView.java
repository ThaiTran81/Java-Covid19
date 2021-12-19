package View;


import Model.profileModel;
import com.cv19.view.event.EventAdRegisterCallBack;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AdminRegisterView extends JFrame implements ActionListener {

    private JCheckBox cbShowPass;
    private JButton btnRegis;
    private JButton btnCancel;
    private JLabel lbEmpty;
    private JLabel lbConfirmPass;
    private JLabel lbHeader;
    private JLabel lbName;
    private JLabel lbPass;
    private JPanel panelHeader;
    private JPanel panelMain;
    private JPasswordField txtConfirmPass;
    private JTextField txtName;
    private JPasswordField txtPass;
    private EventAdRegisterCallBack callback;

    public AdminRegisterView(EventAdRegisterCallBack callback) {
        this.callback = callback;
        initComponents();
    }

    private void initComponents() {

        panelHeader = new JPanel();
        lbHeader = new JLabel();
        panelMain = new JPanel();
        lbName = new JLabel();
        txtName = new JTextField();
        lbPass = new JLabel();
        txtPass = new JPasswordField();
        lbConfirmPass = new JLabel();
        txtConfirmPass = new JPasswordField();
        lbEmpty = new JLabel();
        cbShowPass = new JCheckBox();
        btnCancel = new JButton();
        btnRegis = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        lbHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbHeader.setText("ADMIN REGISTRATION");
        panelHeader.add(lbHeader);

        getContentPane().add(panelHeader);

        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelMain.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        lbName.setText("USERNAME");
        panelMain.add(lbName);

        txtName.setMargin(new java.awt.Insets(5, 10, 5, 10));
        panelMain.add(txtName);

        lbPass.setText("PASSWORD");
        panelMain.add(lbPass);

        txtPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        panelMain.add(txtPass);

        lbConfirmPass.setText("CONFIRM PASSWORD");
        panelMain.add(lbConfirmPass);

        txtConfirmPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        txtConfirmPass.addActionListener(this);
        panelMain.add(txtConfirmPass);
        panelMain.add(lbEmpty);

        cbShowPass.addItemListener(eventShowpass());
        cbShowPass.setText("Show password");
        panelMain.add(cbShowPass);

        btnCancel.addActionListener(this);
        btnCancel.setText("Cancel");
        panelMain.add(btnCancel);

        btnRegis.addActionListener(this);
        btnRegis.setText("Register");
        panelMain.add(btnRegis);

        getContentPane().add(panelMain);

        pack();
        setLocationRelativeTo(null);
    }

    ItemListener eventShowpass() {
        ItemListener event = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cbShowPass.isSelected()) {
                    txtPass.setEchoChar((char) 0);
                } else {
                    txtPass.setEchoChar('•');
                }
            }
        };
        return event;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCancel) {
            this.dispose();
        }
        if (e.getSource() == btnRegis) {
            register();
        }
    }

    void register() {
        String username = txtName.getText();
        String password = String.valueOf(txtPass.getPassword());
        String rePassword = String.valueOf(txtConfirmPass.getPassword());

        if (username.isBlank() || password.isBlank() || rePassword.isBlank()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if(!password.equals(rePassword)){
                JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            profileModel model = new profileModel();
            model.setUsername(username);
            model.setPassword(password);
            model.setType(0);
            callback.register(model);
        }
    }
}

//    public void actionPerformed(ActionEvent e){
//        if (e.getSource() == sub){
//            if(acept.isSelected()){
//                res.setText("Success :)");
//            } else {
//                res.setText("Accept the terms and conditions please!");
//            }
//
//            String temp = String.valueOf(password.getPassword());
//            String temp1 = String.valueOf(repeatpassword.getPassword());
//            if(temp.equals("")) {
//                res.setText("Type password please!");
//            }
//            if(temp1.equals("")) {
//                res.setText("Confirm password please!");
//            }
//            if(!temp.equals(temp1)){
//                res.setText("Password does not match!");
//            }
//            else{
//                Controller.AdminRegisterController.register(username.getText(), temp);
//                this.dispose();
//                new LoginView();
//            }
//        }
//        if (e.getSource() == reset){
//            res.setText("");
//            acept.setSelected(false);
//            password.setText("");
//            repeatpassword.setText("");
//            username.setText("");
//        }
//        if (e.getSource() == showpassword){
//            if (showpassword.isSelected()){
//                password.setEchoChar((char)0);
//            } else {
//                password.setEchoChar('*');
//            }
//            if (showpassword.isSelected()){
//                repeatpassword.setEchoChar((char)0);
//            } else {
//                repeatpassword.setEchoChar('*');
//            }
//        }
//    }
//}
