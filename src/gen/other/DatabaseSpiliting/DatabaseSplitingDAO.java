
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.DatabaseSpiliting;

import gen.database.connection.DatabaseConnection1;
import gen.other.CompanySettings.CompanySettingsDAO;
import gen.other.CompanySettings.CompanySettingsDTO;
import gen.other.DatabaseBackupRestore.Company_InformationDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DatabaseSplitingDAO {

    // this is use to when you want drop database of selected company at the time of restore then it give error
    // so first save it to this varible then drop it 
    // and again assign to global(staic varible ) after database created
    private static String database_Preserve = "";

    // insert new Com0pany
    public static void insert_NewlyCreated_Company(List<String> company_List, String databaseName, String source_Company_Name, String company_ID, List<CompanySettingsDTO> callCompanySettingsDTOLIst) {
        try {

            //set COMPANYDETAILS_DATABASE_CALLING for selection of companies_details database
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            // CompanySettingsDTO for getting all information of previous company
            List<CompanySettingsDTO> CompanySettingsDTOLIst = new ArrayList<CompanySettingsDTO>();
            PreparedStatement prmt = null;

            // this is beacuse if client go directly to create data then we should take data from form in  callCompanySettingsDTOLIst which is passed in method by user when create a new compnay
            if (company_List.isEmpty()) {
                // get all data from create company form
                CompanySettingsDTOLIst = callCompanySettingsDTOLIst;
                System.out.println("callCompanySettingsDTOLIst                                               ");
            } else {
                System.out.println("company_List                                               ");
                for (int i = 0; i < company_List.size(); i++) {
                    // get all data of previous company to insert into new company
                    CompanySettingsDTOLIst = CompanySettingsDAO.getComapany_List(source_Company_Name, company_ID);
                }
            }

            // insert data for  new company
            String query = "insert into tblcompanyinfo(NameOfGod,company_name,company_database,created_by,created_date,Alias,Address,ContactNo,EmailId,IncomeTaxNo,SaleTaxNo,Declaration,TagLine,TermCnditions,SignAuthority,VatTinNo,Note,LBTNo,Field1,Field2,applicable_From_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            prmt = conn.prepareStatement(query);
            for (CompanySettingsDTO companySettingsDTO : CompanySettingsDTOLIst) {
                prmt.setString(1, companySettingsDTO.getNameOfGod());

                if (company_List.isEmpty()) {
                    prmt.setString(2, companySettingsDTO.getCompany_name());
                    prmt.setString(3, databaseName);
                } else {
                    // insert new company_name and Database_name
                    for (int i = 0; i < company_List.size(); i++) {
                        prmt.setString(2, company_List.get(i));
                        prmt.setString(3, databaseName);
                    }
                }

                // insert value of previous company
                prmt.setString(4, companySettingsDTO.getcompany_Created_by());

                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(companySettingsDTO.getcompany_Created_date().trim());
                java.sql.Date sqlStartDate = new Date(date.getTime());
                System.out.println("SQL DATE ------- " + sqlStartDate);
                prmt.setDate(5, sqlStartDate);
//                prmt.setString(6, companySettingsDTO.getcompany_Created_date());
                prmt.setString(6, companySettingsDTO.getcompany_Alias());
                prmt.setString(7, companySettingsDTO.getcompany_Address());
                prmt.setString(8, companySettingsDTO.getcompany_ContactNo());
                prmt.setString(9, companySettingsDTO.getcompany_EmailId());
                prmt.setString(10, companySettingsDTO.getcompany_IncomeTaxNo());
                prmt.setString(11, companySettingsDTO.getcompany_SaleTaxNo());
                prmt.setString(12, companySettingsDTO.getcompany_Declaration());
                prmt.setString(13, companySettingsDTO.getcompany_TagLine());
                prmt.setString(14, companySettingsDTO.getcompany_TermCnditions());
                prmt.setString(15, companySettingsDTO.getcompany_SignAuthority());
                prmt.setString(16, companySettingsDTO.getcompany_VatTinNo());
                prmt.setString(17, companySettingsDTO.getcompany_Note());
                prmt.setString(18, companySettingsDTO.getcompany_LBTNo());
                prmt.setString(19, companySettingsDTO.getcompany_Field1());
                prmt.setString(20, companySettingsDTO.getcompany_Field2());
                java.util.Date date1 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(companySettingsDTO.getCompany_ApplicableFrom_date().trim());
                java.sql.Date Applicable_From_Date = new Date(date1.getTime());
                prmt.setDate(21, Applicable_From_Date);

                prmt.addBatch();
            }
            prmt.executeBatch();

            //remove COMPANYDETAILS_DATABASE_CALLING for selection of companies_details database so that defualt company selection
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            prmt.close();
            conn.commit();
            conn.close();

        } catch (ParseException ex) {
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // insert new Com0pany
    public static void update_Company_Information(List<CompanySettingsDTO> callCompanySettingsDTOLIst, String company_Cliked_Name, Boolean call_From) {
        try {
            //set COMPANYDETAILS_DATABASE_CALLING for selection of companies_details database
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            // conn.setAutoCommit(false);

            String sql = "update tblcompanyinfo set NameOfGod=?,company_name=?,Alias=?,Address=?,ContactNo=?,EmailId=?,IncomeTaxNo=?,SaleTaxNo=?,Declaration=?,TagLine=?,TermCnditions=?,SignAuthority=?,VatTinNo=?,LBTNo=?,modified_date= ?,applicable_From_date = ?,company_database = ?,FIELD1= ?  where company_id = ? and company_name =?";
            PreparedStatement stat = conn.prepareStatement(sql);
            for (CompanySettingsDTO companySettingsDTO : callCompanySettingsDTOLIst) {
                stat.setString(1, companySettingsDTO.getNameOfGod().trim());

                stat.setString(2, companySettingsDTO.getCompany_name().trim());
                System.out.println("Company Nmae  @@@@@@@  ------------ "+company_Cliked_Name);
                System.out.println("Company ID ------------ "+companySettingsDTO.getCompany_id());
                stat.setString(3, companySettingsDTO.getcompany_Alias().trim());
                stat.setString(4, companySettingsDTO.getcompany_Address().trim());
                stat.setString(5, companySettingsDTO.getcompany_ContactNo().trim());
                stat.setString(6, companySettingsDTO.getcompany_EmailId().trim());
                stat.setString(7, companySettingsDTO.getcompany_IncomeTaxNo().trim());
                stat.setString(8, companySettingsDTO.getcompany_SaleTaxNo().trim());
                stat.setString(9, companySettingsDTO.getcompany_Declaration().trim());
                stat.setString(10, companySettingsDTO.getcompany_TagLine().trim());
                stat.setString(11, companySettingsDTO.getcompany_TermCnditions().trim());
                stat.setString(12, companySettingsDTO.getcompany_SignAuthority().trim());
                System.out.println("BATABASE NUmber ----------" + companySettingsDTO.getCompany_DatabaseName().trim());
                System.out.println("VAT TIN NUmber ----------" + companySettingsDTO.getcompany_VatTinNo().trim());
                stat.setString(13, companySettingsDTO.getcompany_VatTinNo().trim());
                System.out.println("companySettingsDTO.getcompany_LBTNo()--->>>" + companySettingsDTO.getcompany_LBTNo());
                stat.setString(14, companySettingsDTO.getcompany_LBTNo());
                System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  " + companySettingsDTO.getCompany_id());
                System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  " + companySettingsDTO.getcompany_SaleTaxNo().trim());

                java.util.Date date = new java.util.Date();
                if (call_From) {
                    date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(companySettingsDTO.getcompany_Modified_date().trim());
                } else {
                    date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(companySettingsDTO.getcompany_Created_date().trim());
                }
//                java.util.Date date = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse("2013-1-1");
                java.sql.Date sqlStartDate = new Date(date.getTime());
                System.out.println("SQL DATE ------- UPDATE    " + sqlStartDate);
//                ps.setDate(2, sqlStartDate);
                java.util.Date date1 = gen.dto.Constants.simpleDateFormatDatabaseWithDash.parse(companySettingsDTO.getCompany_ApplicableFrom_date().trim());
                java.sql.Date Applicable_From_Date = new Date(date1.getTime());
                stat.setDate(15, sqlStartDate);
                stat.setDate(16, Applicable_From_Date);
                stat.setString(17, companySettingsDTO.getCompany_DatabaseName());
                stat.setString(18, companySettingsDTO.getCompany_Identification_No());
                stat.setLong(19, companySettingsDTO.getCompany_id());
                stat.setString(20, company_Cliked_Name);
                
                stat.addBatch();
            }
            stat.executeBatch();


            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            stat.close();
            // conn.commit();
            conn.close();

        } catch (ParseException ex) {
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Create New Databse for New Company
    public static void createNewDatabase(List<Company_InformationDTO> company_InformationDTO_Final_List) {
        try {

            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);



            for (Company_InformationDTO company_InformationDTO : company_InformationDTO_Final_List) {
                String sql = "CREATE DATABASE " + company_InformationDTO.getCompany_Database_Name();

                System.out.println("Create New DAtabase -----------------------       " + company_InformationDTO.getCompany_Database_Name());
                PreparedStatement prmt = conn.prepareStatement(sql);
                prmt.addBatch();
                prmt.executeBatch();
                prmt.close();
            }

            System.out.println(" SucessFully Database Created................. ");
            conn.commit();
            conn.close();
            gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = database_Preserve;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Drop Database for New Company Database
    public static void dropDatabases(List<Company_InformationDTO> company_InformationDTO_Final_List) {
        try {
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "1";
            database_Preserve = gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings;
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            System.out.println("Get company LIst Database ---------------       " + company_InformationDTO_Final_List.size());

            for (Company_InformationDTO company_InformationDTO : company_InformationDTO_Final_List) {
                String sql = "Drop DATABASE " + company_InformationDTO.getCompany_Database_Name();
                PreparedStatement prmt = conn.prepareStatement(sql);
                System.out.println("----------        " + company_InformationDTO.getCompany_Database_Name() + "--------- ");
                prmt.addBatch();
                prmt.executeBatch();
                prmt.close();
                System.out.println(" SucessFully Database Deleted................. " + company_InformationDTO.getCompany_Database_Name());

            }
            conn.commit();
            conn.close();
//	    gen.dto.Constants.COMPANYDETAILS_DATABASE_CALLING = "";
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Delete Records as per date
    public static void delete_Records(String databaseName, String close_Date) {
        try {
            System.out.println("Delete records ----------------------");
            gen.other.CompanySettings.CompanysSettings.CURRENT_DATABASE_Company_Settings = databaseName;
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String query = "";

            // set opening Balance of Ledger as per calculation of Date
            update_Ledger_OPBL(close_Date);

            // set opening Balance of Staockitem as per calculation of Date
            update_StockItem_OPBL(close_Date);

            // set transactions as per calculation of Date
            update_transactions(close_Date);

            query = "delete from  tblaccountvouchersmaxid";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.executeUpdate();


            query = "insert into tbladsumudisettings values('pagination','50')";
            prmt = conn.prepareStatement(query);
            prmt.executeUpdate();

            query = "insert into tblaccountvouchersmaxid(salemaxid, purchasemaxid, receiptmaxid, paymentmaxid, challanmaxid, contramaxid) values(0, 0, 0, 0, 0, 0)";
            prmt = conn.prepareStatement(query);
            prmt.executeUpdate();

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void update_Ledger_OPBL(String close_Date) {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String query = "";

            // Map for total credit and Ledger
            Map<String, Double> map_Credit = new HashMap<String, Double>();

            // Map for total debit and Ledger
            Map<String, Double> map_Debit = new HashMap<String, Double>();

            // List for Ledger_id and opening_Balance
            Set<String> a = new HashSet<String>();
            List<gen.account.ledger.LedgerDTO> ledgerDTOList = new ArrayList<gen.account.ledger.LedgerDTO>();
            ledgerDTOList = gen.account.ledger.LedgerDAO.getLedgerList(a, null);

            // calculate total_Credit and Total_debit
            query = "select sum(trans_amt) as totalDeb, trans_type,trans_ledgerId from tbltransactionledger where trans_id in (select trans_id from tbltransactionmain where trans_date < ?) group by trans_type, trans_ledgerId";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, close_Date);
            ResultSet rs = prmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("trans_type") == (gen.dto.Constants.CREDIT)) {
                    map_Credit.put(rs.getString("trans_ledgerId"), rs.getDouble("totalDeb"));
                } else {
                    map_Debit.put(rs.getString("trans_ledgerId"), rs.getDouble("totalDeb"));
                }
            }

            Double opening_Balance = 0D;
            int credit_Debit = 0;

            // LedgerList for Final - Updation
            List<gen.account.ledger.LedgerDTO> ledgerDTOList_For_Update = new ArrayList<gen.account.ledger.LedgerDTO>();

            for (gen.account.ledger.LedgerDTO ledgerdto : ledgerDTOList) {
                gen.account.ledger.LedgerDTO ledgerDTO = new gen.account.ledger.LedgerDTO();
                Double credit_Value = 0D;
                Double debit_Value = 0D;
                if (map_Debit.get(ledgerdto.getLedgerID()) == null) {
                    debit_Value = 0D;
                } else {
                    debit_Value = map_Debit.get(ledgerdto.getLedgerID());
                }

                if (map_Credit.get(ledgerdto.getLedgerID()) == null) {
                    credit_Value = 0D;
                } else {
                    credit_Value = map_Credit.get(ledgerdto.getLedgerID());
                }

                if (ledgerdto.getDebitOrCredit() == (gen.dto.Constants.DEBIT)) {
                    opening_Balance = ledgerdto.getLedger_OpeningBalence() + credit_Value - debit_Value;
                    if (opening_Balance < 0) {
                        credit_Debit = (gen.dto.Constants.DEBIT);
                    } else {
                        credit_Debit = (gen.dto.Constants.CREDIT);
                    }
                    ledgerDTO.setLedger_OpeningBalence(Math.abs(opening_Balance));
                    ledgerDTO.setDebitOrCredit(credit_Debit);
                } else {
                    opening_Balance = ledgerdto.getLedger_OpeningBalence() + debit_Value - credit_Value;
                    if (opening_Balance > 0) {
                        credit_Debit = (gen.dto.Constants.DEBIT);
                    } else {
                        credit_Debit = (gen.dto.Constants.CREDIT);
                    }
                    ledgerDTO.setLedger_OpeningBalence(Math.abs(opening_Balance));
                    ledgerDTO.setDebitOrCredit(credit_Debit);
                }

                ledgerDTO.setLedgerID(ledgerdto.getLedgerID());
                ledgerDTOList_For_Update.add(ledgerDTO);
            }

            // update Ledger VAlues in New Company with new opening Balance
            String query1 = "update tblledger set ledger_openingBalance = ? where ledger_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            String query2 = "update tblledger set  opening_type = ? where ledger_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            for (gen.account.ledger.LedgerDTO ledgerDTO : ledgerDTOList_For_Update) {

                ps1.setDouble(1, ledgerDTO.getLedger_OpeningBalence());
                ps1.setString(2, ledgerDTO.getLedgerID());
                ps1.addBatch();

                ps2.setDouble(1, ledgerDTO.getDebitOrCredit());
                ps2.setString(2, ledgerDTO.getLedgerID());
                ps2.addBatch();
            }
            ps1.executeBatch();
            ps2.executeBatch();

            ps1.close();
            ps2.close();
            conn.commit();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void update_StockItem_OPBL(String close_Date) {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            String query = "";

            // Map for total credit and Ledger
            Map<String, Double> map_Credit = new HashMap<String, Double>();

            // Map for total debit and Ledger
            Map<String, Double> map_Debit = new HashMap<String, Double>();

            // List for Ledger_id and opening_Balance
            Set<String> a = new HashSet<String>();
            List<gen.account.stockitem.StockItemDTO> stockItemDTOList = new ArrayList<gen.account.stockitem.StockItemDTO>();
            Set<String> pass_Set = new HashSet<String>();
            stockItemDTOList = gen.account.stockitem.StockItemDAO.getStockItemList(pass_Set, "");

            // calculaate total Debit
            query = "select sum(invtrans_qty) as totalDeb,invtrans_itemId from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < ? and trans_typeIndex in(?,?))) group by invtrans_itemId";
            PreparedStatement prmt = conn.prepareStatement(query);
            prmt.setString(1, close_Date);
            prmt.setLong(2, gen.dto.Constants.SALE_TYPE_INDEX);
            prmt.setLong(3, gen.dto.Constants.CHALAN_TYPE_INDEX);
            ResultSet rs = prmt.executeQuery();
            while (rs.next()) {
                map_Debit.put(rs.getString("invtrans_itemId"), rs.getDouble("totalDeb"));
            }

            // calculaate total Credit
            query = "select sum(invtrans_qty) as totalCred,invtrans_itemId from tblinventorytransactionitems where invtrans_id in(select invtrans_id from tblinventorytransaction where trans_id in (select trans_id from tbltransactionmain where trans_date < ? and trans_typeIndex in(?))) group by invtrans_itemId";
            PreparedStatement prmt1 = conn.prepareStatement(query);
            prmt1.setString(1, close_Date);
            prmt1.setLong(2, gen.dto.Constants.PURCHASE_TYPE_INDEX);
            ResultSet rs1 = prmt1.executeQuery();
            while (rs1.next()) {
                map_Credit.put(rs1.getString("invtrans_itemId"), rs1.getDouble("totalCred"));
            }

            Double opening_Balance = 0D;
            // LedgerList for Final - Updation
            List<gen.account.stockitem.StockItemDTO> update_StockItemDTOList = new ArrayList<gen.account.stockitem.StockItemDTO>();

            for (gen.account.stockitem.StockItemDTO stockItemDTO : stockItemDTOList) {
                gen.account.stockitem.StockItemDTO updateStockItemDTO = new gen.account.stockitem.StockItemDTO();

                Double credit_Value = 0D;
                Double debit_Value = 0D;
                if (map_Debit.get(stockItemDTO.getID() + "") == null) {
                    debit_Value = 0D;
                } else {
                    debit_Value = map_Debit.get(stockItemDTO.getID() + "");
                }

                if (map_Credit.get(stockItemDTO.getID() + "") == null) {
                    credit_Value = 0D;
                } else {
                    credit_Value = map_Credit.get(stockItemDTO.getID() + "");
                }

                opening_Balance = stockItemDTO.getOpeningBal() + credit_Value - debit_Value;

                if (opening_Balance > 0) {
                    updateStockItemDTO.setOpeningBal(opening_Balance);
                    updateStockItemDTO.setOpeningBal_type(gen.dto.Constants.CREDIT);
                } else {
                    updateStockItemDTO.setOpeningBal(opening_Balance);
                    updateStockItemDTO.setOpeningBal_type(gen.dto.Constants.DEBIT);
                }

                updateStockItemDTO.setOpeningBal(Math.abs(opening_Balance));
                updateStockItemDTO.setID(stockItemDTO.getID());
                update_StockItemDTOList.add(updateStockItemDTO);
            }

            // update Ledger VAlues in New Company
            String query1 = "update tblstockitem set si_openingBalance = ? where si_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            for (gen.account.stockitem.StockItemDTO stockItemDTO : update_StockItemDTOList) {

                ps1.setDouble(1, stockItemDTO.getOpeningBal());
                ps1.setLong(2, stockItemDTO.getID());
                ps1.addBatch();

            }
            ps1.executeBatch();

            ps1.close();
            conn.commit();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void update_transactions(String close_Date) {
        try {
            Connection conn = DatabaseConnection1.GetConnection();
            conn.setAutoCommit(false);

            List<String> trans_IDList = new ArrayList<String>();
            String query = "";

            // find all trans_id under trans_date
            query = "select trans_id from tbltransactionmain where trans_date < ?";
            PreparedStatement prmt1 = conn.prepareStatement(query);
            prmt1.setString(1, close_Date);
            ResultSet rs1 = prmt1.executeQuery();
            while (rs1.next()) {
                trans_IDList.add(rs1.getString("trans_ID"));
            }


            String pass_transId = "";
            for (String trans_id : trans_IDList) {
                pass_transId = trans_id + "," + pass_transId;
            }

            if (!pass_transId.isEmpty()) {
                pass_transId = pass_transId.substring(0, pass_transId.length() - 1);

                List<String> invtrans_IDList = new ArrayList<String>();

                query = "select invtrans_id from tblinventorytransaction where trans_id in (" + pass_transId + ")";
                PreparedStatement prmt2 = conn.prepareStatement(query);
                ResultSet rs2 = prmt2.executeQuery();
                while (rs2.next()) {
                    invtrans_IDList.add(rs2.getString("invtrans_id"));
                }

                // find invtransId for deletion tblinventorytransactionitems and tblinventorytransaction
                String pass_invtransId = "";
                for (String invtrans_id : invtrans_IDList) {
                    pass_invtransId = invtrans_id + "," + pass_invtransId;
                }

                PreparedStatement prmt = null;
                if (pass_invtransId.isEmpty()) {
                } else {
                    pass_invtransId = pass_invtransId.substring(0, pass_invtransId.length() - 1);

                    query = "delete from  tblinventorytransactionitems where invtrans_id in (" + pass_invtransId + ")";
                    prmt = conn.prepareStatement(query);
                    prmt.executeUpdate();

                    query = "delete from  tblinventorytransaction where invtrans_id in (" + pass_invtransId + ")";
                    prmt = conn.prepareStatement(query);
                    prmt.executeUpdate();
                }

                query = "delete from  tbltransactionotherdetails where trans_id in ( " + pass_transId + " )";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();

                query = "delete from  tbltransactionledger where trans_id in ( " + pass_transId + " )";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();

                query = "delete from  tblledgercreditlimit";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();

                query = "delete from  tblledgercurrentbalance";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();


                query = "delete from  tbltransactiontype";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();

                query = "delete from  tbltransactionvat";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();

                query = "delete from  tbltransactionmain where trans_id in ( " + pass_transId + " )";
                prmt = conn.prepareStatement(query);
                prmt.executeUpdate();
            }
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DatabaseSplitingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
