<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Show</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer
            src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"></script>
</head>
<body>
<div class="container">
    <h1>create a new show</h1>
    <section>
        <div class="columns">
            <div class="column">
                <form:form method="POST" action="/shows/new" modelAttribute="show">
                            <form:label path="showName">Title:</form:label>
                            <form:input path="showName" class="input" /><br>
                            <form:label path="network">Network:</form:label>
                            <form:input path="network" class="input" /><br>
                        <input type="submit" value="Create" class="button" />
                </form:form>
            </div>
            <div class="column"></div>
        </div>
    </section>
</div>
</body>
</html>
