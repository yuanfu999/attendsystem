package com.aboluo.attend.util;

import com.aboluo.attend.pojo.Attendance;
import com.aboluo.attend.pojo.Emp;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class ExcelUtil {


    public ExcelUtil() {
    }


    public Attendance String2Bean(String beanStr){
        Attendance attendance = new Attendance();
        String[] strArr = beanStr.split(",");

        attendance.setEmp_id(Integer.parseInt(strArr[0]));
        attendance.setDept(strArr[2]);
        attendance.setEmp_name(strArr[1]);
        attendance.setLate_num(Integer.parseInt(strArr[3]));
        attendance.setLeave_early_num(Integer.parseInt(strArr[4]));
        attendance.setAttend_day(Integer.parseInt(strArr[5]));
        attendance.setAbsent_day(Integer.parseInt(strArr[6]));
        attendance.setAsk_leave_day(Integer.parseInt(strArr[7]));
        return attendance;
    }



    public String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        // 其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII";
                break;
            default:
                code = "GBK";
        }
        return code;
    }


    /*
     * （考勤记录定制版）
     * path:excel表格所在路径
     * sheet_name:表的名字（excel中sheet的名字）
     * start_row:从表的第几行开始获取数据
     * start_col:从表的第几列开始读取数据
     * */
    public List<Attendance> Excel2Mysql(String path, String sheet_name, int start_row, int start_col){
        Workbook book = null;
        List<Attendance> atds = new ArrayList<>();
        try {
            //excel-2007
            book = WorkbookFactory.create(new FileInputStream(path));
            if (book instanceof XSSFWorkbook){
                //通过索引获取Excel表
                //XSSFSheet sheet = (XSSFSheet) book.getSheetAt(0);
                //通过表名获取那张数据表
                XSSFSheet sheet = (XSSFSheet) book.getSheet(sheet_name);
                //获取数据表的所有行
                int rows = sheet.getPhysicalNumberOfRows();
                for (int i = start_row; i < rows; i++) {
                    // 读取左上端单元格­
                    XSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        //获取到Excel文件中的所有的列­
                        int cells = row.getPhysicalNumberOfCells();
                        String value = "";
                        for (int j = start_col; j < cells; j++) {
                            if (j == 3 || j == 4 || j == 6 || j == 8 || j == 9 || j == 10 || j == 11 || j == 12){
                                continue;
                            }
                            if (j > 15){
                                break;
                            }
                            //获取到列的值­
                            XSSFCell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case XSSFCell.CELL_TYPE_FORMULA:
                                        break;
                                    case XSSFCell.CELL_TYPE_NUMERIC:
                                        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                                        value += bd.toPlainString() + ",";
                                        break;
                                    case XSSFCell.CELL_TYPE_STRING:
                                        //System.out.println(cell.getStringCellValue());
                                        value += cell.getStringCellValue() + ",";
                                        //value += cell.getStringCellValue() + ",";
                                        break;
                                    default:
                                        value += "0";
                                        break;
                                }
                            }
                            //可以直接设置bean的属性，返回bean
                        }//遍历列­
                        Attendance attendance = String2Bean(value);
                        atds.add(attendance);
                    }
                }
            }
            //excel-2003
            if (book instanceof HSSFWorkbook){
                //通过索引获取Excel表
                //XSSFSheet sheet = (XSSFSheet) book.getSheetAt(0);
                //通过表名获取那张数据表
                HSSFSheet sheet = (HSSFSheet) book.getSheet(sheet_name);
                //获取数据表的所有行
                int rows = sheet.getPhysicalNumberOfRows();
                for (int i = start_row; i < rows; i++) {
                    // 读取左上端单元格­
                    HSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        //获取到Excel文件中的所有的列­
                        int cells = row.getPhysicalNumberOfCells();
                        String value = "";
                        for (int j = start_col; j < cells; j++) {
                            if (j == 3 || j == 4 || j == 6 || j == 8 || j == 9 || j == 10 || j == 11 || j == 12){
                                continue;
                            }
                            if (j > 15){
                                break;
                            }
                            //获取到列的值­
                            HSSFCell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_FORMULA:
                                        break;
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                                        value += bd.toPlainString() + ",";
                                        break;
                                    case HSSFCell.CELL_TYPE_STRING:
                                        value += cell.getStringCellValue() + ",";
                                        break;
                                    default:
                                        value += "0";
                                        break;
                                }
                            }
                            //可以直接设置bean的属性，返回bean
                        }//遍历列­

                        //System.out.println(value);
                        Attendance attendance = String2Bean(value);
                        atds.add(attendance);
                    }
                }
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return atds;
    }


    /*
     * path:excel表格所在路径
     * sheet_name:表的名字（excel中sheet的名字）
     * start_row:从表的第几行开始获取数据
     * start_col:从表的第几列开始读取数据
     * */
    public List<String> Excel2Bean(String path, String sheet_name, int start_row, int start_col){
        Workbook book = null;
        List<String> emps = new ArrayList<>();
        try {
            book = WorkbookFactory.create(new FileInputStream(path));
            if (book instanceof XSSFWorkbook){
                //通过索引获取Excel表
                //XSSFSheet sheet = (XSSFSheet) book.getSheetAt(0);
                //通过表名获取那张数据表
                XSSFSheet sheet = (XSSFSheet) book.getSheet(sheet_name);
                //获取数据表的所有行
                int rows = sheet.getPhysicalNumberOfRows();
                for (int i = start_row; i < rows; i++) {
                    // 读取左上端单元格­
                    XSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        //获取到Excel文件中的所有的列­
                        int cells = row.getPhysicalNumberOfCells();
                        String value = "";
                        for (int j = start_col; j < cells; j++) {
                            //获取到列的值­
                            XSSFCell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case XSSFCell.CELL_TYPE_FORMULA:
                                        break;
                                    case XSSFCell.CELL_TYPE_NUMERIC:
                                        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                                        value += bd.toPlainString() + ",";
                                        break;
                                    case XSSFCell.CELL_TYPE_STRING:
                                        value += new String(cell.getStringCellValue().getBytes(), "UTF-8") + ",";
                                        //value += cell.getStringCellValue() + ",";
                                        break;
                                    default:
                                        value += "0";
                                        break;
                                }
                            }
                            //可以直接设置bean的属性，返回bean
                        }//遍历列­
                        if (Integer.parseInt(value) == 0){
                            break;
                        }
                        System.out.println(value);
                        //emps.add(value);
                    }
                }
            }
            if (book instanceof HSSFWorkbook){
                //通过索引获取Excel表
                //XSSFSheet sheet = (XSSFSheet) book.getSheetAt(0);
                //通过表名获取那张数据表
                HSSFSheet sheet = (HSSFSheet) book.getSheet(sheet_name);
                //获取数据表的所有行
                int rows = sheet.getPhysicalNumberOfRows();
                for (int i = start_row; i < rows; i++) {
                    // 读取左上端单元格­
                    HSSFRow row = sheet.getRow(i);
                    // 行不为空
                    if (row != null) {
                        //获取到Excel文件中的所有的列­
                        int cells = row.getPhysicalNumberOfCells();
                        String value = "";
                        for (int j = start_col; j < cells; j++) {
                            //获取到列的值­
                            HSSFCell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case HSSFCell.CELL_TYPE_FORMULA:
                                        break;
                                    case HSSFCell.CELL_TYPE_NUMERIC:
                                        BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                                        value += bd.toPlainString() + ",";
                                        break;
                                    case HSSFCell.CELL_TYPE_STRING:
                                        value += new String(cell.getStringCellValue().getBytes(), "UTF-8") + ",";
                                        //value += cell.getStringCellValue() + ",";
                                        break;
                                    default:
                                        value += "0";
                                        break;
                                }
                            }
                            //可以直接设置bean的属性，返回bean
                        }//遍历列­
                       /* if (Integer.parseInt(value) == 0){
                            break;
                        }*/
                        System.out.println(value);
                        //emps.add(value);
                    }
                }
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emps;
    }


    /*
    * 导出考勤记录表
    * */
    public HSSFWorkbook createWorkbook(List<Attendance> attendances, String[] headers) {
        // 创建表格
        HSSFWorkbook workBook = new HSSFWorkbook();
        // 创建工作簿
        HSSFSheet sheet = workBook.createSheet("考勤记录汇总");
        // 样式
        HSSFCellStyle style = workBook.createCellStyle();
        // 创建Font
        HSSFFont font = workBook.createFont();
        // 设置字体
        font.setColor(HSSFFont.COLOR_NORMAL);
        style.setFont(font);

        //在Excel里创建行,从0开始
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            //让Excel自动适应列宽
            sheet.autoSizeColumn(i);
            //获取表列头的名字
            String columnName = headers[i];
            //创建行的单元格,也是从0开始
            HSSFCell cell1 = row.createCell(i);
            //设置单元格内容
            cell1.setCellValue(columnName);
        }


        // 内容 真实环境查询数据库List，进行for遍历
        for (int i = 0; i < attendances.size(); i++) {
            HSSFRow row1 = sheet.createRow(i+1);
            row1.setHeight((short) 300);
            Attendance attendance = attendances.get(i);
            HSSFCell c1 = row1.createCell(0);
            c1.setCellValue(attendance.getEmp_id());
            HSSFCell c2 = row1.createCell(1);
            c2.setCellValue(attendance.getDept());
            HSSFCell c3 = row1.createCell(2);
            c3.setCellValue(attendance.getEmp_name());
            HSSFCell c4 = row1.createCell(3);
            c4.setCellValue(attendance.getLate_num());
            HSSFCell c5 = row1.createCell(4);
            c5.setCellValue(attendance.getLeave_early_num());
            HSSFCell c6 = row1.createCell(5);
            c6.setCellValue(attendance.getAttend_day());
            HSSFCell c7 = row1.createCell(6);
            c7.setCellValue(attendance.getAbsent_day());
            HSSFCell c8 = row1.createCell(7);
            c8.setCellValue(attendance.getAsk_leave_day());
        }
        return workBook;

    }


    /*
     * 导出考勤记录表
     * */
    public HSSFWorkbook createEmpWorkbook(List<Emp> emps, String[] headers) {
        // 创建表格
        HSSFWorkbook workBook = new HSSFWorkbook();
        // 创建工作簿
        HSSFSheet sheet = workBook.createSheet("考勤记录汇总");
        // 样式
        HSSFCellStyle style = workBook.createCellStyle();
        // 创建Font
        HSSFFont font = workBook.createFont();
        // 设置字体
        font.setColor(HSSFFont.COLOR_NORMAL);
        style.setFont(font);

        //在Excel里创建行,从0开始
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            //让Excel自动适应列宽
            sheet.autoSizeColumn(i);
            //获取表列头的名字
            String columnName = headers[i];
            //创建行的单元格,也是从0开始
            HSSFCell cell1 = row.createCell(i);
            //设置单元格内容
            cell1.setCellValue(columnName);
        }


        // 内容 真实环境查询数据库List，进行for遍历
        for (int i = 0; i < emps.size(); i++) {
            HSSFRow row1 = sheet.createRow(i+1);
            row1.setHeight((short) 300);
            Emp emp = emps.get(i);
            HSSFCell c1 = row1.createCell(0);
            c1.setCellValue(emp.getEmp_id());
            HSSFCell c2 = row1.createCell(1);
            c2.setCellValue(emp.getDept());
            HSSFCell c3 = row1.createCell(2);
            c3.setCellValue(emp.getEmp_name());
            HSSFCell c4 = row1.createCell(3);
            c4.setCellValue(emp.getStu_id());
            HSSFCell c5 = row1.createCell(4);
            c5.setCellValue(emp.getGender());
            HSSFCell c6 = row1.createCell(5);
            c6.setCellValue(emp.getTel());
            HSSFCell c7 = row1.createCell(6);
            c7.setCellValue(emp.getAddress());
            HSSFCell c8 = row1.createCell(7);
            c8.setCellValue(emp.getDisable());
        }
        return workBook;

    }


}
