package com.example.seckillbin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seckillbin.pojo.User;
import com.example.seckillbin.vo.LoginVo;
import com.example.seckillbin.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lb
 * @since 2022-05-04
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);

    // 更新密码
    RespBean updatePassword(String userTicket,String password,HttpServletRequest request,HttpServletResponse response);

}
