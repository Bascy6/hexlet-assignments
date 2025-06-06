package exercise;

import exercise.dto.users.UsersPage;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import exercise.model.User;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.apache.commons.lang3.StringUtils;

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
            var term = ctx.queryParam("term");
            List<User> users;
            if (term == null) {
                users = USERS;
            } else {
                users = USERS
                        .stream()
                        .filter(u -> {
                            return StringUtils.startsWithIgnoreCase(u.getFirstName(), term);
                        })
                        .toList();
            }

            var page = new UsersPage(users, term);
            ctx.render("users/index.jte", model("page", page));

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