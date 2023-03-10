package com.dingtalk.open.example.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 *
 * @author donghuai.zjj
 * @date 2023/01/03
 */
@RestController
public class CustomizedErrorController implements ErrorController {
    /***
     * 404处理
     * @param e
     * @return
     */
    @RequestMapping("/error")
    public Map<String, ?> notFountHandler(HttpServletRequest request,
                                  HttpServletResponse response){
        Object uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String method = request.getMethod();
        Map<String, Object> data = new HashMap<>(2);
        data.put("method", method);
        data.put("path", uri);
        data.put("httpStatus", statusCode);
        response.setStatus(Integer.parseInt(statusCode.toString()));
        return data;
    }
}
