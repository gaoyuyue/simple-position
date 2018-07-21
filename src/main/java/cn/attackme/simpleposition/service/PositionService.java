package cn.attackme.simpleposition.service;

import cn.attackme.simpleposition.config.ValueConfig;
import cn.attackme.simpleposition.model.Position;
import cn.attackme.simpleposition.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author 高语越 (Gao Yuyue)
 * @email gaoyuyue@outlook.com
 */
@Service
public class PositionService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据key保存Position
     * @param key
     * @param position
     */
    public void saveByKey(String key, Position position) {
        redisTemplate.opsForList().rightPush(key, position);
    }

    /**
     * 根据key获取Position List
     * @param key
     * @return
     */
    public List<Position> getListByKey(String key) {
        return redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key));
    }


    /**
     * 获取每个key中的首个Position
     * @return
     */
    public Map<String, Position> getMapWithKeyPosition() {
        Set<String> keys = redisTemplate.keys("*");
        Map<String, Position> map = new HashMap<>();
        for (String key : keys){
            Long size = redisTemplate.opsForList().size(key);
            Position position = (Position) redisTemplate.opsForList().range(key, size-1, size).get(0);
            map.put(key, position);
        }
        return map;
    }

    /**
     * 定时任务持久化过期数据
     */
    @Scheduled(cron = "0 */1 * * * ? ")
    private void persist() throws IOException {
        Set<String> keys = redisTemplate.keys("*");
        for (String key : keys){
            Long size = redisTemplate.opsForList().size(key);
            Position position = (Position) redisTemplate.opsForList().range(key, size-1, size).get(0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(position.getNowTime());
            calendar.add(Calendar.MINUTE, 10);
            boolean b = calendar.getTime().before(new Date());
            if (b) {
                List<Position> positions = redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key));
                FileUtils.writeString(positions.toString(), ValueConfig.getFilePath() + key);
                redisTemplate.delete(key);
            }
        }
    }
}
