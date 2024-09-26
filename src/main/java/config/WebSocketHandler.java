package config;

import com.example.demo2.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;

    private final Set<WebSocketSession> sessions = new HashSet<>(); //socket session list

    private final Map<Long, Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>(); //채팅방 id와 socket session map

    @Override
    public void afterConnectionEstablished(WebSocketSession socketSession) throws Exception {
        log.info("Connect {}", socketSession.getId()) ; //연결된 socket id 출력
        sessions.add(socketSession); //session 저장
        socketSession.sendMessage(new TextMessage("Connect Chat Room")); //socket 연결 완료 메시지 전송
    } //socket 연결 확인

    @Override
    public void handleTextMessage(WebSocketSession socketSession, TextMessage message) throws Exception {
        String payload = message.getPayload(); //데이터(JSON) 받아옴
        log.info("pay load {}", payload); //데이터 출력

        //pay load -> chat message dto
        MessageDto messageDto = mapper.readValue(payload, MessageDto.class);
        log.info("session {}", messageDto.toString());

        //메세지 타입 분류
        if (messageDto.getMessageType().equals(MessageDto.MessageType.ENTER)) {
            chatRoomSessionMap.computeIfAbsent(messageDto.getChatRoom(), s -> new HashSet<>()).add(socketSession);
            messageDto.setMessage("Enter user");
        } else if (messageDto.getMessageType().equals(MessageDto.MessageType.LEAVE)) {
            chatRoomSessionMap.get(messageDto.getChatRoom()).remove(socketSession);
            messageDto.setMessage("Leave user");
        }

        //message 전송
        for (WebSocketSession s : chatRoomSessionMap.get(messageDto.getChatRoom())) {
            s.sendMessage(new TextMessage(mapper.writeValueAsString(messageDto))); //받아온 데이터를 각 socket session에 전송
        }
    } //socket message 처리

    @Override
    public void afterConnectionClosed(WebSocketSession socketSession, CloseStatus status) throws Exception {
        log.info("Close {}", socketSession.getId()); //종료할 session id 출력
        sessions.remove(socketSession); //socket session 연결 종료
        socketSession.sendMessage(new TextMessage("Close Chat Room")); //연결 종료 메시지 전송
    } //socket 연결 종료
}
