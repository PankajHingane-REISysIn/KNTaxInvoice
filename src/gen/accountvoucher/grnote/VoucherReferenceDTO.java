/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.grnote;

/**
 *
 * @author admin
 */
public class VoucherReferenceDTO {

    private String sale_trans_id = "";
    private String chalan_trans_id = "";
    private int voucher_type = 0;

    /**
     * @return the sale_trans_id
     */
    public String getSale_trans_id() {
        return sale_trans_id;
    }

    /**
     * @param sale_trans_id the sale_trans_id to set
     */
    public void setSale_trans_id(String sale_trans_id) {
        this.sale_trans_id = sale_trans_id;
    }

    /**
     * @return the chalan_trans_id
     */
    public String getChalan_trans_id() {
        return chalan_trans_id;
    }

    /**
     * @param chalan_trans_id the chalan_trans_id to set
     */
    public void setChalan_trans_id(String chalan_trans_id) {
        this.chalan_trans_id = chalan_trans_id;
    }

    /**
     * @return the voucher_type
     */
    public int getVoucher_type() {
        return voucher_type;
    }

    /**
     * @param voucher_type the voucher_type to set
     */
    public void setVoucher_type(int voucher_type) {
        this.voucher_type = voucher_type;
    }
}
