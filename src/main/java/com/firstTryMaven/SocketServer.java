package com.firstTryMaven;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    public static void main(String[] args){
        int port = 1234;
        ExecutorService executor = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server open on port " + port);
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected "+ clientSocket.getRemoteSocketAddress());
                executor.execute(new ClientHandler(clientSocket));
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            executor.shutdown();
        }
    }
}
