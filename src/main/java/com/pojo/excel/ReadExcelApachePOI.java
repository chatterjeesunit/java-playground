package com.pojo.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class ReadExcelApachePOI {

    final static Map<String, Integer> headerPos = new HashMap<>();

    public static void main(String[] args) {

        List<OrderDetail> orderDetails = new ArrayList<>();

        try {

            URL resource = ReadExcelApachePOI.class.getResource("/excel/SampleData.xlsx");
            File excelFile = new File(resource.toURI());
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet workbookSheet = Optional.ofNullable(workbook.getSheet("SalesOrders")).orElse(workbook.getSheetAt(0));



            Row headerRow = workbookSheet.getRow(workbookSheet.getFirstRowNum());
            headerRow.forEach(cell -> headerPos.put(cell.getStringCellValue(), cell.getColumnIndex()));

            for(int i = workbookSheet.getFirstRowNum() + 1; i < workbookSheet.getLastRowNum(); i++)
            {
                Row currentRow = workbookSheet.getRow(i);

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderDate(getCell("OrderDate", currentRow).getDateCellValue());
                orderDetail.setRegion(getCell("Region", currentRow).getStringCellValue());
                orderDetail.setRepresentative(getCell("Rep", currentRow).getStringCellValue());
                orderDetail.setItem(getCell("Item", currentRow).getStringCellValue());


                orderDetail.setUnits(getIntValue("Units", currentRow));
                orderDetail.setUnitCost(getDoubleValue("Unit Cost", currentRow));
                orderDetail.setTotal(getDoubleValue("Total", currentRow));

                orderDetails.add(orderDetail);

            }
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderDetails);
            System.out.println(json);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }


    }

    private static Cell getCell(String headerName, Row currentRow) {
        int cellNum = headerPos.get(headerName);
        return currentRow.getCell(cellNum);
    }

    private static int getIntValue(String headerName, Row currentRow ) {
        double value = getCell(headerName, currentRow).getNumericCellValue();
        return Double.valueOf(value).intValue();
    }

    private static double getDoubleValue(String headerName, Row currentRow) {
        double value = getCell(headerName, currentRow).getNumericCellValue();
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
