package com.youvegotnigel.automation.sample_tests;

import com.youvegotnigel.automation.utils.ExcelUtility;

public class ExcelTestDemo {

    public static void main(String[] args) {
        ExcelUtility eu = new ExcelUtility("ExcelTestData.xlsx", "Sheet2");
        eu.GetTotalRowCount();
        eu.GetTotalColumnCount();
        eu.getCellDataStringValue(1, 0);
        eu.getCellDataNumericValue(5, 1);
        eu.getCellDataDateValue(5, 0);
        eu.setCellDataStringValue("Test", 2, 2);
        eu.setCellDataNumericValue(1020, 2, 2);
        eu.CloseExcelFile();
    }
}
