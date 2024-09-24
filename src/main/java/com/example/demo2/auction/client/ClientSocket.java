package com.example.demo2.auction.client;

import com.example.demo2.auction.client.receive.ReceiveClientThread;
import com.example.demo2.auction.client.send.SendClientThread;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

    public void start() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        System.out.println("Connect Server");

        ReceiveClientThread receiveThread = new ReceiveClientThread(socket);
        receiveThread.start();

        SendClientThread sendThread = new SendClientThread(socket);
        sendThread.start();
    }
}
