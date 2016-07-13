package AdSuMuDiSecurity;

import java.util.prefs.Preferences;

public class AdSuMuDiRegistryEntry {

    static Preferences pref;

    public static void main(String[] args) {

        AdSuMuDiRegistryEntry tr = new AdSuMuDiRegistryEntry();
        System.out.println("Done");

    }

//    AdSuMuDiRegistryEntry() {
//        try { 
//            pref = Preferences.userNodeForPackage(AdSuMuDiRegistryEntry.class); 
//            pref.put("1","1"); 
//            System.out.println("AdSuMuDi Entry Made"); 
//        } catch (Exception e) { 
//            System.out.println(e); 
//        } 
//    } 
    public static void firstEntry() {

        try {
            pref = Preferences.userNodeForPackage(AdSuMuDiRegistryEntry.class);
            pref.put("1", "1");
            System.out.println("AdSuMuDi First Entry Made");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void lastEntry() {

        try {
            pref = Preferences.userNodeForPackage(AdSuMuDiRegistryEntry.class);
            pref.put("1", "2");
            System.out.println("AdSuMuDi Last Entry Made");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void removeEntry() {

        try {
            pref = Preferences.userNodeForPackage(AdSuMuDiRegistryEntry.class);
            pref.removeNode();
            System.out.println("AdSuMuDi Node Removed");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
