package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;

import static io.javalin.rendering.template.TemplateUtil.model;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
           UsersPage usersPage = new UsersPage(USERS);
            ctx.render("users/index.jte", model("usersPage", usersPage));
        });

        app.get("/users/{id}", ctx -> {
            long userId = Long.parseLong(ctx.pathParam("id"));
            User user = null;
            for (User u : USERS) {
                if (u.getId() == userId) {
                    user = u;
                    break;
                }
            }

            if (user != null) {
                UserPage userPage = new UserPage(user);
                ctx.render("users/show.jte", model("userPage", userPage));
            } else {
                ctx.status(404).result("User not found");
            }
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
