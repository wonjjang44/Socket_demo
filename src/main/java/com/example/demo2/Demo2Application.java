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
	}

}
