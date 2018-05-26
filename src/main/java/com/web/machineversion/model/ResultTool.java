package com.web.machineversion.model;

import com.web.machineversion.model.OV.Result;

import java.util.List;

import static com.web.machineversion.model.ResultCode.FAILED;
import static com.web.machineversion.model.ResultCode.SUCCESS;

public class ResultTool {

    public static Result success(List<Object> object){
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(object);
        return result;
    }

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(object);
        return result;
    }

    public static Result success(){
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(null);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(FAILED);
        result.setData(null);
        return result;
    }

    public static Result error(String message){
        Result result = new Result();
        result.setMessage(message);
        result.setCode(FAILED);
        result.setData(null);
        return result;
    }
    public static Result PermissionsError(){
        Result result = new Result();
        result.setCode(FAILED);
        result.setData(null);
        result.setMessage("对不起，您没有权限");
        return result;
    }
}
