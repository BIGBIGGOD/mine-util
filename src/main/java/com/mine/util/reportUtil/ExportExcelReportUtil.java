package com.mine.util.reportUtil;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description EXCEL报表工具类
 * Created by jiangqd on 2019/1/12.
 */
@Component
@Slf4j
@Data
@NoArgsConstructor
public class ExportExcelReportUtil {

    /** 创建新的Excel工作薄,创建HSSFWorkbook对象 **/
    private HSSFWorkbook wb = null;

    /** 新建工作表，创建HSSFSheet对象 **/
    private HSSFSheet sheet = null;

    /**
     * 创建HSSFWorkbook对象和创建HSSFSheet对象
     */
    public void createExcelObj(String sheetName){
        this.wb = new HSSFWorkbook();
        this.sheet = wb.createSheet(sheetName);
    }

    /**
     * 简单测试生成Excel报表文件
     */
    public void exportExcelReport() throws IOException {

        /** 创建新的Excel工作薄,创建HSSFWorkbook对象 **/
        wb = new HSSFWorkbook();

        /** 新建一名为"xxx"的工作表，创建HSSFSheet对象 **/
        sheet = wb.createSheet("测试工作表");

        /** 在索引为xx的位置创建行（最顶端的行），创建HSSFRow对象 **/
        HSSFRow row = sheet.createRow(0);

        /**
         *  在索引为xx的位置创建单元格（左上端），创建HSSFCell对象 **
         *  设置单元格类型
         *  设置单元格值
         */
        HSSFCell cell = row.createCell(0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue("你好呀");

        HSSFCell cell1 = row.createCell(1);
        cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell1.setCellValue("你好");

        /** 指定合并区域 **/
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 1));

        /**
         * 新建输出文件流，把生成的Excel报表输出到本地
         * 记得关闭流
         */
        File file = new File("D:\\测试文件夹\\ceshi.xls");
        FileOutputStream fs = new FileOutputStream(file);
        wb.write(fs);
        fs.flush();
        fs.close();
    }

        /**
         * 创建通用EXCEL头部
         * @param headString 头部显示的字符
         * @param colTo 需要合并到的列索引
         */
        public void createNormalHead(String headString, int colTo, int rowIndex) {

            HSSFRow row = sheet.createRow(rowIndex);

            // 设置第一行
            HSSFCell cell = row.createCell(0);
            row.setHeight((short) 400);

            // 定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(headString));

            // 指定合并区域
            sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colTo));

            HSSFCellStyle cellStyle = wb.createCellStyle();

            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行

            // 设置单元格字体
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 300);
            cellStyle.setFont(font);

            cell.setCellStyle(cellStyle);
        }

        /**
         * 创建通用报表第二行
         * @param params 统计条件数组
         * @param colTo 需要合并到的列索引
         * @Param
         */
        public void createNormalTwoRow(String[] params, int colTo,int rowIndex) {
            HSSFRow row = sheet.createRow(rowIndex);
            row.setHeight((short) 300);

            HSSFCell cell = row.createCell(0);

            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString("统计时间：" + params[0] + "至" + params[1]));

            // 指定合并区域
            sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colTo));

            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行

            // 设置单元格字体
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 250);
            cellStyle.setFont(font);

            cell.setCellStyle(cellStyle);
        }

        /**
         * 设置报表标题
         * @param columHeader 标题字符串数组
         */
        public void createColumHeader(String[] columHeader, int rowIndex) {

            // 设置列头
            HSSFRow row = sheet.createRow(rowIndex);

            // 指定行高
            row.setHeight((short) 600);

            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行

            // 单元格字体
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            cellStyle.setFont(font);

        /*
         * cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单无格的边框为粗体
         * cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
         * cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
         * cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
         * cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
         * cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
         */

            // 设置单元格背景色
            cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCell cell = null;

            for (int i = 0; i < columHeader.length; i++) {
                cell = row.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(new HSSFRichTextString(columHeader[i]));
            }

        }

        /**
         * 创建内容单元格
         * @param row HSSFRow
         * @param col short型的列索引
         * @param align 对齐方式
         * @param val 列值
         */
        public void createCell( HSSFRow row, int col, short align, String val) {
            HSSFCell cell = row.createCell(col);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(val));
            HSSFCellStyle cellstyle = wb.createCellStyle();
            cellstyle.setAlignment(align);// 指定单元格居中对齐
            cell.setCellStyle(cellstyle);
        }

        /**
         * 创建合计行
         * @param colSum 需要合并到的列索引
         * @param cellValue
         */
        public void createLastSumRow(int colSum, String[] cellValue) {

            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
            cellStyle.setWrapText(true);// 指定单元格自动换行

            // 单元格字体
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 250);
            cellStyle.setFont(font);

            HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
            HSSFCell sumCell = lastRow.createCell(0);

            sumCell.setCellValue(new HSSFRichTextString("合计"));
            sumCell.setCellStyle(cellStyle);
            sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0, sheet.getLastRowNum(), (short) colSum));// 指定合并区域

            //
            for (int i = 2; i < (cellValue.length + 2); i++) {
                sumCell = lastRow.createCell(i);
                sumCell.setCellStyle(cellStyle);
                sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));

            }

        }

        /**
         * 输出EXCEL文件
         * @param fileName 文件名
         */
        public void exportExcel(String fileName) {
            FileOutputStream fs = null;
            try {
                fs = new FileOutputStream(new File(fileName));
                wb.write(fs);
                fs.flush();
                fs.close();
            } catch (Exception e) {
                log.error("输出Excel文件失败"+ e.getMessage());
            }
        }

}
