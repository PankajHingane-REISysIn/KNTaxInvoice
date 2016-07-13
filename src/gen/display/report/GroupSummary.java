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
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class GroupSummary extends javax.swing.JInternalFrame {
    //Database db=new Database();     //Object of class for database 

    ArrayList<TemporaryClass> groupList = new ArrayList<TemporaryClass>();
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3;
    String q = "", str = "";
    int flag_cash_in_hand = 0;
    JTextField tf;
    //ArrayList<String> GroupItems=new ArrayList<String>();
    private final Vector<String> v = new Vector<String>();
    private final JTextField tfDatePicker;
    private final JTextField tfDatePicker1;
    // private boolean hide_flag = false;

    /**
     * Creates new form LedgerReport
     */
    public GroupSummary(String s) {
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
                    comboGroup.requestFocus();
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
        comboGroup = new javax.swing.JComboBox();
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

        labelTotalDebit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTotalDebit.setText("jLabel6");

        labelTotalCredit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
                        .addComponent(labelTotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(labelTotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
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
        jLabel1.setText("Group Summary");

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

        comboGroup.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        comboGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboGroupKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Group Name");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(dat1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dat2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShow)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrint))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(comboGroup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
                        .addGap(323, 323, 323)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelLedgerDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelMainLayout.createSequentialGroup()
                        .addGap(613, 613, 613)
                        .addComponent(jLabel1)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLedgerDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
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

            q = "select group_id,group_name from tblgroup order by group_name";
            rs1 = st.executeQuery(q);
            comboGroup.addItem("");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            comboGroup.setEditable(true);
            tf = (JTextField) comboGroup.getEditor().getEditorComponent();


            while (rs1.next()) {
                TemporaryClass t1 = new TemporaryClass();
                t1.setLedger_id(Integer.parseInt(rs1.getString("group_id")));
                t1.setLedger_name(rs1.getString("group_name"));
                groupList.add(t1);
                comboGroup.addItem(t1.getLedger_name());

                v.add(rs1.getString("group_name"));
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
                comboGroup.addItem(v.get(i));
            }

            tf.addKeyListener(new KeyAdapter() {
                public void keyTyped(final KeyEvent e) {
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String text = tf.getText();
                            if (text.length() == 0) {
                                comboGroup.hidePopup();
                                setModel(new DefaultComboBoxModel(v), "");
                            } else {
                                DefaultComboBoxModel m = getSuggestedModel(v, text);
                                if (m.getSize() == 0 || hide_flag) {
                                    comboGroup.hidePopup();
                                    hide_flag = false;
                                } else {
                                    setModel(m, text);
                                    comboGroup.showPopup();
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
                                        comboGroup.setSelectedIndex(-1);
                                        tf.setText(str);
                                        return;
                                    }
                                }
                            }

                        }
                    });

                }
            });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            tf.addKeyListener(new KeyAdapter() {
                public void keyPressed(final KeyEvent event) {
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {


                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (comboGroup.getSelectedItem().equals("")) {
                                    // JOptionPane.showMessageDialog(comboGroup,"Please Select Group Name");
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


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(4);

            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
            String dateNow = f.format(dat1.getDate());
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
        panelLedgerDetails.setVisible(false);
        comboGroup.requestFocus();
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void tableLedgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLedgerMouseClicked
        int row = tableLedger.getSelectedRow();
        if (tableLedger.getValueAt(row, 2) != null) {
            if (tableLedger.getValueAt(row, 3).toString().equalsIgnoreCase("Receipt")) {
                try {
                    String id = tableLedger.getValueAt(tableLedger.getSelectedRow(), 2).toString();
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
                    Logger.getLogger(GroupSummary.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(GroupSummary.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(GroupSummary.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(GroupSummary.class.getName()).log(Level.SEVERE, null, ex);
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
//         Map parameter=new HashMap();
//	 parameter.put("Groups","Groups");
//         parameter.put("Sr.No","Sr.No");
//         
//         parameter.put("Debit","Debit");
//          parameter.put("Credit","Credit");
//          parameter.put("groupName",comboGroup.getSelectedItem());
//          parameter.put("dateFrom",dat1.getDate());
//          parameter.put("dateTo",dat2.getDate());
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
//         PrintAllReport.printgroupReport(parameter,new JRTableModelDataSource(tableLedger.getModel()));
        Map parameter = new HashMap();
        parameter.put("Groups", "Groups");
        parameter.put("Sr.No", "Sr.No");

        parameter.put("Debit", "Debit");
        parameter.put("Credit", "Credit");
        parameter.put("groupName", comboGroup.getSelectedItem());
        SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
        parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
        parameter.put("dateTo", saDa.format(dat2.getDate()).toString());

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
        SimpleDateFormat saDa1 = new SimpleDateFormat("dd-MM-yyyy");
        parameter.put("dateFrom", saDa1.format(dat1.getDate()).toString());
        parameter.put("dateTo", saDa1.format(dat2.getDate()).toString());


        PrintAllReport.printgroupReport(parameter, new JRTableModelDataSource(tableLedger.getModel()));




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

            if (comboGroup.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select the Ledger");
                comboGroup.requestFocus();
            } else if (dat1.getDate() == null) {
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
            }

//        if (comboGroup.getSelectedItem().equals("")) {
//            JOptionPane.showMessageDialog(this, "Please select the Group");
//            comboGroup.requestFocus();
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


            panelLedgerDetails.setVisible(true);
            labelTotalCredit.setText("0");
            labelTotalDebit.setText("0");
            DecimalFormat decformat = new DecimalFormat("#.##");

            try {
                Connection conn = DatabaseConnection1.GetConnection();
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                Statement st2 = conn.createStatement();
                Double ledgerTotalDebit = 0d;
                Double ledgerTotalCredit = 0d;
                Double totalDebit = 0d;
                Double totalCredit = 0d;
//            SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
//            String date1 = f.format(dat1.getDate());
//            String date2 = f.format(dat2.getDate());

                //-----------------------Table: Ledger--------------------------------------------
                DefaultTableModel tableModel = (DefaultTableModel) tableLedger.getModel();
                tableModel.setRowCount(0);
                tableModel.setColumnCount(4);



                ArrayList<Long> cash_ids = new ArrayList<Long>();
                int ind = 0;
                for (TemporaryClass temp : this.groupList) {
                    if (temp.ledger_name.equalsIgnoreCase(tf.getText().trim())) {
                        cash_ids.add(temp.ledger_id);
                    }
                }

                int flg = 0;
                do {

                    //System.out.println(groupName);
                    q = "select group_id,group_name from tblgroup where group_under=" + cash_ids.get(ind) + "";
                    ResultSet results12;
                    results12 = st1.executeQuery(q);
                    while (results12.next()) {
                        cash_ids.add(results12.getLong("group_id"));
                        System.out.println(results12.getString("group_name"));
                    }
                    System.out.println("6:" + cash_ids.size());
                    results12.close();
                    if (ind == cash_ids.size() - 1) {
                        flg = 1;
                    }
                    ind++;
                } while (flg == 0);
                System.out.println("7:" + cash_ids.size());

                String temp_group_under_id = "";
                i = 0;
                while (i < cash_ids.size()) {
                    temp_group_under_id = temp_group_under_id + cash_ids.get(i) + ",";
                    i++;
                }//
                String group_under_id = temp_group_under_id.substring(0, temp_group_under_id.length() - 1);
                ArrayList<Long> ledger_ids = new ArrayList<Long>();
                int ind1 = 0;
                q = "select ledger_id,ledger_name from tblledger where ledger_under in(" + group_under_id + ")";
                System.out.println("Query:=" + q);
                ResultSet results12;
                results12 = st.executeQuery(q);
                while (results12.next()) {
                    Long ledgerId = results12.getLong("ledger_id");
                    //ledger_ids.add(results12.getLong("ledger_id"));
                    q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + date1 + "' and trans_date<='" + date2 + "' ) and trans_ledgerid =" + ledgerId;
                    rs1 = st1.executeQuery(q);
                    while (rs1.next()) {
                        ledgerTotalDebit = rs1.getDouble("totalDeb");
                    }
                    rs1.close();
                    q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date>='" + date1 + "' and trans_date<='" + date2 + "' ) and trans_ledgerid =" + ledgerId;
                    rs1 = st1.executeQuery(q);
                    while (rs1.next()) {
                        ledgerTotalCredit = rs1.getDouble("totalCred");
                    }
                    rs1.close();
                    totalCredit += ledgerTotalCredit;
                    totalDebit += ledgerTotalDebit;
                    tableModel = (DefaultTableModel) tableLedger.getModel();
                    if (ledgerTotalDebit != 0d || ledgerTotalCredit != 0d) {
                        // int row = tableLedger.getRowCount();
                        //        tableModel = (DefaultTableModel) tableLedger.getModel();
                        int row = tableLedger.getRowCount();
                        tableModel.setRowCount(row + 1);

                        //  for(int i=1;i<=row;i++)
                        // {
                        tableLedger.setValueAt(row + 1, row, 0);
                        //}

                        tableLedger.setValueAt(results12.getString("ledger_name"), row, 1);
                        if (ledgerTotalDebit != 0) {
                            tableLedger.setValueAt(String.valueOf(decformat.format(ledgerTotalDebit)), row, 2);
                        }
                        if (ledgerTotalCredit != 0) {
                            tableLedger.setValueAt(String.valueOf(decformat.format(ledgerTotalCredit)), row, 3);
                        }
                    }

                }
                labelTotalDebit.setText("" + String.valueOf(decformat.format(totalDebit)));
                labelTotalCredit.setText("" + String.valueOf(decformat.format(totalCredit)));



            } catch (Exception e) {
                e.printStackTrace();
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
        /*        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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
         }*/
    }//GEN-LAST:event_dat2KeyPressed

    private void dat1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dat1FocusLost
        if (dat1.getDate() == null) {
            Calendar currentDate = Calendar.getInstance();
            dat1.setDate(currentDate.getTime());
        }
    }//GEN-LAST:event_dat1FocusLost

    private void dat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dat1KeyPressed
        /*        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_RIGHT) {
         if (dat1.getDate() == null) {
         dat1.requestFocus();
         } else {
         dat2.requestFocus();
         }
         }
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
         comboGroup.requestFocus();
         }*/
    }//GEN-LAST:event_dat1KeyPressed

    private void comboGroupKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboGroupKeyPressed
        /*        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
         if (comboGroup.getSelectedItem().equals("")) {
         comboGroup.showPopup();
         } else {
         dat1.requestFocus();
         }
         }
         if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
         btnBackActionPerformed(null);
         }*/
    }//GEN-LAST:event_comboGroupKeyPressed

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
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            btnPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPrint.requestFocus();
        }
    }//GEN-LAST:event_btnBackKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        comboGroup.setPopupVisible(true);
    }//GEN-LAST:event_formInternalFrameActivated
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox comboGroup;
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
    private javax.swing.JLabel labelTotalCredit;
    private javax.swing.JLabel labelTotalDebit;
    private javax.swing.JPanel panelLedgerDetails;
    private javax.swing.JPanel panelMain;
    private javax.swing.JTable tableLedger;
    // End of variables declaration//GEN-END:variables
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        comboGroup.setModel(mdl);
        comboGroup.setSelectedIndex(-1);
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
        passedTable.getColumnModel().getColumn(3).setMinWidth(165);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(165);
        passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }
}
