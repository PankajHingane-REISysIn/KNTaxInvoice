/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.profitandloss;

import gen.accountvoucher.sale.SaleForm;
import gen.display.report.TrialBalanceDTO;
import gen.dto.Constants;
import gen.dto.GUIConstants;
import gen.dto.Label;
import gen.mainclass.MainClass;
import java.awt.Color;
import java.awt.Container;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class ProfitAndLossForm extends JInternalFrame {

    private Double totalGrossProfit = 0D;
    private ProfitAndLossDTO profitlossDTO;
    static Double finalPofitAndLossOpeningTotal = 0D, finalPofitAndLossClosingTotal = 0D, Gross_Profit_co = 0D, Gross_Loss_co = 0D;
    private String fromDateStr = "";
    private String toDateStr = "";
    private Map<String, String> mapGroupNameWithID = new HashMap<String, String>();
    ////////////// variable for Balance Sheet ///////////////////////////////////////
    Double BL_total_opening_Stock = 0D;
    Double BL_Total_Credit_beforec_o = 0D;
    Double BL_Total_debit_beforec_o = 0D;
    Double Bl_Closing_Stock = 0D;
    Double Bl_Gross_Profit_co = 0D;
    Double Bl_Gross_Loss_co = 0D;
    Double BL_Total_Credit_ = 0D;
    Double BL_Total_debit_ = 0D;
    Double BL_Total_Credit_Indirect_Value = 0D;
    Double BL_Total_debit_Indirect_Value = 0D;
    Double BL_TotalCredit_After_TotalValue = 0D;
    Double BL_TotalDebit_After_TotalValue = 0D;
    Double BL_Net_Loss_Profit = 0D;
    ////////////////////////////////////////////////////////////////////////////////

    public ProfitAndLossForm(String title, Dimension d) {
	initComponents();
	profitlossDTO = new ProfitAndLossDTO();
	//initilize();
	this.setSize(1366, 768);
	//setClosable(true);
    }

    private void initComponents() {

        setClosable(true);
	setFrameIcon(new ImageIcon(SaleForm.class.getResource("/images/Kasturi-logo-1.png")));
	setTitle("Profit and Loss Form");

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
	jMainPanel.setBorder(new TitledBorder(new EtchedBorder(), Label.PROFITANDLOSS_FORM_NAME));
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

	String col1[] = {"Category", "Amount"};
	String data1[][] = {{"", ""}};

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
		    if (fromJDateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(ProfitAndLossForm.this, "Invalid Date Entered");
			fromJDateChooser.requestFocus();
			DefaultTableModel tableModel = (DefaultTableModel) jTableDebit.getModel();
			tableModel.setRowCount(0);
			DefaultTableModel tableModel1 = (DefaultTableModel) jTableCredit.getModel();
			tableModel1.setRowCount(0);
		    } else if (toJDateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(ProfitAndLossForm.this, "Invalid Date Entered");
			toJDateChooser.requestFocus();
			DefaultTableModel tableModel = (DefaultTableModel) jTableDebit.getModel();
			tableModel.setRowCount(0);
			DefaultTableModel tableModel1 = (DefaultTableModel) jTableCredit.getModel();
			tableModel1.setRowCount(0);
		    } else if (fromJDateChooser.getDate().getTime() > toJDateChooser.getDate().getTime()) {
			JOptionPane.showMessageDialog(ProfitAndLossForm.this, "Invalid Date selection");
			fromJDateChooser.requestFocus();
			DefaultTableModel tableModel = (DefaultTableModel) jTableDebit.getModel();
			tableModel.setRowCount(0);
			DefaultTableModel tableModel1 = (DefaultTableModel) jTableCredit.getModel();
			tableModel1.setRowCount(0);
		    } else {
		    List<ProfitAndLossDTO> tempDataList = new ArrayList<ProfitAndLossDTO>();
		    tempDataList = ProfitAndLossDAO.getAllDataTemp();

		    for (ProfitAndLossDTO palDTO : tempDataList) {

			jTextAreaTempWindowSale.setText("Sale Items     :" + palDTO.getSaleItems() + "\n" + "Expected Gain:" + palDTO.getTotalAmountThroughSale() + "\n" + "Amount Received:" + palDTO.getProfitSALE() + "\n" + "Gross Profit     :" + palDTO.getNetProfitSALE());
			jTextAreaTempWindowPurchase.setText("PurchaseItems :" + palDTO.getPurchaseItems() + "\n" + "Expected Expenditure:" + palDTO.getTotalAmountThroughPurchase() + "\n" + "Amount Spent:" + palDTO.getProfitPURCHASE() + "\n" + "Gross Loss     :" + palDTO.getNetProfitPURCHASE());

			totalGrossProfit = palDTO.getNetProfitSALE() + palDTO.getNetProfitPURCHASE();
		    }

		    // all calculation method       
		    initilize();
		    }
		} catch (IOException ex) {
		    Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
		    Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	});
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
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                // throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
            
             @Override
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                try {
                    formInternalFrameActivated(evt);
                } catch (SQLException ex) {
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

    public Double initilize() throws SQLException, IOException {

	String toDateCloseStr = "";
	String fromDateOpenStr = "";
	if (fromJDateChooser.getDate() != null) {
	    try {
		fromDateStr = Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate());
		Calendar c = Calendar.getInstance();
		c.setTime(Constants.simpleDateFormatDatabaseWithDash.parse(Constants.simpleDateFormatDatabaseWithDash.format(fromJDateChooser.getDate())));
		// c.add(Calendar.DATE, -1);  // number of days to add
		fromDateOpenStr = Constants.simpleDateFormatDatabaseWithDash.format(c.getTime());
	    } catch (ParseException ex) {
		Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
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
		Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	// calculate opening Balance
	finalPofitAndLossOpeningTotal = ProfitAndLossDAO.getProfitAndLossOpeningBal(fromDateOpenStr, toDateCloseStr);

	// calculate Closing Balance
	finalPofitAndLossClosingTotal = ProfitAndLossDAO.getProfitAndLossClosingBal(toDateStr, toDateStr);

	mapGroupNameWithID.clear();
	mapGroupNameWithID = ProfitAndLossDAO.allGroupNameWithIDs();

	transactionTableModelCredit.setRowCount(0);
	transactionTableModelDebit.setRowCount(0);

	ProfitAndLossDAO.getGroupsHierarchy();

	// group of ledger's passed 
	List<String> groupNames = new ArrayList<String>();
	groupNames.add(Constants.SALES_ACCOUNT);
	groupNames.add(Constants.PURCHASE_ACCOUNT);
	groupNames.add(Constants.INCOME_DIRECT);
	groupNames.add(Constants.INCOME_INDIRECT);
	groupNames.add(Constants.EXPENSE_DIRECT);
	groupNames.add(Constants.EXPENSE_INDIRECT);

	// List of trialBalanceDTO with Group passedList and FromDate to ToDate 
	List<TrialBalanceDTO> trialBalanceDTOList = ProfitAndLossDAO.getGroupTransactions(groupNames, fromDateStr, toDateStr);

	// set opening Balance of stock item
	transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	transactionTableModelDebit.setValueAt("Opening Stock", transactionTableModelDebit.getRowCount() - 1, 0);
	transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(finalPofitAndLossOpeningTotal), transactionTableModelDebit.getRowCount() - 1, 1);
	BL_total_opening_Stock = finalPofitAndLossOpeningTotal;

	///// 1set credit or debit side except group Income (Indirect) and "Expense (Indirect)"
	set_credit_debit_except_Indirect_Group(trialBalanceDTOList);

	///// 2set Closing Balance of stock item 
	set_closing_Balance();

	//// 3calculate Total Credit Value and Total Debit for Gross_Profit_co or Gross_Loss_co
	Double credit_tableValue = 0D;
	Double debit_tableValue = 0D;
	credit_tableValue = Total_Credit_Value_for_Gross_Profit_Loss_co();
	debit_tableValue = Total_Debit_Value_for_Gross_Profit_Loss_co();

	//// 4calculate Gross_Profit_co or Gross_Loss_co and add in respective table
	int check_ProfitOrLoss = 0;
	check_ProfitOrLoss = cal_Gross_Profit_Loss_co(credit_tableValue, debit_tableValue);

	BL_Total_Credit_ = BL_Total_Credit_beforec_o + Bl_Closing_Stock + Bl_Gross_Loss_co;
	BL_Total_debit_ = BL_Total_debit_beforec_o + BL_total_opening_Stock + Bl_Gross_Profit_co;


	// 5calculate total of credit with addition Gross_Profit_co or Gross_Loss_co
	Double total_CreditTableValue = cal_TotalCredit_With_Gross_Profit_Loss_co();

	// 5calculate total of debit with addition Gross_Profit_co or Gross_Loss_co
	Double total_DebitTableVAlue = cal_TotalDebit_With_Gross_Profit_Loss_co();

	// 6set credit or debit side of group Income (Indirect) and "Expense (Indirect)"
	set_Credit_Debitvalue_For_Indirec_Group(trialBalanceDTOList);
	System.out.println("");
	System.out.println("Differencesdsdf Crdit" + BL_Total_Credit_Indirect_Value);
	System.out.println("Differencesdsdf Debitr " + BL_Total_debit_Indirect_Value);

	// 7calculate  Gross_Profit_bf or Gross_Loss_bf
	setGross_Profit_Loss_CO(check_ProfitOrLoss, Gross_Profit_co, Gross_Loss_co);

	Double debit_Value_After_Total = 0D;
	Double credit_Value_After_Total = 0D;
	// 8calculate  credit value after total Amount in debit and Credit Table for net or Loss
	credit_Value_After_Total = calculate_TotalCredit_AfterTotal();

	// 8calculate debit value after total Amount in debit and Credit Table for net or Loss
	debit_Value_After_Total = calculate_TotalDebit_AfterTotal();

	// 9Caluculate Total Net PRofit_Loss
	Double total_Loss_Profit = calculate_Net_Profit_Loss(debit_Value_After_Total, credit_Value_After_Total);
	Double returnvariable = 0D;
	if (total_Loss_Profit < 0) {
	    returnvariable = -total_Loss_Profit;
	} else if (total_Loss_Profit >= 0) {
	    returnvariable = total_Loss_Profit;
	}

	setallBL_Zero();

////////////////////////////// instead of below Code ew are now Using Functions (BY Atul)///////////////////////////////////   
//       Double credit_tableValue = 0D;
//       Double debit_tableValue = 0D;
//        
//        for (int i = 0; i < transactionTableModelCredit.getRowCount(); i++) {
//            if (!transactionTableModelCredit.getValueAt(i, 1).equals("null")) {
//                credit_tableValue = credit_tableValue + Double.parseDouble(transactionTableModelCredit.getValueAt(i, 1).toString());
//            }
//        }
//        for (int i = 0; i < transactionTableModelDebit.getRowCount(); i++) {
//            if (!transactionTableModelDebit.getValueAt(i, 1).equals("null")) {
//                debit_tableValue = debit_tableValue + Double.parseDouble(transactionTableModelDebit.getValueAt(i, 1).toString());
//            }
//        }
//        // set credit or debit side except group Income (Indirect) and "Expense (Indirect)"
//        for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList) {
//            if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
//                for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
//                    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
//                        if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
//                            transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//                            transactionTableModelCredit.setValueAt(e.getValue(), transactionTableModelCredit.getRowCount() - 1, 0);
//                            transactionTableModelCredit.setValueAt(trialBalanceDTO.getCredit_Debit_Difference(), transactionTableModelCredit.getRowCount() - 1, 1);
//                            BL_Total_Credit_beforec_o = BL_Total_Credit_beforec_o + trialBalanceDTO.getCredit_Debit_Difference();
//                        }
//                    }
//                }
//            } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
//                for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
//                    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
//                        if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
//                            transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//                            transactionTableModelDebit.setValueAt(e.getValue(), transactionTableModelDebit.getRowCount() - 1, 0);
//                            transactionTableModelDebit.setValueAt(trialBalanceDTO.getCredit_Debit_Difference(), transactionTableModelDebit.getRowCount() - 1, 1);
//                            BL_Total_debit_beforec_o = BL_Total_debit_beforec_o + trialBalanceDTO.getCredit_Debit_Difference();
//                        }
//                    }
//                }
//            }
//        }

	//         /// calculate Gross_Profit_co or Gross_Loss_co and add in respective table
//        int check_ProfitOrLoss = 0;
//        Double Gross_Profit_co = 0D;
//        Double Gross_Loss_co = 0D;
//        if (credit_tableValue > debit_tableValue) {
//            transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//            transactionTableModelDebit.setValueAt(credit_tableValue - debit_tableValue, transactionTableModelDebit.getRowCount() - 1, 1);
//            transactionTableModelDebit.setValueAt("Gross Profit c/o", transactionTableModelDebit.getRowCount() - 1, 0);
//            Gross_Profit_co = credit_tableValue - debit_tableValue;
//            Bl_Gross_Profit_co = Gross_Profit_co;
//            check_ProfitOrLoss = 1;
//        } else {
//            transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//            transactionTableModelCredit.setValueAt(debit_tableValue - credit_tableValue, transactionTableModelCredit.getRowCount() - 1, 1);
//            transactionTableModelCredit.setValueAt("Gross Loss c/o", transactionTableModelCredit.getRowCount() - 1, 0);
//            Gross_Loss_co = debit_tableValue - credit_tableValue;
//            Bl_Gross_Loss_co = Gross_Loss_co;
//            check_ProfitOrLoss = 2;
//        }


	// calculate total of credit with addition Gross_Profit_co or Gross_Loss_co
//        Double total_CreditTableValue1 = 0D;
//        Double total_DebitTableVAlue = 0D;
//
//        for (int i = 0; i < transactionTableModelCredit.getRowCount(); i++) {
//            if (!transactionTableModelCredit.getValueAt(i, 1).equals("null")) {
//                total_CreditTableValue = total_CreditTableValue + Double.parseDouble(transactionTableModelCredit.getValueAt(i, 1).toString());
//            }
//        }
//        transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//        transactionTableModelCredit.setValueAt(total_CreditTableValue, transactionTableModelCredit.getRowCount() - 1, 1);
//        transactionTableModelCredit.setValueAt("Total :", transactionTableModelCredit.getRowCount() - 1, 0);
//
//        // calculate total of debit with addition Gross_Profit_co or Gross_Loss_co
//        for (int i = 0; i < transactionTableModelDebit.getRowCount(); i++) {
//            if (!transactionTableModelDebit.getValueAt(i, 1).equals("null")) {
//                total_DebitTableVAlue = total_DebitTableVAlue + Double.parseDouble(transactionTableModelDebit.getValueAt(i, 1).toString());
//            }
//        }
//        transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//        transactionTableModelDebit.setValueAt(total_DebitTableVAlue, transactionTableModelDebit.getRowCount() - 1, 1);
//        transactionTableModelDebit.setValueAt("Total :", transactionTableModelDebit.getRowCount() - 1, 0);



	// set credit or debit side of group Income (Indirect) and "Expense (Indirect)"        
//         for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList) {
//            if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
//                for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
//                    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
//                        if (e.getValue().equalsIgnoreCase("Income (Indirect)") || e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
//                            transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//                            transactionTableModelCredit.setValueAt(e.getValue(), transactionTableModelCredit.getRowCount() - 1, 0);
//                            transactionTableModelCredit.setValueAt(trialBalanceDTO.getCredit_Debit_Difference(), transactionTableModelCredit.getRowCount() - 1, 1);
//                            BL_Total_Credit_Indirect_Value = BL_Total_Credit_Indirect_Value + trialBalanceDTO.getCredit_Debit_Difference();
//                        }
//                    }
//                }
//            } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
//                for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
//                    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
//                        if (e.getValue().equalsIgnoreCase("Income (Indirect)") || e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
//                            transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//                            transactionTableModelDebit.setValueAt(e.getValue(), transactionTableModelDebit.getRowCount() - 1, 0);
//                            transactionTableModelDebit.setValueAt(trialBalanceDTO.getCredit_Debit_Difference(), transactionTableModelDebit.getRowCount() - 1, 1);
//                            BL_Total_debit_Indirect_Value = BL_Total_debit_Indirect_Value + trialBalanceDTO.getCredit_Debit_Difference();
//                        }
//                    }
//                }
//            }
//        }


//        // calculate  Gross_Profit_bf or Gross_Loss_bf
//        if (check_ProfitOrLoss == 1) {
//            transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//            transactionTableModelCredit.setValueAt(Gross_Profit_co, transactionTableModelCredit.getRowCount() - 1, 1);
//            transactionTableModelCredit.setValueAt("Gross Profit b/f", transactionTableModelCredit.getRowCount() - 1, 0);
//        } else if (check_ProfitOrLoss == 2) {
//            transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//            transactionTableModelDebit.setValueAt(Gross_Loss_co, transactionTableModelDebit.getRowCount() - 1, 1);
//            transactionTableModelDebit.setValueAt("Gross Loss b/f", transactionTableModelDebit.getRowCount() - 1, 0);
//        }

	//        // calculate  credit value after total Amount in debit and Credit Table for net or Loss
//        Double credit_Value_After_Total = 0D;
//        Double debit_Value_After_Total = 0D;
//       
//        for (int i = 0; i < jTableCredit.getRowCount(); i++) {
//            if (!jTableCredit.getValueAt(i, 0).toString().equals("null")) {
//                if (jTableCredit.getValueAt(i, 0).toString().equals("Total :")) {
//                    int j = i;
//                    for (j = i + 1; j < jTableCredit.getRowCount(); j++) {
//                        if (jTableCredit.getValueAt(j, 0).toString() != null) {
//                            credit_Value_After_Total = Double.parseDouble(jTableCredit.getValueAt(j, 1).toString()) + credit_Value_After_Total;
//                        }
//                    }
//                }
//            }
//        }
//         BL_TotalCredit_After_TotalValue = credit_Value_After_Total;
//        System.out.println("Credit avlue afetr total ::::::::::::::::::::::::::::::::::::::::::::" + credit_Value_After_Total);

//        // calculate debit value after total Amount in debit and Credit Table for net or Loss
//        for (int i = 0; i < jTableDebit.getRowCount(); i++) {
//            if (!jTableDebit.getValueAt(i, 0).toString().equals("null")) {
//                if (jTableDebit.getValueAt(i, 0).toString().equals("Total :")) {
//                    int j = i;
//                    for (j = i + 1; j < jTableDebit.getRowCount(); j++) {
//                        if (jTableDebit.getValueAt(j, 0).toString() != null) {
//                            debit_Value_After_Total = Double.parseDouble(jTableDebit.getValueAt(j, 1).toString()) + debit_Value_After_Total;
//                        }
//                    }
//                }
//            }
//        }
//        BL_TotalDebit_After_TotalValue = debit_Value_After_Total;
//        System.out.println("Debit vlue afetr total ::::::::::::::::::::::::::::::::::::::::::::" + debit_Value_After_Total);

//        // calculate Net Profit or Loss
//        Double total_Loss_Profit = 0D;
//        total_Loss_Profit = debit_Value_After_Total - credit_Value_After_Total;
//        BL_Net_Loss_Profit = total_Loss_Profit;
//        System.out.println("total_Loss_Profit %$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + total_Loss_Profit);
//        if (total_Loss_Profit < 0) {
//            transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//            transactionTableModelDebit.setValueAt(-total_Loss_Profit, transactionTableModelDebit.getRowCount() - 1, 1);
//            transactionTableModelDebit.setValueAt("Net Profit", transactionTableModelDebit.getRowCount() - 1, 0);
//        } else if (total_Loss_Profit >= 0) {
//            transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//            transactionTableModelCredit.setValueAt(total_Loss_Profit, transactionTableModelCredit.getRowCount() - 1, 1);
//            transactionTableModelCredit.setValueAt("Net Loss", transactionTableModelCredit.getRowCount() - 1, 0);
//        }

////////////////////////////// instead of Above Code ew are now Using Functions (BY Atul) ///////////////////////////////////        


//////////////// sudeep Code down ////////////////////////////////////////
//        if (debitGroupList != null && !debitGroupList.isEmpty()) {
//            for (ProfitAndLossDTO profitAndLossDTO : debitGroupList) {
//                if (profitAndLossDTO.getTrans_Type() == 1 || profitAndLossDTO.getTrans_Type() == 4) {
//                    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//                    transactionTableModelDebit.setValueAt(profitAndLossDTO.getGroupName(), transactionTableModelDebit.getRowCount() - 1, 0);
//                    transactionTableModelDebit.setValueAt(profitAndLossDTO.getBalance(), transactionTableModelDebit.getRowCount() - 1, 1);
//                } else if (profitAndLossDTO.getTrans_Type() ==2 || profitAndLossDTO.getTrans_Type() == 2) {
//                    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//                    transactionTableModelCredit.setValueAt(profitAndLossDTO.getGroupName(), transactionTableModelCredit.getRowCount() - 1, 0);
//                    transactionTableModelCredit.setValueAt(profitAndLossDTO.getBalance(), transactionTableModelCredit.getRowCount() - 1, 1);
//                }
//            }
//
//        }

	//        if (labelNetIncomeAmount.getText().compareTo(labelNetExpenditureAmount.getText()) == 0) {
//            if (totalGrossProfit >= 0) {
//                transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//                transactionTableModelDebit.setValueAt("Gross Profit", transactionTableModelDebit.getRowCount() - 1, 0);
//                transactionTableModelDebit.setValueAt(Math.abs(totalGrossProfit), transactionTableModelDebit.getRowCount() - 1, 1);
//            }
//            if (totalGrossProfit <= 0) {
//                transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//                transactionTableModelCredit.setValueAt("Gross Loss", transactionTableModelCredit.getRowCount() - 1, 0);
//                transactionTableModelCredit.setValueAt(Math.abs(totalGrossProfit), transactionTableModelCredit.getRowCount() - 1, 1);
//            }
//        } else {
//            if (totalGrossProfit >= 0) {
//                transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
//                transactionTableModelDebit.setValueAt("Gross Profit", transactionTableModelDebit.getRowCount() - 1, 0);
//                transactionTableModelDebit.setValueAt(Math.abs(totalGrossProfit), transactionTableModelDebit.getRowCount() - 1, 1);
//            }
//            if (totalGrossProfit <= 0) {
//                transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
//                transactionTableModelCredit.setValueAt("Gross Loss", transactionTableModelCredit.getRowCount() - 1, 0);
//                transactionTableModelCredit.setValueAt(Math.abs(totalGrossProfit), transactionTableModelCredit.getRowCount() - 1, 1);
//            }
//        }

//////////////// sudeep Code up ////////////////////////////////////////        



	setNetExpenditureAmount();
	setNetIncomeAmount();
	return returnvariable;
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

    /*public static void main(String args[]) {
     try {
     javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
     ProfitAndLossForm p = new ProfitAndLossForm();
     p.setVisible(true);
     } catch (ClassNotFoundException ex) {
     Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
     } catch (InstantiationException ex) {
     Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IllegalAccessException ex) {
     Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
     } catch (UnsupportedLookAndFeelException ex) {
     Logger.getLogger(ProfitAndLossForm.class.getName()).log(Level.SEVERE, null, ex);
     }
     }*/
    private void setallBL_Zero() {
	BL_total_opening_Stock = 0D;
	BL_Total_Credit_beforec_o = 0D;
	BL_Total_debit_beforec_o = 0D;
	Bl_Closing_Stock = 0D;
	Bl_Gross_Profit_co = 0D;
	Bl_Gross_Loss_co = 0D;
	BL_Total_Credit_ = 0D;
	BL_Total_debit_ = 0D;
	BL_Total_Credit_Indirect_Value = 0D;
	BL_Total_debit_Indirect_Value = 0D;
	BL_TotalCredit_After_TotalValue = 0D;
	BL_TotalDebit_After_TotalValue = 0D;
	BL_Net_Loss_Profit = 0D;
    }

    // 1set credit or debit side except group Income (Indirect) and "Expense (Indirect)"
    public void set_credit_debit_except_Indirect_Group(List<TrialBalanceDTO> trialBalanceDTOList) {
	for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList) {
	    if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
			    transactionTableModelCredit.setValueAt(e.getValue(), transactionTableModelCredit.getRowCount() - 1, 0);
			    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getCredit_Debit_Difference()), transactionTableModelCredit.getRowCount() - 1, 1);
			    BL_Total_Credit_beforec_o = BL_Total_Credit_beforec_o + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (!e.getValue().equalsIgnoreCase("Income (Indirect)") && !e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
			    transactionTableModelDebit.setValueAt(e.getValue(), transactionTableModelDebit.getRowCount() - 1, 0);
			    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getCredit_Debit_Difference()), transactionTableModelDebit.getRowCount() - 1, 1);
			    BL_Total_debit_beforec_o = BL_Total_debit_beforec_o + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    }
	}
    }

    // 2set Closing Balance of stock item
    public void set_closing_Balance() {
	transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	transactionTableModelCredit.setValueAt("Closing Stock", transactionTableModelCredit.getRowCount() - 1, 0);
	transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(finalPofitAndLossClosingTotal), transactionTableModelCredit.getRowCount() - 1, 1);
	Bl_Closing_Stock = finalPofitAndLossClosingTotal;
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
	    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(credit_tableValue - debit_tableValue), transactionTableModelDebit.getRowCount() - 1, 1);
	    transactionTableModelDebit.setValueAt("Gross Profit c/o", transactionTableModelDebit.getRowCount() - 1, 0);
	    Gross_Profit_co = credit_tableValue - debit_tableValue;
	    Bl_Gross_Profit_co = Gross_Profit_co;
	    check_ProfitOrLoss = 1;
	} else {
	    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(debit_tableValue - credit_tableValue), transactionTableModelCredit.getRowCount() - 1, 1);
	    transactionTableModelCredit.setValueAt("Gross Loss c/o", transactionTableModelCredit.getRowCount() - 1, 0);
	    Gross_Loss_co = debit_tableValue - credit_tableValue;
	    Bl_Gross_Loss_co = Gross_Loss_co;
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
    public void set_Credit_Debitvalue_For_Indirec_Group(List<TrialBalanceDTO> trialBalanceDTOList) {
	for (TrialBalanceDTO trialBalanceDTO : trialBalanceDTOList) {
	    if (trialBalanceDTO.getTrans_Type() == Constants.CREDIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (e.getValue().equalsIgnoreCase("Income (Indirect)") || e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
			    transactionTableModelCredit.setValueAt(e.getValue(), transactionTableModelCredit.getRowCount() - 1, 0);
			    transactionTableModelCredit.setValueAt(Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getCredit_Debit_Difference()), transactionTableModelCredit.getRowCount() - 1, 1);
			    BL_Total_Credit_Indirect_Value = BL_Total_Credit_Indirect_Value + trialBalanceDTO.getCredit_Debit_Difference();
			}
		    }
		}
	    } else if (trialBalanceDTO.getTrans_Type() == Constants.DEBIT) {
		for (Map.Entry<String, String> e : mapGroupNameWithID.entrySet()) {
		    if (trialBalanceDTO.getCoreGroup_id() == Long.parseLong(e.getKey())) {
			if (e.getValue().equalsIgnoreCase("Income (Indirect)") || e.getValue().equalsIgnoreCase("Expense (Indirect)")) {
			    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
			    transactionTableModelDebit.setValueAt(e.getValue(), transactionTableModelDebit.getRowCount() - 1, 0);
			    transactionTableModelDebit.setValueAt(Constants.DECIMAL_FORMAT.format(trialBalanceDTO.getCredit_Debit_Difference()), transactionTableModelDebit.getRowCount() - 1, 1);
			    BL_Total_debit_Indirect_Value = BL_Total_debit_Indirect_Value + trialBalanceDTO.getCredit_Debit_Difference();
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
	BL_TotalCredit_After_TotalValue = credit_Value_After_Total;
	System.out.println("Credit avlue afetr total ::::::::::::::::::::::::::::::::::::::::::::" + credit_Value_After_Total);
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
	BL_TotalDebit_After_TotalValue = debit_Value_After_Total;
	System.out.println("Debit vlue afetr total ::::::::::::::::::::::::::::::::::::::::::::" + debit_Value_After_Total);
	return debit_Value_After_Total;
    }

    // 9calculate Net Profit or Loss
    public Double calculate_Net_Profit_Loss(Double debit_Value_After_Total, Double credit_Value_After_Total) {
	Double total_Loss_Profit = 0D;
	total_Loss_Profit = debit_Value_After_Total - credit_Value_After_Total;
	BL_Net_Loss_Profit = total_Loss_Profit;
	System.out.println("total_Loss_Profit %$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + total_Loss_Profit);
	if (total_Loss_Profit < 0) {
	    transactionTableModelDebit.setRowCount(transactionTableModelDebit.getRowCount() + 1);
	    transactionTableModelDebit.setValueAt(-total_Loss_Profit, transactionTableModelDebit.getRowCount() - 1, 1);
	    transactionTableModelDebit.setValueAt("Net Profit", transactionTableModelDebit.getRowCount() - 1, 0);
	} else if (total_Loss_Profit >= 0) {
	    transactionTableModelCredit.setRowCount(transactionTableModelCredit.getRowCount() + 1);
	    transactionTableModelCredit.setValueAt(total_Loss_Profit, transactionTableModelCredit.getRowCount() - 1, 1);
	    transactionTableModelCredit.setValueAt("Net Loss", transactionTableModelCredit.getRowCount() - 1, 0);
	}
	return total_Loss_Profit;
    }
}