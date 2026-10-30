package com.krishu.orchestrator.DTO;

import com.krishu.orchestrator.TaskStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskRequest {
    @NotNull
    private TaskStatus taskStatus;
    @Size(max=10000)
    private String result;
}
