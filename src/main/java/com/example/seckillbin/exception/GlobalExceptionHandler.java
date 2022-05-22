package com.example.seckillbin.exception;

import com.example.seckillbin.vo.RespBean;
import com.example.seckillbin.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理组件
 * @RestControllerAdivce == @ControllerAdvice + @ResponceBody
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler(Exception.class)表示要处理这种异常
     */
    @ExceptionHandler(Exception.class)
    public RespBean exceptionHandler(Exception e){
        if (e instanceof GlobalException){
            GlobalException exception=(GlobalException) e;
            return RespBean.error(exception.getRespBeanEnum());
        }else if (e instanceof BindException){
            System.out.println("绑定异常");
            BindException bindException=(BindException) e;
            RespBean error = RespBean.error(RespBeanEnum.BINDING_ERROR);
            error.setMessage(error.getMessage()+bindException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return error;
        }
        // 如果不是哪些定制化的异常，就按默认的服务器异常处理
        return RespBean.error(RespBeanEnum.ERROR);
    }
}
