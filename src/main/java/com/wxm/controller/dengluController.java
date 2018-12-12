package com.wxm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxm.model.DengluModel;
import com.wxm.service.IDengluService;
import com.wxm.util.HttpOutUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//lianxifenzhi
@Controller
@RequestMapping("/login.req")
public class dengluController {
    @Resource
    private IDengluService idengluservice;
    private static final Logger logger = LoggerFactory.getLogger(lolaController.class.getName());


    @RequestMapping(method = {RequestMethod.POST},params = "action=come")
    public void getdenglu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        JSONObject result = new JSONObject();
        result.put("result", "10001");
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        logger.info("输入的账号密码为" + "  "+username+"   "+password);
        if(username=="")
        {result.put("errMsg", "账号不能为空");
            result.put("result","10004");}
        else {
            try {
                DengluModel a = idengluservice.login(username, password);
                if (a == null) {
                    result.put("errMsg", "用户名或密码错误");
                    result.put("result","10002");
                } else {
                    result.put("ehahah", "登陆成功");
                    result.put("result","10000");
                }
                 } catch (Exception e) {
                result.put("essMsg", e.getMessage());
                }
        }
        HttpOutUtil.outData(response, JSONObject.toJSONString(result));
    }
}
