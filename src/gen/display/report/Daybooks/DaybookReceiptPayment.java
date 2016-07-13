package gen.display.report.Daybooks;

import com.toedter.calendar.JDateChooser;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.payment.PaymentForm;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.receipt.ReceiptForm;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.DayBookReceiptPayment;
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class DaybookReceiptPayment extends JInternalFrame {

    private JTable tableCredit;
    private JTable tableDebit;
    private JTable combine;
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static double total_receipt, total_payment;
    private JTextField tfDatePicker = null;
    private boolean hide_flag = false;
    static int backAction = 0;
    String date = "";
    /**
     * Launch the application.
     */
    private JPanel panel;
    private JLabel lblDaybook;
    private JDateChooser dat;
    private JButton btnShow;
    private JButton btnPrint;
    private JButton btnBack;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JPanel panelDebit;
    private JLabel lblCredit;
    private JLabel lblDebit;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JPanel panel_3;
    private JScrollPane scrollPane_1;
    private JLabel lblNewLabel;
    private JLabel lblTotalAmount;
    private JLabel labelTotalDebit;
    private JLabel labelTotalCredit;
    private JPanel panel_1;
    private JScrollPane scrollpaneMain;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DaybookReceiptPayment frame = new DaybookReceiptPayment();
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
    public DaybookReceiptPayment() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent arg0) {
                try {
                    setComponentActiveInactive();
                    backAction = 1;
                    Calendar currentDate = Calendar.getInstance();
                    dat.setDate(currentDate.getTime());

                    DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                            .getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(4);

                    tableModel = (DefaultTableModel) tableDebit.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(4);

                    tableModel = (DefaultTableModel) combine.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(8);

                    btnShow.setMnemonic(KeyEvent.VK_S);
                    btnPrint.setMnemonic(KeyEvent.VK_P);
                    btnBack.setMnemonic(KeyEvent.VK_B);

                    btnShowActionPerformed(null);

                    scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

                    dat.requestFocus();

                    tableCredit.setShowGrid(true);

                    tableDebit.setShowGrid(true);

                    JTableHeader header1 = tableDebit.getTableHeader();
                    header1.setBackground(Color.yellow);

                    JTableHeader header2 = tableCredit.getTableHeader();
                    header2.setBackground(Color.yellow);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                MainClass.setstaticvar();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                setComponentActiveInactive();
                if (backAction == 2) {
                    try {
                        tfDatePicker.setText(date);
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                } else {
                    try {
                        Calendar currentDate = Calendar.getInstance();
                        dat.setDate(currentDate.getTime());

                        DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                                .getModel();
                        tableModel.setRowCount(0);
                        tableModel.setColumnCount(4);

                        tableModel = (DefaultTableModel) tableDebit.getModel();
                        tableModel.setRowCount(0);
                        tableModel.setColumnCount(4);

                        tableModel = (DefaultTableModel) combine.getModel();
                        tableModel.setRowCount(0);
                        tableModel.setColumnCount(8);

                        btnShow.setMnemonic(KeyEvent.VK_S);
                        btnPrint.setMnemonic(KeyEvent.VK_P);
                        btnBack.setMnemonic(KeyEvent.VK_B);

                        btnShowActionPerformed(null);

                        scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

                        dat.requestFocus();

                        tableCredit.setShowGrid(true);

                        tableDebit.setShowGrid(true);

                        JTableHeader header1 = tableDebit.getTableHeader();
                        header1.setBackground(Color.yellow);

                        JTableHeader header2 = tableCredit.getTableHeader();
                        header2.setBackground(Color.yellow);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                }
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                backAction = 2;
                date = tfDatePicker.getText();
            }
        });
        setTitle("Daybook For Receipt/Payment");
        setBounds(100, 100, 1322, 674);

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout(
                "",
                "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblDaybook = new JLabel("Daybook");
        lblDaybook.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDaybook.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblDaybook, "cell 1 0");

        dat = new JDateChooser();
        dat.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        btnBackActionPerformed(null);
                    } catch (PropertyVetoException ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                }
            }
        });
        panel.add(dat, "flowx,cell 1 1");

        btnShow = new JButton("Show");
        btnShow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
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
            }
        });
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    btnShowActionPerformed(arg0);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                }

            }
        });
        panel.add(btnShow, "cell 1 1");

        btnPrint = new JButton("Print");
        btnPrint.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        btnPrintActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }

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
            }
        });
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnPrintActionPerformed(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                }

            }
        });
        panel.add(btnPrint, "cell 1 1");

        btnBack = new JButton("Back");
        btnBack.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        btnBackActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }

                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnPrint.requestFocus();
                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnBackActionPerformed(e);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                }

            }
        });
        panel.add(btnBack, "cell 1 1");

        btnNewButton_3 = new JButton("Advance Daybook");
        btnNewButton_3.setEnabled(false);
        panel.add(btnNewButton_3, "cell 1 1");

        btnNewButton_4 = new JButton("Export");
        btnNewButton_4.setEnabled(false);
        panel.add(btnNewButton_4, "cell 1 1");

        panelDebit = new JPanel();
        panelDebit.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panelDebit, "cell 1 2 1 19,grow");
        panelDebit
                .setLayout(new MigLayout(
                "",
                "[0px:640px:640px,grow,shrink 50,fill][0px:640px:640px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        lblCredit = new JLabel("Credit");
        lblCredit.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
        panelDebit.add(lblCredit, "cell 0 0");

        lblDebit = new JLabel("Debit");
        lblDebit.setFont(new Font("Times New Roman", Font.BOLD, 15));
        lblDebit.setHorizontalAlignment(SwingConstants.CENTER);
        panelDebit.add(lblDebit, "cell 1 0");

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelDebit.add(panel_2, "cell 0 1,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane = new JScrollPane();
        panel_2.add(scrollPane, "cell 0 0,grow");

        tableCredit = new JTable();
        tableCredit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
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
                            //tableCreditMouseClicked(null);
                        }
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dat.requestFocus();
                }
            }
        });
        tableCredit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = tableCredit.getSelectedRow();
                if (tableCredit.getValueAt(row, 1) != null
                        && tableCredit.getValueAt(row, 3) != null) {
                    try {
                        String id = tableCredit.getValueAt(
                                tableCredit.getSelectedRow(), 2).toString();
                        ReceiptDTO receiptDTO = new ReceiptDTO();
                        ReceiptForm r = new ReceiptForm(receiptDTO, true);
                        r.loadEditForm(id);
                        r.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(r);
                        Dimension desktopSize = getDesktopPane().getSize();
                        r.setSize(desktopSize);
                        r.setPreferredSize(desktopSize);
                        r.setVisible(!r.isVisible());
                        try {
                            r.setSelected(true);
                        } catch (java.beans.PropertyVetoException e) {
                            e.printStackTrace();
                        }
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) r
                                .getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions = (MouseMotionListener[]) north
                                .getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                }
            }
        });
        tableCredit.setModel(new DefaultTableModel(new Object[][]{{null,
                        null, null, null},}, new String[]{"Sr.No.",
                    "Supplier's/Buyer's Name", "VCH Number", "Amount"}));
        scrollPane.setViewportView(tableCredit);

        panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelDebit.add(panel_3, "cell 1 1,grow");
        panel_3.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane_1 = new JScrollPane();
        panel_3.add(scrollPane_1, "cell 0 0,grow");

        tableDebit = new JTable();
        tableDebit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    i = tableDebit.getSelectedRow();
                    if (tableDebit.getRowCount() > 0) {
                        if (tableDebit.getValueAt(i, 0) == null) {
                            dat.requestFocus();
                        } else {
                            //tableCreditMouseClicked(null);
                        }
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tableCredit.requestFocus();
                    tableCredit.changeSelection(0, 0, false, false);
                }
            }
        });
        tableDebit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDebit.getSelectedRow();
                if (tableDebit.getValueAt(row, 1) != null
                        && tableDebit.getValueAt(row, 3) != null) {
                    try {
                        String id = tableDebit.getValueAt(
                                tableDebit.getSelectedRow(), 2).toString();
                        PaymentDTO paymentDTO = new PaymentDTO();
                        PaymentForm p = new PaymentForm(paymentDTO, true);
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
                        Logger.getLogger(DaybookReceiptPayment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        tableDebit.setModel(new DefaultTableModel(new Object[][]{{null,
                        null, null, null},}, new String[]{"Sr.No.",
                    "Supplier's/Buyer's Name", "VCH Number", "Amount"}));
        scrollPane_1.setViewportView(tableDebit);

        lblNewLabel = new JLabel("Total Amount");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelDebit.add(lblNewLabel, "flowx,cell 0 2");

        lblTotalAmount = new JLabel("Total Amount");
        lblTotalAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDebit.add(lblTotalAmount, "flowx,cell 1 2");

        labelTotalDebit = new JLabel("0");
        labelTotalDebit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelDebit.add(labelTotalDebit, "cell 1 2");

        labelTotalCredit = new JLabel("0");
        labelTotalCredit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panelDebit.add(labelTotalCredit, "cell 0 2");

        panel_1 = new JPanel();
        panel.add(panel_1, "cell 2 20,grow");
        panel_1.setLayout(new MigLayout("", "[grow]", "[grow][]"));

        scrollpaneMain = new JScrollPane();
        panel_1.add(scrollpaneMain, "cell 0 0,grow");

        combine = new JTable();
        combine.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null},},
                new String[]{
                    "Sr.No", "Particulars", "VCH No", "Amount", "`Sr.No", "`Particulars", "`VCH No", "`Amount"
                }));
        scrollpaneMain.setViewportView(combine);
        panel_1.setVisible(false);

        setClosable(true);
        dat.setDateFormatString("dd-MM-yyyy");
        tfDatePicker = (JTextField) dat.getComponent(1);
        System.out.println(tfDatePicker.toString());

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("code:" + e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        btnShow.requestFocus();
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        btnBackActionPerformed(null);
                    } catch (PropertyVetoException ex) {
                        JOptionPane.showMessageDialog(DaybookReceiptPayment.this, ex.getMessage());
                    }
                }
            }
        });

        tfDatePicker.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new
                tfDatePicker.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new
            }
        });

    }

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (dat.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat.requestFocus();

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);

            DefaultTableModel tableModel1 = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel1.setRowCount(0);

            labelTotalCredit.setText("0");
            labelTotalDebit.setText("0");

        } else {
            loadReceiptEntry();
            labelTotalCredit.setText(""
                    + Constants.DECIMAL_FORMAT.format(total_receipt));
            System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
                    + total_receipt);

            loadPaymentEntry();
            labelTotalDebit.setText(""
                    + Constants.DECIMAL_FORMAT.format(total_payment));
            System.out.println("WWWWWWWQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ"
                    + total_payment);
            Connection conn = null;
            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = f.format(dat.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();

                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow + "' and trans_typeIndex=6)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();

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

                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow + "' and trans_typeIndex=3)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }

                System.out.println("DDDDDDDDDRRRRRRRRRRRRRRR" + d);
                System.out.println("CCCCCCCCCCCCCCRRRRRRRRRRRRRRR" + cr);
                // --------------new query-------------------
                // q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=1)";
                // rs1 = st.executeQuery(q);
                // while(rs1.next()) {
                // d=d+rs1.getDouble("totalDeb");
                // }
                // ------------------------------------------

                // System.out.println(d);

                /*   Commented by sudeep on 16-01-2014             if (cr >= d) {
                 o = cr - d;
                 labelOpeningBalance.setText("" + df.format(Math.abs(o)));
                 labelOType.setText("Credit");
                 } else {
                 o = d - cr;
                 labelOpeningBalance.setText("" + df.format(Math.abs(o)));
                 labelOType.setText("Debit");
                 }*/

                // ----------------------calculate closing
                // balance-----------------------------
          /*   Commented by sudeep on 16-01-2014      o = Double.parseDouble(labelOpeningBalance.getText());
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
                 }*/
                // ---------------------------------------------------------------------------
                conn.commit();
                conn.close();
            } catch (Exception ex) {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                throw ex;
            }
        }
        setColumnWidth(tableDebit);
        setColumnWidth(tableCredit);
    }

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        btnShowActionPerformed(evt);
        createTable();

        if (combine.getRowCount() != 0 && dat.getDate() != null) {
            Map parameter = new HashMap();

            String TotalCreditAmount = labelTotalCredit.getText();
            String TotalDebiAmount = labelTotalDebit.getText();
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                    + TotalDebiAmount);

            /*   Commented by sudeep on 16-01-2014String OpeningBalance = labelOpeningBalance.getText();
             String ClosingBalance = labelClosingBalance.getText();*/

            // String OType = labelOType.getText();
            // String CType = labelCType.getText();
            //
            parameter.put("TotalAmountdebit", TotalDebiAmount);
            parameter.put("TotalAmountCredit", TotalCreditAmount);
            //
            // parameter.put("OpeningBalanceValue", OpeningBalance);
            // parameter.put("ClosingBalanceValue", ClosingBalance);
            // parameter.put("OpeningDebit", OType);
            // parameter.put("OpeningCredit", CType);

            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("Date", saDa.format(dat.getDate()).toString());

            PrintAllReport.printReceiptPAyTableDaybookDemo(parameter,
                    new JRTableModelDataSource(combine.getModel()));
        } else {
            JOptionPane.showMessageDialog(this, "No data to Print");
        }

    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) throws PropertyVetoException {
        this.setClosed(true);
    }

    public void loadReceiptEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(4);

            System.out.println("DATE >>>>>> >>>>>>>>>>>>>>>>>" + dateNow);
            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type = 2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=6)";
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1" + q);
            rs2 = st1.executeQuery(q);

            while (rs2.next()) {
                System.out.println("rs2 >>>>>> >>>>>>>>>>>>>>>>>" + q);
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    System.out.println("rs3 >>>>>> >>>>>>>>>>>>>>>>>" + q);
                    int row = tableCredit.getRowCount();
                    // tableModel = (DefaultTableModel) tableDebit.getModel();
                    tableModel.setRowCount(row + 1);

                    // Sr No
                    tableCredit.setValueAt(row + 1, row, 0);

                    // Get Ledger name
                    tableCredit
                            .setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
                            .getDouble("trans_amt")), row, 3);

                }
                // System.out.println("Middle Purchase index: "+creditPurchaseIndex);
            }
            // --------------------------------------------------------------

            // -------------------Count Total Creditted
            // Amount--------------------
            double qty = 0;
            if (tableCredit.getRowCount() > 0) {
                i = 0;
                while (i < tableCredit.getRowCount()) {
                    if (tableCredit.getValueAt(i, 3) != null) {
                        qty = qty
                                + Double.parseDouble(tableCredit.getValueAt(i,
                                3).toString());
                    }
                    i++;
                }
                total_receipt = qty;
            } else {
                total_receipt = qty;
            }
            // --------------------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public void loadPaymentEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            // -----------------------Table:
            // Debit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(4);

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=3)";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Sr No
                    tableDebit.setValueAt(row + 1, row, 0);

                    // Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
                            .getDouble("trans_amt")), row, 3);
                }
            }
            // --------------------------------------------------------------

            // -------------------Count Total Creditted
            // Amount--------------------
            double qty = 0;
            if (tableDebit.getRowCount() > 0) {
                i = 0;
                while (i < tableDebit.getRowCount()) {
                    if (tableDebit.getValueAt(i, 3) != null) {
                        qty = qty
                                + Double.parseDouble(tableDebit
                                .getValueAt(i, 3).toString());
                    }
                    i++;
                }
                total_payment = qty;
            } else {
                total_payment = qty;
            }
            // --------------------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    private void createTable() throws Exception {
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
                // insert credit side
                k = 0;
                for (int j = 0; j < tableCredit.getColumnCount(); j++) {
                    combine.setValueAt(tableCredit.getValueAt(i, j), i, k);
                    System.out.println(tableCredit.getValueAt(i, j));
                    k++;
                }

            }

            if (i < tableDebit.getRowCount()) {
                // insert debit side
                k = 4;
                for (int j = 0; j < tableDebit.getColumnCount(); j++) {
                    combine.setValueAt(tableDebit.getValueAt(i, j), i, k);
                    System.out.println(tableDebit.getValueAt(i, j));
                    k++;
                }
            }
        }

    }

    private void setColumnWidth(JTable passedTable) throws Exception {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

//        passedTable.getColumnModel().getColumn(0)
//                .setCellRenderer(rightRenderer);
        
        passedTable.getColumnModel().getColumn(2)
                .setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(0).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(0).setMaxWidth(50);
//
//        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(1).setMinWidth(236);
//        passedTable.getColumnModel().getColumn(1).setMaxWidth(236);
//
//        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(2).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(2).setMaxWidth(50);
//
//        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(3).setMinWidth(100);
//        passedTable.getColumnModel().getColumn(3).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(3)
                .setCellRenderer(rightRenderer);

    }

    private void setComponentActiveInactive() {

        /*   Commented by sudeep on 16-01-2014labelCType.setVisible(false);
         labelClosingBalance.setVisible(false);
         labelOType.setVisible(false);
         labelOpeningBalance.setVisible(false);
         labelTitleClosingBal.setVisible(false);
         labelTitleOpeningBal.setVisible(false);*/
    }
}
