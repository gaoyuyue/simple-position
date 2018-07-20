package cn.attackme.simpleposition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 高语越 (Gao Yuyue)
 * @email gaoyuyue@outlook.com
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
