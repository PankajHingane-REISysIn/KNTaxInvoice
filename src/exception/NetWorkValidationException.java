/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author pc5
 */
public class NetWorkValidationException extends Exception{
    public NetWorkValidationException(String errorMsg){
        super(errorMsg);
    }
}
