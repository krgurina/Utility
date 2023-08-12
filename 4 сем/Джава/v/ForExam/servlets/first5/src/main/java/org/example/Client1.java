package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {

        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 3001;
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println(" Введите сообщение.");

            while (true) {
                String guess = scanner.nextLine();
                out.println(guess);

                String message = in.readLine();
                System.out.println(message);

                if (message.contains("Congratulations!")) {
                    break;
                }
            }
        }
    }
}
