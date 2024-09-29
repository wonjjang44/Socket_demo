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
}
