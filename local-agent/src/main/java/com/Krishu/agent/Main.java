package com.Krishu.agent;

import com.Krishu.agent.Client.OrchestratorClient;
import com.Krishu.agent.Model.TaskResponse;

public class Main {
    public static void main(String[] args) {
        OrchestratorClient client=new OrchestratorClient("http://localhost:8080");

        TaskResponse task=client.retrieveTask();
        if(task==null){
            System.out.println("No task available");
        }else{
            System.out.println("Task received!");
            System.out.println("ID: " + task.getId());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Status: " + task.getStatus());
        }
    }
}