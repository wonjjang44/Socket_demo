package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper mapper;

    private final Set<WebSocketSession> sessions = new HashSet<>(); //socket session list

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

        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage(payload)); //받아온 데이터를 각 socket session에 전송
        }
    } //socket message 처리

    @Override
    public void afterConnectionClosed(WebSocketSession socketSession, CloseStatus status) throws Exception {
        log.info("Close {}", socketSession.getId()); //종료할 session id 출력
        sessions.remove(socketSession); //socket session 연결 종료
        socketSession.sendMessage(new TextMessage("Close Chat Room")); //연결 종료 메시지 전송
    } //socket 연결 종료
}
