/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.display.report;

import com.toedter.calendar.JDateChooser;
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.MainClass;
import gen.other.print.PrintAllReport;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author pc5
 */
public class TrialBalanceForm extends javax.swing.JInternalFrame implements java.awt.event.ActionListener {

    private String fromDateStr = "";
    private String toDateStr = "";
    Double totalDebit = 0d;
    Double totalCredit = 0d;
    public static int Check_Print_Function = 0;

    public TrialBalanceForm(String str, Dimension d) {
	initComponents();
	this.setPreferredSize(d);
	setClosable(true);
    }

    private void initComponents() {

	setTitle("Trial Balance");
	setBounds(100, 100, 1322, 674);

	panel = new JPanel();
	getContentPane().add(panel, BorderLayout.CENTER);
	panel.setLayout(new MigLayout("", "[0px:10px:10px,grow,shrink 50,fill][grow,shrink 50,fill][0px:10px:10px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

	lblDaybook = new JLabel("Trial Balance");
	lblDaybook.setFont(new Font("Times New Roman", Font.BOLD, 20));
	lblDaybook.setHorizontalAlignment(SwingConstants.CENTER);
	panel.add(lblDaybook, "cell 1 0");

	fromJDateChooser = new JDateChooser();
	panel.add(fromJDateChooser, "flowx,cell 1 1");

	lblTo = new JLabel("To");
	lblTo.setHorizontalAlignment(SwingConstants.CENTER);
	lblTo.setFont(new Font("Times New Roman", Font.BOLD, 14));
	panel.add(lblTo, "cell 1 1");

	toJDateChooser = new JDateChooser();
	panel.add(toJDateChooser, "cell 1 1");

	buttonShow = new JButton("Show");
	panel.add(buttonShow, "cell 1 1");

	buttonPrint = new JButton("Print");
	panel.add(buttonPrint, "cell 1 1");

	buttonBack = new JButton("Back");
	panel.add(buttonBack, "cell 1 1");

	btnNewButton_3 = new JButton("Advance Daybook");
	btnNewButton_3.setEnabled(false);
	panel.add(btnNewButton_3, "cell 1 1");

	btnNewButton_4 = new JButton("Export");
	btnNewButton_4.setEnabled(false);
	panel.add(btnNewButton_4, "cell 1 1");

	panel_1 = new JPanel();
	panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
	panel.add(panel_1, "cell 1 2 1 19,grow");
	panel_1.setLayout(new MigLayout("", "[0px:1280px:1280px,grow,shrink 50,fill]", "[0px:25px:25px,grow,shrink 50,fill][grow][0px:25px:25px,grow,shrink 50,fill]"));

	panel_2 = new JPanel();
	panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
	panel_1.add(panel_2, "cell 0 1,grow");
	panel_2.setLayout(new MigLayout("", "[grow]", "[grow]"));

	JScrollPane scrollPane = new JScrollPane();
	panel_2.add(scrollPane, "cell 0 0,grow");

	String col1[] = {"Sr.No", "Groups", "Debit", "Credit"};
	String data1[][] = {{"", "", "", ""}};

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
	transactionTableModelDebit.setColumnCount(4);
	jTableDebit.getColumnModel().getColumn(0).setPreferredWidth(0);

	jTableDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	//jMainPanel.add(pane1);

	//jMainPanel.add(panelDebit);

	setColumnWidth(jTableDebit);
	scrollPane.setViewportView(jTableDebit);

	lblTotalAmount = new JLabel("Total Amount");
	lblTotalAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
	lblTotalAmount.setHorizontalAlignment(SwingConstants.RIGHT);
	panel_1.add(lblTotalAmount, "flowx,cell 0 2");

	labelTotalDebitAmount = new JLabel("0");
	labelTotalDebitAmount.setHorizontalAlignment(SwingConstants.CENTER);
	labelTotalDebitAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
	panel_1.add(labelTotalDebitAmount, "cell 0 2");

	labelTotalCreditAmount = new JLabel("0");
	labelTotalCreditAmount.setHorizontalAlignment(SwingConstants.CENTER);
	labelTotalCreditAmount.setFont(new Font("Times New Roman", Font.BOLD, 14));
	panel_1.add(labelTotalCreditAmount, "cell 0 2");

	initialiseActionListeners();

    }

    private void initialiseActionListeners() {

	setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
	addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
	    @Override
	    public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
		try {
		    formInternalFrameActivated(evt);
		} catch (Exception ex) {
		    Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
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
		    Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
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
	    }
	});


	fromJDateChooser.setDateFormatString(Constants.DATE_FORMAT_STRING);
	tfDatePicker1 = (JTextField) fromJDateChooser.getComponent(1);

	tfDatePicker1.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_ENTER) {
		    tfDatePicker2.requestFocus();
		    tfDatePicker2.selectAll();
		}
		if (code == KeyEvent.VK_ESCAPE) {
		    try {
			buttonBackActionPerformed(null);
		    } catch (Exception ex) {
			Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
		    }
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
		    buttonShow.requestFocus();
		}
		if (code == KeyEvent.VK_ESCAPE) {
		    tfDatePicker1.requestFocus();
		}
	    }
	});

//        tfDatePicker1.addKeyListener(new java.awt.event.KeyAdapter() {
//            @Override
//            public void keyTyped(java.awt.event.KeyEvent evt) {
//
//                //Util.filterCharacter(evt, jTextFieldAmount);
//
//            }
//
//            @Override
//            public void keyPressed(java.awt.event.KeyEvent evt) {
//
//
//
//                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                    String date = tfDatePicker.getText();
//                    int lengthOfDate = date.length();
//                    System.out.println("Length :::" + lengthOfDate);
//
//                    if (lengthOfDate == 6) {
//                        String dateBits1 = date.substring(0, 1);
//                        String dateBits2 = date.substring(1, 2);
//                        String dateBits3 = date.substring(2, 6);
//                        tfDatePicker.setText("0" + dateBits1 + "-" + "0" + dateBits2 + "-" + dateBits3);
//                    }
//                    if (lengthOfDate == 7) {
//
//                        String subDate = date.substring(1, 3);
//                        System.out.println("subDate :::" + subDate);
//                        if (subDate.equalsIgnoreCase("12") || subDate.equalsIgnoreCase("11") || subDate.equalsIgnoreCase("10")) {
//
//                            String dateBits1 = date.substring(0, 1);
//                            String dateBits2 = date.substring(1, 3);
//                            String dateBits3 = date.substring(3, 7);
//                            tfDatePicker.setText("0" + dateBits1 + "-" + dateBits2 + "-" + dateBits3);
//
//                        } else {
//                            String dateBits1 = date.substring(0, 2);
//                            String dateBits2 = date.substring(2, 3);
//                            String dateBits3 = date.substring(3, 7);
//                            tfDatePicker.setText(dateBits1 + "-" + "0" + dateBits2 + "-" + dateBits3);
//                        }
//
//                    }
//                    if (lengthOfDate == 8) {
//                        String dateBits1 = date.substring(0, 2);
//                        Integer dateBitsInt1 = Integer.parseInt(dateBits1);
//                        String dateBits2 = date.substring(2, 4);
//                        Integer dateBitsInt2 = Integer.parseInt(dateBits2);
//                        String dateBits3 = date.substring(4, 8);
//
//                        if (dateBitsInt1 > 31 && dateBitsInt2 > 12) {
//                            dateBits1 = "31";
//                            dateBits2 = "12";
//                            tfDatePicker.setText(dateBits1 + "-" + dateBits2 + "-" + dateBits3);
//                        } else if (dateBitsInt1 > 31) {
//                            dateBits1 = "31";
//                            tfDatePicker.setText(dateBits1 + "-" + dateBits2 + "-" + dateBits3);
//                        } else if (dateBitsInt2 > 12) {
//                            dateBits2 = "12";
//                            tfDatePicker.setText(dateBits1 + "-" + dateBits2 + "-" + dateBits3);
//                        } else {
//                            tfDatePicker.setText(dateBits1 + "-" + dateBits2 + "-" + dateBits3);
//                        }
//                    }
//                }
//            }
//        });

	tfDatePicker1.addFocusListener(
		new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
			// throw new UnsupportedOperationException("Not supported yet.");
			tfDatePicker1.selectAll();
			//currentFocusValue = 1;
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
			    Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
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
			    Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
			}
		    }
		});

	buttonBack.addActionListener(new java.awt.event.ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    buttonBackActionPerformed(evt);
		} catch (Exception ex) {
		    Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
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
			Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
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
			    Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
			    ex.printStackTrace();
			    JOptionPane.showMessageDialog(TrialBalanceForm.this, ex.getMessage());
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
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws Exception {

	buttonBack.setMnemonic(KeyEvent.VK_B);
	buttonShow.setMnemonic(KeyEvent.VK_S);
	buttonPrint.setMnemonic(KeyEvent.VK_P);

	tfDatePicker1.setText(new TrialBalanceDTO().getDate());
	tfDatePicker2.setText(new TrialBalanceDTO().getDate());
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
	MainClass.setstaticvar();
//	this.dispose();
    }

    private void initilize() throws SQLException, IOException {
    }

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

	if (jTableDebit.getRowCount() > 0) {
	    Map parameter = new HashMap();
	    parameter.put("Groups", "Groups");
	    parameter.put("Sr.No", "Sr.No");

	    parameter.put("Debit", "Debit");
	    parameter.put("Credit", "Credit");

	    String TotalDebitValue = labelTotalDebitAmount.getText();
	    String TotalCreditValue = labelTotalCreditAmount.getText();
	    //  System.out.println(""+TotalAmountValue);

	    // String TotalAmount=labelTotalCredit.getText();

	    parameter.put("TotalDebitValue", TotalDebitValue);
	    parameter.put("TotalCreditValue", TotalCreditValue);


	    parameter.put("TotalDebit", "TotalDebit");
	    parameter.put("TotalCredit", "TotalCredit");


//         parameter.put("OpeningBalance","OpeningBalance");
//         parameter.put("ClosingBalance","ClosingBalance");
	    //parameter.put("subreporttrial",new JRTableModelDataSource(tableLedger.getModel()));


	    parameter.put("TotalDebit", "Total");
	    parameter.put("TotalCredit", "Total");
	    SimpleDateFormat saDa = new SimpleDateFormat("dd-MM-yyyy");
	    parameter.put("dateFrom", saDa.format(fromJDateChooser.getDate()).toString());
	    parameter.put("dateTo", saDa.format(toJDateChooser.getDate()).toString());


	    PrintAllReport.printtrialReport(parameter, new JRTableModelDataSource(jTableDebit.getModel()));
	} else {
	    JOptionPane.showMessageDialog(this, "No Data Available For Printing");
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

	if (fromJDateChooser.getDate() == null) {

	    JOptionPane.showMessageDialog(this, "Invalid Date Entered");
	    tfDatePicker1.requestFocus();
	    fromJDateChooser.requestFocus();

	} else if (toJDateChooser.getDate() == null) {
	    JOptionPane.showMessageDialog(this, "Invalid Date Entered");
	    tfDatePicker2.requestFocus();
	    toJDateChooser.requestFocus();
	} else if (fromJDateChooser.getDate() == null && toJDateChooser.getDate() == null) {

	    JOptionPane.showMessageDialog(this, "Invalid Date Entered");
	    tfDatePicker1.requestFocus();
	    fromJDateChooser.requestFocus();

	} else {
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

	    System.out.println("fromDate" + fromDateStr);
	    System.out.println("toDate" + toDateStr);
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
    private JPanel panelDebit;
    private JPanel panelCredit;
    private JLabel labelDate1;
    private JLabel labelDate2;
    private JLabel labelTotal;
    private JLabel labelTotalCredit;
    private JLabel labelTotalDebitAmount;
    private JLabel labelTotalCreditAmount;
    private JTextArea jTextAreaTempWindowSale;
    private JTextArea jTextAreaTempWindowPurchase;
    private JButton buttonShow;
    private JButton buttonPrint;
    private JButton buttonBack;
    private com.toedter.calendar.JDateChooser fromJDateChooser;
    private com.toedter.calendar.JDateChooser toJDateChooser;
    private JTable jTableDebit;
    private JTable jTableCredit;
    private DefaultTableModel transactionTableModelDebit;
    private DefaultTableModel transactionTableModelCredit;
    private JTextField tfDatePicker1 = null;
    private JTextField tfDatePicker2 = null;
    private JPanel panel;
    private JLabel lblDaybook;
    private JLabel lblName;
    private JButton btnShow;
    private JButton btnPrint;
    private JButton btnBack;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JButton btnChalan;
    private JLabel lblCredit;
    private JLabel lblTo;
    private JLabel lblDebit;
    private JPanel panel_1;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JPanel panel_3;
    private JScrollPane scrollPane_1;
    private JLabel lblNewLabel;
    private JLabel label_1;
    private JLabel label_2;
    private JLabel label_3;
    private JLabel lblTotalAmount;
    private JLabel labelTotalDebit;
    private JPanel PurchasePanel;
    private boolean hide_flag = false;

    private void bindToGUI() throws SQLException, Exception {

	Double totalDebitAmount = 0D;
	Double totalCreditAmount = 0D;

	List<TrialBalanceDTO> trialBalanbceDTOList = TrialBalanceDAO.getTrialBalance(fromDateStr, toDateStr);

	for (TrialBalanceDTO trialBalanceDTO : trialBalanbceDTOList) {

	    if ((trialBalanceDTO.getDebitAmount()) != 0 || (trialBalanceDTO.getCreditAmount()) != 0) {
		transactionTableModelDebit = (DefaultTableModel) jTableDebit.getModel();
		int row = jTableDebit.getRowCount();
		transactionTableModelDebit.setRowCount(row + 1);

		jTableDebit.setValueAt(row + 1, row, 0);
		jTableDebit.setValueAt(trialBalanceDTO.getGroup_name(), row, 1);
		jTableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getDebitAmount()), row, 3);
		jTableDebit.setValueAt(Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getCreditAmount()), row, 2);

		totalDebitAmount += trialBalanceDTO.getDebitAmount();
		totalCreditAmount += trialBalanceDTO.getCreditAmount();

		System.out.println("1. Group_Name---->>" + trialBalanceDTO.getGroup_name());
		System.out.println("2. Total Debit--->>" + Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getDebitAmount()));
		System.out.println("3. Total Credit-->>" + Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getCreditAmount()));
		System.out.println("***************************************************************");
	    }
	}

	labelTotalDebitAmount.setText("" + Constants.DECIMAL_FORMAT.format(totalDebitAmount));
	labelTotalCreditAmount.setText("" + Constants.DECIMAL_FORMAT.format(totalCreditAmount));
    }

    private void setColumnWidth(JTable passedTable) {
	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

	//  passedTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

//	passedTable.getColumnModel().getColumn(0).setPreferredWidth(0);
//	passedTable.getColumnModel().getColumn(0).setMinWidth(40);
//	passedTable.getColumnModel().getColumn(0).setMaxWidth(40);
//
//	passedTable.getColumnModel().getColumn(1).setPreferredWidth(0);
//	passedTable.getColumnModel().getColumn(1).setMinWidth(265);
//	passedTable.getColumnModel().getColumn(1).setMaxWidth(265);
//
//
//	passedTable.getColumnModel().getColumn(2).setPreferredWidth(0);
//	passedTable.getColumnModel().getColumn(2).setMinWidth(180);
//	passedTable.getColumnModel().getColumn(2).setMaxWidth(180);
	passedTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);


//	passedTable.getColumnModel().getColumn(3).setPreferredWidth(0);
//	passedTable.getColumnModel().getColumn(3).setMinWidth(180);
//	passedTable.getColumnModel().getColumn(3).setMaxWidth(180);
	passedTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }
//    public static void main(String args[]) {
//        try {
//            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//            TrialBalanceForm p = new TrialBalanceForm();
//            p.setVisible(true);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(TrialBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}