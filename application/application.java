package application;


import java.io.InputStream;
import java.nio.file.Files;
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
        try
        {
            InputStream fileStream = Files.newInputStream(Paths.get("index.html"));
            byte[] content = fileStream.readAllBytes();
            fileStream.close();

            serverUtils.sendResponse(exchange, 200, content, "text/html; charset=UTF-8");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    

}
