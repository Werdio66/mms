package com.lx.mms.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给客户端的信息
 */
@Getter
@Setter
public class RespData {

    /**
     *  状态：成功、失败
     */
    private boolean rec;

    /**
     * 返回信息
     */
    private String msg;

    /**
     *  响应数据
     */
    private Object data;

    public RespData(boolean rec){
        this.rec = rec;
    }

    public RespData(boolean rec, String msg) {
        this.rec = rec;
        this.msg = msg;
    }

    public RespData(boolean rec, String msg, Object data) {
        this.rec = rec;
        this.msg = msg;
        this.data = data;
    }

    /**
     *  返回成功状态
     */
    public static RespData ok(){
        return new RespData(true);
    }

    /**
     *  返回成功状态和信息
     */
    public static RespData ok(String msg){
        return new RespData(true, msg);
    }

    /**
     *  返回成功状态，信息和数据
     */
    public static RespData ok(Object data){
        return new RespData(true, "", data);

    }

    /**
     *  返回成功状态，信息和数据
     */
    public static RespData ok(String msg, Object data){
        return new RespData(true, msg, data);

    }

    /**
     *  返回错误状态
     */
     public static RespData error(){
        return new RespData(false);
    }
    
    /**
     *  返回错误状态和错误信息
     */
    public static RespData error(String msg){
        return new RespData(false, msg);
    }


    public  Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("rec", this.rec);
        map.put("msg", this.msg);
        map.put("data", this.data);
        return map;
    }
}
