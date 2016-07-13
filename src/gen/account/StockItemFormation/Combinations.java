package gen.account.StockItemFormation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {

//    public static void main(String[] args) {
    public static List<List<String>> generateAllPossibleCombinations(List<List<String>> listOfAllSelections) throws Exception {
        List<List<String>> listOfAllStockItems = new ArrayList<List<String>>();
        try {
//
//        ArrayList<String> category = new ArrayList<String>();
//        category.add("MDF");
//        category.add("Bagasse");
//        lists.add(category);
//
//        ArrayList<String> length = new ArrayList<String>();
//        length.add("3.5");
//        length.add("7.5");
//        lists.add(length);
//
//        ArrayList<String> width = new ArrayList<String>();
//        width.add("2");
//        lists.add(width);
//
//        ArrayList<String> color = new ArrayList<String>();
//        color.add("Blue");
//        color.add("Green");
//        lists.add(color);
//
//        ArrayList<String> thkns = new ArrayList<String>();
//        thkns.add("3.5");
//        lists.add(thkns);
//
//        ArrayList<String> ftype = new ArrayList<String>();
//        ftype.add("Rose Wood");
//        ftype.add("Hard Wood");
//        lists.add(ftype);

            MixedRangeCombinationIterable<String> iterable = new MixedRangeCombinationIterable<String>(listOfAllSelections);
            int count = 0;
            for (List<String> element : iterable) {
                System.out.println("" + element);
                listOfAllStockItems.add(element);
                count++;
            }
            System.out.println("Total Stock Items--->>>" + count);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return listOfAllStockItems;
    }
//    }
}
