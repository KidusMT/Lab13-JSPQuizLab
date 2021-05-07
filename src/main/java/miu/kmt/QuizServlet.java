package miu.kmt;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author KidusMT
 * Date: 5/7/2021
 */
@WebServlet({"/", "/answer"})
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();

        String answer = req.getParameter("ans");
        Cookie current_question = new Cookie("current_question", "0");
        current_question.setMaxAge(15 * 60 * 24);// 15 min
        resp.addCookie(current_question);

        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Quiz App</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div style='align-content: center'>");
        out.println("<h1>The Number Quiz</h1>");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("current_question".equals(cookie.getName())) {
                    // current question number
                    int curQst = Integer.parseInt(cookie.getValue());
                    if (curQst == 0) {// if first question
                        out.println("<p>Your current score is " + Quiz.getScore() + ".</p>" +
                                "<p>Guess the next number in the sequence.</p>" +
                                "<form action='/Lab12_ServletQuizLab_war_exploded/answer' method='get'>" +
                                "<div style='display: inline-flex; flex-direction: column'>" +
                                "<p>" + Quiz.getQuestion(0) + "</p>" +
                                "<label for='answer'>Your answer: <input id='answer' name='ans' width='250px' type='text'/></label><br>" +
                                "<input style='padding: 10px; margin: 20px 0; width: 100px' type='submit'>" + "</div>" +
                                "</form>"
                        );
                        current_question.setValue(String.valueOf(curQst+1));
                    } else {
                        if (curQst < Quiz.getAllQuestions().length) { // if not last question
                            int nextQuestion = curQst + 1;
                            if (answer != null && !answer.isEmpty()) {
                                Quiz.checkAnswer(Integer.parseInt(answer), curQst-1);
                            }
                            out.println("<p>Your current score is " + Quiz.getScore() + ".</p>" +
                                    "<p>Guess the next number in the sequence.</p>" +
                                    "<form action='/Lab12_ServletQuizLab_war_exploded/answer' method='get'>" +
                                    "<div style='display: inline-flex; flex-direction: column'>" +
                                    "<p>" + Quiz.getQuestion(curQst) + "</p>" +
                                    "<label for='answer'>Your answer: <input id='answer' name='ans' width='250px' type='text'/></label><br>" +
                                    "<input style='padding: 10px; margin: 20px 0; width: 100px' type='submit'>" + "</div>" +
                                    "</form>"
                            );
                            current_question.setValue(String.valueOf(nextQuestion));
                        } else {
                            out.println("<p>Your current score is " + Quiz.getScore() + ".</p>" +
                                    "<p>You have completed the Number Quiz, with a score of " + Quiz.getScore() +
                                    " out of " + curQst + ".</p>");
                            current_question.setValue("0");
                            Quiz.setScore(0);
                        }
                    }
                    resp.addCookie(current_question);
                }
            }
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
