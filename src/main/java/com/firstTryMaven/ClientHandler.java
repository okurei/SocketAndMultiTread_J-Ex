package com.firstTryMaven;


import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private final Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }

    @Override
    public void run(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(),true)){
            System.out.println("Connected to the server");
            String message;
            while((message = reader.readLine()) != null){
                writer.println("Echo from "+clientSocket.getRemoteSocketAddress() +" : "+message);
                System.out.println(clientSocket.getRemoteSocketAddress()+" say: "+message);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                clientSocket.close();
                System.out.println("Client closed"+ clientSocket.getRemoteSocketAddress());
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
