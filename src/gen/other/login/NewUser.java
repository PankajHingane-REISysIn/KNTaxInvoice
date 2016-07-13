package gen.other.login;

import gen.database.connection.DatabaseConnection1;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import gen.mainclass.MainClass;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class NewUser extends javax.swing.JInternalFrame {

    ResultSet rs;
    String q = "";

    public NewUser(String s) {
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
        txtUname = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        txtConfirmPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        btnCreateUser = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        optAdmin = new javax.swing.JRadioButton();
        optNormalUser = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtEmailId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        checkGroupLedger = new javax.swing.JCheckBox();
        checkReport = new javax.swing.JCheckBox();
        checkAccVouchers = new javax.swing.JCheckBox();
        checkProduction = new javax.swing.JCheckBox();
        btnAll = new javax.swing.JButton();
        btnNone = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
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
        jLabel1.setText("New User");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Password");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Confirm Password");

        txtUname.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtUname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnameFocusGained(evt);
            }
        });
        txtUname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnameKeyPressed(evt);
            }
        });

        txtPass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        txtConfirmPass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Rights :-");

        btnCreateUser.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCreateUser.setText("Create User");
        btnCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateUserActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
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

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Email_id");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Assign Rights"));
        jPanel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        checkGroupLedger.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        checkGroupLedger.setText("Group-Ledger Entry");
        checkGroupLedger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkGroupLedgerActionPerformed(evt);
            }
        });
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

        btnAll.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAll.setText("All");
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        btnNone.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNone.setText("None");
        btnNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnAll, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNone))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkAccVouchers)
                            .addComponent(checkGroupLedger))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkReport)
                            .addComponent(checkProduction)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkGroupLedger)
                    .addComponent(checkReport))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAccVouchers)
                    .addComponent(checkProduction))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAll)
                    .addComponent(btnNone))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Email_id Password");

        txtEmailpass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmailId)
                                    .addComponent(txtConfirmPass)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(91, 91, 91)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUname)
                                    .addComponent(txtPass, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEmailpass)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(optNormalUser)
                                        .addGap(13, 13, 13))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnCreateUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(optAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(139, 139, 139))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmailpass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(optAdmin)
                        .addGap(9, 9, 9)
                        .addComponent(optNormalUser)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateUser)
                    .addComponent(btnBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass m = new MainClass();
        m.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(5);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateUserActionPerformed
        if (txtUname.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter the Username");
            txtUname.requestFocus();
        } else if (txtPass.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter the Password");
            txtConfirmPass.setText("");
            txtPass.requestFocus();
        }/* else if (txtEmailpass.getText().equals("")) {
         JOptionPane.showMessageDialog(this, "Please Enter Email Id Password");
         txtEmailpass.requestFocus();
         } */ else if (txtEmailId.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter the Email Id");
            txtEmailId.requestFocus();
        } else if (txtPass.getText().equals(txtConfirmPass.getText()) == false) {
            JOptionPane.showMessageDialog(this, "Password Mismatch");
            txtConfirmPass.setText("");
            txtConfirmPass.requestFocus();
        } else if (checkGroupLedger.isSelected() == false && checkReport.isSelected() == false && checkAccVouchers.isSelected() == false && checkProduction.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "User has not been assigned any rights");
            checkGroupLedger.requestFocus();
        } else if (optAdmin.isSelected() == false && optNormalUser.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Select the User Type");
            optAdmin.requestFocus();
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

                    q = "select username,password from tbllogin where username='" + txtUname.getText() + "' or password='" + txtPass.getText() + "'";
                    rs = st.executeQuery(q);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "This Username or Password is already taken");
                        txtPass.setText("");
                        txtConfirmPass.setText("");
                        txtUname.requestFocus();
                    } else {
                        q = "insert into tbllogin(username,password,new_entry,acc_vouchers,report,production,user_type,email_id,email_pass) values('" + txtUname.getText() + "','" + txtPass.getText() + "'," + c1 + "," + c2 + "," + c3 + "," + c4 + "," + u + ",'" + txtEmailId.getText() + "','" + txtEmailpass.getText() + "')";
                        st.executeUpdate(q);
                        conn.commit();
                        JOptionPane.showMessageDialog(this, "New User is Created.");
                        btnBackActionPerformed(evt);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NewUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Email ID");
                txtEmailId.requestFocus();
                txtEmailId.selectAll();
            }
        }
    }//GEN-LAST:event_btnCreateUserActionPerformed

    private void optAdminStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optAdminStateChanged
        if (optAdmin.isSelected()) {
            optNormalUser.setSelected(false);
        }
        if (optNormalUser.isSelected() == false) {
            optAdmin.setSelected(true);
        }
    }//GEN-LAST:event_optAdminStateChanged

    private void optNormalUserStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optNormalUserStateChanged
        if (optNormalUser.isSelected()) {
            optAdmin.setSelected(false);
        }
        if (optAdmin.isSelected() == false) {
            optNormalUser.setSelected(true);
        }
    }//GEN-LAST:event_optNormalUserStateChanged

    private void txtConfirmPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtPass.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmailId.requestFocus();
        }
    }//GEN-LAST:event_txtConfirmPassKeyPressed

    private void txtUnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPass.requestFocus();
        }
    }//GEN-LAST:event_txtUnameKeyPressed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtUname.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtConfirmPass.requestFocus();
        }
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtEmailIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtConfirmPass.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmailpass.requestFocus();
        }
    }//GEN-LAST:event_txtEmailIdKeyPressed

    private void checkGroupLedgerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGroupLedgerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtEmailpass.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkReport.requestFocus();
        }
    }//GEN-LAST:event_checkGroupLedgerKeyPressed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        checkGroupLedger.setSelected(true);
        checkReport.setSelected(true);
        checkAccVouchers.setSelected(true);
        checkProduction.setSelected(true);
    }//GEN-LAST:event_btnAllActionPerformed

    private void btnNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoneActionPerformed
        checkGroupLedger.setSelected(false);
        checkReport.setSelected(false);
        checkAccVouchers.setSelected(false);
        checkProduction.setSelected(false);
    }//GEN-LAST:event_btnNoneActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnAll.setMnemonic(KeyEvent.VK_L);
        btnNone.setMnemonic(KeyEvent.VK_O);
        btnCreateUser.setMnemonic(KeyEvent.VK_C);
        btnBack.setMnemonic(KeyEvent.VK_B);
    }//GEN-LAST:event_formInternalFrameOpened

    private void checkReportKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkReportKeyPressed
        // TODO add your handling code here:
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

    private void optAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optAdminKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            checkProduction.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optNormalUser.requestFocus();
        }
    }//GEN-LAST:event_optAdminKeyPressed

    private void optNormalUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optNormalUserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optAdmin.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCreateUserActionPerformed(null);
        }
    }//GEN-LAST:event_optNormalUserKeyPressed

    private void checkGroupLedgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGroupLedgerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkGroupLedgerActionPerformed

    private void txtUnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnameFocusGained
        // TODO add your handling code here:
        txtUname.selectAll();
    }//GEN-LAST:event_txtUnameFocusGained

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
        // TODO add your handling code here:
        txtPass.selectAll();
    }//GEN-LAST:event_txtPassFocusGained

    private void txtConfirmPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtConfirmPassFocusGained
        // TODO add your handling code here:
        txtConfirmPass.selectAll();
    }//GEN-LAST:event_txtConfirmPassFocusGained

    private void txtEmailIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailIdFocusGained
        // TODO add your handling code here:
        txtEmailId.selectAll();
    }//GEN-LAST:event_txtEmailIdFocusGained
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateUser;
    private javax.swing.JButton btnNone;
    private javax.swing.JCheckBox checkAccVouchers;
    private javax.swing.JCheckBox checkGroupLedger;
    private javax.swing.JCheckBox checkProduction;
    private javax.swing.JCheckBox checkReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton optAdmin;
    private javax.swing.JRadioButton optNormalUser;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JTextField txtEmailId;
    private javax.swing.JPasswordField txtEmailpass;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUname;
    // End of variables declaration//GEN-END:variables
}
