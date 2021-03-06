/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.database.connection.DayBookHelper;
import gen.database.connection.LedgerReportHelper;
import gen.dto.Constants;
import gen.mainclass.MainClass;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import ledgerreport.DayBookDTO;

/**
 *
 * @author pc5
 */
public class DayBook2 extends javax.swing.JInternalFrame {

    /**
     * Creates new form DayBook2
     */
    DecimalFormat df = new DecimalFormat("#.##");
    private final JTextField tfDatePickerfromDate;
    private final JTextField tfDatePickertoDate;
    DefaultTableModel dayBookCreditTableModel;
    DefaultTableModel dayBookDebitTableModel;
    int k = 0;
    JTextField text = new JTextField();
    Double totalSale, totalPurchase, totalReceipt, totalPayment, openigBal, totalDebit, totalCredit, closingBal;

    public DayBook2(String s) {
        initComponents();
        setClosable(true);
        initComponents();
        fromDate.setDateFormatString("dd-MM-yyyy");
        toDate.setDateFormatString("dd-MM-yyyy");
        this.setTitle(s);
        tfDatePickerfromDate = (JTextField) fromDate.getComponent(1);
        tfDatePickertoDate = (JTextField) toDate.getComponent(1);
        initilize();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fromDate = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        toDate = new com.toedter.calendar.JDateChooser();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDayBookCredit = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDayBookDebit = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fromDate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.jLabel3.text")); // NOI18N

        toDate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnShow.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnShow, org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.btnShow.text")); // NOI18N
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPrint, org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.btnPrint.text")); // NOI18N
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShow)
                .addGap(18, 18, 18)
                .addComponent(btnPrint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnPrint))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableDayBookCredit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableDayBookCredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Particulars", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDayBookCredit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDayBookCreditMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDayBookCredit);
        tableDayBookCredit.getColumnModel().getColumn(0).setResizable(false);
        tableDayBookCredit.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.tableDayBookCredit.columnModel.title0")); // NOI18N
        tableDayBookCredit.getColumnModel().getColumn(1).setResizable(false);
        tableDayBookCredit.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.tableDayBookCredit.columnModel.title1")); // NOI18N

        tableDayBookDebit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableDayBookDebit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Particulars", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDayBookDebit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDayBookDebitMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableDayBookDebit);
        tableDayBookDebit.getColumnModel().getColumn(0).setResizable(false);
        tableDayBookDebit.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.tableDayBookDebit.columnModel.title0")); // NOI18N
        tableDayBookDebit.getColumnModel().getColumn(1).setResizable(false);
        tableDayBookDebit.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.tableDayBookDebit.columnModel.title1")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DayBook2.class, "DayBook2.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(183, 183, 183))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            bindToGUI();
        } catch (SQLException ex) {
            Logger.getLogger(Ledger1.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tableDayBookCreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDayBookCreditMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDayBookCreditMouseClicked

    private void tableDayBookDebitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDayBookDebitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDayBookDebitMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDayBookCredit;
    private javax.swing.JTable tableDayBookDebit;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables

    private void initilize() {

        initilizetfDatePickerfromDate();
        initilizetfDatePickertoDate();
        initJTable();
    }

    private void initilizetfDatePickerfromDate() {
        tfDatePickerfromDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                System.out.println("code:" + e.getKeyCode());

                if (code == KeyEvent.VK_ENTER) {
                    //txtAccount.requestFocus();
                    System.out.println("DAte From DatePIcker" + (fromDate.getDate() == null));

                    if (fromDate.getDate() == null) {
                        JOptionPane.showMessageDialog(rootPane, "Please Select Valid Date");
                        tfDatePickerfromDate.requestFocus();
                    } else {
                        tfDatePickertoDate.requestFocus();
                    }

                }

            }
        });
        tfDatePickerfromDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePickerfromDate.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void initilizetfDatePickertoDate() {
        tfDatePickertoDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
                    btnShowActionPerformed(null);
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePickerfromDate.requestFocus();
                }

            }
        });
        tfDatePickertoDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePickertoDate.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void initJTable() {
        dayBookCreditTableModel = (DefaultTableModel) tableDayBookCredit.getModel();
        dayBookCreditTableModel.setRowCount(0);
        dayBookCreditTableModel.setColumnCount(2);

        dayBookDebitTableModel = (DefaultTableModel) tableDayBookDebit.getModel();
        dayBookDebitTableModel.setRowCount(0);
        dayBookDebitTableModel.setColumnCount(2);

        Calendar currentDate = Calendar.getInstance();
        fromDate.setDate(currentDate.getTime());
        toDate.setDate(currentDate.getTime());

        tableDayBookCredit.setCellSelectionEnabled(true);
        tableDayBookDebit.setCellSelectionEnabled(true);
    }

    private void bindToGUI() throws SQLException {
        //get sale List
        initilizeVariables();

        loadCreaditTransacations();
        loadDebitTrancastions();

    }

    private void loadCreaditTransacations() throws SQLException {
        Double valueToReturn = 0D;
        dayBookCreditTableModel.setRowCount(0);
        loadPurchaseTransaction();

    }

    private void loadDebitTrancastions() throws SQLException {
        Double valueToReturn = 0D;
        dayBookDebitTableModel.setRowCount(0);
        loadSaleTransaction();

    }

    private Double loadSaleTransaction() throws SQLException {
//        Double valueToReturn = 0D;
//        List<DayBookDTO> dayBookDTOList = DayBookHelper.getDaybookLedgerList(fromDate.getDate(), toDate.getDate(),  Constants.DEBIT, null, null);
//        if (dayBookDTOList != null && dayBookDTOList.size() > 0) {
//           
////            tableDayBookDebit.setSelectionBackground(Color.GRAY);
////            tableDayBookDebit.setRowSelectionInterval(0, 0);
//            tableDayBookDebit.getColumnModel().getColumn(1).setCellRenderer(new DayBook2.CustomRenderer(1, 0));
//            
//            for (DayBookDTO dayBook : dayBookDTOList) {
//                dayBookDebitTableModel.setRowCount(dayBookDebitTableModel.getRowCount() + 1);
//               
//                dayBookDebitTableModel.setValueAt(dayBook.getLedgerName(), dayBookDebitTableModel.getRowCount() - 1, 0);
//                dayBookDebitTableModel.setValueAt(df.format(dayBook.getAmount()), dayBookDebitTableModel.getRowCount() - 1, 1);
//               
//                valueToReturn = valueToReturn + dayBook.getAmount();
//            }
//        }
        return null;
    }

    private Double loadPurchaseTransaction() throws SQLException {
//        Double valueToReturn = 0D;
//        List<DayBookDTO> dayBookDTOList = DayBookHelper.getDaybookLedgerList(fromDate.getDate(), toDate.getDate(),  Constants.CREDIT, null, null);
//        if (dayBookDTOList != null && dayBookDTOList.size() > 0) {
//           
//            tableDayBookCredit.getColumnModel().getColumn(1).setCellRenderer(new DayBook2.CustomRenderer(1, 0));
//            for (DayBookDTO dayBook : dayBookDTOList) {
//                dayBookCreditTableModel.setRowCount(dayBookCreditTableModel.getRowCount() + 1);
//                
//                dayBookCreditTableModel.setValueAt(dayBook.getLedgerName(), dayBookCreditTableModel.getRowCount() - 1, 0);
//                dayBookCreditTableModel.setValueAt(df.format(dayBook.getAmount()), dayBookCreditTableModel.getRowCount() - 1,1);
//               
//                valueToReturn = valueToReturn + dayBook.getAmount();
//            }
//        }
        return null;
    }

    private void initilizeVariables() {
        totalPayment = 0D;
        totalPurchase = 0D;
        totalReceipt = 0D;
        totalSale = 0D;
        openigBal = 0D;
        totalDebit = 0D;
        totalCredit = 0D;
        closingBal = 0D;
    }

    class CustomRenderer extends DefaultTableCellRenderer {

        int col;
        int row;

        public CustomRenderer(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);



            setBackground((column == this.col && row == this.row)
                    ? Color.YELLOW : Color.WHITE);
            setForeground((column == this.col && row == this.row)
                    ? Color.red : Color.BLACK);



            return c;
        }
    }
}
