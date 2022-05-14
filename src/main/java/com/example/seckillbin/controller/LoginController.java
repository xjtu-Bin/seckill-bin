package com.example.seckillbin.controller;

import com.example.seckillbin.service.IUserService;
import com.example.seckillbin.vo.LoginVo;
import com.example.seckillbin.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    IUserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }


    // 注意在这里添加@Valid注解之后，再在pojo类上添加校验规则才有效
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Validated LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){

//        log.info(loginVo.toString());

          //controller层不做过多操作，跳转到service层完成操作

        RespBean respBean = userService.doLogin(loginVo,request,response);
        return respBean;
    }



}
