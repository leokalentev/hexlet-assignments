@import exercise.dto.LoginPage
@param LoginPage page

<h1>Login</h1>

@if(page.getError() != null && !page.getError().isEmpty())
    <div>
        ${page.getError()}
    </div>
@endif

<form action="${exercise.util.NamedRoutes.loginPath()}" method="post">
    <div>
        <label for="name">Username</label>
        <input type="text" id="name" name="name" value="${page.getName()}" required>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
    </div>
    <button type="submit">Login</button>
</form>