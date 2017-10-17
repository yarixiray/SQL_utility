import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Report {
    private Date date;
    private static String status = "Status";
    private String name;
    private static String location = "Path";
    private Date startDate;

    protected static void createHeaders(HSSFSheet sheet) {
        HSSFRow headerRow = sheet.createRow(0);
        List<String> headers1 = Arrays.asList("Name_1", "Name_2", "Name_3", location, status);
        for (int i = 0; i < headers1.size(); i++) {
            headerRow.createCell(i).setCellValue(headers1.get(i));
        }
    }

    protected static HSSFWorkbook createWorkbook() {
        return new HSSFWorkbook();
    }


    protected static void fileGenearation(HSSFWorkbook workbook) {
        HSSFSheet sheet = workbook.getSheetAt(0);
        int j = 1;
        boolean flag = true;
        HSSFRow row = null;
        List<ResultSet> resultSetList = Utils.runScripts();
        for (int i = 0; i < resultSetList.size(); i++) {

            try {
                ResultSet rs = resultSetList.get(i);
                while (rs.next()) {
                    row = sheet.createRow(j);
                    row.createCell(0).setCellValue(rs.getInt(1));
                    row.createCell(1).setCellValue(rs.getString(2));
                    row.createCell(2).setCellValue(rs.getString(3));
                    flag = false;
                    j++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (flag) {
                HSSFRow row1 = sheet.createRow(j);
                row1.createCell(3).setCellValue(Utils.pathSqlScriptsList.get(i));
                row1.createCell(4).setCellValue("Passed");
                row1.createCell(5).setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                j++;
            } else {
                row.createCell(3).setCellValue(Utils.pathSqlScriptsList.get(i));
                row.createCell(4).setCellValue("Failed");
                row.createCell(5).setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
        }

        creatOutPutFile(workbook);
    }

    private static void creatOutPutFile(HSSFWorkbook workbook) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\JavaRush\\db_check\\1\\workbook.xls");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
