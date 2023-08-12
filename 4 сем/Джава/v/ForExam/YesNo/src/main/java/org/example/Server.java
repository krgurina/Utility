package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        List<Socket> clientSockets = new ArrayList<>();

        System.out.println("Ожидаю подключения клиентов...");

        while(true) {
            Socket clientSocket = serverSocket.accept();
            clientSockets.add(clientSocket);
            System.out.println("ДИКАЯ ВЕРОЧКА АВДЕЕВА ПОДКЛЮЧИЛАСЬ 😀😀😀😀😀");
            new Thread(() -> handleGuesses(clientSocket, clientSockets)).start();
        }
    }

    private static void handleGuesses(Socket clientSocket, List<Socket> clientSockets) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            int sizeYes = 0;
            int sizeNo = 0;
            String guess;

            while(true) {
                guess = in.readLine();

                switch (guess) {
                    case "/no": {
                        sizeNo++;
                        out.println("вера лох говорит ноу");
                        break;
                    }
                    case "/yes":{
                        sizeYes++;
                        out.println("вера враг народа говорит yec");
                        break;
                    }
                    case "/getall":{
                        out.println("гет АЛ уес=" + sizeYes + " и ноу=" + sizeNo);
                        break;
                    }
                    case "/help":{
                        out.println(" /no /yes /getall /help скулит о помощи");
                        break;
                    }
                    case "/exit":{
                        out.println(" прощайте друзи я теперь натурал");
                        clientSocket.close();
                        clientSockets.remove(clientSocket);
                        break;
                    }
                    default: {
                        out.println(" default");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
