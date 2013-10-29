package com.gts.fb.dto;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class FacebookResponse {
    private Integer statusCode;
    private String body;
    private Throwable error;

    public FacebookResponse() {
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Throwable getError() {
        return error;
    }
}
