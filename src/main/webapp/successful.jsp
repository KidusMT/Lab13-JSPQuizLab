<%@ page import="miu.kmt.Quiz" %><%--
  Created by IntelliJ IDEA.
  User: KidusMT
  Date: 5/8/2021
  Time: 5:53 PM
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
        <%--  SCRIPTLETS  --%>
        <%
            Quiz quiz = (Quiz) session.getAttribute("quiz");
        %>
        <%--  EXPRESSIONS  --%>
        <%=
        "<p>You have completed the Number Quiz, with a score of " + quiz.getScore() + " out of 5.</p>"
        %>
        <%--  SCRIPTLETS  --%>
        <%
            session.removeAttribute("quiz");
            session.setAttribute("current_question", 0);
        %>
    </div>
</body>
</html>
