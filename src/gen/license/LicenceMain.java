/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.license;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABC
 */
public class LicenceMain {

    protected boolean checkLicense() {
        // Open the license manager
        LicenseManager licenseManager;
        AbstractLicense abstractLicense = new AbstractLicense() {
        };
        try {
            URL resource = getClass().getResource("pubkey.der");
            InputStream stream = resource.openStream();
            try {
                licenseManager = new LicenseManager(stream, null);
            } finally {
                stream.close();
            }
        } catch (Exception e) {
//                MessageUtils.openWarning(null, "L'installation d'Ekwos est "
//                                + "défectueuse, veuillez ré-installer Ekwos.", e);
            return false;
        }

        // Open the license file
//        File licenseFile = new File(LICENSE_FILE);
        File licenseFile = new File("");
        if (!licenseFile.exists()) {
            licenseFile = new File(AbstractLicense.TYPE_TRIAL);
        }
        try {
            abstractLicense = (AbstractLicense) licenseManager
                    .readLicenseFile(licenseFile);
        } catch (InvalidKeyException e) {
//                MessageUtils.openWarning(null,
//                                "Votre license est invalid ou coromput.", e);
            return false;
        } catch (NoSuchAlgorithmException e) {
            // Should never happen
//                MessageUtils.openWarning(null, "L'installation d'Ekwos est "
//                                + "défectueuse, veuillez ré-installer Ekwos.", e);
            return false;
        } catch (SignatureException e) {
//                MessageUtils.openInfo(null, "L'installation d'Ekwos est "
//                                + "défectueuse, veuillez ré-installer Ekwos.");
            return false;
        } catch (FileNotFoundException e) {
//                MessageUtils.openWarning(null,
//                                "Impossible de trouver votre license.", e);
            return false;
        } catch (IOException e) {
//                MessageUtils.openWarning(null, "Impossible de lire votre license.",
//                                e);
            return false;
        } catch (ClassNotFoundException e) {
//                MessageUtils.openWarning(null,
//                                "Votre license est invalid ou coromput.", e);
            return false;
        }

        // Validate the license data
        String currentVersion = abstractLicense.getVersion();
        try {
            abstractLicense.validate(new Date(), currentVersion);
        } catch (LicenseExpiredException e) {
            if (abstractLicense.getExpiration() != null) {
                String date = DateFormat.getDateInstance().format(
                        abstractLicense.getExpiration());
//                        MessageUtils.openWarning(null,
//                                        "Votre license d'Ekwos est expiré depuis le " + date);
            } else {
//                        MessageUtils.openWarning(null, "Votre license est invalide.");
            }
            return false;
        } catch (LicenseException e) {
//                MessageUtils.openWarning(null, "Votre license est invalide.", e);
            return false;
        }

        return true;
    }

    public static void main(String srgs[]) {
        File publicfile = new File("c:/publickey.der");
        File privatefile = new File("c:/privatekey.der");
        File licenceFile = new File("c:/licence.dat");
        try {
            LicenseManager licenceManager = new LicenseManager(publicfile, privatefile);
            // AbstractLicense abstractLicense=new AbstractLicense() {};
            //licenceManager.writeLicense(abstractLicense, licenceFile);
            //LicenseManager licenceManager=new LicenseManager(null, null)
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(LicenceMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LicenceMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
