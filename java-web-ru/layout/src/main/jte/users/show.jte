@import exercise.dto.users.UserPage
@import exercise.model.User
@param UserPage page

@template("layout/page.jte", title = "Профиль пользователя")

<h1>Данные пользователя</h1>
    <p><strong>ID:</strong> ${page.getUser().getId()}</p>
    <p><strong>Имя:</strong> ${page.getUser().getFirstName()}</p>
    <p><strong>Фамилия:</strong> ${page.getUser().getLastName()}</p>
    <p><strong>Email:</strong> ${page.getUser().getEmail()}</p>
    <p><a href="/users">Назад к списку пользователей</a></p>

<a href="/users" class="btn btn-primary">Назад к списку пользователей</a>
