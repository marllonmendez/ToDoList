package br.com.marllonmendez.todolist.controller;

import br.com.marllonmendez.todolist.model.TaskModel;
import br.com.marllonmendez.todolist.repository.ITaskRepository;
import br.com.marllonmendez.todolist.utils.NullProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("As datas de inicio e término devem ser maiores do que a atual");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser menor do que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var listTasks = this.taskRepository.findByIdUser((UUID) idUser);
        return listTasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id) {
        Optional<TaskModel> idTask = this.taskRepository.findById(id);
        if (idTask.isPresent()) {
            TaskModel task = idTask.get();
            TaskModel updatedTask = this.taskRepository.save(task);
            NullProperties.copyNotNullProperties(taskModel, task);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task inexistente!");
        }
    }
}



