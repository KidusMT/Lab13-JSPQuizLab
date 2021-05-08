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
    <%
        String answer = request.getParameter("ans");

        session.setAttribute("current_question", 0);
        Quiz quiz;
        if (session.getAttribute("quiz") == null) {
            quiz = new Quiz();
        } else {
            quiz = (Quiz) session.getAttribute("quiz");
            if (answer != null && !answer.isEmpty() && session.getAttribute("current_question") != null) {
                quiz.checkAnswer(Integer.parseInt(answer), (Integer) session.getAttribute("current_question"));
            }
        }
        session.setAttribute("quiz", quiz);

        int current_question = 0;
        if (session.getAttribute("current_question") != null) {
            current_question = (Integer) session.getAttribute("current_question");
        }
    %>

    <%
        if (current_question >= quiz.getAllQuestions().length) {
            if (answer != null && !answer.isEmpty() && current_question > 0) {
                quiz.checkAnswer(Integer.parseInt(answer), current_question - 1);
            }
    %>
    <p>You have completed the Number Quiz, with a score of 0 out of 5.</p>
    <%
        }
        ;
    %>

    <p>Guess the next number in the sequence.</p>
    <form action="/Lab12_ServletQuizLab_war_exploded/answer" method="post">
        <div style="display: inline-flex; flex-direction: column">
            <p>1, 4, 9, 16, 25</p>
            <label for="answer">Your answer: <input id="answer" name="ans" width="250px" type="text"></label>
            <br>
            <input style="padding: 10px; margin: 20px 0; width: 100px" type="submit">
        </div>
    </form>

</div>


</body>

</html>
