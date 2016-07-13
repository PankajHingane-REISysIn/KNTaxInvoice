/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author pc5
 */
public class AdSuMuDiRandomKey {

//    public static void main(String args[]) {
//
//
//        String uuid = UUID.randomUUID().toString();
//        System.out.println("AdSuMuDi Key = " + uuid);
//
//    }
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();
    static List<String> e = new ArrayList<String>();

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    //public static void main(String args[]) {
    public static String generateProductKey() {
        int counter = 5;
        int n = 0;
        String newKey = "";
        String subKey = "";
        AdSuMuDiRandomKey a = new AdSuMuDiRandomKey();
        String key = a.randomString(25);
        e.add(key);
        Collections.shuffle(e);
        System.out.println("Key-->>>" + key);
        for (int i = 0; i < 30; i = i + 5) {

            subKey = key.substring(n, i);
            if (i == counter) {

                newKey = newKey + subKey + "-";
                counter = counter + 5;
                n = n + 5;

            }
        }
        String finalKey = newKey.substring(0, 29);
        System.out.println("NewKey--->>>>" + finalKey);

        return finalKey;
    }
    //}
}
