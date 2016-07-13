/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp.selectImport;

/**
 *
 * @author admin
 */
import gen.dto.Constants;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class CopyPasteOperation extends JInternalFrame {

    private JTable table;
    DefaultTableModel dtm;
    Object colNames[] = {"", "Voucher Type", "Receipt No"};
    Object[][] datarow = {};
    Map<String, Object> pass_objectMap = new HashMap<String, Object>();
    Map<String, Set<String>> user_Selected_ReceiptNoMap = new HashMap<String, Set<String>>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Map<String, Object> pass_objectList = new HashMap<String, Object>();
		    CopyPasteOperation frame = new CopyPasteOperation(pass_objectList);
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public CopyPasteOperation(Map<String, Object> pass_objectList) {

	try {
	    initComponents();
	    this.pass_objectMap = pass_objectList;
	    initilize();
	    setClosable(true);
	} catch (Exception ex) {
	    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
	    ex.printStackTrace();
	    JOptionPane.showMessageDialog(CopyPasteOperation.this, ex.getMessage());
	}
    }

    private void initComponents() {
	try {
	    setSelected(true);
	} catch (PropertyVetoException e) {
	    e.printStackTrace();
	}
	setResizable(true);
	setTitle("Data Import Options");
	setFrameIcon(new ImageIcon(CopyPasteOperation.class.getResource("/IMages/Kasturi-logo-1.png")));
	setClosable(true);
	setBounds(100, 100, 791, 539);

	JPanel jMainPanel = new JPanel();
	getContentPane().add(jMainPanel, BorderLayout.CENTER);

	JPanel groupInformationPanel = new JPanel();
	groupInformationPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Data Import Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));

	JPanel controlsPanel = new JPanel();
	controlsPanel.setBorder(new TitledBorder(null, "Controls",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	GroupLayout gl_jMainPanel = new GroupLayout(jMainPanel);
	gl_jMainPanel.setHorizontalGroup(
		gl_jMainPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_jMainPanel.createSequentialGroup()
		.addGroup(gl_jMainPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_jMainPanel.createSequentialGroup()
		.addGap(14)
		.addComponent(groupInformationPanel, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE))
		.addGroup(gl_jMainPanel.createSequentialGroup()
		.addContainerGap()
		.addComponent(controlsPanel, GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)))
		.addContainerGap()));
	gl_jMainPanel.setVerticalGroup(
		gl_jMainPanel.createParallelGroup(Alignment.TRAILING)
		.addGroup(gl_jMainPanel.createSequentialGroup()
		.addContainerGap()
		.addComponent(groupInformationPanel, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
		.addPreferredGap(ComponentPlacement.RELATED)
		.addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
		.addGap(10)));
	groupInformationPanel.setLayout(new MigLayout("", "[100px:100px:100px,grow,fill][25px:25px:25px,grow,shrink 50,fill][grow,fill][0px:25px:25px,grow,shrink 50,fill][0px:100px:100px,grow,shrink 50,fill]", "[grow][][][][][][][10px:10px:10px,grow,fill][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][10px:10px:10px,grow,fill][][][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][][10px:10px:10px,grow,fill][]"));

	JScrollPane scrollPane = new JScrollPane();
	groupInformationPanel.add(scrollPane, "cell 2 0 1 25,grow");

//	Object colNames[] = new String[]{"Voucher Type", "Voucher Number", "Mark"};;
//	Object[][] data = new Object[][]{};
	dtm = new DefaultTableModel(datarow, colNames);
	table = new JTable(dtm);

//	table.setModel(new DefaultTableModel(
//		new Object[][]{},
//		new String[]{
//		    "Voucher Type", "Voucher Number", "Mark"
//		}));
	table.getColumnModel().getColumn(0).setPreferredWidth(150);
	table.getColumnModel().getColumn(1).setPreferredWidth(150);
	table.getColumnModel().getColumn(2).setPreferredWidth(25);
	TableColumn tc = table.getColumnModel().getColumn(0);
	tc.setCellEditor(table.getDefaultEditor(Boolean.class));
	tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	scrollPane.setViewportView(table);
	controlsPanel.setLayout(new MigLayout("wrap",
		"[grow,left][grow,center][grow,right][grow,fill,center]",
		"[]unrel[][]"));

	buttonBack = new JButton("Back");
	buttonBack.setMnemonic('B');
	controlsPanel.add(buttonBack, new CC().spanX(5).split(5).tag("other").growX());

	buttonOverride = new JButton("Overide");
	buttonOverride.setMnemonic('O');
	controlsPanel.add(buttonOverride, new CC().tag("other").growX());

	buttonSkip = new JButton("Skip");
	buttonSkip.setMnemonic('K');
	controlsPanel.add(buttonSkip, new CC().tag("other").growX());

//	buttonSubmit = new JButton("Submit");
//	buttonSubmit.setMnemonic('S');
//	controlsPanel.add(buttonSubmit, new CC().tag("other").growX());
	jMainPanel.setLayout(gl_jMainPanel);

	initialiseActionListeners();
    }

    private void initialiseActionListeners() {
	setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(Util.getImageIconPath())));
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
	    @Override
	    public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {

//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            System.out.println("Focus requested to name field");
//                            jTextFieldGroupName.setFocusable(true);
//                            jTextFieldGroupName.setRequestFocusEnabled(true);
//                            jTextFieldGroupName.requestFocus();
//                        } catch (Exception e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                });

		try {
		    formInternalFrameActivated(evt);
		    // formInternalFrameActivated(evt);
		} catch (Exception ex) {
		    ex.printStackTrace();
		    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
		    JOptionPane.showMessageDialog(CopyPasteOperation.this, ex.getMessage());
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
		    ex.printStackTrace();
		    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
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

	buttonBack.addActionListener(new java.awt.event.ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		buttonBackActionPerformed(evt);
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
		    buttonOverride.requestFocus();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    tfGroupUnderText.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }
	});

//	buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
//	    @Override
//	    public void actionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//		    buttonSubmitActionPerformed(evt);
//		} catch (Exception ex) {
//		    ex.printStackTrace();
//		    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
//		    JOptionPane.showMessageDialog(CopyPasteOperation.this, ex.getMessage());
////                    if (ex.getMessage().equalsIgnoreCase("Group Name Exceeding 40 Character Limit")) {
////                        jTextFieldGroupName.requestFocus();
////                    } else if (ex.getMessage().equalsIgnoreCase("Group Alias Exceeding 40 Character Limit")) {
////                        jTextFieldGroupAlias.requestFocus();
////                    }
//		}
//	    }
//	});

//	buttonSubmit.addKeyListener(new java.awt.event.KeyAdapter() {
//	    @Override
//	    public void keyPressed(java.awt.event.KeyEvent event) {
//		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
//		    tfGroupUnderText.requestFocus();
//		} //                else if (event.getKeyCode() == KeyEvent.VK_ENTER) {
//		//                    try {
//		//                        buttonSubmitActionPerformed(null);
//		//                    } catch (Exception ex) {
//		//                        ex.printStackTrace();
//		//                        Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
//		//                    }
//		//                } 
//		else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
//		    if (buttonSkip.isEnabled()) {
//			buttonSkip.requestFocus();
//		    } else {
//			buttonOverride.requestFocus();
//		    }
//		}
//	    }
//	});

	buttonSkip.addActionListener(new java.awt.event.ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    buttonSkipActionPerformed(evt);
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(CopyPasteOperation.this, ex.getMessage());
		}

	    }
	});

	buttonSkip.addKeyListener(new java.awt.event.KeyListener() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    buttonOverride.requestFocus();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		  //  buttonSubmit.requestFocus();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    tfGroupUnderText.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }
	});


	buttonOverride.addActionListener(new java.awt.event.ActionListener() {
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		try {
		    buttonOverrideActionPerformed(evt);
		} catch (Exception ex) {
		    ex.printStackTrace();
		    JOptionPane.showMessageDialog(CopyPasteOperation.this, ex.getMessage());
		}

	    }
	});

	buttonOverride.addKeyListener(new java.awt.event.KeyListener() {
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
		    if (buttonSkip.isEnabled()) {
			buttonSkip.requestFocus();
		    } else {
			//buttonSubmit.requestFocus();
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		    tfGroupUnderText.requestFocus();
		}
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	    }
	});
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException, Exception {
    }

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws PropertyVetoException {
	MainClass.setstaticvar();
//	this.dispose();
    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {
	// TODO add your handling code here:
	try {
	    MainClass.setstaticvar();
	    //  this.dispose();
	    MainClass m = new MainClass();
	    m.menuselection(1);
	    this.setClosed(true);
	} catch (PropertyVetoException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
	// TODO add your handling code here:
//            if (validateData()) {
//                submit();
//                jTextFieldGroupName.requestFocus();
//                jComboBoxGroupUnder.hidePopup();
//                //clearFormData();
//                buttonOverrideActionPerformed(evt);
//            }
    }

    private void buttonSkipActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
	try {
	    buttonBackActionPerformed(evt);
	} catch (Exception ex) {
	    ex.printStackTrace();
	    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	}

    }

    private void buttonOverrideActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
	try {
	    // newButton();
	    user_Selected_ReceiptNoMap = new HashMap<String, Set<String>>();
	    for (int i = 0; i < table.getRowCount(); i++) {
		System.out.println("Vaslue -----  " + table.getValueAt(i, 0).toString().equals("false"));
		if (!table.getValueAt(i, 0).toString().equals("false")) {
		    if (table.getValueAt(i, 1).equals("SALE")) {
			if (user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()) == null) {
			    user_Selected_ReceiptNoMap.put(table.getValueAt(i, 1).toString(), new HashSet<String>());
			}
		    } else if (table.getValueAt(i, 1).equals("PURCHASE")) {
			if (user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()) == null) {
			    user_Selected_ReceiptNoMap.put(table.getValueAt(i, 1).toString(), new HashSet<String>());
			}
		    } else if (table.getValueAt(i, 1).equals("PAYMENT")) {
			if (user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()) == null) {
			    user_Selected_ReceiptNoMap.put(table.getValueAt(i, 1).toString(), new HashSet<String>());
			}
		    } else if (table.getValueAt(i, 1).equals("RECEIPT")) {
			if (user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()) == null) {
			    user_Selected_ReceiptNoMap.put(table.getValueAt(i, 1).toString(), new HashSet<String>());
			}
		    } else if (table.getValueAt(i, 1).equals("CONTRA")) {
			if (user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()) == null) {
			    user_Selected_ReceiptNoMap.put(table.getValueAt(i, 1).toString(), new HashSet<String>());
			}
		    } else if (table.getValueAt(i, 1).equals("CHALAN")) {
			if (user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()) == null) {
			    user_Selected_ReceiptNoMap.put(table.getValueAt(i, 1).toString(), new HashSet<String>());
			}
		    }
		    //user_Selected_ReceiptNoMap.get(table.getValueAt(i, 1).toString()).add(table.getValueAt(i, 2).toString());
		}
	    }


	    Object object = pass_objectMap.get("mapValuePresent");
	    Map<String, Set<String>> value_Present_Map = (Map<String, Set<String>>) object;

	    Object object1 = pass_objectMap.get("returnMapFromXml");
	    Map<String, Map<String, Object>> returnMapFromXml = (Map<String, Map<String, Object>>) object1;

	    // getting
	    for (Map.Entry<String, Set<String>> e : user_Selected_ReceiptNoMap.entrySet()) {
		System.out.println("Key VAlue -------------- " + e.getKey());
		for (String e1 : e.getValue()) {
		    System.out.println("Value ------------- " + e1);
		}
	    }
	    
	    Map<String, Set<String>> diff_In_ReceiptNo_Between_mapValuePresentandUserSelectedMap = new HashMap<String, Set<String>>();


//	    Map<String, Set<String>> mapValueDeletion = getTrans_id(mapValueexitsInDatabase);
//	    if (mapValueDeletion.size() > 0 && mapValueDeletion != null) {
//		gen.ImpExp.TagHelper2.deleteTransaction(mapValueDeletion);
//	    }

	    gen.ImpExp.TagHelper2.insert_All_VoucherTransaction(returnMapFromXml, value_Present_Map, gen.ImpExp.EnumAction.OVERRIDE, user_Selected_ReceiptNoMap);
	    


	} catch (Exception ex) {
	    ex.printStackTrace();
	    Logger.getLogger(CopyPasteOperation.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	}
	// clearFormData();
	// temp = 1000;
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
    private JLabel labelGroupName;
    private JTextField jTextFieldGroupName;
    private JLabel labelAlias;
    private JTextField jTextFieldGroupAlias;
    private JLabel labelGroupUnder;
    private JComboBox jComboBoxGroupUnder;
    private JButton buttonBack;
//    private JButton buttonSubmit;
    private JButton buttonSkip;
    private JButton buttonOverride;
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JTable jTableGroupList;
    private DefaultTableModel transactionTableModel1;
    private JTextField tfGroupUnderText;

    private void initilize() throws SQLException, ParseException, Exception {
	initilizeGUIComponents();
//	initVariables();
	bindTOGUI();
    }

    private void initilizeGUIComponents() {
	initComponentActiveInActive();
    }

    private void initComponentActiveInActive() {
//	buttonSkip.setEnabled(false);
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {

	Object object = pass_objectMap.get("mapValuePresent");
	Map<String, Set<String>> value_Present_Map = (Map<String, Set<String>>) object;

	System.out.println("--------" + value_Present_Map.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()));

	for (Map.Entry<String, Set<String>> e : value_Present_Map.entrySet()) {
	    for (String set : value_Present_Map.get(e.getKey())) {
		String voucher_Type = "";
		if (e.getKey().toString().equals(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
		    voucher_Type = "SALE";
		}
		if (e.getKey().equals(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString())) {
		    voucher_Type = "PURCHASE";
		}
		if (e.getKey().equals(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString())) {
		    voucher_Type = "RECEIPT";
		}
		if (e.getKey().equals(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString())) {
		    voucher_Type = "PAYMENT";
		}
		if (e.getKey().equals(gen.dto.Constants.CHALAN_TYPE_INDEX.toString())) {
		    voucher_Type = "CHALAN";
		}
		if (e.getKey().equals(gen.dto.Constants.CONTRA_TYPE_INDEX.toString())) {
		    voucher_Type = "CONTRA";
		}
		dtm.addRow(new Object[]{new Boolean(false), voucher_Type, set});
	    }
	}

//	for (Set<String> object1 : value_Present_Map.get(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
//	    SaleDTO saleDTO = (SaleDTO) object;
//	}

    }
}
