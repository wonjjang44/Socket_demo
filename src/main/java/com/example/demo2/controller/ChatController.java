package com.example.demo2.controller;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.dto.AddChatRoomDto;
import com.example.demo2.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody AddChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatService.createRoom(chatRoomDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatRoom);
    }

    @GetMapping("/chat/all")
    public List<ChatRoom> findAllChatRoom() {
        return chatService.findAllRoom();
    }
}
