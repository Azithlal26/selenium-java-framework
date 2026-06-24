package com.azith.framework.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

    public class ExcelUtility {

        public static Workbook getWorkbook(
                String filePath) throws Exception {

            FileInputStream fis =
                    new FileInputStream(filePath);

            return new XSSFWorkbook(fis);
        }

        public static Object[][] getTestData(
                String filePath,
                String sheetName)
                throws Exception {

            Workbook workbook =
                    getWorkbook(filePath);

            Sheet sheet =
                    workbook.getSheet(sheetName);

            if (sheet == null) {

                throw new RuntimeException(
                        "Sheet not found: " + sheetName);
            }

            int rows =
                    sheet.getPhysicalNumberOfRows();

            if (sheet.getPhysicalNumberOfRows() == 0) {

                throw new RuntimeException(
                        "Excel sheet is empty");
            }

            int cols =
                    sheet.getRow(0)
                            .getPhysicalNumberOfCells();

            Object[][] data =
                    new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {

                for (int j = 0; j < cols; j++) {

                    data[i - 1][j] =
                            sheet.getRow(i)
                                    .getCell(j)
                                    .toString();
                }
            }

            workbook.close();

            return data;
        }
    }
