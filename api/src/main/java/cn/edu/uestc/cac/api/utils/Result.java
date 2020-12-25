package cn.edu.uestc.cac.api.utils;

import java.text.MessageFormat;

import cn.edu.uestc.cac.api.enums.Status;

/**
 * @author wang
 */
public class Result <T> {
    private Integer code;

    private String msg;

    private T data;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(T data) {
        this.code  = 0;
        this.data = data;
    }

    private Result(Status status) {
        if (status != null) {
            this.code = status.getCode();
            this.msg = status.getMsg();
        }
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result error(Status status) {
        return new Result(status);
    }

    public static Result errorWithArgs(Status status, Object... args) {
        return new Result(status.getCode(), MessageFormat.format(status.getMsg(), args));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
