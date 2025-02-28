package org.example.Match_Schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@MultipartConfig
public class AdminLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JallikattuDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Bharathi2004";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            out.println("<h3>Error: Email or Password cannot be empty.</h3>");
            return;
        }

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Query to check admin credentials
            String query = "SELECT name FROM admin_users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Fetch admin name
                String adminName = rs.getString("name");

                // Create a session for the admin
                HttpSession session = request.getSession();
                session.setAttribute("adminName", adminName);
                session.setAttribute("adminEmail", email);

                out.println("Login Successful! Welcome, " + adminName + ".");
                response.sendRedirect("adminDashboard.jsp");
            } else {
                out.println("Error: Invalid Email or Password!");
            }

            // Close resources
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Database Connection Error!");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            out.println("<h3>Error: Email or Password cannot be empty.</h3>");
            return;
        }

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Query to check admin credentials
            String query = "SELECT name FROM admin_users WHERE email = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Fetch admin name
                String adminName = rs.getString("name");

                // Create a session for the admin
                HttpSession session = request.getSession();
                session.setAttribute("adminName", adminName);
                session.setAttribute("adminEmail", email);

                out.println("Login Successful! Welcome, " + adminName + ".");
                response.sendRedirect("adminDashboard.jsp");

            } else {
                out.println("Error: Invalid Email or Password!");
            }

            // Close resources
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Database Connection Error!");
        }
    }
}