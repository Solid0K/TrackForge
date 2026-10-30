package com.krishu.orchestrator.Controller;

import com.krishu.orchestrator.DTO.TaskCreationRequest;
import com.krishu.orchestrator.DTO.TaskResponse;
import com.krishu.orchestrator.DTO.UpdateTaskRequest;
import com.krishu.orchestrator.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskCreationRequest request){
        return ResponseEntity.ok(taskService.createTask(request));
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/getAllTask")
    public ResponseEntity<List<TaskResponse>> getAllTask(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PatchMapping("/updateTask/{id}")
    public ResponseEntity<TaskResponse> updateTaskStatusAndResult(@PathVariable UUID id,
                                                                  @RequestBody UpdateTaskRequest request){
        return ResponseEntity.ok(taskService.updateTaskStatus(id,request));
    }

    @PostMapping("/takeTask")
    public ResponseEntity<TaskResponse> agentTakeTask(){
        return taskService.takeTask().map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.noContent().build());
    }
}
