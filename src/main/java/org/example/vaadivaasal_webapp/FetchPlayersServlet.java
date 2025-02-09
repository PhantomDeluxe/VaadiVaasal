package org.example.vaadivaasal_webapp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/FetchPlayersServlet")
public class FetchPlayersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode playerList = objectMapper.createArrayNode();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JallikattuDB", "root", "Bharathi2004");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Players");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ObjectNode player = objectMapper.createObjectNode();
                player.put("id", rs.getInt("id"));
                player.put("name", rs.getString("name"));
                player.put("image", rs.getString("image"));
                player.put("score", rs.getInt("score"));
                player.put("bullsTamed", rs.getInt("bullsTamed"));
                player.put("age", rs.getInt("age"));
                player.put("region", rs.getString("region"));
                player.put("nationality", rs.getString("nationality"));
                player.put("matches", rs.getInt("matches"));
                playerList.add(player);
            }
            response.getWriter().write(objectMapper.writeValueAsString(playerList));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
