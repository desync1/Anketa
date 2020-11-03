<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<% String login = (String)session.getAttribute("user_login"); %>

<% if (login==null || "".equals(login)){ %>
<form action="/login" method="POST">
  <p>Login: <input type="text" name="login"></p>
  <p>Password: <input type="password" name="password"></p>
  <input type="submit" name="login">
</form>
<% } else { %>
<form action="/questions" method="POST">
  <p>Do you like Java?</p>
  <p><input type="radio" name="question1" value="yes">Yes</p>
  <p><input type="radio" name="question1" value="no">No</p>
  <p>Do you like Python?</p>
  <p><input type="radio" name="question2" value="yes">Yes</p>
  <p><input type="radio" name="question2" value="no">No</p>
  <p><input type="submit"></p>
</form>
<p>Click this link to <a href="/questions?a=exit">logout</a></p>
<% } %>
</body>
</html>