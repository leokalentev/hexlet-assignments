package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        var page = new LoginPage("", "");
        ctx.render("build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var name = ctx.formParam("name");
        var password = ctx.formParam("password");

        var userOptional = UsersRepository.findByName(name);

        if (userOptional.isPresent() && userOptional.get().getPassword().equals(encrypt(password))) {
            ctx.sessionAttribute("userId", userOptional.get().getId());
            ctx.redirect(NamedRoutes.rootPath());
        } else {
            var page = new LoginPage(name, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        }
    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("userId", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void index(Context ctx) {
        Long userId = ctx.sessionAttribute("userId");
        String userName = null;

        if (userId != null) {
            var userOpt = UsersRepository.find(userId);
            if (userOpt.isPresent()) {
                userName = userOpt.get().getName();
            }
        }

        var page = new MainPage(userName);
        ctx.render("index.jte", model("page", page));
    }
    // END
}
