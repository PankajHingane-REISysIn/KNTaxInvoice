package gen.display.report;

import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class DayBookSalesPurchase extends javax.swing.JInternalFrame {
    // Database db=new Database();     //Object of class for database 

    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static double total_purchase, total_sales;
    static int backAction = 0;
    String date = "";
    private final JTextField tfDatePicker;
    private boolean hide_flag = false;

    public DayBookSalesPurchase(String s) {
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
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
                    btnShowActionPerformed(null);
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    btnBackActionPerformed(null);
                }
            }
            /*  @Override
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


    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpaneMain = new javax.swing.JScrollPane();
        panelMain = new javax.swing.JPanel();
        panelDebit = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCredit = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
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
        jLabel8 = new javax.swing.JLabel();
        labelTotalCreditWeight = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelTotalDebitWeight = new javax.swing.JLabel();
        dat = new com.toedter.calendar.JDateChooser();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelTitleOpeningBal = new javax.swing.JLabel();
        labelTitleClosingBal = new javax.swing.JLabel();
        labelOpeningBalance = new javax.swing.JLabel();
        labelClosingBalance = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        labelOType = new javax.swing.JLabel();
        labelCType = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        combine = new javax.swing.JTable();

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
                "Sr.No","Particulars","VCH No","Amount","Quantity"
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

        labelTotalCredit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                "Sr.No","Particulars","VCH No","Amount","Quantity"
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

        labelTotalDebit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTotalDebit.setText("jLabel4");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Debit");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Total Quantity");

        labelTotalCreditWeight.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTotalCreditWeight.setText("jLabel9");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Total Quantity");

        labelTotalDebitWeight.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTotalDebitWeight.setText("jLabel11");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDebitLayout.createSequentialGroup()
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDebitLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTotalCreditWeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDebitLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDebitLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTotalDebitWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelDebitLayout.setVerticalGroup(
            panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDebitLayout.createSequentialGroup()
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDebitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelTotalCredit)
                    .addComponent(jLabel3)
                    .addComponent(labelTotalDebit)
                    .addComponent(jLabel8)
                    .addComponent(labelTotalCreditWeight)
                    .addComponent(jLabel10)
                    .addComponent(labelTotalDebitWeight))
                .addContainerGap())
        );

        dat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        dat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datMouseClicked(evt);
            }
        });
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

        labelTitleOpeningBal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTitleOpeningBal.setText("Opening Balance");

        labelTitleClosingBal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTitleClosingBal.setText("Closing Balance");

        labelOpeningBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelOpeningBalance.setText("jLabel6");

        labelClosingBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                "Sr.No","Particulars","VCH No","Amount","Quantity","`Sr.No","`Particulars","`VCH No","`Amount","`Quantity"
            }
        ));
        jScrollPane1.setViewportView(combine);

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(391, 391, 391)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitleClosingBal)
                            .addComponent(labelTitleOpeningBal))
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(labelOpeningBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(labelClosingBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(labelCType)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 405, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(labelOType)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMainLayout.createSequentialGroup()
                                        .addGap(631, 631, 631)
                                        .addComponent(jLabel1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMainLayout.createSequentialGroup()
                                        .addGap(505, 505, 505)
                                        .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnShow)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPrint)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBack))))
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addComponent(panelDebit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGap(462, 573, Short.MAX_VALUE)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(labelCType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPrint)
                                .addComponent(btnShow)
                                .addComponent(btnBack))
                            .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelDebit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMainLayout.createSequentialGroup()
                                .addComponent(labelTitleOpeningBal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelTitleClosingBal))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelOpeningBalance)
                                    .addComponent(labelOType))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelClosingBalance)))))
                .addContainerGap())
        );

        scrollpaneMain.setViewportView(panelMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1174, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableCreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCreditMouseClicked
        int row = tableCredit.getSelectedRow();
        if (tableCredit.getValueAt(row, 1) != null && tableCredit.getValueAt(row, 3) != null) {
            try {
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
//                s.setVisible(true);
//                this.getParent().add(s);
//                this.getParent().setVisible(true);
//                Dimension desktopSize = this.getParent().getSize();
//                s.setSize(desktopSize);
//                s.setPreferredSize(desktopSize);
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
            } //  commented on 18-02-2013
            //        try {
            //            this.setClosed(true);
            //        } catch (PropertyVetoException ex) {
            //            Logger.getLogger(DayBookSalesPurchase.class.getName()).log(Level.SEVERE, null, ex);
            //        }
            catch (Exception ex) {
                Logger.getLogger(DayBookSalesPurchase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //  commented on 18-02-2013
//        try {
//            this.setClosed(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(DayBookSalesPurchase.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
            try {
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
//                p.setVisible(true);
//                this.getParent().add(p);
//                this.getParent().setVisible(true);
//                Dimension desktopSize = this.getParent().getSize();
//                p.setSize(desktopSize);
//                p.setPreferredSize(desktopSize);
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
                Logger.getLogger(DayBookSalesPurchase.class.getName()).log(Level.SEVERE, null, ex);
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

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        if (dat.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat.requestFocus();

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);

            DefaultTableModel tableModel1 = (DefaultTableModel) tableDebit.getModel();
            tableModel1.setRowCount(0);

            labelTotalCredit.setText("0");
            labelTotalDebit.setText("0");
            labelTotalCreditWeight.setText("0");
            labelTotalDebitWeight.setText("0");

        } else {
            loadSalesEntry();
            calculateTotalCreditWeight();
            labelTotalCredit.setText("" + Constants.DECIMAL_FORMAT.format(total_sales));

            loadPurchaseEntry();
            calculateTotalDebitWeight();
            labelTotalDebit.setText("" + Constants.DECIMAL_FORMAT.format(total_purchase));

            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = f.format(dat.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();

                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=6)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();

                //-------------new query----------------
                //                q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                //                rs1 = st.executeQuery(q);
                //                while(rs1.next()) {
                //                    cr=cr+rs1.getDouble("totalCred");
                //                }
                //                rs1.close();
                //--------------------------------------

                //System.out.println(cr);

                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'" + dateNow + "' and trans_typeIndex=3)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }

                //--------------new query-------------------
                //                q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=1)";
                //                rs1 = st.executeQuery(q);
                //                while(rs1.next()) {
                //                    d=d+rs1.getDouble("totalDeb");
                //                }
                //------------------------------------------

                //System.out.println(d);

                System.out.println("DDDDDDDDDRRRRRRRRRRRRRRR" + d);
                System.out.println("CCCCCCCCCCCCCCRRRRRRRRRRRRRRR" + cr);

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

        if (tableCredit.getRowCount() == 0 && tableDebit.getRowCount() == 0) {
            labelTotalCredit.setText("0");
            labelTotalDebit.setText("0");
            labelTotalCreditWeight.setText("0");
            labelTotalDebitWeight.setText("0");
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

        btnShowActionPerformed(evt);
        createTable();

        if (combine.getRowCount() != 0 && dat.getDate() != null) {
            Map parameter = new HashMap();

            String TotalCreditAmount = labelTotalCredit.getText();
            String TotalDebiAmount = labelTotalDebit.getText();

            String OpeningBalance = labelOpeningBalance.getText();
            String ClosingBalance = labelClosingBalance.getText();

//        String OType = labelOType.getText();
//        String CType = labelCType.getText();
//
            parameter.put("TotalAmountdebit", TotalDebiAmount);
            parameter.put("TotalAmountCredit", TotalCreditAmount);
//
//        parameter.put("OpeningBalanceValue", OpeningBalance);
//        parameter.put("ClosingBalanceValue", ClosingBalance);
//        parameter.put("OpeningDebit", OType);
//        parameter.put("OpeningCredit", CType);

            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
//	if(){
            parameter.put("Date", saDa.format(dat.getDate()).toString());
//	}

            PrintAllReport.printSAlePrTableDaybookDemo(parameter, new JRTableModelDataSource(combine.getModel()));
        } else {
            JOptionPane.showMessageDialog(this, "No data to Print");
        }





//        Map parameter=new HashMap();
//	
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
//     
//         
//      
//         SimpleDateFormat saDa=new SimpleDateFormat("dd-MM-yyyy");
//         parameter.put("trans_date",saDa.format( dat.getDate()).toString());
//         System.out.println("Date..........."+TotalCreditAmount+TotalDebiAmount);
//         System.out.println("Date..........."+OpeningBalance+ClosingBalance);
//         System.out.println("Date..........."+OType+CType);   
//     
//               
//         PrintAllReport.printsalepurchaseDayBook(dat.getDate().toString(),parameter);  
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
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
}//GEN-LAST:event_btnBackActionPerformed

    private void btnBackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBackKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }
}//GEN-LAST:event_btnBackKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        setComponentActiveInactive();
        backAction = 1;
        Calendar currentDate = Calendar.getInstance();
        dat.setDate(currentDate.getTime());

        DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(5);

        tableModel = (DefaultTableModel) tableDebit.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(5);

        tableModel = (DefaultTableModel) combine.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(10);


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
            tableModel.setColumnCount(5);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(5);

            tableModel = (DefaultTableModel) combine.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(10);


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
        }


    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        backAction = 2;
        date = tfDatePicker.getText();
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void datMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_datMouseClicked

    public void loadPurchaseEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(5);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=2)";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    //tableModel = (DefaultTableModel) tableDebit.getModel();
                    tableModel.setRowCount(row + 1);

                    //Sr No
                    tableDebit.setValueAt(row + 1, row, 0);

                    //Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
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
                }
                //System.out.println("Middle Purchase index: "+creditPurchaseIndex);
            }
            //--------------------------------------------------------------

            //-------------------Count Total Creditted Amount--------------------
//            if (tableDebit.getRowCount() > 0) {
            i = 0;
            double qty = 0;
            while (i < tableDebit.getRowCount()) {
                if (tableDebit.getValueAt(i, 3) != null) {
                    qty = qty + Double.parseDouble(tableDebit.getValueAt(i, 3).toString());
                }
                i++;
            }
            total_purchase = qty;
//            }
            //--------------------------------------------------------------------------------
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadSalesEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            //-----------------------Table: Debit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(5);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='" + dateNow + "' and trans_typeIndex=1)";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    //Sr No
                    tableCredit.setValueAt(row + 1, row, 0);

                    //Get Ledger name
                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
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
                }
            }
            //--------------------------------------------------------------

            //-------------------Count Total Creditted Amount--------------------
//            if (tableCredit.getRowCount() > 0) {
            i = 0;
            double qty = 0;
            while (i < tableCredit.getRowCount()) {
                if (tableCredit.getValueAt(i, 3) != null) {
                    qty = qty + Double.parseDouble(tableCredit.getValueAt(i, 3).toString());
                }
                i++;
            }
            total_sales = qty;
//            }
            //--------------------------------------------------------------------------------
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void calculateTotalCreditWeight() {
        i = 0;
        double weight = 0;
        while (i < tableCredit.getRowCount()) {
            if (tableCredit.getValueAt(i, 4) != null) {
                weight = weight + Double.parseDouble(tableCredit.getValueAt(i, 4).toString());
            }
            i++;
        }
        labelTotalCreditWeight.setText("" + weight);
    }

    private void calculateTotalDebitWeight() {
        i = 0;
        double weight = 0;
        while (i < tableDebit.getRowCount()) {
            if (tableDebit.getValueAt(i, 4) != null) {
                weight = weight + Double.parseDouble(tableDebit.getValueAt(i, 4).toString());
            }
            i++;
        }
        labelTotalDebitWeight.setText("" + weight);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private javax.swing.JTable combine;
    private com.toedter.calendar.JDateChooser dat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelCType;
    private javax.swing.JLabel labelClosingBalance;
    private javax.swing.JLabel labelOType;
    private javax.swing.JLabel labelOpeningBalance;
    private javax.swing.JLabel labelTitleClosingBal;
    private javax.swing.JLabel labelTitleOpeningBal;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalCreditWeight;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JLabel labelTotalDebitWeight;
    private javax.swing.JPanel panelDebit;
    private javax.swing.JPanel panelMain;
    private javax.swing.JScrollPane scrollpaneMain;
    private javax.swing.JTable tableCredit;
    private javax.swing.JTable tableDebit;
    // End of variables declaration//GEN-END:variables

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
                    combine.setValueAt(tableCredit.getValueAt(i, j), i, k);
                    System.out.println(tableCredit.getValueAt(i, j));
                    k++;
                }

            }

            if (i < tableDebit.getRowCount()) {
                //insert debit side
                k = 5;
                for (int j = 0; j < tableDebit.getColumnCount(); j++) {
                    combine.setValueAt(tableDebit.getValueAt(i, j), i, k);
                    System.out.println(tableDebit.getValueAt(i, j));
                    k++;
                }
            }
        }

    }

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        passedTable.getColumnModel().getColumn(0).setMinWidth(50);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(50);

        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(194);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(194);


        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(50);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(50);

        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(100);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(4).setMinWidth(50);
        passedTable.getColumnModel().getColumn(4).setMaxWidth(50);
        passedTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
    }

    private void setComponentActiveInactive() {

        labelCType.setVisible(false);
        labelClosingBalance.setVisible(false);
        labelOType.setVisible(false);
        labelOpeningBalance.setVisible(false);
        labelTitleClosingBal.setVisible(false);
        labelTitleOpeningBal.setVisible(false);

    }
}
