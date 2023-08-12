package com.example.first4;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private void getExamList() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBC;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
            conn = DriverManager.getConnection(url);
            statement = conn.prepareStatement("select NAMEE, EMAIL, PHONE from EXAM");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exam exam = new Exam();
                exam.setName(resultSet.getString("NAMEE"));
                exam.setEmail(resultSet.getString("EMAIL"));
                exam.setPhone(resultSet.getString("PHONE"));
                Exams.add(exam);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException logOrIgnore) {
            }
            if (statement != null) try {
                statement.close();
            } catch (SQLException logOrIgnore) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException logOrIgnore) {
            }
        }
    }

    public void init() { }
    public void destroy() { }

    public List<Exam> Exams = new ArrayList<>();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String name = request.getParameter("name");
        final String email = request.getParameter("email");
        final String phone = request.getParameter("phone");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JDBC;IntegratedSecurity=true;" +
                    "                    + \"encrypt=true;trustServerCertificate=true");
            Statement statement1 = con.createStatement();

            statement1.executeUpdate("INSERT INTO EXAM(NAMEE, EMAIL, PHONE)" +
                    "VALUES(' "+ name + " ', ' "+ email + " ', ' "+ phone + " ');");

            getExamList();
            HttpSession session = request.getSession();
            request.setAttribute("examList", Exams);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}