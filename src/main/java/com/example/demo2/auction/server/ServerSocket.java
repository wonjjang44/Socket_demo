package com.example.demo2.auction.server;

import com.example.demo2.auction.server.receive.ReceiveServerThread;
import com.example.demo2.auction.server.send.SendServerThread;

import java.io.IOException;
import java.net.Socket;

public class ServerSocket {

    public void start() throws IOException {

        int port = 8888;
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(port); //port를 지정해 socket과 binding
        while (true) {
            Socket socket = serverSocket.accept(); //client와 연결 생성
            System.out.println("[Connection] ip : " + socket.getInetAddress()); //연결 시 메시지 출력

            ReceiveServerThread receiveThread = new ReceiveServerThread(socket); //송신 스레드 생성
            receiveThread.start(); //송신 스레드 실행

            SendServerThread sendThread = new SendServerThread(socket); //수신 스레드 생성
            sendThread.start(); //수신 스레드 실행
        }
    }
}
