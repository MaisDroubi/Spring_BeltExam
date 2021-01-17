
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">

</head>
<body>

<div class="container">
    <h1> Welcome,<c:out value="${user.name}" /></h1>
    <a class="logout" href="/logout">Logout</a>
    <table class="table table-striped">

        <thead>
        <tr><h1>TV Shows</h1></tr>
        <tr>
            <td>Show</td>
            <td>Network</td>
            <td>avg Rating</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${shows}" var="show" varStatus="loop">
            <tr>
                <td><a href="/shows/${show.id}">${show.showName }</a></td>
                <td>${show.network}</td>
                <td>rating</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/shows/new" class="button">Add a Show</a>


</div>
</body>
</html>