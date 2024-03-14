import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login_servlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ashwini";
    private static final String DB_USER = "rickroll";
    private static final String DB_PASSWORD = "onetwo";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE name=? AND password=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // User exists, redirect to profile.jsp
                    response.sendRedirect("profile.jsp");
                } else {
                    // User not found, display error message
                    out.println("<h2>User not found</h2>");
                }
            }
        } catch (SQLException e) {
            // Handle database errors
            e.printStackTrace();
            // Redirect to an error page or display an error message
            out.println("<h2>Database error</h2>");
        }
    }
}
