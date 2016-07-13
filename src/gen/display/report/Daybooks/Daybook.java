package gen.display.report.Daybooks;

import com.toedter.calendar.JDateChooser;
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.contra.ContraDTO;
import gen.accountvoucher.contra.ContraForm;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.payment.PaymentForm;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.receipt.ReceiptForm;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.DayBook;
import gen.display.report.DayBookAdvance1;
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import gen.other.print.PrinterSettings;
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
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
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

@SuppressWarnings("serial")
public class Daybook extends JInternalFrame {

    private JTable tableCredit;
    private JTable tableDebit;
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static int creditSalesIndex = 0, debitPaymentIndex = 0, debitContraIndex = 0;
    static int debitPurchaseIndex = 0, creditReceiptIndex = 0, creditChalanIndex = 0, creditContraIndex = 0;
    static double total_purchase = 0, total_receipt = 0, total_sales = 0,
	    total_payment = 0, total_Contra_Debit = 0, total_Contra_Credit = 0;
    static int debitNonCashSalesIndex = 0, creditNonCashPurchaseIndex = 0;
    static double total_nonCash_sale, total_noncashpurchase;
    static int backAction = 0;
    String date = "";
    Set<String> saleIDSet = new TreeSet<String>();
    Set<String> purchaseIDSet = new TreeSet<String>();
    Set<String> receiptIDSet = new TreeSet<String>();
    Set<String> paymentIDSet = new TreeSet<String>();
    Set<String> chalanIDSet = new TreeSet<String>();
    String path = "";
    // private final JTextField tf;
    // private final JTextField tf1;
    @SuppressWarnings("unused")
    private JTextField tfDatePicker = null;
    @SuppressWarnings("unused")
    private boolean hide_flag = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Daybook frame = new Daybook();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private JLabel labelTotalCredit;
    private JLabel labelTotalDebit;
    private JLabel lblTotalAmount;
    private JLabel lblNewLabel;
    private JLabel openingBL_plus_TotalAmount;
    private JLabel lblDaybook;
    private JDateChooser dat;
    private JButton btnShow;
    private JButton btnPrint;
    private JButton btnBack;
    private JButton btnAdvanceDaybook;
    private JButton btnExport;
    private JLabel lblCredit;
    private JLabel lblDebit;
    private JLabel labelOpeningBalance;
    private JLabel lblNewLabel_5;
    private JLabel labelOType;
    private JLabel labclosingwithoutpending;
    private JLabel lblNewLabel_6;
    private JLabel labelCType;
    private JScrollPane scrollpaneMain;
    private JTable combine;
    private JPanel panelDebit;
    private JPanel panel;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JPanel panel_3;
    private JScrollPane scrollPane_1;
    private JPanel panel_4;
    private JPanel panel_1;
    private JLabel labelPending;
    private JLabel lablePendingBal;
    private JLabel labelClosingBalance;

    /**
     * Create the frame.
     */
    public Daybook() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent arg0) {
                try {
                    labelTotalCredit.setVisible(false);

                    setComponentActiveInactive();
                    backAction = 1;
                    Calendar currentDate = Calendar.getInstance();
                    dat.setDate(currentDate.getTime());

                    DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                            .getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(6);

                    tableModel = (DefaultTableModel) tableDebit.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(6);

                    tableModel = (DefaultTableModel) combine.getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(12);

                    combine.setVisible(false);
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
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                }

            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                MainClass.setstaticvar();
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                try {
                    btnAdvanceDaybook.setEnabled(false);
                    setComponentActiveInactive();
                    if (backAction == 2) {
                        tfDatePicker.setText(date);
                        btnShowActionPerformed(null);
                    } else {
                        Calendar currentDate = Calendar.getInstance();
                        dat.setDate(currentDate.getTime());

                        DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                                .getModel();
                        tableModel.setRowCount(0);
                        tableModel.setColumnCount(6);

                        tableModel = (DefaultTableModel) tableDebit.getModel();
                        tableModel.setRowCount(0);
                        tableModel.setColumnCount(6);

                        tableModel = (DefaultTableModel) combine.getModel();
                        tableModel.setRowCount(0);
                        tableModel.setColumnCount(12);

                        combine.setVisible(false);
                        btnShow.setMnemonic(KeyEvent.VK_S);
                        btnPrint.setMnemonic(KeyEvent.VK_P);
                        btnBack.setMnemonic(KeyEvent.VK_B);
                        btnAdvanceDaybook.setMnemonic(KeyEvent.VK_D);
			btnExport.setMnemonic(KeyEvent.VK_X);

                        btnShowActionPerformed(null);

                        scrollpaneMain.getVerticalScrollBar().setUnitIncrement(20);

                        dat.requestFocus();

                        tableCredit.setShowGrid(true);

                        tableDebit.setShowGrid(true);

                        JTableHeader header1 = tableDebit.getTableHeader();
                        header1.setBackground(Color.yellow);

                        JTableHeader header2 = tableCredit.getTableHeader();
                        header2.setBackground(Color.yellow);

                        if (tableCredit.getRowCount() == 0) {
                            labelTotalCredit.setText("0");
                        }
                        if (tableDebit.getRowCount() == 0) {
                            labelTotalDebit.setText("0");
                        }

                    }
                } catch (Exception ex) {
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                backAction = 2;
                date = tfDatePicker.getText();
            }
        });
        setTitle("Daily Cash Flow");
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
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        btnBackActionPerformed(null);
                    } catch (Exception ex) {
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
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
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
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
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
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
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
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
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
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
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                    }
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnPrint.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (btnAdvanceDaybook.isEnabled()) {
                    btnAdvanceDaybook.requestFocus();
                    } else {
                        btnExport.requestFocus();
                    }
                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnBackActionPerformed(e);
                } catch (Exception ex) {
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                }

            }
        });
        panel.add(btnBack, "cell 1 1");

        btnAdvanceDaybook = new JButton("Advance Daybook");
        btnAdvanceDaybook.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnBack.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
		    btnExport.requestFocus();
                }
            }
        });
        btnAdvanceDaybook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnAdvanceDaybookActionPerformed(e);
                } catch (Exception ex) {
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                }

            }
        });
        panel.add(btnAdvanceDaybook, "cell 1 1");

	btnExport = new JButton("Export");
	btnExport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (btnAdvanceDaybook.isEnabled()) {
                    btnAdvanceDaybook.requestFocus();
                    } else {
                        btnBack.requestFocus();
                    }
                }
            }
        });
	btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    tbnExportActionPerformed(e);
                } catch (Exception ex) {
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                }

            }
        });
	panel.add(btnExport, "cell 1 1");

        panelDebit = new JPanel();
        panelDebit.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panelDebit, "cell 1 2 1 17,grow");
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
                getDesktopPane().setLayout(new CardLayout());
                int row = tableCredit.getSelectedRow();
                if (tableCredit.getValueAt(row, 1) != null
                        && tableCredit.getValueAt(row, 3) != null) {
                    Connection conn;
                    try {
                        conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();
                        ResultSet rs = st
                                .executeQuery("select trans_typeIndex from tbltransactionmain where trans_id="
                                + Long.parseLong(tableCredit
                                .getValueAt(row, 5).toString())
                                + "");
                        if (rs.next()) {
                            if (rs.getInt("trans_typeIndex") == 2) // if
                            // purchase
                            {
                                String id = tableCredit.getValueAt(
                                        tableCredit.getSelectedRow(), 2)
                                        .toString();
                                PurchaseDTO purchaseDTO = new PurchaseDTO();
                                PurchaseForm p = new PurchaseForm(purchaseDTO,
                                        true);
                                p.loadEditForm(id);
                                p.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(p);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
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
                            } else if (rs.getInt("trans_typeIndex") == 1) // if
                            // sales
                            {
                                String id = tableCredit.getValueAt(
                                        tableCredit.getSelectedRow(), 2)
                                        .toString();
                                SaleDTO saleDTO = new SaleDTO();
                                SaleForm s = new SaleForm("Sales",
                                        new Dimension(), saleDTO, true);
                                s.loadEditForm(id);
                                s.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(s);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
                                s.setSize(desktopSize);
                                s.setPreferredSize(desktopSize);
                                s.setVisible(!s.isVisible());
                                s.setSelected(true);
                                BasicInternalFrameUI uiBasicComponent = (BasicInternalFrameUI) s
                                        .getUI();

                                Component north = uiBasicComponent
                                        .getNorthPane();
                                MouseMotionListener[] actions = (MouseMotionListener[]) north
                                        .getListeners(MouseMotionListener.class);

                                for (int i = 0; i < actions.length; i++) {
                                    north.removeMouseMotionListener(actions[i]);
                                }
                            } else if (rs.getInt("trans_typeIndex") == 6) // if
                            // receipt
                            {
                                String id = tableCredit.getValueAt(
                                        tableCredit.getSelectedRow(), 2)
                                        .toString();
                                ReceiptDTO receiptDTO = new ReceiptDTO();
                                ReceiptForm r = new ReceiptForm(receiptDTO,
                                        true);
                                r.loadEditForm(id);
                                r.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(r);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
                                r.setSize(desktopSize);
                                r.setPreferredSize(desktopSize);
                                r.setVisible(!r.isVisible());
                                r.setSelected(true);
                                BasicInternalFrameUI ui = (BasicInternalFrameUI) r
                                        .getUI();

                                Component north = ui.getNorthPane();
                                MouseMotionListener[] actions = (MouseMotionListener[]) north
                                        .getListeners(MouseMotionListener.class);

                                for (int i = 0; i < actions.length; i++) {
                                    north.removeMouseMotionListener(actions[i]);
                                }
                            } else if (rs.getInt("trans_typeIndex") == gen.dto.Constants.CHALAN_TYPE_INDEX) // if    // Chalan
                            {
                                String id = tableCredit.getValueAt(
                                        tableCredit.getSelectedRow(), 2)
                                        .toString();
                                ChalanDTO chalanDTO = new ChalanDTO();
                                ChalanForm1 p = new ChalanForm1(chalanDTO,
                                        true);
                                p.loadEditForm(id);
                                p.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(p);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
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
                            }
                        }
                        conn.commit();
                        conn.close();
                    } catch (Exception ex) {
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                    }
                }
            }
        });
        tableCredit.setModel(new DefaultTableModel(new Object[][]{{null,
                        null, null, null, null},}, new String[]{"", "Particulars",
                    "VCH Number", "Amount", "Quantity"}));
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
                    Connection conn = null;
                    try {
                        conn = DatabaseConnection1.GetConnection();
                        conn.setAutoCommit(false);
                        Statement st = conn.createStatement();
                        ResultSet rs = st
                                .executeQuery("select trans_typeIndex from tbltransactionmain where trans_id="
                                + Long.parseLong(tableDebit.getValueAt(
                                row, 5).toString()) + "");
                        System.out.println("Chalan -------------- " + Long.parseLong(tableDebit.getValueAt(
                                row, 5).toString()) + "");
                        if (rs.next()) {
                            if (rs.getInt("trans_typeIndex") == 1) // if sales
                            {
                                String id = tableDebit.getValueAt(
                                        tableDebit.getSelectedRow(), 2)
                                        .toString();
                                SaleDTO saleDTO = new SaleDTO();
                                SaleForm s = new SaleForm("Sales",
                                        new Dimension(), saleDTO, true);
                                s.loadEditForm(id);
                                s.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(s);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
                                s.setSize(desktopSize);
                                s.setPreferredSize(desktopSize);
                                s.setVisible(!s.isVisible());
                                s.setSelected(true);
                                BasicInternalFrameUI uiBasicComponent = (BasicInternalFrameUI) s
                                        .getUI();

                                Component north = uiBasicComponent
                                        .getNorthPane();
                                MouseMotionListener[] actions = (MouseMotionListener[]) north
                                        .getListeners(MouseMotionListener.class);

                                for (int i = 0; i < actions.length; i++) {
                                    north.removeMouseMotionListener(actions[i]);
                                }
                            } else if (rs.getInt("trans_typeIndex") == 2) // if
                            // purchase
                            {
                                String id = tableDebit.getValueAt(
                                        tableDebit.getSelectedRow(), 2)
                                        .toString();
                                PurchaseDTO purchaseDTO = new PurchaseDTO();
                                PurchaseForm p = new PurchaseForm(purchaseDTO,
                                        true);
                                p.loadEditForm(id);
                                p.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(p);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
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
                            } else if (rs.getInt("trans_typeIndex") == 3) // if
                            // payment
                            {
                                String id = tableDebit.getValueAt(
                                        tableDebit.getSelectedRow(), 2)
                                        .toString();
                                PaymentDTO paymentDTO = new PaymentDTO();
                                PaymentForm p = new PaymentForm(paymentDTO,
                                        true);
                                p.loadEditForm(id);
                                p.pack();
                                JDesktopPane desktopPane = getDesktopPane();
                                desktopPane.add(p);
                                Dimension desktopSize = getDesktopPane()
                                        .getSize();
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
			    } else if (rs.getInt("trans_typeIndex") == gen.dto.Constants.CHALAN_TYPE_INDEX) // if    // Chalan
			    {
				String id = tableCredit.getValueAt(
					tableCredit.getSelectedRow(), 2)
					.toString();
				ChalanDTO chalanDTO = new ChalanDTO();
				ChalanForm1 p = new ChalanForm1(chalanDTO,
					true);
				p.loadEditForm(id);
				p.pack();
				JDesktopPane desktopPane = getDesktopPane();
				desktopPane.add(p);
				Dimension desktopSize = getDesktopPane()
					.getSize();
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
			    } else if (rs.getInt("trans_typeIndex") == gen.dto.Constants.CONTRA_TYPE_INDEX) // if    // Chalan
			    {
				String id = tableDebit.getValueAt(
					tableDebit.getSelectedRow(), 2)
					.toString();
				ContraDTO contraDTO = new ContraDTO();
				ContraForm p = new ContraForm(contraDTO,
					true);
				p.loadEditForm(id);
				p.pack();
				JDesktopPane desktopPane = getDesktopPane();
				desktopPane.add(p);
				Dimension desktopSize = getDesktopPane()
					.getSize();
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
                            }
                        }
                        conn.commit();
                        conn.close();
                    } catch (Exception ex) {
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                    }
                }
            }
        });
        tableDebit.setModel(new DefaultTableModel(new Object[][]{{null,
                        null, null, null, null},}, new String[]{"", "Particulars",
                    "VCH Number", "Amount", "Quantity"}));
        scrollPane_1.setViewportView(tableDebit);

        openingBL_plus_TotalAmount = new JLabel("0");
        openingBL_plus_TotalAmount.setFont(new Font("Times New Roman",
                Font.BOLD, 14));
        openingBL_plus_TotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
        panelDebit.add(openingBL_plus_TotalAmount, "flowx,cell 0 2");

        lblNewLabel = new JLabel("Total Amount");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelDebit.add(lblNewLabel, "cell 0 2");

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

        labelPending = new JLabel("Pending");
        panel.add(labelPending, "cell 0 15");

        lablePendingBal = new JLabel("New label");
        panel.add(lablePendingBal, "cell 0 16");

        labelClosingBalance = new JLabel("New label");
        panel.add(labelClosingBalance, "cell 0 17");

        panel_4 = new JPanel();
        panel.add(panel_4, "cell 1 19 1 2,grow");
        panel_4.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelOpeningBalance = new JLabel("New label");
        labelOpeningBalance.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel_4.add(labelOpeningBalance, "cell 0 0");

        lblNewLabel_5 = new JLabel("Opening Balance");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel_4.add(lblNewLabel_5, "cell 1 0");

        labelOType = new JLabel("New label");
        labelOType.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel_4.add(labelOType, "cell 4 0");

        labclosingwithoutpending = new JLabel("New label");
        labclosingwithoutpending.setFont(new Font("Times New Roman", Font.BOLD,
                14));
        panel_4.add(labclosingwithoutpending, "cell 0 1");

        lblNewLabel_6 = new JLabel("Closing Balance");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel_4.add(lblNewLabel_6, "cell 1 1");

        labelCType = new JLabel("New label");
        labelCType.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel_4.add(labelCType, "cell 4 1");

        setClosable(true);
        dat.setDateFormatString("dd-MM-yyyy");

        panel_1 = new JPanel();
        panel.add(panel_1, "cell 2 20,grow");
        panel_1.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollpaneMain = new JScrollPane();
        panel_1.add(scrollpaneMain, "cell 0 0,grow");

        combine = new JTable();
        combine.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null, null},},
                new String[]{
                    "`", "Particulars", "VCH No", "Amount", "Quantity", "``", "`Particulars", "`VCH No", "`Amount", "`Quantity"
                }));
        scrollpaneMain.setViewportView(combine);
        panel_1.setVisible(false);

        tfDatePicker = (JTextField) dat.getComponent(1);
        System.out.println(tfDatePicker.toString());

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnShow.requestFocus();
                    try {
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    try {
                        btnBackActionPerformed(null);
                    } catch (Exception ex) {
			ex.printStackTrace();
                        JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                    }

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
                // throw new
                tfDatePicker.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new
            }
        });

    }

    public void loadSalesEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());
            DecimalFormat df = new DecimalFormat("#.##");

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            int k = 0; // for putting amount with table header
            creditSalesIndex = 0;

            // --------find if ledger is under Cash In Hand--------
            long g_id = 0;
            q = "select group_id from tblgroup where group_name='Cash In Hand'";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("group_id");
            }

            ArrayList<Long> cash_ids = new ArrayList<Long>();
            int ind = 0;
            cash_ids.add(g_id);
            int flg = 0;
            do {
                q = "select group_id from tblgroup where group_under="
                        + cash_ids.get(ind) + "";
                ResultSet rs = st1.executeQuery(q);
                while (rs.next()) {
                    cash_ids.add(rs.getLong("group_id"));
                }
                if (ind == cash_ids.size() - 1) {
                    flg = 1;
                }
                ind++;
            } while (flg == 0);

            String temp_group_under_id = "";
            i = 0;
            while (i < cash_ids.size()) {
                temp_group_under_id = temp_group_under_id + cash_ids.get(i)
                        + ",";
                i++;
            }

            String group_under_id = temp_group_under_id.substring(0,
                    temp_group_under_id.length() - 1);
            // ----------------------------------------------------

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=1)";
            rs2 = st1.executeQuery(q);
            i = 1;
            int flag_min_one_entry_present = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    tableModel.setRowCount(1);
                    tableModel.setColumnCount(6);
                    creditSalesIndex = 1;
                    tableCredit.setValueAt("Sales", k, 1);
                }
                // for mysql
                // q = "select ledger_name from tblledger where ledger_id=" +
                // rs2.getLong("trans_ledgerid") + " and ledger_under in('" +
                // group_under_id + "')";
                // for HSQLDB
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid")
                        + " and ledger_under in(" + group_under_id + ")";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableCredit
                            .setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        saleIDSet
                                .add(tableCredit.getValueAt(row, 2).toString());
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
                            .getDouble("trans_amt")), row, 3);

                    // Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="
                            + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getDouble("totalQty"), row,
                                4);
                    }
                    rs4.close();

                    flag_min_one_entry_present = 1;
                    creditSalesIndex++;
                }

                // System.out.println("Middle sales index: "+debitSalesIndex);
            }
            // --------------------------------------------------------------
            creditSalesIndex += 2;
            int row = tableCredit.getRowCount();
            tableModel.setRowCount(row + 2);
            System.out.println("creditSalesIndex: " + creditSalesIndex);
            row = tableCredit.getRowCount();
            tableCredit.setValueAt("Total", row - 1, 2);

            // -------------------Count Total Creditted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(0);
                total_sales = 0;
                creditSalesIndex = 0;
            } else {
                if (tableCredit.getRowCount() > 1) {
                    i = 1;
                    double qty = 0;
                    while (i < creditSalesIndex) {
                        if (tableCredit.getValueAt(i, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableCredit
                                    .getValueAt(i, 3).toString());
                        }
                        i++;
                    }
                    total_sales = qty;
                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
                    tableCredit.setValueAt(df.format(qty), k, 0);
                }
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
        // System.out.println("Final index: "+creditPurchaseIndex);
    }

    public void loadNonCashSalesEntry() throws Exception {
        // System.out.println("Entered into non cash sale");

        // -----------------------------------------------------------------------
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            // --------find if ledger is under Cash In Hand--------
            long g_id = 0;
            q = "select group_id from tblgroup where group_name='Cash In Hand'";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("group_id");
            }

            ArrayList<Long> cash_ids = new ArrayList<Long>();
            int ind = 0;
            cash_ids.add(g_id);
            int flg = 0;
            do {
                q = "select group_id from tblgroup where group_under="
                        + cash_ids.get(ind) + "";
                ResultSet rs = st1.executeQuery(q);
                while (rs.next()) {
                    cash_ids.add(rs.getLong("group_id"));
                }
                if (ind == cash_ids.size() - 1) {
                    flg = 1;
                }
                ind++;
            } while (flg == 0);

            String temp_group_under_id = "";
            i = 0;
            while (i < cash_ids.size()) {
                temp_group_under_id = temp_group_under_id + cash_ids.get(i)
                        + ",";
                i++;
            }

            String group_under_id = temp_group_under_id.substring(0,
                    temp_group_under_id.length() - 1);
            // ----------------------------------------------------

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setColumnCount(6);
            int k = 0;
            // q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+
            // dateNow +"' and trans_typeIndex=3)";
            // q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
            // q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+
            // dateNow +"' and a.trans_typeIndex=3)";
            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=1)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;
            while (rs2.next()) {
                System.out
                        .println("rs2.getLong(\"trans_ledgerid\")---------------------->> "
                        + rs2.getLong("trans_ledgerid"));
                System.out
                        .println("flag_min_one_entry_present---------------------->> "
                        + flag_min_one_entry_present);
                if (flag_min_one_entry_present == 0) {
                    debitNonCashSalesIndex = tableDebit.getRowCount();
                    if (debitNonCashSalesIndex == 0) {
                        tableModel.setRowCount(1);
                        tableDebit.setValueAt("Non-Cash Sales",
                                debitNonCashSalesIndex, 1);
                        debitNonCashSalesIndex++;
                    } else {
                        debitNonCashSalesIndex += 2;
                        tableModel.setRowCount(debitNonCashSalesIndex);
                        tableDebit.setValueAt("Non-Cash Sales",
                                debitNonCashSalesIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid")
                        + " and ledger_under not in (" + group_under_id + ")";
                rs3 = st3.executeQuery(q);
                System.out
                        .println("flag_min_one_entry_present---------------------->> "
                        + flag_min_one_entry_present);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        saleIDSet.add(tableDebit.getValueAt(row, 2).toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    // Debitted Amount
                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    // qty will be null in receipt and payment
                    tableDebit.setValueAt(" ", row, 4);
                    flag_min_one_entry_present = 1;

                    i++;
                    debitNonCashSalesIndex++;
                }
            }
            // --------------------------------------------------------------
            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitNonCashSalesIndex += 1;
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            // -------------------Count Total Debitted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(debitPurchaseIndex);
                total_nonCash_sale = 0;
                debitNonCashSalesIndex = debitPurchaseIndex;
            } else {
                if (tableDebit.getRowCount() > 0) {
                    int j = 0;
                    if (debitPurchaseIndex == 0) {
                        j = 0;
                    } else {
                        j = debitPurchaseIndex + 2;
                    }

                    double qty = 0;
                    while (j < debitNonCashSalesIndex - 1) {
                        if (tableDebit.getValueAt(j, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableDebit.getValueAt(
                                    j, 3).toString());
                        }
                        j++;
                    }
                    total_nonCash_sale = qty;
                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
                    if (debitPurchaseIndex == 0) {
                        tableDebit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableDebit.setValueAt(df.format(qty),
                                debitPurchaseIndex + 1, 0);
                    }
                }
            }

            // ------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }

    }

    // ---------------------------------------------------------------
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

            DecimalFormat df = new DecimalFormat("#.##");

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setColumnCount(6);
            int k = 0;

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=6)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;

            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) // set sub header
                {
                    creditReceiptIndex = tableCredit.getRowCount();
                    if (creditReceiptIndex == 0) {
                        tableModel.setRowCount(1);
                        tableCredit
                                .setValueAt("Receipt", creditReceiptIndex, 1);
                        creditReceiptIndex++;
                    } else {
                        creditReceiptIndex += 2;
                        tableModel.setRowCount(creditReceiptIndex);
                        tableCredit.setValueAt("Receipt",
                                creditReceiptIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableCredit
                            .setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        receiptIDSet.add(tableCredit.getValueAt(row, 2)
                                .toString());
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
                            .getDouble("trans_amt")), row, 3);

                    // qty will be null in receipt and payment
                    tableCredit.setValueAt("-", row, 4);

                    flag_min_one_entry_present = 1;
                }
                i++;
                creditReceiptIndex++;
            }
            // --------------------------------------------------------------
            creditReceiptIndex += 2;
            int row = tableCredit.getRowCount();
            tableModel.setRowCount(row + 2);
            row = tableCredit.getRowCount();
            // System.out.println("Row-1:: "+(row-1));
            tableCredit.setValueAt("Total", row - 1, 2);
            // System.out.println("Final Rec index: "+creditReceiptIndex);

            // -------------------Count Total Creditted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(creditSalesIndex);
                total_receipt = 0;
                creditReceiptIndex = 0;
            } else {
                if (tableCredit.getRowCount() > 0) {
                    int j = 0;
                    if (creditSalesIndex == 0) {
                        j = 0;
                    } else {
                        j = creditSalesIndex + 2;
                    }

                    double qty = 0;
                    while (j < creditReceiptIndex - 1) {
                        if (tableCredit.getValueAt(j, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableCredit
                                    .getValueAt(j, 3).toString());
                        }
                        System.out.println("Receipt Credit >>>>>>>>>>>>>>>>>>"
                                + df.format(qty));
                        j++;
                    }
                    total_receipt = qty;
                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
                    if (creditSalesIndex == 0) {
                        tableCredit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableCredit.setValueAt(df.format(qty),
                                creditSalesIndex + 1, 0);
                    }
                }
            }
            // ------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public void loadPurchaseEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            // -----------------------Table:
            // Debit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setColumnCount(6);
            debitPurchaseIndex = 0;

            // --------find if ledger is under Cash In Hand--------
            long g_id = 0;
            q = "select group_id from tblgroup where group_name='Cash In Hand'";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("group_id");
            }

            ArrayList<Long> cash_ids = new ArrayList<Long>();
            int ind = 0;
            cash_ids.add(g_id);
            int flg = 0;
            do {
                q = "select group_id from tblgroup where group_under="
                        + cash_ids.get(ind) + "";
                ResultSet rs = st1.executeQuery(q);
                while (rs.next()) {
                    cash_ids.add(rs.getLong("group_id"));
                }
                if (ind == cash_ids.size() - 1) {
                    flg = 1;
                }
                ind++;
            } while (flg == 0);

            String temp_group_under_id = "";
            i = 0;
            while (i < cash_ids.size()) {
                temp_group_under_id = temp_group_under_id + cash_ids.get(i)
                        + ",";
                i++;
            }

            String group_under_id = temp_group_under_id.substring(0,
                    temp_group_under_id.length() - 1);
            // ----------------------------------------------------

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=2)";
            rs2 = st1.executeQuery(q);
            i = 1;
            int flag_min_one_entry_present = 0;
            int k = 0; // for putting amount with table header
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    tableModel.setRowCount(1);
                    tableModel.setColumnCount(6);
                    debitPurchaseIndex = 1;
                    tableDebit.setValueAt("Purchase", k, 1);
                }
                // for mysql
                // q = "select ledger_name from tblledger where ledger_id=" +
                // rs2.getLong("trans_ledgerid") + " and ledger_under in('" +
                // group_under_id + "')";
                // for HQSLDB
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid")
                        + " and ledger_under in(" + group_under_id + ")";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    // tableModel = (DefaultTableModel) tableDebit.getModel();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        purchaseIDSet.add(tableDebit.getValueAt(row, 2)
                                .toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
                            .getDouble("trans_amt")), row, 3);

                    // Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="
                            + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit
                                .setValueAt(rs4.getDouble("totalQty"), row, 4);
                    }
                    rs4.close();
                    flag_min_one_entry_present = 1;
                    debitPurchaseIndex++;
                }
            }
            // --------------------------------------------------------------

            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitPurchaseIndex += 2;
            // System.out.println("Final Purchase index: "+creditPurchaseIndex);
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            // -------------------Count Total Debitted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(0);
                total_purchase = 0;
                debitPurchaseIndex = 0;
            } else {
                if (tableDebit.getRowCount() > 1) {
                    i = 1;
                    double qty = 0;
                    while (i < debitPurchaseIndex - 1) {
                        if (tableDebit.getValueAt(i, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableDebit.getValueAt(
                                    i, 3).toString());
                        }
                        i++;
                    }
                    total_purchase = qty;
                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
                    tableDebit.setValueAt(df.format(qty), k, 0);
                }
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
        System.out.println("Ful n Final Purchase index: " + debitPurchaseIndex);
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

            DecimalFormat df = new DecimalFormat("#.##");

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setColumnCount(6);
            int k = 0;

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=3)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    debitPaymentIndex = tableDebit.getRowCount();
                    if (debitPaymentIndex == 0) {
                        tableModel.setRowCount(1);
                        tableDebit.setValueAt("Payment", debitPaymentIndex, 1);
                        debitPaymentIndex++;
                    } else {
                        debitPaymentIndex += 2;
                        tableModel.setRowCount(debitPaymentIndex);
                        tableDebit.setValueAt("Payment", debitPaymentIndex - 1,
                                1);
                    }
                }
                System.out.println("Ledger id ----------- " + rs2.getLong("trans_ledgerid"));
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        paymentIDSet.add(tableDebit.getValueAt(row, 2)
                                .toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    // Debitted Amount
                    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
                            .getDouble("trans_amt")), row, 3);

                    // qty will be null in receipt and payment
                    tableDebit.setValueAt("-", row, 4);
                }
                flag_min_one_entry_present = 1;
                debitPaymentIndex++;
            }
            // --------------------------------------------------------------
            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitPaymentIndex += 2;
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            // -------------------Count Total Debitted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(debitPurchaseIndex);
                total_payment = 0;
                debitPaymentIndex = 0;
            } else {
                if (tableDebit.getRowCount() > 1) {
                    int j = 0;
                    if (debitPurchaseIndex == 0) {
                        j = 0;
                    } else {
                        j = debitPurchaseIndex + 2;
                    }

                    double qty = 0;
                    while (j < debitPaymentIndex - 1) {
                        if (tableDebit.getValueAt(j, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableDebit.getValueAt(
                                    j, 3).toString());
                        }
                        j++;
                    }
                    total_payment = qty;
                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
                    if (debitPurchaseIndex == 0) {
                        tableDebit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableDebit.setValueAt(df.format(qty),
                                debitPurchaseIndex + 1, 0);
                    }
                }
            }
	   
            // ------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public void loadPendingPaymentEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableDebit
                    .getModel();
            tableModel.setColumnCount(6);
            int k = 0;
            // q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+
            // dateNow +"' and trans_typeIndex=3)";
            // q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
            // q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+
            // dateNow +"' and a.trans_typeIndex=3)";
            q = "select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select distinct(a.trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and trans_date='"
                    + dateNow
                    + "' and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(select group_id from tblgroup where group_name in('Suspense Account'))) and b.trans_type=1)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    debitPaymentIndex = tableDebit.getRowCount();
                    if (debitPaymentIndex == 0) {
                        tableModel.setRowCount(1);
                        tableDebit.setValueAt("Pending", debitPaymentIndex, 1);
                        debitPaymentIndex++;
                    } else {
                        debitPaymentIndex += 2;
                        tableModel.setRowCount(debitPaymentIndex);
                        tableDebit.setValueAt("Pending", debitPaymentIndex - 1,
                                1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableDebit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableDebit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        paymentIDSet.add(tableDebit.getValueAt(row, 2)
                                .toString());
                        System.out.println("Added payment : "
                                + tableDebit.getValueAt(row, 2).toString());
                        tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    // Debitted Amount
                    tableDebit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    // qty will be null in receipt and payment
                    tableDebit.setValueAt("-", row, 4);
                    flag_min_one_entry_present = 1;
                }

                i++;
                debitPaymentIndex++;
            }
            // --------------------------------------------------------------
            int row = tableDebit.getRowCount();
            tableModel.setRowCount(row + 2);
            debitPaymentIndex += 2;
            row = tableDebit.getRowCount();
            tableDebit.setValueAt("Total", row - 1, 2);

            // -------------------Count Total Debitted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(debitNonCashSalesIndex);
                total_payment = 0;
                debitPaymentIndex = 0;
            } else {
                if (tableDebit.getRowCount() > 0) {
                    int j = 0;
                    if (debitNonCashSalesIndex == 0) {
                        j = 0;
                    } else {
                        j = debitNonCashSalesIndex + 2;
                    }

                    double qty = 0;
                    while (j < debitPaymentIndex - 1) {
                        if (tableDebit.getValueAt(j, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableDebit.getValueAt(
                                    j, 3).toString());
                        }
                        j++;
                    }
                    total_payment = qty;
                    tableDebit.setValueAt(df.format(qty), row - 1, 3);
                    if (debitNonCashSalesIndex == 0) {
                        tableDebit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableDebit.setValueAt(df.format(qty),
                                debitNonCashSalesIndex + 1, 0);
                    }
                }
            }
            // ------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public void loadNonCashPurchaseEntry() throws Exception {
        Connection conn = null;
        try {
            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            // --------find if ledger is under Cash In Hand--------
            long g_id = 0;
            q = "select group_id from tblgroup where group_name='Cash In Hand'";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                g_id = rs1.getLong("group_id");
            }

            ArrayList<Long> cash_ids = new ArrayList<Long>();
            int ind = 0;
            cash_ids.add(g_id);
            int flg = 0;
            do {
                q = "select group_id from tblgroup where group_under="
                        + cash_ids.get(ind) + "";
                ResultSet rs = st1.executeQuery(q);
                while (rs.next()) {
                    cash_ids.add(rs.getLong("group_id"));
                }
                if (ind == cash_ids.size() - 1) {
                    flg = 1;
                }
                ind++;
            } while (flg == 0);

            String temp_group_under_id = "";
            i = 0;
            while (i < cash_ids.size()) {
                temp_group_under_id = temp_group_under_id + cash_ids.get(i)
                        + ",";
                i++;
            }

            String group_under_id = temp_group_under_id.substring(0,
                    temp_group_under_id.length() - 1);
            // ----------------------------------------------------

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat.getDate());

            DecimalFormat df = new DecimalFormat("#.##");

            // -----------------------Table:
            // Credit--------------------------------------------
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setColumnCount(6);
            int k = 0;
            // q="select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"+
            // dateNow +"' and trans_typeIndex=3)";
            // q="select a.trans_ledgerId,a.trans_amt,a.trans_id from tbltransactionledger a left join tbltransactionledger b on(a.trans_id=b.trans_id) where a.trans_type=2 and a.trans_id in(select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date>'04-04-2012') and b.trans_ledgerId in(select ledger_id from tblledger where ledger_under in(56))";
            // q="select trans_ledgerId ,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select a.trans_id from tbltransactionmain a left join tbltransactionledger b using(trans_id) where a.atrans_date='"+
            // dateNow +"' and a.trans_typeIndex=3)";
            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
                    + dateNow + "' and trans_typeIndex=2)";
            rs2 = st1.executeQuery(q);
            int flag_min_one_entry_present = 0;
            i = 0;
            while (rs2.next()) {
                if (flag_min_one_entry_present == 0) {
                    creditNonCashPurchaseIndex = tableCredit.getRowCount();
                    if (creditNonCashPurchaseIndex == 0) {
                        tableModel.setRowCount(1);
                        tableCredit.setValueAt("Non-Cash Purchase",
                                creditNonCashPurchaseIndex, 1);
                        creditNonCashPurchaseIndex++;
                    } else {
                        creditNonCashPurchaseIndex += 2;
                        tableModel.setRowCount(creditNonCashPurchaseIndex);
                        tableCredit.setValueAt("Non-Cash Purchase",
                                creditNonCashPurchaseIndex - 1, 1);
                    }
                }
                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid")
                        + " and ledger_under not in ('" + group_under_id + "')";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Get Ledger name
                    tableCredit
                            .setValueAt(rs3.getString("ledger_name"), row, 1);

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"),
                                row, 2);
                        purchaseIDSet.add(tableCredit.getValueAt(row, 2)
                                .toString());
                        tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
                    }

                    // Debitted Amount
                    tableCredit.setValueAt(rs2.getDouble("trans_amt"), row, 3);

                    // qty will be null in receipt and payment
                    tableCredit.setValueAt("-", row, 4);
                    flag_min_one_entry_present = 1;
                }

                i++;
                creditNonCashPurchaseIndex++;
            }
            // --------------------------------------------------------------
            int row = tableCredit.getRowCount();
            tableModel.setRowCount(row + 2);
            // creditNonCashPurchaseIndex += 2;
            creditNonCashPurchaseIndex += 1;
            row = tableCredit.getRowCount();
            tableCredit.setValueAt("Total", row - 1, 2);

            // -------------------Count Total Debitted
            // Amount--------------------
            if (flag_min_one_entry_present == 0) {
                tableModel.setRowCount(creditSalesIndex);
                total_noncashpurchase = 0;
                creditNonCashPurchaseIndex = creditSalesIndex;
            } else {
                if (tableCredit.getRowCount() > 0) {
                    int j = 0;
                    if (creditSalesIndex == 0) {
                        j = 0;
                    } else {
                        j = creditSalesIndex + 2;
                    }

                    double qty = 0;
                    while (j < creditNonCashPurchaseIndex - 1) {
                        if (tableCredit.getValueAt(j, 3) != null) {
                            qty = qty
                                    + Double.parseDouble(tableCredit
                                    .getValueAt(j, 3).toString());
                        }
                        j++;
                    }
                    total_noncashpurchase = qty;
                    tableCredit.setValueAt(df.format(qty), row - 1, 3);
                    if (creditSalesIndex == 0) {
                        tableCredit.setValueAt(df.format(qty), 0, 0);
                    } else {
                        tableCredit.setValueAt(df.format(qty),
                                creditSalesIndex + 1, 0);
                    }
                }
            }

            System.out.println("Non cash purchase index final: "
                    + creditNonCashPurchaseIndex);
            // ------------------------------------------------------------------
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    public void loadChalanEntry() throws Exception {
	Connection conn = null;
	try {
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    Statement st1 = conn.createStatement();
	    Statement st3 = conn.createStatement();
	    Statement st4 = conn.createStatement();

	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    String dateNow = f.format(dat.getDate());
	    DecimalFormat df = new DecimalFormat("#.##");

	    DefaultTableModel tableModel = (DefaultTableModel) tableCredit
		    .getModel();
	    int k = 0; // for putting amount with table header
	    creditChalanIndex = 0;

	    // --------find if ledger is under Cash In Hand--------
	    long g_id = 0;
	    q = "select group_id from tblgroup where group_name='Cash In Hand'";
	    rs1 = st1.executeQuery(q);
	    if (rs1.next()) {
		g_id = rs1.getLong("group_id");
	    }

	    ArrayList<Long> cash_ids = new ArrayList<Long>();
	    int ind = 0;
	    cash_ids.add(g_id);
	    int flg = 0;
	    do {
		q = "select group_id from tblgroup where group_under="
			+ cash_ids.get(ind) + "";
		ResultSet rs = st1.executeQuery(q);
		while (rs.next()) {
		    cash_ids.add(rs.getLong("group_id"));
		}
		if (ind == cash_ids.size() - 1) {
		    flg = 1;
		}
		ind++;
	    } while (flg == 0);

	    String temp_group_under_id = "";
	    i = 0;
	    while (i < cash_ids.size()) {
		temp_group_under_id = temp_group_under_id + cash_ids.get(i)
			+ ",";
		i++;
	    }

	    String group_under_id = temp_group_under_id.substring(0,
		    temp_group_under_id.length() - 1);
	    // ----------------------------------------------------

	    q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
		    + dateNow + "' and trans_typeIndex = " + gen.dto.Constants.CHALAN_TYPE_INDEX + ")";
	    rs2 = st1.executeQuery(q);
	    i = 1;
	    int flag_min_one_entry_present = 0;
	    while (rs2.next()) {
		if (flag_min_one_entry_present == 0) // set sub header
		{
		    creditChalanIndex = tableCredit.getRowCount();
		    if (creditChalanIndex == 0) {
			tableModel.setRowCount(1);
			tableCredit
				.setValueAt("Chalan", creditChalanIndex, 1);
			creditChalanIndex++;
		    } else {
			creditChalanIndex += 2;
			tableModel.setRowCount(creditChalanIndex);
			tableCredit.setValueAt("Chalan",
				creditChalanIndex - 1, 1);
		    }
		}
//                if (flag_min_one_entry_present == 0) {
//                    tableModel.setRowCount(tableDebit.getRowCount() + 1);
//                    tableModel.setColumnCount(6);
//                    //creditSalesIndex = 1;
//                    tableCredit.setValueAt("Chalan", k, 1);
//                }
		// for mysql
		// q = "select ledger_name from tblledger where ledger_id=" +
		// rs2.getLong("trans_ledgerid") + " and ledger_under in('" +
		// group_under_id + "')";
		// for HSQLDB
		q = "select ledger_name from tblledger where ledger_id="
			+ rs2.getLong("trans_ledgerid")
			+ " and ledger_under in(" + group_under_id + ")";
		rs3 = st3.executeQuery(q);
		if (rs3.next()) {
		    int row = tableCredit.getRowCount();
		    tableModel.setRowCount(row + 1);

		    // Get Ledger name
		    tableCredit
			    .setValueAt(rs3.getString("ledger_name"), row, 1);

		    // VCH No(Receipt No)
		    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
			    + rs2.getLong("trans_id") + "";
		    rs4 = st4.executeQuery(q);
		    if (rs4.next()) {
			tableCredit.setValueAt(rs4.getLong("trans_receiptNo"),
				row, 2);
			chalanIDSet
				.add(tableCredit.getValueAt(row, 2).toString());
			tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
		    }
		    rs4.close();

		    // Debitted Amount
		    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
			    .getDouble("trans_amt")), row, 3);

		    // Qty
		    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="
			    + rs2.getLong("trans_id") + ")";
		    rs4 = st4.executeQuery(q);
		    if (rs4.next()) {
			tableCredit.setValueAt(rs4.getDouble("totalQty"), row,
				4);
		    }
		    rs4.close();

		    flag_min_one_entry_present = 1;
		    creditChalanIndex++;
		}

		// System.out.println("Middle sales index: "+debitSalesIndex);
	    }
	    creditChalanIndex = 0;
	    conn.commit();
	    conn.close();
	} catch (Exception ex) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    throw ex;
	}
    }

    public void loadContraDebitEntry() throws Exception {
	Connection conn = null;
	try {
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    Statement st1 = conn.createStatement();
	    Statement st3 = conn.createStatement();
	    Statement st4 = conn.createStatement();

	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    String dateNow = f.format(dat.getDate());

	    DecimalFormat df = new DecimalFormat("#.##");

	    // -----------------------Table:
	    // Credit--------------------------------------------
	    DefaultTableModel tableModel = (DefaultTableModel) tableDebit
		    .getModel();
	    tableModel.setColumnCount(6);
	    int k = 0;

	    q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=1 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
		    + dateNow + "' and trans_typeIndex= " + gen.dto.Constants.CONTRA_TYPE_INDEX + ")";
	    rs2 = st1.executeQuery(q);
	    int flag_min_one_entry_present = 0;
	    while (rs2.next()) {
		if (flag_min_one_entry_present == 0) {
		    debitContraIndex = tableDebit.getRowCount();
		    if (debitContraIndex == 0) {
			tableModel.setRowCount(debitContraIndex + 1);
			tableDebit.setValueAt("Contra", debitContraIndex, 1);
			debitContraIndex++;
		    } else {
			debitContraIndex += 2;
			tableModel.setRowCount(debitContraIndex);
			tableDebit.setValueAt("Contra", debitContraIndex - 1,
				1);
		    }
		}
		q = "select ledger_name from tblledger where ledger_id="
			+ rs2.getLong("trans_ledgerid") + "";
		rs3 = st3.executeQuery(q);
		if (rs3.next()) {
		    int row = tableDebit.getRowCount();
		    tableModel.setRowCount(row + 1);

		    // Get Ledger name
		    tableDebit.setValueAt(rs3.getString("ledger_name"), row, 1);

		    // VCH No(Receipt No)
		    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
			    + rs2.getLong("trans_id") + "";
		    rs4 = st4.executeQuery(q);
		    if (rs4.next()) {
			tableDebit.setValueAt(rs4.getLong("trans_receiptNo"),
				row, 2);
			paymentIDSet.add(tableDebit.getValueAt(row, 2)
				.toString());
			tableDebit.setValueAt(rs4.getLong("trans_id"), row, 5);
		    }

		    // Debitted Amount
		    tableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
			    .getDouble("trans_amt")), row, 3);

		    // qty will be null in receipt and payment
		    tableDebit.setValueAt("-", row, 4);
		}
		flag_min_one_entry_present = 1;
		debitContraIndex++;
	    }
	    
	    // --------------------------------------------------------------
	    int row = tableDebit.getRowCount();
	    tableModel.setRowCount(row + 2);
	    debitContraIndex += 2;
	    row = tableDebit.getRowCount();
	    tableDebit.setValueAt("Total", row - 1, 2);

	    // -------------------Count Total Debitted
	    // Amount--------------------
	    if (flag_min_one_entry_present == 0) {
		System.out.println("In if -----------------------------------");
		tableModel.setRowCount(debitContraIndex);
		total_Contra_Debit = 0;
		debitContraIndex = 0;
	    } else {
		System.out.println("In Else -----------------------------------");
		if (tableDebit.getRowCount() > 1) {
		    int j = 0;
		    System.out.println("In Else IF -----------------------------------" + debitContraIndex);
		    if (debitContraIndex == 0) {
			j = 0;
		    } else {
			j = debitContraIndex + 2;
		    }

		    double qty = 0;
		    while (j < debitContraIndex - 1) {
			if (tableDebit.getValueAt(j, 3) != null) {
			    System.out.println("************    ");
			    qty = qty
				    + Double.parseDouble(tableDebit.getValueAt(
				    j, 3).toString());
			}
			j++;
		    }
		    total_Contra_Debit = qty;
		    System.out.println("&&&&&&&       ");
		    tableDebit.setValueAt(df.format(qty), row - 1, 3);
		    if (debitPaymentIndex == 0) {
			tableDebit.setValueAt(df.format(qty), 0, 0);
		    } else {
			tableDebit.setValueAt(df.format(qty),
				debitPaymentIndex + 1, 0);
		    }
		}
	    }
	    // ------------------------------------------------------------------
	    conn.commit();
	    conn.close();
	} catch (SQLException ex) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    throw ex;
	}
    }

    public void loadContraCreditEntry() throws Exception {
	Connection conn = null;
	
	try {
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    Statement st1 = conn.createStatement();
	    Statement st3 = conn.createStatement();
	    Statement st4 = conn.createStatement();

	    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    String dateNow = f.format(dat.getDate());

	    DecimalFormat df = new DecimalFormat("#.##");

	    // -----------------------Table:
	    // Credit--------------------------------------------
	    DefaultTableModel tableModel = (DefaultTableModel) tableCredit
		    .getModel();
	    tableModel.setColumnCount(6);
	    int k = 0;

	    q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger where trans_type=2 and trans_id in(select trans_id from tbltransactionmain where trans_date='"
		    + dateNow + "' and trans_typeIndex= " + gen.dto.Constants.CONTRA_TYPE_INDEX + ")";
	    rs2 = st1.executeQuery(q);
	    System.out.println("Query -------------- " + q);
	    int flag_min_one_entry_present = 0;
	    while (rs2.next()) {
		if (flag_min_one_entry_present == 0) {
		    creditContraIndex = tableCredit.getRowCount();
		    if (creditContraIndex == 0) {
			tableModel.setRowCount(creditContraIndex + 1);
			tableCredit.setValueAt("Contra", creditContraIndex, 1);
			creditContraIndex++;
		    } else {
			creditContraIndex += 2;
			tableModel.setRowCount(creditContraIndex);
			tableCredit.setValueAt("Contra", creditContraIndex - 1,
				1);
		    }
		}
		q = "select ledger_name from tblledger where ledger_id="
			+ rs2.getLong("trans_ledgerid") + "";
		rs3 = st3.executeQuery(q);
		if (rs3.next()) {
		    int row = tableCredit.getRowCount();
		    tableModel.setRowCount(row + 1);

		    // Get Ledger name
		    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);

		    // VCH No(Receipt No)
		    q = "select trans_receiptNo,trans_typeIndex,trans_id from tbltransactionmain where trans_id="
			    + rs2.getLong("trans_id") + "";
		    rs4 = st4.executeQuery(q);
		    if (rs4.next()) {
			tableCredit.setValueAt(rs4.getLong("trans_receiptNo"),
				row, 2);
			paymentIDSet.add(tableCredit.getValueAt(row, 2)
				.toString());
			tableCredit.setValueAt(rs4.getLong("trans_id"), row, 5);
		    }

		    // Debitted Amount
		    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2
			    .getDouble("trans_amt")), row, 3);

		    // qty will be null in receipt and payment
		    tableCredit.setValueAt("-", row, 4);
		}
		flag_min_one_entry_present = 1;
		creditContraIndex++;
	    }
	    // --------------------------------------------------------------
	    int row = tableCredit.getRowCount();
//	    tableModel.setRowCount(row + 2);
	    creditContraIndex += 2;
	    row = tableCredit.getRowCount();
	    tableCredit.setValueAt("Total", row - 1, 2);

	    // -------------------Count Total Debitted
	    // Amount--------------------
	    if (flag_min_one_entry_present == 0) {
		System.out.println("In if -----------------------------------");
		tableModel.setRowCount(creditContraIndex);
		total_Contra_Credit = 0;
		creditContraIndex = 0;
	    } else {
		System.out.println("In Else -----------------------------------");
		if (tableCredit.getRowCount() > 1) {
		    int j = 0;
		    System.out.println("In Else IF -----------------------------------" + creditContraIndex);
		    if (creditContraIndex == 0) {
			j = 0;
		    } else {
			j = creditContraIndex + 2;
		    }

		    double qty = 0;
		    while (j < creditContraIndex - 1) {
			if (tableCredit.getValueAt(j, 3) != null) {
			    System.out.println("************    ");
			    qty = qty
				    + Double.parseDouble(tableCredit.getValueAt(
				    j, 3).toString());
			}
			j++;
		    }
		    total_Contra_Credit = qty;
		    System.out.println("&&&&&&&       " + qty);
		    tableCredit.setValueAt(df.format(qty), row - 1, 3);
		    if (creditContraIndex == 0) {
			tableCredit.setValueAt(df.format(qty), 0, 0);
		    } else {
                        tableCredit.setValueAt(df.format(qty), creditContraIndex + 1, 0);
		    }
		}
	    }
	    // ------------------------------------------------------------------
	    conn.commit();
	    conn.close();
	} catch (SQLException ex) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    throw ex;
	}
    }

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
	labelTotalDebit.setText("0");
	labelTotalCredit.setText("0");
	labelOpeningBalance.setText("0");
	labelClosingBalance.setText("0");
        if (dat.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat.requestFocus();

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);

            labelTotalDebit.setText("0");
            labelTotalCredit.setText("0");
            labelOpeningBalance.setText("0");
            labelClosingBalance.setText("0");

        } else {

            saleIDSet = new TreeSet<String>();
            purchaseIDSet = new TreeSet<String>();
            receiptIDSet = new TreeSet<String>();
            paymentIDSet = new TreeSet<String>();
	    chalanIDSet = new TreeSet<String>();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);

            tableModel = (DefaultTableModel) tableDebit.getModel();
            tableModel.setRowCount(0);

            loadSalesEntry();
            // loadNonCashPurchaseEntry();

            loadReceiptEntry();
	    loadChalanEntry();
//	    loadContraCreditEntry();
            labelTotalCredit.setText(""
                    + Constants.DECIMAL_FORMAT.format(total_sales
                    + total_receipt + total_noncashpurchase));

            loadPurchaseEntry();

            // loadNonCashSalesEntry();
            loadPaymentEntry();
            // loadPendingPaymentEntry();
//	    loadContraDebitEntry();
            labelTotalDebit.setText(""
                    + Constants.DECIMAL_FORMAT.format(total_purchase
                    + total_payment + total_nonCash_sale));

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

                // --------find if ledger is under Cash In Hand--------
                long g_id = 0;
                q = "select group_id from tblgroup where group_name='Cash In Hand'";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    g_id = rs1.getLong("group_id");
                }
                rs1.close();

                ArrayList<Long> cash_ids = new ArrayList<Long>();
                int ind = 0;
                cash_ids.add(g_id);
                int flg = 0;
                do {
                    q = "select group_id from tblgroup where group_under="
                            + cash_ids.get(ind) + "";
                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        cash_ids.add(rs.getLong("group_id"));
                    }
                    rs.close();
                    if (ind == cash_ids.size() - 1) {
                        flg = 1;
                    }
                    ind++;
                } while (flg == 0);

                String temp_group_under_id = "";
                i = 0;
                while (i < cash_ids.size()) {
                    temp_group_under_id = temp_group_under_id + cash_ids.get(i)
                            + ",";
                    i++;
                }

                String group_under_id = temp_group_under_id.substring(0,
                        temp_group_under_id.length() - 1);
                // ----------------------------------------------------

                // --- find if ledger id is under suspese account---

                long g_id1 = 0;
                q = "select group_id from tblgroup where group_name='Suspense Account'";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    g_id1 = rs1.getLong("group_id");
                }
                rs1.close();

                ArrayList<Long> cash_ids1 = new ArrayList<Long>();
                int ind1 = 0;
                cash_ids1.add(g_id1);
                int flg1 = 0;
                do {
                    q = "select group_id from tblgroup where group_under="
                            + cash_ids1.get(ind1) + "";
                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        cash_ids1.add(rs.getLong("group_id"));
                    }
                    rs.close();
                    if (ind1 == cash_ids1.size() - 1) {
                        flg1 = 1;
                    }
                    ind1++;
                } while (flg1 == 0);

                String temp_suspense_group_under_id = "";
                i = 0;
                while (i < cash_ids1.size()) {
                    temp_suspense_group_under_id = temp_suspense_group_under_id
                            + cash_ids1.get(i) + ",";
                    i++;
                }

                String group_suspense_under_id = temp_suspense_group_under_id
                        .substring(0, temp_suspense_group_under_id.length() - 1);
                // System.out.println("Suspanse Accounts id:-"+group_suspense_under_id);
                // ----------------------------------------------------

                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow + "' and trans_typeIndex=6)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();
                // System.out.println("Total credited sale:"+cr);
                // -------------new query----------------
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=1)";

                // for mysql
                // q =
                // "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                // + dateNow +
                // "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"
                // + group_under_id + "'))";
                // for HqSlDB
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow
                        + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in("
                        + group_under_id + "))";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where  trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+
                // group_under_id +"'))";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();

                // for non cash sale
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=1)";
                // for mysql
                // q =
                // "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                // + dateNow +
                // "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under not in('"
                // + group_under_id + "'))";
                // for Hqsldb
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow
                        + "' and trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under not in("
                        + group_under_id + "))";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where  trans_typeIndex=1) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"+
                // group_under_id +"'))";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    // temporary commented
                    // d = d + rs1.getDouble("totalCred");
                }
                rs1.close();
                // --------------------------------------
                // System.out.println("Total credited All:"+cr);

                // System.out.println(cr);

                // for mysql
                // q =
                // "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in(select distinct(a.trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and a.trans_date<'"
                // + dateNow +
                // "' and b.trans_ledgerId not in(497) and b.trans_type=1)";
                // for HQSLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in(select distinct(trans_id) from tbltransactionmain a left join tbltransactionledger b using(trans_id) where  a.trans_typeIndex=3 and a.trans_date<'"
                        + dateNow
                        + "' and b.trans_ledgerId not in(497) and b.trans_type=1)";
                // q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=3)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }
                rs1.close();
                // System.out.println("Total Payment debited:"+d);
                // --------------new query-------------------
                // for mysql
                // q =
                // "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                // + dateNow +
                // "' and trans_typeIndex=2) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"
                // + group_under_id + "'))";
                // for HQSLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow
                        + "' and trans_typeIndex=2) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in("
                        + group_under_id + "))";
                // q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    d = d + rs1.getDouble("totalDeb");
                }
                rs1.close();
                // ---non cash purchases---------------------------------------
                // for mysql
                // q =
                // "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                // + dateNow +
                // "' and trans_typeIndex=2) and trans_ledgerid not in(select ledger_id from tblledger where ledger_under in('"
                // + group_under_id + "'))";
                // for HSQLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                        + dateNow
                        + "' and trans_typeIndex=2) and trans_ledgerid not in(select ledger_id from tblledger where ledger_under in("
                        + group_under_id + "))";
                // q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    // temporarary commented
                    // cr = cr + rs1.getDouble("totalDeb");
                }
                rs1.close();
                // --
                long cash_ledger_id = 0;
                q = "select ledger_id from tblledger where ledger_name='Cash'";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    cash_ledger_id = rs1.getLong("ledger_id");
                }
                rs1.close();

                // -----------=------------------------------------------
                q = "select ledger_openingBalance from tblledger where ledger_id="
                        + cash_ledger_id + "";
                rs1 = st.executeQuery(q);
                if (rs1.next()) {
                    cr = cr + rs1.getDouble("ledger_openingBalance");
                }
                rs1.close();
                // System.out.println("Total credited With opening:"+cr);

                // -------calculate pending balance for opening bal
				/*
                 * q=
                 * "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_typeIndex=3) and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"
                 * + group_suspense_under_id +"'))"; //q=
                 * "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"
                 * + dateNow +"' and trans_typeIndex=2)"; rs1 =
                 * st.executeQuery(q); Double lessPending=0.0d;
                 * while(rs1.next()) { lessPending=rs1.getDouble("totalDeb");
                 * cr=cr-lessPending; } rs1.close();
                 * //System.out.print("lessPending:"+lessPending);
                 */

                // -------calculate pending balance for current date
                // for mysql
                // q =
                // "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date <='"
                // + dateNow +
                // "') and trans_ledgerid in(select ledger_id from tblledger where ledger_under in('"
                // + group_suspense_under_id + "'))";
                // for HQSLDB
                q = "select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_typeIndex=3 and trans_date <='"
                        + dateNow
                        + "') and trans_ledgerid in(select ledger_id from tblledger where ledger_under in("
                        + group_suspense_under_id + "))";
                // q="select sum(trans_amt) as totalDeb from tbltransactionledger where trans_type=1 and trans_id in (select trans_id from tbltransactionmain where trans_date<'"+
                // dateNow +"' and trans_typeIndex=2)";
                rs1 = st.executeQuery(q);
                double dayPending = 0.0d;
                while (rs1.next()) {
                    dayPending = rs1.getDouble("totalDeb");

                }
                rs1.close();
                // System.out.println("dayPending:-"+dayPending);
                lablePendingBal.setText(""
                        + Constants.DECIMAL_FORMAT.format(dayPending));

                if (cr >= d) {
                    o = cr - d;
                    labelOpeningBalance.setText("" + df.format(Math.abs(o)));
                    labelOType.setText("Credit");
                } else {
                    o = d - cr;
                    labelOpeningBalance.setText("" + df.format(Math.abs(o)));
                    labelOType.setText("Debit");
                }

                // ----------------------calculate closing
                // balance-----------------------------
                // o=Double.parseDouble(labelOpeningBalance.getText());
                cr = Double.parseDouble(labelTotalCredit.getText());
                d = Double.parseDouble(labelTotalDebit.getText());
                double cl = 00f;

                if (labelOType.getText().equalsIgnoreCase("Credit")) {
                    cl = o + cr - d;// - dayPending;
                    labelClosingBalance.setText("" + df.format(Math.abs(cl)));
                    labclosingwithoutpending.setText(""
                            + df.format(Math.abs(o + cr - d)));
                    if (cl < 0) {
                        labelCType.setText("Debit");
                    } else {
                        labelCType.setText("Credit");
                    }
                } else {
                    cl = o + d - cr;// + dayPending;
                    labelClosingBalance.setText("" + df.format(Math.abs(cl)));
                    labclosingwithoutpending.setText(""
                            + df.format(Math.abs(o + cr - d)));
                    if (cl > 0) {
                        labelCType.setText("Debit");
                    } else {
                        labelCType.setText("Credit");
                    }
                }
                openingBL_plus_TotalAmount.setText(""
                        + df.format(Math.abs(Double
                        .parseDouble(labelTotalCredit.getText()
                        .toString().trim())
                        + Double.parseDouble(labelOpeningBalance
                        .getText().toString().trim()))));
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

        System.out
                .println("DayBook---------------->>btnPrintActionPerformed------->>printer variables: Direct print:"
                + PrinterSettings.flagDirectPrint
                + " Page Format:"
                + PrinterSettings.flagPrintPageFormat
                + " Page Size:"
                + PrinterSettings.flagPrintPageSize);
        btnShowActionPerformed(evt);
        createTable();
        if (combine.getRowCount() != 0 && dat.getDate() != null) {
            Map parameter = new HashMap();

            String TotalCreditAmount = labelTotalCredit.getText();
            String TotalDebiAmount = labelTotalDebit.getText();
            // System.out.println(""+TotalAmountValue);

            String OpeningBalance = labelOpeningBalance.getText();
            String ClosingBalance = labelClosingBalance.getText();
            String pending = lablePendingBal.getText();

            String OType = labelOType.getText();
            String CType = labelCType.getText();

            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("Date", saDa.format(dat.getDate()).toString());

            if (pending.equalsIgnoreCase("")) {
                pending = " ";
            } else {
                pending = "" + lablePendingBal.getText().trim();
            }

            if (OpeningBalance.equalsIgnoreCase("")) {
                OpeningBalance = " ";
            } else {
                OpeningBalance = "" + labelOpeningBalance.getText().trim();
            }

            String totalCredit_plus_OPBL = openingBL_plus_TotalAmount.getText()
                    .toString().trim();

            parameter.put("TotalAmountdebit", TotalDebiAmount);
            parameter.put("TotalAmountCredit", totalCredit_plus_OPBL);

            parameter.put("OpeningBalanceValue", OpeningBalance);
            //parameter.put("ClosingBalanceValue", ClosingBalance);
            parameter.put("OpeningDebit", OType);
            parameter.put("OpeningCredit", CType);
            //parameter.put("pending", pending);

            PrintAllReport.printTableDaybookDemo(parameter,
                    new JRTableModelDataSource(combine.getModel()));
        } else {
            JOptionPane.showMessageDialog(this, "No data to Print");
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        MainClass mainClass = new MainClass();
        mainClass.menuselection(3);
        this.setClosed(true);
    }

    private void tbnExportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
	path = "";
        imageloading o = new imageloading();

        BufferedWriter out = null;
	if (path != null) {
	    if (!path.isEmpty()) {
        try {
            // TODO add your handling code here:
            Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
            IDMapSet.put("Sale", saleIDSet);
            System.out.println("saleIDSet Size--->>>" + saleIDSet.size());
            IDMapSet.put("Purchase", purchaseIDSet);
            IDMapSet.put("Receipt", receiptIDSet);
            IDMapSet.put("Payment", paymentIDSet);
		    IDMapSet.put("Chalan", chalanIDSet);

            String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
            System.out.println("------------------------->>XMLCODE:" + xmlCode);

            // File file = new File("C:\\DayBook.xml");

            File file = new File(path + ".xml");
            String path = file.getPath();
            out = new BufferedWriter(new FileWriter(file));
            out.write(xmlCode);
            out.close();

            JOptionPane.showMessageDialog(this, "Export Successful");

        } catch (Exception ex) {
	    ex.printStackTrace();
            Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null,
                    ex);
            JOptionPane.showMessageDialog(this, "Export failure");
        }
	    }
	}
    }

    private void btnAdvanceDaybookActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        getDesktopPane().setLayout(new CardLayout());
        DayBookAdvance1 c = new DayBookAdvance1("Advance MainDaybook Report");
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
                k = 5;
                for (int j = 0; j < tableDebit.getColumnCount(); j++) {
                    combine.setValueAt(tableDebit.getValueAt(i, j), i, k);
                    System.out.println(tableDebit.getValueAt(i, j));
                    k++;
                }
            }
        }

    }

    public class imageloading // extends JFrame
    {

        BufferedImage mImage;
        String name, name1;

        public imageloading() {
            // JFrame frm=new JFrame("file loading test");
            String source = filechoose();
	    if (source != null) {
		if (!source.isEmpty()) {
            File inputFile = new File(source);
        }
	    }
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

            int r = chooser.showSaveDialog(gen.display.report.Daybooks.Daybook.this);

            if (r == JFileChooser.APPROVE_OPTION) {
                name1 = chooser.getSelectedFile().getAbsolutePath();
		if (!name1.isEmpty()) {
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
                    PrintStream ps = new PrintStream(file);
                    ps.print(path);
                    ps.close();
                } catch (Exception ex) {
		    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Daybook.this, ex.getMessage());
                }
            }
	    }
            return name1;
        }
    }

    private void setColumnWidth(JTable passedTable) throws Exception {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

//        passedTable.getColumnModel().getColumn(0)
//                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(2)
                .setCellRenderer(rightRenderer);

//        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(1).setMinWidth(130);
//        passedTable.getColumnModel().getColumn(1).setMaxWidth(130);
//        
//        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(2).setMinWidth(50);
//        passedTable.getColumnModel().getColumn(2).setMaxWidth(50);
//        
//        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(3).setMinWidth(90);
//        passedTable.getColumnModel().getColumn(3).setMaxWidth(90);
        passedTable.getColumnModel().getColumn(3)
                .setCellRenderer(rightRenderer);
//        
//        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(4).setMinWidth(60);
//        passedTable.getColumnModel().getColumn(4).setMaxWidth(60);
        passedTable.getColumnModel().getColumn(4)
                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(5).setMinWidth(0);
        passedTable.getColumnModel().getColumn(5).setMaxWidth(0);
        passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
    }

    private void setComponentActiveInactive() throws Exception {
        labelPending.setVisible(false);
        labelClosingBalance.setVisible(false);
        lablePendingBal.setVisible(false);
    }
}
