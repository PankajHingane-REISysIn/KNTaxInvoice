package gen.accountvoucher.journal;

import exception.FieldValidationException;
import gen.ImpExp.ImpExpUtil;
import gen.accountvoucher.sale.SaleForm;
import gen.ImpExp.TagsHelper1;
import gen.account.ledger.Ledger_Create;
import gen.accountvoucher.Util.CopyPasteOperation;
import gen.accountvoucher.helper.AccountingVoucherHelper;
import gen.account.ledger.LedgerDAO;
import gen.accountvoucher.sale.SaleDAO;
import gen.database.connection.StockItemDAO;
import gen.display.report.DayBook;
import gen.dto.Constants;
import gen.dto.GUIConstants;
import gen.dto.Label;
import gen.dto.LedgerTransactionDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class JournalForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private JournalDTO journalDTO;
    private JTextField tfAccountText, tfPartiText;
    public JTextField tfDatePicker = null;
    private Vector<String> cashLedgerVector = new Vector<String>();
    private Vector<String> partiVector = new Vector<String>();
    List<JournalDTO> journalDTOTransactionList;
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
    int index = 0;
    int indexForDeletion = 0;
    JPopupMenu popUpMenu = new JPopupMenu();
    JMenuItem Copy_Transaction = new JMenuItem();
    Boolean flag = true;

    public JournalForm(String s, Dimension d) {
        try {
            initComponents();
            journalDTO = new JournalDTO();
            initilize();
            this.setPreferredSize(d);
            setClosable(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    public JournalForm(JournalDTO receiptDTO, Boolean isEdit) {
        try {
            initComponents();
            this.isEdit = isEdit;
            this.journalDTO = receiptDTO;
            initilize();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        container = getContentPane();
        popUpMenu.add(Copy_Transaction);
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        screenWidth = dimension.width;
        screenHeight = dimension.height;

        xCoordinate = (this.getWidth() / 2) - (screenWidth / 2);
        System.out.println("getWidth--->>>" + this.getWidth());
        yCoordinate = (this.getHeight() / 2) - (screenHeight / 2);



        System.out.println("X-->>" + xCoordinate + "y-->>" + yCoordinate);

        screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
        System.out.println("Resolution" + screenResolution);

        flowLblLocationX = (-(xCoordinate) / screenResolution);
        flowLblLocationY = (-(yCoordinate) / screenResolution);

        System.out.println("flowLblLocationX---->>>" + flowLblLocationX);
        System.out.println("flowLblLocationY--->>>" + flowLblLocationY);

        fontValue = (-(xCoordinate) / screenResolution) + (-(yCoordinate) / screenResolution);
        System.out.println("Calculated font size" + fontValue);
        fontSize = fontValue;//(int)Math.round(9.0 * screenRes / 80.0);

        System.out.println("FontSize" + fontSize);

        font = new Font("Tahoma", Font.BOLD, fontSize);

        jMainPanel = new JPanel(null);//new FlowLayout(FlowLayout.LEFT,10,10)); 
        jMainPanel.setBounds(screenWidth / 40, screenHeight / 50, screenWidth - 75, screenHeight - 175);
        jMainPanel.setBorder(new TitledBorder(new EtchedBorder(), Label.JOURNAL_FORM_NAME));
        jMainPanel.setFont(font);

        GUIConstants.init(screenWidth, screenHeight);

        labelReceiptNo = new JLabel(Label.JOURNAL_NO);
        labelReceiptNo.setFont(font);
        labelReceiptNo.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_1, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelReceiptNo);

        jTextFieldReceiptNo = new JTextField(6);
        jTextFieldReceiptNo.setEnabled(false);
        jTextFieldReceiptNo.setBounds(GUIConstants.XAxis_LEVEL_2, GUIConstants.YAxis_LEVEL_1, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldReceiptNo);

        labelDate = new JLabel(Label.DATE);
        labelDate.setFont(font);
        labelDate.setBounds(GUIConstants.XAxis_LEVEL_6, GUIConstants.YAxis_LEVEL_1, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelDate);

        jDateChooser = new com.toedter.calendar.JDateChooser();
        jDateChooser.setBounds(GUIConstants.XAxis_LEVEL_8, GUIConstants.YAxis_LEVEL_1, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jDateChooser.setEnabled(true);
        jMainPanel.add(jDateChooser);

        labelAccount = new JLabel(Label.FROM_TO);
        labelAccount.setFont(font);
        //labelAccount.setBounds(GUIConstants.XAxis_LEVEL_5_1, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        labelAccount.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeLargeWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelAccount);

        jComboBoxFromTo = new JComboBox();
        jComboBoxFromTo.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_4, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jComboBoxFromTo);

        jComboBoxAccount = new JComboBox();
        //jComboBoxAccount.setBounds(GUIConstants.XAxis_LEVEL_5_1, GUIConstants.YAxis_LEVEL_4, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jComboBoxAccount.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_4, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jComboBoxAccount.setVisible(false);
        jMainPanel.add(jComboBoxAccount);

        labelSN = new JLabel(Label.DEBIT);
        labelSN.setFont(font);
        labelSN.setBounds(GUIConstants.XAxis_LEVEL_5_1, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeLargeWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelSN);

        jTextFieldAdjustmentAmount = new JTextField(6);
        Util.checkForEmpty(jTextFieldAdjustmentAmount);
        jTextFieldAdjustmentAmount.setBounds(GUIConstants.XAxis_LEVEL_5_1, GUIConstants.YAxis_LEVEL_4, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldAdjustmentAmount);

        labelParticulars = new JLabel(Label.LEDGER_NAME);
        labelParticulars.setFont(font);
        labelParticulars.setBounds(GUIConstants.XAxis_LEVEL_3, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);

        jMainPanel.add(labelParticulars);

        jComboBoxParti = new JComboBox();
        jComboBoxParti.setBounds(GUIConstants.XAxis_LEVEL_3, GUIConstants.YAxis_LEVEL_4, GUIConstants.comboBoxSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jComboBoxParti);


        labelcheckNo = new JLabel(Label.CHECK_NO);
        labelcheckNo.setFont(font);
        labelcheckNo.setBounds(GUIConstants.XAxis_LEVEL_3_1, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        labelcheckNo.setVisible(false);
        jMainPanel.add(labelcheckNo);

        jTextFieldCheckNo = new JTextField(6);
        Util.checkForEmpty(jTextFieldCheckNo);
        jTextFieldCheckNo.setBounds(GUIConstants.XAxis_LEVEL_3_1, GUIConstants.YAxis_LEVEL_4, GUIConstants.jtextFieldSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jTextFieldCheckNo.setVisible(false);
        jMainPanel.add(jTextFieldCheckNo);


        labelNote = new JLabel(Label.NOTE);
        labelNote.setFont(font);
        labelNote.setBounds(GUIConstants.XAxis_LEVEL_5, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        labelNote.setVisible(false);
        jMainPanel.add(labelNote);

        jTextFieldNote = new JTextField(6);
        Util.checkForEmpty(jTextFieldNote);
        jTextFieldNote.setBounds(GUIConstants.XAxis_LEVEL_5, GUIConstants.YAxis_LEVEL_4, GUIConstants.jtextFieldSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jTextFieldNote.setVisible(false);
        jMainPanel.add(jTextFieldNote);

        labelAmount = new JLabel(Label.CREDIT);
        labelAmount.setFont(font);
        labelAmount.setBounds(GUIConstants.XAxis_LEVEL_8, GUIConstants.YAxis_LEVEL_3, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
        jMainPanel.add(labelAmount);

        jTextFieldAmount = new JTextField(6);
        Util.checkForEmpty(jTextFieldAmount);
        jTextFieldAmount.setBounds(GUIConstants.XAxis_LEVEL_8, GUIConstants.YAxis_LEVEL_4, GUIConstants.jtextFieldSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jMainPanel.add(jTextFieldAmount);

        buttonAdd = new JButton();
        buttonAdd.setMargin(new Insets(2, 0, 2, 0));
        buttonAdd.setBounds(GUIConstants.XAxis_LEVEL_9_1 + 1, GUIConstants.YAxis_LEVEL_4, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonAdd.setText(Label.BUTTON_ADD);
        buttonAdd.setFont(font);
        buttonAdd.setMnemonic(KeyEvent.VK_A);
        jMainPanel.add(buttonAdd);

        buttonParticularDelete = new JButton();
        buttonParticularDelete.setMargin(new Insets(2, 0, 2, 0));
        buttonParticularDelete.setBounds(GUIConstants.XAxis_LEVEL_9_1 + 1, GUIConstants.YAxis_LEVEL_4_1, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonParticularDelete.setText(Label.BUTTON_DELETE);
        buttonParticularDelete.setFont(font);
        buttonParticularDelete.setMnemonic(KeyEvent.VK_T);
        buttonParticularDelete.setVisible(true);
        jMainPanel.add(buttonParticularDelete);


        labelFinalAmount = new JLabel(Label.FINAL_AMOUNT);
        labelFinalAmount.setFont(font);
        labelFinalAmount.setBounds(((screenWidth / 9) * 2), GUIConstants.YAxis_LEVEL_6, screenWidth / 10, screenHeight / 25);
        labelFinalAmount.setVisible(false);
        jMainPanel.add(labelFinalAmount);

        labelFinalAmountTotal = new JLabel("");
        labelFinalAmountTotal.setFont(font);
        labelFinalAmountTotal.setBounds(((screenWidth / 7) * 2) + 5, GUIConstants.YAxis_LEVEL_6, screenWidth / 10, screenHeight / 25);
        labelFinalAmountTotal.setVisible(false);
        jMainPanel.add(labelFinalAmountTotal);


        buttonBack = new JButton();
        buttonBack.setText(Label.BUTTON_BACK);
        buttonBack.setMargin(new Insets(2, 0, 2, 0));
        buttonBack.setFont(font);
        buttonBack.setBounds(GUIConstants.XAxis_LEVEL_1, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonBack.setMnemonic(KeyEvent.VK_B);
        jMainPanel.add(buttonBack);

        buttonNew = new JButton();
        buttonNew.setText(Label.BUTTON_NEW);
        buttonNew.setMargin(new Insets(2, 0, 2, 0));
        buttonNew.setFont(font);
        buttonNew.setBounds(GUIConstants.XAxis_LEVEL_2_1, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonNew.setMnemonic(KeyEvent.VK_N);
        jMainPanel.add(buttonNew);

        buttonPrint = new JButton();
        buttonPrint.setMargin(new Insets(2, 0, 2, 0));
        buttonPrint.setBounds(GUIConstants.XAxis_LEVEL_4, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonPrint.setText(Label.BUTTON_PRINT);
        buttonPrint.setFont(font);
        buttonPrint.setMnemonic(KeyEvent.VK_P);
        jMainPanel.add(buttonPrint);

        buttonExport = new JButton();
        buttonExport.setText(Label.BUTTON_EXPORT);
        buttonExport.setMargin(new Insets(2, 0, 2, 0));
        buttonExport.setFont(font);
        buttonExport.setBounds(GUIConstants.XAxis_LEVEL_5_1, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonExport.setMnemonic(KeyEvent.VK_E);
        jMainPanel.add(buttonExport);

        buttonDelete = new JButton();
        buttonDelete.setText(Label.BUTTON_DELETE);
        buttonDelete.setMargin(new Insets(2, 0, 2, 0));
        buttonDelete.setFont(font);
        buttonDelete.setBounds(GUIConstants.XAxis_LEVEL_7, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonDelete.setMnemonic(KeyEvent.VK_D);
        jMainPanel.add(buttonDelete);

        buttonAddLedger = new JButton();
        buttonAddLedger.setText(Label.BUTTON_ADD_LEDGER);
        buttonAddLedger.setMargin(new Insets(2, 0, 2, 0));
        buttonAddLedger.setFont(font);
        buttonAddLedger.setBounds(GUIConstants.XAxis_LEVEL_8_1, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonAddLedger.setMnemonic(KeyEvent.VK_L);
        jMainPanel.add(buttonAddLedger);

        buttonAddItem = new JButton();
        buttonAddItem.setText(Label.BUTTON_ADD_ITEM);
        buttonAddItem.setMargin(new Insets(2, 0, 2, 0));
        buttonAddItem.setFont(font);
        buttonAddItem.setBounds(GUIConstants.XAxis_LEVEL_10, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonAddItem.setMnemonic(KeyEvent.VK_I);
        jMainPanel.add(buttonAddItem);

        buttonSubmit = new JButton();
        buttonSubmit.setText(Label.BUTTON_SUBMIT);
        buttonSubmit.setMargin(new Insets(2, 0, 2, 0));
        buttonSubmit.setFont(font);
        buttonSubmit.setBounds(GUIConstants.XAxis_LEVEL_12_1, GUIConstants.YAxis_LEVEL_10, GUIConstants.buttonnSizeMediumWidth, GUIConstants.buttonnSizeHeight);
        buttonSubmit.setMnemonic(KeyEvent.VK_S);
        jMainPanel.add(buttonSubmit);

        labelSearch = new JLabel(Label.SEARCH);
        labelSearch.setFont(font);
        labelSearch.setBounds(GUIConstants.XAxis_LEVEL_17, GUIConstants.YAxis_LEVEL_1, GUIConstants.jtextFieldSizeMediumWidth, GUIConstants.labelSizeHeight);
        labelSearch.setVisible(false);
        jMainPanel.add(labelSearch);

        jTextFieldSearch = new JTextField(5);
        jTextFieldSearch.setFont(font);
        jTextFieldSearch.setBounds(GUIConstants.XAxis_LEVEL_18, GUIConstants.YAxis_LEVEL_1, GUIConstants.jtextFieldSizeMediumWidth, GUIConstants.jtextFieldSizeHeight);
        jTextFieldSearch.setVisible(false);
        jMainPanel.add(jTextFieldSearch);

        String col1[] = {Label.TR_NO, Label.DATE_OF_TRANSACTION, Label.LEDGER_NAME};
        String data1[][] = {{"", "", ""}};
        transactionTableModel1 = new DefaultTableModel(data1, col1);
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

        jTableTransactionList.setFont(font);
        transactionTableModel1 = (DefaultTableModel) jTableTransactionList.getModel();
        transactionTableModel1.setRowCount(0);
        transactionTableModel1.setColumnCount(3);
        jTableTransactionList.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JScrollPane pane1 = new JScrollPane(jTableTransactionList);
        pane1.setBounds(GUIConstants.XAxis_LEVEL_16, GUIConstants.YAxis_LEVEL_2, screenWidth / 6 + 60, (screenHeight / 4) * 3 - 100);
        jTableTransactionList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableTransactionList.setFont(font);
        //jTableTransactionList.setVisible(false);
        pane1.setVisible(true);

        jMainPanel.add(pane1);


        //Function For Setting JTable Column Size according to resolution
        int i;
        if (screenWidth < 1400 && screenWidth > 1200) {
            i = 2;
        } else if (screenWidth < 1200 && screenWidth > 1000) {
            i = 1;
        } else {
            i = 0;
        }

        String col[] = {Label.AMOUNT_TO_BE_ADJUSTED, Label.TO, Label.FROM, Label.AMOUNT};
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
        tableParti.setTableHeader(null);
        tableParti.setFont(font);
        partiTableModel = (DefaultTableModel) tableParti.getModel();
        partiTableModel.setRowCount(0);
        partiTableModel.setColumnCount(4);
        tableParti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableParti.getColumnModel().getColumn(0).setPreferredWidth(GUIConstants.comboBoxSizeMediumWidth + 0);
        tableParti.getColumnModel().getColumn(1).setPreferredWidth(GUIConstants.comboBoxSizeMediumWidth + 0);
        tableParti.getColumnModel().getColumn(2).setPreferredWidth(GUIConstants.comboBoxSizeMediumWidth + i);
        tableParti.getColumnModel().getColumn(3).setPreferredWidth(GUIConstants.jtextFieldSizeMediumWidth + 0);
        JScrollPane pane = new JScrollPane(tableParti);
        pane.setBounds(GUIConstants.XAxis_LEVEL_1_1, GUIConstants.YAxis_LEVEL_4 + GUIConstants.labelSizeHeight + 1, GUIConstants.XAxis_LEVEL_9, GUIConstants.tableSizeHeight);
        tableParti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tableParti.setEnabled(true);
        jMainPanel.add(pane);
        container.add(jMainPanel);

        this.setLayout(null);
        this.getContentPane().add(jMainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(screenWidth, screenHeight);
        this.setVisible(true);

        initialiseActionListeners();
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
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
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
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
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
                jComboBoxFromTo.hidePopup();
                jComboBoxParti.hidePopup();
            }
        });

        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new CopyPasteOperation(jTextFieldReceiptNo));

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
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
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
                // throw new UnsupportedOperationException("Not supported yet.");
                tfDatePicker.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        jComboBoxFromTo.setEditable(true);
        jComboBoxFromTo.addItem("From");
        jComboBoxFromTo.addItem("To");

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
                jTextFieldCheckNoKeyPressed(evt);
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
                jTextFieldNoteKeyPressed(evt);
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
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAmountFocusLost(evt);
            }
        });

        jTextFieldAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldAmountKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                try {
                    Util.filterCharacter(evt, jTextFieldAmount);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
            }
        });

        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
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
                tablePartiKeyPressed(evt);
            }
        });

        jTableTransactionList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransactionListMouseClicked(evt);
            }

            public void mousePressed(MouseEvent me) {
                showPopup(me);
            }

            public void mouseReleased(MouseEvent me) {
                showPopup(me);
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
                    Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        });

        buttonAddLedger.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddLedgerActionPerformed(evt);
            }
        });


        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });

        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonSubmitActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
                }
            }
        });

        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonDeleteActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
                }
            }
        });

        buttonParticularDelete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonParticularDeleteActionPerformed(evt);
            }
        });

        buttonNew.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonNewActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(JournalForm.this, ex.getMessage());
                }
            }
        });

        buttonExport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportActionPerformed(evt);
            }
        });

    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException, Exception {

        if (this.ledgerTimeStamp != Constants.LEDGER_TIME_STAMP) {
            initLedger();
        }
        setFocus(null);

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
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

    private void jTextFieldCheckNoFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 4;
        jTextFieldCheckNo.selectAll();
    }

    private void jTextFieldCheckNoFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextFieldCheckNoKeyPressed(java.awt.event.KeyEvent evt) {
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
        Util.checkForEmpty(jTextFieldNote);
    }

    private void jTextFieldNoteKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void jTextFieldReceiptNoFocusLost(java.awt.event.FocusEvent evt) throws Exception {
        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
	    if (jTextFieldReceiptNo.getText().toString().trim().length() < 8) {
            if (AccountingVoucherHelper.checkAccountVoucherNumberAvailabilty(Integer.valueOf(jTextFieldReceiptNo.getText().trim()), Constants.RECEIPT_TYPE_INDEX)) {
                jTextFieldReceiptNo.setText("");
            }
	    } else {
		jTextFieldReceiptNo.requestFocus();
		throw new FieldValidationException(Label.INVALID_FIELD_EXCEPTION);
	    }
        }
    }

    private void jTextFieldAmountFocusGained(java.awt.event.FocusEvent evt) throws Exception {
        // TODO add your handling code here:
        currentFocusValue = 6;
        Util.checkForZero(jTextFieldAmount);
        jTextFieldAmount.selectAll();
    }

    private void jTextFieldAmountFocusLost(java.awt.event.FocusEvent evt) {
        Util.checkForEmpty(jTextFieldAmount);
    }

    private void jTextFieldAmountKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                partiAdd();
                currentFocusValue = 2;
            }
            setFocus(evt);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            tableParti.requestFocus();
        }
    }

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        partiAdd();
    }

    private void partiAdd() {
        if (validateStockTransaction()) {
            addTOJTable();
            clearFormData();
            Util.checkForEmpty(jTextFieldAmount);
            calculateAndBindTotalTOGUI();
        }
    }

    private void partiDelete() {
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
                    tfPartiText.requestFocus();
                }
            } else {
                j = indexForDeletion + 1;
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

    private void buttonAddKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jTextFieldAmount.requestFocus();
        }
    }

    private void tablePartiMouseClicked(java.awt.event.MouseEvent evt) {
        if (tableParti.getRowCount() > 0) {
            index = tableParti.getSelectedRow();
            if (index > -1) {
                Object o = tableParti.getValueAt(index, 1);
                if (o != null) {
                    temp = tableParti.getSelectedRow();
                    tfPartiText.setText(tableParti.getValueAt(temp, 1).toString());
                    tfAccountText.setText(tableParti.getValueAt(temp, 0).toString());
                    jTextFieldAmount.setText(tableParti.getValueAt(temp, 3).toString());
                    buttonAdd.setLabel(Label.BUTTON_ALTER);
                    jComboBoxParti.requestFocus();
                }
                if (tableParti.isRowSelected(0)) {
                    jTextFieldAdjustmentAmount.setText(tableParti.getValueAt(0, 2).toString());
                    buttonAdd.setLabel(Label.BUTTON_ALTER);
                }
            }
        }
    }

    private void tablePartiKeyPressed(java.awt.event.KeyEvent evt) {
        int flag = 0;
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
            clearFormData();
            buttonAdd.setLabel(Label.BUTTON_ADD);
        }
    }

    private void jTableTransactionListMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            String id = jTableTransactionList.getValueAt(jTableTransactionList.getSelectedRow(), 0).toString();
            loadEditForm(id);
        } catch (ParseException ex) {
            Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) throws SQLException {

        List<JournalDTO> receiptList = new ArrayList<JournalDTO>();
        String text = jTextFieldSearch.getText().trim();

        for (JournalDTO receiptDTOEntity : journalDTOTransactionList) {
            if (receiptDTOEntity.getAccountName().toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
                receiptList.add(receiptDTOEntity);
            }
        }
        bindDTOToTransactionTable(receiptList);

    }

    private void buttonAddLedgerActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        Ledger_Create ledgerCreate = new Ledger_Create(Label.CREATE_NEW_LEDGER);
        ledgerCreate.setVisible(true);
        ledgerCreate.pack();
        ledgerCreate.setVisible(true);
        this.getParent().add(ledgerCreate);
        this.getParent().setVisible(true);
        Dimension desktopSize = this.getParent().getSize();
        Dimension jInternalFrameSize = ledgerCreate.getSize();
        ledgerCreate.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        try {
            ledgerCreate.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }

        BasicInternalFrameUI uiInternalFrame = (BasicInternalFrameUI) ledgerCreate.getUI();
        Component north = uiInternalFrame.getNorthPane();
        MouseMotionListener[] actions =
                (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int index = 0; index < actions.length; index++) {
            north.removeMouseMotionListener(actions[index]);
        }
    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            MainClass.setstaticvar();
            MainClass m = new MainClass();
            m.menuselection(4);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            if (validateData()) {
                submit();
            }

        } catch (SQLException ex) {
            Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        JournalDAO.deleteTransaction(Long.parseLong(journalDTO.getTrans_ID()));
        JOptionPane.showMessageDialog(this, "Transaction deleted completly");
        newButton();

    }

    private void buttonParticularDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        if (tableParti.isRowSelected(indexForDeletion)) {
            buttonParticularDelete.setVisible(true);
        }
        partiDelete();
    }

    private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        newButton();
        clearFormData();
        temp = 1000;
        buttonAdd.setText("ADD");
    }

    private void buttonExportActionPerformed(java.awt.event.ActionEvent evt) {

        dataExport();

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
    private JComboBox jComboBoxFromTo;
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
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JTextField jTextFieldAdjustmentAmount;
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

    private void setPartiModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxParti.setModel(mdl);
        jComboBoxParti.setSelectedIndex(-1);
        jComboBoxParti.showPopup();
        tfPartiText.setText(str);
    }

    private void initVariables() throws SQLException, Exception {
        initLedger();
        if (!isEdit) {
            journalDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.JOURNAL_TYPE_INDEX));
        }

        ledgerTimeStamp = Constants.LEDGER_TIME_STAMP;

    }

    private void initLedger() throws SQLException, Exception {
        List<String> groups = new ArrayList<String>();
        groups.add(Constants.CASH_IN_HAND);
        groups.add(Constants.CURRENT_ASSETS);
        groups.add(Constants.BANK_ACCOUNT);
        accountLedgerMap = LedgerDAO.getLedgerFromGroupName(groups, true);
        groups = new ArrayList<String>();
        groups.add(Constants.BANK_ACCOUNT);
        groups = new ArrayList<String>();
        groups.add(Constants.SUSPENSE_ACCOUNT);

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


        partiItemMap = LedgerDAO.getLedgerFromGroupName(groups, false);
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

    private void bindTOGUI() throws ParseException, SQLException {
        bindDTOtoGUI();
        bindDTOtoJtable();
        initTransactionList();
    }

    private void bindDTOtoGUI() throws ParseException {
        tfAccountText.setText(journalDTO.getAccountName());
        jTextFieldReceiptNo.setText(journalDTO.getReceiptNo().toString());
        labelFinalAmountTotal.setText(Constants.DECIMAL_FORMAT.format(journalDTO.getFinalAmount()) + "");
        if (!isEdit) {
            jDateChooser.setDate((java.util.Date) Constants.DATE_FORMATER.parse(journalDTO.getDate().trim()));
        } else {
            jDateChooser.setDate((java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(journalDTO.getDate().trim()));
        }

    }

    private void bindDTOtoJtable() {
        int index = 0;
        List<LedgerTransactionDTO> ledgerTransactionDTOList = journalDTO.getLedgerTransactionDTOList();
        if (ledgerTransactionDTOList != null && ledgerTransactionDTOList.size() > 0) {
            index = partiTableModel.getRowCount();
            partiTableModel.setRowCount(index + 1);
            partiTableModel.setValueAt(journalDTO.getFinalAmount(), index, 2);
            for (LedgerTransactionDTO ledgerTransactionDTO : ledgerTransactionDTOList) {

                if (index == 0) {
                    index = partiTableModel.getRowCount();
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(ledgerTransactionDTO.getName(), index, 1);
                    partiTableModel.setValueAt(journalDTO.getAccountName(), index, 0);
                    partiTableModel.setValueAt(ledgerTransactionDTO.getAmount(), index, 3);
                } else {
                    partiTableModel.setRowCount(partiTableModel.getRowCount() + 1);
                    partiTableModel.setValueAt(ledgerTransactionDTO.getName(), index, 1);
                    partiTableModel.setValueAt(journalDTO.getAccountName(), index, 0);
                    partiTableModel.setValueAt("", index, 2);
                    partiTableModel.setValueAt(ledgerTransactionDTO.getAmount(), index, 3);
                }
                index++;
            }
        }

    }

    private void bindDTOtoGUIWithoutReceiptNo() throws ParseException {
        tfAccountText.setText(journalDTO.getAccountName());
        tfDatePicker.setText(new JournalDTO().getDate());
    }

    private void submit() throws SQLException, ParseException, Exception {
        journalDTO = bindGUITODTO();
        journalDTO = ImpExpUtil.valiDateAndRepairJournalDTO(journalDTO);
        journalDTO.setAccountName(accountLedgerMap.get(journalDTO.getAccountName().trim().toLowerCase()));
        for (LedgerTransactionDTO stockItemTransactionDTO : journalDTO.getLedgerTransactionDTOList()) {
            stockItemTransactionDTO.setName(partiItemMap.get(stockItemTransactionDTO.getName().toLowerCase().trim()));
        }

        if (!isEdit) {
            insertReceipt();

        } else {
            updateReceipt();
        }
        JOptionPane.showMessageDialog(jMainPanel, Label.RECORD_SUBMITTED_SUCCESSFULLY);
        newButton();
    }

    private JournalDTO bindGUITODTO() {
        JournalDTO journalDTOEntity = new JournalDTO();
        journalDTOEntity.setTrans_ID(journalDTO.getTrans_ID());

        if (!jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("")) {
            journalDTOEntity.setReceiptNo(Integer.parseInt(jTextFieldReceiptNo.getText()));
        }
        if (!jDateChooser.getDate().toString().trim().equalsIgnoreCase("")) {
            journalDTOEntity.setDate(Constants.simpleDateFormatDatabase.format(jDateChooser.getDate()).toString().trim());
        }
        if (!tfAccountText.getText().trim().equalsIgnoreCase("")) {
            journalDTOEntity.setAccountName(tfAccountText.getText().trim());
        }
        if (!jTextFieldAdjustmentAmount.getText().trim().equalsIgnoreCase("")) {
            journalDTOEntity.setFinalAmount(Double.parseDouble(jTextFieldAdjustmentAmount.getText().trim()));
        }
        journalDTOEntity.setLedgerTransactionDTOList(bindtableToDTO());

        bindtableToDTO();

        //journalDTOEntity.setLedgerTransactionDTOList(bindtableToDTO());

        return journalDTOEntity;
    }

    private List<LedgerTransactionDTO> bindtableToDTO() {
//        List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
//        for (int index = 1; index < tableParti.getRowCount(); index++) {
//
//            LedgerTransactionDTO stockItemDTO = new LedgerTransactionDTO();
//            stockItemDTO.setDebit(Double.parseDouble(tableParti.getValueAt(0, 2).toString()));
//            stockItemDTO.setName(accountLedgerMap.get(tableParti.getValueAt(index, 1).toString()));
//            //stockItemDTO.setToName(partiItemMap.get(tableParti.getValueAt(index, 0).toString()));
//            stockItemDTO.setCredit(Double.parseDouble(tableParti.getValueAt(index, 3).toString()));
//            stockItemTransactionDTOList.add(stockItemDTO);
//
//        }
//        return stockItemTransactionDTOList;

        List<LedgerTransactionDTO> stockItemTransactionDTOList = new ArrayList<LedgerTransactionDTO>();
        for (int index = 1; index < tableParti.getRowCount(); index++) {

            LedgerTransactionDTO stockItemDTO = new LedgerTransactionDTO();
            stockItemDTO.setName(tableParti.getValueAt(index, 1).toString().trim());
            stockItemDTO.setAmount(Double.parseDouble(tableParti.getValueAt(index, 3).toString()));
            stockItemDTO.setDebit(Double.parseDouble(tableParti.getValueAt(0, 2).toString()));
            stockItemTransactionDTOList.add(stockItemDTO);
        }
        return stockItemTransactionDTOList;

    }

    private void insertReceipt() throws SQLException, ParseException, Exception {
        List<JournalDTO> journalDTOList = new ArrayList<JournalDTO>();
        journalDTOList.add(journalDTO);
        JournalDAO.insertReceiptVoucher(journalDTOList);
        AccountingVoucherHelper.updateVoucherNumber(Constants.JOURNAL_TYPE_INDEX, (journalDTO.getReceiptNo()));
    }

    private void updateReceipt() throws SQLException, ParseException {
        List<JournalDTO> journalDTOList = new ArrayList<JournalDTO>();
        journalDTOList.add(journalDTO);
        JournalDAO.updateReceiptVoucher(journalDTOList);
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

    private Boolean validateData() {
        Boolean flag = true;
        if (jTextFieldReceiptNo.getText().trim().equalsIgnoreCase("") || Double.parseDouble(jTextFieldReceiptNo.getText().trim()) == 0D) {
            JOptionPane.showMessageDialog(jMainPanel, Label.RECEIPT_NUMBER_VALUE_IS_NOT_VALID);
            jTextFieldReceiptNo.requestFocus();
            flag = false;
        } //        else if (accountLedgerMap.get(tfAccountText.getText().trim().toLowerCase()) == null) {
        //            JOptionPane.showMessageDialog(jMainPanel, Label.CASH_LEDGER_VALUE_IS_NOT_VALID);
        //            tfAccountText.requestFocus();
        //            flag = false;
        //        } 
        else if (jDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(jMainPanel, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker.requestFocus();
            flag = false;
        }
//        else if (tableParti.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_EMPTY);
//            flag = false;
//            tfPartiText.requestFocus();
//        }
        return flag;
    }

    private void calculateAndBindTotalTOGUI() {
        int index = 1;
        if (tableParti.getRowCount() >= 2) {
            double amount;
            double amountAdjustment;
            amount = Double.parseDouble(tableParti.getValueAt(index, 3).toString());
            amountAdjustment = Double.parseDouble(tableParti.getValueAt(0, 2).toString());
            double finalAmount = amountAdjustment - amount;
            tableParti.setValueAt(finalAmount, 0, 2);
            index++;
        }
    }

    private void addTOJTable() {

        index = 0;
        while (index < tableParti.getRowCount()) {
            if (tableParti.getValueAt(index, 0) == null) {
                int row = tableParti.getRowCount();
                //partiTableModel.setRowCount(row - 1);
            }
            index++;
        }

        if (temp == 1000) {
            int row = tableParti.getRowCount();
            if (tableParti.getRowCount() == 0) {
                partiTableModel.setRowCount(row + 1);
                tableParti.setValueAt("From", row, 0);
                tableParti.setValueAt("To", row, 1);
                tableParti.setValueAt(jTextFieldAdjustmentAmount.getText().trim(), row, 2);
            } else {
                if (jComboBoxFromTo.getSelectedItem().toString().equalsIgnoreCase("From")) {
                    row = tableParti.getRowCount() + 1;
                    partiTableModel.setRowCount(row);
                    tableParti.setValueAt(tfPartiText.getText().trim(), row - 1, 0);
                    flag = true;
                }
                if (jComboBoxFromTo.getSelectedItem().toString().equalsIgnoreCase("To")) {

                    if (flag) {
                        row = tableParti.getRowCount();
                        partiTableModel.setRowCount(row);
                        tableParti.setValueAt(tfPartiText.getText().trim(), row - 1, 1);
                        tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row - 1, 3);
                        flag = false;
                    } else {
                        row = tableParti.getRowCount() + 1;
                        partiTableModel.setRowCount(row);
                        tableParti.setValueAt(tfPartiText.getText().trim(), row - 1, 1);
                        tableParti.setValueAt(Constants.DECIMAL_FORMAT.format(Double.parseDouble(jTextFieldAmount.getText().trim())), row - 1, 3);
                    }
                }
            }
        } else {
            row = 1;
            tableParti.setValueAt(jTextFieldAdjustmentAmount.getText().trim(), 0, 2);
            tableParti.setValueAt(tfPartiText.getText().trim(), row, 1);
            tableParti.setValueAt(tfAccountText.getText().trim(), row, 0);
            tableParti.setValueAt(jTextFieldAmount.getText().trim(), row, 3);
            row++;
            buttonAdd.setLabel(Label.BUTTON_ADD);
        }
    }

    public void loadEditForm(String id) throws SQLException, ParseException {
        Set<String> idSet = new HashSet<String>();
        idSet.add(id);
        List<JournalDTO> journalDTOList = JournalDAO.getReceipts(idSet);
        if (journalDTOList != null && !journalDTOList.isEmpty()) {
            journalDTO = journalDTOList.get(0);

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

    private Boolean validateStockTransaction() {
//        if (partiItemMap.get(tfPartiText.getText().trim().toLowerCase()) == null) {
//            JOptionPane.showMessageDialog(jMainPanel, Label.PARTICULARS_VALUE_IS_NOT_VALID);
//            tfPartiText.requestFocus();
//            return false;
//        }
//        if ((jTextFieldAmount.getText().trim().equalsIgnoreCase("")) || (Double.parseDouble(jTextFieldAmount.getText().trim()) == 0D)) {
//            JOptionPane.showMessageDialog(jMainPanel, Label.AMOUNT_NOT_VALID);
//            return false;
//        }
        return true;
    }

    private void bindDTOToTransactionTable(List<JournalDTO> journalDTOTransactionList) throws SQLException {
        if (journalDTOTransactionList != null && !journalDTOTransactionList.isEmpty()) {
            transactionTableModel1.setRowCount(0);
            for (JournalDTO journalDTOEntity : journalDTOTransactionList) {
                try {
                    transactionTableModel1.setRowCount(transactionTableModel1.getRowCount() + 1);
                    transactionTableModel1.setValueAt(journalDTOEntity.getReceiptNo(), transactionTableModel1.getRowCount() - 1, 0);
                    java.util.Date date = (java.util.Date) Constants.simpleDateFormatDatabaseWithDash.parse(journalDTOEntity.getDate().trim());
                    transactionTableModel1.setValueAt(Constants.DATE_FORMATER.format(date), transactionTableModel1.getRowCount() - 1, 1);
                    transactionTableModel1.setValueAt(journalDTOEntity.getAccountName(), transactionTableModel1.getRowCount() - 1, 2);


                } catch (ParseException ex) {
                    Logger.getLogger(JournalForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
             transactionTableModel1.setRowCount(0);
        }
    }

    private void newButton() throws ParseException, SQLException, Exception {
        jTextFieldReceiptNo.setEnabled(true);
        journalDTO = new JournalDTO();
        isEdit = false;
        journalDTO.setReceiptNo(AccountingVoucherHelper.getNextAccountVoucherNumber(Constants.JOURNAL_TYPE_INDEX));
        initTransactionList();
        bindTOGUI();
        initComponentActiveInActive();
        partiTableModel.setRowCount(0);
    }

    private void clearFormData() {
        tfPartiText.setText("");
        jTextFieldCheckNo.setText("");
        jTextFieldNote.setText("");
        jTextFieldAmount.setText("");


    }
    //To Do Code to Export 
    String path = "";

    public class MessageBox {

        BufferedImage mImage;
        String name, name1;

        public MessageBox() {
            String source = filechoose();
            File inputFile = new File(source);
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

            int r = chooser.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                name1 = chooser.getSelectedFile().getAbsolutePath();
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
            return name1;
        }
    }

    private void dataExport() {

        MessageBox msgBox = new MessageBox();
        Set<String> receiptSet = new HashSet<String>();
        receiptSet.add(jTextFieldReceiptNo.getText().trim());
        BufferedWriter out = null;
        try {
            // TODO add your handling code here:
            Map<String, Set<String>> IDMapSet = new HashMap<String, Set<String>>();
            IDMapSet.put("Receipt", receiptSet);
            String xmlCode = TagsHelper1.exportDayBook(IDMapSet);
            System.out.println("------------------------->>XMLCODE:" + xmlCode);

            File file = new File(path + ".xml");
            path = file.getPath();
            out = new BufferedWriter(new FileWriter(file));
            out.write(xmlCode);
            out.close();

            JOptionPane.showMessageDialog(this, "Export Successful");



        } catch (Exception ex) {
            Logger.getLogger(DayBook.class
                    .getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(
                    this, "Export failure");
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
                try {
                    Set<String> idSet = new HashSet<String>();
                    idSet.add(id);
                    List<JournalDTO> journalDTOList = JournalDAO.getReceipts(idSet);
                    if (journalDTOList != null && !journalDTOList.isEmpty()) {
                        try {
                            journalDTO = journalDTOList.get(0);
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
                            Logger.getLogger(SaleForm.class
                                    .getName()).log(Level.SEVERE, null, ex);
                        }


                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SaleForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                }



            }
        } catch (Exception ex) {
            Logger.getLogger(JournalForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTransactionList() throws SQLException {
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
        journalDTOTransactionList = JournalDAO.getTransactionList();
        bindDTOToTransactionTable(journalDTOTransactionList);
//                        } catch (SQLException ex) {
//                            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
//                            ex.printStackTrace();
//                        }
//                    }
//                }).start();
    }
}
