/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author pc5
 */
public class FieldValidationException extends Exception{
    public FieldValidationException(String errorMsg){
        super(errorMsg);
    }
    
    
}
