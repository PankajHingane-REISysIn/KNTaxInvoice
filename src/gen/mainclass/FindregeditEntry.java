/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.mainclass;

import AdSuMuDiSecurity.WinRegistry;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FindregeditEntry {

    String value = "";

    public String read() {
	try {
	    value = WinRegistry.readString(
		    WinRegistry.HKEY_LOCAL_MACHINE, //HKEY
		    "SOFTWARE\\Kasturi Novasoft\\AdSuMuDi Beta 9.0", //Key
		    "Location");                                              //ValueName
	    System.out.println("Windows Distribution = " + value);
	} catch (IllegalArgumentException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(FindregeditEntry.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(FindregeditEntry.class.getName()).log(Level.SEVERE, null, ex);
	} catch (InvocationTargetException ex) {
	    ex.printStackTrace();
	    Logger.getLogger(FindregeditEntry.class.getName()).log(Level.SEVERE, null, ex);
	}
	return value;
    }

    public static void main(String args[]) {

	FindregeditEntry r = new FindregeditEntry();
	r.read();

    }
}
