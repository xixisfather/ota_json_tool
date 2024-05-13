package com.saic.cloud.fota.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: chenchunrong
 * @Date: 2024/5/13 2:37 PM
 * @description: FileUtils
 */
public class FileUtils {

    /**
     * 传入文件名以及字符串, 将字符串信息保存到文件中
     *
     * @param strFilename
     * @param strBuffer
     */
    public static void TextToFile(final String strFilename, final String strBuffer) {
        try {
            // 创建文件对象
            File fileText = new File(strFilename);
            System.out.println(fileText.getAbsolutePath());
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);

            // 写文件
            fileWriter.write(strBuffer);
            // 关闭
            fileWriter.close();
        } catch (IOException e) {
            //
            e.printStackTrace();
        }

    }
}
