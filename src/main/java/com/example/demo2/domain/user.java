package com.example.demo2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class user {

    private String nickname;

    public String getNickName() {
        return nickname;
    }
}
