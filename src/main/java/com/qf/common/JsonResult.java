package com.qf.common;

import com.qf.domain.Rank;

public class JsonResult {

    private int code;//状态码
    private String msg;//返回信息
    private Object data;//返回大数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //返回成功信息
    public static JsonResult setOK(Object data){

        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        jsonResult.setMsg("操作成功");
        jsonResult.setData(data);
        return jsonResult;
    }

    //返回失败信息
    public static JsonResult setERROR(Object data){

        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(404);
        jsonResult.setMsg("操作失败");
        jsonResult.setData(data);
        return jsonResult;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
