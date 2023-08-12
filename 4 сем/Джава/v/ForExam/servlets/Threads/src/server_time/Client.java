package server_time;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        BufferedReader priem;
        try
        {
            socket=new Socket(InetAddress.getLocalHost(),3001);
            PrintStream pered=new PrintStream(socket.getOutputStream());
            priem=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner=new Scanner(System.in);
            while (true)
            {
                System.out.println("Введите число");
                int s=scanner.nextInt();
                if(s>=60)
                {
                    System.out.println("Введите число <60");
                }
                else {
                    pered.println(s);
                    System.out.println(new Date().toString());
                    System.out.println(priem.readLine());
                }
            }
        }
        catch (Exception ex)
        {

        }
    }
}
