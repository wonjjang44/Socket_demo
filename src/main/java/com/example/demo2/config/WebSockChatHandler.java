package com.example.demo2.config;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.dto.MessageDto;
import com.example.demo2.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        MessageDto messageDto = mapper.readValue(payload, MessageDto.class);
        ChatRoom room = chatService.findRoomById(messageDto.getChatRoom());
        room.handleActions(session, messageDto, chatService);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WebSockSessionManager.removeSession(session.getId());
    }
}
