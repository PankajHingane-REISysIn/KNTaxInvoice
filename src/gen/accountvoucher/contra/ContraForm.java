package gen.accountvoucher.contra;

import com.toedter.calendar.JDateChooser;
import exception.FieldValidationException;
import gen.ImpExp.ImpExpUtil;
import gen.ImpExp.TagsHelper1;
import gen.account.ledger.LedgerForm;
import gen.account.stockitem.StockItemForm1;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.accountvoucher.sale.SaleForm;
import gen.database.connection.DatabaseConnection1;
import gen.account.ledger.LedgerDAO;
import gen.display.report.DayBook;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.LedgerTransactionDTO;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.BorderLayout;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class ContraForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private ContraDTO contraDTO;
    private JTextField tfAccountText, tfPartiText;
    public JTextField tfDatePicker = null;
    private Vector<String> cashLedgerVector = new Vector<String>();
    private Vector<String> partiVector = new Vector<String>();
    List<ContraDTO> contraDTOTransactionList;
    private DefaultTableModel partiTableModel;
    private Boolean isEdit = false;
    //variables For Calculation
    private Map<String, String> accountLedgerMap;
    private Map<String, String> partiItemMap;
    //variable for focus
    private int currentFocusValue = 0;
    //time stamp
    private Long ledgerTimeStamp;
    private int temp = 1000;
    private int index = 0;
    private int indexForDeletion = 0;
    private int flag;
    private JPopupMenu popUpMenu = new JPopupMenu();
    private JMenuItem Copy_Transaction = new JMenuItem();
    private Boolean isTransactionTableLoad = false;
    private int jTextFieldAmountResult = 0;
    private int labelFinalAmountTotalResult = 0;
    private static int last_Number_In_TransactionList = 0;

    public ContraForm(String s, Dimension d) {
        try {
            initComponents();
            contraDTO = new ContraDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ContraForm(ContraDTO contraDTO, Boolean isEdit) {
        try {
            initComponents();
            this.isEdit = isEdit;
            this.contraDTO = contraDTO;
            isTransactionTableLoad = true;
            initilize();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void initComponents() {

        setFrameIcon(new ImageIcon(ContraForm.class.getResource("/images/Kasturi-logo-1.png")));
        setTitle("Contra Transaction Form");
        setBounds(100, 100, 1332, 674);

        jMainPanel = new JPanel();
        jMainPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Contra Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(jMainPanel, BorderLayout.CENTER);
        jMainPanel.setLayout(new MigLayout("", "[][0px:225px:225px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][][0px:225px:225px,grow,shrink 50,fill][][][][0px:100px:100px,grow,shrink 50,fill][0px:350px:350px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:20px:20px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][][][][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelReceiptNo = new JLabel("Contra No.");
        jMainPanel.add(labelReceiptNo, "cell 0 0,alignx trailing");

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

        labelAccount = new JLabel("Cash Bank");
        jMainPanel.add(labelAccount, "cell 0 2,alignx trailing");

        jComboBoxAccount = new JComboBox();
        jComboBoxAccount.setPrototypeDisplayValue("xxxxxx");
        jComboBoxAccount.setEditable(true);
        jMainPanel.add(jComboBoxAccount, "cell 1 2,growx");

        JScrollPane pane1 = new JScrollPane();
        jMainPanel.add(pane1, "cell 9 2 1 18,grow");

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.RIGHT);

        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer();
        nameRenderer.setHorizontalAlignment(JLabel.LEFT);

        String col1[] = {Label.CONTRA_NO, Label.DATE_TRANSACTION, Label.PARTICULARS};
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
        jTableTransactionList.setFont(font);
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
        panel_1.setLayout(new MigLayout("","[40px:40px:40px,grow,shrink 50,fill][0px:225px:225px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill][0px:175px:175px,grow,shrink 50,fill]","[][][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelSN = new JLabel("S.N.");
        panel_1.add(labelSN, "cell 0 0");

        labelParticulars = new JLabel("Supplier's/Buyer's Name");
        panel_1.add(labelParticulars, "cell 1 0");

        labelcheckNo = new JLabel("Cheque Number");
        panel_1.add(labelcheckNo, "cell 2 0");

        labelNote = new JLabel("Note");
        panel_1.add(labelNote, "cell 3 0");

        labelAmount = new JLabel("Amount");
        panel_1.add(labelAmount, "cell 4 0");

        jComboBoxParti = new JComboBox();
        jComboBoxParti.setPrototypeDisplayValue("xxxxxx");
        jComboBoxParti.setEditable(true);
        panel_1.add(jComboBoxParti, "cell 1 1,growx");

        jTextFieldCheckNo = new JTextField();
        panel_1.add(jTextFieldCheckNo, "cell 2 1,growx");
        jTextFieldCheckNo.setColumns(10);

        jTextFieldNote = new JTextField();
        panel_1.add(jTextFieldNote, "cell 3 1,growx");
        jTextFieldNote.setColumns(10);

        jTextFieldAmount = new JTextField();
        panel_1.add(jTextFieldAmount, "cell 4 1,growx");
        jTextFieldAmount.setTransferHandler(null);
        jTextFieldAmount.setColumns(10);

        JScrollPane pane = new JScrollPane();
        panel_1.add(pane, "cell 0 2 5 5,grow");

        String col[] = {Label.S_N, Label.PARTICULARS, Label.CHECK_NO, Label.NOTE, Label.AMOUNT};
        String data[][] = {{"", "", "", ""}};
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
        partiTableModel.setColumnCount(5);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(45);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(230);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableParti.getColumnModel().getColumn(3).setPreferredWidth(180);
        tableParti.getColumnModel().getColumn(4).setPreferredWidth(180);
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
        panel_2.setLayout(new MigLayout("", "[0px:200px:200px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill][0px:165px:165px,grow,shrink 50,fill][0px:50px:50px,grow,shrink 50,fill][grow]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        labelFinalAmount = new JLabel("Total");
        labelFinalAmount.setHorizontalAlignment(SwingConstants.TRAILING);
        panel_2.add(labelFinalAmount, "cell 3 1");

        labelFinalAmountTotal = new JLabel("0");
        panel_2.add(labelFinalAmountTotal, "cell 5 1");

//        JPanel panel_3 = new JPanel();
//        jMainPanel.add(panel_3, "cell 0 18 8 4,grow");
//        panel_3.setLayout(new MigLayout(
//                "",
//                "[0px:750px:750px,grow,shrink 50,fill][]",
//                "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JPanel panel_3 = new JPanel();
        jMainPanel.add(panel_3, "cell 0 21 9 2,grow");
        panel_3.setLayout(new MigLayout("", "[0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill][0px:103px:103px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][][0px:25px:25px,grow,shrink 50,fill]"));

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
        jMainPanel.add(jTextFieldSearch, "cell 9 0");
        jTextFieldSearch.setColumns(25);

        buttonPrevious_DOWN_Transactions = new JButton("<<");
        jMainPanel.add(buttonPrevious_DOWN_Transactions, "flowx,cell 9 20");

        buttonNext_DOWN_Transactions = new JButton(">>");
        jMainPanel.add(buttonNext_DOWN_Transactions, "cell 9 20");

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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldReceiptNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldReceiptNoKeyPressed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldReceiptNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldReceiptNo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                        JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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



        jComboBoxParti.setEditable(true);
        tfPartiText = (JTextField) jComboBoxParti.getEditor().getEditorComponent();
        tfPartiText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfPartiText.getText();
                        if (text.length() == 0) {
                            jComboBoxParti.hidePopup();
                            setPartiModel(new DefaultComboBoxModel(partiVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(partiVector, text);
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
                            if (!partiVector.contains(text)) {
                                partiVector.addElement(text);
                                Collections.sort(partiVector);
                                setPartiModel(Util.getSuggestedModel(partiVector, text), text);
                            }
                            hide_flag = true;

                        } else if (code == KeyEvent.VK_ESCAPE) {
                            hide_flag = true;
                        } else if (code == KeyEvent.VK_RIGHT) {
                            for (int i = 0; i < partiVector.size(); i++) {
                                String str = partiVector.elementAt(i);
                                if (str.startsWith(text)) {
                                    jComboBoxParti.setSelectedIndex(-1);
                                    tfPartiText.setText(str);
                                    return;

                                }

                            }

                        }

                    }
                });

            }
        });

        tfPartiText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent event) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (event.getKeyCode() == KeyEvent.VK_ENTER || event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                                if (tfPartiText.getText().trim().equalsIgnoreCase("")) {
                                    currentFocusValue = 3;
                                    buttonSubmit.requestFocus();
                                } else {
                                    currentFocusValue = 3;
                                    setFocus(event);
                                }
                            }
                            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                setFocus(event);
                            }
                        }
                    }
                });
            }
        });

        tfPartiText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 3;
                String text = tfPartiText.getText();
                if (text.length() == 0) {
                    jComboBoxParti.hidePopup();
                    setPartiModel(new DefaultComboBoxModel(partiVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(partiVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxParti.hidePopup();
                        hide_flag = false;
                    } else {
                        setPartiModel(m, text);
                        jComboBoxParti.showPopup();
                    }

                }
                tfPartiText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                jComboBoxParti.hidePopup();
            }
        });

        tfPartiText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
        });

        jTextFieldCheckNo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCheckNoFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCheckNoFocusLost(evt);
            }
        });

        jTextFieldCheckNo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldCheckNoKeyPressed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });


        jTextFieldNote.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNoteFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNoteFocusLost(evt);
            }
        });

        jTextFieldNote.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldNoteKeyPressed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        jTextFieldAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldAmountFocusGained(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                try {
                    jTextFieldAmountFocusLost(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        jTextFieldAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldAmountKeyPressed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldAmount);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonAddActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                try {
                    tablePartiMouseClicked(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        tableParti.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    tablePartiKeyPressed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        jTableTransactionList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableTransactionListMouseClicked(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
                showPopup(me);
            }

            @Override
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
                        JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                    }
                }
            }
        });

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldSearchKeyPressed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jTextFieldSearchKeyReleased(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        buttonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                }
            }
        });

        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNewActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    jTextFieldAmount.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonPrintActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        buttonPrintActionPerformed(null);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonNew.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonExport.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    jTextFieldAmount.requestFocus();
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
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
        setFocus(null);

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
        MainClass.setstaticvar();
        //this.dispose();
    }

    private void jTextFieldReceiptNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 0;
    }

    private void jTextFieldReceiptNoKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldCheckNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 4;
        jTextFieldCheckNo.selectAll();
    }

    private void jTextFieldCheckNoFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void buttonSubmitKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jTextFieldCheckNoKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextFieldNoteFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 5;
        jTextFieldNote.selectAll();
    }

    private void jTextFieldNoteFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        //Util.checkForEmpty(jTextFieldNote);
    }

    private void jTextFieldNoteKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
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
                    if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabilty(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.CONTRA_TYPE_INDEX)) {
                        jTextFieldReceiptNo.setText("");
                    }
                } else {
                    jTextFieldReceiptNo.requestFocus();
		    jTextFieldReceiptNo.setText("");
                    throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
                }
            }

            if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
                jTextFieldReceiptNo.setText(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.CONTRA_TYPE_INDEX) + "");
            }
        }
    }

    private void jTextFieldAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        // TODO add your handling code here:
        currentFocusValue = 6;
        Util.checkForZero(jTextFieldAmount);
        jTextFieldAmount.selectAll();
    }

    private void jTextFieldAmountFocusLost(java.awt.event.FocusEvent evt) throws Exception {
        Util.checkForEmpty(jTextFieldAmount);
    }

    private void jTextFieldAmountKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                //partiAdd();
                buttonAddActionPerformed(null);
                //currentFocusValue = 2;
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
            Util.checkForEmpty(jTextFieldAmount);
            calculateAndBindTotalTOGUI();
        }
    }

    private void partiDelete() throws Exception {
        int j = 0;
        flag = 0;
        index = tableParti.getSelectedRow();
        if (tableParti.getRowCount() > 0) {
            j = 0;
            while (j < tableParti.getRowCount()) {
                if (tableParti.getValueAt(j, 1) == null) {
                    if (index == j) {
                        flag = 1;
                    }
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);
                }
                j++;
            }
        }
        if (flag == 0) {
            if (index == tableParti.getRowCount() - 1) {
                DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                tableModel.setRowCount(tableParti.getRowCount() - 1);
                if (tableParti.getRowCount() == 0) {
                    tfPartiText.requestFocus();
                }
            } else {
                j = index + 1;
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
        calculateAndBindTotalTOGUI();
        clearFormData();
        buttonAdd.setLabel(Label.BUTTON_ADD);
    }

    private void buttonAddKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldAmount.requestFocus();
        }
    }

    private void tablePartiMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        if (tableParti.getRowCount() > 0) {
            index = tableParti.getSelectedRow();
            if (index > -1) {
                Object o = tableParti.getValueAt(index, 0);
                if (o != null) {
                    temp = tableParti.getSelectedRow();
                    tfPartiText.setText(tableParti.getValueAt(temp, 1).toString());
                    if (tableParti.getValueAt(temp, 2) != null) {
                        jTextFieldCheckNo.setText(tableParti.getValueAt(temp, 2).toString());
                    } else {
                        jTextFieldCheckNo.setText("");
                    }
                    if (tableParti.getValueAt(temp, 2) != null) {
                        jTextFieldNote.setText(tableParti.getValueAt(temp, 3).toString());
                    } else {
                        jTextFieldNote.setText("");
                    }

                    jTextFieldAmount.setText(tableParti.getValueAt(temp, 4).toString());
                    buttonAdd.setLabel(Label.BUTTON_ALTER);
                    jComboBoxParti.requestFocus();
                    buttonParticularDelete.setEnabled(true);
                }
            }
        }
    }

    private void tablePartiKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        int flagVal = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            index = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                if (tableParti.getValueAt(index, 0) == null) {
                } else {
                    tablePartiMouseClicked(null);
                }
            } else {
                tfPartiText.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfPartiText.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int j = 0;
            flagVal = 0;
            index = tableParti.getSelectedRow();
            if (tableParti.getRowCount() > 0) {
                j = 0;
                while (j < tableParti.getRowCount()) {
                    if (tableParti.getValueAt(j, 1) == null) {
                        if (index == j) {
                            flagVal = 1;
                        }
                        DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                        tableModel.setRowCount(tableParti.getRowCount() - 1);
                    }
                    j++;
                }
            }
            if (flagVal == 0) {
                if (index == tableParti.getRowCount() - 1) {
                    DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                    tableModel.setRowCount(tableParti.getRowCount() - 1);
                    if (tableParti.getRowCount() == 0) {
                        tfPartiText.requestFocus();
                    }
                } else {
                    j = index + 1;
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

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
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

        List<ContraDTO> contraList = new ArrayList<ContraDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (ContraDTO contraDTOEntity : contraDTOTransactionList) {
            if (contraDTOEntity.getAccountName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                contraList.add(contraDTOEntity);
            }
        }
        bindDTOToTransactionTable(contraList);

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
        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(4);
        ContraForm.this.setClosed(true);
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        if (validateData()) {
            submit();
            tfDatePicker.requestFocus();
            jComboBoxAccount.hidePopup();
            jComboBoxParti.hidePopup();
            clearFormData();
            temp = 1000;
            buttonAdd.setText("ADD");
        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        ContraDAO.deleteTransaction(Long.parseLong(contraDTO.getTrans_ID()));
        JOptionPane.showMessageDialog(this, "Transaction deleted completly");
        newButton();
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
        clearFormData();
        temp = 1000;
        buttonAdd.setText("ADD");

    }

    private void buttonParticularDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (tableParti.isRowSelected(indexForDeletion)) {
            buttonParticularDelete.setVisible(true);
        }
        partiDelete();
        temp = 1000;
        tableParti.clearSelection();
        tfPartiText.requestFocus();
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        newButton();
        clearFormData();
        temp = 1000;
        buttonAdd.setText("ADD");
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
    }

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        if (buttonDelete.isEnabled()) {
            HashMap parameter = new HashMap();
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("trans_date", saDa.format(jDateChooser.getDate()).toString());
            parameter.put("S.N. Header", "S.N.");
            parameter.put("Particulars Header", "Particulars");
            parameter.put("Check No Header", "Check No");
            parameter.put("Note Header", "Note");
            parameter.put("Amount Header", "Amount");
            parameter.put("TitleHeader", "Contra");
            parameter.put("LedgerName Header", tfAccountText.getText().toString());
            parameter.put("ReceiptNo Header", jTextFieldReceiptNo.getText().toString());

            System.out.println("Todays Date" + saDa.format(jDateChooser.getDate()).toString());

            parameter.put("Final AmountHeader", labelFinalAmount.getText().toString());
            parameter.put("Final Amount", labelFinalAmountTotal.getText().toString());

            java.sql.Connection conn = DatabaseConnection1.GetConnection();
            Statement st1 = conn.createStatement();

            String id = "";
            String str = "select * from tblledger where ledger_name='" + tfAccountText.getText().toString().trim() + "'";
            ResultSet rs3 = st1.executeQuery(str);
            while (rs3.next()) {
                id = rs3.getString("ledger_contactno");
            }
            parameter.put("MobileNo", id);
            parameter.put("Title", "Contra");


            PrintAllReport.printReceiptReport(parameter, new JRTableModelDataSource(tableParti.getModel()));
        }
        tfDatePicker.requestFocus();
        jComboBoxAccount.hidePopup();
        jComboBoxParti.hidePopup();
    }

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        dataExport();

    }

    private void buttonNextTransactionsActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE + gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
        System.out.println("Value of the row call----------------------" + gen.dto.Constants.OFFSET_VALUE);
        last_Number_In_TransactionList = 1;
        initTransactionList();
    }//

    private void buttonPreviousTransactionsActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        gen.dto.Constants.OFFSET_VALUE = gen.dto.Constants.OFFSET_VALUE - gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination");
        System.out.println("Value of the row call----------------------" + gen.dto.Constants.OFFSET_VALUE);
        initTransactionList();
    }//
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
    private JLabel labelSN;
    private JLabel labelParticulars;
    private JComboBox jComboBoxParti;
    private JLabel labelcheckNo;
    private JTextField jTextFieldCheckNo;
    private JLabel labelNote;
    private JTextField jTextFieldNote;
    private JLabel labelAmount;
    private JTextField jTextFieldAmount;
    private JButton buttonAdd;
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
    private boolean hide_flag = false;

    private void initilizeGUIComponents() throws Exception {
        initComponentActiveInActive();
    }

    private void initComponentActiveInActive() throws Exception {
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

    private void setPartiModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxParti.setModel(mdl);
        jComboBoxParti.setSelectedIndex(-1);
        jComboBoxParti.showPopup();
        tfPartiText.setText(str);
    }

    private void initVariables() throws Exception {
        initLedger();
        if (!isEdit) {
            contraDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.CONTRA_TYPE_INDEX));
        }

        ledgerTimeStamp = Constants.LEDGER_TIME_STAMP;
        buttonParticularDelete.setEnabled(false);
    }

    private void initLedger() throws Exception {
        List<String> groups = new ArrayList<String>();
        groups.add(Constants.CASH_IN_HAND);
        groups.add(Constants.BANK_ACCOUNT);

        accountLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, true);

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

        accountLedgerMap = Util.getSmallCaseMap(accountLedgerMap);


        partiItemMap = LedgerDAO.getLedgerFromGroupName(groups, true);
        partiVector.clear();
        for (String str : partiItemMap.keySet()) {
            partiVector.add(str);
        }

        Collections.sort(
                partiVector,
                new Comparator<String>() {
                    public int compare(String lhs, String rhs) {
                        return lhs.compareToIgnoreCase(rhs);
                    }
                });

        partiItemMap = Util.getSmallCaseMap(partiItemMap);
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {
        gen.dto.Constants.OFFSET_VALUE = 0L;
        Long number = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.CONTRA_TYPE_INDEX, Constants.DEBIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));
        Long remaining = (gen.accountvoucher.chalan.ChalanDAO.getCount_transaction(Constants.CONTRA_TYPE_INDEX, Constants.DEBIT) / gen.other.AdSuMuDiSettingsOLD.AdSuMuDiConstant.map_checkBoxValue.get("pagination"));

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
        tfAccountText.setText(contraDTO.getAccountName());
        jTextFieldReceiptNo.setText("" + contraDTO.getReceiptNo());
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(contraDTO.getFinalAmount()) + "");
        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(contraDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(contraDTO.getDate().trim()));
        }

        if (contraDTO.getLedgerTransactionDTOList().isEmpty() || contraDTO.getLedgerTransactionDTOList() == null) {
            jTextFieldAmount.setText("0.0");
        }

    }

    private void bindDTOtoJtable() throws Exception {
        int index = 1;
        List<LedgerTransactionDTO> stockItemTransactionDTOList = contraDTO.getLedgerTransactionDTOList();
        if (stockItemTransactionDTOList != null && stockItemTransactionDTOList.size() > 0) {
            for (LedgerTransactionDTO stockItemTransactionDTO : stockItemTransactionDTOList) {
                partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                partiTableModel.setValueAt(index, partiTableModel.getRowCount() - 1, 0);
                partiTableModel.setValueAt(stockItemTransactionDTO.getName(), partiTableModel.getRowCount() - 1, 1);
                System.out.println("CheckNo------->>>" + stockItemTransactionDTO.getCheckNo());
                partiTableModel.setValueAt(stockItemTransactionDTO.getCheckNo(), partiTableModel.getRowCount() - 1, 2);
                partiTableModel.setValueAt(stockItemTransactionDTO.getNote(), partiTableModel.getRowCount() - 1, 3);
                partiTableModel.setValueAt(Constants.DECIMAL_FORMAT.format(stockItemTransactionDTO.getAmount()), partiTableModel.getRowCount() - 1, 4);
                index++;
            }
        }

    }

    private void bindDTOtoGUIWithoutReceiptNo() throws ParseException, Exception {
        tfAccountText.setText(contraDTO.getAccountName());
        jTextFieldNote.setText(contraDTO.getNote());
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(contraDTO.getFinalAmount()) + "");
        tfDatePicker.setText(new ContraDTO().getDate());
    }

    private void submit() throws SQLException, ParseException, Exception {
        contraDTO = bindGUITODTO();
        contraDTO = ImpExpUtil.valiDateAndRepairContraDTO(contraDTO);
        contraDTO.setAccountName(accountLedgerMap.get(contraDTO.getAccountName().trim().toLowerCase()));
        for (LedgerTransactionDTO stockItemTransactionDTO : contraDTO.getLedgerTransactionDTOList()) {
            stockItemTransactionDTO.setName(partiItemMap.get(stockItemTransactionDTO.getName().toLowerCase().trim()));
        }

        if (!isEdit) {
            insertContra();

        } else {
            updateContra();
        }
        JOptionPane.showMessageDialog(jMainPanel, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        newButton();
        tfDatePicker.requestFocus();
    }

    private ContraDTO bindGUITODTO() throws Exception {
        ContraDTO contraDTOEntity = new ContraDTO();
        contraDTOEntity.setTrans_ID(contraDTO.getTrans_ID());

        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            contraDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
            contraDTOEntity.setDate(Constants.simpleDateFormatDatabaseWithDash.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            contraDTOEntity.setAccountName(tfAccountText.getText().trim());
        }
        if (!jTextFieldCheckNo.getText().trim().equalsIgnoreCase("")) {
            contraDTOEntity.setCheckNo(jTextFieldCheckNo.getText().trim());
        }
        if (!jTextFieldNote.getText().trim().equalsIgnoreCase("")) {
            contraDTOEntity.setNote(jTextFieldNote.getText().trim());
        }

        contraDTOEntity.setLedgerTransactionDTOList(bindtableToDTO());

        if (!labelFinalAmountTotal.getText().trim().equalsIgnoreCase("")) {
            contraDTOEntity.setFinalAmount(Double.parseDouble(labelFinalAmountTotal.getText().trim()));
        }

        bindtableToDTO();

        return contraDTOEntity;
    }

    private List<LedgerTransactionDTO> bindtableToDTO() throws Exception {
        List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
        for (int index = 0; index < tableParti.getRowCount(); index++) {

            if (!tableParti.getValueAt(index, 1).toString().trim().equalsIgnoreCase("")) {
                LedgerTransactionDTO stockItemDTO = new LedgerTransactionDTO();
                stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
                if (!tableParti.getValueAt(index, 4).toString().trim().equalsIgnoreCase("")) {
                    stockItemDTO.setAmount(Double.parseDouble(tableParti.getValueAt(index, 4).toString()));
                }
                if (tableParti.getValueAt(index, 2) != null) {
                    stockItemDTO.setCheckNo(tableParti.getValueAt(index, 2).toString().trim());
                }
                if (tableParti.getValueAt(index, 3) != null) {
                    stockItemDTO.setNote(tableParti.getValueAt(index, 3).toString().trim());
                }
                stockItemTransactionDTOList.add(stockItemDTO);
            }
        }
        return stockItemTransactionDTOList;
    }

    private void insertContra() throws SQLException, ParseException, Exception {
        List<ContraDTO> contraDTOList = new ArrayList<ContraDTO>();
        contraDTOList.add(contraDTO);
        ContraDAO.insertContraVoucher(contraDTOList);
        AccountingVoucherHelper.updateVoucherNumber(Constants.CONTRA_TYPE_INDEX, (contraDTO.getReceiptNo()));
    }

    private void updateContra() throws SQLException, ParseException, Exception {
        List<ContraDTO> contraDTOList = new ArrayList<ContraDTO>();
        contraDTOList.add(contraDTO);
        ContraDAO.updateContraVoucher(contraDTOList);
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
        if (currentFocusValue > 8) {
            currentFocusValue = 8;
        }


        if (currentFocusValue == 0) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker.requestFocus();
        } else if (currentFocusValue == 2) {
            tfAccountText.requestFocus();
        } else if (currentFocusValue == 3) {
            tfPartiText.requestFocus();
        } else if (currentFocusValue == 4) {
            jTextFieldCheckNo.requestFocus();
        } else if (currentFocusValue == 5) {
            jTextFieldNote.requestFocus();
        } else if (currentFocusValue == 6) {
            jTextFieldAmount.requestFocus();
        }
    }

    private Boolean validateData() throws Exception {
        Boolean flagValue = true;
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
            flagValue = false;
        } else if (accountLedgerMap.get(tfAccountText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.ACCOUNT_LEDGER_VALUE_IS_NOT_VALID);
            tfAccountText.requestFocus();
            flagValue = false;
        } else if (jDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker.requestFocus();
            flagValue = false;
        } else if (tableParti.getRowCount() == 0) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_EMPTY);
            flagValue = false;
            tfPartiText.requestFocus();
        } else if (labelFinalAmountTotal.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthULTRASMALL) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        } else if (labelFinalAmountTotalResult == 1) {
            jTextFieldAmount.requestFocus();
            throw new Exception("Amount only valid upto " + Constants.jTextAreaAMOUNTDIGITS + " digits");
        }
        return flagValue;
    }

    private void calculateAndBindTotalTOGUI() throws Exception {
        Double totalAmount = 0D;
        for (int index = 0; index < partiTableModel.getRowCount(); index++) {
            if (!partiTableModel.getValueAt(index, 4).toString().equalsIgnoreCase("")) {
                totalAmount += Double.parseDouble(partiTableModel.getValueAt(index, 4).toString());
            }
        }
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(totalAmount) + "");
    }

    private void addTOJTable() throws Exception {

        index = 0;
        while (index < tableParti.getRowCount()) {
            if (tableParti.getValueAt(index, 0) == null) {
                int rowValue = tableParti.getRowCount();
                partiTableModel.setRowCount(rowValue - 1);
            }
            index++;
        }

        if (temp == 1000) {
            int rowValue = tableParti.getRowCount();
            index = 0;
            flag = 0;
            while (index < rowValue && flag == 0) {
                if (tableParti.getValueAt(index, 0) == null) {
                    tableParti.setValueAt(index + 1, index, 0);
                    tableParti.setValueAt(tfPartiText.getText().trim(), rowValue, 1);
                    tableParti.setValueAt(jTextFieldCheckNo.getText().trim(), rowValue, 2);
                    tableParti.setValueAt(jTextFieldNote.getText().trim(), rowValue, 3);
                    tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), rowValue, 4);
                    flag = 1;
                }
                index++;
            }
            if (flag == 0) {
                DefaultTableModel tableModel = (DefaultTableModel) tableParti.getModel();
                tableModel.setRowCount(rowValue + 1);
                tableParti.setValueAt(rowValue + 1, rowValue, 0);
                tableParti.setValueAt(tfPartiText.getText().trim(), rowValue, 1);
                tableParti.setValueAt(jTextFieldCheckNo.getText().trim(), rowValue, 2);
                tableParti.setValueAt(jTextFieldNote.getText().trim(), rowValue, 3);
                tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), rowValue, 4);
            }
        } else {
            int rowValue = temp;

            tableParti.setValueAt(tfPartiText.getText().trim(), rowValue, 1);
            tableParti.setValueAt(jTextFieldCheckNo.getText().trim(), rowValue, 2);
            tableParti.setValueAt(jTextFieldNote.getText().trim(), rowValue, 3);
            tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), rowValue, 4);
            temp = 1000;
            buttonAdd.setLabel(Label.BUTTON_ADD);
            tfPartiText.requestFocus();
            tableParti.clearSelection();
        }

    }

    public void loadEditForm(String id) throws SQLException, ParseException, Exception {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<ContraDTO> contraDTOList = ContraDAO.getContra(idSet, "");
        if (contraDTOList != null && !contraDTOList.isEmpty()) {
            contraDTO = contraDTOList.get(0);

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
        if (partiItemMap.get(tfPartiText.getText().trim().toLowerCase()) == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_VALUE_IS_NOT_VALID);
            tfPartiText.requestFocus();
            currentFocusValue = 2;
            return false;
        } else if ((jTextFieldAmount.getText().trim().equalsIgnoreCase("")) || (jTextFieldAmount.getText().trim().equalsIgnoreCase(".")) || (Double.parseDouble(jTextFieldAmount.getText().trim()) == 0D)) {
            JOptionPane.showMessageDialog(jMainPanel, Label.AMOUNT_NOT_VALID);
            jTextFieldAmount.requestFocus();
            currentFocusValue = 5;
            return false;
        } else if (jTextFieldCheckNo.getText().trim().toCharArray().length >= Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldCheckNo.requestFocus();
            throw new Exception("Cheque Number Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
        } else if (jTextFieldNote.getText().trim().toCharArray().length > Constants.jTextFieldCharacterLengthSMALL) {
            jTextFieldNote.requestFocus();
            throw new Exception("Note Exceeding " + Constants.jTextFieldCharacterLengthSMALL + " Character Limit");
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

    private void bindDTOToTransactionTable(List<ContraDTO> contraDTOTransactionList) throws Exception {
        if (contraDTOTransactionList != null && !contraDTOTransactionList.isEmpty()) {
            transactionTableModel1.setRowCount(0);
            for (ContraDTO contraDTOEntity : contraDTOTransactionList) {
                try {
                    transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                    transactionTableModel1.setValueAt(contraDTOEntity.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                    java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(contraDTOEntity.getDate().trim());
                    transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                    transactionTableModel1.setValueAt(contraDTOEntity.getAccountName(), transactionTableModel1.getRowCount() - 1, 2);
                } catch (ParseException ex) {
                    Logger.getLogger(ContraForm.class.getName()).log(Level.SEVERE, null, ex);
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
        contraDTO = new ContraDTO();
        isEdit = false;
        contraDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.CONTRA_TYPE_INDEX));
        initTransactionList();
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);
    }

    private void clearFormData() {
        tfPartiText.setText("");
        jTextFieldCheckNo.setText("");
        jTextFieldNote.setText("");
        jTextFieldAmount.setText("0.0");
        buttonParticularDelete.setEnabled(false);
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

            int r = chooser.showSaveDialog(ContraForm.this);

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
        Set<String> receiptSet = new HashSet<String>();
        receiptSet.add(jTextFieldReceiptNo.getText().trim());

        BufferedWriter out = null;
        if (!path.isEmpty()) {
            try {
                // TODO add your handling code here:
                Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
                IDMapSet.put("Contra", receiptSet);
                String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
//                String xmlCode = TagsHelper1.exportDayBookTally(IDMapSet);
                System.out.println("------------------------->>XMLCODE:" + xmlCode);

                // File file = new File("C:\\DayBook.xml");
                File file = new File(path + ".xml");
                path = file.getPath();
                out = new BufferedWriter(new FileWriter(file));
                out.write(xmlCode);
                out.close();

                JOptionPane.showMessageDialog(ContraForm.this, "Export Successful");

            } catch (Exception ex) {
                Logger.getLogger(DayBook.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Export failure");
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
                List<ContraDTO> contraDTOList = ContraDAO.getContra(idSet, "");
                if (contraDTOList != null && !contraDTOList.isEmpty()) {
                    try {
                        contraDTO = contraDTOList.get(0);
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
            JOptionPane.showMessageDialog(ContraForm.this, ex.getMessage());
        }
    }

    private void initTransactionList() throws SQLException, Exception {
        contraDTOTransactionList = ContraDAO.getTransactionList();
        bindDTOToTransactionTable(contraDTOTransactionList);
    }
}
