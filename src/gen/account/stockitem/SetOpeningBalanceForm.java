/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.stockitem;

/**
 *
 * @author admin
 */
import gen.account.StockItemFormation.StockItemFormFinal;
import gen.account.StockItemFormation.StockItemFormationDAO;
import gen.accountvoucher.TableCellListener;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;

public class SetOpeningBalanceForm extends JInternalFrame {

    private int currentFocusValue = 0;
    private JTextField jtextFieldCategory;
    private JTextField jtextFieldLength;
    private JTextField jtextFieldWidth;
    private JTextField jtextFieldColor;
    private JTextField jtextFieldThickness;
    private JTextField jtextFieldFinishType;
    private JTextField jtextFieldBoardType;
    private JTable jtableStockItem;
    private DefaultTableModel jTableStockItemListModel;
    private JButton jbuttonSubmit;
    private JScrollPane jScrollPaneType;
    private List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
    /// VAlue Assign to below stings are Name of Column in Table of Database so dont change these.
    public static String key_Check_Box_Length = "si_length", key_Check_Box_Width = "si_width", key_Check_Box_Thickness = "si_thickness", key_Check_Box_Category = "Category", key_Check_Box_Color = "Color", key_Check_Box_FinishType = "Finish_Type", key_Check_Box_Board_Type = "Board_Type";
    LinkedList<LinkedHashMap<String, String>> list_Key_Value_Map_Order = new LinkedList<LinkedHashMap<String, String>>();
    LinkedHashMap<String, String> map_Order = new LinkedHashMap<String, String>();
    LinkedHashMap<String, Double> map_Current_Order_With_Quantity = new LinkedHashMap<String, Double>();
    private JButton jbuttonNew;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SetOpeningBalanceForm frame = new SetOpeningBalanceForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SetOpeningBalanceForm() {
        try {
            setTitle("Set Stock Item Opening Balance Form");
            initComponents();
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(SetOpeningBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(SetOpeningBalanceForm.this, ex.getMessage());
        }
    }

    /**
     * Create the frame.
     */
    private void initComponents() throws Exception {
        setBounds(100, 100, 1160, 512);
        getContentPane().setLayout(new MigLayout("", "[0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50][0px:100px:100px,grow,shrink 50]", "[0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50][0px:20px:20px,grow,shrink 50]"));

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setHorizontalTextPosition(SwingConstants.CENTER);
        lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblCategory, "cell 3 2");

        JLabel lblNewLabel_1 = new JLabel("Length");
        getContentPane().add(lblNewLabel_1, "cell 4 2");

        JLabel lblNewLabel_2 = new JLabel("Width");
        getContentPane().add(lblNewLabel_2, "cell 5 2");

        JLabel lblNewLabel_3 = new JLabel("Color");
        getContentPane().add(lblNewLabel_3, "cell 6 2");

        JLabel lblNewLabel_4 = new JLabel("Thickness");
        getContentPane().add(lblNewLabel_4, "cell 7 2");

        JLabel lblNewLabel_5 = new JLabel("Finish Type");
        getContentPane().add(lblNewLabel_5, "cell 8 2");

        JLabel lblNewLabel_6 = new JLabel("Board Type");
        getContentPane().add(lblNewLabel_6, "cell 9 2");

        jtextFieldCategory = new JTextField();
        getContentPane().add(jtextFieldCategory, "cell 3 3,growx");
        jtextFieldCategory.setColumns(10);

        jtextFieldLength = new JTextField();
        getContentPane().add(jtextFieldLength, "cell 4 3,growx");
        jtextFieldLength.setColumns(10);

        jtextFieldWidth = new JTextField();
        getContentPane().add(jtextFieldWidth, "cell 5 3,growx");
        jtextFieldWidth.setColumns(10);

        jtextFieldColor = new JTextField();
        getContentPane().add(jtextFieldColor, "cell 6 3,growx");
        jtextFieldColor.setColumns(10);

        jtextFieldThickness = new JTextField();
        getContentPane().add(jtextFieldThickness, "cell 7 3,growx");
        jtextFieldThickness.setColumns(10);

        jtextFieldFinishType = new JTextField();
        getContentPane().add(jtextFieldFinishType, "cell 8 3,growx");
        jtextFieldFinishType.setColumns(10);

        jtextFieldBoardType = new JTextField();
        getContentPane().add(jtextFieldBoardType, "cell 9 3,growx");
        jtextFieldBoardType.setColumns(10);

        jScrollPaneType = new JScrollPane();
        getContentPane().add(jScrollPaneType, "cell 0 4 11 13,grow");

        jtableStockItem = new JTable();
        jScrollPaneType.setViewportView(jtableStockItem);
        jtableStockItem.setBorder(new LineBorder(new Color(0, 0, 0)));
        jTableStockItemListModel = new DefaultTableModel(
                //                new Object[][]{
                //                    {null, null},},
                //                new String[]{
                //                    "StockItemName", "Quantity"
                //                }
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null},},
                new String[]{
                    "Stock Item Name", "Category", "Length", "Width", "Color", "Thickness", "Finish Type", "Board Type", "Quantity(Op Bl)"
                }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 8) {
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
                if (jtableStockItem.getValueAt(row, 8) != null && jtableStockItem.getRowCount() > 0) {
                    if (jtableStockItem.getValueAt(row, 8).toString().trim().matches("^[0-9]*\\.?[0-9]*$") && !jtableStockItem.getValueAt(row, 8).toString().trim().equalsIgnoreCase("")) {
                        if (jtableStockItem.getEditingRow() >= 0) {
                            map_Current_Order_With_Quantity.put((String) jtableStockItem.getValueAt(jtableStockItem.getSelectedRow(), 0), Double.parseDouble(jtableStockItem.getValueAt(jtableStockItem.getSelectedRow(), 8).toString()));
                        }
                    } else {
                        JOptionPane.showMessageDialog(SetOpeningBalanceForm.this, "You can not enter blank value");
                        try {
                            //jtableStockItem.setValueAt(0D, jtableStockItem.getSelectedRow(), 1);
                            bindStockItemDTOToTransactionTable(stockItemTransactionList);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Logger.getLogger(SetOpeningBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jtableStockItem.setRowSelectionInterval(row - 1, 0);
                    }
                }
            }
        };



        jtableStockItem.setModel(new DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null},},
                new String[]{
                    "Stock Item Name", "Category", "Length", "Width", "Color", "Thickness", "Finish Type", "Board Type", "Quantity(Op Bl)"
                }));


        TableCellListener tcl = new TableCellListener(jtableStockItem, action);
        jTableStockItemListModel = (DefaultTableModel) jtableStockItem.getModel();
        jTableStockItemListModel.setRowCount(0);
        jTableStockItemListModel.setColumnCount(9);
        jtableStockItem.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        jtableStockItem.getColumnModel().getColumn(0).setPreferredWidth(300);
        jtableStockItem.getColumnModel().getColumn(0).setMaxWidth(310);
        jtableStockItem.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtableStockItem.getColumnModel().getColumn(1).setMaxWidth(100);
        jtableStockItem.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtableStockItem.getColumnModel().getColumn(2).setMaxWidth(105);
        jtableStockItem.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtableStockItem.getColumnModel().getColumn(3).setMaxWidth(105);
        jtableStockItem.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtableStockItem.getColumnModel().getColumn(4).setMaxWidth(105);
        jtableStockItem.getColumnModel().getColumn(5).setMaxWidth(110);
        jtableStockItem.getColumnModel().getColumn(6).setMaxWidth(105);
        jtableStockItem.getColumnModel().getColumn(7).setMaxWidth(100);
        jtableStockItem.getColumnModel().getColumn(8).setMaxWidth(85);


        jbuttonSubmit = new JButton("Update");
        jbuttonSubmit.setPreferredSize(new Dimension(100, 23));
        getContentPane().add(jbuttonSubmit, "cell 5 18");

        jbuttonNew = new JButton("New");
        jbuttonNew.setPreferredSize(new Dimension(100, 23));
        getContentPane().add(jbuttonNew, "cell 6 18");

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
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(SetOpeningBalanceForm.this,
                            ex.getMessage());
                }

            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                try {
                    formInternalFrameOpened(e);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(SetOpeningBalanceForm.this,
                            ex.getMessage());
                }
            }

            @Override
            public void internalFrameClosing(
                    javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(SetOpeningBalanceForm.class.getName()).log(
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

        jtextFieldCategory.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextFieldCategoryFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextFieldCategoryFocusLost(evt);
            }
        });
        jtextFieldCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldCategoryKeyPressed(evt);
                } catch (Exception ex) {
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
                    jtextFieldCategoryKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });

        jtextFieldLength.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                jtextFieldLengthFocusGained(arg0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtextFieldLengthFocusLost(e);
            }
        });
        jtextFieldLength.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                jtextFieldLengthKeyPressed(arg0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                jtextFieldLengthKeyTyped(e);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldLengthKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });

        jtextFieldWidth.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                jtextFieldWidthFocusGained(arg0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtextFieldWidthFocusLost(e);
            }
        });
        jtextFieldWidth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                jtextFieldWidthKeyPressed(arg0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                jtextFieldWidthKeyTyped(e);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldWidthKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });

        jtextFieldThickness.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                jtextFieldThicknessFocusGained(arg0);
            }

            @Override
            public void focusLost(FocusEvent e) {
                jtextFieldThicknessFocusLost(e);
            }
        });
        jtextFieldThickness.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                jtextFieldThicknessKeyPressed(arg0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
                jtextFieldThicknessKeyTyped(e);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldThicknessKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });

        jtextFieldColor.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextFieldColorFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextFieldColorFocusLost(evt);
            }
        });
        jtextFieldColor.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextFieldColorKeyPressed(evt);
            }

            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldColorKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });

        jtextFieldFinishType.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextFieldFinishTypeFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextFieldFinishTypeFocusLost(evt);
            }
        });
        jtextFieldFinishType.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextFieldFinishTypeKeyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldFinishTypeKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });

        jtextFieldBoardType.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextFieldBoardTypeFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextFieldBoardTypeFocusLost(evt);
            }
        });
        jtextFieldBoardType.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtextFieldBoardTypeKeyPressed(evt);
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    jtextFieldBoardTypeKeyReleased(evt);
                } catch (Exception ex) {
                    Logger.getLogger(StockItemForm1.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        });



        jbuttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (jtableStockItem.getRowCount() > 0) {
                        insertStockItemBal();
                        initilize();
                        JOptionPane.showMessageDialog(SetOpeningBalanceForm.this, gen.dto.Label.RECORD_SUBMITTED_SUCCESSFULLY);
                    } else {
                        JOptionPane.showMessageDialog(SetOpeningBalanceForm.this,
                                "No StockItem is present for updation");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(SetOpeningBalanceForm.this,
                            e.getMessage());
                }
            }
        });

        jbuttonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    initilize();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(SetOpeningBalanceForm.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(SetOpeningBalanceForm.this,
                            ex.getMessage());
                }
            }
        });

        jtableStockItem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        jtableStockItem.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent evt) {
                try {
                    // TODO add your handling code here:
                    System.out.println("Typed --------------- ");
                    String value = jtableStockItem.getValueAt(jtableStockItem.getSelectedRow(), 8).toString();
                    char c = evt.getKeyChar();
                    System.out.println("ttttttt -------- " + c);
                    System.out.println("ttttttt -------- " + !Character.isDigit(c));
                    if (!Character.isDigit(c) && c != '.') {
                        System.out.println("Typed --------------- 11 ");
                        evt.consume();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(SetOpeningBalanceForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
//               throw new UnsupportedOperationException("Not supported yet.");
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP || code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT) {
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
//               throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void jtextFieldCategoryFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 0;
    }

    private void jtextFieldCategoryFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldCategoryKeyPressed(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER) {
            setFocus(evt);
        }
        if (code == KeyEvent.VK_ESCAPE) {
            System.out.println("YYYYYYYYYYYYYYYY ");
            formInternalFrameClosing(null);
        }

    }

    private void jtextFieldCategoryKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:

        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void jtextFieldLengthFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 1;
    }

    private void jtextFieldLengthFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldLengthKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextFieldLengthKeyTyped(java.awt.event.KeyEvent evt) {
        try {
            // TODO add your handling code here:
            Util.filterCharacter(evt, jtextFieldLength);


        } catch (Exception ex) {
            Logger.getLogger(SetOpeningBalanceForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtextFieldLengthKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void jtextFieldWidthFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 2;
    }

    private void jtextFieldWidthFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldWidthKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextFieldWidthKeyTyped(java.awt.event.KeyEvent evt) {
        try {
            // TODO add your handling code here:
            Util.filterCharacter(evt, jtextFieldWidth);


        } catch (Exception ex) {
            Logger.getLogger(SetOpeningBalanceForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtextFieldWidthKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void jtextFieldColorFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 3;
    }

    private void jtextFieldColorFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldColorKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextFieldColorKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void jtextFieldThicknessFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 4;
    }

    private void jtextFieldThicknessFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldThicknessKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextFieldThicknessKeyTyped(java.awt.event.KeyEvent evt) {
        try {
            // TODO add your handling code here:
            Util.filterCharacter(evt, jtextFieldThickness);


        } catch (Exception ex) {
            Logger.getLogger(SetOpeningBalanceForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtextFieldThicknessKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void jtextFieldFinishTypeFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 5;
    }

    private void jtextFieldFinishTypeFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldFinishTypeKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextFieldFinishTypeKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void jtextFieldBoardTypeFocusGained(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
        currentFocusValue = 6;
    }

    private void jtextFieldBoardTypeFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void jtextFieldBoardTypeKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        int code = evt.getKeyCode();

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
            setFocus(evt);
        }
    }

    private void jtextFieldBoardTypeKeyReleased(java.awt.event.KeyEvent evt) throws Exception {
        // TODO add your handling code here:
        setValues();
        stockItemTransactionList.clear();
        stockItemTransactionList = gen.account.StockItemFormation.StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);
        bindStockItemDTOToTransactionTable(stockItemTransactionList);
    }

    private void formInternalFrameActivated(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException, SQLException {
        System.out.println("Frame activated");
        setFocus(null);
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
        if (currentFocusValue == 0) {
            jtextFieldCategory.requestFocus();
        } else if (currentFocusValue == 1) {
            jtextFieldLength.requestFocus();
        } else if (currentFocusValue == 2) {
            jtextFieldWidth.requestFocus();
        } else if (currentFocusValue == 3) {
            jtextFieldColor.requestFocus();
        } else if (currentFocusValue == 4) {
            jtextFieldThickness.requestFocus();
        } else if (currentFocusValue == 5) {
            jtextFieldFinishType.requestFocus();
        } else if (currentFocusValue == 6) {
            jtextFieldBoardType.requestFocus();
        }
    }

    private void initilize() throws SQLException, Exception {
        setMnemonic();
        bindTOGUI();
        allFieldsEmpty();

    }

    private void setMnemonic() throws ParseException, Exception {
        jbuttonSubmit.setMnemonic(KeyEvent.VK_U);
        jbuttonNew.setMnemonic(KeyEvent.VK_N);
    }

    private void bindTOGUI() throws ParseException, Exception {
        initStockItemTransactionList();
    }

    private void allFieldsEmpty() throws ParseException, Exception {
        jtextFieldCategory.setText("");
        jtextFieldLength.setText("");
        jtextFieldWidth.setText("");
        jtextFieldColor.setText("");
        jtextFieldThickness.setText("");
        jtextFieldFinishType.setText("");
        jtextFieldBoardType.setText("");
    }

    private void initStockItemTransactionList() throws Exception {

        stockItemTransactionList = new ArrayList<gen.account.StockItemFormation.StockItemDTO>();
        list_Key_Value_Map_Order = new LinkedList<LinkedHashMap<String, String>>();
        map_Order = new LinkedHashMap<String, String>();
        map_Current_Order_With_Quantity = new LinkedHashMap<String, Double>();
        Set<String> idSet = new HashSet<String>();

//        stockItemTransactionList = StockItemFormationDAO.getStockItemList(idSet);
        stockItemTransactionList = StockItemFormationDAO.getStockItemList_Order_By(list_Key_Value_Map_Order, map_Order);


        bindStockItemDTOToTransactionTable(stockItemTransactionList);
        setItem_OPBL_Map(stockItemTransactionList);
    }

    private void bindStockItemDTOToTransactionTable(List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionList) throws Exception {

        jTableStockItemListModel.setRowCount(0);
        for (gen.account.StockItemFormation.StockItemDTO stockItemDTO : stockItemTransactionList) {
            try {
                int row = jtableStockItem.getRowCount();
                jTableStockItemListModel.setRowCount(row + 1);
                jtableStockItem.setValueAt(stockItemDTO.getName(), row, 0);
                jtableStockItem.setValueAt(stockItemDTO.getStockItemCategoryDTO().getCategoryName(), row, 1);
                jtableStockItem.setValueAt(stockItemDTO.getLength(), row, 2);
                jtableStockItem.setValueAt(stockItemDTO.getWidth(), row, 3);
                jtableStockItem.setValueAt(stockItemDTO.getStockItemColorDTO().getColorName(), row, 4);
                jtableStockItem.setValueAt(stockItemDTO.getThkness(), row, 5);
                jtableStockItem.setValueAt(stockItemDTO.getStockItemFinishTypeDTO().getFinishTypeName(), row, 6);
                jtableStockItem.setValueAt(stockItemDTO.getStockItemTypeDTO().getTypeName(), row, 7);
                jtableStockItem.setValueAt(Double.parseDouble(stockItemDTO.getOpeningBal().toString()), row, 8);
                if (map_Current_Order_With_Quantity.containsKey(stockItemDTO.getName())) {
                    jtableStockItem.setValueAt(Double.parseDouble(map_Current_Order_With_Quantity.get(stockItemDTO.getName()).toString()), row, 8);
                }
//               map_Current_Order_With_Quantity.put(stockItemDTO.getName().trim(), Double.parseDouble(stockItemDTO.getOpeningBal().toString()));
            } catch (Exception ex) {
                Logger.getLogger(StockItemFormFinal.class
                        .getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
    }

    private void setItem_OPBL_Map(List<gen.account.StockItemFormation.StockItemDTO> stockItemTransactionList) throws Exception {
        for (gen.account.StockItemFormation.StockItemDTO stockItemDTO : stockItemTransactionList) {
            try {
                map_Current_Order_With_Quantity.put(stockItemDTO.getName().trim(), Double.parseDouble(stockItemDTO.getOpeningBal().toString()));
            } catch (Exception ex) {
                Logger.getLogger(StockItemFormFinal.class
                        .getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
    }

    private LinkedHashMap<String, String> addMapOrder(LinkedHashMap order_Map, String Key, String entered_Value) {
        order_Map.put(Key, entered_Value);
        return order_Map;
    }

    private LinkedHashMap<String, String> removeMapOrder(LinkedList order_List, String Key) {
        list_Key_Value_Map_Order.clear();
        map_Order.remove(Key);
        list_Key_Value_Map_Order.add(map_Order);
        return map_Order;
    }

    private void setValues() {
        if (jtextFieldCategory.getText().toString().trim() != null && !jtextFieldCategory.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Category);
            map_Order = addMapOrder(map_Order, key_Check_Box_Category, jtextFieldCategory.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);
        } else if (jtextFieldCategory.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Category);
        }

        if (jtextFieldLength.getText().toString().trim() != null && !jtextFieldLength.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Length);
            map_Order = addMapOrder(map_Order, key_Check_Box_Length, jtextFieldLength.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);

        } else if (jtextFieldLength.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Length);
        }

        if (jtextFieldWidth.getText().toString().trim() != null && !jtextFieldWidth.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Width);
            map_Order = addMapOrder(map_Order, key_Check_Box_Width, jtextFieldWidth.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);
        } else if (jtextFieldWidth.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Width);
        }

        if (jtextFieldColor.getText().toString().trim() != null && !jtextFieldColor.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Color);
            map_Order = addMapOrder(map_Order, key_Check_Box_Color, jtextFieldColor.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);
        } else if (jtextFieldColor.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Color);
        }

        if (jtextFieldThickness.getText().toString().trim() != null && !jtextFieldThickness.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Thickness);
            map_Order = addMapOrder(map_Order, key_Check_Box_Thickness, jtextFieldThickness.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);
        } else if (jtextFieldThickness.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Thickness);
        }

        if (jtextFieldFinishType.getText().toString().trim() != null && !jtextFieldFinishType.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_FinishType);
            map_Order = addMapOrder(map_Order, key_Check_Box_FinishType, jtextFieldFinishType.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);
        } else if (jtextFieldFinishType.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_FinishType);
        }

        if (jtextFieldBoardType.getText().toString().trim() != null && !jtextFieldBoardType.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Board_Type);
            map_Order = addMapOrder(map_Order, key_Check_Box_Board_Type, jtextFieldBoardType.getText().toString().trim());
            list_Key_Value_Map_Order.add(map_Order);
        } else if (jtextFieldBoardType.getText().toString().trim().equalsIgnoreCase("")) {
            removeMapOrder(list_Key_Value_Map_Order, key_Check_Box_Board_Type);
        }
    }

    private void insertStockItemBal() throws Exception {
        for (int i = 0; i < jtableStockItem.getRowCount(); i++) {

            for (gen.account.StockItemFormation.StockItemDTO stockItemDTO : stockItemTransactionList) {

                if (stockItemDTO.getName().equals(jtableStockItem.getValueAt(i, 0).toString().trim())) {
                    stockItemDTO.setOpeningBal(Double.parseDouble(jtableStockItem.getValueAt(i, 8).toString().trim()));
                }
            }
        }
        gen.account.StockItemFormation.StockItemFormationDAO.updateStockItem(stockItemTransactionList);
    }
}