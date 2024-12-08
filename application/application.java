package application;


import java.io.InputStream;
import java.io.OutputStream;
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

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, content.length);
            OutputStream os = exchange.getResponseBody();
            os.write(content);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }


    

}
