package com.jfx.love.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jfx.love.Utils.FileUtils;
import com.jfx.love.config.AlipayConfig;
import com.jfx.love.service.SharerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


@Controller
public class PayController {

    @Autowired
    @Qualifier("alipayConfig")
    AlipayConfig alipayConfig;

    @Autowired
    @Qualifier("sharerService")
    SharerService sharerService;


    @ResponseBody
                                    //设置响应类型
    @PostMapping(value = "alipay", produces = "text/html; charset=UTF-8")
    public String alipay(HttpServletResponse response, String money, int projectId, int sharerId) throws IOException, AlipayApiException {
        if (!sharerService.checkMoneyNum(projectId)){
            String result ="<form name=\"punchout_form\" method=\"get\" action=\"/moneyBeyond\">\\n <input type=\"submit\" value=\"立即支付\" style=\"display:none\" ></form>\\n <script>document.forms[0].submit();</script>";
            return result;
        }

        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getURL(), alipayConfig.getAPPID(), alipayConfig.getAPP_PRIVATE_KEY(), alipayConfig.getFORMAT(), alipayConfig.getCHARSET(), alipayConfig.getALIPAY_PUBLIC_KEY(), alipayConfig.getSIGN_TYPE());
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(alipayConfig.getRETURN_URL());
        request.setNotifyUrl(alipayConfig.getNOTIFY_URL());
        //根据订单编号,查询订单相关信息
//        Order order = payService.selectById(orderId);
        //商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = order.getOrderId().toString();

        //把money加入sql两个表中
        String uuid=FileUtils.getUUID();
        if (!sharerService.saveOrder(projectId,sharerId,new BigDecimal(money),uuid)){
            return "/alipay/alipayFail";
        }

        String out_trade_no = uuid;
        //付款金额，必填
//        String total_amount = order.getOrderPrice().toString();
        String total_amount = money;
        //订单名称，必填
//        String subject = order.getOrderName();
        //sharerId+"捐给"+projectId
        String subject =+sharerId+"捐"+projectId;
        //商品描述，可空
        String body =sharerId+ "捐"+projectId+";捐款金额:"+money;
        String timeout_express = "1c";
        request.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                +"\"timeout_express\":\""+ timeout_express+"\","
                +"\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(request).getBody();
        return result;

    }
}