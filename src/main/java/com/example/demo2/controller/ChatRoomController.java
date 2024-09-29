package com.example.demo2.controller;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.dto.AddChatRoomDto;
import com.example.demo2.dto.ChatRoomResponse;
import com.example.demo2.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping("/chat/room")
    public String chatRoomList(Model model) {
        return "/chat/room";
    } //채팅방 목록 화면

    @GetMapping("/chat/room/all")
    public ResponseEntity<List<ChatRoomResponse>> chatRoomAll() {
        List<ChatRoomResponse> chatRoomResponses = chatService.findAllRoom()
                .stream()
                .map(ChatRoomResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(chatRoomResponses);
    } //채팅방 목록 조회

    @PostMapping("/chat/room")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody AddChatRoomDto addChatRoomDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatService.createRoom(addChatRoomDto.getName()));
    } //채팅방 생성

    @GetMapping("/chat/room/enter/{chatRoomId}")
    public String enterChatRoom(Model model, @PathVariable(value = "chatRoomId") String chatRoomId) {
        model.addAttribute("chatRoomId", chatRoomId);
        return "/chat/enterRoom";
    } //채팅방 입장 화면

    @GetMapping("/chat/room/{chatRoomId}")
    public ResponseEntity<ChatRoomResponse> chatRoom(@PathVariable(value = "chatRoomId") String chatRoomId) {
        ChatRoom chatRoom = chatService.findRoomById(chatRoomId);
        return ResponseEntity.ok()
                .body(new ChatRoomResponse(chatRoom));
    } //채팅방 조회
}
