package Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CountServlet", urlPatterns = "/CountServlet")
public class CountServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);
        Integer counter = (Integer) session.getAttribute("counter");
        if(counter == null){
            session.setAttribute("counter", 1);
        }
        else{
            counter++;
            session.setAttribute("counter", counter);
        }

        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
