package com.ne.perfmance.util;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * desc:生成指定行数的excel文件，以便导入到系统
 * author:devzhong
 * Date:2019/6/6 11:01
 */
public class ExcelUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    private static final String ROLE = "超级管理员";
    private static final String ORGAN = "运营机构1-1";
    private static final String PASSWORD = "11111111";
    private static String FILENAME = "C:\\Users\\sa\\Desktop\\filetest\\账号信息-导入-示例.xlsx";

    public static void main(String[] args){

        String sheetName = "Sheet1";
        FileInputStream ins= null;
        XSSFWorkbook wb = null;
        try {
            ins = new FileInputStream(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (ins == null){
            logger.error("error ins is null");
            return;
        }
        try {
            //以流的形式打开excel，以文件形式打开不能读取
            wb = new XSSFWorkbook(new BufferedInputStream(ins));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wb == null){
            logger.error("error wb is null");
            return;
        }
        XSSFSheet sheet=wb.getSheet(sheetName);
        /*XSSFRow row = sheet.createRow(2);
        row.createCell(0).setCellValue("kobe");*/
        logger.info("start writting........");
        try {
            writeByRow(sheet, 18900000000L, 100);
        }catch (Exception e){
            logger.error(" error  writeByRow error!!!");
            e.printStackTrace();
        }
        logger.info("end writting........");

        OutputStream op = null;
        try {
            op = new FileOutputStream(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (op == null){
            logger.error("err op is null!");
            return;
        }
        try {
            wb.write(op);//以流的形式往文件写数据
            op.close();
            wb.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //逐行写往excel写数据
    private static void writeByRow(XSSFSheet sheet, Long start, int num){
        for (int i = 0; i<num; i++){
            String phoneNo = start.toString();
            String username = start.toString();
            String workNo = start.toString();
            String email = start.toString()+"@qq.com";
            XSSFRow row = sheet.createRow(i+2);
            row.createCell(0).setCellValue(phoneNo);
            row.createCell(1).setCellValue(PASSWORD);
            row.createCell(2).setCellValue(username);
            row.createCell(3).setCellValue(workNo);
            row.createCell(4).setCellValue(email);
            row.createCell(5).setCellValue(ROLE);
            row.createCell(6).setCellValue(ORGAN);
            start = start+1;
        }
    }

}
