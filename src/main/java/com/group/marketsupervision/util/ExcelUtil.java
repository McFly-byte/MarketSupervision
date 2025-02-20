package com.group.marketsupervision.util;

import com.group.marketsupervision.pojo.Equipment;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    public static void exportEquipment(List<Equipment> equipments,
                                       HttpServletResponse response) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("导出设备列表");

            // 创建标题行
            Row headerRow = sheet.createRow(0);
            String[] headers = {"单位名称","特种设备种类","设备名称","设备代码","使用登记证编号",
                    "设备型号规格","使用地点","产品编号","投入使用日期","检验日期","下次检验日期"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 设置日期格式
            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            short dateFormat = createHelper.createDataFormat().getFormat("yyyy-MM-dd");
            dateCellStyle.setDataFormat(dateFormat);

            // 填充数据
            int rowNum = 1;
            for (Equipment equipment : equipments) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(equipment.getCompanyName());
                row.createCell(1).setCellValue(equipment.getType());
                row.createCell(2).setCellValue(equipment.getEname());
                row.createCell(3).setCellValue(equipment.getEcode());
                row.createCell(4).setCellValue(equipment.getRegistrationNumber());
                row.createCell(5).setCellValue(equipment.getVersion());
                row.createCell(6).setCellValue(equipment.getUsingPlace());
                row.createCell(7).setCellValue(equipment.getProductNumber());
//                row.createCell(8).setCellValue(equipment.getCommissioningDate());
//                row.createCell(9).setCellValue(equipment.getInspectionDate());
//                row.createCell(10).setCellValue(equipment.getNextInspectionDate());
                Cell commissioningDateCell = row.createCell(8);
                if (equipment.getCommissioningDate() != null) {
                    commissioningDateCell.setCellValue(equipment.getCommissioningDate());
                    commissioningDateCell.setCellStyle(dateCellStyle);
                }
                Cell inspectionDateCell = row.createCell(9);
                if (equipment.getInspectionDate() != null) {
                    inspectionDateCell.setCellValue(equipment.getInspectionDate());
                    inspectionDateCell.setCellStyle(dateCellStyle);
                }
                Cell nextInspectionDateCell = row.createCell(10);
                if (equipment.getNextInspectionDate() != null) {
                    nextInspectionDateCell.setCellValue(equipment.getNextInspectionDate());
                    nextInspectionDateCell.setCellStyle(dateCellStyle);
                }
                // 打开文件后日期为########是列宽问题
            }
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = "设备列表_" + System.currentTimeMillis() + ".xlsx";
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                    .replaceAll("\\+", "%20"); // 处理空格问题
            // RFC 5987标准编码方式
            response.setHeader("Content-Disposition",
                    "attachment; filename*=UTF-8''" + encodedFileName);


            // 写入响应流
            workbook.write(response.getOutputStream());
        }
    }


    // todo 兼容更多日期格式
    public static List<Equipment> parse(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        List<Equipment> equipments = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 假设第一行为标题行
            Row row = sheet.getRow(i);
            Equipment equipment = new Equipment();

            equipment.setCompanyName(row.getCell(0).getStringCellValue());
            equipment.setType(row.getCell(1).getStringCellValue());
            equipment.setEname(row.getCell(2).getStringCellValue());
            equipment.setEcode(row.getCell(3).getStringCellValue());
            equipment.setRegistrationNumber(row.getCell(4).getStringCellValue());
            equipment.setVersion(row.getCell(5).getStringCellValue());
            equipment.setUsingPlace(row.getCell(6).getStringCellValue());
            equipment.setProductNumber(row.getCell(7).getStringCellValue());

            // 设置日期字段
            try {
                if (row.getCell(8) != null) { // commissioningDate
                    Cell cell = row.getCell(8);
                    if (CellType.STRING == cell.getCellType()) {
                        String dateStr = cell.getStringCellValue();
                        equipment.setCommissioningDate(dateFormat.parse(dateStr));
                    } else if (CellType.NUMERIC == cell.getCellType() && DateUtil.isCellDateFormatted(cell)) {
                        equipment.setCommissioningDate(cell.getDateCellValue());
                    }
                }

                if (row.getCell(9) != null) { // inspectionDate
                    Cell cell = row.getCell(9);
                    if (CellType.STRING == cell.getCellType()) {
                        String dateStr = cell.getStringCellValue();
                        equipment.setInspectionDate(dateFormat.parse(dateStr));
                    } else if (CellType.NUMERIC == cell.getCellType() && DateUtil.isCellDateFormatted(cell)) {
                        equipment.setInspectionDate(cell.getDateCellValue());
                    }
                }

                if (row.getCell(10) != null) { // nextInspectionDate
                    Cell cell = row.getCell(10);
                    if (CellType.STRING == cell.getCellType()) {
                        String dateStr = cell.getStringCellValue();
                        equipment.setNextInspectionDate(dateFormat.parse(dateStr));
                    } else if (CellType.NUMERIC == cell.getCellType() && DateUtil.isCellDateFormatted(cell)) {
                        equipment.setNextInspectionDate(cell.getDateCellValue());
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error parsing date at row " + i, e);
            }

            equipments.add(equipment);
        }
        return equipments;
    }
}