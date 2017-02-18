package com.codeest.geeknews.model.http.response;

/**
 * Created by codeest on 16/11/27.
 */

public class GoldHttpResponse<T> {

    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
