package com.example.demo2;

import com.example.demo2.auction.client.ClientSocket;
import com.example.demo2.auction.server.ServerSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(Demo2Application.class, args);
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		if (input == 1) {
			ServerSocket serverSocket = new ServerSocket();
			serverSocket.start();
		} else if (input == 2) {
			ClientSocket clientSocket = new ClientSocket();
			clientSocket.start();
		} else {
			throw new IllegalArgumentException("Illegal Number");
		}
	}

}
