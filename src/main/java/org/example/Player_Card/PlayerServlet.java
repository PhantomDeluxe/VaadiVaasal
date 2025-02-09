package org.example.Player_Card;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig // Required for handling multipart form data
public class PlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Debugging: Print received form parameters
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            System.out.println("Received part: " + part.getName());
        }

        // Retrieve form parameters
        String playerName = request.getParameter("name");
        String image = request.getParameter("image");
        int score = parseIntOrDefault(request.getParameter("score"), 0);
        int bullsTamed = parseIntOrDefault(request.getParameter("bullsTamed"), 0);
        int age = parseIntOrDefault(request.getParameter("age"), 0);
        String region = request.getParameter("region");
        String nationality = request.getParameter("nationality");
        int matches = parseIntOrDefault(request.getParameter("matches"), 0);

        // Debugging: Log received values
        System.out.println("Received Name: " + playerName);
        System.out.println("Received Image: " + image);
        System.out.println("Received Score: " + score);
        System.out.println("Received Bulls Tamed: " + bullsTamed);
        System.out.println("Received Age: " + age);
        System.out.println("Received Region: " + region);
        System.out.println("Received Nationality:" + nationality);
        System.out.println("Received Matches: " + matches);

        // Validate required fields
        if (playerName == null || playerName.trim().isEmpty()) {
            response.getWriter().write("Error: Player name cannot be null or empty");
            return;
        }

        // Establish database connection and insert data
        String jdbcUrl = "jdbc:mysql://localhost:3306/JallikattuDB";
        String jdbcUser = "root";
        String jdbcPassword = "Bharathi2004";

        String sql = "INSERT INTO Players (name, image, score, bullsTamed, age, region, nationality, matches) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values in PreparedStatement
            stmt.setString(1, playerName);
            stmt.setString(2, image);
            stmt.setInt(3, score);
            stmt.setInt(4, bullsTamed);
            stmt.setInt(5, age);
            stmt.setString(6, region);
            stmt.setString(7, nationality);
            stmt.setInt(8, matches);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().write("Success");
            } else {
                response.getWriter().write("Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Debugging: Print received form parameters
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            System.out.println("Received part: " + part.getName());
        }

        // Retrieve form parameters
        String playerName = request.getParameter("name");
        String image = request.getParameter("image");
        int score = parseIntOrDefault(request.getParameter("score"), 0);
        int bullsTamed = parseIntOrDefault(request.getParameter("bullsTamed"), 0);
        int age = parseIntOrDefault(request.getParameter("age"), 0);
        String region = request.getParameter("region");
        String nationality = request.getParameter("nationality");
        int matches = parseIntOrDefault(request.getParameter("matches"), 0);

        // Debugging: Log received values
        System.out.println("Received Name: " + playerName);
        System.out.println("Received Image: " + image);
        System.out.println("Received Score: " + score);
        System.out.println("Received Bulls Tamed: " + bullsTamed);
        System.out.println("Received Age: " + age);
        System.out.println("Received Region: " + region);
        System.out.println("Received Nationality:" + nationality);
        System.out.println("Received Matches: " + matches);

        // Validate required fields
        if (playerName == null || playerName.trim().isEmpty()) {
            response.getWriter().write("Error: Player name cannot be null or empty");
            return;
        }

        // Establish database connection and insert data
        String jdbcUrl = "jdbc:mysql://localhost:3306/JallikattuDB";
        String jdbcUser = "root";
        String jdbcPassword = "Bharathi2004";

        String sql = "INSERT INTO Players (name, image, score, bullsTamed, age, region, nationality, matches) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values in PreparedStatement
            stmt.setString(1, playerName);
            stmt.setString(2, image);
            stmt.setInt(3, score);
            stmt.setInt(4, bullsTamed);
            stmt.setInt(5, age);
            stmt.setString(6, region);
            stmt.setString(7, nationality);
            stmt.setInt(8, matches);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().write("Success");
            } else {
                response.getWriter().write("Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    // Utility method to safely parse integers with default values
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return (value != null && !value.trim().isEmpty()) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
