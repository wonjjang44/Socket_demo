package com.example.demo2.controller;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.dto.AddChatRoomDto;
import com.example.demo2.dto.ChatRoomResponse;
import com.example.demo2.dto.MessageDto;
import com.example.demo2.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/hello")
    public void message(MessageDto messageDto) {
        if (MessageDto.MessageType.ENTER.equals(messageDto.getType())) {
            messageDto.setMessage(messageDto.getId() + "님이 입장하셨습니다.");
        }
        simpMessageSendingOperations.convertAndSend("/sub/chat/room/" + messageDto.getChatRoom(), messageDto);
    }

    /*
    private final ChatService chatService;

    @PostMapping("/chat")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody AddChatRoomDto chatRoomDto) {
        ChatRoom chatRoom = chatService.createRoom(chatRoomDto.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatRoom);
    }

    @GetMapping("/chat/all")
    public ResponseEntity<List<ChatRoomResponse>> findAllChatRoom() {
        List<ChatRoomResponse> roomResponse = chatService.findAllRoom()
                .stream()
                .map(ChatRoomResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(roomResponse);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<ChatRoomResponse> findChatRoom(@PathVariable(value = "chatId") String chatId) {
        ChatRoom chatRoom = chatService.findRoomById(chatId);
        return ResponseEntity.ok()
                .body(new ChatRoomResponse(chatRoom));
    }
    */
}
