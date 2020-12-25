package cn.edu.uestc.cac.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.uestc.cac.api.enums.Status;
import cn.edu.uestc.cac.api.utils.Result;

/**
 * @author wang
 */
@RestController
@RequestMapping("/indicators")
public class CollectionController {


    @PostMapping
    public Result<String> save() {

        return Result.success(Status.SUCCESS.getMsg());
    }
}
