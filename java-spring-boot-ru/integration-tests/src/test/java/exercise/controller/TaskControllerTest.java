package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

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
    @Test
    public void testShow() throws Exception {
        String title = faker.lorem().sentence(3);
        String description = faker.lorem().sentence(6);

        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> title)
                .supply(Select.field(Task::getDescription), () -> description)
                .create();
        taskRepository.save(task);

        MvcResult mvcResult = mockMvc.perform(get("/tasks/" + task.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        assertThatJson(json).and(
                a -> a.node("title").isEqualTo(title),
                a -> a.node("description").isEqualTo(description)
        );
    }

    @Test
    public void testCreate() throws Exception {
        String title = faker.lorem().sentence(4);
        String description = faker.lorem().sentence(5);

        Task dto = new Task();
        dto.setTitle(title);
        dto.setDescription(description);

        MvcResult mvcResult = mockMvc.perform(
                        post("/tasks")
                                .contentType(APPLICATION_JSON)
                                .content(om.writeValueAsString(dto))
                )
                .andExpect(status().isCreated())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        assertThatJson(json).and(
                a -> a.node("title").isEqualTo(title),
                a -> a.node("description").isEqualTo(description)
        );
    }

    @Test
    public void testUpdate() throws Exception {
        String oldTitle  = faker.lorem().word();
        String oldDescription = faker.lorem().sentence(3);
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle),       () -> oldTitle)
                .supply(Select.field(Task::getDescription), () -> oldDescription)
                .create();
        taskRepository.save(task);

        String newTitle = faker.lorem().sentence(2);
        String newDescription = faker.lorem().sentence(4);
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("title", newTitle);
        updateData.put("description", newDescription);

        MvcResult mvcResult = mockMvc.perform(
                        put("/tasks/" + task.getId())
                                .contentType(APPLICATION_JSON)
                                .content(om.writeValueAsString(updateData))
                )
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        assertThatJson(json).and(
                a -> a.node("title").isEqualTo(newTitle),
                a -> a.node("description").isEqualTo(newDescription)
        );
    }

    @Test
    public void testDelete() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(3))
                .create();
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
    // END
}
