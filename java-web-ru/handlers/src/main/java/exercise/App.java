package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public final class App {

    public static Javalin getApp() {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // Обработчик GET /phones
        app.get("/phones", ctx -> {
            List<String> phones = Data.getPhones();
            ctx.json(phones);
        });

        // Обработчик GET /domains
        app.get("/domains", ctx -> {
            List<String> domains = Data.getDomains();
            ctx.json(domains);
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}