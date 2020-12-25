package cn.edu.uestc.cac.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wang
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping
    public String hello() {
        return "hello world!";
    }
}
