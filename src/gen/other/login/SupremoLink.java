/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.login;

/**
 *
 * @author admin
 */
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SupremoLink extends JFrame {

    private JPanel pan;
    private JLabel website;
    final String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();

    public SupremoLink() {
        this.setTitle("Download Supremo");
        this.setSize(500, 75);
        this.setLocationRelativeTo(null);
        this.setIconImage(img);

        pan = new JPanel();

        website = new JLabel();


        //website.setText("<html> Website : <a href=\"\">http://www.google.com/</a></html>");
        website.setText("<html> Supremo :<a href=\"\">http://www.supremofree.com/download-supremo/</a></html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pan.add(website);
        this.setContentPane(pan);
        this.setVisible(true);

        goWebsite(website);
    }

//    public static void main(String args[]) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new SupremoLink().setVisible(true);
//            }
//        });
//    }
    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://www.supremofree.com/download-supremo/"));
                    } catch (IOException ex) {
                        Logger.getLogger(SupremoLink.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (URISyntaxException ex) {
                }
            }
        });
    }
}
