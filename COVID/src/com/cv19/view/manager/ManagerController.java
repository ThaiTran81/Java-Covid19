
package com.cv19.view.manager;

import Controller.CovidDAO;
import Controller.HistoryDAO;
import Controller.LoginController;
import Model.profileModel;
import com.cv19.view.body.AddForm;
import com.cv19.view.body.HomeForm;
import com.cv19.view.body.ManageForm;
import com.cv19.view.body.MyProfileForm;
import com.cv19.view.body.NecessityForm;
import com.cv19.view.body.OutBalanceForm;
import com.cv19.view.event.EventMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FUtil;

/**
 *
 * @author ThaiTran
 */
public class ManagerController extends javax.swing.JFrame {
    private Point initialClick;
    private profileModel profile;
    
    public ManagerController(String id) {
        try {
            initComponents();
            profile = new CovidDAO().getProfileUser(id);
            setBackground(new Color(0,0,0,0));
            menu.initFrame(this);
            
            //build graph
            
            FUtil.buildGraph();
            
            //add name user to menu
            
            menu.setName(profile.getFullname());
            HistoryDAO.setID(profile.getUsername());
            // add listen
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    initialClick = e.getPoint();
                    getComponentAt(initialClick);
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    
                    // get location of Window
                    int thisX = getLocation().x;
                    int thisY = getLocation().y;
                    
                    // Determine how much the mouse moved since the initial click
                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;
                    
                    // Move window to this position
                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;
                    setLocation(X, Y);
                }
            });
            
            // show form
            showForm(new HomeForm());

        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground = new com.cv19.swing.PanelBack();
        menu = new com.cv19.view.components.Menu();
        body = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelBackground.setBackground(new java.awt.Color(255, 255, 255));

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(body)
                .addContainerGap())
        );

        getContentPane().add(panelBackground, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.repaint();
        body.revalidate();
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        menu.setSelectedIndex(0);
        menu.addEvent(new EventMenu(){
            @Override
            public void menuIndexChange(int index) {
                if(index == 0){
                    showForm(new HomeForm());
                }
                if (index == 1){
                    showForm(new AddForm());
                }
                if(index ==2){
                    showForm(new ManageForm());
                }
                if(index==3){
                    showForm(new NecessityForm());
                }
                if(index==4){
                    showForm(new OutBalanceForm());
                }
                if(index ==5){
                    showForm(new MyProfileForm(profile));
                }
                if(index == 6){
                    new LoginController();
                    ManagerController.this.dispose();
                }
            }
            
        });
    }//GEN-LAST:event_formWindowOpened

    public void setName(String name){
        menu.setName(name);
    }
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerController("1").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane body;
    private com.cv19.view.components.Menu menu;
    private com.cv19.swing.PanelBack panelBackground;
    // End of variables declaration//GEN-END:variables
}
