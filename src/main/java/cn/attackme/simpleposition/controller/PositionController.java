package cn.attackme.simpleposition.controller;

import cn.attackme.simpleposition.model.Position;
import cn.attackme.simpleposition.service.PositionService;
import cn.attackme.simpleposition.utils.CoordinateConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 高语越 (Gao Yuyue)
 * @email gaoyuyue@outlook.com
 */
@RestController
public class PositionController {
    @Autowired
    private PositionService positionService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/position")
    public void saveByKey(String key, Position position) {
        position = CoordinateConversion.bd_google_baidu_encrypt(position.getLatitude(), position.getLongitude());
        messagingTemplate.convertAndSend("/topic/" + key, position);
        Map<String, Position> map = new HashMap<>();
        map.put(key, position);
        messagingTemplate.convertAndSend("/queue", map);
        positionService.saveByKey(key, position);
    }

    @GetMapping("/positions")
    public List<Position> getListByKey(String key) {
        return positionService.getListByKey(key);
    }

    @GetMapping("/positions/now")
    public Map<String, Position> getMapWithKeyPosition() {
        return positionService.getMapWithKeyPosition();
    }
}
