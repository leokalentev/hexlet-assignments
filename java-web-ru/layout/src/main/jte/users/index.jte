@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template("layout/page.jte", title = "Список пользователей")

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
@for (User user : page.getUsers())
    <tr>
        <td>${user.getId()}</td>
        <td>${user.getFirstName()}</td>
        <td>${user.getLastName()}</td>
        <td><a href="/users/${user.getId()}">Просмотр</a></td>
    </tr>
@endfor
    </tbody>
</table>

