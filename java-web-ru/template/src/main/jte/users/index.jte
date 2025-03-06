@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage usersPage
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список пользователей</title>
</head>
<body>
    <h1>Список пользователей</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Действие</th>
            </tr>
        </thead>
        <tbody>
@for(User user : usersPage.getUsers()) {
    <tr>
        <td>${user.getId()}</td>
        <td>${user.getFirstName()}</td>
        <td>${user.getLastName()}</td>
        <td><a href="/users/${user.getId()}">Просмотр</a></td>
    </tr>
}
@endfor
        </tbody>
    </table>
</body>
</html>
