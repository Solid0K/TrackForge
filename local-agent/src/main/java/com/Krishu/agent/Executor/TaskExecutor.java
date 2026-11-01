package com.Krishu.agent.Executor;

import com.Krishu.agent.Model.TaskOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TaskExecutor {
    private final Path workSpacePath;

    public TaskExecutor(String workSpacePath){
        this.workSpacePath=Path.of(workSpacePath)
                .toAbsolutePath().normalize();
    }

    public String execute(TaskOperation operation){
        if(operation==null){
            throw new IllegalArgumentException("Provide a valid task operation");
        }
        return switch(operation){
            case LIST_WORKSPACE_FILES -> listWorkSpaceFile();
        };
    }

    private String listWorkSpaceFile(){
        if(!Files.isDirectory(workSpacePath)){
            throw new IllegalStateException("Working Directory doesnt exists");
        }
        try(Stream<Path> paths=Files.list(workSpacePath)){
            List<String> files=paths.map(path->path.getFileName().toString()).toList();
            return String.join("\n",files);
        }catch(IOException e){
            throw new RuntimeException("Cannot workSpace path");
        }
    }
}
