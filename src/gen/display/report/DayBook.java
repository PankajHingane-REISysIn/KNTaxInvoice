package gen.display.report;

import gen.ImpExp.TagsHelper1;
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
import gen.dto.Constants;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import gen.other.print.PrinterSettings;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class DayBook extends javax.swing.JInternalFrame {
//    Database db=new Database();     //Object of class for database 

    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static int creditSalesIndex = 0, debitPaymentIndex = 0;
    static int debitPurchaseIndex = 0, creditReceiptIndex = 0;
    static double total_purchase = 0, total_receipt = 0, total_sales = 0, total_payment = 0;
    static int debitNonCashSalesIndex = 0, creditNonCashPurchaseIndex = 0;
    static double total_nonCash_sale, total_noncashpurchase;
    static int backAction = 0;
    String date = "";
    Set<String> saleIDSet = new TreeSet<String>();
    Set<String> purchaseIDSet = new TreeSet<String>();
    Set<String> receiptIDSet = new TreeSet<String>();
    Set<String> paymentIDSet = new TreeSet<String>();
    String path = "";
    // private final JTextField tf;
    //   private final JTextField tf1;
    private final JTextField tfDatePicker;
    private boolean hide_flag = false;

    /**
     *
     * @param s
     */
    public DayBook(String s) {
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpaneMain = new javax.swing.JScrollPane();
        panelMain = new javax.swing.JPanel();
        panelDebit = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCredit = new javax.swing.JTable()
        { public boolean isCellEditable(int row, int column) {
            return false;
        };
    }
    ;
    jLabel2 = new javax.swing.JLabel();
    labelTotalCredit = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    tableDebit = new javax.swing.JTable(){
        public boolean isCellEditable(int row, int column) {
            return false;
        };
    };
    jLabel3 = new javax.swing.JLabel();
    labelTotalDebit = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    openingBL_plus_TotalAmount = new javax.swing.JLabel();
    dat = new com.toedter.calendar.JDateChooser();
    btnShow = new javax.swing.JButton();
    btnPrint = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    labelOpeningBalance = new javax.swing.JLabel();
    labelClosingBalance = new javax.swing.JLabel();
    btnBack = new javax.swing.JButton();
    labelOType = new javax.swing.JLabel();
    labelCType = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    combine = new javax.swing.JTable();
    labelPending = new javax.swing.JLabel();
    lablePendingBal = new javax.swing.JLabel();
    labclosingwithoutpending = new javax.swing.JLabel();
    tbnExport = new javax.swing.JButton();
    btnAdvanceDaybook = new javax.swing.JButton();

    setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
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

    scrollpaneMain.setPreferredSize(new java.awt.Dimension(1016, 1191));

    panelMain.setPreferredSize(new java.awt.Dimension(850, 400));

    panelDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    tableCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    tableCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    tableCredit.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {}
        },
        new String [] {
            "","Particulars","VCH No","Amount","Quantity","transID"
        }
    ));
    tableCredit.setGridColor(new java.awt.Color(0, 0, 0));
    tableCredit.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableCredit.getTableHeader().setResizingAllowed(false);
    tableCredit.getTableHeader().setReorderingAllowed(false);
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
    jScrollPane2.setViewportView(tableCredit);

    jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel2.setText("Total Amount");

    labelTotalCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelTotalCredit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labelTotalCredit.setText("jLabel3");

    jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel7.setText("Credit");

    tableDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    tableDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    tableDebit.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {}
        },
        new String [] {
            "","Particulars","VCH No","Amount","Quantity","transID"
        }
    ));
    tableDebit.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableDebit.getTableHeader().setResizingAllowed(false);
    tableDebit.getTableHeader().setReorderingAllowed(false);
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
    jScrollPane3.setViewportView(tableDebit);

    jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel3.setText("Total Amount");

    labelTotalDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelTotalDebit.setText("jLabel4");

    jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel6.setText("Debit");

    openingBL_plus_TotalAmount.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    openingBL_plus_TotalAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    openingBL_plus_TotalAmount.setText("jLabel8");

    javax.swing.GroupLayout panelDebitLayout = new javax.swing.GroupLayout(panelDebit);
    panelDebit.setLayout(panelDebitLayout);
    panelDebitLayout.setHorizontalGroup(
        panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDebitLayout.createSequentialGroup()
            .addGap(205, 205, 205)
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6)
            .addGap(207, 207, 207))
        .addGroup(panelDebitLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDebitLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(openingBL_plus_TotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(109, 109, 109))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDebitLayout.createSequentialGroup()
                    .addComponent(jScrollPane2)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())))
    );
    panelDebitLayout.setVerticalGroup(
        panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelDebitLayout.createSequentialGroup()
            .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jLabel7))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(labelTotalCredit)
                .addComponent(jLabel3)
                .addComponent(labelTotalDebit)
                .addComponent(openingBL_plus_TotalAmount))
            .addContainerGap())
    );

    dat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    dat.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            datKeyPressed(evt);
        }
    });

    btnShow.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    btnShow.setText("Show");
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
    btnPrint.setText("Print");
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

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    jLabel1.setText("DayBook");

    jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel4.setText("Opening Balance");

    jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel5.setText("Closing Balance");

    labelOpeningBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelOpeningBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labelOpeningBalance.setText("jLabel6");

    labelClosingBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelClosingBalance.setText("jLabel6");

    btnBack.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    btnBack.setText("Back");
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

    labelOType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelOType.setText("jLabel6");

    labelCType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelCType.setText("jLabel6");

    combine.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "`","Particulars","VCH No","Amount","Quantity","``","`Particulars","`VCH No","`Amount","`Quantity"
        }
    ));
    jScrollPane1.setViewportView(combine);

    labelPending.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelPending.setText("Pending");

    lablePendingBal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    lablePendingBal.setText("jLabel9");

    labclosingwithoutpending.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labclosingwithoutpending.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labclosingwithoutpending.setText("jLabel9");

    tbnExport.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    tbnExport.setText("Export");
    tbnExport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            tbnExportActionPerformed(evt);
        }
    });
    tbnExport.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            tbnExportKeyPressed(evt);
        }
    });

    btnAdvanceDaybook.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    btnAdvanceDaybook.setText("Advance Daybook");
    btnAdvanceDaybook.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnAdvanceDaybookActionPerformed(evt);
        }
    });
    btnAdvanceDaybook.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            btnAdvanceDaybookKeyPressed(evt);
        }
    });

    javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
    panelMain.setLayout(panelMainLayout);
    panelMainLayout.setHorizontalGroup(
        panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMainLayout.createSequentialGroup()
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainLayout.createSequentialGroup()
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMainLayout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addComponent(labelPending)
                            .addGap(61, 61, 61))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lablePendingBal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelClosingBalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMainLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelOpeningBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addComponent(labclosingwithoutpending, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelMainLayout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelCType))
                                .addGroup(panelMainLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(485, 485, 485)
                                    .addComponent(labelOType))))
                        .addComponent(panelDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelMainLayout.createSequentialGroup()
                    .addGap(382, 382, 382)
                    .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addComponent(btnShow)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnPrint)
                    .addGap(6, 6, 6)
                    .addComponent(btnBack)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnAdvanceDaybook)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tbnExport)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
        .addGroup(panelMainLayout.createSequentialGroup()
            .addGap(635, 635, 635)
            .addComponent(jLabel1)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panelMainLayout.setVerticalGroup(
        panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMainLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addGap(14, 14, 14)
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnShow)
                    .addComponent(btnBack)
                    .addComponent(tbnExport)
                    .addComponent(btnAdvanceDaybook)))
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMainLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPending)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lablePendingBal)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelClosingBalance)
                    .addGap(29, 29, 29)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMainLayout.createSequentialGroup()
                            .addComponent(labelOType)
                            .addGap(0, 0, 0)
                            .addComponent(labelCType))
                        .addGroup(panelMainLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
                .addGroup(panelMainLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(panelDebit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOpeningBalance)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labclosingwithoutpending)
                        .addComponent(jLabel5))
                    .addGap(28, 28, 28))))
    );

    scrollpaneMain.setViewportView(panelMain);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(scrollpaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1542, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(scrollpaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableCreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCreditMouseClicked
        int row = tableCredit.getSelectedRow();
        if (tableCredit.getValueAt(row, 1) != null && tableCredit.getValueAt(row, 3) != null) {
            Connection conn;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select trans_typeIndex from tbltransactionmain where trans_id=" + Long.parseLong(tableCredit.getValueAt(row, 5).toString()) + "");
                if (rs.next()) {
                    if (rs.getInt("trans_typeIndex") == 2) //if purchase
                    {
                        String id = tableCredit.getValueAt(tableCredit.getSelectedRow(), 2).toString();
                        PurchaseDTO purchaseDTO = new PurchaseDTO();
                        PurchaseForm p = new PurchaseForm(purchaseDTO, true);
                        p.loadEditForm(id);
                        p.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(p);
                        Dimension desktopSize = getDesktopPane().getSize();
                        p.setSize(desktopSize);
                        p.setPreferredSize(desktopSize);
                        p.setVisible(!p.isVisible());
//                        p.setVisible(true);
//                        this.getParent().add(p);
//                        this.getParent().setVisible(true);
//                        Dimension desktopSize = this.getParent().getSize();
//                        p.setSize(desktopSize);
//                        p.setPreferredSize(desktopSize);
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
                    } else if (rs.getInt("trans_typeIndex") == 1) //if sales
                    {
                        String id = tableCredit.getValueAt(tableCredit.getSelectedRow(), 2).toString();
                        SaleDTO saleDTO = new SaleDTO();
                        SaleForm s = new SaleForm("Sales", new Dimension(), saleDTO, true);
                        s.loadEditForm(id);
                        s.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(s);
                        Dimension desktopSize = getDesktopPane().getSize();
                        s.setSize(desktopSize);
                        s.setPreferredSize(desktopSize);
                        s.setVisible(!s.isVisible());
//                        s.setVisible(true);
//                        this.getParent().add(s);
//                        this.getParent().setVisible(true);
//                        Dimension desktopSize = this.getParent().getSize();
//                        s.setSize(desktopSize);
//                        s.setPreferredSize(desktopSize);
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
                        String id = tableCredit.getValueAt(tableCredit.getSelectedRow(), 2).toString();
                        ReceiptDTO receiptDTO = new ReceiptDTO();
                        ReceiptForm r = new ReceiptForm(receiptDTO, true);
                        r.loadEditForm(id);
                        r.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(r);
                        Dimension desktopSize = getDesktopPane().getSize();
                        r.setSize(desktopSize);
                        r.setPreferredSize(desktopSize);
                        r.setVisible(!r.isVisible());
//                        r.setVisible(true);
//                        this.getParent().add(r);
//                        this.getParent().setVisible(true);
//                        Dimension desktopSize = this.getParent().getSize();
//                        r.setSize(desktopSize);
//                        r.setPreferredSize(desktopSize);
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
                Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_tableCreditMouseClicked

    private void tableCreditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCreditKeyPressed
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

    private void tableDebitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDebitMouseClicked
        int row = tableDebit.getSelectedRow();
        if (tableDebit.getValueAt(row, 1) != null && tableDebit.getValueAt(row, 3) != null) {
            Connection conn = null;
            try {
                conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("select trans_typeIndex from tbltransactionmain where trans_id=" + Long.parseLong(tableDebit.getValueAt(row, 5).toString()) + "");
                if (rs.next()) {
                    if (rs.getInt("trans_typeIndex") == 1) //if sales
                    {
                        String id = tableDebit.getValueAt(tableDebit.getSelectedRow(), 2).toString();
                        SaleDTO saleDTO = new SaleDTO();
                        SaleForm s = new SaleForm("Sales", new Dimension(), saleDTO, true);
                        s.loadEditForm(id);
                        s.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(s);
                        Dimension desktopSize = getDesktopPane().getSize();
                        s.setSize(desktopSize);
                        s.setPreferredSize(desktopSize);
                        s.setVisible(!s.isVisible());
//                        s.setVisible(true);
//                        this.getParent().add(s);
//                        this.getParent().setVisible(true);
//                        Dimension desktopSize = this.getParent().getSize();
//                        s.setSize(desktopSize);
//                        s.setPreferredSize(desktopSize);
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
                    } else if (rs.getInt("trans_typeIndex") == 2) //if purchase
                    {
                        String id = tableDebit.getValueAt(tableDebit.getSelectedRow(), 2).toString();
                        PurchaseDTO purchaseDTO = new PurchaseDTO();
                        PurchaseForm p = new PurchaseForm(purchaseDTO, true);
                        p.loadEditForm(id);
                        p.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(p);
                        Dimension desktopSize = getDesktopPane().getSize();
                        p.setSize(desktopSize);
                        p.setPreferredSize(desktopSize);
                        p.setVisible(!p.isVisible());
//                        p.setVisible(true);
//                        this.getParent().add(p);
//                        this.getParent().setVisible(true);
//                        Dimension desktopSize = this.getParent().getSize();
//                        p.setSize(desktopSize);
//                        p.setPreferredSize(desktopSize);
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
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(p);
                        Dimension desktopSize = getDesktopPane().getSize();
                        p.setSize(desktopSize);
                        p.setPreferredSize(desktopSize);
                        p.setVisible(!p.isVisible());
//                        p.setVisible(true);
//                        this.getParent().add(p);
//                        this.getParent().setVisible(true);
//                        Dimension desktopSize = this.getParent().getSize();
//                        p.setSize(desktopSize);
//                        p.setPreferredSize(desktopSize);
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
                Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_tableDebitMouseClicked

    private void tableDebitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDebitKeyPressed
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

    private void datKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnShowActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
}//GEN-LAST:event_datKeyPressed

    public void loadSalesEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
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





            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=1)";
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
                // for mysql
//                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in('" + group_under_id + "')";
                // for HSQLDB
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in(" + group_under_id + ")";
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
                        saleIDSet.add(tableCredit.getValueAt(row, 2).toString());
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 3);

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

    //OLD FROM PLYWOOD
//    public void loadSalesEntry() {
//        try {
//            Connection conn = DatabaseConnection1.GetConnection();
//            Statement st1 = conn.createStatement();
//            Statement st3 = conn.createStatement();
//            Statement st4 = conn.createStatement();
//
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            String dateNow = f.format(dat.getDate());
//            DecimalFormat df = new DecimalFormat("#.##");
//
//            //-----------------------Table: Debit--------------------------------------------
//            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
//            int k = 0;    //for putting amount with table header
//            creditSalesIndex = 0;
//
//
//            //--------find if ledger is under Cash In Hand--------
//            long g_id = 0;
//            q = "select group_id from tblgroup where group_name='Cash In Hand'";
//            rs1 = st1.executeQuery(q);
//            if (rs1.next()) {
//                g_id = rs1.getLong("group_id");
//            }
//
//            ArrayList<Long> cash_ids = new ArrayList<Long>();
//            int ind = 0;
//            cash_ids.add(g_id);
//            int flg = 0;
//            do {
//                q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
//                ResultSet rs = st1.executeQuery(q);
//                while (rs.next()) {
//                    cash_ids.add(rs.getLong("group_id"));
//                }
//                if (ind == cash_ids.size() - 1) {
//                    flg = 1;
//                }
//                ind++;
//            } while (flg == 0);
//
//
//            String temp_group_under_id = "";
//            i = 0;
//            while (i < cash_ids.size()) {
//                temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
//                i++;
//            }
//
//            String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);
//            //----------------------------------------------------
//
//
//
//
//
//            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=1)";
//            rs2 = st1.executeQuery(q);
//            i = 1;
//            int flag_min_one_entry_present = 0;
//            while (rs2.next()) {
//                if (flag_min_one_entry_present == 0) {
//                    tableModel.setRowCount(1);
//                    tableModel.setColumnCount(6);
//                    creditSalesIndex = 1;
//                    tableCredit.setValueAt("Sales", k, 1);
//                }
//                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in('" + group_under_id + "')";
//                rs3 = st3.executeQuery(q);
//                if (rs3.next()) {
//                    int row = tableCredit.getRowCount();//Change Made by Sudeep:3-04-2013
//                    tableModel.setRowCount(row + 1);
//
//                    //Get Ledger name
//                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);
//
//                    //VCH No(Receipt No)
//                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
//                    rs4 = st4.executeQuery(q);
//                    if (rs4.next()) {
//                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
//                        saleIDSet.add(tableCredit.getValueAt(row, 2).toString());
//                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
//                    }
//                    rs4.close();
//
//                    //Debitted Amount
//                    tableCredit.setValueAt(rs2.getDouble("trans_amt"), row, 3);
//                    
//                    
//                    
//                    //Qty
//                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
//                    rs4 = st4.executeQuery(q);
//                    if (rs4.next()) {
//                        tableCredit.setValueAt(rs4.getDouble("totalQty"), row, 4);
//                    }
//                    rs4.close();
//
//                    flag_min_one_entry_present = 1;
//                    creditSalesIndex++;
//                }
//
//                //System.out.println("Middle sales index: "+debitSalesIndex);
//            }
//            //--------------------------------------------------------------
//            creditSalesIndex += 2;
//            int row = tableCredit.getRowCount();
//            tableModel.setRowCount(row + 2);
//            //System.out.println("creditSalesIndex: "+creditSalesIndex);
//            row = tableCredit.getRowCount();
//            tableCredit.setValueAt("Total", row - 1, 2);
//
//            //-------------------Count Total Creditted Amount--------------------
//            if (flag_min_one_entry_present == 0) {
//                tableModel.setRowCount(0);
//                total_sales = 0;
//                creditSalesIndex = 0;
//            } else {
//                if (tableCredit.getRowCount() > 1) {
//                    i = 1;
//                    double qty = 0;
//                    while (i < creditSalesIndex) {
//                        if (tableCredit.getValueAt(i, 3) != null) {
//                            qty = qty + Double.parseDouble(tableCredit.getValueAt(i, 3).toString());
//                        }
//                        i++;
//                    }
//                    total_sales = qty;
//                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
//                    System.out.println("SALE Credit >>>>>>>>>>>>>>>>>>"+df.format(qty)); 
//                    tableCredit.setValueAt(df.format(qty), k, 0);
//                }
//            }
//            //--------------------------------------------------------------------------------
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        //System.out.println("Final index: "+creditPurchaseIndex);
//    }
    public void loadNonCashSalesEntry() {
        //System.out.println("Entered into non cash sale");

//-----------------------------------------------------------------------

        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

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





            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;
            //q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+ dateNow +"' and trans_typeIndex=3)";
            //q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
            //q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+ dateNow +"' and a.trans_typeIndex=3)";
            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=1)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;
            while (rs2.next()) {
                System.out.println("rs2.getLong(\"trans_ledgerid\")---------------------->> " + rs2.getLong("trans_ledgerid"));
                System.out.println("flag_min_one_entry_present---------------------->> " + flag_min_one_entry_present);
                if (flag_min_one_entry_present == 0) {
                    debitNonCashSalesIndex = tableDebit.getRowCount();
                    if (debitNonCashSalesIndex == 0) {
                        tableModel.setRowCount(1);
                        tableDebit.setValueAt("Non-Cash Sales", debitNonCashSalesIndex, 1);
                        debitNonCashSalesIndex++;
                    } else {
                        debitNonCashSalesIndex += 2;
                        tableModel.setRowCount(debitNonCashSalesIndex);
                        tableDebit.setValueAt("Non-Cash Sales", debitNonCashSalesIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under not in (" + group_under_id + ")";
                rs3 = st3.executeQuery(q);
                System.out.println("flag_min_one_entry_present---------------------->> " + flag_min_one_entry_present);
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
                        saleIDSet.add(tableDebit.getValueAt(row, 2).toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    //Debitted Amount
                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //qty will be null in receipt and payment
                    tableDebit.setValueAt(" ", row, 4);
                    flag_min_one_entry_present = 1;


                    i++;
                    debitNonCashSalesIndex++;
                }
            }
            //--------------------------------------------------------------
            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitNonCashSalesIndex += 1;
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            //-------------------Count Total Debitted Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(debitPurchaseIndex);
                total_nonCash_sale = 0;
                debitNonCashSalesIndex = debitPurchaseIndex;
            } else {
                if (tableDebit.getRowCount() > 0) {
                    int j = 0;
                    if (debitPurchaseIndex == 0) {
                        j = 0;
                    } else {
                        j = debitPurchaseIndex + 2;
                    }

                    double qty = 0;
                    while (j < debitNonCashSalesIndex - 1) {
                        if (tableDebit.getValueAt(j, 3) != null) {
                            qty = qty + Double.parseDouble(tableDebit.getValueAt(j, 3).toString());
                        }
                        j++;
                    }
                    total_nonCash_sale = qty;
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

    //---------------------------------------------------------------
    /*        try 
     {
     Connection conn=database.GetConnection();
     Statement st1=conn.createStatement();
     Statement st3=conn.createStatement();
     Statement st4=conn.createStatement();
            
     SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
     String dateNow=f.format(dat.getDate());
     DecimalFormat df = new DecimalFormat("#.##");
            
     //-----------------------Table: Debit--------------------------------------------
     DefaultTableModel tableModel = (DefaultTableModel)tableDebit.getModel();
     int k=0;    //for putting amount with table header
     debitSalesIndex = 0;
            
            
     //--------find if ledger is under Cash In Hand--------
     long g_id = 0;
     q = "select group_id from tblgroup where group_name='Cash In Hand'";
     rs1 = st1.executeQuery(q);
     if (rs1.next()) 
     {
     g_id = rs1.getLong("group_id");
     }
                        
     ArrayList<Long> cash_ids = new ArrayList<Long>();
     int ind = 0;
     cash_ids.add(g_id);
     int flg = 0;
     do {
     q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
     ResultSet rs = st1.executeQuery(q);
     while (rs.next()) 
     {
     cash_ids.add(rs.getLong("group_id"));
     }
     if (ind == cash_ids.size() - 1) 
     {
     flg = 1;
     }
     ind++;
     } while (flg == 0);
                        
                        
     String temp_group_under_id="";
     i=0;
     while(i<cash_ids.size())
     {
     temp_group_under_id=temp_group_under_id+cash_ids.get(i)+",";
     i++;
     }
                        
     String group_under_id=temp_group_under_id.substring(0, temp_group_under_id.length()-1);
     //----------------------------------------------------
            
            
            
            
            
     q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+ dateNow +"' and trans_typeIndex=1)";
     rs2=st1.executeQuery(q);
     i=1;
     int flag_min_one_entry_present=0;
     while(rs2.next())
     {
     if(flag_min_one_entry_present==0)
     {
     tableModel.setRowCount(1);
     tableModel.setColumnCount(5);
     debitSalesIndex = 1;
     tableDebit.setValueAt("Sales", k, 1);
     }
     q="select ledger_name from tblledger where ledger_id="+ rs2.getLong("trans_ledgerid") +" and ledger_under not in('"+ group_under_id +"')";
     rs3=st3.executeQuery(q);
     if(rs3.next())
     {
     int row = tableDebit.getRowCount();
     tableModel.setRowCount(row + 1);
                    
     //Get Ledger name
     tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);
                    
     //VCH No(Receipt No)
     q="select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id="+ rs2.getLong("trans_id") +"";
     rs4=st4.executeQuery(q);
     if(rs4.next())
     {
     tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
     }
     rs4.close();
                    
     //Debitted Amount
     tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);
                    
     //Qty
     q="select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="+ rs2.getLong("trans_id") +")";
     rs4=st4.executeQuery(q);
     if(rs4.next())
     {
     tableDebit.setValueAt(rs4.getDouble("totalQty"), row, 4);
     }
     rs4.close();
                    
     flag_min_one_entry_present=1;
     debitSalesIndex++;
     }
                
     //System.out.println("Middle sales index: "+debitSalesIndex);
     }
     //--------------------------------------------------------------
     debitSalesIndex+=2;
     int row = tableDebit.getRowCount();
     tableModel.setRowCount(row + 2);
     System.out.println("creditSalesIndex: "+debitSalesIndex);
     row = tableDebit.getRowCount();
     tableDebit.setValueAt("Total", row-1, 2);

     //-------------------Count Total Creditted Amount--------------------
     if(flag_min_one_entry_present==0)
     {
     tableModel.setRowCount(0);
     total_sales=0;
     debitSalesIndex=0;
     }
     else
     {
     if (tableDebit.getRowCount() > 1) 
     {
     i = 1;
     double qty = 0;
     while (i < debitSalesIndex) 
     {
     if (tableDebit.getValueAt(i, 3) != null)
     {
     qty = qty + Double.parseDouble(tableDebit.getValueAt(i, 3).toString());
     }
     i++;
     }
     total_nonCash_sale= qty;
     tableDebit.setValueAt(df.format(qty), row - 1, 3);
     tableDebit.setValueAt(df.format(qty),k, 0);
     } 
     }
     //--------------------------------------------------------------------------------
            
     }
     catch (SQLException ex) 
     {
     ex.printStackTrace();
     }          */
    //System.out.println("Final index: "+creditPurchaseIndex);
    public void loadReceiptEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=6)";
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
                        receiptIDSet.add(tableCredit.getValueAt(row, 2).toString());
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 3);

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
                        System.out.println("Receipt Credit >>>>>>>>>>>>>>>>>>" + df.format(qty));
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
    //OLD FROM PLYWOOD
//    public void loadReceiptEntry() {
//        try {
//            Connection conn = DatabaseConnection1.GetConnection();
//            Statement st1 = conn.createStatement();
//            Statement st3 = conn.createStatement();
//            Statement st4 = conn.createStatement();
//
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            String dateNow = f.format(dat.getDate());
//
//            DecimalFormat df = new DecimalFormat("#.##");
//
//            //-----------------------Table: Credit--------------------------------------------
//            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
//            tableModel.setColumnCount(6);
//            int k = 0;
//
//            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=6)";
//            rs2 = st1.executeQuery(q);
//            int flag_min_one_entry_present = 0;
//            i = 0;
//
//            while (rs2.next()) {
//                if (flag_min_one_entry_present == 0) //set sub header
//                {
//                    creditReceiptIndex = tableCredit.getRowCount();
//                    if (creditReceiptIndex == 0) {
//                        tableModel.setRowCount(1);
//                        tableCredit.setValueAt("Receipt", creditReceiptIndex, 1);
//                        creditReceiptIndex++;
//                    } else {
//                        creditReceiptIndex += 2;
//                        //creditReceiptIndex += 1;
//                        tableModel.setRowCount(creditReceiptIndex);
//                        tableCredit.setValueAt("Receipt", creditReceiptIndex - 1, 1);
//                    }
//                }
//                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
//                rs3 = st3.executeQuery(q);
//                if (rs3.next()) {
//                    int row = tableCredit.getRowCount();
//                    tableModel.setRowCount(row + 1);
//
//                    //Get Ledger name
//                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);
//
//                    //VCH No(Receipt No)
//                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
//                    rs4 = st4.executeQuery(q);
//                    if (rs4.next()) {
//                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
//                        receiptIDSet.add(tableCredit.getValueAt(row, 2).toString());
//                        System.out.println("Added receipt : " + tableCredit.getValueAt(row, 2).toString());
//                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
//                    }
//                    rs4.close();
//
//                    //Debitted Amount
//                    tableCredit.setValueAt(rs2.getDouble("trans_amt"), row, 3);
//                   
//                    //qty will be null in receipt and payment
//                    tableCredit.setValueAt("-", row, 4);
//
//                    flag_min_one_entry_present = 1;
//                }
//                i++;
//                creditReceiptIndex++;
//            }
//            //--------------------------------------------------------------
//            creditReceiptIndex += 2;
//            int row = tableCredit.getRowCount();
//            tableModel.setRowCount(row + 2);
//            row = tableCredit.getRowCount();
//            //System.out.println("Row-1:: "+(row-1));
//            tableCredit.setValueAt("Total", row - 1, 2);
//            //System.out.println("Final Rec index: "+creditReceiptIndex);
//
//            //-------------------Count Total Creditted Amount--------------------
//            if (flag_min_one_entry_present == 0) {
//                tableModel.setRowCount(creditNonCashPurchaseIndex);
//                total_receipt = 0;
//                creditReceiptIndex = 0;
//            } else {
//                if (tableCredit.getRowCount() > 0) {
//                    int j = 0;
//                    if (creditNonCashPurchaseIndex == 0) {
//                        j = 0;
//                    } else {
//                        j = creditNonCashPurchaseIndex + 2;
//                    }
//
//                    double qty = 0;
//                    while (j < creditReceiptIndex - 1) {
//                        if (tableCredit.getValueAt(j, 3) != null) {
//                            // System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+qty);
//                            qty = qty + Double.parseDouble(tableCredit.getValueAt(j, 3).toString());
//                            System.out.println("Receipt Credit >>>>>>>>>>>>>>>>>>"+df.format(qty)); 
//                        }
//                        j++;
//                    }
//                    total_receipt = qty;
//                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
//                    if (creditNonCashPurchaseIndex == 0) {
//                        tableCredit.setValueAt(df.format(qty), 0, 0);
//                    } else {
//                        tableCredit.setValueAt(df.format(qty), creditNonCashPurchaseIndex + 1, 0);
//                    }
//                }
//            }
//            //------------------------------------------------------------------
//
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    public void loadPurchaseEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
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



            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=2)";
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
                // for mysql
//		q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in('" + group_under_id + "')";
                // for HQSLDB
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in(" + group_under_id + ")";
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
                        purchaseIDSet.add(tableDebit.getValueAt(row, 2).toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 3);

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
    //OLD FROM PLYWOOD
//    public void loadPurchaseEntry() {
//        try {
//            Connection conn = DatabaseConnection1.GetConnection();
//            Statement st1 = conn.createStatement();
//            Statement st3 = conn.createStatement();
//            Statement st4 = conn.createStatement();
//
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            String dateNow = f.format(dat.getDate());
//
//            DecimalFormat df = new DecimalFormat("#.##");
//
//            //-----------------------Table: Debit--------------------------------------------
//            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
//            tableModel.setColumnCount(6);
//            debitPurchaseIndex = 0;
//
//            //--------find if ledger is under Cash In Hand--------
//            long g_id = 0;
//            q = "select group_id from tblgroup where group_name='Cash In Hand'";
//            rs1 = st1.executeQuery(q);
//            if (rs1.next()) {
//                g_id = rs1.getLong("group_id");
//            }
//
//            ArrayList<Long> cash_ids = new ArrayList<Long>();
//            int ind = 0;
//            cash_ids.add(g_id);
//            int flg = 0;
//            do {
//                q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
//                ResultSet rs = st1.executeQuery(q);
//                while (rs.next()) {
//                    cash_ids.add(rs.getLong("group_id"));
//                }
//                if (ind == cash_ids.size() - 1) {
//                    flg = 1;
//                }
//                ind++;
//            } while (flg == 0);
//
//
//            String temp_group_under_id = "";
//            i = 0;
//            while (i < cash_ids.size()) {
//                temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
//                i++;
//            }
//
//            String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);
//            //----------------------------------------------------
//
//
//
//            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=2)";
//            rs2 = st1.executeQuery(q);
//            i = 1;
//            int flag_min_one_entry_present = 0;
//            int k = 0;    //for putting amount with table header
//            while (rs2.next()) {
//                if (flag_min_one_entry_present == 0) {
//                    tableModel.setRowCount(1);
//                    tableModel.setColumnCount(6);
//                    debitPurchaseIndex = 1;
//                    tableDebit.setValueAt("Purchase", k, 1);
//                }
//                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under in('" + group_under_id + "')";
//                rs3 = st3.executeQuery(q);
//                if (rs3.next()) {
//                    int row = tableDebit.getRowCount();
//                    //tableModel = (DefaultTableModel) tableDebit.getModel();
//                    tableModel.setRowCount(row + 1);
//
//                    //Get Ledger name
//                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);
//
//                    //VCH No(Receipt No)
//                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
//                    rs4 = st4.executeQuery(q);
//                    if (rs4.next()) {
//                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
//                        purchaseIDSet.add(tableDebit.getValueAt(row, 2).toString());
//                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
//                    }
//                    rs4.close();
//
//                    //Debitted Amount
//                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);
//                    System.out.println("Purchase Credit >>>>>>>>>>>>>>>>>>"+rs2.getDouble("trans_amt")); 
//                    //Qty
//                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
//                    rs4 = st4.executeQuery(q);
//                    if (rs4.next()) {
//                        tableDebit.setValueAt(rs4.getDouble("totalQty"), row, 4);
//                    }
//                    rs4.close();
//                    flag_min_one_entry_present = 1;
//                    debitPurchaseIndex++;
//                }
//            }
//            //--------------------------------------------------------------
//
//            int row = tableDebit.getRowCount();
//            tableModel.setRowCount(row + 2);
//            debitPurchaseIndex += 2;
//            //System.out.println("Final Purchase index: "+creditPurchaseIndex);
//            row = tableDebit.getRowCount();
//            tableDebit.setValueAt("Total", row - 1, 2);
//
//            //-------------------Count Total Debitted Amount--------------------
//            if (flag_min_one_entry_present == 0) {
//                tableModel.setRowCount(0);
//                total_purchase = 0;
//                debitPurchaseIndex = 0;
//            } else {
//                if (tableDebit.getRowCount() > 1) {
//                    i = 1;
//                    double qty = 0;
//                    while (i < debitPurchaseIndex - 1) {
//                        if (tableDebit.getValueAt(i, 3) != null) {
//                            qty = qty + Double.parseDouble(tableDebit.getValueAt(i, 3).toString());
//                        }
//                        i++;
//                    }
//                    total_purchase = qty;
//                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
//                    tableDebit.setValueAt(df.format(qty), k, 0);
//                }
//            }
//            //--------------------------------------------------------------------------------
//
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        //System.out.println("Ful n Final Purchase index: "+debitPurchaseIndex);
//    }

    public void loadPaymentEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=3)";
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
                        paymentIDSet.add(tableDebit.getValueAt(row, 2).toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    //Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 3);

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

    //OLD FROM PLYWOOD
//    public void loadPaymentEntry() {
//        try {
//            Connection conn = DatabaseConnection1.GetConnection();
//            Statement st1 = conn.createStatement();
//            Statement st3 = conn.createStatement();
//            Statement st4 = conn.createStatement();
//
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//            String dateNow = f.format(dat.getDate());
//
//            DecimalFormat df = new DecimalFormat("#.##");
//
//            //-----------------------Table: Credit--------------------------------------------
//            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
//            tableModel.setColumnCount(6);
//            int k = 0;
//            //q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+ dateNow +"' and trans_typeIndex=3)";
//            //q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
//            //q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+ dateNow +"' and a.trans_typeIndex=3)";
//            q = "select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select distinct(a.trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and trans_date='" + dateNow + "' and b.trans_ledgerId not in(select ledger_id from tblledger where ledger_under in(select group_id from tblgroup where group_name in('Suspense Account'))) and b.trans_type=1)";
//            rs2 = st1.executeQuery(q);
//            int flag_min_one_entry_present = 0;
//            i = 0;
//            while (rs2.next()) {
//                if (flag_min_one_entry_present == 0) {
//                    debitPaymentIndex = tableDebit.getRowCount();
//                    if (debitPaymentIndex == 0) {
//                        tableModel.setRowCount(1);
//                        tableDebit.setValueAt("Payment", debitPaymentIndex, 1);
//                        debitPaymentIndex++;
//                    } else {
//                        debitPaymentIndex += 2;
//                        tableModel.setRowCount(debitPaymentIndex);
//                        tableDebit.setValueAt("Payment", debitPaymentIndex - 1, 1);
//                    }
//                }
//                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
//                rs3 = st3.executeQuery(q);
//                if (rs3.next()) {
//                    int row = tableDebit.getRowCount();
//                    tableModel.setRowCount(row + 1);
//
//                    //Get Ledger name
//                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);
//
//                    //VCH No(Receipt No)
//                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
//                    rs4 = st4.executeQuery(q);
//                    if (rs4.next()) {
//                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
//                        paymentIDSet.add(tableDebit.getValueAt(row, 2).toString());
//                        System.out.println("Added payment : " + tableDebit.getValueAt(row, 2).toString());
//                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
//                    }
//
//                    //Debitted Amount
//                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);
//                    System.out.println("Payment Credit >>>>>>>>>>>>>>>>>>"+rs2.getDouble("trans_amt")); 
//                    
//                    //qty will be null in receipt and payment
//                    tableDebit.setValueAt("-", row, 4);
//                    flag_min_one_entry_present = 1;
//                }
//
//                i++;
//                debitPaymentIndex++;
//            }
//            //--------------------------------------------------------------
//            int row = tableDebit.getRowCount();
//            tableModel.setRowCount(row + 2);
//            debitPaymentIndex += 2;
//            row = tableDebit.getRowCount();
//            tableDebit.setValueAt("Total", row - 1, 2);
//
//            //-------------------Count Total Debitted Amount--------------------
//            if (flag_min_one_entry_present == 0) {
//                tableModel.setRowCount(debitNonCashSalesIndex);
//                total_payment = 0;
//                debitPaymentIndex = 0;
//            } else {
//                if (tableDebit.getRowCount() > 0) {
//                    int j = 0;
//                    if (debitNonCashSalesIndex == 0) {
//                        j = 0;
//                    } else {
//                        j = debitNonCashSalesIndex + 2;
//                    }
//
//                    double qty = 0;
//                    while (j < debitPaymentIndex - 1) {
//                        if (tableDebit.getValueAt(j, 3) != null) {
//                            qty = qty + Double.parseDouble(tableDebit.getValueAt(j, 3).toString());
//                        }
//                        j++;
//                    }
//                    total_payment = qty;
//                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
//                    if (debitNonCashSalesIndex == 0) {
//                        tableDebit.setValueAt(df.format(qty), 0, 0);
//                    } else {
//                        tableDebit.setValueAt(df.format(qty), debitNonCashSalesIndex + 1, 0);
//                    }
//                }
//            }
//            //------------------------------------------------------------------
//
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    public void loadPendingPaymentEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;
            //q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+ dateNow +"' and trans_typeIndex=3)";
            //q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
            //q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+ dateNow +"' and a.trans_typeIndex=3)";
            q = "select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select distinct(a.trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and trans_date='" + dateNow + "' and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(select group_id from tblgroup where group_name in('Suspense Account'))) and b.trans_type=1)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    debitPaymentIndex = tableDebit.getRowCount();
                    if (debitPaymentIndex == 0) {
                        tableModel.setRowCount(1);
                        tableDebit.setValueAt("Pending", debitPaymentIndex, 1);
                        debitPaymentIndex++;
                    } else {
                        debitPaymentIndex += 2;
                        tableModel.setRowCount(debitPaymentIndex);
                        tableDebit.setValueAt("Pending", debitPaymentIndex - 1, 1);
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
                        paymentIDSet.add(tableDebit.getValueAt(row, 2).toString());
                        System.out.println("Added payment : " + tableDebit.getValueAt(row, 2).toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    //Debitted Amount
                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //qty will be null in receipt and payment
                    tableDebit.setValueAt("-", row, 4);
                    flag_min_one_entry_present = 1;
                }

                i++;
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
                tableModel.setRowCount(debitNonCashSalesIndex);
                total_payment = 0;
                debitPaymentIndex = 0;
            } else {
                if (tableDebit.getRowCount() > 0) {
                    int j = 0;
                    if (debitNonCashSalesIndex == 0) {
                        j = 0;
                    } else {
                        j = debitNonCashSalesIndex + 2;
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
                    if (debitNonCashSalesIndex == 0) {
                        tableDebit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableDebit.setValueAt(df.format(qty), debitNonCashSalesIndex + 1, 0);
                    }
                }
            }
            //------------------------------------------------------------------


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadNonCashPurchaseEntry() {
        //System.out.println("Entered into non cash purchase");

//-----------------------------------------------------------------------

        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

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





            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setColumnCount(6);
            int k = 0;
            //q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+ dateNow +"' and trans_typeIndex=3)";
            //q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
            //q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+ dateNow +"' and a.trans_typeIndex=3)";
            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=2)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    creditNonCashPurchaseIndex = tableCredit.getRowCount();
                    if (creditNonCashPurchaseIndex == 0) {
                        tableModel.setRowCount(1);
                        tableCredit.setValueAt("Non-Cash Purchase", creditNonCashPurchaseIndex, 1);
                        creditNonCashPurchaseIndex++;
                    } else {
                        creditNonCashPurchaseIndex += 2;
                        tableModel.setRowCount(creditNonCashPurchaseIndex);
                        tableCredit.setValueAt("Non-Cash Purchase", creditNonCashPurchaseIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + " and ledger_under not in ('" + group_under_id + "')";
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
                        purchaseIDSet.add(tableCredit.getValueAt(row, 2).toString());
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    //Debitted Amount
                    tableCredit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    //qty will be null in receipt and payment
                    tableCredit.setValueAt("-", row, 4);
                    flag_min_one_entry_present = 1;
                }

                i++;
                creditNonCashPurchaseIndex++;
            }
            //--------------------------------------------------------------
            int row = tableCredit.getRowCount();
            tableModel.setRowCount(row + 2);
            //creditNonCashPurchaseIndex += 2;
            creditNonCashPurchaseIndex += 1;
            row = tableCredit.getRowCount();
            tableCredit.setValueAt("Total", row - 1, 2);

            //-------------------Count Total Debitted Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(creditSalesIndex);
                total_noncashpurchase = 0;
                creditNonCashPurchaseIndex = creditSalesIndex;
            } else {
                if (tableCredit.getRowCount() > 0) {
                    int j = 0;
                    if (creditSalesIndex == 0) {
                        j = 0;
                    } else {
                        j = creditSalesIndex + 2;
                    }

                    double qty = 0;
                    while (j < creditNonCashPurchaseIndex - 1) {
                        if (tableCredit.getValueAt(j, 3) != null) {
                            qty = qty + Double.parseDouble(tableCredit.getValueAt(j, 3).toString());
                        }
                        j++;
                    }
                    total_noncashpurchase = qty;
                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
                    if (creditSalesIndex == 0) {
                        tableCredit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableCredit.setValueAt(df.format(qty), creditSalesIndex + 1, 0);
                    }
                }
            }


            System.out.println("Non cash purchase index final: " + creditNonCashPurchaseIndex);
            //------------------------------------------------------------------


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        if (dat.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat.requestFocus();

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);

            labelTotalDebit.setText("0");
            labelTotalCredit.setText("0");
            labelOpeningBalance.setText("0");
            labelClosingBalance.setText("0");

        } else {

            saleIDSet = new TreeSet<String>();
            purchaseIDSet = new TreeSet<String>();
            receiptIDSet = new TreeSet<String>();
            paymentIDSet = new TreeSet<String>();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);

            loadSalesEntry();
            //loadNonCashPurchaseEntry();

            loadReceiptEntry();
            labelTotalCredit.setText("" + Constants.DECIMAL_FORMAT.format(total_sales + total_receipt + total_noncashpurchase));

            loadPurchaseEntry();

            //loadNonCashSalesEntry();
            loadPaymentEntry();
            // loadPendingPaymentEntry();
            labelTotalDebit.setText("" + Constants.DECIMAL_FORMAT.format(total_purchase + total_payment + total_nonCash_sale));

            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
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


                //--- find if ledger id is under suspese account---

                long g_id1 = 0;
                q = "select group_id from tblgroup where group_name='Suspense Account'";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    g_id1 = rs1.getLong("group_id");
                }
                rs1.close();

                ArrayList<Long> cash_ids1 = new ArrayList<Long>();
                int ind1 = 0;
                cash_ids1.add(g_id1);
                int flg1 = 0;
                do {
                    q = "select group_id from tblgroup where group_under=" + cash_ids1.get(ind1) + "";
                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        cash_ids1.add(rs.getLong("group_id"));
                    }
                    rs.close();
                    if (ind1 == cash_ids1.size() - 1) {
                        flg1 = 1;
                    }
                    ind1++;
                } while (flg1 == 0);


                String temp_suspense_group_under_id = "";
                i = 0;
                while (i < cash_ids1.size()) {
                    temp_suspense_group_under_id = temp_suspense_group_under_id + cash_ids1.get(i) + ",";
                    i++;
                }

                String group_suspense_under_id = temp_suspense_group_under_id.substring(0, temp_suspense_group_under_id.length() - 1);
                //System.out.println("Suspanse Accounts id:-"+group_suspense_under_id);
                //----------------------------------------------------





                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=6)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();
                //System.out.println("Total credited sale:"+cr);
                //-------------new query----------------
                //q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=1)";

                // for mysql
                //q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                // for HqSlDB 
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in(" + group_under_id + "))";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where  trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+ group_under_id +"'))";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();


                //for non cash sale
                //q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=1)";
                // for mysql
//		q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under not in('" + group_under_id + "'))";
                // for Hqsldb
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under not in(" + group_under_id + "))";
                //    q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where  trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+ group_under_id +"'))";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    //temporary commented
                    // d = d + rs1.getDouble("totalCred");
                }
                rs1.close();
                //--------------------------------------
                //System.out.println("Total credited All:"+cr);

                //System.out.println(cr);

                // for mysql
//                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in(select distinct(a.trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and a.trans_date<'" + dateNow + "' and b.trans_ledgerId not in(497) and b.trans_type=1)";
                // for HQSLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in(select distinct(trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and a.trans_date<'" + dateNow + "' and b.trans_ledgerId not in(497) and b.trans_type=1)";
                //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=3)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }
                rs1.close();
                //System.out.println("Total Payment debited:"+d);
                //--------------new query-------------------
                // for mysql
//		q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=2) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                // for HQSLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=2) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in(" + group_under_id + "))";
                //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }
                rs1.close();
                //---non cash purchases---------------------------------------
                // for mysql
//              q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=2) and trans_ledgerid not in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                // for HSQLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=2) and trans_ledgerid not in(select ledger_id from tblledger where ledger_under in(" + group_under_id + "))";
                //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    //temporarary commented
                    //cr = cr + rs1.getDouble("totalDeb");
                }
                rs1.close();
                //--
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
                //System.out.println("Total credited With opening:"+cr);

                //-------calculate pending balance for opening bal
             /*q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_typeIndex=3) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+ group_suspense_under_id +"'))";
                 //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                 rs1 = st.executeQuery(q);
                 Double lessPending=0.0d;
                 while(rs1.next())
                 {
                 lessPending=rs1.getDouble("totalDeb");
                 cr=cr-lessPending;
                 }
                 rs1.close();
                 //System.out.print("lessPending:"+lessPending);*/

                //-------calculate pending balance for current date
                // for mysql
//		q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date <='" + dateNow + "') and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_suspense_under_id + "'))";
                // for HQSLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date <='" + dateNow + "') and trans_ledgerid in(select ledger_id from tblledger where ledger_under in(" + group_suspense_under_id + "))";
                //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                double dayPending = 0.0d;
                while (rs1.next()) {
                    dayPending = rs1.getDouble("totalDeb");

                }
                rs1.close();
                //System.out.println("dayPending:-"+dayPending);
                lablePendingBal.setText("" + Constants.DECIMAL_FORMAT.format(dayPending));


                /*
                 //this code has to be remove
                 q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date='"+ dateNow +"' and trans_typeIndex=3) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+ group_suspense_under_id +"'))";
                 //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                 rs1 = st.executeQuery(q);
                 double dayPending1=0.0d;
                 while(rs1.next())
                 {
                 dayPending1=rs1.getDouble("totalDeb");
                 //lablePendingBal.setText(""+ dayPending1);
                 }
                 rs1.close();
                 //System.out.println("dayPending:-"+dayPending1);
                 //System.out.println(d);

            
            
                 //this code has to be remove
                 q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=3) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+ group_suspense_under_id +"'))";
                 //q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                 rs1 = st.executeQuery(q);
                 double dayPending2=0.0d;
                 while(rs1.next())
                 {
                 dayPending2=rs1.getDouble("totalDeb");
                 //lablePendingBal.setText(""+ dayPending1);
                 }
                 rs1.close();
                 //System.out.println("dayPending:-"+dayPending1);
                 //System.out.println(d);
                 */
                //System.out.print("Total Credited:"+cr);
                //System.out.println("Total Debited:"+d);
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
                //o=Double.parseDouble(labelOpeningBalance.getText());
                cr = Double.parseDouble(labelTotalCredit.getText());
                d = Double.parseDouble(labelTotalDebit.getText());
                double cl = 00f;

                if (labelOType.getText().equalsIgnoreCase("Credit")) {
                    cl = o + cr - d;// - dayPending;
                    labelClosingBalance.setText("" + df.format(Math.abs(cl)));
                    labclosingwithoutpending.setText("" + df.format(Math.abs(o + cr - d)));
                    if (cl < 0) {
                        labelCType.setText("Debit");
                    } else {
                        labelCType.setText("Credit");
                    }
                } else {
                    cl = o + d - cr;// + dayPending;
                    labelClosingBalance.setText("" + df.format(Math.abs(cl)));
                    labclosingwithoutpending.setText("" + df.format(Math.abs(o + cr - d)));
                    if (cl > 0) {
                        labelCType.setText("Debit");
                    } else {
                        labelCType.setText("Credit");
                    }
                }
                openingBL_plus_TotalAmount.setText("" + df.format(Math.abs(Double.parseDouble(labelTotalCredit.getText().toString().trim()) + Double.parseDouble(labelOpeningBalance.getText().toString().trim()))));
                //---------------------------------------------------------------------------
            } catch (SQLException ex) {
                ex.printStackTrace();
                //System.out.println(ex.getMessage());
            }
        }



        setColumnWidth(tableDebit);
        setColumnWidth(tableCredit);

}//GEN-LAST:event_btnShowActionPerformed

    private void btnShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnShowKeyPressed
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

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:


        System.out.println("DayBook---------------->>btnPrintActionPerformed------->>printer variables: Direct print:" + PrinterSettings.flagDirectPrint + " Page Format:" + PrinterSettings.flagPrintPageFormat + " Page Size:" + PrinterSettings.flagPrintPageSize);
        btnShowActionPerformed(evt);
        createTable();
        if (combine.getRowCount() != 0 && dat.getDate() != null) {
            Map parameter = new HashMap();

            String TotalCreditAmount = labelTotalCredit.getText();
            String TotalDebiAmount = labelTotalDebit.getText();
            //  System.out.println(""+TotalAmountValue);

            String OpeningBalance = labelOpeningBalance.getText();
            String ClosingBalance = labelClosingBalance.getText();
            String pending = lablePendingBal.getText();

            String OType = labelOType.getText();
            String CType = labelCType.getText();

            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("Date", saDa.format(dat.getDate()).toString());


            if (pending.equalsIgnoreCase("")) {
                pending = " ";
            } else {
                pending = "" + lablePendingBal.getText().trim();
            }

            if (OpeningBalance.equalsIgnoreCase("")) {
                OpeningBalance = " ";
            } else {
                OpeningBalance = "" + labelOpeningBalance.getText().trim();
            }

            if (ClosingBalance.equalsIgnoreCase("")) {
                ClosingBalance = " ";
            } else {
                ClosingBalance = "" + labclosingwithoutpending.getText().trim();
            }

            String totalCredit_plus_OPBL = openingBL_plus_TotalAmount.getText().toString().trim();

            parameter.put("TotalAmountdebit", TotalDebiAmount);
            parameter.put("TotalAmountCredit", totalCredit_plus_OPBL);

            parameter.put("OpeningBalanceValue", OpeningBalance);
            parameter.put("ClosingBalanceValue", ClosingBalance);
            parameter.put("OpeningDebit", OType);
            parameter.put("OpeningCredit", CType);
            parameter.put("pending", pending);


            PrintAllReport.printTableDaybookDemo(parameter, new JRTableModelDataSource(combine.getModel()));
        } else {
            JOptionPane.showMessageDialog(this, "No data to Print");
        }
//         Map parameter=new HashMap();
//	
//         parameter.put("Total Amount","Total Amount");
//         parameter.put("Opening Balance","Opening Balance");
//         parameter.put("Closing Balance","Closing Balance");
//       
//        
//          String TotalCreditAmount=labelTotalCredit.getText(); 
//          String TotalDebiAmount=labelTotalDebit.getText(); 
//       //  System.out.println(""+TotalAmountValue);
//         
//         String OpeningBalance=labelOpeningBalance.getText(); 
//         String ClosingBalance=labelClosingBalance.getText(); 
//         
//         String OType=labelOType.getText(); 
//         String CType=labelCType.getText(); 
//         //String pendingBalance=lablePendingBal.getText();
//         
//         parameter.put("TotalDebitValue",TotalDebiAmount);
//         parameter.put("TotalCreditValue",TotalCreditAmount);
//       
//         parameter.put("OpeningBalanceValue",OpeningBalance);
//         parameter.put("ClosingBalanceValue",ClosingBalance);
//       
//         parameter.put("OType",OType);
//       
//         parameter.put("CType",CType);
//     //    parameter.put("pendingBalance",pendingBalance);
//         
//      
//         SimpleDateFormat saDa=new SimpleDateFormat("yyyy-MM-dd");
//         parameter.put("trans_date",saDa.format( dat.getDate()).toString());
//         
//         SimpleDateFormat saDa1=new SimpleDateFormat("dd-mm-yyyy");
//         parameter.put("CurrentDate",saDa1.format( dat.getDate()).toString());
//         
//         System.out.println("Date..........."+TotalCreditAmount+TotalDebiAmount);
//         System.out.println("Date..........."+OpeningBalance+ClosingBalance);
//         System.out.println("Date..........."+OType+CType);   
//      //   System.out.println("Date..........."+pendingBalance);
//               
//               
//         
//       
//         PrintAllReport.printDayBook(saDa.format( dat.getDate()).toString(),parameter);     














//       PrinterSettings g=new PrinterSettings("Printer Settings");
//     
//            this.getParent().add(g);
//            g.setVisible(true);
//            Dimension desktopSize = this.getParent().getSize();
//            Dimension jInternalFrameSize = g.getSize();
//            g.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
//                    (desktopSize.height - jInternalFrameSize.height) / 2);
//       
//        
//        Map parameter=new HashMap();
//	 parameter.put("Groups","Groups");
//         parameter.put("Sr.No","Sr.No");
//         
//         parameter.put("Debit","Debit");
//          parameter.put("Credit","Credit");
//          
//          String TotalDebitValue=labelTotalDebit.getText(); 
//          String TotalCreditValue=labelTotalCredit.getText(); 
//       //  System.out.println(""+TotalAmountValue);
//         
//        // String TotalAmount=labelTotalCredit.getText();
//         
//         parameter.put("TotalDebitValue",TotalDebitValue);
//         parameter.put("TotalCreditValue",TotalCreditValue);
//       
//         
//         parameter.put("TotalDebit","TotalDebit");
//         parameter.put("TotalCredit","TotalCredit");
//         
//         
////         parameter.put("OpeningBalance","OpeningBalance");
////         parameter.put("ClosingBalance","ClosingBalance");
//         //parameter.put("subreporttrial",new JRTableModelDataSource(tableLedger.getModel()));
//         
//         PrintAllReport.printtrialReport(parameter,new JRTableModelDataSource(tableCredit.getModel()));
}//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPrintKeyPressed
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

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass mainClass = new MainClass();
            mainClass.menuselection(3);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void btnBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBackKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnAdvanceDaybook.requestFocus();
        }
}//GEN-LAST:event_btnBackKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        labelTotalCredit.setVisible(false);

        setComponentActiveInactive();
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

        scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

        dat.requestFocus();

        //tableCredit.setGridColor(Color.red);
        tableCredit.setShowGrid(true);

        //tableDebit.setGridColor(Color.red);
        tableDebit.setShowGrid(true);

        // backAction=2;

    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        setComponentActiveInactive();
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
            btnAdvanceDaybook.setMnemonic(KeyEvent.VK_D);
            tbnExport.setMnemonic(KeyEvent.VK_X);

            btnShowActionPerformed(null);

            scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

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

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        backAction = 2;
        date = tfDatePicker.getText();
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void tbnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnExportActionPerformed

        imageloading o = new imageloading();

        BufferedWriter out = null;
        try {
            // TODO add your handling code here:
            Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
            IDMapSet.put("Sale", saleIDSet);
            System.out.println("saleIDSet Size--->>>" + saleIDSet.size());
            IDMapSet.put("Purchase", purchaseIDSet);
            IDMapSet.put("Receipt", receiptIDSet);
            IDMapSet.put("Payment", paymentIDSet);
            String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
            System.out.println("------------------------->>XMLCODE:" + xmlCode);

            // File file = new File("C:\\DayBook.xml");
            File file = new File(path + ".xml");
            String path = file.getPath();
            out = new BufferedWriter(new FileWriter(file));
            out.write(xmlCode);
            out.close();

            JOptionPane.showMessageDialog(this, "Export Successful");

        } catch (Exception ex) {
            Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Export failure");
        }



    }//GEN-LAST:event_tbnExportActionPerformed

    private void btnAdvanceDaybookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdvanceDaybookActionPerformed
        // TODO add your handling code here:
        DayBookAdvance1 c = new DayBookAdvance1("Advance MainDaybook Report");
        c.pack();
        c.setVisible(true);
        this.getParent().add(c);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        c.setSize(desktopSize);
        c.setPreferredSize(desktopSize);
        try {
            c.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
        BasicInternalFrameUI ui = (BasicInternalFrameUI) c.getUI();

        Component north = ui.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }
    }//GEN-LAST:event_btnAdvanceDaybookActionPerformed

    private void btnAdvanceDaybookKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAdvanceDaybookKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            tbnExport.requestFocus();
        }
    }//GEN-LAST:event_btnAdvanceDaybookKeyPressed

    private void tbnExportKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbnExportKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnAdvanceDaybook.requestFocus();
        }
    }//GEN-LAST:event_tbnExportKeyPressed

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
                for (int j = 0; j < tableCredit.getColumnCount(); j++) {
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
                for (int j = 0; j < tableDebit.getColumnCount(); j++) {
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
    private javax.swing.JButton btnAdvanceDaybook;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labclosingwithoutpending;
    private javax.swing.JLabel labelCType;
    private javax.swing.JLabel labelClosingBalance;
    private javax.swing.JLabel labelOType;
    private javax.swing.JLabel labelOpeningBalance;
    private javax.swing.JLabel labelPending;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JLabel lablePendingBal;
    private javax.swing.JLabel openingBL_plus_TotalAmount;
    private javax.swing.JPanel panelDebit;
    private javax.swing.JPanel panelMain;
    private javax.swing.JScrollPane scrollpaneMain;
    private javax.swing.JTable tableCredit;
    private javax.swing.JTable tableDebit;
    private javax.swing.JButton tbnExport;
    // End of variables declaration//GEN-END:variables

    public class imageloading //extends JFrame
    {

        BufferedImage mImage;
        String name, name1;

        public imageloading() {
//JFrame frm=new JFrame("file loading test");
            String source = filechoose();
            File inputFile = new File(source);
//try {
//mImage = ImageIO.read(inputFile);
//} catch (IOException ex) {
////Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
//}
//JLabel lb=new JLabel(new ImageIcon(mImage));
//frm.getContentPane().add(lb);
//frm.show();
//frm.pack();
        }

        String filechoose() {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogType(JFileChooser.SAVE_DIALOG);


            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File f) {
                    name = f.getName().toLowerCase();
                    return name.endsWith(".xml") || name.endsWith(".xml")
                            || name.endsWith(".xml") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return ".xml";
                }
            });

            int r = chooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                name1 = chooser.getSelectedFile().getAbsolutePath();
                File file = chooser.getSelectedFile();
                path = name1;
                System.out.println("Dynamic Path:::" + path);
                StringBuffer sb = new StringBuffer();
                sb.append(name1);

                int l = sb.length();
                for (int i = 0; i < l; i++) {
                    if (sb.charAt(i) == '\\') {
                        sb.insert(i, "\\");
                        i++;
                    }
                }
                try {
//                 FileWriter fstream = new FileWriter(System.currentTimeMillis() + "chalan.xml");
//                 BufferedWriter out = new BufferedWriter(fstream);
//                 out.write(path);
                    PrintStream ps = new PrintStream(file);
                    ps.print(path);

                    //Close the output stream
                    ps.close();
                } catch (Exception e) {
                }
            }
            return name1;
        }
//public static void main(String a[])
//{
//new imageloading();
//}
    }

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(130);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(130);


        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(50);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(50);

        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(90);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(90);
        passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(4).setMinWidth(60);
        passedTable.getColumnModel().getColumn(4).setMaxWidth(60);
        passedTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(5).setMinWidth(0);
        passedTable.getColumnModel().getColumn(5).setMaxWidth(0);
        passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
    }

    private void setComponentActiveInactive() {


        labelPending.setVisible(false);
        labelClosingBalance.setVisible(false);
        lablePendingBal.setVisible(false);

    }
}