package gen.other.login;

import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class UserRightSetting extends javax.swing.JInternalFrame {

    ResultSet resultSet;
    String q = "";
    JTextField tf;
    //ArrayList<String> GroupItems=new ArrayList<String>();
    private final Vector<String> v = new Vector<String>();

    public UserRightSetting(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkGroupLedger = new javax.swing.JCheckBox();
        checkReport = new javax.swing.JCheckBox();
        checkAccVouchers = new javax.swing.JCheckBox();
        checkProduction = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        optAdmin = new javax.swing.JRadioButton();
        optNormalUser = new javax.swing.JRadioButton();
        btnChange = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboUname = new javax.swing.JComboBox();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("User Right Setting");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Rights :-");

        checkGroupLedger.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkGroupLedger.setText("Group-Ledger Entry");
        checkGroupLedger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkGroupLedgerKeyPressed(evt);
            }
        });

        checkReport.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkReport.setText("Reports");
        checkReport.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkReportKeyPressed(evt);
            }
        });

        checkAccVouchers.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkAccVouchers.setText("Accounting Vouchers");
        checkAccVouchers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkAccVouchersKeyPressed(evt);
            }
        });

        checkProduction.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkProduction.setText("Production");
        checkProduction.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkProductionKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("User Type");

        optAdmin.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optAdmin.setText("Administrator");
        optAdmin.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optAdminStateChanged(evt);
            }
        });
        optAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optAdminKeyPressed(evt);
            }
        });

        optNormalUser.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optNormalUser.setText("Normal User");
        optNormalUser.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optNormalUserStateChanged(evt);
            }
        });
        optNormalUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optNormalUserKeyPressed(evt);
            }
        });

        btnChange.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnChange.setText("Change");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Username");

        comboUname.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboUname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUnameItemStateChanged(evt);
            }
        });
        comboUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUnameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(120, 120, 120))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(optNormalUser)
                            .addComponent(optAdmin))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(27, 27, 27)
                            .addComponent(comboUname, 0, 247, Short.MAX_VALUE)
                            .addGap(29, 29, 29))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(26, 26, 26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkGroupLedger)
                                .addComponent(checkAccVouchers))
                            .addGap(24, 24, 24)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkProduction)
                                .addComponent(checkReport))
                            .addContainerGap(32, Short.MAX_VALUE)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnChange)
                .addGap(27, 27, 27)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkGroupLedger)
                    .addComponent(jLabel5)
                    .addComponent(checkReport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAccVouchers)
                    .addComponent(checkProduction))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(optAdmin)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optNormalUser)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkGroupLedgerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGroupLedgerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUname.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkReport.requestFocus();
        }
}//GEN-LAST:event_checkGroupLedgerKeyPressed

    private void checkReportKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkReportKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            checkGroupLedger.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkAccVouchers.requestFocus();
        }
}//GEN-LAST:event_checkReportKeyPressed

    private void checkAccVouchersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkAccVouchersKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            checkReport.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkProduction.requestFocus();
        }
}//GEN-LAST:event_checkAccVouchersKeyPressed

    private void checkProductionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkProductionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            checkAccVouchers.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optAdmin.requestFocus();
        }
}//GEN-LAST:event_checkProductionKeyPressed

    private void optAdminStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optAdminStateChanged
        if (optAdmin.isSelected()) {
            optNormalUser.setSelected(false);
        }
        if (optNormalUser.isSelected() == false) {
            optAdmin.setSelected(true);
        }
}//GEN-LAST:event_optAdminStateChanged

    private void optAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optAdminKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            checkProduction.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optNormalUser.requestFocus();
        }
}//GEN-LAST:event_optAdminKeyPressed

    private void optNormalUserStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optNormalUserStateChanged
        if (optNormalUser.isSelected()) {
            optAdmin.setSelected(false);
        }
        if (optAdmin.isSelected() == false) {
            optNormalUser.setSelected(true);
        }
}//GEN-LAST:event_optNormalUserStateChanged

    private void optNormalUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optNormalUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optAdmin.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnChangeActionPerformed(null);
        }
}//GEN-LAST:event_optNormalUserKeyPressed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        if (comboUname.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Username");
            comboUname.requestFocus();
        } else if (checkGroupLedger.isSelected() == false && checkReport.isSelected() == false && checkAccVouchers.isSelected() == false && checkProduction.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "User has not been assigned any rights");
            checkGroupLedger.requestFocus();
        } else if (optAdmin.isSelected() == false && optNormalUser.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Select the User Type");
            optAdmin.requestFocus();

        } else {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                int c1 = 0, c2 = 0, c3 = 0, c4 = 0, u = 0;
                if (checkGroupLedger.isSelected()) {
                    c1 = 1;
                } else {
                    c1 = 0;
                }

                if (checkReport.isSelected()) {
                    c2 = 1;
                } else {
                    c2 = 0;
                }

                if (checkAccVouchers.isSelected()) {
                    c3 = 1;
                } else {
                    c3 = 0;
                }

                if (checkProduction.isSelected()) {
                    c4 = 1;
                } else {
                    c4 = 0;
                }

                if (optAdmin.isSelected()) {
                    u = 1;
                } else {
                    u = 2;
                }

                q = "update tbllogin set new_entry=" + c1 + ",acc_vouchers=" + c2 + ",report=" + c3 + ",production=" + c4 + ",user_type=" + u + " where username='" + comboUname.getSelectedItem() + "'";
                st.executeUpdate(q);
                conn.commit();
                JOptionPane.showMessageDialog(this, "User Right Setting is Changed.");
                btnBackActionPerformed(evt);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserRightSetting.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_btnChangeActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void comboUnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUnameItemStateChanged
        if (comboUname.getSelectedItem() == null) {
            checkGroupLedger.setSelected(false);
            checkReport.setSelected(false);
            checkAccVouchers.setSelected(false);
            checkProduction.setSelected(false);
            optNormalUser.setSelected(false);

        } else if (comboUname.getSelectedItem().equals("")) {
            checkGroupLedger.setSelected(false);
            checkReport.setSelected(false);
            checkAccVouchers.setSelected(false);
            checkProduction.setSelected(false);
            optNormalUser.setSelected(false);

        } else {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                q = "select * from tbllogin where username='" + comboUname.getSelectedItem() + "'";
                resultSet = st.executeQuery(q);
                if (resultSet.next()) {
                    if (resultSet.getInt("new_entry") == 1) {
                        checkGroupLedger.setSelected(true);
                    } else {
                        checkGroupLedger.setSelected(false);
                    }

                    if (resultSet.getInt("acc_vouchers") == 1) {
                        checkAccVouchers.setSelected(true);
                    } else {
                        checkAccVouchers.setSelected(false);
                    }

                    if (resultSet.getInt("report") == 1) {
                        checkReport.setSelected(true);
                    } else {
                        checkReport.setSelected(false);
                    }

                    if (resultSet.getInt("production") == 1) {
                        checkProduction.setSelected(true);
                    } else {
                        checkProduction.setSelected(false);
                    }

                    if (resultSet.getInt("user_type") == 1) {
                        optAdmin.setSelected(true);
                    } else {
                        optNormalUser.setSelected(true);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserRightSetting.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_comboUnameItemStateChanged

    private void comboUnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkGroupLedger.requestFocus();
        }
}//GEN-LAST:event_comboUnameKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnChange.setMnemonic(KeyEvent.VK_C);
        btnBack.setMnemonic(KeyEvent.VK_B);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            System.out.println("b4 query");
            comboUname.removeAllItems();
            comboUname.addItem("");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            q = "select username from tbllogin";
            resultSet = st.executeQuery(q);
            comboUname.setEditable(true);
            tf = (JTextField) comboUname.getEditor().getEditorComponent();

            while (resultSet.next()) {
                v.add(resultSet.getString("username"));
            }
            Collections.sort(
                    v,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });


            //cmbGroupAlter_Under.addItem(GroupItems);

            for (int i = 0; i < v.size(); i++) {
                comboUname.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboUname.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboUname.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboUname.showPopup();
                                }

                            }


                            //  String text1 = tf.getText();

                            int code = e.getKeyCode();
                            if (code == KeyEvent.VK_ENTER) {

                                System.out.println("Enter");
                                if (!v.contains(text)) {
                                    v.addElement(text);
                                    Collections.sort(v);
                                    setModel(getSuggestedModel(v, text), text);
                                }
                                hide_flag = true;

                            } else if (code == KeyEvent.VK_ESCAPE) {
                                hide_flag = true;
                            } else if (code == KeyEvent.VK_RIGHT) {
                                for (int i = 0; i < v.size(); i++) {
                                    String str = v.elementAt(i);
                                    if (str.startsWith(text)) {
                                        comboUname.setSelectedIndex(-1);
                                        tf.setText(str);
                                        return;

                                    }

                                }

                            }

                        }
                    });

                }
            });
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                checkGroupLedger.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                //      btnBackActionPerformed(null);
                            }
                        }
                    });
                }
            });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserRightSetting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass m = new MainClass();
        m.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChange;
    private javax.swing.JCheckBox checkAccVouchers;
    private javax.swing.JCheckBox checkGroupLedger;
    private javax.swing.JCheckBox checkProduction;
    private javax.swing.JCheckBox checkReport;
    private javax.swing.JComboBox comboUname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton optAdmin;
    private javax.swing.JRadioButton optNormalUser;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboUname.setModel(mdl);
        comboUname.setSelectedIndex(0);
        tf.setText(str);
        //tf.selectAll();
  /*    if(ComboGroupCreate_Under.getSelectedItem().equals(str))
         {
       
         {
         btnGroupCreate_Create.requestFocus();
         }     
         }*/

    }

    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            //if(s.startsWith(text)) m.addElement(s);
            if (s.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                m.addElement(s);
                System.out.println("Yes" + s);
            }
        }
        return m;
    }
}
