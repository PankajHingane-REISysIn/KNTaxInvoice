/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report.Ledger;

import com.toedter.calendar.JDateChooser;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.chalan.ChalanForm1;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.payment.PaymentForm;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.purchase.PurchaseForm;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.receipt.ReceiptForm;
import gen.accountvoucher.sale.SaleDTO;
import gen.accountvoucher.sale.SaleForm;
import gen.display.report.*;
import gen.dto.Constants;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.BorderLayout;
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
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

/**
 *
 * @author pc5
 */
public class LedgerReportForm1 extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private String fromDateStr = "";
    private String toDateStr = "";
    Double totalDebit = 0d;
    Double totalCredit = 0d;
    public static int Check_Print_Function = 0;
    //variable for focus
    private int currentFocusValue = 0;
    //vextor for comboStockItem
    private Vector<String> ledgerVector = new Vector<String>();
    //variables For Calculation
    private Map<String, String> mapLedger;
    // Date Preserved
    private String fromDate_Preserve = "";
    private String toDate_Preserve = "";

    //public LedgerReportForm1(String str, Dimension d) {
    public LedgerReportForm1(String str) {
	try {
	    initComponents();
	    initilize();
	    setClosable(true);
	} catch (Exception ex) {
	    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
	    ex.printStackTrace();
	    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
	}
    }

    private void initComponents() {

	setTitle("Ledger Report");
	setBounds(100, 100, 1322, 674);

	panel = new JPanel();
	getContentPane().add(panel, BorderLayout.CENTER);
	panel.setLayout(new MigLayout("", "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

	lblDaybook = new JLabel("Ledger Report");
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

	jComboBoxLedger = new JComboBox();
        jComboBoxLedger.setPrototypeDisplayValue("xxxxxx");
	jComboBoxLedger.setEditable(true);
	panel_3.add(jComboBoxLedger, "cell 2 0,growx");

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
	panel.add(panel_1, "cell 1 4 1 19,grow");
	panel_1.setLayout(new MigLayout("", "[0px:1317px:1317px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

	panel_2 = new JPanel();
	panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
	panel_1.add(panel_2, "cell 0 1,grow");
	panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

	JScrollPane pane1 = new JScrollPane();
	panel_2.add(pane1, "cell 0 0,grow");

	String col1[] = {"Sr.No", "Particulars", "Date", "VCH Type", "VCH No", "Debit", "Credit"};
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

	pane1.setViewportView(jTableDebit);

	initialiseActionListeners();

    }

    private void initialiseActionListeners() {

	setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
	addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
	    @Override
	    public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
		try {
		    System.out.println("Frame Activated Hurray.............Enjoy...............");
		    formInternalFrameActivated(evt);
		} catch (Exception ex) {
		    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
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
		    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
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
		fromDate_Preserve = tfDatePicker1.getText().toString().trim();
		toDate_Preserve = tfDatePicker2.getText().toString().trim();
	    }
	});


	jComboBoxLedger.setEditable(true);
	tfLedgerText = (JTextField) jComboBoxLedger.getEditor().getEditorComponent();
	tfLedgerText.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(final KeyEvent e) {
		EventQueue.invokeLater(new Runnable() {
		    @Override
		    public void run() {
			String text = tfLedgerText.getText();
			if (text.length() == 0) {
			    jComboBoxLedger.hidePopup();
			    setAccountLedgetModel(new DefaultComboBoxModel(ledgerVector), "");
			} else {
			    DefaultComboBoxModel m = Util.getSuggestedModel(ledgerVector, text);
			    if (m.getSize() == 0 || hide_flag) {
				jComboBoxLedger.hidePopup();
				hide_flag = false;
			    } else {
				setAccountLedgetModel(m, text);
				jComboBoxLedger.showPopup();
			    }
			}
		    }
		});

	    }
	});
	tfLedgerText.addKeyListener(new KeyAdapter() {
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
				Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
				JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
			    }
			}
		    }
		});
	    }
	});

	tfLedgerText = (JTextField) jComboBoxLedger.getEditor().getEditorComponent();
	tfLedgerText.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
		currentFocusValue = 0;
                System.out.println("Focus Grains       ");
		String text = tfLedgerText.getText();
		if (text.length() == 0) {
		    jComboBoxLedger.hidePopup();
		    setAccountLedgetModel(new DefaultComboBoxModel(ledgerVector), "");
		} else {
		    DefaultComboBoxModel m = Util.getSuggestedModel(ledgerVector, text);
		    if (m.getSize() == 0 || hide_flag) {
			jComboBoxLedger.hidePopup();
			hide_flag = false;
		    } else {
			setAccountLedgetModel(m, text);
			jComboBoxLedger.showPopup();
		    }
		}
		tfLedgerText.selectAll();
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
			    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
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
			    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
			}
		    }
		});

	buttonBack.addActionListener(new java.awt.event.ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    buttonBackActionPerformed(evt);
		} catch (Exception ex) {
		    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
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
		if (Check_Print_Function == 0) {
		    try {
			buttonPrintActionPerformed(evt);
		    } catch (Exception ex) {
			Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
		    }
		}
		Check_Print_Function = 0;
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
			try {
			    buttonPrintActionPerformed(null);
			} catch (Exception ex) {
			    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
			}
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
		    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
		}
	    }

	    public void mousePressed(MouseEvent me) {
	    }

	    public void mouseReleased(MouseEvent me) {
	    }
	});

	jTableDebit.addKeyListener(new java.awt.event.KeyAdapter() {
	    @Override
	    public void keyPressed(java.awt.event.KeyEvent evt) {
		try {
		    jTableDebitKeyPressed(evt);
		} catch (Exception ex) {
		    Logger.getLogger(LedgerReportForm1.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(LedgerReportForm1.this, ex.getMessage());
		}
	    }

	    @Override
	    public void keyReleased(java.awt.event.KeyEvent evt) {
	    }
	});


    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws Exception {

	buttonBack.setMnemonic(KeyEvent.VK_B);
	buttonShow.setMnemonic(KeyEvent.VK_S);
	buttonPrint.setMnemonic(KeyEvent.VK_P);

	if (toDate_Preserve.isEmpty()) {

	    tfDatePicker1.setText(new TrialBalanceDTO().getDate());
	    tfDatePicker2.setText(new TrialBalanceDTO().getDate());
	} else {

	    tfDatePicker1.setText(fromDate_Preserve);
	    tfDatePicker2.setText(toDate_Preserve);
	    buttonShowActionPerformed(null);
	}

    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException, Exception {
	MainClass.setstaticvar();
//	this.dispose();
    }

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

	buttonShowActionPerformed(evt);
	printfunction();

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
	if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
	    if (jTableDebit.getRowCount() > 0) {
		jTableDebit.requestFocus();
		jTableDebit.changeSelection(0, 0, false, false);
	    }
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

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
	// TODO add your handling code here:

	MainClass.setstaticvar();
	MainClass m = new MainClass();
	m.menuselection(3);
	this.setClosed(true);


    }

    private void jTableDebitKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
	if (evt.getKeyCode() == KeyEvent.VK_UP) {
	    if (jTableDebit.getSelectedRow() == 0) {
		buttonShow.requestFocus();
		jTableDebit.clearSelection();
	    }
	}
	if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    buttonShow.requestFocus();
	    jTableDebit.clearSelection();
	}
	if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
	    jTableDebitListMouseClicked(null);
	}
    }

    private void jTableDebitListMouseClicked(java.awt.event.MouseEvent evt) throws Exception {
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

	    } else if (jTableDebit.getValueAt(row, 3).toString().equalsIgnoreCase("Sale")) {

		String id = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 4).toString();
                String date = jTableDebit.getValueAt(jTableDebit.getSelectedRow(), 2).toString();
                System.err.println("date====" + date);
                Date dateEntity = (java.util.Date) Constants.DATE_FORMATER_WITH_SLASH.parse(date.trim());
		SaleDTO saleDTO = new SaleDTO();
		SaleForm s = new SaleForm("Sale", new Dimension(), saleDTO, true);
		s.loadEditForm(id, dateEntity);
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
		ChalanDTO purchaseDTO = new ChalanDTO();
		ChalanForm1 p = new ChalanForm1(purchaseDTO, true);
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
	    }
	}
    }
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
    private JComboBox jComboBoxLedger;
    private JTextField tfLedgerText;
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

    private void initilize() throws Exception {
	initVariables();
    }

    private void initVariables() throws Exception {
	initGroup();
    }

    private void initGroup() throws Exception {
	List<String> groups = new ArrayList<String>();
	mapLedger = gen.account.ledger.LedgerDAO.getLedgerNameMap(groups, false);

	ledgerVector.clear();
//	ledgerVector.add("");
	for (String str : mapLedger.keySet()) {
	    ledgerVector.add(str);
	}

	Collections.sort(
		ledgerVector,
		new Comparator<String>() {
		    @Override
		    public int compare(String lhs, String rhs) {
			return lhs.compareToIgnoreCase(rhs);
		    }
		});

	//mapLedger = Util.getSmallCaseMap(mapLedger);
    }

    private Boolean validateData() throws Exception {
	Boolean flag = true;

	//if (!mapLedger.containsKey(tfLedgerText.getText().toLowerCase().toString().trim())) {
	if (!mapLedger.containsKey(tfLedgerText.getText().toString().trim())) {
	    JOptionPane.showMessageDialog(this, Label.ENTER_VALID_LEDGER_NAME);
            flag = false;
	    tfLedgerText.requestFocus();
	    jComboBoxLedger.requestFocus();
            System.out.println("Presed key ------");
	} else if (fromJDateChooser.getDate() == null) {
	    JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
	    tfDatePicker1.requestFocus();
            fromJDateChooser.requestFocus();
	    flag = false;
	} else if (toJDateChooser.getDate() == null) {
	    JOptionPane.showMessageDialog(this, Label.DATE_VALUE_IS_NOT_VALID);
	    tfDatePicker2.requestFocus();
            toJDateChooser.requestFocus();
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
	}

	if (flag == false) {
	    transactionTableModelDebit.setRowCount(0);
	}

	return flag;
    }

    private void setAccountLedgetModel(DefaultComboBoxModel mdl, String str) {
	jComboBoxLedger.setModel(mdl);
	jComboBoxLedger.setSelectedIndex(-1);
	tfLedgerText.setText(str);
    }

    private void bindToGUI() throws SQLException, Exception {

	String ledger_Name = "";
	DecimalFormat decformat = new DecimalFormat("#.##");

	List<String> ledger_List = new ArrayList<String>();

	//ledger_Name = mapLedger.get(tfLedgerText.getText().toLowerCase().toString().trim());
	ledger_Name = mapLedger.get(tfLedgerText.getText().toString().trim());
	ledger_List.add(ledger_Name);

	List<LedgerReportDTO> ledgerReportFormDTOList = LedgerReportDAO.getLedgerReport(fromDateStr, toDateStr, ledger_List);

	transactionTableModelDebit = (DefaultTableModel) jTableDebit.getModel();
	for (LedgerReportDTO ledgerReportFormDTO : ledgerReportFormDTOList) {
	    for (TransactionsDTO transactionsDTO : ledgerReportFormDTO.getTransactionsDTO()) {
		int row = jTableDebit.getRowCount();
		transactionTableModelDebit.setRowCount(row + 1);

		jTableDebit.setValueAt(row + 1, row, 0);

		for (Map.Entry<String, String> e : mapLedger.entrySet()) {
		    //if (transactionsDTO.getParticulars().toLowerCase().equals(e.getValue())) {
		    if (transactionsDTO.getParticulars().equals(e.getValue())) {
			jTableDebit.setValueAt(e.getKey(), row, 1);
		    }
		}
                

		jTableDebit.setValueAt(transactionsDTO.getDate(), row, 2);
		jTableDebit.setValueAt(transactionsDTO.getVchtype(), row, 3);
		jTableDebit.setValueAt(transactionsDTO.getVchno(), row, 4);


		if (!transactionsDTO.getDebit().equals("")) {
		    jTableDebit.setValueAt(decformat.format(Double.parseDouble(transactionsDTO.getDebit())), row, 5);
		} else if (!transactionsDTO.getCredit().equals("")) {
		    jTableDebit.setValueAt(decformat.format(Double.parseDouble(transactionsDTO.getCredit())), row, 6);
		}
	    }


	    int row = jTableDebit.getRowCount();
	    transactionTableModelDebit.setRowCount(row + 1);

	    jTableDebit.setValueAt("Total", row, 4);

	    jTableDebit.setValueAt(decformat.format(ledgerReportFormDTO.getTotal_debit()), row, 5);
	    jTableDebit.setValueAt(decformat.format(ledgerReportFormDTO.getTotal_credit()), row, 6);

	    int row1 = jTableDebit.getRowCount();
	    transactionTableModelDebit.setRowCount(row1 + 1);
	    jTableDebit.setValueAt("OP Bal", row1, 4);
	    if (ledgerReportFormDTO.getOpening_balance_type().equals("Credit")) {
		jTableDebit.setValueAt("" + decformat.format(Math.abs(ledgerReportFormDTO.getOpening_balance())), row1, 6);
	    } else {
		jTableDebit.setValueAt("" + decformat.format(Math.abs(ledgerReportFormDTO.getOpening_balance())), row1, 5);
	    }

	    int row2 = jTableDebit.getRowCount();
	    transactionTableModelDebit.setRowCount(row2 + 1);
	    jTableDebit.setValueAt("CL Bal", row2, 4);
	    if (ledgerReportFormDTO.getClosing_balance_type().equals("Credit")) {
		jTableDebit.setValueAt("" + decformat.format(Math.abs(ledgerReportFormDTO.getClosing_balance())), row2, 6);
	    } else {
		jTableDebit.setValueAt("" + decformat.format(Math.abs(ledgerReportFormDTO.getClosing_balance())), row2, 5);
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

        System.out.println("currentFocusValue            " + currentFocusValue);
        
	if (currentFocusValue == 0) {
	    tfLedgerText.requestFocus();
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
	passedTable.getColumnModel().getColumn(2).setMinWidth(80);
	passedTable.getColumnModel().getColumn(2).setMaxWidth(80);
	//passedTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);


	passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
	passedTable.getColumnModel().getColumn(3).setMinWidth(80);
	passedTable.getColumnModel().getColumn(3).setMaxWidth(80);
	//passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

	passedTable.getColumnModel().getColumn(4).setPreferredWidth(0);
	passedTable.getColumnModel().getColumn(4).setMinWidth(60);
	passedTable.getColumnModel().getColumn(4).setMaxWidth(60);


	passedTable.getColumnModel().getColumn(5).setPreferredWidth(0);
	passedTable.getColumnModel().getColumn(5).setMinWidth(100);
	passedTable.getColumnModel().getColumn(5).setMaxWidth(100);
	passedTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);


	passedTable.getColumnModel().getColumn(6).setPreferredWidth(0);
	passedTable.getColumnModel().getColumn(6).setMinWidth(100);
	passedTable.getColumnModel().getColumn(6).setMaxWidth(100);
	passedTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
    }

    public void printfunction() throws Exception {

	//if (jTableDebit.getRowCount() > 3 && fromJDateChooser.getDate() != null && toJDateChooser.getDate() != null && fromJDateChooser.getDate().getTime() <= toJDateChooser.getDate().getTime() && mapLedger.containsKey(tfLedgerText.getText().toLowerCase().toString().trim())) {
	if (jTableDebit.getRowCount() > 3 && fromJDateChooser.getDate() != null && toJDateChooser.getDate() != null && fromJDateChooser.getDate().getTime() <= toJDateChooser.getDate().getTime() && mapLedger.containsKey(tfLedgerText.getText().toString().trim())) {
	    Map parameter = new HashMap();
	    parameter.put("Particulars", "Particulars");
	    parameter.put("Sr.No", "Sr.No");
	    parameter.put("VCH No", "VCH No");
	    parameter.put("VCH Type", "VCH Type");
	    parameter.put("Date", "Date");
	    parameter.put("Debit", "Debit");
	    parameter.put("Credit", "Credit");
	    parameter.put("ledgerName", tfLedgerText.getText().toString().trim());
	    parameter.put("dateFrom", tfDatePicker1.getText().toString().trim());
	    parameter.put("dateTo", tfDatePicker2.getText().toString().trim());
	    parameter.put("TotalHeader", "");
	    parameter.put("OpeningBalanceHeader", "");
	    parameter.put("ClosingBalanceHeader", "");
	    PrintAllReport.printLedgerReport(parameter, new JRTableModelDataSource(jTableDebit.getModel()));
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
	//if (!mapLedger.containsKey(tfLedgerText.getText().toLowerCase().toString().trim())) {
	if (!mapLedger.containsKey(tfLedgerText.getText().toString().trim())) {
	    JOptionPane.showMessageDialog(this, Label.ENTER_VALID_LEDGER_NAME);
	    tfLedgerText.requestFocus();
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