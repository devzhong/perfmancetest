package com.ne.perfmance.util;

import java.io.*;

/**
 * desc:生成账号的txt文件，用于jmeter参数化
 * author:devzhong
 * Date:2019/6/5 16:31
 */
public class TxtUtil {

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
            System.out.println("err! fw is null");
            return;
        }
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 500; i++) {
            try {
                bw.write(Long.toString(phone));
                bw.newLine();
                phone = phone + 1;
            } catch (IOException e) {
                System.out.println("error: write err！");
                e.printStackTrace();
            }
        }
        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("error: close err!");
            e.printStackTrace();
        }
        System.out.println("success: write success");
    }
}
