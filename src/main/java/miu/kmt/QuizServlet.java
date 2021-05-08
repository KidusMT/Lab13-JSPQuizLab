package miu.kmt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author KidusMT
 * Date: 5/7/2021
 */
@WebServlet("/answer")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Quiz quiz;
        int current_question = 0;
        String answer = req.getParameter("ans");
        HttpSession session = req.getSession();

        if (session.getAttribute("quiz") == null) {
            quiz = new Quiz();
        } else {
            quiz = (Quiz) session.getAttribute("quiz");
            if (answer != null && !answer.isEmpty() && session.getAttribute("current_question") != null) {
                quiz.checkAnswer(Integer.parseInt(answer), (Integer) session.getAttribute("current_question") - 1);
            }
        }
        session.setAttribute("quiz", quiz);

        if (session.getAttribute("current_question") != null) {
            current_question = (Integer) session.getAttribute("current_question");
        }else{
            session.setAttribute("current_question", 0);
        }

        // forwarding to jsp page
        if (current_question >= quiz.getAllQuestions().length) {
            req.getRequestDispatcher("successful.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
