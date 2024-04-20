package Website;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class Data_Read {
    public static void main(String[] args) throws IOException {
        Data_Read dataRead = new Data_Read();
        Object[][] data = dataRead.getData("Jira");
    }
    public Object[][] getData(String excelSheetName) throws IOException {
        String path = "C:\\Users\\Welcome\\Desktop\\JiraData.xlsx";
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(excelSheetName);

        int lastRow = sheet.getLastRowNum();
        System.out.println("Total number of rows: " + lastRow);
        int lastCell = sheet.getRow(0).getLastCellNum();
        System.out.println("Total number of columns: " + lastCell);

        Object[][] data = new Object[lastRow][lastCell];
        for (int i = 1; i < lastRow; i++) {
            for (int j = 0; j < lastCell; j++) {
                data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                System.out.println(data[i][j]);
            }
        }
        return data;
    }
}
