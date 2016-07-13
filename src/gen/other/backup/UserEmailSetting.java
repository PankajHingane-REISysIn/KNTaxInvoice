package gen.other.backup;

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

public class UserEmailSetting extends javax.swing.JInternalFrame {

    ResultSet rs;
    String q = "";
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    // ArrayList<String> GroupItems=new ArrayList<String>();

    public UserEmailSetting(String s) {
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
        comboUname = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtEmailId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnChange = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtEmailpass = new javax.swing.JPasswordField();

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
        jLabel1.setText("User Email Setting");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Username");

        comboUname.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboUname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboUnameItemStateChanged(evt);
            }
        });
        comboUname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUnameActionPerformed(evt);
            }
        });
        comboUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUnameKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Email_Id");

        txtEmailId.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtEmailId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailIdFocusGained(evt);
            }
        });
        txtEmailId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailIdKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Email_Id Password");

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

        txtEmailpass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtEmailpass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailpassFocusGained(evt);
            }
        });
        txtEmailpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailpassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailpass, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtEmailId)
                                .addComponent(comboUname, javax.swing.GroupLayout.Alignment.LEADING, 0, 202, Short.MAX_VALUE))))
                    .addComponent(jLabel9))
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(btnChange)
                .addGap(32, 32, 32)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(120, 120, 120))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboUname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnChange))
                .addGap(36, 36, 36))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboUnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboUnameItemStateChanged
        if (comboUname.getSelectedItem() == null) {
            txtEmailId.setText("");
            txtEmailpass.setText("");
        } else if (comboUname.getSelectedItem().equals("")) {
            txtEmailId.setText("");
            txtEmailpass.setText("");
        } else {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                q = "select email_id from tbllogin where username='" + comboUname.getSelectedItem() + "'";
                rs = st.executeQuery(q);
                if (rs.next()) {
                    txtEmailId.setText(rs.getString("email_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserEmailSetting.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_comboUnameItemStateChanged

    private void comboUnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {

            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmailId.requestFocus();
        }
}//GEN-LAST:event_comboUnameKeyPressed

    private void txtEmailIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailIdKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUname.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmailpass.requestFocus();
        }
}//GEN-LAST:event_txtEmailIdKeyPressed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        if (comboUname.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Please select the Username");
            comboUname.requestFocus();
        } else if (txtEmailId.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Email Id");
            txtEmailId.setText("");
            txtEmailId.requestFocus();
        } else if (txtEmailpass.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter Email_ID Password");
            txtEmailpass.setText("");
            txtEmailpass.requestFocus();
        } else {

            String email = txtEmailId.getText();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                Connection conn = null;
                try {
                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();

                    q = "update tbllogin set email_id='" + txtEmailId.getText() + "',email_pass='" + txtEmailpass.getText() + "' where username='" + comboUname.getSelectedItem() + "'";
                    st.executeUpdate(q);
                    conn.commit();
                    JOptionPane.showMessageDialog(this, "User Email Setting is Changed.");
                    btnBackActionPerformed(evt);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(UserEmailSetting.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Email ID");
                txtEmailId.requestFocus();
                txtEmailId.selectAll();
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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


                            String text1 = tf.getText();
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
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                txtEmailId.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                btnBackActionPerformed(null);
                            }
                        }
                    });
                }
            });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserEmailSetting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass m = new MainClass();
        m.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtEmailpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailpassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtEmailId.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnChangeActionPerformed(null);
        }
    }//GEN-LAST:event_txtEmailpassKeyPressed

    private void comboUnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUnameActionPerformed
    }//GEN-LAST:event_comboUnameActionPerformed

    private void txtEmailIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailIdFocusGained
        // TODO add your handling code here:
        txtEmailId.selectAll();
    }//GEN-LAST:event_txtEmailIdFocusGained

    private void txtEmailpassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailpassFocusGained
        // TODO add your handling code here:
        txtEmailpass.selectAll();
    }//GEN-LAST:event_txtEmailpassFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChange;
    private javax.swing.JComboBox comboUname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtEmailId;
    private javax.swing.JPasswordField txtEmailpass;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboUname.setModel(mdl);
        comboUname.setSelectedIndex(-1);
        tf.setText(str);
        //tf.selectAll();
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
