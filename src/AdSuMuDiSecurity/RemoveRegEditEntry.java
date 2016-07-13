package AdSuMuDiSecurity;

import java.util.prefs.Preferences;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class RemoveRegEditEntry {

    static Preferences pref;

    public static void removeEntry() {

        try {
            pref = Preferences.userNodeForPackage(RemoveRegEditEntry.class);
            System.out.println("" + pref);
            pref.removeNode();
            System.out.println("AdSuMuDi Node Removed");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {

        RemoveRegEditEntry r = new RemoveRegEditEntry();
        r.removeEntry();

    }
}
