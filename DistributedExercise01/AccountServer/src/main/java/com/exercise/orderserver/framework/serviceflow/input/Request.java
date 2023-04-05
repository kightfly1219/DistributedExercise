package com.exercise.orderserver.framework.serviceflow.input;

import java.io.Serializable;

public interface Request extends Serializable {
    /**
     * Structure serialization.
     *
     * @return Structure serialization string
     */
    @Override
    String toString();
}
