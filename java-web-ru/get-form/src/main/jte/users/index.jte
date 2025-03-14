@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template("layout/page.jte") {
    <h1>Список пользователей</h1>
    <form action="/users" method="get">
    <input type="text" name="term" value="${page.getTerm()}" placeholder="Введите имя пользователя">
    <button type="submit">Фильтровать</button>
    </form>

    @if (!page.getUsers().isEmpty()) {
        <ul>
            @for (User user : page.getUsers()) {
                <li>${user.getFirstName()} ${user.getLastName()}</li>
            }
            @endfor
        </ul>
    }
    @endif
}