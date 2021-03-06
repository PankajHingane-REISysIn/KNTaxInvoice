package gen.display.report.StockItem;

import com.toedter.calendar.JDateChooser;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.grnote.GRNoteDTO;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.payment.PaymentForm;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.receipt.ReceiptForm;
import gen.accountvoucher.sale.SaleDTO;
import gen.display.report.Ledger.TransactionsDTO;
import gen.display.report.TrialBalanceDTO;
import gen.dto.Constants;
import gen.dto.Label;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
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
public class StockItemReportForm1 extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private String fromDateStr = "";
    private String toDateStr = "";
    Double totalDebit = 0d;
    Double totalCredit = 0d;
    public static int Check_Print_Function = 0;
    //variable for focus
    private int currentFocusValue = 0;
    //vextor for comboStockItem
    private Vector<String> stockItemVector = new Vector<String>();
    //variables For Calculation
    private Map<String, String> mapStockItem;
    // Date Preserved
    private String fromDate_Preserve = "";
    private String toDate_Preserve = "";

    public StockItemReportForm1(String s) {
        try {
            initComponents();
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
        }

    }

    private void initComponents() {

        setTitle("Stock Item Report");
        setBounds(100, 100, 1322, 674);

        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new MigLayout("", "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        lblDaybook = new JLabel("Stock Item Report");
        lblDaybook.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblDaybook.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblDaybook, "cell 1 0");

        panel_3 = new JPanel();
        panel.add(panel_3, "cell 1 1,grow");
        panel_3.setLayout(new MigLayout("", "[0px:340px:340px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][0px:250px:250px,grow,shrink 50,fill][0px:200px:200px,grow,shrink 50,fill][0px:340px:340px,grow,shrink 50,fill]", "[]"));

        lblName = new JLabel("Name");
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        panel_3.add(lblName, "cell 1 0,alignx trailing");

        jComboBoxStockItem = new JComboBox();
        jComboBoxStockItem.setPrototypeDisplayValue("xxxxxx");
        jComboBoxStockItem.setEditable(true);
        panel_3.add(jComboBoxStockItem, "cell 2 0,growx");

        fromJDateChooser = new JDateChooser();
        panel.add(fromJDateChooser, "flowx,cell 1 3");

        lblTo = new JLabel("To");
        lblTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(lblTo, "cell 1 3");

        toJDateChooser = new JDateChooser();
        panel.add(toJDateChooser, "cell 1 3");

        buttonShow = new JButton("Show");
        panel.add(buttonShow, "cell 1 3");

        buttonPrint = new JButton("Print");
        panel.add(buttonPrint, "cell 1 3");

        buttonBack = new JButton("Back");
        panel.add(buttonBack, "cell 1 3");

        btnNewButton_3 = new JButton("Advance Daybook");
        btnNewButton_3.setEnabled(false);
        panel.add(btnNewButton_3, "cell 1 3");

        btnNewButton_4 = new JButton("Export");
        btnNewButton_4.setEnabled(false);
        panel.add(btnNewButton_4, "cell 1 3");

        panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.add(panel_1, "cell 1 4 1 18,grow");
        panel_1.setLayout(new MigLayout("", "[0px:1317px:1317px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

        panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.add(panel_2, "cell 0 1,grow");
        panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JScrollPane scrollPane = new JScrollPane();
        panel_2.add(scrollPane, "cell 0 0,grow");

        String col1[] = {"Sr.No", "Particulars", "Date", "VCH Type", "VCH No", "Inward", "Outward"};
        String data1[][] = {{"", "", "", "", "", "", "", ""}};

        transactionTableModelDebit = new DefaultTableModel(data1, col1);
        jTableDebit = new JTable() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
        jTableDebit.setModel(transactionTableModelDebit);
        final JTableHeader header1 = jTableDebit.getTableHeader();
        jTableDebit.getTableHeader().setReorderingAllowed(false);
        header1.setBackground(Color.yellow);
        header1.setFont(font);
        jTableDebit.setFont(font);
        transactionTableModelDebit = (DefaultTableModel) jTableDebit.getModel();
        transactionTableModelDebit.setRowCount(0);
        transactionTableModelDebit.setColumnCount(7);
        jTableDebit.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        jTableDebit.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTableDebit.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        jTableDebit.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

        scrollPane.setViewportView(jTableDebit);

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
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
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
                    JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
                System.out.println("Frame Closed.......................");
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
                fromDate_Preserve = tfDatePicker1.getText().toString().trim();
                toDate_Preserve = tfDatePicker2.getText().toString().trim();
            }
        });

        jComboBoxStockItem.setEditable(true);
        tfStockItemText = (JTextField) jComboBoxStockItem.getEditor().getEditorComponent();
        tfStockItemText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = tfStockItemText.getText();
                        if (text.length() == 0) {
                            jComboBoxStockItem.hidePopup();
                            setAccountLedgetModel(new DefaultComboBoxModel(stockItemVector), "");
                        } else {
                            DefaultComboBoxModel m = Util.getSuggestedModel(stockItemVector, text);
                            if (m.getSize() == 0 || hide_flag) {
                                jComboBoxStockItem.hidePopup();
                                hide_flag = false;
                            } else {
                                setAccountLedgetModel(m, text);
                                jComboBoxStockItem.showPopup();
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
                        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                            setFocus(event);
                        } else if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            try {
                                buttonBackActionPerformed(null);
                            } catch (Exception ex) {
                                Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
                            }
                        }
                    }
                });
            }
        });

        tfStockItemText = (JTextField) jComboBoxStockItem.getEditor().getEditorComponent();
        tfStockItemText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                currentFocusValue = 0;
                String text = tfStockItemText.getText();
                if (text.length() == 0) {
                    jComboBoxStockItem.hidePopup();
                    setAccountLedgetModel(new DefaultComboBoxModel(stockItemVector), "");
                } else {
                    DefaultComboBoxModel m = Util.getSuggestedModel(stockItemVector, text);
                    if (m.getSize() == 0 || hide_flag) {
                        jComboBoxStockItem.hidePopup();
                        hide_flag = false;
                    } else {
                        setAccountLedgetModel(m, text);
                        jComboBoxStockItem.showPopup();
                    }
                }
                tfStockItemText.selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }
        });


        fromJDateChooser.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker1 = (JTextField) fromJDateChooser.getComponent(1);

        tfDatePicker1.addKeyListener(new KeyAdapter() {
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

        toJDateChooser.setDateFormatString(Constants.DATE_FORMAT_STRING);
        tfDatePicker2 = (JTextField) toJDateChooser.getComponent(1);

        tfDatePicker2.addKeyListener(new KeyAdapter() {
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


        tfDatePicker1.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                        tfDatePicker1.selectAll();
                        currentFocusValue = 1;
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }
                });

        tfDatePicker2.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                        tfDatePicker2.selectAll();
                        currentFocusValue = 2;
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // throw new UnsupportedOperationException("Not supported yet.");
                    }
                });

        buttonShow.addActionListener(
                new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        try {
                            buttonShowActionPerformed(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
                        }
                    }
                });

        buttonShow.addKeyListener(
                new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        try {
                            buttonShowKeyPressed(evt);
                        } catch (Exception ex) {
                            Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
                        }
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
                    JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
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
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonPrint.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttonPrint.requestFocus();
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
                    buttonShowActionPerformed(evt);
                    printfunction();
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
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
                    Check_Print_Function = 1;
                    if (Check_Print_Function == 1) {
                        //buttonPrintActionPerformed(null);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonShow.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonBack.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    buttonShow.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        jTableDebit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTableDebitListMouseClicked(evt);
                } catch (Exception ex) {
                    Logger.getLogger(PurchaseForm.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(StockItemReportForm1.this, ex.getMessage());
                }
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }
        });


    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException, Exception {

        buttonBack.setMnemonic(KeyEvent.VK_B);
        buttonShow.setMnemonic(KeyEvent.VK_S);
        buttonPrint.setMnemonic(KeyEvent.VK_P);


        System.out.println("toDate_Preserve ---------------------" + toDate_Preserve);
        if (toDate_Preserve.isEmpty()) {

            tfDatePicker1.setText(new TrialBalanceDTO().getDate());
            tfDatePicker2.setText(new TrialBalanceDTO().getDate());
        } else {
            tfDatePicker1.setText(fromDate_Preserve);
            tfDatePicker2.setText(toDate_Preserve);
            buttonShowActionPerformed(null);

        }
        fromDate_Preserve = "";
        toDate_Preserve = "";


        setFocus(null);

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
        MainClass.setstaticvar();
    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:

        MainClass.setstaticvar();
        MainClass m = new MainClass();
        m.menuselection(3);
        this.setClosed(true);

    }

    private void jTableDebitListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
        getDesktopPane().setLayout(new CardLayout());
        int row = jTableDebit.getSelectedRow();
        if (jTableDebit.getValueAt(row, 2) != null) {
            if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("Receipt")) {
                String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                ReceiptDTO receiptDTO = new ReceiptDTO();
                ReceiptForm r = new ReceiptForm(receiptDTO, true);
                r.loadEditForm(id);
                r.pack();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(r);
                Dimension desktopSize = getDesktopPane().getSize();
                r.setSize(desktopSize);
                r.setPreferredSize(desktopSize);
                r.setVisible(!r.isVisible());
                r.setSelected(true);
                BasicInternalFrameUI ui = (BasicInternalFrameUI) r.getUI();

                Component north = ui.getNorthPane();
                MouseMotionListener[] actions =
                        (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                for (int i = 0; i < actions.length; i++) {
                    north.removeMouseMotionListener(actions[i]);
                }
            } else if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("Payment")) {
                String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                PaymentDTO paymentDTO = new PaymentDTO();
                PaymentForm p = new PaymentForm(paymentDTO, true);
                p.loadEditForm(id);
                p.pack();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(p);
                Dimension desktopSize = getDesktopPane().getSize();
                p.setSize(desktopSize);
                p.setPreferredSize(desktopSize);
                p.setVisible(!p.isVisible());
                p.setSelected(true);
                BasicInternalFrameUI ui = (BasicInternalFrameUI) p.getUI();

                Component north = ui.getNorthPane();
                MouseMotionListener[] actions =
                        (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                for (int i = 0; i < actions.length; i++) {
                    north.removeMouseMotionListener(actions[i]);
                }
            } else if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("Sales")) {
                String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                SaleDTO saleDTO = new SaleDTO();
                gen.accountvoucher.sale.SaleForm s = new gen.accountvoucher.sale.SaleForm("Sales", new Dimension(), saleDTO, true);
                s.loadEditForm(id);
                s.pack();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(s);
                Dimension desktopSize = getDesktopPane().getSize();
                s.setSize(desktopSize);
                s.setPreferredSize(desktopSize);
                s.setVisible(!s.isVisible());
                s.setSelected(true);
                BasicInternalFrameUI ui = (BasicInternalFrameUI) s.getUI();

                Component north = ui.getNorthPane();
                MouseMotionListener[] actions =
                        (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                for (int i = 0; i < actions.length; i++) {
                    north.removeMouseMotionListener(actions[i]);
                }
            } else if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("Purchase")) {
                String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                PurchaseDTO purchaseDTO = new PurchaseDTO();
                PurchaseForm p = new PurchaseForm(purchaseDTO, true);
                p.loadEditForm(id);
                p.pack();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(p);
                Dimension desktopSize = getDesktopPane().getSize();
                p.setSize(desktopSize);
                p.setPreferredSize(desktopSize);
                p.setVisible(!p.isVisible());
                p.setSelected(true);
                BasicInternalFrameUI ui = (BasicInternalFrameUI) p.getUI();

                Component north = ui.getNorthPane();
                MouseMotionListener[] actions =
                        (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                for (int i = 0; i < actions.length; i++) {
                    north.removeMouseMotionListener(actions[i]);
                }
            } else if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("Chalan")) {
                String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                ChalanDTO chalanDTO = new ChalanDTO();
                gen.accountvoucher.chalan.ChalanForm1 s = new gen.accountvoucher.chalan.ChalanForm1(chalanDTO, true);
                s.loadEditForm(id);
                s.pack();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(s);
                Dimension desktopSize = getDesktopPane().getSize();
                s.setSize(desktopSize);
                s.setPreferredSize(desktopSize);
                s.setVisible(!s.isVisible());
                s.setSelected(true);
                BasicInternalFrameUI ui = (BasicInternalFrameUI) s.getUI();

                Component north = ui.getNorthPane();
                MouseMotionListener[] actions =
                        (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                for (int i = 0; i < actions.length; i++) {
                    north.removeMouseMotionListener(actions[i]);
                }
            } else if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("GRNote")) {
                String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                GRNoteDTO gRNoteDTO = new GRNoteDTO();
                gen.accountvoucher.grnote.GRNoteForm s = new gen.accountvoucher.grnote.GRNoteForm(gRNoteDTO, true);
                s.loadEditForm(id);
                s.pack();
                JDesktopPane desktopPane = getDesktopPane();
                desktopPane.add(s);
                Dimension desktopSize = getDesktopPane().getSize();
                s.setSize(desktopSize);
                s.setPreferredSize(desktopSize);
                s.setVisible(!s.isVisible());
                s.setSelected(true);
                BasicInternalFrameUI ui = (BasicInternalFrameUI) s.getUI();

                Component north = ui.getNorthPane();
                MouseMotionListener[] actions =
                        (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

                for (int i = 0; i < actions.length; i++) {
                    north.removeMouseMotionListener(actions[i]);
                }
            }
        }




    }

    private void buttonShowKeyPressed(java.awt.event.KeyEvent evt) throws Exception {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buttonShowActionPerformed(null);
        }
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            tfDatePicker2.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            buttonPrint.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            tfDatePicker2.requestFocus();
        }

    }

    private void buttonShowActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        if (validateData()) {
            transactionTableModelDebit.setRowCount(0);

            String toDateCloseStr = "";
            String fromDateOpenStr = "";

            if (fromJDateChooser.getDate() != null) {
                fromDateStr = Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate());
                Calendar c = Calendar.getInstance();
                c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate())));
                c.add(Calendar.DATE, -1);  // number of days to add
                fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());
            }

            if (toJDateChooser.getDate() != null) {
                toDateStr = Constants.simpleDateFormatDatabaseWithDash.format(toJDateChooser.getDate());
                Calendar c = Calendar.getInstance();
                c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(toJDateChooser.getDate())));
                c.add(Calendar.DATE, -1);  // number of days to add
                toDateCloseStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());
            }

            bindToGUI();
            initilize();

        }
    }
    // GUI fields declaration
    private Font font;
    private JButton buttonShow;
    private JButton buttonPrint;
    private JButton buttonBack;
    private com.toedter.calendar.JDateChooser fromJDateChooser;
    private com.toedter.calendar.JDateChooser toJDateChooser;
    private JTable jTableDebit;
    private DefaultTableModel transactionTableModelDebit;
    private JTextField tfDatePicker1 = null;
    private JTextField tfDatePicker2 = null;
    private JComboBox jComboBoxStockItem;
    private JTextField tfStockItemText;
    private JPanel panel;
    private JLabel lblDaybook;
    private JLabel lblName;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JLabel lblTo;
    private JPanel panel_1;
    private JPanel panel_2;
    private JPanel panel_3;
    private boolean hide_flag = false;

    /////////////////// private methods for functionality/////////////////////////////////
    private void initilize() throws Exception {
        initVariables();
    }

    private void initVariables() throws SQLException, Exception {
        initGroup();
    }

    private void initGroup() throws Exception {

        List<String> groups = new ArrayList<String>();
        mapStockItem = gen.account.stockitem.StockItemDAO.getStockItemWithIDList();

        stockItemVector.clear();
//	stockItemVector.add("");
        for (String str : mapStockItem.keySet()) {
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

        //mapStockItem = Util.getSmallCaseMap(mapStockItem);


    }

    private Boolean validateData() {
        Boolean flag = true;

        //if (!mapStockItem.containsKey(tfStockItemText.getText().toLowerCase().toString().trim())) {
        if (!mapStockItem.containsKey(tfStockItemText.getText().toString().trim())) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_STOCKITEM_NAME);
            tfStockItemText.requestFocus();
            jComboBoxStockItem.requestFocus();
            flag = false;
        } else if (fromJDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker1.requestFocus();
            flag = false;
        } else if (toJDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker2.requestFocus();
            flag = false;
        } else if (fromJDateChooser.getDate() == null && toJDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker1.requestFocus();
            flag = false;
        } else if (fromJDateChooser.getDate().compareTo(toJDateChooser.getDate()) > 0) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker1.requestFocus();
            flag = false;
        }

        if (flag == false) {
            transactionTableModelDebit.setRowCount(0);
        }

        return flag;
    }

    private void setAccountLedgetModel(DefaultComboBoxModel mdl, String str) {
        jComboBoxStockItem.setModel(mdl);
        jComboBoxStockItem.setSelectedIndex(-1);
        tfStockItemText.setText(str);
    }

    private void bindToGUI() throws SQLException, Exception {
        String ledger_Name = "";
        DecimalFormat decformat = new DecimalFormat("#.##");

        //ledger_Name = mapStockItem.get(tfStockItemText.getText().toLowerCase().toString().trim());
        ledger_Name = mapStockItem.get(tfStockItemText.getText().toString().trim());
        List<StockItemReportDTO> stockItemReportDTOList = StockItemReportDAO.getStockItemReport(fromDateStr, toDateStr, ledger_Name);

        transactionTableModelDebit = (DefaultTableModel) jTableDebit.getModel();

        for (StockItemReportDTO stockItemReportDTO : stockItemReportDTOList) {
            for (TransactionsDTO transactionsDTO : stockItemReportDTO.getTransactionsDTOList()) {
                int row = jTableDebit.getRowCount();
                transactionTableModelDebit.setRowCount(row + 1);

                jTableDebit.setValueAt(row + 1, row, 0);

                for (Map.Entry<String, String> e : mapStockItem.entrySet()) {
                    //if (transactionsDTO.getParticulars().toLowerCase().equals(e.getValue())) {
                    if (transactionsDTO.getParticulars().equals(e.getValue())) {
                        jTableDebit.setValueAt(e.getKey(), row, 1);
                    }
                }

                jTableDebit.setValueAt(transactionsDTO.getDate(), row, 2);
                jTableDebit.setValueAt(transactionsDTO.getVchtype(), row, 3);
                jTableDebit.setValueAt(transactionsDTO.getVchno(), row, 4);

                if (!transactionsDTO.getDebit().equals("")) {
                    jTableDebit.setValueAt(decformat.format(Double.parseDouble(transactionsDTO.getDebit())), row, 6);
                } else if (!transactionsDTO.getCredit().equals("")) {
                    jTableDebit.setValueAt(decformat.format(Double.parseDouble(transactionsDTO.getCredit())), row, 5);
                }
            }


            int row = jTableDebit.getRowCount();
            transactionTableModelDebit.setRowCount(row + 1);

            jTableDebit.setValueAt("Total", row, 4);

            jTableDebit.setValueAt(decformat.format(stockItemReportDTO.getTotal_debit()), row, 6);
            jTableDebit.setValueAt(decformat.format(stockItemReportDTO.getTotal_credit()), row, 5);

            int row1 = jTableDebit.getRowCount();
            transactionTableModelDebit.setRowCount(row1 + 1);
            jTableDebit.setValueAt("OP Bal", row1, 4);
            if (stockItemReportDTO.getOpening_balance_type().equals("Credit")) {
                jTableDebit.setValueAt(decformat.format(Math.abs(stockItemReportDTO.getOpening_balance())).toString(), row1, 5);
            } else {
                jTableDebit.setValueAt(decformat.format(Math.abs(stockItemReportDTO.getOpening_balance())).toString(), row1, 6);
            }

            int row2 = jTableDebit.getRowCount();
            transactionTableModelDebit.setRowCount(row2 + 1);
            jTableDebit.setValueAt("CL Bal", row2, 4);
            if (stockItemReportDTO.getClosing_balance_type().equals("Credit")) {
                jTableDebit.setValueAt(decformat.format(Math.abs(stockItemReportDTO.getClosing_balance())).toString(), row2, 5);
            } else {
                jTableDebit.setValueAt(decformat.format(Math.abs(stockItemReportDTO.getClosing_balance())).toString(), row2, 6);
            }

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
        if (currentFocusValue > 3) {
            currentFocusValue = 3;
        }

        if (currentFocusValue == 0) {
            tfStockItemText.requestFocus();
        } else if (currentFocusValue == 1) {
            tfDatePicker1.requestFocus();
        } else if (currentFocusValue == 2) {
            tfDatePicker2.requestFocus();
        } else if (currentFocusValue == 3) {
            buttonShow.requestFocus();
        }
    }

    private void setColumnWidth(JTable passedTable) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        //  passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(0).setMinWidth(40);
        passedTable.getColumnModel().getColumn(0).setMaxWidth(40);

        passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(1).setMinWidth(200);
        passedTable.getColumnModel().getColumn(1).setMaxWidth(200);


        passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(2).setMinWidth(60);
        passedTable.getColumnModel().getColumn(2).setMaxWidth(60);
        //passedTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);


        passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(3).setMinWidth(60);
        passedTable.getColumnModel().getColumn(3).setMaxWidth(60);
        //passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(4).setMinWidth(60);
        passedTable.getColumnModel().getColumn(4).setMaxWidth(60);


        passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(5).setMinWidth(200);
        passedTable.getColumnModel().getColumn(5).setMaxWidth(200);
        passedTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);


        passedTable.getColumnModel().getColumn(6).setPreferredWidth(0);
        passedTable.getColumnModel().getColumn(6).setMinWidth(200);
        passedTable.getColumnModel().getColumn(6).setMaxWidth(200);
        passedTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
    }

    public void printfunction() {
        //if (jTableDebit.getRowCount() > 3 && fromJDateChooser.getDate() != null && toJDateChooser.getDate() != null && fromJDateChooser.getDate().getTime() <= toJDateChooser.getDate().getTime() && ! !mapStockItem.containsKey(tfStockItemText.getText().toLowerCase().toString().trim())) {
        if (jTableDebit.getRowCount() > 3 && fromJDateChooser.getDate() != null && toJDateChooser.getDate() != null && fromJDateChooser.getDate().getTime() <= toJDateChooser.getDate().getTime() && ! !mapStockItem.containsKey(tfStockItemText.getText().toString().trim())) {

            Map parameter = new HashMap();
            parameter.put("Particulars", "Particulars");
            parameter.put("Sr.No", "Sr.No");
            parameter.put("VCH No", "VCH No");
            parameter.put("VCH Type", "VCH Type");
            parameter.put("Date", "Date");
            parameter.put("Debit", "Inward");
            parameter.put("Credit", "Outward");
            parameter.put("ledgerName", tfStockItemText.getText().toString().trim());
            SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
            parameter.put("dateFrom", tfDatePicker1.getText().toString().trim());
            parameter.put("dateTo", tfDatePicker2.getText().toString().trim());
            parameter.put("TotalHeader", "");
            parameter.put("OpeningBalanceHeader", "");
            parameter.put("ClosingBalanceHeader", "");

            PrintAllReport.printStockitemReport(parameter, new JRTableModelDataSource(jTableDebit.getModel()), jTableDebit.getRowCount());
        } else {
            JOptionPane.showMessageDialog(this, "No Data Available For Printing");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean validation_For_Print() {
        Boolean flag = true;
        //if (!mapStockItem.containsKey(tfStockItemText.getText().toLowerCase().toString().trim())) {
        if (!mapStockItem.containsKey(tfStockItemText.getText().toString().trim())) {
            JOptionPane.showMessageDialog(this, Label.ENTER_VALID_LEDGER_NAME);
            tfStockItemText.requestFocus();
            flag = false;
        }
        if (fromJDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker1.requestFocus();
            // fromJDateChooser.requestFocus();
            flag = false;
        } else if (toJDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker2.requestFocus();
            //toJDateChooser.requestFocus();
            flag = false;
        } else if (fromJDateChooser.getDate() == null && toJDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker1.requestFocus();
            fromJDateChooser.requestFocus();
            flag = false;
        } else if (fromJDateChooser.getDate().compareTo(toJDateChooser.getDate()) > 0) {
            JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
            tfDatePicker1.requestFocus();
            flag = false;
        } else if (jTableDebit.getRowCount() > 3) {
            JOptionPane.showMessageDialog(this, Label.NO_DATA_TO_PRINT);
            tfDatePicker1.requestFocus();
            flag = false;
        }
        return flag;
    }
}
