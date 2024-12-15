package application.tasks;

import java.nio.file.Path;

import javax.imageio.IIOException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;

import application.applicationUtils;
import server.server;
import server.serverUtils;

public class CreateTask {
    
    /*Server of toDoList */
    private server currentServer;
    private Path basePath;

    private final String API_PATH = "/api/v1/create-task";
    private final String TASK_RESOURCE_SUB_PATH = "tasks";

    /*
     * Create task constructor
     * @param activeServer
     */
    public CreateTask(server server_in, Path path_in)
    {
        if(path_in == null || !Files.exists(path_in))
        {
            throw new IllegalArgumentException("Invalid base path");
        }
        
        setCurrentServer(server_in);
        basePath = path_in.resolve(TASK_RESOURCE_SUB_PATH).normalize();        
        currentServer.addContext(API_PATH, serverUtils.createHandle(this::addTask));
     
        
    }


    private void addTask(HttpExchange exchange)
    {
        
        String fileName;
        String fileExtension;
        Path filePath;
        byte[] content;

        /*Get File Name */
        switch (exchange.getRequestURI().getPath()) {
            case API_PATH:
                fileName = "createTaskPopup.html";
                break;
        
            default:
                fileName = "createTaskPopup.html";
                break;
        }
        
        try{

            filePath = basePath.resolve(fileName).normalize();

            /*Access denied */
            if (!filePath.startsWith(basePath)) {
                serverUtils.sendResponse(exchange, serverUtils.REPONSE_CODES.getOrDefault("Access denied", 404), "Access denied".getBytes(), serverUtils.CONTENT_TYPES.getOrDefault("txt", "text/html; charset=UTF-8"));
                return;
            }

            /*File not found */
            if (!Files.exists(filePath)) {
                serverUtils.sendResponse(exchange, 
                serverUtils.REPONSE_CODES.getOrDefault("File not found", 404),
                "File not found".getBytes(), 
                serverUtils.CONTENT_TYPES.getOrDefault("txt", "text/html; charset=UTF-8"));
            }

            fileExtension = applicationUtils.getFileExtension(fileName);

            try(InputStream fileStream = Files.newInputStream(filePath)){
                content = fileStream.readAllBytes();
            }

            serverUtils.sendResponse(exchange, 
            serverUtils.REPONSE_CODES.getOrDefault("Success", 200), content, serverUtils.CONTENT_TYPES.getOrDefault(fileExtension, "text/html; charset=UTF-8"));
            System.out.println("Create task");
        }
        catch(IOException e)
        {
            try{
                System.err.println("Error serving file " + e.getMessage());

                serverUtils.sendResponse(exchange, 
                serverUtils.REPONSE_CODES.getOrDefault("Internal server error", 404), 
                "Internal server error".getBytes(), 
                serverUtils.CONTENT_TYPES.getOrDefault("txt", "text/html; charset=UTF-8"));
            }catch(IOException ignored){
                /*Already in error handle */
            }
        }
        
    }



    /*Setters*/

    public void setCurrentServer(server activeServer)
    {
        if(activeServer == null)
        {
            throw new IllegalArgumentException("server cannot be null");
        }

        if(!activeServer.isRunning())
        {
            throw new IllegalArgumentException("server must be running");
        }

        currentServer = activeServer;
    }

}
