<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.1/css/bulma.min.css">
</head>
<body>
<div class="container">
        <p>Title  :<c:out value="${show.showName}" /></p><br>
        <p>Network :<c:out value="${show.network}" /></p>
    <section>
        <div class="columns">
            <div class="column">
                    <tr>
                        <td class="subtitle">Rating:</td>
                    </tr>
                    <c:if test="${show.creator.getId()==currentUserId}">
                        <tr>
                            <td><a class="button" href="/shows/${show.id}/edit">Edit</a></td>
                            <td><a class="button" href="/shows/${show.id}/delete">Delete</a></td>
                        </tr>
                    </c:if>
                </table>


            <div class="column"></div>
        </div>

    </section>
</div>
</body>
</html>