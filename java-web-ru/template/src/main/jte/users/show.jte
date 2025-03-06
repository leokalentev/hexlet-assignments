@import exercise.dto.users.UserPage
@param UserPage userPage
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Пользователь ${userPage.user.getFirstName()}</title>
</head>
<body>
    <h1>Данные пользователя</h1>
    <p><strong>ID:</strong> ${userPage.user.getId()}</p>
    <p><strong>Имя:</strong> ${userPage.user.getFirstName()}</p>
    <p><strong>Фамилия:</strong> ${userPage.user.getLastName()}</p>
    <p><strong>Email:</strong> ${userPage.user.getEmail()}</p>
    <p><a href="/users">Назад к списку пользователей</a></p>
</body>
</html>
