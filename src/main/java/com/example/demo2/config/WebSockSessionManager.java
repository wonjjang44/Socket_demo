package com.example.demo2.config;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSockSessionManager {
    private static final ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    public static void addSession(String sessionId, WebSocketSession webSocketSession) {
        sessionMap.put(sessionId, webSocketSession);
    }

    public static void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
    }

    public static WebSocketSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
