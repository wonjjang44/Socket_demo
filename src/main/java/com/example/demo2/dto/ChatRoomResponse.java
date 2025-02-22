package com.example.demo2.dto;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.domain.Chatter;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatRoomResponse {

    private final String chatRoomId;
    private final String name;
    private final List<Chatter> chatters;
    private final int count;

    public ChatRoomResponse(ChatRoom chatRoom) {
        this.chatRoomId = chatRoom.getChatRoomId();
        this.name = chatRoom.getName();
        this.chatters = chatRoom.getChatters();
        this.count = chatRoom.getChatters().size();
    }
}
