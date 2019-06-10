package com.ne.perfmance.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * desc:读取properties配置文件
 * author:devzhong
 * Date:2019/6/10 16:35
 */
public class PropertiestUtil {

    public static String getValue(String key){
        InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("serverConfig.properties");
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(ins);
        } catch (IOException e) {
            System.out.println("err load ins error");
            e.printStackTrace();
        }
        System.out.println("success load ins success");
        return prop.getProperty(key);
    }

}
