package com.cv19.view.body;

import Controller.CovidDAO;
import Model.AddressModel;
import Model.QuarantineModel;
import Model.profileModel;
import com.cv19.view.components.AddressCombobox;
import com.cv19.view.event.EventFindCallBack;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ThaiTran
 */
public class AddForm extends javax.swing.JPanel {

    String dates[]
            = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25",
                "26", "27", "28", "29", "30",
                "31"};

    String months[]
            = {"1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10",
                "11", "12"};
    String years[]
            = {"1987", "1988", "1989", "1990",
                "1991", "1992", "1993", "1994",
                "1995", "1996", "1997", "1998",
                "1999", "2000", "2001", "2002",
                "2003", "2004", "2005", "2006",
                "2007", "2008", "2009", "2010",
                "2011", "2012", "2013", "2014",
                "2015", "2016", "2017", "2018",
                "2019", "2020", "2021", "2022"};
    String status[] = {"F0", "F1", "F2", "F3", "F4+", "Khỏi bệnh"};
    AddressCombobox comboAddr;
    ArrayList<QuarantineModel> lstQua;

    public AddForm() {
        initComponents();
        comboDay.setModel(new DefaultComboBoxModel(dates));
        comboMonth.setModel(new DefaultComboBoxModel(months));
        comboYear.setModel(new DefaultComboBoxModel(years));
        comboAddr = new AddressCombobox(comboCity, comboDist, comboVill);
        try {
            lstQua = new CovidDAO().getAllQuarantine();
        } catch (SQLServerException ex) {
            Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < lstQua.size(); i++) {
            comboQua.addItem(lstQua.get(i));
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        container = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lbID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lbDOB = new javax.swing.JLabel();
        comboMonth = new javax.swing.JComboBox<>();
        lbAddr = new javax.swing.JLabel();
        comboCity = new javax.swing.JComboBox<>();
        comboDist = new javax.swing.JComboBox<>();
        comboVill = new javax.swing.JComboBox<>();
        lbSta = new javax.swing.JLabel();
        lbSrc = new javax.swing.JLabel();
        txtSta = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnChooseSrc = new javax.swing.JButton();
        btnDelSrc = new javax.swing.JButton();
        lbTreat = new javax.swing.JLabel();
        comboQua = new javax.swing.JComboBox<>();
        comboYear = new javax.swing.JComboBox<>();
        comboDay = new javax.swing.JComboBox<>();
        txtSrc = new javax.swing.JTextField();
        lbGender = new javax.swing.JLabel();
        comboGender = new javax.swing.JComboBox<>();
        lbPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        container.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lbName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbName.setText("Họ tên");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtName.setMargin(new java.awt.Insets(5, 10, 5, 10));

        lbID.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbID.setText("CMND/CCCD");

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtID.setMargin(new java.awt.Insets(5, 10, 5, 10));

        lbDOB.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbDOB.setText("Năm sinh");

        comboMonth.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbAddr.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbAddr.setText("Địa chỉ");

        comboCity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboDist.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboVill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbSta.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbSta.setText("Trạng thái");

        lbSrc.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbSrc.setText("Nguồn lây nhiễm (nếu có)");

        txtSta.setEditable(false);
        txtSta.setBackground(new java.awt.Color(204, 204, 255));
        txtSta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSta.setText("F0");
        txtSta.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtSta.setMargin(new java.awt.Insets(5, 10, 5, 10));

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnReset.setText("Làm mới");

        btnChooseSrc.setText("Chọn");
        btnChooseSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseSrcActionPerformed(evt);
            }
        });

        btnDelSrc.setText("Xoá");
        btnDelSrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelSrcActionPerformed(evt);
            }
        });

        lbTreat.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbTreat.setText("Nơi điều trị");

        comboQua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        comboYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboYearActionPerformed(evt);
            }
        });

        comboDay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSrc.setEditable(false);
        txtSrc.setBackground(new java.awt.Color(204, 204, 255));
        txtSrc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSrc.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtSrc.setMargin(new java.awt.Insets(5, 10, 5, 10));

        lbGender.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbGender.setText("Giới tính");

        comboGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        lbPhone.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbPhone.setText("Số điện thoại");

        txtPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPhone.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtPhone.setMargin(new java.awt.Insets(5, 10, 5, 10));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(lbID)
                .addGap(28, 28, 28)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lbTreat, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(lbAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(comboCity, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(comboDist, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(lbDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(lbGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboVill, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(lbSrc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(lbSta, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtSta, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(btnChooseSrc)
                .addGap(6, 6, 6)
                .addComponent(btnDelSrc, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbName)
                            .addComponent(lbID))))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbPhone))
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbDOB))
                    .addComponent(comboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbGender))))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbAddr))
                    .addComponent(comboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboVill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbSta))
                    .addComponent(txtSta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSrc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSrc)
                            .addComponent(btnChooseSrc)
                            .addComponent(btnDelSrc))))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbTreat))
                    .addComponent(comboQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(btnReset)))
        );

        add(container);
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseSrcActionPerformed
        EventFindCallBack callback;
        new FindDialog(new EventFindCallBack(){
            @Override
            public void setRelatedAddForm(String id, String status) {
                AddForm.this.txtSrc.setText(id);
                if(status.equals("Good")){
                    AddForm.this.txtSta.setText("Good");
                }
                if(status.equals("F0")){
                    AddForm.this.txtSta.setText("F1");
                }
                if(status.equals("F1")){
                    AddForm.this.txtSta.setText("F2");
                }
                if(status.equals("F2")){
                    AddForm.this.txtSta.setText("F3");
                }
                else{
                    AddForm.this.txtSta.setText("F3+");
                }
            }
            
        });
    }//GEN-LAST:event_btnChooseSrcActionPerformed

    private void comboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboYearActionPerformed

    boolean validation(){
        if(txtName.getText().isBlank() || txtID.getText().isBlank()
                || txtPhone.getText().isBlank()|| comboCity.getSelectedIndex()==0
                || comboDist.getSelectedIndex()==0|| comboVill.getSelectedIndex()==0){
            return false;
    }
        return true;
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            // TODO add your handling code here:
            if(!validation()){
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            profileModel user = new profileModel();
            user.setFullname(txtName.getText());
            user.setUsername(txtID.getText());
            String dob = comboYear.getSelectedItem() + "-" + comboMonth.getSelectedItem() + "-" + comboDay.getSelectedItem();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(dob);
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            user.setDob(sql);
            user.setGender((String) comboGender.getSelectedItem());

            user.setPhone(txtPhone.getText());

            AddressModel addr = (AddressModel) comboCity.getSelectedItem();
            user.setProvince(addr.getName());

            addr = (AddressModel) comboDist.getSelectedItem();
            user.setDistrict(addr.getName());

            addr = (AddressModel) comboVill.getSelectedItem();

            user.setVillage(addr.getName());

            user.setStatus(txtSta.getText());
            QuarantineModel quaratine = (QuarantineModel) comboQua.getSelectedItem();
            user.setId_qua(quaratine.getId());

            new CovidDAO().addAccount(user);
        } catch (SQLServerException ex) {
            Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelSrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelSrcActionPerformed
        // TODO add your handling code here:
        txtSrc.setText("");
        txtSta.setText("F0");
    }//GEN-LAST:event_btnDelSrcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChooseSrc;
    private javax.swing.JButton btnDelSrc;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<AddressModel> comboCity;
    private javax.swing.JComboBox<String> comboDay;
    private javax.swing.JComboBox<AddressModel> comboDist;
    private javax.swing.JComboBox<String> comboGender;
    private javax.swing.JComboBox<String> comboMonth;
    private javax.swing.JComboBox<QuarantineModel> comboQua;
    private javax.swing.JComboBox<AddressModel> comboVill;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JPanel container;
    private javax.swing.JLabel lbAddr;
    private javax.swing.JLabel lbDOB;
    private javax.swing.JLabel lbGender;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbSrc;
    private javax.swing.JLabel lbSta;
    private javax.swing.JLabel lbTreat;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSrc;
    private javax.swing.JTextField txtSta;
    // End of variables declaration//GEN-END:variables
}
