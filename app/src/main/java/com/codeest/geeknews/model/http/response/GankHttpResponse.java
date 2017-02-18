package com.codeest.geeknews.model.http.response;

/**
 * Created by codeest on 2016/8/3.
 */
public class GankHttpResponse<T> {

    private boolean error;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
