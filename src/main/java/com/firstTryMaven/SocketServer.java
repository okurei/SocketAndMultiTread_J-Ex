package com.firstTryMaven;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String[] args){
        int port = 1234;
        Scanner scan = new Scanner(System.in);
        ExecutorService executor = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server open on port " + port);
            String exit = "run";
            while (!(exit.equals("exit"))){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected "+ clientSocket.getRemoteSocketAddress());
                executor.execute(new ClientHandler(clientSocket));
                exit = scan.nextLine();
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            executor.shutdown();
        }
    }
}
