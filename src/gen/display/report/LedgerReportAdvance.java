package gen.display.report;

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
import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class LedgerReportAdvance extends javax.swing.JInternalFrame {
    //Database db=new Database();     //Object of class for database 

    Thread thread = null;
    boolean newflag = false;
    int pauseClickCheck = 0;
    ArrayList<TemporaryClass> ledger = new ArrayList<TemporaryClass>();
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();
    private final JTextField tfDatePicker;
    //   private final JTextField tfDatePickerCal;
    private final JTextField tfDatePicker1;
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3;
    String q = "", str = "";
    //  ResultSet rs;
    static int backAction = 0;
    String date = "";
    int flag_cash_in_hand = 0;

    /**
     * Creates new form LedgerReport
     */
    public LedgerReportAdvance(String s) {
        setClosable(true);
        initComponents();
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tfDatePicker = (JTextField) dat1.getComponent(1);
        // tfDatePickerCal=(JTextField)dat1.getComponent(0);
        tfDatePicker1 = (JTextField) dat2.getComponent(1);
        System.out.println(tfDatePicker.toString());



        //   tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                System.out.println("code:" + e.getKeyCode());

                if (code == KeyEvent.VK_ENTER) {
                    //txtAccount.requestFocus();
                    System.out.println("DAte From DatePIcker" + (dat1.getDate() == null));

                    if (dat1.getDate() == null) {
                        JOptionPane.showMessageDialog(rootPane, "Please Select Valid Date");
                        tfDatePicker.requestFocus();
                    } else {
                        tfDatePicker1.requestFocus();
                    }
                    /*int date=31;
                     int month=12;
                     int year=1900;
                     fdate=Integer.parseInt(tfDatePicker.getText()); 
                     System.out.println("Sudeep is here"+fdate);
                     if(fdate<=31||fdate<=12||fdate<=1900)
                     {
                     System.out.println("Sudeep is here"+fdate);
                     }*/
                } else if (code == KeyEvent.VK_ESCAPE) {
                    comboLedger.requestFocus();
                }
            }
            /*   @Override
             public void keyTyped(final KeyEvent e) 
             {
             System.out.println("Key pressed");
             int code = e.getKeyCode();
             System.out.println("code"+code);
             if(code==KeyEvent.VK_ENTER)
             {
             System.out.println("Enter");
                
             }
             EventQueue.invokeLater(new Runnable() 
             {
             @Override
             public void run() 
             {
             int code = e.getKeyCode();
             System.out.println("code"+code);
             if(code==KeyEvent.VK_ENTER)
             {
             System.out.println("Enter");
             }
             else if(code==KeyEvent.VK_ESCAPE) 
             {
             System.out.println("Escape");
             hide_flag = true; 
             }
             else if(code==KeyEvent.VK_RIGHT) 
             {
             System.out.println("Right");
             }
             }
             });
             }*/
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


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //  tfDatePicker1 = (JTextField) dat2.getComponent(1);
        System.out.println(tfDatePicker.toString());



        //   tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // tfDatePicker1.requestFocus(); 
                    btnShow.requestFocus();
                    btnShowActionPerformed(null);
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker.requestFocus();
                }
                //btnShow.requestFocus();
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

        tfDatePicker1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePicker1.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelMain = new javax.swing.JPanel();
        panelLedgerDetails = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLedger = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelTotalDebit = new javax.swing.JLabel();
        labelTotalCredit = new javax.swing.JLabel();
        Open = new javax.swing.JLabel();
        Close = new javax.swing.JLabel();
        labelOpening = new javax.swing.JLabel();
        labelClosing = new javax.swing.JLabel();
        labelOType = new javax.swing.JLabel();
        labelCType = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        dat2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dat1 = new com.toedter.calendar.JDateChooser();
        comboLedger = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnResume = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 700));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelMain.setPreferredSize(new java.awt.Dimension(1000, 788));

        panelLedgerDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableLedger.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableLedger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "Sr.No", "Particulars","Date", "VCH Type", "VCH No","Debit","Credit"
            }
        ));
        tableLedger.getTableHeader().setResizingAllowed(false);
        tableLedger.getTableHeader().setReorderingAllowed(false);
        tableLedger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLedgerMouseClicked(evt);
            }
        });
        tableLedger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableLedgerKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableLedger);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Total Debit");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Total Credit");

        labelTotalDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTotalDebit.setText("jLabel6");

        labelTotalCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTotalCredit.setText("jLabel6");

        Open.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Open.setText("Opening Balance");

        Close.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Close.setText("Closing Balance");

        labelOpening.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelOpening.setText("jLabel8");

        labelClosing.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelClosing.setText("jLabel9");

        labelOType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelOType.setText("jLabel6");

        labelCType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelCType.setText("jLabel6");

        javax.swing.GroupLayout panelLedgerDetailsLayout = new javax.swing.GroupLayout(panelLedgerDetails);
        panelLedgerDetails.setLayout(panelLedgerDetailsLayout);
        panelLedgerDetailsLayout.setHorizontalGroup(
            panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLedgerDetailsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLedgerDetailsLayout.createSequentialGroup()
                        .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelTotalDebit, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(labelTotalCredit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Open)
                            .addComponent(Close))
                        .addGap(18, 18, 18)
                        .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelOpening, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelClosing, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelOType)
                            .addComponent(labelCType))
                        .addGap(92, 92, 92))
                    .addGroup(panelLedgerDetailsLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelLedgerDetailsLayout.setVerticalGroup(
            panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLedgerDetailsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(labelTotalDebit)
                        .addComponent(labelOType))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelOpening, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Open)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelTotalCredit)
                    .addComponent(Close)
                    .addComponent(labelCType)
                    .addComponent(labelClosing, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Ledger Report");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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

        btnShow.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnShow.setText("Print");
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

        dat2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        dat2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dat2FocusLost(evt);
            }
        });
        dat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dat2KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("To");

        dat1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        dat1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dat1FocusLost(evt);
            }
        });
        dat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dat1KeyPressed(evt);
            }
        });

        comboLedger.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboLedger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLedgerActionPerformed(evt);
            }
        });
        comboLedger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboLedgerKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Ledger Name");

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
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

        btnPause.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnPause.setText("Pause");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });
        btnPause.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPauseKeyPressed(evt);
            }
        });

        btnResume.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnResume.setText("Resume");
        btnResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResumeActionPerformed(evt);
            }
        });
        btnResume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnResumeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPause))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(comboLedger, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnPrint)))
                .addGap(18, 18, 18)
                .addComponent(btnResume)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLedger, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrint)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnBack))
                    .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPause)
                        .addComponent(btnResume)))
                .addGap(20, 20, 20))
        );

        jTable1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mark", "Ledger Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelLedgerDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(756, 756, 756)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelLedgerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(224, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        Calendar currentDate = Calendar.getInstance();
        dat1.setDate(currentDate.getTime());
        dat2.setDate(currentDate.getTime());

        JTableHeader header = tableLedger.getTableHeader();
        header.setBackground(Color.yellow);

        tableLedger.setRowSelectionAllowed(true);

        btnShow.setMnemonic(KeyEvent.VK_S);
        btnPrint.setMnemonic(KeyEvent.VK_P);
        btnBack.setMnemonic(KeyEvent.VK_B);



        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();

            q = "select ledger_id,ledger_name from tblledger order by ledger_name";
            rs1 = st.executeQuery(q);
            comboLedger.addItem("");
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            comboLedger.setEditable(true);
            tf = (JTextField) comboLedger.getEditor().getEditorComponent();



            while (rs1.next()) {
                TemporaryClass t1 = new TemporaryClass();
                t1.setLedger_id(Integer.parseInt(rs1.getString("ledger_id")));
                t1.setLedger_name(rs1.getString("ledger_name"));
                ledger.add(t1);
                //  comboLedger.addItem(t1.getLedger_name());

                v.add(rs1.getString("ledger_name"));

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
                comboLedger.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboLedger.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboLedger.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboLedger.showPopup();
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
                                        comboLedger.setSelectedIndex(-1);
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
                                if (comboLedger.getSelectedItem().equals("")) {

                                    //JOptionPane.showMessageDialog(comboLedger,"Please Select Ledger Name");
                                    tfDatePicker.requestFocus();
                                } else {
                                    tfDatePicker.requestFocus();
                                }
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                btnBackActionPerformed(null);
                            }
                        }
                    });
                }
            });

            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    //  throw new UnsupportedOperationException("Not supported yet.");
                    tf.selectAll();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet.");
                }
            });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////           

            dat1.requestFocus();

            DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(7);

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat1.getDate());
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        panelLedgerDetails.setVisible(false);
        // comboLedger.requestFocus();

        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        Connection conn;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from tblledger");

            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(2);


            int i = 0;
            while (rs.next()) {
                i = tableModel.getRowCount();
                tableModel.setRowCount(i + 1);
                jTable1.setValueAt(rs.getString("ledger_name"), i, 1);

            }
        } catch (Exception e) {
            System.out.println("" + e);
        }

    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        backAction = 0;
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void tableLedgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLedgerMouseClicked
        int row = tableLedger.getSelectedRow();
        if (tableLedger.getValueAt(row, 2) != null) {
            if (tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Receipt")) {

                String id = tableLedger.getValueAt(tableLedger.getSelectedRow(), 2).toString();
                ReceiptDTO receiptDTO = new ReceiptDTO();
                ReceiptForm r = new ReceiptForm(receiptDTO, true);
                receiptDTO.setReceiptNo(Integer.parseInt(id));
                r.pack();
                r.setVisible(true);
                this.getParent().add(r);
                this.getParent().setVisible(true);
                Dimension desktopSize = this.getParent().getSize();
                Dimension jInternalFrameSize = r.getSize();
                //                        gc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                //                        (desktopSize.height- jInternalFrameSize.height)/2);
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
            } else if (tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Payment")) {
                try {
                    String id = tableLedger.getValueAt(tableLedger.getSelectedRow(), 2).toString();
                    PaymentDTO paymentDTO = new PaymentDTO();
                    PaymentForm p = new PaymentForm(paymentDTO, true);
                    p.loadEditForm(id);
                    p.pack();
                    p.setVisible(true);
                    this.getParent().add(p);
                    this.getParent().setVisible(true);
                    Dimension desktopSize = this.getParent().getSize();
                    Dimension jInternalFrameSize = p.getSize();
                    //                        gc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                    //                        (desktopSize.height- jInternalFrameSize.height)/2);
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
                } catch (Exception ex) {
                    Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Sales")) {
                try {
                    String id = tableLedger.getValueAt(tableLedger.getSelectedRow(), 2).toString();
                    SaleDTO saleDTO = new SaleDTO();
                    SaleForm s = new SaleForm("Sales", new Dimension(), saleDTO, true);
                    s.loadEditForm(id);
                    s.pack();
                    s.setVisible(true);
                    this.getParent().add(s);
                    this.getParent().setVisible(true);
                    Dimension desktopSize = this.getParent().getSize();
                    Dimension jInternalFrameSize = s.getSize();
                    //                        gc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                    //                        (desktopSize.height- jInternalFrameSize.height)/2);
                    s.setSize(desktopSize);
                    s.setPreferredSize(desktopSize);
                    try {
                        s.setSelected(true);
                    } catch (java.beans.PropertyVetoException e) {
                        e.printStackTrace();
                    }
                    BasicInternalFrameUI ui = (BasicInternalFrameUI) s.getUI();

                    Component north = ui.getNorthPane();
                    MouseMotionListener[] actions =
                            (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                    for (int i = 0; i < actions.length; i++) {
                        north.removeMouseMotionListener(actions[i]);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Purchase")) {
                try {
                    String id = tableLedger.getValueAt(tableLedger.getSelectedRow(), 2).toString();
                    PurchaseDTO purchaseDTO = new PurchaseDTO();
                    PurchaseForm p = new PurchaseForm(purchaseDTO, true);
                    p.loadEditForm(id);
                    p.pack();
                    p.setVisible(true);
                    this.getParent().add(p);
                    this.getParent().setVisible(true);
                    Dimension desktopSize = this.getParent().getSize();
                    Dimension jInternalFrameSize = p.getSize();
                    //                        gc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                    //                        (desktopSize.height- jInternalFrameSize.height)/2);
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
                } catch (Exception ex) {
                    Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tableLedgerMouseClicked

    private void tableLedgerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLedgerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tableLedger.getSelectedRow();
            if (tableLedger.getRowCount() > 0) {
                if (tableLedger.getValueAt(i, 0) == null) {
                    dat1.requestFocus();
                } else {
                    tableLedgerMouseClicked(null);
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dat1.requestFocus();
        }
    }//GEN-LAST:event_tableLedgerKeyPressed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        //DefaultTableModel tableModel = (DefaultTableModel)tableLedger.getModel();

        Map parameter = new HashMap();
        parameter.put("Particulars", "Particulars");
        parameter.put("Sr.No", "Sr.No");
        parameter.put("VCH No", "VCH No");
        parameter.put("VCH Type", "VCH Type");
        parameter.put("Date", "Date");
        parameter.put("Debit", "Debit");
        parameter.put("Credit", "Credit");

        String TotalDebitValue = labelTotalDebit.getText();
        String TotalCreditValue = labelTotalCredit.getText();
        //  System.out.println(""+TotalAmountValue);

        String OpeningBalance = labelOpening.getText();
        String ClosingBalance = labelClosing.getText();

        String labelOType1 = labelOType.getText();
        String labelCType1 = labelCType.getText();
        // String TotalAmount=labelTotalCredit.getText();

        parameter.put("TotalDebitValue", TotalDebitValue);
        parameter.put("TotalCreditValue", TotalCreditValue);

        parameter.put("OpeningBalanceValue", OpeningBalance);
        parameter.put("ClosingBalanceValue", ClosingBalance);

        parameter.put("TotalDebit", "TotalDebit");
        parameter.put("TotalCredit", "TotalCredit");

        parameter.put("OpeningDebit", labelOType1);
        parameter.put("OpeningCredit", labelCType1);


        parameter.put("ledgerName", tf.getText());

        SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
        parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
        parameter.put("dateTo", saDa.format(dat2.getDate()).toString());

//         parameter.put("OpeningBalance","OpeningBalance");
//         parameter.put("ClosingBalance","ClosingBalance");
        //parameter.put("subreporttrial",new JRTableModelDataSource(tableLedger.getModel()));

        PrintAllReport.printLedgerReport(parameter, new JRTableModelDataSource(tableLedger.getModel()));



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
            if (tableLedger.getRowCount() > 0) {
                tableLedger.requestFocus();
                tableLedger.changeSelection(0, 0, false, false);
            } else {
                btnShow.requestFocus();
            }
        }
    }//GEN-LAST:event_btnPrintKeyPressed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0, k = 1;
                String value = "";
                while (jTable1.getRowCount() > i) {
//           System.out.println("--------->>btnShow"+ jTable1.getValueAt(i, 0).equals(true)+jTable1.getValueAt(i, 0).equals(true)); 
                    value = "" + jTable1.getValueAt(i, 0);
                    System.out.println("total row value" + value);
                    if (value.equals("null") || value.equals("false")) {
                    } else {
                        //  System.out.println("In true---------->>" + jTable1.getValueAt(k, 0).toString());
                        // System.out.println("---------->>" + jTable1.getValueAt(k, 1).toString());
                        tf.setText(jTable1.getValueAt(i, 1).toString());
                        System.out.println("Value AT Combo box" + tf.getText());

                        try {
                            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                            String date1 = f.format(dat1.getDate());
                            String date2 = f.format(dat2.getDate());
                            //   JOptionPane.showMessageDialog(this, date1);
                            //  JOptionPane.showMessageDialog(this, date2);
                            if (dat1.getDate() == null) {
                                JOptionPane.showMessageDialog(null, "Invalid Date Entered");
                                dat1.requestFocus();
                                tfDatePicker.requestFocus();
                            } else if (dat2.getDate() == null) {
                                JOptionPane.showMessageDialog(null, "Invalid Date Entered");
                                dat2.requestFocus();
                                tfDatePicker1.requestFocus();
                            } else if (date1.compareTo(date2) > 0) {
                                JOptionPane.showMessageDialog(null, "Invalid Date selection");
                                dat2.requestFocus();
                            } //        if(comboLedger.getSelectedItem().equals("")) {
                            //            JOptionPane.showMessageDialog(this, "Please select the Ledger");
                            //            comboLedger.requestFocus();
                            //        } else if(dat1.getDate()==null) {
                            //            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
                            //            dat1.requestFocus();
                            //            tfDatePicker.requestFocus();
                            //        } else if(dat2.getDate()==null) {
                            //            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
                            //            dat2.requestFocus();
                            //            tfDatePicker1.requestFocus();
                            //        } else if(dat1.getDate().getTime()>dat2.getDate().getTime()) {
                            //            JOptionPane.showMessageDialog(this, "Invalid Date selection");
                            //            dat2.requestFocus();
                            //        } 
                            else {
                                panelLedgerDetails.setVisible(true);
                                labelOType.setText("");
                                labelCType.setText("");
                                labelClosing.setText("0");
                                labelOpening.setText("0");
                                labelTotalCredit.setText("0");
                                labelTotalDebit.setText("0");

                                DecimalFormat decformat = new DecimalFormat("#.##");
                                try {
                                    Connection conn = DatabaseConnection1.GetConnection();
                                    Statement st = conn.createStatement();
                                    Statement st1 = conn.createStatement();
                                    Statement st2 = conn.createStatement();

//                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//                String date1 = f.format(dat1.getDate());
//                String date2 = f.format(dat2.getDate());

                                    //-----------------------Table: Ledger--------------------------------------------
                                    DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
                                    tableModel.setRowCount(0);
                                    tableModel.setColumnCount(7);

                                    //find ledger ID
                                    k = 0;
                                    long led_id = 0;
                                    while (ledger.size() > k) {
                                        if (ledger.get(k).getLedger_name().equals(tf.getText())) {
                                            led_id = ledger.get(k).getLedger_id();
                                        }
                                        k++;
                                    }

                                    double o = 00f;
                                    double cr = 00f;
                                    double d = 00f;

                                    DecimalFormat df = new DecimalFormat("#.##");

                                    q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=6)";
                                    rs1 = st.executeQuery(q);
                                    while (rs1.next()) {
                                        cr = cr + rs1.getDouble("totalCred");
                                    }
                                    rs1.close();

                                    //---------------new query------------------
                                    q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=1)";
                                    rs1 = st.executeQuery(q);
                                    while (rs1.next()) {
                                        cr = cr + rs1.getDouble("totalCred");
                                    }
                                    rs1.close();
                                    //------------------------------------------

                                    q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=3)";
                                    rs1 = st.executeQuery(q);
                                    while (rs1.next()) {
                                        d = d + rs1.getDouble("totalDeb");
                                    }
                                    rs1.close();

                                    //--------------new query-------------------
                                    q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=2)";
                                    rs1 = st.executeQuery(q);
                                    while (rs1.next()) {
                                        d = d + rs1.getDouble("totalDeb");
                                    }
                                    rs1.close();
                                    //------------------------------------------

                                    //System.out.println("d(1): "+d);

                                    //--------------find if ledger is under Cash In Hand-----------
                                    long g_id = 0;
                                    q = "select group_id from tblgroup where group_name='Cash In Hand'";
                                    rs1 = st.executeQuery(q);
                                    if (rs1.next()) {
                                        g_id = rs1.getLong("group_id");
                                    }

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
                                        if (ind == cash_ids.size() - 1) {
                                            flg = 1;
                                        }
                                        ind++;
                                    } while (flg == 0);
                                    //-------------------------------------------------------------

                                    q = "select ledger_openingBalance,opening_type,ledger_under from tblledger where ledger_id=" + led_id + "";
                                    rs1 = st.executeQuery(q);
                                    if (rs1.next()) {
                                        k = 0;
                                        while (k < cash_ids.size()) {
                                            if (rs1.getLong("ledger_under") == cash_ids.get(k)) {
                                                flag_cash_in_hand = 1;
                                                break;
                                            }
                                            k++;
                                        }

                                        o = rs1.getDouble("ledger_openingBalance");
                                        if (rs1.getInt("opening_type") == 2) {
                                            o = o + cr - d;

                                            labelOpening.setText("" + df.format(Math.abs(o)));
                                            if (o < 0) {
                                                labelOType.setText("Debit");
                                            } else {
                                                labelOType.setText("Credit");
                                            }
                                        } else {
                                            o = o + d - cr;
                                            labelOpening.setText("" + df.format(Math.abs(o)));
                                            if (o < 0) {
                                                labelOType.setText("Debit");
                                            } else {
                                                labelOType.setText("Credit");
                                            }
                                        }
                                    }

                                    //----------opening balance---------
                                    q = "select ledger_openingBalance,opening_type from tblledger where ledger_id=" + led_id + "";
                                    rs3 = st2.executeQuery(q);
                                    if (rs3.next()) {
                                        double op = rs3.getDouble("ledger_openingBalance");
                                        labelOpening.setText("" + df.format(op));
                                        if (rs3.getInt("opening_type") == 1) {
                                            labelOType.setText("Debit");
                                        } else {
                                            labelOType.setText("Credit");
                                        }
                                    }
                                    rs3.close();
                                    //----------------------------------

                                    q = "select trans_id,trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex<>7 order by trans_date";
                                    rs1 = st.executeQuery(q);
                                    while (rs1.next()) {
                                        q = "select distinct(trans_type),trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_ledgerId=" + led_id + "";
                                        rs2 = st1.executeQuery(q);
                                        while (rs2.next()) {

                                            if (rs1.getInt("trans_typeIndex") == 1 || rs1.getInt("trans_typeIndex") == 2) {
                                                //--------select ledger of opposite transaction type i.e.debit X Credit----------
                                                q = "select trans_ledgerId,trans_amt,trans_type,trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_type<>" + rs2.getLong("trans_type") + "";
                                                ResultSet rs4 = st2.executeQuery(q);
                                                while (rs4.next()) {
                                                    int row = tableLedger.getRowCount();
                                                    tableModel = (DefaultTableModel) tableLedger.getModel();
                                                    tableModel.setRowCount(row + 1);

                                                    //Sr No
                                                    tableLedger.setValueAt(row + 1, row, 0);

                                                    //Get Ledger name
                                                    k = 0;
                                                    while (k < ledger.size()) {
                                                        if (ledger.get(k).getLedger_id() == rs4.getLong("trans_ledgerid")) {
                                                            tableLedger.setValueAt(ledger.get(k).getLedger_name(), row, 1);
                                                        }
                                                        k++;
                                                    }

                                                    //Date
                                                    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                                                    tableLedger.setValueAt(dateformat.format(rs1.getDate("trans_date")), row, 2);

                                                    //VCH Type
                                                    if (rs1.getInt("trans_typeIndex") == 3) {
                                                        tableLedger.setValueAt("Payment", row, 3);
                                                    } else if (rs1.getInt("trans_typeIndex") == 6) {
                                                        tableLedger.setValueAt("Receipt", row, 3);
                                                    } else if (rs1.getInt("trans_typeIndex") == 1) {
                                                        tableLedger.setValueAt("Sales", row, 3);
                                                    } else if (rs1.getInt("trans_typeIndex") == 2) {
                                                        tableLedger.setValueAt("Purchase", row, 3);
                                                    }

                                                    //VCH No
                                                    tableLedger.setValueAt(rs1.getLong("trans_receiptNo"), row, 4);

                                                    //Creditted-Debitted Amount
                                                    if (rs4.getInt("trans_type") == 1) {
                                                        System.out.println("Testing Value at-19-02-2013:::" + rs4.getDouble("trans_amt"));
                                                        tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 6);
                                                        tableLedger.setValueAt("-", row, 5);
                                                    } else {
                                                        tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 5);
                                                        tableLedger.setValueAt("-", row, 6);
                                                    }
                                                }
                                                rs4.close();
                                            } else {
                                                //--------select ledger of opposite transaction type i.e.debit X Credit----------
                                                q = "select trans_ledgerId,trans_amt,trans_type,trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_type<>" + rs2.getLong("trans_type") + " and trans_index=" + rs2.getLong("trans_index") + "";
                                                ResultSet rs4 = st2.executeQuery(q);
                                                while (rs4.next()) {
                                                    int row = tableLedger.getRowCount();
                                                    tableModel = (DefaultTableModel) tableLedger.getModel();
                                                    tableModel.setRowCount(row + 1);

                                                    //Sr No
                                                    tableLedger.setValueAt(row + 1, row, 0);

                                                    //Get Ledger name
                                                    k = 0;
                                                    while (k < ledger.size()) {
                                                        if (ledger.get(k).getLedger_id() == rs4.getLong("trans_ledgerid")) {
                                                            tableLedger.setValueAt(ledger.get(k).getLedger_name(), row, 1);
                                                        }
                                                        k++;
                                                    }

                                                    //Date
                                                    SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                                                    tableLedger.setValueAt(dateformat.format(rs1.getDate("trans_date")), row, 2);

                                                    //VCH Type
                                                    if (rs1.getInt("trans_typeIndex") == 3) {
                                                        tableLedger.setValueAt("Payment", row, 3);
                                                    } else if (rs1.getInt("trans_typeIndex") == 6) {
                                                        tableLedger.setValueAt("Receipt", row, 3);
                                                    } else if (rs1.getInt("trans_typeIndex") == 1) {
                                                        tableLedger.setValueAt("Sales", row, 3);
                                                    } else if (rs1.getInt("trans_typeIndex") == 2) {
                                                        tableLedger.setValueAt("Purchase", row, 3);
                                                    }

                                                    //VCH No
                                                    tableLedger.setValueAt(rs1.getLong("trans_receiptNo"), row, 4);

                                                    //Creditted-Debitted Amount
                                                    if (rs4.getInt("trans_type") == 1) {
                                                        tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 6);
                                                        tableLedger.setValueAt("-", row, 5);
                                                    } else {
                                                        tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 5);
                                                        tableLedger.setValueAt("-", row, 6);
                                                    }
                                                }
                                                rs4.close();
                                            }

                                        }
                                        rs2.close();
                                    }
                                    rs1.close();

                                    //----------Count Total Debitted and creditted Amount---------
                                    if (tableLedger.getRowCount() > 0) {
                                        k = 0;
                                        double deb_qty = 00f;
                                        double cred_qty = 00f;
                                        System.out.println("TABLE COUNT::::::;" + tableLedger.getRowCount());
                                        while (k < tableLedger.getRowCount()) {
                                            System.out.println("TABLE VALUE AT i,3::::::;" + tableLedger.getValueAt(k, 3) != null);
                                            if (tableLedger.getValueAt(k, 3) != null) {
                                                if (tableLedger.getValueAt(k, 3).toString().equals("Receipt") || tableLedger.getValueAt(k, 3).toString().equals("Payment")) {
                                                    if (tableLedger.getValueAt(k, 5).toString().equals("-") == false && tableLedger.getValueAt(k, 5).toString().equals("") == false) {
                                                        deb_qty = deb_qty + Double.parseDouble(tableLedger.getValueAt(k, 5).toString());
                                                        // System.out.println("DEBIT QUANTITY::::::"+deb_qty);
                                                    }
                                                    if (tableLedger.getValueAt(k, 6).toString().equals("-") == false && tableLedger.getValueAt(k, 6).toString().equals("") == false) {
                                                        cred_qty = cred_qty + Double.parseDouble(tableLedger.getValueAt(k, 6).toString());
                                                    }

                                                } else if ((tableLedger.getValueAt(k, 3).toString().equals("Sales") || tableLedger.getValueAt(k, 3).toString().equals("Purchase"))) {
                                                    if (tableLedger.getValueAt(k, 5).toString().equals("-") == false && tableLedger.getValueAt(k, 5).toString().equals("") == false) {
                                                        deb_qty = deb_qty + Double.parseDouble(tableLedger.getValueAt(k, 5).toString());
                                                        System.out.println("DEBIT QUANTITY::::::" + deb_qty);
                                                    }
                                                    if (tableLedger.getValueAt(k, 6).toString().equals("-") == false && tableLedger.getValueAt(k, 6).toString().equals("") == false) {
                                                        cred_qty = cred_qty + Double.parseDouble(tableLedger.getValueAt(k, 6).toString());
                                                        System.out.println("CREDIT QUANTITY::::::" + cred_qty);
                                                    }
                                                }
                                            }
                                            k++;
                                        }

                                        labelTotalDebit.setText("" + decformat.format(deb_qty));
                                        labelTotalCredit.setText("" + decformat.format(cred_qty));
                                    } else {
                                        labelTotalDebit.setText("0");
                                        labelTotalCredit.setText("0");
                                    }
                                    //-----------------------------------------------------------

                                    //----------------calculate closing balance------------------
                                    o = Double.parseDouble(labelOpening.getText());
                                    cr = Double.parseDouble(labelTotalCredit.getText());
                                    d = Double.parseDouble(labelTotalDebit.getText());
                                    double cl = 00f;
                                    if (labelOType.getText().equalsIgnoreCase("Credit")) {
                                        cl = o + cr - d;
                                        labelClosing.setText("" + Math.abs(cl));
                                        if (cl < 0) {
                                            labelCType.setText("Debit");
                                        } else {
                                            labelCType.setText("Credit");
                                        }
                                    } else {
                                        cl = o + d - cr;
                                        labelClosing.setText("" + decformat.format(Math.abs(cl)));
                                        if (cl > 0) {
                                            labelCType.setText("Debit");
                                        } else {
                                            labelCType.setText("Credit");
                                        }
                                    }
                                    while (checkForThreadHaltRequest()) {
                                        thread.sleep(5000);
                                    }
                                    //-------------------------------------------------------------
                                } catch (SQLException ex) {
                                    //Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        } catch (Exception e) {
                            Logger.getLogger(LedgerReport.class.getName()).log(Level.SEVERE, null, e);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (tableLedger.getRowCount() > 0) {
                            btnPrintActionPerformed(null);
                        }

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    i++;
                }
            }
        });




        thread.start();
        System.out.println("Thread Running...");





        /*   for (int k = 0; k < jTable1.getSelectedRowCount(); k++) {
         System.out.println("--------->>btnShow K :"+k+"  " + jTable1.getValueAt(k, 0));
         if ((Boolean)jTable1.getValueAt(k, 0).toString().equalsIgnoreCase("false")) {
         System.out.println("in false---------->>" + jTable1.getValueAt(k, 0).toString());
         }else{
         System.out.println("In true---------->>" + jTable1.getValueAt(k, 0).toString());
         System.out.println("---------->>" + jTable1.getValueAt(k, 1).toString());
         tf.setText(jTable1.getValueAt(k, 1).toString());


         try {
         SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
         String date1 = f.format(dat1.getDate());
         String date2 = f.format(dat2.getDate());
         //   JOptionPane.showMessageDialog(this, date1);
         //  JOptionPane.showMessageDialog(this, date2);
         if (dat1.getDate() == null) {
         JOptionPane.showMessageDialog(this, "Invalid Date Entered");
         dat1.requestFocus();
         tfDatePicker.requestFocus();
         } else if (dat2.getDate() == null) {
         JOptionPane.showMessageDialog(this, "Invalid Date Entered");
         dat2.requestFocus();
         tfDatePicker1.requestFocus();
         } else if (date1.compareTo(date2) > 0) {
         JOptionPane.showMessageDialog(this, "Invalid Date selection");
         dat2.requestFocus();
         } //        if(comboLedger.getSelectedItem().equals("")) {
         //            JOptionPane.showMessageDialog(this, "Please select the Ledger");
         //            comboLedger.requestFocus();
         //        } else if(dat1.getDate()==null) {
         //            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
         //            dat1.requestFocus();
         //            tfDatePicker.requestFocus();
         //        } else if(dat2.getDate()==null) {
         //            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
         //            dat2.requestFocus();
         //            tfDatePicker1.requestFocus();
         //        } else if(dat1.getDate().getTime()>dat2.getDate().getTime()) {
         //            JOptionPane.showMessageDialog(this, "Invalid Date selection");
         //            dat2.requestFocus();
         //        } 
         else {
         panelLedgerDetails.setVisible(true);
         labelOType.setText("");
         labelCType.setText("");
         labelClosing.setText("0");
         labelOpening.setText("0");
         labelTotalCredit.setText("0");
         labelTotalDebit.setText("0");

         DecimalFormat decformat = new DecimalFormat("#.##");
         try {
         Connection conn = DatabaseConnection1.GetConnection();
         Statement st = conn.createStatement();
         Statement st1 = conn.createStatement();
         Statement st2 = conn.createStatement();

         //                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
         //                String date1 = f.format(dat1.getDate());
         //                String date2 = f.format(dat2.getDate());

         //-----------------------Table: Ledger--------------------------------------------
         DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
         tableModel.setRowCount(0);
         tableModel.setColumnCount(7);

         //find ledger ID
         k = 0;
         long led_id = 0;
         while (ledger.size() > k) {
         if (ledger.get(k).getLedger_name().equals(tf.getText())) {
         led_id = ledger.get(k).getLedger_id();
         }
         k++;
         }

         double o = 00f;
         double cr = 00f;
         double d = 00f;

         DecimalFormat df = new DecimalFormat("#.##");

         q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=6)";
         rs1 = st.executeQuery(q);
         while (rs1.next()) {
         cr = cr + rs1.getDouble("totalCred");
         }
         rs1.close();

         //---------------new query------------------
         q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=1)";
         rs1 = st.executeQuery(q);
         while (rs1.next()) {
         cr = cr + rs1.getDouble("totalCred");
         }
         rs1.close();
         //------------------------------------------

         q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=3)";
         rs1 = st.executeQuery(q);
         while (rs1.next()) {
         d = d + rs1.getDouble("totalDeb");
         }
         rs1.close();

         //--------------new query-------------------
         q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_ledgerId=" + led_id + " and trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=2)";
         rs1 = st.executeQuery(q);
         while (rs1.next()) {
         d = d + rs1.getDouble("totalDeb");
         }
         rs1.close();
         //------------------------------------------

         //System.out.println("d(1): "+d);

         //--------------find if ledger is under Cash In Hand-----------
         long g_id = 0;
         q = "select group_id from tblgroup where group_name='Cash In Hand'";
         rs1 = st.executeQuery(q);
         if (rs1.next()) {
         g_id = rs1.getLong("group_id");
         }

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
         if (ind == cash_ids.size() - 1) {
         flg = 1;
         }
         ind++;
         } while (flg == 0);
         //-------------------------------------------------------------

         q = "select ledger_openingBalance,opening_type,ledger_under from tblledger where ledger_id=" + led_id + "";
         rs1 = st.executeQuery(q);
         if (rs1.next()) {
         k = 0;
         while (k < cash_ids.size()) {
         if (rs1.getLong("ledger_under") == cash_ids.get(k)) {
         flag_cash_in_hand = 1;
         break;
         }
         k++;
         }

         o = rs1.getDouble("ledger_openingBalance");
         if (rs1.getInt("opening_type") == 2) {
         o = o + cr - d;

         labelOpening.setText("" + df.format(Math.abs(o)));
         if (o < 0) {
         labelOType.setText("Debit");
         } else {
         labelOType.setText("Credit");
         }
         } else {
         o = o + d - cr;
         labelOpening.setText("" + df.format(Math.abs(o)));
         if (o < 0) {
         labelOType.setText("Debit");
         } else {
         labelOType.setText("Credit");
         }
         }
         }

         //----------opening balance---------
         q = "select ledger_openingBalance,opening_type from tblledger where ledger_id=" + led_id + "";
         rs3 = st2.executeQuery(q);
         if (rs3.next()) {
         double op = rs3.getDouble("ledger_openingBalance");
         labelOpening.setText("" + df.format(op));
         if (rs3.getInt("opening_type") == 1) {
         labelOType.setText("Debit");
         } else {
         labelOType.setText("Credit");
         }
         }
         rs3.close();
         //----------------------------------

         q = "select trans_id,trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex<>7 order by trans_date";
         rs1 = st.executeQuery(q);
         while (rs1.next()) {
         q = "select distinct(trans_type),trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_ledgerId=" + led_id + "";
         rs2 = st1.executeQuery(q);
         while (rs2.next()) {

         if (rs1.getInt("trans_typeIndex") == 1 || rs1.getInt("trans_typeIndex") == 2) {
         //--------select ledger of opposite transaction type i.e.debit X Credit----------
         q = "select trans_ledgerId,trans_amt,trans_type,trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_type<>" + rs2.getLong("trans_type") + "";
         ResultSet rs4 = st2.executeQuery(q);
         while (rs4.next()) {
         int row = tableLedger.getRowCount();
         tableModel = (DefaultTableModel) tableLedger.getModel();
         tableModel.setRowCount(row + 1);

         //Sr No
         tableLedger.setValueAt(row + 1, row, 0);

         //Get Ledger name
         k = 0;
         while (k < ledger.size()) {
         if (ledger.get(k).getLedger_id() == rs4.getLong("trans_ledgerid")) {
         tableLedger.setValueAt(ledger.get(k).getLedger_name(), row, 1);
         }
         k++;
         }

         //Date
         SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
         tableLedger.setValueAt(dateformat.format(rs1.getDate("trans_date")), row, 2);

         //VCH Type
         if (rs1.getInt("trans_typeIndex") == 3) {
         tableLedger.setValueAt("Payment", row, 3);
         } else if (rs1.getInt("trans_typeIndex") == 6) {
         tableLedger.setValueAt("Receipt", row, 3);
         } else if (rs1.getInt("trans_typeIndex") == 1) {
         tableLedger.setValueAt("Sales", row, 3);
         } else if (rs1.getInt("trans_typeIndex") == 2) {
         tableLedger.setValueAt("Purchase", row, 3);
         }

         //VCH No
         tableLedger.setValueAt(rs1.getLong("trans_receiptNo"), row, 4);

         //Creditted-Debitted Amount
         if (rs4.getInt("trans_type") == 1) {
         System.out.println("Testing Value at-19-02-2013:::" + rs4.getDouble("trans_amt"));
         tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 6);
         tableLedger.setValueAt("-", row, 5);
         } else {
         tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 5);
         tableLedger.setValueAt("-", row, 6);
         }
         }
         rs4.close();
         } else {
         //--------select ledger of opposite transaction type i.e.debit X Credit----------
         q = "select trans_ledgerId,trans_amt,trans_type,trans_index from tbltransactionledger where trans_id=" + rs1.getLong("trans_id") + " and trans_type<>" + rs2.getLong("trans_type") + " and trans_index=" + rs2.getLong("trans_index") + "";
         ResultSet rs4 = st2.executeQuery(q);
         while (rs4.next()) {
         int row = tableLedger.getRowCount();
         tableModel = (DefaultTableModel) tableLedger.getModel();
         tableModel.setRowCount(row + 1);

         //Sr No
         tableLedger.setValueAt(row + 1, row, 0);

         //Get Ledger name
         k = 0;
         while (k < ledger.size()) {
         if (ledger.get(k).getLedger_id() == rs4.getLong("trans_ledgerid")) {
         tableLedger.setValueAt(ledger.get(k).getLedger_name(), row, 1);
         }
         k++;
         }

         //Date
         SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
         tableLedger.setValueAt(dateformat.format(rs1.getDate("trans_date")), row, 2);

         //VCH Type
         if (rs1.getInt("trans_typeIndex") == 3) {
         tableLedger.setValueAt("Payment", row, 3);
         } else if (rs1.getInt("trans_typeIndex") == 6) {
         tableLedger.setValueAt("Receipt", row, 3);
         } else if (rs1.getInt("trans_typeIndex") == 1) {
         tableLedger.setValueAt("Sales", row, 3);
         } else if (rs1.getInt("trans_typeIndex") == 2) {
         tableLedger.setValueAt("Purchase", row, 3);
         }

         //VCH No
         tableLedger.setValueAt(rs1.getLong("trans_receiptNo"), row, 4);

         //Creditted-Debitted Amount
         if (rs4.getInt("trans_type") == 1) {
         tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 6);
         tableLedger.setValueAt("-", row, 5);
         } else {
         tableLedger.setValueAt(decformat.format(rs4.getDouble("trans_amt")), row, 5);
         tableLedger.setValueAt("-", row, 6);
         }
         }
         rs4.close();
         }

         }
         rs2.close();
         }
         rs1.close();

         //----------Count Total Debitted and creditted Amount---------
         if (tableLedger.getRowCount() > 0) {
         k = 0;
         double deb_qty = 00f;
         double cred_qty = 00f;
         System.out.println("TABLE COUNT::::::;" + tableLedger.getRowCount());
         while (k < tableLedger.getRowCount()) {
         System.out.println("TABLE VALUE AT i,3::::::;" + tableLedger.getValueAt(k, 3) != null);
         if (tableLedger.getValueAt(k, 3) != null) {
         if (tableLedger.getValueAt(k, 3).toString().equals("Receipt") || tableLedger.getValueAt(k, 3).toString().equals("Payment")) {
         if (tableLedger.getValueAt(k, 5).toString().equals("-") == false && tableLedger.getValueAt(k, 5).toString().equals("") == false) {
         deb_qty = deb_qty + Double.parseDouble(tableLedger.getValueAt(k, 5).toString());
         // System.out.println("DEBIT QUANTITY::::::"+deb_qty);
         }
         if (tableLedger.getValueAt(k, 6).toString().equals("-") == false && tableLedger.getValueAt(k, 6).toString().equals("") == false) {
         cred_qty = cred_qty + Double.parseDouble(tableLedger.getValueAt(k, 6).toString());
         }

         } else if ((tableLedger.getValueAt(k, 3).toString().equals("Sales") || tableLedger.getValueAt(k, 3).toString().equals("Purchase"))) {
         if (tableLedger.getValueAt(k, 5).toString().equals("-") == false && tableLedger.getValueAt(k, 5).toString().equals("") == false) {
         deb_qty = deb_qty + Double.parseDouble(tableLedger.getValueAt(k, 5).toString());
         System.out.println("DEBIT QUANTITY::::::" + deb_qty);
         }
         if (tableLedger.getValueAt(k, 6).toString().equals("-") == false && tableLedger.getValueAt(k, 6).toString().equals("") == false) {
         cred_qty = cred_qty + Double.parseDouble(tableLedger.getValueAt(k, 6).toString());
         System.out.println("CREDIT QUANTITY::::::" + cred_qty);
         }
         }
         }
         k++;
         }

         labelTotalDebit.setText("" + decformat.format(deb_qty));
         labelTotalCredit.setText("" + decformat.format(cred_qty));
         } else {
         labelTotalDebit.setText("0");
         labelTotalCredit.setText("0");
         }
         //-----------------------------------------------------------

         //----------------calculate closing balance------------------
         o = Double.parseDouble(labelOpening.getText());
         cr = Double.parseDouble(labelTotalCredit.getText());
         d = Double.parseDouble(labelTotalDebit.getText());
         double cl = 00f;
         if (labelOType.getText().equalsIgnoreCase("Credit")) {
         cl = o + cr - d;
         labelClosing.setText("" + Math.abs(cl));
         if (cl < 0) {
         labelCType.setText("Debit");
         } else {
         labelCType.setText("Credit");
         }
         } else {
         cl = o + d - cr;
         labelClosing.setText("" + decformat.format(Math.abs(cl)));
         if (cl > 0) {
         labelCType.setText("Debit");
         } else {
         labelCType.setText("Credit");
         }
         }
         //-------------------------------------------------------------
         } catch (SQLException ex) {
         Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
         }
         }

         } catch (Exception e) {
         Logger.getLogger(LedgerReport.class.getName()).log(Level.SEVERE, null, e);
         }
         try {
         Thread.sleep(1000);
         } catch (InterruptedException ex) {
         Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
         }
         //    btnPrintActionPerformed(null);
         try {
         Thread.sleep(10000);
         } catch (InterruptedException ex) {
         Logger.getLogger(LedgerReportAdvance.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         }*/
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnShowKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnShowActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfDatePicker1.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            tfDatePicker1.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableLedger.getRowCount() > 0) {
                tableLedger.requestFocus();
                tableLedger.changeSelection(0, 0, false, false);
            } else {
                btnShow.requestFocus();
            }
        }
    }//GEN-LAST:event_btnShowKeyPressed

    private void dat2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dat2FocusLost
        if (dat2.getDate() == null) {
            Calendar currentDate = Calendar.getInstance();
            dat2.setDate(currentDate.getTime());
        }
    }//GEN-LAST:event_dat2FocusLost

    private void dat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dat2KeyPressed
        /*        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (dat2.getDate()==null) {
         dat2.requestFocus();
         } else {
         btnShowActionPerformed(null);
         }
         }
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
         dat1.requestFocus();
         }
         if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
         btnShow.requestFocus();
         }*/

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (dat2.getDate() == null) {
                dat2.requestFocus();
            } else {
                btnShowActionPerformed(null);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dat1.requestFocus();
        }
    }//GEN-LAST:event_dat2KeyPressed

    private void dat1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dat1FocusLost
        if (dat1.getDate() == null) {
            Calendar currentDate = Calendar.getInstance();
            dat1.setDate(currentDate.getTime());
        }
    }//GEN-LAST:event_dat1FocusLost

    private void dat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dat1KeyPressed
        /*     if (evt.getKeyCode() == KeyEvent.VK_ENTER||evt.getKeyCode() == KeyEvent.VK_RIGHT) {
         if (dat1.getDate()==null) {
         dat1.requestFocus();
         } else {
         dat2.requestFocus();
         }
         }
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
         comboLedger.requestFocus();
         }*/
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (dat1.getDate() == null) {
                dat1.requestFocus();
            } else {
                dat2.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboLedger.requestFocus();
        }
    }//GEN-LAST:event_dat1KeyPressed

    private void comboLedgerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboLedgerKeyPressed
        /*     if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (comboLedger.getSelectedItem().equals("")) {
         comboLedger.showPopup();
         } else {
         dat1.requestFocus();
         }
         }
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
         btnBackActionPerformed(null);
         }*/
    }//GEN-LAST:event_comboLedgerKeyPressed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass mainClass = new MainClass();
            mainClass.menuselection(3);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Group_Create.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void comboLedgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLedgerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLedgerActionPerformed

    private void btnBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBackKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnShow.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnPause.requestFocus();
        }
    }//GEN-LAST:event_btnBackKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

        btnPrint.setVisible(false);
        comboLedger.showPopup();
        if (backAction == 2) {

            tfDatePicker.setText(date);
            btnShowActionPerformed(null);
        } else {

            Calendar currentDate = Calendar.getInstance();
            dat1.setDate(currentDate.getTime());
            dat2.setDate(currentDate.getTime());
            JTableHeader header = tableLedger.getTableHeader();
            header.setBackground(Color.yellow);

            tableLedger.setRowSelectionAllowed(true);

            btnShow.setMnemonic(KeyEvent.VK_S);
            btnPrint.setMnemonic(KeyEvent.VK_P);
            btnBack.setMnemonic(KeyEvent.VK_B);




            DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(7);


            panelLedgerDetails.setVisible(false);
            //comboLedger.requestFocus();
        }

    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        backAction = 2;
        date = tfDatePicker.getText();
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        pauseClickCheck = 1;
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResumeActionPerformed
        // TODO add your handling code here:
        pauseClickCheck = 0;
    }//GEN-LAST:event_btnResumeActionPerformed

    private void btnPauseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPauseKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnResume.requestFocus();
        }
    }//GEN-LAST:event_btnPauseKeyPressed

    private void btnResumeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnResumeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPause.requestFocus();
        }
    }//GEN-LAST:event_btnResumeKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Close;
    private javax.swing.JLabel Open;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnResume;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox comboLedger;
    private com.toedter.calendar.JDateChooser dat1;
    private com.toedter.calendar.JDateChooser dat2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelCType;
    private javax.swing.JLabel labelClosing;
    private javax.swing.JLabel labelOType;
    private javax.swing.JLabel labelOpening;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JPanel panelLedgerDetails;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTable tableLedger;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboLedger.setModel(mdl);
        comboLedger.setSelectedIndex(-1);
        comboLedger.showPopup();
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

    public boolean checkForThreadHaltRequest() {

        if (pauseClickCheck == 1) {
            newflag = true;
        } else {
            newflag = false;
        }
        return newflag;

    }
}
