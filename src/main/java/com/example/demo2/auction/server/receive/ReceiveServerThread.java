package com.example.demo2.auction.server.receive;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveServerThread extends Thread{

    private final Socket socket;

    public ReceiveServerThread(Socket socket) {
        this.socket = socket;
    } //socket 생성자

    @Override
    public void run() {
        try {
            //데이터 수신을 위한 데이터 스트림 생성
            //socket inputStream과 연결된 객체 생성
            DataInputStream receiver = new DataInputStream(socket.getInputStream());
            String receiveData; //수신한 데이터
            while (true) {
                receiveData = receiver.readUTF(); //UTF-8 형식으로 코딩된 문자열을 읽음
                System.out.println("[Client]" + receiveData); //받은 메시지 출력
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //socket.getInputStream에 대한 예외 처리
        }
    }
}
