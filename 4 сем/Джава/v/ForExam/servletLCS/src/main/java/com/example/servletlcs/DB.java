package com.example.servletlcs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    Connection conn = null;

    public DB() throws SQLException, ClassNotFoundException {

    }
    public List<User> list() throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<User> areas = new ArrayList<User>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ForJava;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
            Connection conn = DriverManager.getConnection(url);
            statement = conn.prepareStatement("select Login, Code from Userr");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User area = new User();
                area.setLogin(resultSet.getString("Login"));
                area.setCode(resultSet.getString("Code"));
                areas.add(area);
            }
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (resultSet != null)
                try { resultSet.close(); }
            catch (SQLException logOrIgnore) { }
            if (statement != null)
                try { statement.close(); }
            catch (SQLException logOrIgnore) { }
            if (conn != null)
                try { conn.close(); }
            catch (SQLException logOrIgnore) { }
        }
        return areas;
    }

    public void getSum(String name) throws ClassNotFoundException, SQLException {
        PreparedStatement statement = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ForJava;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
        Connection conn = DriverManager.getConnection(url);
        ResultSet resultSet = null;
        statement = conn.prepareStatement("select Sum from User where Login = '" + name + "'");
        resultSet = statement.executeQuery();
        int a = 0;
        while (resultSet.next()) {
            a= resultSet.getInt("Sum");
        }
    }
    public void delSto(String name) throws ClassNotFoundException, SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ForJava;trustServerCertificate=true;encrypt=true;IntegratedSecurity=true";
        Connection conn = DriverManager.getConnection(url);
        statement = conn.prepareStatement("select Sum from User where Login = '" + name + "'");
        resultSet = statement.executeQuery();
        int a = 0;
        while (resultSet.next()) {
                a= resultSet.getInt("Sum");
        }
        if(a >=100){
        statement.executeUpdate("update Userr set Sum = Sum - 100 where Login = '" + name + "'");
        }

    }
}
