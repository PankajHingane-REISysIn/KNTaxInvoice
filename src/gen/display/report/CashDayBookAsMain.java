package gen.display.report;

import gen.account.group.GroupAlterFromDetails;
import gen.account.group.Group_Create;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.payment.PaymentForm;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.receipt.ReceiptForm;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import gen.other.print.PrinterSettings;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class CashDayBookAsMain extends javax.swing.JInternalFrame {

    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static int creditSalesIndex = 0, debitPaymentIndex = 0;
    static int debitPurchaseIndex = 0, creditReceiptIndex = 0;
    static double total_purchase, total_receipt, total_sales, total_payment;
    static int backAction = 0;
    String date = "";
    // private final JTextField tf;
    //   private final JTextField tf1;
    private final JTextField tfDatePicker;
    private boolean hide_flag = false;

    public CashDayBookAsMain(String s) {
        setClosable(true);
        initComponents();
        dat.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tfDatePicker = (JTextField) dat.getComponent(1);
        System.out.println(tfDatePicker.toString());



        //   tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
                    btnShowActionPerformed(null);
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btnBackActionPerformed(null);
                }
            }

            @Override
            public void keyTyped(final KeyEvent e) {
                System.out.println("Key pressed");
                int code = e.getKeyCode();
                System.out.println("code" + code);
                if (code == KeyEvent.VK_ENTER) {
                    System.out.println("Enter");

                }
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int code = e.getKeyCode();
                        System.out.println("code" + code);
                        if (code == KeyEvent.VK_ENTER) {
                            System.out.println("Enter");
                        } else if (code == KeyEvent.VK_ESCAPE) {
                            System.out.println("Escape");
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            System.out.println("Right");
                        }
                    }
                });
            }
        });

        tfDatePicker.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePicker.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dat = new com.toedter.calendar.JDateChooser();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        panelDebit = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCredit = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDebit = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelTotalCredit = new javax.swing.JLabel();
        labelTotalDebit = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelOpeningBalance = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelClosingBalance = new javax.swing.JLabel();
        labelCType = new javax.swing.JLabel();
        labelOType2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelOType = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        combine = new javax.swing.JTable();

        setTitle(org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.title")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1558, 713));
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel1.text")); // NOI18N

        dat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnShow.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnShow, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.btnShow.text")); // NOI18N
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });
        btnShow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnShowKeyPressed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPrint, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.btnPrint.text")); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        btnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPrintKeyPressed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnBack, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.btnBack.text")); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        btnBack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBackKeyPressed(evt);
            }
        });

        panelDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableCredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "","Particulars","VCH No","Amount","Weight","transID"
            }
        ));
        tableCredit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCreditMouseClicked(evt);
            }
        });
        tableCredit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableCreditKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableCredit);

        tableDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableDebit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "","Particulars","VCH No","Amount","Weight","transID"
            }
        ));
        tableDebit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDebitMouseClicked(evt);
            }
        });
        tableDebit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableDebitKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableDebit);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel7.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel6.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel3.text")); // NOI18N

        labelTotalCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelTotalCredit, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelTotalCredit.text")); // NOI18N

        labelTotalDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelTotalDebit, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelTotalDebit.text")); // NOI18N

        javax.swing.GroupLayout panelDebitLayout = new javax.swing.GroupLayout(panelDebit);
        panelDebit.setLayout(panelDebitLayout);
        panelDebitLayout.setHorizontalGroup(
            panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDebitLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(panelDebitLayout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(235, 235, 235))
            .addGroup(panelDebitLayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        panelDebitLayout.setVerticalGroup(
            panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDebitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(labelTotalDebit))
                    .addComponent(labelTotalCredit)))
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel5.text")); // NOI18N

        labelOpeningBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelOpeningBalance, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelOpeningBalance.text")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel8.text")); // NOI18N

        labelClosingBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelClosingBalance, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelClosingBalance.text")); // NOI18N

        labelCType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelCType, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelCType.text")); // NOI18N

        labelOType2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelOType2, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelOType2.text")); // NOI18N

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShow)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelOpeningBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelClosingBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(137, 137, 137)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCType, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelOType2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))))
                .addGap(415, 415, 415))
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(615, 615, 615)
                        .addComponent(jLabel1))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(panelDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnPrint)
                        .addComponent(btnBack))
                    .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelOpeningBalance)
                    .addComponent(labelOType2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelClosingBalance)
                    .addComponent(labelCType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 70, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(labelOType, org.openide.util.NbBundle.getMessage(CashDayBookAsMain.class, "CashDayBookAsMain.labelOType.text")); // NOI18N

        combine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "`","Particulars","VCH No","Amount","Weight","``","`Particulars","`VCH No","`Amount","`Weight"
            }
        ));
        jScrollPane3.setViewportView(combine);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(485, 485, 485)
                    .addComponent(jLabel4)
                    .addContainerGap(977, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(415, 415, 415)
                    .addComponent(labelOType, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addGap(416, 416, 416)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(328, 328, 328)
                    .addComponent(jLabel4)
                    .addContainerGap(343, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(328, 328, 328)
                    .addComponent(labelOType)
                    .addContainerGap(343, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        if (dat.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat.requestFocus();
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);

            loadSalesEntry();
            loadReceiptEntry();



            loadReceiptEntry();


            labelTotalCredit.setText("" + (total_sales + total_receipt));



            loadPurchaseEntry();
            loadPaymentEntry();

            labelTotalDebit.setText("" + (total_purchase + total_payment));


            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                String dateNow = f.format(dat.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();




                //--------find if ledger is under Cash In Hand--------
                long g_id = 0;
                q = "select group_id from tblgroup where group_name='Cash In Hand'";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    g_id = rs1.getLong("group_id");
                }
                rs1.close();

                ArrayList<Long> cash_ids = new ArrayList<Long>();
                int ind = 0;
                cash_ids.add(g_id);
                int flg = 0;
                do {
                    q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        cash_ids.add(rs.getLong("group_id"));
                    }
                    rs.close();
                    if (ind == cash_ids.size() - 1) {
                        flg = 1;
                    }
                    ind++;
                } while (flg == 0);


                String temp_group_under_id = "";
                i = 0;
                while (i < cash_ids.size()) {
                    temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
                    i++;
                }

                String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);
                //----------------------------------------------------



                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=6)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();

                //-------------new query----------------
                //q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=1)";
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();
                //--------------------------------------

                //System.out.println(cr);

                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=3)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }

                //--------------new query-------------------
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=2) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }
                //------------------------------------------

                long cash_ledger_id = 0;
                q = "select ledger_id from tblledger where ledger_name='Cash'";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    cash_ledger_id = rs1.getLong("ledger_id");
                }
                rs1.close();

                //-----------=------------------------------------------
                q = "select ledger_openingBalance from tblledger where ledger_id=" + cash_ledger_id + "";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    cr = cr + rs1.getDouble("ledger_openingBalance");
                }
                rs1.close();
                //System.out.println(d);            
                if (cr >= d) {
                    o = cr - d;
                    labelOpeningBalance.setText("" + df.format(Math.abs(o)));
                    labelOType.setText("Credit");
                } else {
                    o = d - cr;
                    labelOpeningBalance.setText("" + df.format(Math.abs(o)));
                    labelOType.setText("Debit");
                }

                //----------------------calculate closing balance-----------------------------
                o = Double.parseDouble(labelOpeningBalance.getText());
                cr = Double.parseDouble(labelTotalCredit.getText());
                d = Double.parseDouble(labelTotalDebit.getText());
                double cl = 00f;

                if (labelOType.getText().equalsIgnoreCase("Credit")) {
                    cl = o + cr - d;
                    labelClosingBalance.setText("" + df.format(Math.abs(cl)));
                    if (cl < 0) {
                        labelCType.setText("Debit");
                    } else {
                        labelCType.setText("Credit");
                    }
                } else {
                    cl = o + d - cr;
                    labelClosingBalance.setText("" + df.format(Math.abs(cl)));
                    if (cl > 0) {
                        labelCType.setText("Debit");
                    } else {
                        labelCType.setText("Credit");
                    }
                }
                //---------------------------------------------------------------------------

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }





    }//GEN-LAST:event_btnShowActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        System.out.println("DayBook---------------->>btnPrintActionPerformed------->>printer variables: Direct print:" + PrinterSettings.flagDirectPrint + " Page Format:" + PrinterSettings.flagPrintPageFormat + " Page Size:" + PrinterSettings.flagPrintPageSize);

        createTable();
        Map parameter = new HashMap();

        String TotalCreditAmount = labelTotalCredit.getText();
        String TotalDebiAmount = labelTotalDebit.getText();
        //  System.out.println(""+TotalAmountValue);

        String OpeningBalance = labelOpeningBalance.getText();
        String ClosingBalance = labelClosingBalance.getText();

        String OType = labelOType.getText();
        String CType = labelCType.getText();

        parameter.put("TotalAmountdebit", TotalDebiAmount);
        parameter.put("TotalAmountCredit", TotalCreditAmount);

        parameter.put("OpeningBalanceValue", OpeningBalance);
        parameter.put("ClosingBalanceValue", ClosingBalance);
        parameter.put("OpeningDebit", OType);
        parameter.put("OpeningCredit", CType);
        PrintAllReport.printTableDaybookDemo(parameter, new JRTableModelDataSource(combine.getModel()));
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        try {
            MainClass mainClass = new MainClass();
            mainClass.menuselection(3);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        if (backAction == 2) {
            tfDatePicker.setText(date);
            btnShowActionPerformed(null);
        } else {
            Calendar currentDate = Calendar.getInstance();
            dat.setDate(currentDate.getTime());

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(6);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(6);

//        
//                loadSalesEntry();
//        loadReceiptEntry();
//        labelTotalCredit.setText(""+(total_sales+total_receipt));
//        
//        loadPurchaseEntry();
//        loadPaymentEntry();
//        labelTotalDebit.setText(""+(total_purchase+total_payment));



            tableModel = (DefaultTableModel) combine.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(12);

            combine.setVisible(false);
            btnShow.setMnemonic(KeyEvent.VK_S);
            btnPrint.setMnemonic(KeyEvent.VK_P);
            btnBack.setMnemonic(KeyEvent.VK_B);

            btnShowActionPerformed(null);

            //    scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

            dat.requestFocus();

            //tableCredit.setGridColor(Color.red);
            tableCredit.setShowGrid(true);

            //tableDebit.setGridColor(Color.red);
            tableDebit.setShowGrid(true);

            if (tableCredit.getRowCount() == 0) {
                labelTotalCredit.setText("0");
            }
            if (tableDebit.getRowCount() == 0) {
                labelTotalDebit.setText("0");
            }

        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        backAction = 2;
        date = tfDatePicker.getText();
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        backAction = 1;
        Calendar currentDate = Calendar.getInstance();
        dat.setDate(currentDate.getTime());

        DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(6);

        tableModel = (DefaultTableModel) tableDebit.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(6);

        tableModel = (DefaultTableModel) combine.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(12);

        combine.setVisible(false);
        btnShow.setMnemonic(KeyEvent.VK_S);
        btnPrint.setMnemonic(KeyEvent.VK_P);
        btnBack.setMnemonic(KeyEvent.VK_B);

        btnShowActionPerformed(null);

        // scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

        dat.requestFocus();

        //tableCredit.setGridColor(Color.red);
        tableCredit.setShowGrid(true);

        //tableDebit.setGridColor(Color.red);
        tableDebit.setShowGrid(true);

        // backAction=2;
    }//GEN-LAST:event_formInternalFrameOpened

    private void tableCreditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCreditKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tableCredit.getSelectedRow();
            if (tableCredit.getRowCount() > 0) {
                if (tableCredit.getValueAt(i, 0) == null) {
                    if (tableDebit.getRowCount() > 0) {
                        tableDebit.requestFocus();
                        tableDebit.changeSelection(0, 0, false, false);
                    } else {
                        tableCredit.changeSelection(0, 0, false, false);
                    }
                } else {
                    tableCreditMouseClicked(null);
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dat.requestFocus();
        }
    }//GEN-LAST:event_tableCreditKeyPressed

    private void tableCreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCreditMouseClicked
        // TODO add your handling code here:
        int row = tableCredit.getSelectedRow();
        if (tableCredit.getValueAt(row, 1) != null && tableCredit.getValueAt(row, 3) != null) {
            Connection conn;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select trans_typeIndex from tbltransactionmain where trans_id=" + Long.parseLong(tableCredit.getValueAt(row, 5).toString()) + "");
                if (rs.next()) {
                    if (rs.getInt("trans_typeIndex") == 1) //if sales
                    {
                        String id = tableCredit.getValueAt(tableCredit.getSelectedRow(), 2).toString();
                        SaleDTO saleDTO = new SaleDTO();
                        SaleForm s = new SaleForm("Sales", new Dimension(), saleDTO, true);
                        s.loadEditForm(id);
                        s.pack();
                        s.setVisible(true);
                        this.getParent().add(s);
                        this.getParent().setVisible(true);
                        Dimension desktopSize = this.getParent().getSize();
                        s.setSize(desktopSize);
                        s.setPreferredSize(desktopSize);
                        try {
                            s.setSelected(true);
                        } catch (java.beans.PropertyVetoException e) {
                        }
                        BasicInternalFrameUI uiBasicComponent = (BasicInternalFrameUI) s.getUI();

                        Component north = uiBasicComponent.getNorthPane();
                        MouseMotionListener[] actions =
                                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    } else if (rs.getInt("trans_typeIndex") == 6) //if receipt
                    {
                        String id = tableCredit.getValueAt(tableCredit.getSelectedRow(), 4).toString();
                        ReceiptDTO receiptDTO = new ReceiptDTO();
                        ReceiptForm r = new ReceiptForm(receiptDTO, true);
                        r.loadEditForm(id);
                        r.pack();
                        r.setVisible(true);
                        this.getParent().add(r);
                        this.getParent().setVisible(true);
                        Dimension desktopSize = this.getParent().getSize();
                        r.setSize(desktopSize);
                        r.setPreferredSize(desktopSize);
                        try {
                            r.setSelected(true);
                        } catch (java.beans.PropertyVetoException e) {
                            e.printStackTrace();
                        }
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) r.getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions =
                                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(CashDayBookAsMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableCreditMouseClicked

    private void tableDebitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDebitKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tableDebit.getSelectedRow();
            if (tableDebit.getRowCount() > 0) {
                if (tableDebit.getValueAt(i, 0) == null) {
                    dat.requestFocus();
                } else {
                    tableCreditMouseClicked(null);
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tableCredit.requestFocus();
            tableCredit.changeSelection(0, 0, false, false);
        }
    }//GEN-LAST:event_tableDebitKeyPressed

    private void tableDebitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDebitMouseClicked
        // TODO add your handling code here:
        int row = tableDebit.getSelectedRow();
        if (tableDebit.getValueAt(row, 1) != null && tableDebit.getValueAt(row, 3) != null) {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select trans_typeIndex from tbltransactionmain where trans_id=" + Long.parseLong(tableDebit.getValueAt(row, 5).toString()) + "");
                if (rs.next()) {
                    if (rs.getInt("trans_typeIndex") == 2) //if purchase
                    {
                        String id = tableDebit.getValueAt(tableDebit.getSelectedRow(), 2).toString();
                        PurchaseDTO purchaseDTO = new PurchaseDTO();
                        PurchaseForm p = new PurchaseForm(purchaseDTO, true);
                        p.loadEditForm(id);
                        p.pack();
                        p.setVisible(true);
                        this.getParent().add(p);
                        this.getParent().setVisible(true);
                        Dimension desktopSize = this.getParent().getSize();
                        p.setSize(desktopSize);
                        p.setPreferredSize(desktopSize);
                        try {
                            p.setSelected(true);
                        } catch (java.beans.PropertyVetoException e) {
                            e.printStackTrace();
                        }
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) p.getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions =
                                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    } else if (rs.getInt("trans_typeIndex") == 3) //if payment
                    {
                        String id = tableDebit.getValueAt(tableDebit.getSelectedRow(), 2).toString();
                        PaymentDTO paymentDTO = new PaymentDTO();
                        PaymentForm p = new PaymentForm(paymentDTO, true);
                        p.loadEditForm(id);
                        p.pack();
                        p.setVisible(true);
                        this.getParent().add(p);
                        this.getParent().setVisible(true);
                        Dimension desktopSize = this.getParent().getSize();
                        p.setSize(desktopSize);
                        p.setPreferredSize(desktopSize);
                        try {
                            p.setSelected(true);
                        } catch (java.beans.PropertyVetoException e) {
                            e.printStackTrace();
                        }
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) p.getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions =
                                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(CashDayBookAsMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableDebitMouseClicked

    private void btnShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnShowKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnShowActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfDatePicker.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            tfDatePicker.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableCredit.getRowCount() > 0) {
                tableCredit.requestFocus();
                tableCredit.changeSelection(0, 0, false, false);
            } else {
                btnShow.requestFocus();
            }
        }
    }//GEN-LAST:event_btnShowKeyPressed

    private void btnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPrintKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPrintActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnShow.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnShow.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableCredit.getRowCount() > 0) {
                tableCredit.requestFocus();
                tableCredit.changeSelection(0, 0, false, false);
            } else {
                btnShow.requestFocus();
            }
        }
    }//GEN-LAST:event_btnPrintKeyPressed

    private void btnBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBackKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }
    }//GEN-LAST:event_btnBackKeyPressed

    public void loadSalesEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = f.format(dat.getDate());
            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Debit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            int k = 0;    //for putting amount with table header
            creditSalesIndex = 0;


            //--------find if ledger is under Cash In Hand--------
            long g_id = 0;
            q = "select group_id from tblgroup where group_name='Cash In Hand'";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("group_id");
            }

            ArrayList<Long> cash_ids = new ArrayList<Long>();
            int ind = 0;
            cash_ids.add(g_id);
            int flg = 0;
            do {
                q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
                ResultSet rs = st1.executeQuery(q);
                while (rs.next()) {
                    cash_ids.add(rs.getLong("group_id"));
                }
                if (ind == cash_ids.size() - 1) {
                    flg = 1;
                }
                ind++;
            } while (flg == 0);


            String temp_group_under_id = "";
            i = 0;
            while (i < cash_ids.size()) {
                temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
                i++;
            }

            String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);
            //----------------------------------------------------





            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=1)";
            rs2 = st1.executeQuery(q);
            i = 1;
            int flag_min_one_entry_present = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    tableModel.setRowCount(1);
                    tableModel.setColumnCount(6);
                    creditSalesIndex = 1;
                    tableCredit.setValueAt("Sales", k, 1);
                }
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in('" + group_under_id + "')";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    //Get Ledger name
                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableCredit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getDouble("totalQty"), row, 4);
                    }
                    rs4.close();

                    flag_min_one_entry_present = 1;
                    creditSalesIndex++;
                }

                //System.out.println("Middle sales index: "+debitSalesIndex);
            }
            //--------------------------------------------------------------
            creditSalesIndex += 2;
            int row = tableCredit.getRowCount();
            tableModel.setRowCount(row + 2);
            System.out.println("creditSalesIndex: " + creditSalesIndex);
            row = tableCredit.getRowCount();
            tableCredit.setValueAt("Total", row - 1, 2);

            //-------------------Count Total Creditted Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(0);
                total_sales = 0;
                creditSalesIndex = 0;
            } else {
                if (tableCredit.getRowCount() > 1) {
                    i = 1;
                    double qty = 0;
                    while (i < creditSalesIndex) {
                        if (tableCredit.getValueAt(i, 3) != null) {
                            qty = qty + Double.parseDouble(tableCredit.getValueAt(i, 3).toString());
                        }
                        i++;
                    }
                    total_sales = qty;
                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
                    tableCredit.setValueAt(df.format(qty), k, 0);
                }
            }
            //--------------------------------------------------------------------------------

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //System.out.println("Final index: "+creditPurchaseIndex);
    }

    public void loadReceiptEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=6)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;

            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) //set sub header
                {
                    creditReceiptIndex = tableCredit.getRowCount();
                    if (creditReceiptIndex == 0) {
                        tableModel.setRowCount(1);
                        tableCredit.setValueAt("Receipt", creditReceiptIndex, 1);
                        creditReceiptIndex++;
                    } else {
                        creditReceiptIndex += 2;
                        tableModel.setRowCount(creditReceiptIndex);
                        tableCredit.setValueAt("Receipt", creditReceiptIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    //Get Ledger name
                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableCredit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //qty will be null in receipt and payment
                    tableCredit.setValueAt("-", row, 4);

                    flag_min_one_entry_present = 1;
                }
                i++;
                creditReceiptIndex++;
            }
            //--------------------------------------------------------------
            creditReceiptIndex += 2;
            int row = tableCredit.getRowCount();
            tableModel.setRowCount(row + 2);
            row = tableCredit.getRowCount();
            //System.out.println("Row-1:: "+(row-1));
            tableCredit.setValueAt("Total", row - 1, 2);
            //System.out.println("Final Rec index: "+creditReceiptIndex);

            //-------------------Count Total Creditted Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(creditSalesIndex);
                total_receipt = 0;
                creditReceiptIndex = 0;
            } else {
                if (tableCredit.getRowCount() > 0) {
                    int j = 0;
                    if (creditSalesIndex == 0) {
                        j = 0;
                    } else {
                        j = creditSalesIndex + 2;
                    }

                    double qty = 0;
                    while (j < creditReceiptIndex - 1) {
                        if (tableCredit.getValueAt(j, 3) != null) {
                            qty = qty + Double.parseDouble(tableCredit.getValueAt(j, 3).toString());
                        }
                        j++;
                    }
                    total_receipt = qty;
                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
                    if (creditSalesIndex == 0) {
                        tableCredit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableCredit.setValueAt(df.format(qty), creditSalesIndex + 1, 0);
                    }
                }
            }
            //------------------------------------------------------------------


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadPurchaseEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Debit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setColumnCount(6);
            debitPurchaseIndex = 0;

            //--------find if ledger is under Cash In Hand--------
            long g_id = 0;
            q = "select group_id from tblgroup where group_name='Cash In Hand'";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("group_id");
            }

            ArrayList<Long> cash_ids = new ArrayList<Long>();
            int ind = 0;
            cash_ids.add(g_id);
            int flg = 0;
            do {
                q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
                ResultSet rs = st1.executeQuery(q);
                while (rs.next()) {
                    cash_ids.add(rs.getLong("group_id"));
                }
                if (ind == cash_ids.size() - 1) {
                    flg = 1;
                }
                ind++;
            } while (flg == 0);


            String temp_group_under_id = "";
            i = 0;
            while (i < cash_ids.size()) {
                temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
                i++;
            }

            String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);
            //----------------------------------------------------



            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=2)";
            rs2 = st1.executeQuery(q);
            i = 1;
            int flag_min_one_entry_present = 0;
            int k = 0;    //for putting amount with table header
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    tableModel.setRowCount(1);
                    tableModel.setColumnCount(6);
                    debitPurchaseIndex = 1;
                    tableDebit.setValueAt("Purchase", k, 1);
                }
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in('" + group_under_id + "')";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    //tableModel = (DefaultTableModel) tableDebit.getModel();
                    tableModel.setRowCount(row + 1);

                    //Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getDouble("totalQty"), row, 4);
                    }
                    rs4.close();
                    flag_min_one_entry_present = 1;
                    debitPurchaseIndex++;
                }
            }
            //--------------------------------------------------------------

            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitPurchaseIndex += 2;
            //System.out.println("Final Purchase index: "+creditPurchaseIndex);
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            //-------------------Count Total Debitted Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(0);
                total_purchase = 0;
                debitPurchaseIndex = 0;
            } else {
                if (tableDebit.getRowCount() > 1) {
                    i = 1;
                    double qty = 0;
                    while (i < debitPurchaseIndex - 1) {
                        if (tableDebit.getValueAt(i, 3) != null) {
                            qty = qty + Double.parseDouble(tableDebit.getValueAt(i, 3).toString());
                        }
                        i++;
                    }
                    total_purchase = qty;
                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
                    tableDebit.setValueAt(df.format(qty), k, 0);
                }
            }
            //--------------------------------------------------------------------------------


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Ful n Final Purchase index: " + debitPurchaseIndex);
    }

    public void loadPaymentEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=3)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    debitPaymentIndex = tableDebit.getRowCount();
                    if (debitPaymentIndex == 0) {
                        tableModel.setRowCount(1);
                        tableDebit.setValueAt("Payment", debitPaymentIndex, 1);
                        debitPaymentIndex++;
                    } else {
                        debitPaymentIndex += 2;
                        tableModel.setRowCount(debitPaymentIndex);
                        tableDebit.setValueAt("Payment", debitPaymentIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    //Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    //Debitted Amount
                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //qty will be null in receipt and payment
                    tableDebit.setValueAt("-", row, 4);
                }
                flag_min_one_entry_present = 1;
                debitPaymentIndex++;
            }
            //--------------------------------------------------------------
            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitPaymentIndex += 2;
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            //-------------------Count Total Debitted Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(debitPurchaseIndex);
                total_payment = 0;
                debitPaymentIndex = 0;
            } else {
                if (tableDebit.getRowCount() > 1) {
                    int j = 0;
                    if (debitPurchaseIndex == 0) {
                        j = 0;
                    } else {
                        j = debitPurchaseIndex + 2;
                    }

                    double qty = 0;
                    while (j < debitPaymentIndex - 1) {
                        if (tableDebit.getValueAt(j, 3) != null) {
                            qty = qty + Double.parseDouble(tableDebit.getValueAt(j, 3).toString());
                        }
                        j++;
                    }
                    total_payment = qty;
                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
                    if (debitPurchaseIndex == 0) {
                        tableDebit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableDebit.setValueAt(df.format(qty), debitPurchaseIndex + 1, 0);
                    }
                }
            }
            //------------------------------------------------------------------


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTable() {
        DefaultTableModel tableModel = (DefaultTableModel) combine.getModel();
        tableModel.setRowCount(0);

        Integer totalRowCount = 0;
        if (tableCredit.getRowCount() > tableDebit.getRowCount()) {
            totalRowCount = tableCredit.getRowCount();
        } else {
            totalRowCount = tableDebit.getRowCount();
        }

        for (int i = 0; i < totalRowCount; i++) {
            int row = combine.getRowCount();
            tableModel.setRowCount(row + 1);
            int k = 0;
            if (i < tableCredit.getRowCount()) {
                //insert credit side
                k = 0;
                for (int j = 0; j < tableCredit.getColumnCount() - 1; j++) {
//                   if(k==5)
//                   {
//                       
//                   }
//                   else
//                   {
                    combine.setValueAt(tableCredit.getValueAt(i, j), i, k);
                    System.out.println(tableCredit.getValueAt(i, j));
//                   }
                    k++;
                }

            }

            if (i < tableDebit.getRowCount()) {
                //insert debit side
                k = 5;
                for (int j = 0; j < tableDebit.getColumnCount() - 1; j++) {
//                   if(k==11)
//                   {
//                    
//                   }
//                   else
//                   {
                    combine.setValueAt(tableDebit.getValueAt(i, j), i, k);
                    System.out.println(tableDebit.getValueAt(i, j));
//                   }
                    k++;
                }
            }
        }


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private javax.swing.JTable combine;
    private com.toedter.calendar.JDateChooser dat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelCType;
    private javax.swing.JLabel labelClosingBalance;
    private javax.swing.JLabel labelOType;
    private javax.swing.JLabel labelOType2;
    private javax.swing.JLabel labelOpeningBalance;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JPanel panelDebit;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTable tableCredit;
    private javax.swing.JTable tableDebit;
    // End of variables declaration//GEN-END:variables
}
