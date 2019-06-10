package com.ne.perfmance.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * desc:生成账号的txt文件，用于jmeter参数化
 * author:devzhong
 * Date:2019/6/5 16:31
 */
public class TxtUtil {

    private final static Logger logger = LoggerFactory.getLogger(TxtUtil.class);
    //存放文件路径
    private static final String FILENAME= "C:\\Users\\sa\\Desktop\\performancetest\\username.txt";

    public static void main(String[] args) {
        long phone = 13000000001L;
        File file1 = new File(FILENAME);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fw == null){
            logger.error("err! fw is null");
            return;
        }
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 500; i++) {
            try {
                bw.write(Long.toString(phone));
                bw.newLine();
                phone = phone + 1;
            } catch (IOException e) {
                logger.error("error: write err！");
                e.printStackTrace();
            }
        }
        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            logger.error("error: close err!");
            e.printStackTrace();
        }
        logger.info("success: write success");
    }
}
