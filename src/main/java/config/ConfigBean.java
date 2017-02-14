package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * ConfigBean
 * <p/>
 * Copyright (C) 2017 copyright.com
 * <p/>
 * Date: 02/13/2017
 *
 * @author Mikita Hladkikh
 */
@Component
@Configuration
@PropertySource("classpath:config.properties")
public class ConfigBean implements EmbeddedServletContainerCustomizer {

    @Value("${server.port}")
    private int port;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(port);
    }
}
