package com.example.jdbcbook.db;

import com.example.jdbcbook.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookDB {
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=task1;integratedSecurity=true;\"\n" +
            "                    + \"encrypt=true;trustServerCertificate=true";

    public static ArrayList<Book> select(int page) {
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = DriverManager.getConnection(url)) {
                String sql = "SELECT author, book_name, publication_year, pages FROM books WHERE pages = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, page);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            String author = resultSet.getString("author");
                            String book_name = resultSet.getString("book_name");
                            int publication_year = resultSet.getInt("publication_year");
                            int pages = resultSet.getInt("pages");

                            Book book = new Book(author, book_name, publication_year, pages);
                            books.add(book);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return books;
    }
}
