package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<TaskDTO> index() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> dto = tasks.stream()
                .map(t -> taskMapper.map(t)).toList();
        return dto;
    }

    @GetMapping("/{id}")
    public TaskDTO show(@PathVariable Long id) {
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found"));
        TaskDTO dto = taskMapper.map(foundTask);
        return dto;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
        Task task = taskMapper.map(taskData);
        taskRepository.save(task);
        TaskDTO dto = taskMapper.map(task);
        return dto;
    }

    @PutMapping("/{id}")
    public TaskDTO update(@Valid @RequestBody TaskUpdateDTO taskData, @PathVariable Long id) {
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found"));
        taskMapper.update(taskData, foundTask);
        var user = userRepository.findById(taskData.getAssigneeId()).get();
        foundTask.setAssignee(user);
        taskRepository.save(foundTask);
        TaskDTO dto = taskMapper.map(foundTask);
        return dto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
    // END
}
