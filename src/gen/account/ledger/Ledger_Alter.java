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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class Ledger_Alter extends javax.swing.JInternalFrame {

    private Object[][] RowData;
    ArrayList<LedgerData> arrayListLedgerData = new ArrayList<LedgerData>();
    int RowId = 0;
    // ArrayList<String> GroupItems=new ArrayList<String>();
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    private final Vector<String> v1 = new Vector<String>();
    static int combocontrol = 0;
    static int comboFocusControl = 0;

    public Ledger_Alter(String s) {

        setClosable(true);
        initComponents();
        ledgergrid.setRowSelectionAllowed(true);
        ledgergrid.setColumnSelectionAllowed(false);
        this.setTitle(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelLedgerAlter1 = new javax.swing.JPanel();
        PanelLedgerAlter2 = new javax.swing.JPanel();
        txtLedgerAlter_grid = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ledgergrid = new javax.swing.JTable(

        );
        PanelLedgerAlter3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLedgerAlter_Name = new javax.swing.JTextField();
        txtLedgerAlter_Alias = new javax.swing.JTextField();
        comboLedgerAlter_Under = new javax.swing.JComboBox();
        txtLedgerAlter_Address = new javax.swing.JTextField();
        txtLedgerAlter_IncomeTax = new javax.swing.JTextField();
        txtLedgerAlter_SaleTax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLedgerAlter_Group = new javax.swing.JButton();
        btnLedgerAlter_Update = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLedgerAlter_ContactNo = new javax.swing.JTextField();
        txtLedgerAlter_EmailId = new javax.swing.JTextField();
        optDebit = new javax.swing.JRadioButton();
        optCredit = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtCreditLimit = new javax.swing.JFormattedTextField();
        txtLedgerAlter_Balance = new javax.swing.JFormattedTextField();

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

        txtLedgerAlter_grid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_grid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLedgerAlter_gridActionPerformed(evt);
            }
        });
        txtLedgerAlter_grid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_gridFocusGained(evt);
            }
        });
        txtLedgerAlter_grid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_gridKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_gridKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_gridKeyTyped(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBack.setText("Back"); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        ledgergrid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ledgergrid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        ledgergrid.setColumnSelectionAllowed(true);
        ledgergrid.setRowHeight(20);
        ledgergrid.setTableHeader(null);
        ledgergrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ledgergridMouseClicked(evt);
            }
        });
        ledgergrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ledgergridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ledgergrid);

        javax.swing.GroupLayout PanelLedgerAlter2Layout = new javax.swing.GroupLayout(PanelLedgerAlter2);
        PanelLedgerAlter2.setLayout(PanelLedgerAlter2Layout);
        PanelLedgerAlter2Layout.setHorizontalGroup(
            PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLedgerAlter2Layout.createSequentialGroup()
                .addGroup(PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelLedgerAlter2Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnBack))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelLedgerAlter2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txtLedgerAlter_grid, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelLedgerAlter2Layout.setVerticalGroup(
            PanelLedgerAlter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtLedgerAlter_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap())
        );

        PanelLedgerAlter3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Name"); // NOI18N

        txtLedgerAlter_Name.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_NameFocusGained(evt);
            }
        });
        txtLedgerAlter_Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_NameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_NameKeyTyped(evt);
            }
        });

        txtLedgerAlter_Alias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_Alias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_AliasFocusGained(evt);
            }
        });
        txtLedgerAlter_Alias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_AliasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_AliasKeyTyped(evt);
            }
        });

        comboLedgerAlter_Under.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboLedgerAlter_Under.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLedgerAlter_UnderActionPerformed(evt);
            }
        });
        comboLedgerAlter_Under.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboLedgerAlter_UnderFocusLost(evt);
            }
        });
        comboLedgerAlter_Under.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboLedgerAlter_UnderKeyPressed(evt);
            }
        });

        txtLedgerAlter_Address.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_Address.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_AddressFocusGained(evt);
            }
        });
        txtLedgerAlter_Address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_AddressKeyPressed(evt);
            }
        });

        txtLedgerAlter_IncomeTax.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_IncomeTax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_IncomeTaxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_IncomeTaxFocusLost(evt);
            }
        });
        txtLedgerAlter_IncomeTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_IncomeTaxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_IncomeTaxKeyTyped(evt);
            }
        });

        txtLedgerAlter_SaleTax.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_SaleTax.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_SaleTaxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_SaleTaxFocusLost(evt);
            }
        });
        txtLedgerAlter_SaleTax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_SaleTaxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_SaleTaxKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Sale Tax Number"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Income Tax Number"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Address"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Opening Balance"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Under"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Alias"); // NOI18N

        btnLedgerAlter_Group.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLedgerAlter_Group.setText("Create Group"); // NOI18N
        btnLedgerAlter_Group.setFocusPainted(false);
        btnLedgerAlter_Group.setFocusable(false);
        btnLedgerAlter_Group.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLedgerAlter_GroupActionPerformed(evt);
            }
        });
        btnLedgerAlter_Group.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLedgerAlter_GroupKeyPressed(evt);
            }
        });

        btnLedgerAlter_Update.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLedgerAlter_Update.setText("Update"); // NOI18N
        btnLedgerAlter_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLedgerAlter_UpdateMouseClicked(evt);
            }
        });
        btnLedgerAlter_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLedgerAlter_UpdateActionPerformed(evt);
            }
        });
        btnLedgerAlter_Update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLedgerAlter_UpdateKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Email Id"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Contact No"); // NOI18N

        txtLedgerAlter_ContactNo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_ContactNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_ContactNoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_ContactNoKeyTyped(evt);
            }
        });

        txtLedgerAlter_EmailId.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_EmailId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLedgerAlter_EmailIdActionPerformed(evt);
            }
        });
        txtLedgerAlter_EmailId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_EmailIdFocusGained(evt);
            }
        });
        txtLedgerAlter_EmailId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_EmailIdKeyPressed(evt);
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

        txtLedgerAlter_Balance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.##"))));
        txtLedgerAlter_Balance.setText("0");
        txtLedgerAlter_Balance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtLedgerAlter_Balance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_BalanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLedgerAlter_BalanceFocusLost(evt);
            }
        });
        txtLedgerAlter_Balance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_BalanceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLedgerAlter_BalanceKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PanelLedgerAlter3Layout = new javax.swing.GroupLayout(PanelLedgerAlter3);
        PanelLedgerAlter3.setLayout(PanelLedgerAlter3Layout);
        PanelLedgerAlter3Layout.setHorizontalGroup(
            PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(36, 36, 36)
                        .addComponent(txtLedgerAlter_IncomeTax, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addComponent(txtLedgerAlter_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(txtLedgerAlter_EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel1)
                                    .addGap(115, 115, 115)
                                    .addComponent(txtLedgerAlter_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel2)
                                    .addGap(120, 120, 120)
                                    .addComponent(txtLedgerAlter_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel3)
                                    .addGap(115, 115, 115)
                                    .addComponent(comboLedgerAlter_Under, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                    .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4))
                                    .addGap(54, 54, 54)
                                    .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtLedgerAlter_Balance)
                                        .addComponent(txtLedgerAlter_Address, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLedgerAlter_Group, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                                .addComponent(optDebit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(optCredit))))
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(53, 53, 53)
                        .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCreditLimit)
                            .addComponent(txtLedgerAlter_SaleTax, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLedgerAlter3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLedgerAlter_Update)
                .addGap(204, 204, 204))
        );
        PanelLedgerAlter3Layout.setVerticalGroup(
            PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtLedgerAlter_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtLedgerAlter_Alias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3))
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLedgerAlter_Under, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLedgerAlter_Group))))
                .addGap(18, 18, 18)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(optDebit)
                        .addComponent(optCredit)
                        .addComponent(txtLedgerAlter_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLedgerAlter_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLedgerAlter_ContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(19, 19, 19)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLedgerAlter_EmailId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtLedgerAlter_IncomeTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLedgerAlter3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel7))
                    .addComponent(txtLedgerAlter_SaleTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(PanelLedgerAlter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(txtCreditLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLedgerAlter_Update)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelLedgerAlter1Layout = new javax.swing.GroupLayout(PanelLedgerAlter1);
        PanelLedgerAlter1.setLayout(PanelLedgerAlter1Layout);
        PanelLedgerAlter1Layout.setHorizontalGroup(
            PanelLedgerAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelLedgerAlter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelLedgerAlter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelLedgerAlter1Layout.setVerticalGroup(
            PanelLedgerAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLedgerAlter1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLedgerAlter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PanelLedgerAlter2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelLedgerAlter3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelLedgerAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelLedgerAlter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLedgerAlter_gridKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridKeyReleased
        try
        {
            DefaultTableModel d=(DefaultTableModel)ledgergrid.getModel();
            String text=txtLedgerAlter_grid.getText().trim();
            int size = txtLedgerAlter_grid.getText().trim().length();
            d.setRowCount(0);
            int i=0;
            for(i=0;i<arrayListLedgerData.size();i++)
            {
                String record=arrayListLedgerData.get(i).getName();
                int id=arrayListLedgerData.get(i).getId();
                if(record.length()>=size)
                {
                    String s=record.substring(0, size);
                    if(s.equalsIgnoreCase(text)) 
                    {
                        int rows=d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        ledgergrid.setValueAt(record, rows-1, 0);
                        ledgergrid.setValueAt(id, rows-1, 1);
                    }
                }
            }
        }
        catch(Exception e)
        {
        }
    }//GEN-LAST:event_txtLedgerAlter_gridKeyReleased

    private void ledgergridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ledgergridMouseClicked
        if (ledgergrid.getRowCount() > 0) {
            txtLedgerAlter_Name.requestFocus();
            int row = ledgergrid.getSelectedRow();
            RowId = Integer.parseInt(ledgergrid.getValueAt(row, 1).toString());
            txtLedgerAlter_grid.setText("");
            String name = (String) ledgergrid.getValueAt(row, 0);
            txtLedgerAlter_grid.setText(name);
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                ResultSet rs = st.executeQuery("select * from tblledger where ledger_id=" + RowId + "");
                if (rs.next()) {
                    txtLedgerAlter_Name.setEnabled(true);
                    txtLedgerAlter_Alias.setEnabled(true);
                    txtLedgerAlter_Balance.setEnabled(true);
                    txtLedgerAlter_Address.setEnabled(true);
                    txtLedgerAlter_ContactNo.setEnabled(true);
                    txtLedgerAlter_EmailId.setEnabled(true);
                    txtLedgerAlter_IncomeTax.setEnabled(true);
                    txtLedgerAlter_SaleTax.setEnabled(true);
                    comboLedgerAlter_Under.setEnabled(true);
                    btnLedgerAlter_Update.setEnabled(true);
                    btnLedgerAlter_Group.setEnabled(true);
                    optCredit.setEnabled(true);
                    optDebit.setEnabled(true);
                    txtCreditLimit.setEnabled(true);

                    txtLedgerAlter_Name.setText(rs.getString("ledger_name"));
                    txtLedgerAlter_Alias.setText(rs.getString("ledger_alias"));
//////////////////////////// Atul Code //////////////////////////////////////////           
                    if (Double.parseDouble("" + rs.getString("ledger_openingBalance")) == 0.00) {
                        txtLedgerAlter_Balance.setText("0");
                    } else {
                        txtLedgerAlter_Balance.setText(Constants.DECIMAL_FORMAT.format(rs.getString("ledger_openingBalance")));
                    }


                    //  txtLedgerAlter_Balance.setText(rs.getString("ledger_openingBalance"));
                    txtLedgerAlter_Address.setText(rs.getString("ledger_address"));
                    txtLedgerAlter_ContactNo.setText(rs.getString("ledger_contactNo"));
                    txtLedgerAlter_EmailId.setText(rs.getString("ledger_emailId"));
                    txtLedgerAlter_IncomeTax.setText(rs.getString("ledger_inTaxo"));
                    txtLedgerAlter_SaleTax.setText(rs.getString("ledger_saleTaxNo"));
                    if (rs.getInt("opening_type") == 1) {
                        optCredit.setSelected(false);
                        optDebit.setSelected(true);
                    } else {
                        optCredit.setSelected(true);
                        optDebit.setSelected(false);
                    }
                    int under = Integer.parseInt(rs.getString("ledger_under"));
                    ResultSet rs1 = st.executeQuery("select group_name from tblgroup where group_id=" + under + "");
                    if (rs1.next()) {
                        // comboLedgerAlter_Under.setSelectedItem(rs1.getString("group_name"));   
                        tf.setText(rs1.getString("group_name"));
                    }
                    rs1.close();

                    System.out.println(RowId);
                    rs1 = st1.executeQuery("select ledger_limit from tblledgercreditlimit where ledger_id=" + RowId + "");
                    if (rs1.next()) {

                        if (Double.parseDouble("" + rs1.getString("ledger_limit")) == 0.00) {
                            txtCreditLimit.setText("0");
                        } else {
                            txtCreditLimit.setText("" + Constants.DECIMAL_FORMAT.format(rs1.getString("ledger_limit")));
                        }
                        //  txtCreditLimit.setText(""+rs1.getString("ledger_limit"));     
                    } else {
                        txtCreditLimit.setText("0");
                    }
                    rs1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Ledger_Alter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
}//GEN-LAST:event_ledgergridMouseClicked

    private void ledgergridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ledgergridKeyPressed

//            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            int r=ledgergrid.getSelectedRow();
//            ledgergridMouseClicked(null);
//             if(r>0){
//                 ledgergrid.changeSelection(r-1, 0, false, false);
//            }
//            else{
//                 ledgergrid.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            txtLedgerAlter_grid.requestFocus();
//        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int r = ledgergrid.getSelectedRow();
            ledgergridMouseClicked(null);
            if (r > 0) {
                ledgergrid.changeSelection(r - 1, 0, false, false);
            } else {
                ledgergrid.changeSelection(0, 0, false, false);
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_grid.requestFocus();
        } else {
            int k = evt.getKeyCode();
            char c = evt.getKeyChar();
            if (k >= 48 && k <= 57) {
                txtLedgerAlter_grid.setText(txtLedgerAlter_grid.getText().trim() + "" + c);
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();
                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
                            ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 65 && k <= 90) {
                txtLedgerAlter_grid.setText(txtLedgerAlter_grid.getText().trim() + "" + c);
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
                            ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k >= 96 && k <= 105) {
                txtLedgerAlter_grid.setText(txtLedgerAlter_grid.getText().trim() + "" + c);
                String text = txtLedgerAlter_grid.getText().trim();
                int size = text.length();
                DefaultTableModel model = new DefaultTableModel();
                ledgergrid.setModel(model);
                ledgergrid.setTableHeader(null);
                //model.setRowCount(0);
                for (int i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();

                    if (record.length() >= size) {
                        String s = record.substring(0, size);
                        if (s.equalsIgnoreCase(text)) {
                            int r = model.getRowCount();
                            model.setRowCount(r + 1);
                            model.setColumnCount(2);
                            ledgergrid.setValueAt(record, r, 0);
                            ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
                            ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                            ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                        }
                    }
                }
            } else if (k == 8) {
                //txt.getText().length()=txt.getText().length()-1;
                if (txtLedgerAlter_grid.getText().trim().equals("") == false) {
                    String text = txtLedgerAlter_grid.getText().trim();
                    int size = text.length();
                    String s1 = text.substring(0, size - 1);
                    txtLedgerAlter_grid.setText(s1);
                    size = txtLedgerAlter_grid.getText().trim().length();
                    DefaultTableModel model = new DefaultTableModel();
                    ledgergrid.setModel(model);
                    ledgergrid.setTableHeader(null);
                    //model.setRowCount(0);
                    for (int i = 0; i < arrayListLedgerData.size(); i++) {
                        String record = arrayListLedgerData.get(i).getName();

                        if (record.length() >= size) {
                            String s2 = record.substring(0, size);
                            if (s2.equalsIgnoreCase(txtLedgerAlter_grid.getText().trim())) {
                                int r = model.getRowCount();
                                model.setRowCount(r + 1);
                                model.setColumnCount(2);
                                ledgergrid.setValueAt(record, r, 0);
                                ledgergrid.setValueAt(arrayListLedgerData.get(i).getId(), r, 1);
                                ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                                ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                                ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                            }
                        }
                    }
                }
            }
        }

}//GEN-LAST:event_ledgergridKeyPressed

    private void txtLedgerAlter_NameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_NameKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            txtLedgerAlter_Alias.requestFocus();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            txtLedgerAlter_grid.requestFocus();
        }
    }//GEN-LAST:event_txtLedgerAlter_NameKeyPressed

    private void txtLedgerAlter_AliasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_AliasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            comboLedgerAlter_Under.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_Name.requestFocus();
        }
}//GEN-LAST:event_txtLedgerAlter_AliasKeyPressed

    private void comboLedgerAlter_UnderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboLedgerAlter_UnderKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerAlter_Balance.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_Alias.requestFocus();
        }
}//GEN-LAST:event_comboLedgerAlter_UnderKeyPressed

    private void txtLedgerAlter_AddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_AddressKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            txtLedgerAlter_ContactNo.requestFocus();
        }
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) 
        {
            optCredit.requestFocus();
        }
}//GEN-LAST:event_txtLedgerAlter_AddressKeyPressed

    private void txtLedgerAlter_IncomeTaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_IncomeTaxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerAlter_SaleTax.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_EmailId.requestFocus();
        }
}//GEN-LAST:event_txtLedgerAlter_IncomeTaxKeyPressed

    private void txtLedgerAlter_IncomeTaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_IncomeTaxKeyTyped
}//GEN-LAST:event_txtLedgerAlter_IncomeTaxKeyTyped

    private void txtLedgerAlter_SaleTaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_SaleTaxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCreditLimit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_IncomeTax.requestFocus();
        }
}//GEN-LAST:event_txtLedgerAlter_SaleTaxKeyPressed

    private void txtLedgerAlter_SaleTaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_SaleTaxKeyTyped
        /* char c=evt.getKeyChar();
         if(Character.isDigit(c))
         evt.consume();*/
}//GEN-LAST:event_txtLedgerAlter_SaleTaxKeyTyped

    private void btnLedgerAlter_GroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLedgerAlter_GroupActionPerformed
        Group_Create group = new Group_Create("Create New Group");
        group.setVisible(true);
        group.pack();
        group.setVisible(true);
        this.getParent().add(group);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = group.getSize();
        group.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);

        try {
            group.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }

        BasicInternalFrameUI basicInternalFrameUI = (BasicInternalFrameUI) group.getUI();

        Component north = basicInternalFrameUI.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }

        comboFocusControl = 1;
}//GEN-LAST:event_btnLedgerAlter_GroupActionPerformed

    private void btnLedgerAlter_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLedgerAlter_UpdateActionPerformed
        String name1 = txtLedgerAlter_Name.getText().trim();
        String no = txtLedgerAlter_ContactNo.getText().trim();

        if (txtLedgerAlter_Name.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Data in Name Fields");
            txtLedgerAlter_Name.requestFocus();
        } //        else if(!name1.equals(txtLedgerAlter_grid.getText().trim()))
        //                {
        //                 JOptionPane.showMessageDialog(null,"Please select a valid Ledger");
        //                 txtLedgerAlter_grid.requestFocus();
        //                }
        else if (comboLedgerAlter_Under.getSelectedItem() == null || comboLedgerAlter_Under.getSelectedItem() == "")// || tf.getText().trim().equalsIgnoreCase("") || ComboGroupCreate_Under.getSelectedItem().toString().trim().equals("") ) 
        {
            System.out.println("eeeeeeeeeeeeeeeee");
            JOptionPane.showMessageDialog(this, "Select Data for Under Group Fields");
            comboLedgerAlter_Under.requestFocus();
        } else if (!checkElementPresence(v1, tf.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Under Field Value is not valid");
            //scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
            comboLedgerAlter_Under.requestFocus();
        } /*  else if(no.length() != 10&&txtLedgerAlter_ContactNo.getText().equals("")==false) 
         {
         JOptionPane.showMessageDialog(null,"Plz Enter valid Contact No.");
         txtLedgerAlter_ContactNo.requestFocus();
         txtLedgerAlter_ContactNo.selectAll();
         }*/ else {
            String email = txtLedgerAlter_EmailId.getText().trim();
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches() && txtLedgerAlter_EmailId.getText().trim().equals("") == false) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                Calendar currentDate = Calendar.getInstance();
                String dateNow = f.format(currentDate.getTime());
                Connection conn = null;

                try {

                    String name = txtLedgerAlter_Name.getText().trim();
                    String alias = txtLedgerAlter_Alias.getText().trim();
                    String under = (String) comboLedgerAlter_Under.getSelectedItem();

                    double balance = 0;
                    if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                        balance = Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                    }

                    String address = txtLedgerAlter_Address.getText().trim();
                    String contactNo = txtLedgerAlter_ContactNo.getText().trim();
                    String emailId = txtLedgerAlter_EmailId.getText().trim();

                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();
                    Statement st4 = conn.createStatement();

                    ResultSet rs3 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtLedgerAlter_Name.getText().trim() + "' and ledger_id<>" + RowId + "");
                    if (rs3.next()) {

                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtLedgerAlter_Name.selectAll();
                        txtLedgerAlter_Name.requestFocus();
                    } else {


                        ResultSet rs = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                        if (rs.next()) {
                            double blnc = 0;
                            int DebCred = 0;
                            String q = "";
                            //**********************************************                        
                            ResultSet rs1 = st1.executeQuery("select ledger_openingbalance,opening_type from tblledger where ledger_id='" + RowId + "'");
                            if (rs1.next()) {

                                int ot = 0;//opening type
                                if (optCredit.isSelected()) {
                                    ot = 2;
                                } else {
                                    ot = 1;
                                }
                                //-----------------update Ledger Table---------------------
                                //    st3.executeUpdate("UPDATE tblledger SET ledger_name='"+name.trim()+"',ledger_under="+rs.getLong("group_id")+",ledger_alias='"+alias.trim()+"',ledger_address='"+address.trim()+"',ledger_contactno='"+contactNo+"',ledger_emailid='"+emailId+"',ledger_openingbalance="+balance+",ledger_intaxo='"+txtLedgerAlter_IncomeTax.getText().trim()+"',ledger_saletaxno='"+txtLedgerAlter_SaleTax.getText().trim()+"',opening_type="+ ot +",dat='"+ dateNow +"' where ledger_id="+RowId+"");

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
                                ps.setString(8, txtLedgerAlter_IncomeTax.getText().trim());
                                ps.setString(9, txtLedgerAlter_SaleTax.getText().trim());
                                ps.setInt(10, ot);
                                ps.setString(11, dateNow);
                                ps.setInt(12, RowId);

                                ps.executeUpdate();
//                            q="update tblledger set ledger_name='"+name+"',ledger_under="+rs.getLong("group_id")+",ledger_alias='"+alias+"',ledger_address='"+address+"',ledger_contactno="+contactNo+" where ledger_id="+RowId+"";
//                            System.out.println(q);
//                            st3.executeUpdate(q);
//                            
//                            q="update tblledger set ledger_emailId='"+ emailId +"',ledger_openingbalance="+balance+",ledger_intaxo="+Long.parseLong(incometax)+",ledger_saletaxno="+Long.parseLong(saletax)+",opening_type="+ ot +",dat='"+ dateNow +"' where ledger_id="+RowId+"";
//                            System.out.println(q);
//                            st3.executeUpdate(q);


                                //------------update Ledger Credit Limit Table---------------
                                double c = 0;
                                if (txtCreditLimit.getText().trim().equals("") == false) {
                                    c = Double.parseDouble(txtCreditLimit.getText().trim());
                                }
                                st4.executeUpdate("UPDATE tblledgercreditlimit SET ledger_limit=" + c + " where ledger_id=" + RowId + "");

                                blnc = rs1.getDouble("ledger_openingbalance");
                                if (rs1.getInt("opening_type") == 2) {
                                    if (optCredit.isSelected()) {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 1;
                                    }

                                } else {
                                    if (optCredit.isSelected()) {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 1;
                                    }

                                }

                                ResultSet rs2 = st2.executeQuery("select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id='" + RowId + "'");
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
                                    st.executeUpdate("UPDATE tblledgercurrentbalance SET ledger_currentBalance=" + Math.abs(blnc) + ",ledger_DebitOrCredit=" + DebCred + " where ledger_id=" + RowId + "");
                                }

                            }
                            //**********************************************

                        }
                        conn.commit();
                        Constants.LEDGER_TIME_STAMP++;
                        JOptionPane.showMessageDialog(null, "Record Updated Successfully");
                        // arrayListLedgerData.clear();
                        txtLedgerAlter_Name.setText("");
                        txtLedgerAlter_Alias.setText("");
                        txtLedgerAlter_Balance.setText("0");
                        txtLedgerAlter_Address.setText("");
                        txtLedgerAlter_ContactNo.setText("");
                        txtLedgerAlter_EmailId.setText("");
                        txtLedgerAlter_IncomeTax.setText("");
                        txtLedgerAlter_SaleTax.setText("");
                        txtCreditLimit.setText("");
                        optDebit.setSelected(false);
                        optCredit.setSelected(false);
                        comboLedgerAlter_Under.setSelectedIndex(0);

                        txtLedgerAlter_Name.setEnabled(false);
                        txtLedgerAlter_Alias.setEnabled(false);
                        txtLedgerAlter_Balance.setEnabled(false);
                        txtLedgerAlter_Address.setEnabled(false);
                        txtLedgerAlter_ContactNo.setEnabled(false);
                        txtLedgerAlter_EmailId.setEnabled(false);
                        txtLedgerAlter_IncomeTax.setEnabled(false);
                        txtLedgerAlter_SaleTax.setEnabled(false);
                        comboLedgerAlter_Under.setEnabled(false);
                        btnLedgerAlter_Update.setEnabled(false);
                        btnLedgerAlter_Group.setEnabled(false);
                        optCredit.setEnabled(false);
                        optDebit.setEnabled(false);
                        txtCreditLimit.setEnabled(false);

                        comboLedgerAlter_Under.setSelectedItem("");
                        tf.setText("");
                        txtLedgerAlter_grid.requestFocus();
                        txtLedgerAlter_grid.setText("");
                        gridlist();
                        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
                        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
                        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
                    }
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (txtLedgerAlter_EmailId.getText().trim().equals("")) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                Calendar currentDate = Calendar.getInstance();
                String dateNow = f.format(currentDate.getTime());
                Connection conn = null;
                try {
                    String name = txtLedgerAlter_Name.getText().trim();
                    String alias = txtLedgerAlter_Alias.getText().trim();
                    String under = (String) comboLedgerAlter_Under.getSelectedItem();

                    double balance = 0;
                    if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                        balance = Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                    }

                    String address = txtLedgerAlter_Address.getText().trim();
                    String contactNo = txtLedgerAlter_ContactNo.getText().trim();
                    String emailId = "";

                    String incometax = "";
                    // Long incometax="";
                    if (txtLedgerAlter_IncomeTax.getText().trim().equals("") == false) //incometax=Long.parseLong(txtLedgerAlter_IncomeTax.getText().trim());
                    {
                        incometax = txtLedgerAlter_IncomeTax.getText().trim();
                    }

                    String saletax = "";
                    //Long saletax=0;
                    if (txtLedgerAlter_SaleTax.getText().trim().equals("") == false) //  saletax=Long.parseLong(txtLedgerAlter_SaleTax.getText().trim());
                    {
                        saletax = "" + txtLedgerAlter_SaleTax.getText().trim();
                    }

                    conn = DatabaseConnection1.GetConnection();
                    conn.setAutoCommit(false);
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();
                    Statement st4 = conn.createStatement();
                    ResultSet rs4 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtLedgerAlter_Name.getText().trim() + "' and ledger_id<>" + RowId + "");
                    if (rs4.next()) {

                        JOptionPane.showMessageDialog(null, "This name is already taken");
                        txtLedgerAlter_Name.selectAll();
                        txtLedgerAlter_Name.requestFocus();
                    } else {


//                    ResultSet rs3 = st.executeQuery("select ledger_id from tblledger where ledger_name='" + txtLedgerAlter_Name.getText() + "'");
//                    if (rs3.next()) 
//                    {
//                        JOptionPane.showMessageDialog(null, "This name is already taken");
//                        txtLedgerAlter_Name.selectAll();
//                        txtLedgerAlter_Name.requestFocus();
//                    }
//                    else 
//                    {
                        ResultSet rs = st.executeQuery("select group_id from tblgroup where group_name='" + under + "'");
                        if (rs.next()) {
                            double blnc = 0;
                            int DebCred = 0;
                            String q = "";
                            //**********************************************                        
                            ResultSet rs1 = st1.executeQuery("select ledger_openingbalance,opening_type from tblledger where ledger_id='" + RowId + "'");
                            if (rs1.next()) {

                                int ot = 0;//opening type
                                if (optCredit.isSelected()) {
                                    ot = 2;
                                } else {
                                    ot = 1;
                                }
                                //-----------------update Ledger Table---------------------
                                q = "update tblledger set ledger_name='" + name + "',ledger_under=" + rs.getLong("group_id") + ",ledger_alias='" + alias + "',ledger_address='" + address + "',ledger_contactno='" + contactNo + "',ledger_emailId='" + emailId + "',ledger_openingbalance=" + balance + ",ledger_intaxo='" + incometax + "',ledger_saletaxno='" + saletax + "',opening_type=" + ot + ",dat='" + dateNow + "' where ledger_id=" + RowId + "";
                                st3.executeUpdate(q);

                                //------------update Ledger Credit Limit Table---------------
                                double c = 0;
                                if (txtCreditLimit.getText().trim().equals("") == false) {
                                    c = Double.parseDouble(txtCreditLimit.getText().trim());
                                }
                                st4.executeUpdate("UPDATE tblledgercreditlimit SET ledger_limit=" + c + " where ledger_id=" + RowId + "");

                                blnc = rs1.getDouble("ledger_openingbalance");

                                if (rs1.getInt("opening_type") == 2) {
                                    if (optCredit.isSelected()) {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 1;
                                    }

                                } else {
                                    if (optCredit.isSelected()) {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc + Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 2;
                                    } else {
                                        if (txtLedgerAlter_Balance.getText().trim().equals("") == false) {
                                            blnc = blnc - Double.parseDouble(txtLedgerAlter_Balance.getText().trim());
                                        }
                                        DebCred = 1;
                                    }
                                }

                                ResultSet rs2 = st2.executeQuery("select ledger_currentBalance,ledger_DebitOrCredit from tblledgercurrentbalance where ledger_id='" + RowId + "'");
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
                                    st.executeUpdate("UPDATE tblledgercurrentbalance SET ledger_currentBalance=" + Math.abs(blnc) + ",ledger_DebitOrCredit=" + DebCred + " where ledger_id=" + RowId + "");
                                }

                            }
                            //**********************************************
                        }
                        JOptionPane.showMessageDialog(null, "Record Updated Successfully");
                        //  arrayListLedgerData.clear();
                        txtLedgerAlter_Name.setText("");
                        txtLedgerAlter_Alias.setText("");
                        txtLedgerAlter_Balance.setText("0");
                        txtLedgerAlter_Address.setText("");
                        txtLedgerAlter_ContactNo.setText("");
                        txtLedgerAlter_EmailId.setText("");
                        txtLedgerAlter_IncomeTax.setText("");
                        txtLedgerAlter_SaleTax.setText("");
                        txtCreditLimit.setText("");
                        optDebit.setSelected(false);
                        optCredit.setSelected(false);
                        comboLedgerAlter_Under.setSelectedIndex(0);

                        txtLedgerAlter_Name.setEnabled(false);
                        txtLedgerAlter_Alias.setEnabled(false);
                        txtLedgerAlter_Balance.setEnabled(false);
                        txtLedgerAlter_Address.setEnabled(false);
                        txtLedgerAlter_ContactNo.setEnabled(false);
                        txtLedgerAlter_EmailId.setEnabled(false);
                        txtLedgerAlter_IncomeTax.setEnabled(false);
                        txtLedgerAlter_SaleTax.setEnabled(false);
                        comboLedgerAlter_Under.setEnabled(false);
                        btnLedgerAlter_Update.setEnabled(false);
                        btnLedgerAlter_Group.setEnabled(false);
                        optCredit.setEnabled(false);
                        optDebit.setEnabled(false);
                        txtCreditLimit.setEnabled(false);

                        txtLedgerAlter_grid.requestFocus();
                        txtLedgerAlter_grid.setText("");
                        tf.setText("");
                        //   gridlist();
                        //}
                    }

                    conn.commit();
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
                txtLedgerAlter_EmailId.requestFocus();
                txtLedgerAlter_EmailId.selectAll();
            }
        }
        gridlist();
        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);

}//GEN-LAST:event_btnLedgerAlter_UpdateActionPerformed

    private void txtLedgerAlter_ContactNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_ContactNoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerAlter_EmailId.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_Address.requestFocus();
        }
    }//GEN-LAST:event_txtLedgerAlter_ContactNoKeyPressed

    private void txtLedgerAlter_ContactNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_ContactNoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
}//GEN-LAST:event_txtLedgerAlter_ContactNoKeyTyped

    private void txtLedgerAlter_EmailIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLedgerAlter_EmailIdActionPerformed
        txtLedgerAlter_EmailId.transferFocus();
}//GEN-LAST:event_txtLedgerAlter_EmailIdActionPerformed

    private void txtLedgerAlter_EmailIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_EmailIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerAlter_IncomeTax.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_ContactNo.requestFocus();
        }
}//GEN-LAST:event_txtLedgerAlter_EmailIdKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        txtLedgerAlter_grid.requestFocus();
        btnBack.setMnemonic(KeyEvent.VK_B);
        btnLedgerAlter_Group.setMnemonic(KeyEvent.VK_O);
        btnLedgerAlter_Update.setMnemonic(KeyEvent.VK_U);

        txtLedgerAlter_Name.setEnabled(false);
        txtLedgerAlter_Alias.setEnabled(false);
        txtLedgerAlter_Balance.setEnabled(false);
        txtLedgerAlter_Address.setEnabled(false);
        txtLedgerAlter_ContactNo.setEnabled(false);
        txtLedgerAlter_EmailId.setEnabled(false);
        txtLedgerAlter_IncomeTax.setEnabled(false);
        txtLedgerAlter_SaleTax.setEnabled(false);
        comboLedgerAlter_Under.setEnabled(false);
        btnLedgerAlter_Update.setEnabled(false);
        btnLedgerAlter_Group.setEnabled(false);
        optCredit.setEnabled(false);
        optDebit.setEnabled(false);
        txtCreditLimit.setEnabled(false);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("select group_name from tblgroup");
            comboLedgerAlter_Under.removeAllItems();
            comboLedgerAlter_Under.addItem("");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            comboLedgerAlter_Under.setEditable(true);
            tf = (JTextField) comboLedgerAlter_Under.getEditor().getEditorComponent();

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
                comboLedgerAlter_Under.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboLedgerAlter_Under.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboLedgerAlter_Under.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboLedgerAlter_Under.showPopup();
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
                                        comboLedgerAlter_Under.setSelectedIndex(-1);
                                        tf.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {

                                if (comboLedgerAlter_Under.getSelectedItem().equals("")) {
                                    // JOptionPane.showMessageDialog(rootPane, "Enter data for Under Group Of Field");
                                    // comboLedgerAlter_Under.requestFocus();
                                } else {
                                    txtLedgerAlter_Balance.requestFocus();
                                }
                                // txtLedgerAlter_Balance.requestFocus();
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                txtLedgerAlter_Alias.requestFocus();
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
                    if (text.length() == 0) {
                        comboLedgerAlter_Under.hidePopup();
                        setModel(new DefaultComboBoxModel(v), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboLedgerAlter_Under.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            comboLedgerAlter_Under.showPopup();
                        }
                    }

                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            gridlist();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Ledger_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        gridlist();

        ledgergrid.getColumnModel().getColumn(1).setWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMinWidth(1);
        ledgergrid.getColumnModel().getColumn(1).setMaxWidth(1);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed

        combocontrol = 0;
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void txtLedgerAlter_gridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (ledgergrid.getRowCount() > 0) {
                ledgergrid.requestFocus();
                ledgergrid.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (ledgergrid.getRowCount() > 0) {
                ledgergrid.requestFocus();
                ledgergrid.changeSelection(0, 0, false, false);
            }
        }
    }//GEN-LAST:event_txtLedgerAlter_gridKeyPressed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Ledger_Alter.class.getName()).log(Level.SEVERE, null, ex);
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
            txtLedgerAlter_Balance.requestFocus();
        }
    }//GEN-LAST:event_optDebitKeyPressed

    private void optCreditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_optCreditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtLedgerAlter_Address.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            optDebit.requestFocus();
        }
    }//GEN-LAST:event_optCreditKeyPressed

    private void txtLedgerAlter_gridKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isDigit(c)) {
            //evt.consume();
        }
    }//GEN-LAST:event_txtLedgerAlter_gridKeyTyped

    private void txtLedgerAlter_NameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_NameKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c)) 
//        {
//            //evt.consume();
//        }
    }//GEN-LAST:event_txtLedgerAlter_NameKeyTyped

    private void txtLedgerAlter_AliasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_AliasKeyTyped
//        char c = evt.getKeyChar();
//        if (!Character.isLetter(c) && !Character.isDigit(c)) 
//        {
//            //evt.consume();
//        }
    }//GEN-LAST:event_txtLedgerAlter_AliasKeyTyped

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        Connection conn = null;
        conn = DatabaseConnection1.GetConnection();
        try {
           // if (combocontrol == 1) {
            //    comboLedgerAlter_Under.requestFocus();


                //Connection conn = null;
                comboLedgerAlter_Under.removeAllItems();
                comboLedgerAlter_Under.setSelectedItem("");
                v1.clear();

//        try {
                //   conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select group_name from tblgroup");

                comboLedgerAlter_Under.addItem("");
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
                comboLedgerAlter_Under.setEditable(true);
                tf = (JTextField) comboLedgerAlter_Under.getEditor().getEditorComponent();


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
                //comboLedgerAlter_Under.removeAllItems();
                // comboLedgerAlter_Under.addItem("");
                int siz = v1.size();
                for (int i = 0; i < siz; i++) {
//              
                    comboLedgerAlter_Under.addItem(v1.get(i));
                }
         //   }
//                            
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
//                          comboLedgerAlter_Under.hidePopup();
//                          setModel(new DefaultComboBoxModel(v1), "");
//                        }
//                        else
//                        {
//                          DefaultComboBoxModel m = getSuggestedModel(v1, text);
//                          if(m.getSize()==0 || hide_flag)
//                          {
//                            comboLedgerAlter_Under.hidePopup();
//                            hide_flag = false;
//                          }
//                          else
//                          {
//                            setModel(m, text);
//                            comboLedgerAlter_Under.showPopup();
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
//                         comboLedgerAlter_Under.setSelectedIndex(-1);
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
//                               txtLedgerAlter_Balance.requestFocus();
//                               }
//                                
//                                //txtLedgerCreate_Balance.requestFocus();
//                            }
//                            if(event.getKeyCode()==KeyEvent.VK_ESCAPE)
//                            {
//                             txtLedgerAlter_Alias.requestFocus();
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
                    // throw new UnsupportedOperationException("Not supported yet.");
                    String text = tf.getText().trim();
                    if (text.length() == 0) {
                        comboLedgerAlter_Under.hidePopup();
                        setModel(new DefaultComboBoxModel(v1), "");
                    } else {
                        DefaultComboBoxModel m = getSuggestedModel(v1, text);
                        if (m.getSize() == 0 || hide_flag) {
                            comboLedgerAlter_Under.hidePopup();
                            hide_flag = false;
                        } else {
                            setModel(m, text);
                            comboLedgerAlter_Under.showPopup();
                        }
                    }

                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Ledger_Create.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (comboFocusControl == 1) {
            tf.requestFocus();
        } else if (comboFocusControl == 0) {
            txtLedgerAlter_Name.requestFocus();
        }
        comboFocusControl = 0;

    }//GEN-LAST:event_formInternalFrameActivated

    private void btnLedgerAlter_GroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLedgerAlter_GroupKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLedgerAlter_GroupActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboLedgerAlter_Under.requestFocus();
        }
    }//GEN-LAST:event_btnLedgerAlter_GroupKeyPressed

    private void txtCreditLimitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCreditLimitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLedgerAlter_Update.requestFocus();
            //btnLedgerAlter_UpdateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtLedgerAlter_SaleTax.requestFocus();
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

    private void txtLedgerAlter_BalanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_BalanceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            optDebit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboLedgerAlter_Under.requestFocus();
        }
    }//GEN-LAST:event_txtLedgerAlter_BalanceKeyPressed

    private void txtLedgerAlter_BalanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLedgerAlter_BalanceKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
        int flag = 0;
        String f = txtLedgerAlter_Balance.getText().trim();
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

    }//GEN-LAST:event_txtLedgerAlter_BalanceKeyTyped

    private void txtLedgerAlter_gridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridActionPerformed
        ledgergrid.requestFocus();
    }//GEN-LAST:event_txtLedgerAlter_gridActionPerformed

    private void comboLedgerAlter_UnderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLedgerAlter_UnderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLedgerAlter_UnderActionPerformed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        combocontrol = 1;
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void txtLedgerAlter_BalanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_BalanceFocusGained
        // TODO add your handling code here:
        if (txtLedgerAlter_Balance.getText().equals("0")) {
            txtLedgerAlter_Balance.setText("");
        }
    }//GEN-LAST:event_txtLedgerAlter_BalanceFocusGained

    private void txtLedgerAlter_BalanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_BalanceFocusLost
        // TODO add your handling code here:
        if (txtLedgerAlter_Balance.getText().equals("")) {
            txtLedgerAlter_Balance.setText("0");
        }
    }//GEN-LAST:event_txtLedgerAlter_BalanceFocusLost

    private void txtLedgerAlter_IncomeTaxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_IncomeTaxFocusGained
        // TODO add your handling code here:
        if (txtLedgerAlter_IncomeTax.getText().equals("0")) {
            txtLedgerAlter_IncomeTax.setText("");
        }
    }//GEN-LAST:event_txtLedgerAlter_IncomeTaxFocusGained

    private void txtLedgerAlter_IncomeTaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_IncomeTaxFocusLost
        // TODO add your handling code here:
        if (txtLedgerAlter_IncomeTax.getText().equals("")) {
            txtLedgerAlter_IncomeTax.setText("0");
        }
    }//GEN-LAST:event_txtLedgerAlter_IncomeTaxFocusLost

    private void txtLedgerAlter_SaleTaxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_SaleTaxFocusGained
        // TODO add your handling code here:
        if (txtLedgerAlter_SaleTax.getText().equals("0")) {
            txtLedgerAlter_SaleTax.setText("");
        }
    }//GEN-LAST:event_txtLedgerAlter_SaleTaxFocusGained

    private void txtLedgerAlter_SaleTaxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_SaleTaxFocusLost
        // TODO add your handling code here:
        if (txtLedgerAlter_SaleTax.getText().equals("")) {
            txtLedgerAlter_SaleTax.setText("0");
        }
    }//GEN-LAST:event_txtLedgerAlter_SaleTaxFocusLost

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

    private void btnLedgerAlter_UpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLedgerAlter_UpdateKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLedgerAlter_UpdateActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtCreditLimit.requestFocus();
        }
    }//GEN-LAST:event_btnLedgerAlter_UpdateKeyPressed

    private void txtLedgerAlter_gridFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_gridFocusGained
        // TODO add your handling code here:
        txtLedgerAlter_grid.selectAll();
    }//GEN-LAST:event_txtLedgerAlter_gridFocusGained

    private void txtLedgerAlter_NameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_NameFocusGained
        // TODO add your handling code here:
        txtLedgerAlter_Name.selectAll();
    }//GEN-LAST:event_txtLedgerAlter_NameFocusGained

    private void txtLedgerAlter_AliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_AliasFocusGained
        // TODO add your handling code here:
        txtLedgerAlter_Alias.selectAll();
    }//GEN-LAST:event_txtLedgerAlter_AliasFocusGained

    private void txtLedgerAlter_AddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_AddressFocusGained
        // TODO add your handling code here:
        txtLedgerAlter_Address.selectAll();
    }//GEN-LAST:event_txtLedgerAlter_AddressFocusGained

    private void txtLedgerAlter_EmailIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLedgerAlter_EmailIdFocusGained
        // TODO add your handling code here:
        txtLedgerAlter_EmailId.selectAll();
    }//GEN-LAST:event_txtLedgerAlter_EmailIdFocusGained

    private void btnLedgerAlter_UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLedgerAlter_UpdateMouseClicked
        // TODO add your handling code here:
        btnLedgerAlter_UpdateActionPerformed(null);
    }//GEN-LAST:event_btnLedgerAlter_UpdateMouseClicked

    private void comboLedgerAlter_UnderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboLedgerAlter_UnderFocusLost
        // TODO add your handling code here:
        comboLedgerAlter_Under.hidePopup();
    }//GEN-LAST:event_comboLedgerAlter_UnderFocusLost

    public void gridlist() {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select ledger_name,ledger_id from tblledger order by ledger_name");
            int i = 0;
            arrayListLedgerData.clear();
            while (rs.next()) {
                LedgerData ld = new LedgerData();
                ld.setId(rs.getInt(2));
                ld.setName(rs.getString(1));
                arrayListLedgerData.add(ld);
            }
            i = 0;
            ledgergrid.removeAll();
            try {
                DefaultTableModel d = (DefaultTableModel) ledgergrid.getModel();
                String text = txtLedgerAlter_grid.getText().trim();
                int size = txtLedgerAlter_grid.getText().trim().length();
                d.setRowCount(0);
                for (i = 0; i < arrayListLedgerData.size(); i++) {
                    String record = arrayListLedgerData.get(i).getName();
                    int id = arrayListLedgerData.get(i).getId();
                    if (record.length() >= size) {
                        int rows = d.getRowCount();
                        rows++;
                        d.setRowCount(rows);
                        d.setColumnCount(2);
                        ledgergrid.setValueAt(record, rows - 1, 0);
                        ledgergrid.setValueAt(id, rows - 1, 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Ledger_Alter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLedgerAlter1;
    private javax.swing.JPanel PanelLedgerAlter2;
    private javax.swing.JPanel PanelLedgerAlter3;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLedgerAlter_Group;
    private javax.swing.JButton btnLedgerAlter_Update;
    private javax.swing.JComboBox comboLedgerAlter_Under;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ledgergrid;
    private javax.swing.JRadioButton optCredit;
    private javax.swing.JRadioButton optDebit;
    private javax.swing.JFormattedTextField txtCreditLimit;
    private javax.swing.JTextField txtLedgerAlter_Address;
    private javax.swing.JTextField txtLedgerAlter_Alias;
    private javax.swing.JFormattedTextField txtLedgerAlter_Balance;
    private javax.swing.JTextField txtLedgerAlter_ContactNo;
    private javax.swing.JTextField txtLedgerAlter_EmailId;
    private javax.swing.JTextField txtLedgerAlter_IncomeTax;
    private javax.swing.JTextField txtLedgerAlter_Name;
    private javax.swing.JTextField txtLedgerAlter_SaleTax;
    private javax.swing.JTextField txtLedgerAlter_grid;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboLedgerAlter_Under.setModel(mdl);
        comboLedgerAlter_Under.setSelectedIndex(-1);
        comboLedgerAlter_Under.setPopupVisible(true);
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
            if (str.equals(comboLedgerAlter_Under.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }
}