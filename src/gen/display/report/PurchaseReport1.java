/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.account.group.Group_Create;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
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
public class PurchaseReport1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form PurchaseReport1
     */
    private final JTextField tfDatePicker;
    private final JTextField tfDatePicker1;
    private boolean hide_flag = false;
    static double total_purchase, total_sales;
    String date1, date2;
    ResultSet rs1, rs2, rs3, rs4, rs5;
    String q = "", str = "";

    public PurchaseReport1(String s) {
        setClosable(true);
        initComponents();
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);

        tfDatePicker = (JTextField) dat1.getComponent(1);
        // tfDatePickerCal=(JTextField)dat1.getComponent(0);
        tfDatePicker1 = (JTextField) dat2.getComponent(1);
        System.out.println(tfDatePicker.toString());


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
//                else if(code==KeyEvent.VK_ESCAPE)
//                {
//                comboLedger.requestFocus();
//                }
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
        jPanel1 = new javax.swing.JPanel();
        dat1 = new com.toedter.calendar.JDateChooser();
        dat2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        PurchasePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDebit = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        labelTotalDebit = new javax.swing.JLabel();

        setTitle(org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.title_1")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(961, 872));
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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.jLabel1.text_1")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dat1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        dat2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.jLabel2.text_1")); // NOI18N

        btnShow.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnShow, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.btnShow.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(btnPrint, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.btnPrint.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(btnBack, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.btnBack.text")); // NOI18N
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
                .addGap(32, 32, 32)
                .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShow)
                .addGap(18, 18, 18)
                .addComponent(btnPrint)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnPrint)
                        .addComponent(btnBack))
                    .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        PurchasePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PurchasePanel.setPreferredSize(new java.awt.Dimension(655, 380));
        PurchasePanel.setRequestFocusEnabled(false);

        tableDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableDebit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "VCH No","Particulars","Reference","Quantity","Amount","Date"
            }
        ));
        tableDebit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDebitMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDebit);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.jLabel3.text")); // NOI18N

        labelTotalDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(labelTotalDebit, org.openide.util.NbBundle.getMessage(PurchaseReport1.class, "PurchaseReport1.labelTotalDebit.text")); // NOI18N

        javax.swing.GroupLayout PurchasePanelLayout = new javax.swing.GroupLayout(PurchasePanel);
        PurchasePanel.setLayout(PurchasePanelLayout);
        PurchasePanelLayout.setHorizontalGroup(
            PurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PurchasePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
            .addGroup(PurchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                .addContainerGap())
        );
        PurchasePanelLayout.setVerticalGroup(
            PurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PurchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelTotalDebit))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PurchasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(242, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(550, 550, 550))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PurchasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:

        // comment on  date 21-12-2013 by atul,NO need of this code
        // it gives exception when invalid date entered
        // SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        // date1 = f.format(dat1.getDate());
        // date2 = f.format(dat2.getDate());


        if (dat1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat1.requestFocus();
            tfDatePicker.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            labelTotalDebit.setText("0");
        } else if (dat2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat2.requestFocus();
            tfDatePicker1.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            labelTotalDebit.setText("0");
//        } else if (date1.compareTo(date2) > 0) {
        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            dat2.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            labelTotalDebit.setText("0");
        } else {
            //loadPurchaseEntry();
            //labelTotalDebit.setText("" + Constants.DECIMAL_FORMAT.format(total_sales));

            loadPurchaseEntry();
            labelTotalDebit.setText("" + Constants.DECIMAL_FORMAT.format(total_purchase));

            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                date1 = f.format(dat1.getDate());
                date2 = f.format(dat2.getDate());
                // String dateNow=f.format(dat.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();



                //-------------new query----------------
                //                q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+ dateNow +"' and trans_typeIndex=2)";
                //                rs1 = st.executeQuery(q);
                //                while(rs1.next()) {
                //                    cr=cr+rs1.getDouble("totalCred");
                //                }
                //                rs1.close();
                //--------------------------------------

                //System.out.println(cr);

                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex<>3 order by trans_date)";//where trans_date<'"+ dateNow +"' and trans_typeIndex=3)";
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

//                if(cr>=d) {
//                    o=cr-d;
//                    labelOpeningBalance.setText(""+ df.format(Math.abs(o)));
//                    labelOType.setText("Credit");
//                } else {
//                    o=d-cr;
//                    labelOpeningBalance.setText(""+ df.format(Math.abs(o)));
//                    labelOType.setText("Debit");
//                }

                //----------------------calculate closing balance-----------------------------
//                o=Double.parseDouble(labelOpeningBalance.getText());
//                cr=Double.parseDouble(labelTotalCredit.getText());
//                d=Double.parseDouble(labelTotalDebit.getText());
//                double cl=00f;
//                
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

        if (tableDebit.getRowCount() == 0) {
            //labelTotalCredit.setText("0");
            labelTotalDebit.setText("0");
        }

        setColumnWidth(tableDebit);
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:

        btnShowActionPerformed(evt);
        if (dat1.getDate() != null && dat2.getDate() != null && tableDebit.getRowCount() > 0 && dat1.getDate().getTime() <= dat2.getDate().getTime()) {
            Map parameter = new HashMap();
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
            parameter.put("dateTo", saDa.format(dat2.getDate()).toString());

            String totalamount = "" + labelTotalDebit.getText();
            if (totalamount.equalsIgnoreCase("")) {
                totalamount = " ";
            } else {
                totalamount = "" + labelTotalDebit.getText().trim();
            }
            parameter.put("totalamount", totalamount);
            PrintAllReport.printPSaleReport(parameter, new JRTableModelDataSource(tableDebit.getModel()));
        } else {
            JOptionPane.showMessageDialog(this, "No data to print");
        }
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
            if (tableDebit.getRowCount() > 0) {
                tableDebit.requestFocus();
                tableDebit.changeSelection(0, 0, false, false);
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
            if (tableDebit.getRowCount() > 0) {
                tableDebit.requestFocus();
                tableDebit.changeSelection(0, 0, false, false);
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

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        Calendar currentDate = Calendar.getInstance();
        dat1.setDate(currentDate.getTime());

        //Calendar currentDate = Calendar.getInstance();
        dat2.setDate(currentDate.getTime());

        DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(6);

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

        //tableDebit.setGridColor(Color.red);
        tableDebit.setShowGrid(true);

//        tableDebit.setGridColor(Color.red);
//        tableDebit.setShowGrid(true);
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void tableDebitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDebitMouseClicked
        // TODO add your handling code here:
        int row = tableDebit.getSelectedRow();
        if (tableDebit.getValueAt(row, 1) != null && tableDebit.getValueAt(row, 4) != null) {
            try {
                String id = tableDebit.getValueAt(tableDebit.getSelectedRow(), 0).toString();
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
//		p.setVisible(true);
//		this.getParent().add(p);
//		this.getParent().setVisible(true);
//		Dimension desktopSize = this.getParent().getSize();
//		p.setSize(desktopSize);
//		p.setPreferredSize(desktopSize);
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
                Logger.getLogger(PurchaseReport1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableDebitMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PurchasePanel;
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
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JTable tableDebit;
    // End of variables declaration//GEN-END:variables

    public void loadPurchaseEntry() {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();
            Statement st5 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            date1 = f.format(dat1.getDate());
            date2 = f.format(dat2.getDate());

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(6);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date BETWEEN '" + date1 + "' AND '" + date2 + "' and trans_typeIndex=2) order by trans_id Asc";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {

                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);
                    //tableModel = (DefaultTableModel) tableDebit.getModel();


                    //Sr No
                    // tableDebit.setValueAt(row + 1, row, 0);

                    //Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // trans_reference
                    q = "select trans_reference from tbltransactionotherdetails where trans_id = " + rs2.getLong("trans_id") + " ";
                    rs5 = st5.executeQuery(q);
                    if (rs5.next()) {
                        System.out.println("QQQQQQQQQQQQQ" + rs5.getString("trans_reference") == null);
                        if (rs5.getString("trans_reference") == null) {
//			    if (rs5.getString("trans_reference").equalsIgnoreCase("0")) {
//				tableDebit.setValueAt("-", row, 2);
//			    } else {
                            tableDebit.setValueAt("-", row, 2);
//			    }
                        } else {
                            if (rs5.getString("trans_reference").equalsIgnoreCase("0")) {
                                tableDebit.setValueAt("-", row, 2);
                            } else {
                                tableDebit.setValueAt(rs5.getString("trans_reference"), row, 2);
                            }
                        }
                    }

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + " ";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
                        String dateSet = f1.format(rs4.getDate("trans_date"));
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 0);
                        tableDebit.setValueAt(dateSet, row, 5);
                    }
                    rs4.close();

                    //Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 4);

                    //Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getDouble("totalQty"), row, 3);
                    }
                    rs4.close();
                }
                //System.out.println("Middle Purchase index: "+creditPurchaseIndex);
            }
            //--------------------------------------------------------------

            //-------------------Count Total Creditted Amount--------------------
            if (tableDebit.getRowCount() > 0) {
                int i = 0;
                double qty = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 3) != null) {
                        qty = qty + Double.parseDouble(tableDebit.getValueAt(i, 3).toString());
                    }
                    i++;
                }
                total_purchase = qty;
            }
            //--------------------------------------------------------------------------------
        } catch (SQLException ex) {
            ex.printStackTrace();
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
        passedTable.getColumnModel().getColumn(1).setMinWidth(220);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(220);


        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(150);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(150);

        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(60);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(60);
        passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(4).setMinWidth(100);
        passedTable.getColumnModel().getColumn(4).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(5).setMinWidth(80);
        passedTable.getColumnModel().getColumn(5).setMaxWidth(80);
    }
}
