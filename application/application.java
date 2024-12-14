package application;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;

import server.server;
import server.serverUtils;

import application.tasks.CreateTask;


public class application {
    
    private server currentServer;
    private static final Path BASE_PATH = Paths.get("application/resources");

    //create application
    public application(server in)
    {
        currentServer = in;
        
        
    }

    //Start application
    public void start()
    {
        System.out.println("Application Statup");
        currentServer.addContext("/", serverUtils.createHandle(this::returnIndex));
       
        new CreateTask(currentServer, BASE_PATH);    
        
        
    }

    private void returnIndex(HttpExchange exchange)
    {
        String fileName = "";

        switch (exchange.getRequestURI().getPath()) {
            case "/":
                fileName = "index.html";
                break;
            case "/style.css":
                fileName = "style.css";     
                break;
            case "/script.js":
                fileName = "script.js";
                break;
            default:
                fileName = "index.html";
                break;
        }

        try
        {
            Path filePath = BASE_PATH.resolve(fileName).normalize();
            

            if (!filePath.startsWith(BASE_PATH)) {
                serverUtils.sendResponse(exchange, 403, "Access denied".getBytes(), serverUtils.CONTENT_TYPES.getOrDefault("txt", "text/html; charset=UTF-8"));
                return;
            }

            if (!Files.exists(filePath)) {
                serverUtils.sendResponse(exchange, 404, "File not found".getBytes(), serverUtils.CONTENT_TYPES.getOrDefault("txt", "text/html; charset=UTF-8"));
                return;
            }
            
            byte[] content;      
            String fileExtension = applicationUtils.getFileExtension(fileName);    
            try(InputStream fileStream = Files.newInputStream(filePath)){
                content = fileStream.readAllBytes();
             
            }            

            serverUtils.sendResponse(exchange, 200, content, serverUtils.CONTENT_TYPES.getOrDefault(fileExtension, "text/html; charset=UTF-8"));
        }
        catch(IOException e)
        {
            
            System.err.println("Error serving index page" + e.getMessage());

            try{
                serverUtils.sendResponse(exchange, 500, "Internal server error".getBytes(), serverUtils.CONTENT_TYPES.getOrDefault("txt", "text/html; charset=UTF-8"));
            }
            catch(IOException ignored)
            {
                //already in error handle do nothing.
            }
        }

    }


    

}
