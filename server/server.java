package server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;




public class server {
    
    private InetSocketAddress address;
    private HttpServer server;
    private boolean isRunning = false;


    //Set basic server data and start up
    public void start() throws Exception
    {
        //Set address
        address = new InetSocketAddress(8080);

        //Create server
        server = HttpServer.create(address, 0);

        server.start();
        isRunning = true;
        System.out.print("Server listening on port 8080....\n");
    }


    public void addContext(String path, HttpHandler handle)
    {
        server.createContext(path, handle);
    }
    

    public boolean isRunning()
    {
        return isRunning;
    }
  

}


