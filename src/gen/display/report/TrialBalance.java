package gen.display.report;

import gen.account.group.Group_Create;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class TrialBalance extends javax.swing.JInternalFrame {
    //Database db=new Database();     //Object of class for database 

    ArrayList<TemporaryClass> ledger = new ArrayList<TemporaryClass>();
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3;
    String q = "", str = "";
    int flag_cash_in_hand = 0;
    private final JTextField tfDatePicker;
    private final JTextField tfDatePicker1;
    private boolean hide_flag = false;

    /**
     * Creates new form LedgerReport
     */
    public TrialBalance(String s) {
        setClosable(true);
        initComponents();
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
        tfDatePicker = (JTextField) dat1.getComponent(1);
        System.out.println(tfDatePicker.toString());
        tfDatePicker1 = (JTextField) dat2.getComponent(1);



        //   tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tfDatePicker1.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        tfDatePicker1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
                    btnShowActionPerformed(null);
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker.requestFocus();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        dat2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dat1 = new com.toedter.calendar.JDateChooser();
        btnBack = new javax.swing.JButton();

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

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        panelLedgerDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableLedger.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableLedger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "Sr.No", "Groups","Debit","Credit"
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

        javax.swing.GroupLayout panelLedgerDetailsLayout = new javax.swing.GroupLayout(panelLedgerDetails);
        panelLedgerDetails.setLayout(panelLedgerDetailsLayout);
        panelLedgerDetailsLayout.setHorizontalGroup(
            panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelLedgerDetailsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLedgerDetailsLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLedgerDetailsLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(labelTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184))))
        );
        panelLedgerDetailsLayout.setVerticalGroup(
            panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLedgerDetailsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLedgerDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelTotalDebit)
                    .addComponent(jLabel5)
                    .addComponent(labelTotalCredit))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Trial Report");

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
                .addGap(45, 45, 45)
                .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShow)
                .addGap(18, 18, 18)
                .addComponent(btnPrint)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
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
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelLedgerDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(571, 571, 571)
                        .addComponent(jLabel1)))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLedgerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
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


            DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(7);

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = f.format(dat1.getDate());
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        panelLedgerDetails.setVisible(false);

    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void tableLedgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLedgerMouseClicked
        /* int row=tableLedger.getSelectedRow();
         if(tableLedger.getValueAt(row, 2)!=null) {
         if(tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Receipt")) {
         EditReceipt r = new EditReceipt("Edit Receipt Entry",Long.parseLong(tableLedger.getValueAt(row, 4).toString()));

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
         BasicInternalFrameUI ui = (BasicInternalFrameUI)r.getUI();

         Component north = ui.getNorthPane();
         MouseMotionListener[] actions =
         (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

         for (int i = 0; i < actions.length; i++)
         north.removeMouseMotionListener( actions[i] );
         } else if(tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Payment")) {
         EditPayment p = new EditPayment("Edit Payment Entry",Long.parseLong(tableLedger.getValueAt(row, 4).toString()));

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
         BasicInternalFrameUI ui = (BasicInternalFrameUI)p.getUI();

         Component north = ui.getNorthPane();
         MouseMotionListener[] actions =
         (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

         for (int i = 0; i < actions.length; i++)
         north.removeMouseMotionListener( actions[i] );
         } else if(tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Sales")) {
         EditSales s = new EditSales("Edit Sales Entry",Long.parseLong(tableLedger.getValueAt(row, 4).toString()));

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
         BasicInternalFrameUI ui = (BasicInternalFrameUI)s.getUI();

         Component north = ui.getNorthPane();
         MouseMotionListener[] actions =
         (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

         for (int i = 0; i < actions.length; i++)
         north.removeMouseMotionListener( actions[i] );
         } */

        /*else if(tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Purchase"))
         {
         EditPurchase p = new EditPurchase("Edit Purchase Entry",Long.parseLong(tableLedger.getValueAt(row, 4).toString()));

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
         try
         {
         p.setSelected(true);
         } catch (java.beans.PropertyVetoException e) {
         e.printStackTrace();
         }
         BasicInternalFrameUI ui = (BasicInternalFrameUI)p.getUI();

         Component north = ui.getNorthPane();
         MouseMotionListener[] actions =
         (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

         for (int i = 0; i < actions.length; i++)
         north.removeMouseMotionListener( actions[i] );
         }
         }*/
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
        Map parameter = new HashMap();
        parameter.put("Groups", "Groups");
        parameter.put("Sr.No", "Sr.No");

        parameter.put("Debit", "Debit");
        parameter.put("Credit", "Credit");

        String TotalDebitValue = labelTotalDebit.getText();
        String TotalCreditValue = labelTotalCredit.getText();
        //  System.out.println(""+TotalAmountValue);

        // String TotalAmount=labelTotalCredit.getText();

        parameter.put("TotalDebitValue", TotalDebitValue);
        parameter.put("TotalCreditValue", TotalCreditValue);


        parameter.put("TotalDebit", "TotalDebit");
        parameter.put("TotalCredit", "TotalCredit");


//         parameter.put("OpeningBalance","OpeningBalance");
//         parameter.put("ClosingBalance","ClosingBalance");
        //parameter.put("subreporttrial",new JRTableModelDataSource(tableLedger.getModel()));


        parameter.put("TotalDebit", "Total");
        parameter.put("TotalCredit", "Total");
        SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
        parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
        parameter.put("dateTo", saDa.format(dat2.getDate()).toString());


        PrintAllReport.printtrialReport(parameter, new JRTableModelDataSource(tableLedger.getModel()));

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
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String date1 = f.format(dat1.getDate());
            String date2 = f.format(dat2.getDate());


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
            } //        if (dat1.getDate() == null) {
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
                panelLedgerDetails.setVisible(true);

                labelTotalCredit.setText("0");
                labelTotalDebit.setText("0");
                DecimalFormat decformat = new DecimalFormat("#.##");

                try {
                    Connection conn = DatabaseConnection1.GetConnection();
                    Statement st = conn.createStatement();
                    Statement st1 = conn.createStatement();

                    System.out.println("1");

                    Double totalDebit = 0d;
                    Double totalCredit = 0d;

//
//                SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
//                String date1 = f.format(dat1.getDate());
//                String date2 = f.format(dat2.getDate());

                    //-----------------------Table: Ledger--------------------------------------------
                    DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(4);
                    System.out.println("2");

                    //find ledger ID
                    i = 0;


                    double o = 00f;

                    DecimalFormat df = new DecimalFormat("#.##");
                    Long grpIdPrimary = 0l;
                    q = "select group_id from tblgroup where group_name='Primary'";
                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        grpIdPrimary = rs.getLong("group_id");
                    }
                    rs.close();

                    q = "select group_id,group_name from tblgroup where group_under=" + grpIdPrimary;
                    ResultSet results11 = st.executeQuery(q);
                    while (results11.next()) {
                        Long grpid = results11.getLong("group_id");
                        System.out.println("grpId:" + grpid + " grpPrimary:" + grpIdPrimary);
                        if (grpid != grpIdPrimary) {
                            System.out.println("grpId:" + grpid + " grpPrimary:" + grpIdPrimary);
                            System.out.println("3");
                            //---------------new query------------------
                            double creditAmt = 00f;
                            double debitAmt = 00f;
                            String groupName = results11.getString("group_name");


                            ArrayList<Long> cash_ids = new ArrayList<Long>();
                            int ind = 0;
                            cash_ids.add(grpid);
                            int flg = 0;
                            System.out.println("4");
                            do {
                                System.out.println("5:" + cash_ids.get(ind));
                                System.out.println(groupName);
                                q = "select group_id from tblgroup where group_under=" + cash_ids.get(ind) + "";
                                ResultSet results12;
                                results12 = st1.executeQuery(q);
                                while (results12.next()) {
                                    cash_ids.add(results12.getLong("group_id"));
                                }
                                System.out.println("6:" + cash_ids.size());
                                results12.close();
                                if (ind == cash_ids.size() - 1) {
                                    flg = 1;
                                }
                                ind++;
                            } while (flg == 0);
                            System.out.println("7:" + cash_ids.size());
                            //-------------------------------------------------------------
/*
                             //                    q = "select ledger_openingBalance,opening_type,ledger_under from tblledger where ledger_id in=" + led_id + "";
                             //                    rs1 = st.executeQuery(q);
                             //                    if (rs1.next()) {
                             //                        i = 0;
                             //                        while (i < cash_ids.size()) {
                             //                            if (rs1.getLong("ledger_under") == cash_ids.get(i)) {
                             //                                flag_cash_in_hand = 1;
                             //                                break;
                             //                            }
                             //                            i++;
                             //                        }
                             //
                             //
                             //                    }

                             */

                            String temp_group_under_id = "";
                            i = 0;
                            while (i < cash_ids.size()) {
                                temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
                                i++;
                            }
//
                            String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);









                            ArrayList<Long> ledger_ids = new ArrayList<Long>();
                            int ind1 = 0;
                            q = "select ledger_id from tblledger where ledger_under in(" + group_under_id + ")";

                            ResultSet results12;
                            results12 = st1.executeQuery(q);
                            while (results12.next()) {
                                ledger_ids.add(results12.getLong("ledger_id"));
                            }



                            String temp_ledger_under_id = "";
                            i = 0;
                            System.out.print("Ledger array:" + ledger_ids.size());
                            while (i < ledger_ids.size()) {
                                temp_ledger_under_id = temp_ledger_under_id + ledger_ids.get(i) + ",";
                                i++;
                            }
                            String ledger_under_id = "-1";
                            if (ledger_ids.size() > 0) {
//
                                ledger_under_id = temp_ledger_under_id.substring(0, temp_ledger_under_id.length() - 1);
                            }


                            System.out.println("8");
                            //     q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date>='"+date1+"' and trans_date<='"+date2+"' ) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                            //q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date>='"+date1+"' and trans_date<='"+date2+"' ) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in(select ledger_id as ledger_under from tblledger where ledger_under in('"+group_under_id+"')))";
                            q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + date1 + "' and trans_date<='" + date2 + "' ) and trans_ledgerid in(" + ledger_under_id + ")";

                            rs1 = st1.executeQuery(q);

                            System.out.println("under:" + q);



                            while (rs1.next()) {
                                debitAmt = debitAmt + rs1.getDouble("totalDeb");
                            }
                            System.out.println("9");
                            System.out.println("debit:" + debitAmt);
                            rs1.close();
//
                            //q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + date1 + "' and trans_date<='" + date2 + "') and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('" + group_under_id + "'))";
                            q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + date1 + "' and trans_date<='" + date2 + "' ) and trans_ledgerid in(" + ledger_under_id + ")";
                            System.out.println("10");
                            rs1 = st1.executeQuery(q);
                            while (rs1.next()) {
                                creditAmt = creditAmt + rs1.getDouble("totalCred");
                            }
                            System.out.println("credit:" + creditAmt);
                            rs1.close();
                            System.out.println("11");
                            if (debitAmt != 0d || creditAmt != 0d) {
                                tableModel = (DefaultTableModel) tableLedger.getModel();
                                int row = tableLedger.getRowCount();
                                tableModel.setRowCount(row + 1);


                                //  tableModel = (DefaultTableModel) tableLedger.getModel();
                                //  tableModel.setRowCount(row + 1);

                                tableLedger.setValueAt(row + 1, row, 0);

                                tableLedger.setValueAt(groupName, row, 1);
                                if (debitAmt != 0d) {
                                    tableLedger.setValueAt(decformat.format(debitAmt), row, 2);
                                }
                                if (creditAmt != 0) {
                                    tableLedger.setValueAt(decformat.format(creditAmt), row, 3);
                                }

                                totalCredit += creditAmt;
                                totalDebit += debitAmt;
                            }

                            System.out.println("12");
                        }
                    }
                    results11.close();
                    System.out.println("13");
                    //----------Count Total Debitted and creditted Amount---------

                    labelTotalDebit.setText("" + decformat.format(totalDebit));
                    labelTotalCredit.setText("" + decformat.format(totalCredit));


                } catch (SQLException ex) {
                    // Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LedgerReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        setColumnWidth(tableLedger);
    }//GEN-LAST:event_btnShowActionPerformed

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
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnShow.requestFocus();
        }
    }//GEN-LAST:event_dat2KeyPressed

    private void dat1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dat1FocusLost
        /*        if (dat1.getDate() == null) {
         Calendar currentDate = Calendar.getInstance();
         dat1.setDate(currentDate.getTime());
         }*/
    }//GEN-LAST:event_dat1FocusLost

    private void dat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dat1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (dat1.getDate() == null) {
                dat1.requestFocus();
            } else {
                dat2.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        }
    }//GEN-LAST:event_dat1KeyPressed

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
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnPrint.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }

    }//GEN-LAST:event_btnBackKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private com.toedter.calendar.JDateChooser dat1;
    private com.toedter.calendar.JDateChooser dat2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JPanel panelLedgerDetails;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTable tableLedger;
    // End of variables declaration//GEN-END:variables

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        //  passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(40);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(40);

        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(265);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(265);


        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(180);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(180);
        passedTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);


        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(180);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(180);
        passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }
}
