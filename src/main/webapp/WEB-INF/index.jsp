<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Belt Exam</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
</head>
<body>
<div class="container">
    <section>
        <div class="columns">
            <div class="column">
                <h3 class="title">Register</h3>
                <p><form:errors path="user.*" /></p>
                <form:form method="POST" action="/registration" modelAttribute="user">
                    <form:label path="name">Name:</form:label>
                    <form:input path="name" class="input" />
                    <form:label path="email">Email:</form:label>
                    <form:input path="email" class="input" />
                    <form:label path="password">password:</form:label>
                    <form:input path="password" type="password" class="input" />
                    <form:label path="passwordConfirmation">password Confirmation:</form:label>
                    <form:input path="passwordConfirmation" type="password" class="input" /><br><br>
                    <input type="submit" value="Register" class="button" />
                </form:form>
            </div>
            <div class="column">
                <h3 class="title">Login</h3>
                <p><c:out value="${error}" /></p>
                <form method="post" action="/login">
                    <label type="email" for="email">Email</label>
                    <input type="text" id="email" name="email" class="input" />
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="input" /><br><br>
                    <input type="submit" value="Login" class="button" />
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>