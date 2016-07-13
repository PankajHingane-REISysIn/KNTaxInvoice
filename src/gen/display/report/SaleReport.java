/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.account.group.Group_Create;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

/**
 *
 * @author pc5
 */
public class SaleReport extends javax.swing.JInternalFrame {

    /**
     * Creates new form SaleReport
     */
    ArrayList<TemporaryClass> ledger = new ArrayList<TemporaryClass>();
    private final JTextField tfDatePicker;
    private final JTextField tfDatePicker1;
    private boolean hide_flag = false;
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static double total_purchase, total_sales;
    String date1, date2;

    public SaleReport(String s) {
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
                    btnBackActionPerformed(null);
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        SaleReportPanel1 = new javax.swing.JPanel();
        dat1 = new com.toedter.calendar.JDateChooser();
        dat2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCredit = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        labelTotalCredit = new javax.swing.JLabel();

        setTitle(org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.title")); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.jLabel1.text")); // NOI18N

        SaleReportPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dat1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        dat2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.jLabel2.text")); // NOI18N

        btnShow.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnShow, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.btnShow.text")); // NOI18N
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

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPrint, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.btnPrint.text")); // NOI18N
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

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnBack, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.btnBack.text")); // NOI18N
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

        javax.swing.GroupLayout SaleReportPanel1Layout = new javax.swing.GroupLayout(SaleReportPanel1);
        SaleReportPanel1.setLayout(SaleReportPanel1Layout);
        SaleReportPanel1Layout.setHorizontalGroup(
            SaleReportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SaleReportPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnShow)
                .addGap(18, 18, 18)
                .addComponent(btnPrint)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        SaleReportPanel1Layout.setVerticalGroup(
            SaleReportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SaleReportPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(SaleReportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SaleReportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnPrint)
                        .addComponent(btnBack))
                    .addGroup(SaleReportPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableCredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Particulars","VCH No","Quantity","Amount","Date"
            }
        ));
        tableCredit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCreditMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCredit);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.jLabel3.text")); // NOI18N

        labelTotalCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelTotalCredit, org.openide.util.NbBundle.getMessage(SaleReport.class, "SaleReport.labelTotalCredit.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(660, 660, 660)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SaleReportPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(SaleReportPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        Calendar currentDate = Calendar.getInstance();
        dat1.setDate(currentDate.getTime());

        //Calendar currentDate = Calendar.getInstance();
        dat2.setDate(currentDate.getTime());

        DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(5);

//        tableModel = (DefaultTableModel) tableDebit.getModel();
//        tableModel.setRowCount(0);
//        tableModel.setColumnCount(5);
//        
//          tableModel = (DefaultTableModel) combine.getModel();
//        tableModel.setRowCount(0);
//        tableModel.setColumnCount(10);


        btnShow.setMnemonic(KeyEvent.VK_S);
        btnPrint.setMnemonic(KeyEvent.VK_P);
        btnBack.setMnemonic(KeyEvent.VK_B);

        btnShowActionPerformed(null);

        //  scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

        dat1.requestFocus();

        //tableCredit.setGridColor(Color.red);
        tableCredit.setShowGrid(true);

//        tableDebit.setGridColor(Color.red);
//        tableDebit.setShowGrid(true);

    }//GEN-LAST:event_formInternalFrameOpened

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:

        // comment on  date 21-12-2013 by atul,NO need of this code
        // it gives exception when invalid date entered
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        date1 = f.format(dat1.getDate());
//        date2 = f.format(dat2.getDate());


        if (dat1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat1.requestFocus();
            tfDatePicker.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);
            labelTotalCredit.setText("0");
        } else if (dat2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat2.requestFocus();
            tfDatePicker1.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);
            labelTotalCredit.setText("0");
//        } else if (date1.compareTo(date2) > 0) {
        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            dat2.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);
            labelTotalCredit.setText("0");
        } else {
            loadSalesEntry();
            labelTotalCredit.setText("" + Constants.DECIMAL_FORMAT.format(total_sales));

//            loadPurchaseEntry();
//            labelTotalDebit.setText(""+(total_purchase));

            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = f.format(dat1.getDate());
                date1 = f.format(dat1.getDate());
                date2 = f.format(dat2.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex<>6 order by trans_date)";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date BETWEEN '"+ date1 +"' AND '"+ date2 +"' and trans_typeIndex<>6 order by trans_date)";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + date1 + "' and trans_date<='" + date2 + "' and trans_typeIndex=6)";
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

//                q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=3)";
//                rs1 = st.executeQuery(q);
//                while(rs1.next()) {
//                    d=d+rs1.getDouble("totalDeb");
//                }

                //--------------new query-------------------
                //                q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=1)";
                //                rs1 = st.executeQuery(q);
                //                while(rs1.next()) {
                //                    d=d+rs1.getDouble("totalDeb");
                //                }
                //------------------------------------------

                //System.out.println(d);

                if (cr >= d) {
                    o = cr - d;
                    //labelOpeningBalance.setText(""+ df.format(Math.abs(o)));
                    // labelOType.setText("Credit");
                } else {
                    o = d - cr;
                    // labelOpeningBalance.setText(""+ df.format(Math.abs(o)));
                    // labelOType.setText("Debit");
                }

                //----------------------calculate closing balance-----------------------------
                //   o=Double.parseDouble(labelOpeningBalance.getText());
                cr = Double.parseDouble(labelTotalCredit.getText());
                //   d=Double.parseDouble(labelTotalDebit.getText());
                double cl = 00f;

//                if(labelOType.getText().equalsIgnoreCase("Credit")) {
//                    cl=o+cr-d;
//                    labelClosingBalance.setText(""+df.format(Math.abs(cl)));
//                    if(cl<0) {
//                        labelCType.setText("Debit");
//                    } else {
//                        labelCType.setText("Credit");
//                    }
//                } else {
//                    cl=o+d-cr;
//                    labelClosingBalance.setText(""+df.format(Math.abs(cl)));
//                    if(cl>0) {
//                        labelCType.setText("Debit");
//                    } else {
//                        labelCType.setText("Credit");
//                    }
//                }
                //---------------------------------------------------------------------------

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (tableCredit.getRowCount() == 0) {
            labelTotalCredit.setText("0");

        }

        setColumnWidth(tableCredit);



    }//GEN-LAST:event_btnShowActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void tableCreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCreditMouseClicked
        // TODO add your handling code here:
        int row = tableCredit.getSelectedRow();
        if (tableCredit.getValueAt(row, 0) != null && tableCredit.getValueAt(row, 2) != null) {
            try {
                String id = tableCredit.getValueAt(tableCredit.getSelectedRow(), 1).toString();
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
//		this.getParent().add(s);
//		this.getParent().setVisible(true);
//		Dimension desktopSize = this.getParent().getSize();
//		s.setSize(desktopSize);
//		s.setPreferredSize(desktopSize);
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
                Logger.getLogger(SaleReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableCreditMouseClicked

    private void btnShowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnShowKeyPressed
        // TODO add your handling code here:
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
            tfDatePicker1.requestFocus();
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
            // btnPrintActionPerformed(null);
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
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }
    }//GEN-LAST:event_btnBackKeyPressed

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

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        btnShowActionPerformed(evt);
        if (dat1.getDate() != null && dat2.getDate() != null && tableCredit.getRowCount() > 0 && dat1.getDate().getTime() <= dat2.getDate().getTime()) {
            Map parameter = new HashMap();
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
            parameter.put("dateTo", saDa.format(dat2.getDate()).toString());
            String TotalAmountCredit = labelTotalCredit.getText().toString().trim();
            parameter.put("TotalAmountCredit", TotalAmountCredit);
            PrintAllReport.printDSaleReport(parameter, new JRTableModelDataSource(tableCredit.getModel()));
        } else {
            JOptionPane.showMessageDialog(this, "No data to print");
        }
    }//GEN-LAST:event_btnPrintActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SaleReportPanel1;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private com.toedter.calendar.JDateChooser dat1;
    private com.toedter.calendar.JDateChooser dat2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JTable tableCredit;
    // End of variables declaration//GEN-END:variables

    public void loadSalesEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat1.getDate());
            date1 = f.format(dat1.getDate());
            date2 = f.format(dat2.getDate());

            //-----------------------Table: Debit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(5);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex=1) order by trans_id Asc";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    //Sr No
                    // tableCredit.setValueAt(row + 1, row, 0);

                    //Get Ledger name
                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 0);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
                        String dateSet = f1.format(rs4.getDate("trans_date"));
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 1);
                        tableCredit.setValueAt(dateSet, row, 4);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 3);

                    //Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
//                        tableCredit.setValueAt(rs4.getDouble("totalQty"), row, 2);
                        tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("totalQty")), row, 2);
                    }
                    rs4.close();
                }
            }
            //--------------------------------------------------------------

            //-------------------Count Total Creditted Amount--------------------
            if (tableCredit.getRowCount() > 0) {
                i = 0;
                double qty = 0;
                while (i < tableCredit.getRowCount()) {
                    if (tableCredit.getValueAt(i, 3) != null) {
                        qty = qty + Double.parseDouble(tableCredit.getValueAt(i, 3).toString());
                    }
                    i++;
                }
                total_sales = qty;
            }
            //--------------------------------------------------------------------------------
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

//        passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(0).setPreferredWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMaxWidth(50);

        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(300);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(300);


        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(60);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(60);

        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(100);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(100);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(4).setMinWidth(120);
        passedTable.getColumnModel().getColumn(4).setMaxWidth(120);
    }
}
