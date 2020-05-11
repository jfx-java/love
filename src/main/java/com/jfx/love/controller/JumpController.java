package com.jfx.love.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfx.love.Utils.DateUtil;
import com.jfx.love.config.AlipayConfig;
import com.jfx.love.pojo.DatabaseRequestProject;
import com.jfx.love.service.SharerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class JumpController {

    Logger logger = LoggerFactory.getLogger(JumpController.class);
    //    @Autowired
//    @Qualifier("demoService")
//    private DemoService demoService;
    @Autowired
    @Qualifier("alipayConfig")
    AlipayConfig alipayConfig;

    @Autowired
    @Qualifier("sharerService")
    private SharerService sharerService;


//    @GetMapping("/aaa")
//    public String demo(Model model){
//        List<Demo> demos = demoService.selectAll();
//        model.addAttribute("demos",demos);
//
//        return "/demo/show";
//    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String main() {
        return "main/index";
    }

    //注册
    @GetMapping("/sharerRegister")
    public String sharerRegister() {
        return "register/sharerRegister";
    }

    @GetMapping("/accepterRegister")
    public String accepterRegister() {
        return "register/accepterRegister";
    }


    //注册成功
    @GetMapping("/sharerRegisterSuccess")
    public String sharerRegisterSuccess() {
        return "registerSuccess/sharerRegisterSuccess";
    }

    @GetMapping("/accepterRegisterSuccess")
    public String accepterRegisterSuccess() {
        return "registerSuccess/accepterRegisterSuccess";
    }

    //登录
    @GetMapping("/sharerLogin")
    public String sharerLogin() {
        return "login/sharerLogin";
    }

    @GetMapping("/accepterLogin")
    public String accepterLogin() {
        return "login/accepterLogin";
    }

    @GetMapping("/moneyBeyond")
    public String moneyBeyond(){return "alipay/demo";}


    @GetMapping("/seekHelp")
    public String doSeekHelp() {
        return "seekHelp/seekHelp";
    }

    @GetMapping("/adminLogin")
    public String adminLogin() {
        return "login/adminLogin";
    }

    @GetMapping("/adminHome")
    public String webSocket() {
        return "admin/adminHome";
    }


    @GetMapping("/donation")
    public String doDonation(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //当前页数，每页数量
        PageHelper.startPage(pn, 3);
        List<DatabaseRequestProject> projects = sharerService.queryAllRunningProject();
        if (projects.isEmpty()||projects==null){
            return "donation/notHave";
        }
        PageInfo pageInfo = new PageInfo(projects);
        model.addAttribute("pageInfo", pageInfo);
        return "donation/donation";
    }

    //查询详情
    @GetMapping("/detail")
    public String detail(Model model, int id) {
        Map<String, Object> detail = sharerService.detail(id);
        DatabaseRequestProject project = (DatabaseRequestProject)detail.get("project");
        String endTime = DateUtil.date2String(project.getEndTime());
        String applyTime = DateUtil.date2String(project.getApplyTime());
        String createTime = DateUtil.date2String(project.getCreateTime());
        model.addAttribute("endTime",endTime);
        model.addAttribute("applyTime",applyTime);
        model.addAttribute("createTime",createTime);
        String code = (String) detail.get("code");
        model.addAttribute("project", project);
        if (detail.get("goods") != null) {
            model.addAttribute("goods", detail.get("goods"));
        }
        switch (code) {
            case "0":
                return "donation/detailMoney";
            case "1":
                return "donation/detailGoods";
            case "2":
                return "donation/detailVolunteer";
            case "01":
                return "donation/detailMoneyGoods";
            case "02":
                return "donation/detailMoneyVolunteer";
            case "12":
                return "donation/detailGoodsVolunteer";
            case "012":
                return "donation/detail";
        }
        return "donation/detail";
    }



//    /seekHelpDetail?id='+${project.id}
    @GetMapping("/seekHelpDetail")
    public String  seekHelpDetail(Model model, Integer id){
        Map<String, Object> detail = sharerService.detail(id);
        DatabaseRequestProject project = (DatabaseRequestProject)detail.get("project");
        String endTime = DateUtil.date2String(project.getEndTime());
        String applyTime = DateUtil.date2String(project.getApplyTime());
        String createTime = DateUtil.date2String(project.getCreateTime());
        model.addAttribute("endTime",endTime);
        model.addAttribute("applyTime",applyTime);
        model.addAttribute("createTime",createTime);
        model.addAttribute("project", project);

        if (detail.get("goods") != null) {
            model.addAttribute("goods", detail.get("goods"));
        }
        String code = (String) detail.get("code");

        switch (code) {
            case "0":
                return "seekHelp/detailMoney";
            case "1":
                return "seekHelp/detailGoods";
            case "2":
                return "seekHelp/detailVolunteer";
            case "01":
                return "seekHelp/detailMoneyGoods";
            case "02":
                return "seekHelp/detailMoneyVolunteer";
            case "12":
                return "seekHelp/detailGoodsVolunteer";
            case "012":
                return "seekHelp/detail";
        }
    return "demo/demo";


    }



    /**
     * 支付宝同步回调
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    @RequestMapping("/alipayReturnNotice")
    public ModelAndView retn(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        logger.info("支付成功, 进入同步通知接口...");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getALIPAY_PUBLIC_KEY(), alipayConfig.getCHARSET(), alipayConfig.getSIGN_TYPE());
        ModelAndView mv = new ModelAndView("alipay/alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            String time = new String(request.getParameter("timestamp").getBytes("ISO-8859-1"),"UTF-8");
            // 修改订单状态为支付成功，已付款; 同时新增支付流水
            //orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);
            if (!sharerService.paySuccessful(out_trade_no,total_amount,trade_no,time)){
                return new ModelAndView("alipay/alipayFail");
            }
            //Order order = orderService.getOrderById(out_trade_no);
//            Product product = productService.getProductById(order.getProductId());
            logger.info("********************** 支付成功(支付宝同步通知) **********************");
            logger.info("* 订单号: {}", out_trade_no);
            logger.info("* 支付宝交易号: {}", trade_no);
            logger.info("* 实付金额: {}", total_amount);
            logger.info("* 支付时间: {}",time);
            logger.info("***************************************************************");
            mv.addObject("out_trade_no", out_trade_no);
            mv.addObject("trade_no", trade_no);
            mv.addObject("total_amount", total_amount);
            mv.addObject("time", time);
        } else {
            logger.info("支付, 验签失败...");
        }
        return mv;
    }


    /**
     * 异步回调
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "alipayNotifyNotice")
    public String alipayNotifyNotice(HttpServletRequest request) throws Exception {

        logger.info("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            /*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getALIPAY_PUBLIC_KEY(), alipayConfig.getCHARSET(), alipayConfig.getSIGN_TYPE());

        //——请在这里编写您的程序（以下代码仅作参考）——

   /* 实际验证过程建议商户务必添加以下校验：
   1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
   2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
   3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
   4、验证app_id是否为该商户本身。
   */
        //验证成功
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            String time = new String(request.getParameter("timestamp").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                if (!sharerService.paySuccessful(out_trade_no,total_amount,trade_no,time)){
                    return "fail";
                }
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
//                orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);
//
//                Order order = orderService.getOrderById(out_trade_no);
//                Product product = productService.getProductById(order.getProductId());

                logger.info("********************** 支付成功(支付宝异步通知) **********************");
                logger.info("* 订单号: {}", out_trade_no);
                logger.info("* 支付宝交易号: {}", trade_no);
                logger.info("* 实付金额: {}", total_amount);
                logger.info("* 支付时间: {}", time);
//                logger.info("* 购买产品: {}", product.getName());
                logger.info("***************************************************************");
            }
            logger.info("支付成功...");
            return "success";
        }else {//验证失败
            logger.info("支付, 验签失败...");
            return "fail";
        }

    }

    @GetMapping("/commitment")
    public String commitment(){
        return "seekHelp/commitment";
    }


}




