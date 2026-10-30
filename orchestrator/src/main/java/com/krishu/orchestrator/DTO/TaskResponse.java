package com.krishu.orchestrator.DTO;

import com.krishu.orchestrator.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class TaskResponse {
    private UUID id;
    private String description;
    private TaskStatus status;
    private String result;
    private Instant createdAt;
    private Instant updatedAt;
}
