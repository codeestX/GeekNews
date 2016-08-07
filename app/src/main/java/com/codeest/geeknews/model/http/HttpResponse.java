package com.codeest.geeknews.model.http;

/**
 * Created by codeest on 2016/8/3.
 */
public class HttpResponse<T> {

    public static final int SUCCESS_CODE = 0;

    private int code;
    private String error;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code == SUCCESS_CODE;
    }
}
