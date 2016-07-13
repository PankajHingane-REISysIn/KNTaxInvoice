package gen.account.ledger;

import gen.account.group.Group_Create;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class LedgerAlterFromDetails extends javax.swing.JInternalFrame {

    private CharSequence Number;
    static int window = 0;
    static String str = "";
    static long l_id = 0;
    // ArrayList<String> GroupItems=new ArrayList<String>();
    private final Vector<String> v = new Vector<String>();
    private final Vector<String> v1 = new Vector<String>();
    JTextField tf;
    static int combocontrol = 0;
    static int comboFocusControl = 0;

    public LedgerAlterFromDetails(String s, long id) {
        setClosable(true);
        initComponents();
        l_id = id;
        this.setTitle(s);
        this.setLayout(new FlowLayout());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAlias = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboUnder = new javax.swing.JComboBox();
        btnCreateGroup = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAddr = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIncomeTaxNo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSaleTaxNo = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        optDebit = new javax.swing.JRadioButton();
        optCredit = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtCreditLimit = new javax.swing.JFormattedTextField();
        txtBalance = new javax.swing.JFormattedTextField();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Symbol.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 582));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name");

        txtName.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFocusGained(evt);
            }
        });
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias");

        txtAlias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAliasFocusGained(evt);
            }
        });
        txtAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAliasKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under");

        comboUnder.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboUnder.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboUnderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboUnderFocusLost(evt);
            }
        });
        comboUnder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboUnderKeyPressed(evt);
            }
        });

        btnCreateGroup.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCreateGroup.setText("Create Group");
        btnCreateGroup.setFocusPainted(false);
        btnCreateGroup.setFocusable(false);
        btnCreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateGroupActionPerformed(evt);
            }
        });
        btnCreateGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCreateGroupKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Opening Balance");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Address");

        txtAddr.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtAddr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAddrFocusGained(evt);
            }
        });
        txtAddr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddrKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Contact No");

        txtContact.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Email Id");

        txtEmail.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Income Tax Number");

        txtIncomeTaxNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtIncomeTaxNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIncomeTaxNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIncomeTaxNoFocusLost(evt);
            }
        });
        txtIncomeTaxNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIncomeTaxNoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIncomeTaxNoKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Sale Tax Number");

        txtSaleTaxNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtSaleTaxNo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSaleTaxNoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSaleTaxNoFocusLost(evt);
            }
        });
        txtSaleTaxNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSaleTaxNoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSaleTaxNoKeyTyped(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        optDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optDebit.setText("Debit");
        optDebit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optDebitStateChanged(evt);
            }
        });
        optDebit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optDebitKeyPressed(evt);
            }
        });

        optCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optCredit.setText("Credit");
        optCredit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optCreditStateChanged(evt);
            }
        });
        optCredit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                optCreditKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Credit Limit");

        txtCreditLimit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCreditLimit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCreditLimitFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCreditLimitFocusLost(evt);
            }
        });
        txtCreditLimit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditLimitKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCreditLimitKeyTyped(evt);
            }
        });

        txtBalance.setText("0");
        txtBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtBalance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBalanceFocusLost(evt);
            }
        });
        txtBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBalanceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBalanceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(148, 148, 148)
                                    .addComponent(comboUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(btnCreateGroup))
                                .addComponent(jLabel3)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(53, 53, 53)
                                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(optDebit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(optCredit))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel9))
                                    .addGap(88, 88, 88)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(132, 132, 132)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(115, 115, 115)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(120, 120, 120)
                                        .addComponent(txtAlias)))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(53, 53, 53)
                                    .addComponent(txtSaleTaxNo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(36, 36, 36)
                                    .addComponent(txtIncomeTaxNo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(79, 79, 79)
                                    .addComponent(txtCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(37, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnUpdate)
                        .addGap(131, 131, 131))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtAlias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateGroup)
                    .addComponent(comboUnder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(optDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(optCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtIncomeTaxNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtSaleTaxNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnUpdate))
                .addGap(133, 133, 133))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAlias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
}//GEN-LAST:event_txtNameKeyPressed

    private void txtAliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboUnder.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtName.requestFocus();
        }
}//GEN-LAST:event_txtAliasKeyPressed

    private void comboUnderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUnderFocusGained
//        comboUnder.showPopup();
}//GEN-LAST:event_comboUnderFocusGained

    private void comboUnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboUnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtBalance.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtAlias.requestFocus();
        }
}//GEN-LAST:event_comboUnderKeyPressed

    private void btnCreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateGroupActionPerformed
        Group_Create group = new Group_Create("Create New Group");
        try {
            group.setVisible(true);
            group.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(LedgerAlterFromDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        // group.setVisible(true);
        // group.pack();
        //    group.setVisible(true);
        this.getParent().add(group);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = group.getSize();
        group.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
//            group.setSize(desktopSize);
//            group.setPreferredSize(desktopSize);
        try {
            group.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }


        BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) group.getUI();

        Component north = basicInternalFrameUI.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }
        // comboUnder.requestFocus();
        comboFocusControl = 1;
}//GEN-LAST:event_btnCreateGroupActionPerformed

    private void txtAddrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtContact.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optCredit.requestFocus();
        }
}//GEN-LAST:event_txtAddrKeyPressed

    private void txtContactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtEmail.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtAddr.requestFocus();
        }
}//GEN-LAST:event_txtContactKeyPressed

    private void txtContactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
}//GEN-LAST:event_txtContactKeyTyped

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIncomeTaxNo.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtContact.requestFocus();
        }
}//GEN-LAST:event_txtEmailKeyPressed

    private void txtIncomeTaxNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIncomeTaxNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSaleTaxNo.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtEmail.requestFocus();
        }
}//GEN-LAST:event_txtIncomeTaxNoKeyPressed

    private void txtIncomeTaxNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIncomeTaxNoKeyTyped
}//GEN-LAST:event_txtIncomeTaxNoKeyTyped

    private void txtSaleTaxNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaleTaxNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCreditLimit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtIncomeTaxNo.requestFocus();
        }
}//GEN-LAST:event_txtSaleTaxNoKeyPressed

    private void txtSaleTaxNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSaleTaxNoKeyTyped
}//GEN-LAST:event_txtSaleTaxNoKeyTyped

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        String no = txtContact.getText().trim();
        if (txtName.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Data in Name Fields");
            txtName.requestFocus();
        } else if (comboUnder.getSelectedItem() == null || comboUnder.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            tf.requestFocus();
        } else if (!checkElementPresence(v1, tf.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Under Field Value is not valid");
            //scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            tf.requestFocus();
        } /* else if(no.length() != 10&&txtContact.getText().trim().equals("")==false) 
         {
         JOptionPane.showMessageDialog(null,"Plz Enter valid Contact No.");
         txtContact.requestFocus();
         txtContact.selectAll();
         }*/ else {
            String email = txtEmail.getText().trim();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches() && email.equals("") == false) {
                Connection conn = null;
                try {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar currentDate = Calendar.getInstance();
                    String dateNow = f.format(currentDate.getTime());

                    String name = txtName.getText().trim();
                    String alias = txtAlias.getText().trim();
                    String under = (String) comboUnder.getSelectedItem();

                    double balance = 0;
                    if (txtBalance.getText().trim().equals("") == false) {
                        balance = Double.parseDouble(txtBalance.getText().trim());
                    }

                    String address = txtAddr.getText().trim();
                    String contactNo = txtContact.getText().trim();
                    String emailId = txtEmail.getText().trim();

                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();
                    ResultSet rs3 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtName.getText() + "' and ledger_id<>" + l_id + "");
                    if (rs3.next()) {
                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtName.selectAll();
                        txtName.requestFocus();
                    } else {


                        ResultSet rs = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                        if (rs.next()) {
                            double blnc = 0;
                            int DebCred = 0;
                            String q = "";
                            //**********************************************                        
                            ResultSet rs1 = st1.executeQuery("select ledger_openingbalance,opening_type from tblledger where ledger_id='" + l_id + "'");
                            if (rs1.next()) {

                                int ot = 0;//opening type
                                if (optCredit.isSelected()) {
                                    ot = 2;
                                } else {
                                    ot = 1;
                                }

                                //-----------------update Ledger Table---------------------
                                // st3.executeUpdate("UPDATE tblledger SET ledger_name='"+name+"',ledger_under="+rs.getLong("group_id")+",ledger_alias='"+alias+"',ledger_address='"+address+"',ledger_contactno='"+contactNo+"',ledger_emailid='"+emailId+"',ledger_openingbalance="+balance+",ledger_intaxo='"+txtIncomeTaxNo.getText()+"',ledger_saletaxno='"+txtSaleTaxNo.getText()+"',opening_type="+ ot +",dat='"+dateNow+"' where ledger_id="+l_id+"");
                                PreparedStatement ps = null;
                                String sql = "UPDATE tblledger SET ledger_name=?,ledger_under=?,ledger_alias=?,ledger_address=?,ledger_contactno=?,ledger_emailid=?,ledger_openingbalance=?,ledger_intaxo=?,ledger_saletaxno=?,opening_type=?,dat=? where ledger_id=?";
                                ps = conn.prepareStatement(sql);
                                ps.setString(1, name.trim());
                                ps.setLong(2, rs.getLong("group_id"));
                                ps.setString(3, alias.trim());
                                ps.setString(4, address.trim());
                                ps.setString(5, contactNo.trim());
                                ps.setString(6, emailId.trim());
                                ps.setDouble(7, balance);
                                ps.setString(8, txtIncomeTaxNo.getText().trim());
                                ps.setString(9, txtSaleTaxNo.getText().trim());
                                ps.setInt(10, ot);
                                ps.setString(11, dateNow);
                                ps.setLong(12, l_id);

                                ps.executeUpdate();

                                //------------update Ledger Credit Limit Table---------------
                                double c = 0;
                                if (txtCreditLimit.getText().equals("") == false) {
                                    c = Double.parseDouble(txtCreditLimit.getText());
                                }
                                st3.executeUpdate("UPDATE tblledgercreditlimit SET ledger_limit=" + c + " where ledger_id=" + l_id + "");

                                blnc = rs1.getDouble("ledger_openingbalance");
                                if (rs1.getInt("opening_type") == 2) {
                                    if (optCredit.isSelected()) {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 1;
                                    }

                                } else {
                                    if (optCredit.isSelected()) {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 1;
                                    }

                                }

                                ResultSet rs2 = st2.executeQuery("select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id='" + l_id + "'");
                                if (rs2.next()) {
                                    if (rs2.getInt("ledger_DebitOrCredit") == 2) {
                                        if (DebCred == 2) {
                                            blnc = Math.abs(blnc) + rs2.getDouble("ledger_currentBalance");
                                            DebCred = 2;
                                        } else {
                                            if (Math.abs(blnc) > rs2.getDouble("ledger_currentBalance")) {
                                                DebCred = 1;
                                            } else {
                                                DebCred = 2;
                                            }
                                            blnc = Math.abs(blnc) - rs2.getDouble("ledger_currentBalance");
                                        }

                                    } else {
                                        if (DebCred == 1) {
                                            blnc = Math.abs(blnc) + rs2.getDouble("ledger_currentBalance");
                                            DebCred = 1;
                                        } else {
                                            if (Math.abs(blnc) > rs2.getDouble("ledger_currentBalance")) {
                                                DebCred = 2;
                                            } else {
                                                DebCred = 1;
                                            }
                                            blnc = Math.abs(blnc) - rs2.getDouble("ledger_currentBalance");
                                        }

                                    }
                                    st.executeUpdate("UPDATE tblledgercurrentbalance SET ledger_currentBalance=" + Math.abs(blnc) + ",ledger_DebitOrCredit=" + DebCred + " where ledger_id=" + l_id + "");

                                }

                            }
                        }
                        conn.commit();
                        Constants.LEDGER_TIME_STAMP++;
                        JOptionPane.showMessageDialog(null, "Record Updated Successfully");
                        // btnBackActionPerformed(null);
                        //    btnBackActionPerformed(evt);
                        txtName.setText("");
                        txtAlias.setText("");
                        txtBalance.setText("0");
                        txtAddr.setText("");
                        txtContact.setText("");
                        txtEmail.setText("");
                        txtIncomeTaxNo.setText("");
                        txtSaleTaxNo.setText("");
                        comboUnder.setSelectedIndex(0);
                        //   btnBackActionPerformed(evt);
                        //**********************************************
                        conn.commit();
                        btnBackActionPerformed(evt);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (email.equals("")) {
                Connection conn = null;
                try {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar currentDate = Calendar.getInstance();
                    String dateNow = f.format(currentDate.getTime());

                    String name = txtName.getText().trim();
                    String alias = txtAlias.getText().trim();
                    String under = (String) comboUnder.getSelectedItem();

                    double balance = 0;
                    if (txtBalance.getText().equals("") == false) {
                        balance = Double.parseDouble(txtBalance.getText());
                    }

                    String address = txtAddr.getText().trim();
                    String contactNo = txtContact.getText().trim();
                    String emailId = txtEmail.getText().trim();

                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();
                    ResultSet rs3 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtName.getText() + "' and ledger_id<>" + l_id + "");
                    if (rs3.next()) {
                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtName.selectAll();
                        txtName.requestFocus();
                    } else {
                        ResultSet rs = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                        if (rs.next()) {
                            double blnc = 0;
                            int DebCred = 0;
                            String q = "";
                            //**********************************************                        
                            ResultSet rs1 = st1.executeQuery("select ledger_openingbalance,opening_type from tblledger where ledger_id='" + l_id + "'");
                            if (rs1.next()) {

                                int ot = 0;//opening type
                                if (optCredit.isSelected()) {
                                    ot = 2;
                                } else {
                                    ot = 1;
                                }

                                //-----------------update Ledger Table---------------------
                                st3.executeUpdate("UPDATE tblledger SET ledger_name='" + name + "',ledger_under=" + rs.getLong("group_id") + ",ledger_alias='" + alias + "',ledger_address='" + address + "',ledger_contactno='" + contactNo + "',ledger_openingbalance=" + balance + ",ledger_intaxo='" + txtIncomeTaxNo.getText() + "',ledger_saletaxno='" + txtSaleTaxNo.getText() + "',opening_type=" + ot + ",ledger_emailid='" + emailId + "',dat='" + dateNow + "' where ledger_id=" + l_id + "");

                                //------------update Ledger Credit Limit Table---------------
                                double c = 0;
                                if (txtCreditLimit.getText().equals("") == false) {
                                    c = Double.parseDouble(txtCreditLimit.getText());
                                }
                                st3.executeUpdate("UPDATE tblledgercreditlimit SET ledger_limit=" + c + " where ledger_id=" + l_id + "");

                                blnc = rs1.getDouble("ledger_openingbalance");
                                if (rs1.getInt("opening_type") == 2) {
                                    if (optCredit.isSelected()) {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 1;
                                    }

                                } else {
                                    if (optCredit.isSelected()) {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtBalance.getText().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtBalance.getText());
                                        }
                                        DebCred = 1;
                                    }

                                }

                                ResultSet rs2 = st2.executeQuery("select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id='" + l_id + "'");
                                if (rs2.next()) {
                                    if (rs2.getInt("ledger_DebitOrCredit") == 2) {
                                        if (DebCred == 2) {
                                            blnc = Math.abs(blnc) + rs2.getDouble("ledger_currentBalance");
                                            DebCred = 2;
                                        } else {
                                            if (Math.abs(blnc) > rs2.getDouble("ledger_currentBalance")) {
                                                DebCred = 1;
                                            } else {
                                                DebCred = 2;
                                            }
                                            blnc = Math.abs(blnc) - rs2.getDouble("ledger_currentBalance");
                                        }

                                    } else {
                                        if (DebCred == 1) {
                                            blnc = Math.abs(blnc) + rs2.getDouble("ledger_currentBalance");
                                            DebCred = 1;
                                        } else {
                                            if (Math.abs(blnc) > rs2.getDouble("ledger_currentBalance")) {
                                                DebCred = 2;
                                            } else {
                                                DebCred = 1;
                                            }
                                            blnc = Math.abs(blnc) - rs2.getDouble("ledger_currentBalance");
                                        }

                                    }
                                    st.executeUpdate("UPDATE tblledgercurrentbalance SET ledger_currentBalance=" + Math.abs(blnc) + ",ledger_DebitOrCredit=" + DebCred + " where ledger_id=" + l_id + "");

                                    JOptionPane.showMessageDialog(null, "Record Updated Successfully");
                                    //  btnBackActionPerformed(null);
                                    //    btnBackActionPerformed(evt);
                                }
                            }

                        }
                        //**********************************************
                        txtName.setText("");
                        txtAlias.setText("");
                        txtBalance.setText("0");
                        txtAddr.setText("");
                        txtContact.setText("");
                        txtEmail.setText("");
                        txtIncomeTaxNo.setText("");
                        txtSaleTaxNo.setText("");
                        comboUnder.setSelectedIndex(0);
                        // btnBackActionPerformed(evt);
                        conn.commit();
                        btnBackActionPerformed(evt);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Email ID");
                txtEmail.requestFocus();
                txtEmail.selectAll();
            }

        }

        //btnBackActionPerformed(evt);
}//GEN-LAST:event_btnUpdateActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        LedgerDetails g = new LedgerDetails("Show Ledger Details");


        this.getParent().add(g);
        g.setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = g.getSize();
        g.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);


        try {
            g.setSelected(true);
            this.setSelected(true);
            this.setClosed(true);
        } catch (java.beans.PropertyVetoException e) {
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void optDebitStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optDebitStateChanged
        if (optDebit.isSelected()) {
            optCredit.setSelected(false);
        }
        if (optCredit.isSelected() == false) {
            optDebit.setSelected(true);
        }
}//GEN-LAST:event_optDebitStateChanged

    private void optDebitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optDebitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optCredit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtBalance.requestFocus();
        }
}//GEN-LAST:event_optDebitKeyPressed

    private void optCreditStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optCreditStateChanged
        if (optCredit.isSelected()) {
            optDebit.setSelected(false);
        }
        if (optDebit.isSelected() == false) {
            optCredit.setSelected(true);
        }
}//GEN-LAST:event_optCreditStateChanged

    private void optCreditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optCreditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtAddr.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optDebit.requestFocus();
        }
}//GEN-LAST:event_optCreditKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        window = 1;
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnCreateGroup.setMnemonic(KeyEvent.VK_O);
        btnUpdate.setMnemonic(KeyEvent.VK_U);
        Connection conn = null;

        try {


            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_name from tblgroup");
            comboUnder.removeAllItems();
            comboUnder.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            comboUnder.setEditable(true);
            tf = (JTextField) comboUnder.getEditor().getEditorComponent();


            while (rs.next()) {
                v.add(rs.getString("group_name"));
            }

            Collections.sort(
                    v,
                    new Comparator<String>() {
                        public int compare(String lhs, String rhs) {
                            return lhs.compareToIgnoreCase(rhs);
                        }
                    });


            //cmbGroupAlter_Under.addItem(GroupItems);
            int size1 = v.size();
            for (int i = 0; i < size1; i++) {
                comboUnder.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboUnder.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboUnder.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboUnder.showPopup();
                                }
                            }


                            String text1 = tf.getText().trim();
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
                                        comboUnder.setSelectedIndex(-1);
                                        tf.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (comboUnder.getSelectedItem().equals("")) {
                                    //JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
                                    // comboUnder.requestFocus();
                                } else {
                                    txtBalance.requestFocus();
                                }

                                //txtBalance.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                txtAlias.requestFocus();
                            }
                        }
                    });
                }
            });


            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet.");
                    String text = tf.getText().trim();
                    if (text.length() == 0) {
                        comboUnder.hidePopup();
                        setModel(new DefaultComboBoxModel(v), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboUnder.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            comboUnder.showPopup();
                        }
                    }

                    tf.selectAll();

                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            rs.close();

            ResultSet rs1 = st1.executeQuery("select * from tblledger where ledger_id=" + l_id + "");
            if (rs1.next()) {

                txtName.setText(rs1.getString("ledger_name"));
                txtAlias.setText(rs1.getString("ledger_alias"));

                ////////////////// Atul Code //////////////////////////////////////////////////  


                if (Double.parseDouble("" + rs1.getString("ledger_openingBalance")) == 0.00) {
                    txtBalance.setText("0");
                } else {
                    txtBalance.setText(rs1.getString("ledger_openingBalance"));
                }

                //     txtBalance.setText(rs1.getString("ledger_openingBalance"));   
                txtAddr.setText(rs1.getString("ledger_address"));
                txtContact.setText(rs1.getString("ledger_contactNo"));
                txtEmail.setText(rs1.getString("ledger_emailId"));
                txtIncomeTaxNo.setText(rs1.getString("ledger_inTaxo"));
                txtSaleTaxNo.setText(rs1.getString("ledger_saleTaxNo"));
                //comboUnder.setSelectedItem("group_name");
                // tf.setText(rs1.getString("group_name"));
                if (rs1.getInt("opening_type") == 1) {
                    optCredit.setSelected(false);
                    optDebit.setSelected(true);
                } else {
                    optCredit.setSelected(true);
                    optDebit.setSelected(false);
                }
                rs = st.executeQuery("select group_name from tblgroup where group_id=" + rs1.getLong("ledger_under") + "");
                if (rs.next()) {
                    if (window == 1) {
                        comboUnder.setSelectedItem(rs.getString("group_name"));
                        str = "" + rs.getString("group_name");
                    }

                }
                rs.close();

                rs1 = st.executeQuery("select ledger_limit from tblledgercreditlimit where ledger_id=" + l_id + "");
                if (rs1.next()) {
                    //////////////// Atul Code ///////////////////////////////////////////////           
                    if (Double.parseDouble("" + rs1.getString("ledger_limit")) == 0.00) {
                        txtCreditLimit.setText("0");
                    } else {
                        txtCreditLimit.setText("" + rs1.getString("ledger_limit"));
                    }
                    //   txtCreditLimit.setText(""+rs1.getString("ledger_limit"));     
                }
                rs1.close();

            }

        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LedgerAlterFromDetails.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        txtName.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

        combocontrol = 0;
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnCreateGroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCreateGroupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCreateGroupActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUnder.requestFocus();
        }
    }//GEN-LAST:event_btnCreateGroupKeyPressed

    private void txtCreditLimitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditLimitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnUpdateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtSaleTaxNo.requestFocus();
        }
    }//GEN-LAST:event_txtCreditLimitKeyPressed

    private void txtCreditLimitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditLimitKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag = 0;
        String f = txtCreditLimit.getText().trim();
        int i = 0;
        while (i < f.length()) {
            if (f.charAt(i) == '.') {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1 && c == '.') {
            evt.consume();
        }

    }//GEN-LAST:event_txtCreditLimitKeyTyped

    private void txtBalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBalanceFocusGained
        if (txtBalance.getText().equals("0")) {
            txtBalance.setText("");
        }
    }//GEN-LAST:event_txtBalanceFocusGained

    private void txtBalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBalanceFocusLost
        if (txtBalance.getText().equals("")) {
            txtBalance.setText("0");
        }
    }//GEN-LAST:event_txtBalanceFocusLost

    private void txtBalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBalanceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optDebit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboUnder.requestFocus();
        }
    }//GEN-LAST:event_txtBalanceKeyPressed

    private void txtBalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBalanceKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        int flag = 0;
        String f = txtBalance.getText().trim();
        int i = 0;
        while (i < f.length()) {
            if (f.charAt(i) == '.') {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1 && c == '.') {
            evt.consume();
        }

    }//GEN-LAST:event_txtBalanceKeyTyped

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

        Connection conn = null;
        conn = DatabaseConnection1.GetConnection();
        try {

//            if (combocontrol == 1) {
//                comboUnder.requestFocus();

//        else 
//        {
//         txtName.requestFocus();
//        }
                txtName.requestFocus();


                //  Connection conn = null;
                comboUnder.removeAllItems();
                comboUnder.setSelectedItem("");
                v1.clear();

                //try {
                //conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select group_name from tblgroup");

                comboUnder.addItem("");
                System.out.println("ResultSet" + rs.next());

//           while (rs.next()) {
//                comboLedgerCreate_Under.addItem(rs.getString("group_name"));
//                System.out.println("ResultSet Value"+rs.getString("group_name"));
//            }
//            comboLedgerCreate_Under.setSelectedItem("");
//        
                String Primary = "Primary";
                //     ResultSet rs = st.executeQuery("select group_name  from tblgroup where group_name <> '"+Primary+"'");
                System.out.println(rs.next());
                //   comboLedgerCreate_Under.removeAllItems();
                //comboLedgerCreate_Under.addItem("");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
                comboUnder.setEditable(true);
                tf = (JTextField) comboUnder.getEditor().getEditorComponent();


                while (rs.next()) {
                    System.out.println("ABCDEFGHIJ" + rs.getString("group_name"));
                    v1.add(rs.getString("group_name"));
                }

                Collections.sort(
                        v1,
                        new Comparator<String>() {
                            public int compare(String lhs, String rhs) {
                                return lhs.compareToIgnoreCase(rhs);
                            }
                        });


                //cmbGroupAlter_Under.addItem(GroupItems);
                comboUnder.removeAllItems();
                comboUnder.addItem("");
                int size1 = v1.size();
                for (int i = 0; i < size1; i++) {
//              
                    comboUnder.addItem(v1.get(i));
                }
           // }
//             tf.addKeyListener(new KeyAdapter()
//                                {
//                public void keyTyped(final KeyEvent e) 
//                {
//               EventQueue.invokeLater(new Runnable() 
//               {
//            public void run() 
//            {
//                String text = tf.getText().trim();
//                        if(text.length()==0) 
//                        {
//                          comboUnder.hidePopup();
//                          setModel(new DefaultComboBoxModel(v1), "");
//                        }
//                        else
//                        {
//                          DefaultComboBoxModel m = getSuggestedModel(v1, text);
//                          if(m.getSize()==0 || hide_flag)
//                          {
//                            comboUnder.hidePopup();
//                            hide_flag = false;
//                          }
//                          else
//                          {
//                            setModel(m, text);
//                            comboUnder.showPopup();
//                          }
//                        }
//                        
//                        
//                  String text1 = tf.getText().trim();
//                  int code = e.getKeyCode();
//                  if(code==KeyEvent.VK_ENTER)
//                  {
//                    System.out.println("Enter");
//                    if(!v1.contains(text)) 
//                    {
//                     v1.addElement(text);
//                     Collections.sort(v1);
//                     setModel(getSuggestedModel(v1, text), text);
//                     }
//                     hide_flag = true; 
//                   }
//                  else if(code==KeyEvent.VK_ESCAPE) 
//                  {
//                     hide_flag = true; 
//                  }
//                  else if(code==KeyEvent.VK_RIGHT) 
//                  {
//                     for(int i=0;i<v1.size();i++)
//                     {
//                       String str = v1.elementAt(i);
//                       if(str.startsWith(text))
//                       {
//                         comboUnder.setSelectedIndex(-1);
//                         tf.setText(str);
//                         return;
//                       }
//                      }
//                   }
//                        
//            }
//               
//        });
//            
//                }
//              
//      });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
//     tf.addKeyListener(new KeyAdapter()
//                    {
//                public void keyPressed(final KeyEvent event) 
//                {
//               EventQueue.invokeLater(new Runnable() 
//               {
//
//                        @Override
//                        public void run() {
//                           
//                            
//                            if(event.getKeyCode()==KeyEvent.VK_ENTER)
//                            {
//                             /*if(comboLedgerCreate_Under.getSelectedItem().equals(""))
//                               {
//                                JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
//                                comboLedgerCreate_Under.requestFocus();
//                               }
//                               else*/
//                               {
//                               txtBalance.requestFocus();
//                               }
//                                
//                                //txtLedgerCreate_Balance.requestFocus();
//                            }
//                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
//                            {
//                             txtAlias.requestFocus();
//                            }
//                        }
//                  
//                   
//               });
//                       }
//                
//               });  

            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");]
                    String text = tf.getText().trim();
                    if (text.length() == 0) {
                        comboUnder.hidePopup();
                        setModel(new DefaultComboBoxModel(v1), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v1, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboUnder.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            comboUnder.showPopup();
                        }
                    }

                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });

            if (window == 0) {
                comboUnder.setSelectedItem(str);
                // comboUnder.requestFocus();
            }
            window = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        
        if(comboFocusControl == 1){
            tf.requestFocus();
        }else if(comboFocusControl == 0){
            txtName.requestFocus();
        }
        comboFocusControl = 0;

    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        combocontrol = 1;
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void txtCreditLimitFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditLimitFocusGained
        // TODO add your handling code here:
        if (txtCreditLimit.getText().equals("0")) {
            txtCreditLimit.setText("");
        }

    }//GEN-LAST:event_txtCreditLimitFocusGained

    private void txtCreditLimitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCreditLimitFocusLost
        // TODO add your handling code here:
        if (txtCreditLimit.getText().equals("")) {
            txtCreditLimit.setText("0");
        }
    }//GEN-LAST:event_txtCreditLimitFocusLost

    private void txtIncomeTaxNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIncomeTaxNoFocusGained
        // TODO add your handling code here:
        if (txtIncomeTaxNo.getText().equals("0")) {
            txtIncomeTaxNo.setText("");
        }
    }//GEN-LAST:event_txtIncomeTaxNoFocusGained

    private void txtIncomeTaxNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIncomeTaxNoFocusLost
        // TODO add your handling code here:
        if (txtIncomeTaxNo.getText().equals("")) {
            txtIncomeTaxNo.setText("0");
        }
    }//GEN-LAST:event_txtIncomeTaxNoFocusLost

    private void txtSaleTaxNoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSaleTaxNoFocusGained
        // TODO add your handling code here:
        if (txtSaleTaxNo.getText().equals("0")) {
            txtSaleTaxNo.setText("");
        }
    }//GEN-LAST:event_txtSaleTaxNoFocusGained

    private void txtSaleTaxNoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSaleTaxNoFocusLost
        // TODO add your handling code here:
        if (txtSaleTaxNo.getText().equals("")) {
            txtSaleTaxNo.setText("0");
        }
    }//GEN-LAST:event_txtSaleTaxNoFocusLost

    private void txtNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusGained
        // TODO add your handling code here:
        txtName.selectAll();
    }//GEN-LAST:event_txtNameFocusGained

    private void txtAliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAliasFocusGained
        // TODO add your handling code here:
        txtAlias.selectAll();
    }//GEN-LAST:event_txtAliasFocusGained

    private void txtAddrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAddrFocusGained
        // TODO add your handling code here:
        txtAddr.selectAll();
    }//GEN-LAST:event_txtAddrFocusGained

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        // TODO add your handling code here:
        txtEmail.selectAll();
    }//GEN-LAST:event_txtEmailFocusGained

    private void comboUnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboUnderFocusLost
        // TODO add your handling code here:
        comboUnder.hidePopup();
    }//GEN-LAST:event_comboUnderFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateGroup;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox comboUnder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton optCredit;
    private javax.swing.JRadioButton optDebit;
    private javax.swing.JTextField txtAddr;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JFormattedTextField txtBalance;
    private javax.swing.JTextField txtContact;
    private javax.swing.JFormattedTextField txtCreditLimit;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIncomeTaxNo;
    public javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSaleTaxNo;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboUnder.setModel(mdl);
        comboUnder.setSelectedIndex(-1);
        comboUnder.setPopupVisible(true);
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

    private boolean checkElementPresence(Vector combo, String str) {
        for (int i = 0; i < combo.size(); i++) {
            if (tf.getText().trim().equals(comboUnder.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}
