package gen.account.StockItemFormation;

/**
 *
 * @author admin
 */
import gen.ImpExp.selectImport.*;
import gen.dto.Label;
import gen.dto.Util;
import gen.mainclass.MainClass;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

@SuppressWarnings("serial")
public class CopyPasteReplaceStockItems extends JInternalFrame {

    private JTable table_Skip;
    DefaultTableModel dtm_Skip, dtm_Copy, dtm_Replace;
    Object colNames[] = {"", "Stock Item Name", "ID"};
    Object[][] datarow = {};
    List<StockItemDTO> allStockItemDTOs = new ArrayList<StockItemDTO>();
    List<StockItemDTO> displayTableStockItemDTOs = new ArrayList<StockItemDTO>();

    public CopyPasteReplaceStockItems(List<StockItemDTO> stockItemDTOs, List<StockItemDTO> stockItemDTOsFromDisplayTable) {

        try {
            allStockItemDTOs = stockItemDTOs;
            displayTableStockItemDTOs = stockItemDTOsFromDisplayTable;
            initComponents();
            initilize();
            setClosable(true);
        } catch (Exception ex) {
            Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(
                    Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this,
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
//        setFrameIcon(new ImageIcon(
//                CopyPasteReplaceStockItems.class
//                .getResource("/IMages/Kasturi-logo-1.png")));
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

        dtm_Skip = new DefaultTableModel(datarow, colNames);
        table_Skip = new JTable(dtm_Skip);

        table_Skip.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_Skip.getColumnModel().getColumn(1).setPreferredWidth(100);
        table_Skip.getColumnModel().getColumn(2).setPreferredWidth(100);
        TableColumn tc = table_Skip.getColumnModel().getColumn(0);
        tc
                .setCellEditor(table_Skip.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table_Skip.getDefaultRenderer(Boolean.class));
        scrollPane.setViewportView(table_Skip);
        JScrollPane scrollPane_1 = new JScrollPane();

        groupInformationPanel.add(scrollPane_1,
                "cell 2 1 1 26,grow");

        table_Copy = new JTable();

        dtm_Copy = new DefaultTableModel(datarow, colNames);
        table_Copy = new JTable(dtm_Copy);
        tc = table_Copy.getColumnModel().getColumn(0);

        tc.setCellEditor(table_Copy.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table_Copy.getDefaultRenderer(Boolean.class));
        table_Copy.getColumnModel()
                .getColumn(0).setPreferredWidth(50);
        table_Copy.getColumnModel()
                .getColumn(1).setPreferredWidth(100);
        table_Copy.getColumnModel()
                .getColumn(2).setPreferredWidth(100);
        scrollPane_1.setViewportView(table_Copy);

        JScrollPane scrollPane_2 = new JScrollPane();
        groupInformationPanel.add(scrollPane_2, "cell 4 1 1 26,grow");

        dtm_Replace = new DefaultTableModel(datarow, colNames);
        table_Replace = new JTable(dtm_Replace);
        tc = table_Replace.getColumnModel().getColumn(0);

        tc.setCellEditor(table_Replace.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table_Replace.getDefaultRenderer(Boolean.class));
        table_Replace.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_Replace.getColumnModel().getColumn(1).setPreferredWidth(100);
        table_Replace.getColumnModel().getColumn(2).setPreferredWidth(100);
        scrollPane_2.setViewportView(table_Replace);

        scrollPane_2.setViewportView(table_Replace);

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
        buttonBack.setMnemonic('B');
        controlsPanel.add(buttonBack, new CC().spanX(5).split(5).tag("other")
                .growX());

        buttonOverride = new JButton("Overide");
        buttonOverride.setMnemonic('O');
        controlsPanel.add(buttonOverride, new CC().tag("other").growX());

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

                try {
                    formInternalFrameActivated(evt);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this,
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(
                            Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
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
                try {
                    buttonSkip_To_CopyActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
                try {
                    buttonSkip_To_ReplaceActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
                try {
                    buttonCopy_To_SkipActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
                try {
                    buttonCopy_To_ReplaceActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
                try {
                    buttonReplace_To_CopyActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
                try {
                    buttonReplace_To_SkipActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
                try {
                    buttonBackActionPerformed(evt);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
                }
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
//		    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this,
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
                    JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, ex.getMessage());
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

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) throws Exception {
        try {
            MainClass.setstaticvar();
            // this.dispose();
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) throws PropertyVetoException {
        // TODO add your handling code here:
        try {
            MainClass.setstaticvar();
            // this.dispose();
            MainClass m = new MainClass();
            m.menuselection(1);
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
            Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw ex;
        }
    }

    private void buttonSkip_To_CopyActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
//	    if (table_Skip.getSelectedRow() >= 0) {
            // getRowCount row must be greater than 0 
            if (table_Skip.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Skip.getRowCount(); i++) {
                    // selected row
                    if (table_Skip.getValueAt(i, 0) != null && !table_Skip.getValueAt(i, 0).toString().equals("false")) {
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
            throw ex;
        }
    }

    private void buttonSkip_To_ReplaceActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            if (table_Skip.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Skip.getRowCount(); i++) {
                    // selected row
                    System.out.println(" I        ------------ " + i);
                    if (table_Skip.getValueAt(i, 0) != null && !table_Skip.getValueAt(i, 0).toString().equals("false")) {
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
            throw ex;
        }
    }

    private void buttonCopy_To_SkipActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            if (table_Copy.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Copy.getRowCount(); i++) {
                    // selected row
                    if (table_Copy.getValueAt(i, 0) != null && !table_Copy.getValueAt(i, 0).toString().equals("false")) {
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
            throw ex;
        }
    }

    private void buttonCopy_To_ReplaceActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            // selected row must be greater than 0 
            if (table_Copy.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Copy.getRowCount(); i++) {
                    // selected row
                    if (table_Copy.getValueAt(i, 0) != null && !table_Copy.getValueAt(i, 0).toString().equals("false")) {
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
            throw ex;
        }
    }

    private void buttonReplace_To_CopyActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            // selected row must be greater than 0 
            if (table_Replace.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Replace.getRowCount(); i++) {
                    // selected row
                    if (table_Replace.getValueAt(i, 0) != null && !table_Replace.getValueAt(i, 0).toString().equals("false")) {
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
            throw ex;
        }
    }

    private void buttonReplace_To_SkipActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // selected row must be greater than 0 
            if (table_Replace.getRowCount() > 0) {
                // find selected row from tableCompanyList
                for (int i = 0; i < table_Replace.getRowCount(); i++) {
                    // selected row
                    if (table_Replace.getValueAt(i, 0) != null && !table_Replace.getValueAt(i, 0).toString().equals("false")) {
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
            throw ex;
        }
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
        displayRepeatedStockItems();
    }

    private void initilizeGUIComponents() {
        initComponentActiveInActive();
    }

    private void initComponentActiveInActive() {
    }

    private void displayRepeatedStockItems() {
        for (int i = 0; i < displayTableStockItemDTOs.size(); i++) {
            for (int j = 0; j < allStockItemDTOs.size(); j++) {
                if (displayTableStockItemDTOs.get(i).getName().equalsIgnoreCase(allStockItemDTOs.get(j).getName())) {
                    System.out.println("displayTableStockItemDTOs.get(i).getName()---" + displayTableStockItemDTOs.get(i).getName());
                    System.out.println("allStockItemDTOs.get(j).getName()---" + allStockItemDTOs.get(j).getName());
                    int row = table_Skip.getRowCount();
                    dtm_Skip.setRowCount(row + 1);
                    table_Skip.setValueAt(displayTableStockItemDTOs.get(i).getName(), row, 1);
                    displayTableStockItemDTOs.get(i).setID(allStockItemDTOs.get(j).getID());
                    table_Skip.setValueAt(displayTableStockItemDTOs.get(i).getID(), row, 2);
                }
            }
        }
    }

    private void buttonOverrideActionPerformed(java.awt.event.ActionEvent evt) throws Exception {

        if (!(table_Copy.getRowCount() > 0) && !(table_Replace.getRowCount() > 0)) {
            JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, "You have skipped all transactions");
        }

        //Another way of doing above

//        if ((table_Copy.getRowCount() <= 0) && (table_Replace.getRowCount() <= 0)) {
//            JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, "You have skipped all transactions");
//        }

        if (table_Copy.getRowCount() > 0) {
            List<StockItemDTO> listOfStockItemDTO = new ArrayList<StockItemDTO>();
            try {
                for (int i = 0; i < table_Copy.getRowCount(); i++) {
                    for (int j = 0; j < displayTableStockItemDTOs.size(); j++) {
//                        if (displayTableStockItemDTOs.get(i).getID() == (allStockItemDTOs.get(j).getID())) {
//                            listOfStockItemDTO.add(displayTableStockItemDTOs.get(i));
//                        }
                        if (table_Copy.getValueAt(i, 2).toString().equalsIgnoreCase(displayTableStockItemDTOs.get(j).getID().toString())) {
                            listOfStockItemDTO.add(displayTableStockItemDTOs.get(j));
                            System.out.println("Date--27-04-2014---" + displayTableStockItemDTOs.get(j).getStockitem_Date());
                            System.out.println("Name---" + displayTableStockItemDTOs.get(j).getName());
                        }
                    }
                }

                StockItemFormationDAO.insertStockItem(listOfStockItemDTO);
                JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, Label.RECORD_SUBMITTED_SUCCESSFULLY);

            } catch (Exception ex) {
                Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }

        if (table_Replace.getRowCount() > 0) {
            List<StockItemDTO> listOfStockItemDTO = new ArrayList<StockItemDTO>();
            try {
                for (int i = 0; i < table_Replace.getRowCount(); i++) {
                    for (int j = 0; j < displayTableStockItemDTOs.size(); j++) {
//                        if (displayTableStockItemDTOs.get(i).getID() == (allStockItemDTOs.get(j).getID())) {
//                            listOfStockItemDTO.add(displayTableStockItemDTOs.get(i));
//                        }
                        if (table_Replace.getValueAt(i, 2).toString().equalsIgnoreCase(displayTableStockItemDTOs.get(j).getID().toString())) {
                            listOfStockItemDTO.add(displayTableStockItemDTOs.get(j));
                        }
                    }
                }

                StockItemFormationDAO.updateStockItem(listOfStockItemDTO);
                JOptionPane.showMessageDialog(CopyPasteReplaceStockItems.this, Label.RECORD_SUBMITTED_SUCCESSFULLY);

            } catch (Exception ex) {
                Logger.getLogger(CopyPasteReplaceStockItems.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
    }
}
