@import gg.jte.Content
@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<form action="/users" method="post">
    <fieldset>
        <legend>Данные о пользователе</legend>
        <label>
            Имя
            <input type="text" name="firstName" placeholder="Введите ваше имя">
        </label>
        <br>

        <label>
            Фамилия
            <input type="text" name="lastName" placeholder="Введите вашу фамилию">
        </label>
        <br>
        <label>
            Электронная почта
            <input type="text" name="email" placeholder="Введите вашу почту">
        </label>
        <br>
        <label>
            Пароль
            <input type="text" name="password" placeholder="Введите пароль">
        </label>
        <br>
    </fieldset>

    <button>Создать пользователя</button>
</form>>
</body>
</html>