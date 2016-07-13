/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.ImpExp;

/**
 *
 * @author admin
 */
public enum EnumAction {

    SKIP("skip"), OVERRIDE("override"), COPY("copy");
    private String action;

    public String getAction() {
	return this.action;
    }

    private EnumAction(String action) {
	this.action = action;
    }
}
