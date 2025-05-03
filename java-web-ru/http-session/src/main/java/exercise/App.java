package exercise;

import io.javalin.Javalin;
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
            // Получаем параметры запроса с значениями по умолчанию
            int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            int per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            // Вычисляем индексы для выборки
            int fromIndex = (page - 1) * per;
            if (fromIndex >= USERS.size()) {
                fromIndex = 0; // если страница за пределами, возвращаем первую страницу
                per = 5;
            }

            int toIndex = Math.min(fromIndex + per, USERS.size());

            // Получаем подсписок пользователей для текущей страницы
            List<Map<String, String>> paginatedUsers = USERS.subList(fromIndex, toIndex);

            // Отправляем ответ в формате JSON
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
