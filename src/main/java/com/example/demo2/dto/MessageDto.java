package com.example.demo2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    public enum MessageType {
        ENTER, TALK, LEAVE
    }

    private MessageType type;
    private Long chatRoom;
    private String id;
    private String message;
}
