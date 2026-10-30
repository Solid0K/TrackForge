package com.krishu.orchestrator.Service;

import com.krishu.orchestrator.DTO.TaskCreationRequest;
import com.krishu.orchestrator.DTO.TaskResponse;
import com.krishu.orchestrator.DTO.UpdateTaskRequest;
import com.krishu.orchestrator.Model.Task;
import com.krishu.orchestrator.Repository.TaskRepo;
import com.krishu.orchestrator.TaskStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo){
        this.taskRepo=taskRepo;
    }

    @Transactional
    public TaskResponse createTask(TaskCreationRequest request){
        Task task=new Task(request.getDescription());
        Task savedTask=taskRepo.save(task);
        return mapToTaskResponse(savedTask);
    }

    @Transactional(readOnly=true)
    public TaskResponse getTaskById(UUID id){
        Task task=taskRepo.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found"));
        return mapToTaskResponse(task);
    }

    @Transactional(readOnly=true)
    public List<TaskResponse> getAllTasks(){
        List<Task> tasks=taskRepo.findAll();
        return tasks.stream().map(this::mapToTaskResponse).toList();
    }

    @Transactional
    public TaskResponse updateTaskStatus(UUID id, UpdateTaskRequest request){
        Task task=taskRepo.findById(id).orElseThrow(()
                ->new ResponseStatusException(HttpStatus.NOT_FOUND,"task not found"));
        validateStatusTransition(task.getTaskStatus(),request.getTaskStatus());
        task.setTaskStatus(request.getTaskStatus());
        task.setResult(request.getResult());
        Task savedTask=taskRepo.save(task);
        return mapToTaskResponse(savedTask);
    }

    @Transactional
    public Optional<TaskResponse> takeTask(){
        Optional<Task> optionalTask=taskRepo.findFirstByTaskStatusOrderByCreatedAtAsc(TaskStatus.QUEUED);
        if(optionalTask.isEmpty()){
            return Optional.empty();
        }
        Task task=optionalTask.get();
        task.setTaskStatus(TaskStatus.RUNNING);
        Task savedTask=taskRepo.save(task);
        return Optional.of(mapToTaskResponse(savedTask));
    }

    private TaskResponse mapToTaskResponse(Task task){
       TaskResponse response=new TaskResponse();
        response.setId(task.getId());
        response.setDescription(task.getDescription());
        response.setStatus(task.getTaskStatus());
        response.setResult(task.getResult());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }

    private void validateStatusTransition(TaskStatus currentStatus,TaskStatus newStatus){
        boolean valid=(currentStatus==TaskStatus.QUEUED && newStatus==TaskStatus.RUNNING)
                || (currentStatus==TaskStatus.RUNNING && (newStatus==TaskStatus.COMPLETED
                || newStatus==TaskStatus.FAILED));
        if(!valid){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Invalid task status transition: "+currentStatus+"->"+newStatus);
        }
    }
}
