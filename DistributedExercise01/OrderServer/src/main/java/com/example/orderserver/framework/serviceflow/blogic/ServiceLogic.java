package com.example.orderserver.framework.serviceflow.blogic;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.text.ParseException;

public interface ServiceLogic<I, O> {

    /**
     * execute
     *
     * @param input input
     * @return O
     */
    O execute(I input) throws Exception;
}
