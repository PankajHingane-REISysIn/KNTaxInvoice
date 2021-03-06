/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.impregnatedpaperproduction;

/**
 *
 * @author admin
 */
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import gen.dto.Label;
import gen.dto.Constants;

import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.account.StockItemFormation.StockItemDTO;
import gen.accountvoucher.TableCellListener;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.display.report.StockItemReport;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.feature.production.finisheditemdefination.AddFinishMaterialForm;
import java.sql.Statement;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;

public class ImpregnatedPaperProduction extends JInternalFrame {

    List<ImpregnatedPaperProductionDTO> impregnatedPaperProductionDTOList_For_Transactions = new ArrayList<ImpregnatedPaperProductionDTO>();
    List<ImpregnatedPaperProductionDTO> impregnatedPaperProductionDTOList = new ArrayList<ImpregnatedPaperProductionDTO>();
    ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO = new ImpregnatedPaperProductionDTO();
    private Long stockItemTimeStamp;
    private int currentFocusValue = 0;
    private Vector<String> vectorFinishStockItem = new Vector<String>();
    private Boolean isEdit = false;
    private Map<String, String> mapFinishStockItem = new HashMap<String, String>();
    private Map<String, String> mapResinStockItemClosingBalance = new HashMap<String, String>();
    private Boolean isTransactionTableLoad = false;
    private Map<String, String> mapAllStockItem = new HashMap<String, String>();
    private String checkDateisChangeOrNot = "";
    private Map<String, ImpregnatedPaperProductionDTO> mapFinishItemWithRawItems = new HashMap<String, ImpregnatedPaperProductionDTO>();

    public ImpregnatedPaperProduction(String s, Dimension d) {
        try {
            initComponents();
            impregnatedPaperProductionDTO = new ImpregnatedPaperProductionDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public void initComponents() {
        setFrameIcon(new ImageIcon(ImpregnatedPaperProduction.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Impregnated Paper Production");
        setBounds(100, 100, 1332, 674);

        JPanel jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(null, "Impregnated Paper Production",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel.setLayout(new MigLayout("", "[][0px:112.5px:112.5px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:70px:70px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:15px:15px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelReceiptNo = new JLabel("Prod No.");
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

        lblImpregnated_Paper = new JLabel("Impregnated Paper");
        lblImpregnated_Paper.setVisible(false);
        jMainPanel.add(lblImpregnated_Paper, "cell 0 1");

        comboBoxResin = new JComboBox();
        comboBoxResin.setEditable(true);
        comboBoxResin.setPrototypeDisplayValue("xxxxxx");
        comboBoxResin.setVisible(false);
        jMainPanel.add(comboBoxResin, "cell 1 1 2 1,growx");

        lblQunatity = new JLabel("Qunatity");
        lblQunatity.setVisible(false);
        jMainPanel.add(lblQunatity, "cell 4 1,alignx trailing");

        jtextFieldQuantity = new JTextField();
        jtextFieldQuantity.setVisible(false);
        jMainPanel.add(jtextFieldQuantity, "cell 5 1 2 1,growx");
        jtextFieldQuantity.setColumns(10);

        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.RIGHT);

        pane11 = new JScrollPane();
        jMainPanel.add(pane11, "cell 0 3 3 18,growx");


//        String col11[] = {Label.SALE_NO, Label.DATE_TRANSACTION, Label.ACCOUNT};
//        String data11[][] = {{"", "", ""}};
//        String col1[] = {Label.PROD_No, Label.DATE_TRANSACTION, Label.IMPREGNATED_PAPER};
        String col1[] = {Label.PARTICULARS, Label.QTY, Label.TOTALQUANTITYCONSUMPTION};
        String data1[][] = {{"", "", ""}};

        tableModelImpregnatedPaperItem = new DefaultTableModel(data1, col1) {
//            public Class getColumnClass(int row) {
//                Class returnValue;
//                if ((row >= 0) && (row < getRowCount())) {
//                    returnValue = getValueAt(0, row).getClass();
//                    System.out.println("getValueAt(0, row)---->>" + getValueAt(0, row));
//                } else {
//                    returnValue = Object.class;
//                }
//                System.out.println("Class---->>" + returnValue);
//                return returnValue;
//            }
        };


        jTableImpregnatedPaperList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1 || column == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        ;
        };
        RowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(tableModelImpregnatedPaperItem);

        jTableImpregnatedPaperList.setModel(tableModelImpregnatedPaperItem);
        JTableHeader header_for_ImpreganetdPaper = jTableImpregnatedPaperList.getTableHeader();
        jTableImpregnatedPaperList.getTableHeader().setReorderingAllowed(false);
        tableModelImpregnatedPaperItem = (DefaultTableModel) jTableImpregnatedPaperList.getModel();
        header_for_ImpreganetdPaper.setBackground(Color.yellow);
        header_for_ImpreganetdPaper.setFont(font);
        jTableImpregnatedPaperList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableImpregnatedPaperList.setRowSorter(sorter1);
        jTableImpregnatedPaperList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableImpregnatedPaperList.setFont(font);
        pane11.setViewportView(jTableImpregnatedPaperList);

        jTableImpregnatedPaperList.getColumnModel().getColumn(0).setCellRenderer(renderer1);
        jTableImpregnatedPaperList.getColumnModel().getColumn(1).setCellRenderer(renderer1);

        jTableImpregnatedPaperList.getColumnModel().getColumn(0).setPreferredWidth(145);
        jTableImpregnatedPaperList.getColumnModel().getColumn(0).setMinWidth(0);
        jTableImpregnatedPaperList.getColumnModel().getColumn(1).setPreferredWidth(15);
        jTableImpregnatedPaperList.getColumnModel().getColumn(1).setMinWidth(0);
        jTableImpregnatedPaperList.getColumnModel().getColumn(2).setPreferredWidth(15);

        Action action1 = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableCellListener tcl = (TableCellListener) e.getSource();
                    int column = tcl.getColumn();
                    int row = tcl.getRow();
                    TableModel TableModelImpregnatedPaper = tcl.getTable().getModel();
                    if (column == 1 || column == 2) {
                        Double newPrice = Double.parseDouble(jTableImpregnatedPaperList.getValueAt(row, 1).toString());
                        Double RawPaperClosing = Double.parseDouble(jTableImpregnatedPaperList.getValueAt(jTableImpregnatedPaperList.getSelectedRow(), 2).toString());
                        if (!jTableImpregnatedPaperList.getValueAt(row, 0).toString().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(jTableImpregnatedPaperList.getValueAt(row, 0).toString().trim().toLowerCase())
                                && jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
                            callTableByFinishItemChange(newPrice, jTableImpregnatedPaperList.getValueAt(row, 0).toString(), RawPaperClosing);

                        } else {
                            partiTableModel.setRowCount(0);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }
        };
        TableCellListener tcl = new TableCellListener(jTableImpregnatedPaperList, action1);


//******************************************************************************************
        pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 11 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

//        String col1[] = {Label.SALE_NO, Label.DATE_TRANSACTION, Label.ACCOUNT};
//        String data1[][] = {{"", "", ""}};

        String col11[] = {Label.PROD_No, Label.DATE_TRANSACTION, Label.IMPREGNATED_PAPER};
        String data11[][] = {{"", "", ""}};

        transactionTableModel = new DefaultTableModel(data11, col11) {
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
        	        
        jTableTransactionList.setModel(transactionTableModel);
        JTableHeader header1 = jTableTransactionList.getTableHeader();
        jTableTransactionList.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        transactionTableModel = (DefaultTableModel) jTableTransactionList.getModel();
        transactionTableModel.setRowCount(0);
        transactionTableModel.setColumnCount(3);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(transactionTableModel);
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

        panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 3 3 7 11,grow");
        panel_1.setLayout(new MigLayout("", "[20px:20px:20px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:58.5px:58.5px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        pane = new JScrollPane();
        panel_1.add(pane, "cell 0 0 12 11,grow");
//******************************************************************************
//	        String col[] = {Label.S_N, Label.PARTICULARS, Label.RATE, Label.UNIT, Label.AMOUNT, Label.UNIT_SYMBOL, Label.COLOR, Label.PACKAGE, Label.NOS, Label.LENGTH, Label.WIDTH, Label.THICKNESS};
//        String col[] = {"SN", Label.PARTICULARS, "Thick", Label.COLOR, "SizeA", "SizeB", Label.PACKAGE, Label.NOS, Label.UNIT, Label.QUANTITY, Label.RATE, Label.AMOUNT, Label.UNIT_SYMBOL};
//        String data[][] = {{"", "", "", "", "", "", "", "", "", "", "", "", ""}};
//        String col[] = {"SN", Label.PARTICULARS, Label.QUANTITY, Label.NOS, Label.TOTALQUANTITY, Label.OPBL, Label.CLBL};
//        String data[][] = {{"", "", "", "", "", "", ""}};
        String col[] = {"SN", Label.PARTICULARS, Label.CLBL};
        String data[][] = {{"", "", ""}};
        partiTableModel = new DefaultTableModel(data, col);
        tableParti = new JTable() {
            private static final long serialVersionUID = 1L;
//            @Override
//            public boolean isCellEditable(int row, int column) {
//               return  column == 2  ? true : false;
//            }
        ;
        };
	        
	tableParti.setModel(partiTableModel);
        JTableHeader header = tableParti.getTableHeader();
        tableParti.getTableHeader().setReorderingAllowed(false);
        header.setBackground(Color.yellow);
        header.setFont(font);
        partiTableModel = (DefaultTableModel) tableParti.getModel();
        partiTableModel.setRowCount(0);
        partiTableModel.setColumnCount(3);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(280);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(45);
//        tableParti.getColumnModel().getColumn(3).setPreferredWidth(45);
//        tableParti.getColumnModel().getColumn(4).setPreferredWidth(45);
//        tableParti.getColumnModel().getColumn(5).setPreferredWidth(45);
//        tableParti.getColumnModel().getColumn(6).setPreferredWidth(60);


        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
//                    TableCellListener tcl = (TableCellListener) e.getSource();
//                    int column = tcl.getColumn();
//
//                    System.out.println("Checking Control");
//                    if (column == 9) {
//                        System.out.println("in if");
//                        int row = tcl.getRow();
//                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
//                        TableModel partiTableModel = tcl.getTable().getModel();
//                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
//                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 10).toString());
//                        Double value = new Double((quantity * rate));
//                        partiTableModel.setValueAt(value, row, 11);
//                        calculateAndBindTotalTOGUI();
//                        updateOnKeyType();
//                    } else if (column == 10) {
//                        System.out.println("in if");
//                        int row = tcl.getRow();
//                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
//                        TableModel partiTableModel = tcl.getTable().getModel();
//                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
//                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 11).toString());
//                        Double value = new Double((quantity * rate));
//                        partiTableModel.setValueAt(value, row, 11);
//                        calculateAndBindTotalTOGUI();
//                        updateOnKeyType();
//                    } else if (column == 11) {
//                        System.out.println("in else");
//                        int row = tcl.getRow();
//                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
//                        System.out.println("newPrice--->>" + newPrice);
//                        TableModel model = tcl.getTable().getModel();
//                        double weight = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
//                        double amount = Double.parseDouble(partiTableModel.getValueAt(row, 10).toString());
//                        Double newValue = new Double((amount / weight));
//                        model.setValueAt(newValue, row, 10);
//                        calculateAndBindTotalTOGUI();
//                        updateOnKeyType();
//                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }
        };

        TableCellListener tcl1 = new TableCellListener(tableParti, action);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        pane.setViewportView(tableParti);

        panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        jMainPanel.add(panel, "cell 3 14 7 7,grow");
        panel.setLayout(new MigLayout("", "[200px:200px:200px,grow,shrink 50,fill][200px:200px:200px,grow,shrink 50,fill][200px:200px:200px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));


        pane_ResinClosing = new JScrollPane();
        panel.add(pane_ResinClosing, "cell 0 0 3 6,grow");

        String col2[] = {"SN", Label.PARTICULARS, Label.CLBL, "ID"};
        String data2[][] = {{"", "", "", ""}};
        resinClosinigTableModel = new DefaultTableModel(data2, col2);
        jtableResinClosingBalance = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 ? true : false;
            }
        ;
        };
	        
	jtableResinClosingBalance.setModel(resinClosinigTableModel);
        JTableHeader header2 = jtableResinClosingBalance.getTableHeader();
        jtableResinClosingBalance.getTableHeader().setReorderingAllowed(false);
        header2.setBackground(Color.yellow);
        header2.setFont(font);
        resinClosinigTableModel = (DefaultTableModel) jtableResinClosingBalance.getModel();
        resinClosinigTableModel.setRowCount(0);
        resinClosinigTableModel.setColumnCount(4);
        jtableResinClosingBalance.getColumnModel().getColumn(0).setPreferredWidth(40);
        jtableResinClosingBalance.getColumnModel().getColumn(0).setMinWidth(20);
        jtableResinClosingBalance.getColumnModel().getColumn(0).setMaxWidth(40);
        jtableResinClosingBalance.getColumnModel().getColumn(1).setPreferredWidth(350);
        jtableResinClosingBalance.getColumnModel().getColumn(1).setMinWidth(100);
        jtableResinClosingBalance.getColumnModel().getColumn(1).setMaxWidth(350);
        jtableResinClosingBalance.getColumnModel().getColumn(2).setPreferredWidth(200);
        jtableResinClosingBalance.getColumnModel().getColumn(2).setMinWidth(100);
        jtableResinClosingBalance.getColumnModel().getColumn(2).setMaxWidth(200);

        TableCellListener tcl2 = new TableCellListener(jtableResinClosingBalance, action);
        jtableResinClosingBalance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jtableResinClosingBalance.setEnabled(true);
        pane_ResinClosing.setViewportView(jtableResinClosingBalance);

        buttonAdd = new JButton("Add");
        buttonAdd.setMnemonic('A');
        jMainPanel.add(buttonAdd, "cell 10 14");

        panel_3 = new JPanel();
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
        jMainPanel.add(buttonPrevious_DOWN_Transactions, "flowx,cell 11 22");

        buttonNext_DOWN_Transactions = new JButton(">>");
        jMainPanel.add(buttonNext_DOWN_Transactions, "cell 11 22");


        initialiseActionListeners();
        tfDatePicker.setTransferHandler(null);

    }

    private void initialiseActionListeners() {

        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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


        jTableTransactionList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableTransactionListMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
//                showPopup(me);
            }

            public void mouseReleased(MouseEvent me) {
//                showPopup(me);
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
                        JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                    }
                }
            }
        });

//////////on Dated 19-08-2014
        jTableImpregnatedPaperList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableImpregnatedPaperListMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
//                showPopup(me);
            }

            public void mouseReleased(MouseEvent me) {
//                showPopup(me);
            }
        });

        jTableImpregnatedPaperList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        jTableImpregnatedPaperListMouseClicked(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                    }
                }
            }
        });
//////////on Dated 19-08-2014


        jTableImpregnatedPaperList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextFieldQuantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    // getting RawItem for selected Finish Item
//                    Double enteredQuantity = 0d;
//                    if (jtextFieldQuantity.getText() != null && !jtextFieldQuantity.getText().isEmpty()) {
//                        enteredQuantity = Double.parseDouble(jtextFieldQuantity.getText().trim());
//                    }
//                    if (!tfFinishItemAccountText.getText().toString().trim().isEmpty() && finishStockItemMap != null && finishStockItemMap.containsKey(tfFinishItemAccountText.getText().toString().trim().toLowerCase())) {
//                        callTableByFinishItemChange(enteredQuantity);
//                    } else {
//                        jtextFieldQuantity.setText("0.0");
//                        partiTableModel.setRowCount(0);
//                    }
//                    System.out.println("Key Released ..................." + jTableImpregnatedPaperList.getValueAt(jTableImpregnatedPaperList.getSelectedRow(), 1));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                    Logger.getLogger(ImpregnatedPaperProduction.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        });


        jTextFieldReceiptNo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTextFieldReceiptNoMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                checkDateisChangeOrNot = tfDatePicker.getText().trim();
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {

                    //                    System.out.println("Focus lost-------------");
                    // first set date then go for transaction 
                    // as date change rawstockitem value also change
                    // Make it finshitemTable blank so that qty will be blank
                    // if date is changed then make finishItemtable blank for Quantiy
                    if (!checkDateisChangeOrNot.equalsIgnoreCase(tfDatePicker.getText().trim())) {
                        // make all dto blank too
                        bindDTOtoImpregnatedTable();
                        partiTableModel.setRowCount(0);
                    }

//                if (!tfDatePicker.getText().equalsIgnoreCase("")) {
//                    if (!jTableImpregnatedPaperList.getValueAt(row, 0).toString().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(jTableImpregnatedPaperList.getValueAt(row, 0).toString().trim().toLowerCase())
//                            && jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
//                        callTableByFinishItemChange(newPrice, jTableImpregnatedPaperList.getValueAt(row, 0).toString());
//                    } else {
//                        partiTableModel.setRowCount(0);
//                    }
//                }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        comboBoxResin.setEditable(true);
        tfFinishItemAccountText = (JTextField) comboBoxResin.getEditor().getEditorComponent();
        tfFinishItemAccountText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfFinishItemAccountText.getText();
                        if (text.length() == 0) {
                            comboBoxResin.hidePopup();
                            setAccountLedgetModel(new DefaultComboBoxModel(vectorFinishStockItem), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(vectorFinishStockItem, text);
                            if (m.getSize() == 0 || hide_flag) {
                                comboBoxResin.hidePopup();
                                hide_flag = false;
                            } else {
                                setAccountLedgetModel(m, text);
                                comboBoxResin.showPopup();
                            }

                        }
                    }
                });

            }
        });
        tfFinishItemAccountText.addKeyListener(new KeyAdapter() {
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

        tfFinishItemAccountText = (JTextField) comboBoxResin.getEditor().getEditorComponent();
        tfFinishItemAccountText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                currentFocusValue = 2;
                String text = tfFinishItemAccountText.getText();
                if (text.length() == 0) {
                    comboBoxResin.hidePopup();
                    setAccountLedgetModel(new DefaultComboBoxModel(vectorFinishStockItem), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(vectorFinishStockItem, text);
                    if (m.getSize() == 0 || hide_flag) {
                        comboBoxResin.hidePopup();
                        hide_flag = false;
                    } else {
                        setAccountLedgetModel(m, text);
                        comboBoxResin.showPopup();
                    }

                }
                tfFinishItemAccountText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // sale account is selected and dont contain selected Ledger
                if (!tfFinishItemAccountText.getText().toString().trim().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(tfFinishItemAccountText.getText().toString().trim().toLowerCase())) {
                    try {
                        // getting RawItem for selected Finish Item
                        Double enteredQuantity = Double.parseDouble(jtextFieldQuantity.getText().trim());
//                        callTableByFinishItemChange(enteredQuantity);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ImpregnatedPaperProduction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
//                    try {
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                        Logger.getLogger(ImpregnatedPaperProduction.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            }
        });

        jtextFieldQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldQuantityFocusGained(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldQuantityFocusLost(evt);
            }
        });

        jtextFieldQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantityKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextFieldQuantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //calculateSquareFeet();
                try {
                    // getting RawItem for selected Finish Item
                    Double enteredQuantity = 0d;
                    if (jtextFieldQuantity.getText() != null && !jtextFieldQuantity.getText().isEmpty()) {
                        enteredQuantity = Double.parseDouble(jtextFieldQuantity.getText().trim());
                    }

//                    callTableByFinishItemChange(enteredQuantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }
        });

        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
                }
            }
        });

        buttonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
//                    buttonExportActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ImpregnatedPaperProduction.this, ex.getMessage());
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
        if (this.stockItemTimeStamp != Constants.STOCK_ITEM_TIME_STAMP) {
//            initFinsishStockItem();
        }
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
        MainClass.setstaticvar();
    }

    private void jTableTransactionListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        String id = jTableTransactionList.getValueAt(jTableTransactionList.getSelectedRow(), 0).toString();
        loadEditForm(id);
    }

    private void jTableImpregnatedPaperListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        String stockItemName = jTableImpregnatedPaperList.getValueAt(jTableImpregnatedPaperList.getSelectedRow(), 0).toString();
        Double stockItemNameQuantity = Double.parseDouble(jTableImpregnatedPaperList.getValueAt(jTableImpregnatedPaperList.getSelectedRow(), 1).toString());
        Double RawPaperClosing = Double.parseDouble(jTableImpregnatedPaperList.getValueAt(jTableImpregnatedPaperList.getSelectedRow(), 2).toString());
        callTableByFinishItemChange(stockItemNameQuantity, stockItemName, RawPaperClosing);
    }

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            if (jTableTransactionList.getRowCount() > 0) {
//                jTableTransactionList.requestFocus();
//                jTableTransactionList.changeSelection(0, 0, false, false);
//            }
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            if (jTableTransactionList.getRowCount() > 0) {
//                jTableTransactionList.requestFocus();
//                jTableTransactionList.changeSelection(0, 0, false, false);
//            }
//        }
    }

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException {
//        List<SaleDTO> saleList = new ArrayList<SaleDTO>();
//        String text = jTextFieldSearch.getText().trim();
//
//        for (SaleDTO saleDTOEntity : saleDTOTransactionList) {
//            if (saleDTOEntity.getSaleLedger().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
//                saleList.add(saleDTOEntity);
//            }
//        }
//        bindDTOToTransactionTable(saleList);
    }

    private void jTextFieldReceiptNoMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        jTextFieldReceiptNo.setEnabled(true);
    }

    private void jTextFieldReceiptNoFocusGained(java.awt.event.FocusEvent evt) {
        currentFocusValue = 0;
    }

    private void jTextFieldReceiptNoKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldReceiptNoFocusLost(java.awt.event.FocusEvent evt) throws Exception {
        if (!isEdit) {
            if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                if (jTextFieldReceiptNo.getText().toString().trim().length() < 8) {
                    if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabilty(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.SALE_TYPE_INDEX)) {
                        jTextFieldReceiptNo.setText("");
                    }
                } else {
                    jTextFieldReceiptNo.requestFocus();
                    jTextFieldReceiptNo.setText("");
                    throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
                }
            }

            if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                jTextFieldReceiptNo.setText(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.SALE_TYPE_INDEX) + "");
            }
        }
    }

    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) throws Exception {
//        currentFocusValue = 5;
        currentFocusValue = 3;
        Util.checkForZero(jtextFieldQuantity);
        jtextFieldQuantity.selectAll();
    }

    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jtextFieldQuantity);
    }

    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
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
            ImpregnatedPaperProduction.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(ImpregnatedPaperProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        try {
//                if (validateFinishItem()) {
//                } else {
//                    JOptionPane.showMessageDialog(this, "First Enter Quantity of  At least One Finish Item");
//                }
            Set<String> idSet = new HashSet<String>();
            List<StockItemDTO> StockItemDTOList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList(idSet);

            for (StockItemDTO stockItemDTO : StockItemDTOList) {
                System.out.println("Yes 11 ...........");
                for (int i = 0; i < transactionTableModel.getRowCount(); i++) {
                    System.out.println("Yes 22 ...........");
                    System.out.println(Long.parseLong(transactionTableModel.getValueAt(i, 3).toString()) == stockItemDTO.getID());
                    if (Long.parseLong(transactionTableModel.getValueAt(i, 3).toString()) == stockItemDTO.getID()) {
                        stockItemDTO.setOpeningBal(Double.parseDouble(transactionTableModel.getValueAt(i, 2).toString()));
                    }
                }
            }

            gen.account.StockItemFormation.StockItemFormationDAO.insertStockItem(StockItemDTOList);

            tfDatePicker.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {

            if (validateFinishItem()) {
//                if (productionProcessMap.size() > 0) {
                    /*
                 *  Code for Take Value of TableStock Item From Map of FinishItemWithList
                 */
                submit();
                getMaxReceiptNo();
                //    bindDTOtoGUIFinishTable();
//                } else {
//                    JOptionPane.showMessageDialog(this, "First Enter Quantity of  At least One Finish Item");
//                tfDatePicker.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, Label.INVALIDRAWITEMQUANTITY);
            }
            tfDatePicker.requestFocus();
//            getMaxReceiptNo();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Occur");
        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {

            String trans_id = "";
            for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO1 : impregnatedPaperProductionDTOList) {
                trans_id = impregnatedPaperProductionDTO1.getTrans_id();
            }
            System.out.println("Impregante trans_id ---------- " + trans_id);
            ImpregnatedPaperProductionDAO.deleteTransaction(Long.parseLong(trans_id));
            JOptionPane.showMessageDialog(this, "Transaction deleted completly");
//            newButton();
//            clearFormData();
//            temp = 1000;
//            buttonAdd.setText("ADD");
            tfDatePicker.requestFocus();
            initilize();
            getMaxReceiptNo();
            bindDTOtoImpregnatedTable();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        newButton();
//        temp = 1000;
//        buttonAdd.setText("ADD");
        tfDatePicker.requestFocus();
//        jComboBoxAccount.hidePopup();
//        jComboBoxParti.hidePopup();
//        jComboBoxSaleLedger.hidePopup();
    }

    private void buttonNextTransactionsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        } catch (Exception ex) {
            Logger.getLogger(ChalanForm1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonPreviousTransactionsActionPerformed(java.awt.event.ActionEvent evt) {
        try {
        } catch (Exception ex) {
            Logger.getLogger(ChalanForm1.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAccountLedgetModel(DefaultComboBoxModel mdl, String str) {
        comboBoxResin.setModel(mdl);
        comboBoxResin.setSelectedIndex(-1);
        comboBoxResin.showPopup();
        tfFinishItemAccountText.setText(str);
    }
    private Font font;
    private JTextField tfDatePicker = null;
    private JPanel jMainPanel;
    private JLabel labelReceiptNo;
    private JLabel labelDate;
    private JLabel lblImpregnated_Paper;
    private JLabel lblQunatity;
    private JTextField jTextFieldReceiptNo;
    private JTextField jTextFieldSearch;
    private JTextField jtextFieldQuantity;
    private JScrollPane pane1;
    private JScrollPane pane;
    private JScrollPane pane_ResinClosing;
    private JScrollPane pane11;
    private JPanel panel_1;
    private JPanel panel_3;
    private JButton buttonBack;
    private JButton buttonNew;
    private JButton buttonPrint;
    private JButton buttonExport;
    private JButton buttonDelete;
    private JButton buttonAddLedger;
    private JButton buttonAddItem;
    private JButton buttonSubmit;
    private JButton buttonPrevious_DOWN_Transactions;
    private JButton buttonNext_DOWN_Transactions;
    private JDateChooser jDateChooser;
    private JComboBox comboBoxResin;
    private JTextField tfFinishItemAccountText;
    private boolean hide_flag = false;
    private JTable jTableImpregnatedPaperList;
    private DefaultTableModel tableModelImpregnatedPaperItem;
    private JTable tableParti;
    private DefaultTableModel transactionTableModel;
    private DefaultTableModel partiTableModel;
    private DefaultTableModel resinClosinigTableModel;
    private JTable jTableTransactionList;
    private JPanel panel;
    private JButton buttonAdd;
    private JTable jtableResinClosingBalance;

    private void initilize() throws SQLException, ParseException, Exception {
        initilizeGUIComponents();
        initVariables();
        bindTOGUI();
    }

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }

    private void initComponentActiveInActive() {
        buttonDelete.setEnabled(false);
        buttonExport.setEnabled(false);
        buttonPrint.setEnabled(false);
        jTextFieldReceiptNo.setEditable(true);
    }

    private void initVariables() throws Exception {
        impregnatedPaperProductionDTOList = new ArrayList<ImpregnatedPaperProductionDTO>();
        impregnatedPaperProductionDTO = new ImpregnatedPaperProductionDTO();
        isEdit = false;
        initStockItem();
        if (!isEdit) {
//            impregnatedPaperProductionDTO.setReceiptNo(AccountingVoucherHelper.getNextProdAccountVoucherNumber(Constants.IMPREGNATED_PAPER_TYPE_INDEX));
            getMaxReceiptNo();
        }
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
    }

    private void initStockItem() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
        stockGroups.add("Impregnated Paper");
        mapFinishStockItem = gen.account.stockitem.StockItemDAO.getStockItemsFromGroupName(stockGroups, true);
        vectorFinishStockItem.clear();
        for (String str : mapFinishStockItem.keySet()) {
            vectorFinishStockItem.add(str);
        }

        Collections.sort(
                vectorFinishStockItem,
                new Comparator<String>() {
                    @Override
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        mapFinishStockItem = Util.getSmallCaseMap(mapFinishStockItem);
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
//        gen.dto.Constants.OFFSET_VALUE = 0L;
//        Long number = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.SALE_TYPE_INDEX, Constants.CREDIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
//        Long remaining = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.SALE_TYPE_INDEX, Constants.CREDIT) % gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
//
//        if (remaining == 0L) {
//            gen.dto.Constants.OFFSET_VALUE = Math.abs((number - 1) * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
//        } else {
//            gen.dto.Constants.OFFSET_VALUE = Math.abs(number * gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
//        }

        // code added on 27-06-2014
//        gen.dto.Constants.OFFSET_VALUE = 0L;

        bindDTOtoGUI();
        bindDTOtoImpregnatedTable();
        bindDTOtoResinClosingBalanceTable();
        bindDTOtoJtable();
        if (!isEdit || isTransactionTableLoad) {
            initTransactionList();
        }
        isTransactionTableLoad = false;

    }

    private void bindDTOtoGUI() throws Exception {
        List<String> groupList = new ArrayList<String>();
        mapAllStockItem = gen.account.stockitem.StockItemDAO.getStockItemsNameID(groupList, false);

        if (!isEdit) {
            System.out.println("is Edit Date------- " + (java.util.Date) Constants.DATE_FORMATER.parse(impregnatedPaperProductionDTO.getDate().trim()));
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(impregnatedPaperProductionDTO.getDate().trim()));
        }

        for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO_Local : impregnatedPaperProductionDTOList) {
            jTextFieldReceiptNo.setText("" + impregnatedPaperProductionDTO_Local.getReceiptNo());
            jtextFieldQuantity.setText("" + impregnatedPaperProductionDTO_Local.getFinishItemQty());

//            System.out.println("is Not Edit Date------- " +(java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(impregnatedPaperProductionDTO_Local.getDate().trim()));
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(impregnatedPaperProductionDTO_Local.getDate().trim()));
//            tfDatePicker.setText(impregnatedPaperProductionDTO_Local.getDate().trim());
        }
        initTransactionList();
    }

    private void bindDTOtoImpregnatedTable() throws Exception {
        int index = 1;

        if (!isEdit) {
            tableModelImpregnatedPaperItem.setRowCount(0);
        }

        bindEmptyImpregnatedPaperTable();

        for (Map.Entry<String, String> entrypoint : mapFinishStockItem.entrySet()) {
            try {

//                if (!isEdit) {
//                    TableModelImpregnatedPaper.setRowCount(TableModelImpregnatedPaper.getRowCount() + 1);
//                    TableModelImpregnatedPaper.setValueAt(entrypoint.getKey(), TableModelImpregnatedPaper.getRowCount() - 1, 0);
//                }

                /// Change the quantity of Impregantaed SI
//                TableModelImpregnatedPaper.setRowCount(0);
                for (int i = 0; i < tableModelImpregnatedPaperItem.getRowCount(); i++) {
                    for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO : impregnatedPaperProductionDTOList) {
                        if (tableModelImpregnatedPaperItem.getValueAt(i, 0).toString().equalsIgnoreCase(impregnatedPaperProductionDTO.getFinishStockItemName())) {
//                            TableModelImpregnatedPaper.setRowCount(TableModelImpregnatedPaper.getRowCount() + 1);
//                            TableModelImpregnatedPaper.setValueAt(impregnatedPaperProductionDTO.getFinishStockItemName(), TableModelImpregnatedPaper.getRowCount() - 1, 0);
                            System.out.println("Finsih Item Qty ------- " + impregnatedPaperProductionDTO.getFinishItemQty());
                            tableModelImpregnatedPaperItem.setValueAt(impregnatedPaperProductionDTO.getFinishItemQty(), i, 1);
                            if (impregnatedPaperProductionDTO != null) {
                                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : impregnatedPaperProductionDTO.getListStockItemDTO()) {

                                    tableModelImpregnatedPaperItem.setValueAt(rawStockItemDTO.getQuantity(), i, 2);

                                }
                            }
                        }
//                        else {
//                            TableModelImpregnatedPaper.setValueAt("0.0", i, 1);
//                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bindDTOtoResinClosingBalanceTable() throws Exception {
        try {
            List<String> stockGroups = new ArrayList<String>();
            stockGroups.add("Resin");
            mapResinStockItemClosingBalance = gen.account.stockitem.StockItemDAO.getStockItemsFromGroupName(stockGroups, true);

            resinClosinigTableModel.setRowCount(0);
            for (Map.Entry<String, String> entrypoint : mapResinStockItemClosingBalance.entrySet()) {
                try {
                    resinClosinigTableModel.setRowCount(resinClosinigTableModel.getRowCount() + 1);
                    int row = resinClosinigTableModel.getRowCount();
                    resinClosinigTableModel.setValueAt(row, resinClosinigTableModel.getRowCount() - 1, 0);
                    resinClosinigTableModel.setValueAt(entrypoint.getKey(), resinClosinigTableModel.getRowCount() - 1, 1);
                    resinClosinigTableModel.setValueAt(0.0, resinClosinigTableModel.getRowCount() - 1, 2);
                    resinClosinigTableModel.setValueAt(entrypoint.getValue(), resinClosinigTableModel.getRowCount() - 1, 3);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void bindEmptyImpregnatedPaperTable() {

        tableModelImpregnatedPaperItem.setRowCount(0);
        for (Map.Entry<String, String> entrypoint : mapFinishStockItem.entrySet()) {
            try {
                tableModelImpregnatedPaperItem.setRowCount(tableModelImpregnatedPaperItem.getRowCount() + 1);
                tableModelImpregnatedPaperItem.setValueAt(entrypoint.getKey(), tableModelImpregnatedPaperItem.getRowCount() - 1, 0);
                tableModelImpregnatedPaperItem.setValueAt(0.0, tableModelImpregnatedPaperItem.getRowCount() - 1, 1);
                tableModelImpregnatedPaperItem.setValueAt(0.0, tableModelImpregnatedPaperItem.getRowCount() - 1, 2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void bindDTOtoJtable() throws Exception {
        try {
            int index = 1;
            List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionDTOList = impregnatedPaperProductionDTO.getListStockItemDTO();
            partiTableModel.setRowCount(0);
            if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
                partiTableModel.setRowCount(0);
                for (gen.account.StockItemFormation.StockItemDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                    partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity() / impregnatedPaperProductionDTO.getFinishItemQty()), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(impregnatedPaperProductionDTO.getFinishItemQty()), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 2);
                    index++;


                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ImpregnatedPaperProduction.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void newButton() throws ParseException, SQLException, Exception {
//        jTextFieldReceiptNo.setEditable(true);
//        jTextFieldReceiptNo.setEnabled(true);
        jTextFieldSearch.setText("");
        jtextFieldQuantity.setText("");
        isEdit = false;
//        saleDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.SALE_TYPE_INDEX));
        initilize();
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);
    }

    private void initTransactionList() throws SQLException, Exception {
        impregnatedPaperProductionDTOList_For_Transactions = gen.feature.production.impregnatedpaperproduction.ImpregnatedPaperProductionDAO.getTransactionList();
        bindDTOToTransactionTable(impregnatedPaperProductionDTOList_For_Transactions);
    }

    private void bindDTOToTransactionTable(List<ImpregnatedPaperProductionDTO> resignProductionProcessDTOTransactionList) throws SQLException {
//        if (resignProductionProcessDTOTransactionList != null && !resignProductionProcessDTOTransactionList.isEmpty()) {
        transactionTableModel.setRowCount(0);
        for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO : resignProductionProcessDTOTransactionList) {
            try {
                transactionTableModel.setRowCount(transactionTableModel.getRowCount() + 1);
                transactionTableModel.setValueAt(impregnatedPaperProductionDTO.getReceiptNo(), transactionTableModel.getRowCount() - 1, 0);
                java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(impregnatedPaperProductionDTO.getDate().trim());
                transactionTableModel.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel.getRowCount() - 1, 1);
                transactionTableModel.setValueAt(impregnatedPaperProductionDTO.getFinishStockItemName(), transactionTableModel.getRowCount() - 1, 2);

            } catch (Exception ex) {
                Logger.getLogger(ImpregnatedPaperProduction.class
                        .getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
//        } else {
//            if (last_Number_In_TransactionList != 1) {
//                transactionTableModel1.setRowCount(0);
//            }
//        }
    }

    private void callTableByFinishItemChange(Double enteredQuantity, String finishItempassed, Double rawPaperClosing) {
        try {

            String finishItem_id = mapFinishStockItem.get(finishItempassed.toLowerCase().trim());
            String date = Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim();

            String trans_id = "";
            for (ImpregnatedPaperProductionDTO impregnatedPaperProductionDTO_Local : impregnatedPaperProductionDTOList) {
                if (finishItempassed.equalsIgnoreCase(impregnatedPaperProductionDTO_Local.getFinishStockItemName())) {
                    trans_id = impregnatedPaperProductionDTO_Local.getTrans_id();
                    System.out.println("Trans----------- id 111AAA" + trans_id);
                }
            }
            System.out.println("finishItem_id " + rawPaperClosing);

            // set closing balance to Impregnated Paper only
            List<String> stockGroups = new ArrayList<String>();
            stockGroups.add("Raw Paper");
            Map<String, String> mapRawPaperStockItem = gen.account.stockitem.StockItemDAO.getStockItemsFromGroupName(stockGroups, true);

            List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
            stockItemDTOList = gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getAllRawStockItemsInfo(finishItem_id, date, null);

            impregnatedPaperProductionDTO = new ImpregnatedPaperProductionDTO();
            impregnatedPaperProductionDTO.setListStockItemDTO(stockItemDTOList);
            impregnatedPaperProductionDTO.setTrans_id(trans_id);
            impregnatedPaperProductionDTO.setFinishStockItemName(finishItempassed);
            impregnatedPaperProductionDTO.setFinishItemId(Long.parseLong(finishItem_id));
            impregnatedPaperProductionDTO.setFinishItemQty(enteredQuantity);
            impregnatedPaperProductionDTO.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
            impregnatedPaperProductionDTO.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText().trim()));
            partiTableModel.setRowCount(0);
            if (impregnatedPaperProductionDTO != null) {
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : impregnatedPaperProductionDTO.getListStockItemDTO()) {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(partiTableModel.getRowCount(), partiTableModel.getRowCount() - 1, 0);
//                    rawStockItemInTableMap.put(rawStockItemDTO.getName(), rawStockItemDTO.getQuantity().toString());
                    partiTableModel.setValueAt(rawStockItemDTO.getName(), partiTableModel.getRowCount() - 1, 1);
//                    partiTableModel.setValueAt(rawStockItemDTO.getQuantity(), partiTableModel.getRowCount() - 1, 2);
//                    partiTableModel.setValueAt(enteredQuantity, partiTableModel.getRowCount() - 1, 2);
//                    partiTableModel.setValueAt(rawStockItemDTO.getQuantity() * enteredQuantity, partiTableModel.getRowCount() - 1, 2);

                    System.out.println("Mapo ------------" + mapRawPaperStockItem.entrySet());

                    if (mapRawPaperStockItem.containsKey(rawStockItemDTO.getName())) {
                        System.out.println("Raw Paper Quantyti");
                        rawStockItemDTO.setQuantity(rawPaperClosing);
                        calOPBLCLBL(Integer.parseInt(mapAllStockItem.get(rawStockItemDTO.getName().trim())), rawPaperClosing);
//                        partiTableModel.setValueAt(rawPaperClosing, partiTableModel.getRowCount() - 1, 2);
                    } else {
                        System.out.println("Raw Chmical Quantyti");
                        rawStockItemDTO.setQuantity(0D);
                    }
                }
            }

            if (mapFinishItemWithRawItems != null && enteredQuantity != 0D) {
                mapFinishItemWithRawItems.put(finishItem_id, impregnatedPaperProductionDTO);
            } else {
                mapFinishItemWithRawItems.remove(finishItem_id);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(AddFinishMaterialForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean validateFinishItem() {
        int i = 0;
        Boolean returntype = true;
        if (tableParti != null && tableParti.getRowCount() <= 0) {
            returntype = false;
        } else if (mapFinishItemWithRawItems.size() <= 0) {
            returntype = false;
        }

        return returntype;
    }

    private void submit() throws SQLException, Exception {
        impregnatedPaperProductionDTOList.clear();
        for (Map.Entry<String, ImpregnatedPaperProductionDTO> e : mapFinishItemWithRawItems.entrySet()) {

            System.out.println("Key ------- " + e.getKey());
            impregnatedPaperProductionDTOList.add(e.getValue());

            for (int i = 0; i < impregnatedPaperProductionDTOList.size(); i++) {
                System.out.println("LIst Size --------- " + impregnatedPaperProductionDTOList.get(i).getReceiptNo());
                System.out.println("LIst Size --------- " + impregnatedPaperProductionDTOList.get(i).getReceiptNo());
            }
            if (!isEdit) {
                ImpregnatedPaperProductionDAO.insert(impregnatedPaperProductionDTOList);
            } else {
                ImpregnatedPaperProductionDAO.update(impregnatedPaperProductionDTOList);
            }
            JOptionPane.showMessageDialog(this, Label.PRODUCTIONADDED);
            getMaxReceiptNo();
            bindDTOtoImpregnatedTable();
            initilize();
        }

    }

    private void getMaxReceiptNo() throws Exception {
        ImpregnatedPaperProductionDAO newProductionProcessDAO = new ImpregnatedPaperProductionDAO();
        jTextFieldReceiptNo.setText(newProductionProcessDAO.getMaxreceiptno().toString());
//        impregnatedPaperProductionDTO.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText().toString().trim()));
    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
//        List<ImpregnatedPaperProductionDTO> ImpregnatedPaperProductionDTOList = ImpregnatedPaperProductionDAO.getImprePaper(idSet, "");
        impregnatedPaperProductionDTOList = ImpregnatedPaperProductionDAO.getImprePaper(idSet, "");
        if (impregnatedPaperProductionDTOList != null && !impregnatedPaperProductionDTOList.isEmpty()) {
//            impregnatedPaperProductionDTO = ImpregnatedPaperProductionDTOList.get(0);
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
        if (currentFocusValue > 4) {
            currentFocusValue = 4;
        }

        if (currentFocusValue == 0) {
            tfFinishItemAccountText.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            tfFinishItemAccountText.requestFocus();
        } else if (currentFocusValue == 3) {
            jtextFieldQuantity.requestFocus();
        }
    }

    private void calOPBLCLBL(int item_id, Double rawPaperClosing) {
        Connection conn = null;
        try {
            conn = gen.database.connection.DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            Statement st4 = conn.createStatement();

            String q = "";
            ResultSet rs1, rs2, rs3, rs4, rs5;

            double openBal = 00f;
            double creditBal = 00f;
            double debitBal = 00f;

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = f.format(jDateChooser.getDate());

//            q = "select sum(invtrans_qty) as totalCred from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date <= '" + date1 + "' and trans_typeIndex in ("+Constants.PURCHASE_TYPE_INDEX+",7)))";
            q = "select sum(invtrans_qty) as totalCred from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date <= '" + date1 + "' and trans_typeIndex in (" + Constants.PURCHASE_TYPE_INDEX + ")))";
            rs1 = st1.executeQuery(q);
            while (rs1.next()) {
                creditBal = creditBal + rs1.getDouble("totalCred");
            }
            tableParti.setValueAt(openBal, tableParti.getRowCount() - 1, 2);
            System.out.println(q + "Credit......?>>>>>>>>>>>>>>>>>>>>>>>>>>." + creditBal);

            q = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date<= '" + date1 + "' and trans_typeIndex=" + Constants.PRODUCTION_TYPE_INDEX + "))";
            rs1 = st1.executeQuery(q);
            while (rs1.next()) {
                debitBal = debitBal + rs1.getDouble("totalDeb");
            }
            System.out.println(q + "Decit......?>>>>>>>>>>>>>>>>>>>>>>>>>>." + debitBal);
            debitBal = rawPaperClosing;
//            q = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date<='" + date1 + "' and trans_typeIndex=7))";
//            rs1 = st1.executeQuery(q);
//            while (rs1.next()) {
//                debitBal = debitBal + rs1.getDouble("totalDeb");
//            }

//            openBal = rawPaperClosing;
            q = "select si_openingBalance from tblstockitem where si_id=" + item_id + "";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {

                openBal = (rs1.getDouble("si_openingBalance") + creditBal) - debitBal;
            }

//            tableParti.setValueAt(openBal, tableParti.getRowCount() - 1, 2);
////            tableParti.setValueAt(creditBal, tableParti.getRowCount() - 1, 5);
//          
            System.out.println("openBal openBal  openBal " + openBal);
            System.out.println("debitBal debitBal  debitBal " + debitBal);
            System.out.println("creditBal creditBal  creditBal " + creditBal);
//            Double Closing = (Double.parseDouble(tableParti.getValueAt(tableParti.getRowCount() - 1, 2).toString())) - Double.parseDouble(tableParti.getValueAt(tableParti.getRowCount() - 1, 2).toString());

            tableParti.setValueAt(openBal, tableParti.getRowCount() - 1, 2);

            //--------------------------------------------------------------------------------


        } catch (SQLException ex) {
            Logger.getLogger(StockItemReport.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();


            } catch (SQLException ex) {
                Logger.getLogger(StockItemReport.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
