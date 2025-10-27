<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Текстовый квест</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: "Georgia", "Times New Roman", serif;
            font-size: 17px;
            line-height: 1.6;
            background: #1a1f2c;
            color: #ffffff;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }
        .container {
            max-width: 700px;
            width: 100%;
            background: #2d3748;
            border: 2px solid #4a5568;
            border-radius: 8px;
            padding: 2.5rem;
            text-align: center;
        }
        h1 {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 2em;
            font-weight: 600;
            margin-bottom: 2rem;
            color: #ffffff;
        }
        .btn-container {
            display: flex;
            flex-direction: column;
            gap: 1rem;
            max-width: 350px;
            margin: 0 auto;
        }
        .start-btn, .continue-btn {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1.1em;
            padding: 1.2rem 1.8rem;
            cursor: pointer;
            transition: all 0.2s ease;
            border-radius: 6px;
            font-weight: 600;
            border: 2px solid;
            width: 100%;
        }
        .start-btn {
            background: #4a5568;
            color: #ffffff;
            border-color: #e2e8f0;
        }
        .start-btn:hover,
        .start-btn:focus {
            background: #e2e8f0;
            color: #2d3748;
            outline: none;
        }
        .continue-btn {
            background: #4a5568;
            color: #e2e8f0;
            border-color: #718096;
        }
        .continue-btn:hover,
        .continue-btn:focus {
            background: #718096;
            border-color: #e2e8f0;
            color: #ffffff;
            outline: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Текстовый Квест</h1>
    <div class="btn-container">
        <form method="get" action="start">
            <button type="submit" class="start-btn">Новая игра</button>
        </form>

        <c:if test="${not empty sessionScope.playerName}">
            <form method="get" action="continue">
                <button type="submit" class="continue-btn">Продолжить</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>