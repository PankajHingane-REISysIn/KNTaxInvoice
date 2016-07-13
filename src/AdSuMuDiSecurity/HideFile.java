package AdSuMuDiSecurity;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class HideFile {

    /**
     * @param args the command line arguments
     */
    void hide(File src) throws InterruptedException, IOException {
        // win32 command line variant
        Process p = Runtime.getRuntime().exec("attrib +h " + src.getPath());
        p.waitFor(); // p.waitFor() important, so that the file really appears as hidden immediately after function exit.
    }
//    public static void main(String[] args) {
//        try {
//            HideFile t = new HideFile();
//            File src = new File("D:\\Sam.properties.txt");
//            t.hide(src);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(HideFile.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(HideFile.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
