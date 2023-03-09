package com.exercise.orderserver.framework.serviceflow.input;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeadersInfo implements Serializable {
    /**
     * アプリバージョン
     */
    private String xNkAppVersion;

    /**
     * 端末のOSタイプ
     */
    private String xDeviceOsType;

    /**
     * 端末のOSバージョン
     */
    private String xDeviceOsVersion;
}
