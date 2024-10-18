package com.firstTryMaven;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        try(Socket clientSocket = new Socket("localhost",1234)){
            try(PrintWriter writer = new PrintWriter(clientSocket.getOutputStream())){
                String message = scan.nextLine();
                while(!(message.equals("exit"))){
                    writer.println(message);
                    System.out.println(message);
                    message = scan.nextLine();
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
