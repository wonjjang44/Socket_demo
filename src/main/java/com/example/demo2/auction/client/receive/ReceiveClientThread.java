package com.example.demo2.auction.client.receive;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveClientThread extends Thread {

    private final Socket socket;
    private String receiveData;
    public ReceiveClientThread(Socket socket) {
        this.socket = socket;
    } //socket 생성자

    @Override
    public void run() {
        try {
            //데이터 수신을 위한 데이터 스트림 생성
            //socket inputStream과 연결된 객체 생성
            DataInputStream receiver = new DataInputStream(socket.getInputStream());
            while (true) {
                receiveData = receiver.readUTF(); //UTF-8 형식으로 코딩된 문자열을 읽음
                if (receiveData == null) {
                    System.out.println("Close Server"); //받은 메시지가 없을 경우 연결이 종료 메시지 출력
                } else {
                    System.out.println("[Server] : " + receiveData); //받은 메시지 출력
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //socket.getInputStream에 대한 예외 처리
        }
    }
}
