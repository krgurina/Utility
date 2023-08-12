package server_time;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread {
    private PrintStream pered;
    private BufferedReader priem;
    public ServerThread(Socket socket) throws Exception
    {
        pered=new PrintStream(socket.getOutputStream());
        priem=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void run()
    {
        String str;
        try
        {
            while (true) {
                str = priem.readLine();
                System.out.println("wait+ "+str+" +sec");
                Thread.sleep(Integer.parseInt(str)*1000);
                pered.println("server time: "+new Date().toString());
            }

        }
        catch (Exception ex)
        {

        }
    }

}
