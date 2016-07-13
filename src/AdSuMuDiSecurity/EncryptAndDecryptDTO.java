/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AdSuMuDiSecurity;

import java.security.PrivateKey;

/**
 *
 * @author pc5
 */
public class EncryptAndDecryptDTO {

    private PrivateKey privateKey = null;
    private byte[] ciphertext = null;

    /**
     * @return the privateKey
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey the privateKey to set
     */
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @return the ciphertext
     */
    public byte[] getCiphertext() {
        return ciphertext;
    }

    /**
     * @param ciphertext the ciphertext to set
     */
    public void setCiphertext(byte[] ciphertext) {
        this.ciphertext = ciphertext;
    }
}
