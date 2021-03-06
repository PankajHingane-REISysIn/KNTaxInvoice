/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

import java.awt.Font;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author pc5
 */
public class Constants {

    public static String DATABASE_SERVER = "";
    public static String MAIN_CLASS_OPEN_COUNT = "0";
    public static final String LEDGER_NAME = "LedgerName";
    public static final String LEDGER_ID = "LedgerID";
    public static final String GROUP_NAME = "GroupName";
    public static final String GROUP_ID = "GroupID";
    public static final String STOCK_GROUP_NAME = "StockGroupName";
    public static final String STOCK_GROUP_ID = "StockGroupID";
    public static final String STOCK_ITEM_NAME = "StockGroupItemName";
    public static final String STOCK_ITEM_ID = "StockGroupItemID";
    public static final String UNIT_MEASURE_NAME = "UnitMeasureName";
    public static final String UNIT_MEASURE_ID = "UnitMeasureID";
    public static final Integer SALE_TYPE_INDEX = 1;
    public static final Integer PURCHASE_TYPE_INDEX = 2;
    public static final Integer PAYMENT_TYPE_INDEX = 3;
    public static final Integer CONTRA_TYPE_INDEX = 4;
    public static final Integer RECEIPT_TYPE_INDEX = 6;
    public static final Integer CHALAN_TYPE_INDEX = 7;
    public static final Integer JOURNAL_TYPE_INDEX = 8;
    public static final Integer PURCHASE_ORDER_TYPE_INDEX = 11;
    public static final Integer GRNOTE_TYPE_INDEX = 12;
    public static final Integer DEBIT = 2;
    public static final Integer CREDIT = 1;
    public static final Integer CREDIT_DEBIT_NIL = 3;
    public static ScreenDTO screenDTO = new ScreenDTO();
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");
    public static final SimpleDateFormat simpleDateFormatDatabase = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat simpleDateFormatDatabaseWithDash = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat simpleDateFormatGUI = new SimpleDateFormat("dd-MM-yyyy");
    public static final String DATE_FORMAT_STRING = "dd-MM-yyyy";
    public static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat DATE_FORMATER_WITH_SLASH = new SimpleDateFormat("dd/MM/yyyy");
    public static final Integer FONT_SIZE = 12;
    public static Font FONT = new Font("Tahoma", Font.BOLD, FONT_SIZE);
    //fix group Names
    public static final String CASH_IN_HAND = "Cash In Hand";
    public static final String SALES_ACCOUNT = "Sales Account";
    public static final String CURRENT_ASSETS = "Current Assets";
    public static final String PURCHASE_ACCOUNT = "Purchase Account";
    public static final String BANK_ACCOUNT = "Bank Account";
    public static final String SUSPENSE_ACCOUNT = "Suspense Account";
    public static final String CAPITAL_ACCOUNT = "Capital Account";
    public static final String SUNDRY_DEBTORS = "Sundry Debtors";
    public static final String SUNDRY_CREDITORS = "Sundry Creditors";
    //Fix StockGroup Names
    // For Balance Sheet and Profit/Loss
    public static String EXPENSE_DIRECT = "Expense (Direct)";
    public static String EXPENSE_INDIRECT = "Expense (Indirect)";
    public static String INCOME_DIRECT = "Income (Direct)";
    public static String INCOME_INDIRECT = "Income (Indirect)";
    public static String LOAN_LIABILITY = "Loan Liability";
    public static String CURRENT_LIABILITY = "Current Liability";
    //time stamp
    public static Long LEDGER_TIME_STAMP = 0L;
    public static Long STOCK_ITEM_TIME_STAMP = 0L;
    public static Long GROUP_TIME_STAMP = 0L;
    /// constant for offset 
    public static Long OFFSET_VALUE = 0L;
    public static Long PAGINATION_VALUE = 50L;
    // for company Details
    public static String COMPANYDETAILS_DATABASE_CALLING = "";
    public static String CURRENT_COMPANY_ID = "";
    public static String CURRENT_COMPANY_NAME = "";
    public static String CURRENT_COMPANY_CREATED_DATE = "";
    // for user Details
    // for user Details
    public static String CURRENT_USER_ID = "";
    public static String CURRENT_USER_NAME = "";
    public static String Features_Call_Class = "";
//  //JTextField Validations Constants
//    public static final Integer jTextFieldCharacterLengthULTRASMALL = 13;
    public static final Integer jTextFieldCharacterLengthULTRASMALL = 15;
    public static final Integer jTextFieldCharacterLengthEXTRASMALL = 20;
    public static final Integer jTextFieldDecimalPlacesLimit = 4;
    public static final Integer jTextFieldCharacterLengthSMALL = 40;
    public static final Integer jTextFieldCharacterLengthMEDIUM = 100;
    //public static final Long jTextFieldAmountLength = 1000000000000L;
    public static final String jTextFieldAmountLengthValue = "1000000000000";
    public static final BigDecimal jTextFieldAmountLength = new BigDecimal(jTextFieldAmountLengthValue);
    public static final Integer jTextAreaCharacterLengthMEDIUM = 200;
    public static final Integer jTextAreaCharacterLengthEXTRALARGE = 1000;
    public static final Integer jTextAreaVATPERCENT = 100;
    public static final Integer jTextAreaAMOUNTDIGITS = 13;
    //PurchaseLicenceWindowConstant
    public static Integer purchaseWindow = 0;
    //Window Visibility
    public static Boolean checkVisibilityOfSettingsWindow = false;
    public static Boolean checkVisibilityOfCompanySelectionWindow = false;
    //Tally import from AdSuMuDi Export
    public static Integer REMOTE_ID_For_Tally = 0;
    //Round Off Constants
    public static final String vatTransactionLedgerString_Sale = "Output Vat @";
    public static final String vatTransactionLedgerString_Purchase = "Input Vat @";
    public static final String excise_DutyTransactionLedgerString = "Excise Duty @";
    public static final String ed_Cess_TransactionLedgerString = "Ed Cess @";
    public static final String hed_Cess_TransactionLedgerString = "HEd Cess @";
    public static final String cst_TransactionLedgerString = "CST @";
    public static final String ROUND_OFF_LEDGER = "Round Off";
    public static Boolean IS_ROUND_OFF = true;
    // tax Type
    public static final Integer TAX_VAT_TYPE = 1;
    public static final Integer TAX_EXCISE_TYPE = 2;
    public static final Integer TAX_EDCESS_TYPE = 3;
    public static final Integer TAX_H_EDCESS_TYPE = 4;
    public static final Integer TAX_CST_TYPE = 5;
    public static final Integer TAX_DISCOUNT1_TYPE = 6;
    public static final Integer TAX_DISCOUNT2_TYPE = 7;
    // copy fields for sale from
    public static String DELIVERY_TYPE = "";
    public static String PO_NUMBER = "";
    public static String TRANSPORT_TYPE = "";
    // for Production
    public static final Integer RESIN_TYPE_INDEX = 11;
    public static final Integer IMPREGNATED_PAPER_TYPE_INDEX = 12;
    public static final Integer FINISH_BOARD_TYPE_INDEX = 13;
    public static final Integer PRODUCTION_TYPE_INDEX = 10;
}
