package org.example.Match_Schedule;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig // Required for handling multipart form data
public class AdminDatabase extends HttpServlet {
        private static final long serialVersionUID = 1L;

        // Database credentials
        private static final String DB_URL = "jdbc:mysql://localhost:3306/JallikattuDB";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "Bharathi2004";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            out.println("<h3>Error: Username or Password cannot be empty.</h3>");
            return;
        }

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL Insert Query
            String query = "INSERT INTO admin_users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("Admin login details saved successfully!");
            } else {
                out.println("<h3>Error saving admin login details.</h3>");
            }

            // Close resources
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Database Connection Error!</h3>");
        }
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Validate input
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                out.println("<h3>Error: Username or Password cannot be empty.</h3>");
                return;
            }

            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish connection
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                // SQL Insert Query
                String query = "INSERT INTO admin_users (name, email, password) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);

                int result = ps.executeUpdate();

                if (result > 0) {
                    out.println("Admin login details saved successfully!");
                } else {
                    out.println("<h3>Error saving admin login details.</h3>");
                }

                // Close resources
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<h3>Database Connection Error!</h3>");
            }
        }
    }
