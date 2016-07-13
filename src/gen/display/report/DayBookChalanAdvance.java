package gen.display.report;

import gen.display.report.*;
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.database.connection.DatabaseConnection1;
import gen.database.connection.DatabaseConnection1;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.*;
import java.awt.Dimension;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class DayBookChalanAdvance extends javax.swing.JInternalFrame {
    //Database db=new Database();     //Object of class for database 

    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    String path = "", printtime = "";
    private final JTextField tfDatePicker;
    private final JTextField tfDatePicker1;
    private boolean hide_flag = false;
    Set<String> chalanIDSet = new TreeSet<String>();

    public DayBookChalanAdvance(String s) {
        setClosable(true);
        initComponents();
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // tfDatePicker1 = (JTextField) dat2.getComponent(1);
        //    System.out.println(tfDatePicker.toString());



        //   tf = (JTextField) btnShow.getEditor().getEditorComponent();//Edits the component in combobox.



        tfDatePicker1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollpaneMain = new javax.swing.JScrollPane();
        panelMain = new javax.swing.JPanel();
        panelDebit = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableChalan = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        dat1 = new com.toedter.calendar.JDateChooser();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        dat2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        btnChalan = new javax.swing.JButton();

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

        scrollpaneMain.setPreferredSize(new java.awt.Dimension(1016, 1191));

        panelMain.setPreferredSize(new java.awt.Dimension(850, 400));

        panelDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableChalan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {
                "Sr.No","Particulars","VCH No","Quantity"
            }
        ));
        tableChalan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableChalan.getTableHeader().setResizingAllowed(false);
        tableChalan.getTableHeader().setReorderingAllowed(false);
        tableChalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableChalanMouseClicked(evt);
            }
        });
        tableChalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableChalanKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableChalan);

        org.jdesktop.layout.GroupLayout panelDebitLayout = new org.jdesktop.layout.GroupLayout(panelDebit);
        panelDebit.setLayout(panelDebitLayout);
        panelDebitLayout.setHorizontalGroup(
            panelDebitLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelDebitLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDebitLayout.setVerticalGroup(
            panelDebitLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelDebitLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        dat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dat1KeyPressed(evt);
            }
        });

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
        jLabel1.setText("DayBook For Chalan");

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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("To");

        btnChalan.setText("Export");
        btnChalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChalanActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panelMainLayout = new org.jdesktop.layout.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelMainLayout.createSequentialGroup()
                .add(panelMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelMainLayout.createSequentialGroup()
                        .add(330, 330, 330)
                        .add(panelMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(panelDebit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(panelMainLayout.createSequentialGroup()
                                .add(dat1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 158, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(dat2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(btnShow)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnPrint)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnBack)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnChalan))))
                    .add(panelMainLayout.createSequentialGroup()
                        .add(565, 565, 565)
                        .add(jLabel1)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .add(14, 14, 14)
                .add(panelMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelMainLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btnShow)
                        .add(btnPrint)
                        .add(btnBack)
                        .add(btnChalan))
                    .add(dat2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(dat1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelDebit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        scrollpaneMain.setViewportView(panelMain);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollpaneMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollpaneMain, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableChalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableChalanMouseClicked
        int row = tableChalan.getSelectedRow();
        if (tableChalan.getValueAt(row, 1) != null && tableChalan.getValueAt(row, 2) != null) {
            try {
                String id = tableChalan.getValueAt(tableChalan.getSelectedRow(), 2).toString();
                ChalanDTO chalanDTO = new ChalanDTO();
                ChalanForm1 c = new ChalanForm1(chalanDTO, true);
                c.loadEditForm(id);
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
            } catch (Exception ex) {
                Logger.getLogger(DayBookChalanAdvance.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_tableChalanMouseClicked

    private void tableChalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableChalanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tableChalan.getSelectedRow();
            if (tableChalan.getRowCount() > 0) {
                if (tableChalan.getValueAt(i, 0) == null) {
                    dat1.requestFocus();
                } else {
                    tableChalanMouseClicked(null);
                }
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dat1.requestFocus();
        }
}//GEN-LAST:event_tableChalanKeyPressed

    private void dat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dat1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnShowActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnBackActionPerformed(null);
        }
}//GEN-LAST:event_dat1KeyPressed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        if (dat1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat1.requestFocus();
        } else if (dat2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat2.requestFocus();
        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            dat2.requestFocus();
        } else {
            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = f.format(dat1.getDate());
                /* Date For Printing in DD/MM/YYYY Format.............*/
                SimpleDateFormat printdateFormat = new SimpleDateFormat("dd/MM/yyyy");


                java.util.Date date1 = f.parse(dateNow);

                dateNow = f.format(dat2.getDate());
                java.util.Date date2 = f.parse(dateNow);

                //     System.out.println("UTIL DATE..................."+date1);
                while (date1.compareTo(date2) != 0) {
                    try {
                        loadChalanEntry();
                        Thread.sleep(500);
                        date1 = new Date(date1.getTime() + 1 * 86400000);
                        String time = f.format(date1);
                        printtime = printdateFormat.format(date1);
                        System.out.println("----------------->>time : " + time);
                        dat1.setDate(date1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DayBookAdvance1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(DayBookAdvance1.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
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
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableChalan.getRowCount() > 0) {
                tableChalan.requestFocus();
                tableChalan.changeSelection(0, 0, false, false);
            } else {
                btnShow.requestFocus();
            }
        }
}//GEN-LAST:event_btnShowKeyPressed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        Map parameter = new HashMap();
        SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
        parameter.put("dateFrom", saDa.format(this.dat1.getDate()).toString());
        parameter.put("dateTo", saDa.format(this.dat1.getDate()).toString());
        PrintAllReport.printDAybookchalanReport(new JRTableModelDataSource(this.tableChalan.getModel()), parameter);
}//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnPrintActionPerformed(null);
        }
        /*  if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
         btnShow.requestFocus();
         }*/
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnBack.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnShow.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (tableChalan.getRowCount() > 0) {
                tableChalan.requestFocus();
                tableChalan.changeSelection(0, 0, false, false);
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

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Calendar currentDate = Calendar.getInstance();
        dat1.setDate(currentDate.getTime());
        dat2.setDate(currentDate.getTime());

        DefaultTableModel tableModel = (DefaultTableModel) tableChalan.getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(4);

        btnShow.setMnemonic(KeyEvent.VK_S);
        btnPrint.setMnemonic(KeyEvent.VK_P);
        btnBack.setMnemonic(KeyEvent.VK_B);

        btnShowActionPerformed(null);

        scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

        dat1.requestFocus();

        //tableChalan.setGridColor(Color.red);
        tableChalan.setShowGrid(true);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnChalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChalanActionPerformed
        // TODO add your handling code here:

        imageloading o = new imageloading();


        BufferedWriter out = null;
        try {
            // TODO add your handling code here:
            Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
            IDMapSet.put("Chalan", chalanIDSet);

            String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
            System.out.println("------------------------->>XMLCODE:" + xmlCode);

            //File file = new File("C:\\ChalanBook.xml");
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


    }//GEN-LAST:event_btnChalanActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        loadChalanEntry();
    }//GEN-LAST:event_formInternalFrameActivated

    public void loadChalanEntry() {
        chalanIDSet = new TreeSet<String>();
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = f.format(dat1.getDate());
            String date2 = f.format(dat2.getDate());

            System.out.println("Date............................................" + date1);

            //-----------------------Table: Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableChalan.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(4);

//By Pankaj   q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_index=1 and trans_id in(select trans_id from tbltransactionmain where trans_date>='"+ date1 +"' and trans_date<='"+ date2 +"' and trans_typeIndex=7)";
            System.out.println("*********   you r at date........................................" + date1);
            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_index=1 and trans_id in(select trans_id from tbltransactionmain where trans_date ='" + date1 + "'  and trans_typeIndex=7)";
            rs2 = st1.executeQuery(q);
            int k = 0;    //for putting amount with table header
            while (rs2.next()) {
                System.out.println("********* you r at date but Inside while........................................" + date1);
                //System.out.println("Entry found");
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableChalan.getRowCount();
                    tableModel.setRowCount(row + 1);

                    //Set serial no
                    tableChalan.setValueAt(row + 1, row, 0);

                    //Get Ledger name
                    tableChalan.setValueAt(rs3.getString("ledger_name"), row, 1);

                    //VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id=" + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableChalan.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
                        chalanIDSet.add(tableChalan.getValueAt(row, 2).toString());
                    }
                    rs4.close();

                    //Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableChalan.setValueAt(rs4.getDouble("totalQty"), row, 3);
                    }
                    rs4.close();


                }


            }

            //--------------------------------------------------------------
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChalan;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private com.toedter.calendar.JDateChooser dat1;
    private com.toedter.calendar.JDateChooser dat2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelDebit;
    private javax.swing.JPanel panelMain;
    private javax.swing.JScrollPane scrollpaneMain;
    private javax.swing.JTable tableChalan;
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
}
