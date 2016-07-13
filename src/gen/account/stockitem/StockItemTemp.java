package gen.account.stockitem;

public class StockItemTemp 
{
    String name="";     //finish type
    int id=0;           //finish type id
    
    String sg_name="";     //Stock group name
    int sg_id=0;           //stock group id
    
    String uom_name="";     //unit of measure
    int uom_id=0;           //uom id

    public void setSg_id(int sg_id) {
        this.sg_id = sg_id;
    }

    public void setSg_name(String sg_name) {
        this.sg_name = sg_name;
    }

    public void setUom_id(int uom_id) {
        this.uom_id = uom_id;
    }

    public void setUom_name(String uom_name) {
        this.uom_name = uom_name;
    }

    public int getUom_id() {
        return uom_id;
    }

    public String getUom_name() {
        return uom_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSg_id() {
        return sg_id;
    }

    public String getSg_name() {
        return sg_name;
    }

    public String getName() {
        return name;
    }
}
