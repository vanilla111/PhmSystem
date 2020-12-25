package cn.edu.uestc.cac.api.enums;

/**
 * @author wang
 */
public enum Status {
    SUCCESS(0, "success");

    private final int code;
    private final String msg;

    private Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
