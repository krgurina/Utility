package com.example.jdbclike.servlet;

import com.example.jdbclike.db.CommentDB;
import com.example.jdbclike.model.Comment;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CommentServlet", value = "/comments")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Comment> comments = CommentDB.select();
        request.setAttribute("comments", comments);

        getServletContext().getRequestDispatcher("/views/look.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int like = Integer.parseInt(request.getParameter("like"));
        int dislike = Integer.parseInt(request.getParameter("dislike"));

        Comment post = new Comment(id, like, dislike);
        CommentDB.update(post);

        response.sendRedirect("comments");
    }
}
