package com.example.orderserver.framework.serviceflow.output;

import java.io.Serializable;

public interface Response extends Serializable {
    /**
     * Structure serialization.
     *
     * @return Structure serialization string
     */
    @Override
    String toString();
}
