<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.tictactoe.Sign" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>

<!DOCTYPE html>
<html>
<head>
    <link href="static/main.css" rel="stylesheet">

    <title>Tic-Tac-Toe</title>
</head>
<body>
<h1>Tic-Tac-Toe</h1>



<table>
    <tr>
        <td onclick="window.location='/logic?click=0'">${data.get(0).getSign()}</td>
        <td onclick="window.location='/logic?click=1'">${data.get(1).getSign()}</td>
        <td onclick="window.location='/logic?click=2'">${data.get(2).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=3'">${data.get(3).getSign()}</td>
        <td onclick="window.location='/logic?click=4'">${data.get(4).getSign()}</td>
        <td onclick="window.location='/logic?click=5'">${data.get(5).getSign()}</td>
    </tr>
    <tr>
        <td onclick="window.location='/logic?click=6'">${data.get(6).getSign()}</td>
        <td onclick="window.location='/logic?click=7'">${data.get(7).getSign()}</td>
        <td onclick="window.location='/logic?click=8'">${data.get(8).getSign()}</td>
    </tr>
</table>
    <hr>
    <c:if test = "${winner == Sign.CROSS}">
        <p>Игрок победил. Отличная работа</p>
        <button class="restart-button" onclick="restart()">Сыграем ещё раз?</button>
    </c:if>
<c:if test="${winner == Sign.NOUGHT}">
        <p>Победил компьютер. Отличная работа! Компьютера...</p>
        <button class="restart-button" onclick="restart()">Сыграем ещё раз?</button>

    </c:if>
<c:if test="${draw == true}">
        <p>Ничья!</p>
        <button class="restart-button" onclick="restart()">Сыграем ещё раз?</button>
    </c:if>


<script>
    function restart() {
        $.ajax({
            url: '/restart',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            async: false,
            success: function () {
                location.reload();
            }
        });
    }
</script>

</body>
</html>