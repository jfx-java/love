package com.jfx.love.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.jfx.love.Utils.SpringUtil;
import com.jfx.love.Utils.TypeUtil;
import com.jfx.love.handler.Handler;
import com.jfx.love.pojo.Message;
import com.jfx.love.pojo.RequestMsg;
import com.jfx.love.pojo.RqProjectGoods;
import com.jfx.love.service.AdminService;

import java.util.List;

public class QueryGoodsById implements Handler {
    private AdminService adminService = SpringUtil.getBean(AdminService.class);
    @Override
    public String handler(RequestMsg requestMsg) {
        List<RqProjectGoods> rqProjectGoods = adminService.queryRqGoodsByid(TypeUtil.objectToInt(requestMsg.getMsg()));
        if (rqProjectGoods!=null&&!rqProjectGoods.isEmpty()){
            return JSONArray.toJSONString(new Message(9003,"查询成功",rqProjectGoods.size(),rqProjectGoods));
        }
        return JSONArray.toJSONString(new Message(0,"查询失败(数据可能丢失)，请及时联系管理员"));
    }
}
