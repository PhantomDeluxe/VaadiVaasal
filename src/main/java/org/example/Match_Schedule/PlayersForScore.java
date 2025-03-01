package org.example.Match_Schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet(name = "PlayersForScore", value = "/PlayersForScore")
public class PlayersForScore extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/JallikattuDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Bharathi2004";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // CORS Headers
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        try (PrintWriter out = response.getWriter()) {
            String region = request.getParameter("region");

            if (region == null || region.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"Region parameter is missing\"}");
                return;
            }

            List<Player> players = getPlayersByRegion(region);

            if (players.isEmpty()) {
                out.print("{\"message\": \"No players found for the given region\"}");
            } else {
                out.print(new Gson().toJson(players));
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("{\"error\": \"Internal Server Error: " + e.getMessage() + "\"}");
        }
    }

    // Method to fetch players based on region
    private List<Player> getPlayersByRegion(String region) {
        List<Player> players = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM players WHERE region = ?")) {

            stmt.setString(1, region);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    players.add(new Player(rs.getInt("id"), rs.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    // Inner class for Player Model
    static class Player {
        private int id;
        private String name;

        public Player(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    // Inner class for Database Connection
    static class DatabaseConnection {
        public static Connection getConnection() throws Exception {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
    }
}
