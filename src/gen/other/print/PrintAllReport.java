/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.other.print;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author Kasturi NovaSoft
 */
public class PrintAllReport {

    /*
     * Fucntion to print sale report
     */
    public static void printSaleReport(String id) {
        // TODO add your handling code here:

        try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/sales/A4_sale_subreport0.jasper");
                PrintFile.printReport("reports/sales/A4_sale.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/sales/A5_sale_subreport0.jasper");
                PrintFile.printReport("reports/sales/A5_sale.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printSalesReport(String id, Map parameter) {
        // TODO add your handling code here:

        try {
            // Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                //parameter.put("subreport1", "reports/salea/A4_sale_subreport0_1.jasper");
                PrintFile.printReport("reports/Salea/A4SaleEstimate.jasper", parameter);
            } else if (PrinterSettings.flagPrintPageSize == 2) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                //parameter.put("subreport1", "reports/salea/A4_sale_subreport0_1.jasper");
                PrintFile.printReport("reports/Salea/A5SaleEstimate.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                //   parameter.put("subreport1", "reports/sales/A5_sale_subreport0.jasper");
                PrintFile.printReport("reports/Salea/A6SaleEstimate.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printSalesReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            // Map<String, String> parameter = new HashMap<String, String>();
            //     parameter.put("trans_id", id);
            if (PrinterSettings.flagPrintPageSize == 1) {
                //PrintTableFile.printReport("reports/Salea/A4SaleEstimate.jasper", parameter, jrtable);
//                PrintTableFile.printReport("reports/Salea/reports with plymate,woodstuff/Ply_Sales_A4_Sale_1withoutSubreport-OLD2New.jasper.jasper", parameter, jrtable);
                PrintTableFile.printReport("reports/Salea/reports with plymate,woodstuff/A4SaleTaxInvoiceCopy.jasper", parameter, jrtable);
            } else if (PrinterSettings.flagPrintPageSize == 2) {
                //PrintTableFile.printReport("reports/Salea/A5SaleEstimate.jasper", parameter, jrtable);
                PrintTableFile.printReport("reports/Salea/reports with plymate,woodstuff/A4SaleTaxInvoiceCopy.jasper", parameter, jrtable);
            } else {
                //PrintTableFile.printReport("reports/Salea/A6SaleEstimate.jasper", parameter, jrtable);
                PrintTableFile.printReport("reports/Salea/reports with plymate,woodstuff/Ply_Sales_A5_Sale_1withoutSubreport-OLD2New.jasper.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printStockitemReport(Map parameter, JRTableModelDataSource jrtable, int rowCount) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);


                PrintTableFile.printReport("reports/Stockitem/A4_Stockitem.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/Stockitem/A5_Stockitem.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printPaymentReport(String id, Map parameter) {
        // TODO add your handling code here:

        try {
            //Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);


            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/payment/A4_payment_subreport0.jasper");
                PrintFile.printReport("reports/payment/A4_payment.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/payment/A5_payment_subreport0.jasper");
                PrintFile.printReport("reports/payment/A5_payment.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printReceiptReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            // Map<String, String> parameter = new HashMap<String, String>();


            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                // parameter.put("subreport1", "reports/receipt/A4_receipt_subreport0.jasper");
                PrintTableFile.printReport("reports/receipt/A4_receipt1.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                // parameter.put("subreport1", "reports/receipt/A5_receipt_subreport0.jasper");
                PrintTableFile.printReport("reports/receipt/A5_receipt.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printLedgerReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/ledger/A4_Ledger_balance.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/ledger/A5_Ledger_balance.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printtrialReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/trial/A4_Trial.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/trial/A5_Trial.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printgroupReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/group/A4_group.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/group/A5_group.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printNewGroupSummaryReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/newGroupSummary/A4_NewGroupSummary.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/newGroupSummary/A5_NewGroupSummary.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void printPurchaseReport(String id) {
//        // TODO add your handling code here:
//
//        try {
//            Map<String, String> parameter = new HashMap<String, String>();
//            parameter.put("trans_id", id);
//            if (PrinterSettings.flagPrintPageSize == 1) {
//                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
//                parameter.put("subreport1", "reports/purchase/A4_purchase_subreport0.jasper");
//                PrintFile.printReport("reports/purchase/A4_purchase.jasper", parameter);
//            } else {
//                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
//                parameter.put("subreport1", "reports/purchase/A5_purchase_subreport0.jasper");
//                PrintFile.printReport("reports/purchase/A5_purchase.jasper", parameter);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    public static void printPurchaseReport(String id, Map parameter) {
        // TODO add your handling code here:

        try {
            //parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/purchase/A4_Purchase_subreport0.jasper");
                PrintFile.printReport("reports/purchase/A4_Purchase.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/purchase/A5_Purchase_subreport0.jasper");
                PrintFile.printReport("reports/purchase/A5_Purchase.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printPurchaseReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            //parameter = new HashMap<String, String>();
            // parameter.put("trans_id", id);
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/purchase/A4_Purchase_subreport0.jasper");
                PrintTableFile.printReport("reports/purchase/A4_Purchase.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/purchase/A5_Purchase_subreport0.jasper");
                PrintTableFile.printReport("reports/purchase/A5_Purchase.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
     * Fucntion to print payment report
     */
    public static void printPaymentReport(String id) {
        // TODO add your handling code here:

        try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);


            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/payment/A4_payment_subreport0.jasper");
                PrintFile.printReport("reports/payment/A4_payment.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/payment/A5_payment_subreport0.jasper");
                PrintFile.printReport("reports/payment/A5_payment.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /*
     * Fucntion to print receipt report
     */
    public static void printReceiptReport(String id) {
        // TODO add your handling code here:

        try {
            Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);


            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/receipt/A4_receipt_subreport0.jasper");
                PrintFile.printReport("reports/receipt/A4_receipt.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                parameter.put("subreport1", "reports/receipt/A5_receipt_subreport0.jasper");
                PrintFile.printReport("reports/receipt/A5_receipt.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printDayBook(String dateNow, Map parameter) {
        try {
            //  Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("Transaction_type", "1");

            parameter.put("trans_date", dateNow);


            if (PrinterSettings.flagPrintPageSize == 1) {
                parameter.put("subreport1", "reports/DayBook/daybookjaspertablesubreport.jasper");
                parameter.put("subreport2", "reports/DayBook/daybookjaspertablesubreportcredit.jasper");
                PrintFile.printReport("reports/DayBook/A4_DAY_Report.jasper", parameter);
            } else {
//                parameter.put("subreport1", "reports/DayBook/A5_daybookjaspertablesubreportcredit.jasper");
//                parameter.put("subreport2", "reports/DayBook/A5_daybookjaspertablesubreport.jasper");
//                PrintFile.printReport("reports/DayBook/A5_daybookjaspertable.jasper", parameter);

                parameter.put("subreport1", "reports/DayBook/daybookjaspertablesubreport.jasper");
                parameter.put("subreport2", "reports/DayBook/daybookjaspertablesubreportcredit.jasper");
                PrintFile.printReport("reports/DayBook/A4_DAY_Report.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//     public static void printDayBook(String dateNow,Map parameter) {
//        try {    
//          //  Map<String, String> parameter = new HashMap<String, String>();
//          //  parameter.put("Transaction_type", "1");
//
//            parameter.put("trans_date", dateNow);
//
//
//            if (PrinterSettings.flagPrintPageSize == 1) {
//                parameter.put("subreport1", "reports/DayBook/daybookjaspertablesubreport.jasper");
//                parameter.put("subreport2", "reports/DayBook/daybookjaspertablesubreportcredit.jasper");
//                PrintFile.printReport("reports/DayBook/A4_DAY_Report.jasper", parameter);
//            } else {
//                parameter.put("subreport1", "reports/DayBook/A5_daybookjaspertablesubreportcredit.jasper");
//                parameter.put("subreport2", "reports/DayBook/A5_daybookjaspertablesubreport.jasper");
//                PrintFile.printReport("reports/DayBook/A5_daybookjaspertable.jasper", parameter);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
////    
    public static void printsalepurchaseDayBook(String dateNow, Map parameter) {
        try {
            //  Map<String, String> parameter = new HashMap<String, String>();
            // parameter.put("Transaction_type", "1");

            parameter.put("trans_date", dateNow);


            if (PrinterSettings.flagPrintPageSize == 1) {
                parameter.put("subreport1", "reports/SaleDayBook/salepurchasedaybookjaspertablesubreportcredit.jasper");
                parameter.put("subreport2", "reports/SaleDayBook/salepurchasedaybookjaspertablesubreport.jasper");
                PrintFile.printReport("reports/SaleDayBook/A4_DAY_Report.jasper", parameter);
            } else {
                parameter.put("subreport1", "reports/SaleDayBook/A5_DAY_Report_subreport0.jasper");
                parameter.put("subreport2", "reports/SaleDayBook/A5_DAY_Report_subreport1.jasper");
                PrintFile.printReport("reports/SaleDayBook/A5_Day_report.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printReceiptPaymentDayBook(String dateNow, Map parameter) {
        try {
            //  Map<String, String> parameter = new HashMap<String, String>();
            // parameter.put("Transaction_type", "1");

            parameter.put("trans_date", dateNow);


            if (PrinterSettings.flagPrintPageSize == 1) {
                parameter.put("subreport1", "reports/ReceiptDayBook/receiptpaymentdaybookjaspertable.jasper");
                parameter.put("subreport2", "reports/ReceiptDayBook/receiptpaymentdaybookjaspertablesubreport.jasper");
                PrintFile.printReport("reports/ReceiptDayBook/receiptpaymentdaybookjaspertablesubreportcredit.jasper    ", parameter);
            } else {
                parameter.put("subreport1", "reports/DayBook/A5_DAY_Report_subreport0.jasper");
                parameter.put("subreport2", "reports/DayBook/A5_DAY_Report_subreport1.jasper");
                PrintFile.printReport("reports/DayBook/A5_Day_report.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printSAlePrTableDaybookDemo(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/DaybookTableDemo/SAlePurchaseCombineTableDemo.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/DaybookTableDemo/A5_SAlePurchaseCombineTableDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printReceiptPAyTableDaybookDemo(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/DaybookTableDemo/ReceiptPayCombineTAbleDemo.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/DaybookTableDemo/A5_ReceiptPayCombineTAbleDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printTableDaybookDemo(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:
        System.out.println("PrintAllReport---------------->>printTableDaybookDemo------->>printer variables: Direct print:" + PrinterSettings.flagDirectPrint + " Page Format:" + PrinterSettings.flagPrintPageSize + " Page Size:" + PrinterSettings.flagPrintPageSize);
        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/DaybookTableDemo/MainDaybookCombineTableDemo.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/DaybookTableDemo/A5_MainDaybookCombineTableDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printDAybookchalanReport(JRTableModelDataSource jrtable, Map parameter) {
        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                PrintTableFile.printReport("reports/DaybookTableDemo/A4_ChallanCombineTAbleDemo.jasper", parameter, jrtable);
            } else {
                PrintTableFile.printReport("reports/DaybookTableDemo/A5_ChallanCombineTAbleDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printDSaleReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/SaleReport/SAleReportTableDemo.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/SaleReport/A5_SAleReportTableDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printPSaleReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/Purchase Report/PurchaseReportTableDemo.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/Purchase Report/A5_PurchaseReportTableDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printTableCashDaybookDemo(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:
        System.out.println("PrintAllReport---------------->>printTableDaybookDemo------->>printer variables: Direct print:" + PrinterSettings.flagDirectPrint + " Page Format:" + PrinterSettings.flagPrintPageSize + " Page Size:" + PrinterSettings.flagPrintPageSize);
        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/DaybookTableDemo/MainCashDaybookCombineTableDemo.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/DaybookTableDemo/A5_MainCashDaybookCombineTableDemo.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //public static void printChalanReport(String id, Map parameter) {
    public static void printChalanReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            //Map<String, String> parameter = new HashMap<String, String>();
//            parameter.put("trans_id", id);


            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                // parameter.put("subreport1", "reports/payment/A4_payment_subreport0.jasper");
                System.out.println("AAAAAA$$$$$$$       44444444444444444444 ");
//                PrintFile.printReport("reports/Chalan/Withlogowoodstuff,plymate/Ply_SaleswithChallan_A4_Sale_1withoutSubreport.jasper", parameter);
                PrintTableFile.printReport("reports/chalan/Withlogowoodstuff,plymate/classicA4.jasper", parameter, jrtable);
//		 PrintFile.printReport("reports/Chalan/Withlogowoodstuff,plymate/classicA4.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                // parameter.put("subreport1", "reports/payment/A5_payment_subreport0.jasper");
                System.out.println("AAAAAA$$$$$$$       555555555555555555555 ");
//                PrintFile.printReport("reports/Chalan/Withlogowoodstuff,plymate/Ply_SaleswithChallan_A5_Sale_1withoutSubreport.jasper", parameter);
                PrintTableFile.printReport("reports/chalan/Withlogowoodstuff,plymate/classicA5.jasper", parameter, jrtable);
//		PrintFile.printReport("reports/Chalan/Withlogowoodstuff,plymate/classicA5.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printChalanEstimateReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            //Map<String, String> parameter = new HashMap<String, String>();

            if (PrinterSettings.flagPrintPageSize == 1) {
                PrintTableFile.printReport("reports/chalan/L_A6SaleEstimate.jasper", parameter, jrtable);
            } else {
                PrintTableFile.printReport("reports/chalan/L_A6SaleEstimate.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void printChalanReport(String id, Map parameter) {
//        // TODO add your handling code here:
//
//        try {
//            //Map<String, String> parameter = new HashMap<String, String>();
//            parameter.put("trans_id", id);
//
//
//            if (PrinterSettings.flagPrintPageSize == 1) {
//                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
//                // parameter.put("subreport1", "reports/payment/A4_payment_subreport0.jasper");
//                PrintFile.printReport("reports/Chalan/Withlogowoodstuff,plymate/Ply_SaleswithChallan_A4_Sale_1withoutSubreport.jasper", parameter);
//            } else {
//                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
//                // parameter.put("subreport1", "reports/payment/A5_payment_subreport0.jasper");
//                PrintFile.printReport("reports/Chalan/Withlogowoodstuff,plymate/Ply_SaleswithChallan_A5_Sale_1withoutSubreport.jasper", parameter);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    public static void printChalanEstimateReport(String id, Map parameter) {
        // TODO add your handling code here:

        try {
            //Map<String, String> parameter = new HashMap<String, String>();
            parameter.put("trans_id", id);


            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                // parameter.put("subreport1", "reports/payment/A4_payment_subreport0.jasper");
                PrintFile.printReport("reports/Chalan/L_A6SaleEstimate.jasper", parameter);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                // parameter.put("subreport1", "reports/payment/A5_payment_subreport0.jasper");
                PrintFile.printReport("reports/Chalan/L_A6SaleEstimate.jasper", parameter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printledgerbalanceReport(Map parameter, JRTableModelDataSource jrtable) {
        // TODO add your handling code here:

        try {
            if (PrinterSettings.flagPrintPageSize == 1) {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);

                PrintTableFile.printReport("reports/LedgerBalanceReports/A4_ledgerwithopenning.jasper", parameter, jrtable);
            } else {
                // PrintFile.printReport("reports/acct/A4_sale.jasper",parameter);
                PrintTableFile.printReport("reports/LedgerBalanceReports/A5_ledgerwithopenning.jasper", parameter, jrtable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}