package org.example;


import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        final int SERVER_PORT = 3001;
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        List<Socket> clientSockets = new ArrayList<>();

        String message = "exit";

        System.out.println("Ожидаю подключения клиентов...");

        int i = 1;
        while(true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент " + i + " подключился!");
            clientSockets.add(clientSocket);

            int finalI = i;
            new Thread(() -> handleGuesses(clientSocket, clientSockets, message, finalI)).start();
            i++;
        }
    }
    private static void handleGuesses(Socket clientSocket, List<Socket> clientSockets, String message, int number) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String guess;

            while(true) {
                guess = in.readLine();

                if (guess.equals(message)) {
                    out.println("Ты угадал, строку");
                    break;
                } else {
                    out.println(number);
                }
            }
            clientSocket.close();
            clientSockets.remove(clientSocket);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
