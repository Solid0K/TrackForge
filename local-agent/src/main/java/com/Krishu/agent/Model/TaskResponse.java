package com.Krishu.agent.Model;

import com.Krishu.agent.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public class TaskResponse {
    private UUID id;
    private String description;
    private TaskStatus status;
    private String result;
    private String  createdAt;
    private String updatedAt;
    private TaskOperation operation;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setOperation(TaskOperation operation){
        this.operation=operation;
    }

    public TaskOperation getOperation(){
        return this.operation;
    }
}
