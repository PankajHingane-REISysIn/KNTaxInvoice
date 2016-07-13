package gen.ImpExp.selectImport;

/**
 *
 * @author admin
 */
import gen.ImpExp.TagsHelper1;
import gen.accountvoucher.chalan.ChalanDTO;
import gen.accountvoucher.contra.ContraDTO;
import gen.accountvoucher.payment.PaymentDTO;
import gen.accountvoucher.purchase.PurchaseDTO;
import gen.accountvoucher.receipt.ReceiptDTO;
import gen.accountvoucher.sale.SaleDTO;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class CopyPasteReplaceImportExport extends JInternalFrame {

    private JTable table_Skip;
    DefaultTableModel dtm_Skip, dtm_Copy, dtm_Replace;
    Object colNames[] = {"", "Voucher Type", "Receipt No"};
    Object[][] datarow = {};
    Map<String, Object> pass_objectMap = new HashMap<String, Object>();
    Map<String, Map<String, Set<String>>> user_Selected_ReceiptNoMap = new HashMap<String, Map<String, Set<String>>>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Map<String, Object> pass_objectList = new HashMap<String, Object>();
                    CopyPasteReplaceImportExport frame = new CopyPasteReplaceImportExport(
                            pass_objectList);
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
    public CopyPasteReplaceImportExport(Map<String, Object> pass_objectList) {

        try {
            initComponents();
            this.pass_objectMap = pass_objectList;
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(CopyPasteReplaceImportExport.this,
                    ex.getMessage());
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
//	setFrameIcon(new ImageIcon(
//		CopyPasteReplaceImportExport.class
//		.getResource("/IMages/Kasturi-logo-1.png")));
        setFrameIcon(new ImageIcon(CopyPasteReplaceImportExport.class.getResource("/images/Kasturi-logo-1.png")));
        setClosable(true);
        setBounds(100, 100, 955, 539);

        JPanel jMainPanel = new JPanel();
        getContentPane().add(jMainPanel, BorderLayout.CENTER);

        JPanel groupInformationPanel = new JPanel();
        groupInformationPanel.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Data Import Options",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBorder(new TitledBorder(null, "Controls",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout gl_jMainPanel = new GroupLayout(jMainPanel);
        gl_jMainPanel.setHorizontalGroup(
                gl_jMainPanel.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_jMainPanel.createSequentialGroup()
                .addGroup(gl_jMainPanel.createParallelGroup(Alignment.TRAILING)
                .addGroup(Alignment.LEADING, gl_jMainPanel.createSequentialGroup()
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
                .addComponent(groupInformationPanel, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                .addGap(10)));
        groupInformationPanel
                .setLayout(new MigLayout("", "[0px:275px:275px,grow,shrink 50,fill][0px:180px:180px,grow,shrink 50,fill][0px:275px:275px,grow,shrink 50,fill][0px:180px:180px,grow,shrink 50,fill][0px:275px:275px,grow,shrink 50,fill]", "[25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][25px:25px:25px,grow,fill][grow,shrink 50]"));

        jCheckBox1 = new JCheckBox("Mark All");
        jCheckBox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                Boolean flag;
                if (table_Skip.getModel().getRowCount() != 0) {
                    jCheckBox1.setEnabled(true);
                    if (jCheckBox1.isSelected()) {
                        flag = true;
                        for (int i = 0; i < table_Skip.getModel().getRowCount(); i++) {
                            table_Skip.getModel().setValueAt(flag, i, 0);
                        }
                    } else {
                        flag = false;
                        for (int i = 0; i < table_Skip.getModel().getRowCount(); i++) {
                            table_Skip.getModel().setValueAt(flag, i, 0);
                        }
                    }
                } else {
                    jCheckBox1.setEnabled(false);
                }

            }
        });
        groupInformationPanel.add(jCheckBox1, "flowx,cell 0 0");

        lblNewLabel = new JLabel("Skip");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        groupInformationPanel.add(lblNewLabel, "cell 0 0");

        jCheckBox2 = new JCheckBox("Mark All");
        jCheckBox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Boolean flag;
                if (table_Copy.getModel().getRowCount() != 0) {
                    jCheckBox2.setEnabled(true);
                    if (jCheckBox2.isSelected()) {
                        flag = true;
                        for (int i = 0; i < table_Copy.getModel().getRowCount(); i++) {
                            table_Copy.getModel().setValueAt(flag, i, 0);
                        }
                    } else {
                        flag = false;
                        for (int i = 0; i < table_Copy.getModel().getRowCount(); i++) {
                            table_Copy.getModel().setValueAt(flag, i, 0);
                        }
                    }
                } else {
                    jCheckBox2.setEnabled(false);
                }
            }
        });
        groupInformationPanel.add(jCheckBox2, "flowx,cell 2 0");

        lblNewLabel_1 = new JLabel("Copy");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        groupInformationPanel.add(lblNewLabel_1, "cell 2 0");

        jCheckBox3 = new JCheckBox("Mark All");
        jCheckBox3.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                Boolean flag;
                if (table_Replace.getModel().getRowCount() != 0) {
                    jCheckBox3.setEnabled(true);
                    if (jCheckBox3.isSelected()) {
                        flag = true;
                        for (int i = 0; i < table_Replace.getModel().getRowCount(); i++) {
                            table_Replace.getModel().setValueAt(flag, i, 0);
                        }
                    } else {
                        flag = false;
                        for (int i = 0; i < table_Replace.getModel().getRowCount(); i++) {
                            table_Replace.getModel().setValueAt(flag, i, 0);
                        }
                    }
                } else {
                    jCheckBox3.setEnabled(false);
                }
            }
        });
        groupInformationPanel.add(jCheckBox3, "flowx,cell 4 0");

        lblNewLabel_2 = new JLabel("Copy/Replace");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        groupInformationPanel.add(lblNewLabel_2, "cell 4 0");

        JScrollPane scrollPane = new JScrollPane();
        groupInformationPanel.add(scrollPane, "cell 0 1 1 26,grow");

        // Object colNames[] = new String[]{"Voucher Type", "Voucher Number",
        // "Mark"};;
        // Object[][] data = new Object[][]{};
        dtm_Skip = new DefaultTableModel(datarow, colNames);
        table_Skip = new JTable(dtm_Skip);

        // table.setModel(new DefaultTableModel(
        // new Object[][]{},
        // new String[]{
        // "Voucher Type", "Voucher Number", "Mark"
        // }));
        table_Skip.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_Skip.getColumnModel().getColumn(1).setPreferredWidth(100);
        table_Skip.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableColumn tc = table_Skip.getColumnModel().getColumn(0);
	tc.setCellEditor(table_Skip.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table_Skip.getDefaultRenderer(Boolean.class));
        scrollPane.setViewportView(table_Skip);
        JScrollPane scrollPane_1 = new JScrollPane();
	groupInformationPanel.add(scrollPane_1, "cell 2 1 1 26,grow");

        table_Copy = new JTable();
//	table_Copy.setModel(new DefaultTableModel(
//		new Object[][]{
//		    {null, null, null},
//		    {null, null, null},
//		    {null, null, null},
//		    {null, null, null},
//		    {null, null, null},},
//		new String[]{
//		    "", "Voucher Type", "Receipt No"
//		}));
        dtm_Copy = new DefaultTableModel(datarow, colNames);
        table_Copy = new JTable(dtm_Copy);
        tc = table_Copy.getColumnModel().getColumn(0);

        tc.setCellEditor(table_Copy.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table_Copy.getDefaultRenderer(Boolean.class));
	table_Copy.getColumnModel().getColumn(0).setPreferredWidth(50);
	table_Copy.getColumnModel().getColumn(1).setPreferredWidth(100);
	table_Copy.getColumnModel().getColumn(2).setPreferredWidth(100);
        scrollPane_1.setViewportView(table_Copy);
        scrollPane_1.setViewportView(table_Copy);

	JScrollPane scrollPane_2 = new JScrollPane();
	groupInformationPanel.add(scrollPane_2, "cell 4 1 1 26,grow");

//	table_Replace = new JTable();
//	table_Replace.setModel(new DefaultTableModel(
//		new Object[][]{
//		    {null, null, null},
//		    {null, null, null},
//		    {null, null, null},},
//		new String[]{
//		    "", "Voucher Type", "Receipt No"
//		}));
        dtm_Replace = new DefaultTableModel(datarow, colNames);
        table_Replace = new JTable(dtm_Replace);
        tc = table_Replace.getColumnModel().getColumn(0);

        tc.setCellEditor(table_Replace.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table_Replace.getDefaultRenderer(Boolean.class));
	table_Replace.getColumnModel().getColumn(0).setPreferredWidth(50);
	table_Replace.getColumnModel().getColumn(1).setPreferredWidth(100);
	table_Replace.getColumnModel().getColumn(2).setPreferredWidth(100);
        scrollPane_2.setViewportView(table_Replace);
//	table_Replace.getColumnModel().getColumn(0).setPreferredWidth(50);
//	table_Replace.getColumnModel().getColumn(1).setPreferredWidth(100);
//	table_Replace.getColumnModel().getColumn(2).setPreferredWidth(100);


        scrollPane_2.setViewportView(table_Replace);
        buttonSkip_To_Copy = new JButton("Copy >>");

        buttonSkip_To_Copy.setToolTipText(
                "Copy data to Copy Operation Table");
        groupInformationPanel.add(buttonSkip_To_Copy,
                "cell 1 3");

        buttonSkip_To_Replace = new JButton("Copy&Replace");

        buttonSkip_To_Replace.setToolTipText(
                "Copy data to Copy/Replace Operation table ");
        groupInformationPanel.add(buttonSkip_To_Replace,
                "cell 1 5");

        buttonCopy_To_Skip = new JButton("<< Skip");

        buttonCopy_To_Skip.setToolTipText(
                "Skips data from Copy Operation to Skip Operation table");
        groupInformationPanel.add(buttonCopy_To_Skip,
                "cell 1 7");

        buttonCopy_To_Replace = new JButton("Replace");

        buttonCopy_To_Replace.setToolTipText(
                "Copy data from Copy Operation to Copy/Replace Operation table");
        groupInformationPanel.add(buttonCopy_To_Replace,
                "cell 3 3");

        buttonReplace_To_Copy = new JButton("Copy");

        buttonReplace_To_Copy.setToolTipText(
                "Copy data from Copy/Replace Operation to Copy Operation table");
        groupInformationPanel.add(buttonReplace_To_Copy,
                "cell 3 5");

        buttonReplace_To_Skip = new JButton("<< Skip");

        buttonReplace_To_Skip.setToolTipText(
                "Skips data from Copy/Replace Operation to Skip Operation");
        groupInformationPanel.add(buttonReplace_To_Skip,
                "cell 3 7");

        controlsPanel.setLayout(
                new MigLayout("wrap",
                "[grow,left][grow,center][grow,right][grow,fill,center]",
                "[]unrel[][]"));

        buttonBack = new JButton("Back");

        buttonBack.setMnemonic(
                'B');
        controlsPanel.add(buttonBack,
                new CC().spanX(5).split(5).tag("other")
                .growX());

        buttonOverride = new JButton("Overide");

        buttonOverride.setMnemonic(
                'O');
        controlsPanel.add(buttonOverride,
                new CC().tag("other").growX());

//	buttonSkip = new JButton("Skip");
//	buttonSkip.setMnemonic('K');
//	controlsPanel.add(buttonSkip, new CC().tag("other").growX());

        // buttonSubmit = new JButton("Submit");
        // buttonSubmit.setMnemonic('S');
        // controlsPanel.add(buttonSubmit, new CC().tag("other").growX());
        jMainPanel.setLayout(gl_jMainPanel);

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

                // SwingUtilities.invokeLater(new Runnable() {
                // @Override
                // public void run() {
                // try {
                // System.out.println("Focus requested to name field");
                // jTextFieldGroupName.setFocusable(true);
                // jTextFieldGroupName.setRequestFocusEnabled(true);
                // jTextFieldGroupName.requestFocus();
                // } catch (Exception e1) {
                // e1.printStackTrace();
                // }
                // }
                // });

                try {
                    formInternalFrameActivated(evt);
                    // formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceImportExport.this,
                            ex.getMessage());
                }
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameClosing(
                    javax.swing.event.InternalFrameEvent evt) {

                try {
                    formInternalFrameClosing(evt);
                } catch (PropertyVetoException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }
        });


        buttonSkip_To_Copy.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSkip_To_CopyActionPerformed(evt);
            }
        });

        buttonSkip_To_Copy.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonSkip_To_Replace.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSkip_To_ReplaceActionPerformed(evt);
            }
        });

        buttonSkip_To_Replace.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonCopy_To_Skip.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCopy_To_SkipActionPerformed(evt);
            }
        });

        buttonCopy_To_Skip.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        buttonCopy_To_Replace.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCopy_To_ReplaceActionPerformed(evt);
            }
        });

        buttonCopy_To_Replace.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        buttonReplace_To_Copy.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReplace_To_CopyActionPerformed(evt);
            }
        });

        buttonReplace_To_Copy.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        buttonReplace_To_Skip.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReplace_To_SkipActionPerformed(evt);
            }
        });

        buttonReplace_To_Skip.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    buttonOverride.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfGroupUnderText.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }
        });

//	buttonSkip.addActionListener(new java.awt.event.ActionListener() {
//	    @Override
//	    public void actionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//		    buttonSkipActionPerformed(evt);
//		} catch (Exception ex) {
//		    JOptionPane.showMessageDialog(CopyPasteReplaceImportExport.this,
//			    ex.getMessage());
//		}
//
//	    }
//	});
//
//	buttonSkip.addKeyListener(new java.awt.event.KeyListener() {
//	    @Override
//	    public void keyTyped(KeyEvent e) {
//		// throw new
//		// UnsupportedOperationException("Not supported yet.");
//	    }
//
//	    @Override
//	    public void keyPressed(KeyEvent e) {
//		// throw new
//		// UnsupportedOperationException("Not supported yet.");
//		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//		    buttonOverride.requestFocus();
//		}
//		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//		    // buttonSubmit.requestFocus();
//		}
//		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//		    tfGroupUnderText.requestFocus();
//		}
//	    }
//
//	    @Override
//	    public void keyReleased(KeyEvent e) {
//		// throw new
//		// UnsupportedOperationException("Not supported yet.");
//	    }
//	});

        buttonOverride.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonOverrideActionPerformed(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CopyPasteReplaceImportExport.this,
                            ex.getMessage());
                }

            }
        });

        buttonOverride.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    buttonBack.requestFocus();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//		    if (buttonSkip.isEnabled()) {
//			buttonSkip.requestFocus();
//		    } else {
//			// buttonSubmit.requestFocus();
//		    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    tfGroupUnderText.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // throw new
                // UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) throws SQLException, Exception {

        if (table_Skip.getModel().getRowCount() != 0) {
            jCheckBox1.setEnabled(true);
        } else {
            jCheckBox1.setEnabled(false);
        }

        if (table_Copy.getModel().getRowCount() != 0) {
            jCheckBox2.setEnabled(true);
        } else {
            jCheckBox2.setEnabled(false);
        }

        if (table_Replace.getModel().getRowCount() != 0) {
            jCheckBox3.setEnabled(true);
        } else {
            jCheckBox3.setEnabled(false);
        }

    }

    private void formInternalFrameClosing(
            javax.swing.event.InternalFrameEvent evt)
            throws PropertyVetoException {
        MainClass.setstaticvar();
        // this.dispose();
    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            MainClass.setstaticvar();
            // this.dispose();
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonSkip_To_CopyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
//	    if (table_Skip.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0 
            if (table_Skip.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Skip.getRowCount(); i++) {
                    // selected row
                    if (!table_Skip.getValueAt(i, 0).toString().equals("false")) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        dtm_Copy.setRowCount(table_Copy.getRowCount() + 1);
                        table_Copy.setValueAt(false, dtm_Copy.getRowCount() - 1, 0);
                        table_Copy.setValueAt(table_Skip.getValueAt(i, 1).toString(), dtm_Copy.getRowCount() - 1, 1);
                        table_Copy.setValueAt(table_Skip.getValueAt(i, 2).toString(), dtm_Copy.getRowCount() - 1, 2);
                        // remove row from table tableCompanyList
                        dtm_Skip.removeRow(i);
                        // decrease value of i(row) as one row removed 
                        i--;
                    }
                }
            }
//	    }

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonSkip_To_ReplaceActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            if (table_Skip.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Skip.getRowCount(); i++) {
                    // selected row
                    if (!table_Skip.getValueAt(i, 0).toString().equals("false")) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        dtm_Replace.setRowCount(table_Replace.getRowCount() + 1);
                        table_Replace.setValueAt(false, table_Replace.getRowCount() - 1, 0);
                        table_Replace.setValueAt(table_Skip.getValueAt(i, 1).toString(), table_Replace.getRowCount() - 1, 1);
                        table_Replace.setValueAt(table_Skip.getValueAt(i, 2).toString(), table_Replace.getRowCount() - 1, 2);
                        // remove row from table tableCompanyList
                        dtm_Skip.removeRow(i);
                        // decrease value of i(row) as one row removed 
                        i--;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonCopy_To_SkipActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            if (table_Copy.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Copy.getRowCount(); i++) {
                    // selected row
                    if (!table_Copy.getValueAt(i, 0).toString().equals("false")) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        dtm_Skip.setRowCount(table_Skip.getRowCount() + 1);
                        table_Skip.setValueAt(false, table_Skip.getRowCount() - 1, 0);
                        table_Skip.setValueAt(table_Copy.getValueAt(i, 1).toString(), table_Skip.getRowCount() - 1, 1);
                        table_Skip.setValueAt(table_Copy.getValueAt(i, 2).toString(), table_Skip.getRowCount() - 1, 2);
                        // remove row from table tableCompanyList
                        dtm_Copy.removeRow(i);
                        // decrease value of i(row) as one row removed 
                        i--;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonCopy_To_ReplaceActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            // selected row must be greater than 0 
            if (table_Copy.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Copy.getRowCount(); i++) {
                    // selected row
                    if (!table_Copy.getValueAt(i, 0).toString().equals("false")) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        dtm_Replace.setRowCount(table_Replace.getRowCount() + 1);
                        table_Replace.setValueAt(false, table_Replace.getRowCount() - 1, 0);
                        table_Replace.setValueAt(table_Copy.getValueAt(i, 1).toString(), table_Replace.getRowCount() - 1, 1);
                        table_Replace.setValueAt(table_Copy.getValueAt(i, 2).toString(), table_Replace.getRowCount() - 1, 2);
                        // remove row from table tableCompanyList
                        dtm_Copy.removeRow(i);
                        // decrease value of i(row) as one row removed 
                        i--;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonReplace_To_CopyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            // selected row must be greater than 0 
            if (table_Replace.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Replace.getRowCount(); i++) {
                    // selected row
                    if (!table_Replace.getValueAt(i, 0).toString().equals("false")) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        dtm_Copy.setRowCount(table_Copy.getRowCount() + 1);
                        table_Copy.setValueAt(false, table_Copy.getRowCount() - 1, 0);
                        table_Copy.setValueAt(table_Replace.getValueAt(i, 1).toString(), table_Copy.getRowCount() - 1, 1);
                        table_Copy.setValueAt(table_Replace.getValueAt(i, 2).toString(), table_Copy.getRowCount() - 1, 2);
                        // remove row from table tableCompanyList
                        dtm_Replace.removeRow(i);
                        // decrease value of i(row) as one row removed 
                        i--;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonReplace_To_SkipActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            if (table_Replace.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Replace.getRowCount(); i++) {
                    // selected row
                    if (!table_Replace.getValueAt(i, 0).toString().equals("false")) {
                        // move data of selected row from tableCompanyList to tableCompanyRestoreUp
                        dtm_Skip.setRowCount(table_Skip.getRowCount() + 1);
                        table_Skip.setValueAt(false, table_Skip.getRowCount() - 1, 0);
                        table_Skip.setValueAt(table_Replace.getValueAt(i, 1).toString(), table_Skip.getRowCount() - 1, 1);
                        table_Skip.setValueAt(table_Replace.getValueAt(i, 2).toString(), table_Skip.getRowCount() - 1, 2);
                        // remove row from table tableCompanyList
                        dtm_Replace.removeRow(i);
                        // decrease value of i(row) as one row removed 
                        i--;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt)
            throws Exception {
        // TODO add your handling code here:
        // if (validateData()) {
        // submit();
        // jTextFieldGroupName.requestFocus();
        // jComboBoxGroupUnder.hidePopup();
        // //clearFormData();
        // buttonOverrideActionPerformed(evt);
        // }
    }

    private void buttonSkipActionPerformed(java.awt.event.ActionEvent evt)
            throws Exception {
        try {
            buttonBackActionPerformed(evt);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw ex;
        }

    }

    private void buttonOverrideActionPerformed(java.awt.event.ActionEvent evt)
            throws Exception {
        try {

            if (table_Copy.getRowCount() > 0 || table_Replace.getRowCount() > 0) {

                user_Selected_ReceiptNoMap = new HashMap<String, Map<String, Set<String>>>();
                //Map<String, Set<String>> user_Selected_ReceiptNoMap1 = new HashMap<String, Set<String>>();

                user_Selected_ReceiptNoMap.put(gen.ImpExp.EnumAction.OVERRIDE.toString(), return_Import_Export_Receipt(table_Replace));
                user_Selected_ReceiptNoMap.put(gen.ImpExp.EnumAction.SKIP.toString(), return_Import_Export_Receipt(table_Skip));
                user_Selected_ReceiptNoMap.put(gen.ImpExp.EnumAction.COPY.toString(), return_Import_Export_Receipt(table_Copy));

                for (Map.Entry<String, Map<String, Set<String>>> e : user_Selected_ReceiptNoMap.entrySet()) {
                    System.out.println("outer Key ------------- " + e.getKey());
                    System.out.println("outer Key ------------- " + e.getValue().get("SALE"));
                    for (Map.Entry<String, Set<String>> e1 : e.getValue().entrySet()) {
                        System.out.println("Type ------------------ " + e1.getKey());
                        System.out.println("value ------------------" + e1.getValue());
                    }
                }

                Map<String, Set<String>> mapValueDeletion = gen.ImpExp.TagHelper2.getTrans_id(user_Selected_ReceiptNoMap.get(gen.ImpExp.EnumAction.OVERRIDE.toString()));
                System.out.println("-----------------   " + mapValueDeletion.size());

                if (mapValueDeletion.size() > 0 && mapValueDeletion != null) {
                    gen.ImpExp.TagHelper2.deleteTransaction(mapValueDeletion);
                }

                Object object1 = pass_objectMap.get("returnMapFromXml");
                Map<String, Map<String, Object>> returnMapFromXml = (Map<String, Map<String, Object>>) object1;

                // Skip Map with Vouchet type and set of its Receipt No
                Map<String, Set<String>> skip_Map = user_Selected_ReceiptNoMap.get(gen.ImpExp.EnumAction.SKIP.toString());
                // Map of Vouchet type and Map of its receipt No and its  DTO object
                for (Map.Entry<String, Map<String, Object>> e1 : returnMapFromXml.entrySet()) {
                    //Map of it receipt No and its  DTO object of respective Voucher type
                    Map<String, Object> receipt_No_and_its_DTO_Map = e1.getValue();
                    System.out.println("e1.getKey().toString() -------   " + e1.getKey().toString());
                    // check wheather Voucher type entry is present in skip_Map
                    if (skip_Map.containsKey(e1.getKey().toString())) {
                        // string from set of receipt_no from skip_Map
                        for (String str : skip_Map.get(e1.getKey().toString())) {
                            // receipt_No_and_its_DTO_Map contains receip_No_from_Copy_Map
                            if (receipt_No_and_its_DTO_Map.containsKey(str)) {
                                returnMapFromXml.get(e1.getKey().toString()).remove(str);
                            }
                        }
                    }
                }

                // Copy Map with Vouchet type and set of its Receipt No
                Map<String, Set<String>> copy_Map = user_Selected_ReceiptNoMap.get(gen.ImpExp.EnumAction.COPY.toString());
                // Map of Vouchet type and Map of its receipt No and its  DTO object
                for (Map.Entry<String, Map<String, Object>> e1 : returnMapFromXml.entrySet()) {
//		Long d = Long.parseLong(TagsHelper1.maxReceiptNoWithVoucherTypeMapping.get(e1.getKey().toString())) + 1;
                    System.out.println("-----------  " + Long.parseLong(TagsHelper1.maxReceiptNoWithVoucherTypeMapping.get(e1.getKey().toString())));
                    Long incremented_Receipt_No = Long.parseLong(TagsHelper1.maxReceiptNoWithVoucherTypeMapping.get(e1.getKey().toString()));
                    System.out.println("1111111   vaouvher type frpm returnMapFromXml " + e1.getKey());
                    //Map of it receipt No and its  DTO object of respective Voucher type
                    Map<String, Object> receipt_No_and_its_DTO_Map = e1.getValue();
                    // check wheather Voucher type entry is present in Copy_Map
                    if (copy_Map.containsKey(e1.getKey().toString())) {
                        // string from set of receipt_no from Copy_Map
                        for (String receip_No_from_Copy_Map : copy_Map.get(e1.getKey().toString())) {
                            System.out.println("2222222   receip_No_from_Copy_Map - == " + receip_No_from_Copy_Map);
                            // check whether incremented key is presnt in DTO or not
                            incremented_Receipt_No++;
                            System.out.println("33333  incremented receipt no   " + incremented_Receipt_No);
                            if (!receipt_No_and_its_DTO_Map.containsKey(incremented_Receipt_No.toString())) {
                                System.out.println("444444444 ----------- ");
                                // receipt_No_and_its_DTO_Map contains receip_No_from_Copy_Map
                                if (receipt_No_and_its_DTO_Map.containsKey(receip_No_from_Copy_Map)) {
                                    System.out.println("555555555 ----------- ");
                                    System.out.println("");
                                    // change DTO Receipt No

                                    Object object = returnMapFromXml.get(e1.getKey().toString()).get(receip_No_from_Copy_Map);
                                    if (e1.getKey().toString().equals(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {

                                        SaleDTO saleDTO = (SaleDTO) object;
                                        saleDTO.setReceiptNo(Integer.parseInt(incremented_Receipt_No.toString()));
                                        returnMapFromXml.get(e1.getKey().toString()).put(incremented_Receipt_No.toString(), saleDTO);
                                        System.out.println("666666666  " + Integer.parseInt(incremented_Receipt_No.toString()));
                                    }
                                    if (e1.getKey().toString().equals(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString())) {
                                        PurchaseDTO purchaseDTO = (PurchaseDTO) object;
                                        purchaseDTO.setReceiptNo(Integer.parseInt(incremented_Receipt_No.toString()));
                                        returnMapFromXml.get(e1.getKey().toString()).put(incremented_Receipt_No.toString(), purchaseDTO);
                                        System.out.println("777777777777777  " + Integer.parseInt(incremented_Receipt_No.toString()));
                                    }
                                    if (e1.getKey().toString().equals(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString())) {
                                        ReceiptDTO receiptDTO = (ReceiptDTO) object;
                                        receiptDTO.setReceiptNo(Integer.parseInt(incremented_Receipt_No.toString()));
                                        returnMapFromXml.get(e1.getKey().toString()).put(incremented_Receipt_No.toString(), receiptDTO);
                                        System.out.println("88888888888  " + Integer.parseInt(incremented_Receipt_No.toString()));
                                    }
                                    if (e1.getKey().toString().equals(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString())) {
                                        PaymentDTO paymentDTO = (PaymentDTO) object;
                                        paymentDTO.setReceiptNo(Integer.parseInt(incremented_Receipt_No.toString()));
                                        returnMapFromXml.get(e1.getKey().toString()).put(incremented_Receipt_No.toString(), paymentDTO);
                                        System.out.println("099999999999999  " + Integer.parseInt(incremented_Receipt_No.toString()));
                                    }

                                    if (e1.getKey().toString().equals(gen.dto.Constants.CHALAN_TYPE_INDEX.toString())) {
                                        ChalanDTO chalanDTO = (ChalanDTO) object;
                                        chalanDTO.setReceiptNo(Integer.parseInt(incremented_Receipt_No.toString()));
                                        returnMapFromXml.get(e1.getKey().toString()).put(incremented_Receipt_No.toString(), chalanDTO);
                                        System.out.println("10101010101010  " + Integer.parseInt(incremented_Receipt_No.toString()));
                                    }
                                    if (e1.getKey().toString().equals(gen.dto.Constants.CONTRA_TYPE_INDEX.toString())) {
                                        ContraDTO contraDTO = (ContraDTO) object;
                                        contraDTO.setReceiptNo(Integer.parseInt(incremented_Receipt_No.toString()));
                                        returnMapFromXml.get(e1.getKey().toString()).put(incremented_Receipt_No.toString(), contraDTO);
                                        System.out.println("11 11  11  11  11  " + Integer.parseInt(incremented_Receipt_No.toString()));
                                    }
                                    returnMapFromXml.get(e1.getKey().toString()).remove(receip_No_from_Copy_Map);
                                    System.out.println(" Copy Map -------------- " + receip_No_from_Copy_Map);
                                }
                            }
                        }
                    }
                }

                for (Map.Entry<String, String> e : TagsHelper1.maxReceiptNoWithVoucherTypeMapping.entrySet()) {
                    System.out.println("E KEY  -----------------" + e.getKey());
                    System.out.println("E VALUE   -----------------" + e.getValue());
                }

                Object object = pass_objectMap.get("mapValuePresent");
                Map<String, Set<String>> value_Present_Map = (Map<String, Set<String>>) object;
                gen.ImpExp.TagHelper2.insert_All_VoucherTransaction(returnMapFromXml, value_Present_Map, gen.ImpExp.EnumAction.OVERRIDE, null);
                JOptionPane.showMessageDialog(CopyPasteReplaceImportExport.this, "Import Successful");
            } else {
                JOptionPane.showMessageDialog(CopyPasteReplaceImportExport.this, "You have skipped all transactions for Import");
            }


            buttonBackActionPerformed(evt);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceImportExport.class.getName()).log(
                    Level.SEVERE, null, ex);
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
    private JButton buttonSkip_To_Copy;
    private JButton buttonSkip_To_Replace;
    private JButton buttonCopy_To_Skip;
    private JButton buttonCopy_To_Replace;
    private JButton buttonReplace_To_Copy;
    private JButton buttonReplace_To_Skip;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    // private JButton buttonSubmit;
    // private JButton buttonSkip;
    private JButton buttonOverride;
    private JLabel labelSearch;
    private JTextField jTextFieldSearch;
    private JTable jTableGroupList;
    private DefaultTableModel transactionTableModel1;
    private JTextField tfGroupUnderText;
    private JTable table_Copy;
    private JTable table_Replace;
    private JCheckBox jCheckBox1;
    private JCheckBox jCheckBox2;
    private JCheckBox jCheckBox3;

    private void initilize() throws SQLException, ParseException, Exception {
        initilizeGUIComponents();
        // initVariables();
        bindTOGUI();
    }

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }

    private void initComponentActiveInActive() {
        // buttonSkip.setEnabled(false);
    }

    private void bindTOGUI() throws ParseException, SQLException, Exception {

        Object object = pass_objectMap.get("mapValuePresent");
        Map<String, Set<String>> value_Present_Map = (Map<String, Set<String>>) object;

        System.out.println("--------"
                + value_Present_Map.get(gen.dto.Constants.SALE_TYPE_INDEX
                .toString()));

        for (Map.Entry<String, Set<String>> e : value_Present_Map.entrySet()) {
            for (String set : value_Present_Map.get(e.getKey())) {
                String voucher_Type = "";
                if (e.getKey().toString()
                        .equals(gen.dto.Constants.SALE_TYPE_INDEX.toString())) {
                    voucher_Type = "SALE";
                }
                if (e.getKey().equals(
                        gen.dto.Constants.PURCHASE_TYPE_INDEX.toString())) {
                    voucher_Type = "PURCHASE";
                }
                if (e.getKey().equals(
                        gen.dto.Constants.RECEIPT_TYPE_INDEX.toString())) {
                    voucher_Type = "RECEIPT";
                }
                if (e.getKey().equals(
                        gen.dto.Constants.PAYMENT_TYPE_INDEX.toString())) {
                    voucher_Type = "PAYMENT";
                }
                if (e.getKey().equals(
                        gen.dto.Constants.CHALAN_TYPE_INDEX.toString())) {
                    voucher_Type = "CHALAN";
                }
                if (e.getKey().equals(
                        gen.dto.Constants.CONTRA_TYPE_INDEX.toString())) {
                    voucher_Type = "CONTRA";
                }
                dtm_Skip.addRow(new Object[]{new Boolean(false), voucher_Type, set});
            }
        }

        // for (Set<String> object1 :
        // value_Present_Map.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()))
        // {
        // SaleDTO saleDTO = (SaleDTO) object;
        // }

    }

    private Map<String, Set<String>> return_Import_Export_Receipt(JTable pass_Table) {
        Map<String, Set<String>> user_Selected_ReceiptNoMap1 = new HashMap<String, Set<String>>();
        for (int i = 0; i < pass_Table.getRowCount(); i++) {

            System.out.println("Vaslue -----  "
                    + pass_Table.getValueAt(i, 0).toString().equals("false"));
            System.out.println("Table Name ------ " + pass_Table.getName());
            System.out.println("Receip Type   ------ " + pass_Table.getValueAt(i, 1).toString());
            System.out.println(" Type   ------ " + pass_Table.getValueAt(i, 2).toString());
            if (pass_Table.getValueAt(i, 0).toString().equals("false")) {
                System.out.println("YABLe -------------  " + pass_Table.getValueAt(i, 1).toString());
                if (pass_Table.getValueAt(i, 1).toString().equals("SALE")) {
//		    if (user_Selected_ReceiptNoMap1.get(pass_Table.getValueAt(i, 1).toString()) == null) {
                    if (user_Selected_ReceiptNoMap1.get(gen.dto.Constants.SALE_TYPE_INDEX.toString()) == null) {
                        user_Selected_ReceiptNoMap1.put(gen.dto.Constants.SALE_TYPE_INDEX.toString(), new HashSet<String>());
                    }
                } else if (pass_Table.getValueAt(i, 1).toString().equals("PURCHASE")) {
                    if (user_Selected_ReceiptNoMap1.get(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString()) == null) {
                        user_Selected_ReceiptNoMap1.put(gen.dto.Constants.PURCHASE_TYPE_INDEX.toString(), new HashSet<String>());
                    }
                } else if (pass_Table.getValueAt(i, 1).toString().equals("PAYMENT")) {
                    if (user_Selected_ReceiptNoMap1.get(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString()) == null) {
                        user_Selected_ReceiptNoMap1.put(gen.dto.Constants.PAYMENT_TYPE_INDEX.toString(), new HashSet<String>());
                    }
                } else if (pass_Table.getValueAt(i, 1).toString().equals("RECEIPT")) {
                    if (user_Selected_ReceiptNoMap1.get(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString()) == null) {
                        user_Selected_ReceiptNoMap1.put(gen.dto.Constants.RECEIPT_TYPE_INDEX.toString(), new HashSet<String>());
                    }
                } else if (pass_Table.getValueAt(i, 1).toString().equals("CONTRA")) {
                    if (user_Selected_ReceiptNoMap1.get(gen.dto.Constants.CONTRA_TYPE_INDEX.toString()) == null) {
                        user_Selected_ReceiptNoMap1.put(gen.dto.Constants.CONTRA_TYPE_INDEX.toString(), new HashSet<String>());
                    }
                } else if (pass_Table.getValueAt(i, 1).toString().equals("CHALAN")) {
                    if (user_Selected_ReceiptNoMap1.get(gen.dto.Constants.CHALAN_TYPE_INDEX.toString()) == null) {
                        user_Selected_ReceiptNoMap1.put(gen.dto.Constants.CHALAN_TYPE_INDEX.toString(), new HashSet<String>());
                    }
                }

                // pass VAoucher zType........
                String voucher_Type = "";
                if (pass_Table.getValueAt(i, 1).toString().equals("SALE")) {
                    voucher_Type = gen.dto.Constants.SALE_TYPE_INDEX.toString();
                } else if (pass_Table.getValueAt(i, 1).toString().equals("PURCHASE")) {
                    voucher_Type = gen.dto.Constants.PURCHASE_TYPE_INDEX.toString();
                } else if (pass_Table.getValueAt(i, 1).toString().equals("PAYMENT")) {
                    voucher_Type = gen.dto.Constants.PAYMENT_TYPE_INDEX.toString();
                } else if (pass_Table.getValueAt(i, 1).toString().equals("RECEIPT")) {
                    voucher_Type = gen.dto.Constants.RECEIPT_TYPE_INDEX.toString();
                } else if (pass_Table.getValueAt(i, 1).toString().equals("CONTRA")) {
                    voucher_Type = gen.dto.Constants.CONTRA_TYPE_INDEX.toString();
                } else if (pass_Table.getValueAt(i, 1).toString().equals("CHALAN")) {
                    voucher_Type = gen.dto.Constants.CHALAN_TYPE_INDEX.toString();
                }

                user_Selected_ReceiptNoMap1.get(voucher_Type).add(pass_Table.getValueAt(i, 2).toString());
            }
        }

        return user_Selected_ReceiptNoMap1;
    }
}
