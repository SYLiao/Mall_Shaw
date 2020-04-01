package me.shaw.mall.common;

public enum ResultCode implements IErrorCode {
    SUCCESS(200, "Successful"),
    FAILED(500, "Failed"),
    VALIDATE_FAILED(404, "VALIDATE_FAILED"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN");
    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}