package server_time;

import java.net.ServerSocket;
import java.net.Socket;

public class NetServerThread {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket=new ServerSocket(3001);
            System.out.println("Start");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("connection - " + socket.getInetAddress().getHostAddress());
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        }
        catch (Exception ex)
        {

        }
    }
}
