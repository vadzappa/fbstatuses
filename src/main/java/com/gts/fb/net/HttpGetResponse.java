package com.gts.fb.net;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class HttpGetResponse {
    private Integer statusCode;
    private String body;
    private Throwable error;

    public HttpGetResponse() {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("statusCode", statusCode)
                .append("body", body)
                .append("error", error)
                .toString();
    }
}
