/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.purchaseorder;

/**
 *
 * @author admin
 */
import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.ImpExp.TagsHelper1;
import gen.account.ledger.LedgerDAO;
import gen.account.ledger.LedgerForm;
import gen.account.stockitem.StockItemDAO;
import gen.account.stockitem.StockItemForm1;
import gen.accountvoucher.TableCellListener;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.database.connection.DatabaseConnection1;
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
import javax.swing.text.BadLocationException;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class PurchaseOrderForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private PurchaseOrderDTO purchaseOrderDTO;
    private List<PurchaseOrderDTO> purchaseOrderDTOTransactionList;
    private Boolean isEdit = false;
    private Vector<String> cashLedgerVector = new Vector<String>();
    private Vector<String> purchaseLedgerVector = new Vector<String>();
    private Vector<String> stockItemVector = new Vector<String>();
    //variables For Calculation
    private Map<String, String> accountLedgerMap;
    private Map<String, String> purchaseLedgerMap;
    private Map<String, String> stockItemMap;
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
    int jTextFieldVatAmountResult = 0;
    int labeltxtBasicAmountResult = 0;
    int labelFinalAmountTotalResult = 0;
    int jTextFieldQuantityResult = 0;
    int jTextFieldRateResult = 0;
    int jTextFieldAmountResult = 0;
    Image img1 = new ImageIcon(getClass().getResource("/images/Down.gif")).getImage();
    Image img2 = new ImageIcon(getClass().getResource("/images/Left.gif")).getImage();
    private static int last_Number_In_TransactionList = 0;
    private int double_Enter_Note = 0;

    public PurchaseOrderForm(String s, Dimension d) {
        try {
            initComponents();
            purchaseOrderDTO = new PurchaseOrderDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public PurchaseOrderForm(String s, PurchaseOrderDTO purchaseOrderDTO, Dimension d, Boolean isEdit) {
        try {
            initComponents();
            this.isEdit = isEdit;
            this.purchaseOrderDTO = purchaseOrderDTO;
            isTransactionTableLoad = true;
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
        }
    }

    private void initComponents() {


        setFrameIcon(new ImageIcon(PurchaseOrderForm.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Purchase Order Form");
        setBounds(100, 100, 1332, 674);

        jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(null, "Purchase Order Form",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel.setLayout(new MigLayout("", "[][0px:225px:225px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][][0px:225px:225px,grow,shrink 50,fill][][][][0px:100px:100px,grow,shrink 50,fill][0px:350px:350px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelReceiptNo = new JLabel("Purchase Order No.");
        jMainPanel.add(labelReceiptNo, "cell 0 0");

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

        labelSearch = new JLabel("Search");
        jMainPanel.add(labelSearch, "flowx,cell 9 0");

        labelAccount = new JLabel("Supplier's Name   ");
        jMainPanel.add(labelAccount, "cell 0 2");

        jComboBoxAccount = new JComboBox();
        jComboBoxAccount.setPrototypeDisplayValue("xxxxxx");
        jComboBoxAccount.setEditable(true);
        jMainPanel.add(jComboBoxAccount, "cell 1 2,growx");

//        labelSaleLedger = new JLabel("Purchase");
//        jMainPanel.add(labelSaleLedger, "cell 3 2");
//
//        jComboBoxSaleLedger = new JComboBox();
//        jComboBoxSaleLedger.setPrototypeDisplayValue("xxxxxx");
//        jComboBoxSaleLedger.setEditable(true);
//        jMainPanel.add(jComboBoxSaleLedger, "cell 4 2,growx");

        JScrollPane pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 9 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer();
        nameRenderer.setHorizontalAlignment(JLabel.LEFT);

        String col1[] = {Label.PO_NO, Label.DATE_TRANSACTION, Label.SUPPLIER_NAME};
        String data1[][] = {{"", "", ""}};

        transactionTableModel1 = new DefaultTableModel(data1, col1) {
            public Class getColumnClass(int row) {
                Class returnValue;
                if ((row >= 0) && (row < getRowCount())) {
                    returnValue = getValueAt(0, row).getClass();
                } else {
                    returnValue = Object.class;
                }
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

        JPanel panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 0 5 8 9,grow");
        panel_1.setLayout(new MigLayout("", "[40px:40px:40px,grow,shrink 50,fill][0px:225px:225px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelSN = new JLabel("SN");
        panel_1.add(labelSN, "cell 0 0");

        labelParticulars = new JLabel("Particulars");
        panel_1.add(labelParticulars, "cell 1 0");

        labelQuantity = new JLabel("Quantity");
        panel_1.add(labelQuantity, "cell 2 0");

        labelRate = new JLabel("Rate");
        panel_1.add(labelRate, "cell 3 0");

        labelAmount = new JLabel("Amount");
        panel_1.add(labelAmount, "cell 4 0");

        jComboBoxParti = new JComboBox();
        jComboBoxParti.setPrototypeDisplayValue("xxxxxx");
        jComboBoxParti.setEditable(true);
        panel_1.add(jComboBoxParti, "cell 1 1,growx");

        jTextFieldQuantity = new JTextField("0.0");
        panel_1.add(jTextFieldQuantity, "cell 2 1,growx");
        jTextFieldQuantity.setTransferHandler(null);
        jTextFieldQuantity.setColumns(10);

        jTextFieldRate = new JTextField();
        panel_1.add(jTextFieldRate, "cell 3 1,growx");
        jTextFieldRate.setTransferHandler(null);
        jTextFieldRate.setColumns(10);

        jTextFieldAmount = new JTextField();
        panel_1.add(jTextFieldAmount, "cell 4 1,growx");
        jTextFieldAmount.setTransferHandler(null);
        jTextFieldAmount.setColumns(10);

        JScrollPane pane = new JScrollPane();
        panel_1.add(pane, "cell 0 2 5 5,grow");

        String col[] = {Label.S_N, Label.PARTICULARS, Label.QUANTITY, Label.RATE, Label.AMOUNT, Label.UNIT_SYMBOL};
        String data[][] = {{"", "", "", "", "", ""}};
        partiTableModel = new DefaultTableModel(data, col);
        tableParti = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2 || column == 3 || column == 4) {
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
        partiTableModel.setColumnCount(6);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(230);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableParti.getColumnModel().getColumn(3).setPreferredWidth(180);
        tableParti.getColumnModel().getColumn(4).setPreferredWidth(180);
        tableParti.getColumnModel().getColumn(5).setMinWidth(0);
        tableParti.getColumnModel().getColumn(5).setMaxWidth(0);
        tableParti.getColumnModel().getColumn(5).setWidth(0);

        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableCellListener tcl = (TableCellListener) e.getSource();
                    int column = tcl.getColumn();

                    if (column == 2) {
                        System.out.println("in if");
                        int row = tcl.getRow();
                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                        TableModel partiTableModel = tcl.getTable().getModel();
                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 2).toString());
                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 3).toString());
                        Double value = new Double((quantity * rate));
                        partiTableModel.setValueAt(value, row, 4);
                        calculateAndBindTotalTOGUI();
                        updateOnKeyType();
                    } else if (column == 3) {
                        System.out.println("in if");
                        int row = tcl.getRow();
                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                        TableModel partiTableModel = tcl.getTable().getModel();
                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 2).toString());
                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 3).toString());
                        Double value = new Double((quantity * rate));
                        partiTableModel.setValueAt(value, row, 4);
                        calculateAndBindTotalTOGUI();
                        updateOnKeyType();
                    } else if (column == 4) {
                        System.out.println("in else");
                        int row = tcl.getRow();
                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                        System.out.println("newPrice--->>" + newPrice);
                        TableModel model = tcl.getTable().getModel();
                        double weight = Double.parseDouble(partiTableModel.getValueAt(row, 2).toString());
                        double amount = Double.parseDouble(partiTableModel.getValueAt(row, 4).toString());
                        Double newValue = new Double((amount / weight));
                        model.setValueAt(newValue, row, 3);
                        calculateAndBindTotalTOGUI();
                        updateOnKeyType();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }
        };

        TableCellListener tcl = new TableCellListener(tableParti, action);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        pane.setViewportView(tableParti);

        buttonAdd = new JButton("Add");
        buttonAdd.setMnemonic('D');
        jMainPanel.add(buttonAdd, "cell 8 6");

        buttonParticularDelete = new JButton("Delete");
        buttonParticularDelete.setMnemonic('L');
        buttonParticularDelete.setEnabled(false);
        jMainPanel.add(buttonParticularDelete, "cell 8 7");

        JPanel panel_2 = new JPanel();
        jMainPanel.add(panel_2, "cell 0 14 8 4,grow");
        panel_2.setLayout(new MigLayout("", "[0px:400px:400px,grow,shrink 50,fill][0px:0px:0px,grow,shrink 50,fill][0px:0px:0px,grow,shrink 50,fill][0px:165px:165px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:170px:170px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelNote = new JLabel("Terms and Conditions");
        panel_2.add(labelNote, "cell 0 0,aligny top");
        JScrollPane scroll = new JScrollPane();
        panel_2.add(scroll, "cell 0 1 1 3,grow");

        jTextFieldNote = new JTextArea();
        scroll.setViewportView(jTextFieldNote);
        jTextFieldNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextFieldNote.setLineWrap(true);
        jTextFieldNote.setWrapStyleWord(true);


//        labelDispatchDocNo = new JLabel("Doc. No.");
//        labelDispatchDocNo.setHorizontalAlignment(SwingConstants.RIGHT);
//        panel_2.add(labelDispatchDocNo, "cell 1 1,growx");
//
//        jTextFieldDispatchDocNo = new JTextField();
//        panel_2.add(jTextFieldDispatchDocNo, "cell 2 1,growx");
//        jTextFieldDispatchDocNo.setColumns(10);

        labelBasicAmount = new JLabel("Amount");
        labelBasicAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelBasicAmount, "cell 3 1");

        labeltxtBasicAmount = new JLabel("0");
        panel_2.add(labeltxtBasicAmount, "cell 5 1");

        labeltxtRoundOffAmount = new JLabel("");

//        labelDispatchDocThrough = new JLabel("Vehicle No.");
//        labelDispatchDocThrough.setHorizontalAlignment(SwingConstants.RIGHT);
//        panel_2.add(labelDispatchDocThrough, "cell 1 2,growx");
//
//        jTextFieldDispatchDocThrough = new JTextField();
//        panel_2.add(jTextFieldDispatchDocThrough, "cell 2 2,growx");
//        jTextFieldDispatchDocThrough.setColumns(10);

        labelVatRate = new JLabel("VAT Rate(%)");
        labelVatRate.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelVatRate, "cell 3 2");

        jTextFieldVatRate = new JTextField();
        panel_2.add(jTextFieldVatRate, "cell 4 2,growx");
        jTextFieldVatRate.setTransferHandler(null);
        jTextFieldVatRate.setColumns(10);

        jTextFieldVatAmount = new JTextField();
        panel_2.add(jTextFieldVatAmount, "cell 5 2,growx");
        jTextFieldVatAmount.setTransferHandler(null);
        jTextFieldVatAmount.setColumns(10);

        labelFinalAmount = new JLabel("Total");
        labelFinalAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelFinalAmount, "cell 3 3");

        labelFinalAmountTotal = new JLabel("0");
        panel_2.add(labelFinalAmountTotal, "cell 5 3");

        JPanel panel_3 = new JPanel();
        jMainPanel.add(panel_3, "cell 0 18 8 4,grow");
        panel_3.setLayout(new MigLayout(
                "",
                "[0px:750px:750px,grow,shrink 50,fill][]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        buttonBack = new JButton("Back");
        buttonBack.setMnemonic('B');
        panel_3.add(buttonBack, "flowx,cell 0 2");

        buttonNew = new JButton("New");
        buttonNew.setMnemonic('N');
        panel_3.add(buttonNew, "cell 0 2");

        buttonPrint = new JButton("Print");
        buttonPrint.setMnemonic('P');
        panel_3.add(buttonPrint, "cell 0 2");
        buttonPrint.setEnabled(false);

//        buttonExport = new JButton("Export");
//        buttonExport.setMnemonic('E');
//        panel_3.add(buttonExport, "cell 0 2");

        buttonDelete = new JButton("Delete");
        buttonDelete.setMnemonic('T');
        panel_3.add(buttonDelete, "cell 0 2");

        buttonAddLedger = new JButton("Ledger");
        buttonAddLedger.setMnemonic('G');
        panel_3.add(buttonAddLedger, "cell 0 2");

        buttonAddItem = new JButton("Item");
        buttonAddItem.setMnemonic('I');
        panel_3.add(buttonAddItem, "cell 0 2");

        buttonSubmit = new JButton("Submit");
        buttonSubmit.setMnemonic('S');
        panel_3.add(buttonSubmit, "cell 1 2");

        jTextFieldSearch = new JTextField();
        jMainPanel.add(jTextFieldSearch, "cell 9 0");
        jTextFieldSearch.setColumns(25);

        buttonPrevious_DOWN_Transactions = new JButton("<<");
        jMainPanel.add(buttonPrevious_DOWN_Transactions, "flowx,cell 9 20");

        buttonNext_DOWN_Transactions = new JButton(">>");
        jMainPanel.add(buttonNext_DOWN_Transactions, "cell 9 20");

        labelUnitSymbol = new JLabel("");
        labelUnitSymbol.setVisible(false);
        jMainPanel.add(labelUnitSymbol, "cell 9 0");

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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                jComboBoxAccount.hidePopup();
//                jComboBoxSaleLedger.hidePopup();
                jComboBoxParti.hidePopup();
            }
        });

        jTextFieldReceiptNo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTextFieldReceiptNoMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                if (!tfDatePicker.getText().equalsIgnoreCase("")) {
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
                if (!tfAccountText.getText().equalsIgnoreCase("")) {
                }
            }
        });

//        jComboBoxSaleLedger.setEditable(true);
//        tfSaleAccountText = (JTextField) jComboBoxSaleLedger.getEditor().getEditorComponent();
//        tfSaleAccountText.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(final KeyEvent e) {
//                EventQueue.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        String text = tfSaleAccountText.getText();
//                        if (text.length() == 0) {
//                            jComboBoxSaleLedger.hidePopup();
//                            setSaleLedgetModel(new DefaultComboBoxModel(purchaseLedgerVector), "");
//                        } else {
//                            DefaultComboBoxModel m = Util.getSuggestedModel(purchaseLedgerVector, text);
//                            if (m.getSize() == 0 || hide_flag) {
//                                jComboBoxSaleLedger.hidePopup();
//                                hide_flag = false;
//                            } else {
//                                setSaleLedgetModel(m, text);
//                                jComboBoxSaleLedger.showPopup();
//                            }
//
//                        }
//                    }
//                });
//
//            }
//        });

//        tfSaleAccountText.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(final KeyEvent event) {
//                EventQueue.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                            setFocus(event);
//                        }
//                    }
//                });
//            }
//        });
//
//        tfSaleAccountText.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                currentFocusValue = 3;
//                String text = tfSaleAccountText.getText();
//                if (text.length() == 0) {
//                   // jComboBoxSaleLedger.hidePopup();
//                    setSaleLedgetModel(new DefaultComboBoxModel(purchaseLedgerVector), "");
//                } else {
//                    DefaultComboBoxModel m = Util.getSuggestedModel(purchaseLedgerVector, text);
//                    if (m.getSize() == 0 || hide_flag) {
//                      //  jComboBoxSaleLedger.hidePopup();
//                        hide_flag = false;
//                    } else {
//                        setSaleLedgetModel(m, text);
//                      //  jComboBoxSaleLedger.showPopup();
//                    }
//
//                }
//                tfSaleAccountText.selectAll();
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (!tfSaleAccountText.getText().equalsIgnoreCase("")) {
//                }
//            }
//        });

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
                                    currentFocusValue = 6;
                                } else {
                                    currentFocusValue = 3;
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
                currentFocusValue = 3;
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }
        });

        tfStockItemText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
        });

        jTextFieldQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldQuantityFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    calculatePartiAmount();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculatePartiAmount();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldAmount);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculatePartiRate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                        JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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

//        jTextFieldDispatchDocNo.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                jTextFieldDispatchDocNoFocusGained(evt);
//            }
//        });
//
//        jTextFieldDispatchDocNo.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jTextFieldDispatchDocNoKeyPressed(evt);
//            }
//        });
//
//        jTextFieldDispatchDocThrough.addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusGained(java.awt.event.FocusEvent evt) {
//                jTextFieldDispatchDocThroughFocusGained(evt);
//            }
//        });
//
//        jTextFieldDispatchDocThrough.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//                jTextFieldDispatchDocThroughKeyPressed(evt);
//            }
//        });

        jTextFieldVatRate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldVatRateFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindVatRate();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    calculateAndBindVatAmount();
                    calculateAndBindFinalTotalTOGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
                    Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    jTextFieldVatAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    jTextFieldVatAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    jTextFieldVatAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    jTextFieldVatAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
//                    buttonExport.requestFocus();
                    buttonPrint.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonAddLedger.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldVatAmount.requestFocus();
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
                    //buttonPrintActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
//                    buttonExport.requestFocus();
                    buttonDelete.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldVatAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

//        buttonExport.addActionListener(new java.awt.event.ActionListener() {
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                try {
//                    buttonExportActionPerformed(evt);
//                } catch (Exception ex) {
//		    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
//                }
//            }
//        });
//
//        buttonExport.addKeyListener(new java.awt.event.KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    buttonPrint.requestFocus();
//                }
//                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    if (buttonDelete.isEnabled()) {
//                        buttonDelete.requestFocus();
//                    } else {
//                        buttonAddLedger.requestFocus();
//                    }
//                }
//                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                    jTextFieldVatAmount.requestFocus();
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });

        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNewActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
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
                    jTextFieldVatAmount.requestFocus();
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
        currentFocusValue = 4;
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

    private void jTextFieldRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 5;
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
                    if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabilty(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.PURCHASE_ORDER_TYPE_INDEX)) {
                        jTextFieldReceiptNo.setText("");
                    }
                } else {
                    jTextFieldReceiptNo.requestFocus();
                    jTextFieldReceiptNo.setText("");
                    throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
                }
            }

            if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                jTextFieldReceiptNo.setText(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.PURCHASE_ORDER_TYPE_INDEX) + "");
            }
        }
    }

    private void jTextFieldAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 6;
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

                JOptionPane.showMessageDialog(PurchaseOrderForm.this, "Max 15 entries allowed per transaction");

            }

            clearFormData();

            Util.checkForEmpty(jTextFieldQuantity);
            Util.checkForEmpty(jTextFieldSquareFeet);
            Util.checkForEmpty(jTextFieldRate);
            Util.checkForEmpty(jTextFieldAmount);

            calculateAndBindTotalTOGUI();
            calculateAndBindVatRate();
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
                        j++;
                    }
                }
                DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                tableModel.setRowCount(tableParti.getRowCount() - 1);

            }
        }
        calculateAndBindTotalTOGUI();
        calculateAndBindVatRate();
        calculateAndBindVatAmount();
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
                    jTextFieldQuantity.setText(tableParti.getValueAt(temp, 2).toString());
                    jTextFieldRate.setText(tableParti.getValueAt(temp, 3).toString());
                    jTextFieldAmount.setText(tableParti.getValueAt(temp, 4).toString());
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

    private void jTextFieldReceiptNoMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        jTextFieldReceiptNo.setEnabled(true);
    }

    private void jTextFieldNoteFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 7;
        double_Enter_Note = 0;
        jTextFieldNote.setText(jTextFieldNote.getText().trim());
//        jTextFieldNote.selectAll();
    }

    private void jTextFieldNoteKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                double_Enter_Note++;
            }
//            if (double_Enter_Note == 0 || double_Enter_Note == 2) {
//                setFocus(evt);
//            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            jTextFieldNote.setText(jTextFieldNote.getText().toString().trim());
            setFocus(evt);
        }
    }

//    private void jTextFieldDispatchDocNoFocusGained(java.awt.event.FocusEvent evt) {
//        currentFocusValue = 9;
////        jTextFieldDispatchDocNo.selectAll();
//    }
//    private void jTextFieldDispatchDocNoKeyPressed(java.awt.event.KeyEvent evt) {
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            setFocus(evt);
//        }
//    }
//    private void jTextFieldDispatchDocThroughFocusGained(java.awt.event.FocusEvent evt) {
//        currentFocusValue = 10;
//        jTextFieldDispatchDocThrough.selectAll();
//    }
//    private void jTextFieldDispatchDocThroughKeyPressed(java.awt.event.KeyEvent evt) {
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            setFocus(evt);
//        }
//    }
    private void jTextFieldVatRateFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 8;
        Util.checkForZero(jTextFieldVatRate);
        jTextFieldVatRate.selectAll();
    }

    private void jTextFieldVatRateFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldVatRate);
    }

    private void jTextFieldVatRateKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldVatAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        currentFocusValue = 9;
        Util.checkForZero(jTextFieldVatAmount);
        jTextFieldVatAmount.selectAll();
    }

    private void jTextFieldVatAmountFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldVatAmount);
    }

    private void jTextFieldVatAmountKeyPressed(java.awt.event.KeyEvent evt) {

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

        List<PurchaseOrderDTO> saleList = new ArrayList<PurchaseOrderDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (PurchaseOrderDTO saleDTOEntity : purchaseOrderDTOTransactionList) {
            if (saleDTOEntity.getCashLedger().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
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

    private void buttonAddItemActionPerformed(java.awt.event.ActionEvent evt) {
        Container container = SwingUtilities.getAncestorOfClass(
                JDesktopPane.class, (Component) evt.getSource());

        if (container != null) {
            JDesktopPane desktopPane = getDesktopPane();
            desktopPane.setLayout(null);
            StockItemForm1 form = new StockItemForm1();
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
            PurchaseOrderForm.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            if (validateData()) {
                submit();
                tfDatePicker.requestFocus();
                jComboBoxAccount.hidePopup();
                jComboBoxParti.hidePopup();
//                jComboBoxSaleLedger.hidePopup();
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
            PurchaseOrderDAO.deleteTransaction(Long.parseLong(purchaseOrderDTO.getpurchaseOrder_Trans_ID()));
            JOptionPane.showMessageDialog(this, "Transaction deleted completly");
            newButton();
            clearFormData();
            temp = 1000;
            buttonAdd.setText("ADD");
            tfDatePicker.requestFocus();
            jComboBoxAccount.hidePopup();
            jComboBoxParti.hidePopup();
//            jComboBoxSaleLedger.hidePopup();
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
//                    String Dispatchthrough = jTextFieldDispatchDocThrough.getText().trim();

//                    if (Dispatchthrough.equals("")) {
//                        Dispatchthrough = " ";
//                    } else {
//                        Dispatchthrough = "" + jTextFieldDispatchDocThrough.getText().trim();
//                    }

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
//                    parameter.put("dispatchthrough", Dispatchthrough);

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
                    InputStream image1 = null;
                    if (image != null) {
                        image1 = new ByteArrayInputStream(image);
                        parameter.put("logo", image1);
                    } else {
                        parameter.put("logo", image1);
                    }
                    PrintAllReport.printSalesReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
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
//                    String Dispatchthrough = jTextFieldDispatchDocThrough.getText().trim();

//                    if (Dispatchthrough.equals("")) {
//                        Dispatchthrough = " ";
//                    } else {
//                        Dispatchthrough = "" + jTextFieldDispatchDocThrough.getText().trim();
//                    }

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
//                    parameter.put("dispatchthrough", Dispatchthrough);

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

//                    InputStream image1 = null;
//                    if (image != null) {
//                        image1 = new ByteArrayInputStream(image);
//                    }
                    InputStream image1 = null;
                    if (image1 != null) {
                        image1 = new ByteArrayInputStream(image);
                        parameter.put("logo", image1);
                    } else {
                        parameter.put("logo", image1);
                    }
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
//        jComboBoxSaleLedger.hidePopup();
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
//        jComboBoxSaleLedger.hidePopup();
    }

    private void buttonNextTransactionsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            last_Number_In_TransactionList = 1;
            initTransactionList();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonPreviousTransactionsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
            initTransactionList();
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
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
//    private JComboBox jComboBoxSaleLedger;
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
    private JTextArea jTextFieldNote;
    private JLabel labelDispatchDocNo;
//    private JTextField jTextFieldDispatchDocNo;
    private JLabel labelDispatchDocThrough;
//    private JTextField jTextFieldDispatchDocThrough;
    private JLabel labelBasicAmount;
    private JLabel labeltxtBasicAmount;
    private JLabel labeltxtRoundOffAmount;
    private JLabel labelVatRate;
    private JLabel labelDiscount;
    //private JLabel labelShipping;
    private JTextField jTextFieldDiscount;
    private JTextField jTextFieldDiscountAmount;
    private JTextField jTextFieldShipping;
    private JTextField jTextFieldVatRate;
    private JTextField jTextFieldVatAmount;
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
//    private JButton buttonExport;
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
    private JTextField tfAccountText, tfStockItemText;
//            tfSaleAccountText
    private JTextField tfDatePicker = null;

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
//        buttonExport.setEnabled(false);
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
//        jComboBoxSaleLedger.setModel(mdl);
//        jComboBoxSaleLedger.setSelectedIndex(-1);
//        jComboBoxSaleLedger.showPopup();
//        tfSaleAccountText.setText(str);
    }

    private void setPartiModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxParti.setModel(mdl);
        jComboBoxParti.setSelectedIndex(-1);
        jComboBoxParti.showPopup();
        tfStockItemText.setText(str);
    }

    private void initVariables() throws Exception {
        initLedger();
        initStockItem();
        if (!isEdit) {
            purchaseOrderDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.PURCHASE_ORDER_TYPE_INDEX));
        }

        ledgerTimeStamp = Constants.LEDGER_TIME_STAMP;
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
        buttonParticularDelete.setEnabled(false);

    }

    private void initLedger() throws Exception {
        List<String> groups = new ArrayList<String>();
        groups.add(Constants.BANK_ACCOUNT);
        groups.add(Constants.CASH_IN_HAND);
//        groups.add(Constants.DUTIES_AND_TAXES);

        accountLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, false);

        groups = new ArrayList<String>();
        groups.add(Constants.SALES_ACCOUNT);
        purchaseLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, true);
        cashLedgerVector.clear();
        for (String str : accountLedgerMap.keySet()) {
            if (!str.contains("Vat") && !str.contains("Round")) {
                cashLedgerVector.add(str);
            }
        }

        Collections.sort(
                cashLedgerVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        purchaseLedgerVector.clear();
        for (String str : purchaseLedgerMap.keySet()) {
            purchaseLedgerVector.add(str);
        }

        Collections.sort(
                purchaseLedgerVector,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        purchaseLedgerMap = Util.getSmallCaseMap(purchaseLedgerMap);
        accountLedgerMap = Util.getSmallCaseMap(accountLedgerMap);
    }

    private void initStockItem() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
        stockItemMap = StockItemDAO.getStockItemsFromGroupName(stockGroups, false);
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

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        gen.dto.Constants.OFFSET_VALUE = 0L;
        Long number = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.PURCHASE_ORDER_TYPE_INDEX, Constants.CREDIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        Long remaining = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.PURCHASE_ORDER_TYPE_INDEX, Constants.CREDIT) % gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));

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

    private void bindDTOtoGUI() throws ParseException {
        tfAccountText.setText(purchaseOrderDTO.getCashLedger());
//        tfSaleAccountText.setText(purchaseOrderDTO.getSaleLedger());
        jTextFieldReceiptNo.setText("" + purchaseOrderDTO.getReceiptNo());
        jTextFieldNote.setText(purchaseOrderDTO.getNote());
//        jTextFieldDispatchDocNo.setText(purchaseOrderDTO.getDispatchDocNo() + "");
//        jTextFieldDispatchDocThrough.setText(purchaseOrderDTO.getDispatchDocThrough() + "");

        jTextFieldVatAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getVatAmount()) + "");
        jTextFieldVatRate.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getVatRate()) + "");

        //set all value to zero
        if (purchaseOrderDTO.getStockItemTreansactioDTOList().isEmpty() || purchaseOrderDTO.getStockItemTreansactioDTOList() == null) {
            jTextFieldAmount.setText("0.0");
            jTextFieldRate.setText("0.0");
            jTextFieldQuantity.setText("0.0");
        }

        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getAmount()) + "");
//        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getRoundOfAmount()) + "");
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getFinalAmount()) + "");

//        if (purchaseOrderDTO.getChallan_trans_ID() != null && !purchaseOrderDTO.getChallan_trans_ID().equals("")) {
//            if (Double.parseDouble(purchaseOrderDTO.getChallan_trans_ID()) >= 0D) {
//            } else {
//            }
//        } else {
//        }

        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(purchaseOrderDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(purchaseOrderDTO.getDate().trim()));
        }

    }

    private void bindDTOtoJtable() throws Exception {
        int index = 1;
        List<StockItemTransactionDTO> stockItemTransactionDTOList = purchaseOrderDTO.getStockItemTreansactioDTOList();
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                try {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                    partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getRate()), partiTableModel.getRowCount() - 1, 3);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getAmount()), partiTableModel.getRowCount() - 1, 4);

                    StockItemTransactionDTO stockItemUnitTransactionDTO = StockItemDAO.getStockItemValues(stockItemTransactionDTO.getName());
                    partiTableModel.setValueAt(stockItemUnitTransactionDTO.getUnit_of_symbol(), partiTableModel.getRowCount() - 1, 5);
                    index++;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private void bindDTOtoGUIWithoutReceiptNo() throws ParseException {
        tfAccountText.setText(purchaseOrderDTO.getCashLedger());
//        tfSaleAccountText.setText(purchaseOrderDTO.getSaleLedger());
//        jTextFieldLessBill.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getLessBillAmount()) + "");
        jTextFieldNote.setText(purchaseOrderDTO.getNote());
//        jTextFieldDispatchDocNo.setText(purchaseOrderDTO.getDispatchDocNo() + "");
//        jTextFieldDispatchDocThrough.setText(purchaseOrderDTO.getDispatchDocThrough() + "");
        jTextFieldVatAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getVatAmount()) + "");
//        jTextFieldTransport.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getTransport()) + "");
        jTextFieldVatRate.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getVatRate()) + "");
        labeltxtBasicAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getAmount()) + "");
//        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getRoundOffAmount()) + "");
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(purchaseOrderDTO.getFinalAmount()) + "");
//        tfDatePicker.setText(new SaleDTO().getDate());
    }

    private void submit() throws SQLException, ParseException, Exception {
        purchaseOrderDTO = bindGUITODTO();
//        purchaseOrderDTO = ImpExpUtil.valiDateAndRepairSaleDTO(purchaseOrderDTO);
//        purchaseOrderDTO.setSaleLedger(saleLedgerMap.get(purchaseOrderDTO.getSaleLedger().trim().toLowerCase()));
        purchaseOrderDTO.setCashLedger(accountLedgerMap.get(purchaseOrderDTO.getCashLedger().trim().toLowerCase()));
        for (StockItemTransactionDTO stockItemTransactionDTO : purchaseOrderDTO.getStockItemTreansactioDTOList()) {
            stockItemTransactionDTO.setName(stockItemMap.get(stockItemTransactionDTO.getName().toLowerCase().trim()));
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

    private PurchaseOrderDTO bindGUITODTO() {
        PurchaseOrderDTO saleDTOEntity = new PurchaseOrderDTO();
        saleDTOEntity.setpurchaseOrder_Trans_ID(purchaseOrderDTO.getpurchaseOrder_Trans_ID());

//        if (purchaseOrderDTO.getChallan_trans_ID() != null && !purchaseOrderDTO.getChallan_trans_ID().equals("")) {
//            if (Double.parseDouble(purchaseOrderDTO.getChallan_trans_ID()) >= 0D) {
//                saleDTOEntity.setChallan_receipt_No(purchaseOrderDTO.getChallan_receipt_No());
//                saleDTOEntity.setChallan_trans_ID(purchaseOrderDTO.getChallan_trans_ID());
//            } else {
//                saleDTOEntity.setChallan_receipt_No("0");
//                saleDTOEntity.setChallan_trans_ID("0");
//            }
//        } else {
//            saleDTOEntity.setChallan_receipt_No("0");
//            saleDTOEntity.setChallan_trans_ID("0");
//        }

        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setCashLedger(tfAccountText.getText().trim());
        }
//        if (!tfSaleAccountText.getText().trim().equalsIgnoreCase("")) {
//           saleDTOEntity.setSaleLedger(tfSaleAccountText.getText().trim());
//        }

        saleDTOEntity.setStockItemTreansactioDTOList(bindtableToDTO());

        if (!jTextFieldNote.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setNote(jTextFieldNote.getText().trim());
        }
//        if (!jTextFieldDispatchDocNo.getText().trim().equalsIgnoreCase("")) {
//            saleDTOEntity.setDispatchDocNo(jTextFieldDispatchDocNo.getText().trim());
//        }
//        if (!jTextFieldDispatchDocThrough.getText().trim().equalsIgnoreCase("")) {
//            saleDTOEntity.setDispatchDocThrough(jTextFieldDispatchDocThrough.getText().trim());
//        }

        if (!jTextFieldVatRate.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setVatRate(Double.parseDouble(jTextFieldVatRate.getText().trim()));
        }
        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setVatAmount(Double.parseDouble(jTextFieldVatAmount.getText().trim()));
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

        return saleDTOEntity;
    }

    private List<StockItemTransactionDTO> bindtableToDTO() {
        List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        for (int index = 0; index < tableParti.getRowCount(); index++) {

            if (!tableParti.getValueAt(index, 1).toString().trim().equalsIgnoreCase("")) {
                StockItemTransactionDTO stockItemDTO = new StockItemTransactionDTO();
                stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
                if (!tableParti.getValueAt(index, 4).toString().trim().equalsIgnoreCase("")) {
                    stockItemDTO.setAmount(Double.parseDouble(tableParti.getValueAt(index, 4).toString()));
                }
                stockItemDTO.setQuantity(Double.parseDouble(tableParti.getValueAt(index, 2).toString().trim()));
                stockItemDTO.setRate(Double.parseDouble(tableParti.getValueAt(index, 3).toString().trim()));
                stockItemDTO.setUnit_of_symbol((tableParti.getValueAt(index, 5).toString().trim()));
                stockItemTransactionDTOList.add(stockItemDTO);
            }
        }
        return stockItemTransactionDTOList;
    }

    private void insertSale() throws SQLException, ParseException, Exception {
        List<PurchaseOrderDTO> saleDTOList = new ArrayList<PurchaseOrderDTO>();
        saleDTOList.add(purchaseOrderDTO);
        PurchaseOrderDAO.insertPurchaseOrderVoucher(saleDTOList);
        AccountingVoucherHelper.updateVoucherNumber(Constants.PURCHASE_ORDER_TYPE_INDEX, (purchaseOrderDTO.getReceiptNo()));
    }

    private void updateSale() throws SQLException, ParseException, Exception {
        List<PurchaseOrderDTO> saleDTOList = new ArrayList<PurchaseOrderDTO>();
        saleDTOList.add(purchaseOrderDTO);
        PurchaseOrderDAO.updatePurchaseOrderVoucher(saleDTOList);
    }

    private void setFocus(java.awt.event.KeyEvent evt) {

        if (evt != null) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                currentFocusValue++;
            } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
                currentFocusValue--;
            }
        }
        if (currentFocusValue < 0) {
            currentFocusValue = 0;
        }
        if (currentFocusValue > 13) {
            currentFocusValue = 13;
        }

        if (currentFocusValue == 0) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            tfAccountText.requestFocus();
        } //        else if (currentFocusValue == 3) {
        //            tfSaleAccountText.requestFocus();
        //        }
        else if (currentFocusValue == 3) {
            tfStockItemText.requestFocus();
        } else if (currentFocusValue == 4) {
            jTextFieldQuantity.requestFocus();
        } else if (currentFocusValue == 5) {
            jTextFieldRate.requestFocus();
        } else if (currentFocusValue == 6) {
            jTextFieldAmount.requestFocus();
        } else if (currentFocusValue == 7) {
            jTextFieldNote.requestFocus();
        } //        else if (currentFocusValue == 9) {
        //            jTextFieldDispatchDocNo.requestFocus();
        //        } else if (currentFocusValue == 10) {
        //            jTextFieldDispatchDocThrough.requestFocus();
        //        } 
        else if (currentFocusValue == 8) {
            jTextFieldVatRate.requestFocus();
        } else if (currentFocusValue == 9) {
            jTextFieldVatAmount.requestFocus();
        }
    }

    private Boolean validateData() throws Exception {
        if (!labeltxtBasicAmount.getText().trim().isEmpty() && !labelFinalAmountTotal.getText().trim().isEmpty() && !jTextFieldVatAmount.getText().trim().isEmpty()) {
            labeltxtBasicAmountResult = new BigDecimal(labeltxtBasicAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            labelFinalAmountTotalResult = new BigDecimal(labelFinalAmountTotal.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
            jTextFieldVatAmountResult = new BigDecimal(jTextFieldVatAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        }
        Boolean flag = true;

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
        } else if ((labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) || (Double.parseDouble(labeltxtBasicAmount.getText().trim()) == 0D)) {
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
        } //        else if (purchaseLedgerMap.get(tfSaleAccountText.getText().trim().toLowerCase()) == null) {
        //            JOptionPane.showMessageDialog(jMainPanel, Label.SALE_LEDGER_VALUE_IS_NOT_VALID);
        //            tfSaleAccountText.requestFocus();
        //            flag = false;
        //        } 
        else if (jDateChooser.getDate() == null) {
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
        } //        else if (jTextFieldDispatchDocNo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
        //            jTextFieldDispatchDocNo.requestFocus();
        //	    throw new Exception("Dispatch Document Number Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        //        } else if (jTextFieldDispatchDocThrough.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
        //            jTextFieldDispatchDocThrough.requestFocus();
        //	    throw new Exception("Vehicle Number Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        //        } 
        else if (labeltxtBasicAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
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
        } else if (jTextFieldVatAmountResult == 1) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        }

        return flag;
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
            if (labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
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
            if (labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
                basicAmount = 0D;
            } else {
                basicAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
            }

            if (basicAmount != 0D) {
                vatRate = (vatAmount * 100) / basicAmount;
            }
            jTextFieldVatRate.setText("" + Constants.DECIMAL_FORMAT.format(vatRate));
        }

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
        Double lessBillAmount = 0D;
        Double transportAmount = 0D;
        Double finalAmount;
        Double roundOffAmount = 0D;

        if (!labeltxtBasicAmount.getText().trim().equalsIgnoreCase("")) {
            totalAmount = Double.parseDouble(labeltxtBasicAmount.getText().trim());
        }

        if (!labeltxtRoundOffAmount.getText().trim().equalsIgnoreCase("")) {
            roundOffAmount = Double.parseDouble(labeltxtRoundOffAmount.getText().trim());
        }
        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            vatAmount = Double.parseDouble(jTextFieldVatAmount.getText().trim());
        }

//        finalAmount = totalAmount + vatAmount - lessBillAmount - transportAmount + roundOffAmount;
        finalAmount = totalAmount + vatAmount - lessBillAmount - transportAmount;


        Double finalAmountWithRoundOff;
        if (gen.dto.Constants.IS_ROUND_OFF) {
            finalAmountWithRoundOff = Double.valueOf(Math.round(Double.valueOf(finalAmount)));
        } else {
            finalAmountWithRoundOff = Double.valueOf(finalAmount);
        }
        roundOffAmount = finalAmount - finalAmountWithRoundOff;

        //set round off value to hidden label
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(finalAmountWithRoundOff) + "");
        labeltxtRoundOffAmount.setText(Constants.DECIMAL_FORMAT.format(roundOffAmount) + "");
    }

    private void calculateAndBindTotalTOGUI() throws Exception {
        Double totalAmount = 0D;
        for (int index = 0; index < partiTableModel.getRowCount(); index++) {
            if (!partiTableModel.getValueAt(index, 4).toString().equalsIgnoreCase("")) {
                totalAmount += Double.parseDouble(partiTableModel.getValueAt(index, 4).toString());
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
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 3);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 4);
            tableParti.setValueAt(labelUnitSymbol.getText().trim(), row, 5);
        } else {
            int row = temp;
            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldRate.getText().trim())), row, 3);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row, 4);
            tableParti.setValueAt(labelUnitSymbol.getText().trim(), row, 5);
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
        calculateAndBindVatRate();
        calculateAndBindVatAmount();
        calculateAndBindFinalTotalTOGUI();


    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<PurchaseOrderDTO> saleDTOList = PurchaseOrderDAO.getPurchaseOrder(idSet, "");
        if (saleDTOList != null && !saleDTOList.isEmpty()) {
            purchaseOrderDTO = saleDTOList.get(0);
            isEdit = true;
            partiTableModel.setRowCount(0);
            jTextFieldReceiptNo.setEditable(false);
            buttonDelete.setEnabled(true);
//            buttonExport.setEnabled(true);
//            buttonPrint.setEnabled(true);
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
            currentFocusValue = 2;
            tfStockItemText.requestFocus();
            return false;
        } //        else {
        //        }
        else if ((jTextFieldAmount.getText().trim().equalsIgnoreCase("")) || (jTextFieldAmount.getText().trim().equalsIgnoreCase(".")) || (Double.parseDouble(jTextFieldAmount.getText().trim()) == 0D)) {
            JOptionPane.showMessageDialog(jMainPanel, Label.AMOUNT_NOT_VALID);
            currentFocusValue = 5;
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
        } else {
            currentFocusValue = 2;
        }

        return true;
    }

    private void bindDTOToTransactionTable(List<PurchaseOrderDTO> saleDTOTransactionList) throws SQLException {
        if (saleDTOTransactionList != null && !saleDTOTransactionList.isEmpty()) {
            transactionTableModel1.setRowCount(0);
            for (PurchaseOrderDTO saleDTOEntity : saleDTOTransactionList) {
                try {
                    transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                    transactionTableModel1.setValueAt(saleDTOEntity.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                    java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(saleDTOEntity.getDate().trim());
                    transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                    transactionTableModel1.setValueAt(saleDTOEntity.getCashLedger(), transactionTableModel1.getRowCount() - 1, 2);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            if (last_Number_In_TransactionList != 1) {
                transactionTableModel1.setRowCount(0);
            }
        }
        last_Number_In_TransactionList = 0;
    }

    private void setStockItemProperties() throws Exception {
        String stockItemName = tfStockItemText.getText().trim();

        if (!stockItemName.equals("")) {
            try {
                StockItemTransactionDTO stockItemTransactionDTO = StockItemDAO.getStockItemValues(stockItemName);
                labelUnitSymbol.setText(stockItemTransactionDTO.getUnit_of_symbol());
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void newButton() throws ParseException, SQLException, Exception {
        jTextFieldReceiptNo.setEditable(true);
        jTextFieldReceiptNo.setEnabled(true);
        jTextFieldSearch.setText("");
        purchaseOrderDTO = new PurchaseOrderDTO();
        isEdit = false;
        purchaseOrderDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.PURCHASE_ORDER_TYPE_INDEX));
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);
    }

    private void clearFormData() {
        tfStockItemText.setText("");
        jTextFieldQuantity.setText("0.0");
        jTextFieldRate.setText("0.0");

        jTextFieldAmount.setText("0.0");
        buttonParticularDelete.setEnabled(false);
    }

    private void setStockItemRate() throws SQLException, Exception {
        if (rateMap == null) {
            rateMap = new HashMap<String, Map<String, String>>();
        }
//        if (rateMap.get(tfAccountText.getText().trim()) == null) {
//	    Map<String, String> rateMapEntity = new HashMap<String, String>();
//	    if (accountLedgerMap.get(tfAccountText.getText().toLowerCase().trim()) != null && !accountLedgerMap.isEmpty()) {
//		rateMapEntity = PurchaseOrderDAO.getRateByCustomerID(Long.parseLong(accountLedgerMap.get(tfAccountText.getText().toLowerCase().trim())));
//	    }
//            System.out.println("rateMapEntity===============>>>" + rateMapEntity.size());
//            if (rateMapEntity != null) {
//                this.rateMap.put(tfAccountText.getText().toLowerCase().trim(), rateMapEntity);
//            }
//        }
        if (rateMap.get(tfAccountText.getText().toLowerCase().trim()) != null) {
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

            int r = chooser.showSaveDialog(PurchaseOrderForm.this);

            if (r == JFileChooser.APPROVE_OPTION) {

                name1 = chooser.getSelectedFile().getAbsolutePath();
                if (!name1.isEmpty()) {
                    File file = chooser.getSelectedFile();
                    path = name1;

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
        PurchaseOrderForm.MessageBox msgBox = new PurchaseOrderForm.MessageBox();
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
                    Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Export failure");
                    ex.printStackTrace();
                }
            }
        }

    }
    int row = 0;

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
                List<PurchaseOrderDTO> saleDTOList = PurchaseOrderDAO.getPurchaseOrder(idSet, "");
                if (saleDTOList != null && !saleDTOList.isEmpty()) {
                    try {
                        purchaseOrderDTO = saleDTOList.get(0);
                        partiTableModel.setRowCount(0);
                        jTextFieldReceiptNo.setEditable(true);
                        buttonDelete.setEnabled(true);
//                        buttonExport.setEnabled(true);
//                        buttonPrint.setEnabled(true);
                        bindDTOtoGUIWithoutReceiptNo();
                        bindDTOtoJtable();
                        currentFocusValue = 1;
                        setFocus(null);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(PurchaseOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(PurchaseOrderForm.this, ex.getMessage());
        }
    }

    private void initTransactionList() throws SQLException, Exception {
        List<String> receiptNo_List = new ArrayList<String>();
        purchaseOrderDTOTransactionList = PurchaseOrderDAO.getTransactionList(receiptNo_List, "");
        bindDTOToTransactionTable(purchaseOrderDTOTransactionList);
    }
}
