package Socets.Template;
import java.io.*;
import java.net.*;
import java.util.*;
/*
* тут просто настроена связь клиент-сервер
* клиент отправляет серверу строку, а сервер пересылвает её назад*/
public class ServerT {
    public static void main(String[] args) throws Exception {
        final int SERVER_PORT = 3000;
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        List<Socket> clientSockets = new ArrayList<>();
        //int number = new Random().nextInt(20);

        System.out.println("Ожидаю подключения клиентов...");

        // Ожидаем подключения клиентов
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент " + clientSocket + " подключился!");
            clientSockets.add(clientSocket);
            new Thread(() -> handleGuesses(clientSocket, clientSockets)).start();   //запускаем отдельный поток для каждого клиента
        }
    }

    private static void handleGuesses(Socket clientSocket, List<Socket> clientSockets) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String guess;
            while(true) {
                guess = in.readLine();
                if (guess == null)
                    break;
                out.println("Ответ от сервера: " + guess);
            }
            clientSocket.close();
            clientSockets.remove(clientSocket);
            } catch (IOException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
    }

