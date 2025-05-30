package exercise.daytime;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void showMessage() {
        System.out.println(getName() + " Bean was created!");
    }
    // END
}
