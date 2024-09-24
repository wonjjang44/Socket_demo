package com.example.demo2.auction.server.send;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SendServerThread extends Thread {

    private final Socket socket;
    private Scanner sc = new Scanner(System.in);

    public SendServerThread(Socket socket) {
        this.socket = socket;
    } //socket 생성자

    @Override
    public void run() {
        try {
            //데이터 전송을 위한 데이터 스트림 생성
            //socket outputStream과 연결된 객체
            DataOutputStream sendWriter = new DataOutputStream(socket.getOutputStream());
            String sendData; //전송할 데이터
            while (true) {
                sendData = sc.nextLine(); //데이터를 읽어옴
                sendWriter.writeUTF(sendData); //UTF-8 형식으로 코딩된 문자열 출력
                sendWriter.flush(); //현재 버퍼에 저장된 내용을 클라이언트로 전송하고 버퍼를 비움
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //socket.getOutputStream()에 대한 예외 처리
        }
    }
}
