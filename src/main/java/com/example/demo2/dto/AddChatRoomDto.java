package com.example.demo2.dto;

import com.example.demo2.domain.ChatRoom;
import com.example.demo2.domain.Chatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddChatRoomDto {

    private String name;

    public ChatRoom toEntity() {
        return ChatRoom.builder()
                .name(name)
                .build();
    }
}
