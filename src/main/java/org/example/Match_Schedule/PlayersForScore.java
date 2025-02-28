package org.example.Match_Schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet(name = "PlayersForScore", value = "/PlayersForScore")
public class PlayersForScore extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // CORS Fix
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // PrintWriter inside try-with-resources (ensures it is closed)
        try (PrintWriter out = response.getWriter()) {
            String region = request.getParameter("region");
            if (region == null || region.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\": \"Region parameter is missing\"}");
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM Players WHERE region = ?")) {

                stmt.setString(1, region);
                try (ResultSet rs = stmt.executeQuery()) {
                    ArrayList<Player> players = new ArrayList<>();
                    while (rs.next()) {
                        players.add(new Player(rs.getInt("id"), rs.getString("name")));
                    }
                    out.print(new Gson().toJson(players));
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"error\": \"Internal Server Error\"}");
            }
        }
    }
}
