import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionsServlet extends HttpServlet {

    static final String TEMPLATE = "<html>" +
            "<head><Title>Statistic</title></head>" +
            "<body>%s</body></html>";
    static final int QUESTION_1_YES = 0;
    static final int QUESTION_1_NO = 1;
    static final int QUESTION_2_YES = 2;
    static final int QUESTION_2_NO = 3;
    final int[] results = new int[4];


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answ1 = req.getParameter("question1");
        String answ2 = req.getParameter("question2");
        HttpSession hs = req.getSession();
        int[] userRes = (int[]) hs.getAttribute("res");
        int idx1 = "yes".equals(answ1) ? QUESTION_1_YES : QUESTION_1_NO;
        int idx2 = "yes".equals(answ2) ? QUESTION_2_YES : QUESTION_2_NO;


        userRes[idx1]++;
        userRes[idx2]++;
        results[idx1]++;
        results[idx2]++;
        hs.setAttribute("res", userRes);

        String resGlobal = "<table border = 1>" +
                "<caption>Global Statistic</caption>" +
                "<tr>" +
                "<th>Questions</th>" +
                "<th>Answers</th>" +
                "</tr>" +
                "<tr>" +
                "<td>Question 1 Yes</td>" +
                "<td>" + results[QUESTION_1_YES] + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td>Question 1 No</td>" +
                "<td>" + results[QUESTION_1_NO] + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td>Question 2 Yes</td>" +
                "<td>" + results[QUESTION_2_YES] + "</td>" +
                "</tr>" +
                "<tr>" +
                "<tr>" +
                "<td>Question 2 No</td>" +
                "<td>" + results[QUESTION_2_NO] + "</td>" +
                "</tr>" +
                "</table>";

        String resUser = "<table border = 1>" +
                "<caption>User Statistic</caption>" +
                "<tr>" +
                "<th>Questions</th>" +
                "<th>Answers</th>" +
                "</tr>" +
                "<tr>" +
                "<td>Question 1 Yes</td>" +
                "<td>" + userRes[QUESTION_1_YES] + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td>Question 1 No</td>" +
                "<td>" + userRes[QUESTION_1_NO] + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td>Question 2 Yes</td>" +
                "<td>" + userRes[QUESTION_2_YES] + "</td>" +
                "</tr>" +
                "<tr>" +
                "<tr>" +
                "<td>Question 2 No</td>" +
                "<td>" + userRes[QUESTION_2_NO] + "</td>" +
                "</tr>" +
                "</table>";


        PrintWriter pw = resp.getWriter();
        pw.write(String.format(TEMPLATE, resUser));
        pw.write(String.format(TEMPLATE, resGlobal));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a = req.getParameter("a");
        HttpSession hs = req.getSession(false);

        if ("exit".equals(a) && (hs != null)) {
            hs.removeAttribute("user_login");
        }
        resp.sendRedirect("/index.jsp");
    }
}
