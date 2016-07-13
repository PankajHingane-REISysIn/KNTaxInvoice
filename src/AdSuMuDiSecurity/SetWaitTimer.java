/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import gen.dto.Util;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author pc5
 */
public class SetWaitTimer {

    static final String dir = System.getProperty("user.dir");
    static JDialog dialog = new JDialog();
    static JOptionPane optionPane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

    public static void resumeWaitTimer(JInternalFrame frame) {
        dialog.dispose();
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }

    public static void startWaitTimerForFrame(JInternalFrame frame) {
        Image img = new javax.swing.ImageIcon(dir + "/images/Kasturi-logo-1.png").getImage();
        ((java.awt.Frame) dialog.getOwner()).setIconImage(img);
        optionPane.setIcon(new ImageIcon(new javax.swing.ImageIcon(dir + "/images/Thinfadingline.gif").getImage()));
        optionPane.setMessage("Please Wait While Processing...");
        dialog.add(optionPane);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(frame);
    }
    
    public static void startEmptyWaitTimerForDesktop(JDesktopPane desktopMain) {
        Image img = new javax.swing.ImageIcon(dir + "/images/Kasturi-logo-1.png").getImage();
        ((java.awt.Frame) dialog.getOwner()).setIconImage(img);
        optionPane.setIcon(new ImageIcon(new javax.swing.ImageIcon(dir + "/images/Thinfadingline.gif").getImage()));
        optionPane.setMessage("Please Wait While Processing...");
        dialog.add(optionPane);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(desktopMain);
    }
}
