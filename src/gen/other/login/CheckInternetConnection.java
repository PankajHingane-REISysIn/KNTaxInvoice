package gen.other.login;

import exception.FieldValidationException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc5
 */
public class CheckInternetConnection {

    public static void testInternetConnection() throws Exception {
        //todo
        //use inbuild method to check internet connectivity.

//        URL url = new URL("http://www.google.com");
//        URLConnection connection = url.openConnection();
//        System.out.println("connection.getContentLength()--->>>"+connection.getContentLength());
//        if (connection.getContentLength() == -1) {
//            throw new FieldValidationException("Either No Internet Conncection or server is down");
//        }

//        boolean networkValue = InetAddress.getByName("www.google.com").isReachable(5000);
//        if (!InetAddress.getByName("www.google.com").isReachable(5000)) {
//            throw new FieldValidationException("Either No Internet Conncection or server is down");
//        }

        try {
            URL myURL = new URL("http://www.google.com");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
        } catch (Exception e) {
            throw new FieldValidationException("Either No Internet Conncection or server is down");
        }
    }
}
