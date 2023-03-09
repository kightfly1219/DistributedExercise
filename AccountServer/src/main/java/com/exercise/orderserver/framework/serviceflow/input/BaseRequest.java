package com.exercise.orderserver.framework.serviceflow.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@ToString(of = {"jwt", "headers"}, doNotUseGetters = true)
public abstract class BaseRequest implements Request {
    @JsonIgnore
    private final JwtInfo jwt = new JwtInfo();
    @JsonIgnore
    private final HeadersInfo headers = new HeadersInfo();

    public BaseRequest() {
        jwtInfo();
        headersInfo();
    }

    private void jwtInfo() {
    }

    private void headersInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String xNkAppVersion = request.getHeader("x-nk-app-version");
        String xDeviceOsType = request.getHeader("x-device-os-type");
        String xDeviceOsVersion = request.getHeader("x-device-os-version");
        headers.setXNkAppVersion(xNkAppVersion);
        headers.setXDeviceOsType(xDeviceOsType);
        headers.setXDeviceOsVersion(xDeviceOsVersion);
    }

    /**
     * Get the information parsed in JWT.
     * (note that if you don't need an authorized interface,
     * all properties of the secondary object are null)
     *
     * @return JWT information
     */
    public JwtInfo getJwt() {
        return jwt;
    }

    public HeadersInfo getHeaders() {
        return headers;
    }
}
