/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.mainclass;

/**
 *
 * @author admin
 */
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pc5
 */
public class SetWaitTimer {

    static final String dir = System.getProperty("user.dir");
    static JDialog dialog = new JDialog();
    static JOptionPane optionPane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

    public static void startWaitTimer(JInternalFrame frame) {

        long endTime = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < endTime) {
            frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        }
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public static void resumeWaitTimer(JInternalFrame frame) {
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public static void startEmptyWaitTimerForFrame(JInternalFrame frame) {
        //Image img = new javax.swing.ImageIcon(dir + "/images/Kasturi-logo-1.png").getImage();
        //((java.awt.Frame) dialog.getOwner()).setIconImage(img);
	System.out.println("DDir ----------------------------------"+dir);
        optionPane.setIcon(new ImageIcon(new javax.swing.ImageIcon(dir + "/images/Thinfadingline.gif").getImage()));
        optionPane.setMessage("Please Wait While Processing...");
        dialog.add(optionPane);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(frame);
        long endTime = System.currentTimeMillis() + 15000;
        while (System.currentTimeMillis() < endTime) {
            frame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        }
        frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
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
        long endTime = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < endTime) {
            desktopMain.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        }
        desktopMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        dialog.setVisible(false);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
    }
    
}

