package com.example.seckillbin.vo;

import com.example.seckillbin.validator.IsMobile;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

/**
 * 接收的公共登陆参数
 */
@Data
@AllArgsConstructor
public class LoginVo {
    @NotNull()
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;
}
