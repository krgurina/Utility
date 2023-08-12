package com.example.fromrar;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    public ArrayList<Book> Books = new ArrayList<>();

    private void initBooks() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBC;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
            statement = con.prepareStatement("select book_name, author, publication_year, pages from TEST");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBook_name(resultSet.getString("book_name"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublication_year(resultSet.getString("publication_year"));
                book.setPages(resultSet.getInt("pages"));
                Books.add(book);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initBooks();

        req.setAttribute("list", Books);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

        public void destroy() {
    }
}