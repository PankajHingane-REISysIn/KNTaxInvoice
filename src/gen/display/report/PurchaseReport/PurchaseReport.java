package gen.display.report.PurchaseReport;

import com.toedter.calendar.JDateChooser;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.SaleReport1.SaleReport;
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class PurchaseReport extends JInternalFrame {

    private JTable tableDebit;
    private JTextField tfDatePicker = null;
    private JTextField tfDatePicker1 = null;
    private boolean hide_flag = false;
    static double total_purchase_quantity, total_purchase_Amount, total_sales;
    String date1, date2;
    ResultSet rs1, rs2, rs3, rs4, rs5;
    String q = "", str = "";
    /**
     * Launch the application.
     */
    private JPanel panel;
    private JLabel lblDaybook;
    private JDateChooser dat1;
    private JDateChooser dat2;
    private JButton buttonShow;
    private JButton buttonPrint;
    private JButton buttonBack;
    private JButton buttonAdvancePurchaseReport;
    private JButton buttonExport;
    private JButton btnChalan;
    private JPanel panelDebit;
    private JLabel lblCredit;
    private JLabel lblTo;
    private JLabel lblDebit;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JPanel panel_3;
    private JScrollPane scrollPane_1;
    private JLabel lblNewLabel;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel lblTotalAmount;
    private JLabel labelTotalDebit;
    private JLabel labelTotalCredit;
    private JPanel PurchasePanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PurchaseReport frame = new PurchaseReport();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PurchaseReport() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                try {
                    Calendar currentDate = Calendar.getInstance();
                    dat1.setDate(currentDate.getTime());

                    dat2.setDate(currentDate.getTime());

                    DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                            .getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(8);

                    buttonShow.setMnemonic(KeyEvent.VK_S);
                    buttonPrint.setMnemonic(KeyEvent.VK_P);
                    buttonBack.setMnemonic(KeyEvent.VK_B);

                    btnShowActionPerformed(null);

                    dat1.requestFocus();

                    tableDebit.setShowGrid(true);
                    JTableHeader header1 = tableDebit.getTableHeader();
                    header1.setBackground(Color.yellow);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                MainClass.setstaticvar();
            }
        });
        setTitle("Purchase Report");
        setBounds(100, 100, 1322, 674);

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout(
                "",
                "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblDaybook = new JLabel("Purchase Report");
        lblDaybook.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDaybook.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblDaybook, "cell 1 0");

        dat1 = new JDateChooser();
        panel.add(dat1, "flowx,cell 1 1");

        lblTo = new JLabel("To");
        lblTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(lblTo, "cell 1 1");

        dat2 = new JDateChooser();
        panel.add(dat2, "cell 1 1");

        buttonShow = new JButton("Show");
        buttonShow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker1.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonPrint.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    tfDatePicker1.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableDebit.getRowCount() > 0) {
                        tableDebit.requestFocus();
                        tableDebit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });
        buttonShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnShowActionPerformed(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                }

            }
        });
        panel.add(buttonShow, "cell 1 1");

        buttonPrint = new JButton("Print");
        buttonPrint.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    // btnPrintActionPerformed(null);
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttonShow.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonBack.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonShow.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableDebit.getRowCount() > 0) {
                        tableDebit.requestFocus();
                        tableDebit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });
        buttonPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnPrintActionPerformed(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                }

            }
        });
        panel.add(buttonPrint, "cell 1 1");

        buttonBack = new JButton("Back");
        buttonBack.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonPrint.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonAdvancePurchaseReport.requestFocus();
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnBackActionPerformed(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                }

            }
        });
        panel.add(buttonBack, "cell 1 1");

        buttonAdvancePurchaseReport = new JButton("Advance Purchase Report");
//        buttonAdvancePurchaseReport.setEnabled(false);
        buttonAdvancePurchaseReport.setMnemonic('A');
        panel.add(buttonAdvancePurchaseReport, "cell 1 1");

        buttonAdvancePurchaseReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonAdvancePurchaseReportActionPerformed(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                }
            }
        });

        buttonAdvancePurchaseReport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttonBack.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonExport.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonBack.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableDebit.getRowCount() > 0) {
                        tableDebit.requestFocus();
                        tableDebit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });

        buttonExport = new JButton("Export");
//        btnNewButton_4.setEnabled(false);
        buttonExport.setMnemonic('E');
        panel.add(buttonExport, "cell 1 1");

        buttonExport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttonAdvancePurchaseReport.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonAdvancePurchaseReport.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableDebit.getRowCount() > 0) {
                        tableDebit.requestFocus();
                        tableDebit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });

        PurchasePanel = new JPanel();
        PurchasePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(PurchasePanel, "cell 1 2 1 19,grow");
        PurchasePanel
                .setLayout(new MigLayout("",
                "[0px:1280px:1280px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        PurchasePanel.add(panel_2, "cell 0 1,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane = new JScrollPane();
        panel_2.add(scrollPane, "cell 0 0,grow");

        tableDebit = new JTable();
        tableDebit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDebit.getSelectedRow();
                if (tableDebit.getValueAt(row, 1) != null && tableDebit.getValueAt(row, 5) != null) {
                    try {
                        String id = tableDebit.getValueAt(tableDebit.getSelectedRow(), 3).toString();
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
                        p.setSelected(true);
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) p
                                .getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions = (MouseMotionListener[]) north
                                .getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                    }
                }
            }
        });
        tableDebit.setModel(new DefaultTableModel(
                //                new Object[][]{{null,null, null, null, null, null},}, 
                //                new String[]{"VCH No", "Particulars", "Reference", "Quantity", "Amount", "Date"}
                new Object[][]{
                    {null, null, null, null, null, null, null},},
                new String[]{
                    "Date", "Particulars", "Reference", "VCH No", "Quantity", "Amount", "VAT Amount", "Final Amount"}));

        scrollPane.setViewportView(tableDebit);

        label_1 = new JLabel("");
        PurchasePanel.add(label_1, "flowx,cell 0 2");

        label_2 = new JLabel("");
        PurchasePanel.add(label_2, "cell 0 2");

        lblTotalAmount = new JLabel("Total Quantity");
        lblTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblTotalAmount.setVisible(false);
        PurchasePanel.add(lblTotalAmount, "cell 0 2");

        labelTotalDebit = new JLabel("0");
        labelTotalDebit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        labelTotalDebit.setVisible(false);
        PurchasePanel.add(labelTotalDebit, "cell 0 2");

        label_3 = new JLabel("");
        PurchasePanel.add(label_3, "cell 0 2");

        setClosable(true);
        dat1.setDateFormatString("dd-MM-yyyy");
        dat2.setDateFormatString("dd-MM-yyyy");

        tfDatePicker = (JTextField) dat1.getComponent(1);
        tfDatePicker1 = (JTextField) dat2.getComponent(1);
        System.out.println(tfDatePicker.toString());

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                System.out.println("code:" + e.getKeyCode());

                if (code == KeyEvent.VK_ENTER) {
                    // txtAccount.requestFocus();
                    System.out.println("DAte From DatePIcker"
                            + (dat1.getDate() == null));

                    if (dat1.getDate() == null) {
                        JOptionPane.showMessageDialog(rootPane,
                                "Please Select Valid Date");
                        tfDatePicker.requestFocus();
                    } else {
                        tfDatePicker1.requestFocus();
                    }
                } else if (code == KeyEvent.VK_ESCAPE) {
                    try {
                        btnBackActionPerformed(null);
                    } catch (PropertyVetoException ex) {
                        JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                    }
                }
            }
        });
        System.out.println(tfDatePicker.toString());

        tfDatePicker1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        // tfDatePicker1.requestFocus();
                        buttonShow.requestFocus();
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PurchaseReport.this, ex.getMessage());
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfDatePicker.requestFocus();
                }
                // btnShow.requestFocus();
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

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        if (dat1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat1.requestFocus();
            tfDatePicker.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setRowCount(0);
            labelTotalDebit.setText("0");
        } else if (dat2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat2.requestFocus();
            tfDatePicker1.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setRowCount(0);
            labelTotalDebit.setText("0");
        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            dat2.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setRowCount(0);
            labelTotalDebit.setText("0");
        } else {
            loadPurchaseEntry();
            labelTotalDebit.setText(""
                    + Constants.DECIMAL_FORMAT.format(total_purchase_quantity));

            System.out.println("Total Purchase Amount ---------- " + total_purchase_Amount);

            Connection conn = null;
            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                date1 = f.format(dat1.getDate());
                date2 = f.format(dat2.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();

                // -------------new query----------------
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=2)";
                // rs1 = st.executeQuery(q);
                // while(rs1.next()) {
                // cr=cr+rs1.getDouble("totalCred");
                // }
                // rs1.close();
                // --------------------------------------

                // System.out.println(cr);

                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=" + gen.dto.Constants.DEBIT + " and trans_id in (select trans_id from tbltransactionmain where trans_date BETWEEN '"
                        + date1
                        + "' AND '"
                        + date2
                        + "' and trans_typeIndex<>3 order by trans_date)";// where
                // trans_date<'"+
                // dateNow
                // +"' and trans_typeIndex=3)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }


                conn.commit();
                conn.close();
            } catch (Exception ex) {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                throw ex;
            }
        }

        if (tableDebit.getRowCount() == 0) {
            labelTotalDebit.setText("0");
        }

        setColumnWidth(tableDebit);
    }

    private void buttonAdvancePurchaseReportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        getDesktopPane().setLayout(new CardLayout());
        gen.display.report.PurchaseReport.AdvancePurchaseReport c = new gen.display.report.PurchaseReport.AdvancePurchaseReport();
        c.pack();
        c.setVisible(true);
        this.getParent().add(c);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        c.setSize(desktopSize);
        c.setPreferredSize(desktopSize);
        c.setSelected(true);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) c.getUI();

        Component north = ui.getNorthPane();
        MouseMotionListener[] actions = (MouseMotionListener[]) north
                .getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }
    }

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        try {
            btnShowActionPerformed(evt);
            if (dat1.getDate() != null && dat2.getDate() != null
                    && tableDebit.getRowCount() > 0
                    && dat1.getDate().getTime() <= dat2.getDate().getTime()) {
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
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) throws PropertyVetoException {
        // TODO add your handling code here:
        MainClass mainClass = new MainClass();
        mainClass.menuselection(3);
        this.setClosed(true);
    }

    public void loadPurchaseEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();
            Statement st5 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            date1 = f.format(dat1.getDate());
            date2 = f.format(dat2.getDate());

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(8);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date BETWEEN '"
                    + date1
                    + "' AND '"
                    + date2
                    + "' and trans_typeIndex=2) order by trans_id Asc";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                q = "select ledger_name from tblledger where ledger_id=" + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {

                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);
                    // tableModel = (DefaultTableModel) tableDebit.getModel();

                    // Sr No
                    // tableDebit.setValueAt(row + 1, row, 0);

                    // Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // trans_reference
                    q = "select trans_reference from tbltransactionotherdetails where trans_id = " + rs2.getLong("trans_id") + " ";
                    rs5 = st5.executeQuery(q);
                    if (rs5.next()) {
                        System.out.println("QQQQQQQQQQQQQ"
                                + rs5.getString("trans_reference") == null);
                        if (rs5.getString("trans_reference") == null) {
                            // if
                            // (rs5.getString("trans_reference").equalsIgnoreCase("0"))
                            // {
                            // tableDebit.setValueAt("-", row, 2);
                            // } else {
                            tableDebit.setValueAt("-", row, 2);
                            // }
                        } else {
                            if (rs5.getString("trans_reference")
                                    .equalsIgnoreCase("0")) {
                                tableDebit.setValueAt("-", row, 2);
                            } else {
                                tableDebit.setValueAt(rs5.getString("trans_reference"), row, 2);
                            }
                        }
                    }

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + " ";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
                        String dateSet = f1.format(rs4.getDate("trans_date"));
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"), row, 3);
                        tableDebit.setValueAt(dateSet, row, 0);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 7);

                    // Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="
                            + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getDouble("totalQty"), row, 4);
                    }
                    rs4.close();

                    //VAT amount and Basic Amount

                    q = "select od.trans_amt,tv.vat_amt from tbltransactionotherdetails as od INNER JOIN tbltransactionvat as tv on od.trans_id = tv.trans_id where od.trans_id = " + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("trans_amt")), row, 5);
                        tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 6);
                    }
                    rs4.close();
                }
                // System.out.println("Middle Purchase index: "+creditPurchaseIndex);
            }
            // --------------------------------------------------------------

            // -------------------Count Total Creditted
            // Quantity--------------------
            if (tableDebit.getRowCount() > 0) {
                int i = 0;
                double qty = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 3) != null) {
                        qty = qty
                                + Double.parseDouble(tableDebit.getValueAt(i, 4).toString());
                    }
                    i++;
                }
                total_purchase_quantity = qty;
            }

            //Calculating all Totals by Sudeep : 11-03-2014
            // Amount--------------------
            double qty = 0;
            double basicAmount = 0;
            double vatAmount = 0;
            double finalAmount = 0;
            if (tableDebit.getRowCount() > 0) {
                int i = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 4) != null) {
                        qty = qty + Double.parseDouble(tableDebit.getValueAt(i, 4).toString());
                    }
                    i++;
                }
                total_purchase_Amount = qty;

                i = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 4) != null) {
                        basicAmount = basicAmount + Double.parseDouble(tableDebit.getValueAt(i, 5).toString());
                    }
                    i++;
                }

                i = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 5) != null) {
                        vatAmount = vatAmount + Double.parseDouble(tableDebit.getValueAt(i, 6).toString());
                    }
                    i++;
                }

                i = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 5) != null) {
                        finalAmount = finalAmount + Double.parseDouble(tableDebit.getValueAt(i, 7).toString());
                    }
                    i++;
                }
            }
            // --------------------------------------------------------------------------------

            int rowCount = tableDebit.getRowCount() + 1;
            tableModel.setRowCount(rowCount + 1);
            tableDebit.setValueAt("Total", rowCount, 3);
            tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(qty), rowCount, 4);
            tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(basicAmount), rowCount, 5);
            tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(vatAmount), rowCount, 6);
            tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(finalAmount), rowCount, 7);

            conn.commit();
            conn.close();



        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    private void setColumnWidth(JTable passedTable) throws Exception {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

//        passedTable.getColumnModel().getColumn(0)
//                .setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(0).setPreferredWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMaxWidth(50);
//
//        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(1).setMinWidth(220);
//        passedTable.getColumnModel().getColumn(1).setMaxWidth(220);
//
//        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(2).setMinWidth(150);
//        passedTable.getColumnModel().getColumn(2).setMaxWidth(150);
//
//        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(3).setMinWidth(60);
//        passedTable.getColumnModel().getColumn(3).setMaxWidth(60);
        passedTable.getColumnModel().getColumn(3)
                .setCellRenderer(rightRenderer);

//        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(4).setMinWidth(100);
//        passedTable.getColumnModel().getColumn(4).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(4)
                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(5)
                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(6)
                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(7)
                .setCellRenderer(rightRenderer);

//        passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(5).setMinWidth(80);
//        passedTable.getColumnModel().getColumn(5).setMaxWidth(80);
    }
}
