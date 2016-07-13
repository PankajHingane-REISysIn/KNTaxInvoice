/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.feature.production.resinproduction;

/**
 *
 * @author admin
 */
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
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
import gen.dto.Label;
import gen.dto.Constants;

import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.account.ledger.LedgerDTO;
import gen.accountvoucher.TableCellListener;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.accountvoucher.sale.SaleDAO;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.display.report.StockItemReport;
import gen.dto.StockItemTransactionDTO;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.feature.production.finisheditemdefination.AddFinishMaterialForm;
import gen.feature.production.finisheditemdefination.AddRawMaterialDTO;
import java.sql.Statement;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import javax.swing.JCheckBox;

public class ResinProductionForm extends JInternalFrame {

    List<ResignProductionProcessDTO> resignProductionProcessDTOList = new ArrayList<ResignProductionProcessDTO>();
    ResignProductionProcessDTO resignProductionProcessDTO = new ResignProductionProcessDTO();
    private Long stockItemTimeStamp;
    private int currentFocusValue = 0;
    private Vector<String> finishStockItemVector = new Vector<String>();
    private Boolean isEdit = false;
    private Map<String, String> mapFinishStockItem = new HashMap<String, String>();
    private Map<String, String> mapAllStockItem = new HashMap<String, String>();
    private Boolean isTransactionTableLoad = false;
    DefaultTableModel partiTableModel;

    public ResinProductionForm(String s, Dimension d) {
        try {
            initComponents();
            resignProductionProcessDTO = new ResignProductionProcessDTO();
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
        setFrameIcon(new ImageIcon(ResinProductionForm.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Resin Production");
        setBounds(100, 100, 1332, 674);

        jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(null, "Resin Production",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel.setLayout(new MigLayout("", "[][0px:112.5px:112.5px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][][0px:112.5px:112.5px,grow,shrink 50,fill][0px:112.5px:112.5px,grow,shrink 50,fill][][grow][][0px:70px:70px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:15px:15px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

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

        lblResin = new JLabel("Resin");
        jMainPanel.add(lblResin, "cell 0 2");

        comboBoxResin = new JComboBox();
        comboBoxResin.setEditable(true);
        comboBoxResin.setPrototypeDisplayValue("xxxxxx");
        jMainPanel.add(comboBoxResin, "cell 1 2 2 1,growx");

        lblQunatity = new JLabel("Qunatity");
        jMainPanel.add(lblQunatity, "cell 4 2,alignx trailing");

        jtextFieldQuantity = new JTextField();
        jMainPanel.add(jtextFieldQuantity, "cell 5 2 2 1,growx");
        jtextFieldQuantity.setColumns(10);

        pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 11 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        String col1[] = {Label.PROD_No, Label.DATE_TRANSACTION, Label.RESIN};
        String data1[][] = {{"", "", ""}};

        transactionTableModel1 = new DefaultTableModel(data1, col1) {
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

        jTableTransactionList = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
	        
         Action action1 = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TableCellListener tcl = (TableCellListener) e.getSource();
                    int column = tcl.getColumn();
                    int row = tcl.getRow();
                    double newPrice = Double.parseDouble(tcl.getNewValue().toString());
                    TableModel partiTableModel = tcl.getTable().getModel();
                    double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
                    double rate = Double.parseDouble(partiTableModel.getValueAt(row, 10).toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
                }
            }
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

        TableCellListener tcl = new TableCellListener(jTableTransactionList, action1);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableTransactionList.setEnabled(true);

        panel_1 = new JPanel();
        jMainPanel.add(panel_1, "cell 0 5 10 12,grow");
        panel_1.setLayout(new MigLayout("", "[20px:20px:20px,grow,shrink 50,fill][0px:280px:280px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:58.5px:58.5px,grow,shrink 50,fill][0px:45px:45px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:80px:80px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        pane = new JScrollPane();
        panel_1.add(pane, "cell 0 0 12 11,grow");

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
//                try {
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
////                        calculateAndBindTotalTOGUI();
////                        updateOnKeyType();
//                    } else if (column == 10) {
//                        System.out.println("in if");
//                        int row = tcl.getRow();
//                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
//                        TableModel partiTableModel = tcl.getTable().getModel();
//                        double quantity = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
//                        double rate = Double.parseDouble(partiTableModel.getValueAt(row, 11).toString());
//                        Double value = new Double((quantity * rate));
//                        partiTableModel.setValueAt(value, row, 11);
////                        calculateAndBindTotalTOGUI();
////                        updateOnKeyType();
//                    } else if (column == 11) {
//                        System.out.println("in else");
//                        int row = tcl.getRow();
//                        double newPrice = Double.parseDouble(tcl.getNewValue().toString());
//                        System.out.println("newPrice--->>" + newPrice);
//                        TableModel model = tcl.getTable().getModel();
////                        double weight = Double.parseDouble(partiTableModel.getValueAt(row, 9).toString());
////                        double amount = Double.parseDouble(partiTableModel.getValueAt(row, 10).toString());
////                        Double newValue = new Double((amount / weight));
////                        model.setValueAt(newValue, row, 10);
////                        calculateAndBindTotalTOGUI();
////                        updateOnKeyType();
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
//                }
            }
        };

        TableCellListener tcl1 = new TableCellListener(tableParti, action);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        pane.setViewportView(tableParti);

        panel_3 = new JPanel();
        jMainPanel.add(panel_3, "cell 0 19 10 3,grow");
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                        JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
                    }
                }
            }
        });


//        tableParti.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//                try {
//                    Util.filterCharacter(evt, jtextFieldQuantity);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                if (jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
                    if (!tfFinishItemAccountText.getText().toString().trim().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(tfFinishItemAccountText.getText().toString().trim().toLowerCase())
                            && jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
                        // getting RawItem for selected Finish Item
                        Double enteredQuantity = Double.parseDouble(jtextFieldQuantity.getText().trim());
                        callTableByFinishItemChange(enteredQuantity);
                    } else {
                        jtextFieldQuantity.setText("0.0");
                        partiTableModel.setRowCount(0);
                    }
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
                            setAccountLedgetModel(new DefaultComboBoxModel(finishStockItemVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(finishStockItemVector, text);
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
                    setAccountLedgetModel(new DefaultComboBoxModel(finishStockItemVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(finishStockItemVector, text);
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
                try {
                    // sale account is selected and dont contain selected Ledger
                    if (!tfFinishItemAccountText.getText().toString().trim().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(tfFinishItemAccountText.getText().toString().trim().toLowerCase())
                            && jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
                        // getting RawItem for selected Finish Item
                        Double enteredQuantity = Double.parseDouble(jtextFieldQuantity.getText().trim());
                        callTableByFinishItemChange(enteredQuantity);
                        System.out.println("Yes you got it.................");

                    } else {
//                        if(){
                        jtextFieldQuantity.setText("0.0");
                        partiTableModel.setRowCount(0);
//                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(ResinProductionForm.class.getName()).log(Level.SEVERE, null, ex);
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    if (!tfFinishItemAccountText.getText().toString().trim().isEmpty() && mapFinishStockItem != null && mapFinishStockItem.containsKey(tfFinishItemAccountText.getText().toString().trim().toLowerCase())
                            && jDateChooser.getDate() != null && !tfDatePicker.getText().equalsIgnoreCase("")) {
                        callTableByFinishItemChange(enteredQuantity);
                    } else {
                        jtextFieldQuantity.setText("0.0");
                        partiTableModel.setRowCount(0);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ResinProductionForm.this, ex.getMessage());
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buttonSubmit.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
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
            ResinProductionForm.this.setClosed(true);
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
            if (validateFinishItem());
            {
//                if (productionProcessMap.size() > 0) {
                    /*
                 *  Code for Take Value of TableStock Item From Map of FinishItemWithList
                 */
                submit();
                //    bindDTOtoGUIFinishTable();
//                } else {
//                    JOptionPane.showMessageDialog(this, "First Enter Quantity of  At least One Finish Item");
                tfDatePicker.requestFocus();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
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
    private JTextField tfDatePicker = null;
    private Font font;
    private JPanel jMainPanel;
    private JLabel labelReceiptNo;
    private JLabel labelDate;
    private JLabel lblResin;
    private JLabel lblQunatity;
    private JTextField jTextFieldReceiptNo;
    private JTextField jTextFieldSearch;
    private JTextField jtextFieldQuantity;
    private JScrollPane pane1;
    private JScrollPane pane;
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
    private JTable tableParti;
    private JTable jTableTransactionList;
    private DefaultTableModel transactionTableModel1;

//    private void showPopup(MouseEvent me) {
//        if (popUpMenu.isPopupTrigger(me)) {
//            Point p = me.getPoint();
//            row = jTableTransactionList.rowAtPoint(p);
//            int col = jTableTransactionList.columnAtPoint(p);
//            if (row != -1 && col == 0 || col == 1 || col == 2) {
//                Copy_Transaction.setText("Copy Transaction");
//                Copy_Transaction.addActionListener(this);
//                popUpMenu.show(jTableTransactionList, p.x, p.y);
//            }
//        }
//    }
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
        resignProductionProcessDTOList = new ArrayList<ResignProductionProcessDTO>();
        resignProductionProcessDTO = new ResignProductionProcessDTO();
        isEdit = false;
        initStockItem();
        if (!isEdit) {
            getMaxReceiptNo();
        }
        stockItemTimeStamp = Constants.STOCK_ITEM_TIME_STAMP;
    }

    private void initStockItem() throws Exception {
        List<String> stockGroups = new ArrayList<String>();
        stockGroups.add("Resin");
        mapFinishStockItem = gen.account.stockitem.StockItemDAO.getStockItemsFromGroupName(stockGroups, true);
        finishStockItemVector.clear();
        for (String str : mapFinishStockItem.keySet()) {
            finishStockItemVector.add(str);
        }

        Collections.sort(
                finishStockItemVector,
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
        bindDTOtoJtable();
        if (!isEdit || isTransactionTableLoad) {
            initTransactionList();
        }
        isTransactionTableLoad = false;

    }

    private void bindDTOtoGUI() throws Exception {
//        resignProductionProcessDTO = new ResignProductionProcessDTO();
        List<String> groupList = new ArrayList<String>();
        mapAllStockItem = gen.account.stockitem.StockItemDAO.getStockItemsNameID(groupList, false);
        tfFinishItemAccountText.setText(resignProductionProcessDTO.getFinishStockItem());
        jTextFieldReceiptNo.setText("" + resignProductionProcessDTO.getReceiptNo());
        jtextFieldQuantity.setText("" + resignProductionProcessDTO.getFinishItemQty());
        System.out.println("Bind Date ---------- " + resignProductionProcessDTO.getDate());

        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(resignProductionProcessDTO.getDate().trim()));
            System.out.println("DAtae   --------------- " + (java.util.Date) Constants.DATE_FORMATER.parse(resignProductionProcessDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(resignProductionProcessDTO.getDate().trim()));
        }
        initTransactionList();

    }

    private void bindDTOtoJtable() throws Exception {
        int index = 1;
        List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionDTOList = resignProductionProcessDTO.getListStockItemDTO();

        partiTableModel.setRowCount(0);
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            for (gen.account.StockItemFormation.StockItemDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                try {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                    partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity() / resignProductionProcessDTO.getFinishItemQty()), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(resignProductionProcessDTO.getFinishItemQty()), partiTableModel.getRowCount() - 1, 3);
                    partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getQuantity()), partiTableModel.getRowCount() - 1, 4);
                    index++;
                } catch (Exception ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                    throw ex;
                }
            }
        }
    }

    private void initTransactionList() throws SQLException, Exception {
        resignProductionProcessDTOList = gen.feature.production.resinproduction.ResignProductionProcessDAO.getTransactionList();
        bindDTOToTransactionTable(resignProductionProcessDTOList);
    }

    private void bindDTOToTransactionTable(List<ResignProductionProcessDTO> resignProductionProcessDTOTransactionList) throws SQLException {
//        if (resignProductionProcessDTOTransactionList != null && !resignProductionProcessDTOTransactionList.isEmpty()) {
        transactionTableModel1.setRowCount(0);
        for (ResignProductionProcessDTO resignProductionProcessDTO : resignProductionProcessDTOTransactionList) {
            try {
                transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                transactionTableModel1.setValueAt(resignProductionProcessDTO.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(resignProductionProcessDTO.getDate().trim());
                transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                transactionTableModel1.setValueAt(resignProductionProcessDTO.getFinishStockItem(), transactionTableModel1.getRowCount() - 1, 2);
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

    private void callTableByFinishItemChange(Double enteredQuantity) {
        try {

            String finishItem = mapFinishStockItem.get(tfFinishItemAccountText.getText().toLowerCase().trim());
            String date = Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim();

            List<gen.account.StockItemFormation.StockItemDTO> stockItemDTOList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
            stockItemDTOList = gen.feature.production.finisheditemdefination.AddRawMaterialDAO.getAllRawStockItemsInfo(finishItem, date, null);

            resignProductionProcessDTO.setListStockItemDTO(stockItemDTOList);
            resignProductionProcessDTO.setFinishStockItem(tfFinishItemAccountText.getText().toLowerCase().trim());
            resignProductionProcessDTO.setDate(Constants.simpleDateFormatGUI.format(jDateChooser.getDate()).toString().trim());


            partiTableModel.setRowCount(0);
            if (resignProductionProcessDTO != null) {
                for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : resignProductionProcessDTO.getListStockItemDTO()) {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(partiTableModel.getRowCount(), partiTableModel.getRowCount() - 1, 0);
//                    rawStockItemInTableMap.put(rawStockItemDTO.getName(), rawStockItemDTO.getQuantity().toString());
                    partiTableModel.setValueAt(rawStockItemDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                    partiTableModel.setValueAt(rawStockItemDTO.getQuantity(), partiTableModel.getRowCount() - 1, 2);
                    partiTableModel.setValueAt(enteredQuantity, partiTableModel.getRowCount() - 1, 3);
                    partiTableModel.setValueAt(rawStockItemDTO.getQuantity() * enteredQuantity, partiTableModel.getRowCount() - 1, 4);
                    System.out.println("Map value ------- " + mapAllStockItem.entrySet());
                    calOPBLCLBL(Integer.parseInt(mapAllStockItem.get(rawStockItemDTO.getName().trim())));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AddFinishMaterialForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAccountLedgetModel(DefaultComboBoxModel mdl, String str) {
        comboBoxResin.setModel(mdl);
        comboBoxResin.setSelectedIndex(-1);
        comboBoxResin.showPopup();
        tfFinishItemAccountText.setText(str);
    }

    private Boolean validateFinishItem() {
        int i = 0;
        Boolean returntype = true;
        while (i < mapFinishStockItem.size()) {
            if (mapFinishStockItem.get(tfFinishItemAccountText.getText().toLowerCase()) == null) {
                returntype = false;
            }
            i++;
        }
        return returntype;
    }

    private void submit() throws SQLException, Exception {
//        ResignProductionProcessDTO newChemicalProductionProcessDTO = new ResignProductionProcessDTO();
//        ProductionReportDTO newProductionProcessListDTO = new ProductionReportDTO();
//

        List<gen.account.StockItemFormation.StockItemDTO> stockitemDTOList = bindRawMaterialGUITODTO();
        resignProductionProcessDTO.setListStockItemDTO(stockitemDTOList);

        resignProductionProcessDTO.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText().trim()));
        resignProductionProcessDTO.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
        resignProductionProcessDTO.setFinishItemId(Long.parseLong(mapAllStockItem.get(tfFinishItemAccountText.getText().toString().trim())));
        resignProductionProcessDTO.setFinishItemQty(Double.parseDouble(jtextFieldQuantity.getText().toString().trim()));
        resignProductionProcessDTO.setResin_group_id("1");
        resignProductionProcessDTOList.add(resignProductionProcessDTO);

        if (!isEdit) {
            ResignProductionProcessDAO.insert(resignProductionProcessDTO);
        } else {

            System.out.println("update finish Item" + resignProductionProcessDTO.getFinishItemId().toString());
            System.out.println("" + resignProductionProcessDTO.getFinishItemQty());

            for (gen.account.StockItemFormation.StockItemDTO rawStockItemDTO : resignProductionProcessDTO.getListStockItemDTO()) {
                System.out.println("update Raaw Stock Item" + rawStockItemDTO.getID());
                System.out.println(" update Raaw Stock" + rawStockItemDTO.getQuantity());
            }
            ResignProductionProcessDAO.update(resignProductionProcessDTO);
        }

        JOptionPane.showMessageDialog(this, "Production is added!!");
        getMaxReceiptNo();
        initilize();
        // initJTableForFinishItem();
//        comboFinishGroup.setSelectedItem("");
//        productionProcessMap.clear();
//        DiffBet2MapsForQtyMap.clear();
//        tfFinishingGroupText.requestFocus();
//        comboFinishItem.setSelectedItem("");
//        txtProductQuantity.setText("");
//        initJTable();
    }

    private List<gen.account.StockItemFormation.StockItemDTO> bindRawMaterialGUITODTO() {
        List<gen.account.StockItemFormation.StockItemDTO> listStockItemDTO = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
        if (tableParti.getRowCount() > 0) {
            for (int i = 0; i < tableParti.getRowCount(); i++) {
                gen.account.StockItemFormation.StockItemDTO stockItemDTO = new gen.account.StockItemFormation.StockItemDTO();
//                stockItemDTO.set(Integer.parseInt(tableParti.getValueAt(i, 0).toString()));
                stockItemDTO.setName(tableParti.getValueAt(i, 1).toString());
                stockItemDTO.setQuantity(Double.parseDouble(tableParti.getValueAt(i, 4).toString()));
                stockItemDTO.setID(Long.parseLong(mapAllStockItem.get(tableParti.getValueAt(i, 1).toString().trim())));
                stockItemDTO.setUnit_of_symbol(tableParti.getValueAt(i, 3).toString());
//                stockItemDTO.setTrialOPBL(Double.parseDouble(tableParti.getValueAt(i, 4).toString()));
//                stockItemDTO.setTrialClBl(Double.parseDouble(tableParti.getValueAt(i, 5).toString()));
                listStockItemDTO.add(stockItemDTO);
            }
        } else {
            JOptionPane.showMessageDialog(null, "First Select Quantity");
        }
        return listStockItemDTO;
    }

    private void getMaxReceiptNo() throws Exception {
        ResignProductionProcessDAO newProductionProcessDAO = new ResignProductionProcessDAO();
        jTextFieldReceiptNo.setText(newProductionProcessDAO.getMaxreceiptno().toString());
        resignProductionProcessDTO.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText().toString().trim()));
    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<ResignProductionProcessDTO> resignProductionProcessDTOList = ResignProductionProcessDAO.getResin(idSet, "");
        if (resignProductionProcessDTOList != null && !resignProductionProcessDTOList.isEmpty()) {
            resignProductionProcessDTO = resignProductionProcessDTOList.get(0);
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
            jTextFieldReceiptNo.requestFocus();
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
