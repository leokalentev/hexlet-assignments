package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {
        int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        int pageSize = 5;

        var posts = PostRepository.findAll(page, pageSize);

        boolean hasLast = page > 1;
        boolean hasNext = posts.size() == pageSize;

        var postsPage = new PostsPage(posts, page, hasLast, hasNext);
        ctx.render("posts/index.jte", model("page", postsPage));
    }
    // END
}
