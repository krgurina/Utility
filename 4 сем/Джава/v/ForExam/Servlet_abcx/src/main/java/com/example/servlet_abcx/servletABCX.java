package com.example.servlet_abcx;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "servletABCX", value = "/servletABCX")
public class servletABCX extends HttpServlet {

    public void init(){

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        String x = request.getParameter("x");
        if(a == "" || b == "" || c == "" || x == "")
        {
            writer.println("<p>Вы не ввели один из параметров<p>");
            writer.close();
        }
        else{
            try
            {
                int aa = Integer.parseInt(a);
                int bb = Integer.parseInt(b);
                int cc = Integer.parseInt(c);
                int xx = Integer.parseInt(x);

                double y = aa + bb*xx + cc*Math.pow(xx,2);

                writer.println("<p>Values Y = " + y +  "<p>");
                writer.close();
            }
            catch (Exception e){
//                writer.println("<p>Error: " + e.toString());
                writer.println("<p>no parameters<p>");
                writer.close();
            }

        }


    }

    public void destroy() {
    }
}