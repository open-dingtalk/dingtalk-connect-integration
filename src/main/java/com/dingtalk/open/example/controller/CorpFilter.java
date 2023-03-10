package com.dingtalk.open.example.controller;

import com.dingtalk.open.example.support.client.DingtalkOpenClient;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/v1/*")
@Component
public class CorpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String corpId = request.getHeader("X-DING-CORP");
        DingtalkOpenClient.IsvCorpToken.setCurrentCorp(corpId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            DingtalkOpenClient.IsvCorpToken.clear();
        }
    }
}