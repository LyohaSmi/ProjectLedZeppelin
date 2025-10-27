<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пролог - Текстовый квест</title>
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
            align-items: flex-start;
            padding: 2rem;
        }
        .container {
            max-width: 700px;
            width: 100%;
            background: #2d3748;
            border: 2px solid #4a5568;
            border-radius: 8px;
            padding: 2.5rem;
        }
        h1 {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1.6em;
            font-weight: 600;
            margin-bottom: 2rem;
            color: #ffffff;
            text-align: center;
            border-bottom: 2px solid #4a5568;
            padding-bottom: 1.2rem;
        }
        .story-text {
            margin-bottom: 2rem;
            text-align: left;
            background: #4a5568;
            padding: 1.5rem;
            border-radius: 6px;
            border-left: 4px solid #e2e8f0;
        }
        .story-text p {
            margin-bottom: 1.2rem;
        }
        .story-text p:last-child {
            margin-bottom: 0;
        }
        .form-container {
            border-top: 2px solid #4a5568;
            padding-top: 2rem;
        }
        input[type="text"] {
            width: 100%;
            font-family: "Georgia", "Times New Roman", serif;
            font-size: 1em;
            background: #4a5568;
            color: #ffffff;
            border: 2px solid #718096;
            border-radius: 6px;
            padding: 1rem 1.2rem;
            margin-bottom: 1.2rem;
            transition: all 0.2s ease;
        }
        input[type="text"]:focus {
            outline: 2px solid #e2e8f0;
            border-color: #e2e8f0;
            background: #5a6578;
        }
        input[type="text"]::placeholder {
            color: #a0aec0;
        }
        .submit-btn {
            font-family: "Arial", "Helvetica", sans-serif;
            font-size: 1.1em;
            background: #4a5568;
            color: #ffffff;
            border: 2px solid #e2e8f0;
            padding: 1.2rem 1.8rem;
            cursor: pointer;
            transition: all 0.2s ease;
            width: 100%;
            text-align: center;
            border-radius: 6px;
            font-weight: 600;
        }
        .submit-btn:hover,
        .submit-btn:focus {
            background: #e2e8f0;
            color: #2d3748;
            outline: none;
        }
        .back-link {
            text-align: center;
            margin-top: 2rem;
            padding-top: 1.5rem;
            border-top: 2px solid #4a5568;
        }
        .back-btn {
            font-family: "Arial", "Helvetica", sans-serif;
            color: #a0aec0;
            text-decoration: none;
            transition: color 0.2s ease;
            font-size: 0.95em;
            padding: 0.4rem 0.8rem;
        }
        .back-btn:hover,
        .back-btn:focus {
            color: #e2e8f0;
            outline: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Пролог</h1>
    <div class="story-text">
        <p>Вы — Логан, вор-призрак, для которого не существовало неприступных замков. Ваша жизнь — это шепот на ветру, скользящая тень, чья добыча исчезает так же бесшумно, как и появляется. Но ваша удача, как выяснилось, имела свой предел. Этим пределом стал древний свиток «Последний Шепот», украденный из коллекции одного сумасбродного аристократа. Предупреждения о «проклятии» вы тогда счел сказками для простаков.</p>
        <p></p>
        <p>Теперь вы знаете, что были неправы.</p>
        <p></p>
        <p>Ваша собственная тень стала вашим личным адом. Она шепчет вам проклятия, когда вы пытаетесь уснуть, тянется к ножу на столе, когда вы отворачиваетесь, и смотрит на вас пустыми глазами, которых у нее быть не должно. Она хочет вас задушить, завладеть вашей плотью, стать вами. Единственный луч надежды — старый учёный Альбас, изучавший подобные аномалии. Его заброшенная усадьба на окраине города — ваш последний шанс исправить ошибку, пока тень не поглотила вас окончательно. Вы стоите перед её мрачным фасадом. Ночь холодна, и ваша тень на стене извивается в такт вашему учащённому сердцебиению.</p>
    </div>

    <div class="form-container">
        <form method="get" action="game">
            <input type="text" name="playerName" placeholder="Username" required>
            <button type="submit" class="submit-btn">Начать путешествие</button>
        </form>
    </div>

    <div class="back-link">
        <a href="index.jsp" class="back-btn">Вернуться на главную</a>
    </div>
</div>
</body>
</html>