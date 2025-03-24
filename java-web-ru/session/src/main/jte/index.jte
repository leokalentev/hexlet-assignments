@import exercise.dto.MainPage
@param MainPage page

<h1>Welcome to the Authentication System</h1>

@if(page.getName() == null)
    <a href="${exercise.util.NamedRoutes.buildSessionPath()}">Войти</a>
@else
    <div>
        <p>Вы вошли как: ${String.valueOf(page.getName())}</p>
        <form action="${exercise.util.NamedRoutes.logoutPath()}" method="post">
            <button type="submit">Выйти</button>
        </form>
    </div>
@endif