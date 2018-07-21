package cn.attackme.simpleposition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 高语越 (Gao Yuyue)
 * @email gaoyuyue@outlook.com
 */
@Configuration
public class ValueConfig {
    private static String filePath;

    public static String getFilePath() {
        return filePath;
    }

    @Value("${file.path}")
    public void setFilePath(String filePath) {
        ValueConfig.filePath = filePath;
    }
}
