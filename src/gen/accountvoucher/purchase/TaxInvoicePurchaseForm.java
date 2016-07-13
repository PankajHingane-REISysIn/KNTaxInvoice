/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.purchase;

/**
 *
 * @author pc5
 */
import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.TagsHelper1;
import gen.account.StockItemFormation.StockItemDTO;
import gen.account.ledger.LedgerDAO;
import gen.account.ledger.LedgerDTO;
import gen.account.ledger.LedgerForm;
import gen.account.stockitem.StockItemDAO;
import gen.account.stockitem.StockItemForm1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.accountvoucher.sale.SaleDAO;
import gen.accountvoucher.sale.SaleForm;
import gen.display.report.DayBook;
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
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author pc5
 */
public class TaxInvoicePurchaseForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private PurchaseDTO purchaseDTO;
    private JTextField tfAccountText, tfPurchaseAccountText, tfStockItemText, tfStockItemUnit;
    public JTextField tfDatePicker = null;
    private JTextField tfDatePOdate = null;
    private JTextField tfDateOCDate = null;
    private Vector<String> cashLedgerVector = new Vector<String>();
    private Vector<String> PurchaseLedgerVector = new Vector<String>();
    private Vector<String> stockItemVector = new Vector<String>();
    private Vector<String> stockItemUnitVector = new Vector<String>();
    private List<PurchaseDTO> purchaseDTOTransactionList;
    private DefaultTableModel partiTableModel;
    private Boolean isEdit = false;
    //variables For Calculation
    private Map<String, String> accountLedgerMap;
    private Map<String, String> purchaseLedgerMap;
    private Map<String, String> stockItemMap;
    private Map<String, String> unitMap;
    //variable for focus
    private int currentFocusValue = 0;
    //time stamp
    private Long ledgerTimeStamp;
    private Long stockItemTimeStamp;
    private int temp = 1000;
    private int indexP = 0;
    private int indexForDeletion = 0;
    private JPopupMenu popUpMenu = new JPopupMenu();
    private JMenuItem Copy_Transaction = new JMenuItem();
    private Boolean isTransactionTableLoad = false;
    private int result = 0;
    private int jTextFieldVatAmountResult = 0;
    int jTextFieldExciseDutyAmountResult = 0;
    int jTextFieldEdCessDutyAmountResult = 0;
    int jTextFieldHEdCessDutyAmountResult = 0;
    int jTextFieldCSTAmountResult = 0;
    private int labeltxtBasicAmountResult = 0;
    private int labelFinalAmountTotalResult = 0;
    private int jTextFieldQuantityResult = 0;
    private int jTextFieldSqFeetResult = 0;
    private int jTextFieldRateResult = 0;
    private int jTextFieldAmountResult = 0;
    private static int last_Number_In_TransactionList = 0;

    public TaxInvoicePurchaseForm(String s, Dimension d) {
        try {
            initComponents();
            purchaseDTO = new PurchaseDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
        }
    }

    public TaxInvoicePurchaseForm(PurchaseDTO purchaseDTO, Boolean isEdit) {
        try {
            initComponents();
            this.isEdit = isEdit;
            isTransactionTableLoad = true;
            this.purchaseDTO = purchaseDTO;
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
        }
    }

    private void initComponents() {
        setTitle("Purchase Transaction Form");
        setFrameIcon(new ImageIcon(TaxInvoicePurchaseForm.class.getResource("/images/Kasturi-logo-1.png")));
        setBounds(100, 100, 1344, 683);

        jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Purchase Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel.setLayout(new MigLayout("", "[][0px:225px:225px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][0px:225px:225px,grow,shrink 50,fill][][grow][0px:50px:50px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:300px:300px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:5px:5px,grow,shrink 50,fill]"));

        labelReceiptNo = new JLabel("Purchase No.");
        jMainPanel.add(labelReceiptNo, "cell 0 0,alignx leading");

        jTextFieldReceiptNo = new JTextField();
        jTextFieldReceiptNo.setEnabled(false);
        jTextFieldReceiptNo.setForeground(new Color(0, 0, 0));
        jMainPanel.add(jTextFieldReceiptNo, "cell 1 0");
        jTextFieldReceiptNo.setTransferHandler(null);
        jTextFieldReceiptNo.setColumns(20);

        labelDate = new JLabel("Date");
        jMainPanel.add(labelDate, "cell 3 0");

        jDateChooser = new JDateChooser();
        jMainPanel.add(jDateChooser, "cell 4 0");

        panel = new JPanel();
        jMainPanel.add(panel, "cell 6 0 3 6,grow");
        panel.setLayout(new MigLayout("", "[0px:50px:50px,grow,shrink 50,fill][grow][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblNewLabel_1 = new JLabel("E.C.S.No.");
        panel.add(lblNewLabel_1, "cell 0 0,alignx trailing");

        jtextField_CSENo = new JTextField();
        panel.add(jtextField_CSENo, "cell 1 0,growx");
        jtextField_CSENo.setColumns(10);

        lblNewLabel_2 = new JLabel("C.VAT.No.");
        panel.add(lblNewLabel_2, "cell 0 1,alignx trailing");

        jtextField_CVATNo = new JTextField();
        panel.add(jtextField_CVATNo, "cell 1 1,growx");
        jtextField_CVATNo.setColumns(10);

        lblNewLabel_3 = new JLabel("C.CST.No.");
        panel.add(lblNewLabel_3, "cell 0 2,alignx trailing");

        jtextField_CSTNo = new JTextField();
        panel.add(jtextField_CSTNo, "cell 1 2,growx");
        jtextField_CSTNo.setColumns(10);

        lblNewLabel_4 = new JLabel("P.O.No.");
        panel.add(lblNewLabel_4, "cell 0 3,alignx trailing");

        jtextField_PONo = new JTextField();
        panel.add(jtextField_PONo, "cell 1 3,growx");
        jtextField_PONo.setColumns(10);

        lblNewLabel_6 = new JLabel("P.O.Date");
        panel.add(lblNewLabel_6, "cell 2 3");

        dateChooser_PODate = new JDateChooser();
        panel.add(dateChooser_PODate, "cell 3 3,grow");

        lblNewLabel_5 = new JLabel("O.C.No.");
        panel.add(lblNewLabel_5, "cell 0 4,alignx trailing");

        jtextField_OCNo = new JTextField();
        panel.add(jtextField_OCNo, "cell 1 4,growx");
        jtextField_OCNo.setColumns(10);

        lblNewLabel_7 = new JLabel("O.C.Date");
        panel.add(lblNewLabel_7, "cell 2 4");

        dateChooser_OCDate = new JDateChooser();
        panel.add(dateChooser_OCDate, "cell 3 4,grow");

        labelSearch = new JLabel("Search");
        jMainPanel.add(labelSearch, "flowx,cell 9 0");

        labelPurchaseReference = new JLabel("Purchase Ref.");
        jMainPanel.add(labelPurchaseReference, "cell 0 1,alignx leading");

        jTextFieldPurchaseReference = new JTextField();
        jMainPanel.add(jTextFieldPurchaseReference, "cell 1 1,growx");
        jTextFieldPurchaseReference.setColumns(10);

        labelAccount = new JLabel("Supplier's Name");
        jMainPanel.add(labelAccount, "cell 0 2,alignx trailing");

        jComboBoxAccount = new JComboBox();
        jComboBoxAccount.setPrototypeDisplayValue("xxxxxx");
        jComboBoxAccount.setEditable(true);
        jMainPanel.add(jComboBoxAccount, "cell 1 2,growx");

        labelPurchaseLedger = new JLabel("Purchases");
        jMainPanel.add(labelPurchaseLedger, "cell 3 2,alignx trailing");

        jComboBoxPurchaseLedger = new JComboBox();
        jComboBoxPurchaseLedger.setPrototypeDisplayValue("xxxxxx");
        jComboBoxPurchaseLedger.setEditable(true);
        jMainPanel.add(jComboBoxPurchaseLedger, "cell 4 2,growx");

        JScrollPane pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 9 2 1 19,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer();
        nameRenderer.setHorizontalAlignment(JLabel.LEFT);

        String col1[] = {Label.PURCHASE_NO, Label.DATE_TRANSACTION, Label.SUPPLIER_NAME};
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
        jTableTransactionList.setModel(transactionTableModel1);
        JTableHeader header1 = jTableTransactionList.getTableHeader();
        jTableTransactionList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        transactionTableModel1 = (DefaultTableModel) jTableTransactionList.getModel();
        transactionTableModel1.setRowCount(0);
        transactionTableModel1.setColumnCount(3);
        jTableTransactionList.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableTransactionList.getColumnModel().getColumn(0).setCellRenderer(renderer);
        jTableTransactionList.getColumnModel().getColumn(1).setCellRenderer(renderer);
        jTableTransactionList.getColumnModel().getColumn(2).setCellRenderer(nameRenderer);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(transactionTableModel1);
        jTableTransactionList.setRowSorter(sorter);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableTransactionList.setFont(font);
        pane1.setViewportView(jTableTransactionList);

        lblAddress = new JLabel("Address");
        jMainPanel.add(lblAddress, "cell 0 4");

        jtextArea_Buyer_Address = new JTextArea();
        jtextArea_Buyer_Address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtextArea_Buyer_Address.setLineWrap(true);
        JScrollPane scroll1 = new JScrollPane(jtextArea_Buyer_Address);
        jMainPanel.add(scroll1, "cell 1 4,grow");
//        jtextArea_Buyer_Address = new JTextArea();
//        jMainPanel.add(jtextArea_Buyer_Address, "cell 1 4,grow");

        lblContactNo = new JLabel("Contact No.");
        jMainPanel.add(lblContactNo, "cell 0 5,alignx leading");

        jtextField_Buyer_Contno = new JTextField();
        jMainPanel.add(jtextField_Buyer_Contno, "cell 1 5,growx");
        jtextField_Buyer_Contno.setColumns(10);

        JPanel panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 0 6 7 6,grow");
        //panel_1.setLayout(new MigLayout("", "[40px:40px:40px,grow,shrink 50,fill][0px:225px:225px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:75px:75px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));
        panel_1.setLayout(new MigLayout("", "[40px:40px:40px,grow,shrink 50,fill][0px:225px:225px,grow,shrink 50,fill][0px:5px:5px,grow,shrink 50,fill][0px:115px:115px,grow,shrink 50,fill][0px:5px:5px,grow,shrink 50,fill][0px:115px:115px,grow,shrink 50,fill][0px:5px:5px,grow,shrink 50,fill][0px:115px:115px,grow,shrink 50,fill][0px:180px:180px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelSN = new JLabel("SN");
        panel_1.add(labelSN, "cell 0 0");

        labelParticulars = new JLabel("Particulars");
        panel_1.add(labelParticulars, "cell 1 0");

        labelQuantity = new JLabel("Quantity");
        panel_1.add(labelQuantity, "cell 3 0");

        labelSquareFeet = new JLabel("Unit");
        panel_1.add(labelSquareFeet, "cell 5 0");

        labelRate = new JLabel("Rate");
        panel_1.add(labelRate, "cell 7 0");

        labelAmount = new JLabel("Amount");
        panel_1.add(labelAmount, "cell 8 0");

        jComboBoxParti = new JComboBox();
        jComboBoxParti.setPrototypeDisplayValue("xxxxxx");
        jComboBoxParti.setEditable(true);
        panel_1.add(jComboBoxParti, "cell 1 1,growx");

        jTextFieldQuantity = new JTextField();
        panel_1.add(jTextFieldQuantity, "cell 3 1,growx");
        jTextFieldQuantity.setTransferHandler(null);
        jTextFieldQuantity.setColumns(10);

        jComboBoxUnit = new JComboBox();
        jComboBoxUnit.setPrototypeDisplayValue("xxxxxx");
        jComboBoxUnit.setEditable(true);
        panel_1.add(jComboBoxUnit, "cell 5 1,growx");
//        jTextFieldSquareFeet.setColumns(10);

        jTextFieldRate = new JTextField();
        panel_1.add(jTextFieldRate, "cell 7 1,growx");
        jTextFieldRate.setTransferHandler(null);
        jTextFieldRate.setColumns(10);

        jTextFieldAmount = new JTextField();
        panel_1.add(jTextFieldAmount, "cell 8 1,growx");
        jTextFieldAmount.setTransferHandler(null);
        jTextFieldAmount.setColumns(10);

        JScrollPane pane = new JScrollPane();
        //panel_1.add(pane, "cell 0 2 9 4,grow");
        panel_1.add(pane, "cell 0 2 9 5,grow");

//        String col[] = {Label.S_N, Label.PARTICULARS, Label.LENGTH, Label.WIDTH, Label.THICKNESS, Label.QUANTITY, Label.SQUARE_FEET, Label.RATE, Label.AMOUNT};
//        String data[][] = {{"", "", "", "", "", "", "", "", ""}};
        String col[] = {Label.S_N, Label.PARTICULARS, Label.QUANTITY, Label.UNIT, Label.RATE, Label.AMOUNT};
        String data[][] = {{"", "", "", "", "", ""}};
        partiTableModel = new DefaultTableModel(data, col);
        tableParti = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };

//        tableParti.setModel(new DefaultTableModel(
//        	new Object[][] {
//        		{"", "", "", "", "", ""},
//        	},
//        	new String[] {
//        		"A", "B", "F", "G", "H", "I"
//        	}
//        ));
     
        tableParti.setModel(partiTableModel);
        JTableHeader header = tableParti.getTableHeader();
        tableParti.getTableHeader().setReorderingAllowed(false);
        header.setBackground(Color.yellow);
        header.setFont(font);
        tableParti.setFont(font);
        partiTableModel = (DefaultTableModel) tableParti.getModel();
        partiTableModel.setRowCount(0);
        partiTableModel.setColumnCount(6);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        tableParti.setFont(font);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableParti.getColumnModel().getColumn(0).setMaxWidth(40);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(240);
        tableParti.getColumnModel().getColumn(1).setMaxWidth(240);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(130);
        tableParti.getColumnModel().getColumn(2).setMaxWidth(130);
        tableParti.getColumnModel().getColumn(3).setPreferredWidth(130);
        tableParti.getColumnModel().getColumn(3).setMaxWidth(130);
        tableParti.getColumnModel().getColumn(4).setPreferredWidth(120);
        tableParti.getColumnModel().getColumn(4).setMaxWidth(130);
        tableParti.getColumnModel().getColumn(5).setPreferredWidth(100);
        pane.setViewportView(tableParti);

        buttonAdd = new JButton("Add");
        buttonAdd.setMnemonic('D');
        jMainPanel.add(buttonAdd, "cell 7 9 2 1");

        buttonParticularDelete = new JButton("Delete");
        buttonParticularDelete.setMnemonic('L');
        buttonParticularDelete.setEnabled(false);
        jMainPanel.add(buttonParticularDelete, "cell 7 10 2 1");

        JPanel panel_2 = new JPanel();
        jMainPanel.add(panel_2, "cell 0 12 7 8,grow");
        panel_2.setLayout(new MigLayout("", "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:165px:165px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][grow][0px:20px:20px,grow,shrink 0,fill]", "[0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:5px:5px,grow,fill][0px:20px:20px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill]"));

        labelNote = new JLabel("Note");
        panel_2.add(labelNote, "cell 0 0,aligny top");
        JScrollPane scroll = new JScrollPane();
        panel_2.add(scroll, "cell 0 1 1 4,grow");

        jTextFieldNote = new JTextArea();
        scroll.setViewportView(jTextFieldNote);
        jTextFieldNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextFieldNote.setLineWrap(true);

        lblNewLabel_10 = new JLabel("Excise Duty(%)");
        lblNewLabel_10.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(lblNewLabel_10, "cell 3 1,alignx trailing");

        jtextField_ExDutyPer = new JTextField();
        panel_2.add(jtextField_ExDutyPer, "cell 4 1,growx");
        jtextField_ExDutyPer.setColumns(10);

        jtextField_ExDutyAmt = new JTextField();
        jtextField_ExDutyAmt.setText((String) null);
        jtextField_ExDutyAmt.setColumns(10);
        panel_2.add(jtextField_ExDutyAmt, "cell 5 1,growx");

        lblNewLabel_9 = new JLabel("Ed.Cess(%)");
        lblNewLabel_9.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(lblNewLabel_9, "cell 3 2,alignx trailing");

        labelDispatchDocNo = new JLabel("Payment Mode");
        labelDispatchDocNo.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelDispatchDocNo, "cell 1 0,growx");

        jTextFieldPaymentMode = new JTextField();
        panel_2.add(jTextFieldPaymentMode, "cell 2 0,growx");
        jTextFieldPaymentMode.setColumns(10);

        labelBasicAmount = new JLabel("Amount");
        labelBasicAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelBasicAmount, "cell 3 0");

        labeltxtBasicAmount = new JLabel("0");
        panel_2.add(labeltxtBasicAmount, "cell 5 0");

        jtextField_EdCessPer = new JTextField();
        jtextField_EdCessPer.setColumns(10);
        panel_2.add(jtextField_EdCessPer, "cell 4 2,growx");

        jtextField_EdCessAmt = new JTextField();
        jtextField_EdCessAmt.setText((String) null);
        jtextField_EdCessAmt.setColumns(10);
        panel_2.add(jtextField_EdCessAmt, "cell 5 2,growx");

        lblNewLabel_8 = new JLabel("H.Ed.Cess(%)");
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(lblNewLabel_8, "cell 3 3,alignx trailing");

        jtextField_H_EdCessPer = new JTextField();
        jtextField_H_EdCessPer.setColumns(10);
        panel_2.add(jtextField_H_EdCessPer, "cell 4 3,growx");

        jtextField_H_EdCessAmt = new JTextField();
        jtextField_H_EdCessAmt.setText((String) null);
        jtextField_H_EdCessAmt.setColumns(10);
        panel_2.add(jtextField_H_EdCessAmt, "cell 5 3,growx");

        lblTotal = new JLabel("Total");
        lblTotal.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(lblTotal, "cell 3 4");

        jtextField_Total_WithoutVAT = new JTextField();
        panel_2.add(jtextField_Total_WithoutVAT, "cell 5 4,growx");
        jtextField_Total_WithoutVAT.setColumns(10);

        labelVatRate = new JLabel("VAT Rate(%)");
        labelVatRate.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelVatRate, "cell 3 5");

        jTextFieldVatRate = new JTextField();
        panel_2.add(jTextFieldVatRate, "cell 4 5,growx");
        jTextFieldVatRate.setTransferHandler(null);
        jTextFieldVatRate.setColumns(10);

        jTextFieldVatAmount = new JTextField();
        panel_2.add(jTextFieldVatAmount, "cell 5 5,growx");
        jTextFieldVatAmount.setTransferHandler(null);
        jTextFieldVatAmount.setColumns(10);

        labeltxtRoundOffAmount = new JLabel("");

        lblCstRate = new JLabel("CST Rate(%)");
        lblCstRate.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(lblCstRate, "cell 3 7,alignx trailing");

        jTextFieldCSTRate = new JTextField();
        jTextFieldCSTRate.setColumns(10);
        panel_2.add(jTextFieldCSTRate, "cell 4 7,growx");

        jTextFieldCSTAmount = new JTextField();
        jTextFieldCSTAmount.setText((String) null);
        jTextFieldCSTAmount.setColumns(10);
        panel_2.add(jTextFieldCSTAmount, "cell 5 7,growx");

        labelFinalAmount = new JLabel("Total");
        labelFinalAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelFinalAmount, "cell 3 8");

        labelFinalAmountTotal = new JLabel("FinalAmount");
        labelFinalAmountTotal.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelFinalAmountTotal, "cell 5 8");

//        JPanel panel_3 = new JPanel();
//        jMainPanel.add(panel_3, "cell 0 18 8 4,grow");
//        panel_3.setLayout(new MigLayout(
//                "",
//                "[0px:750px:750px,grow,shrink 50,fill][]",
//                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JPanel panel_3 = new JPanel();
        jMainPanel.add(panel_3, "cell 0 20 7 2,grow");
        panel_3.setLayout(new MigLayout("", "[0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill]"));

        buttonBack = new JButton("Back");
        buttonBack.setMnemonic('B');
        panel_3.add(buttonBack, "flowx,cell 0 0");

        buttonNew = new JButton("New");
        buttonNew.setMnemonic('N');
        panel_3.add(buttonNew, "cell 1 0");

        buttonPrint = new JButton("Print");
        buttonPrint.setMnemonic('P');
        panel_3.add(buttonPrint, "cell 2 0");

        buttonExport = new JButton("Export");
        buttonExport.setMnemonic('E');
        panel_3.add(buttonExport, "cell 3 0");

        buttonDelete = new JButton("Delete");
        buttonDelete.setMnemonic('T');
        panel_3.add(buttonDelete, "cell 4 0");

        buttonAddLedger = new JButton("Ledger");
        buttonAddLedger.setMnemonic('G');
        panel_3.add(buttonAddLedger, "cell 5 0");

        buttonAddItem = new JButton("Item");
        buttonAddItem.setMnemonic('I');
        panel_3.add(buttonAddItem, "cell 6 0");

        buttonSubmit = new JButton("Submit");
        buttonSubmit.setMnemonic('S');
        panel_3.add(buttonSubmit, "cell 7 0");

        jTextFieldSearch = new JTextField();
        jMainPanel.add(jTextFieldSearch, "cell 9 0");
        jTextFieldSearch.setColumns(25);

        buttonPrevious_DOWN_Transactions = new JButton("<<");
        jMainPanel.add(buttonPrevious_DOWN_Transactions, "flowx,cell 9 21");

        buttonNext_DOWN_Transactions = new JButton(">>");
        jMainPanel.add(buttonNext_DOWN_Transactions, "cell 9 21");

        JLabel lblNewLabel = new JLabel("");
        jMainPanel.add(lblNewLabel, "cell 9 0");

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
                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameClosing(evt);
                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                //  throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                jComboBoxAccount.hidePopup();
                jComboBoxPurchaseLedger.hidePopup();
                jComboBoxParti.hidePopup();
            }
        });

        //Toolkit.getDefaultToolkit().getSystemEventQueue().push(new CopyPasteOperation(jTextFieldReceiptNo));

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
                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldReceiptNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldReceiptNoKeyPressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldReceiptNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldReceiptNo);
                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

//	rtretertretre;
        jTextFieldPurchaseReference.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPurchaseReferenceFocusGained(evt);
            }
        });

        jTextFieldPurchaseReference.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPurchaseReferenceKeyPressed(evt);
            }
        });

        jTextFieldPurchaseReference.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                //Util.filterCharacter(evt, jTextFieldPurchaseReference);
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
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePicker.selectAll();
                currentFocusValue = 1;
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        dateChooser_PODate.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePOdate = (JTextField) dateChooser_PODate.getComponent(1);

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
                currentFocusValue = 11;
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
                currentFocusValue = 13;
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost-------------");
                if (!tfDateOCDate.getText().equalsIgnoreCase("")) {
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
                //throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 3;
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
                // throw new UnsupportedOperationException("Not supported yet.");
                jComboBoxAccount.hidePopup();

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
                            // call transactions in which selected ledger is present
                            String ledger_Id = ledgerDTO.getLedgerID();
                            purchaseDTOTransactionList = PurchaseDAO.getTransactionList();
                            bindDTOToTransactionTable(purchaseDTOTransactionList);
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
                        purchaseDTOTransactionList = PurchaseDAO.getTransactionList();
                        bindDTOToTransactionTable(purchaseDTOTransactionList);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        jComboBoxPurchaseLedger.setEditable(
                true);
        tfPurchaseAccountText = (JTextField) jComboBoxPurchaseLedger.getEditor().getEditorComponent();

        tfPurchaseAccountText.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyTyped(final KeyEvent e) {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                String text = tfPurchaseAccountText.getText();
                                if (text.length() == 0) {
                                    jComboBoxPurchaseLedger.hidePopup();
                                    setSaleLedgetModel(new DefaultComboBoxModel(PurchaseLedgerVector), "");
                                } else {
                                    DefaultComboBoxModel m = Util.getSuggestedModel(PurchaseLedgerVector, text);
                                    if (m.getSize() == 0 || hide_flag) {
                                        jComboBoxPurchaseLedger.hidePopup();
                                        hide_flag = false;
                                    } else {
                                        setSaleLedgetModel(m, text);
                                        jComboBoxPurchaseLedger.showPopup();
                                    }

                                }
                            }
                        });

                    }
                });

        tfPurchaseAccountText.addKeyListener(
                new KeyAdapter() {
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

        tfPurchaseAccountText.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        currentFocusValue = 6;
                        String text = tfPurchaseAccountText.getText();
                        if (text.length() == 0) {
                            jComboBoxPurchaseLedger.hidePopup();
                            setSaleLedgetModel(new DefaultComboBoxModel(PurchaseLedgerVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(PurchaseLedgerVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxPurchaseLedger.hidePopup();
                                hide_flag = false;
                            } else {
                                setSaleLedgetModel(m, text);
                                jComboBoxPurchaseLedger.showPopup();
                            }

                        }
                        tfPurchaseAccountText.selectAll();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                        jComboBoxPurchaseLedger.hidePopup();
                    }
                });

        jtextArea_Buyer_Address.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jTextField_Buyer_AddressFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextArea_Buyer_Address.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });


        jtextField_Buyer_Contno.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jtextField_Buyer_ContnoFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextField_Buyer_Contno.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });


        jtextField_CSENo.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jtextField_CSENoFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextField_CSENo.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });


        jtextField_CVATNo.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jtextField_CVATNoFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextField_CVATNo.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });


        jtextField_CSTNo.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jtextField_CSTNoFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextField_CSTNo.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });


        jtextField_PONo.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jtextField_PONoFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextField_PONo.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

        jtextField_OCNo.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jtextField_OCNoFocusGained(evt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        //jTextField_Buyer_AddressFocusLost(evt);
                    }
                });

        jtextField_OCNo.addKeyListener(
                new java.awt.event.KeyAdapter() {
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
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        //calculateSquareFeet();
                        try {
                            // calculateQuantity();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

        jComboBoxParti.setEditable(true);
        tfStockItemText = (JTextField) jComboBoxParti.getEditor().getEditorComponent();
        tfStockItemText.addKeyListener(
                new KeyAdapter() {
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

        tfStockItemText.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(final KeyEvent event) {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                    if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                        if (tfStockItemText.getText().trim().equalsIgnoreCase("")) {
                                            //currentFocusValue = 8;
                                            jTextFieldNote.requestFocus();
                                        } else {
                                            //currentFocusValue = 5;
                                            jTextFieldQuantity.requestFocus();
                                        }
                                    } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                        tfDateOCDate.requestFocus();
                                    }
                                    //setFocus(event);
                                }
                            }
                        });
                    }
                });

        tfStockItemText.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                        currentFocusValue = 14;
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
                        // no need of zero if stock item is empty
                        if (!tfStockItemText.getText().toString().trim().equalsIgnoreCase("")) {
                            // if stockitem selected by mouse and jump  on amount buy mouse 
                            // so quantity should be 0 
                            if (jTextFieldQuantity.getText().toString().trim().equalsIgnoreCase("") && jTextFieldQuantity.getText().toString().trim() != null) {
//			if (jTextFieldQuantity.getText().toString().trim().equalsIgnoreCase("")) {
                                jTextFieldQuantity.setText(Double.toString(0D));
//			}
                            } else if (Double.parseDouble(jTextFieldQuantity.getText().toString().trim()) == 0D) {
                                // if we use this condition above then it give number format ecxeption when empty
                                jTextFieldQuantity.setText(Double.toString(0D));
                            }
                        }
                        if (!tfStockItemText.getText().trim().equalsIgnoreCase("")) {
                            try {
                                setStockItemProperties();
                            } catch (Exception ex) {
                                Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                            }
                        }
                        jComboBoxParti.hidePopup();
                    }
                });

        tfStockItemText.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        try {
                            setStockItemProperties();
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

        jComboBoxUnit.setEditable(true);
        tfStockItemUnit = (JTextField) jComboBoxUnit.getEditor().getEditorComponent();
        tfStockItemUnit.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyTyped(final KeyEvent e) {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                String text = tfStockItemUnit.getText();
                                if (text.length() == 0) {
                                    jComboBoxUnit.hidePopup();
                                    setPartiUnitModel(new DefaultComboBoxModel(stockItemUnitVector), "");
                                } else {
                                    DefaultComboBoxModel m = Util.getSuggestedModel(stockItemUnitVector, text);
                                    if (m.getSize() == 0 || hide_flag) {
                                        jComboBoxUnit.hidePopup();
                                        hide_flag = false;
                                    } else {
                                        setPartiUnitModel(m, text);
                                        jComboBoxUnit.showPopup();
                                    }
                                }
                                int code = e.getKeyCode();
                                if (code == KeyEvent.VK_ENTER) {
                                    if (!stockItemUnitVector.contains(text)) {
                                        stockItemUnitVector.addElement(text);
                                        Collections.sort(stockItemUnitVector);
                                        setPartiUnitModel(Util.getSuggestedModel(stockItemUnitVector, text), text);
                                    }
                                    hide_flag = true;

                                } else if (code == KeyEvent.VK_ESCAPE) {
                                    hide_flag = true;
                                } else if (code == KeyEvent.VK_RIGHT) {
                                    for (int i = 0; i < stockItemUnitVector.size(); i++) {
                                        String str = stockItemUnitVector.elementAt(i);
                                        if (str.startsWith(text)) {
                                            jComboBoxUnit.setSelectedIndex(-1);
                                            tfStockItemUnit.setText(str);
                                            return;
                                        }
                                    }
                                }
                            }
                        });
                    }
                });

        tfStockItemUnit.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(final KeyEvent event) {
                        EventQueue.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                jTextFieldSquareFeetKeyPressed(event);
                            }
                        });
                    }
                });

        tfStockItemUnit.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        try {
                            jTextFieldSquareFeetFocusGained(e);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        jTextFieldSquareFeetFocusLost(e);
                    }
                });

//        tfStockItemUnit.addMouseListener(
//                new java.awt.event.MouseAdapter() {
//                    @Override
//                    public void mouseClicked(java.awt.event.MouseEvent evt) {
//                        try {
//                            setStockItemProperties();
//                        } catch (Exception ex) {
//                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                        }
//                    }
//                });


//        jTextFieldLngth.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                Util.checkForEmpty(jTextFieldLngth);
//            }
//        });
//
//        jTextFieldWidth.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                Util.checkForEmpty(jTextFieldWidth);
//            }
//        });
//
//        jTextFieldThickness.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                Util.checkForEmpty(jTextFieldThickness);
//            }
//        });


        jTextFieldQuantity.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jTextFieldQuantityFocusGained(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldQuantityFocusLost(evt);
                    }
                });

        jTextFieldQuantity.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jTextFieldQuantityKeyPressed(evt);
                    }

                    @Override
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        try {
                            Util.filterCharacter(evt, jTextFieldQuantity);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        try {
//                    calculateSquareFeet();`
                            calculatePartiAmount();
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

//        jComboBoxUnit.addFocusListener(
//                new java.awt.event.FocusAdapter() {
//                    @Override
//                    public void focusGained(java.awt.event.FocusEvent evt) {
//                        try {
//                            jTextFieldSquareFeetFocusGained(evt);
//                        } catch (Exception ex) {
//                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void focusLost(java.awt.event.FocusEvent evt) {
//                        jTextFieldSquareFeetFocusLost(evt);
//                    }
//                });

//        jComboBoxUnit.addKeyListener(
//                new java.awt.event.KeyAdapter() {
//                    @Override
//                    public void keyPressed(java.awt.event.KeyEvent evt) {
//                        jTextFieldSquareFeetKeyPressed(evt);
//                    }
//
//                    @Override
//                    public void keyTyped(java.awt.event.KeyEvent evt) {
//                        try {
//                            Util.filterCharacter(evt, jComboBoxUnit);
//                        } catch (Exception ex) {
//                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                        }
//                    }
//
//                    @Override
//                    public void keyReleased(java.awt.event.KeyEvent evt) {
//                        try {
//                            calculatePartiAmount();
//                        } catch (Exception ex) {
//                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                        }
//                    }
//                });


        jTextFieldRate.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jTextFieldRateFocusGained(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldRateFocusLost(evt);
                    }
                });

        jTextFieldRate.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jTextFieldRateKeyPressed(evt);
                    }

                    @Override
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        try {
                            Util.filterCharacter(evt, jTextFieldRate);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        try {
                            calculatePartiAmount();
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

        jTextFieldAmount.addFocusListener(
                new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        try {
                            jTextFieldAmountFocusGained(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        jTextFieldAmountFocusLost(evt);
                    }
                });

        jTextFieldAmount.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        try {
                            jTextFieldAmountKeyPressed(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        try {
                            Util.filterCharacter(evt, jTextFieldAmount);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }

                    @Override
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        try {
                            calculatePartiRate();
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

        buttonAdd.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            buttonAddActionPerformed(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                        }
                    }
                });

        buttonAdd.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        buttonAddKeyPressed(evt);
                    }
                });

        tableParti.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tablePartiMouseClicked(evt);
                    }
                });

        tableParti.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tablePartiKeyPressed(evt);
                    }
                });

        jTableTransactionList.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        try {
                            jTableTransactionListMouseClicked(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                        JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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

        jTextFieldPaymentMode.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPaymentModeFocusGained(evt);
            }
        });

        jTextFieldPaymentMode.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPaymentModeKeyPressed(evt);
            }
        });

        jtextField_ExDutyPer.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jtextField_ExDutyPerFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    //calculatePartiAmount();
                    System.out.println(" jtextField_ExDutyPer  Release ------- ");
//                    calculateAndBindExciseDutyRate();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
//                    calculateAndBindTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
//                    calculateAndBindExciseDutyAmount();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
//                    calculateAndBind_EdCessRate();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
//                    calculateAndBind_EdCessAmount();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculatePartiAmount();
//                    calculateAndBind_H_EdCessRate();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculatePartiAmount();
//                    calculateAndBind_H_EdCessAmount();
                    calculateAndBind_Total_Without_VAT_TOGUI();
                    calculateAndBindVatRate();
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    //calculatePartiAmount();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindVatRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindVatAmount();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindCSTRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindCSTAmount();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

//        jTextFieldVatRate.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                try {
//                    jTextFieldVatRateFocusGained(evt);
//                } catch (Exception ex) {
//                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                jTextFieldVatRateFocusLost(evt);
//            }
//        });
//
//        jTextFieldVatRate.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jTextFieldVatRateKeyPressed(evt);
//            }
//
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//                try {
//                    Util.filterCharacter(evt, jTextFieldVatRate);
//                } catch (Exception ex) {
//                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                try {
//                    calculateAndBindVatRate();
//                    calculateAndBindFinalTotalTOGUI();
//                } catch (Exception ex) {
//                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                }
//            }
//        });
//
//        jTextFieldVatAmount.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                try {
//                    jTextFieldVatAmountFocusGained(evt);
//                } catch (Exception ex) {
//                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                jTextFieldVatAmountFocusLost(evt);
//            }
//        });
//
//        jTextFieldVatAmount.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jTextFieldVatAmountKeyPressed(evt);
//            }
//
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//                try {
//                    Util.filterCharacter(evt, jTextFieldVatAmount);
//                } catch (Exception ex) {
//                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                }
//            }
//
//            @Override
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                try {
//                    calculateAndBindVatAmount();
//                    calculateAndBindFinalTotalTOGUI();
//                } catch (Exception ex) {
//                    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
//                }
//            }
//        });

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldSearchKeyReleased(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonAddLedger.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddLedgerActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonAddLedger.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
//		    jTextFieldPaymentMode.requestFocus();
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonAddItem.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddItemActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonAddItem.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonBackActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonBack.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldCSTAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
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
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonDelete.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonParticularDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonParticularDeleteActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonPrintActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonPrint.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNewActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonNew.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonExportActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
            }
        });

        buttonExport.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
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
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonNext_DOWN_Transactions.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNextTransactionsActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
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
                try {
                    buttonPreviousTransactionsActionPerformed(evt);




                } catch (Exception ex) {
                    Logger.getLogger(TaxInvoicePurchaseForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
                }
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
            initStockItemUnit();
        }
        setFocus(null);
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException, Exception {
        MainClass.setstaticvar();
    }

    private void jTextFieldReceiptNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 1;
    }

    private void jTextFieldPurchaseReferenceFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 2;
        jTextFieldPurchaseReference.selectAll();
    }

    private void jTextFieldReceiptNoKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
            this.dispose();
        }
    }

    private void jTextFieldPurchaseReferenceKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextField_Buyer_AddressFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 4;
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
        currentFocusValue = 5;
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
        currentFocusValue = 7;
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
        currentFocusValue = 8;
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
        currentFocusValue = 9;
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
        currentFocusValue = 10;
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
        currentFocusValue = 12;
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

    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        // TODO add your handling code here:
        currentFocusValue = 15;
        Util.checkForZero(jTextFieldQuantity);
        jTextFieldQuantity.selectAll();
    }

    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        Util.checkForEmpty(jTextFieldQuantity);
    }

    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfStockItemText.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextFieldSquareFeetFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        try {
            // TODO add your handling code here:
            currentFocusValue = 16;
            String text = tfStockItemUnit.getText();
            if (text.length() == 0) {
                jComboBoxUnit.hidePopup();
                setPartiUnitModel(new DefaultComboBoxModel(stockItemUnitVector), "");
            } else {
                DefaultComboBoxModel m = Util.getSuggestedModel(stockItemUnitVector, text);
                if (m.getSize() == 0 || hide_flag) {
                    jComboBoxUnit.hidePopup();
                    hide_flag = false;
                } else {
                    setPartiUnitModel(m, text);
                    jComboBoxUnit.showPopup();
                }

            }
            tfStockItemUnit.selectAll();
        } catch (Exception ex) {
            Logger.getLogger(TaxInvoicePurchaseForm.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void jTextFieldSquareFeetFocusLost(java.awt.event.FocusEvent evt) {
    }

    private void jTextFieldSquareFeetKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        // TODO add your handling code here:
        currentFocusValue = 17;
        Util.checkForZero(jTextFieldRate);
        jTextFieldRate.selectAll();
    }

    private void jTextFieldRateFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
//        Util.checkForEmpty(jTextFieldRate);
    }

    private void jTextFieldRateKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
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
                    if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabilty(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.PURCHASE_TYPE_INDEX)) {
                        jTextFieldReceiptNo.setText("");
                    }
                } else {
                    jTextFieldReceiptNo.requestFocus();
                    jTextFieldReceiptNo.setText("");
                    throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
                }
            }
            if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                jTextFieldReceiptNo.setText(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.PURCHASE_TYPE_INDEX) + "");
            }
        }
    }

    private void jTextFieldAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        // TODO add your handling code here:
        currentFocusValue = 18;
        Util.checkForZero(jTextFieldAmount);
        jTextFieldAmount.selectAll();
    }

    private void jTextFieldAmountFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldAmount);
    }

    private void jTextFieldAmountKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
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
        // TODO add your handling code here:
        partiAdd();
    }

    private void partiAdd() throws Exception {
        if (validateStockTransaction()) {
            addTOJTable();
            clearFormData();
            Util.checkForEmpty(jTextFieldQuantity);
//            Util.checkForEmpty(jComboBoxUnit);
            Util.checkForEmpty(jTextFieldRate);
            Util.checkForEmpty(jTextFieldAmount);

            calculateAndBindTotalTOGUI();
            calculateAndBindVatRate();
            calculateAndBindFinalTotalTOGUI();
        }
    }

    private void partiDelete() throws Exception {

        int count = 1;
        indexForDeletion = tableParti.getSelectedRow();
        DefaultTableModel tablePartiModel = (DefaultTableModel) tableParti.getModel();
        tablePartiModel.removeRow(indexForDeletion);
        int row = tablePartiModel.getRowCount();
        if (tableParti.getRowCount() > 0) {
            for (int i = 0; i < row; i++) {
                tablePartiModel.setValueAt(count, i, 0);
                count++;
            }
        }

        calculateAndBindTotalTOGUI();
        calculateAndBindVatRate();
        calculateAndBindVatAmount();
        calculateAndBindFinalTotalTOGUI();
        clearFormData();
        buttonAdd.setLabel(Label.BUTTON_ADD);
        temp = 1000;
        tfStockItemText.requestFocus();
        tableParti.clearSelection();
    }

    private void buttonAddKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldAmount.requestFocus();
        }
    }

    private void tablePartiMouseClicked(java.awt.event.MouseEvent evt) {
        if (tableParti.getRowCount() > 0) {
            indexP = tableParti.getSelectedRow();
            if (indexP > -1) {
                Object o = tableParti.getValueAt(indexP, 0);
                if (o != null) {
                    temp = tableParti.getSelectedRow();
                    tfStockItemText.setText(tableParti.getValueAt(temp, 1).toString());
//                    jTextFieldLength.setText(tableParti.getValueAt(temp, 2).toString());
//                    jTextFieldWidth.setText(tableParti.getValueAt(temp, 3).toString());
//                    jTextFieldThickness.setText(tableParti.getValueAt(temp, 4).toString());
                    jTextFieldQuantity.setText(tableParti.getValueAt(temp, 2).toString());
//                    jComboBoxUnit.setText(tableParti.getValueAt(temp, 3).toString());
                    tfStockItemUnit.setText(tableParti.getValueAt(temp, 3).toString());
                    jTextFieldRate.setText(tableParti.getValueAt(temp, 4).toString());
                    jTextFieldAmount.setText(tableParti.getValueAt(temp, 5).toString());
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
            indexP = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                if (tableParti.getValueAt(indexP, 0) == null) {
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
            indexP = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                j = 0;
                while (j < tableParti.getRowCount()) {
                    if (tableParti.getValueAt(j, 1) == null) {
                        if (indexP == j) {
                            flag = 1;
                        }
                        DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                        tableModel.setRowCount(tableParti.getRowCount() - 1);
                    }
                    j++;
                }
            }
            if (flag == 0) {
                if (indexP == tableParti.getRowCount() - 1) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);
                    if (tableParti.getRowCount() == 0) {
                        tfStockItemText.requestFocus();
                    }
                } else {
                    j = indexP + 1;
                    while (j < tableParti.getRowCount()) {
                        tableParti.setValueAt(tableParti.getValueAt(j, 1), j - 1, 1);
                        tableParti.setValueAt(tableParti.getValueAt(j, 2), j - 1, 2);
                        tableParti.setValueAt(tableParti.getValueAt(j, 3), j - 1, 3);
                        tableParti.setValueAt(tableParti.getValueAt(j, 4), j - 1, 4);
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
        loadEditForm(id);
    }

    private void jTextFieldNoteFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 19;
        jTextFieldNote.selectAll();
    }

    private void jTextFieldNoteKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldPaymentModeFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 20;
        jTextFieldPaymentMode.selectAll();
    }

    private void jTextFieldPaymentModeKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("UUUUUUUUUUUUUUUUU" + currentFocusValue);
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

//    private void jTextFieldVatRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        // TODO add your handling code here:
//        currentFocusValue = 15;
//        Util.checkForZero(jTextFieldVatRate);
//        jTextFieldVatRate.selectAll();
//    }
//
//    private void jTextFieldVatRateFocusLost(java.awt.event.FocusEvent evt) {
//        Util.checkForEmpty(jTextFieldVatRate);
//    }
//
//    private void jTextFieldVatRateKeyPressed(java.awt.event.KeyEvent evt) {
//        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            setFocus(evt);
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            setFocus(evt);
//        }
//    }
//
//    private void jTextFieldVatAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        // TODO add your handling code here:
//        currentFocusValue = 16;
//        Util.checkForZero(jTextFieldVatAmount);
//        jTextFieldVatAmount.selectAll();
//    }
//
//    private void jTextFieldVatAmountFocusLost(java.awt.event.FocusEvent evt) {
//        Util.checkForEmpty(jTextFieldVatAmount);
//    }
//
//    private void jTextFieldVatAmountKeyPressed(java.awt.event.KeyEvent evt) {
//        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            //  buttonSubmitActionPerformed(null);
//            setFocus(evt);
//            buttonSubmit.requestFocus();
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            setFocus(evt);
//        }
//    }
    private void jtextField_ExDutyPerFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 21;
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
        currentFocusValue = 22;
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
        currentFocusValue = 23;
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
        currentFocusValue = 24;
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
        currentFocusValue = 25;
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
        currentFocusValue = 26;
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
        currentFocusValue = 27;
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

//    private void jTextFieldRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 18;
//        Util.checkForZero(jTextFieldRate);
//        jTextFieldRate.selectAll();
//    }
//
//    private void jTextFieldRateFocusLost(java.awt.event.FocusEvent evt) {
//        Util.checkForEmpty(jTextFieldRate);
//    }
//
//    private void jTextFieldRateKeyPressed(java.awt.event.KeyEvent evt) {
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            setFocus(evt);
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            tableParti.requestFocus();
//        }
//    }
    private void jTextFieldVatRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 28;
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
        currentFocusValue = 29;
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
        currentFocusValue = 30;
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
        currentFocusValue = 31;
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
            //btnGroupAlter_BackActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableTransactionList.getRowCount() > 0) {
                jTableTransactionList.requestFocus();
                jTableTransactionList.changeSelection(0, 0, false, false);
            }
        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws Exception {

        List<PurchaseDTO> purchaseList = new ArrayList<PurchaseDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (PurchaseDTO purchaseDTOEntity : purchaseDTOTransactionList) {
            if (purchaseDTOEntity.getPurchaseLedger().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                purchaseList.add(purchaseDTOEntity);
            }
        }
        bindDTOToTransactionTable(purchaseList);





    }

    private void buttonAddLedgerActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container
                != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
            LedgerForm f1 = new LedgerForm();
            desktopPane.add(f1);
            Dimension desktopSize = getDesktopPane().getSize();
            Dimension jInternalFrameSize = f1.getSize();
            int width = (desktopSize.width - jInternalFrameSize.width) / 2;
            int height = (desktopSize.height - jInternalFrameSize.height) / 2;
            f1.setLocation(width, height);
            f1.setVisible(true);
        }
    }

    private void buttonAddItemActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container
                != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
//            StockItemForm1 form = new StockItemForm1();
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
        // TODO add your handling code here:
        try {
            getDesktopPane().setLayout(new CardLayout());
            MainClass.setstaticvar();
            MainClass m = new MainClass();
            m.menuselection(4);
            TaxInvoicePurchaseForm.this.setClosed(true);




        } catch (Exception ex) {
            Logger.getLogger(TaxInvoicePurchaseForm.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
        }
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        try {

            // this ledgerList is use to send ledgerList for creationg Ledger 
            ArrayList<LedgerDTO> ledgerDTOList = new ArrayList<LedgerDTO>();

            // finding all groups
            Map<String, String> group_Map = new HashMap<String, String>();
            List<String> groupNames = new ArrayList<String>();
            group_Map = gen.account.groupDTODAO.GroupDAO.getGroupName(groupNames, false);

            if (accountLedgerMap != null && !tfAccountText.getText().toString().trim().isEmpty() && !accountLedgerMap.containsKey(tfAccountText.getText().toString().trim().toLowerCase())) {
                // getting Group_id for Cash Account
                for (Map.Entry<String, String> e : group_Map.entrySet()) {
                    if (e.getKey().equalsIgnoreCase("Sundry Creditors")) {
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

            // TODO add your handling code here:
            if (validateData()) {
                submit();
                tfDatePicker.requestFocus();
                jComboBoxAccount.hidePopup();
                jComboBoxParti.hidePopup();
                jComboBoxPurchaseLedger.hidePopup();
                clearFormData();
                temp = 1000;
                buttonAdd.setText("ADD");




            }
        } catch (Exception ex) {
            Logger.getLogger(SaleForm.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        System.out.println("purchaseDTO.getTrans_ID()----------------->>" + purchaseDTO.getTrans_ID());
        PurchaseDAO.deleteTransaction(Long.parseLong(purchaseDTO.getTrans_ID()));
        JOptionPane.showMessageDialog(this, "Transaction deleted completly");
        newButton();
        clearFormData();
        temp = 1000;
        buttonAdd.setText("ADD");
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxPurchaseLedger.hidePopup();
    }

    private void buttonParticularDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        if (tableParti.isRowSelected(indexForDeletion)) {
            buttonParticularDelete.setVisible(true);
        }
        partiDelete();

    }

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (buttonDelete.isEnabled()) {
//	    Map parameter = new HashMap();
//	    SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
//
//	    parameter.put("trans_date", Constants.DATE_FORMATER.format(jDateChooser.getDate()).toString().trim());
//
//
//	    String totalamount = "" + labelFinalAmountTotal.getText().trim();
//	    String vatrate = "" + jTextFieldVatRate.getText().trim();
//	    String vatamount = "" + jTextFieldVatAmount.getText().trim();
//
//
//	    parameter.put("totalamount", totalamount);
//	    parameter.put("vatrate", vatrate);
//	    parameter.put("vatamount", vatamount);
//	    parameter.put("Header", "PURCHASE");
//	    parameter.put("ReceiptNo", "Purchase No  :");
//	    parameter.put("Date", "Date:");
//	    parameter.put("LedgerName", "Ledger Name :");
//
//	    parameter.put("SrNo", "Sr No.");
//	    parameter.put("StockItem", Label.PARTICULARS);
//	    parameter.put("Qty", Label.QUANTITY);
//	    parameter.put("Size", Label.SQUARE_FEET);
//	    parameter.put("Rate", Label.RATE);
//	    parameter.put("Amount", Label.AMOUNT);
//
//
//	    String id = jTextFieldReceiptNo.getText().toString();
//	    String trans_id = "";
//	    try {
//		try {
//		    trans_id = loadEditFormForPrint(id);
//		} catch (SQLException ex) {
//		    Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	    } catch (ParseException ex) {
//		Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//	    }
//
//	    PrintAllReport.printPurchaseReport(String.valueOf(trans_id), parameter);
            HashMap parameter = new HashMap();
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("trans_date", saDa.format(jDateChooser.getDate()).toString().trim());
            parameter.put("S.N. Header", "S.N.");
            parameter.put("Particulars Header", "Particulars");
            parameter.put("Check No Header", "Quantity");
            parameter.put("Sq.Feet Header", "Sq.Feet");
            parameter.put("Note Header", "Sq.Feet");
            parameter.put("Amount Header", "Amount");
            parameter.put("LedgerName Header", tfAccountText.getText().toString().trim());
            parameter.put("ReceiptNo Header", jTextFieldReceiptNo.getText().toString().trim());
            parameter.put("Making", "Rate");

            System.out.println("Todays Date" + saDa.format(jDateChooser.getDate()).toString().trim());

            parameter.put("Final AmountHeader", labelFinalAmount.getText().toString().trim());
            parameter.put("Final Amount", labelFinalAmountTotal.getText().toString().trim());

            parameter.put("Final AmountHeader", labelFinalAmount.getText().toString().trim());
            parameter.put("Final Amount", labelFinalAmountTotal.getText().toString().trim());

            parameter.put("TotalAmountHeader", labelBasicAmount.getText().toString().trim());
            parameter.put("TotalAmount", labeltxtBasicAmount.getText().toString().trim());

            parameter.put("VatHeader", "VAT : " + " " + jTextFieldVatRate.getText().toString().trim() + " %");
            parameter.put("VatAmount", jTextFieldVatAmount.getText().toString().trim());


            PrintAllReport.printPurchaseReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
//            PrintAllReport.printPurchaseReport(String.valueOf(trans_id), parameter);

        }
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxPurchaseLedger.hidePopup();
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            newButton();
            clearFormData();
            temp = 1000;
            buttonAdd.setText("ADD");
            tfDatePicker.requestFocus();
            jComboBoxAccount.hidePopup();
            jComboBoxParti.hidePopup();
            jComboBoxPurchaseLedger.hidePopup();




        } catch (ParseException ex) {
            Logger.getLogger(SaleForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaleForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        dataExport();

    }

    private void buttonNextTransactionsActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, Exception {
        gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
        System.out.println("Value of the row call----------------------" + gen.dto.Constants.OFFSET_VALUE);
        last_Number_In_TransactionList = 1;
        initTransactionList();
    }

    private void buttonPreviousTransactionsActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
        System.out.println("Value of the row call----------------------" + gen.dto.Constants.OFFSET_VALUE);
        initTransactionList();
    }
    // GUI fiels declaration
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
    private JLabel labelPurchaseReference;
    private JTextField jTextFieldPurchaseReference;
    private JLabel labelDate;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private JLabel labelAccount;
    private JComboBox jComboBoxAccount;
    private JLabel labelPurchaseLedger;
    private JComboBox jComboBoxPurchaseLedger;
    private JLabel labelSN;
    private JLabel labelParticulars;
    private JComboBox jComboBoxParti;
    private JLabel labelQuantity;
    private JTextField jTextFieldQuantity;
    private JLabel labelSquareFeet;
    private JComboBox jComboBoxUnit;
    private JLabel labelRate;
    private JTextField jTextFieldRate;
    private JLabel labelAmount;
    private JTextField jTextFieldAmount;
    private JButton buttonAdd;
    private JLabel labelNote;
    private JTextArea jTextFieldNote;
    private JLabel labelDispatchDocNo;
    private JTextField jTextFieldDispatchDocNo;
    private JLabel labelPaymentMode;
    private JTextField jTextFieldPaymentMode;
    private JLabel labelDispatchDocThrough;
    private JTextField jTextFieldDispatchDocThrough;
    private JLabel labelBasicAmount;
    private JLabel labeltxtBasicAmount;
    private JLabel labelVatRate;
    private JTextField jTextFieldVatRate;
    private JLabel labeltxtRoundOffAmount;
    private JTextField jTextFieldVatAmount;
    private JLabel labelLessBill;
    private JTextField jTextFieldLessBill;
    private JLabel labelTransport;
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
    private JButton buttonParticularDelete;
    private JButton buttonExport;
    private JButton buttonPrevious_UP_Transactions;
    private JButton buttonNext_UP_Transactions;
    private JButton buttonNext_DOWN_Transactions;
    private JButton buttonPrevious_DOWN_Transactions;
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JTable jTableTransactionList;
    private JTable tableParti;
    private DefaultTableModel transactionTableModel1;

    /////////////////// private methods for functionality/////////////////////////////////
    private void initilize() throws SQLException, ParseException, Exception {
        initilizeGUIComponents();
        initVariables();
        bindTOGUI();
    }

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }
    private boolean hide_flag = false;

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
        jComboBoxPurchaseLedger.setModel(mdl);
        jComboBoxPurchaseLedger.setSelectedIndex(-1);
        jComboBoxPurchaseLedger.showPopup();
        tfPurchaseAccountText.setText(str);
    }

    private void setPartiModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxParti.setModel(mdl);
        jComboBoxParti.setSelectedIndex(-1);
        jComboBoxParti.showPopup();
        tfStockItemText.setText(str);
    }

    private void setPartiUnitModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxUnit.setModel(mdl);
        jComboBoxUnit.setSelectedIndex(-1);
        jComboBoxUnit.showPopup();
        tfStockItemUnit.setText(str);
    }

    private void initVariables() throws Exception {
        initLedger();
        initStockItem();
        initStockItemUnit();
        initTransactionList();
        if (!isEdit) {
            purchaseDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.PURCHASE_TYPE_INDEX));
        }

        ledgerTimeStamp = Constants.LEDGER_TIME_STAMP;
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
        buttonParticularDelete.setEnabled(false);

    }

    private void initLedger() throws Exception {
        List<String> groups = new ArrayList<String>();
//        groups.add(Constants.SALES_ACCOUNT);
//        groups.add(Constants.PURCHASE_ACCOUNT);
        groups.add(Constants.BANK_ACCOUNT);
        groups.add(Constants.CASH_IN_HAND);
        groups.add(Constants.SUNDRY_CREDITORS);
        groups.add(Constants.SUNDRY_DEBTORS);
        accountLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, true);
        groups = new ArrayList<String>();
        groups.add(Constants.PURCHASE_ACCOUNT);
        purchaseLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, true);

        cashLedgerVector.clear();
        for (String str : accountLedgerMap.keySet()) {
            cashLedgerVector.add(str);
        }

        Collections.sort(
                cashLedgerVector,
                new Comparator<String>() {
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        PurchaseLedgerVector.clear();
        for (String str : purchaseLedgerMap.keySet()) {
            PurchaseLedgerVector.add(str);
        }

        Collections.sort(
                PurchaseLedgerVector,
                new Comparator<String>() {
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        purchaseLedgerMap = Util.getSmallCaseMap(purchaseLedgerMap);
        accountLedgerMap = Util.getSmallCaseMap(accountLedgerMap);
    }

    private void initStockItem() throws Exception {
        stockItemMap = StockItemDAO.getStockItemsFromGroupName(null, false);
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

    private void initStockItemUnit() throws Exception {
        unitMap = gen.account.unitofmeasure.UnitOfMeasureDAO.getUOMNameIDMap();
        stockItemUnitVector.clear();
        for (String str : unitMap.keySet()) {
            stockItemUnitVector.add(str);
        }
        Collections.sort(
                stockItemUnitVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        unitMap = Util.getSmallCaseMap(unitMap);
    }

    private void bindTOGUI() throws Exception {
        gen.dto.Constants.OFFSET_VALUE = 0L;
        Long number = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.PURCHASE_TYPE_INDEX, Constants.DEBIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        Long remaining = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.PURCHASE_TYPE_INDEX, Constants.DEBIT) % gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));

        if (remaining == 0L) {
            gen.dto.Constants.OFFSET_VALUE = Math.abs((number - 1) * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        } else {
            gen.dto.Constants.OFFSET_VALUE = Math.abs(number * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        }

        bindDTOtoGUI();
        bindDTOtoJtable();
        if (!isEdit || isTransactionTableLoad) {
            initTransactionList();
        }
        isTransactionTableLoad = false;
    }

    private void bindDTOtoGUI() throws Exception {
        tfAccountText.setText(purchaseDTO.getCashLedger());
        tfPurchaseAccountText.setText(purchaseDTO.getPurchaseLedger());
        jTextFieldReceiptNo.setText(purchaseDTO.getReceiptNo().toString());
        jTextFieldPurchaseReference.setText(purchaseDTO.getPurchaseReference().toString());
        jTextFieldNote.setText(purchaseDTO.getNote());
        jTextFieldPaymentMode.setText(purchaseDTO.getPaymentMode());
        jTextFieldVatAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getVatAmount()) + "");
        jTextFieldCSTAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getCstAmount()) + "");
        jTextFieldVatRate.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getVatRate()) + "");
        jTextFieldCSTRate.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getCstRate()) + "");
        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getAmount()) + "");
        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getRoundOfAmount()) + "");
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getFinalAmount()) + "");
        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(purchaseDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(purchaseDTO.getDate().trim()));
        }

        //set all value to zero
        if (purchaseDTO.getStockItemTreansactioDTOList().isEmpty() || purchaseDTO.getStockItemTreansactioDTOList() == null) {
            jTextFieldAmount.setText("0.0");
            jTextFieldRate.setText("0.0");
            jTextFieldQuantity.setText("0.0");
        }

        // newly added on date 13-04-2014
        jtextArea_Buyer_Address.setText(purchaseDTO.getLedgerDTO().getLedger_Address());
        jtextField_Buyer_Contno.setText(purchaseDTO.getLedgerDTO().getLedger_ContactNo());
        jtextField_CSENo.setText(purchaseDTO.getLedgerDTO().getLedger_ECSNumber());
        jtextField_CVATNo.setText(purchaseDTO.getLedgerDTO().getLedger_CVATNumber());
        jtextField_CSTNo.setText(purchaseDTO.getLedgerDTO().getLedger_SaleTaxNo());

        System.out.println(" ++++++++ " + purchaseDTO.getPoDate());
        // remove on 3-06-2014
        if (purchaseDTO.getPoDate() != null && !purchaseDTO.getPoDate().trim().equalsIgnoreCase("")) {
            if (!isEdit) {
                dateChooser_PODate.setDate((java.util.Date) Constants.DATE_FORMATER.parse(purchaseDTO.getPoDate().trim()));
                tfDatePOdate.setText("");
            } else {
                dateChooser_PODate.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(purchaseDTO.getPoDate().trim()));
            }
        } else {
            tfDatePOdate.setText("");
        }

        if (purchaseDTO.getOcDate() != null && !purchaseDTO.getOcDate().trim().equalsIgnoreCase("")) {
            if (!isEdit) {
                dateChooser_OCDate.setDate((java.util.Date) Constants.DATE_FORMATER.parse(purchaseDTO.getOcDate().trim()));
                tfDateOCDate.setText("");
            } else {
                dateChooser_OCDate.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(purchaseDTO.getOcDate().trim()));
            }
        } else {
            tfDateOCDate.setText("");
        }


        jtextField_PONo.setText(purchaseDTO.getPoNo());
        jtextField_OCNo.setText(purchaseDTO.getOcNo());


        jtextField_ExDutyPer.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getExciseDutyRate()).toString());
        jtextField_ExDutyAmt.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getExciseDutyAmount()).toString());

        jtextField_EdCessPer.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getEdCessRate()).toString());
        jtextField_EdCessAmt.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getEdCessAmount()).toString());

        jtextField_H_EdCessPer.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.gethEdCessRate()).toString());
        jtextField_H_EdCessAmt.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getHedCessAmount()).toString());
        jtextField_Total_WithoutVAT.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getTotal_Without_Vat()).toString());

        calculateAndBind_Total_Without_VAT_TOGUI();

    }

    private void bindDTOtoJtable() throws Exception {
        try {
            int index = 1;
            List<StockItemTransactionDTO> stockItemTransactionDTOList = purchaseDTO.getStockItemTreansactioDTOList();
            if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
                for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                    partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
//                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getLength()), partiTableModel.getRowCount() - 1, 2);
//                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getWidth()), partiTableModel.getRowCount() - 1, 3);
//                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getThkness()), partiTableModel.getRowCount() - 1, 4);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 2);
//                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getSqFeet()), partiTableModel.getRowCount() - 1, 3);

                    for (Map.Entry<String, String> e : unitMap.entrySet()) {
                        if (e.getValue().equalsIgnoreCase(stockItemTransactionDTO.getUser_unit_of_symbol().toString())) {
                            partiTableModel.setValueAt(e.getKey(), partiTableModel.getRowCount() - 1, 3);
                        }
                    }

                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getRate()), partiTableModel.getRowCount() - 1, 4);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getAmount()), partiTableModel.getRowCount() - 1, 5);
                    index++;
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void bindDTOtoGUIWithoutReceiptNo() throws Exception {
        tfAccountText.setText(purchaseDTO.getCashLedger());
        tfPurchaseAccountText.setText(purchaseDTO.getPurchaseLedger());
        jTextFieldNote.setText(purchaseDTO.getNote());
        jTextFieldVatAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getVatAmount()) + "");
        jTextFieldVatRate.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getVatRate()) + "");
        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getAmount()) + "");
        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getRoundOfAmount()) + "");
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(purchaseDTO.getFinalAmount()) + "");
        tfDatePicker.setText(new PurchaseDTO().getDate());
    }

    private void submit() throws Exception {
        purchaseDTO = bindGUITODTO();
        purchaseDTO = ImpExpUtil.valiDateAndRepairPurchaseDTO(purchaseDTO);
        purchaseDTO.setPurchaseLedger(purchaseLedgerMap.get(purchaseDTO.getPurchaseLedger().trim().toLowerCase()));
        purchaseDTO.setCashLedger(accountLedgerMap.get(purchaseDTO.getCashLedger().trim().toLowerCase()));
        for (StockItemTransactionDTO stockItemTransactionDTO : purchaseDTO.getStockItemTreansactioDTOList()) {
            stockItemTransactionDTO.setName(stockItemMap.get(stockItemTransactionDTO.getName().toLowerCase().trim()));
            stockItemTransactionDTO.setUnit(unitMap.get(stockItemTransactionDTO.getUnit().toLowerCase().trim()));
        }

        if (!isEdit) {
            insertPurchase();
        } else {
            updatePurchase();
        }
        JOptionPane.showMessageDialog(jMainPanel, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        newButton();
    }

    private PurchaseDTO bindGUITODTO() throws Exception {
        PurchaseDTO purchaseDTOEntity = new PurchaseDTO();

        purchaseDTOEntity.setTrans_ID(purchaseDTO.getTrans_ID());
        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setCashLedger(tfAccountText.getText().trim());
        }
        if (!tfPurchaseAccountText.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setPurchaseLedger(tfPurchaseAccountText.getText().trim());
        }

        purchaseDTOEntity.setStockItemTreansactioDTOList(bindtableToDTO());

        if (!jTextFieldNote.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setNote(jTextFieldNote.getText().trim());
        }
        if (!jTextFieldPaymentMode.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setPaymentMode(jTextFieldPaymentMode.getText().trim());
        }
        if (!jTextFieldPurchaseReference.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setPurchaseReference(jTextFieldPurchaseReference.getText().trim());
        }

        // newly added on date 13-04-2014
        if (!jtextField_ExDutyPer.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setExciseDutyRate(Double.parseDouble(jtextField_ExDutyPer.getText().trim()));
        }
        if (!jtextField_ExDutyAmt.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setExciseDutyAmount(Double.parseDouble(jtextField_ExDutyAmt.getText().trim()));
        }
        if (!jtextField_EdCessPer.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setEdCessRate(Double.parseDouble(jtextField_EdCessPer.getText().trim()));
        }
        if (!jtextField_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setEdCessAmount(Double.parseDouble(jtextField_EdCessAmt.getText().trim()));
        }
        if (!jtextField_H_EdCessPer.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.sethEdCessRate(Double.parseDouble(jtextField_H_EdCessPer.getText().trim()));
        }
        if (!jtextField_H_EdCessAmt.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setHedCessAmount(Double.parseDouble(jtextField_H_EdCessAmt.getText().trim()));
        }

        if (!jtextField_PONo.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setPoNo(jtextField_PONo.getText().trim());
        }

        if (!jtextField_OCNo.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setOcNo(jtextField_OCNo.getText().trim());
        }


        // remove on 3-06-2014
        if ((dateChooser_PODate.getDate() != null && !tfDatePOdate.getText().trim().equalsIgnoreCase("")) || (dateChooser_PODate.getDate() == null && tfDatePOdate.getText().toString().trim().equalsIgnoreCase(""))) {
            if ((dateChooser_PODate.getDate() != null && !tfDatePOdate.getText().trim().equalsIgnoreCase(""))) {
                purchaseDTOEntity.setPoDate(Constants.simpleDateFormatDatabase.format(dateChooser_PODate.getDate()).toString().trim());
            } else {
                purchaseDTOEntity.setPoDate("");
            }
        }



        if ((dateChooser_OCDate.getDate() != null && !tfDateOCDate.getText().toString().trim().equalsIgnoreCase("")) || (dateChooser_OCDate.getDate() == null && tfDateOCDate.getText().toString().trim().equalsIgnoreCase(""))) {
            if ((dateChooser_OCDate.getDate() != null && !tfDateOCDate.getText().toString().trim().equalsIgnoreCase(""))) {
                purchaseDTOEntity.setOcDate(Constants.simpleDateFormatDatabase.format(dateChooser_OCDate.getDate()).toString().trim());
            } else {
                purchaseDTOEntity.setOcDate("");
            }
        }


        if (!jTextFieldVatRate.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setVatRate(Double.parseDouble(jTextFieldVatRate.getText().trim()));
        }
        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setVatAmount(Double.parseDouble(jTextFieldVatAmount.getText().trim()));
        }
        if (!jTextFieldCSTRate.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setCstRate(Double.parseDouble(jTextFieldCSTRate.getText().trim()));
        }
        if (!jTextFieldCSTAmount.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setCstAmount(Double.parseDouble(jTextFieldCSTAmount.getText().trim()));
        }

        if (!labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setAmount(Double.parseDouble(labeltxtBasicAmount.getText().trim()));
        }
        if (!labeltxtRoundOffAmount.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setRoundOfAmount(Double.parseDouble(labeltxtRoundOffAmount.getText().trim()));
        }
        if (!labelFinalAmountTotal.getText().trim().equalsIgnoreCase("")) {
            purchaseDTOEntity.setFinalAmount(Double.parseDouble(labelFinalAmountTotal.getText().trim()));
        }

        return purchaseDTOEntity;
    }

    private List<StockItemTransactionDTO> bindtableToDTO() {
        List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        for (int index = 0; index < tableParti.getRowCount(); index++) {

            if (!tableParti.getValueAt(index, 1).toString().trim().equalsIgnoreCase("")) {
                StockItemTransactionDTO stockItemDTO = new StockItemTransactionDTO();
                stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
//                stockItemDTO.setLength(Double.parseDouble(tableParti.getValueAt(index, 2).toString().trim()));
//                stockItemDTO.setWidth(Double.parseDouble(tableParti.getValueAt(index, 3).toString().trim()));
//                stockItemDTO.setThkness(Double.parseDouble(tableParti.getValueAt(index, 4).toString().trim()));
                stockItemDTO.setQuantity(Double.parseDouble(tableParti.getValueAt(index, 2).toString().trim()));
//                stockItemDTO.setSqFeet(Double.parseDouble(tableParti.getValueAt(index, 3).toString().trim()));

                for (Map.Entry<String, String> e : unitMap.entrySet()) {
                    if (tableParti.getValueAt(index, 3).toString().trim().equalsIgnoreCase(e.getKey())) {
                        stockItemDTO.setUser_unit_of_symbol(e.getValue());
                    }
                }
                stockItemDTO.setRate(Double.parseDouble(tableParti.getValueAt(index, 4).toString().trim()));
                if (!tableParti.getValueAt(index, 5).toString().trim().equalsIgnoreCase("")) {
                    stockItemDTO.setAmount(Double.parseDouble(tableParti.getValueAt(index, 5).toString()));
                }
                stockItemTransactionDTOList.add(stockItemDTO);
            }
        }
        return stockItemTransactionDTOList;
    }

    private void insertPurchase() throws Exception {
        List<PurchaseDTO> purchaseDTOList = new ArrayList<PurchaseDTO>();
        purchaseDTOList.add(purchaseDTO);
        PurchaseDAO.insertPurchaseVoucher(purchaseDTOList);
        AccountingVoucherHelper.updateVoucherNumber(Constants.PURCHASE_TYPE_INDEX, (purchaseDTO.getReceiptNo()));
    }

    private void updatePurchase() throws Exception {
        List<PurchaseDTO> purchaseDTOList = new ArrayList<PurchaseDTO>();
        purchaseDTOList.add(purchaseDTO);
        PurchaseDAO.updatePurchaseVoucher(purchaseDTOList);
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
        if (currentFocusValue > 32) {
            currentFocusValue = 32;
        }


        if (currentFocusValue == 0) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            jTextFieldPurchaseReference.requestFocus();
        } else if (currentFocusValue == 3) {
            tfAccountText.requestFocus();
        } else if (currentFocusValue == 4) {
            jtextArea_Buyer_Address.requestFocus();
//            tfPurchaseAccountText.requestFocus();
        } else if (currentFocusValue == 5) {
            jtextField_Buyer_Contno.requestFocus();
        } else if (currentFocusValue == 6) {
            tfPurchaseAccountText.requestFocus();
        } else if (currentFocusValue == 7) {
            jtextField_CSENo.requestFocus();
        } else if (currentFocusValue == 8) {
            jtextField_CVATNo.requestFocus();
        } else if (currentFocusValue == 9) {
            jtextField_CSTNo.requestFocus();
        } else if (currentFocusValue == 10) {
            jtextField_PONo.requestFocus();
        } else if (currentFocusValue == 11) {
            tfDatePOdate.requestFocus();
        } else if (currentFocusValue == 12) {
            jtextField_OCNo.requestFocus();
        } else if (currentFocusValue == 13) {
            tfDateOCDate.requestFocus();
        } else if (currentFocusValue == 14) {
            tfStockItemText.requestFocus();
        } else if (currentFocusValue == 16) {
            jComboBoxUnit.requestFocus();
//            jTextFieldWidth.requestFocus();
        } else if (currentFocusValue == 15) {
//            jTextFieldThickness.requestFocus();
            jTextFieldQuantity.requestFocus();
        } else if (currentFocusValue == 17) {
            jTextFieldRate.requestFocus();
        } else if (currentFocusValue == 18) {
            jTextFieldAmount.requestFocus();
        } else if (currentFocusValue == 19) {
            jTextFieldNote.requestFocus();
        } else if (currentFocusValue == 20) {
            jTextFieldPaymentMode.requestFocus();
        } else if (currentFocusValue == 21) {
            jtextField_ExDutyPer.requestFocus();
        } else if (currentFocusValue == 22) {
            jtextField_ExDutyAmt.requestFocus();
        } else if (currentFocusValue == 23) {
            jtextField_EdCessPer.requestFocus();
        } else if (currentFocusValue == 24) {
            jtextField_EdCessAmt.requestFocus();
        } else if (currentFocusValue == 25) {
            jtextField_H_EdCessPer.requestFocus();
        } else if (currentFocusValue == 26) {
            jtextField_H_EdCessAmt.requestFocus();
        } else if (currentFocusValue == 27) {
            jtextField_Total_WithoutVAT.requestFocus();
        } else if (currentFocusValue == 28) {
            jTextFieldVatRate.requestFocus();
        } else if (currentFocusValue == 29) {
            jTextFieldVatAmount.requestFocus();
        } else if (currentFocusValue == 30) {
            jTextFieldCSTRate.requestFocus();
        } else if (currentFocusValue == 31) {
            jTextFieldCSTAmount.requestFocus();
        }
    }

    private Boolean validateData() throws Exception {
        Boolean flag = true;
        if (!labeltxtBasicAmount.getText().trim().isEmpty() && !labelFinalAmountTotal.getText().trim().isEmpty() && !jTextFieldVatAmount.getText().trim().isEmpty()) {
            jTextFieldVatAmountResult = new BigDecimal(labeltxtBasicAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            labeltxtBasicAmountResult = new BigDecimal(labelFinalAmountTotal.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            labelFinalAmountTotalResult = new BigDecimal(jTextFieldVatAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldExciseDutyAmountResult = new BigDecimal(jtextField_ExDutyAmt.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldEdCessDutyAmountResult = new BigDecimal(jtextField_EdCessAmt.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldHEdCessDutyAmountResult = new BigDecimal(jtextField_H_EdCessAmt.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldCSTAmountResult = new BigDecimal(jTextFieldCSTAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        }
        // check company Date
        // dont allow user to enter validation less than company creation date
        java.sql.Date sqlStartDate = null;
        java.sql.Date transaction_date = null;
        if (jDateChooser.getDate() == null) {
            tfDatePicker.requestFocus();
            throw new Exception(Label.DATE_VALUE_IS_NOT_VALID);
        } else {
            java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
            sqlStartDate = new java.sql.Date(date.getTime());
            transaction_date = new java.sql.Date(jDateChooser.getDate().getTime());
        }

        if (transaction_date.compareTo(sqlStartDate) < 0) {
            tfDatePicker.requestFocus();
            throw new Exception("Date is below of Company Creation Date -  " + gen.dto.Constants.CURRENT_COMPANY_CREATED_DATE);
        } else if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("") || Double.parseDouble(jTextFieldReceiptNo.getText().trim()) == 0D) {
            JOptionPane.showMessageDialog(jMainPanel, Label.RECEIPT_NUMBER_VALUE_IS_NOT_VALID);
            jTextFieldReceiptNo.requestFocus();
            flag = false;
        } else if (jTextFieldPurchaseReference.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthEXTRASMALL) {
            jTextFieldPurchaseReference.requestFocus();
            throw new Exception("Purchase Reference value valid upto " + Constants.jTextFieldCharacterLengthEXTRASMALL + " character");
        } else if (accountLedgerMap.get(tfAccountText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.CASH_LEDGER_VALUE_IS_NOT_VALID);
            tfAccountText.requestFocus();
            jComboBoxAccount.requestFocus();
            flag = false;
        } else if (purchaseLedgerMap.get(tfPurchaseAccountText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PURCHASE_LEDGER_VALUE_IS_NOT_VALID);
            tfPurchaseAccountText.requestFocus();
            jComboBoxPurchaseLedger.requestFocus();
            flag = false;
        } else if (jDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker.requestFocus();
            flag = false;
        } else if (tableParti.getRowCount() == 0) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_EMPTY);
            flag = false;
            tfStockItemText.requestFocus();
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
        } else if (jTextFieldNote.getText().trim().toCharArray().length > Constants.jTextAreaCharacterLengthEXTRALARGE) {
            jTextFieldNote.requestFocus();
            throw new Exception("Note Data Exceeding " + Constants.jTextAreaCharacterLengthEXTRALARGE + " Character Limit");
        } else if (jTextFieldPaymentMode.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldPaymentMode.requestFocus();
            throw new Exception("Payment Mode Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (labeltxtBasicAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            labeltxtBasicAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldVatAmountResult == 1) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (labelFinalAmountTotal.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (labeltxtBasicAmountResult == 1) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (Double.parseDouble(jTextFieldVatRate.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldVatRate.requestFocus();
            throw new Exception("VAT Rate Exceeding " + Constants.jTextFieldCharacterLengthMEDIUM + " Digit Limit");
        } else if (jTextFieldVatAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT Amount only valid upto " + Constants.jTextAreaVATPERCENT + " Percent");
        } else if (labelFinalAmountTotalResult == 1) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT Amount only valid upto " + Constants.jTextAreaVATPERCENT + " digits");
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
        } else if (!tfDatePOdate.getText().toString().trim().equals("") && dateChooser_PODate.getDate() == null) {
            tfDatePOdate.requestFocus();
            throw new Exception("PO date is not valid ");
        } else if (!tfDateOCDate.getText().toString().trim().equals("") && dateChooser_OCDate.getDate() == null) {
            tfDateOCDate.requestFocus();
            throw new Exception("OC date is not valid ");
        }

        return flag;
    }

//    private void calculateAndBindVatRate() throws Exception {
//
//        Double vatRate = 0D;
//        Double basicAmount = 0D;
//        Double vatAmount = 0D;
//        if (jTextFieldVatRate.getText().trim().equalsIgnoreCase("")) {
//            vatRate = 0D;
//        } else {
//            vatRate = Double.parseDouble(jTextFieldVatRate.getText().trim());
//        }
//
//        if (labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
//            basicAmount = 0D;
//        } else {
//            basicAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
//        }
//
//        vatAmount = basicAmount * (vatRate / 100);
//        jTextFieldVatAmount.setText("" + Constants.DECIMAL_FORMAT.format(vatAmount));
//    }
//
//    private void calculateAndBindVatAmount() throws Exception {
//
//        Double vatRate = 0D;
//        Double basicAmount = 0D;
//        Double vatAmount = 0D;
//        if (jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
//            vatAmount = 0D;
//        } else {
//            vatAmount = Double.parseDouble(jTextFieldVatAmount.getText().trim());
//        }
//
//        if (labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
//            basicAmount = 0D;
//        } else {
//            basicAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
//        }
//
//        if (basicAmount != 0D) {
//            vatRate = (vatAmount * 100) / basicAmount;
//        }
//        jTextFieldVatRate.setText("" + Constants.DECIMAL_FORMAT.format(vatRate));
//    }
//    private void calculateSquareFeet() {
//
//        Double length = 0D;
//        Double width = 0D;
//        Double quantity = 0D;
//        Double squareFeet = 0D;
//
//        if (!jTextFieldLength.getText().trim().equalsIgnoreCase("")) {
//            length = Double.parseDouble(jTextFieldLength.getText().trim());
//        }
//        if (!jTextFieldWidth.getText().trim().equalsIgnoreCase("")) {
//            width = Double.parseDouble(jTextFieldWidth.getText().trim());
//        }
//        if (!jTextFieldQuantity.getText().trim().equalsIgnoreCase("")) {
//            quantity = Double.parseDouble(jTextFieldQuantity.getText().trim());
//        }
//
//        squareFeet = length * width * quantity;
//
//        jTextFieldSquareFeet.setText("" + Constants.DECIMAL_FORMAT.format(squareFeet));
//    }
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
            jTextFieldVatAmount.setText("" + Constants.DECIMAL_FORMAT.format(vatAmount));
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
            jTextFieldCSTAmount.setText("" + Constants.DECIMAL_FORMAT.format(cstAmount));
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
            if (!partiTableModel.getValueAt(index, 5).toString().equalsIgnoreCase("")) {
                totalAmount += Double.parseDouble(partiTableModel.getValueAt(index, 5).toString());
            }
        }
        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(totalAmount) + "");
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

        jTextFieldAmount.setText("" + Constants.DECIMAL_FORMAT.format(amount));

    }

    private void calculatePartiRate() {
        Double squareFeet = 0D;
        Double rate = 0D;
        Double amount = 0D;

        if (!jTextFieldQuantity.getText().trim().equalsIgnoreCase("")) {
            squareFeet = Double.parseDouble(jTextFieldQuantity.getText().trim());
        }
        if (!jTextFieldAmount.getText().trim().equalsIgnoreCase("")) {
            amount = Double.parseDouble(jTextFieldAmount.getText().trim());
        }

        if (squareFeet != 0D) {
            rate = amount / squareFeet;
        }
        jTextFieldRate.setText("" + Constants.DECIMAL_FORMAT.format(rate));

    }

//    private void calculateAndBindFinalTotalTOGUI() throws Exception {
//        Double totalAmount = 0D;
//        Double vatAmount = 0D;
//        Double lessBillAmount = 0D;
//        Double transportAmount = 0D;
//        Double finalAmount = 0D;
//        Double roundOffAmount = 0D;
//
//        if (!labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
//            totalAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
//        }
//
//        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
//            vatAmount = Double.parseDouble(jTextFieldVatAmount.getText().trim());
//        }
//        if (!labeltxtRoundOffAmount.getText().trim().equalsIgnoreCase("")) {
//            roundOffAmount = Double.parseDouble(labeltxtRoundOffAmount.getText().trim());
//        }
//
////        if (!jTextFieldLessBill.getText().trim().equalsIgnoreCase("")) {
////            lessBillAmount = Double.parseDouble(jTextFieldLessBill.getText().trim());
////        }
//
////        if (!jTextFieldTransport.getText().trim().equalsIgnoreCase("")) {
////            transportAmount = Double.parseDouble(jTextFieldTransport.getText().trim());
////        }
//
////        finalAmount = totalAmount + vatAmount - lessBillAmount + transportAmount + roundOffAmount;
//        finalAmount = totalAmount + vatAmount - lessBillAmount + transportAmount;
//
//        Double finalAmountWithRoundOff;
//        if (gen.dto.Constants.IS_ROUND_OFF) {
//            finalAmountWithRoundOff = Double.valueOf(Math.round(Double.valueOf(finalAmount)));
//        } else {
//            finalAmountWithRoundOff = Double.valueOf(finalAmount);
//        }
////        roundOffAmount = finalAmount - finalAmountWithRoundOff;
//        roundOffAmount = finalAmountWithRoundOff - finalAmount;
//
//        //set round off value to hidden label
//        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(finalAmountWithRoundOff) + "");
//        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(roundOffAmount) + "");
//    }
//
//    private void calculateAndBindTotalTOGUI() throws Exception {
//        Double totalAmount = 0D;
//        for (int index = 0; index < partiTableModel.getRowCount(); index++) {
//            if (!partiTableModel.getValueAt(index, 8).toString().equalsIgnoreCase("")) {
//                totalAmount += Double.parseDouble(partiTableModel.getValueAt(index, 8).toString());
//            }
//        }
//        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(totalAmount) + "");
//    }
    private void addTOJTable() {

        indexP = 0;
        while (indexP < tableParti.getRowCount()) {
            if (tableParti.getValueAt(indexP, 0) == null) {
                int row = tableParti.getRowCount();
                partiTableModel.setRowCount(row - 1);
            }
            indexP++;
        }

        if (temp == 1000) {

            int row = tableParti.getRowCount();
            partiTableModel.setRowCount(row + 1);
            tableParti.setValueAt(row + 1, row, 0);
            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
//            tableParti.setValueAt(jTextFieldLength.getText().trim(), row, 2);
//            tableParti.setValueAt(jTextFieldWidth.getText().trim(), row, 3);
//            tableParti.setValueAt(jTextFieldThickness.getText().trim(), row, 4);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jComboBoxUnit.getText().trim())), row, 3);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(tfStockItemUnit.getText().trim())), row, 3);
            tableParti.setValueAt(tfStockItemUnit.getText().trim(), row, 3);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 4);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 5);
        } else {
            int row = temp;
            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
//            tableParti.setValueAt(jTextFieldLength.getText().trim(), row, 2);
//            tableParti.setValueAt(jTextFieldWidth.getText().trim(), row, 3);
//            tableParti.setValueAt(jTextFieldThickness.getText().trim(), row, 4);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
//            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jComboBoxUnit.getText().trim())), row, 3);
            tableParti.setValueAt(tfStockItemUnit.getText().trim(), row, 3);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 4);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 5);
            temp = 1000;
            buttonAdd.setLabel(Label.BUTTON_ADD);
            tableParti.clearSelection();
            tfStockItemText.requestFocus();
        }
    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<PurchaseDTO> purchaseDTOList = PurchaseDAO.getPurchase(idSet, "");
        if (purchaseDTOList != null && !purchaseDTOList.isEmpty()) {
            purchaseDTO = purchaseDTOList.get(0);
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
//            jTextFieldSqFeetResult = new BigDecimal(jTextFieldSquareFeet.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldRateResult = new BigDecimal(jTextFieldRate.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldAmountResult = new BigDecimal(jTextFieldAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);;
        }
        if (stockItemMap.get(tfStockItemText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_VALUE_IS_NOT_VALID);
            currentFocusValue = 14;
            tfStockItemText.requestFocus();
            return false;
        } else if ((jTextFieldAmount.getText().trim().equalsIgnoreCase("")) || (jTextFieldAmount.getText().trim().equalsIgnoreCase(".")) || (Double.parseDouble(jTextFieldAmount.getText().trim()) == 0D)) {
            JOptionPane.showMessageDialog(jMainPanel, Label.AMOUNT_NOT_VALID);
            currentFocusValue = 18;
            jTextFieldAmount.requestFocus();
            return false;
        } else if (jTextFieldQuantity.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthEXTRASMALL) {
            jTextFieldQuantity.requestFocus();
            throw new Exception("Quantity Exceeding " + Constants.jTextFieldCharacterLengthEXTRASMALL + " digit Limit");
        } else if (jTextFieldQuantityResult == 1) {
            jTextFieldQuantity.requestFocus();
            throw new Exception("Quantity only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (unitMap.get(tfStockItemUnit.getText().trim().toLowerCase()) == null) {
            tfStockItemUnit.requestFocus();
            throw new Exception("Unit of Measure is not Valid");
        } //            else if (jComboBoxUnit.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthEXTRASMALL) {
        //            jComboBoxUnit.requestFocus();
        //            throw new Exception("Square Feet Exceeding " + Constants.jTextFieldCharacterLengthEXTRASMALL + " digit Limit");
        //        } 
        else if (jTextFieldRate.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthEXTRASMALL) {
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
        } else {
            currentFocusValue = 13;
        }

        return true;
    }

    private void bindDTOToTransactionTable(List<PurchaseDTO> purchaseDTOTransactionList) throws Exception {
        try {
            if (purchaseDTOTransactionList != null && !purchaseDTOTransactionList.isEmpty()) {
//	    if (purchaseDTOTransactionList != null) {
                transactionTableModel1.setRowCount(0);
                for (PurchaseDTO purchaseDTOEntity : purchaseDTOTransactionList) {
                    transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                    transactionTableModel1.setValueAt(purchaseDTOEntity.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                    java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(purchaseDTOEntity.getDate().trim());
                    transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                    transactionTableModel1.setValueAt(purchaseDTOEntity.getPurchaseLedger(), transactionTableModel1.getRowCount() - 1, 2);
                }
            } else {
                if (last_Number_In_TransactionList != 1) {
                    transactionTableModel1.setRowCount(0);
                }
            }
            last_Number_In_TransactionList = 0;




        } catch (ParseException ex) {
            Logger.getLogger(TaxInvoicePurchaseForm.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

//    private void setStockItemProperties() {
//        String stockItemName = tfStockItemText.getText().trim();
//
//        if (!stockItemName.equals("")) {
//            try {
//                StockItemTransactionDTO stockItemTransactionDTO = StockItemDAO.getStockItemValues(stockItemName);
//                jTextFieldLength.setText(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemTransactionDTO.getLength().toString())));
//                jTextFieldWidth.setText(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemTransactionDTO.getWidth().toString())));
//                jTextFieldThickness.setText(Constants.DECIMAL_FORMAT.format(Double.parseDouble(stockItemTransactionDTO.getThkness().toString())));
//            } catch (Exception ex) {
//                Logger.getLogger(TaxInvoicePurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    private void setStockItemProperties() throws Exception {
        String stockItemName = tfStockItemText.getText().trim();

        if (!stockItemName.equals("")) {
            try {
                StockItemTransactionDTO stockItemTransactionDTO = gen.account.stockitem.StockItemDAO.getStockItemValues(stockItemName);

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

//                jTextFieldLength.setText(stockItemDTO.getLength().toString());
//                jTextFieldWidth.setText(stockItemDTO.getWidth().toString());;
//                jTextFieldThickness.setText(stockItemDTO.getThkness().toString());;





            } catch (SQLException ex) {
                Logger.getLogger(TaxInvoicePurchaseForm.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    private void newButton() throws ParseException, SQLException, Exception {
        jTextFieldReceiptNo.setEditable(true);
        jTextFieldReceiptNo.setEnabled(true);
        purchaseDTO = new PurchaseDTO();
        isEdit = false;
        purchaseDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.PURCHASE_TYPE_INDEX));
        initTransactionList();
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);

    }

    private void clearFormData() {
        tfStockItemText.setText("");
//        jTextFieldLength.setText("0.0");
//        jTextFieldWidth.setText("0.0");
//        jTextFieldThickness.setText("0.0");
        jTextFieldQuantity.setText("0.0");
//        jComboBoxUnit.setText("0.0");
        jTextFieldRate.setText("0.0");
        jTextFieldAmount.setText("0.0");
        buttonParticularDelete.setEnabled(false);
    }

    public String loadEditFormForPrint(String id) throws SQLException, ParseException, Exception {
        String receipt_id = "";
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<PurchaseDTO> purchaseDTOList = PurchaseDAO.getPurchase(idSet, "");
        if (purchaseDTOList != null && !purchaseDTOList.isEmpty()) {
            purchaseDTO = purchaseDTOList.get(0);
            receipt_id = purchaseDTO.getTrans_ID();

        }
        return receipt_id;




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

            int r = chooser.showSaveDialog(TaxInvoicePurchaseForm.this);

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
                    }
                }
            }
            return name1;
        }
    }

    private void dataExport() throws Exception {

        path = "";
        MessageBox msgBox = new MessageBox();
        Set<String> purchaseSet = new HashSet<String>();
        purchaseSet.add(jTextFieldReceiptNo.getText().trim());
        BufferedWriter out = null;
        if (path != null) {
            if (!path.isEmpty()) {
                try {
                    // TODO add your handling code here:
                    Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
                    IDMapSet.put("Purchase", purchaseSet);
                    String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
//                    String xmlCode = TagsHelper1.exportDayBookTally(IDMapSet);
                    System.out.println("------------------------->>XMLCODE:" + xmlCode);

                    // File file = new File("C:\\DayBook.xml");
                    File file = new File(path + ".xml");
                    path = file.getPath();
                    out = new BufferedWriter(new FileWriter(file));
                    out.write(xmlCode);
                    out.close();

                    JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, "Export Successful");





                } catch (Exception ex) {
                    Logger.getLogger(DayBook.class
                            .getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(
                            this, "Export failure");
                }
            }
        }

    }
    int row = 0;
    private JPanel panel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JTextField jtextField_CSENo;
    private JTextField jtextField_CVATNo;
    private JTextField jtextField_CSTNo;
    private JTextField jtextField_PONo;
    private JTextField jtextField_OCNo;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JDateChooser dateChooser_PODate;
    private JDateChooser dateChooser_OCDate;
    private JLabel lblAddress;
    private JTextArea jtextArea_Buyer_Address;
    private JLabel lblContactNo;
    private JTextField jtextField_Buyer_Contno;
    private JLabel lblCstRate;
    private JLabel lblTotal;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    private JLabel lblNewLabel_10;
    private JTextField jtextField_ExDutyPer;
    private JTextField jtextField_EdCessPer;
    private JTextField jtextField_H_EdCessPer;
    private JTextField jTextFieldCSTRate;
    private JTextField jtextField_ExDutyAmt;
    private JTextField jtextField_EdCessAmt;
    private JTextField jtextField_H_EdCessAmt;
    private JTextField jTextFieldCSTAmount;
    private JTextField jtextField_Total_WithoutVAT;

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
                try {
                    Set<String> idSet = new HashSet<String>();
                    idSet.add(id);
                    List<PurchaseDTO> purchaseDTOList = PurchaseDAO.getPurchase(idSet, "");
                    if (purchaseDTOList != null && !purchaseDTOList.isEmpty()) {
                        try {
                            purchaseDTO = purchaseDTOList.get(0);
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
                            Logger.getLogger(SaleForm.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }




                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SaleForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(SaleForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TaxInvoicePurchaseForm.class
                    .getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(TaxInvoicePurchaseForm.this, ex.getMessage());
        }
    }

    private void initTransactionList() throws SQLException, Exception {
        purchaseDTOTransactionList = PurchaseDAO.getTransactionList();
        bindDTOToTransactionTable(purchaseDTOTransactionList);
    }
}