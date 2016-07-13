package gen.other.login;

import gen.database.connection.DatabaseConnection1;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import gen.mainclass.MainClass;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class ChangePass extends javax.swing.JInternalFrame {

    ResultSet rs;
    String q = "";
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();

    public ChangePass(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnChange = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        comboUname = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JPasswordField();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Change Password");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Current Password");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("New Password");

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

        txtPass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        txtNewPass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNewPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNewPassFocusGained(evt);
            }
        });
        txtNewPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNewPassKeyPressed(evt);
            }
        });

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

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Confirm Password");

        txtConfirmPass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtConfirmPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPassActionPerformed(evt);
            }
        });
        txtConfirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtConfirmPassFocusGained(evt);
            }
        });
        txtConfirmPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfirmPassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboUname, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPass, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNewPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addComponent(txtConfirmPass))
                .addGap(99, 99, 99))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(btnChange)
                .addGap(33, 33, 33)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnChange)
                    .addComponent(btnBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass m = new MainClass();
        m.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        int a = 0;
        if (tf.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Username");
            comboUname.requestFocus();
            System.out.println("Please select the Username............>>>>>>" + tf.getText().toString().trim());
        } else if (txtPass.getText().toString().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please enter the current Password");
            txtNewPass.setText("");
            txtPass.requestFocus();
            System.out.println("Please enter the current Password............>>>>>>" + txtPass.getText().toString().trim());
        } else if (txtNewPass.getText().toString().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please enter the New Password");
            txtNewPass.setText("");
            txtNewPass.requestFocus();
        } else if (txtNewPass.getText().toString().trim().equalsIgnoreCase(txtConfirmPass.getText().toString().trim()) == false) {
            JOptionPane.showMessageDialog(this, "Password Mismatch");
            System.out.println("Password Mismatch............>>>>>>txtNewPass.getPassword().toString().trim()" + txtNewPass.getText().toString().trim());
            System.out.println("Password Mismatch............>>>>>>txtConfirmPass.getPassword().toString().trim()" + txtConfirmPass.getText().toString().trim());
            txtNewPass.setText("");
            txtNewPass.requestFocus();
        } else {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();

                q = "select username,password from tbllogin";
                rs = st.executeQuery(q);
                while (rs.next()) {
                    if (txtPass.getText().toString().trim().equalsIgnoreCase(rs.getString("password")) && tf.getText().toString().trim().equalsIgnoreCase(rs.getString("username"))) {
                        q = "select username,password from tbllogin where password = ?";
//                        rs1 = st.executeQuery(q);
                        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTt" + q);
                        PreparedStatement stat1 = conn.prepareStatement(q);
                        stat1.setString(1, txtNewPass.getText().toString().trim());
                        ResultSet rs1 = stat1.executeQuery();

                        if (rs1.next()) {
                            JOptionPane.showMessageDialog(this, "This Password is already taken");
                            txtPass.setText("");
                            txtNewPass.setText("");
                            txtNewPass.requestFocus();
                        } else {
                            q = "update tbllogin set password= ? where username= ?";
                            //st.executeUpdate(q);
                            PreparedStatement stat = conn.prepareStatement(q);
                            stat.setString(1, txtNewPass.getText().toString().trim());
                            stat.setString(2, tf.getText().toString().trim());
                            stat.executeUpdate();

                            conn.commit();
                            JOptionPane.showMessageDialog(this, "Password is Changed.");
                            btnBackActionPerformed(evt);
                        }
                        a = 2;
                    }
                }
                System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" + a);
                if (a != 2) {
                    JOptionPane.showMessageDialog(this, "You may Entered Wrong Password or UserName");
                    tf.requestFocus();
                    tf.setText("");
                    txtConfirmPass.setText("");
                    txtNewPass.setText("");
                    txtPass.setText("");
                }
                a = 1;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        //        if (comboUname.getSelectedItem().equals("")) {
//            JOptionPane.showMessageDialog(this, "Please select the Username");
//            comboUname.requestFocus();
//        } else if (txtPass.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Please enter the current Password");
//            txtNewPass.setText("");
//            txtPass.requestFocus();
//        } else if (txtNewPass.getText().equals(txtConfirmPass.getText()) == false) {
//            JOptionPane.showMessageDialog(this, "Password Mismatch");
//            txtNewPass.setText("");
//            txtNewPass.requestFocus();
//        } else {
//            Connection conn = null;
//            try {
//                conn = DatabaseConnection1.GetConnection();
//                conn.setAutoCommit(false);
//                Statement st = conn.createStatement();
//
//                q = "select username,password from tbllogin where password='" + txtNewPass.getText() + "'";
//                rs = st.executeQuery(q);
//                if (rs.next()) {
//                    JOptionPane.showMessageDialog(this, "This Password is already taken");
//                    txtPass.setText("");
//                    txtNewPass.setText("");
//                    txtNewPass.requestFocus();
//                } else {
//                    q = "update tbllogin set password='" + txtNewPass.getText() + "' where username='" + comboUname.getSelectedItem() + "'";
//                    st.executeUpdate(q);
//                    conn.commit();
//                    JOptionPane.showMessageDialog(this, "Password is Changed.");
//                    btnBackActionPerformed(evt);
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }//GEN-LAST:event_btnChangeActionPerformed

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
            q = "select username from tbllogin";
            rs = st.executeQuery(q);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            comboUname.setEditable(true);
            tf = (JTextField) comboUname.getEditor().getEditorComponent();

            while (rs.next()) {
                v.add(rs.getString("username"));
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
                                txtPass.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                //      btnBackActionPerformed(null);
                            }
                        }
                    });
                }
            });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void comboUnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUnameItemStateChanged
    }//GEN-LAST:event_comboUnameItemStateChanged

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUname.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNewPass.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed

    private void comboUnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnameKeyPressed
        /*  if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

         btnBackActionPerformed(null);
         }
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         txtPass.requestFocus();
         }*/
    }//GEN-LAST:event_comboUnameKeyPressed

    private void comboUnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboUnameActionPerformed

    private void txtNewPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtPass.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtConfirmPass.requestFocus();
        }
    }//GEN-LAST:event_txtNewPassKeyPressed

    private void btnChangeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnChangeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChangeKeyPressed

    private void txtConfirmPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmPassActionPerformed

    private void txtConfirmPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtNewPass.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnChangeActionPerformed(null);
        }
    }//GEN-LAST:event_txtConfirmPassKeyPressed

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        // TODO add your handling code here:
        txtPass.selectAll();
    }//GEN-LAST:event_txtPassFocusGained

    private void txtNewPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNewPassFocusGained
        // TODO add your handling code here:
        txtNewPass.selectAll();
    }//GEN-LAST:event_txtNewPassFocusGained

    private void txtConfirmPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmPassFocusGained
        // TODO add your handling code here:
        txtConfirmPass.selectAll();
    }//GEN-LAST:event_txtConfirmPassFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChange;
    private javax.swing.JComboBox comboUname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtPass;
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
