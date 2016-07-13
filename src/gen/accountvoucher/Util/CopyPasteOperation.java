package gen.accountvoucher.Util;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.table.*;

public class CopyPasteOperation extends EventQueue {

    public JPopupMenu popup;
    JTable table;
    public BasicAction copy, paste;

    public CopyPasteOperation(JTextField textField) {
        //createPopupMenu();
    }

    public void createPopupMenu(JTextComponent text) {
        copy = new CopyAction("Copy", null);
        paste = new PasteAction("Paste", null);
        copy.setTextComponent(text);
        paste.setTextComponent(text);

        popup = new JPopupMenu();
        popup.add(copy);
        popup.add(paste);
        popup.addSeparator();
    }

    public void showPopup(Component parent, MouseEvent me) {
        popup.validate();
        popup.show(parent, me.getX(), me.getY());
    }

    @Override
    protected void dispatchEvent(AWTEvent event) {
        super.dispatchEvent(event);
        if (!(event instanceof MouseEvent)) {
            return;
        }
        MouseEvent me = (MouseEvent) event;
        if (!me.isPopupTrigger()) {
            return;
        }
        if (!(me.getSource() instanceof Component)) {
            return;
        }
        Component comp = SwingUtilities.getDeepestComponentAt((Component) me.getSource(), me.getX(), me.getY());
        if (!(comp instanceof JTextComponent)) {
            return;
        }
        if (MenuSelectionManager.defaultManager().getSelectedPath().length > 0) {
            return;
        }
        createPopupMenu((JTextComponent) comp);
        showPopup((Component) me.getSource(), me);
    }

    public abstract class BasicAction extends AbstractAction {

        public JTextComponent comp;

        public BasicAction(String text, Icon icon) {
            super(text, icon);
            putValue(Action.SHORT_DESCRIPTION, text);
        }

        public void setTextComponent(JTextComponent comp) {
            this.comp = comp;
        }

        public abstract void actionPerformed(ActionEvent e);
    }

    public class CopyAction extends BasicAction {

        public CopyAction(String text, Icon icon) {
            super(text, icon);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl C"));
        }

        public void actionPerformed(ActionEvent e) {
            comp.copy();
        }

        public boolean isEnabled() {
            return comp != null && comp.getSelectedText() != null;
        }
    }

    public class PasteAction extends BasicAction {

        public PasteAction(String text, Icon icon) {
            super(text, icon);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
        }

        public void actionPerformed(ActionEvent e) {
            comp.paste();
        }

        public boolean isEnabled() {
            Transferable content = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            return comp != null && comp.isEnabled() && comp.isEditable()
                    && content.isDataFlavorSupported(DataFlavor.stringFlavor);
        }
    }
}
