package com.example.jdbc;

import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void init() { }

    private void database() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBC;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = connection.prepareStatement("SELECT _Like, _Dislike, _Message FROM tablet");
        ResultSet resultSet = statement.executeQuery();

//        statement = connection.prepareStatement("UPDATE tablet SET _Like = _Like + 1");
//        statement.executeUpdate();

        while (resultSet.next()){
            //_Message = resultSet.getString("_Message");

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

    }

    public void destroy() { }
}