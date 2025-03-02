package org.example.Bulls_and_Tamers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BullsCardServlet")
public class BullsCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String experience = request.getParameter("experience");
            String region = request.getParameter("region");
            String nationality = request.getParameter("nationality");
            String matchesPlayed = request.getParameter("matchesPlayed");
            String bullName = request.getParameter("bullName");
            String strength = request.getParameter("strength");
            String speed = request.getParameter("speed");
            String breed = request.getParameter("breed");

            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JallikattuDB", "root", "Bharathi2004");
                 PreparedStatement pstmt = con.prepareStatement(
                         "INSERT INTO tamers (name, experience, region, nationality, matches_played, bull_name, strength, speed, breed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                pstmt.setString(1, name);
                pstmt.setString(2, experience);
                pstmt.setString(3, region);
                pstmt.setString(4, nationality);
                pstmt.setString(5, matchesPlayed);
                pstmt.setString(6, bullName);
                pstmt.setString(7, strength);
                pstmt.setString(8, speed);
                pstmt.setString(9, breed);

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    out.println("<h2>Data Inserted Successfully!</h2>");
                } else {
                    out.println("<h2>Failed to Insert Data!</h2>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Bulls & Tamers Data</title></head><body>");
            out.println("<h2>Registered Bulls and Tamers</h2>");

            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JallikattuDB", "root", "Bharathi2004");
                 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tamers");
                 ResultSet rs = pstmt.executeQuery()) {

                out.println("<table border='1'><tr><th>Name</th><th>Experience</th><th>Region</th><th>Nationality</th><th>Matches Played</th><th>Bull Name</th><th>Strength</th><th>Speed</th><th>Breed</th></tr>");

                while (rs.next()) {
                    out.println("<tr><td>" + rs.getString("name") + "</td>" +
                            "<td>" + rs.getString("experience") + "</td>" +
                            "<td>" + rs.getString("region") + "</td>" +
                            "<td>" + rs.getString("nationality") + "</td>" +
                            "<td>" + rs.getString("matches_played") + "</td>" +
                            "<td>" + rs.getString("bull_name") + "</td>" +
                            "<td>" + rs.getString("strength") + "</td>" +
                            "<td>" + rs.getString("speed") + "</td>" +
                            "<td>" + rs.getString("breed") + "</td></tr>");
                }
                out.println("</table>");
            }

            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
