/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import gen.ImpExp.TagsHelper1;
import gen.database.connection.LedgerReportHelper;
import gen.dto.GroupDTO;
import gen.dto.LedgerDTO;
import gen.dto.LedgerReportDTO;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author pc5
 */
public class Ledger1 extends javax.swing.JInternalFrame {

    /**
     * Creates new form Ledger1
     */
    private final JTextField tfDatePickerfromDate;
    private final JTextField tfDatePickertoDate;
    DefaultTableModel ledgerTableModel;

    public Ledger1(String s) {
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
        jLabel1 = new javax.swing.JLabel();
        toDate = new com.toedter.calendar.JDateChooser();
        btnShow = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLedgerBalance = new javax.swing.JTable();

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(Ledger1.class, "Ledger1.jLabel1.text")); // NOI18N

        toDate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        btnShow.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnShow, org.openide.util.NbBundle.getMessage(Ledger1.class, "Ledger1.btnShow.text")); // NOI18N
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(btnPrint, org.openide.util.NbBundle.getMessage(Ledger1.class, "Ledger1.btnPrint.text")); // NOI18N
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
                .addGap(81, 81, 81)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnPrint))
                    .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableLedgerBalance.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tableLedgerBalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ledger Name", "Closing Credit", "Closing Debit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableLedgerBalance);
        tableLedgerBalance.getColumnModel().getColumn(0).setResizable(false);
        tableLedgerBalance.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(Ledger1.class, "Ledger1.tableLedgerBalance.columnModel.title0")); // NOI18N
        tableLedgerBalance.getColumnModel().getColumn(1).setResizable(false);
        tableLedgerBalance.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(Ledger1.class, "Ledger1.tableLedgerBalance.columnModel.title1")); // NOI18N
        tableLedgerBalance.getColumnModel().getColumn(2).setResizable(false);
        tableLedgerBalance.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(Ledger1.class, "Ledger1.tableLedgerBalance.columnModel.title2")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(402, 402, 402)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        try {
            // TODO add your handling code here:
            bindToGUI();
        } catch (SQLException ex) {
            Logger.getLogger(Ledger1.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnShowActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:        
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        Map parameter = new HashMap();
        parameter.put("Groups", "Groups");
        parameter.put("Sr.No", "Sr.No");

        parameter.put("Debit", "Closing Credit");
        parameter.put("Credit", "Closing Debit");

        // String TotalDebitValue=labelTotalDebit.getText(); 
        // String TotalCreditValue=labelTotalCredit.getText(); 
        //  System.out.println(""+TotalAmountValue);

        // String TotalAmount=labelTotalCredit.getText();

        //  parameter.put("TotalDebitValue",TotalDebitValue);
        //  parameter.put("TotalCreditValue",TotalCreditValue);


        parameter.put("TotalDebit", "TotalDebit");
        parameter.put("TotalCredit", "TotalCredit");


//         parameter.put("OpeningBalance","OpeningBalance");
//         parameter.put("ClosingBalance","ClosingBalance");
        //parameter.put("subreporttrial",new JRTableModelDataSource(tableLedger.getModel()));


        parameter.put("TotalDebit", "Total");
        parameter.put("TotalCredit", "Total");
        SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
        parameter.put("dateFrom", saDa.format(fromDate.getDate()).toString());
        parameter.put("dateTo", saDa.format(toDate.getDate()).toString());

        parameter.put("header", "Ledger Report");

        PrintAllReport.printledgerbalanceReport(parameter, new JRTableModelDataSource(tableLedgerBalance.getModel()));

    }//GEN-LAST:event_btnPrintActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        MainClass.setstaticvar();
    }//GEN-LAST:event_formInternalFrameClosed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableLedgerBalance;
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
        ledgerTableModel = (DefaultTableModel) tableLedgerBalance.getModel();
        ledgerTableModel.setRowCount(0);
        ledgerTableModel.setColumnCount(3);
        Calendar currentDate = Calendar.getInstance();
        fromDate.setDate(currentDate.getTime());
        toDate.setDate(currentDate.getTime());
    }

    private void bindToGUI() throws SQLException {
        ledgerTableModel.setRowCount(0);

        Map<String, List<LedgerReportDTO>> mapTOBind = LedgerReportHelper.getLedger(fromDate.getDate(), toDate.getDate());
        for (String groupName : mapTOBind.keySet()) {

            if (mapTOBind.get(groupName) != null && mapTOBind.get(groupName).size() > 0) {
                ledgerTableModel.setRowCount(ledgerTableModel.getRowCount() + 1);
                ledgerTableModel.setValueAt(groupName, ledgerTableModel.getRowCount() - 1, 0);



                for (LedgerReportDTO ledgerReportDTO : mapTOBind.get(groupName)) {
                    ledgerTableModel.setRowCount(ledgerTableModel.getRowCount() + 1);
                    ledgerTableModel.setValueAt(ledgerReportDTO.getLedgerName(), ledgerTableModel.getRowCount() - 1, 0);

                    if (ledgerReportDTO.getClosingBal() < 0) {
                        ledgerTableModel.setValueAt(Math.abs(ledgerReportDTO.getClosingBal()), ledgerTableModel.getRowCount() - 1, 1);
                    } else if (ledgerReportDTO.getClosingBal() > 0) {
                        ledgerTableModel.setValueAt(ledgerReportDTO.getClosingBal(), ledgerTableModel.getRowCount() - 1, 2);
                    }

                }
            }
            ledgerTableModel.setRowCount(ledgerTableModel.getRowCount() + 1);

        }
    }
}
