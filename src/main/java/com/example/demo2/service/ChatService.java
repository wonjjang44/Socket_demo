package com.example.demo2.service;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.dto.MessageDto;
import com.example.demo2.repository.ChatRoomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper mapper;
    private ConcurrentHashMap<String, ChatRoom> chatRoomMap;
    private final ChatRoomRepository chatRoomRepository;

    @PostConstruct
    private void init() {
        chatRoomMap = new ConcurrentHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRoomMap.values());
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    @Transactional
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = chatRoomRepository.save(new ChatRoom(name));
        log.info("Create Room : {} {}", chatRoom.getId(), chatRoom.getName());
        chatRoomMap.put(chatRoom.getChatRoomId(), chatRoom);
        return chatRoom;
    }

    public void sendMessage(WebSocketSession socketSession, MessageDto messageDto) {
        try {
            socketSession.sendMessage(new TextMessage(mapper.writeValueAsString(messageDto)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ChatRoom findRoomByChatRoomId(String chatId) {
        return chatRoomMap.get(chatId);
    }
}
