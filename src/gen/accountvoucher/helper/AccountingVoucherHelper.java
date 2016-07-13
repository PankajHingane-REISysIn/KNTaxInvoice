/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.accountvoucher.helper;

import gen.database.connection.DatabaseConnection1;
import gen.dto.Constants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc5
 */
public class AccountingVoucherHelper {

    public static synchronized boolean checkAccountVoucherNumberAvailabilty(Integer voucherNo, Integer transType) throws SQLException, Exception {
        return checkAccountVoucherNumberAvailabiltyByDate(voucherNo, transType, new Date() );
    }

    public static synchronized boolean checkAccountVoucherNumberAvailabiltyByDate(Integer voucherNo, Integer transType, Date invoiceDate) throws SQLException, Exception {
	Boolean isExist = false;
	Connection con = null;
	try {
            if( invoiceDate == null ) {
                invoiceDate = new Date();
            }
	    con = DatabaseConnection1.GetConnection();
            String dateStr = "";
            if(invoiceDate != null){
                dateStr = getDateBetweenString( invoiceDate );
            }
	    PreparedStatement prpdStmt = con.prepareStatement("select trans_receiptNo from tbltransactionmain where trans_typeIndex=" + transType + " and trans_receiptNo=" + voucherNo + dateStr);
	    ResultSet rs = prpdStmt.executeQuery();
            System.out.println("===========dateStr:" + dateStr);
	    if (rs.next()) {
                System.out.println("Record exist");
		isExist = true;
	    }
	} catch (Exception e) {
	    if (con != null && !con.isClosed()) {
		con.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
	return isExist;
    }
    
    private static String getDateBetweenString(Date invoiceDate) {
        Calendar cal = Calendar.getInstance();
                cal.setTime(invoiceDate);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                
                System.out.println("===========invoiceDate:" + invoiceDate.toString());
                System.out.println("===========month:" + month);
                System.out.println("===========year:" + year);

                Calendar cal1 = Calendar.getInstance();
                cal1.set( month<=2 ? year -1 : year, 3, 1);
                Date firstDate = cal1.getTime();
                
                System.out.println("===========firstDate:" + firstDate.toString());
                
                Calendar cal2 = Calendar.getInstance();
                cal2.set( month<=2 ? year : year+1, 2, 31);
                Date lastDate = cal2.getTime();
                System.out.println("===========lastDate:" + lastDate.toString());
                return " and trans_date between '" + Constants.simpleDateFormatDatabaseWithDash.format( firstDate ) + "' and '" +  Constants.simpleDateFormatDatabaseWithDash.format( lastDate ) +"'";
    }

    public static synchronized Integer getMaxAccountVoucherNumber(Integer transType) {
        return getMaxAccountVoucherNumber(transType, null);
    }
    public static synchronized Integer getMaxAccountVoucherNumber(Integer transType, Date invoiceDate) {
	Integer receiptNo = 1;
	Connection con = DatabaseConnection1.GetConnection();
	try {
            String invoiceDateStr = "";
            if( invoiceDate!= null) {
                invoiceDateStr = getDateBetweenString(invoiceDate);
            }
	    PreparedStatement prpdStmt = con.prepareStatement("select max(trans_receiptNo) as trans_receiptNo from tbltransactionmain where trans_typeIndex=" + transType + invoiceDateStr);
	    ResultSet rs = prpdStmt.executeQuery();
	    if (rs.next()) {
		receiptNo = rs.getInt("trans_receiptNo");
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		con.close();
	    } catch (SQLException ex) {
		Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return receiptNo;
    }
    
    public static synchronized Integer getNextAccountVoucherNumber(Integer transType) throws Exception {
        return getNextAccountVoucherNumber(transType, null);
    }

    public static synchronized Integer getNextAccountVoucherNumber(Integer transType, Date invoiceDate) throws Exception {
	String fieldAPIName = "";
	if (transType == Constants.SALE_TYPE_INDEX) {
	    fieldAPIName = "salemaxid";
	} else if (transType == Constants.PURCHASE_TYPE_INDEX) {
	    fieldAPIName = "purchasemaxid";
	} else if (transType == Constants.RECEIPT_TYPE_INDEX) {
	    fieldAPIName = "receiptmaxid";
	} else if (transType == Constants.PAYMENT_TYPE_INDEX) {
	    fieldAPIName = "paymentmaxid";
	} else if (transType == Constants.CHALAN_TYPE_INDEX) {
	    fieldAPIName = "challanmaxid";
	} else if (transType == Constants.JOURNAL_TYPE_INDEX) {
	    fieldAPIName = "journalmaxid";
	} else if (transType == Constants.CONTRA_TYPE_INDEX) {
	    fieldAPIName = "contramaxid";
	}else if (transType == Constants.PURCHASE_ORDER_TYPE_INDEX) {
            fieldAPIName = "pomaxid";
        } else if (transType == Constants.GRNOTE_TYPE_INDEX) {
            fieldAPIName = "grnotemaxid";
        }
	Integer receiptNo = 1;
	Connection con = DatabaseConnection1.GetConnection();
	try {
	    PreparedStatement prpdStmt = con.prepareStatement("select " + fieldAPIName + " as trans_receiptNo from tblaccountvouchersmaxid");
	    ResultSet rs = prpdStmt.executeQuery();
	    if (rs.next()) {
		receiptNo = rs.getInt("trans_receiptNo");
	    }
	    receiptNo = receiptNo + 1;

	    if (checkAccountVoucherNumberAvailabiltyByDate(receiptNo, transType, invoiceDate)) {
		receiptNo = getMaxAccountVoucherNumber(transType, invoiceDate);
		receiptNo = receiptNo + 1;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		con.close();
	    } catch (SQLException ex) {
		Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return receiptNo;
    }

    public static synchronized void updateVoucherNumber(int transType, Integer maxReceiptID) throws Exception {
	String fieldAPIName = "";
	if (transType == Constants.SALE_TYPE_INDEX) {
	    fieldAPIName = "salemaxid";
	} else if (transType == Constants.PURCHASE_TYPE_INDEX) {
	    fieldAPIName = "purchasemaxid";
	} else if (transType == Constants.RECEIPT_TYPE_INDEX) {
	    fieldAPIName = "receiptmaxid";
	} else if (transType == Constants.CHALAN_TYPE_INDEX) {
	    fieldAPIName = "challanmaxid";
	} else if (transType == Constants.PAYMENT_TYPE_INDEX) {
	    fieldAPIName = "paymentmaxid";
	} else if (transType == Constants.JOURNAL_TYPE_INDEX) {
	    fieldAPIName = "journalmaxid";
	} else if (transType == Constants.CONTRA_TYPE_INDEX) {
	    fieldAPIName = "contramaxid";
	}else if (transType == Constants.PURCHASE_ORDER_TYPE_INDEX) {
            fieldAPIName = "pomaxid";
        } else if (transType == Constants.GRNOTE_TYPE_INDEX) {
            fieldAPIName = "grnotemaxid";
        }
	Connection con = null;
	try {
	    con = DatabaseConnection1.GetConnection();
	    PreparedStatement prpdStmt = con.prepareStatement("update tblaccountvouchersmaxid set " + fieldAPIName + " = " + maxReceiptID);
	    prpdStmt.executeUpdate();
	    prpdStmt.close();
	} catch (Exception ex) {
	    Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
	    throw ex;
	} finally {
	    try {
		con.close();
	    } catch (SQLException ex) {
		Logger.getLogger(AccountingVoucherHelper.class.getName()).log(Level.SEVERE, null, ex);
		throw ex;
	    }
	}
    }

    public static void deleteTransaction(long transID, int check_chalan_sale) throws SQLException, Exception {
	Connection conn = null;
	try {
	    conn = DatabaseConnection1.GetConnection();
	    conn.setAutoCommit(false);
	    Statement st1 = conn.createStatement();
	    Statement st2 = conn.createStatement();
	    Statement st3 = conn.createStatement();
	    String query = "";

            if (check_chalan_sale == 7) {
                query = "delete from tbltranscactionchange where chalan_trans_id = ? and voucher_type = ? ";
                PreparedStatement prmt = conn.prepareStatement(query);
                prmt.setString(1, "" + transID);
                prmt.setString(2, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
                prmt.executeUpdate();
                prmt.close();
            } else if (check_chalan_sale == 1) {
                query = "delete from tbltranscactionchange where sale_trans_id = ? and voucher_type = ? ";
                PreparedStatement prmt = conn.prepareStatement(query);
                prmt.setString(1, "" + transID);
                prmt.setString(2, gen.dto.Constants.CHALAN_TYPE_INDEX.toString());
                prmt.executeUpdate();
                prmt.close();
            } else if (check_chalan_sale == 11) {
                query = "delete from tbltranscactionchange where chalan_trans_id = ? and voucher_type = ? ";
                PreparedStatement prmt = conn.prepareStatement(query);
                prmt.setString(1, "" + transID);
                prmt.setString(2, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString());
                prmt.executeUpdate();
                prmt.close();
            } else if (check_chalan_sale == 12) {
                query = "delete from tbltranscactionchange where sale_trans_id = ? and voucher_type = ? ";
                PreparedStatement prmt = conn.prepareStatement(query);
                prmt.setString(1, "" + transID);
                prmt.setString(2, gen.dto.Constants.PURCHASE_ORDER_TYPE_INDEX.toString());
                prmt.executeUpdate();
                prmt.close();
            }
	    query = "delete from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id=" + transID + ")";
	    st1.executeUpdate(query);

	    query = "delete from tblinventorytransaction where trans_id=" + transID + "";
	    st1.executeUpdate(query);

	    query = "delete from tbltransactionledger where trans_id=" + transID + "";
	    st2.executeUpdate(query);

	    query = "delete from tbltransactionotherdetails where trans_id=" + transID + "";
	    st2.executeUpdate(query);

	    query = "delete from tbltransactionvat where trans_id=" + transID + "";
	    st3.executeUpdate(query);
	    query = "delete from tbltransactionmain where trans_id=" + transID + " ";
	    st3.executeUpdate(query);
	    conn.commit();
	    conn.close();
	} catch (Exception e) {
	    if (conn != null && !conn.isClosed()) {
		conn.close();
	    }
	    e.printStackTrace();
	    throw e;
	}
    }
}
