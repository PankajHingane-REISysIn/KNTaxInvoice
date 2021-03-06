/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.prelanboardproduction;

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
import gen.accountvoucher.TableCellListener;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.accountvoucher.sale.SaleDAO;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.display.report.StockItemReport;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.feature.production.finisheditemdefination.AddFinishMaterialForm;
import gen.feature.production.impregnatedpaperproduction.ImpregnatedPaperProduction;
import java.sql.Statement;

import net.miginfocom.swing.MigLayout;

public class PrelanBoardProduction extends JInternalFrame {

    List<PrelanBoardProductionDTO> prelanBoardProductionDTOList_For_Transactions = new ArrayList<PrelanBoardProductionDTO>();
    List<PrelanBoardProductionDTO> prelanBoardProductionDTOList = new ArrayList<PrelanBoardProductionDTO>();
    PrelanBoardProductionDTO prelanBoardProductionDTO = new PrelanBoardProductionDTO();
    private Long stockItemTimeStamp;
    private int currentFocusValue = 0;
    private Vector<String> vectorFinishStockItem = new Vector<String>();
    private Boolean isEdit = false;
    private Map<String, String> mapFinishStockItem = new HashMap<String, String>();
    private Boolean isTransactionTableLoad = false;
    DefaultTableModel partiTableModel;
    private Map<String, String> mapAllStockItem = new HashMap<String, String>();
    private String checkDateisChangeOrNot = "";
    private Map<String, PrelanBoardProductionDTO> mapFinishItemWithRawItems = new HashMap<String, PrelanBoardProductionDTO>();

    public PrelanBoardProduction(String s, Dimension d) {
        try {
            initComponents();
            prelanBoardProductionDTO = new PrelanBoardProductionDTO();
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
        setFrameIcon(new ImageIcon(PrelanBoardProduction.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Prelan Board Production");
        setBounds(100, 100, 1332, 674);

        JPanel jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(null, "Prelan Board Production",
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

        lblPrelan_Board = new JLabel("Prelan Board");
        jMainPanel.add(lblPrelan_Board, "cell 0 2");

        comboBoxResin = new JComboBox();
        comboBoxResin.setEditable(true);
        comboBoxResin.setPrototypeDisplayValue("xxxxxx");
        jMainPanel.add(comboBoxResin, "cell 1 2 2 1,growx");

        lblQunatity = new JLabel("Qunatity");
        jMainPanel.add(lblQunatity, "cell 4 2,alignx trailing");

        jtextFieldQuantity = new JTextField();
        jMainPanel.add(jtextFieldQuantity, "cell 5 2 2 1,growx");
        jtextFieldQuantity.setColumns(10);

        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
        renderer1.setHorizontalAlignment(JLabel.RIGHT);

        pane11 = new JScrollPane();
        jMainPanel.add(pane11, "cell 0 5 3 16,growx");


//        String col11[] = {Label.SALE_NO, Label.DATE_TRANSACTION, Label.ACCOUNT};
//        String data11[][] = {{"", "", ""}};
//        String col1[] = {Label.PROD_No, Label.DATE_TRANSACTION, Label.IMPREGNATED_PAPER};
        String col1[] = {Label.PARTICULARS, Label.QTY, Label.CLBL};
        String data1[][] = {{"", "", ""}};

        TableModelPrelanBoard = new DefaultTableModel(data1, col1) {
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


        jTablePrelanBoardList = new JTable() {
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
        RowSorter<TableModel> sorter1 = new TableRowSorter<TableModel>(TableModelPrelanBoard);

        jTablePrelanBoardList.setModel(TableModelPrelanBoard);
        JTableHeader header_for_PrelanBoard = jTablePrelanBoardList.getTableHeader();
        jTablePrelanBoardList.getTableHeader().setReorderingAllowed(false);
        TableModelPrelanBoard = (DefaultTableModel) jTablePrelanBoardList.getModel();
        header_for_PrelanBoard.setBackground(Color.yellow);
        header_for_PrelanBoard.setFont(font);
        jTablePrelanBoardList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTablePrelanBoardList.setRowSorter(sorter1);
        jTablePrelanBoardList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTablePrelanBoardList.setFont(font);
        pane11.setViewportView(jTablePrelanBoardList);

        jTablePrelanBoardList.getColumnModel().getColumn(0).setCellRenderer(renderer1);
        jTablePrelanBoardList.getColumnModel().getColumn(1).setCellRenderer(renderer1);

        jTablePrelanBoardList.getColumnModel().getColumn(0).setPreferredWidth(145);
        jTablePrelanBoardList.getColumnModel().getColumn(0).setMinWidth(0);
        jTablePrelanBoardList.getColumnModel().getColumn(1).setPreferredWidth(15);
        jTablePrelanBoardList.getColumnModel().getColumn(1).setMinWidth(0);
        jTablePrelanBoardList.getColumnModel().getColumn(2).setPreferredWidth(15);

        Action action1 = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableCellListener tcl = (TableCellListener) e.getSource();
                    int column = tcl.getColumn();
                    int row = tcl.getRow();
                    TableModel TableModelImpregnatedPaper = tcl.getTable().getModel();
                    if (column == 1) {
                        Double newPrice = Double.parseDouble(jTablePrelanBoardList.getValueAt(row, 1).toString());
//                        System.out.println("Nwe Price ----- " + newPrice);
//                        System.out.println("jTableImpregnatedPaperList.getValueAt(row, 1).toString().trim().toLowerCase()v " + jTableImpregnatedPaperList.getValueAt(row, 1).toString().trim().toLowerCase());
//                        System.out.println("mapFinishStockItem  -- " + mapFinishStockItem.entrySet());
//                        System.out.println("Contains Key ---   -- " + mapFinishStockItem.containsKey(jTableImpregnatedPaperList.getValueAt(row, 1).toString().trim().toLowerCase()));
//                        
                        if (!jTablePrelanBoardList.getValueAt(row, 0).toString().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(jTablePrelanBoardList.getValueAt(row, 0).toString().trim().toLowerCase())
                                && jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
                            callTableByFinishItemChange(newPrice, jTablePrelanBoardList.getValueAt(row, 0).toString());
                        } else {
                            partiTableModel.setRowCount(0);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
                }
            }
        };
        TableCellListener tcl = new TableCellListener(jTablePrelanBoardList, action1);


//******************************************************************************************
        pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 11 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

//        String col1[] = {Label.SALE_NO, Label.DATE_TRANSACTION, Label.ACCOUNT};
//        String data1[][] = {{"", "", ""}};

        String col11[] = {Label.PROD_No, Label.DATE_TRANSACTION, Label.PRELAN_BOARD};
        String data11[][] = {{"", "", ""}};

        transactionTableModel1 = new DefaultTableModel(data11, col11) {
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

        panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 3 5 7 12,grow");
        panel_1.setLayout(new MigLayout("", "[20px:20px:20px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:58.5px:58.5px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        pane = new JScrollPane();
        panel_1.add(pane, "cell 0 0 12 11,grow");
//******************************************************************************
//	        String col[] = {Label.S_N, Label.PARTICULARS, Label.RATE, Label.UNIT, Label.AMOUNT, Label.UNIT_SYMBOL, Label.COLOR, Label.PACKAGE, Label.NOS, Label.LENGTH, Label.WIDTH, Label.THICKNESS};
//        String col[] = {"SN", Label.PARTICULARS, "Thick", Label.COLOR, "SizeA", "SizeB", Label.PACKAGE, Label.NOS, Label.UNIT, Label.QUANTITY, Label.RATE, Label.AMOUNT, Label.UNIT_SYMBOL};
//        String data[][] = {{"", "", "", "", "", "", "", "", "", "", "", "", ""}};
        String col[] = {"SN", Label.PARTICULARS, Label.QUANTITY, Label.NOS, Label.TOTALQUANTITY, Label.OPBL, Label.CLBL};
        String data[][] = {{"", "", "", "", "", "", ""}};
        partiTableModel = new DefaultTableModel(data, col);
        tableParti = new JTable() {
            private static final long serialVersionUID = 1L;
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                if (column == 5 || column == 6) {
//                    return true;
//                } else {
//                    return false;
//                }
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
        partiTableModel.setColumnCount(7);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(280);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(3).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(4).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(5).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(6).setPreferredWidth(60);


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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
                }
            }
        };

        TableCellListener tcl1 = new TableCellListener(tableParti, action);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        pane.setViewportView(tableParti);

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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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

/////////on Dated 19-08-2014
        jTablePrelanBoardList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTablePrelanBoardListMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
//                showPopup(me);
            }

            public void mouseReleased(MouseEvent me) {
//                showPopup(me);
            }
        });

        jTablePrelanBoardList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        jTablePrelanBoardListMouseClicked(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
                    }
                }
            }
        });
//////////on Dated 19-08-2014


        jTableTransactionList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableTransactionListMouseClicked(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                        JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
                    }
                }
            }
        });


        jTablePrelanBoardList.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jtextFieldQuantity);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                        bindDTOtoPrelanBoardTable();
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
                        Logger.getLogger(PrelanBoardProduction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
//                    try {
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                        Logger.getLogger(PrelanBoardProduction.class.getName()).log(Level.SEVERE, null, ex);
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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

        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(PrelanBoardProduction.this, ex.getMessage());
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

    private void jTablePrelanBoardListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        String stockItemName = jTablePrelanBoardList.getValueAt(jTablePrelanBoardList.getSelectedRow(), 0).toString();
        Double stockItemNameQuantity = Double.parseDouble(jTablePrelanBoardList.getValueAt(jTablePrelanBoardList.getSelectedRow(), 1).toString());
        callTableByFinishItemChange(stockItemNameQuantity, stockItemName);
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
            PrelanBoardProduction.this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
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
                JOptionPane.showMessageDialog(this, "First Enter Quantity of  At least One Finish Item");
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
            for (PrelanBoardProductionDTO prelanBoardProductionDTO1 : prelanBoardProductionDTOList) {
                trans_id = prelanBoardProductionDTO1.getTrans_id();
                System.out.println("Impregante trans_id ---------- " + trans_id);
                PrelanBoardProductionDAO.deleteTransaction(Long.parseLong(trans_id));
            }
            JOptionPane.showMessageDialog(this, "Transaction deleted completly");
//            newButton();
//            clearFormData();
//            temp = 1000;
//            buttonAdd.setText("ADD");
            tfDatePicker.requestFocus();
            initilize();
            getMaxReceiptNo();
            bindDTOtoPrelanBoardTable();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        newButton();
//        clearFormData();
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
    private JLabel lblPrelan_Board;
    private JLabel lblQunatity;
    private JTextField jTextFieldReceiptNo;
    private JTextField jTextFieldSearch;
    private JTextField jtextFieldQuantity;
    private JScrollPane pane1;
    private JScrollPane pane;
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
    private JTable jTablePrelanBoardList;
    private DefaultTableModel TableModelPrelanBoard;
    private JTable tableParti;
    private DefaultTableModel transactionTableModel1;
    private JTable jTableTransactionList;

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
        prelanBoardProductionDTOList = new ArrayList<PrelanBoardProductionDTO>();
        prelanBoardProductionDTO = new PrelanBoardProductionDTO();
        isEdit = false;
        initStockItem();
        if (!isEdit) {
//            PrelanBoardProductionDTO.setReceiptNo(AccountingVoucherHelper.getNextProdAccountVoucherNumber(Constants.IMPREGNATED_PAPER_TYPE_INDEX));
            getMaxReceiptNo();
        }
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
    }

    private void initStockItem() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
        stockGroups.add("Prelan Board");
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
        bindDTOtoPrelanBoardTable();
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
            System.out.println("is Edit Date------- " + (java.util.Date) Constants.DATE_FORMATER.parse(prelanBoardProductionDTO.getDate().trim()));
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(prelanBoardProductionDTO.getDate().trim()));
        }

        for (PrelanBoardProductionDTO prelanBoardProductionDTO_Local : prelanBoardProductionDTOList) {
            jTextFieldReceiptNo.setText("" + prelanBoardProductionDTO_Local.getReceiptNo());
            jtextFieldQuantity.setText("" + prelanBoardProductionDTO_Local.getFinishItemQty());

//            System.out.println("is Not Edit Date------- " +(java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(impregnatedPaperProductionDTO_Local.getDate().trim()));
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(prelanBoardProductionDTO_Local.getDate().trim()));
//            tfDatePicker.setText(impregnatedPaperProductionDTO_Local.getDate().trim());
        }
        initTransactionList();
    }

    private void bindDTOtoPrelanBoardTable() throws Exception {
        int index = 1;

        if (!isEdit) {
            TableModelPrelanBoard.setRowCount(0);
        }

        bindEmptyPrelanBoardTable();

        for (Map.Entry<String, String> entrypoint : mapFinishStockItem.entrySet()) {
            try {

//                if (!isEdit) {
//                    TableModelImpregnatedPaper.setRowCount(TableModelImpregnatedPaper.getRowCount() + 1);
//                    TableModelImpregnatedPaper.setValueAt(entrypoint.getKey(), TableModelImpregnatedPaper.getRowCount() - 1, 0);
//                }

                /// Change the quantity of Impregantaed SI
//                TableModelImpregnatedPaper.setRowCount(0);
                for (int i = 0; i < TableModelPrelanBoard.getRowCount(); i++) {
                    for (PrelanBoardProductionDTO PrelanBoardProductionDTO : prelanBoardProductionDTOList) {
                        if (TableModelPrelanBoard.getValueAt(i, 0).toString().equalsIgnoreCase(PrelanBoardProductionDTO.getFinishStockItemName())) {
//                            TableModelImpregnatedPaper.setRowCount(TableModelImpregnatedPaper.getRowCount() + 1);
//                            TableModelImpregnatedPaper.setValueAt(PrelanBoardProductionDTO.getFinishStockItemName(), TableModelImpregnatedPaper.getRowCount() - 1, 0);
                            System.out.println("Finsih Item Qty ------- " + PrelanBoardProductionDTO.getFinishItemQty());
                            TableModelPrelanBoard.setValueAt(PrelanBoardProductionDTO.getFinishItemQty(), i, 1);
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

    private void bindEmptyPrelanBoardTable() {

        TableModelPrelanBoard.setRowCount(0);
        for (Map.Entry<String, String> entrypoint : mapFinishStockItem.entrySet()) {
            try {
                TableModelPrelanBoard.setRowCount(TableModelPrelanBoard.getRowCount() + 1);
                TableModelPrelanBoard.setValueAt(entrypoint.getKey(), TableModelPrelanBoard.getRowCount() - 1, 0);
                TableModelPrelanBoard.setValueAt(0.0, TableModelPrelanBoard.getRowCount() - 1, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void bindDTOtoJtable() throws Exception {
        int index = 1;
        List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionDTOList = prelanBoardProductionDTO.getListStockItemDTO();

        partiTableModel.setRowCount(0);
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            partiTableModel.setRowCount(0);
            for (gen.account.StockItemFormation.StockItemDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                try {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                    partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity() / prelanBoardProductionDTO.getFinishItemQty()), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(prelanBoardProductionDTO.getFinishItemQty()), partiTableModel.getRowCount() - 1, 3);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 4);
                    index++;
                } catch (Exception ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }
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
        prelanBoardProductionDTOList_For_Transactions = gen.feature.production.prelanboardproduction.PrelanBoardProductionDAO.getTransactionList();
        bindDTOToTransactionTable(prelanBoardProductionDTOList_For_Transactions);
    }

    private void bindDTOToTransactionTable(List<PrelanBoardProductionDTO> resignProductionProcessDTOTransactionList) throws SQLException {
//        if (resignProductionProcessDTOTransactionList != null && !resignProductionProcessDTOTransactionList.isEmpty()) {
        transactionTableModel1.setRowCount(0);
        for (PrelanBoardProductionDTO PrelanBoardProductionDTO : resignProductionProcessDTOTransactionList) {
            try {
                transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                transactionTableModel1.setValueAt(PrelanBoardProductionDTO.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(PrelanBoardProductionDTO.getDate().trim());
                transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                transactionTableModel1.setValueAt(PrelanBoardProductionDTO.getFinishStockItemName(), transactionTableModel1.getRowCount() - 1, 2);
            } catch (Exception ex) {
                Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
//        } else {
//            if (last_Number_In_TransactionList != 1) {
//                transactionTableModel1.setRowCount(0);
//            }
//        }
    }

    private void callTableByFinishItemChange(Double enteredQuantity, String finishItempassed) {
        try {

            String finishItem_id = mapFinishStockItem.get(finishItempassed.toLowerCase().trim());
            String date = Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim();

            String trans_id = "";
            for (PrelanBoardProductionDTO prelanBoardProductionDTO_Local : prelanBoardProductionDTOList) {
                if (finishItempassed.equalsIgnoreCase(prelanBoardProductionDTO_Local.getFinishStockItemName())) {
                    trans_id = prelanBoardProductionDTO_Local.getTrans_id();
                    System.out.println("Trans----------- id 111AAA" + trans_id);
                }
            }

            List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
            stockItemDTOList = gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getAllRawStockItemsInfo(finishItem_id, date, null);
            prelanBoardProductionDTO = new PrelanBoardProductionDTO();
            prelanBoardProductionDTO.setListStockItemDTO(stockItemDTOList);
            prelanBoardProductionDTO.setTrans_id(trans_id);
            prelanBoardProductionDTO.setFinishStockItemName(finishItempassed);
            prelanBoardProductionDTO.setFinishItemId(Long.parseLong(finishItem_id));
            prelanBoardProductionDTO.setFinishItemQty(enteredQuantity);
            prelanBoardProductionDTO.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
            prelanBoardProductionDTO.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText().trim()));
            partiTableModel.setRowCount(0);
            if (prelanBoardProductionDTO != null) {
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : prelanBoardProductionDTO.getListStockItemDTO()) {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(partiTableModel.getRowCount(), partiTableModel.getRowCount() - 1, 0);
//                    rawStockItemInTableMap.put(rawStockItemDTO.getName(), rawStockItemDTO.getQuantity().toString());
                    partiTableModel.setValueAt(rawStockItemDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(rawStockItemDTO.getQuantity(), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(enteredQuantity, partiTableModel.getRowCount() - 1, 3);
                    partiTableModel.setValueAt(rawStockItemDTO.getQuantity() * enteredQuantity, partiTableModel.getRowCount() - 1, 4);
                    rawStockItemDTO.setQuantity(rawStockItemDTO.getQuantity() * enteredQuantity);
                    calOPBLCLBL(Integer.parseInt(mapAllStockItem.get(rawStockItemDTO.getName().trim())));
                }
            }

            if (mapFinishItemWithRawItems != null && enteredQuantity != 0D) {
                mapFinishItemWithRawItems.put(finishItem_id, prelanBoardProductionDTO);
            } else {
                mapFinishItemWithRawItems.remove(finishItem_id);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AddFinishMaterialForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean validateFinishItem() {
        int i = 0;
        Boolean returntype = true;
        if (tableParti != null && tableParti.getRowCount() <= 0 ) {
            returntype = false;
        }
        return returntype;
    }

    private void submit() throws SQLException, Exception {
        prelanBoardProductionDTOList.clear();
        for (Map.Entry<String, PrelanBoardProductionDTO> e : mapFinishItemWithRawItems.entrySet()) {

            System.out.println("Key ------- " + e.getKey());
            prelanBoardProductionDTOList.add(e.getValue());


//            PrelanBoardProductionDTO impregnatedPaperProductionDTO1 = new PrelanBoardProductionDTO();
//            impregnatedPaperProductionDTO1 = e.getValue();
//
//            System.out.println("Finish item -- " + impregnatedPaperProductionDTO1.getFinishStockItemName());
//            System.out.println("Finish item ID-- " + impregnatedPaperProductionDTO1.getFinishItemId());
//            System.out.println("Finish item Qty-- " + impregnatedPaperProductionDTO1.getFinishItemQty());

//            for (gen.account.StockItemFormation.StockItemDTO dto : impregnatedPaperProductionDTO1.getListStockItemDTO()) {
//
//                System.out.println("Item Name-------" + dto.getName());
//                System.out.println("Item id-------" + dto.getID());
//                System.out.println("Item Qnty-------" + dto.getQuantity());
//
//            }
//
//            
        }

//        List<PrelanBoardProductionDTO> list = new ArrayList<PrelanBoardProductionDTO>();
//        ProductionReportDTO newProductionProcessListDTO = new ProductionReportDTO();
//        newProductionProcessListDTO.setDate(txtDate.getDate());
//        newProductionProcessListDTO.setReceiptNo(txtProductionID.getText().toString().trim());
//        if (tableFinishItem.getRowCount() > 0) {
//            for (int i = 0; i < tableFinishItem.getRowCount(); i++) {
//                String finishitemvalue = tableFinishItem.getValueAt(i, 1).toString();
//                for (Map.Entry<String, PrelanBoardProductionDTO> e : productionProcessMap.entrySet()) {
//                    System.out.println("");
//                    if (e.getKey().equalsIgnoreCase(finishitemvalue)) {
//                        list.add(e.getValue());
//                        newProductionProcessListDTO.setPaperproductionProcessDTOList(list);
//                    }
//                }
//            }
//        }

        for (int i = 0; i < prelanBoardProductionDTOList.size(); i++) {
            System.out.println("LIst Size --------- " + prelanBoardProductionDTOList.get(i).getReceiptNo());
            System.out.println("LIst Size --------- " + prelanBoardProductionDTOList.get(i).getReceiptNo());
        }
        if (!isEdit) {
            PrelanBoardProductionDAO.insert(prelanBoardProductionDTOList);
        } else {
            PrelanBoardProductionDAO.update(prelanBoardProductionDTOList);
        }
        JOptionPane.showMessageDialog(this, "Production is added!!");
        getMaxReceiptNo();
        bindDTOtoPrelanBoardTable();
        initilize();
//        productionProcessMap.clear();
//        DiffBet2MapsForQtyMap.clear();
//        tfFinishingGroupText.requestFocus();
//        initJTable();
    }

    private void getMaxReceiptNo() throws Exception {
        PrelanBoardProductionDAO newProductionProcessDAO = new PrelanBoardProductionDAO();
        jTextFieldReceiptNo.setText(newProductionProcessDAO.getMaxreceiptno().toString());
//        PrelanBoardProductionDTO.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText().toString().trim()));
    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
//        List<PrelanBoardProductionDTO> prelanBoardProductionDTOList = PrelanBoardProductionDAO.getImprePaper(idSet, "");
        prelanBoardProductionDTOList = PrelanBoardProductionDAO.getImprePaper(idSet, "");
        if (prelanBoardProductionDTOList != null && !prelanBoardProductionDTOList.isEmpty()) {
//            PrelanBoardProductionDTO = prelanBoardProductionDTOList.get(0);
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

    private void calOPBLCLBL(int item_id) {
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

            q = "select sum(invtrans_qty) as totalCred from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date <= '" + date1 + "' and trans_typeIndex in (2,7)))";
            rs1 = st1.executeQuery(q);
            while (rs1.next()) {
                creditBal = creditBal + rs1.getDouble("totalCred");
            }
            tableParti.setValueAt(openBal, tableParti.getRowCount() - 1, 5);
            System.out.println(q + "Credit......?>>>>>>>>>>>>>>>>>>>>>>>>>>." + creditBal);

            q = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date<= '" + date1 + "' and trans_typeIndex=1))";
            rs1 = st1.executeQuery(q);
            while (rs1.next()) {
                debitBal = debitBal + rs1.getDouble("totalDeb");
            }
            System.out.println(q + "Decit......?>>>>>>>>>>>>>>>>>>>>>>>>>>." + debitBal);

//            q = "select sum(invtrans_qty) as totalDeb from tblinventorytransactionitems where invtrans_itemId=" + item_id + " and invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date<='" + date1 + "' and trans_typeIndex=7))";
//            rs1 = st1.executeQuery(q);
//            while (rs1.next()) {
//                debitBal = debitBal + rs1.getDouble("totalDeb");
//            }

            q = "select si_openingBalance from tblstockitem where si_id=" + item_id + "";
            rs1 = st1.executeQuery(q);
            if (rs1.next()) {
                openBal = (rs1.getDouble("si_openingBalance") + creditBal) - debitBal;
            }

            tableParti.setValueAt(openBal, tableParti.getRowCount() - 1, 5);
//            tableParti.setValueAt(creditBal, tableParti.getRowCount() - 1, 5);

            Double Closing = (Double.parseDouble(tableParti.getValueAt(tableParti.getRowCount() - 1, 5).toString())) - Double.parseDouble(tableParti.getValueAt(tableParti.getRowCount() - 1, 4).toString());

            tableParti.setValueAt(Closing, tableParti.getRowCount() - 1, 6);

            //--------------------------------------------------------------------------------
        } catch (SQLException ex) {
            Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StockItemReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
