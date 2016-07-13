/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.account.group;

/**
 *
 * @author Kasturi NovaSoft
 */
public class GroupData
{
    private int id;
    private String name;
    private String alias;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
}
