package org.ksug.seminar.spring4xweb.configuration;

import org.ksug.seminar.spring4xweb.v40.EchoSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoSocketHandler(), "/echo");
        registry.addHandler(echoSocketHandler(), "/echojs").withSockJS();
    }

    @Bean
    public WebSocketHandler echoSocketHandler() {
        return new EchoSocketHandler();
    }

}
