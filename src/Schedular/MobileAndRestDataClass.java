/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Schedular;

import AdSuMuDiProductRegistration.PurchaseLicenceDTO;
import gen.dto.Label;
import gen.other.login.User.CreateUser;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import Schedular.CronTrigger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class MobileAndRestDataClass extends javax.swing.JInternalFrame {

    private static Scheduler sch;
    private static int cronTrigger = 0;
    private static Pattern pattern = Pattern.compile("[A-Z]");

    /**
     * Creates new form MobileAndRestDataClass
     */
    public MobileAndRestDataClass(String className) {
        initComponents();
        setClosable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMobile_Key = new javax.swing.JLabel();
        lblMobile_User_1 = new javax.swing.JLabel();
        lblMobile_User_2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMobileKeyList = new javax.swing.JTable();
        jCheckBoxCronTriggerCheckOut = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setTitle(org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.title")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMobile_Key, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.lblMobile_Key.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMobile_User_1, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.lblMobile_User_1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblMobile_User_2, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.lblMobile_User_2.text")); // NOI18N

        jTableMobileKeyList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sr.No.", "Mobile Unique Key", "Mobile User Keys"
            }
        ));
        jScrollPane1.setViewportView(jTableMobileKeyList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxCronTriggerCheckOut, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jCheckBoxCronTriggerCheckOut.text")); // NOI18N
        jCheckBoxCronTriggerCheckOut.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxCronTriggerCheckOutItemStateChanged(evt);
            }
        });
        jCheckBoxCronTriggerCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCronTriggerCheckOutActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(MobileAndRestDataClass.class, "MobileAndRestDataClass.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblMobile_Key))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblMobile_User_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMobile_User_2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(499, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jCheckBoxCronTriggerCheckOut)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblMobile_Key))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblMobile_User_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblMobile_User_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxCronTriggerCheckOut)
                    .addComponent(jButton3))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        new Thread(new Runnable() {
            @Override
            public void run() {
                sch = CronTrigger.callTrigger();
            }
        }).start();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            sch.shutdown();
        } catch (SchedulerException ex) {
            Logger.getLogger(MobileAndRestDataClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        lblMobile_Key.setVisible(false);
        lblMobile_User_1.setVisible(false);
        lblMobile_User_2.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);

        if (cronTrigger == 1) {
            jCheckBoxCronTriggerCheckOut.setSelected(true);
        }

        DefaultTableModel dtm = (DefaultTableModel) jTableMobileKeyList.getModel();
        dtm.setRowCount(0);
        dtm.setColumnCount(3);
        jTableMobileKeyList.setModel(dtm);
        try {
            // ADD into Table User Deatils
            List<PurchaseLicenceDTO> purchaseLicenceDTO = AdSuMuDiProductRegistration.PurchaseLicenceDAO.get_Licence_Information();
            int count = 1;
            for (PurchaseLicenceDTO purchaseLicenceDTOi : purchaseLicenceDTO) {
                if (pattern.matcher(purchaseLicenceDTOi.getMobile_key()).find() && pattern.matcher(purchaseLicenceDTOi.getMobile_User_1()).find()) {
                dtm.setRowCount(dtm.getRowCount() + 1);
                jTableMobileKeyList.setValueAt(count, dtm.getRowCount() - 1, 0);
                jTableMobileKeyList.setValueAt(purchaseLicenceDTOi.getMobile_key(), dtm.getRowCount() - 1, 1);
                jTableMobileKeyList.setValueAt(purchaseLicenceDTOi.getMobile_User_1(), dtm.getRowCount() - 1, 2);
                count++;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(CreateUser.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void jCheckBoxCronTriggerCheckOutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxCronTriggerCheckOutItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxCronTriggerCheckOutItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxCronTriggerCheckOut.isSelected()) {
            cronTrigger = 1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sch = CronTrigger.callTrigger();
                }
            }).start();
            JOptionPane.showMessageDialog(MobileAndRestDataClass.this, Label.CRON_TRIGGER_TURNED_ON);
        } else {
            cronTrigger = 0;
            try {
                // TODO add your handling code here:
                sch.shutdown();
            } catch (SchedulerException ex) {
                Logger.getLogger(MobileAndRestDataClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(MobileAndRestDataClass.this, Label.CRON_TRIGGER_TURNED_OFF);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBoxCronTriggerCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCronTriggerCheckOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxCronTriggerCheckOutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBoxCronTriggerCheckOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMobileKeyList;
    private javax.swing.JLabel lblMobile_Key;
    private javax.swing.JLabel lblMobile_User_1;
    private javax.swing.JLabel lblMobile_User_2;
    // End of variables declaration//GEN-END:variables
}