package org.example.vaadivaasal_webapp;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdatePlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 📌 Handles GET Requests (Fetch player details)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 🔍 Log request parameters
        System.out.println("GET Request Received - Raw Parameters:");
        request.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + String.join(", ", value)));

        // ✅ Validate & parse ID
        int id = parseId(request.getParameter("id"), response);
        if (id == -1) return; // Stop execution if ID is invalid

        // 📌 Database Connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JallikattuDB", "root", "Bharathi2004")) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "SELECT * FROM Players WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // ✅ Convert player data to JSON format
                        String json = "{ \"id\": " + rs.getInt("id") +
                                ", \"name\": \"" + rs.getString("name") +
                                "\", \"image\": \"" + rs.getString("image") +
                                "\", \"score\": " + rs.getInt("score") +
                                ", \"bullsTamed\": " + rs.getInt("bullsTamed") +
                                ", \"age\": " + rs.getInt("age") +
                                ", \"region\": \"" + rs.getString("region") +
                                "\", \"matches\": " + rs.getInt("matches") + " }";
                        response.getWriter().write(json);
                    } else {
                        response.getWriter().write("{\"error\": \"Player not found\"}");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\": \"Database error: " + e.getMessage() + "\"}");
        }
    }

    // 📌 Handles POST Requests (Update player details)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // 🔍 Log request parameters
        System.out.println("POST Request Received - Raw Parameters:");
        request.getParameterMap().forEach((key, value) -> System.out.println(key + ": " + String.join(", ", value)));

        // ✅ Validate & parse ID
        int id = parseId(request.getParameter("id"), response);
        if (id == -1) return; // Stop execution if ID is invalid

        // ✅ Get other parameters
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String region = request.getParameter("region");
        int score = parseIntOrDefault(request.getParameter("score"), 0);
        int bullsTamed = parseIntOrDefault(request.getParameter("bullsTamed"), 0);
        int age = parseIntOrDefault(request.getParameter("age"), 0);
        int matches = parseIntOrDefault(request.getParameter("matches"), 0);

        // ✅ Validate player name
        if (name == null || name.trim().isEmpty()) {
            response.getWriter().write("Error: Player name cannot be null or empty");
            return;
        }

        // 📌 Database Connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JallikattuDB", "root", "Bharathi2004")) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 📝 Update Query using ID
            String sql = "UPDATE Players SET name=?, image=?, score=?, bullsTamed=?, age=?, region=?, matches=? WHERE id=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, image);
                stmt.setInt(3, score);
                stmt.setInt(4, bullsTamed);
                stmt.setInt(5, age);
                stmt.setString(6, region);
                stmt.setInt(7, matches);
                stmt.setInt(8, id);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    response.getWriter().write("Success");
                } else {
                    response.getWriter().write("Failed: Player not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    // ✅ Utility function to parse ID safely
    private int parseId(String idParam, HttpServletResponse response) throws IOException {
        if (idParam == null || idParam.trim().isEmpty()) {
            response.getWriter().write("Error: Invalid player ID");
            return -1;
        }
        try {
            return Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + idParam);
            response.getWriter().write("Error: Invalid player ID format");
            return -1;
        }
    }

    // ✅ Utility function to safely parse integers
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            if (value != null && !value.trim().isEmpty()) {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + value);
        }
        return defaultValue;
    }
}
