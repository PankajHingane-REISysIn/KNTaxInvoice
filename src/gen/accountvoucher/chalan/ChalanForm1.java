package gen.accountvoucher.chalan;

import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.TagsHelper1;
import gen.account.ledger.LedgerForm;
import gen.account.stockitem.StockItemForm1;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.account.ledger.LedgerDAO;
import gen.account.stockitem.StockItemDAO;
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
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Blob;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class ChalanForm1 extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {
    
    private ChalanDTO chalanDTO;
    private JTextField tfAccountText, tfSaleAccountText, tfStockItemText;
    public JTextField tfDatePicker = null;
    private Vector<String> cashLedgerVector = new Vector<String>();
    private Vector<String> saleLedgerVector = new Vector<String>();
    private Vector<String> stockItemVector = new Vector<String>();
    List<ChalanDTO> chalanDTOTransactionList;
    private DefaultTableModel partiTableModel;
    private Boolean isEdit = false;
    //variables For Calculation
    private Map<String, String> accountLedgerMap;
    private Map<String, String> saleLedgerMap;
    private Map<String, String> stockItemMap;
    //variable for focus
    private int currentFocusValue = 0;
    //time stamp
    private Long ledgerTimeStamp;
    private Long stockItemTimeStamp;
    private int temp = 1000;
    private int call_Submit_place = 0;
    int i = 0;
    int indexForDeletion = 0;
    int flag;
    JPopupMenu popUpMenu = new JPopupMenu();
    JMenuItem Copy_Transaction = new JMenuItem();
    private Boolean isTransactionTableLoad = false;
    private int quantityResult = 0;
    private int jTextFieldVatAmountResult = 0;
    private int jTextFieldLessBillAmountResult = 0;
    private int jTextFieldTransportResult = 0;
    private static int last_Number_In_TransactionList = 0;

    public ChalanForm1(String s, Dimension d) {
        try {
            initComponents();
            chalanDTO = new ChalanDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
        }
    }

    public ChalanForm1(ChalanDTO chalanDTO, Boolean isEdit) {
        try {
            initComponents();
            this.isEdit = isEdit;
            this.chalanDTO = chalanDTO;
            initilize();
            isTransactionTableLoad = true;
        } catch (Exception ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
        }
    }

    private void initComponents() {

        setFrameIcon(new ImageIcon(ChalanForm1.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Chalan Transaction Form");
        setBounds(100, 100, 1332, 674);

        jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chalan Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel
                .setLayout(new MigLayout(
                "",
                "[][0px:225px:225px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][][0px:50px:50px,grow,shrink 50,fill][][0px:225px:225px,grow,shrink 50,fill][][][0px:100px:100px,grow,shrink 50,fill][0px:350px:350px,grow,shrink 50,fill]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelReceiptNo = new JLabel("Chalan No.");
        jMainPanel.add(labelReceiptNo, "cell 0 0,alignx trailing");

        jTextFieldReceiptNo = new JTextField();
        jTextFieldReceiptNo.setEnabled(false);
        jTextFieldReceiptNo.setForeground(new Color(0, 0, 0));
        jMainPanel.add(jTextFieldReceiptNo, "cell 1 0");
        jTextFieldReceiptNo.setTransferHandler(null);
        jTextFieldReceiptNo.setColumns(20);

        labelDate = new JLabel("Date");
        jMainPanel.add(labelDate, "cell 5 0");

        jDateChooser = new JDateChooser();
        jMainPanel.add(jDateChooser, "cell 6 0");

        labelSearch = new JLabel("Search");
        jMainPanel.add(labelSearch, "flowx,cell 10 0");

        labelAccount = new JLabel("Buyer's Name   ");
        jMainPanel.add(labelAccount, "cell 0 2,alignx trailing");

        jComboBoxAccount = new JComboBox();
        jComboBoxAccount.setPrototypeDisplayValue("xxxxxx");
        jComboBoxAccount.setEditable(true);
        jMainPanel.add(jComboBoxAccount, "cell 1 2,growx");

        labelSaleLedger = new JLabel("Sales    ");
        jMainPanel.add(labelSaleLedger, "cell 5 2");

        jComboBoxSaleLedger = new JComboBox();
        jComboBoxSaleLedger.setPrototypeDisplayValue("xxxxxx");
        jComboBoxSaleLedger.setEditable(true);
        jMainPanel.add(jComboBoxSaleLedger, "cell 6 2,growx");

        JScrollPane pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 10 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);
        
        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer();
        nameRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        String col1[] = {Label.CHALAN_NO, Label.DATE_TRANSACTION, Label.ACCOUNT, Label.INVOICE_GENERATED};
        String data1[][] = {{"", "", "", ""}};

        transactionTableModel1 = new DefaultTableModel(data1, col1) {
            public Class getColumnClass(int row) {
                Class returnValue;
                if ((row >= 0) && (row < getRowCount())) {
                    returnValue = getValueAt(0, row).getClass();
//                    System.out.println("getValueAt(0, row)---->>" + getValueAt(0, row));
                } else {
                    returnValue = Object.class;
                }
//                System.out.println("Class---->>" + returnValue);
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
        transactionTableModel1.setColumnCount(4);
        jTableTransactionList.getColumnModel().getColumn(0).setCellRenderer(renderer);
        jTableTransactionList.getColumnModel().getColumn(1).setCellRenderer(renderer);
        jTableTransactionList.getColumnModel().getColumn(2).setCellRenderer(nameRenderer);
        jTableTransactionList.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(transactionTableModel1);
        jTableTransactionList.setRowSorter(sorter);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableTransactionList.setFont(font);
        pane1.setViewportView(jTableTransactionList);
        
        JPanel panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 0 4 5 13,grow");
        panel_1.setLayout(new MigLayout("", "[40px:40px:40px,grow,shrink 50,fill][0px:225px:225px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));
        
        labelSN = new JLabel("SN");
        panel_1.add(labelSN, "cell 0 0");
        
        labelParticulars = new JLabel("Particulars");
        panel_1.add(labelParticulars, "cell 1 0");
        
        labelQuantity = new JLabel("Quantity");
        panel_1.add(labelQuantity, "cell 2 0");
        
        jComboBoxParti = new JComboBox();
        jComboBoxParti.setPrototypeDisplayValue("xxxxxx");
        jComboBoxParti.setEditable(true);
        panel_1.add(jComboBoxParti, "cell 1 1,growx");
        
        jTextFieldQuantity = new JTextField();
        panel_1.add(jTextFieldQuantity, "cell 2 1,growx");
        jTextFieldQuantity.setTransferHandler(null);
        jTextFieldQuantity.setColumns(10);
        
        JScrollPane pane = new JScrollPane();
        panel_1.add(pane, "cell 0 2 3 10,grow");
        
        String col[] = {Label.S_N, Label.PARTICULARS, Label.QUANTITY};
        String data[][] = {{"", "", ""}};
        partiTableModel = new DefaultTableModel(data, col);
        tableParti = new JTable() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        tableParti.setModel(partiTableModel);
        JTableHeader header = tableParti.getTableHeader();
        tableParti.getTableHeader().setReorderingAllowed(false);
        header.setBackground(Color.yellow);
        header.setFont(font);
        tableParti.setFont(font);
        partiTableModel = (DefaultTableModel) tableParti.getModel();
        partiTableModel.setRowCount(0);
        partiTableModel.setColumnCount(3);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(230);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        tableParti.setFont(font);
        pane.setViewportView(tableParti);
        
        buttonAdd = new JButton("Add");
        buttonAdd.setMnemonic('D');
        jMainPanel.add(buttonAdd, "cell 5 5,growx");
        
        buttonParticularDelete = new JButton("Delete");
        buttonParticularDelete.setMnemonic('L');
        buttonParticularDelete.setEnabled(false);
        jMainPanel.add(buttonParticularDelete, "cell 5 6");
        
        JPanel panel_2 = new JPanel();
        jMainPanel.add(panel_2, "cell 0 17 9 4,grow");
        panel_2.setLayout(new MigLayout(
                "",
                "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][grow]",
                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));
        
        labelNote = new JLabel("Note");
        panel_2.add(labelNote, "cell 0 0,aligny top");
        
        jTextFieldNote = new JTextArea();
        jTextFieldNote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextFieldNote.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(jTextFieldNote);
        panel_2.add(scroll, "cell 0 1 1 3,grow");
        
        labelVatRate = new JLabel("VAT Rate(%)");
        labelVatRate.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelVatRate, "cell 3 1,alignx trailing");
        
        jTextFieldVatRate = new JTextField();
        panel_2.add(jTextFieldVatRate, "cell 4 1,growx");
        jTextFieldVatRate.setColumns(10);
        
        jTextFieldVatAmount = new JTextField();
        panel_2.add(jTextFieldVatAmount, "cell 5 1,growx");
        jTextFieldVatAmount.setColumns(10);
        
        labelDispatchDocNo = new JLabel("Doc. No.");
        labelDispatchDocNo.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelDispatchDocNo, "cell 1 2,growx");
        
        jTextFieldDispatchDocNo = new JTextField();
        panel_2.add(jTextFieldDispatchDocNo, "cell 2 2,growx");
        jTextFieldDispatchDocNo.setColumns(10);
        
        labelLessBill = new JLabel("Less Bill Amount");
        labelLessBill.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelLessBill, "cell 3 2");
        
        jTextFieldLessBill = new JTextField();
        panel_2.add(jTextFieldLessBill, "cell 5 2,growx");
        jTextFieldLessBill.setColumns(10);
        
        labelDispatchDocThrough = new JLabel("Vehicle No.");
        labelDispatchDocThrough.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelDispatchDocThrough, "cell 1 3,growx");
        
        jTextFieldDispatchDocThrough = new JTextField();
        panel_2.add(jTextFieldDispatchDocThrough, "cell 2 3,growx");
        jTextFieldDispatchDocThrough.setColumns(10);
        
        labelTransport = new JLabel("Transport");
        labelTransport.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_2.add(labelTransport, "cell 3 3");
        
        jTextFieldTransport = new JTextField();
        panel_2.add(jTextFieldTransport, "cell 5 3,growx");
        jTextFieldTransport.setColumns(10);
        
        buttonGenerateInvoice = new JButton("Generate Invoice");
        buttonGenerateInvoice.setMnemonic('O');
        panel_2.add(buttonGenerateInvoice, "cell 6 3");
        
//        JPanel panel_3 = new JPanel();
//        jMainPanel.add(panel_3, "cell 0 21 9 2,grow");
//        panel_3.setLayout(new MigLayout(
//                "",
//                "[0px:750px:750px,grow,shrink 50,fill][]",
//                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));


        JPanel panel_3 = new JPanel();
        jMainPanel.add(panel_3, "cell 0 23 9 2,grow");
        panel_3.setLayout(new MigLayout("", "[0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill][0px:102px:102px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][0px:25px:25px,grow,shrink 50,fill]"));
        
        buttonBack = new JButton("Back");
        buttonBack.setMnemonic('B');
        panel_3.add(buttonBack, "flowx,cell 0 3");
        
        buttonNew = new JButton("New");
        buttonNew.setMnemonic('N');
        panel_3.add(buttonNew, "cell 1 3");
        
        buttonPrint = new JButton("Print");
        buttonPrint.setMnemonic('P');
        panel_3.add(buttonPrint, "cell 2 3");
        
        buttonExport = new JButton("Export");
        buttonExport.setMnemonic('E');
        panel_3.add(buttonExport, "cell 3 3");
        
        buttonDelete = new JButton("Delete");
        buttonDelete.setMnemonic('T');
        panel_3.add(buttonDelete, "cell 4 3");
        
        buttonAddLedger = new JButton("Ledger");
        buttonAddLedger.setMnemonic('G');
        panel_3.add(buttonAddLedger, "cell 5 3");
        
        buttonAddItem = new JButton("Item");
        buttonAddItem.setMnemonic('I');
        panel_3.add(buttonAddItem, "cell 6 3");
        
        buttonSubmit = new JButton("Submit");
        buttonSubmit.setMnemonic('S');
        panel_3.add(buttonSubmit, "cell 7 3");
        
        jTextFieldSearch = new JTextField();
        jMainPanel.add(jTextFieldSearch, "cell 10 0");
        jTextFieldSearch.setColumns(25);
        
        buttonPrevious_DOWN_Transactions = new JButton("<<");
        jMainPanel.add(buttonPrevious_DOWN_Transactions, "flowx,cell 10 20");
        
        buttonNext_DOWN_Transactions = new JButton(">>");
        jMainPanel.add(buttonNext_DOWN_Transactions, "cell 10 20");
        
        JLabel lblNewLabel = new JLabel("");
        jMainPanel.add(lblNewLabel, "cell 10 0");
        
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
                    System.out.println("Frame Activated------------------------------ Chalan=");
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                jComboBoxSaleLedger.hidePopup();
                jComboBoxParti.hidePopup();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });

        //Toolkit.getDefaultToolkit().getSystemEventQueue().push(new CopyPasteOperation(jTextFieldReceiptNo));

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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    try {
                        buttonBackActionPerformed(null);
                    } catch (Exception ex) {
                        Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                    }
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
                // throw new UnsupportedOperationException("Not supported yet.");
                jComboBoxAccount.hidePopup();
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
                //throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 3;
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
                // throw new UnsupportedOperationException("Not supported yet.");
                jComboBoxSaleLedger.hidePopup();
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
                                    currentFocusValue = 5;
                                } else {
                                    currentFocusValue = 4;
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
                // throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 4;
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
                if (!tfStockItemText.getText().trim().equalsIgnoreCase("")) {
                }
                jComboBoxParti.hidePopup();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                try {
                    jTextFieldQuantityKeyPressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
            
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldQuantity);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
            
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                //calculatePartiAmount();
            }
        });
        
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        buttonAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonAddKeyPressed(evt);
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
                try {
                    tablePartiKeyPressed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        jTableTransactionList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableTransactionListMouseClicked(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                        JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
        
        jTextFieldVatRate.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldVatRateFocusGained(evt);
            }
        });
        
        jTextFieldVatRate.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldVatRateKeyPressed(evt);
            }
        });
        
        jTextFieldVatAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldVatAmountFocusGained(evt);
            }
        });
        
        jTextFieldVatAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldVatAmountKeyPressed(evt);
            }
        });
        
        jTextFieldLessBill.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLessBillFocusGained(evt);
            }
        });
        
        jTextFieldLessBill.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldLessBillKeyPressed(evt);
            }
        });
        
        jTextFieldTransport.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTransportFocusGained(evt);
            }
        });
        
        jTextFieldTransport.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTransportKeyPressed(evt);
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
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        buttonAddLedger.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddLedgerActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    if (buttonSubmit.isEnabled()) {
                        buttonSubmit.requestFocus();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        buttonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonPrintActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    jTextFieldDispatchDocThrough.requestFocus();
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        buttonGenerateInvoice.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonGenerateInvoiceActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonExportActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    jTextFieldDispatchDocThrough.requestFocus();
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                }
            }
        });
        
        buttonPrevious_DOWN_Transactions.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
            }
        });
        
    }
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException, Exception {
        System.out.println("activated");
        if (this.ledgerTimeStamp != Constants.LEDGER_TIME_STAMP) {
            initLedger();
        }
        if (this.stockItemTimeStamp != Constants.STOCK_ITEM_TIME_STAMP) {
            initStockItem();
        }
        if (isEdit) {
            initTransactionList();
            if (!validate_Invoice_For_Sale()) {
                buttonGenerateInvoice.setEnabled(true);
            } else {
                buttonGenerateInvoice.setEnabled(false);
            }
            
            if (!validate_Invoice_For_Sale()) {
                buttonSubmit.setEnabled(true);
                buttonDelete.setEnabled(true);
            } else {
                buttonSubmit.setEnabled(false);
                buttonDelete.setEnabled(false);
            }
        }
        setFocus(null);
    }
    
    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException, Exception {
        MainClass.setstaticvar();
        this.dispose();
    }
    
    private void jTextFieldReceiptNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 0;
    }
    
    private void jTextFieldReceiptNoKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        // TODO add your handling code here:
        currentFocusValue = 5;
        Util.checkForZero(jTextFieldQuantity);
        jTextFieldQuantity.selectAll();
    }
    
    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        Util.checkForEmpty(jTextFieldQuantity);
    }
    
    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buttonAddActionPerformed(null);
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                    if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabilty(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.CHALAN_TYPE_INDEX)) {
                        jTextFieldReceiptNo.setText("");
                    }
                } else {
                    jTextFieldReceiptNo.requestFocus();
		    jTextFieldReceiptNo.setText("");
                    throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
                }
            }
            
            if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                jTextFieldReceiptNo.setText(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.CHALAN_TYPE_INDEX) + "");
            }
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
            tfStockItemText.requestFocus();
        }
    }
    
    private void partiDelete() throws Exception {
	int j = 0;
	flag = 0;
	i = tableParti.getSelectedRow();
        if (tableParti.getRowCount() > 0) {
	    j = 0;
	    while (j < tableParti.getRowCount()) {
		if (tableParti.getValueAt(j, 1) == null) {
		    if (i == j) {
			flag = 1;
		    }
		    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
		    tableModel.setRowCount(tableParti.getRowCount() - 1);
		}
		j++;
	    }
	}

	if (flag == 0) {
	    if (i == tableParti.getRowCount() - 1) {
		DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
		tableModel.setRowCount(tableParti.getRowCount() - 1);
		if (tableParti.getRowCount() == 0) {
		    tfStockItemText.requestFocus();
		}
	    } else {
		j = i + 1;
		while (j < tableParti.getRowCount()) {
		    tableParti.setValueAt(tableParti.getValueAt(j, 1), j - 1, 1);
		    tableParti.setValueAt(tableParti.getValueAt(j, 2), j - 1, 2);
		    j++;
		}
		DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
		tableModel.setRowCount(tableParti.getRowCount() - 1);
            }
        }
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
            i = tableParti.getSelectedRow();
            if (i > -1) {
                Object o = tableParti.getValueAt(i, 0);
                if (o != null) {
                    temp = tableParti.getSelectedRow();
                    tfStockItemText.setText(tableParti.getValueAt(temp, 1).toString());
                    jTextFieldQuantity.setText(tableParti.getValueAt(temp, 2).toString());
                    buttonAdd.setLabel(Label.BUTTON_ALTER);
                    jComboBoxParti.requestFocus();
                    buttonParticularDelete.setEnabled(true);
                }
            }
        }
    }
    
    private void tablePartiKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        int flag = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            i = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                if (tableParti.getValueAt(i, 0) == null) {
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
            i = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                j = 0;
                while (j < tableParti.getRowCount()) {
                    if (tableParti.getValueAt(j, 1) == null) {
                        if (i == j) {
                            flag = 1;
                        }
                        DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                        tableModel.setRowCount(tableParti.getRowCount() - 1);
                    }
                    j++;
                }
            }
            if (flag == 0) {
                if (i == tableParti.getRowCount() - 1) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);
                    if (tableParti.getRowCount() == 0) {
                        tfStockItemText.requestFocus();
                    }
                } else {
                    j = i + 1;
                    while (j < tableParti.getRowCount()) {
                        tableParti.setValueAt(tableParti.getValueAt(j, 1), j - 1, 1);
                        tableParti.setValueAt(tableParti.getValueAt(j, 2), j - 1, 2);
                        tableParti.setValueAt(tableParti.getValueAt(j, 3), j - 1, 3);
                        tableParti.setValueAt(tableParti.getValueAt(j, 4), j - 1, 4);
                        tableParti.setValueAt(tableParti.getValueAt(j, 5), j - 1, 5);
                        
                        tableParti.setValueAt(tableParti.getValueAt(j, 6), j - 1, 6);
                        tableParti.setValueAt(tableParti.getValueAt(j, 7), j - 1, 7);
                        tableParti.setValueAt(tableParti.getValueAt(j, 8), j - 1, 8);
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
        System.out.println("Chalan Receipt No ------------ " + id);
        loadEditForm(id);
    }
    
    private void jTextFieldNoteFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 6;
	//jTextFieldNote.selectAll();
    }
    
    private void jTextFieldNoteKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            setFocus(evt);
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldDispatchDocNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 7;
        jTextFieldDispatchDocNo.selectAll();
    }
    
    private void jTextFieldDispatchDocNoKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldDispatchDocThroughFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 8;
        jTextFieldDispatchDocThrough.selectAll();
    }
    
    private void jTextFieldDispatchDocThroughKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (buttonSubmit.isEnabled()) {
                jTextFieldVatRate.requestFocus();
            } else {
                jTextFieldDispatchDocThrough.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldVatRateFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 9;
        jTextFieldVatRate.selectAll();
    }
    
    private void jTextFieldVatRateKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldVatAmountFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 10;
        jTextFieldVatAmount.selectAll();
    }
    
    private void jTextFieldVatAmountKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldLessBillFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 11;
        jTextFieldLessBill.selectAll();
    }
    
    private void jTextFieldLessBillKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }
    
    private void jTextFieldTransportFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 12;
        jTextFieldTransport.selectAll();
    }
    
    private void jTextFieldTransportKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTableTransactionList.getRowCount() > 0) {
                jTableTransactionList.requestFocus();
                jTableTransactionList.changeSelection(0, 0, false, false);
            }
        }
    }
    
    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException, Exception {
        
        List<ChalanDTO> chalanList = new ArrayList<ChalanDTO>();
        String text = jTextFieldSearch.getText().trim();
        
        for (ChalanDTO chalanDTOEntity : chalanDTOTransactionList) {
            if (chalanDTOEntity.getSaleLedger().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                chalanList.add(chalanDTOEntity);
            }
        }
        bindDTOToTransactionTable(chalanList);
        
    }
    
    private void buttonAddLedgerActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

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
    
    private void buttonAddItemActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

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
    
    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        getDesktopPane().setLayout(new CardLayout());
        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(4);
        ChalanForm1.this.setClosed(true);
        
    }
    
    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (validateData()) {
            submit();
            jComboBoxAccount.hidePopup();
            jComboBoxParti.hidePopup();
            jComboBoxSaleLedger.hidePopup();
            if (call_Submit_place == 0) {
                clearFormData();
                temp = 1000;
                buttonAdd.setText("ADD");
            }
            tfDatePicker.requestFocus();
        }
    }
    
    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        System.out.println("saleDTO.getTrans_ID()----------------->>" + chalanDTO.getTrans_ID());
        ChalanDAO.deleteTransaction(Long.parseLong(chalanDTO.getTrans_ID()));
        JOptionPane.showMessageDialog(this, "Transaction deleted completly");
        newButton();
        initilize();
        clearFormData();
        temp = 1000;
        buttonAdd.setText("ADD");
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxSaleLedger.hidePopup();
        tfDatePicker.requestFocus();
    }
    
    private void buttonParticularDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (tableParti.isRowSelected(indexForDeletion)) {
            buttonParticularDelete.setVisible(true);
        }
        partiDelete();
    }
    
    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (buttonDelete.isEnabled()) {
            
            try {
                Connection conn1 = DatabaseConnection1.GetConnection();
                Long trans_id = 0l;
                String q = "select trans_id from tbltransactionmain where trans_receiptno = ?  and trans_typeIndex = ?";
                PreparedStatement prmt1 = conn1.prepareStatement(q);
                prmt1.setString(1, jTextFieldReceiptNo.getText().trim());
                prmt1.setString(2, "7");
                ResultSet rs2 = prmt1.executeQuery();
                while (rs2.next()) {
                    trans_id = rs2.getLong("trans_id");
                }
                
                int i = 0;
                long id = 0;
                String address = "";
                String CustomerVatTin = "";
                String str = "select ledger_id,ledger_address,ledger_saleTaxNo from tblledger where ledger_name = ?";
                prmt1 = conn1.prepareStatement(str);
                prmt1.setString(1, tfAccountText.getText().toString().trim());
                ResultSet rs3 = prmt1.executeQuery();
                while (rs3.next()) {
                    id = rs3.getLong("ledger_id");
                    address = "" + rs3.getString("ledger_address");
                    if (address.trim().equalsIgnoreCase("")) {
                        address = " ";
                    }
                    CustomerVatTin = "" + rs3.getString("ledger_saleTaxNo");
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
//		str = "select ledger_address,ledger_saleTaxNo from tblledger where ledger_id=" + id + "";
//		ResultSet rs2 = st3.executeQuery(str);
//		String address = "";
//		String CustomerVatTin = "";
//		while (rs2.next()) {
//		    address = "" + rs2.getString("ledger_address");
//		    if (address.trim().equalsIgnoreCase("")) {
//			address = " ";
//		    }
//		    CustomerVatTin = "" + rs2.getString("ledger_saleTaxNo");
//		    if (CustomerVatTin.trim().equalsIgnoreCase("")) {
//			CustomerVatTin = " ";
//		    }
//		}

                gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
                Connection conn = DatabaseConnection1.GetConnection();
                q = "select * from TBLCOMPANYINFO where company_name = ? and company_ID = ?";
                PreparedStatement prmt = conn.prepareStatement(q);
                prmt.setString(1, gen.dto.Constants.CURRENT_COMPANY_NAME);
                prmt.setString(2, gen.dto.Constants.CURRENT_COMPANY_ID);
                ResultSet rs1 = prmt.executeQuery();
                int k = 0;
                if (rs1.next()) {
                    Map parameter = new HashMap();
                    String NameOfGod = rs1.getString("NameOfGod");
                    String companyname = rs1.getString("COMPANY_NAME");
                    String Address = rs1.getString("Address");
                    String contactno = rs1.getString("ContactNo");
                    String Emailid = rs1.getString("EmailId");
                    String Tagline = rs1.getString("Tagline");
                    String Buyers = "" + tfAccountText.getText().trim();
                    
                    String InVoiceNo = "" + jTextFieldReceiptNo.getText().trim();
                    String DispatchThrough = jTextFieldDispatchDocThrough.getText().trim();
                    String Declaration = rs1.getString("Note");
                    
                    if (InVoiceNo.equals("")) {
                        InVoiceNo = "--";
                    } else {
                        InVoiceNo = "" + jTextFieldReceiptNo.getText().trim();
                    }
                    if (DispatchThrough.equals("")) {
                        DispatchThrough = " ";
                    } else {
                        DispatchThrough = "" + jTextFieldDispatchDocThrough.getText().trim();
                    }
                    
                    if (DispatchThrough.equals("")) {
                        DispatchThrough = " ";
                    } else {
                        DispatchThrough = "" + jTextFieldDispatchDocThrough.getText().trim();
                    }
//		    /*Code For Image*/
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
                    }
                    parameter.put("logo", image1);


//                    int i = 0;
//                    long id = 0;
//		    String str = "select ledger_id from tblledger where ledger_name='" + tfAccountText.getText().toString().trim() + "'";
//                    ResultSet rs3 = st3.executeQuery(str);
//                    while (rs3.next()) {
//                        id = rs3.getLong("ledger_id");
//                    }
//                    str = "select ledger_address,ledger_saleTaxNo from tblledger where ledger_id=" + id + "";
//                    ResultSet rs2 = st3.executeQuery(str);
//                    String address = "";
//                    String CustomerVatTin = "";
//                    while (rs2.next()) {
//                        address = "" + rs2.getString("ledger_address");
//                        if (address.trim().equalsIgnoreCase("")) {
//                            address = " ";
//                        }
//                        CustomerVatTin = "" + rs2.getString("ledger_saleTaxNo");
//                        if (CustomerVatTin.trim().equalsIgnoreCase("")) {
//                            CustomerVatTin = " ";
//                        }
//                    }

//		    parameter.put("logo", image1);
                    parameter.put("GodName", NameOfGod);
                    parameter.put("CompanyName", companyname);
                    parameter.put("BuyerAddress", address);
                    parameter.put("CompanyEmail", Emailid);
                    parameter.put("CompanyPhone", contactno);
                    parameter.put("CompanyDescription", Tagline);
                    parameter.put("Buyersname", Buyers);
                    parameter.put("InVoiceNo.", InVoiceNo);
                    parameter.put("Declaration", Declaration);
                    parameter.put("dispatchthrough", DispatchThrough);
                    SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
                    if (jDateChooser.getDate() == null) {
                        parameter.put("trans_date", "--");
                    } else {
                        parameter.put("trans_date", saDa.format(jDateChooser.getDate()).toString());
                    }
                    parameter.put("Declaration", Declaration);
                    parameter.put("CompanySign", companyname);
                    parameter.put("SalesTaxPercent", "");

                    parameter.put("VatPercent", jTextFieldVatRate.getText().toString().trim());
                    parameter.put("PercentAmount", jTextFieldVatAmount.getText().toString().trim());
                    parameter.put("packging", jTextFieldLessBill.getText().toString().trim());
                    parameter.put("GrandTotalAmount", jTextFieldTransport.getText().toString().trim());

                    InputStream logowoodstuff = this.getClass().getResourceAsStream("/images/Woodstuff logo.jpg");
                    InputStream logoplymate = this.getClass().getResourceAsStream("/images/NEW Duracraft.jpg");
//		    PrintAllReport.printChalanReport(String.valueOf(trans_id), parameter);
//		    PrintAllReport.printSalesReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
//		    PrintAllReport.printChalanReport(String.valueOf(trans_id), parameter);
                    PrintAllReport.printChalanEstimateReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                    PrintAllReport.printChalanReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
                } else {
                    Map parameter = new HashMap();
                    String NameOfGod = "";
                    String companyname = "";
                    String Address = "";
                    String contactno = "";
                    String Emailid = "";
                    String Tagline = "";
                    String Declaration = "";
                    String Buyers = "" + tfAccountText.getText().trim();
                    String InVoiceNo = "" + jTextFieldReceiptNo.getText().trim();
                    String DispatchThrough = jTextFieldDispatchDocThrough.getText().trim();


                    if (InVoiceNo.equals("")) {
                        InVoiceNo = "--";
                    } else {
                        InVoiceNo = "" + jTextFieldReceiptNo.getText().trim();
                    }
                    if (DispatchThrough.equals("")) {
                        DispatchThrough = " ";
                    } else {
                        DispatchThrough = "" + jTextFieldDispatchDocThrough.getText().trim();
                    }

                    if (DispatchThrough.equals("")) {
                        DispatchThrough = " ";
                    } else {
                        DispatchThrough = "" + jTextFieldDispatchDocThrough.getText().trim();
                    }
                    /*Code For Image*/
//		    InputStream img1 = this.getClass().getResourceAsStream("/images/unnati_hinges_packing1_1.jpg");
//		    parameter.put("logo", img1);
//		    Blob image = null;
//		    ResultSet rs5;
//		    q = "select * from registration";
//		    rs5 = st2.executeQuery(q);
//		    while (rs5.next()) {
//			image = rs5.getBlob("image");
//		    }
//		    java.io.InputStream image1 = null;
//		    //System.out.println("HI EverOne"+image==null);
//		    if (image == null) {
//			image1 = null;
//		    } else {
//			image1 = image.getBinaryStream();
//		    }

//		    int i = 0;
//		    long id = 0;
//		    String str = "select ledger_id from tblledger where ledger_name='" + tfAccountText.getText().toString().trim() + "'";
//		    ResultSet rs3 = st3.executeQuery(str);
//		    while (rs3.next()) {
//			id = rs3.getLong("ledger_id");
//		    }
//		    str = "select ledger_address,ledger_saleTaxNo from tblledger where ledger_id=" + id + "";
//		    ResultSet rs2 = st3.executeQuery(str);
//		    String address = "";
//		    String CustomerVatTin = "";
//		    while (rs2.next()) {
//			address = "" + rs2.getString("ledger_address");
//			if (address.trim().equalsIgnoreCase("")) {
//			    address = " ";
//			}
//			CustomerVatTin = "" + rs2.getString("ledger_saleTaxNo");
//			if (CustomerVatTin.trim().equalsIgnoreCase("")) {
//			    CustomerVatTin = " ";
//			}
//		    }

//		    parameter.put("logo", image1);
                    parameter.put("GodName", NameOfGod);
                    parameter.put("CompanyName", companyname);
                    parameter.put("BuyerAddress", address);
                    parameter.put("CompanyEmail", Emailid);
                    parameter.put("CompanyPhone", contactno);
                    parameter.put("CompanyDescription", Tagline);
                    parameter.put("Buyersname", Buyers);
                    parameter.put("InVoiceNo.", InVoiceNo);
                    parameter.put("Declaration", Declaration);
                    parameter.put("dispatchthrough", DispatchThrough);
                    SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
                    if (jDateChooser.getDate() == null) {
                        parameter.put("trans_date", "--");
                    } else {
                        parameter.put("trans_date", saDa.format(jDateChooser.getDate()).toString());
                    }

                    System.out.println("VatPercent ------- " + jTextFieldVatRate.getText().toString().trim());
                    System.out.println("PercentAmount ------- " + jTextFieldVatAmount.getText().toString().trim());
                    System.out.println("packging ------- " + jTextFieldLessBill.getText().toString().trim());
                    System.out.println("GrandTotalAmount ------- " + jTextFieldTransport.getText().toString().trim());

                    parameter.put("VatPercent", jTextFieldVatRate.getText().toString().trim());
                    parameter.put("PercentAmount", jTextFieldVatAmount.getText().toString().trim());
                    parameter.put("packging", jTextFieldLessBill.getText().toString().trim());
                    parameter.put("GrandTotalAmount", jTextFieldTransport.getText().toString().trim());

                    parameter.put("Declaration", Declaration);
                    parameter.put("CompanySign", companyname);
                    parameter.put("SalesTaxPercent", "");
                    InputStream logowoodstuff = this.getClass().getResourceAsStream("/images/Woodstuff logo.jpg");
                    InputStream logoplymate = this.getClass().getResourceAsStream("/images/NEW Duracraft.jpg");
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
                    if (image1 != null) {
                        parameter.put("logo", image1);
                    } else {
                        parameter.put("logo", "");
                    }
                    PrintAllReport.printChalanEstimateReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                    PrintAllReport.printChalanReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                Logger.getLogger(ChalanForm1.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxSaleLedger.hidePopup();
    }
    
    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        newButton();
        clearFormData();
        initilize();
        temp = 1000;
        buttonAdd.setText("ADD");
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        jComboBoxSaleLedger.hidePopup();
    }
    
    private void buttonGenerateInvoiceActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        getDesktopPane().setLayout(new CardLayout(0, 0));
        if (validateData()) {
            if (!validate_Invoice_For_Sale()) {
                // generate Invoice code is present in buttonsubmitActonPerformed
                call_Submit_place = 1;
                buttonSubmitActionPerformed(evt);
                call_Submit_place = 0;
            } else {
                JOptionPane.showMessageDialog(ChalanForm1.this, Label.ALREADY_INVOICE_GENERATED);
            }
        }
    }
    
    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        dataExport();
    }
    
    private void buttonNextTransactionsActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
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
    private JLabel labelDate;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private JLabel labelAccount;
    private JComboBox jComboBoxAccount;
    private JLabel labelSaleLedger;
    private JComboBox jComboBoxSaleLedger;
    private JLabel labelSN;
    private JLabel labelParticulars;
    private JComboBox jComboBoxParti;
    private JLabel labelLength;
    private JTextField jTextFieldLngth;
    private JLabel labelWidth;
    private JTextField jTextFieldWidth;
    private JLabel labelThickness;
    private JTextField jTextFieldThickness;
    private JLabel labelQuantity;
    private JTextField jTextFieldQuantity;
    private JLabel labelSquareFeet;
    private JTextField jTextFieldSquareFeet;
    private JLabel labelRate;
    private JTextField jTextFieldRate;
    private JLabel labelAmount;
    private JTextField jTextFieldAmount;
    private JButton buttonAdd;
    private JLabel labelNote;
    private JTextArea jTextFieldNote;
    private JLabel labelDispatchDocNo;
    private JTextField jTextFieldDispatchDocNo;
    private JLabel labelDispatchDocThrough;
    private JTextField jTextFieldDispatchDocThrough;
    private JLabel labelBasicAmount;
    private JLabel labeltxtBasicAmount;
    private JLabel labelVatRate;
    private JTextField jTextFieldVatRate;
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
    private JButton buttonParticularDelete;
    private JButton buttonNew;
    private JButton buttonPrint;
    private JButton buttonGenerateInvoice;
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
        buttonPrint.setEnabled(false);
        buttonExport.setEnabled(false);
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
    
    private void initVariables() throws SQLException, Exception {
        initLedger();
        initStockItem();
        if (!isEdit) {
            chalanDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.CHALAN_TYPE_INDEX));
        }
        ledgerTimeStamp = Constants.LEDGER_TIME_STAMP;
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
        buttonParticularDelete.setEnabled(false);
    }
    
    private void initLedger() throws SQLException, Exception {
        List<String> groups = new ArrayList<String>();
        groups.add(Constants.SALES_ACCOUNT);
        groups.add(Constants.PURCHASE_ACCOUNT);
        accountLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, false);
        groups = new ArrayList<String>();
        groups.add(Constants.SALES_ACCOUNT);
        saleLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, true);
        
        
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
        
        saleLedgerVector.clear();
        for (String str : saleLedgerMap.keySet()) {
            saleLedgerVector.add(str);
        }
        
        Collections.sort(
                saleLedgerVector,
                new Comparator<String>() {
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });
        
        saleLedgerMap = Util.getSmallCaseMap(saleLedgerMap);
        accountLedgerMap = Util.getSmallCaseMap(accountLedgerMap);
    }
    
    private void initStockItem() throws SQLException, Exception {
        stockItemMap = StockItemDAO.getStockItemsFromGroupName(null, false);
        stockItemVector.clear();
        for (String str : stockItemMap.keySet()) {
            stockItemVector.add(str);
        }
        
        Collections.sort(
                stockItemVector,
                new Comparator<String>() {
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });
        
        stockItemMap = Util.getSmallCaseMap(stockItemMap);
    }
    
    private void bindTOGUI() throws ParseException, SQLException, Exception {
        gen.dto.Constants.OFFSET_VALUE = 0L;
        
        Long number = 0L;
        number = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.CHALAN_TYPE_INDEX, Constants.CREDIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        Long remaining = 0L;
        remaining = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.CHALAN_TYPE_INDEX, Constants.CREDIT) % gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        
        if (remaining == 0) {
            gen.dto.Constants.OFFSET_VALUE = Math.abs((number - 1) * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        } else {
            gen.dto.Constants.OFFSET_VALUE = Math.abs(number * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        }
        bindDTOtoGUI();
        bindDTOtoJtable();
        if (!isEdit || isTransactionTableLoad) {
            System.out.println("Transaction List---->>>");
            initTransactionList();
        }
        isTransactionTableLoad = false;
    }
    
    private void bindDTOtoGUI() throws Exception {
        tfAccountText.setText(chalanDTO.getCashLedger());
        tfSaleAccountText.setText(chalanDTO.getSaleLedger());
        jTextFieldReceiptNo.setText(chalanDTO.getReceiptNo().toString());
        jTextFieldNote.setText(chalanDTO.getNote());
        jTextFieldDispatchDocNo.setText(chalanDTO.getDispatchDocNo() + "");
        jTextFieldDispatchDocThrough.setText(chalanDTO.getDispatchDocThrough() + "");
        jTextFieldVatRate.setText(chalanDTO.getVatRate() + "");
        jTextFieldVatAmount.setText(chalanDTO.getVatAmount() + "");
        jTextFieldLessBill.setText(chalanDTO.getLessBillAmount() + "");
        jTextFieldTransport.setText(chalanDTO.getTransport() + "");
        

			if (chalanDTO.getStockItemTreansactioDTOList().isEmpty() || chalanDTO.getStockItemTreansactioDTOList() == null) {
	    jTextFieldQuantity.setText("0.0");
	}
	
        if (!validate_Invoice_For_Sale()) {
            buttonGenerateInvoice.setEnabled(true);
        } else {
            buttonGenerateInvoice.setEnabled(false);
        }
        
        if (!validate_Invoice_For_Sale()) {
            buttonSubmit.setEnabled(true);
            if (isEdit) {
                buttonDelete.setEnabled(true);
            }
        } else {
            buttonSubmit.setEnabled(false);
            buttonDelete.setEnabled(false);
        }
        
        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(chalanDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(chalanDTO.getDate().trim()));
        }
        
    }
    
    private void bindDTOtoJtable() throws Exception {
        int index = 1;
        List<StockItemTransactionDTO> stockItemTransactionDTOList = chalanDTO.getStockItemTreansactioDTOList();
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 2);
                index++;
            }
        }
        
    }
    
    private void bindDTOtoGUIWithoutReceiptNo() throws Exception {
        tfAccountText.setText(chalanDTO.getCashLedger());
        tfSaleAccountText.setText(chalanDTO.getSaleLedger());
        jTextFieldNote.setText(chalanDTO.getNote());
        jTextFieldDispatchDocNo.setText(chalanDTO.getDispatchDocNo() + "");
        jTextFieldDispatchDocThrough.setText(chalanDTO.getDispatchDocThrough() + "");
        tfDatePicker.setText(new ChalanDTO().getDate());
    }
    
    private void submit() throws SQLException, Exception {
        chalanDTO = bindGUITODTO();
        chalanDTO = ImpExpUtil.valiDateAndRepairChalanDTO(chalanDTO);
        chalanDTO.setSaleLedger(saleLedgerMap.get(chalanDTO.getSaleLedger().trim().toLowerCase()));
        chalanDTO.setCashLedger(accountLedgerMap.get(chalanDTO.getCashLedger().trim().toLowerCase()));
        for (StockItemTransactionDTO stockItemTransactionDTO : chalanDTO.getStockItemTreansactioDTOList()) {
            stockItemTransactionDTO.setName(stockItemMap.get(stockItemTransactionDTO.getName().toLowerCase().trim()));
        }
        
        String receipt_no = chalanDTO.getReceiptNo() + "";
        
        if (!isEdit) {
            insertChalan();
        } else {
            updateChalan();
        }
        JOptionPane.showMessageDialog(jMainPanel, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        
        if (call_Submit_place == 0) {
            newButton();
        } else {
            initTransactionList();
            generateInvoice(receipt_no);
        }
        
        if (call_Submit_place == 1) {
            loadEditForm(receipt_no);
        }
    }
    
    private ChalanDTO bindGUITODTO() throws Exception {
        ChalanDTO chalanDTOEntity = new ChalanDTO();
        chalanDTOEntity.setTrans_ID(chalanDTO.getTrans_ID());
        
        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
	    chalanDTOEntity.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setCashLedger(tfAccountText.getText().trim());
        }
        if (!tfSaleAccountText.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setSaleLedger(tfSaleAccountText.getText().trim());
        }
        
        chalanDTOEntity.setStockItemTreansactioDTOList(bindtableToDTO());
        
        if (!jTextFieldNote.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setNote(jTextFieldNote.getText().trim());
        }
        if (!jTextFieldDispatchDocNo.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setDispatchDocNo(jTextFieldDispatchDocNo.getText().trim());
        }
        if (!jTextFieldDispatchDocThrough.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setDispatchDocThrough(jTextFieldDispatchDocThrough.getText().trim());
        }
        
        if (!jTextFieldVatRate.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setVatRate(Double.parseDouble(jTextFieldVatRate.getText().trim()));
        }
        if (!jTextFieldVatAmount.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setVatAmount(Double.parseDouble(jTextFieldVatAmount.getText().trim()));
        }
        if (!jTextFieldLessBill.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setLessBillAmount(Double.parseDouble(jTextFieldLessBill.getText().trim()));
        }
        if (!jTextFieldTransport.getText().trim().equalsIgnoreCase("")) {
            chalanDTOEntity.setTransport(Double.parseDouble(jTextFieldTransport.getText().trim()));
        }
        
        return chalanDTOEntity;
    }
    
    private List<StockItemTransactionDTO> bindtableToDTO() throws Exception {
        List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        for (int index = 0; index < tableParti.getRowCount(); index++) {
            
            if (!tableParti.getValueAt(index, 1).toString().trim().equalsIgnoreCase("")) {
                StockItemTransactionDTO stockItemDTO = new StockItemTransactionDTO();
                stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
                stockItemDTO.setQuantity(Double.parseDouble(tableParti.getValueAt(index, 2).toString().trim()));
                stockItemTransactionDTOList.add(stockItemDTO);
            }
        }
        return stockItemTransactionDTOList;
    }
    
    private void insertChalan() throws SQLException, ParseException, Exception {
        List<ChalanDTO> chalanDTOList = new ArrayList<ChalanDTO>();
        chalanDTOList.add(chalanDTO);
        ChalanDAO.insertChalanVoucher(chalanDTOList);
        AccountingVoucherHelper.updateVoucherNumber(Constants.CHALAN_TYPE_INDEX, (chalanDTO.getReceiptNo()));
    }
    
    private void updateChalan() throws SQLException, ParseException, Exception {
        List<ChalanDTO> chalanDTOList = new ArrayList<ChalanDTO>();
        chalanDTOList.add(chalanDTO);
        ChalanDAO.updateChalanVoucher(chalanDTOList);
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
        if (currentFocusValue > 12) {
            currentFocusValue = 12;
        }
        
        
        if (currentFocusValue == 0) {
            jTextFieldReceiptNo.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            tfAccountText.requestFocus();
        } else if (currentFocusValue == 3) {
            tfSaleAccountText.requestFocus();
        } else if (currentFocusValue == 4) {
            tfStockItemText.requestFocus();
        } else if (currentFocusValue == 5) {
            jTextFieldQuantity.requestFocus();
        } else if (currentFocusValue == 6) {
            jTextFieldNote.requestFocus();
        } else if (currentFocusValue == 7) {
            jTextFieldDispatchDocNo.requestFocus();
        } else if (currentFocusValue == 8) {
            jTextFieldDispatchDocThrough.requestFocus();
        } else if (currentFocusValue == 9) {
            jTextFieldVatRate.requestFocus();
        } else if (currentFocusValue == 10) {
            jTextFieldVatAmount.requestFocus();
        } else if (currentFocusValue == 11) {
            jTextFieldLessBill.requestFocus();
        } else if (currentFocusValue == 12) {
            jTextFieldTransport.requestFocus();
        }
    }
    
    private Boolean validateData() throws Exception {
        
        jTextFieldVatAmountResult = new BigDecimal(jTextFieldVatAmount.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        jTextFieldLessBillAmountResult = new BigDecimal(jTextFieldLessBill.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        jTextFieldTransportResult = new BigDecimal(jTextFieldTransport.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        
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
        } else if (validate_Invoice_For_Sale()) {
            initTransactionList();
            JOptionPane.showMessageDialog(jMainPanel, Label.ALREADY_INVOICE_GENERATED);
            flag = false;
            System.out.println("Validate Invoice For Sale =========>>>>");
            buttonGenerateInvoice.setEnabled(false);
            buttonSubmit.setEnabled(false);
            buttonDelete.setEnabled(false);
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
        } else if (jTextFieldNote.getText().trim().toCharArray().length > Constants.jTextAreaCharacterLengthEXTRALARGE) {
            jTextFieldNote.requestFocus();
            throw new Exception("Note Data Exceeding " + Constants.jTextAreaCharacterLengthEXTRALARGE + " Character Limit");
        } else if (jTextFieldDispatchDocNo.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldDispatchDocNo.requestFocus();
            throw new Exception("Dispatch Document Number Data Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldDispatchDocThrough.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldDispatchDocThrough.requestFocus();
            throw new Exception("Vehicle Number Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (Double.parseDouble(jTextFieldVatRate.getText().trim()) > Constants.jTextFieldCharacterLengthMEDIUM) {
            jTextFieldVatRate.requestFocus();
            throw new Exception("VAT Rate Exceeding " + Constants.jTextAreaVATPERCENT + " Percent Limit");
        } else if (jTextFieldVatAmount.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldVatAmountResult == 1) {
            jTextFieldVatAmount.requestFocus();
            throw new Exception("VAT only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldLessBill.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldLessBill.requestFocus();
            throw new Exception("Less Bill only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldLessBillAmountResult == 1) {
            jTextFieldLessBill.requestFocus();
            throw new Exception("Less Bill only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldTransport.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldTransport.requestFocus();
            throw new Exception("Transport only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (jTextFieldTransportResult == 1) {
            jTextFieldTransport.requestFocus();
            throw new Exception("Transport only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        }
        
        return flag;
    }
    
    private Boolean validate_Invoice_For_Sale() throws Exception {
        Boolean check = gen.accountvoucher.chalan.ChalanDAO.check_Chalan_For_Generate_Invoice(jTextFieldReceiptNo.getText().toString().trim());
        return check;
    }
    
    private void addTOJTable() throws Exception {
        
        i = 0;
        while (i < tableParti.getRowCount()) {
            if (tableParti.getValueAt(i, 0) == null) {
                int row = tableParti.getRowCount();
                partiTableModel.setRowCount(row - 1);
            }
            i++;
        }
        
        if (temp == 1000) {
            
            int row = tableParti.getRowCount();
            partiTableModel.setRowCount(row + 1);
            tableParti.setValueAt(row + 1, row, 0);
            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
        } else {
            int row = temp;
            tableParti.setValueAt(tfStockItemText.getText().trim(), row, 1);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldQuantity.getText().trim())), row, 2);
            temp = 1000;
            buttonAdd.setLabel(Label.BUTTON_ADD);
            tfStockItemText.requestFocus();
            tableParti.clearSelection();
        }
        tfStockItemText.requestFocus();
    }
    
    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<ChalanDTO> chalanDTOList = ChalanDAO.getChalan(idSet, "");
        System.out.println("IDIDIDIDIDIDID::::::::::::::::::::::;;" + id);
        //System.out.println("chalanDTOList           ::::::::::::::::::::::;;"+ chalanDTOList.isEmpty() +"     "+chalanDTOList.retainAll(idSet));
        if (chalanDTOList != null && !chalanDTOList.isEmpty()) {
            chalanDTO = chalanDTOList.get(0);
            isEdit = true;
            partiTableModel.setRowCount(0);
            jTextFieldReceiptNo.setEditable(false);
            buttonDelete.setEnabled(true);
            buttonPrint.setEnabled(true);
            buttonExport.setEnabled(true);
            bindTOGUI();
            currentFocusValue = 1;
            setFocus(null);
        }
    }
    
    private Boolean validateStockTransaction() throws Exception {
        if (!jTextFieldQuantity.getText().trim().isEmpty()) {
            quantityResult = new BigDecimal(jTextFieldQuantity.getText().trim()).compareTo(Constants.jTextFieldAmountLength);
        }
        if (stockItemMap.get(tfStockItemText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_VALUE_IS_NOT_VALID);
            tfStockItemText.requestFocus();
            currentFocusValue = 3;
            return false;
        }
        if ((jTextFieldQuantity.getText().trim().equalsIgnoreCase("")) || (jTextFieldQuantity.getText().trim().equalsIgnoreCase(".")) || (Double.parseDouble(jTextFieldQuantity.getText().trim()) == 0D)) {
            JOptionPane.showMessageDialog(jMainPanel, Label.QUANTITY_NOT_VALID);
            jTextFieldQuantity.requestFocus();
            currentFocusValue = 4;
            return false;
        } else if (quantityResult == 1) {
            jTextFieldQuantity.requestFocus();
            throw new Exception("Quantity only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else {
            currentFocusValue = 3;
	}
//	else if (jTextFieldQuantity.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthEXTRASMALL) {
//	    jTextFieldQuantity.requestFocus();
//	    throw new Exception("Quantity Exceeding "+Constants.jTextFieldCharacterLengthEXTRASMALL+" digit Limit");
//	}

        return true;
    }
    
    private void bindDTOToTransactionTable(List<ChalanDTO> chalanDTOTransactionList) throws SQLException, Exception {
        List<String> Invoice_Generated_chalan_Trans_ID_List = gen.accountvoucher.chalan.ChalanDAO.getInvoice_Generated_Chalan_trans_ID();
        
        System.out.println("$$$$$$$$$$$$$$$$$$4---------------" + chalanDTOTransactionList.isEmpty());
        if (chalanDTOTransactionList != null && !chalanDTOTransactionList.isEmpty()) {
            transactionTableModel1.setRowCount(0);
            for (ChalanDTO chalanDTOEntity : chalanDTOTransactionList) {
                transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                transactionTableModel1.setValueAt(chalanDTOEntity.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(chalanDTOEntity.getDate().trim());
                transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                transactionTableModel1.setValueAt(chalanDTOEntity.getSaleLedger(), transactionTableModel1.getRowCount() - 1, 2);
                
                if (Invoice_Generated_chalan_Trans_ID_List.contains(chalanDTOEntity.getTrans_ID().toString().trim())) {
                    transactionTableModel1.setValueAt("Yes", transactionTableModel1.getRowCount() - 1, 3);
                } else {
                    transactionTableModel1.setValueAt("No", transactionTableModel1.getRowCount() - 1, 3);
                }
            }
        } else {
	    if (last_Number_In_TransactionList != 1) {
            transactionTableModel1.setRowCount(0);
        }
	}
	last_Number_In_TransactionList = 0;
    }
    
    private void newButton() throws ParseException, SQLException, Exception {
        jTextFieldReceiptNo.setEnabled(true);
        chalanDTO = new ChalanDTO();
        isEdit = false;
        chalanDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.CHALAN_TYPE_INDEX));
        initTransactionList();
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);
    }
    
    private void generateInvoice(String chalan_Receipt_NO) throws Exception {
        SaleDTO saleDTO = bindGUITOSaleDTO(chalan_Receipt_NO);
        
        if (saleDTO != null) {
            SaleForm sale = new SaleForm("Sales", this.getPreferredSize(), saleDTO, false);
            sale.setVisible(true);
            sale.pack();
            sale.setVisible(true);
            this.getParent().add(sale);
            this.getParent().setVisible(true);
            Dimension desktopSize = this.getSize();
            sale.setSize(desktopSize);
            sale.setPreferredSize(desktopSize);
            
            try {
                sale.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
            
            BasicInternalFrameUI uiInternalFrame = (BasicInternalFrameUI) sale.getUI();
            Component north = uiInternalFrame.getNorthPane();
            MouseMotionListener[] actions =
                    (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);
            
            for (int index = 0;
                    index < actions.length;
                    index++) {
                north.removeMouseMotionListener(actions[index]);
            }
        }
    }
    
    private SaleDTO bindGUITOSaleDTO(String chalan_Receipt_NO) throws SQLException, Exception {
        SaleDTO saleDTOEntity = new SaleDTO();
        StockItemTransactionDTO stockItemDTO = new StockItemTransactionDTO();
        
        saleDTOEntity.setChallan_trans_ID(gen.accountvoucher.chalan.ChalanDAO.getChalan_trans_ID(jTextFieldReceiptNo.getText().toString().trim()));
        saleDTOEntity.setChallan_receipt_No(chalan_Receipt_NO);
        
        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
            //saleDTOEntity.setDate(Constants.simpleDateFormatDatabase.format(jDateChooser.getDate()).toString().trim());
            saleDTOEntity.setDate(Constants.DATE_FORMATER.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setCashLedger(tfAccountText.getText().trim());
        }
        if (!tfSaleAccountText.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setSaleLedger(tfSaleAccountText.getText().trim());
        }
        
        saleDTOEntity.setStockItemTreansactioDTOList(bindtableToSaleDTO());
        
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
        if (!jTextFieldLessBill.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setLessBillAmount(Double.parseDouble(jTextFieldLessBill.getText().trim()));
        }
        if (!jTextFieldTransport.getText().trim().equalsIgnoreCase("")) {
            saleDTOEntity.setTransport(Double.parseDouble(jTextFieldTransport.getText().trim()));
        }
        
        return saleDTOEntity;
    }
    
    private List<StockItemTransactionDTO> bindtableToSaleDTO() throws SQLException, Exception {
        List<StockItemTransactionDTO> stockItemTransactionDTOList = new ArrayList<StockItemTransactionDTO>();
        Set<String> nameSet = new HashSet<String>();
        for (int index = 0; index < tableParti.getRowCount(); index++) {
            
            if (!tableParti.getValueAt(index, 1).toString().trim().equalsIgnoreCase("")) {
                StockItemTransactionDTO stockItemDTO = new StockItemTransactionDTO();
                stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
                nameSet.add(stockItemDTO.getName());
                if (!tableParti.getValueAt(index, 2).toString().trim().equalsIgnoreCase("")) {
                }
                stockItemDTO.setQuantity(Double.parseDouble(tableParti.getValueAt(index, 2).toString().trim()));
                stockItemTransactionDTOList.add(stockItemDTO);
            }
        }
        Map<String, StockItemTransactionDTO> stockItemMap = StockItemDAO.getStockItemValues(nameSet);
        for (StockItemTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
            stockItemTransactionDTO.setLength(stockItemMap.get(stockItemTransactionDTO.getName()).getLength());
            stockItemTransactionDTO.setWidth(stockItemMap.get(stockItemTransactionDTO.getName()).getWidth());
            stockItemTransactionDTO.setThkness(stockItemMap.get(stockItemTransactionDTO.getName()).getThkness());
        }
        return stockItemTransactionDTOList;
    }
    
    private void clearFormData() {
        tfStockItemText.setText("");
	jTextFieldQuantity.setText("0.0");
        buttonParticularDelete.setEnabled(false);
    }
    //To Do Export Data
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
            
            int r = chooser.showSaveDialog(ChalanForm1.this);
            
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
        Set<String> chalanSet = new HashSet<String>();
        chalanSet.add(jTextFieldReceiptNo.getText().trim());
        
        BufferedWriter out = null;
        if (!path.isEmpty()) {
            try {
                // TODO add your handling code here:

                Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
                IDMapSet.put("Chalan", chalanSet);
                String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
                System.out.println("------------------------->>XMLCODE:" + xmlCode);

                // File file = new File("C:\\DayBook.xml");
                File file = new File(path + ".xml");
                path = file.getPath();
                out = new BufferedWriter(new FileWriter(file));
                out.write(xmlCode);
                out.close();
                
                JOptionPane.showMessageDialog(ChalanForm1.this, "Export Successful");
                
            } catch (Exception ex) {
                Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(ChalanForm1.this, "Export failure");
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
                Copy_Transaction.addActionListener(ChalanForm1.this);
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
                List<ChalanDTO> saleDTOList = ChalanDAO.getChalan(idSet, "");
                if (saleDTOList != null && !saleDTOList.isEmpty()) {
                    try {
                        chalanDTO = saleDTOList.get(0);
                        partiTableModel.setRowCount(0);
                        jTextFieldReceiptNo.setEditable(true);
                        buttonDelete.setEnabled(true);
                        buttonPrint.setEnabled(true);
                        buttonExport.setEnabled(true);
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
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
        }
    }
    
    private void initTransactionList() throws Exception {
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
                        try {
                            chalanDTOTransactionList = ChalanDAO.getTransactionList();
                            bindDTOToTransactionTable(chalanDTOTransactionList);
                        } catch (Exception ex) {
                            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(ChalanForm1.this, ex.getMessage());
                        }
//                    }
//                }).start();
    }
}
