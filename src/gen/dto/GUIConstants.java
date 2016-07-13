/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

import gen.accountvoucher.sale.SaleForm;

/**
 *
 * @author pc5
 */
public class GUIConstants {

    //y-Axis
    public static Integer YAxis_LEVEL_for_challan_in_Sale;
    public static Integer YAxis_LEVEL_1;
    public static Integer YAxis_LEVEL_1_1;
    public static Integer YAxis_LEVEL_1_2;
    public static Integer YAxis_LEVEL_2;
    public static Integer YAxis_LEVEL_2_1;
    public static Integer YAxis_LEVEL_2_2;
    public static Integer YAxis_LEVEL_2_3;
    public static Integer YAxis_LEVEL_3;
    public static Integer YAxis_LEVEL_3_1;
    public static Integer YAxis_LEVEL_3_2;
    public static Integer YAxis_LEVEL_4;
    public static Integer YAxis_LEVEL_4_1;
    public static Integer YAxis_LEVEL_4_2;
    public static Integer YAxis_LEVEL_5;
    public static Integer YAxis_LEVEL_5_1;
    public static Integer YAxis_LEVEL_6;
    public static Integer YAxis_LEVEL_6_1;
    public static Integer YAxis_LEVEL_7;
    public static Integer YAxis_LEVEL_7_1;
    public static Integer YAxis_LEVEL_8;
    public static Integer YAxis_LEVEL_8_1;
    public static Integer YAxis_LEVEL_9;
    public static Integer YAxis_LEVEL_9_1;
    public static Integer YAxis_LEVEL_10;
    public static Integer YAxis_LEVEL_10_1;
    public static Integer YAxis_LEVEL_11;
    public static Integer YAxis_LEVEL_11_1;
    public static Integer YAxis_LEVEL_12;
    public static Integer YAxis_LEVEL_12_1;
    public static Integer YAxis_LEVEL_13;
    public static Integer YAxis_LEVEL_13_1;
    public static Integer YAxis_LEVEL_14;
    public static Integer YAxis_LEVEL_15;
    //X-Axis
    public static Integer XAxis_LEVEL_1;
    public static Integer XAxis_LEVEL_1_1;
    public static Integer XAxis_LEVEL_2;
    public static Integer XAxis_LEVEL_2_1;
    public static Integer XAxis_LEVEL_3;
    public static Integer XAxis_LEVEL_3_1;
    public static Integer XAxis_LEVEL_4;
    public static Integer XAxis_LEVEL_4_1;
    public static Integer XAxis_LEVEL_5;
    public static Integer XAxis_LEVEL_5_1;
    public static Integer XAxis_LEVEL_6;
    public static Integer XAxis_LEVEL_6_1;
    public static Integer XAxis_LEVEL_7;
    public static Integer XAxis_LEVEL_7_1;
    public static Integer XAxis_LEVEL_8;
    public static Integer XAxis_LEVEL_8_1;
    public static Integer XAxis_LEVEL_9;
    public static Integer XAxis_LEVEL_9_1;
    public static Integer XAxis_LEVEL_10;
    public static Integer XAxis_LEVEL_10_1;
    public static Integer XAxis_LEVEL_11;
    public static Integer XAxis_LEVEL_11_1;
    public static Integer XAxis_LEVEL_12;
    public static Integer XAxis_LEVEL_12_1;
    public static Integer XAxis_LEVEL_13;
    public static Integer XAxis_LEVEL_13_1;
    public static Integer XAxis_LEVEL_14;
    public static Integer XAxis_LEVEL_14_1;
    public static Integer XAxis_LEVEL_15;
    public static Integer XAxis_LEVEL_15_1;
    public static Integer XAxis_LEVEL_16;
    public static Integer XAxis_LEVEL_16_1;
    public static Integer XAxis_LEVEL_17;
    public static Integer XAxis_LEVEL_18;
    public static Integer XAxis_LEVEL_18_1;
    public static Integer XAxis_LEVEL_19;
    public static Integer XAxis_LEVEL_20;
    public static Integer XAxis_LEVEL_21;
    public static Integer XAxis_LEVEL_22;
    public static Integer XAxis_LEVEL_23;
    public static Integer XAxis_LEVEL_24;
    public static Integer XAxis_LEVEL_25;
    //Text Field Width
    public static Integer jtextFieldSizeSmallWidth;
    public static Integer jtextFieldSizeMediumWidth;
    public static Integer jtextFieldSizeLargeWidth;
    public static Integer jtextFieldSizeExtraLargeWidth;
    public static Integer jtextFieldSizeMediumLargeWidth;
    //Button Width
    public static Integer buttonnSizeSmallWidth;
    public static Integer buttonnSizeMediumWidth;
    public static Integer buttonnSizeLargeWidth;
    public static Integer buttonnSizeExtraLargeWidth;
    //Label Width
    public static Integer labelSizeSmallWidth;
    public static Integer labelSizeMediumWidth;
    public static Integer labelSizeLargeWidth;
    public static Integer labelSizeExtraLargeWidth;
    //Combobox Width
    public static Integer comboBoxSizeSmallWidth;
    public static Integer comboBoxSizeMediumWidth;
    public static Integer comboBoxSizeLargeWidth;
    public static Integer comboBoxSizeExtraLargeWidth;
    //Table Width
    public static Integer tableSizeSmallWidth;
    public static Integer tableSizeMediumWidth;
    public static Integer tableSizeLargeWidth;
    public static Integer tableSizeExtraLargeWidth;
    //Text Field Height
    public static Integer jtextFieldSizeHeight;
    //Button Height
    public static Integer buttonnSizeHeight;
    //Label Height
    public static Integer labelSizeHeight;
    //Combobox height
    public static Integer comboBoxSizeHeight;
    //Table Height
    public static Integer tableSizeHeight;
    public static Integer plywoodTableSizeHeight;

    public static void init(int screenWidth, int screenHeight) {
        YAxis_LEVEL_for_challan_in_Sale = (screenHeight / 30) * 2;
        YAxis_LEVEL_1 = (screenHeight / 60) * 2;
        YAxis_LEVEL_1_1 = (screenHeight / 60) * 4;
        YAxis_LEVEL_1_2 = (screenHeight / 60) * 5;
        YAxis_LEVEL_2 = (screenHeight / 20) * 2;
        YAxis_LEVEL_2_1 = (screenHeight / 20) * 3;
        YAxis_LEVEL_2_2 = (screenHeight / 22) * 4 - 5;
        YAxis_LEVEL_2_3 = (screenHeight / 20) * 3;
        YAxis_LEVEL_3 = (screenHeight / 15) * 3 + 15;
        YAxis_LEVEL_3_1 = (screenHeight / 15) * 3;
        YAxis_LEVEL_3_2 = (screenHeight / 20) * 4;
        YAxis_LEVEL_4 = (screenHeight / 8) * 2;
        YAxis_LEVEL_4_1 = (screenHeight / 10) * 3;
        YAxis_LEVEL_4_2 = (screenHeight / 10) * 4 - 30;
        YAxis_LEVEL_5 = (screenHeight / 5) * 2;
        YAxis_LEVEL_5_1 = (screenHeight / 7) * 3;
        YAxis_LEVEL_6 = (screenHeight / 5) * 2 + 25;
        YAxis_LEVEL_7 = (screenHeight / 5) * 2 + 50;
        YAxis_LEVEL_8 = (screenHeight / 5) * 2 + 75;
        YAxis_LEVEL_9 = (screenHeight / 5) * 2 + 105;
        YAxis_LEVEL_10 = (screenHeight / 5) * 3 - 20;

        YAxis_LEVEL_6_1 = (screenHeight / 5) * 3 - 50;
        YAxis_LEVEL_7_1 = (screenHeight / 5) * 3 - 25;
        YAxis_LEVEL_8_1 = (screenHeight / 5) * 3 + 3;
        YAxis_LEVEL_9_1 = (screenHeight / 4) * 3 - 85;
        YAxis_LEVEL_10_1 = (screenHeight / 5) * 3 + 50;
        YAxis_LEVEL_11 = (screenHeight / 4) * 3 - 20;
        YAxis_LEVEL_11_1 = (screenHeight / 4) * 3 - 20;
        YAxis_LEVEL_12 = (screenHeight / 4) * 3 + 25;
        YAxis_LEVEL_12_1 = (screenHeight / 4) * 3 + 25;


        XAxis_LEVEL_1 = (screenWidth / 225) * 10;
        XAxis_LEVEL_1_1 = XAxis_LEVEL_1 / 2;
        XAxis_LEVEL_2 = XAxis_LEVEL_1 * 2;
        XAxis_LEVEL_2_1 = XAxis_LEVEL_2 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_3 = XAxis_LEVEL_1 * 3;
        XAxis_LEVEL_3_1 = XAxis_LEVEL_3 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_4 = XAxis_LEVEL_1 * 4;
        XAxis_LEVEL_4_1 = XAxis_LEVEL_4 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_5 = XAxis_LEVEL_1 * 5;
        XAxis_LEVEL_5_1 = XAxis_LEVEL_5 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_6 = XAxis_LEVEL_1 * 6;
        XAxis_LEVEL_6_1 = XAxis_LEVEL_6 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_7 = XAxis_LEVEL_1 * 7;
        XAxis_LEVEL_7_1 = XAxis_LEVEL_7 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_8 = XAxis_LEVEL_1 * 8;
        XAxis_LEVEL_8_1 = XAxis_LEVEL_8 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_9 = XAxis_LEVEL_1 * 9;
        XAxis_LEVEL_9_1 = XAxis_LEVEL_9 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_10 = XAxis_LEVEL_1 * 10;
        XAxis_LEVEL_10_1 = XAxis_LEVEL_10 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_11 = XAxis_LEVEL_1 * 11;
        XAxis_LEVEL_11_1 = XAxis_LEVEL_11 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_12 = XAxis_LEVEL_1 * 12;
        XAxis_LEVEL_12_1 = XAxis_LEVEL_12 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_13 = XAxis_LEVEL_1 * 13;
        XAxis_LEVEL_13_1 = XAxis_LEVEL_13 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_14 = XAxis_LEVEL_1 * 14;
        XAxis_LEVEL_14_1 = XAxis_LEVEL_14 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_15 = XAxis_LEVEL_1 * 15;
        XAxis_LEVEL_15_1 = XAxis_LEVEL_15 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_16 = XAxis_LEVEL_1 * 16;
        XAxis_LEVEL_16_1 = XAxis_LEVEL_16 + XAxis_LEVEL_1_1;
        XAxis_LEVEL_17 = XAxis_LEVEL_1 * 17;
        XAxis_LEVEL_18 = XAxis_LEVEL_1 * 18;
        XAxis_LEVEL_18_1 = (XAxis_LEVEL_1 * 18) + 40;
        XAxis_LEVEL_19 = XAxis_LEVEL_1 * 19;
        XAxis_LEVEL_20 = XAxis_LEVEL_1 * 20;
        XAxis_LEVEL_21 = XAxis_LEVEL_1 * 21;
        XAxis_LEVEL_22 = XAxis_LEVEL_1 * 22;
        XAxis_LEVEL_23 = XAxis_LEVEL_1 * 23;
        XAxis_LEVEL_24 = XAxis_LEVEL_1 * 24;
        XAxis_LEVEL_25 = XAxis_LEVEL_1 * 25;

        jtextFieldSizeHeight = screenHeight / 35;
        labelSizeHeight = screenHeight / 30;

        comboBoxSizeMediumWidth = XAxis_LEVEL_3_1 - XAxis_LEVEL_1 - 1;
        comboBoxSizeLargeWidth = XAxis_LEVEL_4_1 - XAxis_LEVEL_1 - 1;

        jtextFieldSizeMediumWidth = XAxis_LEVEL_2_1 - XAxis_LEVEL_1 - 1;
        jtextFieldSizeSmallWidth = jtextFieldSizeMediumWidth / 2;
        jtextFieldSizeLargeWidth = XAxis_LEVEL_4_1 - XAxis_LEVEL_1 - 1;
        jtextFieldSizeExtraLargeWidth = XAxis_LEVEL_2 - XAxis_LEVEL_1 - 1;
        jtextFieldSizeMediumLargeWidth = XAxis_LEVEL_4 - XAxis_LEVEL_1 - 1;

        labelSizeMediumWidth = XAxis_LEVEL_2_1 - XAxis_LEVEL_1 - 1;
        labelSizeSmallWidth = XAxis_LEVEL_3 - XAxis_LEVEL_1 - 1;
        labelSizeLargeWidth = XAxis_LEVEL_4_1 - XAxis_LEVEL_1 - 1;
        labelSizeExtraLargeWidth = XAxis_LEVEL_2 - XAxis_LEVEL_1 - 1;
        labelSizeSmallWidth = screenWidth / 15;

        buttonnSizeSmallWidth = (XAxis_LEVEL_2_1 - XAxis_LEVEL_1) * 50 / 100;
        buttonnSizeMediumWidth = (XAxis_LEVEL_2_1 - XAxis_LEVEL_1) * 85 / 100;
        buttonnSizeLargeWidth = (XAxis_LEVEL_2_1 - XAxis_LEVEL_1) * 110 / 100;
        buttonnSizeExtraLargeWidth = (XAxis_LEVEL_2_1 - XAxis_LEVEL_1) * 150 / 100;

        buttonnSizeHeight = jtextFieldSizeHeight;

        tableSizeLargeWidth = (screenWidth / 5) * 3;
        tableSizeHeight = screenHeight / 9;

        plywoodTableSizeHeight = screenHeight / 3 - 14;
    }
}
