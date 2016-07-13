package gen.display.report;

import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.chalan.ChalanForm1;
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
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.*;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class StockItemReport extends javax.swing.JInternalFrame {

    ArrayList<TemporaryClass> SI = new ArrayList<TemporaryClass>();
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    JTextField tf;
    private final Vector<String> v = new Vector<String>();
    //ArrayList<String> GroupItems=new ArrayList<String>();
    private final JTextField tfDatePicker;
    private final JTextField tfDatePicker1;

    public StockItemReport(String s) {
        setClosable(true);
        initComponents();
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tfDatePicker = (JTextField) dat1.getComponent(1);
        // tfDatePickerCal=(JTextField)dat1.getComponent(0);
        tfDatePicker1 = (JTextField) dat2.getComponent(1);
        System.out.println(tfDatePicker.toString());



        //   tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                // btnShow.requestFocus();
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tfDatePicker1.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    comboStockItem.requestFocus();
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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
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
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelMain = new javax.swing.JPanel();
        panelItemDetails = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableItem = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelTotalCredit = new javax.swing.JLabel();
        labelTotalDebit = new javax.swing.JLabel();
        Open = new javax.swing.JLabel();
        Close = new javax.swing.JLabel();
        labelOpeningDebit = new javax.swing.JLabel();
        labelClosingDebit = new javax.swing.JLabel();
        labelOType = new javax.swing.JLabel();
        labelCType = new javax.swing.JLabel();
        labelOpeningCredit = new javax.swing.JLabel();
        labelClosingCredit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        dat2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dat1 = new com.toedter.calendar.JDateChooser();
        comboStockItem = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

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
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelItemDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "Sr.No", "Particulars","Date", "VCH Type", "VCH No","Debit","Credit"
            }
        )
        {
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        }

    );
    tableItem.getTableHeader().setResizingAllowed(false);
    tableItem.getTableHeader().setReorderingAllowed(false);
    tableItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tableItemMouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(tableItem);

    jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel4.setText("Total ");

    jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel5.setText("Total Credit");

    labelTotalCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelTotalCredit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelTotalCredit.setText("jLabel6");

    labelTotalDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelTotalDebit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelTotalDebit.setText("jLabel6");

    Open.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    Open.setText("Opening Balance");

    Close.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    Close.setText("Closing Balance");

    labelOpeningDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelOpeningDebit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelOpeningDebit.setText("jLabel8");

    labelClosingDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelClosingDebit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelClosingDebit.setText("jLabel9");

    labelOType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelOType.setText("jLabel6");

    labelCType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelCType.setText("jLabel6");

    labelOpeningCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelOpeningCredit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelOpeningCredit.setText("jLabel6");

    labelClosingCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    labelClosingCredit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    labelClosingCredit.setText("jLabel7");

    javax.swing.GroupLayout panelItemDetailsLayout = new javax.swing.GroupLayout(panelItemDetails);
    panelItemDetails.setLayout(panelItemDetailsLayout);
    panelItemDetailsLayout.setHorizontalGroup(
        panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelItemDetailsLayout.createSequentialGroup()
            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItemDetailsLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelItemDetailsLayout.createSequentialGroup()
                    .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelItemDetailsLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelOType)
                                .addComponent(labelCType))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Open, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(26, 26, 26)
                            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelClosingDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelOpeningDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItemDetailsLayout.createSequentialGroup()
                            .addGap(410, 410, 410)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(62, 62, 62)
                            .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelClosingCredit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItemDetailsLayout.createSequentialGroup()
                            .addGap(0, 4, Short.MAX_VALUE)
                            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelTotalDebit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelOpeningCredit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGap(41, 41, 41))
    );
    panelItemDetailsLayout.setVerticalGroup(
        panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelItemDetailsLayout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelTotalCredit)
                .addComponent(labelTotalDebit)
                .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Open)
                .addComponent(labelOpeningDebit)
                .addComponent(labelOType)
                .addComponent(labelOpeningCredit))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(Close)
                .addComponent(labelClosingDebit)
                .addComponent(labelCType)
                .addComponent(labelClosingCredit))
            .addContainerGap(23, Short.MAX_VALUE))
    );

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    jLabel1.setText("Stock Item Report");

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel1.setPreferredSize(new java.awt.Dimension(782, 111));

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

    comboStockItem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    comboStockItem.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            comboStockItemFocusLost(evt);
        }
    });
    comboStockItem.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            comboStockItemKeyPressed(evt);
        }
    });

    jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
    jLabel3.setText("Stock Item");

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

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnShow)
                    .addGap(18, 18, 18)
                    .addComponent(btnPrint)
                    .addGap(18, 18, 18)
                    .addComponent(btnBack))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(225, 225, 225)
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addComponent(comboStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboStockItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShow)
                    .addComponent(btnPrint)
                    .addComponent(btnBack))
                .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(20, 20, 20))
    );

    javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
    panelMain.setLayout(panelMainLayout);
    panelMainLayout.setHorizontalGroup(
        panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMainLayout.createSequentialGroup()
            .addGap(276, 276, 276)
            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(panelItemDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(156, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(518, 518, 518))
    );
    panelMainLayout.setVerticalGroup(
        panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMainLayout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(panelItemDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(83, Short.MAX_VALUE))
    );

    jScrollPane1.setViewportView(panelMain);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1199, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        //  Calendar currentDate = Calendar.getInstance();
        //  SimpleDateFormat todayformat = new SimpleDateFormat("yyyy/MM/dd");
        try {





            if (tf.getText().toString().trim().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select the StockItem");
                comboStockItem.requestFocus();
            } else if (dat1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Invalid Date Entered");
                dat1.requestFocus();
                tfDatePicker.requestFocus();
            } else if (dat2.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Invalid Date Entered");
                dat2.requestFocus();
                tfDatePicker1.requestFocus();
            } else if (dat1.getDate().compareTo(dat2.getDate()) > 0) {
                JOptionPane.showMessageDialog(this, "Invalid Date selection");
                tfDatePicker1.requestFocus();
            } else if (!checkElementPresence(v, tf.getText().trim())) {
                JOptionPane.showMessageDialog(this, "Please select valid Stock Item");
                //    scrollpaneMain.getVerticalScrollBar().setValue(0);  //go to top of scrollpane
                comboStockItem.requestFocus();
            } //        if (comboStockItem.getSelectedItem().equals("")) {
            //            JOptionPane.showMessageDialog(this, "Please select the Stock Item");
            //            comboStockItem.requestFocus();
            //        } else if (dat1.getDate() == null) {
            //            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            //            dat1.requestFocus();
            //            tfDatePicker.requestFocus();
            //        } else if (dat2.getDate() == null) {
            //            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            //            dat2.requestFocus();
            //            tfDatePicker1.requestFocus();
            //        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            //            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            //            dat2.requestFocus();
            //        } 
            else {
                panelItemDetails.setVisible(true);
                labelOType.setText("");
                labelCType.setText("");
                labelClosingDebit.setText("0");
                labelClosingCredit.setText("0");
                labelOpeningCredit.setText("0");
                labelOpeningDebit.setText("0");
                labelTotalDebit.setText("0");
                labelTotalCredit.setText("0");
                Connection conn = null;
                try {
                    conn = DatabaseConnection1.GetConnection();
                    Statement st1 = conn.createStatement();
                    Statement st2 = conn.createStatement();
                    Statement st3 = conn.createStatement();
                    Statement st4 = conn.createStatement();

//                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
//                String date1 = f.format(dat1.getDate());
//                String date2 = f.format(dat2.getDate());
                    SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                    String date1 = f.format(dat1.getDate());
                    String date2 = f.format(dat2.getDate());


                    //-----------------------Table: Item--------------------------------------------
                    DefaultTableModel tableModel = (DefaultTableModel) tableItem.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(7);

                    //find stock item id
                    i = 0;
                    long item_id = 0;
                    while (SI.size() > i) {
                        if (SI.get(i).getStock_item_name().equals(comboStockItem.getSelectedItem())) {
                            item_id = SI.get(i).getStock_item_id();
                        }
                        i++;
                    }

                    double openBal = 00f;
                    double creditBal = 00f;
                    double debitBal = 00f;

                    q = "select sum(invtrans_qty) as totalCred from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=2))";
                    rs1 = st1.executeQuery(q);
                    while (rs1.next()) {
                        creditBal = creditBal + rs1.getDouble("totalCred");
                    }

                    q = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date<'" + date1 + "' and trans_typeIndex=1))";
                    rs1 = st1.executeQuery(q);
                    while (rs1.next()) {
                        debitBal = debitBal + rs1.getDouble("totalDeb");
                    }

                    q = "select si_openingBalance from tblstockitem where si_id=" + item_id + "";
                    rs1 = st1.executeQuery(q);
                    if (rs1.next()) {
                        openBal = rs1.getDouble("si_openingBalance");
                        openBal = openBal + creditBal - debitBal;
//			labelOpeningCredit.setText("" + Math.abs(openBal));
                        if (openBal < 0) {
                            labelOpeningDebit.setText("" + Math.abs(openBal));
                            labelOpeningCredit.setText("" + 0);
                            labelOType.setText("Debit");
                        } else {
                            labelOType.setText("Credit");
                            labelOpeningCredit.setText("" + Math.abs(openBal));
                            labelOpeningDebit.setText("" + 0);
                        }
                    }

                    q = "select trans_id,trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' order by trans_date";
                    rs1 = st1.executeQuery(q);
                    while (rs1.next()) {
                        q = "select invtrans_id,invtrans_type from tblinventorytransaction where trans_id=" + rs1.getLong("trans_id") + "";
                        rs2 = st2.executeQuery(q);
                        if (rs2.next()) {

                            q = "select invtrans_qty from tblinventorytransactionitems where invtrans_id=" + rs2.getLong("invtrans_id") + " and invtrans_itemId=" + item_id + "";
                            rs3 = st3.executeQuery(q);
                            while (rs3.next()) {
                                int row = tableItem.getRowCount();
                                tableModel = (DefaultTableModel) tableItem.getModel();
                                tableModel.setRowCount(row + 1);

                                //Sr No
                                tableItem.setValueAt(row + 1, row, 0);

//				//Get Item name
//				q = "select si_name,si_openingBalance from tblstockitem where si_id=" + item_id + "";
//				rs4 = st4.executeQuery(q);
//				if (rs4.next()) {
//				    tableItem.setValueAt(rs4.getString("si_name"), row, 1);
//				    //----------opening balance---------
//				    labelOpeningDebit.setText("" + Constants.DECIMAL_FORMAT.format(rs4.getLong("si_openingBalance")));
//				    labelOpeningCredit.setText(""+0);
//				    labelOType.setText("Credit");
//				    //----------------------------------
//				}

                                //Date
                                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                                tableItem.setValueAt(dateformat.format(rs1.getDate("trans_date")), row, 2);

                                //VCH Type
                                if (rs1.getInt("trans_typeIndex") == 1) {
                                    tableItem.setValueAt("Sales", row, 3);
                                } else if (rs1.getInt("trans_typeIndex") == 2) {
                                    tableItem.setValueAt("Purchase", row, 3);
                                } else if (rs1.getInt("trans_typeIndex") == 7) {
                                    tableItem.setValueAt("Challan", row, 3);
                                }

                                //VCH No
                                tableItem.setValueAt(rs1.getLong("trans_receiptNo"), row, 4);

                                //Debitted Amount
                                if (rs2.getInt("invtrans_type") == 1) {
                                    tableItem.setValueAt(rs3.getLong("invtrans_qty"), row, 5);
                                    tableItem.setValueAt("-", row, 6);
                                } else if (rs2.getInt("invtrans_type") == 2) {
                                    tableItem.setValueAt(rs3.getLong("invtrans_qty"), row, 6);
                                    tableItem.setValueAt("-", row, 5);
                                } else if (rs2.getInt("invtrans_type") == 7) {
                                    tableItem.setValueAt(rs3.getLong("invtrans_qty"), row, 5);
                                    tableItem.setValueAt("-", row, 6);
                                }

                            }
                        }
                    }
                    //Count Total Debitted Amount
                    if (tableItem.getRowCount() > 0) {
                        i = 0;
                        long deb_qty = 0;
                        long cred_qty = 0;
                        while (i < tableItem.getRowCount()) {
                            if (tableItem.getValueAt(i, 6).toString().equals("-") == false && tableItem.getValueAt(i, 6).toString().equals("") == false) {
                                deb_qty = deb_qty + Long.parseLong(tableItem.getValueAt(i, 6).toString());
                            }
                            if (tableItem.getValueAt(i, 5).toString().equals("-") == false && tableItem.getValueAt(i, 5).toString().equals("") == false) {
                                cred_qty = cred_qty + Long.parseLong(tableItem.getValueAt(i, 5).toString());
                            }
                            i++;
                        }
                        labelTotalDebit.setText("" + deb_qty);
                        labelTotalCredit.setText("" + cred_qty);
                    } else {
                        labelTotalDebit.setText("0");
                        labelTotalCredit.setText("0");
                    }

                    System.out.println("Credit value-------------" + labelOpeningDebit.getText().toString().trim().equals("0"));

                    if (labelOpeningDebit.getText().toString().trim().equals("0.0")) {
                        openBal = Double.parseDouble(labelOpeningDebit.getText());
                    } else {
                        openBal = Double.parseDouble(labelOpeningCredit.getText());
                    }
                    creditBal = Double.parseDouble(labelTotalDebit.getText());

                    debitBal = Double.parseDouble(labelTotalCredit.getText());

//                     debitBal= Double.parseDouble(labelTotalDebit.getText());
//		    creditBal = Double.parseDouble(labelTotalCredit.getText());
                    double cl = 00f;


                    System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRR --   openBal" + openBal);
                    System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRR --  creditBal" + creditBal);
                    System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRR -- debitBal" + debitBal);
                    if (labelOType.getText().equalsIgnoreCase("Credit")) {
                        cl = openBal + creditBal - debitBal;
//                        labelClosingCredit.setText("" + Math.abs(cl));
                        if (cl < 0) {
                            labelCType.setText("Debit");
                            labelClosingDebit.setText("" + Math.abs(cl));
                        } else {
                            labelCType.setText("Credit");
                            labelClosingCredit.setText("" + Math.abs(cl));
                        }
                    } else {
                        cl = openBal + debitBal - creditBal;
                        labelClosingDebit.setText("" + Math.abs(cl));
                        if (cl > 0) {
                            labelCType.setText("Debit");
                            labelClosingDebit.setText("" + Math.abs(cl));
                        } else {
                            labelCType.setText("Credit");
                            labelClosingCredit.setText("" + Math.abs(cl));
                        }
                    }


                    //--------------------------------------------------------------------------------
                } catch (SQLException ex) {
                    Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, e);
        }

        setColumnWidth(tableItem);
}//GEN-LAST:event_btnShowActionPerformed

    private void dat2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dat2FocusLost
        if (dat2.getDate() == null) {
            Calendar currentDate = Calendar.getInstance();
            dat2.setDate(currentDate.getTime());
        }
}//GEN-LAST:event_dat2FocusLost

    private void dat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dat2KeyPressed
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (dat1.getDate() == null) {
                dat1.requestFocus();
            } else {
                dat2.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboStockItem.requestFocus();
        }
}//GEN-LAST:event_dat1KeyPressed

    private void comboStockItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboStockItemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (comboStockItem.getSelectedItem().equals("")) {
                comboStockItem.showPopup();
            } else {
                dat1.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
}//GEN-LAST:event_comboStockItemKeyPressed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            MainClass m = new MainClass();
            m.menuselection(3);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        labelOType.setVisible(false);
        labelCType.setVisible(false);
        jLabel5.setVisible(false);
        Calendar currentDate = Calendar.getInstance();
        dat1.setDate(currentDate.getTime());
        dat2.setDate(currentDate.getTime());

        JTableHeader header = tableItem.getTableHeader();
        header.setBackground(Color.yellow);

        tableItem.setRowSelectionAllowed(true);

        btnShow.setMnemonic(KeyEvent.VK_S);
        btnPrint.setMnemonic(KeyEvent.VK_P);
        btnBack.setMnemonic(KeyEvent.VK_B);
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();

            q = "select si_id,si_name from tblstockitem";
            rs1 = st.executeQuery(q);
            comboStockItem.addItem("");
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            comboStockItem.setEditable(true);
            tf = (JTextField) comboStockItem.getEditor().getEditorComponent();

            while (rs1.next()) {
                TemporaryClass t1 = new TemporaryClass();
                t1.setStock_item_id(Integer.parseInt(rs1.getString("si_id")));
                t1.setStock_item_name(rs1.getString("si_name"));
                SI.add(t1);
                // comboStockItem.addItem(t1.getStock_item_name());

                v.add(rs1.getString("si_name"));

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
                comboStockItem.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboStockItem.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboStockItem.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboStockItem.showPopup();
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
                                        comboStockItem.setSelectedIndex(-1);
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
                                if (comboStockItem.getSelectedItem().equals("")) {
                                    //JOptionPane.showMessageDialog(comboStockItem, "Please Select Stock Item");
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
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
            DefaultTableModel tableModel = (DefaultTableModel) tableItem.getModel();
            tableModel.setRowCount(0);
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        panelItemDetails.setVisible(false);
        comboStockItem.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        printFunction();

    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPrintKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//           btnPrintActionPerformed(null);
//        }
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
            if (tableItem.getRowCount() > 0) {
                tableItem.requestFocus();
                tableItem.changeSelection(0, 0, false, false);
            } else {
                btnPrint.requestFocus();
            }
        }
    }//GEN-LAST:event_btnPrintKeyPressed

    private void btnShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnShowKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnShowActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfDatePicker1.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            dat2.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableItem.getRowCount() > 0) {
                tableItem.requestFocus();
                tableItem.changeSelection(0, 0, false, false);
            } else {
                btnShow.requestFocus();
            }
        }
    }//GEN-LAST:event_btnShowKeyPressed

    private void btnBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBackKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnShow.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableItem.getRowCount() > 0) {
                tableItem.requestFocus();
                tableItem.changeSelection(0, 0, false, false);
            } else {
                btnBack.requestFocus();
            }
        }
    }//GEN-LAST:event_btnBackKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        //comboStockItem.setPopupVisible(true);
    }//GEN-LAST:event_formInternalFrameActivated

    private void comboStockItemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboStockItemFocusLost
        // TODO add your handling code here:
        comboStockItem.hidePopup();
    }//GEN-LAST:event_comboStockItemFocusLost

    private void tableItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableItemMouseClicked
        // TODO add your handling code here:
        int row = tableItem.getSelectedRow();
        if (tableItem.getValueAt(row, 2) != null) {
            if (tableItem.getValueAt(row, 3).toString().equalsIgnoreCase("Receipt")) {
                try {
                    String id = tableItem.getValueAt(tableItem.getSelectedRow(), 4).toString();
                    ReceiptDTO receiptDTO = new ReceiptDTO();
                    ReceiptForm r = new ReceiptForm(receiptDTO, true);
                    r.loadEditForm(id);
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
                } catch (Exception ex) {
                    Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tableItem.getValueAt(row, 3).toString().equalsIgnoreCase("Payment")) {
                try {
                    String id = tableItem.getValueAt(tableItem.getSelectedRow(), 4).toString();
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
                    Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tableItem.getValueAt(row, 3).toString().equalsIgnoreCase("Sales")) {
                try {
                    String id = tableItem.getValueAt(tableItem.getSelectedRow(), 4).toString();
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
                    Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tableItem.getValueAt(row, 3).toString().equalsIgnoreCase("Purchase")) {
                try {
                    String id = tableItem.getValueAt(tableItem.getSelectedRow(), 4).toString();
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
                    Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tableItem.getValueAt(row, 3).toString().equalsIgnoreCase("Challan")) {
                try {
                    String id = tableItem.getValueAt(tableItem.getSelectedRow(), 4).toString();
                    ChalanDTO chalanDTO = new ChalanDTO();
                    ChalanForm1 c = new ChalanForm1(chalanDTO, true);
                    c.loadEditForm(id);
                    c.pack();
                    c.setVisible(true);
                    this.getParent().add(c);
                    this.getParent().setVisible(true);
                    Dimension desktopSize = this.getParent().getSize();
                    Dimension jInternalFrameSize = c.getSize();
                    //                        gc.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
                    //                        (desktopSize.height- jInternalFrameSize.height)/2);
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
                } catch (Exception ex) {
                    Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_tableItemMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Close;
    private javax.swing.JLabel Open;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox comboStockItem;
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
    private javax.swing.JLabel labelCType;
    private javax.swing.JLabel labelClosingCredit;
    private javax.swing.JLabel labelClosingDebit;
    private javax.swing.JLabel labelOType;
    private javax.swing.JLabel labelOpeningCredit;
    private javax.swing.JLabel labelOpeningDebit;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JPanel panelItemDetails;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTable tableItem;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboStockItem.setModel(mdl);
        comboStockItem.setSelectedIndex(-1);
//        comboStockItem.setPopupVisible(true);
        comboStockItem.showPopup();
        tf.setText(str);
        //   tf.selectAll();
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
            if (tf.getText().trim().equals(comboStockItem.getItemAt(i))) {
                return true;
            }
        }
        return false;
    }

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(50);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(50);

        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(250);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(250);

        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(75);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(75);

        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(75);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(75);
        //passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(4).setMinWidth(50);
        passedTable.getColumnModel().getColumn(4).setMaxWidth(50);
        passedTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(5).setMinWidth(75);
        passedTable.getColumnModel().getColumn(5).setMaxWidth(75);
        passedTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(6).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(6).setMinWidth(75);
        passedTable.getColumnModel().getColumn(6).setMaxWidth(75);
        passedTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
    }

    public void printFunction() {
        if (tableItem.getRowCount() > 0) {

            Map parameter = new HashMap();
            parameter.put("Particulars", "Particulars");
            parameter.put("Sr.No", "Sr.No");
            parameter.put("VCH No", "VCH No");
            parameter.put("VCH Type", "VCH Type");
            parameter.put("Date", "Date");
            parameter.put("Debit", "Credit");
            parameter.put("Credit", "Debit");
            // parameter.put("G","G");
            String TotalDebitValue = labelTotalCredit.getText();
            String TotalCreditValue = labelTotalDebit.getText();
            //  System.out.println(""+TotalAmountValue);

            String OpeningBalance = labelOpeningDebit.getText();
            String ClosingBalance = labelClosingDebit.getText();

            String labelOType1 = labelOType.getText();
            String labelCType1 = labelCType.getText();
            // String TotalAmount=labelTotalCredit.getText();

            parameter.put("TotalDebitValue", TotalCreditValue);
            parameter.put("TotalCreditValue", TotalDebitValue);

            parameter.put("OpeningBalanceValue", OpeningBalance);
            parameter.put("ClosingBalanceValue", ClosingBalance);

            parameter.put("TotalDebit", "TotalDebit");
            parameter.put("TotalCredit", "TotalCredit");

            parameter.put("OpeningDebit", labelOType1);
            parameter.put("OpeningCredit", labelCType1);
            parameter.put("ledgerName", comboStockItem.getSelectedItem());
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
            parameter.put("dateTo", saDa.format(dat2.getDate()).toString());

//         parameter.put("OpeningBalance","OpeningBalance");
//         parameter.put("ClosingBalance","ClosingBalance");
            //parameter.put("subreporttrial",new JRTableModelDataSource(tableLedger.getModel()));

            String Blank = " ";

//	    if (labelOType1.equalsIgnoreCase("Debit")) {
//		parameter.put("OpeningBalanceValue", OpeningBalance);
//		parameter.put("OpeningDebit", Blank);
//		System.out.print("IN IF...............");
//	    } else {
//		parameter.put("OpeningDebit", OpeningBalance);
//		parameter.put("OpeningBalanceValue", Blank);
//		System.out.print("IN Else...............");
//	    }
//
//	    if (labelCType1.equalsIgnoreCase("Credit")) {
//		parameter.put("OpeningCredit", ClosingBalance);
//		parameter.put("ClosingBalanceValue", Blank);
//		System.out.print("IN IF Credit...............");
//	    } else {
//
//		parameter.put("ClosingBalanceValue", ClosingBalance);
//		parameter.put("OpeningCredit", Blank);
//		System.out.print("IN Else credit...............");
//	    }

            String OpeningBalanceDebit_pass = labelOpeningCredit.getText();
            String OpeningBalanceCredit_pass = labelOpeningDebit.getText();

            String ClosingBalanceDebit_pass = labelClosingCredit.getText();
            String ClosingBalanceCredit_pass = labelClosingDebit.getText();

            parameter.put("OpeningBalanceValue", OpeningBalanceDebit_pass);
            parameter.put("OpeningDebit", OpeningBalanceCredit_pass);

            parameter.put("ClosingBalanceValue", ClosingBalanceDebit_pass);
            parameter.put("OpeningCredit", ClosingBalanceCredit_pass);



            PrintAllReport.printStockitemReport(parameter, new JRTableModelDataSource(tableItem.getModel()), tableItem.getRowCount());
        } else {
            JOptionPane.showMessageDialog(this, "No Data Available For Printing");
        }
    }
}
