/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.balancesheet;

import gen.accountvoucher.sale.SaleForm;
import gen.display.report.TrialBalanceDTO;
import gen.dto.Constants;
import gen.dto.GUIConstants;
import gen.dto.Label;
import gen.mainclass.MainClass;
import gen.profitandloss.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author pc5
 */
public class BalanceSheetForm extends JInternalFrame{

    private Double totalGrossProfit = 0D;
    private ProfitAndLossDTO profitlossDTO;
    static Double finalPofitAndLossOpeningTotal = 0D, Gross_Profit_co = 0D, Gross_Loss_co = 0D;
    static Double finalPofitAndLossClosingTotal = 0D;
    private String fromDateStr = "";
    private String toDateStr = "";
    private Map<String, String> mapGroupNameWithID = new HashMap<String, String>();
    ////////////// variable for Balance Sheet ///////////////////////////////////////
    Double PL_total_opening_Stock = 0D;
    Double PL_Total_Credit_beforec_o = 0D;
    Double PL_Total_debit_beforec_o = 0D;
    Double PL_Closing_Stock = 0D;
    Double PL_Gross_Profit_co = 0D;
    Double PL_Gross_Loss_co = 0D;
    Double PL_Total_Credit_Before_Grossco = 0D;
    Double PL_Total_debit__Before_Grossco = 0D;
    Double PL_Total_Credit_Indirect_Value = 0D;
    Double PL_Total_debit_Indirect_Value = 0D;
    Double PL_TotalCredit_After_TotalValue = 0D;
    Double PL_TotalDebit_After_TotalValue = 0D;
    Double PL_Net_Loss_Profit = 0D;
    Double PL_Total_Credit_Value_for_Gross_Profit_Loss_co = 0D;
    Double PL_Total_Debit_Value_for_Gross_Profit_Loss_co = 0D;
    Double PL_Gross_Profit_bf = 0D;
    Double PL_Gross_Loss_bf = 0D;
    int current_Period_Loss_Profit = 0;
    int Opening_Balance_Profit_Loss = 0;
    ///// temp_Opening_Balance_Profit_Loss = 0 means opeing_abalance is in Loss Side
    int temp_Opening_Balance_Profit_Loss = 0;
    ///// temp_Current_Period_Profit_Loss = 0 means Current Period is in Loss Side
    int temp_Current_Period_Profit_Loss = 0;

    ////////////////////////////////////////////////////////////////////////////////
    public BalanceSheetForm(String s, Dimension d) {
	initComponents();
	profitlossDTO = new ProfitAndLossDTO();
	//initilize();
	this.setSize(1366, 768);
	//setClosable(true);
    }

    private void initComponents() {

        setClosable(true);
	setFrameIcon(new ImageIcon(SaleForm.class.getResource("/images/Kasturi-logo-1.png")));
	setTitle("Balance Sheet Form");
	//  setBounds(100, 100, 1332, 674);

	container = getContentPane();
	toolkit = Toolkit.getDefaultToolkit();
	dimension = toolkit.getScreenSize();
	screenWidth = dimension.width;
	screenHeight = dimension.height;

	xCoordinate = (this.getWidth() / 2) - (screenWidth / 2);
	yCoordinate = (this.getHeight() / 2) - (screenHeight / 2);

	screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();

	flowLblLocationX = (-(xCoordinate) / screenResolution);
	flowLblLocationY = (-(yCoordinate) / screenResolution);

	fontValue = (-(xCoordinate) / screenResolution) + (-(yCoordinate) / screenResolution);
	fontSize = fontValue;//(int)Math.round(9.0 * screenRes / 80.0);

	Integer fontCase = Font.BOLD;

	if (screenWidth < 1200) {
	    fontCase = Font.PLAIN;
	}
	font = new Font("Tahoma", fontCase, fontSize);

	jMainPanel = new JPanel(null);//new FlowLayout(FlowLayout.LEFT,10,10)); 
	jMainPanel.setBounds(screenWidth / 40, screenHeight / 50, screenWidth - 75, screenHeight - 100);
        jMainPanel.setBorder(new TitledBorder(new EtchedBorder(), Label.BALANCESHEET_FORM_NAME));
	jMainPanel.setFont(font);

	panelDebit = new JPanel(null);//new FlowLayout(FlowLayout.LEFT,10,10)); 
	panelDebit.setBounds(screenWidth / 45, screenHeight / 10, screenWidth - 800, screenHeight - 250);
	panelDebit.setBorder(new TitledBorder(new EtchedBorder(), Label.DEBIT));
	panelDebit.setFont(font);

	panelCredit = new JPanel(null);//new FlowLayout(FlowLayout.LEFT,10,10)); 
	panelCredit.setBounds(screenWidth / 2, screenHeight / 10, screenWidth - 800, screenHeight - 250);
	panelCredit.setBorder(new TitledBorder(new EtchedBorder(), Label.CREDIT));
	panelCredit.setFont(font);

	GUIConstants.init(screenWidth, screenHeight);

	labelDate1 = new JLabel(Label.FROM);
	labelDate1.setFont(font);
	labelDate1.setBounds(GUIConstants.XAxis_LEVEL_6, GUIConstants.YAxis_LEVEL_1, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
	jMainPanel.add(labelDate1);

	fromJDateChooser = new com.toedter.calendar.JDateChooser();
	fromJDateChooser.setBounds(GUIConstants.XAxis_LEVEL_7, GUIConstants.YAxis_LEVEL_1, GUIConstants.comboBoxSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
	fromJDateChooser.setEnabled(true);
	fromJDateChooser.setFont(font);
	jMainPanel.add(fromJDateChooser);

	labelDate2 = new JLabel(Label.TO);
	labelDate2.setFont(font);
	labelDate2.setBounds(GUIConstants.XAxis_LEVEL_11, GUIConstants.YAxis_LEVEL_1, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
	jMainPanel.add(labelDate2);

	toJDateChooser = new com.toedter.calendar.JDateChooser();
	toJDateChooser.setBounds(GUIConstants.XAxis_LEVEL_12, GUIConstants.YAxis_LEVEL_1, GUIConstants.comboBoxSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
	toJDateChooser.setEnabled(true);
	toJDateChooser.setFont(font);
	jMainPanel.add(toJDateChooser);

	buttonShow = new JButton(Label.BUTTON_SHOW);
	buttonShow.setFont(font);
	buttonShow.setBounds(GUIConstants.XAxis_LEVEL_16, GUIConstants.YAxis_LEVEL_1, GUIConstants.labelSizeMediumWidth, GUIConstants.labelSizeHeight);
	jMainPanel.add(buttonShow);

	labelNetExpenditure = new JLabel(Label.NET_EXPENDITURE);
	labelNetExpenditure.setFont(font);
	labelNetExpenditure.setBounds(GUIConstants.XAxis_LEVEL_2, GUIConstants.YAxis_LEVEL_12, GUIConstants.comboBoxSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
	jMainPanel.add(labelNetExpenditure);

	labelNetExpenditureAmount = new JLabel(Label.NET_EXPENDITURE_AMOUNT);
	labelNetExpenditureAmount.setFont(font);
	labelNetExpenditureAmount.setBounds(GUIConstants.XAxis_LEVEL_4_1, GUIConstants.YAxis_LEVEL_12, GUIConstants.comboBoxSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
	jMainPanel.add(labelNetExpenditureAmount);

	labelNetIncome = new JLabel(Label.NET_INCOME);
	labelNetIncome.setFont(font);
	labelNetIncome.setBounds(GUIConstants.XAxis_LEVEL_14, GUIConstants.YAxis_LEVEL_12, GUIConstants.comboBoxSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
	jMainPanel.add(labelNetIncome);

	labelNetIncomeAmount = new JLabel(Label.NET_INCOME_AMOUNT);
	labelNetIncomeAmount.setFont(font);
	labelNetIncomeAmount.setBounds(GUIConstants.XAxis_LEVEL_16, GUIConstants.YAxis_LEVEL_12, GUIConstants.comboBoxSizeLargeWidth, GUIConstants.jtextFieldSizeHeight);
	jMainPanel.add(labelNetIncomeAmount);

	jTextAreaTempWindowSale = new JTextArea();
	jTextAreaTempWindowSale.setBounds(GUIConstants.XAxis_LEVEL_2, GUIConstants.YAxis_LEVEL_10, GUIConstants.comboBoxSizeLargeWidth, screenHeight / 7);
	jMainPanel.add(jTextAreaTempWindowSale);

	jTextAreaTempWindowPurchase = new JTextArea();
	jTextAreaTempWindowPurchase.setBounds(GUIConstants.XAxis_LEVEL_14, GUIConstants.YAxis_LEVEL_10, GUIConstants.comboBoxSizeLargeWidth, screenHeight / 7);
	jMainPanel.add(jTextAreaTempWindowPurchase);

	String col1[] = {"Category", ""};
	String data1[][] = {{"", ""}};

//        transactionTableModelDebit = new DefaultTableModel(data1, col1);
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
	transactionTableModelDebit.setColumnCount(2);
	jTableDebit.getColumnModel().getColumn(0).setPreferredWidth(0);


	jTableDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	JScrollPane pane1 = new JScrollPane(jTableDebit);
	pane1.setBounds(GUIConstants.XAxis_LEVEL_1, GUIConstants.YAxis_LEVEL_2_1, screenWidth / 3 + 60, (screenHeight / 5) * 2);
	jTableDebit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	jMainPanel.add(pane1);


	String col2[] = {"Category", "Amount"};
	String data2[][] = {{"", ""}};

	jTableCredit = new JTable() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;
	    }
	;
	};
        transactionTableModelCredit = new DefaultTableModel(data2, col2);
	jTableCredit.setModel(transactionTableModelCredit);
	final JTableHeader header2 = jTableCredit.getTableHeader();
	jTableDebit.getTableHeader().setReorderingAllowed(false);
	header2.setBackground(Color.yellow);
	header2.setFont(font);
	jTableCredit.setFont(font);
	transactionTableModelCredit = (DefaultTableModel) jTableCredit.getModel();
	transactionTableModelCredit.setRowCount(0);
	transactionTableModelCredit.setColumnCount(2);
	jTableCredit.getColumnModel().getColumn(0).setPreferredWidth(0);


	jTableCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	JScrollPane pane2 = new JScrollPane(jTableCredit);
	pane2.setBounds(GUIConstants.XAxis_LEVEL_12, GUIConstants.YAxis_LEVEL_2_1, screenWidth / 3 + 60, (screenHeight / 5) * 2);
	jTableCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	jMainPanel.add(pane2);

	jMainPanel.add(panelDebit);
	jMainPanel.add(panelCredit);

	this.setLayout(null);
	this.getContentPane().add(jMainPanel);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(screenWidth, screenHeight);
	this.setVisible(true);

	buttonShow.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
//                    List<ProfitAndLossDTO> tempDataList = new ArrayList<ProfitAndLossDTO>();
//                    tempDataList = BalanceSheetDAO.getAllDataTemp();
//
//                    for (ProfitAndLossDTO palDTO : tempDataList) {
//
//                        jTextAreaTempWindowSale.setText("Sale Items     :" + palDTO.getSaleItems() + "\n" + "Expected Gain:" + palDTO.getTotalAmountThroughSale() + "\n" + "Amount Received:" + palDTO.getProfitSALE() + "\n" + "Gross Profit     :" + palDTO.getNetProfitSALE());
//                        jTextAreaTempWindowPurchase.setText("PurchaseItems :" + palDTO.getPurchaseItems() + "\n" + "Expected Expenditure:" + palDTO.getTotalAmountThroughPurchase() + "\n" + "Amount Spent:" + palDTO.getProfitPURCHASE() + "\n" + "Gross Loss     :" + palDTO.getNetProfitPURCHASE());
//
//                        totalGrossProfit = palDTO.getNetProfitSALE() + palDTO.getNetProfitPURCHASE();
//                    }
		    initilize();
		} catch (IOException ex) {
		    ex.printStackTrace();
		    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
		    ex.printStackTrace();
		    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	});
	// initialiseActionListeners();
        initialiseActionListeners();

    }

    private void initialiseActionListeners() {

        //   setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
//            @Override
//            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
//                try {
//                    formInternalFrameActivated(evt);
//                } catch (SQLException ex) {
//                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public void internalFrameOpened(InternalFrameEvent e) {
//                // throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
//
//                try {
//                    formInternalFrameClosing(evt);
//                } catch (PropertyVetoException ex) {
//                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//            @Override
//            public void internalFrameClosed(InternalFrameEvent e) {
//                // throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void internalFrameIconified(InternalFrameEvent e) {
//                // throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void internalFrameDeiconified(InternalFrameEvent e) {
//                //  throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void internalFrameDeactivated(InternalFrameEvent e) {
//                // throw new UnsupportedOperationException("Not supported yet.");
//            }
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");
                 try {
                    formInternalFrameClosing(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                try {
                    formInternalFrameActivated(e);
                } catch (Exception ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException {
//        buttonBack.setMnemonic(KeyEvent.VK_B);
//        buttonShow.setMnemonic(KeyEvent.VK_S);
//        buttonPrint.setMnemonic(KeyEvent.VK_P);
//
//        tfDatePicker1.setText(new TrialBalanceDTO().getDate());
//        tfDatePicker2.setText(new TrialBalanceDTO().getDate());
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
	MainClass.setstaticvar();
	this.dispose();
    }

    private void initilize() throws SQLException, IOException {
	String toDateCloseStr = "";
	String fromDateOpenStr = "";
	String toDateClosefor_opening_display = "";
	if (fromJDateChooser.getDate() == null) {
	    JOptionPane.showMessageDialog(this, "Invalid Date Entered");
	    fromJDateChooser.requestFocus();
	    DefaultTableModel tableModel = (DefaultTableModel) jTableDebit.getModel();
	    tableModel.setRowCount(0);
	    DefaultTableModel tableModel1 = (DefaultTableModel) jTableCredit.getModel();
	    tableModel1.setRowCount(0);
	} else if (toJDateChooser.getDate() == null) {
	    JOptionPane.showMessageDialog(this, "Invalid Date Entered");
	    toJDateChooser.requestFocus();
	    DefaultTableModel tableModel = (DefaultTableModel) jTableDebit.getModel();
	    tableModel.setRowCount(0);
	    DefaultTableModel tableModel1 = (DefaultTableModel) jTableCredit.getModel();
	    tableModel1.setRowCount(0);
	} else if (fromJDateChooser.getDate().getTime() > toJDateChooser.getDate().getTime()) {
	    JOptionPane.showMessageDialog(this, "Invalid Date selection");
	    fromJDateChooser.requestFocus();
	    DefaultTableModel tableModel = (DefaultTableModel) jTableDebit.getModel();
	    tableModel.setRowCount(0);
	    DefaultTableModel tableModel1 = (DefaultTableModel) jTableCredit.getModel();
	    tableModel1.setRowCount(0);
	} else {
	if (fromJDateChooser.getDate() != null) {
	    try {
		fromDateStr = Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate());

		Calendar c = Calendar.getInstance();
		c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate())));
		//c.add(Calendar.DATE, -1);  // number of days to add
		fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());

		Calendar c1 = Calendar.getInstance();
		c1.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate())));
		c1.add(Calendar.DATE, -1);  // number of days to add

		toDateClosefor_opening_display = Constants.simpleDateFormatDatabaseWithDash.format(c1.getTime());;
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + toDateClosefor_opening_display);


	    } catch (ParseException ex) {
		Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	if (toJDateChooser.getDate() != null) {
	    try {
		toDateStr = Constants.simpleDateFormatDatabaseWithDash.format(toJDateChooser.getDate());
		Calendar c = Calendar.getInstance();
		c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(toJDateChooser.getDate())));
		// c.add(Calendar.DATE, -1);  // number of days to add
		toDateCloseStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());
	    } catch (ParseException ex) {
		Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	mapGroupNameWithID.clear();
	mapGroupNameWithID = ProfitAndLossDAO.allGroupNameWithIDs();

	transactionTableModelCredit.setRowCount(0);
	transactionTableModelDebit.setRowCount(0);

	// BalanceSheetDAO.getGroupsHierarchy();

	List<String> groupNames = new ArrayList<String>();
	groupNames.add(Constants.CURRENT_ASSETS);
	groupNames.add(Constants.CURRENT_LIABILITY);
	groupNames.add(Constants.LOAN_LIABILITY);
	groupNames.add(Constants.SUSPENSE_ACCOUNT);
	groupNames.add(Constants.CAPITAL_ACCOUNT);

	/// 10 for check and 10 for Balance Sheet Data 
	List<TrialBalanceDTO> trialBalanceDTOList = BalanceSheetDAO.getGroupTransactions(groupNames, fromDateStr, toDateStr, 10, 10);

	for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList) {
	    if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    if (e.getValue().equalsIgnoreCase("Current Assets")) {
				transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
				transactionTableModelCredit.setValueAt(e.getValue(), transactionTableModelCredit.getRowCount() - 1, 0);
				if (trialBalanceDTO.getTrans_Type() == 1) {
				    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(-(trialBalanceDTO.getCredit_Debit_Difference() - BalanceSheetDAO.getProfitAndLossClosingBal(fromDateStr, toDateStr, 10)))), transactionTableModelCredit.getRowCount() - 1, 1);
				} else if (trialBalanceDTO.getTrans_Type() == 2) {
				    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(-(trialBalanceDTO.getCredit_Debit_Difference() + BalanceSheetDAO.getProfitAndLossClosingBal(fromDateStr, toDateStr, 10)))), transactionTableModelCredit.getRowCount() - 1, 1);
				}
			    } else {
				transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
				transactionTableModelCredit.setValueAt(e.getValue(), transactionTableModelCredit.getRowCount() - 1, 0);
				transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(trialBalanceDTO.getCredit_Debit_Difference())), transactionTableModelCredit.getRowCount() - 1, 1);
			    }
			}
		    }
		}
	    } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    if (e.getValue().equalsIgnoreCase("Current Assets")) {
				transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
				transactionTableModelDebit.setValueAt(e.getValue(), transactionTableModelDebit.getRowCount() - 1, 0);
//                                System.out.println(",,,,,,,,, trialBalanceDTO.getCredit_Debit_Difference(),,,,,,,,,,,,,,,,,,,,,,,,,,,,," + trialBalanceDTO.getCredit_Debit_Difference());
//                                System.out.println(",,,,,,,,, trialBalanceDTO.getCredit_Debit_Difference(),,,,,,,,,,,,,,,,,,,,,,,,,,,,," + BalanceSheetDAO.getProfitAndLossClosingBal(toDateStr, toDateStr, 10));
				if (trialBalanceDTO.getTrans_Type() == 1) {
				    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(-(trialBalanceDTO.getCredit_Debit_Difference() - BalanceSheetDAO.getProfitAndLossClosingBal(toDateStr, toDateStr, 10)))), transactionTableModelDebit.getRowCount() - 1, 1);
				} else if (trialBalanceDTO.getTrans_Type() == 2) {
				    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(-(trialBalanceDTO.getCredit_Debit_Difference() + BalanceSheetDAO.getProfitAndLossClosingBal(toDateStr, toDateStr, 10)))), transactionTableModelDebit.getRowCount() - 1, 1);
				}
			    } else {
				transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
				transactionTableModelDebit.setValueAt(e.getValue(), transactionTableModelDebit.getRowCount() - 1, 0);
				transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(trialBalanceDTO.getCredit_Debit_Difference())), transactionTableModelDebit.getRowCount() - 1, 1);
			    }
			}
		    }
		}
	    }
	}


///////////////////////////////////////////////////////////////////////////////////////////////////
	// calculate return_Current_Peroid
	Double return_Current_Peroid = calculate_Closing_Balance(fromDateStr, toDateStr, 10);

	current_Period_Loss_Profit = temp_Current_Period_Profit_Loss;
	temp_Current_Period_Profit_Loss = 0;
	temp_Opening_Balance_Profit_Loss = 0;

	setallBL_Zero();

	// calculate return_Opening_Balance_for_Display, value of check = 5 for calculate return_Balance_For_Display
	Double return_Opening_Balance_for_Display = calculate_Closing_Balance(toDateClosefor_opening_display, toDateStr, 5);
	Opening_Balance_Profit_Loss = temp_Opening_Balance_Profit_Loss;

	if (PL_TotalDebit_After_TotalValue >= PL_TotalCredit_After_TotalValue) {
	    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	    transactionTableModelCredit.setValueAt("Profit AND LOSS", transactionTableModelCredit.getRowCount() - 1, 0);

	    if ((Opening_Balance_Profit_Loss == 0 && current_Period_Loss_Profit == 0) || (Opening_Balance_Profit_Loss == 1 && current_Period_Loss_Profit == 1)) {
		transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Opening_Balance_for_Display + return_Current_Peroid)), transactionTableModelCredit.getRowCount() - 1, 1);
	    } else if ((Opening_Balance_Profit_Loss == 0 && current_Period_Loss_Profit == 1) || (Opening_Balance_Profit_Loss == 1 && current_Period_Loss_Profit == 0)) {
		transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Opening_Balance_for_Display - return_Current_Peroid)), transactionTableModelCredit.getRowCount() - 1, 1);
	    }

	    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	    transactionTableModelCredit.setValueAt("Opening_Balance", transactionTableModelCredit.getRowCount() - 1, 0);
	    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Opening_Balance_for_Display)), transactionTableModelCredit.getRowCount() - 1, 1);

	    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	    transactionTableModelCredit.setValueAt("Current Period", transactionTableModelCredit.getRowCount() - 1, 0);
	    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Current_Peroid)), transactionTableModelCredit.getRowCount() - 1, 1);
	} else if (PL_TotalDebit_After_TotalValue < PL_TotalCredit_After_TotalValue) {
	    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	    transactionTableModelDebit.setValueAt("Profit AND LOSS", transactionTableModelDebit.getRowCount() - 1, 0);
	    if ((Opening_Balance_Profit_Loss == 0 && current_Period_Loss_Profit == 0) || (Opening_Balance_Profit_Loss == 1 && current_Period_Loss_Profit == 1)) {
		transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Opening_Balance_for_Display + return_Current_Peroid)), transactionTableModelDebit.getRowCount() - 1, 1);
	    } else if ((Opening_Balance_Profit_Loss == 0 && current_Period_Loss_Profit == 1) || (Opening_Balance_Profit_Loss == 1 && current_Period_Loss_Profit == 0)) {
		transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Opening_Balance_for_Display - return_Current_Peroid)), transactionTableModelCredit.getRowCount() - 1, 1);
	    }

	    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	    transactionTableModelDebit.setValueAt("Opening_Balance", transactionTableModelDebit.getRowCount() - 1, 0);
	    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Opening_Balance_for_Display)), transactionTableModelDebit.getRowCount() - 1, 1);

	    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	    transactionTableModelDebit.setValueAt("Current Period", transactionTableModelDebit.getRowCount() - 1, 0);
	    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Math.abs(return_Current_Peroid)), transactionTableModelDebit.getRowCount() - 1, 1);
	}
	}
	setallBL_Zero();
	temp_Current_Period_Profit_Loss = 0;
	temp_Opening_Balance_Profit_Loss = 0;
//////////////////////////////////////////////////////////////////////////////////////////////////       

//        transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//        transactionTableModelCredit.setValueAt("Closing Stock", transactionTableModelCredit.getRowCount() - 1, 0);
//        transactionTableModelCredit.setValueAt(finalPofitAndLossClosingTotal, transactionTableModelCredit.getRowCount() - 1, 1);

//        setNetExpenditureAmount();
//        setNetIncomeAmount();
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
    private JLabel labelNetExpenditure;
    private JLabel labelNetExpenditureAmount;
    private JLabel labelNetIncome;
    private JLabel labelNetIncomeAmount;
    private JTextArea jTextAreaTempWindowSale;
    private JTextArea jTextAreaTempWindowPurchase;
    private JButton buttonShow;
    private com.toedter.calendar.JDateChooser fromJDateChooser;
    private com.toedter.calendar.JDateChooser toJDateChooser;
    private JTable jTableDebit;
    private JTable jTableCredit;
    private DefaultTableModel transactionTableModelDebit;
    private DefaultTableModel transactionTableModelCredit;

    private void setNetExpenditureAmount() throws SQLException, IOException {
	double netExpenditureAmount = 0D;

	int row = transactionTableModelDebit.getRowCount();

	for (int i = 0; i < row; i++) {

	    netExpenditureAmount += Double.parseDouble(transactionTableModelDebit.getValueAt(i, 1).toString());

	}

	labelNetExpenditureAmount.setText("" + netExpenditureAmount);
    }

    private void setNetIncomeAmount() throws SQLException, IOException {
	double netExpenditureAmount = 0D;

	int row = transactionTableModelCredit.getRowCount();

	for (int i = 0; i < row; i++) {

	    netExpenditureAmount += Double.parseDouble(transactionTableModelCredit.getValueAt(i, 1).toString());

	}

	labelNetIncomeAmount.setText("" + netExpenditureAmount);
    }

   /* public static void main(String args[]) {
	try {
	    javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
	    BalanceSheetForm p = new BalanceSheetForm("",desktopMain.getPreferredSize());
	    p.setVisible(true);
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	} catch (UnsupportedLookAndFeelException ex) {
	    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	}
    }*/
    private void setallBL_Zero() {
	PL_total_opening_Stock = 0D;
	PL_Total_Credit_beforec_o = 0D;
	PL_Total_debit_beforec_o = 0D;
	PL_Closing_Stock = 0D;
	PL_Gross_Profit_co = 0D;
	PL_Gross_Loss_co = 0D;
	PL_Total_Credit_Before_Grossco = 0D;
	PL_Total_debit__Before_Grossco = 0D;
	PL_Total_Credit_Indirect_Value = 0D;
	PL_Total_debit_Indirect_Value = 0D;
	PL_TotalCredit_After_TotalValue = 0D;
	PL_TotalDebit_After_TotalValue = 0D;
	PL_Net_Loss_Profit = 0D;
	Gross_Profit_co = 0D;
	Gross_Loss_co = 0D;
    }

    // 1set credit or debit side except group Income (Indirect) and "Expense (Indirect)"
    public void set_credit_debit_except_Indirect_Group(List<TrialBalanceDTO> trialBalanceDTOList_Profit) {
	for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList_Profit) {
	    if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    PL_Total_Credit_beforec_o = PL_Total_Credit_beforec_o + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    PL_Total_debit_beforec_o = PL_Total_debit_beforec_o + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    }
	}
    }

    // 2set Closing Balance of stock item
    public void set_closing_Balance() {
	PL_Closing_Stock = finalPofitAndLossClosingTotal;
    }

    //// 3calculate Total Credit Value for Gross_Profit_co or Gross_Loss_co
    public Double Total_Credit_Value_for_Gross_Profit_Loss_co() {
	Double credit_tableValue = 0D;
	for (int i = 0; i < transactionTableModelCredit.getRowCount(); i++) {
	    if (!transactionTableModelCredit.getValueAt(i, 1).equals("null")) {
		credit_tableValue = credit_tableValue + Double.parseDouble(transactionTableModelCredit.getValueAt(i, 1).toString());
	    }
	}
	return credit_tableValue;
    }

    //// 3calculate Total Debit for Gross_Profit_co or Gross_Loss_co
    public Double Total_Debit_Value_for_Gross_Profit_Loss_co() {
	Double debit_tableValue = 0D;
	for (int i = 0; i < transactionTableModelDebit.getRowCount(); i++) {
	    if (!transactionTableModelDebit.getValueAt(i, 1).equals("null")) {
		debit_tableValue = debit_tableValue + Double.parseDouble(transactionTableModelDebit.getValueAt(i, 1).toString());
	    }
	}
	return debit_tableValue;
    }

    /// 4calculate Gross_Profit_co or Gross_Loss_co and add in respective table
    public int cal_Gross_Profit_Loss_co(Double credit_tableValue, Double debit_tableValue) {
	int check_ProfitOrLoss = 0;
	Gross_Profit_co = 0D;
	Gross_Loss_co = 0D;
	if (credit_tableValue > debit_tableValue) {
	    Gross_Profit_co = credit_tableValue - debit_tableValue;
	    //  PL_Gross_Profit_co = Gross_Profit_co;
	    check_ProfitOrLoss = 1;
	} else {
	    Gross_Loss_co = debit_tableValue - credit_tableValue;
	    // PL_Gross_Loss_co = Gross_Loss_co;
	    check_ProfitOrLoss = 2;
	}
	return check_ProfitOrLoss;
    }

    // 5calculate total of credit with addition Gross_Profit_co or Gross_Loss_co Not Gross_Profit_LOSS_BF
    public Double cal_TotalCredit_With_Gross_Profit_Loss_co() {
	Double total_CreditTableValue = 0D;
	for (int i = 0; i < transactionTableModelCredit.getRowCount(); i++) {
	    if (!transactionTableModelCredit.getValueAt(i, 1).equals("null")) {
		total_CreditTableValue = total_CreditTableValue + Double.parseDouble(transactionTableModelCredit.getValueAt(i, 1).toString());
	    }
	}
	transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(total_CreditTableValue), transactionTableModelCredit.getRowCount() - 1, 1);
	transactionTableModelCredit.setValueAt("Total :", transactionTableModelCredit.getRowCount() - 1, 0);
	return total_CreditTableValue;
    }

    // 5calculate total of debit with addition Gross_Profit_co or Gross_Loss_co Not Gross_Profit_LOSS_BF
    public Double cal_TotalDebit_With_Gross_Profit_Loss_co() {
	Double total_DebitTableVAlue = 0D;
	for (int i = 0; i < transactionTableModelDebit.getRowCount(); i++) {
	    if (!transactionTableModelDebit.getValueAt(i, 1).equals("null")) {
		total_DebitTableVAlue = total_DebitTableVAlue + Double.parseDouble(transactionTableModelDebit.getValueAt(i, 1).toString());
	    }
	}
	transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(total_DebitTableVAlue), transactionTableModelDebit.getRowCount() - 1, 1);
	transactionTableModelDebit.setValueAt("Total :", transactionTableModelDebit.getRowCount() - 1, 0);
	return total_DebitTableVAlue;
    }

    // 6set credit or debit side of group Income (Indirect) and "Expense (Indirect)" 
    public void set_Credit_Debitvalue_For_Indirec_Group(List<TrialBalanceDTO> trialBalanceDTOList_Profit) {
	for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList_Profit) {
	    if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (e.getValue().equalsIgnoreCase("Income (Indirect)") || e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    PL_Total_Credit_Indirect_Value = PL_Total_Credit_Indirect_Value + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (e.getValue().equalsIgnoreCase("Income (Indirect)") || e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    PL_Total_debit_Indirect_Value = PL_Total_debit_Indirect_Value + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    }
	}
    }

    // 7calculate  Gross_Profit_bf or Gross_Loss_bf
    public void setGross_Profit_Loss_CO(int check_ProfitOrLoss, Double Gross_Profit_co, Double Gross_Loss_co) {
	if (check_ProfitOrLoss == 1) {
	    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(Gross_Profit_co), transactionTableModelCredit.getRowCount() - 1, 1);
	    transactionTableModelCredit.setValueAt("Gross Profit b/f", transactionTableModelCredit.getRowCount() - 1, 0);
	} else if (check_ProfitOrLoss == 2) {
	    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(Gross_Loss_co), transactionTableModelDebit.getRowCount() - 1, 1);
	    transactionTableModelDebit.setValueAt("Gross Loss b/f", transactionTableModelDebit.getRowCount() - 1, 0);
	}
    }

    // 8calculate  credit value after total Amount in debit and Credit Table for net or Loss
    public Double calculate_TotalCredit_AfterTotal() {
	Double credit_Value_After_Total = 0D;
	for (int i = 0; i < jTableCredit.getRowCount(); i++) {
	    if (!jTableCredit.getValueAt(i, 0).toString().equals("null")) {
		if (jTableCredit.getValueAt(i, 0).toString().equals("Total :")) {
		    int j = i;
		    for (j = i + 1; j < jTableCredit.getRowCount(); j++) {
			if (jTableCredit.getValueAt(j, 0).toString() != null) {
			    credit_Value_After_Total = Double.parseDouble(jTableCredit.getValueAt(j, 1).toString()) + credit_Value_After_Total;
			}
		    }
		}
	    }
	}
	PL_TotalCredit_After_TotalValue = credit_Value_After_Total;
	// System.out.println("Credit avlue afetr total ::::::::::::::::::::::::::::::::::::::::::::" + credit_Value_After_Total);
	return credit_Value_After_Total;
    }

    //9 calculate debit value after total Amount in debit and Credit Table for net or Loss
    public Double calculate_TotalDebit_AfterTotal() {
	Double debit_Value_After_Total = 0D;
	for (int i = 0; i < jTableDebit.getRowCount(); i++) {
	    if (!jTableDebit.getValueAt(i, 0).toString().equals("null")) {
		if (jTableDebit.getValueAt(i, 0).toString().equals("Total :")) {
		    int j = i;
		    for (j = i + 1; j < jTableDebit.getRowCount(); j++) {
			if (jTableDebit.getValueAt(j, 0).toString() != null) {
			    debit_Value_After_Total = Double.parseDouble(jTableDebit.getValueAt(j, 1).toString()) + debit_Value_After_Total;
			}
		    }
		}
	    }
	}
	PL_TotalDebit_After_TotalValue = debit_Value_After_Total;
	// System.out.println("Debit vlue afetr total ::::::::::::::::::::::::::::::::::::::::::::" + debit_Value_After_Total);
	return debit_Value_After_Total;
    }

    // 9calculate Net Profit or Loss
    public Double calculate_Net_Profit_Loss(Double debit_Value_After_Total, Double credit_Value_After_Total) {
	Double total_Loss_Profit = 0D;
	total_Loss_Profit = debit_Value_After_Total - credit_Value_After_Total;
	//  System.out.println("total_Loss_Profit %$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + total_Loss_Profit);
	if (total_Loss_Profit < 0) {
	    PL_Net_Loss_Profit = -total_Loss_Profit;
	} else if (total_Loss_Profit >= 0) {
	    PL_Net_Loss_Profit = -total_Loss_Profit;
	}
	return total_Loss_Profit;
    }

    private Double calculate_Closing_Balance(String fromDateStr, String toDateStr, int check) {
	Double returnvariable = 0D;
	try {
	    mapGroupNameWithID.clear();
	    mapGroupNameWithID = ProfitAndLossDAO.allGroupNameWithIDs();

	    System.out.println("Passed Date for the value of Check" + fromDateStr + "  " + toDateStr + "   " + check);

	    // calculate opening Balance
	    finalPofitAndLossOpeningTotal = BalanceSheetDAO.getProfitAndLossOpeningBal(fromDateStr, toDateStr, check);

	    // calculate Closing Balance
	    finalPofitAndLossClosingTotal = BalanceSheetDAO.getProfitAndLossClosingBal(fromDateStr, toDateStr, check);

	    // group of ledger's passed 
	    List<String> groupNames_Profit = new ArrayList<String>();
	    groupNames_Profit.add(Constants.SALES_ACCOUNT);
	    groupNames_Profit.add(Constants.PURCHASE_ACCOUNT);
	    groupNames_Profit.add(Constants.INCOME_DIRECT);
	    groupNames_Profit.add(Constants.INCOME_INDIRECT);
	    groupNames_Profit.add(Constants.EXPENSE_DIRECT);
	    groupNames_Profit.add(Constants.EXPENSE_INDIRECT);

	    System.out.println("pASSED dASTE vALUE OTHEDFDFDFDS DFDSFSD SDF" + fromDateStr + "xxxxxxxxxxxx" + toDateStr);


	    // List of trialBalanceDTO with Group passedList and FromDate to ToDate  and Call_For_Profit_Or_Balance_transaction = 5 for callinfg Profit_Loss transaction
	    int Call_For_Profit_Or_Balance_transaction = 5;
	    List<TrialBalanceDTO> trialBalanceDTOList_Profit = BalanceSheetDAO.getGroupTransactions(groupNames_Profit, fromDateStr, toDateStr, check, Call_For_Profit_Or_Balance_transaction);

	    // set opening Balance of stock item
	    PL_total_opening_Stock = finalPofitAndLossOpeningTotal;
	    System.out.println("Value of thee PL_total_opening_Stock" + PL_total_opening_Stock);

	    ///// 1set credit or debit side except group Income (Indirect) and "Expense (Indirect)"
	    set_credit_debit_except_Indirect_Group(trialBalanceDTOList_Profit);
	    System.out.println("Value of thee PL_Total_Credit_beforec_o" + PL_Total_Credit_beforec_o);
	    System.out.println("Value of thee PL_Total_debit_beforec_o" + PL_Total_debit_beforec_o);


	    ///// 2set Closing Balance of stock item 
	    set_closing_Balance();
	    System.out.println("Value of thee PL_Closing_Stock" + PL_Closing_Stock);

	    //// 3calculate Total Credit Value and Total Debit for Gross_Profit_co or Gross_Loss_co
	    //  credit_tableValue = Total_Credit_Value_for_Gross_Profit_Loss_co();
	    //  debit_tableValue = Total_Debit_Value_for_Gross_Profit_Loss_co();
	    PL_Total_Credit_Value_for_Gross_Profit_Loss_co = PL_Total_Credit_beforec_o + PL_Closing_Stock;
	    PL_Total_Debit_Value_for_Gross_Profit_Loss_co = PL_Total_debit_beforec_o;
	    System.out.println("Value of thee PL_Total_Credit_Value_for_Gross_Profit_Loss_co" + PL_Total_Credit_Value_for_Gross_Profit_Loss_co);
	    System.out.println("Value of thee PL_Total_Debit_Value_for_Gross_Profit_Loss_co" + PL_Total_Debit_Value_for_Gross_Profit_Loss_co);

	    //// 4calculate Gross_Profit_co or Gross_Loss_co and add in respective table
	    int check_ProfitOrLoss = 0;
	    check_ProfitOrLoss = cal_Gross_Profit_Loss_co(PL_Total_Credit_Value_for_Gross_Profit_Loss_co, PL_Total_Debit_Value_for_Gross_Profit_Loss_co);

	    PL_Total_Credit_Before_Grossco = PL_Total_Credit_beforec_o + PL_Closing_Stock;
	    PL_Total_debit__Before_Grossco = PL_Total_debit_beforec_o + PL_total_opening_Stock;
	    System.out.println("Value of thee PL_Total_Credit_" + PL_Total_Credit_Before_Grossco);
	    System.out.println("Value of thee PL_Total_debit_" + PL_Total_debit__Before_Grossco);

	    if (PL_Total_Credit_Before_Grossco >= PL_Total_debit__Before_Grossco) {
		PL_Gross_Profit_co = PL_Total_Credit_Before_Grossco - PL_Total_debit__Before_Grossco;
	    } else if (PL_Total_Credit_Before_Grossco < PL_Total_debit__Before_Grossco) {
		PL_Gross_Loss_co = PL_Total_debit__Before_Grossco - PL_Total_Credit_Before_Grossco;
	    }

	    System.out.println("Value of The PL_Gross_Profit_co" + PL_Gross_Profit_co);
	    System.out.println("Value of The PL_Gross_Loss_co" + PL_Gross_Loss_co);

	    // 6set credit or debit side of group Income (Indirect) and "Expense (Indirect)"
	    set_Credit_Debitvalue_For_Indirec_Group(trialBalanceDTOList_Profit);
	    // 7calculate  Gross_Profit_bf or Gross_Loss_bf
	    if (check_ProfitOrLoss == 1) {
		PL_Gross_Loss_bf = PL_Gross_Profit_co;
	    } else if (check_ProfitOrLoss == 2) {
		PL_Gross_Profit_bf = PL_Gross_Loss_co;
	    }
	    //  setGross_Profit_Loss_CO(check_ProfitOrLoss, Gross_Profit_co, Gross_Loss_co);
	    // 8calculate  credit value after total Amount in debit and Credit Table for net or Loss
	    // credit_Value_After_Total = calculate_TotalCredit_AfterTotal();

	    // 8calculate debit value after total Amount in debit and Credit Table for net or Loss
	    //debit_Value_After_Total = calculate_TotalDebit_AfterTotal();

	    PL_TotalCredit_After_TotalValue = PL_Total_Credit_Indirect_Value + PL_Gross_Profit_co;
	    PL_TotalDebit_After_TotalValue = PL_Total_debit_Indirect_Value + PL_Gross_Loss_co;
	    // 9Caluculate Total Net PRofit_Loss
	    Double total_Loss_Profit = calculate_Net_Profit_Loss(PL_TotalDebit_After_TotalValue, PL_TotalCredit_After_TotalValue);
	    System.out.println("----------------------------------------------------------------------------" + returnvariable);

	    if (total_Loss_Profit <= 0) {
		returnvariable = -total_Loss_Profit;
		if (check == 5) {
		    temp_Opening_Balance_Profit_Loss = 1;
		} else if (check == 10) {
		    temp_Current_Period_Profit_Loss = 1;
		}
		System.out.println("Loss OR PROFIT >>>>>>>>>>>>>>>>>>>>>> in returnvariable < 0 --- Profit       " + check + "        " + returnvariable);
	    } else if (total_Loss_Profit > 0) {
		returnvariable = total_Loss_Profit;
		if (check == 5) {
		    temp_Opening_Balance_Profit_Loss = 0;
		} else if (check == 10) {
		    temp_Current_Period_Profit_Loss = 0;
		}
		System.out.println("Loss OR PROFIT >>>>>>>>>>>>>>>>>>>>>> in returnvariable >= 0 ---- LOss       " + check + "        " + returnvariable);
	    }
	    System.out.println("PL_TotalDebit_After_TotalValue ........................." + PL_TotalDebit_After_TotalValue);
	    System.out.println("PL_TotalCredit_After_TotalValue ........................." + PL_TotalDebit_After_TotalValue);
	} catch (SQLException ex) {
	    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(BalanceSheetForm.class.getName()).log(Level.SEVERE, null, ex);
	}
	return returnvariable;
    }
}