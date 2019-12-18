package com.lx.framework.configure.rabbitmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "lx.rabbitmq")
public class RabbitmqProperties {

    private int port;
    private String host;
    private String userName;
    private String password;
    private boolean confirm;
    private String vhost;

}
