package org.ksug.seminar.spring4xweb.v40;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoSocketHandler extends TextWebSocketHandler {

    /**
     * Echo를 붙이는 간단한 소켓
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        TextMessage echoMessage = new TextMessage("Echo: " + message.getPayload());
        session.sendMessage(echoMessage);
    }
}
