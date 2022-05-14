package com.example.seckillbin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seckillbin.mapper.UserMapper;
import com.example.seckillbin.pojo.User;
import com.example.seckillbin.service.IUserService;
import com.example.seckillbin.utils.MD5Util;
import com.example.seckillbin.utils.ValidatorUtils;
import com.example.seckillbin.vo.LoginVo;
import com.example.seckillbin.vo.RespBean;
import com.example.seckillbin.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lb
 * @since 2022-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /*@Autowired
    RedisTemplate redisTemplate;*/



    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile=loginVo.getMobile();
        String password = loginVo.getPassword();
        /**
         * 后端是守护安全的最后一道防线，所以即使前端校验了，避免传输过程中出现问题，后端仍然需要再次校验
         */
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if (!ValidatorUtils.isMobile(mobile)){
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }
        /**
         * 上面校验都通过了，就可以去数据库查询数据了
         */

        User user = userMapper.selectById(mobile);
        if (user==null){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            //throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        /**
         * 注意这里校验的盐是我们写的固定的在数据库里的，实际开发中，盐是注册时随机生成保存在数据库中的。
         */
        if(!MD5Util.fromPass2DBPass(password,user.getSalt()).equals(user.getPassword())){
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            //throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        return RespBean.success();
        // 登陆成功的话，用session存储用户的状态信息。给用户发送sessionid存入cookie中返回给用户
        // 首先生成返回给用户的uuid
        /*String tickets= UUIDUtil.uuid();
        log.info(tickets);

        // 服务端保存用户信息到session中
//        request.getSession().setAttribute(tickets,user);
        //优化一下，不再使用session存储数据，而是将用户数据直接存储在redis中
        redisTemplate.opsForValue().set("user:"+tickets,user);

        // 将uuid返回给用户，用户再来的时候带着uuid就能知道之前登陆过没
        CookieUtil.setCookie(request,response,"userTicket",tickets);

        return RespBean.success(tickets);*/
    }


    /**
     * 对象缓存就是把对象缓存到redis中，每次获取对象就从redis中寻找，更细粒度的页面缓存
     * @param userTicket
     * @param request
     * @param response
     * @return
     */
    /*@Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {

        if (StringUtils.isEmpty(userTicket)){
            return null;
        }

        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);

        // 出于安全考虑，重新设置一遍cookie
        if (user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }*/

    /**
     * 更新密码
     * 1.更新数据库
     * 2.删除redis缓存（因为redis中存的还是旧的数据，所以用户新修改的密码是看不到的，只有删除redis中用户信息才可）
     */
    @Override
    public RespBean updatePassword(String userTicket,String password,HttpServletRequest request,HttpServletResponse response){
       /* User user = getUserByCookie(userTicket, request, response);
        //如果获取不到，说明登陆过期了或者还没登陆，进而去登陆
        if (user==null){
            throw new GlobalException(RespBeanEnum.LOGIN_TIMEOUT_ERROR);
        }
        //如果能获取到，就更新密码，删除redis缓存
        user.setPassword(MD5Utils.inputPass2DBPass(password,user.getSalt()));
        int result = userMapper.updateById(user);
        if (result==1){
            redisTemplate.delete("user:"+userTicket);
            return RespBean.success();
        }
        //更新数据库失败就返回更新失败

        */
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_ERROR);
    }
}
