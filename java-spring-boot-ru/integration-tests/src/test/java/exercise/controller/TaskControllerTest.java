package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    private Task createTaskForTest() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(2))
                .create();
    }

    @Test
    public void testShowCorrect() throws Exception {
        var task = createTaskForTest();
        taskRepository.save(task);
        var request = get("/tasks/" + task.getId());
        var result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(value -> value.node("title").isEqualTo(task.getTitle())
                , value -> value.node("description").isEqualTo(task.getDescription()));
    }

    @Test
    public void testShowIncorrect() throws Exception {
        var request = get("/tasks/100");
        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    public void testCreate() throws Exception {
        var task = createTaskForTest();
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        mockMvc.perform(request)
                .andExpect(status().isCreated());
        var foundTask = taskRepository.findByTitle(task.getTitle()).get();
        assertThat(foundTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(foundTask.getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    public void testUpdateCorrect() throws Exception {
        var task = createTaskForTest();
        taskRepository.save(task);
        var editedTaskContent = new HashMap<>();
        editedTaskContent.put("title", "edited task title");
        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(editedTaskContent));
        mockMvc.perform(request).andExpect(status().isOk());
        var foundTask = taskRepository.findById(task.getId()).get();
        assertThat(foundTask.getTitle().equals(editedTaskContent.get("title")));
    }

    @Test
    public void testUpdateIncorrect() throws Exception {
        var task = createTaskForTest();
        taskRepository.save(task);
        var taskContent = new HashMap<>();
        taskContent.put("title", "edited task title");
        var request = put("/tasks/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskContent));
        mockMvc.perform(request).andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCorrect() throws Exception {
        var task = createTaskForTest();
        taskRepository.save(task);
        var request = delete("/tasks/{id}", task.getId());
        mockMvc.perform(request).andExpect(status().isOk());
        task = taskRepository.findById(task.getId()).orElse(null);
        assertThat(task).isNull();
    }
    // END
}
