package com.lishuai.spring.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录表单
 *
 * @Author scott
 * @since  2019-01-18
 */
@ApiModel(value="登录对象", description="登录对象")
public class SysLoginModel {
	@ApiModelProperty(value = "账号")
    private String username;
	@ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "登录字符串")
    private String loginStr; // 该str是由用户名、密码、时间戳拼接加密后生成
	@ApiModelProperty(value = "验证码")
    private String captcha;
	@ApiModelProperty(value = "验证码key")
    private String checkKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginStr() {
        return this.loginStr;
    }

    public void setLoginStr(String loginStr) {
        this.loginStr = loginStr;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

	public String getCheckKey() {
		return checkKey;
	}

	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}
    
}