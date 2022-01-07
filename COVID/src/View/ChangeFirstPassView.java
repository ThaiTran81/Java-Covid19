package View;


public class ChangeFirstPassView extends javax.swing.JFrame {

    public ChangeFirstPassView(String id) {
        initComponents();
        txtName.setText(id);
    }

    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        lbHeader = new javax.swing.JLabel();
        panelMain = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        lbConfirmPass = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbShowPass = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        lbHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbHeader.setText("ĐỔI MẬT KHẨU");
        panelHeader.add(lbHeader);

        getContentPane().add(panelHeader);

        panelMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelMain.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        lbName.setText("TÀI KHOẢN:");
        panelMain.add(lbName);

        txtName.setEditable(false);
        txtName.setMargin(new java.awt.Insets(5, 10, 5, 10));
        panelMain.add(txtName);

        lbPass.setText("MẬT KHẨU MỚI");
        panelMain.add(lbPass);

        txtPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        panelMain.add(txtPass);

        lbConfirmPass.setText("XÁC NHẬN MẬT KHẨU MỚI");
        panelMain.add(lbConfirmPass);

        txtConfirmPass.setMargin(new java.awt.Insets(5, 10, 5, 10));
        txtConfirmPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPassActionPerformed(evt);
            }
        });
        panelMain.add(txtConfirmPass);
        panelMain.add(jLabel5);

        cbShowPass.setText("Show password");
        panelMain.add(cbShowPass);

        btnCancel.setText("HUỶ");
        panelMain.add(btnCancel);

        btnSubmit.setText("XÁC NHẬN");
        panelMain.add(btnSubmit);

        getContentPane().add(panelMain);

        pack();
    }// </editor-fold>

    private void txtConfirmPassActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangeFirstPassView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JCheckBox cbShowPass;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbHeader;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPass;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTextField txtConfirmPass;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPass;
    // End of variables declaration
}

