package cn.attackme.simpleposition.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author 高语越 (Gao Yuyue)
 * @email gaoyuyue@outlook.com
 */
public class FileUtils {
    /**
     * 写入Object到文件
     * @param content
     * @param filePath
     * @throws IOException
     */
    public static void writeString(String content, String filePath) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
        writer.write(content);
        writer.flush();
        writer.close();
        outputStream.close();
    }
}
