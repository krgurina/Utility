package com.example.task2;

import java.io.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
чё
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    private int _Like = 0;
    private int _Dislike = 0;
    private String _Message = "";

    private void db() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBC;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = connection.prepareStatement("SELECT _Like, _Dislike, _Message FROM tablet");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            _Message = resultSet.getString("_Message");
            _Like = resultSet.getInt("_Like");
            _Dislike = resultSet.getInt("_Dislike");
        }
//            if (resultSet != null) try {
//                resultSet.close();
//            } catch (SQLException logOrIgnore) {
//            }
//            if (statement != null) try {
//                statement.close();
//            } catch (SQLException logOrIgnore) {
//            }
//            if (connection != null) try {
//                connection.close();
//            } catch (SQLException logOrIgnore) {
//            }

    }



    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dilike();
            db();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("message", _Message);
        req.setAttribute("like", _Like);
        req.setAttribute("dislike", _Dislike);

        req.getRequestDispatcher("/look.jsp").forward(req, resp);


    }


    public void like() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBC;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = null;
        statement = connection.prepareStatement("UPDATE tablet SET _Like = _Like + 1");
        statement.executeUpdate();
    }
    public void dilike() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBC;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement statement = null;
        statement = connection.prepareStatement("UPDATE tablet SET _Dislike = _Dislike + 1");
        statement.executeUpdate();
    }


//        if (statement != null) try {
//            statement.close();
//        } catch (SQLException logOrIgnore) {
//        }
//        if (connection != null) try {
//            connection.close();
//        } catch (SQLException logOrIgnore) {
//        }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            like();
            db();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("message", _Message);
        request.setAttribute("like", _Like);
        request.setAttribute("dislike", _Dislike);

        request.getRequestDispatcher("/look.jsp").forward(request, response);
    }


    public void destroy() {
    }
}