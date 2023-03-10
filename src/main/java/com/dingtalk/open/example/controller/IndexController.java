package com.dingtalk.open.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：首页
 *
 * @author donghuai.zjj
 * @date 2023/01/03
 */
@RequestMapping("/")
@Controller
public class IndexController {
    @GetMapping
    public String index() {
        return "index.html";
    }
}
