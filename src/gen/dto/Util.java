/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

import gen.dto.StockItemTransactionDTO;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

/**
 *
 * @author pc5
 */
public class Util {

    public static Boolean isEmpty(List<StockItemTransactionDTO> objectList) {
	System.out.println("Util--------->>isEmpty------->> " + objectList.size());
	if (objectList != null && objectList.size() > 0) {
	    return false;
	}
	return true;
    }

    public static Boolean isEmpty(Set<String> stringMap) {
	System.out.println("Util--------->>isEmpty------->> " + stringMap.size());
	if (stringMap != null && stringMap.size() > 0) {
	    return false;
	}
	return true;
    }

    public static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
	DefaultComboBoxModel defaultcomboBoxModel = new DefaultComboBoxModel();
	for (String string : list) {
	    if (string.toLowerCase().matches("(.*)" + text.toLowerCase() + "(.*)")) {
		defaultcomboBoxModel.addElement(string);
	    }
	}
	return defaultcomboBoxModel;
    }

    public static void filterCharacter(java.awt.event.KeyEvent evt, javax.swing.JFormattedTextField jtxtField) {
	char c = evt.getKeyChar();
	if (!Character.isDigit(c) && c != '.') {
	    evt.consume();
	}
	int i;

	if (c == '.') {
	    int flg = 0;
	    i = 0;
	    while (i < jtxtField.getText().trim().length()) {
		if (jtxtField.getText().trim().charAt(i) == '.') {
		    flg = 1;
		    break;
		}
		i++;
	    }

	    if (flg == 1) {
		evt.consume();
	    }
	}
    }

    public static void filterCharacter(java.awt.event.KeyEvent evt, javax.swing.JTextField jtxtField) throws Exception {
	char c = evt.getKeyChar();
	if (!Character.isDigit(c) && c != '.') {
	    evt.consume();
	}
	int i;

	if (c == '.') {
	    int flg = 0;
	    i = 0;
	    while (i < jtxtField.getText().trim().length()) {
		if (jtxtField.getText().trim().charAt(i) == '.') {
		    flg = 1;
		    break;
		}
		i++;
	    }

	    if (flg == 1) {
		evt.consume();
	    }
	}
    }
    
    public static void filterToNumbers(java.awt.event.KeyEvent evt, javax.swing.JTextField jtxtField) throws Exception {
	char c = evt.getKeyChar();
	if (!Character.isDigit(c)) {
	    evt.consume();
	}
//	int i;

//	if (c == '.') {
//	    int flg = 0;
//	    i = 0;
//	    while (i < jtxtField.getText().trim().length()) {
//		if (jtxtField.getText().trim().charAt(i) == '.') {
//		    flg = 1;
//		    break;
//		}
//		i++;
//	    }
//
//	    if (flg == 1) {
//		evt.consume();
//	    }
//	}
    }

    public static void filterForDigitAndDash(java.awt.event.KeyEvent evt, javax.swing.JTextField jtxtField) {
	char c = evt.getKeyChar();
	if (!Character.isDigit(c) && c != '-') {
	    evt.consume();
	}
	int i;

	if (c == '.') {
	    int flg = 0;
	    i = 0;
	    while (i < jtxtField.getText().trim().length()) {
		if (jtxtField.getText().trim().charAt(i) == '.') {
		    flg = 1;
		    break;
		}
		i++;
	    }

	    if (flg == 1) {
		evt.consume();
	    }
	}
    }

    public static void filterForContactNo(java.awt.event.KeyEvent evt, javax.swing.JTextField jtxtField) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '-' && c!= '+' && c!=' ') {
            evt.consume();
        }
        int i;

        if (c == '.') {
            int flg = 0;
            i = 0;
            while (i < jtxtField.getText().trim().length()) {
                if (jtxtField.getText().trim().charAt(i) == '.') {
                    flg = 1;
                    break;
                }
                i++;
            }

            if (flg == 1) {
                evt.consume();
            }
        }
    }
    String string;
    String st1[] = {"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
	"EIGHT", "NINE",};
    String st2[] = {"HUNDRED", "THOUSAND", "LAKH", "CRORE"};
    String st3[] = {"TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN",
	"FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINTEEN",};
    String st4[] = {"TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY", "SEVENTY",
	"EIGHTY", "NINTY"};

    public String convert(Long number) {
	int n = 1;
	Long word;
	string = "";
	while (number != 0) {
	    switch (n) {
		case 1:
		    word = number % 100;
		    pass(Integer.parseInt(word.toString()));
		    if (number > 100 && number % 100 != 0) {
			show(" ");
		    }
		    number /= 100;
		    break;

		case 2:
		    word = number % 10;
		    if (word != 0) {
			show(" ");
			show(st2[0]);
			show(" ");
			pass(Integer.parseInt(word.toString()));
		    }
		    number /= 10;
		    break;

		case 3:
		    word = number % 100;
		    if (word != 0) {
			show(" ");
			show(st2[1]);
			show(" ");
			pass(Integer.parseInt(word.toString()));
		    }
		    number /= 100;
		    break;

		case 4:
		    word = number % 100;
		    if (word != 0) {
			show(" ");
			show(st2[2]);
			show(" ");
			pass(Integer.parseInt(word.toString()));
		    }
		    number /= 100;
		    break;

		case 5:
		    word = number % 100;
		    if (word != 0) {
			show(" ");
			show(st2[3]);
			show(" ");
			pass(Integer.parseInt(word.toString()));
		    }
		    number /= 100;
		    break;

	    }
	    n++;
	}
	return string;
    }

    public void pass(int number) {
	int word, q;
	if (number < 10) {
	    show(st1[number]);
	}
	if (number > 9 && number < 20) {
	    show(st3[number - 10]);
	}
	if (number > 19) {
	    word = number % 10;
	    if (word == 0) {
		q = number / 10;
		show(st4[q - 2]);
	    } else {
		q = number / 10;
		show(st1[word]);
		show(" ");
		show(st4[q - 2]);
	    }
	}
    }

    public void show(String s) {
	String st;
	st = string;
	string = s;
	string += st;
    }

    public static Map<String, String> getSmallCaseMap(Map<String, String> paramMap) {
	Map<String, String> mapToReturn = new HashMap<String, String>();
	for (String key : paramMap.keySet()) {
	    mapToReturn.put(key.toLowerCase(), paramMap.get(key));
	}
	return mapToReturn;
    }

    public static Boolean checkForZero(JTextField jtextField) throws Exception {
	if (jtextField != null && !jtextField.getText().trim().equalsIgnoreCase("")) {
	    if (Double.parseDouble(jtextField.getText().trim()) == 0D) {
		jtextField.setText("");
	    }
	}
	return false;
    }

    public static Boolean checkForEmpty(JTextField jtextField) {
	if (jtextField != null) {
	    if (jtextField.getText().trim().equalsIgnoreCase("")) {
		jtextField.setText("0.0");
	    }
	    if (jtextField.getText().trim().equalsIgnoreCase(".")) {
		jtextField.setText("0.0");
	    }
	}
	return false;
    }

    public static String getImageIconPath() {
	String path = "/images/Kasturi-logo-1.png";
	return path;
    }

    private void clearFormData(JTextField jtextField) {

	jtextField.setText("");

    }

    public static String formatDatePickerText(String dateStr) {
	System.out.println("in the formatDatePickerText===========================>>");
	String returnString = "";
	if (dateStr != null) {
	    String dateStringList[] = dateStr.split("-");
	    if (dateStringList.length == 1) {
		try {
		    Integer day = Integer.parseInt(dateStringList[0]);
		    if (day > 31) {
			day = 31;
		    }
		    returnString += day;
		    if (dateStringList[0].length() > 1) {
			returnString += "-";
		    }
		} catch (Exception e) {
		}
	    }
	    if (dateStringList.length == 2) {
		try {
		    Integer month = Integer.parseInt(dateStringList[1]);
		    if (month > 12) {
			month = 12;
		    }
		    returnString += dateStringList[0];
		    returnString += "-";
		    returnString += month;
		    if (dateStringList[0].length() > 1) {
			returnString += "-";
		    }

		} catch (Exception e) {
		}
	    }
	    if (dateStringList.length == 3) {
		try {
		    Integer year = Integer.parseInt(dateStringList[2]);
		    if (year > 10000) {
			year = 9999;
		    }
		    returnString += dateStringList[0];
		    returnString += "-";
		    returnString += dateStringList[1];
		    returnString += "-";
		    returnString += year;
		} catch (Exception e) {
		}
	    }
	}
	return returnString;
    }

    // for tally import from AdSuMuDi export for getting date with month
    public static String getDate(String date) throws Exception {
        String transactin_date = "";
        try {

            String stringDateFormat1 = date;
            System.out.println("se ------------ " + date);
            DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedDate1 = (Date) formatter1.parse(stringDateFormat1);
            System.out.println(convertedDate1);
            System.out.println("New date is : " + new SimpleDateFormat("dd-MMM-yyyy").format(convertedDate1));
            transactin_date = new SimpleDateFormat("dd-MMM-yyyy").format(convertedDate1);

        } catch (Exception ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw ex;
        }
        return transactin_date;
    }

    // for tally import from AdSuMuDi export for getting time
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String current_time = sdf.format(cal.getTime());
        System.out.println("String -------- " + current_time);
        System.out.println(sdf.format(cal.getTime()));
        return current_time;
    }
}
