<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Игра - Текстовый квест</title>
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
            padding: 2rem;
        }
        .content-wrapper {
            max-width: 700px;
            margin: 0 auto;
        }
        .header {
            background: #2d3748;
            border: 2px solid #4a5568;
            border-radius: 8px;
            padding: 1.5rem;
            margin-bottom: 2rem;
        }
        h1 {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1.6em;
            font-weight: 600;
            margin-bottom: 0.8rem;
            color: #ffffff;
            text-align: center;
        }
        .player-info {
            font-family: "Arial", "Helvetica", sans-serif;
            color: #e2e8f0;
            font-size: 1em;
            text-align: center;
            font-weight: 500;
        }
        .stats {
            background: #4a5568;
            border: 2px solid #718096;
            border-radius: 8px;
            padding: 1.2rem 1.5rem;
            margin: 2rem 0;
            color: #e2e8f0;
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 0.95em;
            text-align: center;
        }
        .scene {
            background: #2d3748;
            border: 2px solid #4a5568;
            border-radius: 8px;
            padding: 1.5rem;
            margin-bottom: 2rem;
            text-align: left;
        }
        .scene p {
            margin-bottom: 1.2rem;
        }
        .scene p:last-child {
            margin-bottom: 0;
        }
        .options {
            display: flex;
            flex-direction: column;
            gap: 1rem;
            margin-bottom: 2rem;
        }
        .option-btn {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1em;
            background: #4a5568;
            color: #ffffff;
            border: 2px solid #718096;
            padding: 1.2rem 1.5rem;
            cursor: pointer;
            transition: all 0.2s ease;
            text-align: left;
            border-radius: 6px;
        }
        .option-btn:hover,
        .option-btn:focus {
            background: #5a6578;
            border-color: #e2e8f0;
            outline: none;
        }
        .game-over-message {
            background: #2d3748;
            border: 2px solid #4a5568;
            border-radius: 8px;
            padding: 1.2rem;
            margin-bottom: 1rem;
            text-align: center;
        }
        .game-over-message.win {
            border: 2px solid #48bb78;
            background: #2d4a3a;
        }
        .game-over-message.lose {
            border: 2px solid #f56565;
            background: #4a2d3a;
        }
        .game-over-message.neutral {
            border: 2px solid #e2e8f0;
        }
        .game-over-message h2 {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1.3em;
            font-weight: 600;
            margin: 0;
        }
        .game-over-message.win h2 {
            color: #48bb78;
        }
        .game-over-message.lose h2 {
            color: #f56565;
        }
        .game-over-message.neutral h2 {
            color: #e2e8f0;
        }
        .restart-btn {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1em;
            background: #4a5568;
            color: #ffffff;
            border: 2px solid #e2e8f0;
            padding: 1rem 1.5rem;
            cursor: pointer;
            transition: all 0.2s ease;
            border-radius: 6px;
            font-weight: 600;
            width: 100%;
            margin-bottom: 1.5rem;
        }
        .restart-btn:hover,
        .restart-btn:focus {
            background: #e2e8f0;
            color: #2d3748;
            outline: none;
        }
        .main-page-link {
            text-align: center;
            margin-top: 1.5rem;
            padding-top: 1.5rem;
            border-top: 2px solid #4a5568;
        }
        .main-page-btn {
            font-family: "Arial", "Helvetica", sans-serif;
            color: #a0aec0;
            text-decoration: none;
            transition: color 0.2s ease;
            font-size: 0.95em;
            padding: 0.4rem 0.8rem;
        }
        .main-page-btn:hover,
        .main-page-btn:focus {
            color: #e2e8f0;
            outline: none;
        }
    </style>
</head>
<body>
<div class="content-wrapper">
    <div class="header">
        <h1>Текстовый Квест</h1>
        <div class="player-info">Игрок: ${playerName}</div>
    </div>

    <div class="stats">
        <c:if test="${not empty gameStats}">
            Статистика:
            Игр: ${gameStats.totalGames} |
            Побед: ${gameStats.wins} |
            Поражений: ${gameStats.losses}
        </c:if>
    </div>

    <div class="scene">
        <p>${scene.text}</p>
    </div>

    <c:choose>
        <c:when test="${scene.gameOver}">
            <div class="game-over-message ${scene.endType == 'win' ? 'win' : scene.endType == 'lose' ? 'lose' : 'neutral'}">
                <c:choose>
                    <c:when test="${scene.endType == 'win'}">
                        <h2>Победа</h2>
                    </c:when>
                    <c:when test="${scene.endType == 'lose'}">
                        <h2>Поражение</h2>
                    </c:when>
                    <c:otherwise>
                        <h2>Игра завершена</h2>
                    </c:otherwise>
                </c:choose>
            </div>
            <form action="restart" method="post">
                <button type="submit" class="restart-btn">Начать заново</button>
            </form>
        </c:when>
        <c:otherwise>
            <form method="post" action="game" class="options">
                <c:forEach items="${scene.options}" var="option" varStatus="loop">
                    <button type="submit" name="selectedOption" value="${loop.index}" class="option-btn">
                            ${option.text}
                    </button>
                </c:forEach>
            </form>
        </c:otherwise>
    </c:choose>

    <div class="main-page-link">
        <a href="index.jsp" class="main-page-btn">На главную</a>
    </div>
</div>
</body>
</html>