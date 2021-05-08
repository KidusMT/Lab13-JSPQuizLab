<%@ page import="miu.kmt.Quiz" %>
<%--
  Created by IntelliJ IDEA.
  User: KidusMT
  Date: 5/7/2021
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Quiz App</title>
</head>

<body>
<div style="align-content: center">
    <%@ include file="header.jsp" %>

    <%--  DECLARATIONS  --%>
    <%!
        Quiz quiz;
        int current_question = 0;
    %>

    <%--  SCRIPTLETS  --%>
    <%
        String answer = request.getParameter("ans");

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
        }

        if (current_question >= quiz.getAllQuestions().length) {

    %>

    <%--  EXPRESSIONS  --%>
    <%=
    "<p>You have completed the Number Quiz, with a score of " + quiz.getScore() + " out of 5.</p>"
    %>
    <%--  SCRIPTLETS  --%>
    <%
        session.removeAttribute("quiz");
        session.setAttribute("current_question", 0);
    } else {
    %>
    <form action="/QuizServletLab_war_exploded/answer" method="get">
        <div style="display: inline-flex; flex-direction: column">
            <%--  SCRIPTLETS  --%>
            <%
                if (current_question < quiz.getAllQuestions().length) {
            %>
            <%--  EXPRESSIONS  --%>
            <%=
            "<p>Your current score is " + quiz.getScore() + ".</p>" +
                    "<p>Guess the next number in the sequence.</p>" +
                    quiz.getQuestion(current_question)
            %>
            <br>
            <%--  SCRIPTLETS  --%>
            <%
                    session.setAttribute("current_question", current_question + 1);
                }
            %>

            <label for="answer">Your answer: <input id="answer" name="ans" width="250px" type="text"></label>
            <br>
            <input style="padding: 10px; margin: 20px 0; width: 100px" type="submit">
        </div>
    </form>
    <%--  SCRIPTLETS  --%>
    <%
        }
    %>
</div>

</body>

</html>
