package com.codeest.geeknews.model.http;

/**
 * Created by codeest on 2016/8/4.
 */
public class ApiException extends Exception{
    public ApiException(String msg)
    {
        super(msg);
    }
}
