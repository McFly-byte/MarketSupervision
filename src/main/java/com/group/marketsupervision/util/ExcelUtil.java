package com.group.marketsupervision.util;

import com.group.marketsupervision.pojo.Equipment;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ExcelUtil {

    // 支持的日期格式列表
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("M-d-yyyy")
    );

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
                populateCell(row, 0, equipment.getCompanyName());
                populateCell(row, 1, equipment.getType());
                populateCell(row, 2, equipment.getEname());
                populateCell(row, 3, equipment.getEcode());
                populateCell(row, 4, equipment.getRegistrationNumber());
                populateCell(row, 5, equipment.getVersion());
                populateCell(row, 6, equipment.getUsingPlace());
                populateCell(row, 7, equipment.getProductNumber());

                // 处理LocalDate类型日期字段
                populateLocalDateCell(row, 8, equipment.getCommissioningDate(), dateCellStyle);
                populateLocalDateCell(row, 9, equipment.getInspectionDate(), dateCellStyle);
                populateLocalDateCell(row, 10, equipment.getNextInspectionDate(), dateCellStyle);
            }

            // 自动调整列宽
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 设置响应头（保持不变）
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String fileName = "设备列表_" + System.currentTimeMillis() + ".xlsx";
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                    .replaceAll("\\+", "%20");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename*=UTF-8''" + encodedFileName);

            // 写入响应流
            workbook.write(response.getOutputStream());
        }
    }

    // 新增的LocalDate处理方法
    private static void populateLocalDateCell(Row row, int column, LocalDate localDate, CellStyle style) {
        if (localDate != null) {
            Cell cell = row.createCell(column);
            cell.setCellValue(localDate.toString()); // 直接使用ISO格式字符串
            cell.setCellStyle(style);
        }
    }

    // 原普通单元格填充方法保持不变
    private static void populateCell(Row row, int column, String value) {
        if (value != null) {
            row.createCell(column).setCellValue(value);
        }
    }

    public static List<Equipment> parse(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Equipment> equipments = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Equipment equipment = new Equipment();
                try {
                    // 处理普通字段
                    equipment.setCompanyName(getCellStringValue(row.getCell(0)));
                    equipment.setType(getCellStringValue(row.getCell(1)));
                    equipment.setEname(getCellStringValue(row.getCell(2)));
                    equipment.setEcode(getCellStringValue(row.getCell(3)));
                    equipment.setRegistrationNumber(getCellStringValue(row.getCell(4)));
                    equipment.setVersion(getCellStringValue(row.getCell(5)));
                    equipment.setUsingPlace(getCellStringValue(row.getCell(6)));
                    equipment.setProductNumber(getCellStringValue(row.getCell(7)));

                    // 处理日期字段
                    equipment.setCommissioningDate(parseDateCell(row.getCell(8), i));
                    equipment.setInspectionDate(parseDateCell(row.getCell(9), i));
                    equipment.setNextInspectionDate(parseDateCell(row.getCell(10), i));

                    equipments.add(equipment);
                } catch (Exception e) {
                    throw new RuntimeException("解析第" + (i+1) + "行数据时出错: " + e.getMessage(), e);
                }
            }
            return equipments;
        }
    }

    private static String getCellStringValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private static LocalDate parseDateCell(Cell cell, int rowNum) {
        if (cell == null) return null;

        try {
            switch (cell.getCellType()) {
                case STRING:
                    String dateStr = cell.getStringCellValue().trim();
                    return parseDateString(dateStr);
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date javaDate = cell.getDateCellValue();
                        return javaDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    }
                    throw new DateTimeParseException("数值格式不是有效日期", "", 0);
                default:
                    throw new DateTimeParseException("不支持的单元格类型", "", 0);
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException("第" + (rowNum+1) + "行日期解析失败: " + e.getMessage() +
                    "，输入值：[" + getCellOriginalValue(cell) + "]");
        }
    }

    private static LocalDate parseDateString(String dateStr) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
                // 继续尝试下一个格式
            }
        }
        throw new DateTimeParseException("无法识别的日期格式: " + dateStr, dateStr, 0);
    }


    // 获取单元格字符串值
    private static String getCellOriginalValue(Cell cell) {
        if (cell == null) return "空单元格";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return "未知类型";
        }
    }
}