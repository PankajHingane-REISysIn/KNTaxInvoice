package gen.display.report.SaleReport1;

import com.toedter.calendar.JDateChooser;
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.display.report.PurchaseReport.PurchaseReportWithAllTax;
import gen.display.report.TemporaryClass;
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
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class SaleReport extends JInternalFrame {

    private JTable tableCredit;
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
    private JButton buttonAdvanceSaleReport;
    private JButton buttonExport;
    private JButton buttonChalan;
    private JPanel panelDebit;
    private JLabel lblCredit;
    private JLabel lblTo;
    private JLabel lblDebit;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JPanel panel_3;
    private JScrollPane scrollPane_1;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblTotalAmount;
    private JLabel labelTotalDebit;
    private JLabel labelTotalCredit;
    private JPanel panel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SaleReport frame = new SaleReport();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    ArrayList<TemporaryClass> ledger = new ArrayList<TemporaryClass>();
    private JTextField tfDatePicker = null;
    private JTextField tfDatePicker1 = null;
    private boolean hide_flag = false;
    int i = 0, flag = 0;
    ResultSet rs1, rs2, rs3, rs4;
    String q = "", str = "";
    static double total_purchase, total_sales;
    String date1, date2;
    AdvanceSaleReportDTOWithFinalValue advanceSaleReportDTOWithFinalValue = new AdvanceSaleReportDTOWithFinalValue();

    Set<String> saleIDSet = new HashSet<String>();
    /**
     * Create the frame.
     */
    public SaleReport() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent arg0) {
                try {
                    Calendar currentDate = Calendar.getInstance();
                    dat1.setDate(currentDate.getTime());

                    dat2.setDate(currentDate.getTime());

                    DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                            .getModel();
                    tableModel.setRowCount(0);
                    tableModel.setColumnCount(11);
                    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
                    tableCredit.setRowSorter(sorter);

                    buttonShow.setMnemonic(KeyEvent.VK_S);
                    buttonPrint.setMnemonic(KeyEvent.VK_P);
                    buttonBack.setMnemonic(KeyEvent.VK_B);

                    btnShowActionPerformed(null);

                    dat1.requestFocus();

                    tableCredit.setShowGrid(true);

                    JTableHeader header2 = tableCredit.getTableHeader();
                    header2.setBackground(Color.yellow);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
                }

            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                MainClass.setstaticvar();
            }
        });
        setTitle("Sale Report");
        setBounds(100, 100, 1322, 674);

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout(
                "",
                "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblDaybook = new JLabel("Sale Report");
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
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
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
                    if (tableCredit.getRowCount() > 0) {
                        tableCredit.requestFocus();
                        tableCredit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });
        buttonShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    btnShowActionPerformed(arg0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
                }
            }
        });
        panel.add(buttonShow, "cell 1 1");

        buttonPrint = new JButton("Print");
        buttonPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnPrintActionPerformed(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
                }

            }
        });
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
                    if (tableCredit.getRowCount() > 0) {
                        tableCredit.requestFocus();
                        tableCredit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });
        panel.add(buttonPrint, "cell 1 1");

        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    btnBackActionPerformed(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
                }
            }
        });
        buttonBack.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonPrint.requestFocus();
                } if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonAdvanceSaleReport.requestFocus();
                }
            }
        });
        panel.add(buttonBack, "cell 1 1");

        buttonAdvanceSaleReport = new JButton("Advance Sale Report");
        buttonAdvanceSaleReport.setMnemonic('A');
//        buttonAdvanceSaleReport.setEnabled(false);
        panel.add(buttonAdvanceSaleReport, "cell 1 1");

        buttonAdvanceSaleReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btnAdvanceSaleReportActionPerformed(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
                }
            }
        });

        buttonAdvanceSaleReport.addKeyListener(new KeyAdapter() {
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
                    if (tableCredit.getRowCount() > 0) {
                        tableCredit.requestFocus();
                        tableCredit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });

        buttonExport = new JButton("Export");
//        buttonExport.setEnabled(false);
        buttonExport.setMnemonic('E');
        panel.add(buttonExport, "cell 1 1");

        buttonExport.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttonAdvanceSaleReport.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonAdvanceSaleReport.requestFocus();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (tableCredit.getRowCount() > 0) {
                        tableCredit.requestFocus();
                        tableCredit.changeSelection(0, 0, false, false);
                    } else {
                        buttonShow.requestFocus();
                    }
                }
            }
        });

        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    buttonExportActionPerformed(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(SaleReport.this, "Export Failure");
                }

            }
        });

        panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_1, "cell 1 2 1 19,grow");
        panel_1.setLayout(new MigLayout("",
                "[0px:1280px:1280px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.add(panel_2, "cell 0 1,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        scrollPane = new JScrollPane();
        panel_2.add(scrollPane, "cell 0 0,grow");

        tableCredit = new JTable();
        tableCredit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = tableCredit.getSelectedRow();
                if (tableCredit.getValueAt(row, 1) != null && tableCredit.getValueAt(row, 3) != null) {
                    try {
                        String id = tableCredit.getValueAt(
                                tableCredit.getSelectedRow(), 2).toString();
                        String date = tableCredit.getValueAt(tableCredit.getSelectedRow(), 0).toString();
        System.err.println("date====" + date);
        Date dateEntity = (java.util.Date) Constants.DATE_FORMATER_WITH_SLASH.parse(date.trim());
                        SaleDTO saleDTO = new SaleDTO();
                        SaleForm s = new SaleForm("Sales", new Dimension(),
                                saleDTO, true);
                        s.loadEditForm(id, dateEntity);
                        s.pack();
                        JDesktopPane desktopPane = getDesktopPane();
                        desktopPane.add(s);
                        Dimension desktopSize = getDesktopPane().getSize();
                        s.setSize(desktopSize);
                        s.setPreferredSize(desktopSize);
                        s.setVisible(!s.isVisible());
                        s.setSelected(true);
                        BasicInternalFrameUI ui = (BasicInternalFrameUI) s
                                .getUI();

                        Component north = ui.getNorthPane();
                        MouseMotionListener[] actions = (MouseMotionListener[]) north
                                .getListeners(MouseMotionListener.class);

                        for (int i = 0; i < actions.length; i++) {
                            north.removeMouseMotionListener(actions[i]);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
                    }
                }
            }
        });
        tableCredit.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null, null, null},},
                new String[]{
                    "Date", "Particulars", "VCH No", "Quantity", "Amount", "Ex Amt", "Ed Amt", "H_Ed Amt", "VAT Amount", "CST Amt", "Final Amount"
                }));

        scrollPane.setViewportView(tableCredit);

        lblNewLabel_3 = new JLabel("");
        panel_1.add(lblNewLabel_3, "flowx,cell 0 2");

        lblNewLabel_2 = new JLabel("");
        panel_1.add(lblNewLabel_2, "cell 0 2");

        lblNewLabel = new JLabel("Total Quantity");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setVisible(false);
        panel_1.add(lblNewLabel, "cell 0 2");

        labelTotalCredit = new JLabel("0");
        labelTotalCredit.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel_1.add(labelTotalCredit, "cell 0 2");
        labelTotalCredit.setVisible(false);

        lblNewLabel_4 = new JLabel("");
        panel_1.add(lblNewLabel_4, "cell 0 2");

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
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
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
                    // tfDatePicker1.requestFocus();
                    try {
                        buttonShow.requestFocus();
                        btnShowActionPerformed(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SaleReport.this, ex.getMessage());
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

        // comment on date 21-12-2013 by atul,NO need of this code
        // it gives exception when invalid date entered
        // SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        // date1 = f.format(dat1.getDate());
        // date2 = f.format(dat2.getDate());

        if (dat1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat1.requestFocus();
            tfDatePicker.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);
            labelTotalCredit.setText("0");
        } else if (dat2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Invalid Date Entered");
            dat2.requestFocus();
            tfDatePicker1.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);
            labelTotalCredit.setText("0");
        } else if (dat1.getDate().getTime() > dat2.getDate().getTime()) {
            JOptionPane.showMessageDialog(this, "Invalid Date selection");
            dat2.requestFocus();
            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);
            labelTotalCredit.setText("0");
        } else {
            loadSalesEntry();
            labelTotalCredit.setText(""
                    + Constants.DECIMAL_FORMAT.format(total_sales));

            Connection conn = null;
            try {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = f.format(dat1.getDate());
                date1 = f.format(dat1.getDate());
                date2 = f.format(dat2.getDate());

                double o = 00f;
                double cr = 00f;
                double d = 00f;
                DecimalFormat df = new DecimalFormat("#.##");

                conn = DatabaseConnection1.GetConnection();
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                q = "select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date BETWEEN '"
                        + date1
                        + "' AND '"
                        + date2
                        + "' and trans_typeIndex<>6 order by trans_date)";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date BETWEEN '"+
                // date1 +"' AND '"+ date2
                // +"' and trans_typeIndex<>6 order by trans_date)";
                // q="select sum(trans_amt) as totalCred from tbltransactionledger where trans_type=2 and trans_id in (select trans_id from tbltransactionmain where trans_date>='"
                // + date1 + "' and trans_date<='" + date2 +
                // "' and trans_typeIndex=6)";
                rs1 = st.executeQuery(q);
                while (rs1.next()) {
                    cr = cr + rs1.getDouble("totalCred");
                }
                rs1.close();

                if (cr >= d) {
                    o = cr - d;
                    // labelOpeningBalance.setText(""+ df.format(Math.abs(o)));
                    // labelOType.setText("Credit");
                } else {
                    o = d - cr;
                    // labelOpeningBalance.setText(""+ df.format(Math.abs(o)));
                    // labelOType.setText("Debit");
                }

                // ----------------------calculate closing
                // balance-----------------------------
                // o=Double.parseDouble(labelOpeningBalance.getText());
                cr = Double.parseDouble(labelTotalCredit.getText());
                // d=Double.parseDouble(labelTotalDebit.getText());
                double cl = 00f;

                conn.commit();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
                throw ex;
            }
        }

        if (tableCredit.getRowCount() == 0) {
            labelTotalCredit.setText("0");

        }

        setColumnWidth(tableCredit);

    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        MainClass mainClass = new MainClass();
        mainClass.menuselection(3);
        this.setClosed(true);
    }

    private void btnAdvanceSaleReportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        getDesktopPane().setLayout(new CardLayout());
        gen.display.report.SaleReport1.AdvanceSaleReport c = new gen.display.report.SaleReport1.AdvanceSaleReport();
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

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        dataExport();
    }

    private void dataExport() throws Exception {
        path = "";
        imageloading o;
        o = new imageloading();
        System.out.println("--------------------size()uuu-11---->>:" + saleIDSet.size());

        BufferedWriter out = null;
	if (path != null) {
        try {
            // TODO add your handling code here:
            System.out.println("--------------------size()uuu----->>:" + saleIDSet.size());
            Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
            IDMapSet.put("Sale", saleIDSet);

            String xmlCode = TagsHelper1.exportDayBook(IDMapSet,"TransId");
            System.out.println("------------------------->>XMLCODE:" + xmlCode);

            // File file = new File("C:\\ChalanBook.xml");
            File file = new File(path + ".xml");
            String path = file.getPath();
            out = new BufferedWriter(new FileWriter(file));
            out.write(xmlCode);
            out.close();

            JOptionPane.showMessageDialog(this, "Export Successful");

        } catch (Exception ex) {
            Logger.getLogger(PurchaseReportWithAllTax.class.getName()).log(Level.SEVERE, null,
                    ex);
            JOptionPane.showMessageDialog(this, "Export failure");
        }
	}
    }
    
    
    String path = "";

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

            int r = chooser.showSaveDialog(gen.display.report.SaleReport1.SaleReport.this);

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
                    // FileWriter fstream = new
                    // FileWriter(System.currentTimeMillis() + "chalan.xml");
                    // BufferedWriter out = new BufferedWriter(fstream);
                    // out.write(path);
                    PrintStream ps = new PrintStream(file);
                    ps.print(path);

                    // Close the output stream
                    ps.close();
                } catch (Exception e) {
                }
            }
	    }
            return name1;
        }
        // public static void main(String a[])
        // {
        // new imageloading();
        // }
    }
    
    public class MessageBox {

        BufferedImage mImage;
        String name, name1;

        public MessageBox() {
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
                    return name.endsWith(".xls") || name.endsWith(".xls")
                            || name.endsWith(".xls") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return ".xls";
                }
            });

            int r = chooser.showSaveDialog(SaleReport.this);

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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return name1;
        }
    }

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        try {
            btnShowActionPerformed(evt);
            if (dat1.getDate() != null && dat2.getDate() != null
                    && tableCredit.getRowCount() > 0
                    && dat1.getDate().getTime() <= dat2.getDate().getTime()) {
                Map parameter = new HashMap();
                SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
                parameter.put("dateFrom", saDa.format(dat1.getDate()).toString());
                parameter.put("dateTo", saDa.format(dat2.getDate()).toString());
                String TotalAmountCredit = labelTotalCredit.getText().toString()
                        .trim();
                parameter.put("TotalAmountCredit", TotalAmountCredit);
                PrintAllReport.printDSaleReport(parameter, new JRTableModelDataSource(tableCredit.getModel()));
            } else {
                JOptionPane.showMessageDialog(this, "No data to print");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void loadSalesEntry() throws Exception {
        saleIDSet = new TreeSet<String>();
        Connection conn = null;
        try {
            Double total_Ex_Amount = 0D;
            Double total_Ed_Amount = 0D;
            Double total_H_Ed_Amount = 0D;
            Double total_CST_Amount = 0D;
            Double total_VAT_Amount = 0D;

            conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);
            Statement st1 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = f.format(dat1.getDate());
            date1 = f.format(dat1.getDate());
            date2 = f.format(dat2.getDate());

            // -----------------------Table:
            // Debit--------------------------------------------

            // added for export in excel
            List<AdvanceSaleReportDTO> advanceSaleReportDTOList = new ArrayList<AdvanceSaleReportDTO>();

            DefaultTableModel tableModel = (DefaultTableModel) tableCredit
                    .getModel();
            tableModel.setRowCount(0);
            tableModel.setColumnCount(11);
            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
            tableCredit.setRowSorter(sorter);
            System.out.println("======query");

            q = "select trans_ledgerId,trans_amt,trans_id from tbltransactionledger tbltransTbl INNER JOIN tbltransactionmain tbltransactionmain ON tbltransTbl.trans_id = tbltransactionmain.trans_id where trans_type=" + gen.dto.Constants.CREDIT + " and trans_id in(select trans_id from tbltransactionmain where trans_date BETWEEN '"
                    + date1
                    + "' AND '"
                    + date2
                    + "' and trans_typeIndex=1 order by trans_date desc,trans_receiptNo desc)  order by tbltransactionmain.trans_receiptNo desc,tbltransactionmain.trans_date desc ";
            rs2 = st1.executeQuery(q);
            while (rs2.next()) {
                // AdvanceSaleReportDTO added
                AdvanceSaleReportDTO advanceSaleReportDTO = new AdvanceSaleReportDTO();

                q = "select ledger_name from tblledger where ledger_id="
                        + rs2.getLong("trans_ledgerid") + "";
                rs3 = st3.executeQuery(q);
                if (rs3.next()) {
                    int row = tableCredit.getRowCount();
                    tableModel.setRowCount(row + 1);

                    // Sr No
                    // tableCredit.setValueAt(row + 1, row, 0);

                    // Get Ledger name
                    tableCredit.setValueAt(rs3.getString("ledger_name"), row, 1);
                    advanceSaleReportDTO.setParticular(rs3.getString("ledger_name"));

                    // VCH No(Receipt No)
                    q = "select trans_receiptNo,trans_typeIndex,trans_date from tbltransactionmain where trans_id="
                            + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
                        String dateSet = f1.format(rs4.getDate("trans_date"));
                        tableCredit.setValueAt(rs4.getLong("trans_receiptNo"), row, 2);
                        advanceSaleReportDTO.setVch_No(rs4.getString("trans_receiptNo"));
                        saleIDSet.add(advanceSaleReportDTO.getVch_No());
                        tableCredit.setValueAt(dateSet, row, 0);
                        advanceSaleReportDTO.setSr_no(dateSet);
                    }
                    rs4.close();

                    // Debitted Amount
                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")), row, 10);
                    advanceSaleReportDTO.setTotal_Final_Amount(Constants.DECIMAL_FORMAT.format(rs2.getDouble("trans_amt")));

                    // Qty
                    q = "select sum(invtrans_qty) as totalQty from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id="
                            + rs2.getLong("trans_id") + ")";
                    rs4 = st4.executeQuery(q);
                    if (rs4.next()) {
                        // tableCredit.setValueAt(rs4.getDouble("totalQty"),
                        // row, 2);
                        tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("totalQty")), row, 3);
                        advanceSaleReportDTO.setQuantity(Constants.DECIMAL_FORMAT.format(rs4.getDouble("totalQty")));
                    }
                    rs4.close();

                    //VAT amount and Basic Amount
                    q = "select od.trans_amt,tv.vat_amt,tv.tax_type,tv.vat_rate from tbltransactionotherdetails as od INNER JOIN tbltransactionvat as tv on od.trans_id = tv.trans_id where od.trans_id = " + rs2.getLong("trans_id") + "";
                    rs4 = st4.executeQuery(q);
                    while (rs4.next()) {
                        if (rs4.getInt("tax_type") == 1) {
                            if (rs4.getDouble("vat_rate") <= 0D) {
//                                tableCredit.setValueAt("", row, 5);
                                tableCredit.setValueAt("", row, 8);
                                total_VAT_Amount = 0D + total_VAT_Amount;
                                advanceSaleReportDTO.setVat_Amt("");
                            } else {
                                tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 8);
                                advanceSaleReportDTO.setVat_Amt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")));
//                                tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 6);
                                total_VAT_Amount = rs4.getDouble("vat_amt") + total_VAT_Amount;
                            }

                        }
                        if (rs4.getInt("tax_type") == 2) {
                            if (rs4.getDouble("vat_rate") <= 0D) {
                                tableCredit.setValueAt("", row, 5);
//                                    tableCredit.setValueAt("", row, 8);
                                advanceSaleReportDTO.setEx_Amt("");
                                total_Ex_Amount = 0D + total_Ex_Amount;
                            } else {
                                tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 5);
//                                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 8);
                                advanceSaleReportDTO.setEx_Amt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")));
                                total_Ex_Amount = rs4.getDouble("vat_amt") + total_Ex_Amount;
                            }
                        }
                        if (rs4.getInt("tax_type") == 3) {
                            if (rs4.getDouble("vat_rate") <= 0D) {
                                tableCredit.setValueAt("", row, 6);
//                                    tableCredit.setValueAt("", row, 10);
                                advanceSaleReportDTO.setEd_Amt("");
                                total_Ed_Amount = 0D + total_Ed_Amount;
                            } else {
                                tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 6);
//                                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 10);
                                advanceSaleReportDTO.setEd_Amt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")));
                                total_Ed_Amount = rs4.getDouble("vat_amt") + total_Ed_Amount;
                            }
                        }
                        if (rs4.getInt("tax_type") == 4) {

                            if (rs4.getDouble("vat_rate") <= 0D) {
                                tableCredit.setValueAt("", row, 7);
//                                    tableCredit.setValueAt("", row, 12);
                                advanceSaleReportDTO.setH_Ed_Amt("");
                                total_H_Ed_Amount = 0D + total_H_Ed_Amount;
                            } else {
                                tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 7);
//                                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 12);
                                advanceSaleReportDTO.setH_Ed_Amt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")));
                                total_H_Ed_Amount = rs4.getDouble("vat_amt") + total_H_Ed_Amount;
                            }
                        }
                        if (rs4.getInt("tax_type") == 5) {

                            if (rs4.getDouble("vat_rate") <= 0D) {
                                tableCredit.setValueAt("", row, 9);
//                                    tableCredit.setValueAt("", row, 14);
                                advanceSaleReportDTO.setCst_Amt("");
                                total_CST_Amount = 0D + total_CST_Amount;
                            } else {
//                                    tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_rate")), row, 13);
                                tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")), row, 9);
                                advanceSaleReportDTO.setCst_Amt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("vat_amt")));
                                total_CST_Amount = rs4.getDouble("vat_amt") + total_CST_Amount;
                            }
                        }

                        tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(rs4.getDouble("trans_amt")), row, 4);
                        advanceSaleReportDTO.setTotal_Basic_Amount(Constants.DECIMAL_FORMAT.format(rs4.getDouble("trans_amt")));
                    }
                    rs4.close();
                    advanceSaleReportDTOList.add(advanceSaleReportDTO);
                }
            }
            // --------------------------------------------------------------

            // -------------------Count Total Creditted
            //Calculating all Totals by Sudeep : 11-03-2014
            // Amount--------------------
            double qty = 0;
            double basicAmount = 0;
            double vatAmount = 0;
            double finalAmount = 0;
            if (tableCredit.getRowCount() > 0) {
                i = 0;
                while (i < tableCredit.getRowCount()) {
                    if (tableCredit.getValueAt(i, 3) != null) {
                        qty = qty + Double.parseDouble(tableCredit.getValueAt(i, 3).toString());
                    }
                    i++;
                }
                total_sales = qty;

                i = 0;
                while (i < tableCredit.getRowCount()) {
                    if (tableCredit.getValueAt(i, 4) != null) {
                        basicAmount = basicAmount + Double.parseDouble(tableCredit.getValueAt(i, 4).toString());
                    }
                    i++;
                }

//                    i = 0;
//                    while (i < tableCredit.getRowCount()) {
//                        if (tableCredit.getValueAt(i, 6) != null) {
//                            vatAmount = vatAmount + Double.parseDouble(tableCredit.getValueAt(i, 6).toString());
//                        }
//                        i++;
//                    }

                i = 0;
                while (i < tableCredit.getRowCount()) {
                    if (tableCredit.getValueAt(i, 10) != null) {
                        finalAmount = finalAmount + Double.parseDouble(tableCredit.getValueAt(i, 10).toString());
                    }
                    i++;
                }

            }

            // --------------------------------------------------------------------------------

            advanceSaleReportDTOWithFinalValue.setAdvanceSaleReportDTOList(advanceSaleReportDTOList);
            int rowCount = tableCredit.getRowCount() + 1;
            tableModel.setRowCount(rowCount + 1);
            tableCredit.setValueAt("Total", rowCount, 2);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(qty), rowCount, 3);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(basicAmount), rowCount, 4);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(total_VAT_Amount), rowCount, 8);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(total_Ex_Amount), rowCount, 5);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(total_Ed_Amount), rowCount, 6);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(total_H_Ed_Amount), rowCount, 7);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(total_CST_Amount), rowCount, 9);
            tableCredit.setValueAt(Constants.DECIMAL_FORMAT.format(finalAmount), rowCount, 10);

            advanceSaleReportDTOWithFinalValue.setFinal_Total_Quantity(Constants.DECIMAL_FORMAT.format(qty));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_Basic_Amount(Constants.DECIMAL_FORMAT.format(basicAmount));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_Ex_Amount(Constants.DECIMAL_FORMAT.format(total_Ex_Amount));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_Ed_Amount(Constants.DECIMAL_FORMAT.format(total_Ed_Amount));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_H_Ed_Amount(Constants.DECIMAL_FORMAT.format(total_H_Ed_Amount));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_Vat_Amount(Constants.DECIMAL_FORMAT.format(total_VAT_Amount));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_Cst_Amount(Constants.DECIMAL_FORMAT.format(total_CST_Amount));
            advanceSaleReportDTOWithFinalValue.setFinal_Total_Amount(Constants.DECIMAL_FORMAT.format(finalAmount));

            conn.commit();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            throw ex;
        }
    }

    private void setColumnWidth(JTable passedTable) throws Exception {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

//        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(0).setMinWidth(300);
//        passedTable.getColumnModel().getColumn(0).setMaxWidth(300);
//
//        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(1).setMinWidth(60);
//        passedTable.getColumnModel().getColumn(1).setMaxWidth(60);
//
//        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(2).setMinWidth(100);
//        passedTable.getColumnModel().getColumn(2).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(2)
                .setCellRenderer(rightRenderer);

//        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(3).setMinWidth(100);
//        passedTable.getColumnModel().getColumn(3).setMaxWidth(100);
        passedTable.getColumnModel().getColumn(3)
                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4)
                .setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
        passedTable.getColumnModel().getColumn(10).setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(11).setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(12).setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(13).setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(14).setCellRenderer(rightRenderer);
//        passedTable.getColumnModel().getColumn(15).setCellRenderer(rightRenderer);

//        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
//        passedTable.getColumnModel().getColumn(4).setMinWidth(120);
//        passedTable.getColumnModel().getColumn(4).setMaxWidth(120);


        tableCredit.getColumnModel().getColumn(0).setMaxWidth(100);
        tableCredit.getColumnModel().getColumn(1).setPreferredWidth(220);
        tableCredit.getColumnModel().getColumn(1).setMaxWidth(220);
        tableCredit.getColumnModel().getColumn(2).setPreferredWidth(50);
        tableCredit.getColumnModel().getColumn(2).setMaxWidth(50);
        tableCredit.getColumnModel().getColumn(3).setMaxWidth(75);
        tableCredit.getColumnModel().getColumn(4).setPreferredWidth(150);
        tableCredit.getColumnModel().getColumn(4).setMaxWidth(150);
        tableCredit.getColumnModel().getColumn(5).setMaxWidth(100);
        tableCredit.getColumnModel().getColumn(6).setPreferredWidth(100);
        tableCredit.getColumnModel().getColumn(6).setMaxWidth(100);
        tableCredit.getColumnModel().getColumn(7).setPreferredWidth(100);
        tableCredit.getColumnModel().getColumn(7).setMaxWidth(100);
        tableCredit.getColumnModel().getColumn(8).setPreferredWidth(100);
        tableCredit.getColumnModel().getColumn(8).setMaxWidth(100);
        tableCredit.getColumnModel().getColumn(9).setPreferredWidth(100);
        tableCredit.getColumnModel().getColumn(9).setMaxWidth(100);
        tableCredit.getColumnModel().getColumn(10).setPreferredWidth(150);
        tableCredit.getColumnModel().getColumn(10).setMaxWidth(150);
//        tableCredit.getColumnModel().getColumn(11).setPreferredWidth(60);
//        tableCredit.getColumnModel().getColumn(11).setMaxWidth(60);
//        tableCredit.getColumnModel().getColumn(12).setPreferredWidth(60);
//        tableCredit.getColumnModel().getColumn(12).setMaxWidth(60);
//        tableCredit.getColumnModel().getColumn(13).setPreferredWidth(70);
//        tableCredit.getColumnModel().getColumn(13).setMaxWidth(70);
//        tableCredit.getColumnModel().getColumn(14).setPreferredWidth(60);
//        tableCredit.getColumnModel().getColumn(14).setMaxWidth(60);
//        tableCredit.getColumnModel().getColumn(15).setPreferredWidth(150);
//        tableCredit.getColumnModel().getColumn(15).setMaxWidth(150);

    }
}
