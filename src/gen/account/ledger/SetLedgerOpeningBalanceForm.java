/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.ledger;

/**
 *
 * @author admin
 */
import gen.account.StockItemFormation.StockItemFormFinal;
import gen.account.StockItemFormation.StockItemFormationDAO;
import gen.account.stockitem.SetOpeningBalanceForm;
import gen.account.stockitem.StockItemForm1;
import gen.accountvoucher.TableCellListener;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class SetLedgerOpeningBalanceForm extends JInternalFrame {

    private DefaultTableModel jTableStockItemListModel;
    private JTextField textFieldLedgerName;
    private JTable jtableLedgerOpeningBalance;
    JButton btnNew, btnSubmit;
    private List<gen.account.ledger.LedgerDTO> ledgerTransactionList = new ArrayList<gen.account.ledger.LedgerDTO>();
    LinkedList<LinkedHashMap<String, String>> list_Key_Value_Map_Order = new LinkedList<LinkedHashMap<String, String>>();
    LinkedHashMap<String, String> map_Order = new LinkedHashMap<String, String>();
    LinkedHashMap<String, Double> map_Current_Ledger_With_Balance = new LinkedHashMap<String, Double>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetLedgerOpeningBalanceForm frame = new SetLedgerOpeningBalanceForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SetLedgerOpeningBalanceForm() {
        try {
            setTitle("Set Ledger Opening Balance Form");
            initComponents();
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SetLedgerOpeningBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this, ex.getMessage());
        }
    }

    /**
     * Create the frame.
     */
    private void initComponents() throws Exception {
        setBounds(100, 100, 665, 574);
        getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

        JPanel panel = new JPanel();
        getContentPane().add(panel, "cell 0 0,grow");
        panel.setLayout(new MigLayout("", "[0px:50px:50px,grow,shrink 10,fill][0px:50px:50px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:90px:90px,grow,shrink 10,fill][0px:100px:100px,grow,shrink 10,fill][0px:50px:50px,grow,shrink 10,fill][0px:50px:50px,grow,shrink 10,fill]", "[0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill][0px:25px:25px,grow,shrink 50,fill]"));

        JLabel lblLedgerName = new JLabel("Name");
        panel.add(lblLedgerName, "cell 0 2,alignx trailing");

        textFieldLedgerName = new JTextField();
        panel.add(textFieldLedgerName, "cell 1 2 4 1,growx");
        textFieldLedgerName.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        jtableLedgerOpeningBalance = new JTable();
        scrollPane.setViewportView(jtableLedgerOpeningBalance);
        panel.add(scrollPane, "cell 1 3 6 12,grow");

        jtableLedgerOpeningBalance.setBorder(new LineBorder(new Color(0, 0, 0)));
        jtableLedgerOpeningBalance.getTableHeader().setReorderingAllowed(false);
        jTableStockItemListModel = new DefaultTableModel(
                new Object[][]{
                    {null, null},},
                new String[]{
                    "Ledger Name", "Opening Balance"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) {
                    return true;
                } else {
                    return false;//This causes all cells to be not editable
                }
            }
        };

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                int row = tcl.getRow();
                if (jtableLedgerOpeningBalance.getValueAt(row, 1) != null && jtableLedgerOpeningBalance.getRowCount() > 0) {
                    if (jtableLedgerOpeningBalance.getValueAt(row, 1).toString().trim().matches("^[0-9]*\\.?[0-9]*$") && !jtableLedgerOpeningBalance.getValueAt(row, 1).toString().trim().equalsIgnoreCase("")) {
                        if (jtableLedgerOpeningBalance.getEditingRow() >= 0) {
                            map_Current_Ledger_With_Balance.put((String) jtableLedgerOpeningBalance.getValueAt(jtableLedgerOpeningBalance.getSelectedRow(), 0), Double.parseDouble(jtableLedgerOpeningBalance.getValueAt(jtableLedgerOpeningBalance.getSelectedRow(), 1).toString()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this, "You can not enter blank value");
                        try {
                            //jtableStockItem.setValueAt(0D, jtableStockItem.getSelectedRow(), 1);
                            bindStockItemDTOToTransactionTable(ledgerTransactionList);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Logger.getLogger(SetOpeningBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jtableLedgerOpeningBalance.setRowSelectionInterval(row - 1, 0);
                    }
                }
            }
        };

        jtableLedgerOpeningBalance.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null},},
                new String[]{
                    "Ledger Name", "Opening Balance"
                }));

       
        TableCellListener tcl = new TableCellListener(jtableLedgerOpeningBalance, action);
        jTableStockItemListModel = (DefaultTableModel) jtableLedgerOpeningBalance.getModel();
        jTableStockItemListModel.setRowCount(0);
        jTableStockItemListModel.setColumnCount(2);
        jtableLedgerOpeningBalance.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jtableLedgerOpeningBalance.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtableLedgerOpeningBalance.getColumnModel().getColumn(0).setResizable(false);
        jtableLedgerOpeningBalance.getColumnModel().getColumn(0).setPreferredWidth(350);
        jtableLedgerOpeningBalance.getColumnModel().getColumn(0).setMinWidth(100);
        jtableLedgerOpeningBalance.getColumnModel().getColumn(0).setMaxWidth(350);
        jtableLedgerOpeningBalance.getColumnModel().getColumn(1).setMaxWidth(200);
//        scrollPane.setColumnHeaderView(jtableLedgerOpeningBalance);

        btnNew = new JButton("New");
        panel.add(btnNew, "cell 3 15");

        btnSubmit = new JButton("Submit");
        panel.add(btnSubmit, "cell 4 15");

        initialiseActionListeners();
    }

    private void initialiseActionListeners() {
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(
                Util.getImageIconPath())));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            @Override
            public void internalFrameActivated(
                    javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SetLedgerOpeningBalanceForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this,
                            ex.getMessage());
                }

            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                try {
                    formInternalFrameOpened(e);
                } catch (PropertyVetoException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SetLedgerOpeningBalanceForm.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this,
                            ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosing(
                    javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SetLedgerOpeningBalanceForm.class.getName()).log(
                            Level.SEVERE, null, ex);
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

        textFieldLedgerName.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                textFieldLedgerNameFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldLedgerNameFocusLost(evt);
            }
        });
        textFieldLedgerName.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    textFieldLedgerNameKeyPressed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    textFieldLedgerNameKeyReleased(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });


        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (jtableLedgerOpeningBalance.getRowCount() > 0) {
                        insertStockItemBal();
                        initilize();
                        JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this, gen.dto.Label.RECORD_SUBMITTED_SUCCESSFULLY);
                    } else {
                        JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this,
                                "No Ledger is present for updation");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this,
                            e.getMessage());
                }
            }
        });

        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    initilize();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SetOpeningBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(SetLedgerOpeningBalanceForm.this,
                            ex.getMessage());
                }
            }
        });


    }

    private void formInternalFrameActivated(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException, SQLException {
        System.out.println("Frame activated");
//        setFocus(null);
    }

    private void formInternalFrameOpened(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
    }

    private void formInternalFrameClosing(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
        MainClass.setstaticvar();
    }

    private void textFieldLedgerNameFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
//        currentFocusValue = 0;
    }

    private void textFieldLedgerNameFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void textFieldLedgerNameKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
//            setFocus(evt);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            formInternalFrameClosing(null);
        }

    }

    private void textFieldLedgerNameKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:

//        setValues();
        ledgerTransactionList.clear();
        String ledger_Name_Search = "";
        ledger_Name_Search = textFieldLedgerName.getText().toString().trim();
        ledgerTransactionList = gen.account.ledger.LedgerDAO.getLedgerInfoForSetOPBL(ledger_Name_Search);
        bindStockItemDTOToTransactionTable(ledgerTransactionList);
    }

    private void initilize() throws SQLException, Exception {
        setMnemonic();
        allFieldsEmpty();
        bindTOGUI();
    }

    private void setMnemonic() throws ParseException, Exception {
        btnSubmit.setMnemonic(KeyEvent.VK_S);
        btnNew.setMnemonic(KeyEvent.VK_N);
    }

    private void bindTOGUI() throws ParseException, Exception {
        initStockItemTransactionList();
    }

    private void initStockItemTransactionList() throws Exception {

        ledgerTransactionList = new ArrayList<gen.account.ledger.LedgerDTO>();
        list_Key_Value_Map_Order = new LinkedList<LinkedHashMap<String, String>>();
        map_Order = new LinkedHashMap<String, String>();
        map_Current_Ledger_With_Balance = new LinkedHashMap<String, Double>();
        Set<String> idSet = new HashSet<String>();

        String ledger_Name_Search = "";
        ledger_Name_Search = textFieldLedgerName.getText().toString().trim();
        ledgerTransactionList = gen.account.ledger.LedgerDAO.getLedgerInfoForSetOPBL(ledger_Name_Search);

        setItem_OPBL_Map(ledgerTransactionList);
        bindStockItemDTOToTransactionTable(ledgerTransactionList);

    }

    private void bindStockItemDTOToTransactionTable(List<gen.account.ledger.LedgerDTO> ledgerTransactionList) throws Exception {

        jTableStockItemListModel.setRowCount(0);
        for (gen.account.ledger.LedgerDTO legerItemDTO : ledgerTransactionList) {
            try {
                int row = jtableLedgerOpeningBalance.getRowCount();
                jTableStockItemListModel.setRowCount(row + 1);
                jtableLedgerOpeningBalance.setValueAt(legerItemDTO.getLedger_Name(), row, 0);
                if (map_Current_Ledger_With_Balance.containsKey(legerItemDTO.getLedger_Name())) {
                    jtableLedgerOpeningBalance.setValueAt(Double.parseDouble(map_Current_Ledger_With_Balance.get(legerItemDTO.getLedger_Name()).toString()), row, 1);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(StockItemFormFinal.class
                        .getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
    }

    private void setItem_OPBL_Map(List<gen.account.ledger.LedgerDTO> ledgerItemTransactionList) throws Exception {
        for (gen.account.ledger.LedgerDTO ledgerItemDTO : ledgerItemTransactionList) {
            try {
                map_Current_Ledger_With_Balance.put(ledgerItemDTO.getLedger_Name().trim(), Double.parseDouble(ledgerItemDTO.getLedger_OpeningBalence().toString()));
            } catch (Exception ex) {
                ex.printStackTrace();
                Logger.getLogger(StockItemFormFinal.class
                        .getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
    }

    private void allFieldsEmpty() throws ParseException, Exception {
        textFieldLedgerName.setText("");
    }

    private void insertStockItemBal() throws Exception {
        for (int i = 0; i < jtableLedgerOpeningBalance.getRowCount(); i++) {

            for (gen.account.ledger.LedgerDTO ledgerItemDTO : ledgerTransactionList) {

                if (ledgerItemDTO.getLedger_Name().equals(jtableLedgerOpeningBalance.getValueAt(i, 0).toString().trim())) {
                    ledgerItemDTO.setLedger_OpeningBalence(Double.parseDouble(jtableLedgerOpeningBalance.getValueAt(i, 1).toString().trim()));

                    SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                    Calendar currentDate = Calendar.getInstance();
                    String dateNow = f.format(currentDate.getTime());

                    ledgerItemDTO.setLedger_Date(dateNow);
                }
            }
        }
        gen.account.ledger.LedgerDAO.UpdateLedger(ledgerTransactionList);
    }
}
