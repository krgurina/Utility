package com.example.jdbcbook.servlet;

import com.example.jdbcbook.db.BookDB;
import com.example.jdbcbook.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetBooksServlet", value = "/books")
public class GetBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");
        int pageInt;
        if(pageStr != null) {
            pageInt = Integer.parseInt(pageStr);
        } else {
            pageInt = 1;
        }

        ArrayList<Book> books = BookDB.select(pageInt);
        if(books.size() == 0) {
            pageInt = 1;
            books = BookDB.select(pageInt);
        }
        request.setAttribute("books", books);
        request.setAttribute("nextPage", pageInt + 1);

        getServletContext().getRequestDispatcher("/views/books.jsp").forward(request, response);
    }
}
