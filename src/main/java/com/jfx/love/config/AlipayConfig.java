package com.jfx.love.config;//package com.jfx.love.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("alipayConfig")
public class AlipayConfig {

	// 商户appid
	@Value("${alipay.APPID}")
	private  String APPID ;

	// 私钥 pkcs8格式的
	@Value("${alipay.APP_PRIVATE_KEY}")
	private  String APP_PRIVATE_KEY ;

	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	@Value("${alipay.NOTIFY_URL}")
	private  String NOTIFY_URL ;

	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	@Value("${alipay.RETURN_URL}")
	private  String RETURN_URL ;

	// 请求网关地址
	@Value("${alipay.URL}")
	private  String URL ;

	// 编码
	@Value("${alipay.CHARSET}")
	private  String CHARSET ;

	// 返回格式
	@Value("${alipay.FORMAT}")
	private  String FORMAT;

	// 支付宝公钥
	@Value("${alipay.ALIPAY_PUBLIC_KEY}")
	private  String ALIPAY_PUBLIC_KEY;

	// 日志记录目录
	@Value("${alipay.log_path}")
	private  String log_path ;

	// RSA2
	@Value("${alipay.SIGN_TYPE}")
	private  String SIGN_TYPE ;

    //应用公匙证书
    @Value("${alipay.app_cert_path}")
    public  String app_cert_path ;
    //支付宝公钥证书文件路径
    @Value("${alipay.alipay_cert_path}")
    public  String alipay_cert_path  ;
    //支付宝CA根证书
    @Value("${alipay.alipay_root_cert_path}")
    public  String alipay_root_cert_path ;


	public String getAPPID() {
		return APPID;
	}

	public String getAPP_PRIVATE_KEY() {
		return APP_PRIVATE_KEY;
	}

	public String getNOTIFY_URL() {
		return NOTIFY_URL;
	}

	public String getRETURN_URL() {
		return RETURN_URL;
	}

	public String getURL() {
		return URL;
	}

	public String getCHARSET() {
		return CHARSET;
	}

	public String getFORMAT() {
		return FORMAT;
	}

	public String getALIPAY_PUBLIC_KEY() {
		return ALIPAY_PUBLIC_KEY;
	}

	public String getLog_path() {
		return log_path;
	}

	public String getSIGN_TYPE() {
		return SIGN_TYPE;
	}

	public String getApp_cert_path() {
		return app_cert_path;
	}

	public String getAlipay_cert_path() {
		return alipay_cert_path;
	}

	public String getAlipay_root_cert_path() {
		return alipay_root_cert_path;
	}
}
