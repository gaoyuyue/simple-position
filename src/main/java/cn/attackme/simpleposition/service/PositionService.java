package cn.attackme.simpleposition.service;

import cn.attackme.simpleposition.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
        redisTemplate.opsForList().leftPush(key, position);
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
            Position position = (Position) redisTemplate.opsForList().range(key, 0, 1).get(0);
            map.put(key, position);
        }
        return map;
    }
}
