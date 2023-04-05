package com.example.orderserver.framework.serviceflow.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class R<D extends Response> implements Serializable {
    @Getter
    @Setter
    private Boolean success;
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private D data;
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MessageBean message;

    private R() {}

    public static <D extends Response> R<D> ok() {
        R<D> r = new R<>();
        r.setSuccess(true);
        return r;
    }
    public static <D extends Response> R<D> ok(D data) {
        R<D> r = new R<>();
        r.setSuccess(true);
        r.setData(data);
        return r;
    }
    public static <D extends Response> R<D> ok(D data, MessageBean message) {
        R<D> r = new R<>();
        r.setSuccess(true);
        r.setData(data);
        r.setMessage(message);
        return r;
    }
    public static <D extends Response> R<D> ok(MessageBean message) {
        R<D> r = new R<>();
        r.setSuccess(true);
        r.setMessage(message);
        return r;
    }
    public static <D extends Response> R<D> error() {
        R<D> r = new R<>();
        r.setSuccess(false);
        return r;
    }
    public static <D extends Response> R<D> error(D data) {
        R<D> r = new R<>();
        r.setSuccess(false);
        r.setData(data);
        return r;
    }
    public static <D extends Response> R<D> error(D data, MessageBean message) {
        R<D> r = new R<>();
        r.setSuccess(false);
        r.setData(data);
        r.setMessage(message);
        return r;
    }
    public static <D extends Response> R<D> error(MessageBean message) {
        R<D> r = new R<>();
        r.setSuccess(false);
        r.setMessage(message);
        return r;
    }
}
