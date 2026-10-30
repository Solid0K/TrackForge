package com.krishu.orchestrator.Model;

import com.krishu.orchestrator.TaskStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length=500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false,length=20)
    private TaskStatus taskStatus;

    @Column(columnDefinition = "TEXT")
    private String result;

    @Column(nullable=false,updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Task(String description){
        this.description=description;
        this.taskStatus=TaskStatus.QUEUED;
    }

    protected Task() {}

    @PrePersist
    protected void onCreate(){
        this.createdAt=Instant.now();
        this.updatedAt=Instant.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=Instant.now();
    }

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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
