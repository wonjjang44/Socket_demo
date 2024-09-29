package com.example.demo2.domain;

import com.example.demo2.config.WebSockSessionManager;
import com.example.demo2.dto.MessageDto;
import com.example.demo2.service.ChatService;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chatRoomId")
    private String chatRoomId;

    @Column(name = "name")
    private String name;

    @OneToMany
    @Column(name = "chatterList")
    private List<Chatter> chatters;

    @Builder
    public ChatRoom(String name) {
        this.chatRoomId = UUID.randomUUID().toString();
        this.name = name;
        this.chatters = new ArrayList<>();
    }

    /*
    public void handleActions(WebSocketSession socketSession, MessageDto messageDto, ChatService chatService) {
        if (messageDto.getType().equals(MessageDto.MessageType.ENTER)) {
            Chatter chatter = Chatter.builder()
                    .id(messageDto.getId())
                    .sessionId(socketSession.getId())
                    .build();

            WebSockSessionManager.addSession(socketSession.getId(), socketSession);
            chatters.add(chatter);
            messageDto.setMessage(messageDto.getId() + "님이 입장했습니다.");
        }

        if (messageDto.getType().equals(MessageDto.MessageType.LEAVE)) {
            Chatter chatter = chatters.stream()
                    .filter(c -> socketSession.getId().equals(c.getSessionId()))
                    .findAny()
                    .orElse(null);

            WebSockSessionManager.removeSession(socketSession.getId());
            chatters.remove(chatter);
            messageDto.setMessage(messageDto.getId() + "님이 퇴장했습니다.");
        }
        sendMessage(messageDto, chatService);
    }

    public void sendMessage(MessageDto messageDto, ChatService chatService) {
        chatters.parallelStream().forEach(s -> chatService.sendMessage(WebSockSessionManager.getSession(s.getSessionId()), messageDto));
    }
    */
}
