/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiProductRegistration;

/**
 *
 * @author admin
 */
import gen.dto.Constants;
import gen.other.login.SupremoLink;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.codehaus.groovy.tools.shell.commands.ShowCommand;

public class VersionExpired extends JFrame {
    
    private JPanel pan;
    private JLabel website;
    final String dir = System.getProperty("user.dir");
    Image img = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
    
    public VersionExpired() {
        this.setTitle("Purchase AdSuMuDi...");
        this.setSize(500, 90);
        this.setLocationRelativeTo(null);
        this.setIconImage(img);
        
        pan = new JPanel();
        
        JButton buttonPurchase = new JButton("Purchase Here...!");
        pan.add(buttonPurchase);
        
        website = new JLabel();
        
        
        website.setText("<html>Trial Version Time Period Expired Visit Us At:<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<a href=\"\">http://www.kasturinovasoft.com/</a><br>To Purchase Licenced Version Of AdSuMuDi</html>");
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pan.add(website);
        this.setContentPane(pan);
        this.setVisible(true);
        
        goWebsite(website);
        
        buttonPurchase.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    buttonPurchaseActionPerformed(evt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VersionExpired.this, ex.getMessage());
                }
            }
        });
    }
    
    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    try {
                        Desktop.getDesktop().browse(new URI("http://www.kasturinovasoft.com/"));
                    } catch (IOException ex) {
                        Logger.getLogger(SupremoLink.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (URISyntaxException ex) {
                }
            }
        });
    }
    
    public void buttonPurchaseActionPerformed(ActionEvent evt) throws Exception {
        try {
            Constants.purchaseWindow = 1;
            VersionExpired.this.dispose();
            JFrame frame = new JFrame("Purchase AdSuMuDi Product Licence");
            frame.setResizable(false);
            frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            Image img1 = new ImageIcon(getClass().getResource("/images/Kasturi-logo-1.png")).getImage();
            frame.setIconImage(img1);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = (int) (screenSize.height * 2 / 3);
            int width = (int) (screenSize.width * 2 / 3.5);
            Dimension jInternalFrameSize = frame.getSize();
            PurchaseLicence form = new PurchaseLicence("Purchase Licence");
            form.setLocation(((screenSize.width) - (jInternalFrameSize.width)) / 2,
                    ((screenSize.height) - (jInternalFrameSize.height)) / 2);
            form.setVisible(true);
            form.pack();
            form.setClosable(false);
            frame.add(form);
            frame.setSize(width, height);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
            frame.setVisible(true);
            form.setSelected(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public static void main(String args[]) {
        
        VersionExpired v = new VersionExpired();
        v.setVisible(true);
        
    }
}
