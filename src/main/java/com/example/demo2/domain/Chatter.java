package com.example.demo2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatter {

    @Id
    private String id;

    private String nickname;

    @Builder
    public static Chatter toEntity(String id, String nickname) {
        return Chatter.builder()
                .id(id)
                .nickname(nickname)
                .build();
    }
}
