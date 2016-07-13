package gen.accountvoucher.sale;

//// third 
import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.TagsHelper1;
import gen.account.StockItemFormation.StockItemColorDTO;
import gen.account.StockItemFormation.StockItemDTO;
import gen.account.ledger.LedgerDTO;
import gen.accountvoucher.TableCellListener;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.StockItemTransactionDTO;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class SaleForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private SaleDTO saleDTO;
    private List<SaleDTO> saleDTOTransactionList;
    private Boolean isEdit = false;
    private Vector<String> cashLedgerVector = new Vector<String>();
    private Vector<String> saleLedgerVector = new Vector<String>();
    private Vector<String> stockItemVector = new Vector<String>();
    private Vector<String> colorVector = new Vector<String>();
    private Vector<String> unitVector = new Vector<String>();
    //variables For Calculation
    private Map<String, String> accountLedgerMap;
    private Map<String, String> saleLedgerMap;
    private Map<String, String> stockItemMap;
    private Map<String, String> colorMap;
    private Map<String, String> unitMap;
//    private Map<String, String> unitMap;
    private Map<String, String> categoryMap;
    private Map<String, String> boardTypeMap;
    private Map<String, String> typeMap;
    //rate Map
    Map<String, Map<String, String>> rateMap;
    //variable for focus
    private int currentFocusValue = 0;
    private int recordsCount = 0;
    //time stamp
    private Long ledgerTimeStamp;
    private Long stockItemTimeStamp;
    private int temp = 1000;
    private int indexForDeletion = 0;
    private JPopupMenu popUpMenu = new JPopupMenu();
    private JMenuItem Copy_Transaction = new JMenuItem();
    private Boolean isTransactionTableLoad = false;
    static String dir = System.getProperty("user.dir");
    //int result = 0;
    int jTextFieldExciseDutyAmountResult = 0;
    int jTextFieldEdCessDutyAmountResult = 0;
    int jTextFieldHEdCessDutyAmountResult = 0;
    int jTextFieldVatAmountResult = 0;
    int jTextFieldCSTAmountResult = 0;
    int labeltxtBasicAmountResult = 0;
    int labelFinalAmountTotalResult = 0;
    int jTextFieldQuantityResult = 0;
    int jTextFieldRateResult = 0;
    int jTextFieldAmountResult = 0;
    Image img1 = new ImageIcon(getClass().getResource("/images/Down.gif")).getImage();
    Image img2 = new ImageIcon(getClass().getResource("/images/Left.gif")).getImage();
    private static int last_Number_In_TransactionList = 0;

    public SaleForm(String s, Dimension d) {
        try {
            initComponents();
            saleDTO = new SaleDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public SaleForm(String s, Dimension d, SaleDTO saleDTO, Boolean isEdit) {
        try {
            initComponents();
            this.isEdit = isEdit;
            this.saleDTO = saleDTO;
            isTransactionTableLoad = true;
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
        }
    }

    private void initComponents() {

        setFrameIcon(new ImageIcon(SaleForm.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Sale Transaction Form");
        setBounds(100, 100, 1332, 674);

        jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(null, "Sale Form",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel.setLayout(new MigLayout("", "[][0px:112.5px:112.5px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][][0px:112.5px:112.5px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][][grow][][0px:70px:70px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:15px:15px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelReceiptNo = new JLabel("Sale No.");
        jMainPanel.add(labelReceiptNo, "cell 0 0");

        jTextFieldReceiptNo = new JTextField();
        jTextFieldReceiptNo.setEnabled(false);
        jTextFieldReceiptNo.setForeground(new Color(0, 0, 0));
        jMainPanel.add(jTextFieldReceiptNo, "cell 1 0 2 1");
        jTextFieldReceiptNo.setTransferHandler(null);
        jTextFieldReceiptNo.setColumns(20);

        labelDate = new JLabel("Date");
        jMainPanel.add(labelDate, "cell 4 0");

        jDateChooser = new JDateChooser();
        jMainPanel.add(jDateChooser, "cell 5 0 2 1");

        panel = new JPanel();
        jMainPanel.add(panel, "cell 8 0 3 5,grow");
        panel.setLayout(new MigLayout("", "[0px:50px:50px,grow,shrink 50,fill][grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[][][][0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill]"));

        lblEcsno = new JLabel("E.C.S.No.");
        panel.add(lblEcsno, "cell 0 0,alignx trailing");

        jtextField_CSENo = new JTextField();
        panel.add(jtextField_CSENo, "cell 1 0,growx");
        jtextField_CSENo.setColumns(10);

        lblCvatno = new JLabel("C.VAT.No.");
        panel.add(lblCvatno, "cell 0 1,alignx trailing");

        jtextField_CVATNo = new JTextField();
        panel.add(jtextField_CVATNo, "cell 1 1,growx");
        jtextField_CVATNo.setColumns(10);

        lblCcstno = new JLabel("C.CST.No.");
        panel.add(lblCcstno, "cell 0 2,alignx trailing");

        jtextField_CSTNo = new JTextField();
        panel.add(jtextField_CSTNo, "cell 1 2,growx");
        jtextField_CSTNo.setColumns(10);

        lblPono_1 = new JLabel("P.O.No");
        panel.add(lblPono_1, "cell 0 3,alignx trailing");

        jtextField_PONo = new JTextField();
        panel.add(jtextField_PONo, "cell 1 3,growx");
        jtextField_PONo.setColumns(10);

        lblPodate = new JLabel("P.O.Date");
        panel.add(lblPodate, "cell 2 3,alignx trailing");

        dateChooser_PO = new JDateChooser();
        panel.add(dateChooser_PO, "cell 3 3,growx");
//        jtextField_PODate.setColumns(10);

        lblOcno = new JLabel("O.C.No.");
        panel.add(lblOcno, "cell 0 4,alignx trailing");

        jtextField_OCNo = new JTextField();
        panel.add(jtextField_OCNo, "cell 1 4,growx");
        jtextField_OCNo.setColumns(10);

        lblOcdate_1 = new JLabel("O.C.Date");
        panel.add(lblOcdate_1, "cell 2 4,alignx trailing");

        dateChooser_OCDate = new JDateChooser();
        panel.add(dateChooser_OCDate, "cell 3 4,growx");
//        jtextField_OCDate.setColumns(10);

        labelSearch = new JLabel("Search");
        jMainPanel.add(labelSearch, "flowx,cell 11 0");

        labelAccount = new JLabel("Buyer's Name   ");
        jMainPanel.add(labelAccount, "cell 0 1");

        jComboBoxAccount = new JComboBox();
        jComboBoxAccount.setPrototypeDisplayValue("xxxxxx");
        jComboBoxAccount.setEditable(true);
        jMainPanel.add(jComboBoxAccount, "cell 1 1 2 1,growx");

        labelSaleLedger = new JLabel("Sales    ");
        jMainPanel.add(labelSaleLedger, "cell 4 1");

        jComboBoxSaleLedger = new JComboBox();
        jComboBoxSaleLedger.setPrototypeDisplayValue("xxxxxx");
        jComboBoxSaleLedger.setEditable(true);
        jMainPanel.add(jComboBoxSaleLedger, "cell 5 1 2 1,growx");

        lblNewLabel = new JLabel("Address            ");
        lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        jMainPanel.add(lblNewLabel, "cell 0 2,alignx trailing");

        jtextArea_Buyer_Address = new JTextArea();
        jtextArea_Buyer_Address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtextArea_Buyer_Address.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(jtextArea_Buyer_Address);
        jMainPanel.add(scroll, "cell 1 2 2 2,growx");

        JScrollPane pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 11 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        String col1[] = {Label.SALE_NO, Label.DATE_TRANSACTION, Label.ACCOUNT};
        String data1[][] = {{"", "", ""}};

        transactionTableModel1 = new DefaultTableModel(data1, col1) {
            public Class getColumnClass(int row) {
                Class returnValue;
                if ((row >= 0) && (row < getRowCount())) {
                    returnValue = getValueAt(0, row).getClass();
                    System.out.println("getValueAt(0, row)---->>" + getValueAt(0, row));
                } else {
                    returnValue = Object.class;
                }
                System.out.println("Class---->>" + returnValue);
                return returnValue;
            }
        };

        jTableTransactionList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        
//        jTableTransactionList.setModel(new DefaultTableModel(
//                new Object[][]{
//                    {"", "", ""},},
//                new String[]{
//                    "A", "B", "C"
//                }));
        
       
        jTableTransactionList.setModel(transactionTableModel1);
        JTableHeader header1 = jTableTransactionList.getTableHeader();
        jTableTransactionList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        transactionTableModel1 = (DefaultTableModel) jTableTransactionList.getModel();
        transactionTableModel1.setRowCount(0);
        transactionTableModel1.setColumnCount(3);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(transactionTableModel1);
        jTableTransactionList.setRowSorter(sorter);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableTransactionList.setFont(font);
        pane1.setViewportView(jTableTransactionList);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);

        jTableTransactionList.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        jTableTransactionList.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);

        jTableTransactionList.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTableTransactionList.getColumnModel().getColumn(0).setMinWidth(0);
        jTableTransactionList.getColumnModel().getColumn(1).setPreferredWidth(40);
        jTableTransactionList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableTransactionList.getColumnModel().getColumn(2).setPreferredWidth(140);

        lblNewLabel_1 = new JLabel("Contact             ");
        jMainPanel.add(lblNewLabel_1, "cell 0 4,alignx trailing");

        jtextField_Buyer_Contno = new JTextField();
        jMainPanel.add(jtextField_Buyer_Contno, "cell 1 4 2 1,growx");
        jtextField_Buyer_Contno.setColumns(10);

        JPanel panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 0 5 10 9,grow");
        panel_1.setLayout(new MigLayout("", "[20px:20px:20px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:58.5px:58.5px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelSN = new JLabel("SN");
        panel_1.add(labelSN, "cell 0 0");

        labelParticulars = new JLabel("Particulars");
        panel_1.add(labelParticulars, "cell 1 0");

        lblThickness = new JLabel("Thk.");
        panel_1.add(lblThickness, "cell 2 0");

        lblColor = new JLabel("Color");
        panel_1.add(lblColor, "cell 3 0");

        lblSizea = new JLabel("SizeA");
        panel_1.add(lblSizea, "cell 4 0");

        lblSizeb = new JLabel("SizeB");
        panel_1.add(lblSizeb, "cell 5 0");

        lblPackage = new JLabel("Package");
        panel_1.add(lblPackage, "cell 6 0");

        lblNumbers = new JLabel("Nos.");
        panel_1.add(lblNumbers, "cell 7 0");

        lblUnit = new JLabel("Unit");
        panel_1.add(lblUnit, "cell 8 0");

        labelQuantity = new JLabel("Quantity");
        panel_1.add(labelQuantity, "cell 9 0");

        labelRate = new JLabel("Rate");
        panel_1.add(labelRate, "cell 10 0");

        labelAmount = new JLabel("Amount");
        panel_1.add(labelAmount, "cell 11 0");

        jComboBoxParti = new JComboBox();
        jComboBoxParti.setPrototypeDisplayValue("xxxxxx");
        jComboBoxParti.setEditable(true);
        panel_1.add(jComboBoxParti, "cell 1 1,growx");

        jtextField_Thk = new JTextField();
        panel_1.add(jtextField_Thk, "cell 2 1,growx");
        jtextField_Thk.setColumns(10);
        jtextField_Thk.setEnabled(false);

        jcomboBoxColor = new JTextField();
//        jcomboBoxColor.setMinimumSize(new Dimension(10, 20));
//        jcomboBoxColor.setEditable(true);
        jcomboBoxColor.setColumns(10);
        panel_1.add(jcomboBoxColor, "cell 3 1,growx");
        jcomboBoxColor.setEnabled(false);

        jTextField_SizeA = new JTextField();
        panel_1.add(jTextField_SizeA, "cell 4 1,growx");
        jTextField_SizeA.setColumns(10);
        jTextField_SizeA.setEnabled(false);

        jTextField_SizeB = new JTextField();
        panel_1.add(jTextField_SizeB, "cell 5 1,growx");
        jTextField_SizeB.setColumns(10);
        jTextField_SizeB.setEnabled(false);

        jTextField_Package = new JTextField();
        panel_1.add(jTextField_Package, "cell 6 1,growx");
        jTextField_Package.setColumns(10);

        jTextField_Nos = new JTextField();
        panel_1.add(jTextField_Nos, "cell 7 1,growx");
        jTextField_Nos.setColumns(10);

        jcomboBoxUnit = new JComboBox();
        jcomboBoxUnit.setEditable(true);
        jcomboBoxUnit.setMinimumSize(new Dimension(10, 20));
        panel_1.add(jcomboBoxUnit, "cell 8 1,growx");

        jTextFieldQuantity = new JTextField();
        panel_1.add(jTextFieldQuantity, "cell 9 1,growx");
        jTextFieldQuantity.setTransferHandler(null);
        jTextFieldQuantity.setColumns(10);

        jTextFieldRate = new JTextField();
        panel_1.add(jTextFieldRate, "cell 10 1,growx");
        jTextFieldRate.setTransferHandler(null);
        jTextFieldRate.setColumns(10);

        jTextFieldAmount = new JTextField();
        panel_1.add(jTextFieldAmount, "cell 11 1,growx");
        jTextFieldAmount.setTransferHandler(null);
        jTextFieldAmount.setColumns(10);

        JScrollPane pane = new JScrollPane();
        panel_1.add(pane, "cell 0 2 12 5,grow");

//        String col[] = {Label.S_N, Label.PARTICULARS, Label.RATE, Label.UNIT, Label.AMOUNT, Label.UNIT_SYMBOL, Label.COLOR, Label.PACKAGE, Label.NOS, Label.LENGTH, Label.WIDTH, Label.THICKNESS};
        String col[] = {"SN", Label.PARTICULARS, "Thick", Label.COLOR, "SizeA", "SizeB", Label.PACKAGE, Label.NOS, Label.UNIT, Label.QUANTITY, Label.RATE, Label.AMOUNT, Label.UNIT_SYMBOL};
        String data[][] = {{"", "", "", "", "", "", "", "", "", "", "", "", ""}};
        partiTableModel = new DefaultTableModel(data, col);
        tableParti = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 9 || column == 10 || column == 11) {
                    return true;
                } else {
                    return false;
                }
            }
        ;
        };
        
        tableParti.setModel(partiTableModel);
        JTableHeader header = tableParti.getTableHeader();
        tableParti.getTableHeader().setReorderingAllowed(false);
        header.setBackground(Color.yellow);
        header.setFont(font);
        partiTableModel = (DefaultTableModel) tableParti.getModel();
        partiTableModel.setRowCount(0);
        partiTableModel.setColumnCount(13);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(280);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(3).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(4).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(5).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(6).setPreferredWidth(60);
        tableParti.getColumnModel().getColumn(7).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(8).setPreferredWidth(80);
        tableParti.getColumnModel().getColumn(9).setPreferredWidth(80);
        tableParti.getColumnModel().getColumn(10).setPreferredWidth(50);
        tableParti.getColumnModel().getColumn(11).setPreferredWidth(110);
        tableParti.getColumnModel().getColumn(12).setPreferredWidth(0);
        tableParti.getColumnModel().getColumn(12).setMinWidth(0);
        tableParti.getColumnModel().getColumn(12).setMaxWidth(0);

        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableCellListener tcl = (TableCellListener) e.getSource();
                    int column = tcl.getColumn();

                    System.out.println("Checking Control");
                    if (column == 9) {
                        System.out.println("in if");
                        int row = tcl.getRow();
                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                        TableModel partiTableModel = tcl.getTable().getModel();
                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 10).toString());
                        Double value = new Double((quantity * rate));
                        partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(value), row, 11);
                        calculateAndBindTotalTOGUI();
                        updateOnKeyType();
                    } else if (column == 10) {
                        System.out.println("in if");
                        int row = tcl.getRow();
                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                        TableModel partiTableModel = tcl.getTable().getModel();
                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 10).toString());
                        Double value = new Double((quantity * rate));
                        partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(value), row, 11);
                        calculateAndBindTotalTOGUI();
                        updateOnKeyType();
                    } else if (column == 11) {
                        System.out.println("in else");
                        int row = tcl.getRow();
                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                        System.out.println("newPrice--->>" + newPrice);
                        TableModel model = tcl.getTable().getModel();
                        double weight = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
                        double amount = Double.parseDouble(partiTableModel.getValueAt(row, 11).toString());
                        Double newValue = new Double((amount / weight));
                        model.setValueAt(Constants.DECIMAL_FORMAT.format(newValue), row, 10);
                        calculateAndBindTotalTOGUI();
                        updateOnKeyType();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        };

        TableCellListener tcl = new TableCellListener(tableParti, action);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        pane.setViewportView(tableParti);
        

        buttonAdd = new JButton("Add");
        buttonAdd.setMnemonic('D');
        jMainPanel.add(buttonAdd, "cell 10 6");

        buttonParticularDelete = new JButton("Delete");
        buttonParticularDelete.setMnemonic('L');
        buttonParticularDelete.setEnabled(false);
        jMainPanel.add(buttonParticularDelete, "cell 10 7");

        JPanel panel_2 = new JPanel();
        jMainPanel.add(panel_2, "cell 0 14 10 7,grow");
        panel_2.setLayout(new MigLayout("", "[0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:160px:160px,grow,shrink 0][0px:165px:165px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:135px:135px,grow,shrink 50,fill]", "[0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill][0px:18px:18px,grow,shrink 50,fill]"));

        labelNote = new JLabel("Delivery Type");
        panel_2.add(labelNote, "cell 0 0,aligny top");

        labelBasicAmount = new JLabel("Amount");
        labelBasicAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelBasicAmount, "cell 5 0");

        labeltxtBasicAmount = new JLabel("0");
        panel_2.add(labeltxtBasicAmount, "cell 7 0");

        jTextFieldNote = new JTextField();
        panel_2.add(jTextFieldNote, "cell 0 1 2 1,growx");
        jTextFieldNote.setColumns(10);


        labelDispatchDocNo = new JLabel("Payment With in");
        labelDispatchDocNo.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelDispatchDocNo, "cell 2 1,growx");

        jTextFieldDispatchDocNo = new JTextField();
        panel_2.add(jTextFieldDispatchDocNo, "cell 3 1,growx");
        jTextFieldDispatchDocNo.setColumns(10);

        lblExciseDuty = new JLabel("Excise Duty(%)");
        lblExciseDuty.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(lblExciseDuty, "cell 5 1,alignx trailing");

        jtextField_ExDutyPer = new JTextField();
        panel_2.add(jtextField_ExDutyPer, "cell 6 1,growx");
        jtextField_ExDutyPer.setColumns(10);

        jtextField_ExDutyAmt = new JTextField();
        panel_2.add(jtextField_ExDutyAmt, "cell 7 1,growx");
        jtextField_ExDutyAmt.setColumns(10);

        labelDispatchDocThrough = new JLabel("Transport");
        labelDispatchDocThrough.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelDispatchDocThrough, "cell 2 2,growx");

        jTextFieldDispatchDocThrough = new JTextField();
        panel_2.add(jTextFieldDispatchDocThrough, "cell 3 2,growx");
        jTextFieldDispatchDocThrough.setColumns(10);

        lblEdcess = new JLabel("Ed.Cess(%)");
        lblEdcess.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(lblEdcess, "cell 5 2,alignx trailing");

        jtextField_EdCessPer = new JTextField();
        panel_2.add(jtextField_EdCessPer, "cell 6 2,growx");
        jtextField_EdCessPer.setColumns(10);

        jtextField_EdCessAmt = new JTextField();
        panel_2.add(jtextField_EdCessAmt, "cell 7 2,growx");
        jtextField_EdCessAmt.setColumns(10);

        lblHedcess = new JLabel("H.Ed.Cess(%)");
        lblHedcess.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(lblHedcess, "cell 5 3,alignx trailing");

        jtextField_H_EdCessPer = new JTextField();
        panel_2.add(jtextField_H_EdCessPer, "cell 6 3,growx");
        jtextField_H_EdCessPer.setColumns(10);

        jtextField_H_EdCessAmt = new JTextField();
        panel_2.add(jtextField_H_EdCessAmt, "cell 7 3,growx");
        jtextField_H_EdCessAmt.setColumns(10);

        lblDateOfIssue = new JLabel("Date Iss");
        panel_2.add(lblDateOfIssue, "cell 0 4");
        lblDateOfIssue.setHorizontalAlignment(SwingConstants.RIGHT);

        dateChooser_Date_Iss = new JDateChooser();
        panel_2.add(dateChooser_Date_Iss, "cell 1 4");

        lblTimeOfIssue = new JLabel("Time Iss.");
        panel_2.add(lblTimeOfIssue, "cell 2 4");
        lblTimeOfIssue.setHorizontalAlignment(SwingConstants.RIGHT);

        jtextField_Time_Iss = new JTextField();
        panel_2.add(jtextField_Time_Iss, "cell 3 4");
        jtextField_Time_Iss.setColumns(10);

        lblNewLabel_3 = new JLabel("Total");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(lblNewLabel_3, "cell 5 4");

        jtextField_Total_WithoutVAT = new JTextField();
        panel_2.add(jtextField_Total_WithoutVAT, "cell 7 4,growx");
        jtextField_Total_WithoutVAT.setColumns(10);

        lblDateRemainder = new JLabel("Date Rem");
        panel_2.add(lblDateRemainder, "cell 0 5");
        lblDateRemainder.setHorizontalAlignment(SwingConstants.RIGHT);

        dateChooser_Date_Rem = new JDateChooser();
        panel_2.add(dateChooser_Date_Rem, "cell 1 5");

        lblTimeRem = new JLabel("Time Rem.");
        panel_2.add(lblTimeRem, "cell 2 5");
        lblTimeRem.setHorizontalAlignment(SwingConstants.RIGHT);

        jtextField_Time_Rem = new JTextField();
        panel_2.add(jtextField_Time_Rem, "cell 3 5");
        jtextField_Time_Rem.setColumns(10);

        labelVatRate = new JLabel("VAT Rate(%)");
        labelVatRate.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelVatRate, "cell 5 5");

        jTextFieldVatRate = new JTextField();
        panel_2.add(jTextFieldVatRate, "cell 6 5,growx");
        jTextFieldVatRate.setTransferHandler(null);
        jTextFieldVatRate.setColumns(10);

        jTextFieldVatAmount = new JTextField();
        panel_2.add(jTextFieldVatAmount, "cell 7 5,growx");
        jTextFieldVatAmount.setTransferHandler(null);
        jTextFieldVatAmount.setColumns(10);

        labeltxtRoundOffAmount = new JLabel("");

        lblCstRate = new JLabel("CST Rate(%)");
        lblCstRate.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(lblCstRate, "cell 5 6,alignx trailing");

        jTextFieldCSTRate = new JTextField();
        panel_2.add(jTextFieldCSTRate, "cell 6 6,growx");
        jTextFieldCSTRate.setColumns(10);

        jTextFieldCSTAmount = new JTextField();
        panel_2.add(jTextFieldCSTAmount, "cell 7 6,growx");
        jTextFieldCSTAmount.setTransferHandler(null);
        jTextFieldCSTAmount.setColumns(10);

        labelFinalAmount = new JLabel("Total");
        labelFinalAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelFinalAmount, "cell 5 7");

        labelFinalAmountTotal = new JLabel("0");
        panel_2.add(labelFinalAmountTotal, "cell 7 7");

        JPanel panel_3 = new JPanel();
        jMainPanel.add(panel_3, "cell 0 22 10 2,grow");
        panel_3.setLayout(new MigLayout("", "[0px:750px:750px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        buttonBack = new JButton("Back");
        buttonBack.setMnemonic('B');
        panel_3.add(buttonBack, "flowx,cell 0 0");

        buttonNew = new JButton("New");
        buttonNew.setMnemonic('N');
        panel_3.add(buttonNew, "cell 0 0");

        buttonPrint = new JButton("Print");
        buttonPrint.setMnemonic('P');
        panel_3.add(buttonPrint, "cell 0 0");

        buttonExport = new JButton("Export");
        buttonExport.setMnemonic('E');
        panel_3.add(buttonExport, "cell 0 0");

        buttonDelete = new JButton("Delete");
        buttonDelete.setMnemonic('T');
        panel_3.add(buttonDelete, "cell 0 0");

        buttonAddLedger = new JButton("Ledger");
        buttonAddLedger.setMnemonic('G');
        panel_3.add(buttonAddLedger, "cell 0 0");

        buttonAddItem = new JButton("Item");
        buttonAddItem.setMnemonic('I');
        panel_3.add(buttonAddItem, "cell 0 0");

        buttonSubmit = new JButton("Submit");
        buttonSubmit.setMinimumSize(new Dimension(100, 23));
        buttonSubmit.setMaximumSize(new Dimension(100, 23));
        buttonSubmit.setPreferredSize(new Dimension(100, 23));
        buttonSubmit.setMnemonic('S');
        panel_3.add(buttonSubmit, "cell 1 0");

        jTextFieldSearch = new JTextField();
        jMainPanel.add(jTextFieldSearch, "cell 11 0");
        jTextFieldSearch.setColumns(25);

        buttonPrevious_DOWN_Transactions = new JButton("<<");
        jMainPanel.add(buttonPrevious_DOWN_Transactions, "flowx,cell 11 21");

        buttonNext_DOWN_Transactions = new JButton(">>");
        jMainPanel.add(buttonNext_DOWN_Transactions, "cell 11 21");

        labelUnitSymbol = new JLabel("");
        labelUnitSymbol.setVisible(false);
        jMainPanel.add(labelUnitSymbol, "cell 11 0");

        initialiseActionListeners();
        tfDatePicker.setTransferHandler(null);
    }

    //Action Listeners
    private void initialiseActionListeners() {

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
            }
        });

        jTextFieldReceiptNo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTextFieldReceiptNoMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }
        });

        jTextFieldReceiptNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldReceiptNoFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldReceiptNoFocusLost(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldReceiptNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldReceiptNoKeyPressed(evt);
            }
        });

        jTextFieldReceiptNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldReceiptNo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jDateChooser.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker = (JTextField) jDateChooser.getComponent(1);

        tfDatePicker.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER) {
                    setFocus(e);
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    buttonBackActionPerformed(null);
                }
            }
        });

        tfDatePicker.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }
        });

        tfDatePicker.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDatePicker.selectAll();
                currentFocusValue = 1;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost-------------");
                if (!tfDatePicker.getText().equalsIgnoreCase("")) {
                    tfDateISS.setText(tfDatePicker.getText().trim());
                    tfDateRem.setText(tfDatePicker.getText().trim());
                }
            }
        });

        // remove on date 3-06-2014
//        /*

        dateChooser_PO.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePOdate = (JTextField) dateChooser_PO.getComponent(1);

        tfDatePOdate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER) {
                    setFocus(e);
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    setFocus(e);
                }
            }
        });

        tfDatePOdate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }
        });

        tfDatePOdate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDatePOdate.selectAll();
                currentFocusValue = 10;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost-------------");
                if (!tfDatePOdate.getText().equalsIgnoreCase("")) {
                }
            }
        });


        dateChooser_OCDate.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDateOCDate = (JTextField) dateChooser_OCDate.getComponent(1);

        tfDateOCDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER) {
                    setFocus(e);
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    setFocus(e);
                }
            }
        });

        tfDateOCDate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }
        });

        tfDateOCDate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDateOCDate.selectAll();
                currentFocusValue = 12;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost-------------");
                if (!tfDateOCDate.getText().equalsIgnoreCase("")) {
                }
            }
        });


//         */
// remove on date 3-06-2014


        dateChooser_Date_Iss.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDateISS = (JTextField) dateChooser_Date_Iss.getComponent(1);

        tfDateISS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER) {
                    setFocus(e);
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    setFocus(e);
                }
            }
        });

        tfDateISS.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }
        });

        tfDateISS.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDateISS.selectAll();
                currentFocusValue = 21;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost-------------");
                if (!tfDateISS.getText().equalsIgnoreCase("")) {
                }
            }
        });

        dateChooser_Date_Rem.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDateRem = (JTextField) dateChooser_Date_Rem.getComponent(1);

        tfDateRem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int code = e.getKeyCode();

                if (code == KeyEvent.VK_ENTER) {
                    setFocus(e);
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    setFocus(e);
                }
            }
        });

        tfDateRem.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }
        });

        tfDateRem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tfDateRem.selectAll();
                currentFocusValue = 22;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost-------------");
                if (!tfDateISS.getText().equalsIgnoreCase("")) {
                }
            }
        });

        jComboBoxAccount.setEditable(true);
        tfAccountText = (JTextField) jComboBoxAccount.getEditor().getEditorComponent();
        tfAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfAccountText.getText();
                        if (text.length() == 0) {
                            jComboBoxAccount.hidePopup();
                            setAccountLedgetModel(new DefaultComboBoxModel(cashLedgerVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(cashLedgerVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxAccount.hidePopup();
                                hide_flag = false;
                            } else {
                                setAccountLedgetModel(m, text);
                                jComboBoxAccount.showPopup();
                            }

                        }
                    }
                });

            }
        });
        tfAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            setFocus(event);
                        }
                    }
                });
            }
        });

        tfAccountText = (JTextField) jComboBoxAccount.getEditor().getEditorComponent();
        tfAccountText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 2;
                String text = tfAccountText.getText();
                if (text.length() == 0) {
                    jComboBoxAccount.hidePopup();
                    setAccountLedgetModel(new DefaultComboBoxModel(cashLedgerVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(cashLedgerVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxAccount.hidePopup();
                        hide_flag = false;
                    } else {
                        setAccountLedgetModel(m, text);
                        jComboBoxAccount.showPopup();
                    }

                }
                tfAccountText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // sale account is selected and dont contain selected Ledger
                if (!tfAccountText.getText().toString().trim().isEmpty() && accountLedgerMap != null && accountLedgerMap.containsKey(tfAccountText.getText().toString().trim().toLowerCase())) {
                    try {
                        System.out.println("Yes you got it.................");
                        List<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();
                        Set<String> ledgerName_List = new HashSet<String>();
                        ledgerName_List.add(tfAccountText.getText().toString().trim());
                        ledgerDTOList = gen.account.ledger.LedgerDAO.getLedgerList(ledgerName_List, Constants.LEDGER_NAME);
                        for (LedgerDTO ledgerDTO : ledgerDTOList) {
                            jtextArea_Buyer_Address.setText(ledgerDTO.getLedger_Address());
                            jtextField_Buyer_Contno.setText(ledgerDTO.getLedger_ContactNo());
                            jtextField_CSENo.setText(ledgerDTO.getLedger_ECSNumber());
                            jtextField_CSTNo.setText(ledgerDTO.getLedger_CSTNumber());
                            jtextField_CVATNo.setText(ledgerDTO.getLedger_CVATNumber());

//                            // call transactions in which selected ledger is present
                            String ledger_Id = ledgerDTO.getLedgerID();
                            saleDTOTransactionList = SaleDAO.getTransactionList(ledger_Id);
                            bindDTOToTransactionTable(saleDTOTransactionList);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        jtextArea_Buyer_Address.setText("");
                        jtextField_Buyer_Contno.setText("");
                        jtextField_CSENo.setText("");
                        jtextField_CSTNo.setText("");
                        jtextField_CVATNo.setText("");

                        saleDTOTransactionList = SaleDAO.getTransactionList("");
                        bindDTOToTransactionTable(saleDTOTransactionList);


                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        jComboBoxSaleLedger.setEditable(true);
        tfSaleAccountText = (JTextField) jComboBoxSaleLedger.getEditor().getEditorComponent();
        tfSaleAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfSaleAccountText.getText();
                        if (text.length() == 0) {
                            jComboBoxSaleLedger.hidePopup();
                            setSaleLedgetModel(new DefaultComboBoxModel(saleLedgerVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(saleLedgerVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxSaleLedger.hidePopup();
                                hide_flag = false;
                            } else {
                                setSaleLedgetModel(m, text);
                                jComboBoxSaleLedger.showPopup();
                            }

                        }
                    }
                });

            }
        });

        tfSaleAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            setFocus(event);
                        }
                    }
                });
            }
        });

        tfSaleAccountText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 5;
                String text = tfSaleAccountText.getText();
                if (text.length() == 0) {
                    jComboBoxSaleLedger.hidePopup();
                    setSaleLedgetModel(new DefaultComboBoxModel(saleLedgerVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(saleLedgerVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxSaleLedger.hidePopup();
                        hide_flag = false;
                    } else {
                        setSaleLedgetModel(m, text);
                        jComboBoxSaleLedger.showPopup();
                    }

                }
                tfSaleAccountText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        jComboBoxParti.setEditable(true);
        tfStockItemText = (JTextField) jComboBoxParti.getEditor().getEditorComponent();
        tfStockItemText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfStockItemText.getText();
                        if (text.length() == 0) {
                            jComboBoxParti.hidePopup();
                            setPartiModel(new DefaultComboBoxModel(stockItemVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(stockItemVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxParti.hidePopup();
                                hide_flag = false;
                            } else {
                                setPartiModel(m, text);
                                jComboBoxParti.showPopup();
                            }
                        }
                        int code = e.getKeyCode();
                        if (code == KeyEvent.VK_ENTER) {
                            if (!stockItemVector.contains(text)) {
                                stockItemVector.addElement(text);
                                Collections.sort(stockItemVector);
                                setPartiModel(Util.getSuggestedModel(stockItemVector, text), text);
                            }
                            hide_flag = true;

                        } else if (code == KeyEvent.VK_ESCAPE) {
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            for (int i = 0; i < stockItemVector.size(); i++) {
                                String str = stockItemVector.elementAt(i);
                                if (str.startsWith(text)) {
                                    jComboBoxParti.setSelectedIndex(-1);
                                    tfStockItemText.setText(str);
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        });

        tfStockItemText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (tfStockItemText.getText().trim().equalsIgnoreCase("")) {
                                    currentFocusValue = 19;
                                } else {
                                    currentFocusValue = 13;
                                }
                            }
                            setFocus(event);
                        }
                    }
                });
            }
        });

        tfStockItemText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 13;
                String text = tfStockItemText.getText();
                if (text.length() == 0) {
                    jComboBoxParti.hidePopup();
                    setPartiModel(new DefaultComboBoxModel(stockItemVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(stockItemVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxParti.hidePopup();
                        hide_flag = false;
                    } else {
                        setPartiModel(m, text);
                        jComboBoxParti.showPopup();
                    }

                }
                tfStockItemText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    // no need of zero if stock item is empty
                    if (!tfStockItemText.getText().toString().trim().equalsIgnoreCase("")) {
                        // if stockitem selected by mouse and jump  on amount buy mouse 
                        // so quantity should be 0 
                        if (jTextFieldQuantity.getText().toString().trim().equalsIgnoreCase("") && jTextFieldQuantity.getText().toString().trim() != null) {
                            jTextFieldQuantity.setText(Double.toString(0D));
//			}
                        } else if (Double.parseDouble(jTextFieldQuantity.getText().toString().trim()) == 0D) {
                            // if we use this condition above then it give number format ecxeption when empty
                            jTextFieldQuantity.setText(Double.toString(0D));
                        }
                    }
                    if (!tfStockItemText.getText().trim().equalsIgnoreCase("")) {
                        setStockItemProperties();
                    }
                    setStockItemRate();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        tfStockItemText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
        });


//        jcomboBoxColor.setEditable(true);
//        tfColorText = (JTextField) jcomboBoxColor.getEditor().getEditorComponent();
//        tfColorText.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(final KeyEvent e) {
//                EventQueue.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        String text = tfColorText.getText();
//                        if (text.length() == 0) {
//                            jcomboBoxColor.hidePopup();
//                            setColorModel(new DefaultComboBoxModel(colorVector), "");
//                        } else {
//                            DefaultComboBoxModel m = Util.getSuggestedModel(colorVector, text);
//                            if (m.getSize() == 0 || hide_flag) {
//                                jcomboBoxColor.hidePopup();
//                                hide_flag = false;
//                            } else {
//                                setColorModel(m, text);
//                                jcomboBoxColor.showPopup();
//                            }
//                        }
//                        int code = e.getKeyCode();
//                        if (code == KeyEvent.VK_ENTER) {
//                            if (!colorVector.contains(text)) {
//                                colorVector.addElement(text);
//                                Collections.sort(colorVector);
//                                setColorModel(Util.getSuggestedModel(colorVector, text), text);
//                            }
//                            hide_flag = true;
//
//                        } else if (code == KeyEvent.VK_ESCAPE) {
//                            hide_flag = true;
//                        } else if (code == KeyEvent.VK_RIGHT) {
//                            for (int i = 0; i < colorVector.size(); i++) {
//                                String str = colorVector.elementAt(i);
//                                if (str.startsWith(text)) {
//                                    jcomboBoxColor.setSelectedIndex(-1);
//                                    tfColorText.setText(str);
//                                    return;
//                                }
//                            }
//                        }
//                    }
//                });
//            }
//        });
//
//        tfColorText.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(final KeyEvent event) {
//                EventQueue.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
////                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
////                                if (tfColorText.getText().trim().equalsIgnoreCase("")) {
////                                    currentFocusValue = 7;
////                                } else {
////                                    currentFocusValue = 4;
////                                }
////                            }
//                            setFocus(event);
//                        }
//                    }
//                });
//            }
//        });
//
//        tfColorText.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                currentFocusValue = 15;
//                String text = tfColorText.getText();
//                if (text.length() == 0) {
//                    jcomboBoxColor.hidePopup();
//                    setColorModel(new DefaultComboBoxModel(colorVector), "");
//                } else {
//                    DefaultComboBoxModel m = Util.getSuggestedModel(colorVector, text);
//                    if (m.getSize() == 0 || hide_flag) {
//                        jcomboBoxColor.hidePopup();
//                        hide_flag = false;
//                    } else {
//                        setColorModel(m, text);
//                        jcomboBoxColor.showPopup();
//                    }
//
//                }
//                tfColorText.selectAll();
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                try {
//                    // no need of zero if stock item is empty
//                    if (!tfColorText.getText().toString().trim().equalsIgnoreCase("")) {
//                        // if stockitem selected by mouse and jump  on amount buy mouse 
//                        // so quantity should be 0 
//                        if (jTextFieldQuantity.getText().toString().trim().equalsIgnoreCase("") && jTextFieldQuantity.getText().toString().trim() != null) {
//                            jTextFieldQuantity.setText(Double.toString(0D));
////			}
//                        } else if (Double.parseDouble(jTextFieldQuantity.getText().toString().trim()) == 0D) {
//                            // if we use this condition above then it give number format ecxeption when empty
//                            jTextFieldQuantity.setText(Double.toString(0D));
//                        }
//                    }
//                    if (!tfColorText.getText().trim().equalsIgnoreCase("")) {
//                        setStockItemProperties();
//                    }
//                    setStockItemRate();
//
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//        });
//
//        tfColorText.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//            }
//        });


        jcomboBoxUnit.setEditable(true);
        tfUnitText = (JTextField) jcomboBoxUnit.getEditor().getEditorComponent();
        tfUnitText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfUnitText.getText();
                        if (text.length() == 0) {
                            jcomboBoxUnit.hidePopup();
                            setUnitModel(new DefaultComboBoxModel(unitVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(unitVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jcomboBoxUnit.hidePopup();
                                hide_flag = false;
                            } else {
                                setUnitModel(m, text);
                                jcomboBoxUnit.showPopup();
                            }
                        }
                        int code = e.getKeyCode();
                        if (code == KeyEvent.VK_ENTER) {
                            if (!unitVector.contains(text)) {
                                unitVector.addElement(text);
                                Collections.sort(unitVector);
                                setUnitModel(Util.getSuggestedModel(unitVector, text), text);
                            }
                            hide_flag = true;

                        } else if (code == KeyEvent.VK_ESCAPE) {
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            for (int i = 0; i < unitVector.size(); i++) {
                                String str = unitVector.elementAt(i);
                                if (str.startsWith(text)) {
                                    jcomboBoxUnit.setSelectedIndex(-1);
                                    tfUnitText.setText(str);
                                    return;
                                }
                            }
                        }
                    }
                });
            }
        });

        tfUnitText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
//                                if (tfColorText.getText().trim().equalsIgnoreCase("")) {
//                                    currentFocusValue = 7;
//                                } else {
//                                    currentFocusValue = 4;
//                                }
//                            }
                            setFocus(event);
                        }
                    }
                });
            }
        });

        tfUnitText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 16;
                String text = tfUnitText.getText();
                if (text.length() == 0) {
                    jcomboBoxUnit.hidePopup();
                    setUnitModel(new DefaultComboBoxModel(unitVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(unitVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jcomboBoxUnit.hidePopup();
                        hide_flag = false;
                    } else {
                        setUnitModel(m, text);
                        jcomboBoxUnit.showPopup();
                    }

                }
                tfUnitText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    // no need of zero if stock item is empty
                    if (!tfUnitText.getText().toString().trim().equalsIgnoreCase("")) {
                        // if stockitem selected by mouse and jump  on amount buy mouse 
                        // so quantity should be 0 
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        tfUnitText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
        });

/////////////////////////////////////////////////////////////////////
        jtextArea_Buyer_Address.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextField_Buyer_AddressFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextArea_Buyer_Address.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_Buyer_AddressKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
//                    Util.filterCharacter(evt, jtextField_Buyer_Address);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jtextField_Buyer_Contno.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_Buyer_ContnoFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextField_Buyer_Contno.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_Buyer_ContnoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_Buyer_Contno);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jtextField_CSENo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_CSENoFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextField_CSENo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_CSENoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    //Util.filterCharacter(evt, jtextField_CSENo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jtextField_CVATNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_CVATNoFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextField_CVATNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_CVATNoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    //Util.filterCharacter(evt, jtextField_CSENo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jtextField_CSTNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_CSTNoFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextField_CSTNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_CSTNoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    //Util.filterCharacter(evt, jtextField_CSENo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jtextField_PONo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_PONoFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextField_PONo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_PONoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    //Util.filterCharacter(evt, jtextField_CSENo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

// remove on 5-06-2014
//        jtextField_PODate.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                try {
//                    jtextField_PODateFocusGained(evt);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                //jTextField_Buyer_AddressFocusLost(evt);
//            }
//        });
//
//        jtextField_PODate.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jtextField_PODateKeyPressed(evt);
//            }
//
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//                try {
//                    //Util.filterCharacter(evt, jtextField_CSENo);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                //calculateSquareFeet();
//                try {
//                    // calculateQuantity();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//        });



        jtextField_OCNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_OCNoFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextField_Buyer_AddressFocusLost(evt);
            }
        });

        jtextField_OCNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_OCNoKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    //Util.filterCharacter(evt, jtextField_CSENo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

// remove on 5-06-2014
//        jtextField_OCDate.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                try {
//                    jtextField_OCDateFocusGained(evt);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                //jTextField_Buyer_AddressFocusLost(evt);
//            }
//        });
//
//        jtextField_OCDate.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jtextField_OCDateKeyPressed(evt);
//            }
//
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//                try {
//                    //Util.filterCharacter(evt, jtextField_CSENo);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                //calculateSquareFeet();
//                try {
//                    // calculateQuantity();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                }
//            }
//        });



        jtextField_Thk.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_ThkFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_ThkFocusLost(evt);
            }
        });

        jtextField_Thk.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_ThkKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_Thk);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextField_SizeA.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextField_SizeAFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_SizeAFocusLost(evt);
            }
        });

        jTextField_SizeA.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_SizeAKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextField_SizeA);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextField_SizeB.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextField_SizeBFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_SizeBFocusLost(evt);
            }
        });

        jTextField_SizeB.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_SizeBKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextField_SizeB);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
//                    calculatePartiAmount();
                    calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jTextField_Package.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextField_PackageFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_PackageFocusLost(evt);
            }
        });

        jTextField_Package.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_PackageKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    //Util.filterCharacter(evt, jTextField_Package);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    //calculatePartiAmount();
                    //calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jTextField_Nos.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextField_NosFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_NosFocusLost(evt);
            }
        });

        jTextField_Nos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_NosKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextField_Nos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    //calculatePartiAmount();
                    calculateQuantity();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldQuantityFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldQuantityFocusLost(evt);
            }
        });

        jTextFieldQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantityKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldQuantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    calculatePartiAmount();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldRate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldRateFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldRateFocusLost(evt);
            }
        });

        jTextFieldRate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldRateKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldRate);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculatePartiAmount();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldAmountFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAmountFocusLost(evt);
                if (!jTextFieldAmount.getText().equalsIgnoreCase("")) {
                }
            }
        });

        jTextFieldAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldAmountKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldAmount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculatePartiRate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                    if (ex.getMessage().equalsIgnoreCase("Quantity Field Exceeding 20 digit Limit")) {
//                        jTextFieldQuantity.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Quantity only valid upto 1 Trillion")) {
//                        jTextFieldQuantity.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Rate Field Exceeding 20 digit Limit")) {
//                        jTextFieldRate.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Rate only valid upto 1 Trillion")) {
//                        jTextFieldRate.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Amount only valid upto 1 Trillion")) {
//                        jTextFieldAmount.requestFocus();
//                    }
                }
            }
        });

        buttonAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonAddKeyPressed(evt);
            }
        });

        buttonParticularDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonParticularDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        tableParti.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePartiMouseClicked(evt);
            }
        });

        tableParti.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablePartiKeyPressed(evt);
            }
        });

        jTableTransactionList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableTransactionListMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
                showPopup(me);
            }

            public void mouseReleased(MouseEvent me) {
                showPopup(me);
            }
        });

        jTableTransactionList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        jTableTransactionListMouseClicked(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                    }
                }
            }
        });

        jTextFieldNote.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNoteFocusGained(evt);
            }
        });

        jTextFieldNote.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNoteKeyPressed(evt);
            }
        });


        jTextFieldDispatchDocNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDispatchDocNoFocusGained(evt);
            }
        });

        jTextFieldDispatchDocNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDispatchDocNoKeyPressed(evt);
            }
        });

        jTextFieldDispatchDocThrough.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDispatchDocThroughFocusGained(evt);
            }
        });

        jTextFieldDispatchDocThrough.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldDispatchDocThroughKeyPressed(evt);
            }
        });

        jtextField_Time_Iss.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_Time_IssFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextFieldAmountFocusLost(evt);
            }
        });

        jtextField_Time_Iss.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jtextField_Time_IssKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_Time_Iss);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    // calculatePartiRate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jtextField_Time_Rem.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_Time_RemFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    //JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                //jTextFieldAmountFocusLost(evt);
            }
        });

        jtextField_Time_Rem.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jtextField_Time_RemKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_Time_Rem);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    // calculatePartiRate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jtextField_ExDutyPer.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_ExDutyPerFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_ExDutyPerFocusLost(evt);
            }
        });

        jtextField_ExDutyPer.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_ExDutyPerKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_ExDutyPer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    //calculatePartiAmount();
                    System.out.println(" jtextField_ExDutyPer  Release ------- ");
                    calculateAndBindExciseDutyRate();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jtextField_ExDutyAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_ExDutyAmtFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_ExDutyAmtFocusLost(evt);
            }
        });

        jtextField_ExDutyAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_ExDutyAmtKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_ExDutyAmt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    calculateAndBindExciseDutyAmount();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });
        //
        // 
        jtextField_EdCessPer.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_EdCessPerFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_EdCessPerFocusLost(evt);
            }
        });

        jtextField_EdCessPer.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_EdCessPerKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_EdCessPer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    calculateAndBind_EdCessRate();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jtextField_EdCessAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_EdCessAmtFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_EdCessAmtFocusLost(evt);
            }
        });

        jtextField_EdCessAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_EdCessAmtKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_EdCessAmt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBind_EdCessAmount();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });
        ///
        //
        jtextField_H_EdCessPer.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_H_EdCessPerFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_H_EdCessPerFocusLost(evt);
            }
        });

        jtextField_H_EdCessPer.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_H_EdCessPerKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_H_EdCessPer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculatePartiAmount();
                    calculateAndBind_H_EdCessRate();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jtextField_H_EdCessAmt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_H_EdCessAmtFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_H_EdCessAmtFocusLost(evt);
            }
        });

        jtextField_H_EdCessAmt.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_H_EdCessAmtKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_H_EdCessAmt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculatePartiAmount();
                    calculateAndBind_H_EdCessAmount();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jtextField_Total_WithoutVAT.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_Total_WithoutVATFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextField_Total_WithoutVATFocusLost(evt);
            }
        });

        jtextField_Total_WithoutVAT.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextField_Total_WithoutVATKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextField_Total_WithoutVAT);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculatePartiAmount();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });
        //
        //
/////////////////////////        /////////////////////////////

        jTextFieldVatRate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldVatRateFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldVatRateFocusLost(evt);
            }
        });

        jTextFieldVatRate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldVatRateKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldVatRate);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindVatRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldVatAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldVatAmountFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldVatAmountFocusLost(evt);
            }
        });

        jTextFieldVatAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldVatAmountKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldVatAmount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindVatAmount();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldCSTRate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldCSTRateFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCSTRateFocusLost(evt);
            }
        });

        jTextFieldCSTRate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCSTRateKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldCSTRate);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });


        jTextFieldCSTAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldCSTAmountFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCSTAmountFocusLost(evt);
            }
        });

        jTextFieldCSTAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCSTAmountKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldCSTAmount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindCSTAmount();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldSearchKeyReleased(evt);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        });

        buttonAddLedger.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddLedgerActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonAddLedger.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (buttonDelete.isEnabled()) {
                        buttonDelete.requestFocus();
                    } else {
                        buttonNew.requestFocus();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonAddItem.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonAddItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddItemActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonAddItem.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonAddLedger.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonSubmit.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonBackActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonBack.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
//                    if (ex.getMessage().equalsIgnoreCase("Note Data Exceeding 1000 Character Limit")) {
//                        jTextFieldNote.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Dispatch Document Number Data Exceeding 40 Character Limit")) {
//                        jTextFieldDispatchDocNo.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Vehicle Number Exceeding 40 Character Limit")) {
//                        jTextFieldDispatchDocThrough.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("Amount only valid upto 1 Trillion")) {
//                        jTextFieldAmount.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("VAT Rate Exceeding 100 Digit Limit")) {
//                        jTextFieldVatRate.requestFocus();
//                    } else if (ex.getMessage().equalsIgnoreCase("VAT Amount only valid upto 1 Trillion")) {
//                        jTextFieldVatAmount.requestFocus();
//                    }
                }
            }
        });

        buttonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonAddItem.requestFocus();
                }
            }
        });

        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonDelete.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonExport.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonAddLedger.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonPrintActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonPrint.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonExport.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonExportActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonExport.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonPrint.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (buttonDelete.isEnabled()) {
                        buttonDelete.requestFocus();
                    } else {
                        buttonAddLedger.requestFocus();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNewActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
                }
            }
        });

        buttonNew.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonBack.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (buttonPrint.isEnabled()) {
                        buttonPrint.requestFocus();
                    } else {
                        buttonAddLedger.requestFocus();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonNext_DOWN_Transactions.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextTransactionsActionPerformed(evt);
            }
        });

        buttonNext_DOWN_Transactions.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
            }
        });

        buttonPrevious_DOWN_Transactions.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPreviousTransactionsActionPerformed(evt);
            }
        });

        buttonPrevious_DOWN_Transactions.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
            }
        });

    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws Exception {

        if (this.ledgerTimeStamp != Constants.LEDGER_TIME_STAMP) {
            initLedger();
        }
        if (this.stockItemTimeStamp != Constants.STOCK_ITEM_TIME_STAMP) {
            initStockItem();
            initColor();
        }

        setFocus(null);

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
        MainClass.setstaticvar();
        //this.dispose();
    }

    private void jTextFieldReceiptNoFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 0;
    }

    private void jTextFieldReceiptNoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 17;
        Util.checkForZero(jTextFieldQuantity);
        jTextFieldQuantity.selectAll();
    }

    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldQuantity);
    }

    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextField_Buyer_AddressFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 3;
//        Util.checkForZero(jtextField_Buyer_Address);
//        jtextArea_Buyer_Address.selectAll();
        jtextArea_Buyer_Address.setText(jtextArea_Buyer_Address.getText().trim());
    }

    private void jTextField_Buyer_AddressKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_Buyer_ContnoFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 4;
        Util.checkForZero(jtextField_Buyer_Contno);
        jtextField_Buyer_Contno.selectAll();
    }

    private void jtextField_Buyer_ContnoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_CSENoFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 6;
//        Util.checkForZero(jtextField_Buyer_Address);
        jtextField_CSENo.selectAll();
    }

    private void jtextField_CSENoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_CVATNoFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 7;
//        Util.checkForZero(jtextField_Buyer_Address);
        jtextField_CSENo.selectAll();
    }

    private void jtextField_CVATNoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_CSTNoFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 8;
//        Util.checkForZero(jtextField_Buyer_Address);
        jtextField_CSENo.selectAll();
    }

    private void jtextField_CSTNoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_PONoFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 9;
//        Util.checkForZero(jtextField_Buyer_Address);
        jtextField_PONo.selectAll();
    }

    private void jtextField_PONoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_PODateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 10;
//        Util.checkForZero(jtextField_Buyer_Address);
        tfDatePOdate.selectAll();
    }

    private void jtextField_PODateKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_OCNoFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 11;
//        Util.checkForZero(jtextField_Buyer_Address);
        jtextField_OCNo.selectAll();
    }

    private void jtextField_OCNoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_OCDateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 12;
//        Util.checkForZero(jtextField_Buyer_Address);
        tfDateOCDate.selectAll();
    }

    private void jtextField_OCDateKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jtextField_ThkFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
//        currentFocusValue = 14;
        Util.checkForZero(jtextField_Thk);
        jtextField_Thk.selectAll();
    }

    private void jtextField_ThkFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_Thk);
    }

    private void jtextField_ThkKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextField_SizeAFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
//        currentFocusValue = 16;
        Util.checkForZero(jTextField_SizeA);
        jTextField_SizeA.selectAll();
    }

    private void jTextField_SizeAFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextField_SizeA);
    }

    private void jTextField_SizeAKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextField_SizeBFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
//        currentFocusValue = 17;
        Util.checkForZero(jTextField_SizeB);
        jTextField_SizeB.selectAll();
    }

    private void jTextField_SizeBFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextField_SizeB);
    }

    private void jTextField_SizeBKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextField_PackageFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 14;
        // Util.checkForZero(jTextField_Package);
        jTextField_Package.selectAll();
    }

    private void jTextField_PackageFocusLost(java.awt.event.FocusEvent evt) {
        // Util.checkForEmpty(jTextField_Package);
    }

    private void jTextField_PackageKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextField_NosFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 15;
        Util.checkForZero(jTextField_Nos);
        jTextField_Nos.selectAll();
    }

    private void jTextField_NosFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextField_Nos);
    }

    private void jTextField_NosKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

//    private void jTextField_UnitFocusGained(java.awt.event.FocusEvent evt) throws Exception {
////        currentFocusValue = 5;
//        currentFocusValue = 20;
//        Util.checkForZero(jTextField_Unit);
//        jTextField_Unit.selectAll();
//    }
    private void jtextField_Time_IssFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 25;
        Util.checkForZero(jtextField_Time_Iss);
        jtextField_Time_Iss.selectAll();
    }

    private void jtextField_Time_IssFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_Time_Iss);
    }

    private void jtextField_Time_IssKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextField_Time_RemFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 26;
        Util.checkForZero(jtextField_Time_Rem);
        jtextField_Time_Rem.selectAll();
    }

    private void jtextField_Time_RemFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_Time_Rem);
    }

    private void jtextField_Time_RemKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextField_ExDutyPerFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 27;
        Util.checkForZero(jtextField_ExDutyPer);
        jtextField_ExDutyPer.selectAll();
    }

    private void jtextField_ExDutyPerFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_ExDutyPer);
    }

    private void jtextField_ExDutyPerKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jtextField_ExDutyAmtFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 28;
        Util.checkForZero(jtextField_ExDutyAmt);
        jtextField_ExDutyAmt.selectAll();
    }

    private void jtextField_ExDutyAmtFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_ExDutyAmt);
    }

    private void jtextField_ExDutyAmtKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jtextField_EdCessPerFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 29;
        Util.checkForZero(jtextField_EdCessPer);
        jtextField_EdCessPer.selectAll();
    }

    private void jtextField_EdCessPerFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_EdCessPer);
    }

    private void jtextField_EdCessPerKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jtextField_EdCessAmtFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 30;
        Util.checkForZero(jtextField_EdCessAmt);
        jtextField_EdCessAmt.selectAll();
    }

    private void jtextField_EdCessAmtFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_EdCessAmt);
    }

    private void jtextField_EdCessAmtKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jtextField_H_EdCessPerFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 31;
        Util.checkForZero(jtextField_H_EdCessPer);
        jtextField_H_EdCessPer.selectAll();
    }

    private void jtextField_H_EdCessPerFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_H_EdCessPer);
    }

    private void jtextField_H_EdCessPerKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jtextField_H_EdCessAmtFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 32;
        Util.checkForZero(jtextField_H_EdCessAmt);
        jtextField_H_EdCessAmt.selectAll();
    }

    private void jtextField_H_EdCessAmtFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_H_EdCessAmt);
    }

    private void jtextField_H_EdCessAmtKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jtextField_Total_WithoutVATFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 33;
        Util.checkForZero(jtextField_Total_WithoutVAT);
        jtextField_Total_WithoutVAT.selectAll();
    }

    private void jtextField_Total_WithoutVATFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextField_Total_WithoutVAT);
    }

    private void jtextField_Total_WithoutVATKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextFieldRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 18;
        Util.checkForZero(jTextFieldRate);
        jTextFieldRate.selectAll();
    }

    private void jTextFieldRateFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldRate);
    }

    private void jTextFieldRateKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextFieldReceiptNoFocusLost(java.awt.event.FocusEvent evt) throws Exception {
        if (!isEdit) {
            if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                if (jTextFieldReceiptNo.getText().toString().trim().length() < 8) {
                    if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabiltyByDate(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.SALE_TYPE_INDEX, jDateChooser.getDate())) {
                        jTextFieldReceiptNo.setText("");
                    }
                } else {
                    jTextFieldReceiptNo.requestFocus();
                    jTextFieldReceiptNo.setText("");
                    throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
                }
            }

            if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                jTextFieldReceiptNo.setText(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.SALE_TYPE_INDEX, jDateChooser.getDate()) + "");
            }
        }
    }

    private void jTextFieldAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 19;
        Util.checkForZero(jTextFieldAmount);
        jTextFieldAmount.selectAll();
    }

    private void jTextFieldAmountFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldAmount);
    }

    private void jTextFieldAmountKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                partiAdd();
            }
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        partiAdd();
    }

    private void buttonParticularDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (tableParti.isRowSelected(indexForDeletion)) {
            buttonParticularDelete.setVisible(true);
        }
        partiDelete();
    }

    private void partiAdd() throws Exception {
        if (validateStockTransaction()) {

            int entryCount = tableParti.getRowCount();
            if (entryCount < 15) {
                addTOJTable();
            } else {

                JOptionPane.showMessageDialog(SaleForm.this, "Max 15 entries allowed per transaction");

            }
            System.out.println("ROW COUNT IN TABLE PARTICULARS------->>>>>>" + tableParti.getRowCount());

            clearFormData();

            Util.checkForEmpty(jTextFieldQuantity);
            Util.checkForEmpty(jTextFieldSquareFeet);
            Util.checkForEmpty(jTextFieldRate);
            Util.checkForEmpty(jTextFieldAmount);

            calculateAndBindTotalTOGUI();
            calculateAndBindExciseDutyRate();
            calculateAndBind_EdCessRate();
            calculateAndBind_H_EdCessRate();
            calculateAndBind_Total_Without_VAT_TOGUI();
            calculateAndBindVatRate();
            calculateAndBindCSTRate();
            calculateAndBindFinalTotalTOGUI();

        }
    }

    private void partiDelete() throws Exception {
        int j = 0;
        int flag = 0;
        indexForDeletion = tableParti.getSelectedRow();
        if (tableParti.getRowCount() > 0) {
            j = 0;
            while (j < tableParti.getRowCount()) {
                if (tableParti.getValueAt(j, 1) == null) {
                    if (indexForDeletion == j) {
                        flag = 1;
                    }
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);
                }
                j++;
            }
        }
        if (flag == 0) {
            if (indexForDeletion == tableParti.getRowCount() - 1) {
                DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                tableModel.setRowCount(tableParti.getRowCount() - 1);
                if (tableParti.getRowCount() == 0) {
                    tfStockItemText.requestFocus();
                }
            } else {
                j = indexForDeletion + 1;
                if (tableParti.getRowCount() > 0) {
                    while (j < tableParti.getRowCount()) {
                        tableParti.setValueAt(tableParti.getValueAt(j, 1), j - 1, 1);
                        tableParti.setValueAt(tableParti.getValueAt(j, 2), j - 1, 2);
                        tableParti.setValueAt(tableParti.getValueAt(j, 3), j - 1, 3);
                        tableParti.setValueAt(tableParti.getValueAt(j, 4), j - 1, 4);
                        tableParti.setValueAt(tableParti.getValueAt(j, 5), j - 1, 5);
                        j++;
                    }
                }
                DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                tableModel.setRowCount(tableParti.getRowCount() - 1);

            }
        }
//        calculateAndBindTotalTOGUI();
//        calculateAndBindVatRate();
//        calculateAndBindVatAmount();
//        calculateAndBindFinalTotalTOGUI();
        calculateAndBindTotalTOGUI();
        calculateAndBindExciseDutyRate();
        calculateAndBindExciseDutyAmount();
        calculateAndBind_EdCessRate();
        calculateAndBind_EdCessAmount();
        calculateAndBind_H_EdCessRate();
        calculateAndBind_H_EdCessAmount();
        calculateAndBind_Total_Without_VAT_TOGUI();
        calculateAndBindVatRate();
        calculateAndBindVatAmount();
        calculateAndBindCSTRate();
        calculateAndBindCSTAmount();
        calculateAndBindFinalTotalTOGUI();
        clearFormData();
        buttonAdd.setLabel(Label.BUTTON_ADD);
        temp = 1000;
        tableParti.clearSelection();
        tfStockItemText.requestFocus();
    }

    private void buttonAddKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldAmount.requestFocus();
        }
    }

    private void tablePartiMouseClicked(java.awt.event.MouseEvent evt) {
        if (tableParti.getRowCount() > 0) {
            indexForDeletion = tableParti.getSelectedRow();
            if (indexForDeletion > -1) {
                Object o = tableParti.getValueAt(indexForDeletion, 0);
                if (o != null) {
                    temp = tableParti.getSelectedRow();
                    tfStockItemText.setText(tableParti.getValueAt(temp, 1).toString());
                    jTextFieldQuantity.setText(tableParti.getValueAt(temp, 9).toString());
                    jTextFieldRate.setText(tableParti.getValueAt(temp, 10).toString());
                    tfUnitText.setText(tableParti.getValueAt(temp, 8).toString());
                    jTextFieldAmount.setText(tableParti.getValueAt(temp, 11).toString());
                    if (tableParti.getValueAt(temp, 3) != null && !tableParti.getValueAt(temp, 3).toString().trim().isEmpty()) {
                        jcomboBoxColor.setText(tableParti.getValueAt(temp, 3).toString());
                    } else {
                        jcomboBoxColor.setText("");
                    }
                    jTextField_Package.setText(tableParti.getValueAt(temp, 6).toString());
                    jTextField_Nos.setText(tableParti.getValueAt(temp, 7).toString());
                    jTextField_SizeA.setText(tableParti.getValueAt(temp, 4).toString());
                    jTextField_SizeB.setText(tableParti.getValueAt(temp, 5).toString());
                    jtextField_Thk.setText(tableParti.getValueAt(temp, 2).toString());
                    buttonAdd.setLabel(Label.BUTTON_ALTER);
                    jComboBoxParti.requestFocus();
                    buttonParticularDelete.setEnabled(true);
                }
            }
        }
    }

    private void tablePartiKeyPressed(java.awt.event.KeyEvent evt) {
        int flag = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            indexForDeletion = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                if (tableParti.getValueAt(indexForDeletion, 0) == null) {
                    jTextFieldNote.requestFocus();
                } else {
                    tablePartiMouseClicked(null);
                }
            } else {
                tfStockItemText.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfStockItemText.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int j = 0;
            flag = 0;
            indexForDeletion = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                j = 0;
                while (j < tableParti.getRowCount()) {
                    if (tableParti.getValueAt(j, 1) == null) {
                        if (indexForDeletion == j) {
                            flag = 1;
                        }
                        DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                        tableModel.setRowCount(tableParti.getRowCount() - 1);
                    }
                    j++;
                }
            }
            if (flag == 0) {
                if (indexForDeletion == tableParti.getRowCount() - 1) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);
                    if (tableParti.getRowCount() == 0) {
                        tfStockItemText.requestFocus();
                    }
                } else {
                    j = indexForDeletion + 1;
                    while (j < tableParti.getRowCount()) {
                        tableParti.setValueAt(tableParti.getValueAt(j, 1), j - 1, 1);
                        tableParti.setValueAt(tableParti.getValueAt(j, 2), j - 1, 2);
                        tableParti.setValueAt(tableParti.getValueAt(j, 3), j - 1, 3);
                        tableParti.setValueAt(tableParti.getValueAt(j, 4), j - 1, 4);
                        tableParti.setValueAt(tableParti.getValueAt(j, 5), j - 1, 5);
                        j++;
                    }
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);

                }
            }
            clearFormData();
            buttonAdd.setLabel(Label.BUTTON_ADD);
        }
    }

    private void jTableTransactionListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        String id = jTableTransactionList.getValueAt(jTableTransactionList.getSelectedRow(), 0).toString();
        String date = jTableTransactionList.getValueAt(jTableTransactionList.getSelectedRow(), 1).toString();
        System.err.println("date====" + date);
        Date dateEntity = (java.util.Date) Constants.DATE_FORMATER.parse(date.trim());
        loadEditForm(id, Constants.simpleDateFormatDatabaseWithDash.format( dateEntity ));
    }

    private void jTextFieldReceiptNoMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        jTextFieldReceiptNo.setEnabled(true);
    }

    private void jTextFieldNoteFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 20;
        jTextFieldNote.selectAll();
    }

    private void jTextFieldNoteKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldDispatchDocNoFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 23;
        jTextFieldDispatchDocNo.selectAll();
    }

    private void jTextFieldDispatchDocNoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldDispatchDocThroughFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 24;
        jTextFieldDispatchDocThrough.selectAll();
    }

    private void jTextFieldDispatchDocThroughKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldVatRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 34;
        Util.checkForZero(jTextFieldVatRate);
        jTextFieldVatRate.selectAll();
    }

    private void jTextFieldVatRateFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldVatRate);
        // make CST tax zero if user trying to add vat tax
        if (jTextFieldVatRate.getText().toString().trim() != null && jTextFieldCSTRate.getText().toString() != null
                && jTextFieldCSTAmount.getText().toString() != null) {
            Double cst_Rate = Double.parseDouble(jTextFieldCSTRate.getText().toString().trim());
            Double cst_Amount = Double.parseDouble(jTextFieldCSTAmount.getText().toString().trim());
            if ((Double.parseDouble(jTextFieldVatRate.getText().toString().trim())) > 0D && (cst_Rate > 0D || cst_Amount > 0D)) {

                jTextFieldCSTRate.setText("" + Double.parseDouble("0.00"));
                jTextFieldCSTAmount.setText("" + Double.parseDouble("0.00"));

            }
        }
    }

    private void jTextFieldVatRateKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("RRRRRRRRRRRRRRRRRRRRRR" + currentFocusValue);
            setFocus(evt);
        }
    }

    private void jTextFieldVatAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 35;
        Util.checkForZero(jTextFieldVatAmount);
        jTextFieldVatAmount.selectAll();
    }

    private void jTextFieldVatAmountFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldVatAmount);

        // make CST tax zero if user trying to add vat tax
        if (jTextFieldVatAmount.getText().toString().trim() != null && jTextFieldCSTRate.getText().toString() != null
                && jTextFieldCSTAmount.getText().toString() != null) {
            Double cst_Rate = Double.parseDouble(jTextFieldCSTRate.getText().toString().trim());
            Double cst_Amount = Double.parseDouble(jTextFieldCSTAmount.getText().toString().trim());
            if ((Double.parseDouble(jTextFieldVatAmount.getText().toString().trim())) > 0D && (cst_Rate > 0D || cst_Amount > 0D)) {

                jTextFieldCSTRate.setText("" + Double.parseDouble("0.00"));
                jTextFieldCSTAmount.setText("" + Double.parseDouble("0.00"));

            }
        }

    }

    private void jTextFieldVatAmountKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            buttonSubmit.requestFocus();
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jTextFieldCSTRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 36;
        Util.checkForZero(jTextFieldCSTRate);
        jTextFieldCSTRate.selectAll();
    }

    private void jTextFieldCSTRateFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldCSTRate);

        // make vat tax zero if user trying to add cst tax
        if (jTextFieldCSTRate.getText().toString().trim() != null && jTextFieldVatRate.getText().toString() != null
                && jTextFieldVatAmount.getText().toString() != null) {
            Double vat_Rate = Double.parseDouble(jTextFieldVatRate.getText().toString().trim());
            Double vat_Amount = Double.parseDouble(jTextFieldVatAmount.getText().toString().trim());
            if ((Double.parseDouble(jTextFieldCSTRate.getText().toString().trim())) > 0D && (vat_Rate > 0D || vat_Amount > 0D)) {

                jTextFieldVatRate.setText("" + Double.parseDouble("0.00"));
                jTextFieldVatAmount.setText("" + Double.parseDouble("0.00"));

            }
        }
    }

    private void jTextFieldCSTRateKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("RRRRRRRRRRRRRRRRRRRRRR" + currentFocusValue);
            setFocus(evt);
        }
    }

    private void jTextFieldCSTAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 37;
        Util.checkForZero(jTextFieldCSTAmount);
        jTextFieldCSTAmount.selectAll();
    }

    private void jTextFieldCSTAmountFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldCSTAmount);
        // make vat tax zero if user trying to add cst tax
        if (jTextFieldCSTAmount.getText().toString().trim() != null && jTextFieldVatRate.getText().toString() != null
                && jTextFieldVatAmount.getText().toString() != null) {
            Double vat_Rate = Double.parseDouble(jTextFieldVatRate.getText().toString().trim());
            Double vat_Amount = Double.parseDouble(jTextFieldVatAmount.getText().toString().trim());
            if ((Double.parseDouble(jTextFieldCSTAmount.getText().toString().trim())) > 0D && (vat_Rate > 0D || vat_Amount > 0D)) {

                jTextFieldVatRate.setText("" + Double.parseDouble("0.00"));
                jTextFieldVatAmount.setText("" + Double.parseDouble("0.00"));

            }
        }
    }

    private void jTextFieldCSTAmountKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buttonSubmit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }

    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jTableTransactionList.getRowCount() > 0) {
                jTableTransactionList.requestFocus();
                jTableTransactionList.changeSelection(0, 0, false, false);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableTransactionList.getRowCount() > 0) {
                jTableTransactionList.requestFocus();
                jTableTransactionList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException {

        List<SaleDTO> saleList = new ArrayList<SaleDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (SaleDTO saleDTOEntity : saleDTOTransactionList) {
            if (saleDTOEntity.getSaleLedger().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                saleList.add(saleDTOEntity);
            }
        }
        bindDTOToTransactionTable(saleList);

    }

    private void buttonAddLedgerActionPerformed(java.awt.event.ActionEvent evt) {

        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
//            LedgerForm f1 = new LedgerForm();
            gen.account.ledger.LedgerGUIForm f1 = new gen.account.ledger.LedgerGUIForm();
            desktopPane.add(f1);
            Dimension desktopSize = getDesktopPane().getSize();
            Dimension jInternalFrameSize = f1.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            f1.setLocation(width, height);
            f1.setVisible(true);
        }

    }

    private void buttonAddItemActionPerformed(java.awt.event.ActionEvent evt) {
        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
            gen.account.StockItemFormation.StockItemFormFinal form = new gen.account.StockItemFormation.StockItemFormFinal();
            desktopPane.add(form);
            Dimension desktopSize = getDesktopPane().getSize();
            Dimension jInternalFrameSize = form.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            form.setLocation(width, height);
            form.setVisible(true);
        }


    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {
        try {
//            getDesktopPane().setLayout(new MigLayout("center panel",
//                    "[100px:100px:1366px,grow,shrink 50,center]",
//                    "[100px:100px:768px,grow,shrink 50,center]"));
            getDesktopPane().setLayout(new CardLayout());
            MainClass.setstaticvar();
            MainClass m = new MainClass();
            m.menuselection(4);
            SaleForm.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            // this ledgerList is use to send ledgerList for creationg Ledger 
            ArrayList<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();

            // finding all groups
            Map<String, String> group_Map = new HashMap<String, String>();
            List<String> groupNames = new ArrayList<String>();
            group_Map = gen.account.groupDTODAO.GroupDAO.getGroupName(groupNames, false);

//            // check ledger is present or not if not then create it
//            if (saleLedgerMap != null && !saleLedgerMap.containsKey(tfSaleAccountText.getText().toString().trim().toLowerCase())) {
//                // getting Group_id for Sale Account
//                for (Map.Entry<String, String> e : group_Map.entrySet()) {
//                    if (e.getKey().equalsIgnoreCase("Sales Account")) {
//                        // create ledger
//                        System.out.println("Create Sale ledger ------------------------------" + e.getValue());
//                        gen.account.ledger.LedgerDTO ledgerDTO = new gen.account.ledger.LedgerDTO();
//                        ledgerDTO.setLedger_Name(tfSaleAccountText.getText().toString().trim());
//                        ledgerDTO.setLedger_Under(e.getValue());
//
//                        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
//                        Calendar currentDate = Calendar.getInstance();
//                        String dateNow = f.format(currentDate.getTime());
//
//                        ledgerDTO.setLedger_Date(dateNow);
//
//                        // for Credit for ledger so 
////                        ledgerDTO.setDebitOrCredit(gen.dto.Constants.DEBIT);
//                        ledgerDTO.setDebitOrCredit(2);
//                        // add in LedgerList
//                        ledgerDTOList.add(ledgerDTO);
//                    }
//                }
//
//            }

            System.out.println("  Group Name ----------   " + group_Map.entrySet());
            System.out.println("  accountLedgerMap Name ----------   " + accountLedgerMap.entrySet());
            System.out.println("  SALE LEDGER  Name ----------   " + saleLedgerMap.entrySet());

            if (accountLedgerMap != null && !tfAccountText.getText().toString().trim().isEmpty() && !accountLedgerMap.containsKey(tfAccountText.getText().toString().trim().toLowerCase())) {
                // getting Group_id for Cash Account
                for (Map.Entry<String, String> e : group_Map.entrySet()) {
                    if (e.getKey().equalsIgnoreCase("Sundry Debtors")) {
                        // create ledger
                        System.out.println("Create Cash ledger ------------------------------" + e.getValue());
                        gen.account.ledger.LedgerDTO ledgerDTO = new gen.account.ledger.LedgerDTO();
                        ledgerDTO.setLedger_Name(tfAccountText.getText().toString().trim());
                        ledgerDTO.setLedger_Under(e.getValue());
                        ledgerDTO.setLedger_Address(jtextArea_Buyer_Address.getText().toString().trim());
                        ledgerDTO.setLedger_ContactNo(jtextField_Buyer_Contno.getText().toString().trim());
                        ledgerDTO.setLedger_CVATNumber(jtextField_CVATNo.getText().toString().trim());
                        ledgerDTO.setLedger_CSTNumber(jtextField_CSTNo.getText().toString().trim());
                        ledgerDTO.setLedger_ECSNumber(jtextField_CSENo.getText().toString().trim());
                        // for Debit for ledger so 
//                        ledgerDTO.setDebitOrCredit(gen.dto.Constants.CREDIT);
                        ledgerDTO.setDebitOrCredit(1);

                        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                        Calendar currentDate = Calendar.getInstance();
                        String dateNow = f.format(currentDate.getTime());

                        ledgerDTO.setLedger_Date(dateNow);
                        System.out.println("Create CASh Ledger -------------------------");
                        // add in LedgerList
                        ledgerDTOList.add(ledgerDTO);
                    }
                }
            }

            // pass LedgerList For Creation of Ledger
            gen.account.ledger.LedgerDAO.insertLedger(ledgerDTOList);
            initVariables();
            if (validateData()) {

                setOld_Del_PO_Trans_Value();
                submit();
                tfDatePicker.requestFocus();
                jComboBoxAccount.hidePopup();
                jComboBoxParti.hidePopup();
                jComboBoxSaleLedger.hidePopup();
                clearFormData();
                temp = 1000;
                buttonAdd.setText("ADD");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            SaleDAO.deleteTransaction(Long.parseLong(saleDTO.getTrans_ID()));
            JOptionPane.showMessageDialog(this, "Transaction deleted completly");
            newButton();
            clearFormData();
            temp = 1000;
            buttonAdd.setText("ADD");
            tfDatePicker.requestFocus();
            jComboBoxAccount.hidePopup();
            jComboBoxParti.hidePopup();
            jComboBoxSaleLedger.hidePopup();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        if (buttonDelete.isEnabled()) {

            try {
                // for company_table 

                Connection conn1 = DatabaseConnection1.GetConnection();
                Long trans_id = 0l;
                String q = "select trans_id from tbltransactionmain where trans_receiptno = ? and trans_typeIndex = ?";
                PreparedStatement prmt1 = conn1.prepareStatement(q);
                prmt1.setString(1, jTextFieldReceiptNo.getText().trim());
                prmt1.setString(2, "1");
                ResultSet rs2 = prmt1.executeQuery();
                while (rs2.next()) {
                    trans_id = rs2.getLong("trans_id");
                }

                int i = 0;
                long Ledger_id = 0;
                String address = "";
                String CustomerVatTin = "";
                String str = "select ledger_id,LEDGER_ADDRESS,LEDGER_SALETAXNO from tblledger where ledger_name = ? ";
                prmt1 = conn1.prepareStatement(str);
                prmt1.setString(1, tfAccountText.getText().toString().trim());
                ResultSet rs3 = prmt1.executeQuery();
                while (rs3.next()) {
                    address = "" + rs3.getString("LEDGER_ADDRESS");
                    if (address.trim().equalsIgnoreCase("")) {
                        address = " ";
                    }
                    CustomerVatTin = "" + rs3.getString("LEDGER_SALETAXNO");
                    if (CustomerVatTin.trim().equalsIgnoreCase("")) {
                        CustomerVatTin = " ";
                    }
                }

                if (!CustomerVatTin.trim().equalsIgnoreCase("")) {
                    String cust = "Customer VAT TIN No: ";
                    CustomerVatTin = cust + CustomerVatTin;
                }
                prmt1.close();
                conn1.commit();
                
                String companyNo = "";


                //-----------------------Table: Credit--------------------------------------------
                // for company_info outside database
                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                Connection conn = DatabaseConnection1.GetConnection();
                q = "select * from TBLCOMPANYINFO where company_name = ? and company_ID = ?";
                PreparedStatement prmt = conn.prepareStatement(q);
                prmt.setString(1, gen.dto.Constants.CURRENT_COMPANY_NAME);
                prmt.setString(2, gen.dto.Constants.CURRENT_COMPANY_ID);
                ResultSet rs1 = prmt.executeQuery();
                if (rs1.next()) {
                    Map parameter = new HashMap();
                    String NameOfGod = rs1.getString("NameOfGod");
                    String companyname = rs1.getString("COMPANY_NAME");
                    String Address = rs1.getString("Address");
                    String contactno = rs1.getString("ContactNo");
                    String Emailid = rs1.getString("EmailId");
                    String Tagline = rs1.getString("Tagline");
                    String Buyers = "" + tfAccountText.getText().trim();//txtAccount.getSelectedItem();
                    String InVoiceNo = "" + jTextFieldReceiptNo.getText().trim();
                    String subtotal = "" + labeltxtBasicAmount.getText().trim();
                    String SalesPercent = "" + jTextFieldVatRate.getText().trim();
                    String SalesPercentAmount = "" + jTextFieldVatAmount.getText().trim();
                    String GrandTotalAmount = "" + labelFinalAmountTotal.getText().trim();
                    String ModesofPayment = "";

                    String CompanyCSTNo = rs1.getString("SaleTaxNo");
                    GrandTotalAmount = "" + Math.round(Double.parseDouble(labelFinalAmountTotal.getText().trim()));
                    Long total = Long.parseLong(GrandTotalAmount);
                    Util util = new Util();
                    String AmountChargeable = util.convert(total);

                    String Remarks = "" + jTextFieldNote.getText().trim();
                    String TermsCnditions = rs1.getString("TermCnditions");
                    String Declaration = rs1.getString("Declaration");
                    String VATTINNo = rs1.getString("VatTinNo");
                    String propriator = rs1.getString("SignAuthority");
                    companyNo = rs1.getString("Field1");
                    String Dispatchthrough = jTextFieldDispatchDocThrough.getText().trim();

                    if (Dispatchthrough.equals("")) {
                        Dispatchthrough = " ";
                    } else {
                        Dispatchthrough = "" + jTextFieldDispatchDocThrough.getText().trim();
                    }

                    String str1 = "";
                    String str2 = "";

                    if (!contactno.trim().equalsIgnoreCase("") || !Emailid.trim().equalsIgnoreCase("")) {
                        if (!contactno.trim().equalsIgnoreCase("")) {
                            str1 = "Phone No: ";
                            contactno = str1 + contactno;
                        }
                        if (!Emailid.trim().equalsIgnoreCase("")) {
                            str2 = " EMail-Id: ";
                            Emailid = str2 + Emailid;
                        }
                        contactno = contactno + Emailid;
                    }
                    parameter.put("GodName", NameOfGod);
                    parameter.put("BuyerAddress", address);
                    parameter.put("CustomerVatTin", CustomerVatTin);
                    parameter.put("email-id", Emailid);
                    parameter.put("CompanyName", companyname);
                    parameter.put("CompanyAddress", Address);
                    parameter.put("CompanyEmail", Emailid);
                    parameter.put("CompanyPhone", contactno);
                    parameter.put("CompanyDescription", Tagline);
                    parameter.put("Buyersname", Buyers);
                    parameter.put("InVoiceNo.", InVoiceNo);
                    SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
                    parameter.put("trans_date", saDa.format(jDateChooser.getDate()).toString());
                    parameter.put("ModeTermsOfPayment", ModesofPayment);
                    parameter.put("subtotal", subtotal);
                    parameter.put("SalesTaxPercent", SalesPercent);
                    parameter.put("PercentAmount", SalesPercentAmount);
                    parameter.put("GrandTotalAmount", GrandTotalAmount);
                    parameter.put("AmountChargeable", AmountChargeable);
                    parameter.put("Remarks", Remarks);
                    parameter.put("TermsandConditions", TermsCnditions);
                    parameter.put("CompanyCST", CompanyCSTNo);
                    parameter.put("VatTinNo", VATTINNo);
                    parameter.put("Declaration", Declaration);
                    parameter.put("CompanySign", companyname);
                    parameter.put("propriator", propriator);
                    parameter.put("transport", "-");
                    parameter.put("dispatchthrough", Dispatchthrough);

                    InputStream logowoodstuff = this.getClass().getResourceAsStream("/images/Woodstuff logo.jpg");

                    InputStream logoplymate = this.getClass().getResourceAsStream("/images/NEW Duracraft.jpg");
                    parameter.put("logowoodstuff", logowoodstuff);
                    parameter.put("logoplymate", logoplymate);

                    // code for image 
//		    InputStream img1 = this.getClass().getResourceAsStream("/images/unnati_hinges_packing1_1.jpg");
//		    parameter.put("logo", img1);

                    byte[] image = null;
                    String query = "select company_image from tblcompaniesimage where company_name = '" + gen.dto.Constants.CURRENT_COMPANY_NAME + "' and company_id = " + gen.dto.Constants.CURRENT_COMPANY_ID + "";
                    System.out.println("Q======  " + q);
                    PreparedStatement prmt5 = conn.prepareStatement(query);
                    ResultSet rs5 = prmt5.executeQuery();
                    if (rs5.next()) {
                        image = rs5.getBytes("COMPANY_IMAGE");
                    }
                    prmt5.close();
                    rs5.close();
//                    InputStream image1 = new ByteArrayInputStream(image);
                    if (image != null) {
                        InputStream image1 = new ByteArrayInputStream(image);
                        parameter.put("logo", image1);
                    }


                    /// added on 14-06-2014 for Tax-Invoice Copy Only

                    GrandTotalAmount = "" + Math.round(Double.parseDouble(labelFinalAmountTotal.getText().trim()));
                    Long total1 = Long.parseLong(GrandTotalAmount);
                    String GrandTotalAmount1 = labelFinalAmountTotal.getText().trim();
                    Util util1 = new Util();
                    String AmountChargeable1 = util1.convert(total1);

                    parameter.put("Salesname", tfSaleAccountText.getText().toString().trim());
                    parameter.put("InVoiceNo.", jTextFieldReceiptNo.getText().toString().trim());
                    parameter.put("trans_date", tfDatePicker.getText().toString().trim());
                    parameter.put("Buyersname", tfAccountText.getText().toString().trim());
                    parameter.put("BuyerAddress", jtextArea_Buyer_Address.getText().toString().trim());


                    parameter.put("VatTinNo", jtextField_CVATNo.getText().toString().trim());
                    parameter.put("BuyerCSTNo", jtextField_CSTNo.getText().toString().trim());
                    parameter.put("ECSNumber", jtextField_CSENo.getText().toString().trim());
                    parameter.put("PONo", jtextField_PONo.getText().toString().trim());
                    parameter.put("OCNo", jtextField_OCNo.getText().toString().trim());
                    parameter.put("PODate", tfDatePOdate.getText().toString().trim());
                    parameter.put("OCDate", tfDateOCDate.getText().toString().trim());
                    parameter.put("transport", jTextFieldDispatchDocThrough.getText().toString().trim());


                    parameter.put("Delivery_Type", ( companyNo != null ? companyNo+ "     " : "") + jTextFieldNote.getText().toString().trim());
                    parameter.put("PaymentWithin", jTextFieldDispatchDocNo.getText().toString().trim());
                    parameter.put("Date_of_Inv_Issue", tfDateISS.getText().toString().trim());
                    parameter.put("Time_of_Issue_Invoice", jtextField_Time_Iss.getText().toString().trim());
                    parameter.put("Date_of_Removal", tfDateRem.getText().toString().trim());
                    parameter.put("Time_Of_Remove", jtextField_Time_Rem.getText().toString().trim());

                    String Ed_Cess_Amt = jtextField_EdCessAmt.getText().toString().trim();
                    if (Double.parseDouble(Ed_Cess_Amt) <= 0D) {
                        Ed_Cess_Amt = "";
                    }
                    parameter.put("EdCess", Ed_Cess_Amt);

                    String H_Ed_Cess_Amt = jtextField_H_EdCessAmt.getText().toString().trim();
                    if (Double.parseDouble(H_Ed_Cess_Amt) <= 0D) {
                        H_Ed_Cess_Amt = "";
                    }
                    parameter.put("HEdCess", H_Ed_Cess_Amt);

                    parameter.put("TotalWithoutVat", jtextField_Total_WithoutVAT.getText().toString().trim());

                    String vatPercent = jTextFieldVatRate.getText().toString().trim();
                    String vatAmount = jTextFieldVatAmount.getText().toString().trim();
                    if (Double.parseDouble(vatPercent) <= 0D) {
                        vatPercent = "";
                    }
                    if (Double.parseDouble(vatAmount) <= 0D) {
                        vatAmount = "";
                    }

                    ////////////////////////////////// 
                    if (!vatPercent.isEmpty()) {
                        Long round_Off_VAT_duty = Math.round(Double.parseDouble(vatPercent));
                        Double diff_VAT = round_Off_VAT_duty - Double.parseDouble(vatPercent);
                        System.out.println("difference diff_VAT ---------- " + diff_VAT);
                        if (diff_VAT == 0D) {
                            vatPercent = round_Off_VAT_duty.toString();
                        }
                    }
                    ////////////////////////////////////////////////////////

                    parameter.put("PercentAmount", vatAmount);
                    parameter.put("SalesTaxPercent", vatPercent);

                    parameter.put("GrandTotalAmount", GrandTotalAmount1.toString().trim());
                    parameter.put("GrandTotal_In_Words", AmountChargeable1);

                    String rate_of_Duty_Percent = jtextField_ExDutyPer.getText().toString().trim();
                    String rate_of_CENVATPercent = jtextField_ExDutyPer.getText().toString().trim();
                    String rate_of_CENVATAmount = jtextField_ExDutyAmt.getText().toString().trim();

                    if (Double.parseDouble(rate_of_Duty_Percent) <= 0D) {
                        rate_of_Duty_Percent = "";
                        rate_of_CENVATPercent = "";
                    }
                    if (Double.parseDouble(rate_of_CENVATAmount) <= 0D) {
                        rate_of_CENVATAmount = "";
                    }

                    //////////////////////////////////
                    if (!rate_of_Duty_Percent.isEmpty()) {
                        Long round_Off_Excise_duty = Math.round(Double.parseDouble(rate_of_Duty_Percent));
                        Double diff = round_Off_Excise_duty - Double.parseDouble(rate_of_Duty_Percent);
                        System.out.println("difference ---------- " + diff);
                        if (diff == 0D) {
                            rate_of_Duty_Percent = round_Off_Excise_duty.toString();
                            rate_of_CENVATPercent = round_Off_Excise_duty.toString();
                        }
                    }
                    ////////////////////////////////////////////////////////

                    parameter.put("rate_of_Duty_Percent", rate_of_Duty_Percent);
//                    parameter.put("rate_of_CENVATPercent", rate_of_CENVATPercent);
//                    parameter.put("rate_of_CENVATAmount", rate_of_CENVATAmount);
                    parameter.put("Excise_Duty_Rate", rate_of_CENVATPercent);
                    parameter.put("Excise_Duty", rate_of_CENVATAmount);


                    String CSTRate = jTextFieldCSTRate.getText().toString().trim();
                    String CSTAmt = jTextFieldCSTAmount.getText().toString().trim();
                    if (Double.parseDouble(CSTRate) <= 0D) {
                        CSTRate = "";
                    }
                    if (Double.parseDouble(CSTAmt) <= 0D) {
                        CSTAmt = "";
                    }

                    //////////////////////////////////
                    if (!CSTRate.isEmpty()) {
                        Long round_Off_CST_duty = Math.round(Double.parseDouble(CSTRate));
                        Double diff_CST = round_Off_CST_duty - Double.parseDouble(CSTRate);
                        System.out.println("difference diff_CST ---------- " + diff_CST);
                        if (diff_CST == 0D) {
                            CSTRate = round_Off_CST_duty.toString();
                        }
                    }
                    ////////////////////////////////////////////////////////


                    parameter.put("CSTAmt", CSTAmt);
                    parameter.put("CSTRate", CSTRate);


                    // calculate Total Central Excise Duty
                    Long total_value = 0L;

                    String GrandTotalAmount_ExDutyAmt = "" + Math.round(Double.parseDouble(jtextField_ExDutyAmt.getText().trim()));
                    Long GrandTotalAmount_ExDutyAmt_1 = Long.parseLong(GrandTotalAmount_ExDutyAmt);

                    String GrandTotalAmount_EdCessAmt = "" + Math.round(Double.parseDouble(jtextField_EdCessAmt.getText().trim()));
                    Long GrandTotalAmount_ExDutyEdCess_1 = Long.parseLong(GrandTotalAmount_EdCessAmt);

                    String GrandTotalAmount_H_EdDutyAmt = "" + Math.round(Double.parseDouble(jtextField_H_EdCessAmt.getText().trim()));
                    Long GrandTotalAmount_H_EdDutyAmt_1 = Long.parseLong(GrandTotalAmount_H_EdDutyAmt);

                    total_value = GrandTotalAmount_ExDutyAmt_1 + GrandTotalAmount_ExDutyEdCess_1 + GrandTotalAmount_H_EdDutyAmt_1;
                    total_value = Long.parseLong("" + Math.round(total_value));
                    Long Total_Central_Excise_Duty_Words = total_value;

                    String Total_Central_Excise_Duty_Words_1 = "" + util1.convert(Total_Central_Excise_Duty_Words);
                    if (Total_Central_Excise_Duty_Words > 0) {
                        Total_Central_Excise_Duty_Words_1 = "RS. " + Total_Central_Excise_Duty_Words_1 + " ONLY";
                    } else {
                        Total_Central_Excise_Duty_Words_1 = "";
                    }
                    parameter.put("Total_Central_Excise", Total_Central_Excise_Duty_Words_1);

                    //                    // display sale ledger
//                    String sale_Ledger_Name = tfSaleAccountText.getText().toString().trim();
//                    parameter.put("", sale_Ledger_Name);

                    // create new table with no * among length,width,thicknes
//                    Set<String> idSet = new HashSet<String>();
//                    idSet.add(jTextFieldReceiptNo.getText().toString());
//                    List<SaleDTO> saleDTOList = SaleDAO.getSales(idSet, "");
//                    SaleDTO saleDTOForPrint = new SaleDTO(); 
//                    saleDTOForPrint = saleDTOList.get(0);

//                    bindDTOtoJtableWithSpaceAmoungLength_Width_Thickness();

                    PrintAllReport.printSalesReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";

//                    // set to table to previous condition
//                    bindDTOtoJtable();

                    prmt.close();
                    conn.close();
                } else {
                    Map parameter = new HashMap();
                    String NameOfGod = "";
                    String companyname = "";
                    String Address = "";
                    String contactno = "";
                    String Emailid = "";
                    String CompanyCSTNo = "";
                    String Tagline = "";
                    String TermsCnditions = "";
                    String Declaration = "";
                    String VATTINNo = "";
                    String propriator = "";

                    String Buyers = "" + tfAccountText.getText().trim();//txtAccount.getSelectedItem();
                    String InVoiceNo = "" + jTextFieldReceiptNo.getText().trim();
                    String subtotal = "" + labeltxtBasicAmount.getText().trim();
                    String SalesPercent = "" + jTextFieldVatRate.getText().trim();
                    String SalesPercentAmount = "" + jTextFieldVatAmount.getText().trim();
                    String GrandTotalAmount = "" + labelFinalAmountTotal.getText().trim();

                    GrandTotalAmount = "" + Math.round(Double.parseDouble(labelFinalAmountTotal.getText().trim()));
                    Long total = Long.parseLong(GrandTotalAmount);
                    Util util = new Util();
                    String AmountChargeable = util.convert(total);

                    String Remarks = "" + jTextFieldNote.getText().trim();
                    String Dispatchthrough = jTextFieldDispatchDocThrough.getText().trim();

                    if (Dispatchthrough.equals("")) {
                        Dispatchthrough = " ";
                    } else {
                        Dispatchthrough = "" + jTextFieldDispatchDocThrough.getText().trim();
                    }

                    String str1 = "";
                    String str2 = "";

                    if (!contactno.trim().equalsIgnoreCase("") || !Emailid.trim().equalsIgnoreCase("")) {
                        if (!contactno.trim().equalsIgnoreCase("")) {
                            str1 = "Phone No: ";
                            contactno = str1 + contactno;
                        }
                        if (!Emailid.trim().equalsIgnoreCase("")) {
                            str2 = " EMail-Id: ";
                            Emailid = str2 + Emailid;
                        }
                        contactno = contactno + Emailid;
                    }
                    parameter.put("GodName", NameOfGod);
                    parameter.put("BuyerAddress", address);
                    parameter.put("CustomerVatTin", CustomerVatTin);
                    parameter.put("email-id", Emailid);
                    parameter.put("CompanyName", companyname);
                    parameter.put("CompanyAddress", Address);
                    parameter.put("CompanyEmail", Emailid);
                    parameter.put("CompanyPhone", contactno);
                    parameter.put("CompanyDescription", Tagline);
                    parameter.put("Buyersname", Buyers);
                    parameter.put("InVoiceNo.", InVoiceNo);
                    SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
                    parameter.put("trans_date", saDa.format(jDateChooser.getDate()).toString());
                    parameter.put("subtotal", subtotal);
                    parameter.put("SalesTaxPercent", SalesPercent);
                    parameter.put("PercentAmount", SalesPercentAmount);
                    parameter.put("GrandTotalAmount", GrandTotalAmount);
                    parameter.put("AmountChargeable", AmountChargeable);
                    parameter.put("Remarks", Remarks);
                    parameter.put("TermsandConditions", TermsCnditions);
                    parameter.put("CompanyCST", CompanyCSTNo);
                    parameter.put("VatTinNo", VATTINNo);
                    parameter.put("Declaration", Declaration);
                    parameter.put("CompanySign", companyname);
                    parameter.put("propriator", propriator);
                    parameter.put("transport", "-");
                    parameter.put("dispatchthrough", Dispatchthrough);

                    InputStream logowoodstuff = this.getClass().getResourceAsStream("/images/Woodstuff logo.jpg");
                    InputStream logoplymate = this.getClass().getResourceAsStream("/images/NEW Duracraft.jpg");
                    parameter.put("logowoodstuff", logowoodstuff);
                    parameter.put("logoplymate", logoplymate);

                    byte[] image = null;
                    String query = "select company_image from tblcompaniesimage where company_name = '" + gen.dto.Constants.CURRENT_COMPANY_NAME + "' and company_id = " + gen.dto.Constants.CURRENT_COMPANY_ID + "";
                    System.out.println("Q======  " + q);
                    PreparedStatement prmt5 = conn.prepareStatement(query);
                    ResultSet rs5 = prmt5.executeQuery();
                    if (rs5.next()) {
                        image = rs5.getBytes("COMPANY_IMAGE");
                    }
                    prmt5.close();
                    rs5.close();

                    InputStream image1 = null;
                    if (image != null) {
                        image1 = new ByteArrayInputStream(image);
                    }
                    parameter.put("logo", image1);
                    //InputStream image1 = new ByteArrayInputStream(image);
                    // parameter.put("logo", image1);

                    PrintAllReport.printSalesReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxSaleLedger.hidePopup();
    }

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) {
        dataExport();
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        newButton();
        clearFormData();
        temp = 1000;
        buttonAdd.setText("ADD");
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxSaleLedger.hidePopup();
    }

    private void buttonNextTransactionsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            System.out.println("Value of the row call----------------------" + gen.dto.Constants.OFFSET_VALUE);
            last_Number_In_TransactionList = 1;
            initTransactionList();
        } catch (Exception ex) {
            Logger.getLogger(ChalanForm1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonPreviousTransactionsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            System.out.println("Value of the row call----------------------" + gen.dto.Constants.OFFSET_VALUE);
            initTransactionList();
        } catch (Exception ex) {
            Logger.getLogger(ChalanForm1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    // GUI fields declaration
    private Container container;
    private Toolkit toolkit;
    private Dimension dimension;
    private Integer screenWidth;
    private Integer screenHeight;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private Integer screenResolution;
    private Integer flowLblLocationX;
    private Integer flowLblLocationY;
    private Integer fontValue;
    private Integer fontSize;
    private Font font;
    private JPanel jMainPanel;
    private JLabel labelReceiptNo;
    private JTextField jTextFieldReceiptNo;
    private JLabel labelChalanNo;
    private JLabel labelChalanNoText;
    private JLabel labelDate;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private JLabel labelAccount;
    private JComboBox jComboBoxAccount;
    private JLabel labelSaleLedger;
    private JComboBox jComboBoxSaleLedger;
    private JLabel labelSN;
    private JLabel labelParticulars;
    private JComboBox jComboBoxParti;
    //private JLabel labelLength;
    private JTextField jTextFieldLngth;
    //private JLabel labelWidth;
    private JTextField jTextFieldWidth;
    //private JLabel labelThickness;
    private JTextField jTextFieldThickness;
    private JLabel labelQuantity;
    private JTextField jTextFieldQuantity;
    //private JLabel labelSquareFeet;
    private JTextField jTextFieldSquareFeet;
    private JLabel labelRate;
    private JTextField jTextFieldRate;
    private JLabel labelAmount;
    private JLabel labelUnitSymbol;
    private JTextField jTextFieldAmount;
    private JButton buttonAdd;
    private JButton buttonParticularDelete;
    private JLabel labelNote;
    private JLabel labelDispatchDocNo;
    private JTextField jTextFieldDispatchDocNo;
    private JLabel labelDispatchDocThrough;
    private JTextField jTextFieldDispatchDocThrough;
    private JLabel labelBasicAmount;
    private JLabel labeltxtBasicAmount;
    private JLabel labelVatRate;
    private JLabel labelDiscount;
    //private JLabel labelShipping;
    private JTextField jTextFieldDiscount;
    private JTextField jTextFieldDiscountAmount;
    private JTextField jTextFieldShipping;
    private JTextField jTextFieldVatRate;
    private JTextField jTextFieldVatAmount;
    private JLabel labeltxtRoundOffAmount;
    //private JLabel labelLessBill;
    private JTextField jTextFieldLessBill;
    //private JLabel labelTransport;
    private JTextField jTextFieldTransport;
    private JLabel labelFinalAmount;
    private JLabel labelFinalAmountTotal;
    private JButton buttonAddLedger;
    private JButton buttonAddItem;
    private JButton buttonBack;
    private JButton buttonSubmit;
    private JButton buttonDelete;
    private JButton buttonNew;
    private JButton buttonPrint;
    private JButton buttonExport;
    private JButton buttonNext_UP_Transactions;
    private JButton buttonPrevious_UP_Transactions;
    private JButton buttonNext_DOWN_Transactions;
    private JButton buttonPrevious_DOWN_Transactions;
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JTable jTableTransactionList;
    private JTable tableParti;
    private DefaultTableModel transactionTableModel1;
    private DefaultTableModel partiTableModel;
    private JTextField tfAccountText, tfSaleAccountText, tfStockItemText, tfColorText, tfUnitText;
    private JTextField tfDatePicker = null;
    private JTextField tfDatePOdate = null;
    private JTextField tfDateOCDate = null;
    private JTextField tfDateISS = null;
    private JTextField tfDateRem = null;

    /////////////////// private methods for functionality/////////////////////////////////
    private void initilize() throws SQLException, ParseException, Exception {
        initilizeGUIComponents();
        initVariables();
        bindTOGUI();
    }
    private boolean hide_flag = false;

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }

    private void initComponentActiveInActive() {
        buttonDelete.setEnabled(false);
        buttonExport.setEnabled(false);
        buttonPrint.setEnabled(false);
        jTextFieldReceiptNo.setEditable(true);
    }

    private void setAccountLedgetModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxAccount.setModel(mdl);
        jComboBoxAccount.setSelectedIndex(-1);
        jComboBoxAccount.showPopup();
        tfAccountText.setText(str);
    }

    private void setSaleLedgetModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxSaleLedger.setModel(mdl);
        jComboBoxSaleLedger.setSelectedIndex(-1);
        jComboBoxSaleLedger.showPopup();
        tfSaleAccountText.setText(str);
    }

    private void setPartiModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxParti.setModel(mdl);
        jComboBoxParti.setSelectedIndex(-1);
        jComboBoxParti.showPopup();
        tfStockItemText.setText(str);
    }

//    private void setColorModel(DefaultComboBoxModel mdl, String str) {
//        jcomboBoxColor.setModel(mdl);
//        jcomboBoxColor.setSelectedIndex(-1);
//        jcomboBoxColor.showPopup();
//        tfColorText.setText(str);
//    }
    private void setUnitModel(DefaultComboBoxModel mdl, String str) {
        jcomboBoxUnit.setModel(mdl);
        jcomboBoxUnit.setSelectedIndex(-1);
        jcomboBoxUnit.showPopup();
        tfUnitText.setText(str);
    }

    private void initVariables() throws Exception {
        initLedger();
        initStockItem();
        initColor();
        if (!isEdit) {
            saleDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.SALE_TYPE_INDEX, jDateChooser.getDate()));
        }

        ledgerTimeStamp = Constants.LEDGER_TIME_STAMP;
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
        buttonParticularDelete.setEnabled(false);

    }

    private void initLedger() throws Exception {
        List<String> groups = new ArrayList<String>();
        groups.add(Constants.BANK_ACCOUNT);
        groups.add(Constants.CASH_IN_HAND);
        groups.add(Constants.SUNDRY_CREDITORS);
        groups.add(Constants.SUNDRY_DEBTORS);
//        groups.add(Constants.PURCHASE_ACCOUNT);
//        groups.add(Constants.PURCHASE_ACCOUNT);
        accountLedgerMap = gen.account.ledger.LedgerDAO.getLedgerFromGroupName(groups, true);

        groups = new ArrayList<String>();
        groups.add(Constants.SALES_ACCOUNT);
        saleLedgerMap = gen.account.ledger.LedgerDAO.getLedgerFromGroupName(groups, true);
        cashLedgerVector.clear();
        for (String str : accountLedgerMap.keySet()) {
            cashLedgerVector.add(str);
        }

        Collections.sort(
                cashLedgerVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        saleLedgerVector.clear();
        for (String str : saleLedgerMap.keySet()) {
            saleLedgerVector.add(str);
        }

        Collections.sort(
                saleLedgerVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        saleLedgerMap = Util.getSmallCaseMap(saleLedgerMap);
        accountLedgerMap = Util.getSmallCaseMap(accountLedgerMap);
    }

    private void initStockItem() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
//        stockItemMap = gen.account.stockitem.StockItemDAO.getStockItemsFromGroupName(stockGroups, false);
        stockItemMap = gen.account.stockitem.StockItemDAO.getStockItemsNameID(stockGroups, false);
        stockItemVector.clear();
        for (String str : stockItemMap.keySet()) {
            stockItemVector.add(str);
        }

        Collections.sort(
                stockItemVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        stockItemMap = Util.getSmallCaseMap(stockItemMap);
    }

    private void initColor() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
        colorMap = gen.account.stockitem.StockItemDAO.loadColorsNameToIDMap();
        colorVector.clear();
        for (String str : colorMap.keySet()) {
            colorVector.add(str);
        }
        Collections.sort(
                colorVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        colorMap = Util.getSmallCaseMap(colorMap);

        unitMap = gen.account.unitofmeasure.UnitOfMeasureDAO.getUOMNameIDMap();
        unitVector.clear();
        for (String str : unitMap.keySet()) {
            unitVector.add(str);
        }
        Collections.sort(
                unitVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        unitMap = Util.getSmallCaseMap(unitMap);

    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        gen.dto.Constants.OFFSET_VALUE = 0L;
        Long number = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.SALE_TYPE_INDEX, Constants.CREDIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        Long remaining = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.SALE_TYPE_INDEX, Constants.CREDIT) % gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));

        if (remaining == 0L) {
            gen.dto.Constants.OFFSET_VALUE = Math.abs((number - 1) * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        } else {
            gen.dto.Constants.OFFSET_VALUE = Math.abs(number * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        }

        // code added on 27-06-2014
        gen.dto.Constants.OFFSET_VALUE = 0L;

        bindDTOtoGUI();
        bindDTOtoJtable();
        if (!isEdit || isTransactionTableLoad) {
            initTransactionList();
        }
        isTransactionTableLoad = false;

    }

    private void bindDTOtoGUI() throws Exception {
        tfAccountText.setText(saleDTO.getCashLedger());
        tfSaleAccountText.setText(saleDTO.getSaleLedger());
        jTextFieldReceiptNo.setText("" + saleDTO.getReceiptNo());
        jTextFieldDispatchDocNo.setText(saleDTO.getDispatchDocNo() + "");
        jTextFieldDispatchDocThrough.setText(saleDTO.getDispatchDocThrough() + "");

        jTextFieldVatAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getVatAmount()) + "");
        jTextFieldCSTAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getCstAmount()) + "");
        jTextFieldVatRate.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getVatRate()) + "");
        jTextFieldCSTRate.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getCstRate()) + "");

        //set all value to zero
        if (saleDTO.getStockItemTreansactioDTOList().isEmpty() || saleDTO.getStockItemTreansactioDTOList() == null) {
            jTextFieldAmount.setText("0.0");
            jTextFieldRate.setText("0.0");
            jTextFieldQuantity.setText("0.0");
        }

        System.out.println("----------- " + saleDTO.getAmount().toString());
        labeltxtBasicAmount.setText((Constants.DECIMAL_FORMAT.format(saleDTO.getAmount())));
        labelFinalAmountTotal.setText((Constants.DECIMAL_FORMAT.format(saleDTO.getFinalAmount())));
        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getRoundOffAmount()) + "");
        if (saleDTO.getChallan_trans_ID() != null && !saleDTO.getChallan_trans_ID().equals("")) {
            if (Double.parseDouble(saleDTO.getChallan_trans_ID()) >= 0D) {
            } else {
            }
        } else {
        }

        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(saleDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getDate().trim()));
        }

        // newly added on date 13-04-2014
        jtextArea_Buyer_Address.setText(saleDTO.getLedgerDTO().getLedger_Address());
        jtextField_Buyer_Contno.setText(saleDTO.getLedgerDTO().getLedger_ContactNo());
        jtextField_CSENo.setText(saleDTO.getLedgerDTO().getLedger_ECSNumber());
        jtextField_CVATNo.setText(saleDTO.getLedgerDTO().getLedger_CVATNumber());
        jtextField_CSTNo.setText(saleDTO.getLedgerDTO().getLedger_SaleTaxNo());

        if (saleDTO.getDateIssDate() != null && !saleDTO.getDateIssDate().trim().equalsIgnoreCase("")) {
            if (!isEdit) {
                dateChooser_Date_Iss.setDate((java.util.Date) Constants.DATE_FORMATER.parse(saleDTO.getDateIssDate().trim()));
            } else {
                dateChooser_Date_Iss.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getDateIssDate().trim()));
            }
        } else {
            tfDateISS.setText("");
        }

        if (saleDTO.getDateRemDate() != null && !saleDTO.getDateRemDate().trim().equalsIgnoreCase("")) {
            if (!isEdit) {
                dateChooser_Date_Rem.setDate((java.util.Date) Constants.DATE_FORMATER.parse(saleDTO.getDateRemDate().trim()));
            } else {
                dateChooser_Date_Rem.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getDateRemDate().trim()));
            }
        } else {
            tfDateRem.setText("");
        }

        System.out.println(" ++++++++ " + saleDTO.getPoDate());
        // remove on 3-06-2014
        if (saleDTO.getPoDate() != null && !saleDTO.getPoDate().trim().equalsIgnoreCase("")) {
            if (!isEdit) {
                dateChooser_PO.setDate((java.util.Date) Constants.DATE_FORMATER.parse(saleDTO.getPoDate().trim()));
                tfDatePOdate.setText("");
            } else {
                dateChooser_PO.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getPoDate().trim()));
            }
        } else {
            tfDatePOdate.setText("");
        }

        if (saleDTO.getOcDate() != null && !saleDTO.getOcDate().trim().equalsIgnoreCase("")) {
            if (!isEdit) {
                dateChooser_OCDate.setDate((java.util.Date) Constants.DATE_FORMATER.parse(saleDTO.getOcDate().trim()));
                tfDateOCDate.setText("");
            } else {
                dateChooser_OCDate.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTO.getOcDate().trim()));
            }
        } else {
            tfDateOCDate.setText("");
        }


        jtextField_PONo.setText(saleDTO.getPoNo());
        jtextField_OCNo.setText(saleDTO.getOcNo());

        jTextFieldNote.setText(saleDTO.getNote());
        jTextFieldDispatchDocThrough.setText(saleDTO.getDispatchDocThrough());
        jTextFieldDispatchDocNo.setText(saleDTO.getDispatchDocNo());
        jtextField_Time_Iss.setText(saleDTO.getTimeIss());
        jtextField_Time_Rem.setText(saleDTO.getTimeRem());

        jtextField_ExDutyPer.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getExciseDutyRate()).toString());
        jtextField_ExDutyAmt.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getExciseDutyAmount()).toString());

        jtextField_EdCessPer.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getEdCessRate()).toString());
        jtextField_EdCessAmt.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getEdCessAmount()).toString());

        jtextField_H_EdCessPer.setText(Constants.DECIMAL_FORMAT.format(saleDTO.gethEdCessRate()).toString());
        jtextField_H_EdCessAmt.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getHedCessAmount()).toString());
        jtextField_Total_WithoutVAT.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getTotal_Without_Vat()).toString());

        calculateAndBind_Total_Without_VAT_TOGUI();


    }

    private void bindDTOtoJtable() throws Exception {
        int index = 1;
        List<StockItemTransactionDTO> stockItemTransactionDTOList = saleDTO.getStockItemTreansactioDTOList();
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            partiTableModel.setRowCount(0);
            for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                try {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                    partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 9);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getRate()), partiTableModel.getRowCount() - 1, 10);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getAmount()), partiTableModel.getRowCount() - 1, 11);

                    for (Map.Entry<String, String> e : unitMap.entrySet()) {
                        if (e.getValue().equalsIgnoreCase(stockItemTransactionDTO.getUser_unit_of_symbol() != null ? stockItemTransactionDTO.getUser_unit_of_symbol().toString() : "")) {
                            partiTableModel.setValueAt(e.getKey(), partiTableModel.getRowCount() - 1, 8);
                        }
                    }

                    StockItemTransactionDTO stockItemUnitTransactionDTO = gen.account.stockitem.StockItemDAO.getStockItemValues(stockItemTransactionDTO.getName());
                    partiTableModel.setValueAt(stockItemUnitTransactionDTO.getUnit_of_symbol(), partiTableModel.getRowCount() - 1, 12);

                    if (stockItemTransactionDTO.getColor() != null) {
                        for (Map.Entry<String, String> e : colorMap.entrySet()) {
                            if (e.getValue().equalsIgnoreCase(stockItemTransactionDTO.getColor().toString())) {
                                partiTableModel.setValueAt(e.getKey(), partiTableModel.getRowCount() - 1, 3);
                            }
                        }
                    } else {
                        partiTableModel.setValueAt("", partiTableModel.getRowCount() - 1, 6);
                    }

                    partiTableModel.setValueAt(stockItemTransactionDTO.getStkpackage(), partiTableModel.getRowCount() - 1, 6);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getNo()), partiTableModel.getRowCount() - 1, 7);

                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getLength()), partiTableModel.getRowCount() - 1, 4);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getWidth()), partiTableModel.getRowCount() - 1, 5);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getThkness()), partiTableModel.getRowCount() - 1, 2);

                    index++;
                } catch (SQLException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void bindDTOtoJtableWithSpaceAmoungLength_Width_Thickness() throws Exception {
        int index = 1;
        List<StockItemTransactionDTO> stockItemTransactionDTOList = saleDTO.getStockItemTreansactioDTOList();
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            partiTableModel.setRowCount(0);
            for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                try {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);

                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 9);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getRate()), partiTableModel.getRowCount() - 1, 10);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getAmount()), partiTableModel.getRowCount() - 1, 11);

                    for (Map.Entry<String, String> e : unitMap.entrySet()) {
                        if (e.getValue().equalsIgnoreCase(stockItemTransactionDTO.getUser_unit_of_symbol().toString())) {
                            partiTableModel.setValueAt(e.getKey(), partiTableModel.getRowCount() - 1, 8);
                        }
                    }

                    StockItemTransactionDTO stockItemUnitTransactionDTO = gen.account.stockitem.StockItemDAO.getStockItemValues(stockItemTransactionDTO.getName());
                    partiTableModel.setValueAt(stockItemUnitTransactionDTO.getUnit_of_symbol(), partiTableModel.getRowCount() - 1, 12);

                    if (stockItemTransactionDTO.getColor() != null) {
                        for (Map.Entry<String, String> e : colorMap.entrySet()) {
                            if (e.getValue().equalsIgnoreCase(stockItemTransactionDTO.getColor().toString())) {
                                partiTableModel.setValueAt(e.getKey(), partiTableModel.getRowCount() - 1, 3);
                            }
                        }
                    } else {
                        partiTableModel.setValueAt("", partiTableModel.getRowCount() - 1, 6);
                    }

                    partiTableModel.setValueAt(stockItemTransactionDTO.getStkpackage(), partiTableModel.getRowCount() - 1, 6);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getNo()), partiTableModel.getRowCount() - 1, 7);

                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getLength()), partiTableModel.getRowCount() - 1, 4);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getWidth()), partiTableModel.getRowCount() - 1, 5);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getThkness()), partiTableModel.getRowCount() - 1, 2);

                    // creating New Name of StockItem
                    String stkname_with_sapce = Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getLength()).toString() + " "
                            + Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getWidth()).toString() + " "
                            + Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getThkness()).toString() + " ";

                    partiTableModel.setValueAt(stkname_with_sapce, partiTableModel.getRowCount() - 1, 1);

                    index++;
                } catch (SQLException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void bindDTOtoGUIWithoutReceiptNo() throws ParseException {
        tfAccountText.setText(saleDTO.getCashLedger());
        tfSaleAccountText.setText(saleDTO.getSaleLedger());
        jTextFieldLessBill.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getLessBillAmount()) + "");
        jTextFieldNote.setText(saleDTO.getNote());
        jTextFieldDispatchDocNo.setText(saleDTO.getDispatchDocNo() + "");
        jTextFieldDispatchDocThrough.setText(saleDTO.getDispatchDocThrough() + "");
        jTextFieldVatAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getVatAmount()) + "");
        jTextFieldCSTAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getCstAmount()) + "");
        jTextFieldTransport.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getTransport()) + "");
        jTextFieldVatRate.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getVatRate()) + "");
        jTextFieldCSTRate.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getCstRate()) + "");
        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getAmount()) + "");
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getFinalAmount()) + "");
        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(saleDTO.getRoundOffAmount()) + "");
        tfDatePicker.setText(new SaleDTO().getDate());
    }

    private void submit() throws SQLException, ParseException, Exception {
        saleDTO = bindGUITODTO();
        saleDTO = ImpExpUtil.valiDateAndRepairSaleDTO(saleDTO);
        saleDTO.setSaleLedger(saleLedgerMap.get(saleDTO.getSaleLedger().trim().toLowerCase()));
        saleDTO.setCashLedger(accountLedgerMap.get(saleDTO.getCashLedger().trim().toLowerCase()));
        for (StockItemTransactionDTO stockItemTransactionDTO : saleDTO.getStockItemTreansactioDTOList()) {
            stockItemTransactionDTO.setName(stockItemMap.get(stockItemTransactionDTO.getName().toLowerCase().trim()));
            if (stockItemTransactionDTO.getColor() != null) {
                stockItemTransactionDTO.setColor(colorMap.get(stockItemTransactionDTO.getColor().toLowerCase().trim()));
            } else {
                stockItemTransactionDTO.setColor(null);
            }
            stockItemTransactionDTO.setUnit(unitMap.get(stockItemTransactionDTO.getUnit().toLowerCase().trim()));
        }

        if (!isEdit) {
            insertSale();

        } else {
            updateSale();
        }
        JOptionPane.showMessageDialog(jMainPanel, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        newButton();
        tfDatePicker.requestFocus();
    }

    private SaleDTO bindGUITODTO() {
        SaleDTO saleDTOEntity = new SaleDTO();
        saleDTOEntity.setTrans_ID(saleDTO.getTrans_ID());

        if (saleDTO.getChallan_trans_ID() != null && !saleDTO.getChallan_trans_ID().equals("")) {
            if (Double.parseDouble(saleDTO.getChallan_trans_ID()) >= 0D) {
                saleDTOEntity.setChallan_receipt_No(saleDTO.getChallan_receipt_No());
                saleDTOEntity.setChallan_trans_ID(saleDTO.getChallan_trans_ID());
            } else {
                saleDTOEntity.setChallan_receipt_No("0");
                saleDTOEntity.setChallan_trans_ID("0");
            }
        } else {
            saleDTOEntity.setChallan_receipt_No("0");
            saleDTOEntity.setChallan_trans_ID("0");
        }

        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setCashLedger(tfAccountText.getText().trim());
        }
        if (!tfSaleAccountText.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setSaleLedger(tfSaleAccountText.getText().trim());
        }

        saleDTOEntity.setStockItemTreansactioDTOList(bindtableToDTO());

        if (!jTextFieldNote.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setNote(jTextFieldNote.getText().trim());
        }

        if (!jTextFieldDispatchDocNo.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setDispatchDocNo(jTextFieldDispatchDocNo.getText().trim());
        }
        if (!jTextFieldDispatchDocThrough.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setDispatchDocThrough(jTextFieldDispatchDocThrough.getText().trim());
        }

        if (!jTextFieldVatRate.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setVatRate(Double.parseDouble(jTextFieldVatRate.getText().trim()));
        }
        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setVatAmount(Double.parseDouble(jTextFieldVatAmount.getText().trim()));
        }
        if (!jTextFieldCSTRate.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setCstRate(Double.parseDouble(jTextFieldCSTRate.getText().trim()));
        }
        if (!jTextFieldCSTAmount.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setCstAmount(Double.parseDouble(jTextFieldCSTAmount.getText().trim()));
        }

        if (!labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setAmount(Double.parseDouble(labeltxtBasicAmount.getText().trim()));
        }

        if (!labeltxtRoundOffAmount.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setRoundOffAmount(Double.parseDouble(labeltxtRoundOffAmount.getText().trim()));
        }

        if (!labelFinalAmountTotal.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setFinalAmount(Double.parseDouble(labelFinalAmountTotal.getText().trim()));
        }

        // newly added on date 13-04-2014
        if (!jtextField_ExDutyPer.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setExciseDutyRate(Double.parseDouble(jtextField_ExDutyPer.getText().trim()));
        }
        if (!jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setExciseDutyAmount(Double.parseDouble(jtextField_ExDutyAmt.getText().trim()));
        }
        if (!jtextField_EdCessPer.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setEdCessRate(Double.parseDouble(jtextField_EdCessPer.getText().trim()));
        }
        if (!jtextField_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setEdCessAmount(Double.parseDouble(jtextField_EdCessAmt.getText().trim()));
        }
        if (!jtextField_H_EdCessPer.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.sethEdCessRate(Double.parseDouble(jtextField_H_EdCessPer.getText().trim()));
        }
        if (!jtextField_H_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setHedCessAmount(Double.parseDouble(jtextField_H_EdCessAmt.getText().trim()));
        }

        if (!jtextField_PONo.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setPoNo(jtextField_PONo.getText().trim());
        }

        if (!jtextField_OCNo.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setOcNo(jtextField_OCNo.getText().trim());
        }


        // remove on 3-06-2014
        if ((dateChooser_PO.getDate() != null && !tfDatePOdate.getText().trim().equalsIgnoreCase("")) || (dateChooser_PO.getDate() == null && tfDatePOdate.getText().toString().trim().equalsIgnoreCase(""))) {
            if ((dateChooser_PO.getDate() != null && !tfDatePOdate.getText().trim().equalsIgnoreCase(""))) {
                saleDTOEntity.setPoDate(Constants.simpleDateFormatDatabase.format(dateChooser_PO.getDate()).toString().trim());
            } else {
                saleDTOEntity.setPoDate("");
            }
        }



        if ((dateChooser_OCDate.getDate() != null && !tfDateOCDate.getText().toString().trim().equalsIgnoreCase("")) || (dateChooser_OCDate.getDate() == null && tfDateOCDate.getText().toString().trim().equalsIgnoreCase(""))) {
            if ((dateChooser_OCDate.getDate() != null && !tfDateOCDate.getText().toString().trim().equalsIgnoreCase(""))) {
                saleDTOEntity.setOcDate(Constants.simpleDateFormatDatabase.format(dateChooser_OCDate.getDate()).toString().trim());
            } else {
                saleDTOEntity.setOcDate("");
            }
        }

        if ((dateChooser_Date_Iss.getDate() != null && !tfDateISS.getText().toString().trim().equalsIgnoreCase("")) || (dateChooser_Date_Iss.getDate() == null && tfDateISS.getText().toString().trim().equalsIgnoreCase(""))) {
            if ((dateChooser_Date_Iss.getDate() != null && !tfDateISS.getText().toString().trim().equalsIgnoreCase(""))) {
                saleDTOEntity.setDateIssDate(Constants.simpleDateFormatDatabase.format(dateChooser_Date_Iss.getDate()).toString().trim());
            } else {
                saleDTOEntity.setDateIssDate("");
            }
        }

        if (!jtextField_Time_Iss.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setTimeIss(jtextField_Time_Iss.getText().trim());
        }

        if ((dateChooser_Date_Rem.getDate() != null && !tfDateRem.getText().toString().trim().equalsIgnoreCase("")) || (dateChooser_Date_Rem.getDate() == null && tfDateRem.getText().toString().trim().equalsIgnoreCase(""))) {
            if ((dateChooser_Date_Rem.getDate() != null && !tfDateRem.getText().toString().trim().equalsIgnoreCase(""))) {
                saleDTOEntity.setDateRemDate(Constants.simpleDateFormatDatabase.format(dateChooser_Date_Rem.getDate()).toString().trim());
            } else {
                saleDTOEntity.setDateRemDate("");
            }
        }

        if (!jtextField_Time_Rem.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setTimeRem(jtextField_Time_Rem.getText().trim());
        }

        return saleDTOEntity;
    }

    private List<StockItemTransactionDTO> bindtableToDTO() {
        List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        for (int index = 0; index < tableParti.getRowCount(); index++) {

            if (!tableParti.getValueAt(index, 1).toString().trim().equalsIgnoreCase("")) {
                StockItemTransactionDTO stockItemDTO = new StockItemTransactionDTO();
                stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
                if (!tableParti.getValueAt(index, 11).toString().trim().equalsIgnoreCase("")) {
                    stockItemDTO.setAmount(Double.parseDouble(tableParti.getValueAt(index, 11).toString()));
                }
                // newly added on date 13-04-2014

                stockItemDTO.setQuantity(Double.parseDouble(tableParti.getValueAt(index, 9).toString().trim()));
                stockItemDTO.setRate(Double.parseDouble(tableParti.getValueAt(index, 10).toString().trim()));
                for (Map.Entry<String, String> e : unitMap.entrySet()) {
                    if (tableParti.getValueAt(index, 8).toString().trim().equalsIgnoreCase(e.getKey())) {
                        stockItemDTO.setUser_unit_of_symbol(e.getValue());
                    }
                }
                stockItemDTO.setUnit_of_symbol((tableParti.getValueAt(index, 12).toString().trim()));

                if (tableParti.getValueAt(index, 3) != null) {
                    stockItemDTO.setColor(tableParti.getValueAt(index, 3).toString().trim().toLowerCase());
                } else {
                    stockItemDTO.setColor(null);
                }
//                for (Map.Entry<String, String> e : colorMap.entrySet()) {
//                    if (tableParti.getValueAt(index, 3).toString().trim().equalsIgnoreCase(e.getKey())) {
//                        stockItemDTO.setColor(e.getValue());
//                    }
//                }
                stockItemDTO.setStkpackage(tableParti.getValueAt(index, 6).toString().trim());
                stockItemDTO.setNo(Double.parseDouble(tableParti.getValueAt(index, 7).toString().trim()));
                stockItemDTO.setLength(Double.parseDouble(tableParti.getValueAt(index, 4).toString().trim()));
                stockItemDTO.setWidth(Double.parseDouble(tableParti.getValueAt(index, 5).toString().trim()));
                stockItemDTO.setThkness(Double.parseDouble(tableParti.getValueAt(index, 2).toString().trim()));



                stockItemTransactionDTOList.add(stockItemDTO);
            }
        }
        return stockItemTransactionDTOList;
    }

    private void insertSale() throws SQLException, ParseException, Exception {
        List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
        saleDTOList.add(saleDTO);
        SaleDAO.insertSaleVoucher(saleDTOList);
        AccountingVoucherHelper.updateVoucherNumber(Constants.SALE_TYPE_INDEX, (saleDTO.getReceiptNo()));
    }

    private void updateSale() throws SQLException, ParseException, Exception {
        List<SaleDTO> saleDTOList = new ArrayList<SaleDTO>();
        saleDTOList.add(saleDTO);
        SaleDAO.updateSaleVoucher(saleDTOList);
    }

    private void setFocus(java.awt.event.KeyEvent evt) {
        if (evt != null) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                currentFocusValue++;
            } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                currentFocusValue--;
            }
        }
        if (currentFocusValue < 0) {
            currentFocusValue = 0;
        }
        if (currentFocusValue > 38) {
            currentFocusValue = 38;
        }


//        if (currentFocusValue == 0) {
//            tfDatePicker.requestFocus();
//        } else if (currentFocusValue == 1) {
//            tfDatePicker.requestFocus();
//        } else if (currentFocusValue == 2) {
//            tfAccountText.requestFocus();
//        } else if (currentFocusValue == 3) {
//            tfSaleAccountText.requestFocus();
//        } else if (currentFocusValue == 4) {
//            tfStockItemText.requestFocus();
//        } else if (currentFocusValue == 5) {
//            jTextFieldQuantity.requestFocus();
//        } else if (currentFocusValue == 6) {
//            jTextFieldRate.requestFocus();
//        } else if (currentFocusValue == 7) {
//            jTextFieldAmount.requestFocus();
//        } else if (currentFocusValue == 8) {
//            jTextFieldNote.requestFocus();
//        } else if (currentFocusValue == 9) {
//            jTextFieldDispatchDocNo.requestFocus();
//        } else if (currentFocusValue == 10) {
//            jTextFieldDispatchDocThrough.requestFocus();
//        } else if (currentFocusValue == 11) {
//            jTextFieldVatRate.requestFocus();
//        } else if (currentFocusValue == 12) {
//            jTextFieldVatAmount.requestFocus();
//        }

        if (currentFocusValue == 0) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            tfAccountText.requestFocus();
        } else if (currentFocusValue == 3) {
            jtextArea_Buyer_Address.requestFocus();
        } else if (currentFocusValue == 4) {
            jtextField_Buyer_Contno.requestFocus();
        } else if (currentFocusValue == 5) {
            tfSaleAccountText.requestFocus();
        } else if (currentFocusValue == 6) {
            jtextField_CSENo.requestFocus();
        } else if (currentFocusValue == 7) {
            jtextField_CVATNo.requestFocus();
        } else if (currentFocusValue == 8) {
            jtextField_CSTNo.requestFocus();
        } else if (currentFocusValue == 9) {
            jtextField_PONo.requestFocus();
        } else if (currentFocusValue == 10) {
            tfDatePOdate.requestFocus();
        } else if (currentFocusValue == 11) {
            jtextField_OCNo.requestFocus();
        } else if (currentFocusValue == 12) {
            tfDateOCDate.requestFocus();
        } else if (currentFocusValue == 13) {
            tfStockItemText.requestFocus();
        } //        else if (currentFocusValue == 14) {
        //            jtextField_Thk.requestFocus();
        //        } else if (currentFocusValue == 15) {
        //            tfColorText.requestFocus();
        //        } else if (currentFocusValue == 16) {
        //            jTextField_SizeA.requestFocus();
        //        } else if (currentFocusValue == 17) {
        //            jTextField_SizeB.requestFocus();
        //        } 
        else if (currentFocusValue == 14) {
            jTextField_Package.requestFocus();
        } else if (currentFocusValue == 15) {
            jTextField_Nos.requestFocus();
        } else if (currentFocusValue == 16) {
            tfUnitText.requestFocus();
        } else if (currentFocusValue == 17) {
            jTextFieldQuantity.requestFocus();
        } else if (currentFocusValue == 18) {
            jTextFieldRate.requestFocus();
        } else if (currentFocusValue == 19) {
            jTextFieldAmount.requestFocus();
        } else if (currentFocusValue == 20) {
            jTextFieldNote.requestFocus();
        } else if (currentFocusValue == 21) {
            tfDateISS.requestFocus();
        } else if (currentFocusValue == 22) {
            tfDateRem.requestFocus();
        } else if (currentFocusValue == 23) {
            jTextFieldDispatchDocNo.requestFocus();
        } else if (currentFocusValue == 24) {
            jTextFieldDispatchDocThrough.requestFocus();
        } else if (currentFocusValue == 25) {
            jtextField_Time_Iss.requestFocus();
        } else if (currentFocusValue == 26) {
            jtextField_Time_Rem.requestFocus();
        } else if (currentFocusValue == 27) {
            jtextField_ExDutyPer.requestFocus();
        } else if (currentFocusValue == 28) {
            jtextField_ExDutyAmt.requestFocus();
        } else if (currentFocusValue == 29) {
            jtextField_EdCessPer.requestFocus();
        } else if (currentFocusValue == 30) {
            jtextField_EdCessAmt.requestFocus();
        } else if (currentFocusValue == 31) {
            jtextField_H_EdCessPer.requestFocus();
        } else if (currentFocusValue == 32) {
            jtextField_H_EdCessAmt.requestFocus();
        } else if (currentFocusValue == 33) {
            jtextField_Total_WithoutVAT.requestFocus();
        } else if (currentFocusValue == 34) {
            jTextFieldVatRate.requestFocus();
        } else if (currentFocusValue == 35) {
            jTextFieldVatAmount.requestFocus();
        } else if (currentFocusValue == 36) {
            jTextFieldCSTRate.requestFocus();
        } else if (currentFocusValue == 37) {
            jTextFieldCSTAmount.requestFocus();
        }

    }

    private Boolean validateData() throws Exception {
        if (!labeltxtBasicAmount.getText().trim().isEmpty() && !labelFinalAmountTotal.getText().trim().isEmpty() && !jTextFieldVatAmount.getText().trim().isEmpty()) {
            labeltxtBasicAmountResult = new BigDecimal(labeltxtBasicAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            labelFinalAmountTotalResult = new BigDecimal(labelFinalAmountTotal.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldVatAmountResult = new BigDecimal(jTextFieldVatAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldExciseDutyAmountResult = new BigDecimal(jtextField_ExDutyAmt.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldEdCessDutyAmountResult = new BigDecimal(jtextField_EdCessAmt.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldHEdCessDutyAmountResult = new BigDecimal(jtextField_H_EdCessAmt.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldCSTAmountResult = new BigDecimal(jTextFieldCSTAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        }
        Boolean flag = true;
        if ((labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) || (Double.parseDouble(labeltxtBasicAmount.getText().trim()) == 0D)) {
            JOptionPane.showMessageDialog(jMainPanel, Label.AMOUNT_NOT_VALID);
            tfDatePicker.requestFocus();
            currentFocusValue = 1;
            return false;
        } else if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("") || Double.parseDouble(jTextFieldReceiptNo.getText().trim()) == 0D) {
            JOptionPane.showMessageDialog(jMainPanel, Label.RECEIPT_NUMBER_VALUE_IS_NOT_VALID);
            jTextFieldReceiptNo.requestFocus();
            flag = false;
        } else if (accountLedgerMap.get(tfAccountText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.CASH_LEDGER_VALUE_IS_NOT_VALID);
            tfAccountText.requestFocus();
            flag = false;
        } else if (saleLedgerMap.get(tfSaleAccountText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.SALE_LEDGER_VALUE_IS_NOT_VALID);
            tfSaleAccountText.requestFocus();
            flag = false;
        } else if (jDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker.requestFocus();
            flag = false;
        } else if (tableParti.getRowCount() == 0) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_EMPTY);
            flag = false;
            tfStockItemText.requestFocus();
        } //        else {
        //        }
        else if (jTextFieldNote.getText().trim().toCharArray().length > Constants.jTextAreaCharacterLengthEXTRALARGE) {
            jTextFieldNote.requestFocus();
            throw new Exception("Note Data Exceeding " + Constants.jTextAreaCharacterLengthEXTRALARGE + " Character Limit");
        } else if (jTextFieldDispatchDocNo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldDispatchDocNo.requestFocus();
            throw new Exception("Dispatch Document Number Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldDispatchDocThrough.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldDispatchDocThrough.requestFocus();
            throw new Exception("Vehicle Number Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jtextField_CSENo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jtextField_CSENo.requestFocus();
            throw new Exception("E.C.S Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jtextField_CVATNo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jtextField_CVATNo.requestFocus();
            throw new Exception("C.VAT Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jtextField_CSTNo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jtextField_CSTNo.requestFocus();
            throw new Exception("C.CST Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jtextField_PONo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jtextField_PONo.requestFocus();
            throw new Exception("P.O.No. Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jtextField_OCNo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jtextField_OCNo.requestFocus();
            throw new Exception("O.C.No. Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (labeltxtBasicAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (labeltxtBasicAmountResult == 1) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (labelFinalAmountTotal.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (labelFinalAmountTotalResult == 1) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (Double.parseDouble(jTextFieldVatRate.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldVatRate.requestFocus();
            throw new Exception("VAT Rate Exceeding " + Constants.jTextAreaVATPERCENT + " Percent Limit");
        } else if (jTextFieldVatAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (Double.parseDouble(jTextFieldCSTRate.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldCSTRate.requestFocus();
            throw new Exception("CST Rate Exceeding " + Constants.jTextAreaVATPERCENT + " Percent Limit");
        } else if (jTextFieldCSTAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldCSTAmount.requestFocus();
            throw new Exception("CST only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (Double.parseDouble(jtextField_ExDutyPer.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jtextField_ExDutyPer.requestFocus();
            throw new Exception("Excise Duty Rate Exceeding " + Constants.jTextAreaVATPERCENT + " Percent Limit");
        } else if (jtextField_ExDutyAmt.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jtextField_ExDutyAmt.requestFocus();
            throw new Exception("Excise Duty only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (Double.parseDouble(jtextField_EdCessPer.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jtextField_EdCessPer.requestFocus();
            throw new Exception("Ed.Cess Rate Exceeding " + Constants.jTextAreaVATPERCENT + " Percent Limit");
        } else if (jtextField_EdCessAmt.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jtextField_EdCessAmt.requestFocus();
            throw new Exception("Ed.Cess only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (Double.parseDouble(jtextField_H_EdCessPer.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jtextField_H_EdCessPer.requestFocus();
            throw new Exception("H.Ed.Cess Rate Exceeding " + Constants.jTextAreaVATPERCENT + " Percent Limit");
        } else if (jtextField_H_EdCessAmt.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jtextField_H_EdCessAmt.requestFocus();
            throw new Exception("H.Ed.Cess only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldVatAmountResult == 1) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldCSTAmountResult == 1) {
            jTextFieldCSTAmount.requestFocus();
            throw new Exception("CST only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldExciseDutyAmountResult == 1) {
            jtextField_ExDutyAmt.requestFocus();
            throw new Exception("Excise Duty only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldEdCessDutyAmountResult == 1) {
            jtextField_EdCessAmt.requestFocus();
            throw new Exception("Ed.Cess only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldHEdCessDutyAmountResult == 1) {
            jtextField_H_EdCessAmt.requestFocus();
            throw new Exception("H.Ed.Cess only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } //        else if (!jtextField_PODate.getText().toString().trim().equals("")) {
        //            jtextField_PODate.requestFocus();
        //            throw new Exception("PO date is not valid ");
        //        } else if (!jtextField_OCDate.getText().toString().trim().equals("")) {
        //            jtextField_OCDate.requestFocus();
        //            throw new Exception("OC date is not valid ");
        //        }
        else if (!tfDatePOdate.getText().toString().trim().equals("") && dateChooser_PO.getDate() == null) {
            tfDatePOdate.requestFocus();
            throw new Exception("PO date is not valid ");
        } else if (!tfDateOCDate.getText().toString().trim().equals("") && dateChooser_OCDate.getDate() == null) {
            tfDateOCDate.requestFocus();
            throw new Exception("OC date is not valid ");
        } else if (!tfDateISS.getText().toString().trim().equals("") && dateChooser_Date_Iss.getDate() == null) {
            tfDateISS.requestFocus();
            throw new Exception("Iss date is not valid ");
        } else if (!tfDateRem.getText().toString().trim().equals("") && dateChooser_Date_Rem.getDate() == null) {
            tfDateRem.requestFocus();
            throw new Exception("Date Rem is not valid ");
        }

        return flag;
    }

    private void calculateAndBindExciseDutyRate() throws Exception {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jtextField_ExDutyPer.getText().trim().equalsIgnoreCase("")) {
            vatRate = 0D;
        } else {
            vatRate = Double.parseDouble(jtextField_ExDutyPer.getText().trim());
        }
        if (vatRate != 0) {
            if (labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
            }

            vatAmount = basicAmount * (vatRate / 100);
            jtextField_ExDutyAmt.setText("" + Constants.DECIMAL_FORMAT.format( Math.round(vatAmount) ));
        }

    }

    private void calculateAndBindExciseDutyAmount() {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
            vatAmount = 0D;
        } else {
            vatAmount = Double.parseDouble(jtextField_ExDutyAmt.getText().trim());
        }

        if (vatAmount != 0) {
            if (labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
            }

            if (basicAmount != 0D) {
                vatRate = (vatAmount * 100) / basicAmount;
            }
            jtextField_ExDutyPer.setText("" + Constants.DECIMAL_FORMAT.format(vatRate));
        }

    }

    private void calculateAndBind_EdCessRate() throws Exception {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jtextField_EdCessPer.getText().trim().equalsIgnoreCase("")) {
            vatRate = 0D;
        } else {
            vatRate = Double.parseDouble(jtextField_EdCessPer.getText().trim());
        }
        if (vatRate != 0) {
            if (jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_ExDutyAmt.getText().trim());
            }

            vatAmount = basicAmount * (vatRate / 100);
            jtextField_EdCessAmt.setText("" + Constants.DECIMAL_FORMAT.format( Math.round( vatAmount )));
        }

    }

    private void calculateAndBind_EdCessAmount() {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jtextField_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            vatAmount = 0D;
        } else {
            vatAmount = Double.parseDouble(jtextField_EdCessAmt.getText().trim());
        }

        if (vatAmount != 0) {
            if (jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_ExDutyAmt.getText().trim());
            }

            if (basicAmount != 0D) {
                vatRate = (vatAmount * 100) / basicAmount;
            }
            jtextField_EdCessPer.setText("" + Constants.DECIMAL_FORMAT.format(vatRate));
        }

    }

    private void calculateAndBind_H_EdCessRate() throws Exception {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jtextField_H_EdCessPer.getText().trim().equalsIgnoreCase("")) {
            vatRate = 0D;
        } else {
            vatRate = Double.parseDouble(jtextField_H_EdCessPer.getText().trim());
        }
        if (vatRate != 0) {
            if (jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_ExDutyAmt.getText().trim());
            }

            vatAmount = basicAmount * (vatRate / 100);
            jtextField_H_EdCessAmt.setText("" + Constants.DECIMAL_FORMAT.format( Math.round(vatAmount) ));
        }

    }

    private void calculateAndBind_H_EdCessAmount() {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jtextField_H_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            vatAmount = 0D;
        } else {
            vatAmount = Double.parseDouble(jtextField_H_EdCessAmt.getText().trim());
        }

        if (vatAmount != 0) {
            if (jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_ExDutyAmt.getText().trim());
            }

            if (basicAmount != 0D) {
                vatRate = (vatAmount * 100) / basicAmount;
            }
            jtextField_H_EdCessPer.setText("" + Constants.DECIMAL_FORMAT.format(vatRate));
        }

    }

    private void calculateAndBind_Total_Without_VAT_TOGUI() throws Exception {
        Double totalAmount = 0D;
        Double h_EdCessAmt = 0D;
        Double exciseDuty = 0D;
        Double edCessAmt = 0D;
        Double finalAmount = 0D;

        if (!labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
            totalAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
        }

//        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
//            vatAmount = Double.parseDouble(jTextFieldVatAmount.getText().trim());
//        }

        if (!jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
            exciseDuty = Double.parseDouble(jtextField_ExDutyAmt.getText().trim());
        }

        if (!jtextField_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            edCessAmt = Double.parseDouble(jtextField_EdCessAmt.getText().trim());
        }

        if (!jtextField_H_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            h_EdCessAmt = Double.parseDouble(jtextField_H_EdCessAmt.getText().trim());
        }


        finalAmount = totalAmount + exciseDuty + edCessAmt + h_EdCessAmt;
        jtextField_Total_WithoutVAT.setText(Constants.DECIMAL_FORMAT.format(finalAmount) + "");
    }

    private void calculateAndBindVatRate() throws Exception {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jTextFieldVatRate.getText().trim().equalsIgnoreCase("")) {
            vatRate = 0D;
        } else {
            vatRate = Double.parseDouble(jTextFieldVatRate.getText().trim());
        }
        if (vatRate != 0) {
            if (jtextField_Total_WithoutVAT.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_Total_WithoutVAT.getText().trim());
            }

            vatAmount = basicAmount * (vatRate / 100);
            jTextFieldVatAmount.setText("" + Constants.DECIMAL_FORMAT.format( Math.round(vatAmount) ));
        }

    }

    private void calculateAndBindVatAmount() {

        Double vatRate = 0D;
        Double basicAmount = 0D;
        Double vatAmount = 0D;
        if (jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            vatAmount = 0D;
        } else {
            vatAmount = Double.parseDouble(jTextFieldVatAmount.getText().trim());
        }

        if (vatAmount != 0) {
            if (jtextField_Total_WithoutVAT.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_Total_WithoutVAT.getText().trim());
            }

            if (basicAmount != 0D) {
                vatRate = (vatAmount * 100) / basicAmount;
            }
            jTextFieldVatRate.setText("" + Constants.DECIMAL_FORMAT.format(vatRate));
        }

    }

    private void calculateAndBindCSTRate() throws Exception {

        Double cstRate = 0D;
        Double basicAmount = 0D;
        Double cstAmount = 0D;
        if (jTextFieldCSTRate.getText().trim().equalsIgnoreCase("")) {
            cstRate = 0D;
        } else {
            cstRate = Double.parseDouble(jTextFieldCSTRate.getText().trim());
        }
        if (cstRate != 0) {
            if (jtextField_Total_WithoutVAT.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_Total_WithoutVAT.getText().trim());
            }

            cstAmount = basicAmount * (cstRate / 100);
            jTextFieldCSTAmount.setText("" + Constants.DECIMAL_FORMAT.format( Math.round(cstAmount) ));
        }

    }

    private void calculateAndBindCSTAmount() {

        Double cstRate = 0D;
        Double basicAmount = 0D;
        Double cstAmount = 0D;
        if (jTextFieldCSTAmount.getText().trim().equalsIgnoreCase("")) {
            cstAmount = 0D;
        } else {
            cstAmount = Double.parseDouble(jTextFieldCSTAmount.getText().trim());
        }

        if (cstAmount != 0) {
            if (jtextField_Total_WithoutVAT.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(jtextField_Total_WithoutVAT.getText().trim());
            }

            if (basicAmount != 0D) {
                cstRate = (cstAmount * 100) / basicAmount;
            }
            jTextFieldCSTRate.setText("" + Constants.DECIMAL_FORMAT.format(cstRate));
        }

    }

    private void calculateQuantity() {
        Double sizeA = 0D;
        Double sizeB = 0D;
        Double Nos = 0D;
        Double quantity = 0D;

        if (!jTextField_SizeA.getText().trim().equalsIgnoreCase("")) {
            sizeA = Double.parseDouble(jTextField_SizeA.getText().trim());
        }
        if (!jTextField_SizeB.getText().trim().equalsIgnoreCase("")) {
            sizeB = Double.parseDouble(jTextField_SizeB.getText().trim());
        }
        if (!jTextField_Nos.getText().trim().equalsIgnoreCase("")) {
            Nos = Double.parseDouble(jTextField_Nos.getText().trim());
        }

        quantity = sizeA * sizeB * Nos;

        jTextFieldQuantity.setText("" + Constants.DECIMAL_FORMAT.format(quantity));

    }

    private void calculatePartiAmount() {
        Double quantity = 0D;
        Double rate = 0D;
        Double amount = 0D;

        if (!jTextFieldQuantity.getText().trim().equalsIgnoreCase("")) {
            quantity = Double.parseDouble(jTextFieldQuantity.getText().trim());
        }
        if (!jTextFieldRate.getText().trim().equalsIgnoreCase("")) {
            rate = Double.parseDouble(jTextFieldRate.getText().trim());
        }

        amount = quantity * rate;

        jTextFieldAmount.setText("" + Constants.DECIMAL_FORMAT.format( Math.round(amount) ));

    }

    private void calculatePartiRate() throws Exception {
        Double quantity = 0D;
        Double rate = 0D;
        Double amount = 0D;

        if (!jTextFieldQuantity.getText().trim().equalsIgnoreCase("")) {
            quantity = Double.parseDouble(jTextFieldQuantity.getText().trim());
        }
        if (!jTextFieldAmount.getText().trim().equalsIgnoreCase("") && !jTextFieldAmount.getText().trim().equalsIgnoreCase(".")) {
            amount = Double.parseDouble(jTextFieldAmount.getText().trim());
        }

        if (quantity != 0D) {
            rate = amount / quantity;
        }
        jTextFieldRate.setText("" + Constants.DECIMAL_FORMAT.format(rate));

    }

    private void calculateAndBindFinalTotalTOGUI() throws Exception {
        Double totalAmount = 0D;
        Double vatAmount = 0D;
        Double cstAmount = 0D;
        Double lessBillAmount = 0D;
        Double transportAmount = 0D;
        Double finalAmount = 0D;
        Double roundOffAmount = 0D;

        if (!jtextField_Total_WithoutVAT.getText().trim().equalsIgnoreCase("")) {
            totalAmount = Double.parseDouble(jtextField_Total_WithoutVAT.getText().trim());
        }

        if (!labeltxtRoundOffAmount.getText().trim().equalsIgnoreCase("")) {
            roundOffAmount = Double.parseDouble(labeltxtRoundOffAmount.getText().trim());
        }

        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            vatAmount = Double.parseDouble(jTextFieldVatAmount.getText().trim());
        }

        if (!jTextFieldCSTAmount.getText().trim().equalsIgnoreCase("")) {
            cstAmount = Double.parseDouble(jTextFieldCSTAmount.getText().trim());
        }

        finalAmount = totalAmount + vatAmount + cstAmount - lessBillAmount - transportAmount;

        Double finalAmountWithRoundOff;
        if (gen.dto.Constants.IS_ROUND_OFF) {
            finalAmountWithRoundOff = Double.valueOf(Math.round(Double.valueOf(finalAmount)));
            System.out.println("finalAmountWithRoundOff--->>" + finalAmountWithRoundOff);
            System.out.println("Math.round(Double.valueOf(finalAmount)--->>" + Math.round(Double.valueOf(finalAmount)));
        } else {
            finalAmountWithRoundOff = Double.valueOf(finalAmount);
        }
        roundOffAmount = finalAmount - finalAmountWithRoundOff;

        System.out.println("roundOffAmount---" + roundOffAmount);

        //set round off value to hidden label
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(finalAmountWithRoundOff) + "");
        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(roundOffAmount) + "");


//        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(finalAmount) + "");
    }

    private void calculateAndBindTotalTOGUI() throws Exception {
        Double totalAmount = 0D;
        for (int index = 0; index < partiTableModel.getRowCount(); index++) {
            if (!partiTableModel.getValueAt(index, 11).toString().equalsIgnoreCase("")) {
                totalAmount += Double.parseDouble(partiTableModel.getValueAt(index, 11).toString());
            }
        }
        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(totalAmount) + "");
    }

    private void addTOJTable() {

        indexForDeletion = 0;
        while (indexForDeletion < tableParti.getRowCount()) {
            if (tableParti.getValueAt(indexForDeletion, 0) == null) {
                int row = tableParti.getRowCount();
                partiTableModel.setRowCount(row - 1);
            }
            indexForDeletion++;
        }

        if (temp == 1000) {

            int row = tableParti.getRowCount();
            partiTableModel.setRowCount(row + 1);
            tableParti.setValueAt(row + 1, row, 0);
            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 9);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 10);
            tableParti.setValueAt(tfUnitText.getText().trim(), row, 8);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 11);
            tableParti.setValueAt(labelUnitSymbol.getText().trim(), row, 12);
            tableParti.setValueAt(jcomboBoxColor.getText().trim(), row, 3);
            tableParti.setValueAt(jTextField_Package.getText().trim(), row, 6);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_Nos.getText().trim())), row, 7);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_SizeA.getText().trim())), row, 4);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_SizeB.getText().trim())), row, 5);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jtextField_Thk.getText().trim())), row, 2);
        } else {
            int row = temp;
//            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 3);
//            tableParti.setValueAt(tfUnitText.getText().trim(), row, 4);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 5);
//            tableParti.setValueAt(labelUnitSymbol.getText().trim(), row, 6);
//            tableParti.setValueAt(tfColorText.getText().trim(), row, 7);
//            tableParti.setValueAt(jTextField_Package.getText().trim(), row, 8);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_Nos.getText().trim())), row, 9);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_SizeA.getText().trim())), row, 10);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_SizeB.getText().trim())), row, 11);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jtextField_Thk.getText().trim())), row, 12);

            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 9);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 10);
            tableParti.setValueAt(tfUnitText.getText().trim(), row, 8);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 11);
            tableParti.setValueAt(labelUnitSymbol.getText().trim(), row, 12);
            tableParti.setValueAt(jcomboBoxColor.getText().trim(), row, 3);
            tableParti.setValueAt(jTextField_Package.getText().trim(), row, 6);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_Nos.getText().trim())), row, 7);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_SizeA.getText().trim())), row, 4);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextField_SizeB.getText().trim())), row, 5);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jtextField_Thk.getText().trim())), row, 2);

            temp = 1000;
            buttonAdd.setLabel(Label.BUTTON_ADD);
            tableParti.clearSelection();
            tfStockItemText.requestFocus();
        }
    }

    private void updateOnKeyType() throws Exception {
        temp = 1000;
        buttonAdd.setLabel(Label.BUTTON_ADD);
        clearFormData();

        Util.checkForEmpty(jTextFieldQuantity);
        Util.checkForEmpty(jTextFieldSquareFeet);
        Util.checkForEmpty(jTextFieldRate);
        Util.checkForEmpty(jTextFieldAmount);

        calculateAndBindTotalTOGUI();
        calculateAndBindExciseDutyRate();
        calculateAndBindExciseDutyAmount();
        calculateAndBind_EdCessRate();
        calculateAndBind_EdCessAmount();
        calculateAndBind_H_EdCessRate();
        calculateAndBind_H_EdCessAmount();
        calculateAndBind_Total_Without_VAT_TOGUI();
        calculateAndBindVatRate();
        calculateAndBindVatAmount();
        calculateAndBindCSTRate();
        calculateAndBindCSTAmount();
        calculateAndBindFinalTotalTOGUI();


    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        loadEditForm(id, "");
    }
    public void loadEditForm(String id, Date date) throws SQLException, ParseException, Exception {
        loadEditForm(id, Constants.simpleDateFormatDatabaseWithDash.format( date ));
    }
    public void loadEditForm(String id, String date) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<SaleDTO> saleDTOList = SaleDAO.getSales(idSet, "", date,"VoucherId");
        if (saleDTOList != null && !saleDTOList.isEmpty()) {
            saleDTO = saleDTOList.get(0);
            isEdit = true;
            partiTableModel.setRowCount(0);
            jTextFieldReceiptNo.setEditable(false);
            buttonDelete.setEnabled(true);
            buttonExport.setEnabled(true);
            buttonPrint.setEnabled(true);
            bindTOGUI();
            currentFocusValue = 1;
            setFocus(null);
        }
    }

    private Boolean validateStockTransaction() throws Exception {
        if (!jTextFieldQuantity.getText().trim().isEmpty() && !jTextFieldRate.getText().trim().isEmpty() && !jTextFieldAmount.getText().trim().isEmpty()) {
            jTextFieldQuantityResult = new BigDecimal(jTextFieldQuantity.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldRateResult = new BigDecimal(jTextFieldRate.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldAmountResult = new BigDecimal(jTextFieldAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        }
        if (stockItemMap.get(tfStockItemText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_VALUE_IS_NOT_VALID);
            currentFocusValue = 12;
            tfStockItemText.requestFocus();
            return false;
        } //        else {
        //        }
        else if ((jTextFieldAmount.getText().trim().equalsIgnoreCase("")) || (jTextFieldAmount.getText().trim().equalsIgnoreCase(".")) || (Double.parseDouble(jTextFieldAmount.getText().trim()) == 0D)) {
            JOptionPane.showMessageDialog(jMainPanel, Label.AMOUNT_NOT_VALID);
            currentFocusValue = 19;
            jTextFieldAmount.requestFocus();
            return false;
        } //        else {
        //        }
        //currentFocusValue = 3;
        else if (jTextFieldQuantity.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthEXTRASMALL) {
            jTextFieldQuantity.requestFocus();
            throw new Exception("Quantity Exceeding " + Constants.jTextAreaAMOUNTDIGITS + " digit Limit");
        } else if (jTextFieldQuantityResult == 1) {
            jTextFieldQuantity.requestFocus();
            throw new Exception("Quantity only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldRate.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthEXTRASMALL) {
            jTextFieldRate.requestFocus();
            throw new Exception("Rate Field Exceeding " + Constants.jTextFieldCharacterLengthEXTRASMALL + " digit Limit");
        } else if (jTextFieldRateResult == 1) {
            jTextFieldRate.requestFocus();
            throw new Exception("Rate only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldAmountResult == 1) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (unitMap.get(tfUnitText.getText().trim().toLowerCase()) == null) {
            tfUnitText.requestFocus();
            throw new Exception("Unit of Measure is not Valid");
        } //        else if (colorMap.get(jcomboBoxColor.getText().toLowerCase().trim()) == null) {
        //            jcomboBoxColor.requestFocus();
        //            throw new Exception("Color is not Valid");
        //        } 
        else {
            currentFocusValue = 12;
        }

        return true;
    }

    private void bindDTOToTransactionTable(List<SaleDTO> saleDTOTransactionList) throws SQLException {
        if (saleDTOTransactionList != null && !saleDTOTransactionList.isEmpty()) {
            transactionTableModel1.setRowCount(0);
            for (SaleDTO saleDTOEntity : saleDTOTransactionList) {
                try {
                    transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                    transactionTableModel1.setValueAt(saleDTOEntity.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                    java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTOEntity.getDate().trim());
                    transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                    transactionTableModel1.setValueAt(saleDTOEntity.getSaleLedger(), transactionTableModel1.getRowCount() - 1, 2);
                } catch (ParseException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            if (last_Number_In_TransactionList != 1) {
                transactionTableModel1.setRowCount(0);
            }
        }
        last_Number_In_TransactionList = 0;
    }

//    private void setStockItemProperties() throws Exception {
//        String stockItemName = tfStockItemText.getText().trim();
//
//        if (!stockItemName.equals("")) {
//            try {
//                StockItemTransactionDTO stockItemTransactionDTO = gen.account.stockitem.StockItemDAO.getStockItemValues(stockItemName);
//                labelUnitSymbol.setText(stockItemTransactionDTO.getUnit_of_symbol());
//                System.out.println("Symbol-------->>>>>" + labelUnitSymbol.getText());
//            } catch (SQLException ex) {
//                Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    private void setStockItemProperties() throws Exception {
        String stockItemName = tfStockItemText.getText().trim();

        if (!stockItemName.equals("")) {
            try {
                jcomboBoxColor.setText("");
                jTextField_SizeA.setText("0.0");
                jTextField_SizeB.setText("0.0");
                jtextField_Thk.setText("0.0");

                StockItemTransactionDTO stockItemTransactionDTO = gen.account.stockitem.StockItemDAO.getStockItemValues(stockItemName);
                labelUnitSymbol.setText(stockItemTransactionDTO.getUnit_of_symbol());
                System.out.println("Symbol-------->>>>>" + labelUnitSymbol.getText());


                String selected_SI_ID = "";
                selected_SI_ID = stockItemMap.get(tfStockItemText.getText().toString().toLowerCase().trim());
                List<StockItemDTO> stockItemDTOList = new ArrayList<StockItemDTO>();
                Set<String> si_ids = new HashSet<String>();
                si_ids.add(selected_SI_ID);
                stockItemDTOList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList(si_ids);
                StockItemDTO stockItemDTO = new StockItemDTO();


                for (StockItemDTO stockItemDTO1 : stockItemDTOList) {
                    stockItemDTO = stockItemDTO1;
                }

                System.out.println("===========" + stockItemDTO.equals(null));

                if (stockItemDTOList.size() > 0) {

//                    StockItemCategoryDTO stockItemCategoryDTO = stockItemDTO.getStockItemCategoryDTO();
//                    for (Map.Entry<String, String> e : categoryMap.entrySet()) {
//                        if (stockItemCategoryDTO.getCategoryID() == Integer.parseInt(e.getValue())) {
//                            System.out.println("-------------------- Catogary " + e.getKey());
////                            tfcatogaryTypeText.setText(e.getKey());
//                            jcomboBoxCatogary.setText(e.getKey());
//                        }
//                    }

                    StockItemColorDTO stockItemColorDTO = stockItemDTO.getStockItemColorDTO();
                    for (Map.Entry<String, String> e : colorMap.entrySet()) {
                        if (stockItemColorDTO.getColorID() == Integer.parseInt(e.getValue())) {
                            System.out.println("-------------------- Catogary " + e.getKey());
                            jcomboBoxColor.setText(e.getKey());
                        }
                    }

//                    StockItemFinishTypeDTO stockItemFinishTypeDTO = stockItemDTO.getStockItemFinishTypeDTO();
//                    for (Map.Entry<String, String> e : typeMap.entrySet()) {
//                        if (stockItemFinishTypeDTO.getFinishTypeID() == Integer.parseInt(e.getValue())) {
//                            System.out.println("-------------------- Catogary " + e.getKey());
//                            jcomboBoxFinishType.setText(e.getKey());
//                        }
//                    }

//                    StockItemTypeDTO stockItemTypeDTO = stockItemDTO.getStockItemTypeDTO();
//                    for (Map.Entry<String, String> e : boardTypeMap.entrySet()) {
//                        if (stockItemTypeDTO.getTypeID() == Integer.parseInt(e.getValue())) {
//                            System.out.println("-------------------- Catogary " + e.getKey());
//                            jcomboBoxBType.setText(e.getKey());
//                        }
//                    }
                }
                jTextField_SizeA.setText(stockItemDTO.getLength().toString());
                jTextField_SizeB.setText(stockItemDTO.getWidth().toString());;
                jtextField_Thk.setText(stockItemDTO.getThkness().toString());;

            } catch (SQLException ex) {
                Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    private void newButton() throws ParseException, SQLException, Exception {
        jTextFieldReceiptNo.setEditable(true);
        jTextFieldReceiptNo.setEnabled(true);
        jTextFieldSearch.setText("");
        saleDTO = new SaleDTO();
        isEdit = false;
        saleDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.SALE_TYPE_INDEX, jDateChooser.getDate()));
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);
    }

    private void clearFormData() {
        tfStockItemText.setText("");
        jcomboBoxColor.setText("");
        tfUnitText.setText("");
        jTextFieldQuantity.setText("0.0");
        jTextFieldRate.setText("0.0");
        jTextField_SizeA.setText("0.0");
        jTextField_SizeB.setText("0.0");
        jtextField_Thk.setText("0.0");
        jTextField_Package.setText("");
        jTextField_Nos.setText("0.0");
        jTextFieldAmount.setText("0.0");
        buttonParticularDelete.setEnabled(false);
    }

    private void setStockItemRate() throws SQLException, Exception {
        if (rateMap == null) {
            rateMap = new HashMap<String, Map<String, String>>();
        }
        if (rateMap.get(tfAccountText.getText().trim()) == null) {
            Map<String, String> rateMapEntity = new HashMap<String, String>();
            if (accountLedgerMap.get(tfAccountText.getText().toLowerCase().trim()) != null && !accountLedgerMap.isEmpty()) {
                rateMapEntity = SaleDAO.getRateByCustomerID(Long.parseLong(accountLedgerMap.get(tfAccountText.getText().toLowerCase().trim())));
            }
            System.out.println("rateMapEntity===============>>>" + rateMapEntity.size());
            if (rateMapEntity != null) {
                this.rateMap.put(tfAccountText.getText().toLowerCase().trim(), rateMapEntity);
            }
        }
        if (rateMap.get(tfAccountText.getText().toLowerCase().trim()) != null) {
            System.out.println("rateMap.get(tfAccountText.getText().toLowerCase().trim()===============>>>" + rateMap.get(tfAccountText.getText().toLowerCase().trim()));
            if (rateMap.get(tfAccountText.getText().toLowerCase().trim()).get(tfStockItemText.getText().toLowerCase().trim()) != null) {
                jTextFieldRate.setText(rateMap.get(tfAccountText.getText().toLowerCase().trim()).get(tfStockItemText.getText().toLowerCase().trim()));
            }
        }
    }
    //To Do Code to Export 
    String path = "";

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
                    return name.endsWith(".xml") || name.endsWith(".xml")
                            || name.endsWith(".xml") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return ".xml";
                }
            });

            int r = chooser.showSaveDialog(SaleForm.this);

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

    private void dataExport() {

        path = "";
        SaleForm.MessageBox msgBox = new SaleForm.MessageBox();
        Set<String> saleSet = new HashSet<String>();
        saleSet.add(jTextFieldReceiptNo.getText().trim());
        BufferedWriter out = null;
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    // TODO add your handling code here:
                    Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
                    IDMapSet.put("Sale", saleSet);

                    String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
                    System.out.println("------------------------->>XMLCODE:" + xmlCode);

                    File file = new File(path + ".xml");
                    path = file.getPath();
                    out = new BufferedWriter(new FileWriter(file));
                    out.write(xmlCode);
                    out.close();

                    JOptionPane.showMessageDialog(this, "Export Successful");

                } catch (Exception ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Export failure");
                }
            }
        }

    }
    int row = 0;
    private JLabel lblDateOfIssue;
    private JDateChooser dateChooser_Date_Iss;
    private JLabel lblDateRemainder;
    private JDateChooser dateChooser_Date_Rem;
    private JLabel lblTimeOfIssue;
    private JTextField jtextField_Time_Iss;
    private JLabel lblTimeRem;
    private JTextField jtextField_Time_Rem;
    private JLabel lblExciseDuty;
    private JTextField jtextField_ExDutyPer;
    private JTextField jtextField_ExDutyAmt;
    private JLabel lblEdcess;
    private JTextField jtextField_EdCessPer;
    private JTextField jtextField_EdCessAmt;
    private JLabel lblHedcess;
    private JTextField jtextField_H_EdCessPer;
    private JTextField jtextField_H_EdCessAmt;
    private JLabel lblNewLabel_3;
    private JTextField jtextField_Total_WithoutVAT;
    private JTextField jTextFieldNote;
    private JLabel lblThickness;
    private JTextField jtextField_Thk;
    private JLabel lblColor;
    private JLabel lblSizea;
    private JTextField jTextField_SizeA;
    private JLabel lblSizeb;
    private JTextField jTextField_SizeB;
    private JLabel lblPackage;
    private JTextField jTextField_Package;
    private JLabel lblNumbers;
    private JTextField jTextField_Nos;
    private JLabel lblUnit;
    private JPanel panel;
    private JLabel lblEcsno;
    private JTextField jtextField_CSENo;
    private JLabel lblCvatno;
    private JTextField jtextField_CVATNo;
    private JLabel lblCcstno;
    private JTextField jtextField_CSTNo;
    private JLabel lblPono_1;
    private JTextField jtextField_PONo;
    private JLabel lblPodate;
    private JLabel lblOcno;
    private JTextField jtextField_OCNo;
    private JLabel lblOcdate_1;
    private JTextArea jtextArea_Buyer_Address;
    private JTextField jtextField_Buyer_Contno;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JTextField jcomboBoxColor;
    private JComboBox jcomboBoxUnit;
    private JDateChooser dateChooser_PO;
    private JDateChooser dateChooser_OCDate;
    private JLabel lblCstRate;
    private JTextField jTextFieldCSTRate;
    private JTextField jTextFieldCSTAmount;

    private void showPopup(MouseEvent me) {
        if (popUpMenu.isPopupTrigger(me)) {
            Point p = me.getPoint();
            row = jTableTransactionList.rowAtPoint(p);
            int col = jTableTransactionList.columnAtPoint(p);
            if (row != -1 && col == 0 || col == 1 || col == 2) {
                Copy_Transaction.setText("Copy Transaction");
                Copy_Transaction.addActionListener(this);
                popUpMenu.show(jTableTransactionList, p.x, p.y);
            }
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        try {
            newButton();
            String cmd = e.getActionCommand();
            String id = jTableTransactionList.getValueAt(row, 0).toString();
            if (cmd.equals("Copy Transaction")) {
                Set<String> idSet = new HashSet<String>();
                idSet.add(id);
                List<SaleDTO> saleDTOList = SaleDAO.getSales(idSet, "");
                if (saleDTOList != null && !saleDTOList.isEmpty()) {
                    try {
                        saleDTO = saleDTOList.get(0);
                        partiTableModel.setRowCount(0);
                        jTextFieldReceiptNo.setEditable(true);
                        buttonDelete.setEnabled(true);
                        buttonExport.setEnabled(true);
                        buttonPrint.setEnabled(true);
                        bindDTOtoGUIWithoutReceiptNo();
                        bindDTOtoJtable();
                        currentFocusValue = 1;
                        setFocus(null);
                    } catch (ParseException ex) {
                        Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(SaleForm.this, ex.getMessage());
        }
    }

    private void initTransactionList() throws SQLException, Exception {
        saleDTOTransactionList = SaleDAO.getTransactionList("");
        bindDTOToTransactionTable(saleDTOTransactionList);
    }

    private void setOld_Del_PO_Trans_Value() {

        gen.dto.Constants.DELIVERY_TYPE = jTextFieldNote.getText().trim();
        gen.dto.Constants.PO_NUMBER = jtextField_PONo.getText().trim();;
        gen.dto.Constants.TRANSPORT_TYPE = jTextFieldDispatchDocThrough.getText().trim();;

    }
}
