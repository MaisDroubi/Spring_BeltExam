<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Shows</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
    <script defer
            src="https://use.fontawesome.com/releases/v5.1.0/js/all.js"></script>
</head>
<body>
<div class="container">
    <h1>Edit a show</h1>
        <div class="columns">
            <div class="column">

                <form:form method="POST" action="/shows/${id}/edit" modelAttribute="editshow">
                    <form:input type="hidden" path="id"></form:input>
                            <form:label path="showName">Title:</form:label>
                            <form:input path="showName" class="input"/>
                            <form:errors path="showName"/>
                            <form:label path="network">Network:</form:label>
                            <form:input path="network" class="input"/>
                            <form:errors path="network"/>
                    <form:input type="hidden" path="creator" value="${creator.id}"/>
                    <div class="buttons">
                        <input type="submit" value="Edit" class="button" />
                    </div>
                </form:form>
            </div>
            <div class="column"></div>
        </div>
</div>
</body>
</html>