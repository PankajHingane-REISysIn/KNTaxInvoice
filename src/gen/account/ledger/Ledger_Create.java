package gen.account.ledger;

import gen.account.group.Group_Create;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
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

public class Ledger_Create extends javax.swing.JInternalFrame {

    private CharSequence Number;
    //ArrayList<String> GroupItems=new ArrayList<String>();
    private final Vector<String> v = new Vector<String>();
    private final Vector<String> v1 = new Vector<String>();
    JTextField tf;
 
    static int combocontrol=0;
    static int comboFocusControl=0;
    static int addComboItem=0;
    
    public Ledger_Create(String s) {
        setClosable(true);
        initComponents();
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLedgerCreate_Name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLedgerCreate_Alias = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboLedgerCreate_Under = new javax.swing.JComboBox();
        btnLedgerCreate_CreateGroup = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtLedgerCreate_Address = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtLedgerCreate_ContactNo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtLedgerCreate_EmailId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtLedgerCreate_IncomeTax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLedgerCreate_SaleTax = new javax.swing.JTextField();
        btnLedgerCreate_Create = new javax.swing.JButton();
        btnLedgerCreate_Back = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtCreditLimit = new javax.swing.JFormattedTextField();
        txtLedgerCreate_Balance = new javax.swing.JFormattedTextField();
        optCredit = new javax.swing.JRadioButton();
        optDebit = new javax.swing.JRadioButton();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Symbol.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 582));
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
        jLabel1.setText("Name"); // NOI18N

        txtLedgerCreate_Name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerCreate_NameFocusGained(evt);
            }
        });
        txtLedgerCreate_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_NameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_NameKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias"); // NOI18N

        txtLedgerCreate_Alias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_Alias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerCreate_AliasFocusGained(evt);
            }
        });
        txtLedgerCreate_Alias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_AliasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_AliasKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under"); // NOI18N

        comboLedgerCreate_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboLedgerCreate_Under.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboLedgerCreate_UnderMouseClicked(evt);
            }
        });
        comboLedgerCreate_Under.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                comboLedgerCreate_UnderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboLedgerCreate_UnderFocusLost(evt);
            }
        });
        comboLedgerCreate_Under.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboLedgerCreate_UnderKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboLedgerCreate_UnderKeyReleased(evt);
            }
        });

        btnLedgerCreate_CreateGroup.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLedgerCreate_CreateGroup.setText("Create Group"); // NOI18N
        btnLedgerCreate_CreateGroup.setFocusPainted(false);
        btnLedgerCreate_CreateGroup.setFocusable(false);
        btnLedgerCreate_CreateGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLedgerCreate_CreateGroupActionPerformed(evt);
            }
        });
        btnLedgerCreate_CreateGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLedgerCreate_CreateGroupKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Opening Balance"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Address"); // NOI18N

        txtLedgerCreate_Address.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_Address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerCreate_AddressFocusGained(evt);
            }
        });
        txtLedgerCreate_Address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_AddressKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Contact No"); // NOI18N

        txtLedgerCreate_ContactNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_ContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_ContactNoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_ContactNoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Email Id"); // NOI18N

        txtLedgerCreate_EmailId.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_EmailId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerCreate_EmailIdFocusGained(evt);
            }
        });
        txtLedgerCreate_EmailId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_EmailIdKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Income Tax Number"); // NOI18N

        txtLedgerCreate_IncomeTax.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_IncomeTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_IncomeTaxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_IncomeTaxKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Sale Tax Number"); // NOI18N

        txtLedgerCreate_SaleTax.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_SaleTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_SaleTaxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_SaleTaxKeyTyped(evt);
            }
        });

        btnLedgerCreate_Create.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLedgerCreate_Create.setText("Create"); // NOI18N
        btnLedgerCreate_Create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLedgerCreate_CreateMouseClicked(evt);
            }
        });
        btnLedgerCreate_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLedgerCreate_CreateActionPerformed(evt);
            }
        });
        btnLedgerCreate_Create.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLedgerCreate_CreateKeyPressed(evt);
            }
        });

        btnLedgerCreate_Back.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLedgerCreate_Back.setText("Back"); // NOI18N
        btnLedgerCreate_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLedgerCreate_BackActionPerformed(evt);
            }
        });
        btnLedgerCreate_Back.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLedgerCreate_BackKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Credit Limit");

        txtCreditLimit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCreditLimit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCreditLimitKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCreditLimitKeyTyped(evt);
            }
        });

        txtLedgerCreate_Balance.setText("0");
        txtLedgerCreate_Balance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerCreate_Balance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerCreate_BalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLedgerCreate_BalanceFocusLost(evt);
            }
        });
        txtLedgerCreate_Balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_BalanceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerCreate_BalanceKeyTyped(evt);
            }
        });

        optCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optCredit.setText("Credit"); // NOI18N
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

        optDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        optDebit.setText("Debit"); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLedgerCreate_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLedgerCreate_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboLedgerCreate_Under, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLedgerCreate_CreateGroup))))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLedgerCreate_IncomeTax, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(txtLedgerCreate_SaleTax)))
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(txtCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(43, 43, 43)
                                .addComponent(txtLedgerCreate_Balance, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel5))
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtLedgerCreate_ContactNo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(txtLedgerCreate_EmailId)
                                    .addComponent(txtLedgerCreate_Address))))
                        .addGap(21, 21, 21)
                        .addComponent(optDebit)
                        .addGap(16, 16, 16)
                        .addComponent(optCredit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnLedgerCreate_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnLedgerCreate_Create)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLedgerCreate_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtLedgerCreate_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLedgerCreate_CreateGroup)
                        .addComponent(comboLedgerCreate_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLedgerCreate_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(optCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLedgerCreate_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLedgerCreate_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLedgerCreate_EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLedgerCreate_IncomeTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLedgerCreate_SaleTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLedgerCreate_Back)
                    .addComponent(btnLedgerCreate_Create))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLedgerCreate_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_NameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_Alias.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnLedgerCreate_BackActionPerformed(null);
        }
}//GEN-LAST:event_txtLedgerCreate_NameKeyPressed

    private void txtLedgerCreate_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_AliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboLedgerCreate_Under.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_Name.requestFocus();
        }
}//GEN-LAST:event_txtLedgerCreate_AliasKeyPressed

    private void comboLedgerCreate_UnderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboLedgerCreate_UnderFocusGained
        comboLedgerCreate_Under.showPopup();
        
}//GEN-LAST:event_comboLedgerCreate_UnderFocusGained

    private void comboLedgerCreate_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboLedgerCreate_UnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_Balance.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_Alias.requestFocus();
        }
}//GEN-LAST:event_comboLedgerCreate_UnderKeyPressed

    private void btnLedgerCreate_CreateGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLedgerCreate_CreateGroupActionPerformed
        Group_Create group = new Group_Create("Create New Group");
        try {
            group.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
        group.setVisible(true);
        group.pack();
        group.setVisible(true);
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
            e.printStackTrace();
        }

        BasicInternalFrameUI ui = (BasicInternalFrameUI) group.getUI();

        Component north = ui.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
//            comboLedgerCreate_Under.requestFocus();
//            comboLedgerCreate_Under.hidePopup();
            
        }
       comboFocusControl = 1;
    }//GEN-LAST:event_btnLedgerCreate_CreateGroupActionPerformed

    private void txtLedgerCreate_AddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_AddressKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_ContactNo.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optCredit.requestFocus();
        }
}//GEN-LAST:event_txtLedgerCreate_AddressKeyPressed

    private void txtLedgerCreate_ContactNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_ContactNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_EmailId.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_Address.requestFocus();
        }
}//GEN-LAST:event_txtLedgerCreate_ContactNoKeyPressed

    private void txtLedgerCreate_ContactNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_ContactNoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
}//GEN-LAST:event_txtLedgerCreate_ContactNoKeyTyped

    private void txtLedgerCreate_EmailIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_EmailIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_IncomeTax.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_ContactNo.requestFocus();
        }
}//GEN-LAST:event_txtLedgerCreate_EmailIdKeyPressed

    private void txtLedgerCreate_IncomeTaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_IncomeTaxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_SaleTax.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_EmailId.requestFocus();
        }
}//GEN-LAST:event_txtLedgerCreate_IncomeTaxKeyPressed

    private void txtLedgerCreate_IncomeTaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_IncomeTaxKeyTyped
        
}//GEN-LAST:event_txtLedgerCreate_IncomeTaxKeyTyped

    private void btnLedgerCreate_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLedgerCreate_CreateActionPerformed
       String no = txtLedgerCreate_ContactNo.getText().trim();
      // int bal=Integer.parseInt(txtLedgerCreate_Balance.getText());
        if (txtLedgerCreate_Name.getText().trim().equals("")) 
        {
            JOptionPane.showMessageDialog(null, "Enter Data in Name Fields");
            txtLedgerCreate_Name.requestFocus();
        } 
        else if (comboLedgerCreate_Under.getSelectedItem()==null || comboLedgerCreate_Under.getSelectedItem()=="")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            comboLedgerCreate_Under.requestFocus();
        }
         else if(!checkElementPresence(v1, tf.getText().trim() )){
            JOptionPane.showMessageDialog(this, "Selected Value For Under Field is not valid");
        //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            comboLedgerCreate_Under.requestFocus();
        }
        else 
        {  
            
             
            //Email ID validation
            String email = txtLedgerCreate_EmailId.getText().trim();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()&&txtLedgerCreate_EmailId.getText().trim().equals("")==false) 
            {
                String Lname = txtLedgerCreate_Name.getText().trim();
                String Lalias = txtLedgerCreate_Alias.getText().trim();
                String Lunder = (String) comboLedgerCreate_Under.getSelectedItem();
                
                double LopenBalance =0;
                if(txtLedgerCreate_Balance.getText().trim().equals("")==false)
                LopenBalance=Double.parseDouble(txtLedgerCreate_Balance.getText().trim());
                
                String LcontactNo = txtLedgerCreate_ContactNo.getText().trim();
                String LemailId = txtLedgerCreate_EmailId.getText().trim();
                String Laddress = txtLedgerCreate_Address.getText().trim();
                                
                SimpleDateFormat f=new SimpleDateFormat("yyyy/MM/dd");
                Calendar currentDate = Calendar.getInstance();
                String dateNow=f.format(currentDate.getTime());
                Connection conn=null;
                try 
                {
                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    ResultSet rs1;
                    
                    rs1 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtLedgerCreate_Name.getText().trim() + "'");
                    if (rs1.next()) 
                    {
                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtLedgerCreate_Name.selectAll();
                        txtLedgerCreate_Name.requestFocus();
                    }
                    else
                    {
                    int id = 0;
                    ResultSet rs = st.executeQuery("select group_id from tblgroup where group_name='" + Lunder + "'");
                    if (rs.next()) 
                    {
                        id = rs.getInt(1);
                        String sql="";
                        if(optDebit.isSelected())
                        {
                            // sql = "insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,dat) values('" + Lname.trim() + "'," + id + ",'" + Laddress.trim() + "','" + LcontactNo + "','" + LemailId + "'," + LopenBalance + ",'" + Lalias.trim() + "','" + txtLedgerCreate_IncomeTax.getText().trim() + "','" + txtLedgerCreate_SaleTax.getText().trim() + "',1,'"+ dateNow +"');";
                             PreparedStatement ps;
                             sql="insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,dat) values(?,?,?,?,?,?,?,?,?,?,?)";
                             ps=conn.prepareStatement(sql);
                             
                             ps.setString(1, Lname.trim());
                             ps.setInt(2, id);
                             ps.setString(3, Laddress.trim());
                             ps.setString(4, LcontactNo.trim());
                             ps.setString(5, LemailId.trim());
                             ps.setDouble(6, LopenBalance);
                             ps.setString(7, Lalias.trim());
                             ps.setString(8, txtLedgerCreate_IncomeTax.getText().trim());
                             ps.setString(9, txtLedgerCreate_SaleTax.getText().trim());
                             ps.setInt(10, 1);
                             ps.setString(11, dateNow);
                             
                             ps.executeUpdate();
                        }
                        else
                        {
                            //sql = "insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,dat) values('" + Lname.trim() + "'," + id + ",'" + Laddress.trim() + "','" + LcontactNo + "','" + LemailId + "'," + LopenBalance + ",'" + Lalias.trim() + "','" + txtLedgerCreate_IncomeTax.getText().trim() + "','" + txtLedgerCreate_SaleTax.getText().trim() + "',2,'"+ dateNow +"');";
                        
                            //st.executeUpdate(sql);
                            
                               PreparedStatement ps;
                             sql="insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,dat) values(?,?,?,?,?,?,?,?,?,?,?)";
                             ps=conn.prepareStatement(sql);
                             
                             ps.setString(1, Lname.trim());
                             ps.setInt(2, id);
                             ps.setString(3, Laddress.trim());
                             ps.setString(4, LcontactNo.trim());
                             ps.setString(5, LemailId.trim());
                             ps.setDouble(6, LopenBalance);
                             ps.setString(7, Lalias.trim());
                             ps.setString(8, txtLedgerCreate_IncomeTax.getText().trim());
                             ps.setString(9, txtLedgerCreate_SaleTax.getText().trim());
                             ps.setInt(10, 2);
                             ps.setString(11, dateNow);
                             
                             ps.executeUpdate();
                        }
                        
                        
                        String q="select last_insert_id() as id";
                        rs1=st1.executeQuery(q);
                        int temp_id=0;
                        if(rs1.next())
                            temp_id=rs1.getInt("id");
                        else
                            temp_id=1;
                        
//                        q="insert into tblledgercreditlimit(ledger_id,ledger_limit) values("+ temp_id +","+ Double.parseDouble(txtCreditLimit.getText()) +")";
//                        st1.executeUpdate(q);
                          if(txtCreditLimit.getText().trim().equals("")==false)
                            q="insert into tblledgercreditlimit(ledger_id,ledger_limit) values("+ temp_id +","+ Double.parseDouble(txtCreditLimit.getText().trim()) +")";
                          else
                            q="insert into tblledgercreditlimit(ledger_id,ledger_limit) values("+ temp_id +",0)";
                          st1.executeUpdate(q);
                        
                        if(optCredit.isSelected())
                            q="insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + temp_id + ","+ Double.parseDouble(txtLedgerCreate_Balance.getText().trim()) +",2)";  //2 for credit and 1 for debit
                        else
                            q="insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + temp_id + ","+ Double.parseDouble(txtLedgerCreate_Balance.getText().trim()) +",1)";  //2 for credit and 1 for debit
                        st1.executeUpdate(q);
                        conn.commit();
                        
                        Constants.LEDGER_TIME_STAMP = Constants.LEDGER_TIME_STAMP + 1;
                        
                        System.out.println("In Ledger:--->>>Constants.ledgerTimeStamp" + Constants.LEDGER_TIME_STAMP);
                        
                        JOptionPane.showMessageDialog(null, "Successfully Created");
                        txtLedgerCreate_Name.setText("");
                        txtLedgerCreate_Alias.setText("");
                        txtLedgerCreate_Balance.setText("0");
                        txtLedgerCreate_ContactNo.setText("");
                        txtLedgerCreate_EmailId.setText("");
                        txtLedgerCreate_Address.setText("");
                        txtLedgerCreate_IncomeTax.setText("");
                        txtLedgerCreate_SaleTax.setText("");
                        txtCreditLimit.setText("");
                        optDebit.setSelected(false);
                        optCredit.setSelected(false);
                        comboLedgerCreate_Under.setSelectedIndex(0);
                        comboLedgerCreate_Under.setSelectedItem("");
                        txtLedgerCreate_Name.requestFocus();
                    }
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
                finally
            {
                try
                {
                    conn.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
                

                
            } 
            else if(txtLedgerCreate_EmailId.getText().trim().equals(""))
            {
                String Lname = txtLedgerCreate_Name.getText().trim();
                String Lalias = txtLedgerCreate_Alias.getText().trim();
                String Lunder = (String) comboLedgerCreate_Under.getSelectedItem();
                double LopenBalance =0;
                if(txtLedgerCreate_Balance.getText().trim().equals("")==false)
                    LopenBalance=Double.parseDouble(txtLedgerCreate_Balance.getText().trim());
                
                String LcontactNo = txtLedgerCreate_ContactNo.getText().trim();
                String LemailId = txtLedgerCreate_EmailId.getText().trim();
                String Laddress = txtLedgerCreate_Address.getText().trim();
                
                String LincoTaxNo ="";
                if(txtLedgerCreate_IncomeTax.getText().trim().equals("")==false)
                LincoTaxNo=""+txtLedgerCreate_IncomeTax.getText().trim();

                String LsaleTaxNo ="";
                if(txtLedgerCreate_SaleTax.getText().trim().equals("")==false)
                LsaleTaxNo=""+txtLedgerCreate_SaleTax.getText().trim();
                
                SimpleDateFormat f=new SimpleDateFormat("yyyy/MM/dd");
                Calendar currentDate = Calendar.getInstance();
                String dateNow=f.format(currentDate.getTime());
                Connection conn=null;
                try 
                {
                    conn =DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    ResultSet rs1;
                    rs1 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtLedgerCreate_Name.getText().trim() + "'");
                    if (rs1.next()) 
                    {
                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtLedgerCreate_Name.selectAll();
                        txtLedgerCreate_Name.requestFocus();
                    }
                    else
                    {
                    int id = 0;
                    ResultSet rs = st.executeQuery("select group_id from tblgroup where group_name='" + Lunder + "'");
                    if (rs.next()) 
                    {
                        id = rs.getInt(1);
                        String sql="";
                        if(optCredit.isSelected())
                            sql = "insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,dat) values('" + Lname + "'," + id + ",'" + Laddress + "','" + LcontactNo + "','" + LemailId + "'," + LopenBalance + ",'" + Lalias + "','" + LincoTaxNo + "','" + LsaleTaxNo + "',2,'"+ dateNow +"');";
                        else
                            sql = "insert into tblledger(ledger_name,ledger_under,ledger_address,ledger_contactNo,ledger_emailId,ledger_openingBalance,ledger_alias,ledger_inTaxo,ledger_saleTaxNo,opening_type,dat) values('" + Lname + "'," + id + ",'" + Laddress + "','" + LcontactNo + "','" + LemailId + "'," + LopenBalance + ",'" + Lalias + "','" + LincoTaxNo + "','" + LsaleTaxNo + "',1,'"+ dateNow +"');";
                        st.executeUpdate(sql);
                        
                        String q="select last_insert_id() as id";
                        rs1=st1.executeQuery(q);
                        int temp_id=0;
                        if(rs1.next())
                            temp_id=rs1.getInt("id");
                        else
                            temp_id=1;
                        if(txtCreditLimit.getText().trim().equals("")==false)
                            q="insert into tblledgercreditlimit(ledger_id,ledger_limit) values("+ temp_id +","+ Double.parseDouble(txtCreditLimit.getText().trim()) +")";
                        else
                            q="insert into tblledgercreditlimit(ledger_id,ledger_limit) values("+ temp_id +",0)";
                        st1.executeUpdate(q);
                        
                        if(optCredit.isSelected())
                            q="insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + temp_id + ","+ Double.parseDouble(txtLedgerCreate_Balance.getText().trim()) +",2)";  //2 for credit and 1 for debit
                        else
                            q="insert into tblLedgerCurrentBalance(ledger_id,ledger_currentBalance,ledger_DebitOrCredit) values(" + temp_id + ","+ Double.parseDouble(txtLedgerCreate_Balance.getText().trim()) +",1)";  //2 for credit and 1 for debit
                        st1.executeUpdate(q);
                        conn.commit();
                        Constants.LEDGER_TIME_STAMP = Constants.LEDGER_TIME_STAMP + 1;
                        
                    }
                    JOptionPane.showMessageDialog(null, "Ledger Successfully Created");

                        txtLedgerCreate_Name.setText("");
                        txtLedgerCreate_Alias.setText("");
                        txtLedgerCreate_Balance.setText("0");
                        txtLedgerCreate_ContactNo.setText("");
                        txtLedgerCreate_EmailId.setText("");
                        txtLedgerCreate_Address.setText("");
                        txtLedgerCreate_IncomeTax.setText("");
                        txtLedgerCreate_SaleTax.setText("");
                        txtCreditLimit.setText("");
                        optDebit.setSelected(false);
                        optCredit.setSelected(false);
                        comboLedgerCreate_Under.setSelectedIndex(0);
                        txtLedgerCreate_Name.requestFocus();
                        tf.setText("");
                    }
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
                finally
            {
                try
                {
                    conn.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
                
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Email ID");
                txtLedgerCreate_EmailId.requestFocus();
                txtLedgerCreate_EmailId.selectAll();
            }
        }
       
}//GEN-LAST:event_btnLedgerCreate_CreateActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
//        v.clear();
        txtLedgerCreate_Name.requestFocus();
        optDebit.setSelected(true);
        btnLedgerCreate_Create.setMnemonic(KeyEvent.VK_C);
        btnLedgerCreate_Back.setMnemonic(KeyEvent.VK_B);
        btnLedgerCreate_CreateGroup.setMnemonic(KeyEvent.VK_O);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            String Primary="Primary";
            ResultSet rs = st.executeQuery("select group_name  from tblgroup where group_name <> '"+Primary+"'");
          // System.out.println(rs.next());
            comboLedgerCreate_Under.removeAllItems();
            comboLedgerCreate_Under.addItem("");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            comboLedgerCreate_Under.setEditable(true);
            tf=(JTextField) comboLedgerCreate_Under.getEditor().getEditorComponent();
            
            
            while (rs.next()) 
            {
              // System.out.println("ABCDEFGHIJ"+rs.getString("group_name")); 
                v1.add(rs.getString("group_name"));
            }
            
            Collections.sort(
                                    v1, 
                                    new Comparator<String>() 
                                     {
                                       public int compare(String lhs, String rhs) 
                                       {
                                         return lhs.compareToIgnoreCase(rhs);
                                        }
                                      }
                                   ); 
                
                
                //cmbGroupAlter_Under.addItem(GroupItems);
              int size1=v1.size(); 
              for(int i=0;i<size1;i++)
              {
               System.out.println("Value Of"+v1.get(i)+"Value Of i"+i); 
               
               if(v1.get(i).trim().equals("Primary"))
               {
                  System.out.println("In Primary"+v1.get(i));  
               }
               else
               {
                    System.out.println("Outside"+v1.get(i));
                    comboLedgerCreate_Under.addItem(v1.get(i));
               }
             
              }
                            
             tf.addKeyListener(new KeyAdapter()
                                {
                public void keyTyped(final KeyEvent e) 
                {
               EventQueue.invokeLater(new Runnable() 
               {
            public void run() 
            {
                String text = tf.getText();
                        if(text.length()==0) 
                        {
                          comboLedgerCreate_Under.hidePopup();
                          setModel(new DefaultComboBoxModel(v1), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v1, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            comboLedgerCreate_Under.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            comboLedgerCreate_Under.showPopup();
                          }
                        }
                        
                        
                  String text1 = tf.getText().trim();
                  int code = e.getKeyCode();
                  if(code==KeyEvent.VK_ENTER)
                  {
                    System.out.println("Enter");
                    if(!v1.contains(text)) 
                    {
                     v1.addElement(text);
                     Collections.sort(v1);
                     setModel(getSuggestedModel(v1, text), text);
                     }
                     hide_flag = true; 
                   }
                  else if(code==KeyEvent.VK_ESCAPE) 
                  {
                     hide_flag = true; 
                  }
                  else if(code==KeyEvent.VK_RIGHT) 
                  {
                     for(int i=0;i<v1.size();i++)
                     {
                       String str = v1.elementAt(i);
                       if(str.startsWith(text))
                       {
                         comboLedgerCreate_Under.setSelectedIndex(-1);
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
     tf.addKeyListener(new KeyAdapter()
                    {
                public void keyPressed(final KeyEvent event) 
                {
               EventQueue.invokeLater(new Runnable() 
               {

                        @Override
                        public void run() {
                           
                            
                            if(event.getKeyCode()==KeyEvent.VK_ENTER)
                            {
                             /*if(comboLedgerCreate_Under.getSelectedItem().equals(""))
                               {
                                JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
                                comboLedgerCreate_Under.requestFocus();
                               }
                               else*/
                               {
                               txtLedgerCreate_Balance.requestFocus();
                               }
                                
                                //txtLedgerCreate_Balance.requestFocus();
                            }
                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
                            {
                             txtLedgerCreate_Alias.requestFocus();
                            }
                        }
                  
                   
               });
                       }
                
               });  
     
     tf.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                   // throw new UnsupportedOperationException("Not supported yet.");
                      String text = tf.getText().trim();
                        if(text.length()==0) 
                        {
                          comboLedgerCreate_Under.hidePopup();
                          setModel(new DefaultComboBoxModel(v1), "");
                        }
                        else
                        {
                          DefaultComboBoxModel m = getSuggestedModel(v1, text);
                          if(m.getSize()==0 || hide_flag)
                          {
                            comboLedgerCreate_Under.hidePopup();
                            hide_flag = false;
                          }
                          else
                          {
                            setModel(m, text);
                            comboLedgerCreate_Under.showPopup();
                          }
                        }
                        
                        tf.selectAll();
                        
                }

                @Override
                public void focusLost(FocusEvent e) {
                  //  throw new UnsupportedOperationException("Not supported yet.");
                }
            });
     
          // System.out.println("Primary Removed"+comboLedgerCreate_Under.removeItem("Primary"));
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            txtLedgerCreate_Name.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        combocontrol=0;
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
  
        //addComboItem=0;
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnLedgerCreate_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLedgerCreate_BackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLedgerCreate_BackActionPerformed

    private void optDebitStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optDebitStateChanged
        if (optDebit.isSelected()) {
            optCredit.setSelected(false);
        }
        if (optCredit.isSelected() == false) {
            optDebit.setSelected(true);
        }
    }//GEN-LAST:event_optDebitStateChanged

    private void optCreditStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optCreditStateChanged
        if (optCredit.isSelected()) {
            optDebit.setSelected(false);
        }
        if (optDebit.isSelected() == false) {
            optCredit.setSelected(true);
        }
    }//GEN-LAST:event_optCreditStateChanged

    private void optDebitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optDebitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optCredit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_Balance.requestFocus();
        }
    }//GEN-LAST:event_optDebitKeyPressed

    private void optCreditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optCreditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerCreate_Address.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optDebit.requestFocus();
        }
    }//GEN-LAST:event_optCreditKeyPressed

    private void txtLedgerCreate_NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_NameKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c) && !Character.isWhitespace(c)) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtLedgerCreate_NameKeyTyped

    private void txtLedgerCreate_AliasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_AliasKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c)) {
//            evt.consume();
//        }
    }//GEN-LAST:event_txtLedgerCreate_AliasKeyTyped

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
 
        if(addComboItem==1)
        {
         comboLedgerCreate_Under.removeAllItems();
         comboLedgerCreate_Under.addItem("");
         v.clear();
                Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            String Primary="Primary";
            ResultSet rs = st.executeQuery("select group_name  from tblgroup where group_name <> '"+Primary+"'");
            
            while(rs.next())
            {
             v.add(rs.getString("group_name"));
             
            }
                        Collections.sort(
                                    v, 
                                    new Comparator<String>() 
                                     {
                                       public int compare(String lhs, String rhs) 
                                       {
                                         return lhs.compareToIgnoreCase(rhs);
                                        }
                                      }
                                   ); 
        }catch(Exception e)
        {
        
        }
//        comboLedgerCreate_Under.removeAllItems();
//        comboLedgerCreate_Under.addItem("");
         int size1=v.size();
         for(int i=0;i<size1;i++)
         {
          comboLedgerCreate_Under.addItem(v.get(i));
         }
        }
        
        if(comboFocusControl == 1){
            tf.requestFocus();
        }else if(comboFocusControl == 0){
            txtLedgerCreate_Name.requestFocus();
        }
        comboFocusControl = 0;
       
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        //     
//      //  comboLedgerCreate_Under.removeAllItems();
//         v1.clear();
//         
//         //v.clear();
//       // tf.requestFocus();
//         if(combocontrol==1)
//         {
//          comboLedgerCreate_Under.requestFocus();
//         }
//        comboLedgerCreate_Under.removeAllItems();
//        Connection conn = null;
//        try {
//            conn = DatabaseConnection1.GetConnection();
//            Statement st = conn.createStatement();
//            String Primary="Primary";
//            ResultSet rs = st.executeQuery("select group_name  from tblgroup where group_name <> '"+Primary+"'");
//            System.out.println(rs.next());
//           
//            comboLedgerCreate_Under.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
//            comboLedgerCreate_Under.setEditable(true);
//            tf=(JTextField) comboLedgerCreate_Under.getEditor().getEditorComponent();
//            
//            // comboLedgerCreate_Under.removeAllItems();
//            while (rs.next()) 
//            {
//              // System.out.println("ABCDEFGHIJ"+rs.getString("group_name")); 
//                v1.add(rs.getString("group_name"));
//           }
//         
//      //   }
//            Collections.sort(
//                                    v1, 
//                                    new Comparator<String>() 
//                                     {
//                                       public int compare(String lhs, String rhs) 
//                                       {
//                                         return lhs.compareToIgnoreCase(rhs);
//                                        }
//                                      }
//                                   ); 
//               int siz=v1.size(); 
//               for(int i=0;i<siz;i++)
//               {
//               comboLedgerCreate_Under.addItem(v1.get(i));
//               }
//                //cmbGroupAlter_Under.addItem(GroupItems);
////            comboLedgerCreate_Under.removeAllItems(); 
////              for(int i=0;i<v1.size();i++)
////              {
////               comboLedgerCreate_Under.addItem(v1.get(i));
////              }
////              {
//////               System.out.println("Value Of"+v1.get(i)+"Value Of i"+i); 
//////               
//////               if(v1.get(i).trim().equals("Primary"))
//////               {
//////                  System.out.println("In Primary"+v1.get(i));  
//////               }
//////               else
//////               {
////                   // System.out.println("Outside"+v1.get(i));
////                    comboLedgerCreate_Under.addItem(v1.get(i));
//////               }
////             
////              }
//             //               
////             tf.addKeyListener(new KeyAdapter()
////                                {
////                                    
////                public void keyTyped(final KeyEvent e) 
////                {
////               EventQueue.invokeLater(new Runnable() 
////               {
////            public void run() 
////            {
////                String text = tf.getText();
////                        if(text.length()==0) 
////                        {
////                          comboLedgerCreate_Under.hidePopup();
////                          setModel(new DefaultComboBoxModel(v1), "");
////                        }
////                        else
////                        {
////                          DefaultComboBoxModel m = getSuggestedModel(v1, text);
////                          if(m.getSize()==0 || hide_flag)
////                          {
////                            comboLedgerCreate_Under.hidePopup();
////                            hide_flag = false;
////                          }
////                          else
////                          {
////                            setModel(m, text);
////                            comboLedgerCreate_Under.showPopup();
////                          }
////                        }
////                        
////                        
////                  String text1 = tf.getText();
////                  int code = e.getKeyCode();
////                  if(code==KeyEvent.VK_ENTER)
////                  {
////                    System.out.println("Enter");
////                    if(!v1.contains(text)) 
////                    {
////                    // v1.clear();   
////                     v1.addElement(text);
////                     Collections.sort(v1);
////                     setModel(getSuggestedModel(v1, text), text);
////                     }
////                     hide_flag = true; 
////                   }
////                  else if(code==KeyEvent.VK_ESCAPE) 
////                  {
////                     hide_flag = true; 
////                  }
////                  else if(code==KeyEvent.VK_RIGHT) 
////                  {
////                     for(int i=0;i<v1.size();i++)
////                     {
////                       String str = v1.elementAt(i);
////                       if(str.startsWith(text))
////                       {
////                         comboLedgerCreate_Under.setSelectedIndex(-1);
////                         tf.setText(str);
////                         return;
////                       }
////                      }
////                   }
////                        
////            }
////               
////        });
////            
////                }
////              
////      });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
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
//                               txtLedgerCreate_Balance.requestFocus();
//                               }
//                                
//                                //txtLedgerCreate_Balance.requestFocus();
//                            }
//                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
//                            {
//                             txtLedgerCreate_Alias.requestFocus();
//                            }
//                        }
//                  
//                   
//               });
//                       }
//                
//               });  
//     
//         ///comboLedgerCreate_Under.removeItem("Primary");
//          // System.out.println("Primary Removed"+comboLedgerCreate_Under.removeItem("Primary"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
//            txtLedgerCreate_Name.requestFocus();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//      
    }//GEN-LAST:event_formInternalFrameActivated

    private void btnLedgerCreate_CreateGroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLedgerCreate_CreateGroupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLedgerCreate_Create.requestFocus();
            btnLedgerCreate_CreateGroupActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboLedgerCreate_Under.requestFocus();
        }
    }//GEN-LAST:event_btnLedgerCreate_CreateGroupKeyPressed

    private void txtCreditLimitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditLimitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLedgerCreate_Create.requestFocus();
           // btnLedgerCreate_CreateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //txtCreditLimit.setText();
            txtLedgerCreate_SaleTax.requestFocus();
        }
    }//GEN-LAST:event_txtCreditLimitKeyPressed

    private void txtLedgerCreate_SaleTaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_SaleTaxKeyTyped
       
}//GEN-LAST:event_txtLedgerCreate_SaleTaxKeyTyped

    private void txtLedgerCreate_SaleTaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_SaleTaxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCreditLimit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerCreate_IncomeTax.requestFocus();
        }
}//GEN-LAST:event_txtLedgerCreate_SaleTaxKeyPressed

    private void txtLedgerCreate_BalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerCreate_BalanceFocusGained
        if (txtLedgerCreate_Balance.getText().trim().equals("0")) {
            txtLedgerCreate_Balance.setText("");
        }
    }//GEN-LAST:event_txtLedgerCreate_BalanceFocusGained

    private void txtLedgerCreate_BalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerCreate_BalanceFocusLost
       if (txtLedgerCreate_Balance.getText().trim().equals("")) {
            txtLedgerCreate_Balance.setText("0");
        }
    }//GEN-LAST:event_txtLedgerCreate_BalanceFocusLost

    private void txtLedgerCreate_BalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_BalanceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optDebit.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboLedgerCreate_Under.requestFocus();
        }
    }//GEN-LAST:event_txtLedgerCreate_BalanceKeyPressed

    private void txtLedgerCreate_BalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerCreate_BalanceKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag=0;
        String f=txtLedgerCreate_Balance.getText().trim();
        int i=0;
        while(i<f.length())
        {
            if(f.charAt(i)=='.')
            {
                flag=1;
                break;
            }
            i++;
        }
        if(flag==1 && c == '.')
        {
            evt.consume();
        } 

    }//GEN-LAST:event_txtLedgerCreate_BalanceKeyTyped

    private void txtCreditLimitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditLimitKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag=0;
        String f=txtCreditLimit.getText().trim();
        int i=0;
        while(i<f.length())
        {
            if(f.charAt(i)=='.')
            {
                flag=1;
                break;
            }
            i++;
        }
        if(flag==1 && c == '.')
        {
            evt.consume();
        } 
    }//GEN-LAST:event_txtCreditLimitKeyTyped

    private void btnLedgerCreate_CreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLedgerCreate_CreateKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
         btnLedgerCreate_CreateActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_LEFT)
        {
         btnLedgerCreate_Back.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
         txtCreditLimit.requestFocus();
        }
    }//GEN-LAST:event_btnLedgerCreate_CreateKeyPressed

    private void btnLedgerCreate_BackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLedgerCreate_BackKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
        btnLedgerCreate_BackActionPerformed(null);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
        {
         btnLedgerCreate_Create.requestFocus();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
         txtCreditLimit.requestFocus();
        }
    }//GEN-LAST:event_btnLedgerCreate_BackKeyPressed

    private void comboLedgerCreate_UnderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboLedgerCreate_UnderKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLedgerCreate_UnderKeyReleased

    private void comboLedgerCreate_UnderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboLedgerCreate_UnderMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_comboLedgerCreate_UnderMouseClicked

    private void btnLedgerCreate_CreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLedgerCreate_CreateMouseClicked
     btnLedgerCreate_CreateActionPerformed(null);
    }//GEN-LAST:event_btnLedgerCreate_CreateMouseClicked

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        combocontrol=1;
        addComboItem=1;

    }//GEN-LAST:event_formInternalFrameDeactivated

    private void txtLedgerCreate_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerCreate_NameFocusGained
        // TODO add your handling code here:
        txtLedgerCreate_Name.selectAll();
    }//GEN-LAST:event_txtLedgerCreate_NameFocusGained

    private void txtLedgerCreate_AliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerCreate_AliasFocusGained
        // TODO add your handling code here:
        txtLedgerCreate_Alias.selectAll();
    }//GEN-LAST:event_txtLedgerCreate_AliasFocusGained

    private void txtLedgerCreate_AddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerCreate_AddressFocusGained
        // TODO add your handling code here:
        txtLedgerCreate_Address.selectAll();
    }//GEN-LAST:event_txtLedgerCreate_AddressFocusGained

    private void txtLedgerCreate_EmailIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerCreate_EmailIdFocusGained
        // TODO add your handling code here:
        txtLedgerCreate_EmailId.selectAll();
    }//GEN-LAST:event_txtLedgerCreate_EmailIdFocusGained

    private void comboLedgerCreate_UnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboLedgerCreate_UnderFocusLost
        // TODO add your handling code here:
        comboLedgerCreate_Under.hidePopup();
    }//GEN-LAST:event_comboLedgerCreate_UnderFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLedgerCreate_Back;
    private javax.swing.JButton btnLedgerCreate_Create;
    private javax.swing.JButton btnLedgerCreate_CreateGroup;
    private javax.swing.JComboBox comboLedgerCreate_Under;
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
    private javax.swing.JFormattedTextField txtCreditLimit;
    private javax.swing.JTextField txtLedgerCreate_Address;
    private javax.swing.JTextField txtLedgerCreate_Alias;
    private javax.swing.JFormattedTextField txtLedgerCreate_Balance;
    private javax.swing.JTextField txtLedgerCreate_ContactNo;
    private javax.swing.JTextField txtLedgerCreate_EmailId;
    private javax.swing.JTextField txtLedgerCreate_IncomeTax;
    public javax.swing.JTextField txtLedgerCreate_Name;
    private javax.swing.JTextField txtLedgerCreate_SaleTax;
    // End of variables declaration//GEN-END:variables
private boolean hide_flag = false;
       private void setModel(DefaultComboBoxModel mdl, String str) {
      
           comboLedgerCreate_Under.setModel(mdl);
        comboLedgerCreate_Under.setSelectedIndex(-1);
        comboLedgerCreate_Under.setPopupVisible(true);
        tf.setText(str);
        //tf.selectAll();
    }
    
    
private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for(String s: list) {
            //if(s.startsWith(text)) m.addElement(s);
            if(s.toLowerCase().matches("(.*)"+text.toLowerCase()+"(.*)")) {
                m.addElement(s);
                System.out.println("Yes"+s);
            }
        }
        return m;
    }





 private boolean checkElementPresence(Vector combo,String str) 
         {
          for(int i=0;i<combo.size();i++)
          {
           if(tf.getText().trim().equals(comboLedgerCreate_Under.getItemAt(i)))
           {
             return true;
           }
          }
       return false;
         }

}
