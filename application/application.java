package application;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;

import server.server;
import server.serverUtils;


public class application {
    
    private server currentServer;

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
            default:
                fileName = "index.html";
                break;
        }

        try
        {
            Path indexPath = Paths.get(fileName);

            if (!Files.exists(indexPath)) {
                serverUtils.sendResponse(exchange, 404, "File not found".getBytes(), "text/plain; charset=UTF-8");
                return;
            }
            
            byte[] content;
            try(InputStream fileStream = Files.newInputStream(indexPath)){
                content = fileStream.readAllBytes();
                fileStream.close();
            }            

            serverUtils.sendResponse(exchange, 200, content, "text/html; charset=UTF-8");
        }
        catch(IOException e)
        {
            
            System.err.println("Error serving index page" + e.getMessage());

            try{
                serverUtils.sendResponse(exchange, 500, "Internal server error".getBytes(), "text/html; charset=UTF-8");
            }
            catch(IOException ignored)
            {
                //already in error handle do nothing.
            }
        }

    }


    

}
