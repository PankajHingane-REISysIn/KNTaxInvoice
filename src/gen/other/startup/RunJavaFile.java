package gen.other.startup;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class RunJavaFile extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("JVM Bit size: " + System.getProperty("sun.arch.data.model"));
            if (System.getProperty("sun.arch.data.model").equals("32")) {
                Runtime.getRuntime().exec("JRE_Installed/jre-7-windows-i586 /s /L C:/setup.log");      //install 32 bit
            } else {
                Runtime.getRuntime().exec("JRE_Installed/jre-7-windows-i64 /s /L C:/setup.log");      //install 64 bit
            }


        } catch (IOException ex) {
            Logger.getLogger(RunJavaFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}