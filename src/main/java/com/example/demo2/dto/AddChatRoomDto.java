package com.example.demo2.dto;

import com.example.demo2.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
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
