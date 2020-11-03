import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    public static Map<String, String> users = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (users.get(login) == null) {
            users.put(login, password);
            hs.setAttribute("res", new int[4]);
        }

        if (password.equals(users.get(login))) {

            hs.setAttribute("user_login", login);
        }

        resp.sendRedirect("/index.jsp");
    }
}
