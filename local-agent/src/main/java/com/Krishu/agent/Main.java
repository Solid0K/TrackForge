package com.Krishu.agent;

import com.Krishu.agent.Client.OrchestratorClient;
import com.Krishu.agent.Executor.TaskExecutor;
import com.Krishu.agent.Model.TaskResponse;

public class Main {
    public static void main(String[] args) {
        OrchestratorClient client=new OrchestratorClient("http://localhost:8080");
        String workSpace=System.getProperty("user.home") + "/trackForgeWorkSpace";
        TaskExecutor executor=new TaskExecutor(workSpace);

        TaskResponse task=client.retrieveTask();
        if(task==null){
            System.out.println("No task available");
            return;
        }

        System.out.println("Task received!");
        System.out.println("Task ID: " + task.getId());
        System.out.println("Description: " + task.getDescription());
        System.out.println("Operation: " + task.getOperation());

        String result=executor.execute(task.getOperation());
        System.out.println("\nTask execution result:");
        System.out.println(result);
    }
}