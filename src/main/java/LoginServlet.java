import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jdbc.JDBCManger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    private final JDBCManger jdbcManger = new JDBCManger();
    private final LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            jdbcManger.loadDrive();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        if (username.equalsIgnoreCase("admin1")) {
            boolean isAuthenticated;
            try {
                isAuthenticated = service.authenticateUser(username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (isAuthenticated) {
                HttpSession session = req.getSession();
                session.setAttribute("role", "admin");
                req.setAttribute("name", session.getAttribute("role"));
                req.getRequestDispatcher("admin.jsp").forward(req, resp);
            } else {
                req.setAttribute("errorMessage", "Incorrect username or password!!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else {
            boolean isAuthenticated;
            try {
                isAuthenticated = service.authenticateUser(username, service.hashPassword(password));
            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            if (isAuthenticated) {
                String role;
                int loginID;
                try {
                    role = service.getRole(username, service.hashPassword(password));
                    loginID = service.getID(username, service.hashPassword(password));
                } catch (SQLException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                if (role.equalsIgnoreCase("student")) {
                    req.setAttribute("name", role);
                    int id;
                    try {
                        id = getUserID(loginID);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    HttpSession session= req.getSession();
                    session.setAttribute("stID",id);

                    req.getRequestDispatcher("student.jsp").forward(req, resp);
                } else {
                    int id;
                    try {
                        id = getID(loginID);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    HttpSession session= req.getSession();
                    session.setAttribute("instructorID",id);

                    req.getRequestDispatcher("instructor.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("errorMessage", "Incorrect username or password!!");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }

    }

    public Integer getUserID(int loginID) throws SQLException {
        String query = "SELECT userID FROM user WHERE loginID = ?";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, loginID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("userID");
    }
    public Integer getID(int loginID) throws SQLException {
        String query = "SELECT instructorID FROM instructor WHERE loginID = ?";
        PreparedStatement statement = jdbcManger.connect().prepareStatement(query);
        statement.setInt(1, loginID);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("instructorID");

    }
}
