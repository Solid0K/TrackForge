package com.krishu.orchestrator.DTO;

import com.krishu.orchestrator.TaskOperation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreationRequest {
    @NotBlank(message="description cannot be blank")
    @Size(max=500,message="It should exceed the 500 words count")
    private String description;
    @NotNull(message="Task Operation is required")
    private TaskOperation operation;
}
