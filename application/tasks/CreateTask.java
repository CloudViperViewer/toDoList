package application.tasks;

import java.nio.file.Path;

import com.sun.net.httpserver.HttpExchange;

import server.server;
import server.serverUtils;

public class CreateTask {
    
    /*Server of toDoList */
    private server currentServer;
    private Path basePath;

   

    /*
     * Create task constructor
     * @param activeServer
     */
    public CreateTask(server server_in, Path path_in)
    {
        
        setCurrentServer(server_in);
        basePath = path_in;
        currentServer.addContext("tasks/createTask", serverUtils.createHandle(this::addTask));
     
        
    }


    private void addTask(HttpExchange exchange)
    {
        System.out.println("Create Task");
    }



    /*Setters*/

    public void setCurrentServer(server activeServer)
    {
        if(activeServer == null)
        {
            throw new IllegalArgumentException("server cannot be null");
        }

        currentServer = activeServer;
    }

}
