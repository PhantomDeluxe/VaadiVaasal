package org.example.vaadivaasal_webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class PlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String playerName = request.getParameter("name");
        String image = request.getParameter("image");
        String bullValue = request.getParameter("bullsTamed");
        int bullsTamed = (bullValue != null && !bullValue.isEmpty()) ? Integer.parseInt(bullValue) : 0;
        String scoreParam = request.getParameter("score");
        int score = (scoreParam != null && !scoreParam.isEmpty()) ? Integer.parseInt(scoreParam) : 0; // Default to 0 if null or empty
        String ageParam = request.getParameter("age");
        int age = (ageParam != null && !ageParam.isEmpty()) ? Integer.parseInt(ageParam) : 0; // Default to 0 if null or empty
        String region = request.getParameter("region");
        String matchValue = request.getParameter("matches"); // Get matchValue
        int matches = (matchValue != null && !matchValue.isEmpty()) ? Integer.parseInt(matchValue) : 0; // Default to 0 if null or empty

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JallikattuDB", "root", "Bharathi2004");

            // Insert Query

            String name = request.getParameter("name");

            if (name == null || name.trim().isEmpty()) {
                response.getWriter().write("Error: Player name cannot be null or empty");
                return; // Stop further execution
            }

            String sql = "INSERT INTO Players (name, image, score, bullsTamed, age, region, matches) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, playerName);
            stmt.setString(2, image);
            stmt.setInt(3, score);
            stmt.setInt(4, bullsTamed);
            stmt.setInt(5, age);
            stmt.setString(6, region);
            stmt.setInt(7, matches);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().write("Success");
            } else {
                response.getWriter().write("Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
