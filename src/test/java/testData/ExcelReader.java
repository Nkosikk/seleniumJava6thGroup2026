package testData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcelReader {

    public static Object[][] getLoginDataFromExcel(String filePath, String sheetName) {
        List<Object[]> loginData = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet  = workbook.getSheet(sheetName);

            if(sheet == null){
                System.out.println("Sheet " + sheetName + " does not exist in the Excel file.");
                return new Objects[0][0];
            }

            // Skip header row (row 0), start from row 1
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);

                if(row == null){
                    continue;
                }

                Cell emailCell = row.getCell(0); // Assuming email is in the first column
                Cell passwordCell = row.getCell(1); // Assuming password is in the second column

              if(emailCell != null && passwordCell != null){
                  String email = emailCell.getStringCellValue();
                  String password = passwordCell.getStringCellValue();
                  loginData.add(new Object[]{email, password});
                  System.out.println("Loaded credentials from Excel - Email" +email);
              }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginData.toArray(new Object[0][0]);
    }

}
