package Socets.Template;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientT1 {
    public static void main(String[] args) throws Exception {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 3000;
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println("Введите сообщение для сервера: ");

            while(true) {
                String guess = scanner.nextLine();
                out.println(guess); //отправляем серверу

                String message = in.readLine(); //получаем ответ от сервера
                System.out.println(message);

                if (guess.equals("exit")) break;
            }
        }
    }
}
