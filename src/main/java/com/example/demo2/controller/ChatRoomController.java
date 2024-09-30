package com.example.demo2.controller;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public List<ChatRoom> chatRoomAll() {
        return chatService.findAllRoom();
    } //채팅방 목록 조회

    @PostMapping("/chat/room")
    @ResponseBody
    public ChatRoom createChatRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    } //채팅방 생성

    @GetMapping("/chat/room/enter/{chatRoomId}")
    public String enterChatRoom(Model model, @PathVariable(value = "chatRoomId") String chatRoomId) {
        model.addAttribute("chatRoomId", chatRoomId);
        return "/chat/enter";
    } //채팅방 입장 화면

    @GetMapping("/chat/room/{chatRoomId}")
    @ResponseBody
    public ChatRoom chatRoom(@PathVariable(value = "chatRoomId") String chatRoomId) {
        return chatService.findRoomById(chatRoomId);
    } //채팅방 조회
}
