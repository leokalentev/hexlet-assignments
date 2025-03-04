package exercise;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            int per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            int start = (page - 1) * per;
            int end = Math.min(start + per, USERS.size());

            if (start >= USERS.size()) {
                ctx.json(Collections.emptyList());
                return;
            }

            List<Map<String, String>> paginatedUsers = USERS.subList(start, end);
            ctx.json(paginatedUsers);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
