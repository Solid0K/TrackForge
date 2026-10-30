package com.Krishu.agent.Client;

import com.Krishu.agent.Model.TaskResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OrchestratorClient {

    private final HttpClient httpClient;
    private final String baseUrl;
    private final ObjectMapper objectMapper;

    public OrchestratorClient(String baseUrl){
        this.httpClient= HttpClient.newHttpClient();
        this.baseUrl=baseUrl;
        this.objectMapper=new ObjectMapper();
    }

    public TaskResponse retrieveTask(){
        try{
            HttpRequest request=HttpRequest.newBuilder().uri(URI.create(baseUrl+"/task/takeTask"))
                    .POST(HttpRequest.BodyPublishers.noBody()).
                    header("Content-Type","application/json").build();
            HttpResponse<String> response=httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode()==200){
                return objectMapper.readValue(response.body(),TaskResponse.class);
            }
            if(response.statusCode()==204){
                return null;
            }
            throw new RuntimeException("Failed to claim a task,HTTP status code: "+response.statusCode());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
