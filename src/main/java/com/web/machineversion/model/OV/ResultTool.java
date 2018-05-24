package com.web.machineversion.model.OV;

import static com.web.machineversion.model.ResultCode.FAILED;
import static com.web.machineversion.model.ResultCode.SUCCESS;

public class ResultTool {
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
}
